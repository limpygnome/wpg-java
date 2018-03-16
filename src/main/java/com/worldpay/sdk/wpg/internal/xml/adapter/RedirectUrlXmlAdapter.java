package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

public class RedirectUrlXmlAdapter
{

    public RedirectUrl read(XmlResponse response) throws WpgMalformedXmlException
    {
        HttpResponse httpResponse = response.getResponse();
        String xml = httpResponse.getBody();
        XmlBuilder builder = XmlBuilder.parse(xml);

        String url = builder.e("reply").e("orderStatus").e("reference").cdata();
        RedirectUrl redirectUrl = new RedirectUrl(url);
        return redirectUrl;
    }

}
