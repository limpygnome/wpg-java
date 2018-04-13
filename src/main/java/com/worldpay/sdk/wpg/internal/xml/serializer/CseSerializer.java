package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class CseSerializer
{

    public static void decorateOrder(XmlBuildParams params, String encryptedData, Address cardHolderAddress)
    {
        XmlBuilder builder = params.xmlBuilder();

        builder.e("paymentDetails").e("CSE-DATA");

        // add encrypted data
        builder.e("encryptedData").cdata(encryptedData).up();

        // add cardholder address
        builder.e("cardAddress");
        AddressSerializer.decorateCurrentElement(params, cardHolderAddress);

        builder.up();

        // reset to order element
        builder.up().up();
    }

}
