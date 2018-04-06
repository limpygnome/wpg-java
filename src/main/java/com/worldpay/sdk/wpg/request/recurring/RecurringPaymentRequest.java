package com.worldpay.sdk.wpg.request.recurring;

import com.worldpay.sdk.wpg.domain.payment.Amount;

public class RecurringPaymentRequest
{
    private String orderCode;
    private Amount amount;
    // -- CVC appears to be optional
    private String cvc;

    private String existingOrderCode;
    private String existingMerchantCode;

    public RecurringPaymentRequest(String orderCode, Amount amount, String cvc, String existingOrderCode, String existingMerchantCode)
    {
        this.orderCode = orderCode;
        this.amount = amount;
        this.cvc = cvc;
        this.existingOrderCode = existingOrderCode;
        this.existingMerchantCode = existingMerchantCode;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public RecurringPaymentRequest orderCode(String orderCode)
    {
        this.orderCode = orderCode;
        return this;
    }

    public Amount getAmount()
    {
        return amount;
    }

    public RecurringPaymentRequest amount(Amount amount)
    {
        this.amount = amount;
        return this;
    }

    public String getCvc()
    {
        return cvc;
    }

    public String getExistingOrderCode()
    {
        return existingOrderCode;
    }

    public String getExistingMerchantCode()
    {
        return existingMerchantCode;
    }
}
