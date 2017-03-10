/*
 * Created on Feb 25, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Dvazqueza
 *
 */
public class DirectorDivVO implements java.io.Serializable{
	
	private int pcCveVendedor;
	private String pcNombreVendedor;
	private int pcCvePuesto;
	private String pcDescPuesto;
	private int pcCveDiv;
	private String pcDescDiv;
		
	//	Empty Constructor
	public DirectorDivVO() {
	} 
	
	//	Real Constructor 
	public DirectorDivVO(int pcCveVendedor,
			String pcNombreVendedor,
			int pcCvePuesto,
			String pcDescPuesto,
			int pcCveDiv,
			String pcDescDiv){
		setPcCveVendedor(pcCveVendedor);
		setPcNombreVendedor(pcNombreVendedor);
		setPcCvePuesto(pcCvePuesto);
		setPcDescPuesto(pcDescPuesto);
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
	public int getPcCvePuesto() {
		return pcCvePuesto;
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
	public String getPcDescDiv() {
		String string = (this.pcDescDiv == null)? "":this.pcDescDiv;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcDescPuesto() {
		String string = (this.pcDescPuesto == null)? "":this.pcDescPuesto;
		return string;
	}

	/**
	 * @return
	 */
	public String getPcNombreVendedor() {
		String string = (this.pcNombreVendedor == null)? "":this.pcNombreVendedor;
		return string;
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
	public void setPcCvePuesto(int i) {
		pcCvePuesto = i;
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
	public void setPcDescDiv(String string) {
		pcDescDiv = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescPuesto(String string) {
		pcDescPuesto = string;
	}

	/**
	 * @param string
	 */
	public void setPcNombreVendedor(String string) {
		pcNombreVendedor = string;
	}

}
