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
public class Pc_DivisionVO implements java.io.Serializable {

//	SELECT PC_REGION.PC_CVE_REGION CVE_REGION, PC_REGION.PC_DESC_REGION DESC_REGION 
//	FROM PC_REGION 

	private int pcCveDiv;
	private String pcDescDiv;

	//	Empty Constructor
	public Pc_DivisionVO() {
	} 

	//	Real Constructor 
	public Pc_DivisionVO( int pcCveDiv, String pcDescDiv){
		setPcCveDiv(pcCveDiv);
		setPcDescDiv(pcDescDiv);

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

}


	

