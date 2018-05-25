package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * This holds details about the authorisation made during a payout, currently only for Mastercard.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/manage/cardpayouts.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/manage/cardpayouts.htm</a>
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PayoutAuthorisationResult that = (PayoutAuthorisationResult) o;

        return authorisationId != null ? authorisationId.equals(that.authorisationId) : that.authorisationId == null;
    }

    @Override
    public int hashCode()
    {
        return authorisationId != null ? authorisationId.hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "PayoutAuthorisationResult{" +
                "authorisationId='" + authorisationId + '\'' +
                '}';
    }

}
