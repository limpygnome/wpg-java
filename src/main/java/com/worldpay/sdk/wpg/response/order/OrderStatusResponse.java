package com.worldpay.sdk.wpg.response.order;

import com.worldpay.sdk.wpg.domain.payment.result.AvsResult;
import com.worldpay.sdk.wpg.domain.payment.result.AvvResult;
import com.worldpay.sdk.wpg.domain.payment.result.CvcResult;
import com.worldpay.sdk.wpg.domain.payment.result.ISO8583Result;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.domain.payment.ThreeDsResult;

public class OrderStatusResponse
{
    private String paymentMethod;
    private Amount amount;
    private PaymentStatus lastEvent;

    private ISO8583Result iso8583Result;
    private ThreeDsResult threeDsResult;
    private AvsResult avsResult;
    private CvcResult cvcResult;
    private AvvResult avvResult;
}
