package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm
 */
public class AvsResult
{
    private final String avsResultCode;

    public AvsResult(String avsResultCode)
    {
        this.avsResultCode = avsResultCode;
    }

    public String getAvsResultCode()
    {
        return avsResultCode;
    }
}
