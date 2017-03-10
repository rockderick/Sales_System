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
public class Pc_RolesDivVO implements java.io.Serializable {

	private int pcCveRolDiv; 
	private String pcUserid;
	private int pcCveRol;
	private String pcDescRol;
	private int pcCveDiv;
	private String pcDescDiv;
	private String pcDescSubdiv;
	private int pcStatus;
	private String pcDescStatus;

	//	Empty Constructor
	public Pc_RolesDivVO() {
	} 

	//	Real Constructor 
	public Pc_RolesDivVO(  int pcCveRolDiv,	
	                       String pcUserid, 
	                       int pcCveRol,
	                       int pcCveDiv,
						   String pcDescRol, 
						   String pcDescDiv,
	                       int pcStatus,
	                       String pcDescStatus){
		setPcCveRolDiv(pcCveRolDiv); 
		setPcUserid(pcUserid);
		setPcCveRol(pcCveRol);
		setPcCveDiv(pcCveDiv);
		setPcDescRol(pcDescRol);
		setPcDescDiv(pcDescDiv);
		setPcStatus(pcStatus);
		setPcDescStatus(pcDescStatus);
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
	public int getPcCveRol() {
		return pcCveRol;
	}

/**
 * @return
 */
public int getPcCveRolDiv() {
	return pcCveRolDiv;
}

	/**
	 * @return
	 */
	public int getPcStatus() {
		return pcStatus;
	}

	/**
	 * @return
	 */
	public String getPcUserid() {
		return pcUserid;
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
	public void setPcCveRol(int i) {
		pcCveRol = i;
	}

/**
 * @param i
 */
public void setPcCveRolDiv(int i) {
	pcCveRolDiv = i;
}

	/**
	 * @param i
	 */
	public void setPcStatus(int i) {
		pcStatus = i;
	}

	/**
	 * @param string
	 */
	public void setPcUserid(String string) {
		pcUserid = string;
	}

	/**
	 * @return
	 */
	public String getPcDescDiv() {
		return pcDescDiv;
	}

	/**
	 * @return
	 */
	public String getPcDescRol() {
		return pcDescRol;
	}

	/**
	 * @param string
	 */
	public void setPcDescDiv(String string) {
		pcDescDiv = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescRol(String string) {
		pcDescRol = string;
	}

	/**
	 * @return
	 */
	public String getPcDescStatus() {
		return pcDescStatus;
	}

	/**
	 * @param string
	 */
	public void setPcDescStatus(String string) {
		pcDescStatus = string;
	}

	/**
	 * @return
	 */
	public String getPcDescSubdiv() {
		return pcDescSubdiv;
	}

	/**
	 * @param string
	 */
	public void setPcDescSubdiv(String string) {
		pcDescSubdiv = string;
	}

}


	

