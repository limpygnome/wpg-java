package com.worldpay.sdk.demoshop.api.model;

import com.worldpay.sdk.demoshop.domain.ApiCardDetails;
import com.worldpay.sdk.demoshop.domain.ApiOrderDetails;

public class PayByCardRequest
{
    private ApiCardDetails cardDetails;
    private ApiOrderDetails orderDetails;

    public PayByCardRequest() { }

    public ApiCardDetails getCardDetails()
    {
        return cardDetails;
    }

    public void setCardDetails(ApiCardDetails cardDetails)
    {
        this.cardDetails = cardDetails;
    }

    public ApiOrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(ApiOrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }
}
