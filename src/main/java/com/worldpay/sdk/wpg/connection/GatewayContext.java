package com.worldpay.sdk.wpg.connection;

import com.worldpay.sdk.wpg.connection.auth.Auth;
import com.worldpay.sdk.wpg.connection.factory.ConnectionFactory;
import com.worldpay.sdk.wpg.connection.factory.SimpleConnectionFactory;

public class GatewayContext
{
    private Environment environment;
    private Auth auth;

    private ConnectionFactory connectionFactory;

    public GatewayContext(Environment environment, Auth auth)
    {
        this.environment = environment;
        this.auth = auth;
        this.connectionFactory = new SimpleConnectionFactory();
    }

    public Environment getEnvironment()
    {
        return environment;
    }

    public ConnectionFactory getConnectionFactory()
    {
        return connectionFactory;
    }

    public Auth getAuth()
    {
        return auth;
    }

}