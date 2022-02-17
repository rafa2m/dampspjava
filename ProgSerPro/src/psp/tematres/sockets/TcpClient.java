package psp.tematres.sockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
/**
 * Ejemplo de cliente socket. Se envía un mensaje al servidor y se espera la respuesta
 * @author rafa
 * @version 1.0
 *
 */
public class TcpClient {
	public static void main(String argv[]) throws Exception {
		Message message;
		String sentence;
		String modifiedSentence;
		
		message = new Message("Rafa",
				InetAddress.getLocalHost().getHostAddress());
		
		
		//se abre el flujo de lectura desde la consola
		BufferedReader inFromUser = new BufferedReader( new 
				InputStreamReader(System.in));
		
		
		//se conecta con el servidor
		Socket clientSocket = new Socket("localhost", 6789);
		
		//asigno mi puerto TCP al mensaje
		message.setPort(clientSocket.getLocalPort());
		
		//se obtiene el flujo de escritura del servidor para
		//hablar con él
		ObjectOutputStream outToServer = new 
				ObjectOutputStream(clientSocket.
						getOutputStream());
		//se obtiene el flujo de lectura para escuchar al servidor
		ObjectInputStream inFromServer = new 
				ObjectInputStream(clientSocket.
						getInputStream());
		
		//se lee de consola la entrada del usuario
		sentence = inFromUser.readLine();
		
		//actualizo el texto del mensaje
		message.setMessage(sentence);
		
		//se envía lo que escribe el usuario al servidor
		outToServer.writeObject(message);
		//outToServer.writeBytes(sentence + "\n");
		
		//se espera la respuesta del servidor
		message = (Message)inFromServer.readObject();
		//modifiedSentence = inFromServer.readLine();
		
		//el servidor ha respondido, se muestra la respuesta en consola
		System.out.println("FROM SERVER: " + message.getMessage());
		//se cierra el socket
		clientSocket.close();
	}

}
