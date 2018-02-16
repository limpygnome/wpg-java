package com.worldpay.sdk.wpg.validation;

public class Assert
{

    public static void notNull(Object object, String message)
    {
        if (object == null)
        {
            throw new IllegalArgumentException(message);
        }
    }

}
