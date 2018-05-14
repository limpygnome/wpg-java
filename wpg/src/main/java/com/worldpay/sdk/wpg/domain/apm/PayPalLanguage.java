package com.worldpay.sdk.wpg.domain.apm;

/**
 * Supported languages for PayPal.
 */
public enum PayPalLanguage
{
    ENGLISH("gb"),
    GERMAN("de"),
    FRENCH("fr"),
    ITALIAN("it"),
    SPANISH("es"),
    POLISH("pl"),
    CHINESE("zh");

    public final String LANGUAGE_CODE;

    PayPalLanguage(String LANGUAGE_CODE)
    {
        this.LANGUAGE_CODE = LANGUAGE_CODE;
    }
}
