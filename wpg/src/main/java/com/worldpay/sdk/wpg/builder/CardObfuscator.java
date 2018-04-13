package com.worldpay.sdk.wpg.builder;

public class CardObfuscator
{
    public static String mask(String cardNumber)
    {
        String result;
        if (cardNumber == null)
        {
            result = "null";
        }
        else if (cardNumber.length() < 4)
        {
            result = "***";
        }
        else
        {
            result = "***" + cardNumber.substring(cardNumber.length() - 4, 4);
        }
        return result;
    }
}
