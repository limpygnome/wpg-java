package com.worldpay.sdk.wpg.domain;

public enum Language
{
    ENGLISH("EN");

    public final String ISO639_1_2_LANGUAGE_CODE;

    Language(String code)
    {
        this.ISO639_1_2_LANGUAGE_CODE = code;
    }

}
