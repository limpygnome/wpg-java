package com.worldpay.sdk.wpg.builder;

import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsDetails;

import java.net.URL;

/**
 * A builder used to construct a page to redirect to the 3ds page / issuer for card
 * authentication.
 *
 * A termUrl is mandatory, which is used to redirect from the issuer / 3ds page
 * back to your site, which will post back a "paRes" value to be submitted
 * to the gateway to continue the payment.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm</a>
 */
public class ThreeDsRedirectBuilder
{
    private ThreeDsDetails response;
    private String termUrl;
    private String merchantData;

    /**
     * @param response threeds details from a payment request
     */
    public ThreeDsRedirectBuilder(ThreeDsDetails response)
    {
        if (response == null)
        {
            throw new IllegalArgumentException("No threeds response provided");
        }
        this.response = response;
    }

    /**
     * This is mandatory.
     *
     * @param url your page to capture the pares from the issuer
     * @return this
     */
    public ThreeDsRedirectBuilder termUrl(URL url)
    {
        this.termUrl = url.toString();
        return this;
    }

    /**
     * This is mandatory.
     *
     * @param url your page to capture the pares from the issuer
     * @return this
     */
    public ThreeDsRedirectBuilder termUrl(String url)
    {
        this.termUrl = url;
        return this;
    }

    /**
     * @param merchantData optional data passed back to your page with the pares from the issuer
     * @return this
     */
    public ThreeDsRedirectBuilder merchantData(String merchantData)
    {
        this.merchantData = merchantData;
        return this;
    }

    /**
     * Constructs HTML to redirect the current page to the issuer / 3ds page.
     *
     * You can display this inside of an iframe.
     *
     * @return constructed HTML
     */
    public String build()
    {
        if (termUrl == null)
        {
            throw new IllegalStateException("TermURL is required");
        }

        /*
            http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm
         */

        String isserUrl = response.getIssuerURL();
        String paReq = response.getPaRequest();

        StringBuilder page = new StringBuilder();
        page.append("<!DOCTYPE html>")
            .append("<html>")
            .append("<head>")
                .append("<meta charset=\"UTF-8\">")
                .append("<title>3ds authentication redirect</title>")
            .append("</head>")
            .append("<body onload=\"auth_onloadEvent()\">")
                .append("<form name=\"form3ds\" method=\"post\" action=\"").append(isserUrl).append("\">")
                    .append("<input type=\"hidden\" name=\"PaReq\" value=\"").append(paReq).append("\" />")
                    .append("<input type=\"hidden\" name=\"TermUrl\" value=\"").append(termUrl).append("\" />")
                    .append("<input type=\"hidden\" name=\"MD\" value=\"").append(merchantData).append("\" />")
                    .append("<input type=\"submit\" value=\"Continue\" />")
                .append("</form>")
                .append("<script type=\"text/javascript\">")
                    .append("function auth_onloadEvent() {")
                        .append("document.form3ds.submit();")
                    .append("}")
                .append("</script>")
            .append("</body>")
            .append("</html>");
        return page.toString();
    }

}
