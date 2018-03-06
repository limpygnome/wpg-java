package com.worldpay.sdk.wpg.response.payment;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.domain.payment.result.ThreeDSecureResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvsResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvvResult;
import com.worldpay.sdk.wpg.domain.payment.result.CardResult;
import com.worldpay.sdk.wpg.domain.payment.result.CvcResult;
import com.worldpay.sdk.wpg.domain.payment.result.ISO8583Result;
import com.worldpay.sdk.wpg.domain.payment.result.PayoutAuthorisationResult;
import com.worldpay.sdk.wpg.domain.payment.result.RiskResult;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.ResponseType;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.XmlResponse;
import com.worldpay.sdk.wpg.xml.serializer.AmountSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.CardResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.ISO8583ResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.PaymentMethodSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.PayoutAuthorisationResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.ThreeDSecureResultSerializer;

/**
 * Response from a payment attempt.
 */
public class PaymentResponse extends XmlResponse
{
    private final PaymentMethod paymentMethod;
    private final Amount amount;
    private final PaymentStatus status;

    private final CardResult cardResult;
    private final PayoutAuthorisationResult payoutAuthorisationResult;
    private final ISO8583Result iso8583Result;
    private final ThreeDSecureResult threeDSecureResult;

    // TODO parse rest of results
    //private final AvsResult avsResult;
    //private final CvcResult cvcResult;
    //private final AvvResult avvResult;
    //private final RiskResult riskResult;

    public PaymentResponse(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException
    {
        super(httpResponse, builder);

        // payment method
        String paymentMethodMask = builder.e("paymentMethod").cdata();
        builder.up();
        paymentMethod = PaymentMethodSerializer.convert(paymentMethodMask);

        // amount
        builder.e("amount");
        amount = AmountSerializer.read(builder);
        builder.up();

        // last event
        builder.e("lastEvent");
        status = PaymentStatus.valueOf(builder.cdata());
        builder.up();

        // read results
        cardResult = CardResultSerializer.read(builder);
        payoutAuthorisationResult = PayoutAuthorisationResultSerializer.read(builder);
        iso8583Result = ISO8583ResultSerializer.read(builder);
        threeDSecureResult = ThreeDSecureResultSerializer.read(builder);

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
        return null;//avsResult;
    }

    public CvcResult getCvcResult()
    {
        return null;//cvcResult;
    }

    public AvvResult getAvvResult()
    {
        return null;// avvResult;
    }

}
