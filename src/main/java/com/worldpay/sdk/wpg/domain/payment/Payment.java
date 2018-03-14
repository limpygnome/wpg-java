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
import com.worldpay.sdk.wpg.domain.tokenisation.TokenCardDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenDetails;

/**
 * Response from a payment attempt.
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
    private final TokenDetails tokenDetails;
    private final TokenCardDetails tokenCardDetails;

    public Payment(PaymentMethod paymentMethod, Amount amount, LastEvent lastEvent, Balance balance,
                   CardResult cardResult, PayoutAuthorisationResult payoutAuthorisationResult,
                   ISO8583Result iso8583Result, ThreeDSecureResult threeDSecureResult, AvsResult avsResult,
                   CvcResult cvcResult, AvvResult avvResult, RiskScoreResult riskScoreResult,
                   TokenDetails tokenDetails, TokenCardDetails tokenCardDetails)
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
        this.tokenDetails = tokenDetails;
        this.tokenCardDetails = tokenCardDetails;
    }

    /**
     * @return payment method used
     */
    public PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * @return order amount
     */
    public Amount getAmount()
    {
        return amount;
    }

    /**
     * @return the last event to occur for the payment.
     */
    public LastEvent getLastEvent()
    {
        return lastEvent;
    }

    /**
     * Details related to the card used in the payment.
     *
     * @return card details, or null if not card payment
     */
    public CardResult getCardResult()
    {
        return cardResult;
    }

    public PayoutAuthorisationResult getPayoutAuthorisationResult()
    {
        return payoutAuthorisationResult;
    }

    public ISO8583Result getIso8583Result()
    {
        return iso8583Result;
    }

    public ThreeDSecureResult getThreeDSecureResult()
    {
        return threeDSecureResult;
    }

    public AvsResult getAvsResult()
    {
        return avsResult;
    }

    public CvcResult getCvcResult()
    {
        return cvcResult;
    }

    public AvvResult getAvvResult()
    {
        return avvResult;
    }

    public Balance getBalance()
    {
        return balance;
    }

    public RiskScoreResult getRiskScoreResult()
    {
        return riskScoreResult;
    }

    public TokenDetails getTokenDetails()
    {
        return tokenDetails;
    }

    public TokenCardDetails getTokenCardDetails()
    {
        return tokenCardDetails;
    }

}
