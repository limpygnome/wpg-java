package com.worldpay.sdk.demoshop.service;

import com.worldpay.sdk.demoshop.domain.ApiCardDetails;
import com.worldpay.sdk.demoshop.domain.ApiOrderDetails;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.domain.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SdkService
{
    @Autowired
    private GatewayContext gatewayContext;

    public PaymentResponse pay(ApiOrderDetails apiOrderDetails, ApiCardDetails apiCardDetails) throws WpgException
    {
        // Translate API details to request
        CardDetails cardDetails = new CardDetails(
                apiCardDetails.getCardNumber(),
                apiCardDetails.getExpiryMonth(),
                apiCardDetails.getExpiryYear(),
                apiCardDetails.getName(),
                apiCardDetails.getCvc()
        );

        OrderDetails orderDetails = toOrderDetails(apiOrderDetails);
        Address address = toAddress(apiOrderDetails);
        Shopper shopper = toShopper(apiOrderDetails);

        // Make request
        CardPaymentRequest request = new CardPaymentRequest(orderDetails, cardDetails, shopper, address, address);
        PaymentResponse response = request.send(gatewayContext);
        return response;
    }

    private OrderDetails toOrderDetails(ApiOrderDetails apiOrderDetails)
    {
        Amount amount = new Amount(apiOrderDetails.getCurrency(), 2L, apiOrderDetails.getAmount());
        OrderDetails orderDetails = new OrderDetails(
                apiOrderDetails.getOrderCode(),
                apiOrderDetails.getDescription(),
                amount
        );
        return orderDetails;
    }

    private Shopper toShopper(ApiOrderDetails apiOrderDetails)
    {
        Shopper shopper = new Shopper(new ShopperBrowser(
                apiOrderDetails.getBrowserAccepts(),
                apiOrderDetails.getUserAgent()
        ));
        return shopper;
    }

    private Address toAddress(ApiOrderDetails apiOrderDetails)
    {
        Address address = new Address(
                apiOrderDetails.getAddress(),
                apiOrderDetails.getCity(),
                apiOrderDetails.getPostalCode(),
                apiOrderDetails.getCountryCode()
        );
        return address;
    }

}
