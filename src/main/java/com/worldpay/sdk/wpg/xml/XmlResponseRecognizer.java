package com.worldpay.sdk.wpg.xml;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.Response;
import com.worldpay.sdk.wpg.response.approval.CurrencyConversionResponse;
import com.worldpay.sdk.wpg.response.payment.PaymentResponse;
import com.worldpay.sdk.wpg.response.redirect.RedirectUrlResponse;
import com.worldpay.sdk.wpg.response.threeds.ThreeDsRequestedResponse;

/**
 * Takes XML response and delegates it to the correct response handler class based on matching certain elements.
 */
public class XmlResponseRecognizer
{

    public Response match(HttpResponse httpResponse) throws WpgRequestException, WpgErrorResponseException, WpgMalformedXmlException
    {
        XmlBuilder builder = XmlBuilder.parse(httpResponse.getBody());

        Response response = null;

        if (builder.isCurrentTag("paymentService"))
        {
            if (builder.hasE("reply"))
            {
                if (builder.hasE("orderStatus"))
                {
                    response = handleOrderStatus(httpResponse, builder);
                }
                else if (builder.hasE("error"))
                {
                    handleError(httpResponse, builder);
                }
            }
        }

        if (response == null)
        {
            throw new WpgRequestException("Unable to handle server response: \n" + httpResponse.getBody());
        }

        return response;
    }

    private Response handleOrderStatus(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException, WpgErrorResponseException
    {
        Response response = null;

        if (builder.hasE("error"))
        {
            handleError(httpResponse, builder);
        }
        else if (builder.hasE("requestInfo"))
        {
            if (builder.hasE("request3DSecure"))
            {
                response = new ThreeDsRequestedResponse(httpResponse, builder);
            }
        }
        else if (builder.hasE("fxApprovalRequired"))
        {
            response = new CurrencyConversionResponse(httpResponse, builder);
        }
        else if (builder.hasE("payment"))
        {
            response = new PaymentResponse(httpResponse, builder);
        }
        else if (builder.hasE("reference"))
        {
            response = new RedirectUrlResponse(httpResponse, builder);
        }

        // check we have something
        if (response == null)
        {
            throw new WpgErrorResponseException(0, "Unable to handle response from gateway, not recognized", httpResponse);
        }

        return response;
    }

    private void handleError(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException, WpgErrorResponseException
    {
        long code = builder.aToLong("code");
        String message = builder.cdata();
        throw new WpgErrorResponseException(code, message, httpResponse);
    }

}
