/*
 * Created on Feb 25, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Dvazqueza
 *
 */
public class Pc_AccionesVO implements java.io.Serializable{

	private String pcCveAccion;
	private String pcDescAccion;
	private String pcUrlAccion;
	
	//Empty Constructor
	public Pc_AccionesVO() {
	}

	//Real Constructor
	public Pc_AccionesVO(String pcCveAccion,
						 String pcDescAccion,
						 String pcUrlAccion) {
		setPcCveAccion(pcCveAccion);
		setPcDescAccion(pcDescAccion);
		setPcUrlAccion(pcUrlAccion);
	}
	


	/**
	 * @return
	 */
	public String getPcCveAccion() {
		return pcCveAccion;
	}

	/**
	 * @return
	 */
	public String getPcDescAccion() {
		return pcDescAccion;
	}

	/**
	 * @return
	 */
	public String getPcUrlAccion() {
		return pcUrlAccion;
	}

	/**
	 * @param string
	 */
	public void setPcCveAccion(String string) {
		pcCveAccion = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescAccion(String string) {
		pcDescAccion = string;
	}

	/**
	 * @param string
	 */
	public void setPcUrlAccion(String string) {
		pcUrlAccion = string;
	}

}
