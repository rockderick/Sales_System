/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

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
/**
 * @author Dvazqueza
 *
 */
public class DivisionAction extends Action {
	
	public DivisionAction() {
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
			// 	saveToken(request);

			 if (action.equals("home")) {
				 return home(mapping, form, request, response);
			 } else if(action.equals("agregar")){ 
				 return agregar(mapping, form, request, response);
			 } else if(action.equals("eliminar")){ 
				 return eliminar(mapping, form, request, response);
			 } else if(action.equals("modificar")){ 
				 return modificar(mapping, form, request, response);
			 } else if(action.equals("consultar")){ 
				 return consultar(mapping, form, request, response);
			 }
	          
				return (mapping.findForward(action));
			} catch (Exception ex) {
				return mapping.findForward("error");
			}
			//return null;
		}

	private ActionForward home(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
				
			return new  DivisionProxy().verLista(mapping,form,request,response);	
	}
		
	private ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {

			DivisionForm forma = (DivisionForm) form;
			ActionMessages errors = (ActionMessages) forma.validate(mapping, request);

			if (errors != null && errors.isEmpty()) {
				return new  DivisionProxy().agregar(mapping,form,request,response);	
			} else {
				saveErrors(request, errors);
				return mapping.findForward("home");
			}						
	}
		
	private ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
				
			return new  DivisionProxy().eliminar(mapping,form,request,response);	
	}

	private ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
				
			DivisionForm forma = (DivisionForm) form;
			//ActionMessages errors = (ActionMessages) forma.validate(mapping, request);
			ActionMessages errors = null;
			if (errors != null && errors.isEmpty()) {
				return new  DivisionProxy().consultar(mapping,form,request,response);	
			} else {
				saveErrors(request, errors);
				return mapping.findForward("home");
			}						
	}

	private ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			DivisionForm forma = (DivisionForm) form;
			ActionMessages errors = (ActionMessages) forma.validate(mapping, request);

			if (errors != null && errors.isEmpty()) {
				return new  DivisionProxy().modificar(mapping,form,request,response);	
			} else {
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

