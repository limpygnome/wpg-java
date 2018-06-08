package com.worldpay.sdk.demoshop.api.controller;

import com.worldpay.sdk.demoshop.api.model.ApiResult;
import com.worldpay.sdk.demoshop.api.model.PayByCardRequest;
import com.worldpay.sdk.demoshop.domain.ApiOrderDetails;
import com.worldpay.sdk.demoshop.service.SdkService;
import com.worldpay.sdk.wpg.domain.payment.PaymentResponse;
import com.worldpay.sdk.wpg.exception.WpgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pay")
public class PayController
{
    @Autowired
    private SdkService sdkService;

    @PostMapping("/card")
    public ApiResult payByCard(HttpServletRequest servletRequest, @RequestBody PayByCardRequest request)
    {
        ApiResult result;

        populateBrowserDetails(servletRequest, request.getOrderDetails());

        try
        {
            PaymentResponse paymentResponse = sdkService.pay(request.getOrderDetails(), request.getCardDetails());
            result = new ApiResult(paymentResponse, null, null);
        }
        catch (WpgException e)
        {
            result = new ApiResult(null, null, e.getMessage());
        }

        return result;
    }

    @PostMapping("/token")
    public void payUsingToken()
    {
    }

    @PostMapping("/hpp")
    public void payByHpp()
    {
    }

    @PostMapping("/paypal")
    public void payByPayPal()
    {
    }

    private void populateBrowserDetails(HttpServletRequest servletRequest, ApiOrderDetails apiOrderDetails)
    {
        String accepts = servletRequest.getHeader("Accepts");
        apiOrderDetails.setBrowserAccepts(accepts);
        String userAgent = servletRequest.getHeader("User-Agent");
        apiOrderDetails.setUserAgent(userAgent);
    }

}
