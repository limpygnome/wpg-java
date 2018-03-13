package com.worldpay.sdk.wpg.exception;

public abstract class WpgException extends Exception
{
    public WpgException(String message)
    {
        super(message);
    }

    public WpgException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public WpgException(Throwable cause)
    {
        super(cause);
    }
}
