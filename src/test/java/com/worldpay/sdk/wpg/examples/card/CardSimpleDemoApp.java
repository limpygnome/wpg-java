package com.worldpay.sdk.wpg.examples.card;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.CountryCode;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Session;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.response.PaymentResultResponse;
import com.worldpay.sdk.wpg.response.Response;
import com.worldpay.sdk.wpg.response.approval.CurrencyConversionResponse;
import com.worldpay.sdk.wpg.response.error.ErrorResponse;
import com.worldpay.sdk.wpg.response.payment.PaymentResponse;
import com.worldpay.sdk.wpg.response.threeds.ThreeDsRequestedResponse;

public class CardSimpleDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        Auth auth = new UserPassAuth("NGPPTESTMERCH1", "live2014", "NGPPTESTMERCH1", "1008775");
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, auth);

        // build order details
        Amount amount = new Amount(Currency.GBP, 2L, 1234L);
        OrderDetails orderDetails = new OrderDetails("test order", amount);

        Address address = new Address("123 test address", "blah", "1234", CountryCode.GREAT_BRITAIN);
        Shopper shopper = new Shopper("test@test.com");

        // build card details
        CardDetails cardDetails = new CardDetails("4444333322221111", 1, 2020, "Cardholder name", 123, address);

        try
        {
            // create order
            Response response = new CardPaymentRequest()
                    .orderDetails(orderDetails)
                    .cardDetails(cardDetails)
                    .session(new Session("test-ip"))
                    .billingAddress(address)
                    .shippingAddress(address)
                    .shopper(shopper)
                    .send(gatewayContext);

            switch (response.getResponseType())
            {
                case CURRENCY_CONVERSION_REQUESTED:
                    CurrencyConversionResponse currencyConversion = (CurrencyConversionResponse) response;
                    // do something...
                    break;
                case THREEDS_REQUESTED:
                    ThreeDsRequestedResponse threeDs = (ThreeDsRequestedResponse) response;
                    // do something...
                    break;
                case PAYMENT_STATUS:
                    PaymentResponse orderStatus = (PaymentResponse) response;
                    // do something...
                    break;
                case ERROR:
                    ErrorResponse error = (ErrorResponse) response;
                    System.err.println("Request failed - code=" + error.getCode() + ", message=" + error.getMessage());
                    break;
                default:
                    throw new IllegalStateException("Unhandled response - type=" + response.getResponseType());
            }
        }
        catch (WpgConnectionException e)
        {
            e.printStackTrace();
        }
        catch (WpgRequestException e)
        {
            e.printStackTrace();
        }
    }

}
