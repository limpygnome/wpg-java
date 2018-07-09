package com.worldpay.sdk.wpg.integration.apm;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.apm.PayPalLanguage;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.apm.PayPalPaymentRequest;
import org.junit.Test;

import java.io.IOException;

public class PayPalPaymentRequestTest extends BaseIntegrationTest
{

    @Test
    public void orderUrl() throws WpgException, IOException
    {
        // Given
        OrderDetails orderDetails = new OrderDetails("description", new Amount("GBP", 2L, 1234L));
        Shopper shopper = new Shopper("test@worldpay.com");
        String resultURL = "https://result.worldpay.com";

        // When
        RedirectUrl redirectUrl = new PayPalPaymentRequest(orderDetails, shopper, resultURL)
                .send(GATEWAY_CONTEXT);

        // Then
        assertStatusCode(redirectUrl.getUrl(), 200);
    }

    @Test
    public void languageCode() throws WpgException, IOException
    {
        // Given
        OrderDetails orderDetails = new OrderDetails("description", new Amount("EUR", 2L, 1234L));
        Shopper shopper = new Shopper("test@worldpay.com");
        String resultURL = "https://result.worldpay.com";

        // When
        RedirectUrl redirectUrl = new PayPalPaymentRequest(orderDetails, shopper, resultURL)
                .language(PayPalLanguage.CHINESE)
                .send(GATEWAY_CONTEXT);

        // Then
        assertStatusCode(redirectUrl.getUrl(), 200);
    }

    @Test
    public void resultUrl() throws WpgException, IOException
    {
        // Given
        OrderDetails orderDetails = new OrderDetails("description", new Amount("EUR", 2L, 1234L));
        Shopper shopper = new Shopper("test@worldpay.com");

        // When
        RedirectUrl redirectUrl = new PayPalPaymentRequest()
                .orderDetails(orderDetails)
                .shopper(shopper)
                .resultURL("https://result.worldpay.com")
                .send(GATEWAY_CONTEXT);

        // Then
        assertStatusCode(redirectUrl.getUrl(), 200);
    }

    @Test
    public void resultUrls() throws WpgException, IOException
    {
        // Given
        OrderDetails orderDetails = new OrderDetails("description", new Amount("EUR", 2L, 1234L));
        Shopper shopper = new Shopper("test@worldpay.com");

        // When
        RedirectUrl redirectUrl = new PayPalPaymentRequest()
                .orderDetails(orderDetails)
                .shopper(shopper)
                .successURL("https://success.worldpay.com")
                .failureURL("https://failure.worldpay.com")
                .cancelURL("https://cancel.worldpay.com")
                .send(GATEWAY_CONTEXT);

        // Then
        assertStatusCode(redirectUrl.getUrl(), 200);
    }

    @Test
    public void billingAndShippingAddress() throws WpgException, IOException
    {
        // Given
        OrderDetails orderDetails = new OrderDetails("description", new Amount("EUR", 2L, 1234L));
        Shopper shopper = new Shopper("test@worldpay.com");
        String resultURL = "https://result.worldpay.com";

        // When
        RedirectUrl redirectUrl = new PayPalPaymentRequest()
                .orderDetails(orderDetails)
                .shopper(shopper)
                .resultURL(resultURL)
                .billingAddress(new Address("123 billing address", "billing", "1234", "GB"))
                .shippingAddress(new Address("123 shipping address", "shipping", "1234", "GB"))
                .send(GATEWAY_CONTEXT);

        // Then
        assertStatusCode(redirectUrl.getUrl(), 200);
    }

    @Test
    public void tokenise() throws WpgException, IOException
    {
        // Given
        OrderDetails orderDetails = new OrderDetails("description", new Amount("EUR", 2L, 1234L));
        Shopper shopper = new Shopper("test@worldpay.com", "shopperId");
        String resultURL = "https://result.worldpay.com";

        // When
        RedirectUrl redirectUrl = new PayPalPaymentRequest()
                .orderDetails(orderDetails)
                .shopper(shopper)
                .resultURL(resultURL)
                .tokeniseForReoccurringPayments(new CreateTokenDetails("event_reference", "reason"))
                .send(GATEWAY_CONTEXT);

        // Then
        assertStatusCode(redirectUrl.getUrl(), 200);
    }

}
