package com.worldpay.sdk.wpg.domain.notification;

public class BatchError
{
    private final String orderCode;
    private final int code;
    private final String text;

    public BatchError(String orderCode, int code, String text)
    {
        this.orderCode = orderCode;
        this.code = code;
        this.text = text;
    }

    public String getOrderCode()
    {
        return orderCode;
    }

    public int getCode()
    {
        return code;
    }

    public String getText()
    {
        return text;
    }
}
