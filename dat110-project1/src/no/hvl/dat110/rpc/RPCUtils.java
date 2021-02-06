package no.hvl.dat110.rpc;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded;

		// TODO: marshall RPC identifier and string into byte array
		
		//=================================================================
		
		byte[] temp = str.getBytes();
		encoded = new byte[temp.length + 1];
		encoded[0] = rpcid;
		System.arraycopy(temp, 0, encoded, 1, temp.length);
		
		//=================================================================

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded;

		// TODO: unmarshall String contained in data into decoded
		
		//=================================================================
		
		data = Arrays.copyOfRange(data, 1, data.length);
		decoded = new String(data);
		
		//=================================================================		

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded;

		// TODO: marshall RPC identifier in case of void type
		
		//=================================================================
		
		encoded = new byte[1];
		encoded[0] = rpcid;
		
		//=================================================================

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// COMPLETED: unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded;

		// TODO: marshall RPC identifier and string into byte array
		
		//=================================================================
		
		encoded = new byte[5]; // rpcid + 4 byte integer
		encoded[0] = rpcid;
		byte[] data = integerToByteArray(x);
		System.arraycopy(data, 0, encoded, 1, data.length);
		
		//=================================================================

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return encoded;
	}
	
	private static final void printArray(byte[] bytes) {
		for (byte b : bytes) {
			System.out.println(b);
		}
	}
	
	//=================================================================
	
	private static final byte[] integerToByteArray(int val) {
		return new byte[] {
				(byte)(val >>> 24),
				(byte)(val >>> 16),
				(byte)(val >>> 8),
				(byte)val
		};
	}
	
	private static final int byteArrayToInteger(byte[] data) {
		return ((data[0] & 0xFF) << 24) | 
	           ((data[1] & 0xFF) << 16) | 
	           ((data[2] & 0xFF) << 8 ) | 
	           ((data[3] & 0xFF) << 0 );
	}
	
	//=================================================================

	public static int unmarshallInteger(byte[] data) {

		int decoded;

		// TODO: unmarshall integer contained in data
		
		//=================================================================
		
		byte[] temp = Arrays.copyOfRange(data, 1, 5); //rpcid + 4 byte integer = 5
		decoded = byteArrayToInteger(temp);
		
		//=================================================================

//		if (true) {
//			throw new UnsupportedOperationException(TODO.method());
//		}

		return decoded;

	}
}
