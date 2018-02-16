package com.worldpay.sdk.wpg.response.threeds;

public class ThreeDsResponse
{
    private String issuerURL;
    private String paRequest;

    public String generateRedirectForm()
    {
        /*
            generate simple page with non-js/js form to redirect to issuer
            http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm
         */
        return null;
    }

}
