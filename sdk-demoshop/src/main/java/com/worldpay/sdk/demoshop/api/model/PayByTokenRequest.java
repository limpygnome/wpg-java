package com.worldpay.sdk.demoshop.api.model;

import com.worldpay.sdk.demoshop.domain.ApiOrderDetails;
import com.worldpay.sdk.demoshop.domain.ApiTokenDetails;

public class PayByTokenRequest
{
    private ApiOrderDetails orderDetails;
    private ApiTokenDetails tokenDetails;

    public PayByTokenRequest() { }

    public ApiOrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(ApiOrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public ApiTokenDetails getTokenDetails()
    {
        return tokenDetails;
    }

    public void setTokenDetails(ApiTokenDetails tokenDetails)
    {
        this.tokenDetails = tokenDetails;
    }

}
