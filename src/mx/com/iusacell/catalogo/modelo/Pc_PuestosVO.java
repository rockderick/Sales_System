/*
 * Created on Feb 14, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author DVAZQUEZA
 *
 */
public class Pc_PuestosVO implements java.io.Serializable{
	
//SELECT PC_PUESTOS.PC_CVE_PUESTO AS CLAVE, PC_PUESTOS.PC_DESC_PUESTO AS PUESTO FROM PC_PUESTOS;
	
	private int pcCvePuesto;
	private String pcDescPuesto;
	private int pcPrcntjComis;
	private String pcHorario;
	
	//	Empty Constructor
	public Pc_PuestosVO() {
	} 
	
	//	Real Constructor 
	public Pc_PuestosVO( int pcCvePuesto, String pcDescPuesto, int pcPrcntjComis){
		setPcCvePuesto(pcCvePuesto);
		setPcDescPuesto(pcDescPuesto);
		setPcPrcntjComis(pcPrcntjComis);
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
	public String getPcDescPuesto() {
		return pcDescPuesto;
	}

/**
 * @param value
 */
public void setPcCvePuesto(int value) {
	pcCvePuesto = value;
}

	/**
	 * @param string
	 */
	public void setPcDescPuesto(String string) {
		pcDescPuesto = string;
	}

	/**
	 * @return
	 */
	public int getPcPrcntjComis() {
		return pcPrcntjComis;
	}

	/**
	 * @param i
	 */
	public void setPcPrcntjComis(int i) {
		pcPrcntjComis = i;
	}

	/**
	 * @return
	 */
	public String getPcHorario() {
		return pcHorario;
	}

	/**
	 * @param string
	 */
	public void setPcHorario(String string) {
		pcHorario = string;
	}

}
