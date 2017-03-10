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
public class ReporteMovimientoForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;

	private String pcCvePuestoSeek;		//String para almacenar el campo de Busqueda para Puesto
	private String pcCveVendedorSeek;	//String para almacenar el campo de Busqueda para Clave del Vendedor
	private String pcNomVendedorSeek;	//String para almacenar el campo de Busqueda para Nombre del Vendedor
	private String pcNomClaveEmpleadoReferenciaSeek;
	private String pcCveVendedorRadio;
	
	private String rangoFchDesde;
	private String rangoFchHasta;
	
	
	//Empty Constructor
	public ReporteMovimientoForm() {
		setPcCveVendedorSeek(null);

		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);	
		setPcNomVendedorSeek(null);	

		setPcCveVendedorRadio(null);
	
		setRangoFchDesde(null);
		setRangoFchHasta(null);
	}
	

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCveVendedorSeek(null);

		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);	
		setPcNomVendedorSeek(null);	

		setPcCveVendedorRadio(null);
	
		setRangoFchDesde(null);
		setRangoFchHasta(null);
	}

	/**
	 * @return
	 */
	public String getPcCvePuestoSeek() {
		return pcCvePuestoSeek;
	}

	/**
	 * @return
	 */
	public String getPcCveVendedorRadio() {
		return pcCveVendedorRadio;
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
	public String getPcNomVendedorSeek() {
		return pcNomVendedorSeek;
	}

	/**
	 * @return
	 */
	public String getRangoFchDesde() {
		return rangoFchDesde;
	}

	/**
	 * @return
	 */
	public String getRangoFchHasta() {
		return rangoFchHasta;
	}

	/**
	 * @param string
	 */
	public void setPcCvePuestoSeek(String string) {
		pcCvePuestoSeek = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveVendedorRadio(String string) {
		pcCveVendedorRadio = string;
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
	public void setPcNomVendedorSeek(String string) {
		pcNomVendedorSeek = string;
	}

	/**
	 * @param string
	 */
	public void setRangoFchDesde(String string) {
		rangoFchDesde = string;
	}

	/**
	 * @param string
	 */
	public void setRangoFchHasta(String string) {
		rangoFchHasta = string;
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
	public void setPcNomClaveEmpleadoReferenciaSeek(String string) {
		pcNomClaveEmpleadoReferenciaSeek = string;
	}

}
