package com.worldpay.sdk.wpg.domain.notification;

/**
 * Represents an error from a batch of modifications.
 */
public class BatchError
{
    private final String orderCode;
    private final int code;
    private final String text;

    public BatchError(String orderCode, int code, String text)
    {
        this.orderCode = orderCode;
        this.code = code;
        this.text = text;
    }

    /**
     * @return The order code with the error
     */
    public String getOrderCode()
    {
        return orderCode;
    }

    /**
     * @return Error code
     */
    public int getCode()
    {
        return code;
    }

    /**
     * @return Error message
     */
    public String getMessage()
    {
        return text;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchError that = (BatchError) o;

        if (code != that.code) return false;
        if (orderCode != null ? !orderCode.equals(that.orderCode) : that.orderCode != null) return false;
        return text != null ? text.equals(that.text) : that.text == null;
    }

    @Override
    public int hashCode()
    {
        int result = orderCode != null ? orderCode.hashCode() : 0;
        result = 31 * result + code;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "BatchError{" +
                "orderCode='" + orderCode + '\'' +
                ", code=" + code +
                ", message='" + text + '\'' +
                '}';
    }

}
