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

    /**
     * Retrieves currency based on ISO4217 currency code.
     *
     * @param code currency code
     * @retur Representation, or throw {@link IllegalArgumentException}
     */
    public static Currency getByCode(String code)
    {
        for (Currency value : values())
        {
            if (value.ISO4217_CURRENCY_CODE.equals(code))
            {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid / unsupported currency code provided");
    }

}
