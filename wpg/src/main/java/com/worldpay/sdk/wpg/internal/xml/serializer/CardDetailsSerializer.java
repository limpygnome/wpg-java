package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.card.CardDetails;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

public class CardDetailsSerializer
{

    public static void decorateOrder(XmlBuildParams params, CardDetails cardDetails)
    {
        if (cardDetails != null)
        {
            XmlBuilder builder = params.xmlBuilder();

            builder.e("paymentDetails");

            // build card element
            // TODO allow card scheme to be customised?
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
                        .up();

            // cvc
            String cvc = cardDetails.getCvc();
            if (cvc != null && cvc.length() > 0)
            {
                builder.e("cvc").cdata(cvc).up();
            }

            // add card holder address
            if (cardDetails.getCardHolderAddress() != null)
            {
                builder.e("cardAddress");
                AddressSerializer.decorateCurrentElement(params, cardDetails.getCardHolderAddress());
                builder.up();
            }

            // reset to order element
            builder.up().up();
        }
    }

    public static CardDetails read(XmlBuilder builder) throws WpgRequestException
    {
        String cardNumber = builder.getCdata("cardNumber");
        String cardHolderName = builder.getCdata("cardHolderName");
        String cvc = builder.getCdata("cvc");
        String encryptedPAN = builder.getCdata("encryptedPAN");
        Long expiryMonth = null;
        Long expiryYear = null;

        if (builder.hasE("expiryDate"))
        {
            if (builder.hasE("date"))
            {
                try
                {
                    expiryMonth = Long.parseLong(builder.a("month"));
                    expiryYear = Long.parseLong(builder.a("year"));
                }
                catch (NumberFormatException | NullPointerException e)
                {
                }
                builder.up();
            }
            builder.up();
        }

        Address cardHolderAddress = null;
        if (builder.hasE("cardAddress"))
        {
            cardHolderAddress = AddressSerializer.read(builder);
            builder.up();
        }

        /*
            Only return results if we actually parsed something; tokenisation returns an empty instance, with empty date.
            Thus we don't want to give merchants an empty object.
         */

        boolean hasDetails = (cardNumber != null || cardHolderName != null || cvc != null || encryptedPAN != null || cardHolderAddress != null);
        hasDetails |= (expiryMonth != null && expiryYear != null);

        CardDetails result = null;
        if (hasDetails)
        {
            result = new CardDetails(
                    cardNumber, expiryMonth, expiryYear, cardHolderName, cvc, cardHolderAddress, encryptedPAN);
        }

        return result;
    }

}
