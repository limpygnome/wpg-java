package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import org.w3c.dom.CharacterData;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlBuilder
{
    private Document document;
    private Element current;

    private XmlBuilder() { }

    public XmlBuilder(String rootTagName)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
            current = document.createElement(rootTagName);
            document.appendChild(current);
        }
        catch (ParserConfigurationException e)
        {
            throw new RuntimeException("Unable to prepare xml document", e);
        }
    }

    public XmlBuilder a(String key, String value)
    {
        current.setAttribute(key, value);
        return this;
    }

    public XmlBuilder a(String key, int value)
    {
        String text = String.valueOf(value);
        return a(key, text);
    }

    public String a(String key)
    {
        String value = current.getAttribute(key);
        if (value.length() == 0)
        {
            value = null;
        }
        return value;
    }

    public long aToLong(String key) throws WpgRequestException
    {
        String value = a(key);
        try
        {
            long longValue = Long.parseLong(value);
            return longValue;
        }
        catch (NumberFormatException e)
        {
            throw new WpgRequestException("Failed to read attribute '" + key + "' at " + getPath() + " - value: " + value, e);
        }
    }

    public Integer aToIntegerOptional(String key) throws WpgRequestException
    {
        Integer result = null;
        String value = a(key);

        if (value != null)
        {
            try
            {
                result = Integer.parseInt(value);
            }
            catch (NumberFormatException e)
            {
                throw new WpgRequestException("Failed to read attribute '" + key + "' at " + getPath() + " - value: " + value, e);
            }
        }

        return result;
    }

    public int aToInt(String key) throws WpgRequestException
    {
        String value = a(key);
        try
        {
            int intValue = Integer.parseInt(value);
            return intValue;
        }
        catch (NumberFormatException e)
        {
            throw new WpgRequestException("Failed to read attribute '" + key + "' at " + getPath() + " - value: " + value, e);
        }
    }

    public XmlBuilder e(String name)
    {
        e(name, false);
        return this;
    }

    public XmlBuilder e(String name, boolean addNew)
    {
        // Create if doesn't exist
        NodeList list;

        if (addNew || (list = current.getElementsByTagName(name)).getLength() == 0)
        {
            Element element = document.createElement(name);
            current.appendChild(element);
            current = element;
        }
        else
        {
            current = (Element) list.item(0);
        }

        return this;
    }

    public boolean isCurrentTag(String tagName)
    {
        return current.getTagName().equals(tagName);
    }

    public boolean hasE(String name)
    {
        NodeList list = current.getElementsByTagName(name);
        boolean present = list.getLength() > 0;

        if (present)
        {
            current = (Element) list.item(0);
        }

        return present;
    }

    public XmlBuilder cdata(String value)
    {
        Text text = document.createTextNode(value);
        current.appendChild(text);
        return this;
    }

    public XmlBuilder cdata(int value)
    {
        String text = String.valueOf(value);
        return cdata(text);
    }

    public String cdata()
    {
        String result = null;
        Node firstChild = current.getFirstChild();

        if (firstChild instanceof CharacterData)
        {
            CharacterData data = (CharacterData) firstChild;
            result = data.getData();
        }
        return result;
    }

    public String getCdata(String elementName)
    {
        String result = null;

        // Find node
        NodeList list = current.getElementsByTagName(elementName);
        Element element = (Element) list.item(0);

        if (element != null)
        {
            Node firstChild = element.getFirstChild();

            if (firstChild instanceof CharacterData)
            {
                CharacterData data = (CharacterData) firstChild;
                result = data.getData();
            }
        }

        return result;
    }

    public Long getCdataLong(String elementName) throws WpgRequestException
    {
        String value = getCdata(elementName);
        try
        {
            long longValue = Long.parseLong(value);
            return longValue;
        }
        catch (NumberFormatException e)
        {
            throw new WpgRequestException("Failed to read attribute '" + elementName + "' at " + getPath() + " - value: " + value, e);
        }
    }

    public XmlBuilder up()
    {
        Element element = (Element) current.getParentNode();
        if (element != null)
        {
            current = element;
        }
        return this;
    }

    public XmlBuilder reset()
    {
        current = document.getDocumentElement();
        return this;
    }

    @Override
    public String toString()
    {
        try
        {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMImplementation domImplementation = document.getImplementation();
            DocumentType docType = domImplementation.createDocumentType(
                    "doctype", "-//Worldpay//DTD Worldpay PaymentService v1//EN", "http://dtd.worldpay.com/paymentService_v1.dtd"
            );

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, docType.getPublicId());
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, docType.getSystemId());

            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter(4096);
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(domSource, streamResult);
            String text = writer.toString();
            return text;
        }
        catch (TransformerException e)
        {
            throw new RuntimeException("Failed to convert xml to text", e);
        }
    }

    public static XmlBuilder parse(String text) throws WpgMalformedXmlException
    {
        try
        {
            XmlBuilder xmlBuilder = new XmlBuilder();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputSource inputSource = new InputSource(new StringReader(text));
            xmlBuilder.document = builder.parse(inputSource);
            xmlBuilder.reset();

            return xmlBuilder;
        }
        catch (ParserConfigurationException | SAXException | IOException e)
        {
            throw new WpgMalformedXmlException("Failed to parse response XML", e);
        }
    }

    public static XmlBuilder create()
    {
        XmlBuilder builder = new XmlBuilder("paymentService");
        builder.a("version", "1.4");
        return builder;
    }

    public String getPath()
    {
        String path = "";
        Node parent = current;

        do
        {
            path = parent.getNodeName() + (path.length() > 0 ? "/" : "");
            parent = parent.getParentNode();
        }
        while (parent != null);

        return path.toString();
    }

}
