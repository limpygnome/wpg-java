package com.worldpay.sdk.demoshop.config;

import com.worldpay.sdk.wpg.connection.Environment;
import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.auth.UserPassAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SdkConfig
{

    @Bean
    public GatewayContext gatewayContext(@Value("${sdk.env:sandbox}") String environment,
                                         @Value("${sdk.merchantCode}") String merchantCode,
                                         @Value("${sdk.user}") String user,
                                         @Value("${sdk.pass}") String pass,
                                         @Value("${sdk.installationId:}") String installationId)
    {
        Environment environment1 = Environment.valueOf(environment.toUpperCase());
        Auth auth = new UserPassAuth(user, pass, merchantCode, installationId);
        GatewayContext gatewayContext = new GatewayContext(environment1, auth);
        return gatewayContext;
    }

}
