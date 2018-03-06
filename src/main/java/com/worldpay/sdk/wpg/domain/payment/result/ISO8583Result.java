package com.worldpay.sdk.wpg.domain.payment.result;

/**
 *
 * http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/reference/usefultables.htm#Acquirer
 *
 * TODO change code into enum
 */
public class ISO8583Result
{
    private final String code;
    private final String description;

    public ISO8583Result(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public String getCode()
    {
        return code;
    }

    public String getDescription()
    {
        return description;
    }

}
