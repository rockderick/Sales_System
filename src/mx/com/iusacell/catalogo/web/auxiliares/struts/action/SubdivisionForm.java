/*
 * Created on Feb 25, 2005
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
public class SubdivisionForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;

	private String pcCveSubdiv;
	private String pcDescSubdiv;
	private String pcCveDiv;
	private String pcCveDivision;
	
	//Empty Constructor
	public SubdivisionForm() {
		setPcCveSubdiv(null);
		setPcDescSubdiv(null);
		setPcCveDiv(null);
		setPcCveDivision(null);
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCveSubdiv(null);
		setPcDescSubdiv(null);
		setPcCveDiv(null);
		setPcCveDivision(null);
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
	public String getPcCveDiv() {
		return pcCveDiv;
	}

	/**
	 * @return
	 */
	public String getPcCveDivision() {
		return pcCveDivision;
	}

	/**
	 * @return
	 */
	public String getPcCveSubdiv() {
		return pcCveSubdiv;
	}

	/**
	 * @return
	 */
	public String getPcDescSubdiv() {
		return pcDescSubdiv;
	}

	/**
	 * @param string
	 */
	public void setPcCveDiv(String string) {
		pcCveDiv = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveDivision(String string) {
		pcCveDivision = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveSubdiv(String string) {
		pcCveSubdiv = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescSubdiv(String string) {
		pcDescSubdiv = string;
	}

}
