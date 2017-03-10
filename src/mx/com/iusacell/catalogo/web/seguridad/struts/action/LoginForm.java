/*
 * Decompiled from SAEO project on Nov 22, 2016
 * 
 * 
 */
package mx.com.iusacell.catalogo.web.seguridad.struts.action;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.validator.ValidatorForm;

public class LoginForm extends ValidatorForm implements Serializable {
	private String action = null;

	private String pcUserid = null;
	private String pcPassword = null;
	private String pswConfirma = null;
	private String pcNombreCuenta;
	private int pcCveRol;
	private String pcDescRol;
	private int pcCveDiv;
	private String pcDescDiv;
	private int pcCveSubdiv;
	private String pcDescSubdiv;
	private int pcCveRolDiv;
	private int pcStatus;
	private String usu;
	
	protected static final Logger logger = Logger.getLogger(LoginForm.class);

	public LoginForm() {
	}

	public LoginForm(String pcUserid, String pcPassword, String pswConfirma) {
		setPcUserid(pcUserid);
		setPcPassword(pcPassword);
		setPswConfirma(pswConfirma);
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.action = null;
		this.pcUserid = null;
		this.pcPassword = null;
		this.pswConfirma = null;
	}

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		logger.info("Entra a validar");
		if ((this.pcUserid == null) || (this.pcUserid.equals(""))) {
			logger.info("Usuario es necesario");
			errors.add("usuario", new ActionMessage("userRegistration.usuario.problem"));
		}
		if ((this.pcPassword == null) || (this.pcPassword.equals(""))) {
			logger.info("Password es necesario");
			errors.add("contrasenia", new ActionMessage("userRegistration.password.problem"));
		}
		//System.out.println(request.getAttribute("mensaje"));
		return errors;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("[");
		result.append(" Cuenta = " + getPcNombreCuenta() + ",");
		result.append(" Rol = " + getPcDescRol());
		result.append("]");
		return result.toString();
	}

	public String getPcPassword() {
		return this.pcPassword;
	}

	public String getPcUserid() {
		return this.pcUserid;
	}

	public void setPcPassword(String string) {
		this.pcPassword = string;
	}

	public void setPcUserid(String string) {
		this.pcUserid = string;
	}

	public int getPcCveDiv() {
		return this.pcCveDiv;
	}

	public int getPcCveRol() {
		return this.pcCveRol;
	}

	public int getPcCveRolDiv() {
		return this.pcCveRolDiv;
	}

	public String getPcDescDiv() {
		return this.pcDescDiv;
	}

	public String getPcDescRol() {
		return this.pcDescRol;
	}

	public int getPcStatus() {
		return this.pcStatus;
	}

	public void setPcCveDiv(int i) {
		this.pcCveDiv = i;
	}

	public void setPcCveRol(int i) {
		this.pcCveRol = i;
	}

	public void setPcCveRolDiv(int i) {
		this.pcCveRolDiv = i;
	}

	public void setPcDescDiv(String string) {
		this.pcDescDiv = string;
	}

	public void setPcDescRol(String string) {
		this.pcDescRol = string;
	}

	public void setPcStatus(int i) {
		this.pcStatus = i;
	}

	public String getPcNombreCuenta() {
		return this.pcNombreCuenta;
	}

	public void setPcNombreCuenta(String string) {
		this.pcNombreCuenta = string;
	}

	public int getPcCveSubdiv() {
		return this.pcCveSubdiv;
	}

	public String getPcDescSubdiv() {
		return this.pcDescSubdiv;
	}

	public void setPcCveSubdiv(int i) {
		this.pcCveSubdiv = i;
	}

	public void setPcDescSubdiv(String string) {
		this.pcDescSubdiv = string;
	}

	public String getPswConfirma() {
		return this.pswConfirma;
	}

	public void setPswConfirma(String string) {
		this.pswConfirma = string;
	}

	public String getUsu() {
		return this.usu;
	}

	public void setUsu(String string) {
		this.usu = string;
	}
}
