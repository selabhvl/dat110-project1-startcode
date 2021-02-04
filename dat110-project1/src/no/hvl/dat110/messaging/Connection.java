package no.hvl.dat110.messaging;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static no.hvl.dat110.messaging.MessageConfig.SEGMENTSIZE;

public class Connection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		try {
			outStream.write(message.encapsulate());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Message receive() { 
		Message message = new Message(); 
		byte[] recvbuf = new byte[SEGMENTSIZE]; 
		
		// TODO: COMPLETE 
		// read a segment (128 bytes) from the input stream and decapsulate into message 
		// Hint: create a new Message object and use the decapsulate method 
		
		try { int read = inStream.read(recvbuf,0,MessageConfig.SEGMENTSIZE); 
			if (read != MessageConfig.SEGMENTSIZE) { 
				throw new IOException("receive - missing data"); 
			}
				catch (IOException e) { 
				e.printStackTrace();  
			message = new Message(); 
			message.decapsulate(recvbuf); 
		
		return message; 
	}
}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}