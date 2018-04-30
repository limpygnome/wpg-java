package com.worldpay.sdk.wpg.builder;

import org.junit.Test;

import static org.junit.Assert.*;

public class CardObfuscatorTest
{
    @Test
    public void mask_null()
    {
        String result = CardObfuscator.mask(null);
        assertEquals("null", result);
    }

    @Test
    public void mask_empty()
    {
        String result = CardObfuscator.mask("");
        assertEquals("null", result);
    }

    @Test
    public void mask_short()
    {
        String result = CardObfuscator.mask("1");
        assertEquals("***", result);
    }

    @Test
    public void mask_actual_onlyLastThree()
    {
        String result = CardObfuscator.mask("4444333322221129");
        assertEquals("***129", result);
    }
}