package com.worldpay.sdk.wpg.internal.xml.adapter;

import com.worldpay.sdk.wpg.domain.notification.BatchError;
import com.worldpay.sdk.wpg.domain.notification.BatchInquiry;
import com.worldpay.sdk.wpg.domain.notification.BatchStatus;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuilder;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;

import java.util.ArrayList;
import java.util.List;

public class BatchInquiryAdapter
{

    public static BatchInquiry read(XmlResponse response) throws WpgErrorResponseException, WpgRequestException, WpgMalformedXmlException
    {
        XmlBuilder builder = response.getBuilder();

        if (!builder.hasE("reply") || !builder.hasE("batchStatus"))
        {
            throw new WpgMalformedResponseException(response);
        }

        BatchStatus status = readStatus(builder);
        int transactions = readTransactions(builder);
        List<BatchError> errors = readErrors(builder);
        BatchInquiry inquiry = new BatchInquiry(status, transactions, errors);
        return inquiry;
    }

    private static BatchStatus readStatus(XmlBuilder builder)
    {
        String statusText = builder.a("status");
        BatchStatus status = BatchStatus.valueOf(statusText.toUpperCase());
        return status;
    }

    private static int readTransactions(XmlBuilder builder) throws WpgRequestException
    {
        int transactions = builder.aToInt("transactions");
        return transactions;
    }

    private static List<BatchError> readErrors(XmlBuilder builder) throws WpgRequestException
    {
        List<XmlBuilder> elements = builder.childTags("orderStatus");
        List<BatchError> errors = new ArrayList<>(elements.size());
        for (XmlBuilder element : elements)
        {
            BatchError error = readError(element);
            errors.add(error);
        }
        return errors;
    }

    private static BatchError readError(XmlBuilder element) throws WpgRequestException
    {
        String orderCode = element.a("orderCode");
        int errorCode = element.e("error").aToInt("code");
        String text = element.cdata();

        BatchError batchError = new BatchError(orderCode, errorCode, text);
        return batchError;
    }

}
