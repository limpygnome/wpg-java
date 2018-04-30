package com.worldpay.sdk.wpg.request.card;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.CardDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.SessionSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation.CreateTokenDetailsSerializer;
import com.worldpay.sdk.wpg.request.batch.BatchOrderItem;

/**
 * A request to make a payment by submitting card details.
 *
 * Supports tokenisation.
 */
public class CardPaymentRequest extends XmlRequest<PaymentResponse> implements BatchOrderItem
{
    // Mandatory
    private OrderDetails orderDetails;
    private CardDetails cardDetails;
    private Shopper shopper;

    // Optional
    private Address billingAddress;
    private Address shippingAddress;
    private CreateTokenDetails createTokenDetails;

    public CardPaymentRequest() { }

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

    public CardPaymentRequest(OrderDetails orderDetails, CardDetails cardDetails, Shopper shopper, Address billingAddress, Address shippingAddress, CreateTokenDetails createTokenDetails)
    {
        this.orderDetails = orderDetails;
        this.cardDetails = cardDetails;
        this.shopper = shopper;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.createTokenDetails = createTokenDetails;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        // Shopper tokens always require shopper to be present
        if (this.createTokenDetails != null && TokenScope.SHOPPER.equals(createTokenDetails.getScope()))
        {
            Assert.notNull(shopper, "Shopper is required for tokenised payments");
            Assert.notEmpty(shopper.getShopperId(), "Shopper ID is required for tokenised payments");
        }
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorateAndStartOrder(params, orderDetails);
        CardDetailsSerializer.decorateOrder(params, cardDetails);
        SessionSerializer.decorateOrderPaymentDetails(params, shopper);
        ShopperSerializer.decorateOrder(params, shopper);
        AddressSerializer.decorateOrder(params, billingAddress, shippingAddress);
        CreateTokenDetailsSerializer.decorateOrder(params, createTokenDetails);
        OrderDetailsSerializer.decorateFinishOrder(params);
    }

    @Override
    protected PaymentResponse adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
        PaymentResponse result = adapter.read(response);
        return result;
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

    public CardPaymentRequest tokeniseForReoccurringPayments(CreateTokenDetails createTokenDetails)
    {
        this.createTokenDetails = createTokenDetails;
        return this;
    }

}
