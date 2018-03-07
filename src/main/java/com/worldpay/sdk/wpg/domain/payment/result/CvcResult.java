package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm
 */
public class CvcResult
{
    private final String description;

    public CvcResult(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
}
