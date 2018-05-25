package com.worldpay.sdk.wpg.domain.payment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Used to filter included/excluded payment methods for Hosted Payment Pages.
 *
 * Payment methods can either be included or excluded, you cannot add payment methods to both.
 */
public class PaymentMethodFilter
{
    private List<PaymentMethod> included;
    private List<PaymentMethod> excluded;

    public PaymentMethodFilter()
    {
        included = new LinkedList<>();
        excluded = new LinkedList<>();
    }

    /**
     * @param paymentMethod Payment method to be included
     * @return Current instance
     */
    public PaymentMethodFilter include(PaymentMethod... paymentMethod)
    {
        included.addAll(Arrays.asList(paymentMethod));
        return this;
    }

    /**
     * @param paymentMethod Payment method to be excluded
     * @return Current instance
     */
    public PaymentMethodFilter exclude(PaymentMethod... paymentMethod)
    {
        excluded.addAll(Arrays.asList(paymentMethod));
        return this;
    }

    /**
     * @return Payment methods to be included
     */
    public List<PaymentMethod> getIncluded()
    {
        return included;
    }

    /**
     * @param included Payment methods to be included
     */
    public void setIncluded(List<PaymentMethod> included)
    {
        this.included = included;
    }

    /**
     * @return Payment methods to be excluded
     */
    public List<PaymentMethod> getExcluded()
    {
        return excluded;
    }

    /**
     * @param excluded Payment methods to be excluded
     */
    public void setExcluded(List<PaymentMethod> excluded)
    {
        this.excluded = excluded;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentMethodFilter that = (PaymentMethodFilter) o;

        if (included != null ? !included.equals(that.included) : that.included != null) return false;
        return excluded != null ? excluded.equals(that.excluded) : that.excluded == null;
    }

    @Override
    public int hashCode()
    {
        int result = included != null ? included.hashCode() : 0;
        result = 31 * result + (excluded != null ? excluded.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "PaymentMethodFilter{" +
                "included=" + included +
                ", excluded=" + excluded +
                '}';
    }

}
