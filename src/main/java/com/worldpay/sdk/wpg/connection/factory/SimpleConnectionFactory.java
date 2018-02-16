package com.worldpay.sdk.wpg.connection.factory;

import com.worldpay.sdk.wpg.connection.GatewayContext;

import java.io.IOException;
import java.net.Socket;

public class SimpleConnectionFactory implements ConnectionFactory
{

    public Socket get(GatewayContext connection) throws IOException
    {
        return null;
    }

    public void release(Socket socket)
    {
    }

}
