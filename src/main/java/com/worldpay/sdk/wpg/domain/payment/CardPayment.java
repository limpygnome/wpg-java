package com.worldpay.sdk.wpg.domain.payment;

import com.worldpay.sdk.wpg.domain.payment.conversion.CurrencyConversionRequired;
import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsRequired;
import com.worldpay.sdk.wpg.exception.WpgRequestException;

import java.io.Serializable;

public class CardPayment implements Serializable
{
    private static final long serialVersionUID = 1L;

    private Payment payment;
    private CurrencyConversionRequired currencyConversionRequired;
    private ThreeDsRequired threeDsRequired;

    private CardPaymentStatus status;

    public CardPayment(Payment payment) throws WpgRequestException
    {
        this.payment = payment;
        this.status = CardPaymentStatus.PAYMENT_STATUS;
    }

    public CardPayment(CurrencyConversionRequired currencyConversionRequired)
    {
        this.currencyConversionRequired = currencyConversionRequired;
        this.status = CardPaymentStatus.CURRENCY_CONVERSION_REQUESTED;
    }

    public CardPayment(ThreeDsRequired threeDsRequired)
    {
        this.threeDsRequired = threeDsRequired;
        this.status = CardPaymentStatus.THREEDS_REQUESTED;
    }

    public CardPaymentStatus getStatus()
    {
        return status;
    }

    public Payment getPayment()
    {
        return payment;
    }

    public CurrencyConversionRequired getCurrencyConversionRequired()
    {
        return currencyConversionRequired;
    }

    public ThreeDsRequired getThreeDsRequired()
    {
        return threeDsRequired;
    }

}
