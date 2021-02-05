package no.hvl.dat110.rpc.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServer;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class TestRPC {

	private static int PORT = 8080;
	private static String SERVER = "localhost";

	@Test
	public void testStartStop() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCServerStopStub stub = new RPCServerStopStub();

				client.register(stub);

				stub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testVoidCall() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestVoidVoidImpl voidvoidimpl = new TestVoidVoidImpl();

				server.register(1, voidvoidimpl);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCServerStopStub stopstub = new RPCServerStopStub();
				TestVoidVoidStub voidvoidstub = new TestVoidVoidStub();

				client.register(stopstub);
				client.register(voidvoidstub);

				// void test case
				voidvoidstub.m();

				assertTrue(true); // just check that we complete call
				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testStringCall() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestStringStringImpl stringstringimpl = new TestStringStringImpl();

				server.register(2, stringstringimpl);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCServerStopStub stopstub = new RPCServerStopStub();
				TestStringStringStub stringstringstub = new TestStringStringStub();

				client.register(stopstub);
				client.register(stringstringstub);

				// string test case
				String teststr = "string";
				String resstr = stringstringstub.m(teststr);

				assertEquals(teststr + teststr, resstr);

				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testIntCall() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestIntIntImpl intintimpl = new TestIntIntImpl();

				server.register(3, intintimpl);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCServerStopStub stopstub = new RPCServerStopStub();
				TestIntIntStub intintstub = new TestIntIntStub();

				client.register(stopstub);
				client.register(intintstub);

				// int test case
				int x = 42;
				int resx = intintstub.m(x);

				assertEquals(x, resx);

				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testBoolCall() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestBooleanBooleanImpl boolboolimpl = new TestBooleanBooleanImpl();

				server.register(4, boolboolimpl);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCServerStopStub stopstub = new RPCServerStopStub();
				TestBooleanBooleanStub boolboolstub = new TestBooleanBooleanStub();

				client.register(stopstub);
				client.register(boolboolstub);

				// boolean test case

				boolean testb = true;
				boolean resb = boolboolstub.m(testb);

				assertEquals(!testb, resb);

				testb = false;
				resb = boolboolstub.m(testb);
				assertEquals(!testb, resb);

				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}

	@Test
	public void testAllCalls() {

		RPCClient client = new RPCClient(SERVER, PORT);
		RPCServer server = new RPCServer(PORT);

		Thread serverthread = new Thread() {

			public void run() {

				TestVoidVoidImpl voidvoidimpl = new TestVoidVoidImpl();
				TestStringStringImpl stringstringimpl = new TestStringStringImpl();
				TestIntIntImpl intintimpl = new TestIntIntImpl();
				TestBooleanBooleanImpl boolboolimpl = new TestBooleanBooleanImpl();

				server.register(1, voidvoidimpl);
				server.register(2, stringstringimpl);
				server.register(3, intintimpl);
				server.register(4, boolboolimpl);

				server.run();

				server.stop();
			}
		};

		Thread clientthread = new Thread() {

			public void run() {

				client.connect();

				RPCServerStopStub stopstub = new RPCServerStopStub();
				TestVoidVoidStub voidvoidstub = new TestVoidVoidStub();
				TestStringStringStub stringstringstub = new TestStringStringStub();
				TestIntIntStub intintstub = new TestIntIntStub();
				TestBooleanBooleanStub boolboolstub = new TestBooleanBooleanStub();

				client.register(stopstub);
				client.register(voidvoidstub);
				client.register(stringstringstub);
				client.register(intintstub);
				client.register(boolboolstub);

				// void test case
				voidvoidstub.m();

				// string test case
				String teststr = "string";
				String resstr = stringstringstub.m(teststr);

				assertEquals(teststr + teststr, resstr);

				// int test case
				int x = 42;
				int resx = intintstub.m(x);

				assertEquals(x, resx);
				// boolean test case

				boolean testb = true;
				boolean resb = boolboolstub.m(testb);

				assertEquals(!testb, resb);

				testb = false;
				resb = boolboolstub.m(testb);
				assertEquals(!testb, resb);

				stopstub.stop();

				client.disconnect();

			}
		};

		System.out.println("System starting ... ");

		serverthread.start();
		clientthread.start();

		try {
			serverthread.join();
			clientthread.join();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("System stopping ... ");

	}
}
