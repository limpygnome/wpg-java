package com.worldpay.sdk.wpg.domain.payment;

public class Amount
{
    private Currency currency;
    private long exponent;
    private long value;

    public Amount(Currency currency, long exponent, long value)
    {
        this.currency = currency;
        this.exponent = exponent;
        this.value = value;
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public void setCurrency(Currency currency)
    {
        this.currency = currency;
    }

    public long getExponent()
    {
        return exponent;
    }

    public void setExponent(long exponent)
    {
        this.exponent = exponent;
    }

    public long getValue()
    {
        return value;
    }

    public void setValue(long value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "Amount{" +
                "currency=" + currency +
                ", exponent=" + exponent +
                ", value=" + value +
                '}';
    }

}
