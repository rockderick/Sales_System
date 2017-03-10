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
public class Pc_Inter_SubdivisionVO implements java.io.Serializable {

	private int pcCveSubdiv;
	private int pcCveVendedor;

	//	Empty Constructor
	public Pc_Inter_SubdivisionVO() {
	} 

	//	Real Constructor 
	public Pc_Inter_SubdivisionVO( int pcCveDiv, int pcCveVendedor){
		setPcCveSubdiv(pcCveSubdiv);
		setPcCveVendedor(pcCveVendedor);
	}	

	/**
	 * @param i
	 */
	public void setPcCveSubdiv(int i) {
		pcCveSubdiv = i;
	}


	/**
	 * @return
	 */
	public int getPcCveSubdiv() {
		return pcCveSubdiv;
	}

	/**
	 * @return
	 */
	public int getPcCveVendedor() {
		return pcCveVendedor;
	}

	/**
	 * @param i
	 */
	public void setPcCveVendedor(int i) {
		pcCveVendedor = i;
	}

}


	

