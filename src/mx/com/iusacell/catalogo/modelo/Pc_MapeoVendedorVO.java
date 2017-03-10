/*
 * Creado el 10/03/2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Dvazqueza
 *
 * Paquete : mx.com.iusacell.catalogo.modelo
 * Proyecto : AdminCatalogoWeb
 *
 * Derechos Reservados Iusacell SA de CV
 */
public class Pc_MapeoVendedorVO implements java.io.Serializable {

//	SELECT PC_REGION.PC_CVE_REGION CVE_REGION, PC_REGION.PC_DESC_REGION DESC_REGION 
//	FROM PC_REGION 


	private int pcCveVendedor;
	private String pcSistema;
	private int pcCustomerId;
	private String pcCveRegVenta;
	private String pcNomUsuario;

	private String pcCveCompuesta;
	private String pcSubdivision;
	//	Empty Constructor
	public Pc_MapeoVendedorVO() {
	} 

	//	Real Constructor 
	public Pc_MapeoVendedorVO(int pcCveVendedor,
	 			String pcSistema,
	 			int pcCustomerId,
	 			String pcCveRegVentas,
	 			String pcNomUsuario,
	 			String pcCveCompuesta){
		setPcCveVendedor(pcCveVendedor);
		setPcSistema(pcSistema);
		setPcCustomerId(pcCustomerId);
		setPcCveRegVenta(pcCveRegVentas);
		setPcNomUsuario(pcNomUsuario);
		setPcCveCompuesta(pcCveCompuesta);
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
		String string = (this.pcCveRegVenta == null)? "":this.pcCveRegVenta;
		return string;
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
		String string = (this.pcNomUsuario == null)? "":this.pcNomUsuario;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcSistema() {
		String string = (this.pcSistema == null)? "":this.pcSistema;
		return string;
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
	 * @return
	 */
	public String getPcCveCompuesta() {
		String claveCompuesta = this.pcSistema + "/" + this.pcCveRegVenta;
		return claveCompuesta;
	}

	/**
	 * @param string
	 */
	public void setPcCveCompuesta(String string) {
		pcCveCompuesta = string;
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

}


	

