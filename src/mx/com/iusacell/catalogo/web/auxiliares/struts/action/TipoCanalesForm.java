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
public class TipoCanalesForm extends ValidatorForm implements Serializable{
	private String action = null;

	private String pcCveTpCanal;
	private String pcDescTpCanal;
	
	private String boton;
	
	

	//Empty Constructor
	public TipoCanalesForm() {
		setPcCveTpCanal(null);
		setPcDescTpCanal(null);
	}

	/**
	 * @return
	 */
	public String getPcCveTpCanal() {
		return pcCveTpCanal;
	}

	/**
	 * @return
	 */
	public String getPcDescTpCanal() {
		return pcDescTpCanal;
	}

	/**
	 * @param string
	 */
	public void setPcCveTpCanal(String string) {
		pcCveTpCanal = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescTpCanal(String string) {
		pcDescTpCanal = string;
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
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCveTpCanal(null);
		setPcDescTpCanal(null);	
	}
}
