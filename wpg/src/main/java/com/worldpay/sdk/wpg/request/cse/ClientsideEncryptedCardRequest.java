package com.worldpay.sdk.wpg.request.cse;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.CseSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.SessionSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation.CreateTokenDetailsSerializer;
import com.worldpay.sdk.wpg.request.batch.BatchOrderItem;

/**
 * Not yet supported.
 *
 *
 * Submit card payment details to make a payment, which have been client-side encrypted.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/clientsideencryption/serversideintegration.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/clientsideencryption/serversideintegration.htm</a>
 */
public class ClientsideEncryptedCardRequest extends XmlRequest<PaymentResponse> implements BatchOrderItem
{
    // Mandatory
    private OrderDetails orderDetails;
    private String encryptedData;
    private Address cardHolderAddress;
    private Shopper shopper;

    // Optional
    private Address billingAddress;
    private Address shippingAddress;
    private CreateTokenDetails createTokenDetails;

    public ClientsideEncryptedCardRequest() { }

    public ClientsideEncryptedCardRequest(OrderDetails orderDetails, String encryptedData)
    {
        this.orderDetails = orderDetails;
        this.encryptedData = encryptedData;
    }

    public ClientsideEncryptedCardRequest(OrderDetails orderDetails, String encryptedData, Address cardHolderAddress, Shopper shopper)
    {
        this.orderDetails = orderDetails;
        this.encryptedData = encryptedData;
        this.cardHolderAddress = cardHolderAddress;
        this.shopper = shopper;
    }

    public ClientsideEncryptedCardRequest(OrderDetails orderDetails, String encryptedData, Address cardHolderAddress, Shopper shopper, Address billingAddress, Address shippingAddress)
    {
        this.orderDetails = orderDetails;
        this.encryptedData = encryptedData;
        this.cardHolderAddress = cardHolderAddress;
        this.shopper = shopper;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public ClientsideEncryptedCardRequest(OrderDetails orderDetails, String encryptedData, Address cardHolderAddress, Shopper shopper, Address billingAddress, Address shippingAddress, CreateTokenDetails createTokenDetails)
    {
        this.orderDetails = orderDetails;
        this.encryptedData = encryptedData;
        this.cardHolderAddress = cardHolderAddress;
        this.shopper = shopper;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.createTokenDetails = createTokenDetails;
    }

    protected void validate(XmlBuildParams params)
    {
    }

    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorateAndStartOrder(params, orderDetails);
        CseSerializer.decorateOrder(params, encryptedData, cardHolderAddress);
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
        PaymentResponse paymentResponse = adapter.read(response);
        return paymentResponse;
    }

    public ClientsideEncryptedCardRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    public ClientsideEncryptedCardRequest encryptedData(String encryptedData)
    {
        this.encryptedData = encryptedData;
        return this;
    }

    public ClientsideEncryptedCardRequest cardHolderAddress(Address cardHolderAddress)
    {
        this.cardHolderAddress = cardHolderAddress;
        return this;
    }

    public ClientsideEncryptedCardRequest shopper(Shopper shopper)
    {
        this.shopper = shopper;
        return this;
    }

    public ClientsideEncryptedCardRequest billingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
        return this;
    }

    public ClientsideEncryptedCardRequest shippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

    // TODO does cse support tokenisation? prolly not
    public ClientsideEncryptedCardRequest tokeniseForReoccurringPayments(CreateTokenDetails createTokenDetails)
    {
        this.createTokenDetails = createTokenDetails;
        return this;
    }

}
