package com.worldpay.sdk.wpg.domain.payment;

import com.worldpay.sdk.wpg.domain.payment.result.AvsResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvvResult;
import com.worldpay.sdk.wpg.domain.payment.result.Balance;
import com.worldpay.sdk.wpg.domain.payment.result.CardResult;
import com.worldpay.sdk.wpg.domain.payment.result.CvcResult;
import com.worldpay.sdk.wpg.domain.payment.result.ISO8583Result;
import com.worldpay.sdk.wpg.domain.payment.result.PayoutAuthorisationResult;
import com.worldpay.sdk.wpg.domain.payment.result.RiskScoreResult;
import com.worldpay.sdk.wpg.domain.payment.result.ThreeDSecureResult;
import com.worldpay.sdk.wpg.domain.tokenisation.Token;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenCardDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenDetails;

/**
 * Response from a payment attempt.
 *
 * This only represents the payment at the time of submission, with changes/updates available through order
 * notifications.
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/ordernotifications.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/ordernotifications.htm</a>
 */
public class Payment
{
    private final PaymentMethod paymentMethod;
    private final Amount amount;
    private final LastEvent lastEvent;
    private final Balance balance;

    private final CardResult cardResult;
    private final PayoutAuthorisationResult payoutAuthorisationResult;
    private final ISO8583Result iso8583Result;
    private final ThreeDSecureResult threeDSecureResult;
    private final AvsResult avsResult;
    private final CvcResult cvcResult;
    private final AvvResult avvResult;
    private final RiskScoreResult riskScoreResult;
    private final Token token;

    public Payment(PaymentMethod paymentMethod, Amount amount, LastEvent lastEvent, Balance balance,
                   CardResult cardResult, PayoutAuthorisationResult payoutAuthorisationResult,
                   ISO8583Result iso8583Result, ThreeDSecureResult threeDSecureResult, AvsResult avsResult,
                   CvcResult cvcResult, AvvResult avvResult, RiskScoreResult riskScoreResult,
                   Token token)
    {
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.lastEvent = lastEvent;
        this.balance = balance;
        this.cardResult = cardResult;
        this.payoutAuthorisationResult = payoutAuthorisationResult;
        this.iso8583Result = iso8583Result;
        this.threeDSecureResult = threeDSecureResult;
        this.avsResult = avsResult;
        this.cvcResult = cvcResult;
        this.avvResult = avvResult;
        this.riskScoreResult = riskScoreResult;
        this.token = token;
    }

    /**
     * @return Payment method used
     */
    public PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * @return Order amount
     */
    public Amount getAmount()
    {
        return amount;
    }

    /**
     * @return The last event to occur for the payment; changes can be received using order notifications
     * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/ordernotifications.htm">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/manage/ordernotifications.htm</a>
     */
    public LastEvent getLastEvent()
    {
        return lastEvent;
    }

    /**
     * @return Details relating to the card used for a payment, for card payments (may not always be present)
     */
    public CardResult getCardResult()
    {
        return cardResult;
    }

    /**
     * @return Details relating to payout authorisation
     */
    public PayoutAuthorisationResult getPayoutAuthorisationResult()
    {
        return payoutAuthorisationResult;
    }

    /**
     * @return Extended ISO8583 details from submitting card details (may not always be present)
     */
    public ISO8583Result getIso8583Result()
    {
        return iso8583Result;
    }

    /**
     * @return Result from threeds during the payment; only present after submitting threeds response
     */
    public ThreeDSecureResult getThreeDSecureResult()
    {
        return threeDSecureResult;
    }

    /**
     * @return AVS result, not always present
     */
    public AvsResult getAvsResult()
    {
        return avsResult;
    }

    /**
     * @return CVC result, not always present
     */
    public CvcResult getCvcResult()
    {
        return cvcResult;
    }

    /**
     * @return AVV result, not always present
     */
    public AvvResult getAvvResult()
    {
        return avvResult;
    }

    /**
     * @return Balance, not always present
     */
    public Balance getBalance()
    {
        return balance;
    }

    /**
     * @return Results from performing result checks, not always present
     */
    public RiskScoreResult getRiskScoreResult()
    {
        return riskScoreResult;
    }

    /**
     * @return Result from attempting to create a token, only present for tokenised payments
     */
    public Token getToken()
    {
        return token;
    }

    @Override
    public String toString()
    {
        return "Payment{" +
                "paymentMethod=" + paymentMethod +
                ", amount=" + amount +
                ", lastEvent=" + lastEvent +
                ", balance=" + balance +
                ", cardResult=" + cardResult +
                ", payoutAuthorisationResult=" + payoutAuthorisationResult +
                ", iso8583Result=" + iso8583Result +
                ", threeDSecureResult=" + threeDSecureResult +
                ", avsResult=" + avsResult +
                ", cvcResult=" + cvcResult +
                ", avvResult=" + avvResult +
                ", riskScoreResult=" + riskScoreResult +
                ", token=" + token +
                '}';
    }

}
