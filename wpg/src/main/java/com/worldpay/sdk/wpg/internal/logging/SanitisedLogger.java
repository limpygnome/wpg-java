package com.worldpay.sdk.wpg.internal.logging;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Provides functions for sanitising logging and output.
 *
 * Currently this is based on known XML elements, and anything that potentially looks like a card.
 */
public class SanitisedLogger
{
    private static final Pattern CARD_NUMBER = Pattern.compile("[0-9]{10,19}", Pattern.MULTILINE);
    private static final Pattern ELEMENTS_CARD_NUMBER = Pattern.compile("<cardNumber>(.{6})?.+(.{4})</cardNumber>", Pattern.MULTILINE);
    private static final Pattern ELEMENTS_CARD_DETAILS = Pattern.compile("<(cardHolderName)>(.{6})?.+(.{4})</(\\1)>", Pattern.MULTILINE);
    private static final Pattern ELEMENTS_CARD_CVC = Pattern.compile("<cvc>(.+)</cvc>", Pattern.MULTILINE);
    private static final Pattern ELEMENTS_ADDRESS = Pattern.compile("<(firstName|lastName|street|houseName|houseNumber|houseNumberExtension|address1|address2|address3|postalCode|city|state|countryCode|telephoneNumber|shopperEmailAddress)>(.+)</(\\1)>", Pattern.MULTILINE);

    /**
     * Invokes logger with message sanitised.
     *
     * @param logger logger
     * @param level log level
     * @param message message (to be sanitised)
     */
    public static void log(Logger logger, Level level, String message)
    {
        String sanitised = sanitise(message);
        logger.log(level, sanitised);
    }

    /**
     * @param message raw input
     * @return sanitised output
     */
    public static String sanitise(String message)
    {
        String sanitised = sanitiseCardNumbers(message);
        sanitised = sanitisePIIData(sanitised);
        return sanitised;
    }

    private static String sanitiseCardNumbers(String message)
    {
        // XML element for card
        String result = ELEMENTS_CARD_NUMBER.matcher(message).replaceAll("<cardNumber>$1***$2</cardNumber>");

        // XML element for cvc
        result = ELEMENTS_CARD_CVC.matcher(result).replaceAll("<cvc>***</cvc>");

        // Final round just in case...
        result = CARD_NUMBER.matcher(result).replaceAll("*** potential card number***");

        return result;
    }

    private static String sanitisePIIData(String message)
    {
        String result = ELEMENTS_ADDRESS.matcher(message).replaceAll("<$1>***</$1>");
        result = ELEMENTS_CARD_DETAILS.matcher(result).replaceAll("<$1>***</$1>");
        return result;
    }

}
