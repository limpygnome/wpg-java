package com.worldpay.sdk.wpg.request.apm;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.RedirectUrlXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation.CreateTokenDetailsSerializer;

/**
 * Supports tokenisation
 *
 * Supported language codes, otherwise defaults to English:
 * http://support.worldpay.com/support/kb/gg/paypal/paypalcg.htm#languagecodes.htm%3FTocPath%3DXML%2520input%2520examples%7CTechnical%2520Integration%7C_____3
 */
public class PayPalPaymentRequest extends XmlRequest<RedirectUrl>
{
    // Mandatory
    private OrderDetails orderDetails;
    private Shopper shopper;
    private String successURL;
    private String failureURL;
    private String cancelURL;

    // Optional
    private Address billingAddress;
    private Address shippingAddress;
    private String languageCode;
    private CreateTokenDetails createTokenDetails;

    @Override
    protected void validate(XmlBuildParams params)
    {
        if (this.createTokenDetails == null)
        {
            Assert.notEmpty(shopper.getShopperId(), "Shopper ID is required for tokenised payments");
        }
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorateAndStartOrder(params, orderDetails);

        XmlBuilder builder = params.xmlBuilder();

        // PayPal information
        // --- Language
        if (languageCode != null && languageCode.length() > 0)
        {
            builder.e("submit").e("order").a("shopperLanguageCode", languageCode);
        }
        builder.reset();

        // -- PayPal details
        builder.e("submit").e("order").e("paymentDetails").e("PAYPAL-EXPRESS");

        if (createTokenDetails != null)
        {
            builder.a("firstInBillingRun", "true");
        }

        builder.e("successURL").cdata(successURL).up();
        builder.e("failureURL").cdata(failureURL).up();
        builder.e("cancelURL").cdata(cancelURL).up();

        builder.reset();

        // Continue generic information
        ShopperSerializer.decorateOrder(params, shopper);
        AddressSerializer.decorateOrder(params, billingAddress, shippingAddress);
        CreateTokenDetailsSerializer.decorateOrder(params, createTokenDetails);

        OrderDetailsSerializer.decorateFinishOrder(params);
    }

    @Override
    protected RedirectUrl adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        RedirectUrlXmlAdapter adapter = new RedirectUrlXmlAdapter();
        RedirectUrl redirectUrl = adapter.read(response);
        return redirectUrl;
    }

    public PayPalPaymentRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    public PayPalPaymentRequest shopper(Shopper shopper)
    {
        this.shopper = shopper;
        return this;
    }

    public PayPalPaymentRequest resultURL(String resultURL)
    {
        successURL(resultURL);
        failureURL(resultURL);
        cancelURL(resultURL);
        return this;
    }

    public PayPalPaymentRequest successURL(String successURL)
    {
        this.successURL = successURL;
        return this;
    }

    public PayPalPaymentRequest failureURL(String failureURL)
    {
        this.failureURL = failureURL;
        return this;
    }

    public PayPalPaymentRequest cancelURL(String cancelURL)
    {
        this.cancelURL = cancelURL;
        return this;
    }

    public PayPalPaymentRequest billingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
        return this;
    }

    public PayPalPaymentRequest shippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public PayPalPaymentRequest languageCode(String languageCode)
    {
        this.languageCode = languageCode;
        return this;
    }

    public PayPalPaymentRequest tokeniseForReoccurringPayments(CreateTokenDetails createTokenDetails)
    {
        this.createTokenDetails = createTokenDetails;
        return this;
    }

}
