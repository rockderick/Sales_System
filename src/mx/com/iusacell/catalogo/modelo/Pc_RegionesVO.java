/*
 * Creado el 22/02/2005
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
public class Pc_RegionesVO implements java.io.Serializable {

//	SELECT PC_REGION.PC_CVE_REGION CVE_REGION, PC_REGION.PC_DESC_REGION DESC_REGION 
//	FROM PC_REGION 

	private String pcCveRegion;
	private String pcDescRegion;

	//	Empty Constructor
	public Pc_RegionesVO() {
	} 

	//	Real Constructor 
	public Pc_RegionesVO( String pcCveRegion, String pcDescRegion){
		setPcCveRegion(pcCveRegion);
		setPcDescRegion(pcDescRegion);

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
 * @param string
 */
public void setPcCveRegion(String string) {
	pcCveRegion = string;
}

	/**
	 * @param string
	 */
	public void setPcDescRegion(String string) {
		pcDescRegion = string;
	}

}


	

