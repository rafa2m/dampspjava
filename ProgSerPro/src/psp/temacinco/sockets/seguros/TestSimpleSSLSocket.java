package psp.temacinco.sockets.seguros;

import java.io.IOException;

public class TestSimpleSSLSocket {
	public static void main(String[] args) throws IOException {
		new SimpleSSLServerSocket(5557).start();

		new SimpleSSLClientSocket("localhost",5557).start();
	}
}
