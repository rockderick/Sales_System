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


import mx.com.iusacell.catalogo.utilerias.Constantes;

import org.apache.log4j.Logger;

/**
 * @author Dvazqueza
 *
 */
public class PuestosArbolAction extends Action {

	protected static final Logger logger = Logger.getLogger(PuestosArbolAction.class);
	
	public PuestosArbolAction() {
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
			 //   saveToken(request);

			
				
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
				} else if(action.equals("asignar")){ 
					return asignar(mapping, form, request, response);
				}
	          
				return (mapping.findForward(action));
			} catch (Exception ex) {
				
				//ex.printStackTrace();
				return mapping.findForward("error");
			}
			//return null;
		}

	

	private ActionForward home(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			return new  PuestosArbolProxy().verLista(mapping,form,request,response);	
	}

	private ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			ActionMessages errors = (ActionMessages) form.validate(mapping, request);
			if (errors != null && errors.isEmpty()) {
				return new  PuestosArbolProxy().agregar(mapping,form,request,response);	
			} else {
				saveErrors(request, errors);
				return mapping.findForward("home");
			}
	}

	private ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			return new  PuestosArbolProxy().eliminar(mapping,form,request,response);	
	}

	private ActionForward asignar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			return new  PuestosArbolProxy().asignar(mapping,form,request,response);	
	}
	
	private ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			ActionMessages errors = (ActionMessages) form.validate(mapping, request);
			/**********************************************************************************
			Enumeration e = request.getSession().getAttributeNames(); 
			for ( ; e.hasMoreElements() ;) {
					 logger.info(e.nextElement());
			}
			**********************************************************************************/

			if (errors != null && errors.isEmpty()) {
				if(Constantes.DEBUG_MESSAGES==true) logger.info("PuestosArbolAction:modificar:noerrors");
				return new  PuestosArbolProxy().modificar(mapping,form,request,response);	
			} else {
				if(Constantes.DEBUG_MESSAGES==true) logger.info(errors.toString());
				saveErrors(request, errors);
				return mapping.findForward("home");
			}
	}

	private ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			ActionMessages errors = (ActionMessages) form.validate(mapping, request);

			if (errors != null && errors.isEmpty()) {
				return new  PuestosArbolProxy().consultar(mapping,form,request,response);	
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

