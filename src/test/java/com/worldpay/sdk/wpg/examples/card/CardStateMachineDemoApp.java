package com.worldpay.sdk.wpg.examples.card;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.CountryCode;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.response.ResponseType;
import com.worldpay.sdk.wpg.response.PaymentResultResponse;
import com.worldpay.sdk.wpg.response.approval.CurrencyConversionResponse;
import com.worldpay.sdk.wpg.response.payment.PaymentResponse;
import com.worldpay.sdk.wpg.response.threeds.ThreeDsRequestedResponse;

public class CardStateMachineDemoApp
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
        CardDetails cardDetails = new CardDetails("4444333322221111", 1, 2020, "Cardholder name", 123);

        try
        {
            // create order
            PaymentResultResponse response = (PaymentResultResponse) new CardPaymentRequest()
                    .orderDetails(orderDetails)
                    .cardDetails(cardDetails)
                    .billingAddress(address)
                    .shippingAddress(address)
                    .shopper(shopper)
                    .send(gatewayContext);

            ResponseType result;
            boolean continuePayment = true;

            do
            {
                result = response.getResult();

                switch (response.getResult())
                {
                    case CURRENCY_CONVERSION_REQUESTED:
                        CurrencyConversionResponse currencyConversion = (CurrencyConversionResponse) response;
                        Amount approvalAmount = currencyConversion.getAmount();

                        // prompt to continue
                        System.out.println("The order will need to convert the amount to " + approvalAmount.getValue() + " " + approvalAmount.getCurrency() + ", continue?");

                        break;
                    case THREEDS_REQUESTED:
                        ThreeDsRequestedResponse threeDs = (ThreeDsRequestedResponse) response;
                        break;
                    case ORDER_STATUS:
                        PaymentResponse orderStatus = (PaymentResponse) response;
                        break;
                    default:
                        throw new IllegalStateException("Unhandled response - result=" + response.getResult());
                }
            }
            while (continuePayment && result != ResponseType.ORDER_STATUS);
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
