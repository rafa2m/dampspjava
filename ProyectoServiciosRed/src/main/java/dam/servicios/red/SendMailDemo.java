package dam.servicios.red;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Clase para enviar correos a cuentas Google, usando TSL o SSL
 * @author rafa
 * @version 1.0
 */
public class SendMailDemo {
	public static void main(String[] args) {
		//destinatario del correo.
		String destinatario =  "destinatario@gmail.com"; 
		//A quien le quieres escribir.
		String asunto = "Correo de prueba enviado desde Java";
		String cuerpo = "Esta es una prueba de correo...";
		enviarCorreoSsl(destinatario, asunto, cuerpo);
	}
	/**
	 * Envío de correo con TLS
	 * @param destinatario Dirección de correo del destinatario
	 * @param asunto Asunto del correo
	 * @param cuerpo Mensaje del correo
	 */
	public static void enviarCorreoTls(String destinatario, String asunto, String cuerpo) {
		//la cuenta Google del remitente es la que tiene que activado: Acceso de aplicaciones menos seguras
		final String USERNAME = "remitente@gmail.com";
		final String PASSWORD = "clave_remitente";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); //TLS

		Session session = Session.getInstance(prop,
				new Authenticator() {
			protected PasswordAuthentication 
			getPasswordAuthentication() {
				return new 
						PasswordAuthentication(USERNAME, 
								PASSWORD);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(
					Message.RecipientType.TO,
					InternetAddress.parse(destinatario)
					);
			message.setSubject(asunto);
			message.setText(cuerpo);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Envía correos usando SSL
	 * @param destinatario Dirección de correo del destinatario
	 * @param asunto Asunto del correo
	 * @param cuerpo Mensaje del correo
	 */
	public static void enviarCorreoSsl(String 
			destinatario,String asunto, String cuerpo) {
		final String USERNAME = "remitente@gmail.com";
		final String PASSWORD = "clave_remitente";

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", 
				"javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication 
			getPasswordAuthentication() {
				return new 
						PasswordAuthentication(USERNAME, 
								PASSWORD);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.setRecipients(
					Message.RecipientType.TO,
					InternetAddress.parse(destinatario)
					);
			message.setSubject(asunto);
			message.setText(cuerpo);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}


}
