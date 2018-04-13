package com.worldpay.sdk.wpg.builder;

import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsRequired;

import java.net.URL;

public class ThreeDsRedirectBuilder
{
    private ThreeDsRequired response;
    private String termUrl;
    private String merchantData;

    public ThreeDsRedirectBuilder(ThreeDsRequired response)
    {
        if (response == null)
        {
            throw new IllegalArgumentException("No threeds response provided");
        }
        this.response = response;
    }

    public ThreeDsRedirectBuilder termUrl(URL url)
    {
        this.termUrl = url.toString();
        return this;
    }

    public ThreeDsRedirectBuilder termUrl(String url)
    {
        this.termUrl = url;
        return this;
    }

    public ThreeDsRedirectBuilder merchantData(String merchantData)
    {
        this.merchantData = merchantData;
        return this;
    }

    public String build()
    {
        if (termUrl == null)
        {
            throw new IllegalArgumentException("TermURL is required");
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
                .append("<form name=\"form3ds\" method=\"post\" action=\"").append(isserUrl).append("\" />")
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
            .append("</body")
            .append("</html>");
        return page.toString();
    }

}
