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
public class Pc_PerfilesVO implements java.io.Serializable {

	private int pcCvePerfil;

	private int pcCveAccion;
	private String pcDescAccion;

	private int pcCveRol;
	private String pcDescRol;

	//	Empty Constructor
	public Pc_PerfilesVO() {
	} 

	//	Real Constructor 
	public Pc_PerfilesVO( int pcCveRol, String pcDescRol,
						  int pcCvePerfil, int pcCveAccion, 
						  String pcDescAccion){
		setPcCveRol(pcCveRol);
		setPcDescRol(pcDescRol);
		setPcCvePerfil(pcCvePerfil); 
		setPcCveAccion(pcCveAccion); 
		setPcDescAccion(pcDescAccion);
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

	/**
	 * @return
	 */
	public int getPcCveAccion() {
		return pcCveAccion;
	}

	/**
	 * @return
	 */
	public int getPcCvePerfil() {
		return pcCvePerfil;
	}

	/**
	 * @return
	 */
	public String getPcDescAccion() {
		return pcDescAccion;
	}

	/**
	 * @param i
	 */
	public void setPcCveAccion(int i) {
		pcCveAccion = i;
	}

	/**
	 * @param i
	 */
	public void setPcCvePerfil(int i) {
		pcCvePerfil = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescAccion(String string) {
		pcDescAccion = string;
	}

}


	

