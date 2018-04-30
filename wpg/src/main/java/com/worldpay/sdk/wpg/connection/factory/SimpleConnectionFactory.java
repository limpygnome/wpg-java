package com.worldpay.sdk.wpg.connection.factory;

import com.worldpay.sdk.wpg.connection.GatewayContext;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Provides simple SSL sockets.
 */
public class SimpleConnectionFactory implements ConnectionFactory
{
    private SSLSocketFactory socketFactory;

    public SimpleConnectionFactory()
    {
        socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
    }

    public Socket get(GatewayContext gatewayContext, String hostName, int port) throws IOException
    {
        Socket socket = socketFactory.createSocket();
        socket.setSoTimeout(gatewayContext.getSocketReadTimeout());
        socket.connect(new InetSocketAddress(hostName, port), gatewayContext.getSocketConnectTimeout());
        return socket;
    }

    public void release(Socket socket)
    {
        if (socket != null)
        {
            try
            {
                socket.close();
            }
            catch (IOException e)
            {
                // TODO consider warning
            }
        }
    }

}
