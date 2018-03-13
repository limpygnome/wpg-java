package com.worldpay.sdk.wpg.examples.cse;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.CountryCode;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.request.cse.ClientsideEncryptedCardRequest;
import com.worldpay.sdk.wpg.response.Response;
import com.worldpay.sdk.wpg.response.approval.CurrencyConversionResponse;
import com.worldpay.sdk.wpg.response.payment.PaymentResponse;
import com.worldpay.sdk.wpg.response.threeds.ThreeDsRequestedResponse;

public class CseDemoApp
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
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"));

        // encrypted card details payload
        String encryptedData = "eyJhbGciOiJSU0EtT0FFUCIsImNvbS53b3JsZHBheS5hcGlWZXJzaW9uIjoiMS4wIiwiY29tLndvcmxk\n" +
                "cGF5LmNoYW5uZWwiOiJjdXN0b20iLCJjb20ud29ybGRwYXkubGliVmVyc2lvbiI6IjEuMC4wIiwiZW5j\n" +
                "IjoiQTI1NkNCQy1IUzUxMiIsImtpZCI6IjIifQ.WlLLbIdn-_5JXVBzz2tJOK1rofXz41MRAVBiIxwrj\n" +
                "DBZfatQKLq8iYSFBO6lAuLP38vEtazPAO-Z8L7gUNO9KqDMHeUdDNJ7HxOzLYyQdnOzLMZKetfhlrKAT\n" +
                "MMjQk4ZUq8HHdvhx_dWbxugcRfQbCDUPtxFMVuirMpGw6VYESu0ZTlFOKy8YPlf8spafVvgINXVUbPKm\n" +
                "1fxQpSvwrBi_vaMpLbn0GjyDnpv2YkOZgq0Mcd5O1K2ZmEZdx8ZYTUOWnhN7SFzv9BduEndUNhHCoLkh\n" +
                "JRtoVwqYYgrsEXL_j4PsvAZF0_PI_NIOviYRe9RSoYZDkRzKuCWFsxHtSStPQ.y-cOrxjqAJhsa1RYDG\n" +
                "hpag.-PEsJ6a5VwAatWbk7saa8Rty6iGvPiBfP8V9r26eIksir8cb-JHdPjldBGqI6EdGSTNv9K5Pa73\n" +
                "Jk9KRaWs3Co6sOvmSkqJqGOc3oIeaacvWp5m3SEJQZu2ZVx9bjTElG67cOLR5XTaP5AzOg13HptZ-T9J\n" +
                "GT-pe2V-i_m-wJy4.pVKcqWbUQ0IwBo9HF3sXQoLlOauRg3_uGVEBs-cfB34";

        try
        {
            // create order
            Response response = new ClientsideEncryptedCardRequest()
                    .orderDetails(orderDetails)
                    .encryptedData(encryptedData)
                    .cardHolderAddress(address)
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
                    System.out.println("3ds required - issuer URL:" + threeDs.getIssuerURL() + ", paRes: " + threeDs.getPaRequest());
                    break;
                case PAYMENT_STATUS:
                    PaymentResponse paymentResponse = (PaymentResponse) response;
                    break;
                default:
                    throw new IllegalStateException("Unhandled response - type=" + response.getResponseType());
            }
        }
        catch (WpgException e)
        {
            e.printStackTrace();
        }
    }

}
