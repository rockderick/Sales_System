/*
 * Created on 23/02/2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Dvazqueza
 *
 */
public class Pc_Punto_VentasVO implements java.io.Serializable{


	private String pcCvePtoventas; 
	private String pcCveReferencia; 
	private String pcCveCanal; 
	private String pcCveRegion; 
	private String pcNomPtoventas; 
	private String pcCntlInt; 

	private String pcCiudad;           
	private String pcEstado;           
	private String pcCp;               
	private String pcTel;             
	private String pcFax;             
	private String pcDireccion;       	
	private String pcColonia;
	private String pcJefeTienda;
	private String pcDescCiudad;
	private String pcDescEstado; 
	private int pcCveCiudad; 
	private int pcCveEstado;  
	  
	private String pcDescCanal; 
	private String pcDescRegion; 
	private String pcCveTpCanal; 
	private String pcDescTpCanal; 
	
	private int pcCveDiv; 
	private String pcDescDiv; 

	private int pcCveSubdiv; 
	private String pcDescSubdiv; 
	
	//agregado 15/09/2005
	private int pcDigVerif;
	private String pcStatus = "";
	
	//agregado 27/08/08
	private String pcFchIniOp;
	private String pcFchAlta;
	private String pcFchBaja;
	private String pcFchReactvcn;
	
	//	Empty Constructor
	public Pc_Punto_VentasVO() {
	}    
	
	//	Real Constructor
	public Pc_Punto_VentasVO( 
			String pcCvePtoventas,
			String pcCveReferencia, 
			String pcCveCanal,
			String pcCveRegion,
			String pcNomPtoventas, 
			String pcCntlInt,
			String pcCiudad,           
			String pcEstado,  
			int pcCveCiudad,    
			int pcCveEstado,  
			String pcCp,               
			String pcTel,              
			String pcFax,             
			String pcDireccion,       	
			String pcColonia,  
			String pcDescCanal,
			String pcDescRegion,
			String pcCveTpCanal, 
			String pcDescTpCanal,
			String pcDescCiudad,
			String pcDescEstado, 
			String pcJefeTienda,
			int pcCveDiv,
			String pcDescDiv,
			int pcCveSubdiv,
			String pcDescSubdiv,
			String pcFchIniOp,
			String pcFchBaja,
			String pcFchAlta,
			String pcFchReactvcn){

		setPcCvePtoventas(pcCvePtoventas); 
		setPcCveReferencia(pcCveReferencia); 
		setPcCveCanal(pcCveCanal); 
		setPcCveRegion(pcCveRegion); 
		setPcNomPtoventas(pcNomPtoventas); 
		setPcCntlInt(pcCntlInt); 
		
		setPcCiudad(pcCiudad);
		setPcEstado(pcEstado);
		setPcCveCiudad(pcCveCiudad);
		setPcCveEstado(pcCveEstado);
		setPcCp(pcCp);
		setPcTel(pcTel);
		setPcFax(pcFax);
		setPcDireccion(pcDireccion);
		setPcColonia(pcColonia);
		setPcDescCiudad(pcDescCiudad);
		setPcDescEstado(pcDescEstado);
		setPcJefeTienda(pcJefeTienda);
		
		setPcDescCanal(pcDescCanal); 
		setPcDescRegion(pcDescRegion);
		setPcCveTpCanal(pcCveTpCanal); 
		setPcDescTpCanal(pcDescTpCanal); 

		setPcCveDiv(pcCveDiv); 
		setPcDescDiv(pcDescDiv); 

		setPcCveSubdiv(pcCveSubdiv); 
		setPcDescSubdiv(pcDescSubdiv);
		
		setPcFchIniOp(pcFchIniOp);
		setPcFchBaja(pcFchBaja);
		setPcFchAlta(pcFchAlta);
		setPcFchReactvcn(pcFchReactvcn);
		 
    }

	/**
	 * @return
	 */
	public String getPcCntlInt() {
		String string = (this.pcCntlInt == null)? "":this.pcCntlInt ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCveCanal() {
		String string = (this.pcCveCanal == null)? "":this.pcCveCanal ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCveReferencia() {
		String string = (this.pcCveReferencia == null)? "":this.pcCveReferencia ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCveRegion() {
		String string = (this.pcCveRegion == null)? "":this.pcCveRegion ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcNomPtoventas() {
		String string = (this.pcNomPtoventas == null)? "":this.pcNomPtoventas ;
		return string;
	}

	/**
	 * @param string
	 */
	public void setPcCntlInt(String string) {
		pcCntlInt = string;
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
	public void setPcCveReferencia(String string) {
		pcCveReferencia = string;
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
	public void setPcNomPtoventas(String string) {
		pcNomPtoventas = string;
	}

	/**
	 * @return
	 */
	public String getPcDescCanal() {
		String string = (this.pcDescCanal == null)? "":this.pcDescCanal ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcDescRegion() {
		String string = (this.pcDescRegion == null)? "":this.pcDescRegion ;
		return string;
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
	 * @return
	 */
	public String getPcCveTpCanal() {
		String string = (this.pcCveTpCanal == null)? "":this.pcCveTpCanal ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcDescTpCanal() {
		String string = (this.pcDescTpCanal == null)? "":this.pcDescTpCanal;
		return string;
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
	public void setPcDescTpCanal(String string) {
		pcDescTpCanal = string;
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
	public String getPcColonia() {
		String string = (this.pcColonia == null)? "":this.pcColonia ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcCp() {
		String string = (this.pcCp == null)? "":this.pcCp ;
		return string;
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
	public String getPcTel() {
		String string = (this.pcTel == null)? "":this.pcTel ;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcJefeTienda() {
		String string = (this.pcJefeTienda == null)? "":this.pcJefeTienda ;
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
	public void setPcCvePtoventas(String string) {
		pcCvePtoventas = string;
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
	 * @param string
	 */
	public void setPcJefeTienda(String string) {
		pcJefeTienda = string;
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
	public String getPcDescDiv() {
		String string = (this.pcDescDiv == null)? "":this.pcDescDiv ;
		return string;
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
	public int getPcCveSubdiv() {
		return pcCveSubdiv;
	}

	/**
	 * @return
	 */
	public String getPcDescSubdiv() {
		return pcDescSubdiv;
	}

	/**
	 * @param i
	 */
	public void setPcCveSubdiv(int i) {
		pcCveSubdiv = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescSubdiv(String string) {
		pcDescSubdiv = string;
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
	public String getPcStatus() {
		return pcStatus;
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
	public void setPcStatus(String string) {
		pcStatus = string;
	}

	/**
	 * @return
	 */
	public String getPcFchAlta() {
		String string = (this.pcFchAlta == null)? "":this.pcFchAlta;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcFchBaja() {
		String string = (this.pcFchBaja == null)? "":this.pcFchBaja;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcFchIniOp() {
		String string = (this.pcFchIniOp == null)? "":this.pcFchIniOp;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcFchReactvcn() {
		String string = (this.pcFchReactvcn == null)? "":this.pcFchReactvcn;
		return string;
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
	public void setPcFchIniOp(String string) {
		pcFchIniOp = string;
	}

	/**
	 * @param string
	 */
	public void setPcFchReactvcn(String string) {
		pcFchReactvcn = string;
	}

	

	/**
	 * @return
	 */
	public String getPcDescCiudad() {
		return pcDescCiudad;
	}

	/**
	 * @return
	 */
	public String getPcDescEstado() {
		return pcDescEstado;
	}

	
	/**
	 * @param string
	 */
	public void setPcDescCiudad(String string) {
		pcDescCiudad = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescEstado(String string) {
		pcDescEstado = string;
	}

	/**
	 * @return
	 */
	public int getPcCveCiudad() {
		return pcCveCiudad;
	}

	/**
	 * @return
	 */
	public int getPcCveEstado() {
		return pcCveEstado;
	}

	/**
	 * @param i
	 */
	public void setPcCveCiudad(int i) {
		pcCveCiudad = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveEstado(int i) {
		pcCveEstado = i;
	}

}

