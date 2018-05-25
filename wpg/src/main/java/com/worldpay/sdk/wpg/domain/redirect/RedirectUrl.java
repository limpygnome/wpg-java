package com.worldpay.sdk.wpg.domain.redirect;

import com.worldpay.sdk.wpg.builder.PaymentPagesRedirectBuilder;

import java.io.Serializable;

/**
 * A URL response from WPG.
 *
 * You should redirect the shopper to the URL to continue their order.
 */
public class RedirectUrl implements Serializable
{
    private static final long serialVersionUID = 1L;

    private final String url;

    public RedirectUrl(String url)
    {
        this.url = url;
    }

    /**
     * When doing a Hosted Payment Pages order, you will receive an instance of this class. You can customise
     * the URL to change some options on the payment pages (language, country, selected payment method, etc).
     *
     * This method is just a shortcut to the builder, and not applicable for all URLs received.
     *
     * @return A builder to customise this URL
     */
    public PaymentPagesRedirectBuilder paymentPages()
    {
        return new PaymentPagesRedirectBuilder(url);
    }

    /**
     * @return The URL to continue the order
     */
    public String getUrl()
    {
        return url;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RedirectUrl that = (RedirectUrl) o;

        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode()
    {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "RedirectUrl{" +
                "url='" + url + '\'' +
                '}';
    }

}
