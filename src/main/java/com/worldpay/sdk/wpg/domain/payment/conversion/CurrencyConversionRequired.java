package com.worldpay.sdk.wpg.domain.payment.conversion;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.exception.WpgRequestException;

public class CurrencyConversionRequired
{
    private final Amount amount;

    public CurrencyConversionRequired(Amount amount) throws WpgRequestException
    {
        this.amount = amount;
    }

    public Amount getAmount()
    {
        return amount;
    }

}
