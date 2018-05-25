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

    /**
     * @return The result; refer to XML docs
     */
    public String getDescription()
    {
        return description;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CvcResult cvcResult = (CvcResult) o;

        return description != null ? description.equals(cvcResult.description) : cvcResult.description == null;
    }

    @Override
    public int hashCode()
    {
        return description != null ? description.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "CvcResult{" +
                "description='" + description + '\'' +
                '}';
    }
}
