package dam.servicios.red;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FtpConnectAndLoginDemo {	
	public static void main(String[] args) {
		//se almacena el nombre del servidor FTP
		String server = "137.74.235.45";
		//indicamos el puerto TCP
		int port = 21;
		//guardamos usuario y clave
		String user = "dam2psp@iesantoniogala.es";
		String pass = "GalaDamPsp_2022";
		//instanciamos objeto de FTPClient
		FTPClient ftpClient = new FTPClient();
		try {
			//conectamos con el servidor
			ftpClient.connect(server, port);
			//se delimitan los puertos utilizables
			//en el lado del cliente
			ftpClient.setActivePortRange(2000, 3500);
			//trabajamos en modo pasivo, para transferencia
			//de datos entre cliente y servidor
			ftpClient.enterLocalPassiveMode();
			
			showServerReply(ftpClient);
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				System.out.println("Operation failed. Server reply code: " + replyCode);
							return;
			}
			//usamos credenciales anónimas para iniciar sesión
			boolean success = ftpClient.login(user, pass);
			showServerReply(ftpClient);
			if (!success) {
				System.out.println("Could not login to the server");
						return;
			} else {
				System.out.println("LOGGED IN SERVER");
			}
			
			//cambiamos de directorio
			boolean result = ftpClient.changeWorkingDirectory("/dampsp");
			showServerReply(ftpClient);
			if(result) {
				//listamos todos los ficheros del directorio /mirror
				FTPFile[] files = ftpClient.listFiles("/dampsp");
				showServerReply(ftpClient);
				for(FTPFile file:files) {
					System.out.println(file.getName());
				}
			}
		} catch (IOException ex) {
			System.out.println("Oops! Something wrong happened");
					ex.printStackTrace();
		} finally {
			try {
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
	}	
	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}
}
