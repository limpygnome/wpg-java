package com.worldpay.sdk.wpg.request.hosted;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodTypeFilter;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.RedirectUrlXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.PaymentMethodTypeMaskSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;

/**
 * Generates an order for taking payments using the Hosted Payment Pages (HPP) platform.
 *
 * This request will return a URL, which can be used to redirect the shopper or with the JavaScript SDK (for embedded
 * payments).
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/hostedintegration/quickstart.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/hostedintegration/quickstart.htm</a>
 */
public class HostedPaymentPagesRequest extends XmlRequest<RedirectUrl>
{
    // Mandatory
    private OrderDetails orderDetails;

    // Optional
    private Shopper shopper;
    private Address billingAddress;
    private Address shippingAddress;
    private PaymentMethodTypeFilter paymentMethodTypeFilter;

    public HostedPaymentPagesRequest()
    {
    }

    public HostedPaymentPagesRequest(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public HostedPaymentPagesRequest(OrderDetails orderDetails, Shopper shopper)
    {
        this.orderDetails = orderDetails;
        this.shopper = shopper;
    }

    public HostedPaymentPagesRequest(OrderDetails orderDetails, Shopper shopper, Address billingAddress, Address shippingAddress, PaymentMethodTypeFilter paymentMethodTypeFilter)
    {
        this.orderDetails = orderDetails;
        this.shopper = shopper;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.paymentMethodTypeFilter = paymentMethodTypeFilter;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notNull(orderDetails, "Order details are mandatory");
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorateAndStartOrder(params, orderDetails);
        PaymentMethodTypeMaskSerializer.decorate(params, paymentMethodTypeFilter);
        ShopperSerializer.decorateOrder(params, shopper);
        AddressSerializer.decorateOrder(params, billingAddress, shippingAddress);
        OrderDetailsSerializer.decorateFinishOrder(params);
    }

    @Override
    protected RedirectUrl adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
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

    public HostedPaymentPagesRequest filter(PaymentMethodTypeFilter paymentMethodTypeFilter)
    {
        this.paymentMethodTypeFilter = paymentMethodTypeFilter;
        return this;
    }

}
