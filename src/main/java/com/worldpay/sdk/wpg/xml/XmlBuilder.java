package com.worldpay.sdk.wpg.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XmlBuilder
{
    private Document document;
    private Element current;

    public XmlBuilder(String rootElement)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.newDocument();
    }

    public XmlBuilder a(String key, String value)
    {
        document.getDocumentElement();
    }

    public XmlBuilder e(String name)
    {
        e(name, true);
    }

    public XmlBuilder e(String name, boolean addNew)
    {
        // Create if doesn't exist
        NodeList list = current.getElementsByTagName(name);
        if (list.getLength() == 0)
        {
            Element element = document.createElement(name);
            current.appendChild(element);
            current = element;
        }
        else
        {
            current = (Element) list.item(0);
        }
    }

    public XmlBuilder up()
    {
        Element element = (Element) current.getParentNode();
        if (element != null)
        {
            current = element;
        }
    }

    public XmlBuilder reset()
    {
        current = document.getDocumentElement();
    }

}
