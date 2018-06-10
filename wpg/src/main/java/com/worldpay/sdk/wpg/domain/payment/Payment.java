package com.worldpay.sdk.wpg.domain.payment;

import com.worldpay.sdk.wpg.domain.payment.result.AvsResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvvResult;
import com.worldpay.sdk.wpg.domain.payment.result.Balance;
import com.worldpay.sdk.wpg.domain.payment.result.CardDetailsResultResult;
import com.worldpay.sdk.wpg.domain.payment.result.CvcResult;
import com.worldpay.sdk.wpg.domain.payment.result.ISO8583Result;
import com.worldpay.sdk.wpg.domain.payment.result.PayoutAuthorisationResult;
import com.worldpay.sdk.wpg.domain.payment.result.RiskScoreResult;
import com.worldpay.sdk.wpg.domain.payment.result.ThreeDSecureResult;
import com.worldpay.sdk.wpg.domain.tokenisation.Token;

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
    private final PaymentMethodType paymentMethodType;
    private final String paymentMethodMask;
    private final Amount amount;
    private final LastEvent lastEvent;
    private final Balance balance;

    private final CardDetailsResultResult cardDetailsResultResult;
    private final PayoutAuthorisationResult payoutAuthorisationResult;
    private final ISO8583Result iso8583Result;
    private final ThreeDSecureResult threeDSecureResult;
    private final AvsResult avsResult;
    private final CvcResult cvcResult;
    private final AvvResult avvResult;
    private final RiskScoreResult riskScoreResult;
    private final Token token;

    public Payment(PaymentMethodType paymentMethodType, String paymentMethodMask, Amount amount, LastEvent lastEvent, Balance balance,
                   CardDetailsResultResult cardDetailsResultResult, PayoutAuthorisationResult payoutAuthorisationResult,
                   ISO8583Result iso8583Result, ThreeDSecureResult threeDSecureResult, AvsResult avsResult,
                   CvcResult cvcResult, AvvResult avvResult, RiskScoreResult riskScoreResult,
                   Token token)
    {
        this.paymentMethodType = paymentMethodType;
        this.paymentMethodMask = paymentMethodMask;
        this.amount = amount;
        this.lastEvent = lastEvent;
        this.balance = balance;
        this.cardDetailsResultResult = cardDetailsResultResult;
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
     * When a card child brand, e.g. VISA_CREDIT, is used, this will return VISA instead.
     *
     * @return Payment method used; may be null when unknown by the SDK
     */
    public PaymentMethodType getPaymentMethodType()
    {
        return paymentMethodType;
    }

    /**
     * This is the name of the payment method used by the gateway.
     *
     * @return Payment method used (raw gateway mask)
     */
    public String getPaymentMethodMask()
    {
        return paymentMethodMask;
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
    public CardDetailsResultResult getCardDetailsResultResult()
    {
        return cardDetailsResultResult;
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
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (paymentMethodType != payment.paymentMethodType) return false;
        if (amount != null ? !amount.equals(payment.amount) : payment.amount != null) return false;
        if (lastEvent != payment.lastEvent) return false;
        if (balance != null ? !balance.equals(payment.balance) : payment.balance != null) return false;
        if (cardDetailsResultResult != null ? !cardDetailsResultResult.equals(payment.cardDetailsResultResult) : payment.cardDetailsResultResult != null) return false;
        if (payoutAuthorisationResult != null ? !payoutAuthorisationResult.equals(payment.payoutAuthorisationResult) : payment.payoutAuthorisationResult != null)
            return false;
        if (iso8583Result != null ? !iso8583Result.equals(payment.iso8583Result) : payment.iso8583Result != null)
            return false;
        if (threeDSecureResult != null ? !threeDSecureResult.equals(payment.threeDSecureResult) : payment.threeDSecureResult != null)
            return false;
        if (avsResult != null ? !avsResult.equals(payment.avsResult) : payment.avsResult != null) return false;
        if (cvcResult != null ? !cvcResult.equals(payment.cvcResult) : payment.cvcResult != null) return false;
        if (avvResult != null ? !avvResult.equals(payment.avvResult) : payment.avvResult != null) return false;
        if (riskScoreResult != null ? !riskScoreResult.equals(payment.riskScoreResult) : payment.riskScoreResult != null)
            return false;
        return token != null ? token.equals(payment.token) : payment.token == null;
    }

    @Override
    public int hashCode()
    {
        int result = paymentMethodType != null ? paymentMethodType.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (lastEvent != null ? lastEvent.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (cardDetailsResultResult != null ? cardDetailsResultResult.hashCode() : 0);
        result = 31 * result + (payoutAuthorisationResult != null ? payoutAuthorisationResult.hashCode() : 0);
        result = 31 * result + (iso8583Result != null ? iso8583Result.hashCode() : 0);
        result = 31 * result + (threeDSecureResult != null ? threeDSecureResult.hashCode() : 0);
        result = 31 * result + (avsResult != null ? avsResult.hashCode() : 0);
        result = 31 * result + (cvcResult != null ? cvcResult.hashCode() : 0);
        result = 31 * result + (avvResult != null ? avvResult.hashCode() : 0);
        result = 31 * result + (riskScoreResult != null ? riskScoreResult.hashCode() : 0);
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Payment{" +
                "paymentMethod=" + paymentMethodType +
                ", amount=" + amount +
                ", lastEvent=" + lastEvent +
                ", balance=" + balance +
                ", cardDetails=" + cardDetailsResultResult +
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
