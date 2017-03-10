/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.puntosventa.struts.action;

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
public class PuntosVentaAction extends Action {

	protected static final Logger logger = Logger.getLogger(PuntosVentaAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

			MessageResources messages = getResources(request);

			String action = request.getParameter("action");
			
			logger.info("**** Ejecutando PuntosVentaAction.execute() ****");

			// Was this transaction cancelled?
			if (isCancelled(request)) {
				removeFormBean(mapping, request);
				return (mapping.findForward("home"));
			}

			if (action == null) {
				action = "home";
			}
			
			logger.info("Accion = " + action);
			try {
				// Set a transactional control token to prevent double posting
			    // saveToken(request);
				if (action.equals("home")) {
					return home(mapping, form, request, response);
				} else if(action.equals("agregar")){ 
					return agregar(mapping, form, request, response);
				} else if(action.equals("eliminar")){ 
					return eliminar(mapping, form, request, response);
				} else if(action.equals("modificar")){ 
					return modificar(mapping, form, request, response);
				} else if(action.equals("reactivar")){ 
				    return reactivar(mapping, form, request, response);
			     }else if(action.equals("consultar")){ 
					return consultar(mapping, form, request, response);
				} else if(action.equals("agregarMarca")){ 
					return agregarMarca(mapping, form, request, response);
				} else if(action.equals("eliminarMarca")){ 
					return eliminarMarca(mapping, form, request, response);
				} else if(action.equals("buscaRegion")){ 
					return buscaRegion(mapping, form, request, response);
				} else if(action.equals("buscaCiudad")){ 
					return buscaCiudad(mapping, form, request, response);
				} else if(action.equals("buscaEstado")){ 
					return buscaEstado(mapping, form, request, response);
				}
				return (mapping.findForward(action));
			} catch (Exception ex) {
				logger.error("Error en execute: " + ex.toString());
			}
			return (mapping.findForward("home"));
		}

	

	private ActionForward home(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new PuntosVentaProxy().verLista(mapping,form,request,response);	
	}

	private ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			PuntosVentaForm forma = (PuntosVentaForm) form;
			ActionMessages errors = (ActionMessages) forma.validate(mapping, request);
			if (errors != null && errors.isEmpty()) {
				request.setAttribute("cveRegion",request.getParameter("pcRegion"));
				request.setAttribute("cveCiudad",request.getParameter("pcCiudad"));
				//request.setAttribute("cveEstado",request.getParameter("pcEstado"));
				return new  PuntosVentaProxy().agregar(mapping,form,request,response);	
			} else {
				request.setAttribute("cveRegion",request.getParameter("pcRegion"));
				request.setAttribute("cveCiudad",request.getParameter("pcCiudad"));
				//request.setAttribute("cveEstado",request.getParameter("pcEstado"));
				saveErrors(request, errors);
				return mapping.findForward("home");
			}
	}

	private ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  PuntosVentaProxy().eliminar(mapping,form,request,response);	
	}
	
		
	private ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  PuntosVentaProxy().modificar(mapping,form,request,response);	
	}
	
	private ActionForward reactivar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
			return new  PuntosVentaProxy().reactivar(mapping,form,request,response);	
		}

	private ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  PuntosVentaProxy().consultar(mapping,form,request,response);	
	}
	
	private ActionForward agregarMarca(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  PuntosVentaProxy().agregarMarca(mapping,form,request,response);	
	}
	
	private ActionForward eliminarMarca(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			return new  PuntosVentaProxy().eliminarMarca(mapping,form,request,response);	
	}
	
	private ActionForward buscaRegion(ActionMapping mapping, ActionForm form,
									  HttpServletRequest request, HttpServletResponse response) {
		new PuntosVentaProxy().buscaRegion(request,response);
		return null;	
	}	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward buscaCiudad(ActionMapping mapping, ActionForm form,
										  HttpServletRequest request, HttpServletResponse response) {
			new PuntosVentaProxy().buscaCiudad(request,response);
			return null;	
	}
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward buscaEstado(ActionMapping mapping, ActionForm form,
											  HttpServletRequest request, HttpServletResponse response) {
				new PuntosVentaProxy().buscaEstado(request,response);
				return null;	
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

