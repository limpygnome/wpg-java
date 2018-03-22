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
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.conversion.CurrencyConversionRequired;
import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsRequired;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;

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
            PaymentResponse paymentResponse = new CardPaymentRequest()
                    .orderDetails(orderDetails)
                    .cardDetails(cardDetails)
                    .billingAddress(address)
                    .shippingAddress(address)
                    .shopper(shopper)
                    .send(gatewayContext);

            PaymentStatus result;
            boolean continuePayment = true;

            do
            {
                result = paymentResponse.getStatus();
                switch (result)
                {
                    case CURRENCY_CONVERSION_REQUESTED:
                        CurrencyConversionRequired currencyConversion = paymentResponse.getCurrencyConversionRequired();
                        Amount approvalAmount = currencyConversion.getAmount();

                        // prompt to continue
                        System.out.println("The order will need to convert the amount to " + approvalAmount.getValue() + " " + approvalAmount.getCurrency() + ", continue?");

                        break;
                    case THREEDS_REQUESTED:
                        ThreeDsRequired threeDs = paymentResponse.getThreeDsRequired();
                        break;
                    case PAYMENT_RESULT:
                        Payment payment = paymentResponse.getPayment();
                        System.out.println("payment - lastEvent: " + payment.getLastEvent());
                        break;
                    default:
                        throw new IllegalStateException("Unhandled response - result=" + result);
                }
            }
            while (continuePayment && result != PaymentStatus.PAYMENT_RESULT);
        }
        catch (WpgConnectionException e)
        {
            e.printStackTrace();
        }
        catch (WpgRequestException e)
        {
            e.printStackTrace();
        }
        catch (WpgMalformedXmlException e)
        {
            e.printStackTrace();
        }
        catch (WpgErrorResponseException e)
        {
            e.printStackTrace();
        }
    }

}
