package com.worldpay.sdk.wpg.exception;

/**
 * THrown when a request cannot be sent to the WPG gateway.
 */
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
