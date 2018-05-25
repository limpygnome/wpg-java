package com.worldpay.sdk.wpg.domain.tokenisation;

/**
 * A token represents payment details saved on our gateway from a previous order. You can use the paymentTokenid to
 * carry out future payments, without resubmitting the payment details (with the exception of CVC / security code for
 * cards).
 */
public class Token
{
    private final String paymentTokenId;
    private final TokenScope scope;
    private final TokenDetails details;
    private final TokenInstrument instrument;
    private final String shopperId;

    /**
     * This should not be used to create a token.
     *
     * Instead attach {@link CreateTokenDetails} to a payment request, when supported.
     *
     * @param details Token details
     * @param instrument Token instrument
     * @param shopperId Shopper ID
     */
    public Token(TokenDetails details, TokenInstrument instrument, String shopperId)
    {
        this.paymentTokenId = (details != null ? details.getPaymentTokenId() : null);
        this.scope = (shopperId != null && shopperId.length() > 0 ? TokenScope.SHOPPER : TokenScope.MERCHANT);
        this.details = details;
        this.instrument = instrument;
        this.shopperId = shopperId;
    }

    /**
     * @return The identifier of the token
     */
    public String getPaymentTokenId()
    {
        return paymentTokenId;
    }

    /**
     * @return The scope of the token
     */
    public TokenScope getScope()
    {
        return scope;
    }

    /**
     * @return General details about the token
     */
    public TokenDetails getDetails()
    {
        return details;
    }

    /**
     * @return Payment / instrument details used to carry out a payment, may not always be present
     */
    public TokenInstrument getInstrument()
    {
        return instrument;
    }

    /**
     * @return The shopper ID to which this token belongs, as provided by the merchant; only present for shopper tokens
     */
    public String getShopperId()
    {
        return shopperId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (paymentTokenId != null ? !paymentTokenId.equals(token.paymentTokenId) : token.paymentTokenId != null)
            return false;
        if (scope != token.scope) return false;
        if (details != null ? !details.equals(token.details) : token.details != null) return false;
        if (instrument != null ? !instrument.equals(token.instrument) : token.instrument != null) return false;
        return shopperId != null ? shopperId.equals(token.shopperId) : token.shopperId == null;
    }

    @Override
    public int hashCode()
    {
        int result = paymentTokenId != null ? paymentTokenId.hashCode() : 0;
        result = 31 * result + (scope != null ? scope.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (instrument != null ? instrument.hashCode() : 0);
        result = 31 * result + (shopperId != null ? shopperId.hashCode() : 0);
        return result;
    }

}
