package com.worldpay.sdk.wpg.request.tokenisation;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.SessionSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.tokenisation.TokenPaymentSerializer;
import com.worldpay.sdk.wpg.request.batch.BatchOrderItem;

public class TokenPaymentRequest extends XmlRequest<PaymentResponse> implements BatchOrderItem
{
    private String paymentTokenId;
    private TokenScope scope;
    private OrderDetails orderDetails;
    private Shopper shopper;

    // Optional
    private Address billingAddress;
    private Address shippingAddress;

    @Override
    protected void validate(XmlBuildParams params)
    {
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorateAndStartOrder(params, orderDetails);
        TokenPaymentSerializer.decorateOrder(params, this);
        SessionSerializer.decorateOrderPaymentDetails(params, shopper);
        ShopperSerializer.decorateOrder(params, shopper);
        AddressSerializer.decorateOrder(params, billingAddress, shippingAddress);
        OrderDetailsSerializer.decorateFinishOrder(params);
    }

    @Override
    protected PaymentResponse adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
        PaymentResponse paymentResponse = adapter.read(response);
        return paymentResponse;
    }

    public String getPaymentTokenId()
    {
        return paymentTokenId;
    }

    public TokenPaymentRequest paymentTokenId(String paymentTokenId)
    {
        this.paymentTokenId = paymentTokenId;
        return this;
    }

    public TokenScope getScope()
    {
        return scope;
    }

    public TokenPaymentRequest scope(TokenScope scope)
    {
        this.scope = scope;
        return this;
    }

    public OrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    public TokenPaymentRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    public Shopper getShopper()
    {
        return shopper;
    }

    public TokenPaymentRequest shopper(Shopper shopper)
    {
        this.shopper = shopper;
        return this;
    }

    public Address getBillingAddress()
    {
        return billingAddress;
    }

    public TokenPaymentRequest billingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
        return this;
    }

    public Address getShippingAddress()
    {
        return shippingAddress;
    }

    public TokenPaymentRequest shippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

}
