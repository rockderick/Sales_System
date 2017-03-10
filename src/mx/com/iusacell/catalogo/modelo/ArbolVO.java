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
public class ArbolVO implements java.io.Serializable{
	
	private int level;
	private int nivel;
	private int pcCveVendedor;
	private String pcNombreVendedor;
	private int pcCvePuesto;
	private String pcDescPuesto;

	private int pcCveSuperior;
	private String pcNombreSuperior;

	private Date pcFchAlta; 
	private Date pcFchBaja; 
	private int pcDigVerif; 
	private String pcCiudad;
	private String pcEstado;		
	private String pcDireccion;	
	private String pcColonia;		
	private String pcCp;		
	private String pcTel;		
	private String pcFax;		
		
	private String pcClaveCompleta;

	private String pcFechaAltaStr;	
	private String pcFechaBajaStr;
	
	private String pcCvePtoventas; 
	private String pcNomPtoventas; 
	private String pcCveCanal; 
	private String pcDescCanal; 
	private String pcCveRegion; 
	private String pcCveReferencia; 
	private String pcDescRegion; 
	private String pcCveTpCanal; 
	private String pcDescTpCanal; 
	private String pcCveEmpRef;
	private int pcCveDiv; 
	private String pcDescDiv; 
	private int pcCvePuestoSup;
	private String pcDescPuestoSup;

	//	Empty Constructor
	public ArbolVO() {
	} 
	
	//	Real Constructor 
	public ArbolVO(int level,
				   int nivel,
	               int pcCveVendedor,
	               String pcNombreVendedor,
	               int pcCvePuesto,
	               String pcDescPuesto,
	               Date pcFchAlta,
	               Date pcFchBaja,
	               int pcDigVerif,
	               String pcCiudad,
	               String pcEstado,
	               String pcDireccion,
	               String pcColonia,
	               String pcCp,
	               String pcTel,
	               String pcFax,
	               String pcClaveCompleta,
	               String pcFechaAltaStr,
	               String pcFechaBajaStr,
				   String pcCvePtoventas,
				   String pcNomPtoventas,
				   String pcCveCanal,
				   String pcDescCanal,
				   String pcCveRegion,
				   String pcDescRegion,
				   String pcCveReferencia,
				   String pcCveTpCanal,
				   String pcDescTpCanal,
				   int pcCveSuperior,
				   String pcNombreSuperior,
			   	   int pcCveDiv,
			   	   String pcDescDiv){
		setLevel(level);
		setNivel(nivel);
		setPcCveVendedor(pcCveVendedor);
		setPcNombreVendedor(pcNombreVendedor);
		setPcCvePuesto(pcCvePuesto);
		setPcDescPuesto(pcDescPuesto);
		setPcFchAlta(pcFchAlta); 
		setPcFchBaja(pcFchBaja); 
		setPcDigVerif(pcDigVerif); 
		setPcCiudad(pcCiudad);
		setPcEstado(pcEstado);		
		setPcDireccion(pcDireccion);	
		setPcColonia(pcColonia);		
		setPcCp(pcCp);		
		setPcTel(pcTel);		
		setPcFax(pcFax);		
		setPcClaveCompleta(pcClaveCompleta);
		setPcFechaAltaStr(pcFechaAltaStr);	
		setPcFechaBajaStr(pcFechaBajaStr);	
		setPcCvePtoventas(pcCvePtoventas); 
		setPcNomPtoventas(pcNomPtoventas); 
		setPcCveCanal(pcCveCanal); 
		setPcDescCanal(pcDescCanal); 
		setPcCveRegion(pcCveRegion); 
		setPcDescRegion(pcDescRegion); 
		setPcCveReferencia(pcCveReferencia); 
		setPcCveTpCanal(pcCveTpCanal); 
		setPcDescTpCanal(pcDescTpCanal); 
		setPcCveSuperior(pcCveSuperior);
		setPcNombreSuperior(pcNombreSuperior);
		setPcCveDiv(pcCveDiv);
		setPcDescDiv(pcDescDiv);
	}	


	/**
	 * @return
	 */
	public int getLevel() {
		return level;
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
	public String getPcDescPuesto() {
		String string = (this.pcDescPuesto == null)? "":this.pcDescPuesto;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcNombreVendedor() {
		String string = (this.pcNombreVendedor == null)? "":this.pcNombreVendedor;
		return string;
	}

	/**
	 * @param i
	 */
	public void setLevel(int i) {
		level = i;
	}

	/**
	 * @param value
	 */
	public void setPcCvePuesto(int value) {
		pcCvePuesto = value;
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
	public void setPcDescPuesto(String string) {
		pcDescPuesto = string;
	}

	/**
	 * @param string
	 */
	public void setPcNombreVendedor(String string) {
		pcNombreVendedor = string;
	}

	/**
	 * @return
	 */
	public String getPcCiudad() {
		String string = (this.pcCiudad == null)? "":this.pcCiudad;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcClaveCompleta() {
		String clave = String.valueOf(this.getPcCveVendedor());
		String digito = String.valueOf(this.getPcDigVerif());
		return new String(clave + '-' + digito);
	}

	/**
	 * @return
	 */
	public String getPcColonia() {
		String string = (this.pcColonia == null)? "":this.pcColonia;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCp() {
		String string = (this.pcCp == null)? "":this.pcCp;
		return string;
	}

	/**
	 * @return
	 */
	public int getPcDigVerif() {
		return pcDigVerif;
	}

	/**
	 * @return
	 */
	public String getPcDireccion() {
		String string = (this.pcDireccion == null)? "":this.pcDireccion ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcEstado() {
		String string = (this.pcEstado == null)? "":this.pcEstado ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcFax() {
		String string = (this.pcFax == null)? "":this.pcFax ;
		return string;
	}

	/**
	 * @return
	 */
	public Date getPcFchAlta() {
		return pcFchAlta;
	}

	/**
	 * @return
	 */
	public Date getPcFchBaja() {
		return pcFchBaja;
	}

	/**
	 * @return
	 */
	public String getPcFechaAltaStr() {
		return Fecha.dateToString(this.pcFchAlta);
	}

	/**
	 * @return
	 */
	public String getPcFechaBajaStr() {
		return Fecha.dateToString(this.pcFchBaja);
	}

	/**
	 * @return
	 */
	public String getPcTel() {
		String string = (this.pcTel == null)? "":this.pcTel ;
		return string;
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
	public void setPcClaveCompleta(String string) {
		pcClaveCompleta = string;
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
	 * @param i
	 */
	public void setPcDigVerif(int i) {
		pcDigVerif = i;
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
	 * @param date
	 */
	public void setPcFchAlta(Date date) {
		pcFchAlta = date;
	}

	/**
	 * @param date
	 */
	public void setPcFchBaja(Date date) {
		pcFchBaja = date;
	}

	/**
	 * @param string
	 */
	public void setPcFechaAltaStr(String string) {
		pcFechaAltaStr = string;
	}

	/**
	 * @param string
	 */
	public void setPcFechaBajaStr(String string) {
		pcFechaBajaStr = string;
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
	public String getPcCveCanal() {
		return pcCveCanal;
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
	public String getPcCveRegion() {
		return pcCveRegion;
	}

	/**
	 * @return
	 */
	public String getPcCveTpCanal() {
		return pcCveTpCanal;
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
	public String getPcDescRegion() {
		return pcDescRegion;
	}

	/**
	 * @return
	 */
	public String getPcDescTpCanal() {
		return pcDescTpCanal;
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
	public void setPcCveCanal(String string) {
		pcCveCanal = string;
	}

	/**
	 * @param string
	 */
	public void setPcCvePtoventas(String string) {
		pcCvePtoventas = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveRegion(String string) {
		pcCveRegion = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveTpCanal(String string) {
		pcCveTpCanal = string;
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
	public void setPcDescRegion(String string) {
		pcDescRegion = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescTpCanal(String string) {
		pcDescTpCanal = string;
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
	public int getPcCveSuperior() {
		return pcCveSuperior;
	}

	/**
	 * @return
	 */
	public String getPcNombreSuperior() {
		return pcNombreSuperior;
	}

	/**
	 * @param i
	 */
	public void setPcCveSuperior(int i) {
		pcCveSuperior = i;
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
	public int getPcCveDiv() {
		return pcCveDiv;
	}

	/**
	 * @return
	 */
	public String getPcDescDiv() {
		return pcDescDiv;
	}

	/**
	 * @param i
	 */
	public void setPcCveDiv(int i) {
		pcCveDiv = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescDiv(String string) {
		pcDescDiv = string;
	}

	/**
	 * @return
	 */
	public int getPcCvePuestoSup() {
		return pcCvePuestoSup;
	}

	/**
	 * @return
	 */
	public String getPcDescPuestoSup() {
		return pcDescPuestoSup;
	}

	/**
	 * @param i
	 */
	public void setPcCvePuestoSup(int i) {
		pcCvePuestoSup = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescPuestoSup(String string) {
		pcDescPuestoSup = string;
	}

	/**
	 * @return
	 */
	public String getPcCveReferencia() {
		return pcCveReferencia;
	}

	/**
	 * @param string
	 */
	public void setPcCveReferencia(String string) {
		pcCveReferencia = string;
	}

	/**
	 * @return
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * @param i
	 */
	public void setNivel(int i) {
		nivel = i;
	}

	/**
	 * @return
	 */
	public String getPcCveEmpRef() {
		return pcCveEmpRef;
	}

	/**
	 * @param string
	 */
	public void setPcCveEmpRef(String string) {
		pcCveEmpRef = string;
	}

}
