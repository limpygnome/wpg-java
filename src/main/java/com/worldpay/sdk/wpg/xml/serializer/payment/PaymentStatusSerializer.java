package com.worldpay.sdk.wpg.xml.serializer.payment;

import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
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
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.xml.XmlBuilder;
import com.worldpay.sdk.wpg.xml.serializer.AmountSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.AvsResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.AvvResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.BalanceSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.CardResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.CvcResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.ISO8583ResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.PayoutAuthorisationResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.RiskScoreResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.result.ThreeDSecureResultSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.tokenisation.TokenCardDetailsSerializer;
import com.worldpay.sdk.wpg.xml.serializer.payment.tokenisation.TokenDetailsSerializer;

public class PaymentStatusSerializer
{
    public static Payment read(XmlBuilder builder) throws WpgRequestException
    {
        // payment method
        String paymentMethodMask = builder.getCdata("paymentMethod");
        PaymentMethod paymentMethod = PaymentMethodSerializer.convert(paymentMethodMask);

        // amount
        builder.e("amount");
        Amount amount = AmountSerializer.read(builder);
        builder.up();

        // last event
        String lastEventRaw = builder.getCdata("lastEvent");
        LastEvent lastEvent = LastEvent.valueOf(lastEventRaw);

        // read results
        CardResult cardResult = CardResultSerializer.read(builder);
        PayoutAuthorisationResult payoutAuthorisationResult = PayoutAuthorisationResultSerializer.read(builder);
        ISO8583Result iso8583Result = ISO8583ResultSerializer.read(builder);
        ThreeDSecureResult threeDSecureResult = ThreeDSecureResultSerializer.read(builder);
        AvsResult avsResult = AvsResultSerializer.read(builder);
        CvcResult cvcResult = CvcResultSerializer.read(builder);
        AvvResult avvResult = AvvResultSerializer.read(builder);
        Balance balance = BalanceSerializer.read(builder);
        RiskScoreResult riskScoreResult = RiskScoreResultSerializer.read(builder);
        TokenDetails tokenDetails = TokenDetailsSerializer.read(builder);
        TokenCardDetails tokenCardDetails = TokenCardDetailsSerializer.read(builder);
        builder.reset();

        // wrap it all up
        Payment payment = new Payment(
                paymentMethod, amount, lastEvent, balance, cardResult, payoutAuthorisationResult,
                iso8583Result, threeDSecureResult, avsResult, cvcResult, avvResult, riskScoreResult , tokenDetails,
                tokenCardDetails
        );
        return payment;
    }
}
