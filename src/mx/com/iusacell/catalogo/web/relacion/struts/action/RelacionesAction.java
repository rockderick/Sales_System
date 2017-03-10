/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.relacion.struts.action;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import org.apache.log4j.Logger;

/**
 * @author Dvazqueza
 *
 */
public class RelacionesAction extends Action {
	
	protected static final Logger logger = Logger.getLogger(RelacionesAction.class);
	
	public RelacionesAction() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
				
			logger.info("***** Ejecutando RelacionesAction.excute() *****");

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

			logger.info("*** Accion = " + action);
			try {
				if (action.equals("home")) {
					return home(mapping, form, request, response);
				} else if(action.equals("consultar")){ 
					return consultar(mapping, form, request, response);
				} else if(action.equals("agregar")){ 
					return agregar(mapping, form, request, response);
				} else if(action.equals("eliminar")){ 
					return eliminar(mapping, form, request, response);
				} else if(action.equals("modificar")){ 
					return modificar(mapping, form, request, response);
				} else if(action.equals("solicitar")){ 
					return solicitar(mapping, form, request, response);
				}
	          
				return (mapping.findForward(action));
			} catch (Exception ex) {
				logger.error("Error en execute: " + ex.toString());
			}
			return mapping.findForward("error");
		}

	private ActionForward home(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  RelacionesProxy().verLista(mapping,form,request,response);	
	}

	private ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  RelacionesProxy().consultar(mapping,form,request,response);	
	}

	private ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  RelacionesProxy().agregar(mapping,form,request,response);	
	}

	private ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new RelacionesProxy().modificar(mapping,form,request,response);	
	}

	private ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  RelacionesProxy().eliminar(mapping,form,request,response);	
	}

	private ActionForward solicitar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  RelacionesProxy().solicitar(mapping,form,request,response);	
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

