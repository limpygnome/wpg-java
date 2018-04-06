package com.worldpay.sdk.wpg.domain.tokenisation;

public class Token
{
    private final TokenDetails details;
    private final TokenInstrument instrument;
    private final String shopperId;

    public Token(TokenDetails details, TokenInstrument instrument, String shopperId)
    {
        this.details = details;
        this.instrument = instrument;
        this.shopperId = shopperId;
    }

    public TokenDetails getDetails()
    {
        return details;
    }

    public TokenInstrument getInstrument()
    {
        return instrument;
    }

    public String getShopperId()
    {
        return shopperId;
    }
}
