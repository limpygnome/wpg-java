package com.worldpay.sdk.wpg.exception;

public class WpgRequestException extends Exception
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
