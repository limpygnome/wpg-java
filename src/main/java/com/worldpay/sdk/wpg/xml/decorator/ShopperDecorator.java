package com.worldpay.sdk.wpg.xml.decorator;

import com.jamesmurty.utils.XMLBuilder2;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;

public class ShopperDecorator
{
    public static void decorate(XmlBuildParams params, Shopper shopper)
    {
        XMLBuilder2 builder = params.xmlBuilder2();

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
        builder.up(3);
    }

    private static void decorateBrowser(XMLBuilder2 builder, ShopperBrowser browser)
    {
        // TODO later
    }

}
