package com.worldpay.sdk.wpg.request.card;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Session;

public class CardPaymentRequest// extends XmlRequest implements BatchableRequest
{
    private OrderDetails details;
    private Address billingAddress;
    private Address shippingAddress;
    private CardDetails cardDetails;
    private Session session;
}
