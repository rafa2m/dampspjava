package psp.temacinco.sockets.seguros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketUtil {
	public static void startClientWorking(final Socket client){
		System.out.println("client start");
		new Thread() {
			public void run() {
				try {
					PrintWriter output = new PrintWriter(client.getOutputStream());
					output.println("Rafa");
					output.flush();
					System.out.println("Rafa sent");
					BufferedReader input = new BufferedReader(new InputStreamReader(
							client.getInputStream()));
					String received = input.readLine();
					System.out.println("Received : " + received);
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public static void startServerWorking(final ServerSocket serverSocket) {
		System.out.println("server start");
		new Thread() {
			public void run() {
				try {
					Socket aClient = serverSocket.accept();
					System.out.println("Rafa accepted");
					aClient.setSoLinger(true, 1000);
					BufferedReader input = new BufferedReader(new InputStreamReader(
							aClient.getInputStream()));
					String recibido = input.readLine();
					System.out.println("Recibido " + recibido);
					PrintWriter output = new PrintWriter(aClient.getOutputStream());
					output.println("Hola, " + recibido);
					output.flush();
					aClient.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}.start();
	}
}
