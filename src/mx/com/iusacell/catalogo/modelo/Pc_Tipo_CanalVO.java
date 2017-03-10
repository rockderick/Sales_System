/*
 * Created on Feb 23, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author DVAZQUEZA
 *
 */
public class Pc_Tipo_CanalVO implements java.io.Serializable{


	private String pcCveTpCanal;
	private String pcDescTpCanal;
	
	//	Empty Constructor
	public Pc_Tipo_CanalVO() {
	} 
	
	//	Real Constructor 
	public Pc_Tipo_CanalVO(String pcCveTpCanal,String pcDescTpCanal){
		setPcCveTpCanal(pcCveTpCanal);
		setPcDescTpCanal(pcDescTpCanal);
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
	public String getPcDescTpCanal() {
		return pcDescTpCanal;
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

}
