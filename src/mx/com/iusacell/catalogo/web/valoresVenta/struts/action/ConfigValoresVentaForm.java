/*
 * Tipo de archivo: Clase
 * Compañía:        <p>Iusacell</p>
 * Nombre:          mx.com.iusacell.catalogo.web.valoresVenta.struts.action.ConfigValoresVentaForm
 * Descripción:     <p></p>
 * 
 * Fecha:           130202
 * Autor:           CAL
 * Versión:         1.0
 * 
 */
package mx.com.iusacell.catalogo.web.valoresVenta.struts.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/**
 * Compañía:    <p>Iusacell</p>
 * Nombre:      ConfigValoresVentaForm.java
 * Descripción: <p></p>
 * 
 * Fecha:       130202
 * @author      CAL
 * @version     1.0
 */
public class ConfigValoresVentaForm extends ValidatorForm implements Serializable{
	
	private String action;
	private String boton;
	
	private int    cl_Configuracion;
	private	String ds_Configuracion;
	private String ds_Valor;
	private String ds_Command;
   
   /**
    * 
    *
    */
	public ConfigValoresVentaForm(){
		action = null;
		setCl_Configuracion(0);
		setDs_Configuracion(null);
		setDs_Valor(null);
	}
	
/**
 * Reset all properties to their default values.
 *
 * @param mapping The mapping used to select this instance
 * @param request The servlet request we are processing
 */
public void reset(ActionMapping mapping, HttpServletRequest request) {
	action = null;
	setCl_Configuracion(0);
	setDs_Configuracion(null);
	setDs_Valor(null);
	setDs_Command(null);	
}    

	/**
	 * @return
	 */
	public int getCl_Configuracion() {
		return cl_Configuracion;
	}

	/**
	 * @return
	 */
	public String getDs_Configuracion() {
		return ds_Configuracion;
	}

	/**
	 * @return
	 */
	public String getDs_Valor() {
		return ds_Valor;
	}

	/**
	 * @param i
	 */
	public void setCl_Configuracion(int i) {
		cl_Configuracion = i;
	}

	/**
	 * @param string
	 */
	public void setDs_Configuracion(String string) {
		ds_Configuracion = string;
	}

	/**
	 * @param string
	 */
	public void setDs_Valor(String string) {
		ds_Valor = string;
	}

	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return
	 */
	public String getBoton() {
		return boton;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
	}

	/**
	 * @param string
	 */
	public void setBoton(String string) {
		boton = string;
	}

	/**
	 * @return
	 */
	public String getDs_Command() {
		return ds_Command;
	}

	/**
	 * @param string
	 */
	public void setDs_Command(String string) {
		ds_Command = string;
	}

}//Fin de la clase ConfigValoresVentaForm***********
