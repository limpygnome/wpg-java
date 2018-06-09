package com.worldpay.sdk.demoshop.manual;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class NonSdkXmlNotification
{
    public static void main(String[] args)
    {
        try
        {
            // Parse XML
            String response = "xml here";

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature("http://xml.org/sax/features/namespaces", false);
            factory.setFeature("http://xml.org/sax/features/validation", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setXIncludeAware(false);
            factory.setExpandEntityReferences(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));

            NodeList paymentNodes = doc.getElementsByTagName("payment");
            for (int i = 0; i < paymentNodes.getLength(); i++)
            {
                Element node = (Element) paymentNodes.item(i);
                Element lastEvent = (Element) node.getElementsByTagName("lastEvent").item(0);
                System.out.println(lastEvent.getTextContent());
            }
        }
        catch (IOException | SAXException | ParserConfigurationException e)
        {
            e.printStackTrace();
        }
    }

}
