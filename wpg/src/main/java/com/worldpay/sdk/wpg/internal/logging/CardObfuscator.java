package com.worldpay.sdk.wpg.internal.logging;

public class CardObfuscator
{
    public static String mask(String cardNumber)
    {
        String result;
        if (cardNumber == null || cardNumber.length() == 0)
        {
            result = "null";
        }
        else
        {
            int length = cardNumber.length();
            if (length < 4)
            {
                result = "***";
            }
            else
            {
                result = "***" + cardNumber.substring(length - 3, length);
            }
        }
        return result;
    }
}
