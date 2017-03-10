/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.vendedores.struts.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import java.io.Serializable;
import org.apache.struts.validator.ValidatorForm;
/**
 * @author Dvazqueza
 *
 */
public class VendedoresSeekForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;


	private String pcCveVendedor;      //Clave Vendedor
	private String pcCvePuesto;
	private String pcDescPuesto;		//String para la descripcion del Puesto seleccionado en la forma 
	private String pcCveSuperior; 
	private String pcNombreSuperior; 	//String para el nombre del Supervisor asignado
	private String pcNomVendedor; 
	private String pcApePaterno;
	private String pcApeMaterno; 
	private String pcFchAlta; 
	private String pcFchBaja; 
	private String pcDigVerif; 
	private String pcCntrTda; 
	private String pcStatus; 
	private String pcCveEmpRef; 
	private String pcCiudad; 

	private String pcCvePuestoSeek;		//String para almacenar el campo de Busqueda para Puesto
	private String pcCveVendedorSeek;	//String para almacenar el campo de Busqueda para Clave del Vendedor
	private String pcNomVendedorSeek;	//String para almacenar el campo de Busqueda para Nombre del Vendedor
		
	//Empty Constructor
	public VendedoresSeekForm() {
		setPcCveVendedor(null); 
		setPcCvePuesto(null);
		setPcCveSuperior(null); 
		setPcNomVendedor(null); 
		setPcApePaterno(null);
		setPcApeMaterno(null); 
		setPcFchAlta(null); 
		setPcFchBaja(null); 
		setPcDigVerif(null); 
		setPcCntrTda(null); 
		setPcStatus(null); 
		setPcCveEmpRef(null);
		setPcCiudad(null);
		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);
		setPcNomVendedorSeek(null);
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
	public String getPcCiudad() {
		return pcCiudad;
	}

	/**
	 * @return
	 */
	public String getPcCntrTda() {
		return pcCntrTda;
	}

	/**
	 * @return
	 */
	public String getPcCveEmpRef() {
		return pcCveEmpRef;
	}

	/**
	 * @return
	 */
	public String getPcCvePuesto() {
		return pcCvePuesto;
	}

	/**
	 * @return
	 */
	public String getPcCveSuperior() {
		return pcCveSuperior;
	}

	/**
	 * @return
	 */
	public String getPcCveVendedor() {
		return pcCveVendedor;
	}

	/**
	 * @return
	 */
	public String getPcDigVerif() {
		return pcDigVerif;
	}

	/**
	 * @return
	 */
	public String getPcFchAlta() {
		return pcFchAlta;
	}

	/**
	 * @return
	 */
	public String getPcFchBaja() {
		return pcFchBaja;
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
	public String getPcStatus() {
		return pcStatus;
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
	public void setPcCiudad(String string) {
		pcCiudad = string;
	}

	/**
	 * @param string
	 */
	public void setPcCntrTda(String string) {
		pcCntrTda = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveEmpRef(String string) {
		pcCveEmpRef = string;
	}

	/**
	 * @param string
	 */
	public void setPcCvePuesto(String string) {
		pcCvePuesto = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveSuperior(String string) {
		pcCveSuperior = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveVendedor(String string) {
		pcCveVendedor = string;
	}

	/**
	 * @param string
	 */
	public void setPcDigVerif(String string) {
		pcDigVerif = string;
	}

	/**
	 * @param string
	 */
	public void setPcFchAlta(String string) {
		pcFchAlta = string;
	}

	/**
	 * @param string
	 */
	public void setPcFchBaja(String string) {
		pcFchBaja = string;
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
	public void setPcStatus(String string) {
		pcStatus = string;
	}

	/**
	 * @return
	 */
	public String getPcDescPuesto() {
		return pcDescPuesto;
	}

	/**
	 * @return
	 */
	public String getPcNombreSuperior() {
		return pcNombreSuperior;
	}

	/**
	 * @param string
	 */
	public void setPcDescPuesto(String string) {
		pcDescPuesto = string;
	}

	/**
	 * @param string
	 */
	public void setPcNombreSuperior(String string) {
		pcNombreSuperior = string;
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
	 * @param string
	 */
	public void setPcCvePuestoSeek(String string) {
		pcCvePuestoSeek = string;
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
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCveVendedor(null); 
		setPcCvePuesto(null);
		setPcCveSuperior(null); 
		setPcNomVendedor(null); 
		setPcApePaterno(null);
		setPcApeMaterno(null); 
		setPcFchAlta(null); 
		setPcFchBaja(null); 
		setPcDigVerif(null); 
		setPcCntrTda(null); 
		setPcStatus(null); 
		setPcCveEmpRef(null);
		setPcCiudad(null);
		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);
		setPcNomVendedorSeek(null);
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
