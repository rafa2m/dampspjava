package psp.tematres.chatdam.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import psp.tematres.chatdam.client.UdpChatClient;

//TODO: revisar, optimizar y documentar el código (JavaDoc)
public class ThreadChatServer extends Thread{
	private static ArrayList<UdpChatClient> udpClients=new ArrayList<UdpChatClient>();
	private ChatServer serverSocket;
	private ObjectInputStream fEntrada;
	private ObjectOutputStream fSalida;
	private Socket socket;

	public ThreadChatServer(ChatServer serverSocket, Socket socket) {
		this.serverSocket = serverSocket;
		this.socket = socket;
		try {
			this.fEntrada = new ObjectInputStream(socket.getInputStream());
			this.fSalida = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		//hay que leer los datos de identificación del cliente UDP
		//esperamos los datos del cliente UDP
		UdpChatClient udpChatClient;
		try {
			long count=0;
			udpChatClient = (UdpChatClient) fEntrada.readObject();
			//si no existe se añade a la lista de clientes UDP
			if((count = this.udpClients.stream().filter(u->u.getNickName().equals(udpChatClient.getNickName())
					&& u.getUdpPort()==udpChatClient.getUdpPort()).count())==0) {
				this.udpClients.add(udpChatClient);
				this.serverSocket.setUdpChatClients(udpClients);
			}
			//se devuelve la lista de clientes UDP al cliente que se ha conectado
			this.fSalida.writeObject(this.udpClients);
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}finally {
			try {
				fSalida.close();
				fEntrada.close();
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
