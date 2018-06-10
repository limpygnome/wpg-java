package com.worldpay.sdk.wpg.request.card;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.card.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.shopper.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
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
 * A request to make a payment using card details.
 *
 * Supports tokenisation.
 *
 * <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/paymentrequests.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/paymentrequests.htm</a>
 */
public class CardPaymentRequest extends XmlRequest<PaymentResponse> implements BatchOrderItem
{
    // Mandatory
    private OrderDetails orderDetails;
    private CardDetails cardDetails;

    // Grey area (mandatory for 3ds)
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
        Assert.notNull(orderDetails, "Order details are mandatory");
        Assert.notNull(cardDetails, "Card details are mandatory");

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
    protected PaymentResponse adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
        PaymentResponse result = adapter.read(response);
        return result;
    }

    /**
     * @param orderDetails Order details (mandatory)
     * @return Current instance
     */
    public CardPaymentRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    /**
     * @param cardDetails Card details (mandatory)
     * @return Current instance
     */
    public CardPaymentRequest cardDetails(CardDetails cardDetails)
    {
        this.cardDetails = cardDetails;
        return this;
    }

    /**
     * Mandatory when 3ds / ThreeDS is enabled to authenticate payments.
     *
     * You will need to specify shopper browser details:
     * - {@link ShopperBrowser#getUserAgentHeader()}
     * - {@link ShopperBrowser#getAcceptHeader()}
     *
     * Refer to example code.
     *
     * @param shopper Shopper details (mandatory for 3ds)
     * @return Current instance
     */
    public CardPaymentRequest shopper(Shopper shopper)
    {
        this.shopper = shopper;
        return this;
    }

    /**
     * @param billingAddress Shopper's billing address (optional)
     * @return Current instance
     */
    public CardPaymentRequest billingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
        return this;
    }

    /**
     * @param shippingAddress Shopper's shipping address (optional)
     * @return Current instance
     */
    public CardPaymentRequest shippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

    /**
     * @param createTokenDetails Details to tokenise card details for future payments (optional)
     * @return Current instance
     */
    public CardPaymentRequest tokeniseForReoccurringPayments(CreateTokenDetails createTokenDetails)
    {
        this.createTokenDetails = createTokenDetails;
        return this;
    }

    /**
     * @return Order details
     */
    public OrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    /**
     * @return Card details
     */
    public CardDetails getCardDetails()
    {
        return cardDetails;
    }

    /**
     * @return Shopper details
     */
    public Shopper getShopper()
    {
        return shopper;
    }

    /**
     * @return Shopper's billing address
     */
    public Address getBillingAddress()
    {
        return billingAddress;
    }

    /**
     * @return Shopper's shipping address
     */
    public Address getShippingAddress()
    {
        return shippingAddress;
    }

    /**
     * @return Tokenisation details
     */
    public CreateTokenDetails getCreateTokenDetails()
    {
        return createTokenDetails;
    }

}
