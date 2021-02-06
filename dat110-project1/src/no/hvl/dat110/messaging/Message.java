package no.hvl.dat110.messaging;

import java.util.Arrays;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = null;
		
		// COMPLETED
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format
		
		//=================================================================
		
		encoded = Arrays.copyOf(this.payload, MessageConfig.SEGMENTSIZE);
		encoded[0] = (byte) this.payload.length;
		System.arraycopy(this.payload, 0, encoded, 1, this.payload.length);
		
		//=================================================================

		return encoded;	
	}

	public void decapsulate(byte[] received) {

		// COMPLETED
		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		
		//=================================================================
		
		this.payload = Arrays.copyOfRange(received, 1, received[0] + 1);
		
		//=================================================================
	}
}
