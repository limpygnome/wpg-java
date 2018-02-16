package com.worldpay.sdk.wpg;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import com.worldpay.sdk.wpg.domain.Address;
import com.worldpay.sdk.wpg.domain.OrderDetails;
import com.worldpay.sdk.wpg.domain.Shopper;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.request.card.CardPaymentRequest;
import com.worldpay.sdk.wpg.response.Response;
import com.worldpay.sdk.wpg.response.approval.CurrencyConversionResponse;
import com.worldpay.sdk.wpg.response.error.ErrorResponse;
import com.worldpay.sdk.wpg.response.order.OrderStatusResponse;
import com.worldpay.sdk.wpg.response.threeds.ThreeDsResponse;

public class CardDemoApp
{

    public static void main(String[] args)
    {
//        // setup sharable gateway
//        Auth auth = new UserPassAuth("NGPPTESTMERCH1", "live2018", "NGPPTESTMERCH1", null);
//        GatewayContext gatewayContext = new GatewayContext(Environment.SANDBOX, auth);
//
//        // build payment
//        OrderDetails details;
//
//        Address billingAddress;
//        Address shippingAddress;
//        Shopper shopper;
//
//        CardPaymentRequest payment = new CardPaymentRequest();
//
//        // send payment(s)
//        try
//        {
//            SessionContext sessionContext = new SessionContext();
//            Response response = payment.send(gatewayContext, sessionContext);
//
//            if (response instanceof OrderStatusResponse)
//            {
//            }
//            else if (response instanceof ThreeDsResponse)
//            {
//            }
//            else if (response instanceof CurrencyConversionResponse)
//            {
//            }
//            else if (response instanceof ErrorResponse)
//            {
//            }
//            else
//            {
//                System.err.println("unhandled response - " + response.getText());
//            }
//        }
//        catch (WpgConnectionException e)
//        {
//            e.printStackTrace();
//        }
//        catch (WpgRequestException e)
//        {
//            e.printStackTrace();
//        }
    }

}
