package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * This holds details about the authorisation made during a payout, currently only for Mastercard.
 *
 * More details can be found here:
 * http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/manage/cardpayouts.htm
 */
public class PayoutAuthorisationResult
{
    private final String authorisationId;

    public PayoutAuthorisationResult(String authorisationId)
    {
        this.authorisationId = authorisationId;
    }

    /**
     * @return identifier tied to the authorisation
     */
    public String getAuthorisationId()
    {
        return authorisationId;
    }

    @Override
    public String toString()
    {
        return "PayoutAuthorisationResult{" +
                "authorisationId='" + authorisationId + '\'' +
                '}';
    }
}
