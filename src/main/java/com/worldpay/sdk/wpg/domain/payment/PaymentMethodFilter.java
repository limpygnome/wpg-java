package com.worldpay.sdk.wpg.domain.payment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PaymentMethodFilter
{
    private List<PaymentMethod> included;
    private List<PaymentMethod> excluded;

    public PaymentMethodFilter()
    {
        included = new LinkedList<>();
        excluded = new LinkedList<>();
    }

    public PaymentMethodFilter include(PaymentMethod... paymentMethod)
    {
        included.addAll(Arrays.asList(paymentMethod));
        return this;
    }

    public PaymentMethodFilter exclude(PaymentMethod... paymentMethod)
    {
        excluded.addAll(Arrays.asList(paymentMethod));
        return this;
    }

    public List<PaymentMethod> getIncluded()
    {
        return included;
    }

    public void setIncluded(List<PaymentMethod> included)
    {
        this.included = included;
    }

    public List<PaymentMethod> getExcluded()
    {
        return excluded;
    }

    public void setExcluded(List<PaymentMethod> excluded)
    {
        this.excluded = excluded;
    }

}
