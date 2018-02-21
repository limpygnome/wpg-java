package com.worldpay.sdk.wpg.xml.serializer;

import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class ShopperSerializer
{
    public static void decorate(XmlBuildParams params, Shopper shopper)
    {
        XmlBuilder builder = params.xmlBuilder();

        // Get to shopper element
        builder.e("submit")
                .e("order")
                .e("shopper");

        // Append details
        if (shopper.getEmail() == null)
        {
            throw new IllegalArgumentException("Shopper e-mail address is mandatory");
        }

        builder.e("shopperEmailAddress").cdata(shopper.getEmail()).up();

        if (shopper.getShopperId() != null)
        {
            builder.e("authenticatedShopperID").cdata(shopper.getShopperId()).up();
        }

        ShopperBrowser browser = shopper.getBrowser();

        if (browser != null)
        {
            decorateBrowser(builder, browser);
        }

        // Reset
        builder.reset();
    }

    private static void decorateBrowser(XmlBuilder builder, ShopperBrowser browser)
    {
        // TODO later
    }

}
