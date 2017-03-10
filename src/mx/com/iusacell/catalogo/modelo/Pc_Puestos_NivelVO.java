/*
 * Created on Feb 14, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author DVAZQUEZA
 *
 */
public class Pc_Puestos_NivelVO implements java.io.Serializable{
	
//SELECT PC_PUESTOS.PC_CVE_PUESTO AS CLAVE, PC_PUESTOS.PC_DESC_PUESTO AS PUESTO FROM PC_PUESTOS;
	
	private int pcPuestosNivelId;
	private int pcCvePuesto;
	private int pcCvePuestoSup;
	
	//	Empty Constructor
	public Pc_Puestos_NivelVO() {
	} 
	
	//	Real Constructor 
	public Pc_Puestos_NivelVO(int pcPuestosNivelId,	int pcCvePuesto, int pcCvePuestoSup){
		setPcCvePuesto(pcCvePuesto);
		setPcPuestosNivelId(pcPuestosNivelId);
		setPcCvePuestoSup(pcCvePuestoSup);
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
	public int getPcCvePuestoSup() {
		return pcCvePuestoSup;
	}

/**
 * @return
 */
public int getPcPuestosNivelId() {
	return pcPuestosNivelId;
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
	public void setPcCvePuestoSup(int i) {
		pcCvePuestoSup = i;
	}

/**
 * @param i
 */
public void setPcPuestosNivelId(int i) {
	pcPuestosNivelId = i;
}

}
