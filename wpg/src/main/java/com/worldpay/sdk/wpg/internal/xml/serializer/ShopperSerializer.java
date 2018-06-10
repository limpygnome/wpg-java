package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.shopper.ShopperBrowser;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class ShopperSerializer
{
    public static void decorateOrder(XmlBuildParams params, Shopper shopper)
    {
        if (shopper != null)
        {
            XmlBuilder builder = params.xmlBuilder();

            // Get to shopper element
            builder.e("shopper");

            // Append details
            if (shopper.getEmail() != null)
            {
                builder.e("shopperEmailAddress").cdata(shopper.getEmail()).up();
            }

            if (shopper.getShopperId() != null)
            {
                builder.e("authenticatedShopperID").cdata(shopper.getShopperId()).up();
            }

            ShopperBrowser browser = shopper.getBrowser();
            decorateBrowser(builder, browser);

            // Reset to order element
            builder.up();
        }
    }

    private static void decorateBrowser(XmlBuilder builder, ShopperBrowser browser)
    {
        if (browser != null)
        {
            builder.e("browser")
                    .e("acceptHeader").cdata(browser.getAcceptHeader()).up()
                    .e("userAgentHeader").cdata(browser.getUserAgentHeader()).up()
                    .up();
        }
    }

}
