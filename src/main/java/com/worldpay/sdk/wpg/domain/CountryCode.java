package com.worldpay.sdk.wpg.domain;

public enum CountryCode
{
    GREAT_BRITAIN("GB");

    public final String ISO3166_1_ALPHA_2_COUNTRY_CODE;

    CountryCode(String code)
    {
        this.ISO3166_1_ALPHA_2_COUNTRY_CODE = code;
    }

}
