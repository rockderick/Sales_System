/*
 * Created on 22/06/2005
 *
 */
package mx.com.iusacell.catalogo.excepciones;

/**
 * @author DVAZQUEZA
 *
 */
public class CatalogoFlowException extends Exception {

	/**
	 * Constructor que indica un mensaje descriptivo
	 * @param mensaje Mensaje que describe el error
	 */
	public CatalogoFlowException(String mensaje){
		super(mensaje);
	}
}

