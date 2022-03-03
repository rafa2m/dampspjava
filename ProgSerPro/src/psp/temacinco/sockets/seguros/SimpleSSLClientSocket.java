package psp.temacinco.sockets.seguros;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

public class SimpleSSLClientSocket {
	Socket client = null;

	public SimpleSSLClientSocket(String server, int port)
			throws UnknownHostException, IOException {
		try {
			//almacen de claves públicas
			KeyStore keyStore = KeyStore.getInstance("JKS");
			//se obtiene la clave pública desde fichero usando la clave
			//de desbloqueo del cliente clientpass
			keyStore.load(new FileInputStream("src/psp/certs/client/clientKey.jks"),
					"clientpass".toCharArray());
			//se inicializa el 
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, "clientpass".toCharArray());

			KeyStore trustedStore = KeyStore.getInstance("JKS");
			trustedStore.load(new FileInputStream(
					"src/psp/certs/client/clientTrustedCerts.jks"), "clientpass"
					.toCharArray());

			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustedStore);

			SSLContext sc = SSLContext.getInstance("TLS");
			TrustManager[] trustManagers = tmf.getTrustManagers();
			KeyManager[] keyManagers = kmf.getKeyManagers();
			sc.init(keyManagers, trustManagers, null);

			SSLSocketFactory ssf = sc.getSocketFactory();
			client = (SSLSocket) ssf.createSocket(server, port);
			((SSLSocket) client).startHandshake();
		}catch (UnrecoverableKeyException | KeyStoreException | 
				NoSuchAlgorithmException | CertificateException | IOException 
				| KeyManagementException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		SocketUtil.startClientWorking(client);
	}
}
