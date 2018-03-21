package com.worldpay.sdk.wpg.domain.tokenisation;

import java.time.LocalDateTime;

public class TokenDetails
{
    private final String paymentTokenId;
    private final String shopperId;
    private final LocalDateTime tokenExpiry;
    private final String tokenEvent;
    private final String eventReference;
    private final String eventReason;

    public TokenDetails(String paymentTokenId, String shopperId, LocalDateTime tokenExpiry, String tokenEvent, String eventReference, String eventReason)
    {
        this.paymentTokenId = paymentTokenId;
        this.shopperId = shopperId;
        this.tokenExpiry = tokenExpiry;
        this.tokenEvent = tokenEvent;
        this.eventReference = eventReference;
        this.eventReason = eventReason;
    }

    public String getPaymentTokenId()
    {
        return paymentTokenId;
    }

    public String getShopperId()
    {
        return shopperId;
    }

    public LocalDateTime getTokenExpiry()
    {
        return tokenExpiry;
    }

    public String getTokenEvent()
    {
        return tokenEvent;
    }

    public String getEventReference()
    {
        return eventReference;
    }

    public String getEventReason()
    {
        return eventReason;
    }

    @Override
    public String toString()
    {
        return "TokenDetails{" +
                "paymentTokenId='" + paymentTokenId + '\'' +
                ", shopperId='" + shopperId + '\'' +
                ", tokenExpiry=" + tokenExpiry +
                ", tokenEvent='" + tokenEvent + '\'' +
                ", eventReference='" + eventReference + '\'' +
                ", eventReason='" + eventReason + '\'' +
                '}';
    }
}
