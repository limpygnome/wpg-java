package com.worldpay.sdk.wpg.request.apm;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.apm.PayPalLanguage;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
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
 * A request to create a transaction and take a payment using PayPal.
 *
 * Supports tokenisation.
 *
 * <a href="http://support.worldpay.com/support/kb/gg/paypal/paypalcg.htm#languagecodes.htm%3FTocPath%3DXML%2520input%2520examples%7CTechnical%2520Integration%7C_____3">http://support.worldpay.com/support/kb/gg/paypal/paypalcg.htm#languagecodes.htm%3FTocPath%3DXML%2520input%2520examples%7CTechnical%2520Integration%7C_____3</a>
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

    public PayPalPaymentRequest() { }

    public PayPalPaymentRequest(OrderDetails orderDetails, Shopper shopper, String successURL, String failureURL, String cancelURL)
    {
        this.orderDetails = orderDetails;
        this.shopper = shopper;
        this.successURL = successURL;
        this.failureURL = failureURL;
        this.cancelURL = cancelURL;
    }

    public PayPalPaymentRequest(OrderDetails orderDetails, Shopper shopper, String resultURL)
    {
        this.orderDetails = orderDetails;
        this.shopper = shopper;
        this.resultURL(resultURL);
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notNull(orderDetails, "Order details are mandatory");
        Assert.notNull(shopper, "Shopper details are mandatory");
        Assert.notNull(shopper.getEmail(), "Shopper's e-mail is mandatory");
        Assert.notNull(successURL, "Success URL is mandatory");
        Assert.notNull(successURL, "Failure URL is mandatory");
        Assert.notNull(successURL, "Cancel URL is mandatory");

        if (this.createTokenDetails != null)
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
            builder.a("shopperLanguageCode", languageCode);
        }

        // -- PayPal details
        builder.e("paymentDetails").e("PAYPAL-EXPRESS");

        if (createTokenDetails != null)
        {
            builder.a("firstInBillingRun", "true");
        }

        builder.e("successURL").cdata(successURL).up();
        builder.e("failureURL").cdata(failureURL).up();
        builder.e("cancelURL").cdata(cancelURL).up();

        builder.up().up();

        // Continue generic information
        ShopperSerializer.decorateOrder(params, shopper);
        AddressSerializer.decorateOrder(params, billingAddress, shippingAddress);
        CreateTokenDetailsSerializer.decorateOrder(params, createTokenDetails);

        OrderDetailsSerializer.decorateFinishOrder(params);
    }

    @Override
    protected RedirectUrl adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedException
    {
        RedirectUrlXmlAdapter adapter = new RedirectUrlXmlAdapter();
        RedirectUrl redirectUrl = adapter.read(response);
        return redirectUrl;
    }

    /**
     * @param orderDetails Order details (mandatory)
     * @return Current instance
     */
    public PayPalPaymentRequest orderDetails(OrderDetails orderDetails)
    {
        this.orderDetails = orderDetails;
        return this;
    }

    /**
     * The shopper's e-mail address is required.
     *
     * @param shopper Shopper details (mandatory)
     * @return Current instance
     */
    public PayPalPaymentRequest shopper(Shopper shopper)
    {
        this.shopper = shopper;
        return this;
    }

    /**
     * Sets the success, cancel and failure URL to the same thing.
     *
     * These URLs are mandatory.
     *
     * @param resultURL The URL for all outcomes
     * @return Current instance
     */
    public PayPalPaymentRequest resultURL(String resultURL)
    {
        successURL(resultURL);
        failureURL(resultURL);
        cancelURL(resultURL);
        return this;
    }

    /**
     * @param successURL The URL to which the shopper is redirected for a successful payment (mandatory)
     * @return Current instance
     */
    public PayPalPaymentRequest successURL(String successURL)
    {
        this.successURL = successURL;
        return this;
    }

    /**
     * @param failureURL The URL to which the shopper is redirected for a failed payment (mandatory)
     * @return Current instance
     */
    public PayPalPaymentRequest failureURL(String failureURL)
    {
        this.failureURL = failureURL;
        return this;
    }

    /**
     * @param cancelURL The URL to which the shopper is redirected for a cancelled payment (mandatory)
     * @return Current instance
     */
    public PayPalPaymentRequest cancelURL(String cancelURL)
    {
        this.cancelURL = cancelURL;
        return this;
    }

    /**
     * @param billingAddress The shopper's billing address (optional)
     * @return Current instance
     */
    public PayPalPaymentRequest billingAddress(Address billingAddress)
    {
        this.billingAddress = billingAddress;
        return this;
    }

    /**
     * @param shippingAddress The shopper's shipping address (optional)
     * @return Current instance
     */
    public PayPalPaymentRequest shippingAddress(Address shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

    /**
     * Defaults to showing PayPal with English.
     *
     * @param language The language used for PayPal (optional)
     * @return Current instance
     */
    public PayPalPaymentRequest language(PayPalLanguage language)
    {
        this.languageCode = language.LANGUAGE_CODE;
        return this;
    }

    /**
     * @param languageCode The language used for PayPal (optional)
     * @return Current instance
     */
    public PayPalPaymentRequest language(String languageCode)
    {
        this.languageCode = languageCode;
        return this;
    }

    /**
     * Allows reoccurring PayPal payments using the same details.
     * @param createTokenDetails Token details
     * @return Current instance
     */
    public PayPalPaymentRequest tokeniseForReoccurringPayments(CreateTokenDetails createTokenDetails)
    {
        this.createTokenDetails = createTokenDetails;
        return this;
    }

}
