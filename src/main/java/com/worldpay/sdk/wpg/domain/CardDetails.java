package com.worldpay.sdk.wpg.domain;

public class CardDetails
{
    private String cardNumber;
    private long expiryMonth;
    private long expiryYear;
    private long cvc;

    // TODO investigate; prolly need track data + track according to dtd
    private String track;

}
