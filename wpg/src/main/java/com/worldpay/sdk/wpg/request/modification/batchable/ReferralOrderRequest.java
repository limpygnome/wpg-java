package com.worldpay.sdk.wpg.request.modification.batchable;

import com.worldpay.sdk.wpg.domain.modification.ReferralAction;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.validation.Assert;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

/**
 * Not yet supported.
 */
public class ReferralOrderRequest extends XmlRequest<Void> implements BatchModificationItem
{
    // Mandatory
    private ReferralAction action;
    private String cardReferral;

    // Optional
    private String reason;

    public ReferralOrderRequest() { }

    public ReferralOrderRequest(ReferralAction action, String reason, String cardReferral)
    {
        this.action = action;
        this.reason = reason;
        this.cardReferral = cardReferral;
    }

    @Override
    protected void validate(XmlBuildParams params)
    {
        Assert.notNull(action, "Referral action is mandatory");
        Assert.notEmpty(cardReferral, "Card referral is mandatory");
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        throw new IllegalStateException("Referral request may only be carried out for batch modifications");
    }

    @Override
    protected Void adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        return null;
    }

    public ReferralOrderRequest action(ReferralAction action)
    {
        this.action = action;
        return this;
    }

    public ReferralOrderRequest reason(String reason)
    {
        this.reason = reason;
        return this;
    }

    public ReferralOrderRequest cardReferral(String cardReferral)
    {
        this.cardReferral = cardReferral;
        return this;
    }

    public ReferralAction getAction()
    {
        return action;
    }

    public String getReason()
    {
        return reason;
    }

    public String getCardReferral()
    {
        return cardReferral;
    }

}
