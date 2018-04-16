package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * The ISO8583 result from the acquirer.
 *
 * TODO change code into enum
 *
 * @see <a href="http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/reference/usefultables.htm#Acquirer">http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/reference/usefultables.htm#Acquirer</a>
 */
public class ISO8583Result
{
    private final String code;
    private final String description;

    public ISO8583Result(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public String getCode()
    {
        return code;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return "ISO8583Result{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
