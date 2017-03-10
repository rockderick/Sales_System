/*
 * Created on Mar 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.com.iusacell.catalogo.utilerias;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.engines.BlowfishEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;
/**
 * @author JOJEDAH
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EncriptaBlowfish {

	/**
	 * Método para encriptar una cadena utilizando la implementación de Blowfish
	 * de BouncyCastle.
	 * 
	 * @param cadena Cadena que se va a encriptar.
	 * @return Cadena en formato hexadecimal que representa la encripción de la
	 * cadena original.
	 */
	public static String encripta(String cadena) {
		
		/* Define la llave de encripción */
		CipherParameters param = new KeyParameter(Hex.decode("2900554932452843"));
		
		/* Define el crifador utilizando Blowfish*/
		BufferedBlockCipher cipher = new BufferedBlockCipher(new BlowfishEngine());
	
		/* Preprocesa la cadena de entrada */
		byte[] entrada = Hex.decode(preProcesaCadena(cadena));
	
		/* Inicializa el cifrador para encripción */
		cipher.init(true, param);
		
		/* Procesa de el arreglo de entrada */
		byte[] salida = new byte[entrada.length];
		cipher.processBytes(entrada, 0, entrada.length, salida, 0);
		
		/* Regresa cadena cifrada */
		return new String(Hex.encode(salida));
	}
    
	/**
	 * Método que regresa siempre una cadena de 16 caracteres de longitud.
	 * @param cadena Cadena a preprocesar.
	 * @return Cadena de 16 caracteres de longitud.
	*/
	private static String preProcesaCadena(String cadena) {
		String cadenaProcesada = "";
		for(int i = 0; i < cadena.length(); i++){
			if(Character.isLowerCase(cadena.charAt(i))){
				cadenaProcesada += cadena.charAt(i);
			}else{
				cadenaProcesada += cadena.charAt(i) + 127;
			}
		}
		return new String(cadenaProcesada.trim().concat("2900554932452843").substring(0,16));
	}
}

