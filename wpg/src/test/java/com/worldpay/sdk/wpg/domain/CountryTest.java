package com.worldpay.sdk.wpg.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.*;

import static org.junit.Assert.*;

public class CountryTest
{

    @Test
    public void getByCode_lowerCase()
    {
        Country code = Country.getByCode("gb");
        assertThat(code, is(Country.GREAT_BRITAIN));
    }

    @Test
    public void getByCode_upperCase()
    {
        Country code = Country.getByCode("GB");
        assertThat(code, is(Country.GREAT_BRITAIN));
    }

}
