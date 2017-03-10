/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.personal.struts.action;


import javax.servlet.http.HttpServletRequest;


import org.apache.struts.action.ActionMapping;
import java.io.Serializable;
import org.apache.struts.validator.ValidatorForm;
/**
 * @author Dvazqueza
 *
 */
public class PersonalForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;

	private String coordXHolder = null;
	private String coordYHolder = null;

	private int pcCveVendedor;      //Clave Vendedor
	private int pcCvePuesto;
	private String pcDescPuesto;		//String para la descripcion del Puesto seleccionado en la forma 
	private int pcCveSuperior; 
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
	private String pcEstado;
	private String pcDireccion;
	private String pcColonia;
	private String pcCp;
	private String pcTel;
	private String pcFax;
	private String pcCveEmpresa;

	private String pcTipoRegistro;
	private String pcCvePuestoSeek;		//String para almacenar el campo de Busqueda para Puesto
	private String pcCveVendedorSeek;	//String para almacenar el campo de Busqueda para Clave del Vendedor
	private String pcNomVendedorSeek;	//String para almacenar el campo de Busqueda para Nombre del Vendedor
	private String pcNomClaveEmpleadoReferenciaSeek; //String almacena el campo de Busqueda por Clave de Referncia de Empleado

	private int pcCveVendedorChg;	//String para almacenar el campo de Busqueda para Clave del Vendedor
	private int pcCveVendedorDefine;

	private int pcCveSupCad; 
	private String pcNombreSupCad;
	private int pcCveSuperiorSel;

	private String pcFchCambio; 
	
	private String pcCveSubdiv;
	
	private String pcCveHorario;
	private String pcDescHorario;
			
	//Empty Constructor
	public PersonalForm() {
		setPcCveVendedor(0); 
		setPcCvePuesto(0);
		setPcCveSuperior(0); 
		setPcNomVendedor(null); 
		setPcApePaterno(null);
		setPcApeMaterno(null); 
		setPcFchAlta(null); 
		setPcFchBaja(null); 
		setPcFchCambio(null); 
		setPcDigVerif(null); 
		setPcCntrTda(null); 
		setPcStatus(null); 
		setPcCveEmpRef(null);
		setPcCiudad(null);
		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);
		setPcNomVendedorSeek(null);
		setPcNomClaveEmpleadoReferenciaSeek(null);
		setPcEstado(null);
		setPcDireccion(null);
		setPcColonia(null);
		setPcCp(null);
		setPcTel(null);
		setPcFax(null);
		setPcTipoRegistro(null);
		setPcCveVendedorChg(0);
		setCoordXHolder(null);
		setCoordYHolder(null);
		setPcCveVendedorDefine(0);
		setPcCveSupCad(0); 
		setPcNombreSupCad(null);
		setPcCveSuperiorSel(0);
		setPcCveSubdiv(null);
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCveVendedor(0); 
		setPcCvePuesto(0);
		setPcCveSuperior(0); 
		setPcNomVendedor(null); 
		setPcApePaterno(null);
		setPcApeMaterno(null); 
		setPcFchAlta(null); 
		setPcFchBaja(null); 
		setPcFchCambio(null); 
		setPcDigVerif(null); 
		setPcCntrTda(null); 
		setPcStatus(null); 
		setPcCveEmpRef(null);
		setPcCiudad(null);
		setPcEstado(null);
		setPcDireccion(null);
		setPcColonia(null);
		setPcCp(null);
		setPcTel(null);
		setPcFax(null);
		setPcTipoRegistro(null);
		setPcCveVendedorChg(0);
		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);
		setPcNomVendedorSeek(null);
		setPcNomClaveEmpleadoReferenciaSeek(null);
		setPcTipoRegistro(null);
		setPcCveVendedorChg(0);
		setCoordXHolder(null);
		setCoordYHolder(null);
		setPcCveVendedorDefine(0);
		setPcCveSupCad(0); 
		setPcNombreSupCad(null);
		setPcCveSuperiorSel(0);
		setPcCveSubdiv(null);
//		if (mapping.getAttribute() != null) {
//			if ("request".equals(mapping.getScope())) {
//				request.removeAttribute(mapping.getAttribute());
//			} else {
//			   HttpSession session = request.getSession();
//			   session.removeAttribute(mapping.getAttribute());
//			}
//		}
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
	

	public String getPcNomClaveEmpleadoReferenciaSeek(){
				return pcNomClaveEmpleadoReferenciaSeek;
		}
	
	/**
		 * @param string
		 */
	public void setPcNomClaveEmpleadoReferenciaSeek(String string) {
		pcNomClaveEmpleadoReferenciaSeek = string;
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
	public String getPcColonia() {
		return pcColonia;
	}

	/**
	 * @return
	 */
	public String getPcCp() {
		return pcCp;
	}

	/**
	 * @return
	 */
	public String getPcDireccion() {
		return pcDireccion;
	}

	/**
	 * @return
	 */
	public String getPcEstado() {
		return pcEstado;
	}

	/**
	 * @return
	 */
	public String getPcFax() {
		return pcFax;
	}

	/**
	 * @return
	 */
	public String getPcTel() {
		return pcTel;
	}

	/**
	 * @param string
	 */
	public void setPcColonia(String string) {
		pcColonia = string;
	}

	/**
	 * @param string
	 */
	public void setPcCp(String string) {
		pcCp = string;
	}

	/**
	 * @param string
	 */
	public void setPcDireccion(String string) {
		pcDireccion = string;
	}

	/**
	 * @param string
	 */
	public void setPcEstado(String string) {
		pcEstado = string;
	}

	/**
	 * @param string
	 */
	public void setPcFax(String string) {
		pcFax = string;
	}

	/**
	 * @param string
	 */
	public void setPcTel(String string) {
		pcTel = string;
	}

	/**
	 * @return
	 */
	public String getPcTipoRegistro() {
		return pcTipoRegistro;
	}

	/**
	 * @param string
	 */
	public void setPcTipoRegistro(String string) {
		pcTipoRegistro = string;
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
	public int getPcCveSupCad() {
		return pcCveSupCad;
	}

	/**
	 * @return
	 */
	public String getPcNombreSupCad() {
		return pcNombreSupCad;
	}

	/**
	 * @param i
	 */
	public void setPcCveSupCad(int i) {
		pcCveSupCad = i;
	}

	/**
	 * @param string
	 */
	public void setPcNombreSupCad(String string) {
		pcNombreSupCad = string;
	}

	/**
	 * @return
	 */
	public String getPcFchCambio() {
		return pcFchCambio;
	}

	/**
	 * @param string
	 */
	public void setPcFchCambio(String string) {
		pcFchCambio = string;
	}

	/**
	 * @return
	 */
	public int getPcCveSuperiorSel() {
		return pcCveSuperiorSel;
	}

	/**
	 * @param i
	 */
	public void setPcCveSuperiorSel(int i) {
		pcCveSuperiorSel = i;
	}

	/**
	 * @return
	 */
	public String getPcCveSubdiv() {
		return pcCveSubdiv;
	}

	/**
	 * @param string
	 */
	public void setPcCveSubdiv(String string) {
		pcCveSubdiv = string;
	}

	/**
	 * @return
	 */
	public String getPcCveEmpresa() {
		return pcCveEmpresa;
	}

	/**
	 * @param string
	 */
	public void setPcCveEmpresa(String string) {
		pcCveEmpresa = string;
	}

	/**
	 * @return
	 */
	public String getPcCveHorario() {
		return pcCveHorario;
	}

	/**
	 * @return
	 */
	public String getPcDescHorario() {
		return pcDescHorario;
	}

	/**
	 * @param string
	 */
	public void setPcCveHorario(String string) {
		pcCveHorario = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescHorario(String string) {
		pcDescHorario = string;
	}

}
