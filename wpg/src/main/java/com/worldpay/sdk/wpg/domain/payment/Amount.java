package com.worldpay.sdk.wpg.domain.payment;

public class Amount
{
    private final Currency currency;
    private final long exponent;
    private final long value;
    private final DebitCreditIndicator debitCreditIndicator;

    public Amount(Currency currency, long exponent, long value)
    {
        this.currency = currency;
        this.exponent = exponent;
        this.value = value;
        this.debitCreditIndicator = null;
    }

    public Amount(Currency currency, long exponent, long value, DebitCreditIndicator debitCreditIndicator)
    {
        this.currency = currency;
        this.exponent = exponent;
        this.value = value;
        this.debitCreditIndicator = debitCreditIndicator;
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public long getExponent()
    {
        return exponent;
    }

    public long getValue()
    {
        return value;
    }

    public DebitCreditIndicator getDebitCreditIndicator()
    {
        return debitCreditIndicator;
    }

    @Override
    public String toString()
    {
        return "Amount{" +
                "currency=" + currency +
                ", exponent=" + exponent +
                ", value=" + value +
                ", debitCreditIndicator=" + debitCreditIndicator +
                '}';
    }

}
