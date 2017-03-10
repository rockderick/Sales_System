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
public class Pc_Inter_Vendedores_PtoventasVO implements java.io.Serializable, Comparable{

	private int pcCveInter;
	private int pcCveVendedor;
	private String pcCvePtoventas;

	private Date pcFchMovimiento;   
	private int pcCveUsuario; 
	private int pcCveAutoriza;     
	
	private String pcNomPtoventas;
	private String pcCveCanal;
	private String pcDescCanal; 
	private String pcNomVendedor;
	private String pcApePaterno;
	private String pcApeMaterno;
	private String pcNombreVendedor;

	private String pcFchMovimientoStr;
	private String pcCntlInt;	
	
	//Empty Constructor
	public Pc_Inter_Vendedores_PtoventasVO() {
	}

	//Real Constructor
	public Pc_Inter_Vendedores_PtoventasVO(
			int pcCveInter,
			int pcCveVendedor,
			String pcCvePtoventas,
			String pcOrigenPtoventas,
			String pcNomPtoventas,
			String pcCveCanal,
			String pcDescCanal, 
			String pcNomVendedor,
			String pcApePaterno,
			String pcApeMaterno,
			String pcNombreVendedor,
			Date pcFchMovimiento,  
			int pcCveUsuario,     
			int pcCveAutoriza) {
		setPcCveInter(pcCveInter);
		setPcCveVendedor(pcCveVendedor);
		setPcCvePtoventas(pcCvePtoventas);
		setPcNomPtoventas(pcNomPtoventas);
		setPcCveCanal(pcCveCanal);
		setPcDescCanal(pcDescCanal);
		setPcNomVendedor(pcNomVendedor);
		setPcApePaterno(pcApePaterno);
		setPcApeMaterno(pcApeMaterno);
		setPcNombreVendedor(pcNombreVendedor);
		setPcFchMovimiento(pcFchMovimiento);
		setPcCveUsuario(pcCveUsuario);
		setPcCveAutoriza(pcCveAutoriza);
	}
	
	/**
	 * @return
	 */
	public String getPcApeMaterno() {
		String string = (this.pcApeMaterno == null)? "":this.pcApeMaterno;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcApePaterno() {
		String string = (this.pcApePaterno == null)? "":this.pcApePaterno;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCveCanal() {
		String string = (this.pcCveCanal == null)? "":this.pcCveCanal;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcDescCanal() {
		String string = (this.pcDescCanal == null)? "":this.pcDescCanal;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcNomPtoventas() {
		String string = (this.pcNomPtoventas == null)? "":this.pcNomPtoventas;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcNomVendedor() {
		String string = (this.pcNomVendedor == null)? "":this.pcNomVendedor;
		return string;
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
	 * @return
	 */
	public String getPcNombreVendedor() {
		String nombre = (this.pcNomVendedor == null)? "":this.pcNomVendedor;
		String apePaterno = (this.pcApePaterno == null)? "":this.pcApePaterno;
		String apeMaterno = (this.pcApeMaterno == null)? "":this.pcApeMaterno;
		String nombreCompleto = (nombre + ' ' + apePaterno + ' ' + apeMaterno);
		return nombreCompleto;
	}

	/**
	 * @param string
	 */
	public void setPcNombreVendedor(String string) {
		pcNombreVendedor = (this.pcNomVendedor + ' ' + this.pcApePaterno + ' ' + this.pcApeMaterno);
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
	public int getPcCveUsuario() {
		return pcCveUsuario;
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
	public void setPcCveInter(int i) {
		pcCveInter = i;
	}

	/**
	 * @param string
	 */
	public void setPcCvePtoventas(String string) {
		pcCvePtoventas = string;
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
	public int getPcCveInter() {
		return pcCveInter;
	}

	/**
	 * @return
	 */
	public String getPcCvePtoventas() {
		return pcCvePtoventas;
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
	public String getPcFchMovimientoStr() {
		return Fecha.dateToString(this.pcFchMovimiento);
	}

	/**
	 * @param string
	 */
	public void setPcFchMovimientoStr(String string) {
		pcFchMovimiento = Fecha.stringToDate(string);
	}
	
	public int compareTo(Object relacion) throws ClassCastException{
		String compVendedor = ((Pc_Inter_Vendedores_PtoventasVO) relacion).getPcNombreVendedor();
			
		int order = this.getPcNombreVendedor().compareTo(compVendedor);
		if(order==0){
			String compPtoventas = ((Pc_Inter_Vendedores_PtoventasVO) relacion).getPcNomPtoventas();
			return this.getPcNomPtoventas().compareTo(compPtoventas);
		}else{
			return order;
		}
	}
	
	/* (no Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object relacion) {
		try{
			return this.getPcCveInter()==((Pc_Inter_Vendedores_PtoventasVO) relacion).getPcCveInter();
		}catch(Exception e){
			return false;
		}
	}

	/* (no Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.pcCveInter;
	}

	/**
	 * @return
	 */
	public String getPcCntlInt() {
		return pcCntlInt;
	}

	/**
	 * @param string
	 */
	public void setPcCntlInt(String string) {
		pcCntlInt = string;
	}

}
