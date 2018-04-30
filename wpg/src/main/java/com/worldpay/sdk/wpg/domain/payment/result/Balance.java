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

    /**
     * Describes the status of the payment, depending on your service level.
     *
     * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/reference/thepaymentprocess.html">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/reference/thepaymentprocess.html</a>
     * @return Account type
     */
    public String getAccountType()
    {
        return accountType;
    }

    /**
     * @return Payment amount
     */
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
