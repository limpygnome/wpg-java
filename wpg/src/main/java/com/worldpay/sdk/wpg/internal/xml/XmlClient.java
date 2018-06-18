package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.Constants;
import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.connection.factory.ConnectionFactory;
import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.logging.SanitisedLogger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URL;
import java.util.Base64;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlClient
{
    private static final Pattern PAGE_ERROR_EXTRACTOR = Pattern.compile("(?:.+)<p>([^<]+)(?:.+)", Pattern.MULTILINE | Pattern.DOTALL);

    private static final Logger logger = Logger.getLogger(XmlClient.class.getName());

    public XmlResponse send(XmlBuildParams params)
            throws WpgRequestException, WpgConnectionException, WpgErrorResponseException
    {
        GatewayContext gatewayContext = params.gatewayContext();
        SessionContext sessionContext = params.sessionContext();
        XmlBuilder builder = params.xmlBuilder();

        ConnectionFactory connectionFactory = gatewayContext.getConnectionFactory();
        Socket socket = null;

        try
        {
            Environment environment = gatewayContext.getEnvironment();
            URL url = params.getEndpoint().getUrl(environment);

            // build request
            byte[] request = buildRequest(url, gatewayContext, sessionContext, builder);

            // open connection
            String hostName = url.getHost();
            int port = url.getPort();

            if (port < 1)
            {
                port = 443;
            }

            socket = connectionFactory.get(gatewayContext, hostName, port);

            // write payload
            OutputStream os = socket.getOutputStream();
            os.write(request);
            os.flush();

            // handle response
            XmlResponse response = readResponse(connectionFactory, socket, sessionContext, params);
            return response;
        }
        catch (IOException e)
        {
            // Unexpected problem, release the socket prematurely
            if (socket != null)
            {
                connectionFactory.release(socket);
            }

            throw new WpgConnectionException(e);
        }
    }

    private byte[] buildRequest(URL url, GatewayContext gatewayContext, SessionContext sessionContext, XmlBuilder builder) throws WpgRequestException
    {
        try
        {
            String xml = builder.toString();
            byte[] payload = xml.getBytes("UTF-8");
            byte[] headers = buildHeaders(url, gatewayContext, sessionContext, payload.length);

            byte[] request = new byte[headers.length + payload.length];
            System.arraycopy(headers, 0, request, 0, headers.length);
            System.arraycopy(payload, 0, request, headers.length, payload.length);

            // Debug logging
            String text = new String(request, "UTF-8");
            SanitisedLogger.log(logger, Level.FINEST, "Making request to gateway:\n" + text);

            return request;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new WpgRequestException("Failed to build request", e);
        }
    }

    private byte[] buildHeaders(URL url, GatewayContext gatewayContext, SessionContext sessionContext, long payloadSize) throws WpgRequestException
    {
        try
        {
            UserPassAuth auth = (UserPassAuth) gatewayContext.getAuth();
            String authHeaderValue = authHeader(auth.getUser() + ":" + auth.getPass());

            StringBuilder buff = new StringBuilder();
            buff.append("POST ").append(url.getPath()).append(" HTTP/1.1").append("\r\n");
            buff.append("Host: ").append(url.getHost()).append("\r\n");
            buff.append("Content-Type: text/xml; charset=utf-8\r\n");
            buff.append("Authorization: Basic " + authHeaderValue).append("\r\n");
            buff.append(Constants.STATS_HEADER_KEY).append(": ").append(Constants.STATS_HEADER_VALUE).append("\r\n");

            // append headers
            for (Map.Entry<String, String> header : sessionContext.getHeaders().entrySet())
            {
                buff.append(header.getKey()).append(": ").append(header.getValue()).append("\r\n");
            }

            // append content/payload length
            buff.append("Content-Length: ").append(payloadSize).append("\r\n");
            buff.append("\r\n");

            byte[] headers = buff.toString().getBytes("UTF-8");
            return headers;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new WpgRequestException("Failed to prepare request", e);
        }
    }

    private String authHeader(String value)
    {
        try
        {
            String encoded = Base64.getEncoder().encodeToString(value.getBytes("UTF-8"));
            return encoded;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("UTF-8 charset not available", e);
        }
    }

    private XmlResponse readResponse(ConnectionFactory connectionFactory, Socket socket, SessionContext sessionContext, XmlBuildParams params)
            throws IOException, WpgRequestException, WpgErrorResponseException
    {
        // read raw response
        InputStream is = socket.getInputStream();

        ByteArrayOutputStream baos = new ByteArrayOutputStream(4096);
        byte[] buffer = new byte[4096];
        int bytesRead;
        boolean complete = false;
        HttpResponse httpResponse = null;

        while (!complete && (bytesRead = is.read(buffer)) > 0)
        {
            // write latest data read
            baos.write(buffer, 0, bytesRead);

            // parse as response and check whether full response received yet
            httpResponse = new HttpResponse(baos.toByteArray());
            complete = httpResponse.isComplete();
        }

        // release socket early as possible
        connectionFactory.release(socket);

        if (httpResponse == null || !httpResponse.isComplete())
        {
            throw new WpgRequestException("Incomplete response from gateway, connection may have ended prematurely - response: " + (httpResponse != null ? "yes" : "no"));
        }

        // output response
        String fullResponse = httpResponse.getFull();
        SanitisedLogger.log(logger, Level.FINEST, "Response from gateway:\n" + fullResponse);

        // copy cookies to session
        String cookies = httpResponse.getHeader("Set-Cookie");
        if (cookies != null)
        {
            sessionContext.addHeader("Cookies", cookies);
        }

        // deserialize
        String body = httpResponse.getBody();

        try
        {
            // check not empty or page response
            if (body == null || body.length() == 0)
            {
                throw new WpgMalformedException("Empty response from server", httpResponse);
            }

            // attempt to parse
            XmlBuilder builder = XmlBuilder.parse(params.getEndpoint(), body);
            XmlResponse response = new XmlResponse(params.sessionContext(), httpResponse, builder);
            return response;
        }
        catch (WpgMalformedException e)
        {
            if (body != null)
            {
                // Check for network error from WPG
                if ("Temporary Failure, please Retry".equals(body))
                {
                    throw new WpgRequestException("Failed to make request - networking issue on gateway - " + body);
                }

                // Check for generic HTTP error page and extract messagex
                Matcher matcher = PAGE_ERROR_EXTRACTOR.matcher(body);

                if (matcher.matches())
                {
                    String pageError = matcher.group(1).trim();
                    throw new WpgRequestException("Failed to make request - message from server: " + pageError);
                }
            }
            throw new WpgRequestException("Failed to parse XML", e);
        }
    }

}
