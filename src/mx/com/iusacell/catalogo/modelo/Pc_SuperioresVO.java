/*
 * Created on 23/02/2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

import java.util.Date;
import mx.com.iusacell.catalogo.utilerias.Fecha;
/**
 * @author Dvazqueza
 *
 */
public class Pc_SuperioresVO implements java.io.Serializable, Comparable{

	private int pcCveVendedor; 
	private int pcCvePuesto;
	private int pcCveSuperior; 
	private String pcNomVendedor; 
	private String pcApePaterno;
	private String pcApeMaterno; 
	private Date pcFchAlta; 
	private Date pcFchBaja; 
	private int pcDigVerif; 
	private String pcCntrTda; 
	private String pcStatus; 
	private String pcCveEmpRef; 
	private int pcCveEmpresa;
	private String pcCiudad;
	private String pcEstado;		
	private String pcDireccion;	
	private String pcColonia;		
	private String pcCp;		
	private String pcTel;		
	private String pcFax;		
	private int pcCveSupCad; 

	private int pcCveSubdiv; 
	
	private String pcDescPuesto;		//String para la descripcion del Puesto seleccionado en la forma 
	private String pcNombreSuperior; 	//String para el nombre del Supervisor asignado
	private String pcNombreSupCad; 	//String para el nombre del Supervisor asignado
		
	private String pcNombreVendedor;
	private String pcNombreCompleto;
	private String pcClaveCompleta;

	private String pcFechaAltaStr;	
	private String pcFechaBajaStr;	
	
	private String pcSubdivision;
	
	private int pcCveHorario;
	private String pcDescHorario;
		
	//	Empty Constructor
	public Pc_SuperioresVO() {
	}    
	
	//	Real Constructor
	public Pc_SuperioresVO( 
			int pcCveVendedor,
			int pcCvePuesto,
			int pcCveSuperior, 
			String pcNomVendedor, 
			String pcApePaterno,
			String pcApeMaterno, 
			Date pcFchAlta, 
			Date pcFchBaja, 
			int pcDigVerif, 
			String pcCntrTda, 
			String pcStatus, 
			String pcCveEmpRef, 
			String pcCiudad,
			String pcEstado,		
			String pcDireccion,	
			String pcColonia,		
			String pcCp,		
			String pcTel,		
			String pcFax,	
			String pcDescPuesto, 
			String pcNombreSuperior,
			String pcNombreVendedor,
			String pcNombreCompleto,
			String pcClaveCompleta,
			String pcFechaAltaStr,	
			String pcFechaBajaStr,
			int pcCveSupCad,
			String pcNombreSupCad,
			int pcCveHorario){
		setPcCveVendedor(pcCveVendedor); 
		setPcCvePuesto(pcCvePuesto);
		setPcCveSuperior(pcCveSuperior); 
		setPcNomVendedor(pcNomVendedor); 
		setPcApePaterno(pcApePaterno);
		setPcApeMaterno(pcApeMaterno); 
		setPcFchAlta(pcFchAlta); 
		setPcFchBaja(pcFchBaja); 
		setPcDigVerif(pcDigVerif); 
		setPcCntrTda(pcCntrTda); 
		setPcStatus(pcStatus); 
		setPcCveEmpRef(pcCveEmpRef); 
		setPcCiudad(pcCiudad);
		setPcEstado(pcEstado);		
		setPcDireccion(pcDireccion);		
		setPcColonia(pcColonia);
		setPcCp(pcCp);
		setPcTel(pcTel);
		setPcFax(pcFax);
		setPcDescPuesto(pcDescPuesto); 
		setPcNombreSuperior(pcNombreSuperior);
		setPcNombreVendedor(pcNombreVendedor);
		setPcNombreCompleto(pcNombreCompleto);
		setPcNombreCompleto(pcClaveCompleta);
		setPcFechaAltaStr(pcFechaAltaStr);	
		setPcFechaBajaStr(pcFechaBajaStr);	
		setPcCveSupCad(pcCveSupCad); 
		setPcNombreSupCad(pcNombreSupCad);
		setPcCveHorario(pcCveHorario);
    }
		
		

	/**
	 * @return
	 */
	public String getPcApeMaterno() {
		String string = (this.pcApeMaterno == null)? "":this.pcApeMaterno ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcApePaterno() {
		String string = (this.pcApePaterno == null)? "":this.pcApePaterno ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCiudad() {
		String string = (this.pcCiudad == null)? "":this.pcCiudad ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCntrTda() {
		String string = (this.pcCntrTda == null)? "":this.pcCntrTda;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCveEmpRef() {
		String string = (this.pcCveEmpRef == null)? "":this.pcCveEmpRef;
		return string;
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
	public int getPcDigVerif() {
		return pcDigVerif;
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
	public String getPcNomVendedor() {
		String string = (this.pcNomVendedor == null)? "":this.pcNomVendedor;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcStatus() {
		String string = (this.pcStatus == null)? "":this.pcStatus;
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
	public void setPcCvePuesto(int value) {
		pcCvePuesto = value;
	}

	/**
	 * @param value
	 */
	public void setPcDigVerif(int value) {
		pcDigVerif = value;
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
		String string = (this.pcDescPuesto == null)? "":this.pcDescPuesto;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcNombreSuperior() {
		String string = (this.pcNombreSuperior == null)? "":this.pcNombreSuperior;
		return string;
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
	public String getPcNombreVendedor() {
		String string = (this.pcNombreVendedor == null)? "":this.pcNombreVendedor;
		return string;
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
	public String getPcNombreCompleto() {
		String nombre = (this.pcNomVendedor == null)? "":this.pcNomVendedor ;
		String apePaterno = (this.pcApePaterno == null)? "":this.pcApePaterno;
		String apeMaterno = (this.pcApeMaterno == null)? "":this.pcApeMaterno;
		
		String NombreCompleto = (nombre + ' ' + apePaterno + ' '+ apeMaterno);

		return NombreCompleto;
	}

	/**
	 * @param string
	 */
	public void setPcNombreCompleto(String string) {
		String nombre = (this.pcNomVendedor == null)? "":this.pcNomVendedor ;
		String apePaterno = (this.pcApePaterno == null)? "":this.pcApePaterno;
		String apeMaterno = (this.pcApeMaterno == null)? "":this.pcApeMaterno;
		
		pcNombreCompleto = (nombre + ' ' + apePaterno + ' '+ apeMaterno);
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
	public String getPcDireccion() {
		String string = (this.pcDireccion == null)? "":this.pcDireccion;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcEstado() {
		String string = (this.pcEstado == null)? "":this.pcEstado;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcFax() {
		String string = (this.pcFax == null)? "":this.pcFax;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcTel() {
		String string = (this.pcTel == null)? "":this.pcTel;
		return string;
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
	public void setPcCveSuperior(int i) {
		pcCveSuperior = i;
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
	public String getPcClaveCompleta() {
		String clave = String.valueOf(this.getPcCveVendedor());
		String digito = String.valueOf(this.getPcDigVerif());
		return new String(clave + '-' + digito);
	}

	/**
	 * @param string
	 */
	public void setPcClaveCompleta(String string) {
		pcClaveCompleta = string;
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

	public int compareTo(Object vendedor) throws ClassCastException{
			String comparar = ((Pc_SuperioresVO) vendedor).getPcNombreCompleto();

			return (this.getPcNombreCompleto().compareTo(comparar));
	}

	/* (no Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object vendedor) {
		try{
			return this.getPcCveVendedor()==((Pc_SuperioresVO) vendedor).getPcCveVendedor();
		}catch(Exception e){
			return false;
		}
	}

	/* (no Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return this.pcCveVendedor;
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

	/**
	 * @return
	 */
	public String getPcSubdivision() {
		return pcSubdivision;
	}

	/**
	 * @param string
	 */
	public void setPcSubdivision(String string) {
		pcSubdivision = string;
	}

	/**
	 * @return
	 */
	public int getPcCveEmpresa() {
		return pcCveEmpresa;
	}

	/**
	 * @param string
	 */
	public void setPcCveEmpresa(int string) {
		pcCveEmpresa = string;
	}

	/**
	 * @return
	 */
	public int getPcCveHorario() {
		return pcCveHorario;
	}

	/**
	 * @return
	 */
	public String getPcDescHorario() {
		return pcDescHorario;
	}

	/**
	 * @param i
	 */
	public void setPcCveHorario(int i) {
		pcCveHorario = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescHorario(String string) {
		pcDescHorario = string;
	}

}

