package com.worldpay.sdk.wpg.response;

import java.util.Map;

public interface Response
{

    Map<String, String> getHeaders();

    String getText();

    Object getObject();

    void parse(Object response);

}
