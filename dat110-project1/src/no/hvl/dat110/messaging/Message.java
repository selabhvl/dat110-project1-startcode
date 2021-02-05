package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message
{
    public static int SIZE = 128;

    private byte[] payload;

    public Message(byte[] payload) throws ArrayIndexOutOfBoundsException
    {
        this.payload = payload;
        // DONE: check for length within boundary
        if (payload.length > SIZE - 1)
        {
            throw new ArrayIndexOutOfBoundsException("Payload cannot exceed " + (SIZE - 1) + " bytes.");
        }
    }

    public Message()
    {
        super();
    }

    public byte[] getData()
    {
        return this.payload;
    }

    public byte[] encapsulate()
    {
        byte[] encoded = new byte[SIZE];

        // DONE
        // encapulate/encode the payload of this message in the
        // encoded byte array according to message format

        encoded[0] = (byte) payload.length;

        for (int i = 0; i < payload.length; i++)
        {
            encoded[i + 1] = payload[i];
        }

        return encoded;
    }

    public void decapsulate(byte[] received)
    {
        // DONE
        // decapsulate the data contained in the received byte array and store it
        // in the payload of this message

        int length = received[0];

        payload = new byte[length];

        for (int i = 0; i < length; i++)
        {
            payload[i] = received[i + 1];
        }
    }
}
