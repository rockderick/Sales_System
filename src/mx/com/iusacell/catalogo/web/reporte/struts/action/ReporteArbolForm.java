/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import java.io.Serializable;
/**
 * @author Dvazqueza
 *
 */
public class ReporteArbolForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;

	private String pcCveDivisionSeek;
	private String pcDescDivisionSeek; 
	private String pcCveVendedorSeek;
	private String pcNomClaveEmpleadoReferenciaSeek;
	private String pcFchHistorico;
	
	
	//Empty Constructor
	public ReporteArbolForm() {
		setPcCveDivisionSeek(null); 
		setPcCveVendedorSeek(null);
		setPcDescDivisionSeek(null);
		setPcFchHistorico(null);
	}
	

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCveDivisionSeek(null); 
		setPcCveVendedorSeek(null);
		setPcDescDivisionSeek(null);
		setPcFchHistorico(null);
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
	 * @return
	 */
	public String getPcCveDivisionSeek() {
		return pcCveDivisionSeek;
	}

	/**
	 * @return
	 */
	public String getPcCveVendedorSeek() {
		return pcCveVendedorSeek;
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
	 * @param string
	 */
	public void setPcCveDivisionSeek(String string) {
		pcCveDivisionSeek = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveVendedorSeek(String string) {
		pcCveVendedorSeek = string;
	}

	/**
	 * @return
	 */
	public String getPcDescDivisionSeek() {
		return pcDescDivisionSeek;
	}

	/**
	 * @param string
	 */
	public void setPcDescDivisionSeek(String string) {
		pcDescDivisionSeek = string;
	}

	/**
	 * @return
	 */
	public String getPcNomClaveEmpleadoReferenciaSeek() {
		return pcNomClaveEmpleadoReferenciaSeek;
	}

	/**
	 * @param string
	 */
	public void setPcNomClaveEmpleadoReferenciaSeek(String string) {
		pcNomClaveEmpleadoReferenciaSeek = string;
	}
	
	/**
	 * @return
	 */
	public String getPcFchHistorico() {
		return pcFchHistorico;
	}

	/**
	 * @param string
	 */
	public void setPcFchHistorico(String string) {
		pcFchHistorico = string;
	}
}
