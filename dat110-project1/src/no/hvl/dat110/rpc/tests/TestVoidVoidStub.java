package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestVoidVoidStub extends RPCStub {

	public void m() {
		
		byte[] request = RPCUtils.marshallVoid((byte)1);
		
		byte[] reply = rpcclient.call(request);
		
		RPCUtils.unmarshallVoid(reply);
		
	}
}
