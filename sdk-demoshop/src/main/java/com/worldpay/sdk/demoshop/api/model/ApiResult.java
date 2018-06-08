package com.worldpay.sdk.demoshop.api.model;

import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;

public class ApiResult
{
    private final PaymentResponse paymentResponse;
    private final String redirect;
    private final String error;

    public ApiResult(PaymentResponse paymentResponse, String redirect, String error)
    {
        this.paymentResponse = paymentResponse;
        this.redirect = redirect;
        this.error = error;
    }

    public PaymentResponse getPaymentResponse()
    {
        return paymentResponse;
    }

    public String getRedirect()
    {
        return redirect;
    }

    public String getError()
    {
        return error;
    }

}
