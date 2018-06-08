package com.worldpay.sdk.demoshop.api.model;

import com.worldpay.sdk.demoshop.domain.ApiHppDetails;
import com.worldpay.sdk.demoshop.domain.ApiOrderDetails;

public class PayByHppRequest
{
    private ApiOrderDetails orderDetails;
    private ApiHppDetails hppDetails;

    public PayByHppRequest() { }

    public ApiOrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(ApiOrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public ApiHppDetails getHppDetails()
    {
        return hppDetails;
    }

    public void setHppDetails(ApiHppDetails hppDetails)
    {
        this.hppDetails = hppDetails;
    }

}
