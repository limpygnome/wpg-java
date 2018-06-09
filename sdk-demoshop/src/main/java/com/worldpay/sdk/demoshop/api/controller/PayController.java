package com.worldpay.sdk.demoshop.api.controller;

import com.worldpay.sdk.demoshop.api.model.ApiResult;
import com.worldpay.sdk.demoshop.api.model.PayByCardRequest;
import com.worldpay.sdk.demoshop.api.model.PayByHppRequest;
import com.worldpay.sdk.demoshop.api.model.PayByPayPalRequest;
import com.worldpay.sdk.demoshop.api.model.PayByTokenRequest;
import com.worldpay.sdk.demoshop.domain.ApiOrderDetails;
import com.worldpay.sdk.demoshop.service.SdkService;
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
        populateBrowserDetails(servletRequest, request.getOrderDetails());
        ApiResult result = sdkService.pay(request.getOrderDetails(), request.getCardDetails());
        return result;
    }

    @PostMapping("/token")
    public ApiResult payUsingToken(HttpServletRequest servletRequest, @RequestBody PayByTokenRequest request)
    {
        populateBrowserDetails(servletRequest, request.getOrderDetails());
        ApiResult result = sdkService.pay(request.getOrderDetails(), request.getTokenDetails());
        return result;
    }

    @PostMapping("/hpp")
    public ApiResult payByHpp(HttpServletRequest servletRequest, @RequestBody PayByHppRequest request)
    {
        populateBrowserDetails(servletRequest, request.getOrderDetails());
        ApiResult result = sdkService.pay(request.getOrderDetails(), request.getHppDetails());
        return result;
    }

    @PostMapping("/paypal")
    public ApiResult payByPayPal(HttpServletRequest servletRequest, @RequestBody PayByPayPalRequest request)
    {
        populateBrowserDetails(servletRequest, request.getOrderDetails());
        ApiResult result = sdkService.pay(request.getOrderDetails(), request.getPayPalDetails());
        return result;
    }

    private void populateBrowserDetails(HttpServletRequest servletRequest, ApiOrderDetails apiOrderDetails)
    {
        String accepts = servletRequest.getHeader("Accepts");
        String userAgent = servletRequest.getHeader("User-Agent");
        String ipAddress = servletRequest.getRemoteAddr();
        apiOrderDetails.setBrowserAccepts(accepts);
        apiOrderDetails.setUserAgent(userAgent);
        apiOrderDetails.setIpAddress(ipAddress);
    }

}
