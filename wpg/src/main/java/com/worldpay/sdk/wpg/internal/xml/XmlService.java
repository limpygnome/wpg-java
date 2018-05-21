package com.worldpay.sdk.wpg.internal.xml;

import com.worldpay.sdk.wpg.connection.Environment;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * THE XML service / end-point to be used.
 */
public enum XmlService
{

    /**
     * Payment service.
     */
    PAYMENT(
            "paymentService",
            "-//Worldpay//DTD Worldpay PaymentService v1//EN",
            "http://dtd.worldpay.com/paymentService_v1.dtd",
            "1.4",
            "https://secure-test.worldpay.com/jsp/merchant/xml/paymentService.jsp",
            "https://secure.worldpay.com/jsp/merchant/xml/paymentService.jsp"
    ),

    /**
     * Batch (modifications) service.
     */
    BATCH(
            "batchService",
            "-//Worldpay//DTD Worldpay batchService v1//EN",
            "http://dtd.worldpay.com/batchService_v1.dtd",
            "1.0",
            "https://secure-test.worldpay.com/jsp/merchant/xml/batch.html",
            "https://secure.worldpay.com/jsp/merchant/xml/batch.html"
    );

    public String XML_ROOT_ELEMENT;
    public String XML_DOCTYPE_PUBLIC_ID;
    public String XML_DOCTYPE_SYSTEM_ID;
    public String VERSION;
    public URL SANDBOX_URL;
    public URL LIVE_URL;

    XmlService(String XML_ROOT_ELEMENT, String XML_DOCTYPE_PUBLIC_ID, String XML_DOCTYPE_SYSTEM_ID, String VERSION, String sandboxLive, String liveUrl)
    {
        try
        {
            this.XML_ROOT_ELEMENT = XML_ROOT_ELEMENT;
            this.XML_DOCTYPE_PUBLIC_ID = XML_DOCTYPE_PUBLIC_ID;
            this.XML_DOCTYPE_SYSTEM_ID = XML_DOCTYPE_SYSTEM_ID;
            this.VERSION = VERSION;
            this.SANDBOX_URL = new URL(sandboxLive);
            this.LIVE_URL = new URL(liveUrl);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException("Failed to setup XML servive URLs");
        }
    }

    public URL getUrl(Environment environment)
    {
        switch (environment)
        {
            case SANDBOX:
                return SANDBOX_URL;
            case PRODUCTION:
                return LIVE_URL;
            default:
                throw new IllegalArgumentException("Environment not supported: " + environment);
        }
    }

}
