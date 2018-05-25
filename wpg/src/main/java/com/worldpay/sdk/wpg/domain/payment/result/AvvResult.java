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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvvResult avvResult = (AvvResult) o;

        if (addressResultCode != null ? !addressResultCode.equals(avvResult.addressResultCode) : avvResult.addressResultCode != null)
            return false;
        if (postCodeResultCode != null ? !postCodeResultCode.equals(avvResult.postCodeResultCode) : avvResult.postCodeResultCode != null)
            return false;
        if (cardHolderNameResultCode != null ? !cardHolderNameResultCode.equals(avvResult.cardHolderNameResultCode) : avvResult.cardHolderNameResultCode != null)
            return false;
        if (telephoneResultCode != null ? !telephoneResultCode.equals(avvResult.telephoneResultCode) : avvResult.telephoneResultCode != null)
            return false;
        return emailResultCode != null ? emailResultCode.equals(avvResult.emailResultCode) : avvResult.emailResultCode == null;
    }

    @Override
    public int hashCode()
    {
        int result = addressResultCode != null ? addressResultCode.hashCode() : 0;
        result = 31 * result + (postCodeResultCode != null ? postCodeResultCode.hashCode() : 0);
        result = 31 * result + (cardHolderNameResultCode != null ? cardHolderNameResultCode.hashCode() : 0);
        result = 31 * result + (telephoneResultCode != null ? telephoneResultCode.hashCode() : 0);
        result = 31 * result + (emailResultCode != null ? emailResultCode.hashCode() : 0);
        return result;
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
