/*
 * Creado el 3/11/2006
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import org.apache.struts.action.ActionForm;
/**
 * @author JOJEDAH
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class MarcasForm extends ActionForm{
	
	String pcDescMarca;
	String pcCveMarca;
	
	

	/**
	 * @return
	 */
	public String getPcDescMarca() {
		return pcDescMarca;
	}

	/**
	 * @param string
	 */
	public void setPcDescMarca(String string) {
		pcDescMarca = string;
	}

	/**
	 * @return
	 */
	public String getPcCveMarca() {
		return pcCveMarca;
	}

	/**
	 * @param string
	 */
	public void setPcCveMarca(String string) {
		pcCveMarca = string;
	}

}
