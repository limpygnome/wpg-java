package com.worldpay.sdk.wpg.connection;

import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.factory.ConnectionFactory;
import com.worldpay.sdk.wpg.connection.factory.SimpleConnectionFactory;

import java.net.Proxy;

public class GatewayContext
{
    // Mandatory
    private Environment environment;
    private Auth auth;

    // Connection
    private int socketTimeout;

    private ConnectionFactory connectionFactory;

    public GatewayContext(Environment environment, Auth auth)
    {
        this.environment = environment;
        this.auth = auth;
        this.connectionFactory = new SimpleConnectionFactory();
        this.socketTimeout = 30_000;
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

    public int getSocketTimeout()
    {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout)
    {
        this.socketTimeout = socketTimeout;
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