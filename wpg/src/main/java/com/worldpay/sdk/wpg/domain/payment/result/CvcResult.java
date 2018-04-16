package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * Result from card verification code (CVC).
 *
 * TODO change description to enum
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm</a>
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

    @Override
    public String toString()
    {
        return "CvcResult{" +
                "description='" + description + '\'' +
                '}';
    }
}
