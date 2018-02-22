package com.worldpay.sdk.wpg.xml;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.Response;
import com.worldpay.sdk.wpg.response.approval.CurrencyConversionResponse;
import com.worldpay.sdk.wpg.response.error.ErrorResponse;
import com.worldpay.sdk.wpg.response.redirect.RedirectUrlResponse;
import com.worldpay.sdk.wpg.response.threeds.RequestThreeDsResponse;

/**
 * Takes XML response and delegates it to the correct response handler class based on matching certain elements.
 */
public class XmlResponseRecognizer
{

    public Response match(HttpResponse httpResponse) throws WpgRequestException
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
            }
        }

        if (response == null)
        {
            throw new WpgRequestException("Unable to handle server response: \n" + httpResponse.getBody());
        }

        return response;
    }

    private Response handleOrderStatus(HttpResponse httpResponse, XmlBuilder builder) throws WpgRequestException
    {
        Response response = null;

        if (builder.hasE("error"))
        {
            response = new ErrorResponse(httpResponse, builder);
        }
        else if (builder.hasE("requestInfo"))
        {
            if (builder.hasE("request3DSecure"))
            {
                response = new RequestThreeDsResponse(httpResponse, builder);
            }
        }
        else if (builder.hasE("fxApprovalRequired"))
        {
            response = new CurrencyConversionResponse(httpResponse, builder);
        }
        else if (builder.hasE("payment"))
        {
            // TODO payment
        }
        else if (builder.hasE("reference"))
        {
            response = new RedirectUrlResponse(httpResponse, builder);
        }

        return response;
    }

}
