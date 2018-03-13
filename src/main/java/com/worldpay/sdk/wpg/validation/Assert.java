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

    public static void notEmpty(String object, String message)
    {
        if (object == null || object.length() == 0)
        {
            throw new IllegalArgumentException(message);
        }
    }

}
