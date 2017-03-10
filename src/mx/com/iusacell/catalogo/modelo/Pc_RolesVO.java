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
public class Pc_RolesVO implements java.io.Serializable {

//	SELECT PC_REGION.PC_CVE_REGION CVE_REGION, PC_REGION.PC_DESC_REGION DESC_REGION 
//	FROM PC_REGION 

	private int pcCveRol;
	private String pcDescRol;

	//	Empty Constructor
	public Pc_RolesVO() {
	} 

	//	Real Constructor 
	public Pc_RolesVO( int pcCveRol, String pcDescRol){
		setPcCveRol(pcCveRol);
		setPcDescRol(pcDescRol);

	}	
/**
 * @return
 */
public int getPcCveRol() {
	return pcCveRol;
}

	/**
	 * @return
	 */
	public String getPcDescRol() {
		return pcDescRol;
	}

/**
 * @param i
 */
public void setPcCveRol(int i) {
	pcCveRol = i;
}

	/**
	 * @param string
	 */
	public void setPcDescRol(String string) {
		pcDescRol = string;
	}

}


	

