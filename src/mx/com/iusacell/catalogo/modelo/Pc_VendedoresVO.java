/*
 * Created on 23/02/2005
 *
 */
package mx.com.iusacell.catalogo.modelo;


import java.util.Date;

import org.jdom.Document;
import org.jdom.Element;

import mx.com.iusacell.catalogo.utilerias.Fecha;
/**
 * @author Dvazqueza
 *
 */
public class Pc_VendedoresVO implements java.io.Serializable, Comparable {

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

	private String pcDescPuesto;
	//String para la descripcion del Puesto seleccionado en la forma
	private String pcNombreSuperior;
	//String para el nombre del Supervisor asignado
	private String pcNombreSupCad;
	//String para el nombre del Supervisor asignado

	private String pcNombreVendedor;
	private String pcNombreCompleto;
	private String pcClaveCompleta;

	private String pcFechaAltaStr;
	private String pcFechaBajaStr;

	private String pcSubdivision;

	private int pcCveHorario;
	private String pcDescHorario;

	private String pcEstatusHuella;
	private String pcCveOta;

	
	private int pcNivelVentas;
	private String pcTipoComision;
	private String horario;

	private String pcTipoVendedor;
	
		
	//	Empty Constructor
	public Pc_VendedoresVO() {
	}

	//	Real Constructor
	public Pc_VendedoresVO(
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
		int pcCveHorario,
	    String pcEstatusHuella) {
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
		setPcEstatusHuella(pcEstatusHuella);
	}

	/**
	 * @return
	 */
	public String getPcApeMaterno() {
		String string = (this.pcApeMaterno == null) ? "" : this.pcApeMaterno;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcApePaterno() {
		String string = (this.pcApePaterno == null) ? "" : this.pcApePaterno;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCiudad() {
		String string = (this.pcCiudad == null) ? "" : this.pcCiudad;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCntrTda() {
		String string = (this.pcCntrTda == null) ? "" : this.pcCntrTda;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCveEmpRef() {
		String string = (this.pcCveEmpRef == null) ? "" : this.pcCveEmpRef;
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
		String string = (this.pcNomVendedor == null) ? "" : this.pcNomVendedor;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcStatus() {
		String string = (this.pcStatus == null) ? "" : this.pcStatus;
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
		String string = (this.pcDescPuesto == null) ? "" : this.pcDescPuesto;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcNombreSuperior() {
		String string =
			(this.pcNombreSuperior == null) ? "" : this.pcNombreSuperior;
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
		String string =
			(this.pcNombreVendedor == null) ? "" : this.pcNombreVendedor;
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
		String nombre = (this.pcNomVendedor == null) ? "" : this.pcNomVendedor;
		String apePaterno =
			(this.pcApePaterno == null) ? "" : this.pcApePaterno;
		String apeMaterno =
			(this.pcApeMaterno == null) ? "" : this.pcApeMaterno;

		String NombreCompleto = (nombre + ' ' + apePaterno + ' ' + apeMaterno);

		return NombreCompleto;
	}

	/**
	 * @param string
	 */
	public void setPcNombreCompleto(String string) {
		String nombre = (this.pcNomVendedor == null) ? "" : this.pcNomVendedor;
		String apePaterno =
			(this.pcApePaterno == null) ? "" : this.pcApePaterno;
		String apeMaterno =
			(this.pcApeMaterno == null) ? "" : this.pcApeMaterno;

		pcNombreCompleto = (nombre + ' ' + apePaterno + ' ' + apeMaterno);
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
		String string = (this.pcColonia == null) ? "" : this.pcColonia;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCp() {
		String string = (this.pcCp == null) ? "" : this.pcCp;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcDireccion() {
		String string = (this.pcDireccion == null) ? "" : this.pcDireccion;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcEstado() {
		String string = (this.pcEstado == null) ? "" : this.pcEstado;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcFax() {
		String string = (this.pcFax == null) ? "" : this.pcFax;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcTel() {
		String string = (this.pcTel == null) ? "" : this.pcTel;
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
		int digito = this.getPcDigVerif();
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

	public int compareTo(Object vendedor) throws ClassCastException {
		String comparar = ((Pc_VendedoresVO) vendedor).getPcNombreCompleto();

		return (this.getPcNombreCompleto().compareTo(comparar));
	}

	/* (no Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object vendedor) {
		try {
			return this.getPcCveVendedor()
				== ((Pc_VendedoresVO) vendedor).getPcCveVendedor();
		} catch (Exception e) {
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

	/**
	 * @return
	 */
	public String getPcCveOta() {
		return pcCveOta;
	}

	/**
	 * @return
	 */
	public String getPcEstatusHuella() {
		return pcEstatusHuella;
	}
	
	/**
	 * @param string
	 */
	public void setPcCveOta(String string) {
		pcCveOta = string;
	}

	/**
	 * @param string
	 */
	public void setPcEstatusHuella(String string) {
		pcEstatusHuella = string;
	}
	
	/**
	 * Este método regresa la fecha del status del usuario
	 * son ambos datos concatenados.
	 * @param
	 * @author
	 */
	public String getPcStatusFecha(){
		String fecha = "";
		fecha = (Fecha.dateToString(this.pcFchBaja) == null) ? "" : Fecha.dateToString(this.pcFchBaja);   
		String status = String.valueOf(this.getPcStatus());
		 if(fecha.equals("")){
			fecha = (Fecha.dateToString(this.pcFchAlta) == null) ? "" : Fecha.dateToString(this.pcFchAlta);
		 }               
	      return new String(status + "( " + fecha + " )");
	}
	
	/**
	 * @return
	 */
	public int getPcNivelVentas() {
		return pcNivelVentas;
	}

	/**
	 * @return
	 */
	public String getPcTipoComision() {
	       String string = (this.pcTipoComision == null) ? "" : this.pcTipoComision;
		return string;
	}

	/**
	 * @param i
	 */
	public void setPcNivelVentas(int i) {
		pcNivelVentas = i;
	}

	/**
	 * @param string
	 */
	public void setPcTipoComision(String string) {
		pcTipoComision = string;
	}
	
	/* Salida en XML */
	public Document toXml(){
		Document doc = null;
		try{
			//Creamos raiz
			Element root = new Element("vendedor");
			//Creamos nodos
			Element e1 = new Element("clave").setText(pcCveVendedor+"");
			Element e2 = new Element("nombre").setText(pcNomVendedor);
			Element e3 = new Element("paterno").setText(pcApePaterno);
			Element e4 = new Element("materno").setText(pcApeMaterno);
			Element e5 = new Element("ciudad").setText(pcCiudad);
			Element e6 = new Element("estado").setText(pcEstado);
			Element e7 = new Element("direccion").setText(pcDireccion);
			Element e8 = new Element("colonia").setText(pcColonia);
			Element e9 = new Element("cp").setText(pcCp);
			Element e10 = new Element("telefono").setText(pcTel);
			Element e11 = new Element("fax").setText(pcFax);
			Element e12 = new Element("descPuesto").setText(pcDescPuesto);
			Element e13 = new Element("nomSuperior").setText(pcNombreSuperior);
			Element e14 = new Element("horario").setText(pcCveHorario+"");
			Element e15 = new Element("numero").setText(pcCveEmpRef);
			Element e16 = new Element("empresa").setText(pcCveEmpresa+"");
			String alta = (Fecha.dateToString(this.pcFchAlta) == null) ? "" : Fecha.dateToString(this.pcFchAlta);
			Element e17 = new Element("fechaAlta").setText(""+alta);
			Element e18 = new Element("division").setText(pcSubdivision);
			Element e19 = new Element("cveSuperior").setText(pcCveSuperior+"");
			Element e20 = new Element("cvePuesto").setText(pcCvePuesto+"");
			Element e21 = new Element("cveDivision").setText(pcCveSubdiv+"");
			Element e22 = new Element("digito").setText(pcDigVerif+"");
			Element e23 = new Element("nivelVentas").setText(pcNivelVentas+"");
			Element e24 = new Element("tipoComision").setText(""+pcTipoComision);
			Element e25 = new Element("claveOta").setText(pcCveVendedor+"-"+pcDigVerif);
			String baja = (Fecha.dateToString(this.pcFchBaja) == null) ? "" : Fecha.dateToString(this.pcFchBaja);
			Element e26 = new Element("fechaBaja").setText(""+baja);
			Element e27 = new Element("habHorario").setText(horario);
			Element e28 = new Element("tipoVendedor").setText(pcTipoVendedor);
			
			//Agregamos nodos a la raiz
			root.addContent(e1);
			root.addContent(e22);
			root.addContent(e2);
			root.addContent(e3);
			root.addContent(e4);
			root.addContent(e5);
			root.addContent(e6);
			root.addContent(e7);
			root.addContent(e8);
			root.addContent(e9);
			root.addContent(e10);
			root.addContent(e11);
			root.addContent(e20);
			root.addContent(e12);
			root.addContent(e19);
			root.addContent(e13);
			root.addContent(e14);
			root.addContent(e15);
			root.addContent(e16);
			root.addContent(e17);
			root.addContent(e21);
			root.addContent(e18);
			root.addContent(e23);
			root.addContent(e24);
			root.addContent(e25);
			root.addContent(e26);
			root.addContent(e27);
			root.addContent(e28);
			
			//Creamos el documento
			doc = new Document(root);
		}catch(Exception e){
			e.printStackTrace();
		}
		return doc;
		
	}
	/**
	 * @return
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param string
	 */
	public void setHorario(String string) {
		horario = string;
	}

	/**
	 * @return
	 */
	public String getPcTipoVendedor() {
		return pcTipoVendedor;
	}

	/**
	 * @param string
	 */
	public void setPcTipoVendedor(String string) {
		pcTipoVendedor = string;
	}

}
