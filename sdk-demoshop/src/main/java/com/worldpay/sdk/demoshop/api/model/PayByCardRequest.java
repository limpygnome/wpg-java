package com.worldpay.sdk.demoshop.api.model;

import com.worldpay.sdk.demoshop.domain.CardDetails;
import com.worldpay.sdk.demoshop.domain.OrderDetails;

public class PayByCardRequest
{
    private CardDetails cardDetails;
    private OrderDetails orderDetails;

    public PayByCardRequest() { }

    public CardDetails getCardDetails()
    {
        return cardDetails;
    }

    public void setCardDetails(CardDetails cardDetails)
    {
        this.cardDetails = cardDetails;
    }

    public OrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }
}
