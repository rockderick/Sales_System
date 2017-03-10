/*
 * Created on Jul 8, 2011
 *
 */
package mx.com.iusacell.catalogo.modelo;

import java.sql.Date;

/**
 * @author lcrespo
 *
 * Paquete : mx.com.iusacell.catalogo.modelo
 * Proyecto : AdminCatalogoWeb
 *
 * Derechos Reservados Iusacell SA de CV
 */
public class Pc_MapeoVendedorMovimientosVO implements java.io.Serializable {

	private int 	pcCveVendedor;
	private String 	pcSistema;
	private int 	pcCustomerId;
	private String 	pcCveRegVenta;
	private String 	pcNomUsuario;
	private String 	pcPassword;
	private int 	pcCveUser;
	private String 	pcIdUser;
	private String 	pcNombreUser;
	private String 	pcCveMovimiento;
	private Date	pcFechaMovimiento;
	
	
	/**
	 * @return
	 */
	public int getPcCustomerId() {
		return pcCustomerId;
	}

	/**
	 * @return
	 */
	public String getPcCveMovimiento() {
		return pcCveMovimiento;
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
	public int getPcCveUser() {
		return pcCveUser;
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
	public Date getPcFechaMovimiento() {
		return pcFechaMovimiento;
	}

	/**
	 * @return
	 */
	public String getPcIdUser() {
		return pcIdUser;
	}

	/**
	 * @return
	 */
	public String getPcNombreUser() {
		return pcNombreUser;
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
	public String getPcPassword() {
		return pcPassword;
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
	public void setPcCveMovimiento(String string) {
		pcCveMovimiento = string;
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
	public void setPcCveUser(int i) {
		pcCveUser = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveVendedor(int i) {
		pcCveVendedor = i;
	}

	/**
	 * @param date
	 */
	public void setPcFechaMovimiento(Date date) {
		pcFechaMovimiento = date;
	}

	/**
	 * @param string
	 */
	public void setPcIdUser(String string) {
		pcIdUser = string;
	}

	/**
	 * @param string
	 */
	public void setPcNombreUser(String string) {
		pcNombreUser = string;
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
	public void setPcPassword(String string) {
		pcPassword = string;
	}

	/**
	 * @param string
	 */
	public void setPcSistema(String string) {
		pcSistema = string;
	}
}