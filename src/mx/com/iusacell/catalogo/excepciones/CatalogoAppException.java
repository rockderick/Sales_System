/*
 * Created on 17/12/2004
 *
 */
package mx.com.iusacell.catalogo.excepciones;

/**
 * @author EHERNANDEZH
 *
 */
public class CatalogoAppException extends CatalogoException {

	/**
	 * Constructor que indica un mensaje descriptivo
	 * @param mensaje Mensaje que describe el error
	 */
	public CatalogoAppException(String mensaje){
		super(mensaje, null);
	}

	/**
	 * Constructor que indica un mensaje descriptivo y la excepcion original
	 * @param mensaje Mensaje que describe el error
	 * @param error Excepción que provocó este error
	 */
	public CatalogoAppException(String mensaje, Exception error){
		super(mensaje, error);
	}
	
}

