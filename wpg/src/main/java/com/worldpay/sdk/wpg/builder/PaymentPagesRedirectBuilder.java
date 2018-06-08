package com.worldpay.sdk.wpg.builder;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.internal.xml.PaymentMethodTranslator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

/**
 * Appends extra parameters to a Hosted Payment Pages (HPP) redirect URL to customise the shopper's flow.
 */
public final class PaymentPagesRedirectBuilder
{
    private static final String ENCODING_CHARSET = "UTF-8";

    private String orderUrl;

    private String successUrl;
    private String pendingUrl;
    private String failureUrl;
    private String errorUrl;
    private String cancelUrl;
    private String preferredPaymentMethod;
    private String country;
    private String language;

    /**
     * Creates a new builder.
     *
     * @param orderUrl The HPP redirect URL
     */
    public PaymentPagesRedirectBuilder(String orderUrl)
    {
        this.orderUrl = orderUrl;
    }

    public String build()
    {
        try
        {
            StringBuilder builder = new StringBuilder(orderUrl);

            if (successUrl != null && successUrl.length() > 0)
            {
                builder.append("&successURL=").append(URLEncoder.encode(successUrl, ENCODING_CHARSET));
            }
            if (pendingUrl != null && pendingUrl.length() > 0)
            {
                builder.append("&pendingURL=").append(URLEncoder.encode(pendingUrl, ENCODING_CHARSET));
            }
            if (failureUrl != null && failureUrl.length() > 0)
            {
                builder.append("&failureURL=").append(URLEncoder.encode(failureUrl, ENCODING_CHARSET));
            }
            if (errorUrl != null && errorUrl.length() > 0)
            {
                builder.append("&errorURL=").append(URLEncoder.encode(errorUrl, ENCODING_CHARSET));
            }
            if (cancelUrl != null && cancelUrl.length() > 0)
            {
                builder.append("&cancelURL=").append(URLEncoder.encode(cancelUrl, ENCODING_CHARSET));
            }
            if (preferredPaymentMethod != null && preferredPaymentMethod.length() > 0)
            {
                builder.append("&preferredPaymentMethod=").append(URLEncoder.encode(preferredPaymentMethod.toLowerCase(), ENCODING_CHARSET));
            }
            if (country != null && country.length() > 0)
            {
                builder.append("&country=").append(URLEncoder.encode(country.toLowerCase(), ENCODING_CHARSET));
            }
            if (language != null && language.length() > 0)
            {
                builder.append("&language=").append(URLEncoder.encode(language.toLowerCase(), ENCODING_CHARSET));
            }

            String url = builder.toString();
            return url;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("UTF-8 charset not available", e);
        }
    }

    /**
     * The shopper is redirected to the specified URL when the payment authorisation is successful.
     *
     * @param successUrl The URL
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder successUrl(String successUrl)
    {
        this.successUrl = successUrl;
        return this;
    }

    /**
     * The shopper is redirected to the specified URL when the payment authorisation is pending.
     *
     * @param pendingUrl The URL
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder pendingUrl(String pendingUrl)
    {
        this.pendingUrl = pendingUrl;
        return this;
    }

    /**
     * The shopper is redirected to the specified URL when the payment authorisation fails.
     *
     * @param failureUrl The URL
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder failureUrl(String failureUrl)
    {
        this.failureUrl = failureUrl;
        return this;
    }

    /**
     * The shopper is redirected to the specified URL when an error occurs on the payment pages.
     *
     * @param errorUrl The URL
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder errorUrl(String errorUrl)
    {
        this.errorUrl = errorUrl;
        return this;
    }

    /**
     * The shopper is redirected to the specified URL when they cancel the payment / want to return back to the
     * merchant's site.
     *
     * @param cancelUrl The URL
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder cancelUrl(String cancelUrl)
    {
        this.cancelUrl = cancelUrl;
        return this;
    }

    /**
     * The shopper is redirected to the specified URL for all flows (success, failure, pending, error and cancel).
     *
     * @param url The URL
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder allUrls(String url)
    {
        successUrl(url);
        pendingUrl(url);
        failureUrl(url);
        errorUrl(url);
        cancelUrl(url);
        return this;
    }

    /**
     * The preferred payment method to be chosen when visiting the payment pages.
     *
     * Otherwise:
     * = When only card payment methods are available, the cards page is shown.
     * = When cards and APMs are available, a payment selection page is shown.
     *
     * @param paymentMethod The payment method
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder preferredPaymentMethod(PaymentMethod paymentMethod)
    {
        this.preferredPaymentMethod = PaymentMethodTranslator.getMask(paymentMethod);
        return this;
    }

    /**
     * The preferred payment method to be chosen when visiting the payment pages.
     *
     * Otherwise:
     * = When only card payment methods are available, the cards page is shown.
     * = When cards and APMs are available, a payment selection page is shown.
     *
     * This method has been added in the event new payment methods have been added to the payment pages, but not
     * to the SDK.
     *
     * @param paymentMethodMask The payment method mask, known by the gateway
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder preferredPaymentMethod(String paymentMethodMask)
    {
        this.preferredPaymentMethod = paymentMethodMask;
        return this;
    }

    /**
     * The language in which the payment pages are shown.
     *
     * @param languageCode The ISO 639-1 (two character) language code
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder language(String languageCode)
    {
        this.language = languageCode;
        return this;
    }

    /**
     * The country in which the shopper resides or/and can make their payment.
     *
     * This impacts the available payment methods, which may be important for legal reasons.
     *
     * @param countryCode ISO 3166-1 (two character) country code
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder country(String countryCode)
    {
        this.country = countryCode;
        return this;
    }

    /**
     * Sets both language and country based on locale.
     *
     * @see {@link #language(String)}
     * @see {@link #country(String)}
     *
     * @param locale The locale
     * @return Current instance
     */
    public PaymentPagesRedirectBuilder languageAndCountry(Locale locale)
    {
        this.language = locale.getLanguage();
        this.country = locale.getCountry();
        return this;
    }

}
