package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/directintegration/authentication.htm
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

    public String getAddressResultCode()
    {
        return addressResultCode;
    }

    public String getPostCodeResultCode()
    {
        return postCodeResultCode;
    }

    public String getCardHolderNameResultCode()
    {
        return cardHolderNameResultCode;
    }

    public String getTelephoneResultCode()
    {
        return telephoneResultCode;
    }

    public String getEmailResultCode()
    {
        return emailResultCode;
    }
}
