package mx.com.iusacell.catalogo.web.empresa.struts.action;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;




/**
 * Form bean for a Struts application.
 * Users may access 2 fields on this form:
 * <ul>
 * <li>pcCveEmpresa - [your comment here]
 * <li>pcDescEmpresa - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class EmpresaForm extends ActionForm {

	private String pcCveEmpresa = null;
	private String pcDescEmpresa = null;
	private String pcTipoEmpresa = null;
	private String pcNroCompania = null;

	/**
	 * Get pcCveEmpresa
	 * @return String
	 */
	public String getPcCveEmpresa() {
		return pcCveEmpresa;
	}

	/**
	 * Set pcCveEmpresa
	 * @param <code>String</code>
	 */
	public void setPcCveEmpresa(String p) {
		this.pcCveEmpresa = p;
	}

	/**
	 * Get pcDescEmpresa
	 * @return String
	 */
	public String getPcDescEmpresa() {
		return pcDescEmpresa;
	}

	/**
	 * Set pcDescEmpresa
	 * @param <code>String</code>
	 */
	public void setPcDescEmpresa(String p) {
		this.pcDescEmpresa = p;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		pcCveEmpresa = null;
		pcDescEmpresa = null;

	}

	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new org.apache.struts.action.ActionError("error.field.required"));
		// }
		return errors;

	}
	/**
	 * @return
	 */
	public String getPcTipoEmpresa() {
		return pcTipoEmpresa;
	}

	/**
	 * @param string
	 */
	public void setPcTipoEmpresa(String string) {
		pcTipoEmpresa = string;
	}

	/**
	 * @return
	 */
	public String getPcNroCompania() {
		return pcNroCompania;
	}

	/**
	 * @param string
	 */
	public void setPcNroCompania(String string) {
		pcNroCompania = string;
	}

}
