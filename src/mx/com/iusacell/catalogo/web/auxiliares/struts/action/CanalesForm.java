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
public class CanalesForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;

	private String pcCveCanal;
	private String pcDescCanal;
	private String pcCveTpCanal;
	private String pcCveTipoCanal;
	private String pcFecha;
	private String pcGenerar;
	private String pcCveOta;
	
	//Empty Constructor
	public CanalesForm() {
		setPcCveCanal(null);
		setPcDescCanal(null);
		setPcCveTpCanal(null);
		setPcCveTipoCanal(null);
		setPcCveOta(null);
	}
	


	/**
	 * @return
	 */
	public String getPcCveCanal() {
		return pcCveCanal;
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
	public String getPcDescCanal() {
		return pcDescCanal;
	}

	/**
	 * @param string
	 */
	public void setPcCveCanal(String string) {
		pcCveCanal = string;
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
	public void setPcDescCanal(String string) {
		pcDescCanal = string;
	}

	/**
	 * @return
	 */
	public String getPcCveTipoCanal() {
		return pcCveTipoCanal;
	}

	/**
	 * @param string
	 */
	public void setPcCveTipoCanal(String string) {
		pcCveTipoCanal = string;
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCveCanal(null);
		setPcDescCanal(null);
		setPcCveTpCanal(null);
		setPcCveTipoCanal(null);
		setPcCveOta(null);	
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
	public String getPcFecha() {
		return pcFecha;
	}

	/**
	 * @param string
	 */
	public void setPcFecha(String string) {
		pcFecha = string;
	}

	/**
	 * @return
	 */
	public String getPcGenerar() {
		return pcGenerar;
	}

	/**
	 * @param string
	 */
	public void setPcGenerar(String string) {
		pcGenerar = string;
	}

	/**
	 * @return
	 */
	public String getPcCveOta() {
		return pcCveOta;
	}

	/**
	 * @param string
	 */
	public void setPcCveOta(String string) {
		pcCveOta = string;
	}

}
