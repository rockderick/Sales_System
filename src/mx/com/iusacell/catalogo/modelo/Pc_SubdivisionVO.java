/*
 * Creado el 10/03/2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Dvazqueza
 *
 * Paquete : mx.com.iusacell.catalogo.modelo
 * Proyecto : CatalogoVendedoresWeb
 *
 * Derechos Reservados Iusacell SA de CV
 */
public class Pc_SubdivisionVO implements java.io.Serializable {

	private int pcCveDiv;
	private int pcCveSubdiv;
	private String pcDescSubdiv;
	
	//Regiones
	private String pcCveRegion;
	private String pcDescRegion;
	
	//Estados
	private int pcCveEstado;
	private String pcDescEstado;
	
	//	Empty Constructor
	public Pc_SubdivisionVO() {
	} 

	//	Real Constructor 
	public Pc_SubdivisionVO( int pcCveDiv, int pcCveSubdiv, String pcDescSubdiv){
		setPcCveDiv(pcCveDiv);
		setPcDescSubdiv(pcDescSubdiv);
		setPcCveSubdiv(pcCveSubdiv);
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
	public String getPcCveRegion() {
		return pcCveRegion;
	}

	/**
	 * @return
	 */
	public String getPcDescRegion() {
		return pcDescRegion;
	}

	/**
	 * @param i
	 */
	public void setPcCveRegion(String i) {
		pcCveRegion = i;
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
	public String getPcDescEstado() {
		return pcDescEstado;
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
	public int getPcCveEstado() {
		return pcCveEstado;
	}

	/**
	 * @param i
	 */
	public void setPcCveEstado(int i) {
		pcCveEstado = i;
	}

}


	

