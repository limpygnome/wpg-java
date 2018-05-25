package com.worldpay.sdk.wpg.domain.tokenisation;

import java.time.LocalDateTime;

/**
 * Details for tokenising a payment.
 *
 * When making a payment, we can store the payment details for some time. You can then make future payments even quicker,
 * by using the token identifier in the response to a successful payment.
 *
 * <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/tokenisation/createshoppertokens.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/tokenisation/createshoppertokens.htm</a>
 * <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/tokenisation/createmerchanttokens.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/tokenisation/createmerchanttokens.htm</a>
 */
public class CreateTokenDetails
{
    private TokenScope scope;
    private String eventReference;
    private String reason;
    private int shortLifeMins;
    private LocalDateTime expiry;

    /**
     * Sets up a shopper token.
     */
    public CreateTokenDetails()
    {
        this(null, null);
    }

    /**
     * Sets up a shopper token.
     *
     * @param eventReference An identifier for this token creation event (optional) (can only be alpha-numeric or _)
     * @param reason Reason to create the token (optional)
     */
    public CreateTokenDetails(String eventReference, String reason)
    {
        this(null, eventReference, reason, 0, null);
    }

    /**
     * @param scope The scope of the token (optional), defaults to {@link TokenScope#SHOPPER}
     * @param eventReference An identifier for this token creation event (optional) (can only be alpha-numeric or _)
     * @param reason Reason to create the token (optional)
     */
    public CreateTokenDetails(TokenScope scope, String eventReference, String reason)
    {
        this(scope, eventReference, reason, null);
    }

    /**
     * @param scope The scope of the token (optional), defaults to {@link TokenScope#SHOPPER}
     * @param eventReference An identifier for this token creation event (optional) (can only be alpha-numeric or _)
     * @param reason Reason to create the token (optional)
     * @param expiry TIme at which the token should expire (optional); this may not be ignored
     */
    public CreateTokenDetails(TokenScope scope, String eventReference, String reason, LocalDateTime expiry)
    {
        this(scope, eventReference, reason, 0, expiry);
    }

    /**
     * @param scope The scope of the token (optional), defaults to {@link TokenScope#SHOPPER}
     * @param eventReference An identifier for this token creation event (optional) (can only be alpha-numeric or _) (max 64 chars)
     * @param reason Reason to create the token (optional) (max 255 chars)
     * @param shortLifeMins 1 to 120 minutes
     */
    public CreateTokenDetails(TokenScope scope, String eventReference, String reason, int shortLifeMins)
    {
        this(scope, eventReference, reason, shortLifeMins, null);
    }

    private CreateTokenDetails(TokenScope scope, String eventReference, String reason, int shortLifeMins, LocalDateTime expiry)
    {
        this.scope = scope != null ? scope : TokenScope.SHOPPER;
        this.eventReference = eventReference;
        this.reason = reason;
        this.shortLifeMins = shortLifeMins;
        this.expiry = expiry;
    }

    /**
     * The scope refers to the type of token, whether it's a merchant or shopper token.
     *
     * THe difference is that a shopper token has an associated shopper ID, which can later be used to query all the
     * tokens belonging to a shopper.
     *
     * @see com.worldpay.sdk.wpg.request.tokenisation.FetchTokensByShopperRequest
     *
     * @return Scope; never null
     */
    public TokenScope getScope()
    {
        return scope;
    }

    /**
     * A unique identifier for the token creation event.
     *
     * @return
     */
    public String getEventReference()
    {
        return eventReference;
    }

    public String getReason()
    {
        return reason;
    }

    public int getShortLifeMins()
    {
        return shortLifeMins;
    }

    public LocalDateTime getExpiry()
    {
        return expiry;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateTokenDetails that = (CreateTokenDetails) o;

        if (shortLifeMins != that.shortLifeMins) return false;
        if (scope != that.scope) return false;
        if (eventReference != null ? !eventReference.equals(that.eventReference) : that.eventReference != null)
            return false;
        if (reason != null ? !reason.equals(that.reason) : that.reason != null) return false;
        return expiry != null ? expiry.equals(that.expiry) : that.expiry == null;
    }

    @Override
    public int hashCode()
    {
        int result = scope != null ? scope.hashCode() : 0;
        result = 31 * result + (eventReference != null ? eventReference.hashCode() : 0);
        result = 31 * result + (reason != null ? reason.hashCode() : 0);
        result = 31 * result + shortLifeMins;
        result = 31 * result + (expiry != null ? expiry.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "CreateTokenDetails{" +
                "scope=" + scope +
                ", eventReference='" + eventReference + '\'' +
                ", reason='" + reason + '\'' +
                ", shortLifeMins=" + shortLifeMins +
                ", expiry=" + expiry +
                '}';
    }

}
