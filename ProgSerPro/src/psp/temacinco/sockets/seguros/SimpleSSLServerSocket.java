package psp.temacinco.sockets.seguros;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
/**
 * Servidor de socket seguro usando SSL
 *
 */
public class SimpleSSLServerSocket {
	private ServerSocket serverSocket;
	/**
	 * Constructor especializado en iniciar un servidor a partir del puerto
	 * indicado como parámetro
	 * @param port Puerto para el socket
	 */
	public SimpleSSLServerSocket(int port) {
		//almacen de claves públicas
		KeyStore keyStore;
		//gestor de claves públicas
		KeyManagerFactory kmf;
		//almacen de claves privadas
		KeyStore trustedStore;
		//gestor de claves privadas
		TrustManagerFactory tmf;
		//socket seguro
		SSLContext sc;
		try {
			//proveedor de claves SUN
			keyStore = KeyStore.getInstance("JKS");
			//se obtiene la clave pública del fichero, para ello se usa la clave de 
			//desbloqueo serverpass
			keyStore.load(new FileInputStream("src/psp/certs/server/serverKey.jks"),
					"servpass".toCharArray());
			//el gestor de clave con el algoritmo por defecto SunX509 
			kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			//se inicia el getor de clave pública a partir de la clave obtenida desde
			//fichero y la clave de desbloqueo
			kmf.init(keyStore, "servpass".toCharArray());
			
			//proveedor de claves SUN para la clave privada
			//se hace lo mismo que antes pero para la clave privada
			trustedStore = KeyStore.getInstance("JKS");
			trustedStore.load(new FileInputStream(
					"src/psp/certs/server/serverTrustedCerts.jks"), "servpass"
					.toCharArray());
			tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(trustedStore);
			
			//se indica el tipo de algoritmo para el socket
			sc = SSLContext.getInstance("TLS");
			//se inicializan los gestores de clave pública y privada
			TrustManager[] trustManagers = tmf.getTrustManagers();
			KeyManager[] keyManagers = kmf.getKeyManagers();
			//se inicializa el socket seguro
			sc.init(keyManagers, trustManagers, null);
			//se inicializa la factoría de sockets seguro
			SSLServerSocketFactory ssf = sc.getServerSocketFactory();
			//se crea el socket seguro
			serverSocket = (SSLServerSocket) ssf.createServerSocket(port);
		} catch (UnrecoverableKeyException | KeyStoreException | 
				NoSuchAlgorithmException | CertificateException | IOException 
				| KeyManagementException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Método para iniciar la comunicación con los clientes del socket
	 */
	public void start() {
		SocketUtil.startServerWorking(serverSocket);
	}
}
