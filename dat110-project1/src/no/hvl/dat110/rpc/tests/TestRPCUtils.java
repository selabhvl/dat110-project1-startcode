package no.hvl.dat110.rpc.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.rpc.RPCUtils;

class TestRPCUtils {

	@Test
	void testMarshallString() {
		
		String str = "teststring";
		
		byte[] encoded = RPCUtils.marshallString((byte)0, str);
		String decoded = RPCUtils.unmarshallString(encoded);
		
		assertEquals(str,decoded);
	}
	
	@Test
	void testMarshallInteger() {
		
		int testint = 255;
		
		byte[] encoded = RPCUtils.marshallInteger((byte)0, testint);
		int decoded = RPCUtils.unmarshallInteger(encoded);
		
		assertEquals(testint,decoded);
	}
	
	@Test
	void testMarshallBoolean( ) {
		
		byte[] encoded = RPCUtils.marshallBoolean((byte)0, true);
		boolean decoded = RPCUtils.unmarshallBoolean(encoded);
		
		assertTrue(decoded);
		
		encoded = RPCUtils.marshallBoolean((byte)0, false);
		decoded = RPCUtils.unmarshallBoolean(encoded);
		
		assertFalse(decoded);
		
	}
}
