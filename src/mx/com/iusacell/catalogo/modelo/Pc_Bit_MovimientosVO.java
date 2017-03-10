/*
 * Created on Feb 25, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

import java.util.Date;
import mx.com.iusacell.catalogo.utilerias.Fecha;
/**
 * @author Dvazqueza
 *
 */
public class Pc_Bit_MovimientosVO implements java.io.Serializable{

	private int pcCveBitMovimientos;
	private int pcCveVendedor;		
	private int pcCveSuperiorOrigen;	
	private int pcCveSuperiorDestino;	
	private int pcCvePuestoOrigen;	
	private int pcCvePuestoDestino;	
	private String pcCvePtoventasOrigen;	
	private String pcCvePtoventasDestino;	
	private int pcCveUsuario;		
	private int pcCveAutoriza;		
	private Date pcFchMovimiento;	
	private Date pcFchTransaccion;
	
	private String pcFchMovimientoStr;
	private String pcFchTransaccionStr;	
		
	private String pcNomCompVendedor;		
	private String pcNomCompSuperiorOrigen;	
	private String pcNomCompSuperiorDestino;	
	private String pcDescPuestoOrigen;	
	private String pcDescPuestoDestino;	
	private String pcNomPtoventasOrigen;	
	private String pcNomPtoventasDestino;	
		
		
	//Empty Constructor
	public Pc_Bit_MovimientosVO() {
	}

	//Real Constructor
	public Pc_Bit_MovimientosVO(
		int pcCveBitMovimientos,	
		int pcCveVendedor,		
		int pcCveSuperiorOrigen,	
		int pcCveSuperiorDestino,	
		int pcCvePuestoOrigen,	
		int pcCvePuestoDestino,	
		String pcCvePtoventasOrigen,	
		String pcCvePtoventasDestino,	
		int pcCveUsuario,		
		int pcCveAutoriza,		
		Date pcFchMovimiento,
		String pcNomCompVendedor,
		String pcNomCompSuperiorOrigen,
		String pcNomCompSuperiorDestino,	
		String pcDescPuestoOrigen,	
		String pcDescPuestoDestino,	
		String pcNomPtoventasOrigen,	
		String pcNomPtoventasDestino) {
			setPcCveBitMovimientos(pcCveBitMovimientos);
			setPcCveVendedor(pcCveVendedor);
			setPcCveSuperiorOrigen(pcCveSuperiorOrigen);
			setPcCveSuperiorDestino(pcCveSuperiorDestino);
			setPcCvePuestoOrigen(pcCvePuestoOrigen);
			setPcCvePuestoDestino(pcCvePuestoDestino);
			setPcCvePtoventasOrigen(pcCvePtoventasOrigen);
			setPcCvePtoventasDestino(pcCvePtoventasDestino);	
			setPcCveUsuario(pcCveUsuario);
			setPcCveAutoriza(pcCveAutoriza);
			setPcFchMovimiento(pcFchMovimiento);
			setPcNomCompVendedor(pcNomCompVendedor);		
			setPcNomCompSuperiorOrigen(pcNomCompSuperiorOrigen);	
			setPcNomCompSuperiorDestino(pcNomCompSuperiorDestino);	
			setPcDescPuestoOrigen(pcDescPuestoOrigen);	
			setPcDescPuestoDestino(pcDescPuestoDestino);	
			setPcNomPtoventasOrigen(pcNomPtoventasOrigen);	
			setPcNomPtoventasDestino(pcNomPtoventasDestino);	
	}
	
	/**
	 * @return
	 */
	public int getPcCveAutoriza() {
		return pcCveAutoriza;
	}

	/**
	 * @return
	 */
	public int getPcCveBitMovimientos() {
		return pcCveBitMovimientos;
	}

	/**
	 * @return
	 */
	public String getPcCvePtoventasDestino() {
		return pcCvePtoventasDestino;
	}

	/**
	 * @return
	 */
	public String getPcCvePtoventasOrigen() {
		return pcCvePtoventasOrigen;
	}

	/**
	 * @return
	 */
	public int getPcCvePuestoDestino() {
		return pcCvePuestoDestino;
	}

	/**
	 * @return
	 */
	public int getPcCvePuestoOrigen() {
		return pcCvePuestoOrigen;
	}

	/**
	 * @return
	 */
	public int getPcCveSuperiorDestino() {
		return pcCveSuperiorDestino;
	}

	/**
	 * @return
	 */
	public int getPcCveSuperiorOrigen() {
		return pcCveSuperiorOrigen;
	}

	/**
	 * @return
	 */
	public int getPcCveUsuario() {
		return pcCveUsuario;
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
	public Date getPcFchMovimiento() {
		return pcFchMovimiento;
	}

	/**
	 * @param i
	 */
	public void setPcCveAutoriza(int i) {
		pcCveAutoriza = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveBitMovimientos(int i) {
		pcCveBitMovimientos = i;
	}

	/**
	 * @param string
	 */
	public void setPcCvePtoventasDestino(String string) {
		pcCvePtoventasDestino = string;
	}

	/**
	 * @param string
	 */
	public void setPcCvePtoventasOrigen(String string) {
		pcCvePtoventasOrigen = string;
	}

	/**
	 * @param i
	 */
	public void setPcCvePuestoDestino(int i) {
		pcCvePuestoDestino = i;
	}

	/**
	 * @param i
	 */
	public void setPcCvePuestoOrigen(int i) {
		pcCvePuestoOrigen = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveSuperiorDestino(int i) {
		pcCveSuperiorDestino = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveSuperiorOrigen(int i) {
		pcCveSuperiorOrigen = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveUsuario(int i) {
		pcCveUsuario = i;
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
	public void setPcFchMovimiento(Date date) {
		pcFchMovimiento = date;
	}

	/**
	 * @return
	 */
	public String getPcFchMovimientoStr() {
		return Fecha.dateToString(this.pcFchMovimiento);
	}

	/**
	 * @param string
	 */
	public void setPcFchMovimientoStr(String string) {
		pcFchMovimiento = Fecha.stringToDate(string);
	}
	/**
	 * @return
	 */
	public String getPcDescPuestoDestino() {
		return pcDescPuestoDestino;
	}

	/**
	 * @return
	 */
	public String getPcDescPuestoOrigen() {
		return pcDescPuestoOrigen;
	}

	/**
	 * @return
	 */
	public String getPcNomCompSuperiorDestino() {
		return pcNomCompSuperiorDestino;
	}

	/**
	 * @return
	 */
	public String getPcNomCompSuperiorOrigen() {
		return pcNomCompSuperiorOrigen;
	}

	/**
	 * @return
	 */
	public String getPcNomCompVendedor() {
		return pcNomCompVendedor;
	}

	/**
	 * @return
	 */
	public String getPcNomPtoventasDestino() {
		return pcNomPtoventasDestino;
	}

	/**
	 * @return
	 */
	public String getPcNomPtoventasOrigen() {
		return pcNomPtoventasOrigen;
	}

	/**
	 * @param string
	 */
	public void setPcDescPuestoDestino(String string) {
		pcDescPuestoDestino = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescPuestoOrigen(String string) {
		pcDescPuestoOrigen = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomCompSuperiorDestino(String string) {
		pcNomCompSuperiorDestino = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomCompSuperiorOrigen(String string) {
		pcNomCompSuperiorOrigen = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomCompVendedor(String string) {
		pcNomCompVendedor = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomPtoventasDestino(String string) {
		pcNomPtoventasDestino = string;
	}

	/**
	 * @param string
	 */
	public void setPcNomPtoventasOrigen(String string) {
		pcNomPtoventasOrigen = string;
	}

	/**
	 * @return
	 */
	public Date getPcFchTransaccion() {
		return pcFchTransaccion;
	}

	/**
	 * @return
	 */
	public String getPcFchTransaccionStr() {
		//return pcFchTransaccionStr;
		return Fecha.dateToString(this.pcFchTransaccion);
	}

	/**
	 * @param date
	 */
	public void setPcFchTransaccion(Date date) {
		pcFchTransaccion = date;
	}

	/**
	 * @param string
	 */
	public void setPcFchTransaccionStr(String string) {
		//pcFchTransaccionStr = string;
		pcFchTransaccion = Fecha.stringToDate(string);
	}

}
