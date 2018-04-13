package com.worldpay.sdk.wpg.connection.factory;

import com.worldpay.sdk.wpg.connection.GatewayContext;

import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.SocketOptions;

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

    public Socket get(GatewayContext connection, String hostName, int port) throws IOException
    {
        Socket socket = socketFactory.createSocket(hostName, port);
        socket.setSoTimeout(connection.getSocketTimeout());
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
