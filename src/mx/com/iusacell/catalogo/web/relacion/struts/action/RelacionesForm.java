/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.relacion.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import java.io.Serializable;

/**
 * @author Dvazqueza
 *
 */
public class RelacionesForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;
	private String ultimaBusqueda = null;


	private String pcCveInter;
	private String[] pcCveVendedor;
	private String[] pcCvePtoventas;
	private String pcOrigenPtoventas;
	private String pcNomPtoventas;
	private String pcCveCanal;
	private String pcDescCanal;
	private String pcNomVendedor;
	private String pcApePaterno;
	private String pcApeMaterno;

	private String pcCvePuestoSeek;
	private String pcCveVendedorSeek;
	private String pcNomVendedorSeek;
	private String pcNomClaveEmpleadoReferenciaSeek; //String almacena el campo de Busqueda por Clave de Referncia de Empleado
	
	private String pcCveDivisionSeek;
	private String pcCveTipoCanalSeek;
	private String pcCveCanalSeek;
	private String pcCvePtoventasSeek;
	private String pcNomPtoventasSeek;
	
	private String pcFchMovimiento;	
	private String pcFchBaja;	
	
	//Empty Constructor
	public RelacionesForm() {
		setPcCveInter(null);
		setPcCveVendedor(null);
		setPcCvePtoventas(null);
		setPcOrigenPtoventas(null);
		setPcNomPtoventas(null);
		setPcCveCanal(null);
		setPcDescCanal(null);
		setPcNomVendedor(null);
		setPcApePaterno(null);
		setPcApeMaterno(null);
		
		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);
		setPcNomVendedorSeek(null);
		setPcNomClaveEmpleadoReferenciaSeek(null);

		setPcCveDivisionSeek(null);
		setPcCveTipoCanalSeek(null);
		setPcCveCanalSeek(null);
		setPcCvePtoventasSeek(null);
		setPcNomPtoventasSeek(null);
		
		setPcFchMovimiento(null);
		setPcFchBaja(null);
	}
	

	/**
	 * @return
	 */
	public String getPcApeMaterno() {
		return pcApeMaterno;
	}

	/**
	 * @return
	 */
	public String getPcApePaterno() {
		return pcApePaterno;
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
	public String getPcCveInter() {
		return pcCveInter;
	}

	/**
	 * @return
	 */
	public String[] getPcCvePtoventas() {
		return pcCvePtoventas;
	}

	/**
	 * @return
	 */
	public String[] getPcCveVendedor() {
		return pcCveVendedor;
	}

	/**
	 * @return
	 */
	public String getPcDescCanal() {
		return pcDescCanal;
	}

	/**
	 * @return
	 */
	public String getPcNomPtoventas() {
		return pcNomPtoventas;
	}

	/**
	 * @return
	 */
	public String getPcNomVendedor() {
		return pcNomVendedor;
	}

	/**
	 * @return
	 */
	public String getPcOrigenPtoventas() {
		return pcOrigenPtoventas;
	}

	/**
	 * @param string
	 */
	public void setPcApeMaterno(String string) {
		pcApeMaterno = string;
	}

	/**
	 * @param string
	 */
	public void setPcApePaterno(String string) {
		pcApePaterno = string;
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
	public void setPcCveInter(String string) {
		pcCveInter = string;
	}

	/**
	 * @param strings
	 */
	public void setPcCvePtoventas(String[] strings) {
		pcCvePtoventas = strings;
	}

	/**
	 * @param strings
	 */
	public void setPcCveVendedor(String[] strings) {
		pcCveVendedor = strings;
	}

	/**
	 * @param string
	 */
	public void setPcDescCanal(String string) {
		pcDescCanal = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomPtoventas(String string) {
		pcNomPtoventas = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomVendedor(String string) {
		pcNomVendedor = string;
	}

	/**
	 * @param string
	 */
	public void setPcOrigenPtoventas(String string) {
		pcOrigenPtoventas = string;
	}

	/**
	 * @return
	 */
	public String getPcCveCanalSeek() {
		return pcCveCanalSeek;
	}


	/**
	 * @return
	 */
	public String getPcCvePtoventasSeek() {
		return pcCvePtoventasSeek;
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
	public String getPcCveDivisionSeek() {
		return pcCveDivisionSeek;
	}

	/**
	 * @return
	 */
	public String getPcCveTipoCanalSeek() {
		return pcCveTipoCanalSeek;
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
	public String getPcNomPtoventasSeek() {
		return pcNomPtoventasSeek;
	}

	/**
	 * @return
	 */
	public String getPcNomVendedorSeek() {
		return pcNomVendedorSeek;
	}

	/**
	 * @param string
	 */
	public void setPcCveCanalSeek(String string) {
		pcCveCanalSeek = string;
	}

	/**
	 * @param string
	 */
	public void setPcCvePtoventasSeek(String string) {
		pcCvePtoventasSeek = string;
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
	public void setPcCveDivisionSeek(String string) {
		pcCveDivisionSeek = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveTipoCanalSeek(String string) {
		pcCveTipoCanalSeek = string;
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
	public void setPcNomPtoventasSeek(String string) {
		pcNomPtoventasSeek = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomVendedorSeek(String string) {
		pcNomVendedorSeek = string;
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCveInter(null);
		setPcCveVendedor(null);
		setPcCvePtoventas(null);
		setPcOrigenPtoventas(null);
		setPcNomPtoventas(null);
		setPcCveCanal(null);
		setPcDescCanal(null);
		setPcNomVendedor(null);
		setPcApePaterno(null);
		setPcApeMaterno(null);
		
		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);
		setPcNomVendedorSeek(null);
		setPcNomClaveEmpleadoReferenciaSeek(null);

		setPcCveDivisionSeek(null);
		setPcCveTipoCanalSeek(null);
		setPcCveCanalSeek(null);
		setPcCvePtoventasSeek(null);
		setPcNomPtoventasSeek(null);
		
		setPcFchMovimiento(null);
		setPcFchBaja(null);	
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
	public String getPcFchMovimiento() {
		return pcFchMovimiento;
	}

	/**
	 * @param string
	 */
	public void setPcFchMovimiento(String string) {
		pcFchMovimiento = string;
	}

	/**
	 * @return
	 */
	public String getPcFchBaja() {
		return pcFchBaja;
	}

	/**
	 * @param string
	 */
	public void setPcFchBaja(String string) {
		pcFchBaja = string;
	}

	/**
	 * @return
	 */
	public String getUltimaBusqueda() {
		return ultimaBusqueda;
	}

	/**
	 * @param string
	 */
	public void setUltimaBusqueda(String string) {
		ultimaBusqueda = string;
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

}
