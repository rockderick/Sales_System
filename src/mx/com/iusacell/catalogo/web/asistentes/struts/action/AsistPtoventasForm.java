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
public class AsistPtoventasForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;
	private String pagina;
	

	private String coordXHolder = null;
	private String coordYHolder = null;

	private int pcCveVendedor;      //Clave Vendedor
	private int pcCvePuesto;
	private String pcDescPuesto;		//String para la descripcion del Puesto seleccionado en la forma 
	private String pcNomVendedor; 
	private String pcApePaterno;
	private String pcApeMaterno; 
	private String pcDigVerif; 
	private String pcStatus; 
	private String pcCveEmpRef; 

	private int pcCvePuestoSeek;		//String para almacenar el campo de Busqueda para Puesto
	private int pcCveVendedorSeek;	//String para almacenar el campo de Busqueda para Clave del Vendedor
	private String pcNomVendedorSeek;	//String para almacenar el campo de Busqueda para Nombre del Vendedor
	private String pcNomClaveEmpleadoReferenciaSeek;

	private String pcCveTipoCanalSeek; 
	private String pcCveCanalSeek; 
	private String pcCvePtoventasSeek;
	private int pcCveDivSeek;
	private String pcNomPtoventasSeek;
	
	private String pcCvePtoventas;
	private String pcNomPtoventas;
	private String pcNombreCompleto;

	private int pcCveVendedorChg;	
	private String pcNombreCompletoChg;
	
	private int pcCveDiv;
	private int pcCveSubdiv;
	
	private String pcFchMovimiento;
		
	//Empty Constructor
	public AsistPtoventasForm() {
		setPcCveVendedor(0); 
		setPcCvePuesto(0);
		setPcNomVendedor(null); 
		setPcApePaterno(null);
		setPcApeMaterno(null); 
		setPcDigVerif(null); 
		setPcStatus(null); 
		setPcCveEmpRef(null);
		setPcCvePuestoSeek(0);
		setPcCveVendedorSeek(0);
		setPcNomVendedorSeek(null);
		setPcCveVendedorChg(0);
		setCoordXHolder(null);
		setCoordYHolder(null);
		setPcCveTipoCanalSeek(null); 
		setPcCveCanalSeek(null); 
		setPcCvePtoventasSeek(null);
		setPcCveDivSeek(0);
		setPcNomPtoventasSeek(null);
		setPcCvePtoventas(null);
		setPcNomPtoventas(null);
		setPcNombreCompleto(null);
		setPcCveDiv(0);
		setPcCveSubdiv(0);
		setPcFchMovimiento(null);
		setPcNomClaveEmpleadoReferenciaSeek(null);
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
			setPcCveTipoCanalSeek(null); 
			setPcCveCanalSeek(null); 
			setPcCvePtoventasSeek(null);
			setPcCveDivSeek(0);
			setPcNomPtoventasSeek(null);
		}
		if(super.page==1){
			setPcCvePtoventas(null);
			setPcNomPtoventas(null);
			setPcCveDiv(0);
			setPcCveSubdiv(0);
		}
		if(super.page==2){
			setPcCveVendedor(0); 
			setPcCvePuesto(0);
			setPcNomClaveEmpleadoReferenciaSeek(null);
		}
		if(super.page==3){
			setPcNomVendedor(null); 
			setPcApePaterno(null);
			setPcApeMaterno(null); 
			setPcDigVerif(null); 
			setPcStatus(null); 
			setPcCveEmpRef(null);
		}
		setCoordXHolder(null);
		setCoordYHolder(null);
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
	public String getPcCveEmpRef() {
		return pcCveEmpRef;
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
	public int getPcCveVendedor() {
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
	public void setPcCveEmpRef(String string) {
		pcCveEmpRef = string;
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
	public void setPcCveVendedor(int value) {
		pcCveVendedor = value;
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
	 * @param string
	 */
	public void setPcDescPuesto(String string) {
		pcDescPuesto = string;
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
	 * @return
	 */
	public String getPcCveCanalSeek() {
		return pcCveCanalSeek;
	}

	/**
	 * @return
	 */
	public int getPcCveDivSeek() {
		return pcCveDivSeek;
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
	public String getPcCveTipoCanalSeek() {
		return pcCveTipoCanalSeek;
	}

	/**
	 * @return
	 */
	public String getPcNomPtoventasSeek() {
		return pcNomPtoventasSeek;
	}

	/**
	 * @param string
	 */
	public void setPcCveCanalSeek(String string) {
		pcCveCanalSeek = string;
	}

	/**
	 * @param i
	 */
	public void setPcCveDivSeek(int i) {
		pcCveDivSeek = i;
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
	public void setPcCveTipoCanalSeek(String string) {
		pcCveTipoCanalSeek = string;
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
	public String getPcCvePtoventas() {
		return pcCvePtoventas;
	}

	/**
	 * @param string
	 */
	public void setPcCvePtoventas(String string) {
		pcCvePtoventas = string;
	}

	/**
	 * @return
	 */
	public String getPcNomPtoventas() {
		return pcNomPtoventas;
	}

	/**
	 * @param string
	 */
	public void setPcNomPtoventas(String string) {
		pcNomPtoventas = string;
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
	public int getPcCveSubdiv() {
		return pcCveSubdiv;
	}

	/**
	 * @param i
	 */
	public void setPcCveSubdiv(int i) {
		pcCveSubdiv = i;
	}
	
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
