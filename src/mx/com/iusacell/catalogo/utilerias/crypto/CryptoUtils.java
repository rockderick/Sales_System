
package mx.com.iusacell.catalogo.utilerias.crypto;


import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;



/**
 * This is a auxilary class to encrypt using AES algorithm
 * using CBC operating mode and PkCS5 for padding
 * 
 * @author rj148m
 * @version: 2/12/2016/A
 * 
 */
public class CryptoUtils {
	
	
	private final static String alg = "AES";
	private final static String cI = "AES/CBC/PKCS5Padding";
	 

	 /**
	 * Returns encrypted text as a String encoded usig BASE64 algorithm 
	 * 
	 * @param plainText  the plaintext to encrypt
	 * @param KeyValue	 the key that is used to encrypt
	 * @return			 the encrypted text as a String 	
	 * @throws Exception
	 * @throws NoSuchPaddingException
	 */
	public static String encrypt(String plainText, byte[] KeyValue) throws Exception,
		NoSuchPaddingException {
		 Cipher cipher = Cipher.getInstance(cI);
		 IvParameterSpec ivSpec = new IvParameterSpec(KeyValue);
		 SecretKeySpec keySpec = new SecretKeySpec(KeyValue, alg);
		 cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
		 byte[] encrypted = cipher.doFinal(plainText.getBytes());
		 String encryptedValue = new BASE64Encoder().encode(encrypted);
		
		 return encryptedValue;
	 }



	
}
