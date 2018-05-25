package com.worldpay.sdk.wpg.domain.tokenisation;

import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;

/**
 * Represents the response from performing a token payment.
 *
 * This has two independent flows:
 * - Payment response - provided when a payment has been successful or needs authenticating (e.g. 3ds).
 * - Capture CVC - when a token payment requests CVC, you can redirect the shopper to a URL to complete the payment, which captures their card's CVC / security code.
 *
 * Capture CVC will not be present, unless you've explicitly requested it in your tokenisation request.
 */
public class TokenisationPaymentResponse
{
    private PaymentResponse paymentResponse;
    private RedirectUrl captureCvcUrl;

    public TokenisationPaymentResponse(PaymentResponse paymentResponse, RedirectUrl captureCvcUrl)
    {
        this.paymentResponse = paymentResponse;
        this.captureCvcUrl = captureCvcUrl;
    }

    /**
     * @return Payment response
     */
    public PaymentResponse getPaymentResponse()
    {
        return paymentResponse;
    }

    /**
     * @return The URL at which the shopper can complete the payment to capture their card's CVC / security code
     */
    public RedirectUrl getCaptureCvcUrl()
    {
        return captureCvcUrl;
    }

    /**
     * @return Indicates whether capture CVC URL is present
     */
    public boolean isCaptureCvc()
    {
        return captureCvcUrl != null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenisationPaymentResponse that = (TokenisationPaymentResponse) o;

        if (paymentResponse != null ? !paymentResponse.equals(that.paymentResponse) : that.paymentResponse != null)
            return false;
        return captureCvcUrl != null ? captureCvcUrl.equals(that.captureCvcUrl) : that.captureCvcUrl == null;
    }

    @Override
    public int hashCode()
    {
        int result = paymentResponse != null ? paymentResponse.hashCode() : 0;
        result = 31 * result + (captureCvcUrl != null ? captureCvcUrl.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "TokenisationPaymentResponse{" +
                "paymentResponse=" + paymentResponse +
                ", captureCvcUrl=" + captureCvcUrl +
                '}';
    }

}
