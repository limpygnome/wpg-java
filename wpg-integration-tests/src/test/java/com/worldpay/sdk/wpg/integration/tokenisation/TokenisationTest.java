package com.worldpay.sdk.wpg.integration.tokenisation;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.card.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.shopper.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethodType;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.Token;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenisationPaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgErrorResponseException;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.request.tokenisation.DeleteTokenRequest;
import com.worldpay.sdk.wpg.request.tokenisation.FetchTokenRequest;
import com.worldpay.sdk.wpg.request.tokenisation.FetchTokensByShopperRequest;
import com.worldpay.sdk.wpg.request.tokenisation.TokenPaymentRequest;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class TokenisationTest extends BaseIntegrationTest
{
    /**
     * Maximum times to poll a token to check whether it has been deleted.
     */
    private static final int MAX_ATTEMPTS_POLL_TOKEN_DELETION = 10;

    /**
     * Delay between polls, for when checking a token has been deletedd, in milliseconds.
     */
    private static final long POLL_TOKEN_DELETION_DELAY = 2000L;

    @Test
    public void captureCvc() throws Exception
    {
        // Given
        ShopperBrowser browser = new ShopperBrowser("accepts", "user agent");
        Shopper shopper = new Shopper("email@email.com", "1.2.3.4",  browser, "shopper123");
        Token token = setupOrder(new CreateTokenDetails(TokenScope.SHOPPER, "event_ref", "reason"), shopper);

        OrderDetails orderDetails = new OrderDetails("test", new Amount(Currency.EUR, 2L, 1234L));
        Address address = new Address("address 1", "city", "post code", "GB");

        // When
        TokenisationPaymentResponse response = new TokenPaymentRequest(
                token.getPaymentTokenId(), TokenScope.SHOPPER, orderDetails, shopper, address, address, true)
                .send(GATEWAY_CONTEXT);

        // Then
        assertNotNull(response);
        assertNull(response.getPaymentResponse());
        assertNotNull(response.getCaptureCvcUrl());
        assertNotNull(response.getCaptureCvcUrl().getUrl());
    }

    @Test
    public void captureCvc_simple() throws Exception
    {
        // Given
        ShopperBrowser browser = new ShopperBrowser("accepts", "user agent");
        Shopper shopper = new Shopper(null, null, browser, "shopper123");
        Token token = setupOrder(new CreateTokenDetails(TokenScope.SHOPPER, "event_ref", "reason"), shopper);

        OrderDetails orderDetails = new OrderDetails("test", new Amount(Currency.EUR, 2L, 1234L));

        // When
        TokenisationPaymentResponse response = new TokenPaymentRequest(
                token.getPaymentTokenId(), TokenScope.SHOPPER, orderDetails, shopper, true)
                .send(GATEWAY_CONTEXT);

        // Then
        assertNotNull(response);
        assertNull(response.getPaymentResponse());
        assertNotNull(response.getCaptureCvcUrl());
        assertNotNull(response.getCaptureCvcUrl().getUrl());
    }

    @Test
    public void makePayment() throws Exception
    {
        // Given
        ShopperBrowser browser = new ShopperBrowser("accepts", "user agent");
        Shopper shopper = new Shopper(null, null, browser, "shopper123");
        Token token = setupOrder(new CreateTokenDetails(TokenScope.SHOPPER, "event_ref", "reason"), shopper);

        OrderDetails orderDetails = new OrderDetails("test", new Amount(Currency.EUR, 2L, 1234L));
        Address address = new Address("address 1", "city", "post code", "GB");

        // When
        TokenisationPaymentResponse response = new TokenPaymentRequest(
                token.getPaymentTokenId(), TokenScope.SHOPPER, orderDetails, shopper, address, address, false)
                .send(GATEWAY_CONTEXT);

        // Then
        assertNotNull(response);
        assertNotNull(response.getPaymentResponse());
        assertThat(response.getPaymentResponse().getStatus(), is(PaymentStatus.PAYMENT_RESULT));

        Payment payment = response.getPaymentResponse().getPayment();
        assertNotNull(payment);
        assertThat(payment.getPaymentMethodType(), is(PaymentMethodType.VISA));
        assertThat(payment.getLastEvent(), is(LastEvent.AUTHORISED));
    }

    @Test
    public void makePayment_simple() throws Exception
    {
        // Given
        ShopperBrowser browser = new ShopperBrowser("accepts", "user agent");
        Shopper shopper = new Shopper(null, null, browser, "shopper123");
        Token token = setupOrder(new CreateTokenDetails(TokenScope.SHOPPER, "event_ref", "reason"), shopper);

        OrderDetails orderDetails = new OrderDetails("test", new Amount(Currency.EUR, 2L, 1234L));

        // When
        TokenisationPaymentResponse response = new TokenPaymentRequest(
                token.getPaymentTokenId(), orderDetails, shopper)
                .send(GATEWAY_CONTEXT);

        // Then
        assertNotNull(response);
        assertNotNull(response.getPaymentResponse());
        assertThat(response.getPaymentResponse().getStatus(), is(PaymentStatus.PAYMENT_RESULT));

        Payment payment = response.getPaymentResponse().getPayment();
        assertNotNull(payment);
        assertThat(payment.getPaymentMethodType(), is(PaymentMethodType.VISA));
        assertThat(payment.getLastEvent(), is(LastEvent.AUTHORISED));
    }

    @Test
    public void endToEnd_createShopperToken() throws Exception
    {
        ShopperBrowser browser = new ShopperBrowser("accepts", "user agent");
        Shopper shopper = new Shopper("email@email.com", "1.2.3.4", browser, "shopper123");

        // Create token
        Token token = setupOrder(new CreateTokenDetails(TokenScope.SHOPPER, "event_ref", "reason"), shopper);

        // Fetch token
        Token fetchedToken = new FetchTokenRequest(token.getPaymentTokenId(), shopper.getShopperId())
                .send(GATEWAY_CONTEXT);

        assertNotNull(fetchedToken);
        assertEquals(token.getPaymentTokenId(), fetchedToken.getPaymentTokenId());
        assertEquals(token.getScope(), fetchedToken.getScope());
        assertEquals(token.getInstrument(), fetchedToken.getInstrument());
        assertEquals(token.getShopperId(), fetchedToken.getShopperId());
        assertEquals(token.getDetails().getPaymentTokenId(), fetchedToken.getDetails().getPaymentTokenId());
        assertEquals(token.getDetails().getEventReason(), fetchedToken.getDetails().getEventReason());
        assertEquals(token.getDetails().getEventReference(), fetchedToken.getDetails().getEventReference());
        assertEquals(token.getDetails().getTokenExpiry(), fetchedToken.getDetails().getTokenExpiry());

        // -- Token event wont be set when fetched, as token not being created/used/matched
        assertNotNull(token.getDetails().getTokenEvent());
        assertNull(fetchedToken.getDetails().getTokenEvent());

        // Fetch by shopper ID
        List<Token> shopperTokens = new FetchTokensByShopperRequest(shopper.getShopperId())
                .send(GATEWAY_CONTEXT);

        assertNotNull(fetchedToken);
        assertThat(shopperTokens.size(), is(1));
        assertEquals(fetchedToken, shopperTokens.get(0));

        // Delete token
        new DeleteTokenRequest(token.getPaymentTokenId(), shopper.getShopperId())
                .send(GATEWAY_CONTEXT);

        // Check token is deleted
        pollForDeletion(token);
    }

    @Test
    public void endToEnd_createMerchantToken() throws Exception
    {
        ShopperBrowser browser = new ShopperBrowser("accepts", "user agent");
        Shopper shopper = new Shopper(browser);

        // Create token
        Token token = setupOrder(new CreateTokenDetails(TokenScope.MERCHANT, "event_ref", "reason"), shopper);

        // Fetch token
        Token fetchedToken = new FetchTokenRequest(token.getPaymentTokenId())
                .send(GATEWAY_CONTEXT);

        assertNotNull(fetchedToken);
        assertEquals(token.getPaymentTokenId(), fetchedToken.getPaymentTokenId());
        assertEquals(token.getScope(), fetchedToken.getScope());
        assertEquals(token.getInstrument(), fetchedToken.getInstrument());
        assertEquals(token.getShopperId(), fetchedToken.getShopperId());
        assertEquals(token.getDetails().getPaymentTokenId(), fetchedToken.getDetails().getPaymentTokenId());
        assertEquals(token.getDetails().getEventReason(), fetchedToken.getDetails().getEventReason());
        assertEquals(token.getDetails().getEventReference(), fetchedToken.getDetails().getEventReference());
        assertEquals(token.getDetails().getTokenExpiry(), fetchedToken.getDetails().getTokenExpiry());

        // -- Token event wont be set when fetched, as token not being created/used/matched
        assertNotNull(token.getDetails().getTokenEvent());
        assertNull(fetchedToken.getDetails().getTokenEvent());

        // Delete token
        new DeleteTokenRequest(token.getPaymentTokenId())
                .send(GATEWAY_CONTEXT);

        // Check token is deleted
        pollForDeletion(token);
    }

    private Token setupOrder(CreateTokenDetails createTokenDetails, Shopper shopper) throws Exception
    {
        OrderDetails orderDetails = new OrderDetails("threeds test order", new Amount(Currency.GBP, 2L, 1000L));
        CardDetails cardDetails = new CardDetails("4444333322221129", 1L, 2030L, "test");
        CardPaymentRequest request = new CardPaymentRequest(orderDetails, cardDetails, shopper);
        request.tokeniseForReoccurringPayments(createTokenDetails);

        PaymentResponse response = request.send(GATEWAY_CONTEXT);
        assertEquals(PaymentStatus.PAYMENT_RESULT, response.getStatus());

        Token token = response.getPayment().getToken();
        assertNotNull("Expected token to be in result", token);

        // Wait for order to be replicated
        pollUntil(orderDetails, LastEvent.AUTHORISED);

        return token;
    }

    // Required due to replication delay
    private void pollForDeletion(Token token) throws WpgException
    {
        int attempts = 0;
        boolean success = false;

        do
        {
            // Attempt to poll...
            try
            {
                new FetchTokenRequest(token.getPaymentTokenId())
                        .send(GATEWAY_CONTEXT);
            }
            catch (WpgErrorResponseException e)
            {
                assertThat(e.getGatewayErrorMessage(), is("Token does not exist"));
                assertThat(e.getGatewayErrorCode(), is(5L));
                success = true;
            }

            // Sleep if unsuccessful
            if (!success)
            {
                try
                {
                    Thread.sleep(POLL_TOKEN_DELETION_DELAY);
                }
                catch (InterruptedException e) { }
            }
        }
        while (!success && attempts++ < MAX_ATTEMPTS_POLL_TOKEN_DELETION);

        if (!success)
        {
            fail("Exception should have been thrown, stating token no longer exists");
        }
    }

}
