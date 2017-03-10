/*
 * Created on Feb 25, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;
import java.util.Date;
import mx.com.iusacell.catalogo.utilerias.Fecha;

/**
 * @author Dvazqueza
 *
 */
public class Pc_LoginVO implements java.io.Serializable{

	private String pcUserid;
	private String pcPassword;
	private String pcNombreCuenta;
	private int pcCveUsuario;
	private String pcDescRol;
	private int pcIsblocked;
	private Date pcLogindate;
	private String pcLoginDateStr;
	private Date pcExpirationdate;
	private String pcExpirationDateStr;
	private int pcCambiaPassword;
	
	//Empty Constructor
	public Pc_LoginVO() {
	}

	//Real Constructor
	public Pc_LoginVO(String pcUserid, String pcPassword, String pcNombreCuenta, int pcCveUsuario, String pcDescRol) {
		setPcUserid(pcUserid);
		setPcPassword(pcPassword);
		setPcNombreCuenta(pcNombreCuenta);
		setPcCveUsuario(pcCveUsuario);
		setPcDescRol(pcDescRol);
	}
	

	/**
	 * @return
	 */
	public String getPcUserid() {
		return pcUserid;
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
	public String getPcPassword() {
		return pcPassword;
	}

	/**
	 * @param string
	 */
	public void setPcUserid(String string) {
		pcUserid = string;
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
	public void setPcPassword(String string) {
		pcPassword = string;
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
	public String getPcDescRol() {
		return pcDescRol;
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
	public int getPcIsblocked() {
		return pcIsblocked;
	}

	/**
	 * @param i
	 */
	public void setPcIsblocked(int i) {
		pcIsblocked = i;
	}

	/**
	 * @return
	 */
	public Date getPcLogindate() {
		return pcLogindate;
	}

	/**
	 * @return
	 */
	public String getPcLoginDateStr() {
		return pcLoginDateStr;
	}

	/**
	 * @param date
	 */
	public void setPcLogindate(Date date) {
		pcLogindate = date;
		setPcLoginDateStr(Fecha.dateToString(date));;
	}

	/**
	 * @param string
	 */
	public void setPcLoginDateStr(String string) {
		pcLoginDateStr = string;
		
	}

	/**
	 * @return
	 */
	public Date getPcExpirationdate() {
		return pcExpirationdate;
	}

	/**
	 * @return
	 */
	public String getPcExpirationDateStr() {
		return pcExpirationDateStr;
	}

	/**
	 * @param date
	 */
	public void setPcExpirationdate(Date date) {
		pcExpirationdate = date;
		setPcExpirationDateStr(Fecha.dateToString(date));
	}

	/**
	 * @param string
	 */
	public void setPcExpirationDateStr(String string) {
		pcExpirationDateStr = string;
	}

	/**
	 * @return
	 */
	public int getPcCambiaPassword() {
		return pcCambiaPassword;
	}

	/**
	 * @param i
	 */
	public void setPcCambiaPassword(int i) {
		pcCambiaPassword = i;
	}

	}
