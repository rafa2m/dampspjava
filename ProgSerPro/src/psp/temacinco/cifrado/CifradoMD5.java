package psp.temacinco.cifrado;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/**
 * Ejemplo para extraer MD5 de una cadena de caracteres.
 * Los bytes obtenidos son legibles, después se muestran en
 * formato hexadecimal y también se muestran codificado
 * en base 64 bits con la ayuda de la librería de
 * Apache commons
 * 
 * Características de MD5:
 * - Se utiliza para textos cortos
 * - Es un algoritmo asimétrico, no es posible descifrar el texto
 * una vez cifrado
 *
 */
public class CifradoMD5 {

	public static void main(String[] args) {
		MessageDigest md;
		try {
			//inicialización del objeto digest para cifrar
			md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
			
			//si queremos usar SHA sólo tenemos que cambiar el algoritmo
			//md = MessageDigest.getInstance(MessageDigestAlgorithms.SHA3_512);
			
			//le pasamos el texot a cifrar, con update()
			md.update("texto a cifrar".getBytes());
			//obtenemos el texto a cifrar en un array de bytes 
			//para después mostrarlo en hexadecimal
			byte[] digest = md.digest();
			
			//escribimos byte a byte su equivalente en hexadecimal
			for(byte b:digest) {
				//conversión de byte a hexadecimal
				System.out.print(Integer.toHexString(0xFF & b));
			}
			System.out.println();
			
			//se escribe codificado en base 64 bits
			byte[] encoded = Base64.encodeBase64(digest);
			System.out.println(new String(encoded));
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}		
	}

	
	
	
	
	
	
	
	
	
	
}
