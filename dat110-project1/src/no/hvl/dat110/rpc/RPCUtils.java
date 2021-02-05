package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils
{

    // Utility methods for marshalling and unmarshalling of parameters and return values
    // in RPC request and RPC responses
    // data bytearrays and return byte arrays is according to the
    // RPC message syntax [rpcid,parameter/return value]

    public static byte[] marshallString(byte rpcid, String str)
    {
        byte[] encoded;

        // DONE: marshall RPC identifier and string into byte array

        byte[] string = str.getBytes();
        encoded = new byte[string.length + 1];

        encoded[0] = rpcid;

        for (int i = 0; i < string.length; i++)
        {
            encoded[i + 1] = string[i];
        }

        return encoded;
    }

    public static String unmarshallString(byte[] data)
    {
        // DONE: unmarshall String contained in data into decoded

        return new String(Arrays.copyOfRange(data, 1, data.length), StandardCharsets.UTF_8);
    }

    public static byte[] marshallVoid(byte rpcid)
    {
        byte[] encoded = new byte[1];

        // Done: marshall RPC identifier in case of void type

        encoded[0] = rpcid;

        return encoded;
    }

    public static void unmarshallVoid(byte[] data)
    {
        // DONE: unmarshall void type
        // Nothing to do here...
    }

    public static byte[] marshallBoolean(byte rpcid, boolean b)
    {
        byte[] encoded = new byte[2];

        encoded[0] = rpcid;

        encoded[1] = b ? (byte) 1 : (byte) 0;

        return encoded;
    }

    public static boolean unmarshallBoolean(byte[] data)
    {
        return (data[1] > 0);
    }

    public static byte[] marshallInteger(byte rpcid, int x)
    {
        byte[] encoded = new byte[5];

        // DONE: marshall RPC identifier and string into byte array

        encoded[0] = rpcid;

        for (int i = 0; i < 4; i++)
        {
            encoded[i + 1] = intToBytes(x)[i];
        }

        return encoded;
    }

    public static int unmarshallInteger(byte[] data)
    {
        // DONE: unmarshall integer contained in data

        return bytesToInt(Arrays.copyOfRange(data, 1, data.length));
    }

    private static byte[] intToBytes(int data)
    {
        return new byte[]{
                (byte) ((data >> 24) & 0xff),
                (byte) ((data >> 16) & 0xff),
                (byte) ((data >> 8) & 0xff),
                (byte) ((data >> 0) & 0xff),
        }; // 0000 0000  0000 0000  0000 0000  0000 0000
    }

    private static int bytesToInt(byte[] data)
    {
        int val = 0;
        for (int i = 0; i < 4; i++)
        {
            int shift = (4 - 1 - i) * 8;
            val += (data[i] & 0x000000ff) << shift;
        }
        return val;
    }
}
