package com.worldpay.sdk.wpg.integration.card;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.Currency;
import com.worldpay.sdk.wpg.domain.payment.DebitCreditIndicator;
import com.worldpay.sdk.wpg.domain.payment.LastEvent;
import com.worldpay.sdk.wpg.domain.payment.Payment;
import com.worldpay.sdk.wpg.domain.payment.PaymentMethod;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.payment.PaymentStatus;
import com.worldpay.sdk.wpg.domain.payment.result.Balance;
import com.worldpay.sdk.wpg.domain.payment.result.CvcResult;
import com.worldpay.sdk.wpg.domain.payment.result.RiskScoreResult;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.Token;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenCardDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.integration.BaseIntegrationTest;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CardPaymentRequestTest extends BaseIntegrationTest
{

    @Test
    public void send_noAuth()
    {
        try
        {
            // given
            GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, new UserPassAuth("fake", "fake", "fake", "1234"));

            OrderDetails orderDetails = new OrderDetails("test order", new Amount(Currency.GBP, 2L, 1234L));
            Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"));

            CardDetails cardDetails = new CardDetails("4444333322221111", 1L, 2020L, "Cardholder name", "123");

            // when
            new CardPaymentRequest()
                    .orderDetails(orderDetails)
                    .cardDetails(cardDetails)
                    .shopper(shopper)
                    .send(gatewayContext);
        }
        catch (WpgException e)
        {
            // then
            final String EXPECTED = "Failed to make request - message from server: This request requires HTTP authentication.";
            assertEquals(EXPECTED, e.getMessage());
            assertThat(e, instanceOf(WpgRequestException.class));
        }
    }

    @Test
    public void send() throws WpgException
    {
        OrderDetails orderDetails = new OrderDetails("test order", new Amount(Currency.GBP, 2L, 1234L));
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"));

        CardDetails cardDetails = new CardDetails("4444333322221111", 1L, 2020L, "Cardholder name", "123");

        PaymentResponse response = new CardPaymentRequest()
                .orderDetails(orderDetails)
                .cardDetails(cardDetails)
                .shopper(shopper)
                .send(GATEWAY_CONTEXT);

        // check threeds missing
        assertEquals("ThreeDS should not be used", PaymentStatus.PAYMENT_RESULT, response.getStatus());
        assertNull("ThreeDS should not be present", response.getThreeDsDetails());

        // check payment
        Payment payment = response.getPayment();
        assertNotNull("Payment result should be present", payment);
        Amount expectedAmount = new Amount(Currency.GBP, 2, 1234L, DebitCreditIndicator.CREDIT);
        assertEquals("Amount should be similar, but with credit indicator", expectedAmount, payment.getAmount());
        assertEquals("Last event should be authorised", LastEvent.AUTHORISED, payment.getLastEvent());

        // check payment method
        PaymentMethod paymentMethod = payment.getPaymentMethod();
        assertEquals("Payment method should be VISA", PaymentMethod.VISA, paymentMethod);
        assertEquals("Payment method mask should be VISA_CREDIT", "VISA_CREDIT-SSL", payment.getPaymentMethodMask());

        // check balance
        Balance balance = payment.getBalance();
        assertEquals("Balance should be same amount", expectedAmount, balance.getAmount());
        assertEquals("Balance should be authorised", "IN_PROCESS_AUTHORISED", balance.getAccountType());

        // check results expected
        CvcResult cvcResult = payment.getCvcResult();
        assertNotNull("CVC result should be present", cvcResult);
        assertEquals("Description should state CVC was not provided", "NOT SENT TO ACQUIRER", cvcResult.getDescription());

        // check risk
        RiskScoreResult riskScoreResult = payment.getRiskScoreResult();
        assertNotNull("Risk score expected ", riskScoreResult);
        assertThat(riskScoreResult.getValue(), is(1));

        // check results not expected
        assertNull(payment.getIso8583Result());
        assertNull(payment.getThreeDSecureResult());
        assertNull(payment.getAvsResult());
        assertNull(payment.getAvvResult());
        assertNull(payment.getToken());
    }

    @Test
    public void send_withoutCvc() throws WpgException
    {
        OrderDetails orderDetails = new OrderDetails("test order", new Amount(Currency.GBP, 2L, 1234L));
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"));

        CardDetails cardDetails = new CardDetails("4444333322221111", 1L, 2020L, "Cardholder name");

        PaymentResponse response = new CardPaymentRequest()
                .orderDetails(orderDetails)
                .cardDetails(cardDetails)
                .shopper(shopper)
                .send(GATEWAY_CONTEXT);

        // check cvc status
        Payment payment = response.getPayment();
        assertNotNull("Payment result should be present", payment);

        CvcResult cvcResult = payment.getCvcResult();
        assertNotNull("CVC result should be present", cvcResult);
        assertEquals("Description should state CVC was not provided", "NOT SUPPLIED BY SHOPPER", cvcResult.getDescription());
    }

    @Test
    public void send_withAddresses() throws WpgException
    {
        OrderDetails orderDetails = new OrderDetails("test order", new Amount(Currency.GBP, 2L, 1234L));
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"));

        CardDetails cardDetails = new CardDetails("4444333322221111", 1L, 2020L, "Cardholder name");

        Address billingAddress = new Address("123 test address", "blah", "1234", "GB");
        Address shippingAddress = new Address("987 test address", "blah", "4321", "GB");

        PaymentResponse response = new CardPaymentRequest()
                .orderDetails(orderDetails)
                .cardDetails(cardDetails)
                .shopper(shopper)
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
                .send(GATEWAY_CONTEXT);

        // check payment present
        Payment payment = response.getPayment();
        assertNotNull("Payment result should be present", payment);
    }

    @Test
    public void send_createShopperToken_shopperFullDetails() throws WpgException
    {
        OrderDetails orderDetails = new OrderDetails("test order", new Amount(Currency.GBP, 2L, 1234L));
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"), "shopper123");

        CardDetails cardDetails = new CardDetails("4444333322221111", 1L, 2020L, "Cardholder name");
        cardDetails.setCardHolderAddress(new Address("test", "test", "123 test street", "testridge", null, "test123", "testridge", null, "GB", "01234567890"));

        final TokenScope tokenScope = TokenScope.SHOPPER;
        final String eventReference = "EVENT123";
        final String eventReason = "event reason";
        final LocalDateTime expiry = LocalDateTime.now().plusDays(1);

        CreateTokenDetails createTokenDetails = new CreateTokenDetails(tokenScope, eventReference, eventReason, expiry);

        PaymentResponse response = new CardPaymentRequest()
                .orderDetails(orderDetails)
                .cardDetails(cardDetails)
                .shopper(shopper)
                .tokeniseForReoccurringPayments(createTokenDetails)
                .send(GATEWAY_CONTEXT);

        // check payment present
        Payment payment = response.getPayment();
        assertNotNull("Payment result should be present", payment);

        // check token present
        Token token = payment.getToken();
        assertNotNull("Token details should be present", token);
        assertEquals("Response should have shopper ID", "shopper123", token.getShopperId());
        assertEquals("Scope should be same", tokenScope, token.getScope());

        // check token details
        TokenDetails tokenDetails = token.getDetails();
        assertNotNull("Payment token ID should have been sent back", tokenDetails.getPaymentTokenId());
        assertEquals("Event ref should be same as passed in", eventReference, tokenDetails.getEventReference());
        assertEquals("Event reason should be same as passed in", eventReason, tokenDetails.getEventReason());

        // check token expiry is within an hour of what we specified
        LocalDateTime tokenExpiryResponse = tokenDetails.getTokenExpiry();
        Duration duration = Duration.between(tokenExpiryResponse, expiry);
        assertTrue("Token expiry should not be greater than 244hrs different to requested expiry - expiry wanted: " + expiry + ", response: " + tokenExpiryResponse, duration.getSeconds() < 24*60*60);

        // check token instrument
        assertNotNull("Payment instrument (payment details used to create token) should be present", token.getInstrument());
        assertThat(token.getInstrument(), instanceOf(TokenCardDetails.class));

        TokenCardDetails tokenCardDetails = (TokenCardDetails) token.getInstrument();
        assertEquals("Card brand should be VISA", "VISA", tokenCardDetails.getCardBrand());
        assertEquals("Sub brand should be VISA CREDIT", "VISA_CREDIT", tokenCardDetails.getCardSubBrand());
        assertEquals("Issuer country code should be unknown", "N/A", tokenCardDetails.getIssuerCountryCode());
        assertEquals("Obfuscated PAN not in expected format", "4444********1111", tokenCardDetails.getObfuscatedCardNumber());

        CardDetails responseDetails = tokenCardDetails.getCardDetails();
        assertNull("Card number should be missing", responseDetails.getCardNumber());
        assertNull("CVC should be null", responseDetails.getCvc());
        assertNull("Encrypted card number should be missing", responseDetails.getEncryptedCardNumber());
        assertEquals("Expiry month should be same", cardDetails.getExpiryMonth(), responseDetails.getExpiryMonth());
        assertEquals("Expiry year should be same", cardDetails.getExpiryYear(), responseDetails.getExpiryYear());
        assertEquals("Card holder name should be same", cardDetails.getCardHolderName(), responseDetails.getCardHolderName());

        Address sentAddress = cardDetails.getCardHolderAddress();
        Address expectedAddress = new Address(
                null, null,
                sentAddress.getAddress1(), sentAddress.getAddress2(), sentAddress.getAddress3(),
                sentAddress.getPostalCode(), sentAddress.getCity(), sentAddress.getState(), sentAddress.getCountryCode(),
                null
        );
        assertEquals("Address should be same, without name and telephone", expectedAddress, responseDetails.getCardHolderAddress());
    }

    @Test
    public void send_createMerchanrToken_fullDetails() throws WpgException
    {
        OrderDetails orderDetails = new OrderDetails("test order 2", new Amount(Currency.GBP, 2L, 1234L));

        // No shopper ID present as not needed; other details only needed for 3ds
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"));

        CardDetails cardDetails = new CardDetails("4929930199830280", 1L, 2020L, "Cardholder name");
        cardDetails.setCardHolderAddress(new Address("test", "test", "123 test street", "testridge", null, "test123", "testridge", null, "GB", "01234567890"));

        final TokenScope tokenScope = TokenScope.MERCHANT;
        final String eventReference = "EVENT123";
        final String eventReason = "event reason";
        final LocalDateTime expiry = LocalDateTime.now().plusDays(1);

        CreateTokenDetails createTokenDetails = new CreateTokenDetails(tokenScope, eventReference, eventReason, expiry);

        PaymentResponse response = new CardPaymentRequest()
                .orderDetails(orderDetails)
                .cardDetails(cardDetails)
                .shopper(shopper)
                .tokeniseForReoccurringPayments(createTokenDetails)
                .send(GATEWAY_CONTEXT);

        // check payment present
        Payment payment = response.getPayment();
        assertNotNull("Payment result should be present", payment);

        // check token present
        Token token = payment.getToken();
        assertNotNull("Token details should be present", token);
        assertNull("Shopper ID not expected as merchant token", token.getShopperId());
        assertEquals("Scope should be same", tokenScope, token.getScope());

        // check token details
        TokenDetails tokenDetails = token.getDetails();
        assertNotNull("Payment token ID should have been sent back", tokenDetails.getPaymentTokenId());
        assertEquals("Event ref should be same as passed in", eventReference, tokenDetails.getEventReference());
        assertEquals("Event reason should be same as passed in", eventReason, tokenDetails.getEventReason());

        // check token expiry is within 12 hrs of what we specified
        LocalDateTime tokenExpiryResponse = tokenDetails.getTokenExpiry();
        Duration duration = Duration.between(tokenExpiryResponse, expiry);
        assertTrue("Token expiry should not be greater than 244hrs different to requested expiry - expiry wanted: " + expiry + ", response: " + tokenExpiryResponse, duration.getSeconds() < 24*60*60);

        // check token instrument
        assertNotNull("Payment instrument (payment details used to create token) should be present", token.getInstrument());
        assertThat(token.getInstrument(), instanceOf(TokenCardDetails.class));

        TokenCardDetails tokenCardDetails = (TokenCardDetails) token.getInstrument();
        assertEquals("Card brand should be VISA", "VISA", tokenCardDetails.getCardBrand());
        assertEquals("Sub brand should be VISA CREDIT", "VISA_CREDIT", tokenCardDetails.getCardSubBrand());
        assertEquals("Issuer country code should be GB", "GB", tokenCardDetails.getIssuerCountryCode());
        assertEquals("Obfuscated PAN not in expected format", "4929********0280", tokenCardDetails.getObfuscatedCardNumber());

        CardDetails responseDetails = tokenCardDetails.getCardDetails();
        assertNull("Card number should be missing", responseDetails.getCardNumber());
        assertNull("CVC should be null", responseDetails.getCvc());
        assertNull("Encrypted card number should be missing", responseDetails.getEncryptedCardNumber());
        assertEquals("Expiry month should be same", cardDetails.getExpiryMonth(), responseDetails.getExpiryMonth());
        assertEquals("Expiry year should be same", cardDetails.getExpiryYear(), responseDetails.getExpiryYear());
        assertEquals("Card holder name should be same", cardDetails.getCardHolderName(), responseDetails.getCardHolderName());

        Address sentAddress = cardDetails.getCardHolderAddress();
        Address expectedAddress = new Address(
                null, null,
                sentAddress.getAddress1(), sentAddress.getAddress2(), sentAddress.getAddress3(),
                sentAddress.getPostalCode(), sentAddress.getCity(), sentAddress.getState(), sentAddress.getCountryCode(),
                null
        );
        assertEquals("Address should be same, without name and telephone", expectedAddress, responseDetails.getCardHolderAddress());
    }

    @Test
    public void send_tokeniseWithAddresses() throws WpgException
    {
        final String shopperId = "shopper1234";
        OrderDetails orderDetails = new OrderDetails("test order", new Amount(Currency.GBP, 2L, 1234L));
        Shopper shopper = new Shopper("test@test.com", "123.123.123.123", new ShopperBrowser("text/html", "Mozilla/5.0 Chrome/62.0.3202.94 Safari/537.36"), shopperId);

        CardDetails cardDetails = new CardDetails("4444333322221111", 1L, 2020L, "Cardholder name");
        Address billingAddress = new Address("123 test address", "blah", "1234", "GB");
        Address shippingAddress = new Address("987 test address", "blah", "4321", "GB");

        CreateTokenDetails tokenDetails = new CreateTokenDetails("TEST123", "test");

        PaymentResponse response = new CardPaymentRequest()
                .orderDetails(orderDetails)
                .cardDetails(cardDetails)
                .shopper(shopper)
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
                .tokeniseForReoccurringPayments(tokenDetails)
                .send(GATEWAY_CONTEXT);

        // check payment present
        Payment payment = response.getPayment();
        assertNotNull("Payment result should be present", payment);

        // check token present
        Token token = payment.getToken();
        assertNotNull("Token details should be present", token);
        assertEquals("Should return same shopper ID back", shopperId, token.getShopperId());
    }

}
