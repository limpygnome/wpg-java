package com.worldpay.sdk.wpg.domain.payment.threeds;

import com.worldpay.sdk.wpg.builder.ThreeDsRedirectBuilder;

/**
 * Present on a {@link com.worldpay.sdk.wpg.domain.payment.PaymentResponse} when threeds (3ds) authentication
 * is required in order to continue a card payment. This shifts liability of fraudulent payments back to the card
 * issuer. This is only required when threeds is enabled for your merchant.
 *
 * You will need to redirect the shopper to the provided issuer URL as a POST request, more details can be found
 * in the XML docs guide.
 *
 * You can use {@link #createRedirectPage()} to create the HTML for a full page redirect.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm</a>
 */
public class ThreeDsDetails
{
    private final String issuerURL;
    private final String paRequest;

    public ThreeDsDetails(String issuerURL, String paRequest)
    {
        this.issuerURL = issuerURL;
        this.paRequest = paRequest;
    }

    /**
     * @return issuer's threeds URL
     */
    public String getIssuerURL()
    {
        return issuerURL;
    }

    /**
     * @return required value to be submitted to issuer's threeds URL
     */
    public String getPaRequest()
    {
        return paRequest;
    }

    /**
     * @return builder to create threeds redirect
     */
    public ThreeDsRedirectBuilder createRedirectPage()
    {
        return new ThreeDsRedirectBuilder(this);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreeDsDetails that = (ThreeDsDetails) o;

        if (issuerURL != null ? !issuerURL.equals(that.issuerURL) : that.issuerURL != null) return false;
        return paRequest != null ? paRequest.equals(that.paRequest) : that.paRequest == null;
    }

    @Override
    public int hashCode()
    {
        int result = issuerURL != null ? issuerURL.hashCode() : 0;
        result = 31 * result + (paRequest != null ? paRequest.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "ThreeDsDetails{" +
                "issuerURL='" + issuerURL + '\'' +
                ", paRequest='" + paRequest + '\'' +
                '}';
    }

}
