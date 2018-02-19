package com.worldpay.sdk.wpg.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class XmlBuilder
{
    private Document document;
    private Element current;

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

    public XmlBuilder cdata(String value)
    {
        Text text = document.createTextNode(value);
        current.appendChild(text);
        return this;
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

}
