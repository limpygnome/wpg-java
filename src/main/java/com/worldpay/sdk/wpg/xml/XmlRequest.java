package com.worldpay.sdk.wpg.xml;

import com.jamesmurty.utils.XMLBuilder2;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.connection.factory.ConnectionFactory;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.Request;
import com.worldpay.sdk.wpg.response.Response;
import com.worldpay.sdk.wpg.xml.XmlResponseRecognizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public abstract class XmlRequest implements Request
{
    private static final URL SANDBOX_URL;
    private static final URL PRODUCTION_URL;

    static
    {
        try
        {
            SANDBOX_URL = new URL("https://secure-test.worldpay.com/jsp/merchant/xml/paymentService.jsp");
            PRODUCTION_URL = new URL("https://secure.worldpay.com/jsp/merchant/xml/paymentService.jsp");
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Response send(GatewayContext gatewayContext, SessionContext sessionContext) throws WpgRequestException, WpgConnectionException
    {
        ConnectionFactory connectionFactory = gatewayContext.getConnectionFactory();
        Socket socket = null;

        try
        {
            // build request
            byte[] request = buildRequest(gatewayContext, sessionContext);

            // open connection
            socket = connectionFactory.get(gatewayContext);

            // write payload
            OutputStream os = socket.getOutputStream();
            os.write(request);
            os.flush();

            // handle response
            Response response = readResponse(socket, sessionContext);
            return response;
        }
        catch (IOException e)
        {
            throw new WpgConnectionException();
        }
        finally
        {
            if (socket != null)
            {
                connectionFactory.release(socket);
            }
        }
    }

    protected abstract void build(XmlBuildParams params);

    private byte[] buildRequest(GatewayContext gatewayContext, SessionContext sessionContext) throws WpgRequestException
    {
        try
        {
            XMLBuilder2 builder = XMLBuilder2.create("paymentService");
            XmlBuildParams params = new XmlBuildParams(gatewayContext, sessionContext, builder);
            build(params);
            String xml = builder.asString();
            byte[] payload = xml.getBytes("UTF-8");
            byte[] headers = buildHeaders(gatewayContext, sessionContext, payload.length);

            byte[] request = new byte[headers.length + payload.length];
            System.arraycopy(headers, 0, request, 0, headers.length);
            System.arraycopy(payload, 0, request, headers.length, payload.length);
            return request;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new WpgRequestException("Failed to build request", e);
        }
    }

    private byte[] buildHeaders(GatewayContext gatewayContext, SessionContext sessionContext, long payloadSize) throws WpgRequestException
    {
        try
        {
            URL url = getUrl(gatewayContext);

            StringBuilder buff = new StringBuilder();
            buff.append("POST ").append(url.getPath()).append(" HTTP/1.1").append("\r\n");
            buff.append("Host: ").append(url.getHost()).append("\r\n");

            // append headers
            for (Map.Entry<String, String> header : sessionContext.getHeaders().entrySet())
            {
                buff.append(header.getKey()).append("=").append(header.getValue()).append("\r\n");
            }

            // append content/payload length
            buff.append("Length: ").append(payloadSize).append("\r\n");
            buff.append("\r\n");

            byte[] headers = buff.toString().getBytes("UTF-8");
            return headers;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new WpgRequestException("Failed to prepare request", e);
        }
    }

    private URL getUrl(GatewayContext gatewayContext) throws WpgRequestException
    {
        switch (gatewayContext.getEnvironment())
        {
            case PRODUCTION:
                return PRODUCTION_URL;
            case SANDBOX:
                return SANDBOX_URL;
            default:
                throw new WpgRequestException("Unknown environment for WPG - " + gatewayContext.getEnvironment());
        }
    }

    private Response readResponse(Socket socket, SessionContext sessionContext) throws IOException
    {
        // read raw response
        StringBuilder rawResponseBuilder = new StringBuilder();
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, "UTF-8");

        try (Reader reader = new BufferedReader(isr))
        {
            // TODO need to check this, seems dodgy for utf-8 chars...
            int c = reader.read();
            if (c > 0)
            {
                rawResponseBuilder.append((char) c);
            }
        }

        // split by headers/text
        String rawResponse = rawResponseBuilder.toString();
        int headerSplit = rawResponse.indexOf("\r\n\r\n");
        String rawHeader = headerSplit > 0 ? rawResponse.substring(0, headerSplit) : "";
        String body = headerSplit > 0 && rawResponse.length() - (headerSplit+4) > 0 ? rawResponse.substring(headerSplit + 4) : "";
        Map<String, String> headers = readHeaders(rawHeader);

        // copy cookies to session
        String cookies = headers.get("Set-Cookie");
        if (cookies != null)
        {
            sessionContext.addHeader("Cookies", cookies);
        }

        // deserialize
        XmlResponseRecognizer recognizer = new XmlResponseRecognizer();
        Response response = recognizer.match(headers, body);
        return response;
    }

    private Map<String, String> readHeaders(String headers)
    {
        Map<String, String> result = new HashMap<>();
        String[] lines = headers.split("\r\n");
        for (String line : lines)
        {
            int sep = line.indexOf(":");
            if (sep > 0 && sep < line.length()+1)
            {
                String key = line.substring(0, sep);
                String value = line.substring(sep + 1);
                result.put(key, value);
            }
        }
        return result;
    }

}
