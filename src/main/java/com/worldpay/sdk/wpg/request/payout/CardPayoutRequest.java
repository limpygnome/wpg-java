package com.worldpay.sdk.wpg.request.payout;

import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.payout.CardPayout;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.XmlRequest;
import com.worldpay.sdk.wpg.internal.xml.XmlResponse;
import com.worldpay.sdk.wpg.internal.xml.adapter.CardPayoutAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.CardDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;

/**
 * A payment may not always be returned, as is the case for Visa. This still means the payout has been received
 * successfully.
 *
 * TODO test
 */
public class CardPayoutRequest extends XmlRequest<CardPayout>
{
    private OrderDetails orderDetails;
    private CardDetails cardDetails;

    @Override
    protected void validate(XmlBuildParams params)
    {
    }

    @Override
    protected void build(XmlBuildParams params)
    {
        OrderDetailsSerializer.decorate(params, orderDetails);
        CardDetailsSerializer.decorate(params, cardDetails);
    }

    @Override
    protected CardPayout adapt(XmlResponse response) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        CardPayoutAdapter adapter = new CardPayoutAdapter();
        CardPayout payout = adapter.read(response);
        return payout;
    }

}
