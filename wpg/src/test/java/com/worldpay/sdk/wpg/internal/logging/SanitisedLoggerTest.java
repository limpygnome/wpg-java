package com.worldpay.sdk.wpg.internal.logging;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class SanitisedLoggerTest
{

    @Parameterized.Parameters
    public static Collection<Object[]> params()
    {
        return Arrays.asList(new Object[][]{
                {"<cardNumber>4444333322221129</cardNumber>", "<cardNumber>444433***1129</cardNumber>"},
                {"abc\n<cardNumber>4444333322221129</cardNumber>\nsdsadsad", "abc\n<cardNumber>444433***1129</cardNumber>\nsdsadsad"},
                {"<cvc>123</cvc>", "<cvc>***</cvc>"},
                {"4444333322221129", "*** potential card number***"},
                {"<firstName>test</firstName>", "<firstName>***</firstName>"},
                {"<lastName>test</lastName>", "<lastName>***</lastName>"},
                {"<street>test</street>", "<street>***</street>"},
                {"<houseName>test</houseName>", "<houseName>***</houseName>"},
                {"<houseNumber>test</houseNumber>", "<houseNumber>***</houseNumber>"},
                {"<houseNumberExtension>test</houseNumberExtension>", "<houseNumberExtension>***</houseNumberExtension>"},
                {"<address1>test</address1>", "<address1>***</address1>"},
                {"<address2>test</address2>", "<address2>***</address2>"},
                {"<address3>test</address3>", "<address3>***</address3>"},
                {"<postalCode>test</postalCode>", "<postalCode>***</postalCode>"},
                {"<city>test</city>", "<city>***</city>"},
                {"<state>test</state>", "<state>***</state>"},
                {"<countryCode>test</countryCode>", "<countryCode>***</countryCode>"},
                {"<telephoneNumber>test</telephoneNumber>", "<telephoneNumber>***</telephoneNumber>"},
                {"<shopperEmailAddress>test@test.com</shopperEmailAddress>", "<shopperEmailAddress>***</shopperEmailAddress>"},
                {"<cardHolderName>John Doe</cardHolderName>", "<cardHolderName>***</cardHolderName>"}
        });
    }

    private String input;
    private String expected;

    public SanitisedLoggerTest(String input, String expected)
    {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void sanitise()
    {
        String sanitised = SanitisedLogger.sanitise(input);
        assertEquals(expected, sanitised);
    }

}