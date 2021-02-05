package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import no.hvl.dat110.TODO;

public class MessagingServer
{

    private ServerSocket welcomeSocket;

    public MessagingServer(int port)
    {
        try
        {
            this.welcomeSocket = new ServerSocket(port);
        }
        catch (IOException ex)
        {

            System.out.println("Messaging server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // accept an incoming connection from a client
    public Connection accept() throws IOException
    {
        // DONE
        // accept TCP connection on welcome socket and create messaging connection

        Connection connection = new Connection(welcomeSocket.accept());

        return connection;
    }

    public void stop()
    {
        if (welcomeSocket != null)
        {
            try
            {
                welcomeSocket.close();
            }
            catch (IOException ex)
            {
                System.out.println("Messaging server: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
