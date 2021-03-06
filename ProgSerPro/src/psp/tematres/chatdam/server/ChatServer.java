package psp.tematres.chatdam.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import psp.tematres.chatdam.client.UdpChatClient;
import psp.tematres.multisockets.HiloServidor;
import psp.tematres.multisockets.Servidor;
import psp.tematres.multisockets.SocketCliente;

//TODO: revisar, optimizar y documentar el código (JavaDoc)
public class ChatServer {
	private ArrayList<UdpChatClient> udpChatClients;
	private ServerSocket serverSocket;
	private boolean stopServer=false;
	
	public static void main(String[] args) {
		try {
			ChatServer chatServer = new ChatServer(9999);
			chatServer.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ChatServer(int port) throws IOException {
		this.serverSocket = new ServerSocket(port);
	}
	public ArrayList<UdpChatClient> getUdpChatClients(){
		return this.udpChatClients;
	}
	public void setUdpChatClients(ArrayList<UdpChatClient> udpChatClients) {
		this.udpChatClients = udpChatClients;
	}
	public void start() {
		System.out.println("Servidor...");
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(!stopServer) {
					try {
						Socket socket = serverSocket.accept();
						ThreadChatServer hilo = new ThreadChatServer(ChatServer.this,socket);
						hilo.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
