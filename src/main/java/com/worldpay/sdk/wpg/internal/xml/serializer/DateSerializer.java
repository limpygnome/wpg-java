package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

import java.time.LocalDate;

public class DateSerializer
{

    public static LocalDate read(XmlBuilder builder) throws WpgRequestException
    {
        int dayOfMonth = builder.aToInt("dayOfMonth");
        int month = builder.aToInt("month");
        int year = builder.aToInt("year");

        LocalDate result = LocalDate.of(year, month, dayOfMonth);
        return result;
    }

}
