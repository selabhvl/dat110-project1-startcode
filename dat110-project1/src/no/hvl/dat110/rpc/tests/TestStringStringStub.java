package no.hvl.dat110.rpc.tests;

import no.hvl.dat110.rpc.RPCStub;
import no.hvl.dat110.rpc.RPCUtils;

public class TestStringStringStub extends RPCStub {

	public String m(String str) {
		
		byte[] request = RPCUtils.marshallString((byte)2,str);
		
		byte[] reply = rpcclient.call(request);
		
		String strres = RPCUtils.unmarshallString(reply);
		
		return strres;
	}
}
