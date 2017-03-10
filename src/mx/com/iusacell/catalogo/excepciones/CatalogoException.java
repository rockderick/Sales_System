/*
 * Created on 17/12/2004
 *
 */
package mx.com.iusacell.catalogo.excepciones;

import javax.ejb.EJBException;
/**
 * @author EHERNANDEZH
 *
 */
public class CatalogoException extends EJBException {

	/**
	 * Constructor que sirve como puente hacia el constructor
	 * de EJBException
	 * 
	 * @param mensaje Mensaje que describe el error
	 * @param error Excepción que provocó este error
	 */
	public CatalogoException(String mensaje, Exception error) {
//		super(Fecha.dateToString(new Date()) + ": " +  mensaje, error);
		
		super(mensaje, error);
		
	}

}