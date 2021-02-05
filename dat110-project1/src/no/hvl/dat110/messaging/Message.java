package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

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
		
		// TODO
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format
		
		//=================================================================
		
		encoded = Arrays.copyOf(this.payload, MessageConfig.SEGMENTSIZE);
		encoded[0] = (byte) this.payload.length;
		System.arraycopy(this.payload, 0, encoded, 1, this.payload.length);
		
//		Copies the source array (payload) startin from position 0 into the
//		target array (encoded) from position 1, seeing as position 0 is
//		reserved for the length. the last integer says how much of the
//		original array to copy, payload.length means we copy the entire thing
		
//		Writing this comment mainly because it is a tricky method to keep track of.
		
		//=================================================================
		
//		if (true)
//		   throw new UnsupportedOperationException(TODO.method());

		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		
		//=================================================================
		
		this.payload = Arrays.copyOfRange(received, 1, received[0] + 1);
		
		//=================================================================
		
//		throw new UnsupportedOperationException(TODO.method());
		
	}
}
