package com.worldpay.sdk.wpg.examples.hpp;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodFilter;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgMalformedXmlException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.hosted.HostedPaymentPagesRequest;

import java.util.Locale;

import static com.worldpay.sdk.wpg.examples.AuthConstants.INSTALLATION_ID;
import static com.worldpay.sdk.wpg.examples.AuthConstants.MERCHANT_CODE;
import static com.worldpay.sdk.wpg.examples.AuthConstants.PASS;
import static com.worldpay.sdk.wpg.examples.AuthConstants.USER;

public class HppAdvancedDemoApp
{

    public static void main(String[] args)
    {
        // setup gateway details
        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth(USER, PASS, MERCHANT_CODE, INSTALLATION_ID));

        // build order details
        Amount amount = new Amount(Currency.GBP, 2L, 1000L);
        OrderDetails orderDetails = new OrderDetails("test order", amount);
        Address billingAddress = new Address("123 test address", "blah", "1234", "GB");
        Address shippingAddress = new Address("123 test address", "blah", "1234", "GB");
        Shopper shopper = new Shopper("test@test.com");

        // filter payment methods available
        PaymentMethodFilter filter = new PaymentMethodFilter();
        filter.include(PaymentMethod.VISA, PaymentMethod.PAYPAL);

        try
        {
            // create order
            RedirectUrl response = new HostedPaymentPagesRequest()
                    .orderDetails(orderDetails)
                    .billingAddress(billingAddress)
                    .shippingAddress(shippingAddress)
                    .shopper(shopper)
                    .filter(filter)
                    .send(gatewayContext);

            // decorates url with additional parameters
            String url = response
                    .paymentPages()
                    .cancelUrl("http://merchant.com/result/cancel")
                    .errorUrl("http://merchant.com/result/error")
                    .failureUrl("http://merchant.com/result/error")
                    .pendingUrl("http://merchant.com/result/pending")
                    .successUrl("http://merchant.com/result/success")
                    .languageAndCountry(Locale.CANADA_FRENCH)
                    .preferredPaymentMethod(PaymentMethod.VISA)
                    .build();

            System.out.println(url);
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
