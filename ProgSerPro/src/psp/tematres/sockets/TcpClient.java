package psp.tematres.sockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
/**
 * Ejemplo de cliente socket. Se envía un mensaje al servidor y se espera la respuesta
 * @author rafa
 * @version 1.0
 *
 */
public class TcpClient {
	public static void main(String argv[]) throws Exception {
		String sentence;
		String modifiedSentence;
		//se abre el flujo de lectura desde la consola
		BufferedReader inFromUser = new BufferedReader( new 
				InputStreamReader(System.in));
		//se conecta con el servidor
		Socket clientSocket = new Socket("localhost", 6789);
		//se obtiene el flujo de escritura del servidor para
		//hablar con él
		DataOutputStream outToServer = new 
				DataOutputStream(clientSocket.
						getOutputStream());
		//se obtiene el flujo de lectura para escuchar al servidor
		BufferedReader inFromServer = new BufferedReader(new 
				InputStreamReader(clientSocket.
						getInputStream()));
		//se lee de consola la entrada del usuario
		sentence = inFromUser.readLine();
		//se envía lo que escribe el usuario al servidor
		outToServer.writeBytes(sentence + "\n");
		//se espera la respuesta del servidor
		modifiedSentence = inFromServer.readLine();
		//el servidor ha respondido, se muestra la respuesta en consola
		System.out.println("FROM SERVER: " + modifiedSentence);
		//se cierra el socket
		clientSocket.close();
	}

}
