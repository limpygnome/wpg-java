package com.worldpay.sdk.wpg.domain;

public enum Country
{
    GREAT_BRITAIN("GB");

    public final String ISO3166_1_ALPHA_2_COUNTRY_CODE;

    Country(String code)
    {
        this.ISO3166_1_ALPHA_2_COUNTRY_CODE = code;
    }

    /**
     * Fetches a known supported country code.
     *
     * @param code country code
     * @return matched instance, or null
     */
    public static Country getByCode(String code)
    {
        if (code == null)
        {
            throw new NullPointerException("Country code is null");
        }

        // Convert to upper-case
        String codeUpper = code.toUpperCase();

        for (Country cc : values())
        {
            if (cc.ISO3166_1_ALPHA_2_COUNTRY_CODE.equals(codeUpper))
            {
                return cc;
            }
        }

        return null;
    }

}
