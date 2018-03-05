package com.worldpay.sdk.wpg.xml.serializer;

import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.xml.XmlBuilder;

public class CardDetailsSerializer
{

    public static void decorate(XmlBuildParams params, CardDetails cardDetails)
    {
        if (cardDetails != null)
        {
            XmlBuilder builder = params.xmlBuilder();

            builder.e("submit").e("order").e("paymentDetails");

            // build card element
            // TODO allow scheme to be customised?
            builder.e("CARD-SSL")
                    .e("cardNumber")
                    .cdata(cardDetails.getCardNumber())
                    .up()
                    .e("expiryDate")
                        .e("date")
                        .a("month", String.valueOf(cardDetails.getExpiryMonth()))
                        .a("year", String.valueOf(cardDetails.getExpiryYear()))
                        .up()
                    .up()
                    .e("cardHolderName")
                    .cdata(cardDetails.getCardHolderName())
                    .up()
                    .e("cardAddress");

            AddressSerializer.decorateCurrentElement(params, cardDetails.getCardHolderAddress());

            builder.reset();
        }
    }

}
