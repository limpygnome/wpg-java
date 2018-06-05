package com.worldpay.sdk.wpg.builder;

import static org.hamcrest.core.Is.is;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class PaymentPagesRedirectBuilderTest
{
    private static final String URL = "https://payments.worldpay.com/app/hpp/sdkdemo?Test=XYZ";

    @Test
    public void locale()
    {
        PaymentPagesRedirectBuilder builder = new PaymentPagesRedirectBuilder(URL);
        builder.languageAndCountry(Locale.CANADA_FRENCH);

        String url = builder.build();

        assertThat(url, is(URL + "&country=ca&language=fr"));
    }

}
