package com.worldpay.sdk.wpg.request.hosted;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodFilter;
import com.worldpay.sdk.wpg.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.xml.XmlRequest;
import com.worldpay.sdk.wpg.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.xml.serializer.PaymentMethodMaskSerializer;
import com.worldpay.sdk.wpg.xml.serializer.ShopperSerializer;

public class HostedPaymentPagesRequest extends XmlRequest
{
    // Mandatory
    private OrderDetails orderDetails;
    private Shopper shopper;
    private Address billingAddress;

    // Optional
    private Address shippingAddress;
    private PaymentMethodFilter paymentMethodFilter;

    public HostedPaymentPagesRequest()
    {
    }

    public HostedPaymentPagesRequest(OrderDetails orderDetails, Shopper shopper, Address billingAddress)
    {
        this.orderDetails = orderDetails;
        this.shopper = shopper;
        this.billingAddress = billingAddress;
    }

    public HostedPaymentPagesRequest(OrderDetails orderDetails, Shopper shopper, Address billingAddress, Address shippingAddress, PaymentMethodFilter paymentMethodFilter)
    {
        this.orderDetails = orderDetails;
        this.shopper = shopper;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.paymentMethodFilter = paymentMethodFilter;
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorate(params, orderDetails);
        PaymentMethodMaskSerializer.decorate(params, paymentMethodFilter);
        ShopperSerializer.decorate(params, shopper);
        AddressSerializer.decorate(params, billingAddress, shippingAddress);
    }

    public HostedPaymentPagesRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    public HostedPaymentPagesRequest shopper(Shopper shopper)
    {
        this.shopper = shopper;
        return this;
    }

    public HostedPaymentPagesRequest billingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
        return this;
    }

    public HostedPaymentPagesRequest shippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public HostedPaymentPagesRequest filter(PaymentMethodFilter paymentMethodFilter)
    {
        this.paymentMethodFilter = paymentMethodFilter;
        return this;
    }

}
