package com.worldpay.sdk.demoshop.manual;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.UUID;

public class NonSdkPayPalPayment
{
    private static final String MERCHANT_CODE = "merchant code";
    private static final String USER = "xml user";
    private static final String PASS = "xml pass";

    public static void main(String[] args)
    {
        try
        {
            boolean sandbox = args.length >= 1 && args[0] != null ? Boolean.parseBoolean(args[0]) : true;

            // Load XML
            String xml = read(NonSdkPayPalPayment.class.getResourceAsStream("/manual/hpp.xml"));

            // Insert data
            xml = xml.replaceAll("#MERCHANT_CODE#", MERCHANT_CODE)
                    .replace("#ORDER_CODE#", UUID.randomUUID().toString())
                    .replace("#DESCRIPTION#", "test order")
                    .replace("#CURRENCY#", "GBP")
                    .replace("#AMOUNT#", "1300")
                    .replace("#SUCCESS_URL#", "https://success.worldpay.com")
                    .replace("#FAILURE_URL#", "https://failure.worldpay.com")
                    .replace("#CANCEL_URL#", "https://cancel.worldpay.com")
                    .replace("#SHOPPER_IP#", "123.123.123.123")
                    .replace("#SESSION_ID#", "session123")
                    .replace("#SHOPPER_EMAIL#", "shopper@worldpay.com")
                    .replace("#SHOPPER_ID#", "shopper123")
                    .replace("#ACCEPTS_HEADER#", "text/html")
                    .replace("#USERAGENT#", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36")
                    .replace("#TOKEN_REFERENCE#", "tokenref123")
                    .replace("#TOKEN_REASON#", "test token")
                    .replace("#ADDRESS_LINE1#", "address 1")
                    .replace("#ADDRESS_LINE2#", "address 2")
                    .replace("#ADDRESS_LINE3#", "address 3")
                    .replace("#ADDRESS_POSTCODE#", "post code")
                    .replace("#ADDRESS_CITY#", "city")
                    .replace("#ADDRESS_STATE#", "Cambridgeshire")
                    .replace("#ADDRESS_COUNTRY#", "GB");

            // Open up connection
            final String LIVE_URL = "https://" + USER + ":" + PASS + "@secure.worldpay.com/jsp/merchant/xml/paymentService.jsp";
            final String SANDBOX_URL = "https://" + USER + ":" + PASS + "@secure-test.worldpay.com/jsp/merchant/xml/paymentService.jsp";
            URLConnection conn = new URL(sandbox ? SANDBOX_URL : LIVE_URL)
                    .openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String authHeader = Base64.getEncoder().encodeToString((USER + ":" + PASS).getBytes("UTF-8"));
            conn.setRequestProperty("Authorization", "Basic " + authHeader);

            // Write out data
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(xml.getBytes("UTF-8"));
            outputStream.flush();

            // Read response
            String response = read(conn.getInputStream());

            // Convert to XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://xml.org/sax/features/namespaces", false);
            factory.setFeature("http://xml.org/sax/features/validation", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));

            NodeList referenceNodes = doc.getElementsByTagName("reference");

            // Handle response
            if (referenceNodes.getLength() > 0)
            {
                String url = referenceNodes.item(0).getTextContent();
                System.out.println("hpp url: " + url);
            }
            else
            {
                System.out.println("unhandled response: " + response);
            }
        }
        catch (IOException | SAXException | ParserConfigurationException e)
        {
            System.err.println("something went wrong");
            e.printStackTrace();
        }
    }

    private static String read(InputStream inputStream) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = inputStream.read(buffer)) >= 0)
        {
            baos.write(buffer, 0, read);
        }
        byte[] data = baos.toByteArray();
        return new String(data, "UTF-8");
    }

}
