/*
 * Creado el Jun 1, 2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;
		

import org.apache.struts.action.ActionForm;

/**
 * @author jojedah
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generaci�n de c�digo&gt;C�digo y comentarios
 */
public class ChecadorForm extends ActionForm {

	String fechaInicial;
	String fechaFinal;
	
	/**
	 * @return
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * @return
	 */
	public String getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * @param string
	 */
	public void setFechaFinal(String string) {
		fechaFinal = string;
	}

	/**
	 * @param string
	 */
	public void setFechaInicial(String string) {
		fechaInicial = string;
	}

}
