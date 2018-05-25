package com.worldpay.sdk.wpg.exception;

/**
 * Thrown when a connection issue occurs to the WPG gateway.
 */
public class WpgConnectionException extends WpgException
{

    public WpgConnectionException(Throwable cause)
    {
        super(cause);
    }

}
