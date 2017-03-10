/*
 * Created on Feb 25, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Dvazqueza
 *
 */
public class Pc_CanalVO implements java.io.Serializable{

	String pcCveCanal;
	String pcCveOta;
	String pcDescCanal;
	String pcCveTpCanal;
	String pcStatus;
	
	//Empty Constructor
	public Pc_CanalVO() {
	}

	//Real Constructor
	public Pc_CanalVO(String pcCveCanal, String pcDescCanal, String pcCveTpCanal, String pcCveOta) {
		setPcCveCanal(pcCveCanal);
		setPcDescCanal(pcDescCanal);
		setPcCveTpCanal(pcCveTpCanal);
		setPcCveOta(pcCveOta);
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
	public String getPcCveTpCanal() {
		return pcCveTpCanal;
	}

	/**
	 * @return
	 */
	public String getPcDescCanal() {
		return pcDescCanal;
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
	public void setPcCveTpCanal(String string) {
		pcCveTpCanal = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescCanal(String string) {
		pcDescCanal = string;
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
	public void setPcStatus(String string) {
		pcStatus = string;
	}

	/**
	 * @return
	 */
	public String getPcCveOta() {
		return pcCveOta;
	}

	/**
	 * @param string
	 */
	public void setPcCveOta(String string) {
		pcCveOta = string;
	}

}
