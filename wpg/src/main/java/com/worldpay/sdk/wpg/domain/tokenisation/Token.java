package com.worldpay.sdk.wpg.domain.tokenisation;

/**
 * Represents a tokenised payment instrument.
 */
public class Token
{
    private final TokenScope scope;
    private final TokenDetails details;
    private final TokenInstrument instrument;
    private final String shopperId;

    public Token(TokenDetails details, TokenInstrument instrument, String shopperId)
    {
        this.scope = (shopperId != null && shopperId.length() > 0 ? TokenScope.SHOPPER : TokenScope.MERCHANT);
        this.details = details;
        this.instrument = instrument;
        this.shopperId = shopperId;
    }

    /**
     * @return scope of the token
     */
    public TokenScope getScope()
    {
        return scope;
    }

    /**
     * @return general details about the token's life
     */
    public TokenDetails getDetails()
    {
        return details;
    }

    /**
     * @return payment / instrument details used to carry out a payment, may not always be present
     */
    public TokenInstrument getInstrument()
    {
        return instrument;
    }

    /**
     * @return the shopper ID to which this token belongs, as provided by the merchant
     */
    public String getShopperId()
    {
        return shopperId;
    }

}
