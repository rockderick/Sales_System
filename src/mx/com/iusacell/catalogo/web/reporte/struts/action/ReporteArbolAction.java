/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import org.apache.log4j.Logger;


/**
 * @author Dvazqueza
 *
 */
public class ReporteArbolAction extends Action {

	protected static final Logger logger = Logger.getLogger(ReporteArbolAction.class);
	
	public ReporteArbolAction() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

			MessageResources messages = getResources(request);

			String action = request.getParameter("action");

			// Was this transaction cancelled?
			if (isCancelled(request)) {
				removeFormBean(mapping, request);
				return (mapping.findForward("home"));
			}

			if (action == null) {
				action = "home";
			}

			try {
				// Set a transactional control token to prevent double posting
			    // saveToken(request);
				if (action.equals("home")) {
					return home(mapping, form, request, response);
				} else if(action.equals("agregar")){ 
					return agregar(mapping, form, request, response);
				} else if(action.equals("excel")){ 
					return excel(mapping, form, request, response);
				} else if(action.equals("eliminar")){ 
					return eliminar(mapping, form, request, response);
				} else if(action.equals("modificar")){ 
					return modificar(mapping, form, request, response);
				} else if(action.equals("consultar")){ 
					return consultar(mapping, form, request, response);
				} else if(action.equals("historico")){ 					
					return historico(mapping, form, request, response);
				}
	          
				return (mapping.findForward(action));
			} catch (Exception ex) {
				logger.error(ex.toString());
			}
			return null;
		}

	

	private ActionForward home(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return mapping.findForward("home");
	}

	private ActionForward excel(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new ReporteArbolProxy().excel(mapping,form,request,response);
	}

	private ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return mapping.findForward("home");
	}

	private ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return mapping.findForward("home");
	}

	private ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return mapping.findForward("home");			
	}

	private ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			ActionMessages errors = (ActionMessages) ((ReporteArbolForm) form).validate(mapping, request);
			if (errors != null && errors.isEmpty()) {
				return new  ReporteArbolProxy().consultar(mapping,form,request,response);			
			} else {
				saveErrors(request, errors);
				return mapping.findForward("home");
			}

			//return new  ReporteArbolProxy().consultar(mapping,form,request,response);	
	}
	
	private ActionForward historico(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		ActionMessages errors = (ActionMessages) ((ReporteArbolForm) form).validate(mapping, request);
		if (errors != null && errors.isEmpty()) {
		return new  ReporteArbolProxy().historico(mapping,form,request,response);
		}else{
			saveErrors(request, errors);
			return mapping.findForward("home");
		}			
	}
	/**
	 * Convenience method for removing the obsolete form bean.
	 *
	 * @param mapping The ActionMapping used to select this instance
	 * @param request The HTTP request we are processing
	*/
	protected void removeFormBean(ActionMapping mapping, HttpServletRequest request) {
	   // Remove the obsolete form bean
	   if (mapping.getAttribute() != null) {
		   if ("request".equals(mapping.getScope())) {
			   request.removeAttribute(mapping.getAttribute());
		   } else {
			  HttpSession session = request.getSession();
			  session.removeAttribute(mapping.getAttribute());
		   }
	   }
	}	
}

