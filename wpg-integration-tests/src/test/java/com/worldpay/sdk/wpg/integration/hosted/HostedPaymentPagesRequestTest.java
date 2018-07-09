package com.worldpay.sdk.wpg.integration.hosted;

import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodType;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.hosted.HostedPaymentPagesRequest;
import org.junit.Test;

import java.io.IOException;
import java.util.Locale;

public class HostedPaymentPagesRequestTest extends BaseIntegrationTest
{
    private RedirectUrl redirectUrl;

    @Test
    public void basicOrder() throws IOException, WpgException
    {
        // Given
        OrderDetails orderDetails = new OrderDetails("test order", new Amount("GBP", 2, 1234L));

        // When
        HostedPaymentPagesRequest request = new HostedPaymentPagesRequest(orderDetails);
        redirectUrl = request.send(GATEWAY_CONTEXT);

        // Then
        assertStatusCode(redirectUrl.getUrl(), 200);
    }

    @Test
    public void orderUrl_vanilla() throws IOException, WpgException
    {
        givenOrder();
        assertStatusCode(redirectUrl.getUrl(), 200);
    }

    @Test
    public void orderUrl_malformed() throws IOException, WpgException
    {
        givenOrder();
        assertStatusCode(redirectUrl.getUrl() + "&blah=ttest", 400);
    }

    @Test
    public void orderUrl_withResultUrls() throws IOException, WpgException
    {
        givenOrder();

        String url = redirectUrl.paymentPages()
                .successUrl("https://success.worldpay.com")
                .cancelUrl("https://cancel.worldpay.com")
                .errorUrl("https://error.worldpay.com")
                .failureUrl("https://failure.worldpay.com")
                .pendingUrl("https://pending.worldpay.com")
                .build();

        assertStatusCode(url, 200);
    }

    @Test
    public void orderUrl_withPreferredPaymentMethod() throws IOException, WpgException
    {
        givenOrder();

        String url = redirectUrl.paymentPages()
                .preferredPaymentMethod(PaymentMethodType.VISA)
                .build();

        assertStatusCode(url, 200);
    }

    @Test
    public void orderUrl_withLocale() throws IOException, WpgException
    {
        givenOrder();

        String url = redirectUrl.paymentPages()
                .languageAndCountry(Locale.CANADA_FRENCH)
                .build();

        assertStatusCode(url, 200);
    }

    @Test
    public void orderUrl_withCountryLanguage() throws IOException, WpgException
    {
        givenOrder();

        String url = redirectUrl.paymentPages()
                .country("gb")
                .language("en")
                .build();

        assertStatusCode(url, 200);
    }

    @Test
    public void orderUrl_withCountry2() throws IOException, WpgException
    {
        givenOrder();

        String url = redirectUrl.paymentPages()
                .country("GB")
                .language("en")
                .build();

        assertStatusCode(url, 200);
    }

    @Test
    public void orderUrl_withEverything() throws IOException, WpgException
    {
        givenOrder();

        String url = redirectUrl.paymentPages()
                .successUrl("https://success.worldpay.com")
                .cancelUrl("https://cancel.worldpay.com")
                .errorUrl("https://error.worldpay.com")
                .failureUrl("https://failure.worldpay.com")
                .pendingUrl("https://pending.worldpay.com")
                .preferredPaymentMethod(PaymentMethodType.VISA)
                .languageAndCountry(Locale.CANADA_FRENCH)
                .build();

        assertStatusCode(url, 200);
    }

    private void givenOrder() throws WpgException
    {
        OrderDetails orderDetails = new OrderDetails("test order", new Amount("GBP", 2, 1234L));
        HostedPaymentPagesRequest request = new HostedPaymentPagesRequest(orderDetails, new Shopper("test@worldpay.com"));
        redirectUrl = request.send(GATEWAY_CONTEXT);
    }

}
