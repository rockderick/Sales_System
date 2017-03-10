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
public class RegionesForm extends ValidatorForm implements Serializable{
	private String action = null;

	private String pcDescRegion;
	private String pcCveRegion;
	private String boton;
	
	
	//Empty Constructor
	public RegionesForm() {
		setPcDescRegion(null);
		setPcCveRegion(null);
	}
	

	/**
	 * @return
	 */
	public String getPcDescRegion() {
		return pcDescRegion;
	}

	/**
	 * @param string
	 */
	public void setPcDescRegion(String string) {
		pcDescRegion = string;
	}

	/**
	 * @return
	 */
	public String getPcCveRegion() {
		return pcCveRegion;
	}

	/**
	 * @param string
	 */
	public void setPcCveRegion(String string) {
		pcCveRegion = string;
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcDescRegion(null);
		setPcCveRegion(null);	
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

}
