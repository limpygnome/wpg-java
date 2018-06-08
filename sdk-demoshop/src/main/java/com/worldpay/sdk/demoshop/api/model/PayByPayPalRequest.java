package com.worldpay.sdk.demoshop.api.model;

import com.worldpay.sdk.demoshop.domain.ApiOrderDetails;
import com.worldpay.sdk.demoshop.domain.ApiPayPalDetails;

public class PayByPayPalRequest
{
    private ApiOrderDetails orderDetails;
    private ApiPayPalDetails payPalDetails;

    public PayByPayPalRequest() { }

    public ApiOrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(ApiOrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public ApiPayPalDetails getPayPalDetails()
    {
        return payPalDetails;
    }

    public void setPayPalDetails(ApiPayPalDetails payPalDetails)
    {
        this.payPalDetails = payPalDetails;
    }
}
