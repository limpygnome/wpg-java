package com.worldpay.sdk.wpg.request.tokenisation;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenisationPaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.adapter.TokenisationPaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.SessionSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.tokenisation.TokenPaymentSerializer;
import com.worldpay.sdk.wpg.request.batch.BatchOrderItem;

/**
 * Makes a payment using previously tokenised payment details.
 */
public class TokenPaymentRequest extends XmlRequest<TokenisationPaymentResponse> implements BatchOrderItem
{
    // Mandatory
    private String paymentTokenId;
    private TokenScope scope;
    private OrderDetails orderDetails;

    // Partially mandatory
    private Shopper shopper;

    // Optional
    private Address billingAddress;
    private Address shippingAddress;
    private boolean captureCvc;

    /**
     * Creates a new request.
     *
     * Defaults the scope to {@link TokenScope#SHOPPER} and capture CVC to false.
     */
    public TokenPaymentRequest()
    {
        this(null, TokenScope.SHOPPER, null, null, null, null, false);
    }

    /**
     * Creates a new request.
     *
     * Defaults the scope to {@link TokenScope#MERCHANT} and capture CVC to false.
     *
     * @param paymentTokenId token identifier from previous payment
     * @param orderDetails order details
     */
    public TokenPaymentRequest(String paymentTokenId, OrderDetails orderDetails)
    {
        this(paymentTokenId, TokenScope.MERCHANT, orderDetails, null, null, null, false);
    }

    /**
     * Defaults the scope to {@link TokenScope#SHOPPER} and capture CVC to false.
     *
     * @param paymentTokenId token identifier from previous payment
     * @param orderDetails order details
     * @param shopper shopper details
     */
    public TokenPaymentRequest(String paymentTokenId, OrderDetails orderDetails, Shopper shopper)
    {
        this(paymentTokenId, TokenScope.SHOPPER, orderDetails, shopper, null, null, false);
    }

    /**
     * Creates a new request.
     *
     * @param paymentTokenId token identifier from previous payment
     * @param scope scope of token
     * @param orderDetails order details
     * @param shopper shopper details
     * @param captureCvc indicates whether to redirect to our Worldpay payment pages to capture CVC
     */
    public TokenPaymentRequest(String paymentTokenId, TokenScope scope, OrderDetails orderDetails, Shopper shopper, boolean captureCvc)
    {
        this(paymentTokenId, scope, orderDetails, shopper, null, null, captureCvc);
    }

    /**
     * Creates a new request.
     *
     * @param paymentTokenId token identifier from previous payment
     * @param scope scope of token
     * @param orderDetails order details
     * @param shopper shopper details
     * @param billingAddress billing address
     * @param shippingAddress shipping address
     * @param captureCvc indicates whether to redirect to our Worldpay payment pages to capture CVC
     */
    public TokenPaymentRequest(String paymentTokenId, TokenScope scope, OrderDetails orderDetails, Shopper shopper, Address billingAddress, Address shippingAddress, boolean captureCvc)
    {
        this.paymentTokenId = paymentTokenId;
        this.scope = scope;
        this.orderDetails = orderDetails;
        this.shopper = shopper;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.captureCvc = captureCvc;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notEmpty(paymentTokenId, "Payment token ID is mandatory");
        Assert.notNull(scope, "Token scope is mandatory");
        Assert.notNull(orderDetails, "Order details are mandatory");

        if (scope == TokenScope.SHOPPER)
        {
            Assert.notNull(shopper, "Shopper ID, using shopper, is mandatory for shopper tokens");
            Assert.notEmpty(shopper.getShopperId(), "Shopper ID, using shopper, is mandatory for shopper tokens\"");
        }
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
    protected TokenisationPaymentResponse adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        TokenisationPaymentResponseXmlAdapter adapter = new TokenisationPaymentResponseXmlAdapter();
        TokenisationPaymentResponse paymentResponse = adapter.read(response);
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

    public boolean isCaptureCvc()
    {
        return captureCvc;
    }

    public TokenPaymentRequest captureCvc(boolean captureCvc)
    {
        this.captureCvc = captureCvc;
        return this;
    }

}
