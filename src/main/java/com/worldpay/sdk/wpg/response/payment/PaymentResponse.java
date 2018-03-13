package com.worldpay.sdk.wpg.response.payment;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.domain.payment.result.Balance;
import com.worldpay.sdk.wpg.domain.payment.result.ThreeDSecureResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvsResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvvResult;
import com.worldpay.sdk.wpg.domain.payment.result.CardResult;
import com.worldpay.sdk.wpg.domain.payment.result.CvcResult;
import com.worldpay.sdk.wpg.domain.payment.result.ISO8583Result;
import com.worldpay.sdk.wpg.domain.payment.result.PayoutAuthorisationResult;
import com.worldpay.sdk.wpg.domain.payment.result.RiskScoreResult;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenCardDetails;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.ResponseType;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.XmlResponse;
import com.worldpay.sdk.wpg.xml.serializer.AmountSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.AvsResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.AvvResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.BalanceSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.CardResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.CvcResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.ISO8583ResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.PaymentMethodSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.PayoutAuthorisationResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.RiskScoreResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.ThreeDSecureResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.tokenisation.TokenCardDetailsSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.tokenisation.TokenDetailsSerializer;

/**
 * Response from a payment attempt.
 */
public class PaymentResponse extends XmlResponse
{
    private final PaymentMethod paymentMethod;
    private final Amount amount;
    private final PaymentStatus status;
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

    public PaymentResponse(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException
    {
        super(httpResponse, builder);

        // payment method
        String paymentMethodMask = builder.getCdata("paymentMethod");
        paymentMethod = PaymentMethodSerializer.convert(paymentMethodMask);

        // amount
        builder.e("amount");
        amount = AmountSerializer.read(builder);
        builder.up();

        // last event
        String lastEventRaw = builder.getCdata("lastEvent");
        status = PaymentStatus.valueOf(lastEventRaw);

        // read results
        cardResult = CardResultSerializer.read(builder);
        payoutAuthorisationResult = PayoutAuthorisationResultSerializer.read(builder);
        iso8583Result = ISO8583ResultSerializer.read(builder);
        threeDSecureResult = ThreeDSecureResultSerializer.read(builder);
        avsResult = AvsResultSerializer.read(builder);
        cvcResult = CvcResultSerializer.read(builder);
        avvResult = AvvResultSerializer.read(builder);
        balance = BalanceSerializer.read(builder);
        riskScoreResult = RiskScoreResultSerializer.read(builder);
        tokenDetails = TokenDetailsSerializer.read(builder);
        tokenCardDetails = TokenCardDetailsSerializer.read(builder);
        builder.reset();
    }

    @Override
    public ResponseType getResponseType()
    {
        return ResponseType.PAYMENT_STATUS;
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
     * The last event / status of the order.
     *
     * @return status
     */
    public PaymentStatus getStatus()
    {
        return status;
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
