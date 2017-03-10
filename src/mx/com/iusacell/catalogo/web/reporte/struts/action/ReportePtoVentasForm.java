/*
 * Created on Nov 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import java.io.Serializable;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReportePtoVentasForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;
	
	private String pcCveDivisionSeek;
	private String pcDescDivisionSeek; 
	private String pcCveVendedorSeek;
	private String pcNomClaveEmpleadoReferenciaSeek;
	private String pcFchHistorico;

	//constructor
	public ReportePtoVentasForm() {
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
	 * @return
	 */
	public String getPcDescDivisionSeek() {
		return pcDescDivisionSeek;
	}

	/**
	 * @return
	 */
	public String getPcFchHistorico() {
		return pcFchHistorico;
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
	 * @param string
	 */
	public void setPcDescDivisionSeek(String string) {
		pcDescDivisionSeek = string;
	}

	/**
	 * @param string
	 */
	public void setPcFchHistorico(String string) {
		pcFchHistorico = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomClaveEmpleadoReferenciaSeek(String string) {
		pcNomClaveEmpleadoReferenciaSeek = string;
	}

}
