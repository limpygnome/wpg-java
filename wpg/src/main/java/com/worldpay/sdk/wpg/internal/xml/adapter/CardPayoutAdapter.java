package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payout.CardPayout;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.PaymentSerializer;

public class CardPayoutAdapter
{

    public CardPayout read(XmlResponse response) throws WpgRequestException, WpgMalformedException
    {
        CardPayout result = null;

        XmlBuilder builder = response.getBuilder();

        if (builder.hasE("paymentService") && builder.hasE("reply"))
        {
            if (builder.hasE("ok"))
            {
                // VISA payout, which has no payment result / details
                result = new CardPayout(null);
            }
            else if (builder.hasE("orderStatus") && builder.hasE("payment"))
            {
                Payment payment = PaymentSerializer.read(builder);
                result = new CardPayout(payment);
            }
        }

        if (result == null)
        {
            throw new WpgMalformedException(response.getResponse());
        }

        return result;
    }

}
