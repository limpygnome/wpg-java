package com.worldpay.sdk.demoshop.api;

import com.worldpay.sdk.demoshop.api.model.PayByCardRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController
{

    @PostMapping("/card")
    public void payByCard(@RequestBody PayByCardRequest request)
    {
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

}
