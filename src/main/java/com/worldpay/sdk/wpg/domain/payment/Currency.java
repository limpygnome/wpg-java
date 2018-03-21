package com.worldpay.sdk.wpg.domain.payment;

public enum Currency
{
    GBP("GBP"),
    EUR("EUR"),
    BOB("BOB");

    public final String ISO4217_CURRENCY_CODE;

    Currency(String code)
    {
        this.ISO4217_CURRENCY_CODE = code;
    }

}
