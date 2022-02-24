package psp.temacinco.cifrado;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
/**
 * Cifrado RSA con par de claves pública-privada. Las
 * claves se guardan y obtienen de un fichero
 */
public class CifradoRSA {
	private static Cipher rsa;

	public static void main(String[] args) throws Exception {
		// Generar el par de claves
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		// Se salva y recupera de fichero la clave publica
		saveKey(publicKey, "publickey.dat");
		publicKey = loadPublicKey("publickey.dat");

		// Se salva y recupera de fichero la clave privada
		saveKey(privateKey, "privatekey.dat");
		privateKey = loadPrivateKey("privatekey.dat");

		// Obtener la clase para encriptar/desencriptar
		rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");

		// Texto a encriptar
		String text = "Texto a cifrar";

		// Se encripta
		rsa.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encriptado = rsa.doFinal(text.getBytes());

		// Escribimos el encriptado para verlo, con caracteres visibles
		for (byte b : encriptado) {
			System.out.print(Integer.toHexString(0xFF & b));
		}
		System.out.println();

		// Se desencripta
		rsa.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] bytesDesencriptados = rsa.doFinal(encriptado);
		String textoDesencripado = new String(bytesDesencriptados);

		// Se escribe el texto desencriptado
		System.out.println(textoDesencripado);
	}
	/**
	 * Método para obtener la clave pública almacenada en un fichero
	 * @param fileName Nombre del fichero donde está almacenada la 
	 * clave pública
	 * @return Clave pública
	 * @throws Exception Excepción general no controlada
	 */
	private static PublicKey loadPublicKey(String fileName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		int numBtyes = fis.available();
		byte[] bytes = new byte[numBtyes];
		//se lee la clave pública y se obtiene en un array de bytes
		fis.read(bytes);
		fis.close();
		//se obtiene la instancia del fabricante de claves
		//para cifrado RSA
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		//se cifra la clave
		KeySpec keySpec = new X509EncodedKeySpec(bytes);
		//se marca como clave pública
		PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
		//se devuelve
		return keyFromBytes;
	}
	/**
	 * Obtiene la clave privada desde un fichero que se indica 
	 * como parámetro
	 * @param fileName Nombre del fichero en el que se encuentra la clave
	 * @return Clave privada
	 * @throws Exception Excepción general
	 */
	private static PrivateKey loadPrivateKey(String fileName) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		int numBtyes = fis.available();
		byte[] bytes = new byte[numBtyes];
		fis.read(bytes);
		fis.close();
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		//se decodifica la clave
		KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
		//se marca como clave privada
		PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
		//se devuelve la clave privada
		return keyFromBytes;
	}
	/**
	 * Guarda la clave pasada como parámetro en el fichero pasado
	 * como parámetro
	 * @param key Clave a guardar en el fichero
	 * @param fileName Nombre del fichero
	 * @throws Exception Excepción general
	 */
	private static void saveKey(Key key, String fileName) throws Exception {
		byte[] publicKeyBytes = key.getEncoded();
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(publicKeyBytes);
		fos.close();
	}
}
