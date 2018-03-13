package com.worldpay.sdk.wpg.exception;

public class WpgRequestException extends WpgException
{

    public WpgRequestException(String message)
    {
        super(message);
    }

    public WpgRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
