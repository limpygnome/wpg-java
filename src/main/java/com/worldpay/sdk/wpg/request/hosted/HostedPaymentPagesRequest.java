package com.worldpay.sdk.wpg.request.hosted;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodFilter;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.RedirectUrlXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.PaymentMethodMaskSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;

public class HostedPaymentPagesRequest extends XmlRequest<RedirectUrl>
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
    protected void validate(XmlBuildParams params)
    {
        // TODO add validation
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorateAndStartOrder(params, orderDetails);
        PaymentMethodMaskSerializer.decorate(params, paymentMethodFilter);
        ShopperSerializer.decorateOrder(params, shopper);
        AddressSerializer.decorateOrder(params, billingAddress, shippingAddress);
        OrderDetailsSerializer.decorateFinishOrder(params);
    }

    @Override
    protected RedirectUrl adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        RedirectUrlXmlAdapter adapter = new RedirectUrlXmlAdapter();
        RedirectUrl redirectUrl = adapter.read(response);
        return redirectUrl;
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
