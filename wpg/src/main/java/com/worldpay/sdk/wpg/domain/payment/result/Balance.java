package com.worldpay.sdk.wpg.domain.payment.result;

import com.worldpay.sdk.wpg.domain.payment.Amount;

public class Balance
{
    private final String accountType;
    private final Amount amount;

    public Balance(String accountType, Amount amount)
    {
        this.accountType = accountType;
        this.amount = amount;
    }

    public String getAccountType()
    {
        return accountType;
    }

    public Amount getAmount()
    {
        return amount;
    }

    @Override
    public String toString()
    {
        return "Balance{" +
                "accountType='" + accountType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
