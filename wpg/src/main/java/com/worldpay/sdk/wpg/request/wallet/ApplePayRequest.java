package com.worldpay.sdk.wpg.request.wallet;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.wallet.ApplePaySerializer;

public class ApplePayRequest extends XmlRequest<PaymentResponse>
{
    /*
        Useful resources:
        - https://webkit.org/blog/8182/introducing-the-payment-request-api-for-apple-pay/
        - https://www.w3.org/TR/payment-request/#paymentresponse-interface
        - https://developer.apple.com/documentation/apple_pay_on_the_web/applepaypayment
        - https://developer.apple.com/library/archive/documentation/PassKit/Reference/PaymentTokenJSON/PaymentTokenJSON.html
     */

    private OrderDetails orderDetails;

    // -- Optional
    private Shopper shopper;
    private Address billingAddress;
    private Address shippingAddress;

    // -- PaymentTokenJSON: Header
    private String ephemeralPublicKey;
    private String publicKeyHash;
    private String transactionId;

    // -- PaymentTokenJSON
    private String signature;
    private String version;
    private String data;

    public ApplePayRequest()
    {
    }

    public ApplePayRequest(OrderDetails orderDetails, Shopper shopper)
    {
        this.orderDetails = orderDetails;
        this.shopper = shopper;
    }

    public ApplePayRequest(OrderDetails orderDetails, Shopper shopper, String ephemeralPublicKey, String publicKeyHash, String transactionId, String signature, String version, String data)
    {
        this.orderDetails = orderDetails;
        this.shopper = shopper;
        this.ephemeralPublicKey = ephemeralPublicKey;
        this.publicKeyHash = publicKeyHash;
        this.transactionId = transactionId;
        this.signature = signature;
        this.version = version;
        this.data = data;
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
        ApplePaySerializer.decorateOrder(params, this);
        ShopperSerializer.decorateOrder(params, shopper);
        AddressSerializer.decorateOrder(params, billingAddress, shippingAddress);
        OrderDetailsSerializer.decorateFinishOrder(params);
    }

    @Override
    protected PaymentResponse adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        PaymentResponseXmlAdapter adapter = new PaymentResponseXmlAdapter();
        PaymentResponse result = adapter.read(response);
        return result;
    }

    public ApplePayRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    public OrderDetails getOrderDetails()
    {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
    }

    public ApplePayRequest shopper(Shopper shopper)
    {
        this.shopper = shopper;
        return this;
    }

    public Shopper getShopper()
    {
        return shopper;
    }

    public void setShopper(Shopper shopper)
    {
        this.shopper = shopper;
    }

    public ApplePayRequest billingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
        return this;
    }

    public Address getBillingAddress()
    {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
    }

    public ApplePayRequest shippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public Address getShippingAddress()
    {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
    }

    public ApplePayRequest ephemeralPublicKey(String ephemeralPublicKey)
    {
        this.ephemeralPublicKey = ephemeralPublicKey;
        return this;
    }

    public String getEphemeralPublicKey()
    {
        return ephemeralPublicKey;
    }

    public void setEphemeralPublicKey(String ephemeralPublicKey)
    {
        this.ephemeralPublicKey = ephemeralPublicKey;
    }

    public ApplePayRequest publicKeyHash(String publicKeyHash)
    {
        this.publicKeyHash = publicKeyHash;
        return this;
    }

    public String getPublicKeyHash()
    {
        return publicKeyHash;
    }

    public void setPublicKeyHash(String publicKeyHash)
    {
        this.publicKeyHash = publicKeyHash;
    }

    public ApplePayRequest transactionId(String transactionId)
    {
        this.transactionId = transactionId;
        return this;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

    public ApplePayRequest signature(String signature)
    {
        this.signature = signature;
        return this;
    }

    public String getSignature()
    {
        return signature;
    }

    public void setSignature(String signature)
    {
        this.signature = signature;
    }

    public ApplePayRequest version(String version)
    {
        this.version = version;
        return this;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public ApplePayRequest data(String data)
    {
        this.data = data;
        return this;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

}
