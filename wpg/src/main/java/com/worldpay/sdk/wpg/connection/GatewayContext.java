package com.worldpay.sdk.wpg.connection;

import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.factory.ConnectionFactory;
import com.worldpay.sdk.wpg.connection.factory.SimpleConnectionFactory;

public class GatewayContext
{
    // Mandatory
    private Environment environment;
    private Auth auth;

    // Connection
    private int socketConnectTimeout;
    private int socketReadTimeout;

    private ConnectionFactory connectionFactory;

    public GatewayContext(Environment environment, Auth auth)
    {
        this.environment = environment;
        this.auth = auth;
        this.connectionFactory = new SimpleConnectionFactory();
        this.socketConnectTimeout = 10_000;
        this.socketReadTimeout = 60_000;
    }

    public Environment getEnvironment()
    {
        return environment;
    }

    public void setEnvironment(Environment environment)
    {
        this.environment = environment;
    }

    public Auth getAuth()
    {
        return auth;
    }

    public void setAuth(Auth auth)
    {
        this.auth = auth;
    }

    public int getSocketReadTimeout()
    {
        return socketReadTimeout;
    }

    public void setSocketReadTimeout(int socketReadTimeout)
    {
        this.socketReadTimeout = socketReadTimeout;
    }

    public int getSocketConnectTimeout()
    {
        return socketConnectTimeout;
    }

    public void setSocketConnectTimeout(int socketConnectTimeout)
    {
        this.socketConnectTimeout = socketConnectTimeout;
    }

    public ConnectionFactory getConnectionFactory()
    {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory)
    {
        this.connectionFactory = connectionFactory;
    }

}