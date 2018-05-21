package com.worldpay.sdk.wpg.request.card;

import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.internal.xml.XmlBuildParams;
import com.worldpay.sdk.wpg.internal.xml.adapter.PaymentResponseXmlAdapter;
import com.worldpay.sdk.wpg.internal.xml.serializer.AddressSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.CardDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.OrderDetailsSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.SessionSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.ShopperSerializer;
import com.worldpay.sdk.wpg.internal.xml.serializer.payment.tokenisation.CreateTokenDetailsSerializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.powermock.core.classloader.annotations.*;
import org.powermock.modules.junit4.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.powermock.api.mockito.PowerMockito.*;


@PrepareForTest({
        OrderDetailsSerializer.class,
        CardDetailsSerializer.class,
        SessionSerializer.class,
        ShopperSerializer.class,
        AddressSerializer.class,
        CreateTokenDetailsSerializer.class,
        OrderDetailsSerializer.class,
        PaymentResponseXmlAdapter.class
})
@RunWith(PowerMockRunner.class)
public class CardPaymentRequestTest
{
    @Mock
    private OrderDetails orderDetails;
    @Mock
    private CardDetails cardDetails;
    @Mock
    private Shopper shopper;
    @Mock
    private Address billingAddress;
    @Mock
    private Address shippingAddress;
    @Mock
    private CreateTokenDetails createTokenDetails;
    @Mock
    private XmlBuildParams params;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        mockStatic(OrderDetailsSerializer.class);
        mockStatic(CardDetailsSerializer.class);
        mockStatic(SessionSerializer.class);
        mockStatic(ShopperSerializer.class);
        mockStatic(AddressSerializer.class);
        mockStatic(CreateTokenDetailsSerializer.class);
        mockStatic(OrderDetailsSerializer.class);
    }

    @Test
    public void constructor_3()
    {
        CardPaymentRequest request = new CardPaymentRequest(orderDetails, cardDetails, shopper);
        assertEquals(orderDetails, request.getOrderDetails());
        assertEquals(cardDetails, request.getCardDetails());
        assertEquals(shopper, request.getShopper());
    }

    @Test
    public void constructor_5()
    {
        CardPaymentRequest request = new CardPaymentRequest(orderDetails, cardDetails, shopper, billingAddress, shippingAddress);
        assertEquals(orderDetails, request.getOrderDetails());
        assertEquals(cardDetails, request.getCardDetails());
        assertEquals(shopper, request.getShopper());
        assertEquals(billingAddress, request.getBillingAddress());
        assertEquals(shippingAddress, request.getShippingAddress());
    }

    @Test
    public void constructor_6()
    {
        CardPaymentRequest request = new CardPaymentRequest(orderDetails, cardDetails, shopper, billingAddress, shippingAddress, createTokenDetails);
        assertEquals(orderDetails, request.getOrderDetails());
        assertEquals(cardDetails, request.getCardDetails());
        assertEquals(shopper, request.getShopper());
        assertEquals(billingAddress, request.getBillingAddress());
        assertEquals(shippingAddress, request.getShippingAddress());
        assertEquals(createTokenDetails, request.getCreateTokenDetails());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_shopperTokenMissingShopper()
    {
        given(createTokenDetails.getScope()).willReturn(TokenScope.SHOPPER);

        new CardPaymentRequest()
                .tokeniseForReoccurringPayments(createTokenDetails)
                .validate(params);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validate_shopperTokenMissingShopperId()
    {
        given(createTokenDetails.getScope()).willReturn(TokenScope.SHOPPER);

        new CardPaymentRequest()
                .tokeniseForReoccurringPayments(createTokenDetails)
                .shopper(shopper)
                .validate(params);
    }

    @Test
    public void validate_shopperTokenValid()
    {
        given(createTokenDetails.getScope()).willReturn(TokenScope.SHOPPER);
        given(shopper.getShopperId()).willReturn("shopper123");

        new CardPaymentRequest()
                .orderDetails(orderDetails)
                .cardDetails(cardDetails)
                .tokeniseForReoccurringPayments(createTokenDetails)
                .shopper(shopper)
                .validate(params);
    }

    @Test
    public void build_invokesAsExpected()
    {
        // when
        new CardPaymentRequest()
                .orderDetails(orderDetails)
                .cardDetails(cardDetails)
                .shopper(shopper)
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
                .tokeniseForReoccurringPayments(createTokenDetails)
                .orderDetails(orderDetails)
                .build(params);

        // then
        verifyStatic(OrderDetailsSerializer.class);
        OrderDetailsSerializer.decorateAndStartOrder(params, orderDetails);

        verifyStatic(CardDetailsSerializer.class);
        CardDetailsSerializer.decorateOrder(params, cardDetails);

        verifyStatic(SessionSerializer.class);
        SessionSerializer.decorateOrderPaymentDetails(params, shopper);

        verifyStatic(ShopperSerializer.class);
        ShopperSerializer.decorateOrder(params, shopper);

        verifyStatic(AddressSerializer.class);
        AddressSerializer.decorateOrder(params, billingAddress, shippingAddress);

        verifyStatic(CreateTokenDetailsSerializer.class);
        CreateTokenDetailsSerializer.decorateOrder(params, createTokenDetails);

        verifyStatic(OrderDetailsSerializer.class);
        OrderDetailsSerializer.decorateFinishOrder(params);
    }

}