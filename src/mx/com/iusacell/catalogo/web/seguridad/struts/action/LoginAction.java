/*
 * Decompiled from SAEO project on Nov 22, 2016
 *
 * 
 * 
 */
package mx.com.iusacell.catalogo.web.seguridad.struts.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

public class LoginAction extends Action {
	protected static final Logger logger = Logger.getLogger(LoginAction.class);

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		MessageResources messages = getResources(request);

		String action = request.getParameter("action");
		logger.info("Entra LoginAction mi prueba action = " + action);

		if (isCancelled(request)) {
			removeFormBean(mapping, request);
			return mapping.findForward("home");
		}

		if (action == null) {
			action = "home";
		}
		
		try {
			if (action.equals("home"))
			{	
				//return home(mapping, form, request, response);
				return mapping.findForward("home");
			}	
			if (action.equals("solicitar"))
				return solicitar(mapping, form, request, response);
			if (action.equals("autorizar"))
				return autorizar(mapping, form, request, response);
			if (action.equals("Login"))
				return login(mapping, form, request, response);
			if (action.equals("GuardarPassword"))
				return guardarPassword(mapping, form, request, response);
			if (action.equals("CancelarPassword"))
				return cancelarPassword(mapping, form, request, response);
			if (action.equals("Salir"))
			{	
				//return mapping.findForward("Salir");
				return salir(mapping, form, request, response);
			}	
			if (action.equals("cambio")) {
				return mapping.findForward("login");
			}
			return mapping.findForward(action);
		} catch (Exception ex) {
			logger.error("Error en execute " + ex.getMessage());
			ex.printStackTrace();
		}

		return mapping.findForward("error");
	}

	private ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//return new LoginProxy().home(mapping, form, request, response);
		return new LoginProxy().loginHome(mapping, form, request, response);
	}

	private ActionForward guardarPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return new LoginProxy().guardarPassword(mapping, form, request, response);
	}

	private ActionForward cancelarPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new LoginProxy().cancelarPassword(mapping, form, request, response);
	}

	private ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new LoginProxy().login(mapping, form, request, response);
	}

	private ActionForward autorizar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//return new LoginProxy().home(mapping, form, request, response);
		return new LoginProxy().loginHome(mapping, form, request, response);
	}

	private ActionForward solicitar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new LoginProxy().solicitar(mapping, form, request, response);
	}
	
	private ActionForward salir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return new LoginProxy().salir(mapping, form, request, response);
	}

	protected void removeFormBean(ActionMapping mapping, HttpServletRequest request) {
		if (mapping.getAttribute() != null)
			if ("request".equals(mapping.getScope())) {
				request.removeAttribute(mapping.getAttribute());
			} else {
				HttpSession session = request.getSession();
				session.removeAttribute(mapping.getAttribute());
			}
	}
}
