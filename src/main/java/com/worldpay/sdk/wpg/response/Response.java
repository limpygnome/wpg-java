package com.worldpay.sdk.wpg.response;

import com.worldpay.sdk.wpg.connection.http.HttpResponse;

public interface Response<T>
{

    HttpResponse getHttpResponse();

    T getObject();

    ResponseType getResponseType();

}
