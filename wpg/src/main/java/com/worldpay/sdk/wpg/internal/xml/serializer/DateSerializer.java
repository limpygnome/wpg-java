package com.worldpay.sdk.wpg.internal.xml.serializer;

import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateSerializer
{

    public static LocalDate readDate(XmlBuilder builder) throws WpgRequestException
    {
        int dayOfMonth = builder.aToInt("dayOfMonth");
        int month = builder.aToInt("month");
        int year = builder.aToInt("year");

        LocalDate result = LocalDate.of(year, month, dayOfMonth);
        return result;
    }

    public static LocalDateTime readDateTime(XmlBuilder builder) throws WpgRequestException
    {
        builder.e("date");

        int year = builder.aToInt("year");
        int month = builder.aToInt("month");
        int dayOfMonth = builder.aToInt("dayOfMonth");

        Integer hour = builder.aToInt("hour");
        Integer minute = builder.aToInt("minute");
        Integer second = builder.aToInt("second");

        builder.up();

        LocalDateTime result = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        return result;
    }

    public static void writeDateTime(XmlBuilder builder, LocalDateTime dateTime)
    {
        builder.e("date");
        builder.a("year", dateTime.getYear());
        builder.a("month", dateTime.getMonthValue());
        builder.a("dayOfMonth", dateTime.getDayOfMonth());
        builder.a("hour", dateTime.getHour());
        builder.a("minute", dateTime.getMinute());
        builder.a("second", dateTime.getSecond());
        builder.up();
    }

}
