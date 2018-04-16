package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * Represents the result from address verification service (AVS) checks.
 *
 * This is performed on the card holder address.
 *
 * TODO change result code to enum
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm</a>
 */
public class AvsResult
{
    private final String avsResultCode;

    public AvsResult(String avsResultCode)
    {
        this.avsResultCode = avsResultCode;
    }

    /**
     * @return see XML docs
     */
    public String getAvsResultCode()
    {
        return avsResultCode;
    }

    @Override
    public String toString()
    {
        return "AvsResult{" +
                "avsResultCode='" + avsResultCode + '\'' +
                '}';
    }

}
