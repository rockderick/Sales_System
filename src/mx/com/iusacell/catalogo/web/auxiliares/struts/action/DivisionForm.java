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
public class DivisionForm extends ValidatorForm implements Serializable{
	private String action = null;

	private String pcDescDiv;
	private int pcCveDiv;
	private String boton;
	
	
	//Empty Constructor
	public DivisionForm() {
		setPcDescDiv(null);
		setPcCveDiv(0);
	}
	


	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcDescDiv(null);
		setPcCveDiv(0);	
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
	public int getPcCveDiv() {
		return pcCveDiv;
	}

	/**
	 * @return
	 */
	public String getPcDescDiv() {
		return pcDescDiv;
	}

	/**
	 * @param i
	 */
	public void setPcCveDiv(int i) {
		pcCveDiv = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescDiv(String string) {
		pcDescDiv = string;
	}

}
