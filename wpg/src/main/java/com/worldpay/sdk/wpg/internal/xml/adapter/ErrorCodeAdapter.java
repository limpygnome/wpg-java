package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

public class ErrorCodeAdapter
{

    public static void throwIfPresent(XmlResponse response) throws WpgRequestException, WpgErrorResponseException
    {
        XmlBuilder builder = response.getBuilder();
        XmlBuilder errorTag = builder.getElementByName("error");

        if (errorTag != null)
        {
            handleError(response, errorTag);
        }
    }

    private static void handleError(XmlResponse response, XmlBuilder errorTag) throws WpgRequestException, WpgErrorResponseException
    {
        long code = errorTag.aToLong("code");
        String message = errorTag.cdata();
        throw new WpgErrorResponseException(code, message, response.getResponse());
    }

}
