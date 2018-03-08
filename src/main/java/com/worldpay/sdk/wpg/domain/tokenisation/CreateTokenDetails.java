package com.worldpay.sdk.wpg.domain.tokenisation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * http://support.worldpay.com/support/kb/gg/corporate-gateway-guide-beta/content/tokenisation/createtokens.htm
 */
public class CreateTokenDetails
{
    private TokenScope scope;
    private String eventReference;
    private String reason;
    private int shortLifeMins;
    private LocalDateTime expiry;

    public CreateTokenDetails(String eventReference, String reason)
    {
        this.scope = TokenScope.SHOPPER;
        this.eventReference = eventReference;
        this.reason = reason;
        this.shortLifeMins = 0;
        this.expiry = null;
    }

    public CreateTokenDetails(TokenScope scope, String eventReference, String reason, LocalDateTime expiry)
    {
        this.scope = scope;
        this.eventReference = eventReference;
        this.reason = reason;
        this.shortLifeMins = 0;
        this.expiry = expiry;
    }

    /**
     *
     * @param scope
     * @param eventReference
     * @param reason
     * @param shortLifeMins 1 to 120 minutes
     */
    public CreateTokenDetails(TokenScope scope, String eventReference, String reason, int shortLifeMins)
    {
        this.scope = scope;
        this.eventReference = eventReference;
        this.reason = reason;
        this.shortLifeMins = shortLifeMins;
    }

    public TokenScope getScope()
    {
        return scope;
    }

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

}
