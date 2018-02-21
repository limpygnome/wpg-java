package com.worldpay.sdk.wpg.response;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;

import java.util.Map;

public interface Response<T>
{

    HttpResponse getHttpResponse();

    T getObject();

}
