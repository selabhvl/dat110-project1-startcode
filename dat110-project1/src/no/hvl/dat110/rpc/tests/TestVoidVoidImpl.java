package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class TestVoidVoidImpl implements RPCImpl {

	public void m() {
		System.out.println("void m() executed");
	}
	
	public byte[] invoke(byte[] request) {
		
		RPCUtils.unmarshallVoid(request);
		
		m();
		
		byte[] reply = RPCUtils.marshallVoid(request[0]);
		
		return reply;
	}
}
