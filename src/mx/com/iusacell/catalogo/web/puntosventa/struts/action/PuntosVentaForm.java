/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.puntosventa.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import java.io.Serializable;
/**
 * @author Dvazqueza
 *
 */
public class PuntosVentaForm extends ValidatorForm implements Serializable{
	private String action = null;
	private String boton;

	
	private String pcCvePtoventas="0";
	private String pcCveReferencia; 
	private String pcCveCanal; 
	private String pcCveRegion; 
	private String pcNomPtoventas; 
	private String pcCntlInt; 
	private String pcDescCanal; 
	private String pcDescRegion; 
	private String pcCveTipoCanal; 
	private String pcDescTipoCanal; 
	private int pcCveDiv; 
	private String pcDescDiv; 
	private String pcDireccion;
	private String pcColonia;
	private String pcCp;
	private String pcTel;
	private String pcFax;
	private String pcJefeTienda;
	
	private String pcCveTipoCanalSeek; 
	private String pcCveRegionSeek; 
	private String pcCveCanalSeek; 
	private String pcCvePtoventasSeek;
	private int pcCveDivSeek;
	private String pcNomPtoventasSeek;
	private String pcCveNumEconomico;

	private int pcCveSubdiv; 
	private String pcDescSubdiv; 
	
	//agregado 15/09/2005
	private int pcDigVerif;
	private String pcStatus;
	private String pcCiudad;
	private String pcEstado;
	private String pcTipoRegistro;
	private String pcCveMarca;
	private String marcasCombo;
	private int pcCveCiudad; 
	private int pcCveEstado;
	private String pcDescCiudad;
	private String pcDescEstado;
	
	//agregado 28/08/2008
	private String pcFchIniOp;
	private String pcFchAlta;
	private String pcFchBaja;
	private String pcFchReactvcn;
	
	//Empty Constructor
	public PuntosVentaForm() {
		
		setPcCvePtoventas(""); 
		setPcCveReferencia(null); 
		setPcCveCanal(null); 
		setPcCveRegion(null); 
		setPcNomPtoventas(null); 
		setPcCntlInt(null); 
		setPcDescCanal(null); 
		setPcDescRegion(null); 
		setPcCveTipoCanal(null); 
		setPcDescTipoCanal(null); 
		setPcCveTipoCanalSeek(null); 
		setPcCveRegionSeek(null); 
		setPcCveCanalSeek(null); 
		setPcCvePtoventasSeek(null);
		setPcNomPtoventasSeek(null); 
		setPcCveDiv(0); 
		setPcCveDivSeek(0);
		setPcDescDiv(null);
		setPcCveSubdiv(0); 
		setPcDescSubdiv(null);
		setPcDireccion(null);
		setPcColonia(null);
		setPcCp(null);
		setPcTel(null);
		setPcFax(null);
		setPcJefeTienda(null);
		setPcDigVerif(0);
		setPcStatus(null);
		setPcCiudad(null);
		setPcEstado(null);
		setPcCveCiudad(0);
		setPcCveEstado(0);
		setPcFchIniOp(null);
		setPcFchAlta(null);
		setPcFchBaja(null);
		setPcFchReactvcn(null);
	}

	/**
	 * Reset all properties to their default values.
	 *
	 * @param mapping The mapping used to select this instance
	 * @param request The servlet request we are processing
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		action = null;
		setPcCvePtoventas(""); 
		setPcCveReferencia(null); 
		setPcCveCanal(null); 
		setPcCveRegion(null); 
		setPcNomPtoventas(null); 
		setPcCntlInt(null); 
		setPcDescCanal(null); 
		setPcDescRegion(null); 
		setPcCveTipoCanal(null); 
		setPcDescTipoCanal(null); 
		setPcCveTipoCanalSeek(null); 
		setPcCveRegionSeek(null); 
		setPcCveCanalSeek(null); 
		setPcCvePtoventasSeek(null);
		setPcNomPtoventasSeek(null); 	
		setPcCveDivSeek(0);
		setPcCveDiv(0);
		setPcDescDiv(null);
		setPcCveSubdiv(0);
		setPcDescSubdiv(null);
		setPcDireccion(null);
		setPcColonia(null);
		setPcCp(null);
		setPcTel(null);
		setPcFax(null);
		setPcJefeTienda(null);
		setPcDigVerif(0);
		setPcStatus(null);
		setPcCiudad(null);
		setPcEstado(null);
		setPcCveCiudad(0);
		setPcCveEstado(0);
		setPcFchIniOp(null);
	    setPcFchAlta(null);
		setPcFchBaja(null);
		setPcFchReactvcn(null);
	}	

	/**
	 * @return
	 */
	public String getPcCntlInt() {
		return pcCntlInt;
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
	public String getPcCveReferencia() {
		return pcCveReferencia;
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
	public String getPcNomPtoventas() {
		return pcNomPtoventas;
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
	public String getPcTel() {
		return pcTel;
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
	public String getPcJefeTienda() {
		return pcJefeTienda;
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
	public void setPcCvePtoventas(String string) {
		pcCvePtoventas = string;
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
	 * @param string
	 */
	public void setPcDireccion(String string) {
		pcDireccion = string;
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
	public void setPcTel(String string) {
		pcTel = string;
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
	public void setPcJefeTienda(String string) {
		pcJefeTienda = string;
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
	public String getPcCveTipoCanal() {
		return pcCveTipoCanal;
	}

	/**
	 * @return
	 */
	public String getPcDescTipoCanal() {
		return pcDescTipoCanal;
	}

	/**
	 * @param string
	 */
	public void setPcCveTipoCanal(String string) {
		pcCveTipoCanal = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescTipoCanal(String string) {
		pcDescTipoCanal = string;
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
	public String getPcCveRegionSeek() {
		return pcCveRegionSeek;
	}

	/**
	 * @return
	 */
	public String getPcCveTipoCanalSeek() {
		return pcCveTipoCanalSeek;
	}

	/**
	 * @param string
	 */
	public void setPcCveCanalSeek(String string) {
		pcCveCanalSeek = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveRegionSeek(String string) {
		pcCveRegionSeek = string;
	}

	/**
	 * @param string
	 */
	public void setPcCveTipoCanalSeek(String string) {
		pcCveTipoCanalSeek = string;
	}

	/**
	 * @return
	 */
	public String getPcCvePtoventasSeek() {
		return pcCvePtoventasSeek;
	}

	/**
	 * @param string
	 */
	public void setPcCvePtoventasSeek(String string) {
		pcCvePtoventasSeek = string;
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
	public void setPcNomPtoventasSeek(String string) {
		pcNomPtoventasSeek = string;
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
	public int getPcCveDiv() {
		return pcCveDiv;
	}

	/**
	 * @return
	 */
	public int getPcCveDivSeek() {
		return pcCveDivSeek;
	}

	/**
	 * @param i
	 */
	public void setPcCveDiv(int i) {
		pcCveDiv = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveDivSeek(int i) {
		pcCveDivSeek = i;
	}

	/**
	 * @return
	 */
	public String getPcDescDiv() {
		return pcDescDiv;
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
	public String getPcCiudad() {
		return pcCiudad;
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
	public String getPcEstado() {
		return pcEstado;
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
	public void setPcCiudad(String string) {
		pcCiudad = string;
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
	public void setPcEstado(String string) {
		pcEstado = string;
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
	public String getPcCveNumEconomico() {
		return pcCveNumEconomico;
	}

	/**
	 * @param string
	 */
	public void setPcCveNumEconomico(String string) {
		pcCveNumEconomico = string;
	}

	/**
	 * @return
	 */
	public String getPcCveMarca() {
		return pcCveMarca;
	}

	/**
	 * @param string
	 */
	public void setPcCveMarca(String string) {
		pcCveMarca = string;
	}



	/**
	 * @return
	 */
	public String getMarcasCombo() {
		return marcasCombo;
	}

	/**
	 * @param string
	 */
	public void setMarcasCombo(String string) {
		marcasCombo = string;
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
	public String getPcFchIniOp() {
		return pcFchIniOp;
	}

	/**
	 * @return
	 */
	public String getPcFchReactvcn() {
		return pcFchReactvcn;
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
