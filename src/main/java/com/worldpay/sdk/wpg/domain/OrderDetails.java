package com.worldpay.sdk.wpg.domain;

import com.worldpay.sdk.wpg.domain.payment.Amount;

import java.util.UUID;

public class OrderDetails
{
    private String orderCode;
    private String description;
    private Amount amount;

    public OrderDetails(Amount amount)
    {
        this(null, null, amount);
    }

    public OrderDetails(String description, Amount amount)
    {
        this(null, description, amount);
    }

    public OrderDetails(String orderCode, String description, Amount amount)
    {
        this.orderCode = orderCode;
        this.description = description;
        this.amount = amount;

        if (orderCode == null)
        {
            this.orderCode = UUID.randomUUID().toString()
        }
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public void setOrderCode(String orderCode)
    {
        this.orderCode = orderCode;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Amount getAmount()
    {
        return amount;
    }

    public void setAmount(Amount amount)
    {
        this.amount = amount;
    }

}
