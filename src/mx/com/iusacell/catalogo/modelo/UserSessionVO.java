/*
 * Created on Feb 25, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Dvazqueza
 *
 */
import java.util.ArrayList;
import java.util.Date;


public class UserSessionVO implements java.io.Serializable{
	
	private String pcUserid;
	private String pcNombreCuenta;
	private int pcCveDiv;
	private int pcCveSubdiv;
	private String pcCvesDivs;
	private int pcCveRol;
	private int pcCveUsuario;
	private ArrayList pcAcciones; 
	private int pcCveVendedor;
	private Date pc_expirationdate;
	private boolean pcValidadoConfig;
	
	
	
	//	Empty Constructor
	public UserSessionVO() {
	} 
	
	//	Real Constructor 
	public UserSessionVO(String pcUserid,
                         String pcNombreCuenta,
		                 int pcCveDiv,
						 int pcCveSubdiv,
		                 int pcCveRol,
		                 int pcCveUsuario,
		                 int pcCveVendedor,
		                 Date pc_expirationDate){
		setPcUserid(pcUserid);
		setPcNombreCuenta(pcNombreCuenta);
		setPcCveDiv(pcCveDiv);
		setPcCveSubdiv(pcCveSubdiv);
		setPcCveRol(pcCveRol);
		setPcCveUsuario(pcCveUsuario);
		setPcCveVendedor(pcCveVendedor);
		setPc_expirationdate(pc_expirationDate);
		setPcValidadoConfig(pcValidadoConfig);
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
	public String getPcNombreCuenta() {
		return pcNombreCuenta;
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
	 * @param string
	 */
	public void setPcNombreCuenta(String string) {
		pcNombreCuenta = string;
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
	public ArrayList getPcAcciones() {
		return pcAcciones;
	}

	/**
	 * @param list
	 */
	public void setPcAcciones(ArrayList list) {
		pcAcciones = list;
	}

	/**
	 * @return
	 */
	public int getPcCveUsuario() {
		return pcCveUsuario;
	}

	/**
	 * @param i
	 */
	public void setPcCveUsuario(int i) {
		pcCveUsuario = i;
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

	/**
	 * @return
	 */
	public int getPcCveSubdiv() {
		return pcCveSubdiv;
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
	public Date getPc_expirationdate() {
		return pc_expirationdate;
	}

	/**
	 * @param date
	 */
	public void setPc_expirationdate(Date date) {
		pc_expirationdate = date;
	}

	/**
	 * @return
	 */
	public String getPcCvesDivs() {
		return pcCvesDivs;
	}

	/**
	 * @param string
	 */
	public void setPcCvesDivs(String string) {
		pcCvesDivs = string;
	}

	/**
	 * @return
	 */
	public boolean isPcValidadoConfig() {
		return pcValidadoConfig;
	}

	/**
	 * @param b
	 */
	public void setPcValidadoConfig(boolean b) {
		pcValidadoConfig = b;
	}

}
