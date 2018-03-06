package com.worldpay.sdk.wpg.request.apm;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.request.BatchableRequest;

public class PayPalPaymentRequest implements BatchableRequest
{
    // Mandatory
    private OrderDetails orderDetails;
    private Shopper shopper;
    private String successURL;
    private String failureURL;
    private String cancelURL;

    // Optional
    private Address billingAddress;
    private Address shippingAddress;
    private boolean reoccurring;
    private CreateTokenDetails tokenDetails;

}
