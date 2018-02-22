package com.worldpay.sdk.wpg.response.redirect;

import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.xml.PaymentMethodTranslator;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

// TODO validation on setters
public final class RedirectBuilder
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

    public RedirectBuilder(String orderUrl)
    {
        this.orderUrl = orderUrl;
    }

    public String build()
    {
        try
        {
            StringBuilder builder = new StringBuilder(orderUrl);

            if (successUrl != null)
            {
                builder.append("&successURL=").append(URLEncoder.encode(successUrl, ENCODING_CHARSET));
            }
            if (pendingUrl != null)
            {
                builder.append("&pendingURL=").append(URLEncoder.encode(pendingUrl, ENCODING_CHARSET));
            }
            if (failureUrl != null)
            {
                builder.append("&failureURL=").append(URLEncoder.encode(failureUrl, ENCODING_CHARSET));
            }
            if (errorUrl != null)
            {
                builder.append("&errorURL=").append(URLEncoder.encode(errorUrl, ENCODING_CHARSET));
            }
            if (cancelUrl != null)
            {
                builder.append("&cancelURL=").append(URLEncoder.encode(cancelUrl, ENCODING_CHARSET));
            }
            if (preferredPaymentMethod != null)
            {
                builder.append("&preferredPaymentMethod=").append(URLEncoder.encode(preferredPaymentMethod, ENCODING_CHARSET));
            }
            if (country != null)
            {
                builder.append("&country=").append(URLEncoder.encode(country, ENCODING_CHARSET));
            }
            if (language != null)
            {
                builder.append("&language=").append(URLEncoder.encode(language, ENCODING_CHARSET));
            }

            String url = builder.toString();
            return url;
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("UTF-8 charset not available", e);
        }
    }

    public RedirectBuilder successUrl(String successUrl)
    {
        this.successUrl = successUrl;
        return this;
    }

    public RedirectBuilder successUrl(URL successUrl)
    {
        successUrl(successUrl.toString());
        return this;
    }

    public RedirectBuilder pendingUrl(String pendingUrl)
    {
        this.pendingUrl = pendingUrl;
        return this;
    }

    public RedirectBuilder failureUrl(String failureUrl)
    {
        this.failureUrl = failureUrl;
        return this;
    }

    public RedirectBuilder errorUrl(String errorUrl)
    {
        this.errorUrl = errorUrl;
        return this;
    }

    public RedirectBuilder cancelUrl(String cancelUrl)
    {
        this.cancelUrl = cancelUrl;
        return this;
    }

    public RedirectBuilder allUrls(String url)
    {
        successUrl(url);
        pendingUrl(url);
        failureUrl(url);
        errorUrl(url);
        cancelUrl(url);
        return this;
    }

    public RedirectBuilder preferredPaymentMethod(PaymentMethod paymentMethod)
    {
        this.preferredPaymentMethod = PaymentMethodTranslator.getMask(paymentMethod);
        return this;
    }

    public RedirectBuilder language(String language)
    {
        this.language = language;
        return this;
    }

    public RedirectBuilder country(String country)
    {
        this.country = country;
        return this;
    }

    public RedirectBuilder languageAndCountry(Locale locale)
    {
        this.language = locale.getLanguage();
        this.country = locale.getCountry();
        return this;
    }

}
