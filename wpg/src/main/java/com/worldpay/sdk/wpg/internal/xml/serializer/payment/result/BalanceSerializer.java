package com.worldpay.sdk.wpg.internal.xml.serializer.payment.result;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.result.Balance;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.serializer.AmountSerializer;

public class BalanceSerializer
{

    public static Balance read(XmlBuilder builder) throws WpgRequestException
    {
        Balance result = null;

        if (builder.hasE("balance"))
        {
            String accountType = builder.getCdata("accountType");

            builder.e("amount");
            Amount amount = AmountSerializer.read(builder);
            builder.up();

            if (accountType != null || amount != null)
            {
                result = new Balance(accountType, amount);
            }
        }

        return result;
    }

}
