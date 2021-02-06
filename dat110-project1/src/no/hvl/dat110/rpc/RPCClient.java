package no.hvl.dat110.rpc;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void register(RPCStub remote) {
		remote.register(this);
	}
	
	public void connect() {
		
		// COMPLETED: connect using the underlying messaging layer connection
		
		//=================================================================
		
		connection = msgclient.connect();
		
		//=================================================================
	}
	
	public void disconnect() {
		
		// COMPLETED: disconnect/close the underlying messaging connection
		
		//=================================================================
		
		connection.close();
		connection = null;
		
		//=================================================================
	}
	
	public byte[] call(byte[] rpcrequest) {
		
		byte[] rpcreply;
		
		/* COMPLETED: 
		
		Make a remote call on the RPC server by sending the RPC request message
		and receive an RPC reply message
		
		rpcrequest is the marshalled rpcrequest from the client-stub
		rpctreply is the rpcreply to be unmarshalled by the client-stub
		
		*/
		
		//=================================================================
		
		Message message = new Message(rpcrequest);
		connection.send(message);
		
		message = connection.receive();
		rpcreply = message.getData();
		
		//=================================================================
		
		return rpcreply;
		
	}

}
