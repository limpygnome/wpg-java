package com.worldpay.sdk.demoshop.service;

import com.worldpay.sdk.demoshop.api.model.ApiResult;
import com.worldpay.sdk.demoshop.domain.ApiCardDetails;
import com.worldpay.sdk.demoshop.domain.ApiHppDetails;
import com.worldpay.sdk.demoshop.domain.ApiOrderDetails;
import com.worldpay.sdk.demoshop.domain.ApiPayPalDetails;
import com.worldpay.sdk.demoshop.domain.ApiTokenDetails;
import com.worldpay.sdk.wpg.builder.RandomIdentifier;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.card.CardDetails;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.shopper.Shopper;
import com.worldpay.sdk.wpg.domain.shopper.ShopperBrowser;
import com.worldpay.sdk.wpg.domain.payment.Amount;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.domain.redirect.RedirectUrl;
import com.worldpay.sdk.wpg.domain.tokenisation.CreateTokenDetails;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenScope;
import com.worldpay.sdk.wpg.domain.tokenisation.TokenisationPaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgException;
import com.worldpay.sdk.wpg.request.apm.PayPalPaymentRequest;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.request.hosted.HostedPaymentPagesRequest;
import com.worldpay.sdk.wpg.request.tokenisation.TokenPaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SdkService
{
    @Autowired
    private GatewayContext gatewayContext;

    public ApiResult pay(ApiOrderDetails apiOrderDetails, ApiCardDetails apiCardDetails)
    {
        ApiResult result;

        try
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

            if (apiCardDetails.isTokenise())
            {
                CreateTokenDetails tokenDetails = new CreateTokenDetails(RandomIdentifier.generate(8), "demoshop");
                request.tokeniseForReoccurringPayments(tokenDetails);
            }

            PaymentResponse response = request.send(gatewayContext);
            result = new ApiResult(response, null, null);
        }
        catch (WpgException e)
        {
            result = new ApiResult(null, null, e.getMessage());
        }

        return result;
    }

    public ApiResult pay(ApiOrderDetails apiOrderDetails, ApiTokenDetails apiTokenDetails)
    {
        ApiResult result;

        try
        {
            // Translate
            OrderDetails orderDetails = toOrderDetails(apiOrderDetails);
            Address address = toAddress(apiOrderDetails);
            Shopper shopper = toShopper(apiOrderDetails);

            // Make request
            TokenPaymentRequest request = new TokenPaymentRequest(apiTokenDetails.getTokenId(), TokenScope.SHOPPER, orderDetails, shopper, address, address, apiTokenDetails.isCaptureCvc());

            TokenisationPaymentResponse response = request.send(gatewayContext);
            if (response.isCaptureCvc())
            {
                result = new ApiResult(null, response.getCaptureCvcUrl().getUrl(), null);
            }
            else
            {
                result = new ApiResult(response.getPaymentResponse(), null, null);
            }
        }
        catch (WpgException e)
        {
            result = new ApiResult(null, null, e.getMessage());
        }

        return result;
    }

    public ApiResult pay(ApiOrderDetails apiOrderDetails, ApiHppDetails apiHppDetails)
    {
        ApiResult result;

        try
        {
            // Translate
            OrderDetails orderDetails = toOrderDetails(apiOrderDetails);
            Address address = toAddress(apiOrderDetails);
            Shopper shopper = toShopper(apiOrderDetails);

            // Make request
            HostedPaymentPagesRequest request = new HostedPaymentPagesRequest()
                    .orderDetails(orderDetails)
                    .billingAddress(address)
                    .shippingAddress(address)
                    .shopper(shopper);

            RedirectUrl response = request.send(gatewayContext);

            // Manipulate
            String url = response.paymentPages()
                    .successUrl(apiHppDetails.getSuccessUrl())
                    .pendingUrl(apiHppDetails.getPendingUrl())
                    .failureUrl(apiHppDetails.getFailureUrl())
                    .errorUrl(apiHppDetails.getErrorUrl())
                    .cancelUrl(apiHppDetails.getCancelUrl())
                    .preferredPaymentMethod(apiHppDetails.getPaymentMethod())
                    .build();

            result = new ApiResult(null, url, null);
        }
        catch (WpgException e)
        {
            result = new ApiResult(null, null, e.getMessage());
        }

        return result;
    }

    public ApiResult pay(ApiOrderDetails apiOrderDetails, ApiPayPalDetails apiPayPalDetails)
    {
        ApiResult result;

        try
        {
            // Translate
            OrderDetails orderDetails = toOrderDetails(apiOrderDetails);
            Address address = toAddress(apiOrderDetails);
            Shopper shopper = toShopper(apiOrderDetails);
            shopper.setEmail(apiPayPalDetails.getEmail());

            // Make request
            PayPalPaymentRequest request = new PayPalPaymentRequest()
                    .orderDetails(orderDetails)
                    .shopper(shopper)
                    .billingAddress(address)
                    .shippingAddress(address)
                    .successURL(apiPayPalDetails.getSuccessUrl())
                    .failureURL(apiPayPalDetails.getFailureUrl())
                    .cancelURL(apiPayPalDetails.getCancelUrl());

            RedirectUrl response = request.send(gatewayContext);
            result = new ApiResult(null, response.getUrl(), null);
        }
        catch (WpgException e)
        {
            result = new ApiResult(null, null, e.getMessage());
        }

        return result;
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
        Shopper shopper = new Shopper(null, apiOrderDetails.getIpAddress(), new ShopperBrowser(
                apiOrderDetails.getBrowserAccepts(),
                apiOrderDetails.getUserAgent()
        ), apiOrderDetails.getShopperId());
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
