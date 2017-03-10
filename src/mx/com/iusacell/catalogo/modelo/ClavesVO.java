/*
 * Created on Feb 25, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

import java.util.Date;

/**
 * @author Dvazqueza
 *
 */
public class ClavesVO implements java.io.Serializable{
	
	private int clave;
	private Date fecha;
	private int pcTpMov;	
	//	Empty Constructor
	public ClavesVO() {
	} 
	
	//	Real Constructor 
	public ClavesVO( int intClave, int pcTpMov, Date fecha){
		setClave(intClave);
		setPcTpMov(pcTpMov);
		setFecha(fecha);
	}	
	
//	Real Constructor 
	public ClavesVO( int intClave){
		setClave(intClave);

	}	

	/**
	 * @return
	 */
	public int getClave() {
		return clave;
	}

	/**
	 * @param i
	 */
	public void setClave(int i) {
		clave = i;
	}

	/**
	 * @return
	 */
	public int getPcTpMov() {
		return pcTpMov;
	}

	/**
	 * @param i
	 */
	public void setPcTpMov(int i) {
		pcTpMov = i;
	}

	/**
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param date
	 */
	public void setFecha(Date date) {
		fecha = date;
	}

}
