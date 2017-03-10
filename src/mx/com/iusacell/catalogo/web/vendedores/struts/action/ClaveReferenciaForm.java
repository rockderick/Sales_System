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
public class ClaveReferenciaForm extends ValidatorForm implements Serializable{
	private String action = null;

	private String pcCvePuestoSeek;
	private String pcCveVendedorSeek;
	private String pcNomVendedorSeek;
	private String pcCveVendedorRadio;
	private String pcMapeoVendedorRadio;
	private String pcNomClaveEmpleadoReferenciaSeek;
	
	private int pcCveVendedor;
	private String pcSistema;
	private int pcCustomerId;
	private String pcCveRegVenta;
	private String pcNomUsuario;

	//Empty Constructor
	public ClaveReferenciaForm() {
		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);
		setPcNomVendedorSeek(null);
		setPcCveVendedorRadio(null);
		setPcMapeoVendedorRadio(null);

		setPcCveVendedor(0);
		setPcSistema(null);
		setPcCustomerId(0);
		setPcCveRegVenta(null);
		setPcNomUsuario(null);
	}

	/**
	 * @return
	 */
	public int getPcCustomerId() {
		return pcCustomerId;
	}

	/**
	 * @return
	 */
	public String getPcCveRegVenta() {
		return pcCveRegVenta;
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
	public String getPcNomUsuario() {
		return pcNomUsuario;
	}

	/**
	 * @return
	 */
	public String getPcSistema() {
		return pcSistema;
	}

	/**
	 * @param i
	 */
	public void setPcCustomerId(int i) {
		pcCustomerId = i;
	}

	/**
	 * @param string
	 */
	public void setPcCveRegVenta(String string) {
		pcCveRegVenta = string;
	}

	/**
 	* @param i
 	*/
	public void setPcCveVendedor(int i) {
		pcCveVendedor = i;
	}

	/**
	 * @param string
	 */
	public void setPcNomUsuario(String string) {
		pcNomUsuario = string;
	}

	/**
	 * @param string
	 */
	public void setPcSistema(String string) {
		pcSistema = string;
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCvePuestoSeek(null);
		setPcCveVendedorSeek(null);
		setPcNomVendedorSeek(null);
		setPcCveVendedorRadio(null);
		setPcMapeoVendedorRadio(null);
		
		setPcCveVendedor(0);
		setPcSistema(null);
		setPcCustomerId(0);
		setPcCveRegVenta(null);
		setPcNomUsuario(null);
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
	 * @param string
	 */
	public void setAction(String string) {
		action = string;
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
	 * @return
	 */
	public String getPcMapeoVendedorRadio() {
		return pcMapeoVendedorRadio;
	}

	/**
	 * @param string
	 */
	public void setPcMapeoVendedorRadio(String string) {
		pcMapeoVendedorRadio = string;
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
