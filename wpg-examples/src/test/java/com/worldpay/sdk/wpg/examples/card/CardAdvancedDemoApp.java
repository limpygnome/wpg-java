package com.worldpay.sdk.wpg.examples.card;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.card.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.threeds.ThreeDsDetails;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

public class CardAdvancedDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        Auth auth = new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID);
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, auth);

        // build order details
        Amount amount = new Amount("GBP", 2L, 1234L);
        OrderDetails orderDetails = new OrderDetails("test order", amount);
        Address address = new Address("123 test address", "blah", "1234", "GB");
        Shopper shopper = new Shopper("test@test.com");

        // build card details
        CardDetails cardDetails = new CardDetails("4444333322221111", 1L, 2020L, "Cardholder name", "123");

        try
        {
            // make payment
            PaymentResponse paymentResponse = new CardPaymentRequest()
                    .orderDetails(orderDetails)
                    .cardDetails(cardDetails)
                    .billingAddress(address)
                    .shippingAddress(address)
                    .shopper(shopper)
                    .send(gatewayContext);

            switch (paymentResponse.getStatus())
            {
                case THREEDS_REQUESTED:
                    ThreeDsDetails threeDs = paymentResponse.getThreeDsDetails();
                    System.out.println("3ds required - issuer URL:" + threeDs.getIssuerURL() + ", paRes: " + threeDs.getPaRequest());
                    break;
                case PAYMENT_RESULT:
                    Payment payment = paymentResponse.getPayment();
                    System.out.println("payment - lastEvent: " + payment.getLastEvent());
                    break;
                default:
                    throw new IllegalStateException("Unhandled response - result=" + paymentResponse.getStatus());
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
        catch (WpgMalformedException e)
        {
            e.printStackTrace();
        }
        catch (WpgErrorResponseException e)
        {
            e.printStackTrace();
        }
    }

}
