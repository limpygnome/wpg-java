package com.worldpay.sdk.wpg.builder;

import java.security.SecureRandom;

public class RandomIdentifier
{
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generate(int length)
    {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
        {
            // generate char 0-9 or a-z
            int r = secureRandom.nextInt(36);
            char c = (char) ((r < 10 ? '0' : 'W') + r);
            sb.append(c);
        }
        return sb.toString();
    }

}
