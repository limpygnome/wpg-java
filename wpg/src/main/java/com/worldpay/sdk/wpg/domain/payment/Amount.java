package com.worldpay.sdk.wpg.domain.payment;

public class Amount
{
    private final String currencyCode;
    private final long exponent;
    private final long value;
    private final DebitCreditIndicator debitCreditIndicator;

    public Amount(String currencyCode, long exponent, long value)
    {
        this(currencyCode, exponent, value, null);
    }

    public Amount(String currencyCode, long exponent, long value, DebitCreditIndicator debitCreditIndicator)
    {
        this.currencyCode = currencyCode;
        this.exponent = exponent;
        this.value = value;
        this.debitCreditIndicator = debitCreditIndicator;
    }

    /**
     * @return Currency
     */
    public String getCurrencyCode()
    {
        return currencyCode;
    }

    /**
     * @return Exponent of amount
     */
    public long getExponent()
    {
        return exponent;
    }

    /**
     * @return Value
     */
    public long getValue()
    {
        return value;
    }

    /**
     * @return Debited / credited amount; only present in responses
     */
    public DebitCreditIndicator getDebitCreditIndicator()
    {
        return debitCreditIndicator;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Amount amount = (Amount) o;

        if (exponent != amount.exponent) return false;
        if (value != amount.value) return false;
        if (currencyCode != null ? !currencyCode.equals(amount.currencyCode) : amount.currencyCode != null)
            return false;
        return debitCreditIndicator == amount.debitCreditIndicator;
    }

    @Override
    public int hashCode()
    {
        int result = currencyCode != null ? currencyCode.hashCode() : 0;
        result = 31 * result + (int) (exponent ^ (exponent >>> 32));
        result = 31 * result + (int) (value ^ (value >>> 32));
        result = 31 * result + (debitCreditIndicator != null ? debitCreditIndicator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Amount{" +
                "currencyCode=" + currencyCode +
                ", exponent=" + exponent +
                ", value=" + value +
                ", debitCreditIndicator=" + debitCreditIndicator +
                '}';
    }

}
