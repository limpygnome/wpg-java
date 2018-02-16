package com.worldpay.sdk.wpg.connection;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Holds information for a series of requests forming a session.
 */
public class SessionContext
{
    private String sessionId;
    private Map<String, String> headers;

    public SessionContext()
    {
        sessionId = UUID.randomUUID().toString();
        headers = new HashMap<>();
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public Map<String, String> getHeaders()
    {
        return headers;
    }

    public void setHeaders(Map<String, String> headers)
    {
        this.headers = headers;
    }

    public void addHeader(String key, String value)
    {
        headers.put(key, value);
    }

}
