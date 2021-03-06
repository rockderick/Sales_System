/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import java.io.Serializable;
/**
 * @author Dvazqueza
 *
 */
public class PuestosArbolForm extends ValidatorForm implements Serializable{
	
	private String action = null;
	private String boton;


	private String pcDescPuestos;
	private int pcCvePuestos;
	private String pcDescPuestosSup;
	private int pcCvePuestosSup;

	//Empty Constructor
	public PuestosArbolForm() {
		setPcDescPuestos(null);
		setPcCvePuestos(0);
		setPcDescPuestosSup(null);
		setPcCvePuestosSup(0);
	}
	

	/**
	 * @return
	 */
	public String getPcDescPuestos() {
		return pcDescPuestos;
	}

	/**
	 * @param string
	 */
	public void setPcDescPuestos(String string) {
		pcDescPuestos = string;
	}

	/**
	 * @return
	 */
	public int getPcCvePuestos() {
		return pcCvePuestos;
	}

	/**
	 * @param value
	 */
	public void setPcCvePuestos(int value) {
		pcCvePuestos = value;
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcDescPuestos(null);
		setPcCvePuestos(0);	
		setPcDescPuestosSup(null);
		setPcCvePuestosSup(0);	
	}
	/**
	 * @return
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
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
	public void setBoton(String string) {
		boton = string;
	}

	/**
	 * @return
	 */
	public int getPcCvePuestosSup() {
		return pcCvePuestosSup;
	}

	/**
	 * @return
	 */
	public String getPcDescPuestosSup() {
		return pcDescPuestosSup;
	}

	/**
	 * @param i
	 */
	public void setPcCvePuestosSup(int i) {
		pcCvePuestosSup = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescPuestosSup(String string) {
		pcDescPuestosSup = string;
	}

}
