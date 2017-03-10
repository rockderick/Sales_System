/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.asistentes.struts.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import java.io.Serializable;
import org.apache.struts.validator.ValidatorForm;
/**
 * @author Dvazqueza
 *
 */
public class AsistSuperiorForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;
	private String pagina;
	

	private String coordXHolder = null;
	private String coordYHolder = null;

	private int pcCveVendedor;      //Clave Vendedor
	private int pcCvePuesto;
	private String pcDescPuesto;		//String para la descripcion del Puesto seleccionado en la forma 
	private int pcCveSuperior; 
	private String pcNombreSuperior; 	//String para el nombre del Supervisor asignado
	private String pcNombreCompleto; 	//String para el nombre del Supervisor asignado

	private int pcCvePuestoSeek;		//String para almacenar el campo de Busqueda para Puesto
	private int pcCveVendedorSeek;	//String para almacenar el campo de Busqueda para Clave del Vendedor
	private String pcNomVendedorSeek;	//String para almacenar el campo de Busqueda para Nombre del Vendedor

	private int pcCveVendedorChg;	//String para almacenar el campo de Busqueda para Clave del Vendedor
	private String pcNombreCompletoChg; 	//String para el nombre del Supervisor asignado

	private int pcCveVendedorDefine;
	
	private int pcCveDiv;

	private int pcCveSuperiorActual; 
	private String pcNombreSuperiorActual; 	//String para el nombre del Supervisor asignado

	private int pcCvePuestoReasignar;
	private int PcCveSuperiorReasignar;
	private String pcNombreSuperiorReasignar; 	//String para el nombre del Supervisor asignado
	
	private String pcNomClaveEmpleadoReferenciaSeek; //String almacena el campo de Busqueda por Clave de Referncia de Empleado
	
	private String pcFchMovimiento;
	
	//Empty Constructor
	public AsistSuperiorForm() {
		setPcCvePuestoSeek(0);
		setPcCveVendedorSeek(0);
		setPcNomVendedorSeek(null);
		setPcCveVendedorChg(0);
		setCoordXHolder(null);
		setCoordYHolder(null);
		setPcCveVendedor(0); 
		setPcCvePuesto(0);
		setPcCveSuperior(0); 
		setPcCveVendedorDefine(0);
		setPcCveDiv(0);
		setPcNombreCompleto(null); 
		setPcCveSuperiorActual(0); 
		setPcNombreSuperiorActual(null); 
		setPcCvePuestoReasignar(0);
		setPcCveSuperiorReasignar(0); 
		setPcNombreSuperiorReasignar(null); 
		setPcFchMovimiento(null);
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		if(super.page==0){
			setPcCvePuestoSeek(0);
			setPcCveVendedorSeek(0);
			setPcNomVendedorSeek(null);
		}
		if(super.page==1){
			setPcCvePuestoSeek(0);
			setPcCveVendedorSeek(0);
			setPcNomVendedorSeek(null);
		}
		if(super.page==2){
			setPcCvePuestoSeek(0);
			setPcCveVendedorSeek(0);
			setPcNomVendedorSeek(null);
		}
		if(super.page==3){
			setPcFchMovimiento(null);
		}
		setCoordXHolder(null);
		setCoordYHolder(null);
	}

	

	/**
	 * @return
	 */
	public int getPcCvePuesto() {
		return pcCvePuesto;
	}

	/**
	 * @return
	 */
	public int getPcCveSuperior() {
		return pcCveSuperior;
	}

	/**
	 * @return
	 */
	public int getPcCveVendedor() {
		return pcCveVendedor;
	}


	/**
	 * @param value
	 */
	public void setPcCvePuesto(int value) {
		pcCvePuesto = value;
	}

	/**
	 * @param value
	 */
	public void setPcCveSuperior(int value) {
		pcCveSuperior = value;
	}

	/**
	 * @param value
	 */
	public void setPcCveVendedor(int value) {
		pcCveVendedor = value;
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
	public int getPcCvePuestoSeek() {
		return pcCvePuestoSeek;
	}

	/**
	 * @return
	 */
	public int getPcCveVendedorSeek() {
		return pcCveVendedorSeek;
	}

	/**
	 * @return
	 */
	public String getPcNomVendedorSeek() {
		return pcNomVendedorSeek;
	}

	/**
	 * @param value
	 */
	public void setPcCvePuestoSeek(int value) {
		pcCvePuestoSeek = value;
	}

	/**
	 * @param value
	 */
	public void setPcCveVendedorSeek(int value) {
		pcCveVendedorSeek = value;
	}

	/**
	 * @param string
	 */
	public void setPcNomVendedorSeek(String string) {
		pcNomVendedorSeek = string;
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
	public int getPcCveVendedorChg() {
		return pcCveVendedorChg;
	}

	/**
	 * @param string
	 */
	public void setPcCveVendedorChg(int value) {
		pcCveVendedorChg = value;
	}

	/**
	 * @return
	 */
	public String getCoordXHolder() {
		return coordXHolder;
	}

	/**
	 * @return
	 */
	public String getCoordYHolder() {
		return coordYHolder;
	}

	/**
	 * @param string
	 */
	public void setCoordXHolder(String string) {
		coordXHolder = string;
	}

	/**
	 * @param string
	 */
	public void setCoordYHolder(String string) {
		coordYHolder = string;
	}

	/**
	 * @param string
	 */
	public void setPagina(String string) {
		pagina = string;
	}

	/**
	 * @return
	 */
	public String getPagina() {
		return pagina;
	}

	/**
	 * @return
	 */
	public int getPcCveVendedorDefine() {
		return pcCveVendedorDefine;
	}

	/**
	 * @param i
	 */
	public void setPcCveVendedorDefine(int i) {
		pcCveVendedorDefine = i;
	}

	/**
	 * @return
	 */
	public int getPcCveDiv() {
		return pcCveDiv;
	}

	/**
	 * @param i
	 */
	public void setPcCveDiv(int i) {
		pcCveDiv = i;
	}

	/**
	 * @return
	 */
	public String getPcNombreCompleto() {
		return pcNombreCompleto;
	}

	/**
	 * @param string
	 */
	public void setPcNombreCompleto(String string) {
		pcNombreCompleto = string;
	}

	/**
	 * @return
	 */
	public String getPcNombreCompletoChg() {
		return pcNombreCompletoChg;
	}

	/**
	 * @param string
	 */
	public void setPcNombreCompletoChg(String string) {
		pcNombreCompletoChg = string;
	}

	/**
	 * @return
	 */
	public int getPcCveSuperiorActual() {
		return pcCveSuperiorActual;
	}

	/**
	 * @return
	 */
	public String getPcNombreSuperiorActual() {
		return pcNombreSuperiorActual;
	}

	/**
	 * @param i
	 */
	public void setPcCveSuperiorActual(int i) {
		pcCveSuperiorActual = i;
	}

	/**
	 * @param string
	 */
	public void setPcNombreSuperiorActual(String string) {
		pcNombreSuperiorActual = string;
	}

	/**
	 * @return
	 */
	public int getPcCveSuperiorReasignar() {
		return PcCveSuperiorReasignar;
	}

	/**
	 * @return
	 */
	public String getPcNombreSuperiorReasignar() {
		return pcNombreSuperiorReasignar;
	}

	/**
	 * @param i
	 */
	public void setPcCveSuperiorReasignar(int i) {
		PcCveSuperiorReasignar = i;
	}

	/**
	 * @param string
	 */
	public void setPcNombreSuperiorReasignar(String string) {
		pcNombreSuperiorReasignar = string;
	}

	/**
	 * @return
	 */
	public int getPcCvePuestoReasignar() {
		return pcCvePuestoReasignar;
	}

	/**
	 * @param i
	 */
	public void setPcCvePuestoReasignar(int i) {
		pcCvePuestoReasignar = i;
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
