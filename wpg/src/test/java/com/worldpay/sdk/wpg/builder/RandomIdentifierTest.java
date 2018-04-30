package com.worldpay.sdk.wpg.builder;

import org.junit.Test;

import static org.junit.Assert.*;

public class RandomIdentifierTest
{

    @Test
    public void generate_zero()
    {
        String result = RandomIdentifier.generate(0);
        assertEquals("", result);
    }

    @Test
    public void generate_asExpected()
    {
        // Large enough range ensures we get each char (eventually)
        String result = RandomIdentifier.generate(9999);
        assertTrue(result.matches("[0-9a-z]{9999}"));
    }

}
