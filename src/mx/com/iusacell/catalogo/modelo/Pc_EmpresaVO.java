/*
 * Created on Aug 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Pc_EmpresaVO {
	
	private int pcCveEmpresa=0;
	private String pcDescEmpresa = ""; 
	private String pcTipoEmpresa= null; 
	private int pcNroCompania=0; 
	
	

	/**
	 * @return
	 */
	public int getPcCveEmpresa() {
		return pcCveEmpresa;
	}

	/**
	 * @return
	 */
	public String getPcDescEmpresa() {
		return pcDescEmpresa;
	}

	/**
	 * @return
	 */
	public int getPcNroCompania() {
		return pcNroCompania;
	}

	/**
	 * @return
	 */
	public String getPcTipoEmpresa() {
		return pcTipoEmpresa;
	}

	/**
	 * @param i
	 */
	public void setPcCveEmpresa(int i) {
		pcCveEmpresa = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescEmpresa(String string) {
		pcDescEmpresa = string;
	}

	/**
	 * @param i
	 */
	public void setPcNroCompania(int i) {
		pcNroCompania = i;
	}

	/**
	 * @param string
	 */
	public void setPcTipoEmpresa(String string) {
		pcTipoEmpresa = string;
	}

}
