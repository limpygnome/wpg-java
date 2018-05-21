package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.XmlService;

public class RedirectUrlXmlAdapter
{

    public RedirectUrl read(XmlResponse response) throws WpgMalformedXmlException
    {
        HttpResponse httpResponse = response.getResponse();
        String xml = httpResponse.getBody();
        XmlBuilder builder = XmlBuilder.parse(XmlService.PAYMENT, xml);

        if (builder.hasE("reply") && builder.hasE("orderStatus") && builder.hasE("reference"))
        {
            String url = builder.cdata();
            RedirectUrl redirectUrl = new RedirectUrl(url);
            return redirectUrl;
        }
        else
        {
            throw new WpgMalformedResponseException(response);
        }
    }

}
