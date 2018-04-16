package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * The result from threeds (3ds) authentication during a card payment.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/directintegration/authentication.htm</a>
 */
public class ThreeDSecureResult
{
    private String description;
    private String eci;
    private String cavv;

    public ThreeDSecureResult(String description, String eci, String cavv)
    {
        this.description = description;
        this.eci = eci;
        this.cavv = cavv;
    }

    /**
     * @return Description of the outcome
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return Electronic Commerce Indicator (ECI), only present if enabled for your account
     */
    public String getEci()
    {
        return eci;
    }

    /**
     * @return Cardholder Authentication Verification Value (CAVV), only present if enabled for your account
     */
    public String getCavv()
    {
        return cavv;
    }

    @Override
    public String toString()
    {
        return "ThreeDSecureResult{" +
                "description='" + description + '\'' +
                ", eci='" + eci + '\'' +
                ", cavv='" + cavv + '\'' +
                '}';
    }

}
