package com.worldpay.sdk.wpg.request.card;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.xml.XmlRequest;
import com.worldpay.sdk.wpg.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.xml.serializer.CardDetailsSerializer;
import com.worldpay.sdk.wpg.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.xml.serializer.SessionSerializer;
import com.worldpay.sdk.wpg.xml.serializer.ShopperSerializer;

public class CardPaymentRequest extends XmlRequest
{
    // Mandatory
    private OrderDetails orderDetails;
    private CardDetails cardDetails;
    private Shopper shopper;

    // Optional
    private Address billingAddress;
    private Address shippingAddress;

    public CardPaymentRequest() { }

    // TODO merge session with shopper
    public CardPaymentRequest(OrderDetails orderDetails, CardDetails cardDetails, Shopper shopper)
    {
        this.orderDetails = orderDetails;
        this.cardDetails = cardDetails;
        this.shopper = shopper;
    }

    public CardPaymentRequest(OrderDetails orderDetails, CardDetails cardDetails, Shopper shopper, Address billingAddress, Address shippingAddress)
    {
        this.orderDetails = orderDetails;
        this.cardDetails = cardDetails;
        this.shopper = shopper;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorate(params, orderDetails);
        CardDetailsSerializer.decorate(params, cardDetails);
        SessionSerializer.decorate(params, shopper);
        ShopperSerializer.decorate(params, shopper);
        AddressSerializer.decorate(params, billingAddress, shippingAddress);
    }

    public CardPaymentRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    public CardPaymentRequest cardDetails(CardDetails cardDetails)
    {
        this.cardDetails = cardDetails;
        return this;
    }

    public CardPaymentRequest shopper(Shopper shopper)
    {
        this.shopper = shopper;
        return this;
    }

    public CardPaymentRequest billingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
        return this;
    }

    public CardPaymentRequest shippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

}
