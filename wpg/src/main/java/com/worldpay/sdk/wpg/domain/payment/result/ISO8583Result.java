package com.worldpay.sdk.wpg.domain.payment.result;

/**
 * The ISO8583 result from the acquirer.
 *
 * TODO future improvement - change code into enum
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

    /**
     * @return Result code; refer to XML docs
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @return Result code; refer to XML docs
     */
    public String getDescription()
    {
        return description;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ISO8583Result that = (ISO8583Result) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode()
    {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
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
