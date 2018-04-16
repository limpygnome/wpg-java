package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * Represents the result from American Express Advanced Verification (AVV) checks.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm</a>
 */
public class AvvResult
{
    private final String addressResultCode;
    private final String postCodeResultCode;
    private final String cardHolderNameResultCode;
    private final String telephoneResultCode;
    private final String emailResultCode;

    public AvvResult(String addressResultCode, String postCodeResultCode, String cardHolderNameResultCode, String telephoneResultCode, String emailResultCode)
    {
        this.addressResultCode = addressResultCode;
        this.postCodeResultCode = postCodeResultCode;
        this.cardHolderNameResultCode = cardHolderNameResultCode;
        this.telephoneResultCode = telephoneResultCode;
        this.emailResultCode = emailResultCode;
    }

    /**
     * @return see XML docs
     */
    public String getAddressResultCode()
    {
        return addressResultCode;
    }

    /**
     * @return see XML docs
     */
    public String getPostCodeResultCode()
    {
        return postCodeResultCode;
    }

    /**
     * @return see XML docs
     */
    public String getCardHolderNameResultCode()
    {
        return cardHolderNameResultCode;
    }

    /**
     * @return see XML docs
     */
    public String getTelephoneResultCode()
    {
        return telephoneResultCode;
    }

    /**
     * @return see XML docs
     */
    public String getEmailResultCode()
    {
        return emailResultCode;
    }

    @Override
    public String toString()
    {
        return "AvvResult{" +
                "addressResultCode='" + addressResultCode + '\'' +
                ", postCodeResultCode='" + postCodeResultCode + '\'' +
                ", cardHolderNameResultCode='" + cardHolderNameResultCode + '\'' +
                ", telephoneResultCode='" + telephoneResultCode + '\'' +
                ", emailResultCode='" + emailResultCode + '\'' +
                '}';
    }

}
