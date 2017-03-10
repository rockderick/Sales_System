/*
 * Created on 17/12/2004
 *
 */
package mx.com.iusacell.catalogo.excepciones;

/**
 * @author EHERNANDEZH
 *
 */
import mx.com.iusacell.catalogo.excepciones.CatalogoException;


public class CatalogoSystemException extends CatalogoException {

	/**
	 * Constructor que indica un mensaje descriptivo y la excepcion original
	 * @param mensaje Mensaje que describe el error
	 * @param error Excepción que provocó este error
	 */
	public CatalogoSystemException(String mensaje, Exception error){
		super(mensaje, error);
	}
	
}