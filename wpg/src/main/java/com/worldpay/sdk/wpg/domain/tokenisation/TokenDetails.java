package com.worldpay.sdk.wpg.domain.tokenisation;

import java.time.LocalDateTime;

/**
 * Details about a token's lifecycle.
 */
public class TokenDetails
{
    private final String paymentTokenId;
    private final LocalDateTime tokenExpiry;
    private final String tokenEvent;
    private final String eventReference;
    private final String eventReason;

    public TokenDetails(String paymentTokenId, LocalDateTime tokenExpiry, String tokenEvent, String eventReference, String eventReason)
    {
        this.paymentTokenId = paymentTokenId;
        this.tokenExpiry = tokenExpiry;
        this.tokenEvent = tokenEvent;
        this.eventReference = eventReference;
        this.eventReason = eventReason;
    }

    /**
     * @return Token identifier, used to carry out future payments using this token
     */
    public String getPaymentTokenId()
    {
        return paymentTokenId;
    }

    /**
     * @return Time at which the token will expire and cannot be used (optional)
     */
    public LocalDateTime getTokenExpiry()
    {
        return tokenExpiry;
    }

    /**
     * @return Outcome of attempting to create a token (optional)
     */
    public String getTokenEvent()
    {
        return tokenEvent;
    }

    /**
     * @return Event identifier (optional)
     */
    public String getEventReference()
    {
        return eventReference;
    }

    /**
     * @return Reason the token was created (as specified by merchant) (optional)
     */
    public String getEventReason()
    {
        return eventReason;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenDetails that = (TokenDetails) o;

        if (paymentTokenId != null ? !paymentTokenId.equals(that.paymentTokenId) : that.paymentTokenId != null)
            return false;
        if (tokenExpiry != null ? !tokenExpiry.equals(that.tokenExpiry) : that.tokenExpiry != null) return false;
        if (tokenEvent != null ? !tokenEvent.equals(that.tokenEvent) : that.tokenEvent != null) return false;
        if (eventReference != null ? !eventReference.equals(that.eventReference) : that.eventReference != null)
            return false;
        return eventReason != null ? eventReason.equals(that.eventReason) : that.eventReason == null;
    }

    @Override
    public int hashCode()
    {
        int result = paymentTokenId != null ? paymentTokenId.hashCode() : 0;
        result = 31 * result + (tokenExpiry != null ? tokenExpiry.hashCode() : 0);
        result = 31 * result + (tokenEvent != null ? tokenEvent.hashCode() : 0);
        result = 31 * result + (eventReference != null ? eventReference.hashCode() : 0);
        result = 31 * result + (eventReason != null ? eventReason.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "TokenDetails{" +
                "paymentTokenId='" + paymentTokenId + '\'' +
                ", tokenExpiry=" + tokenExpiry +
                ", tokenEvent='" + tokenEvent + '\'' +
                ", eventReference='" + eventReference + '\'' +
                ", eventReason='" + eventReason + '\'' +
                '}';
    }

}
