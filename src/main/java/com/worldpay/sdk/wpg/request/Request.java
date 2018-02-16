package com.worldpay.sdk.wpg.request;

import com.worldpay.sdk.wpg.connection.GatewayContext;
import com.worldpay.sdk.wpg.connection.SessionContext;
import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.exception.WpgConnectionException;
import com.worldpay.sdk.wpg.exception.WpgRequestException;
import com.worldpay.sdk.wpg.response.Response;

public interface Request<T extends Response>
{

    T send(GatewayContext gatewayContext, SessionContext sessionContext) throws WpgRequestException, WpgConnectionException;

}
