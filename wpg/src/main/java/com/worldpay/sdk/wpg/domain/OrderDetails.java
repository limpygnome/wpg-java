package com.worldpay.sdk.wpg.domain;

import com.worldpay.sdk.wpg.builder.RandomIdentifier;
import com.worldpay.sdk.wpg.domain.payment.Amount;

import java.util.UUID;

public class OrderDetails
{
    private static final int ID_MAX_LEN = 32;

    private String orderCode;
    private String description;
    private Amount amount;

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
            this.orderCode = RandomIdentifier.generate(ID_MAX_LEN);
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
