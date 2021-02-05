package no.hvl.dat110.messaging.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessageConfig;

class TestMessage {

	private byte[] createPayload (int size) {
	
		byte[] payload = new byte[size];
		
		for (int i = 0; i<payload.length;i++) {
			payload[i] = (byte)i;
		}
		
		return payload;
	}
	
	@Test
	void testEncapsulate() {
		
		int size = 56;
		byte[] payload = createPayload(size);
		
		Message message = new Message(payload);
		
		byte[] encoded = message.encapsulate();
		
		assertEquals(size,encoded[0]);
		
		assertEquals(MessageConfig.SEGMENTSIZE,encoded.length);
		
		for (int i = 0; i<payload.length;i++) {
			assertEquals(payload[i],encoded[i+1]);
		}
	}
		
	@Test
	void testDecapsulate() {
		
		byte[] encoded = new byte[MessageConfig.SEGMENTSIZE];
		
		encoded[0] = 5;
		encoded[1] = 1;
		encoded[2] = 2;
		encoded[3] = 3;
		encoded[4] = 4;
		encoded[5] = 5;
		
		Message message = new Message();
		
		message.decapsulate(encoded);
		
		byte[] payload = message.getData();
		
		assertEquals(5,payload.length);
		
		for (int i = 0;i<5;i++) {
			assertEquals(encoded[i+1],payload[i]);
		}
	}

	@Test
	void EncapsulateDecapsulate () {
	
		for (int size = 0;size<MessageConfig.SEGMENTSIZE-1;size++) {
			
			byte[] payload = createPayload(size);
			
			Message message1 = new Message(payload);
			
			byte[] encoded = message1.encapsulate();
			
			Message message2 = new Message();
			
			message2.decapsulate(encoded);
			
			byte[] decoded = message2.getData();
			
			assertTrue(Arrays.equals(payload, decoded));
		}
		
	}	
}