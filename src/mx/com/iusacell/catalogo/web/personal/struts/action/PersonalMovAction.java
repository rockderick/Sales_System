/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.personal.struts.action;

import java.io.IOException;

import java.util.HashMap;

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
public class PersonalMovAction extends Action {
	
	protected static final Logger logger = Logger.getLogger(PersonalMovAction.class);
	
	public PersonalMovAction() {}
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, Exception {
		
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
		
		logger.info("Entre a PersonalMovAction action: " + action);
		logger.info("*** PersonalMovAction - action = " + action);

		try {
			// Set a transactional control token to prevent double posting
		 	// saveToken(request);

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
			} else if(action.equals("alta")) {
			   return alta(mapping, form, request, response);
			} else if(action.equals("cambiar")){ 
				return cambiarDiv(mapping, form, request, response);
			} else if(action.equals("horario")){ 
				return horario(mapping, form, request, response);
			}else if(action.equals("buscarSuperior")){ 
				return superior(mapping, form, request, response);
			}else if(action.equals("mostrarSup")){ 
				return mostrarSuperior(mapping, form, request, response);
			} else if(action.equals("reactivar")){ 
				return reactivar(mapping, form, request, response);
			} 
			//Feb2008
			else if(action.equals("buscar")){ 					
				return buscar(mapping, form, request, response);
			}else if(action.equals("buscaAjax")){ 
				return buscaAjax(mapping, form, request, response);
			}else if(action.equals("ajaxSuperior")){ 
				return ajaxSuperior(mapping, form, request, response);
			}

          
			return (mapping.findForward(action));
		} catch (Exception ex) {
			//ex.printStackTrace();
			logger.error("Error en execute : " + ex.toString());
			return mapping.findForward("error");
		}
		//return null;
	}

	private ActionForward home(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			return new  PersonalMovProxy().verLista(mapping,form,request,response);	
	}

	private ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			return new  PersonalMovProxy().consultar(mapping,form,request,response);	
	}

	private ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			logger.info("Entre a agregar usuario");

			PersonalMovForm forma = (PersonalMovForm) form;
			ActionMessages errors = (ActionMessages) forma.validate(mapping, request);
			if (errors != null && errors.isEmpty()) {
				return new  PersonalMovProxy().agregar(mapping,form,request,response);	
			} else {
				saveErrors(request, errors);
				return mapping.findForward("home");
			}
	}

	private ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			PersonalMovForm forma = (PersonalMovForm) form;
			ActionMessages errors = (ActionMessages) forma.validate(mapping, request);
			if (errors != null && errors.isEmpty()) {
				return new PersonalMovProxy().modificar(mapping,form,request,response);	
			} else {
				saveErrors(request, errors);
				return mapping.findForward("home");
			}
	}
	
	private ActionForward alta(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
			
				return new PersonalMovProxy().alta(mapping,form,request,response);				
	}
	
	private ActionForward superior(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {		
					return new  PersonalMovProxy().superior(mapping,form,request,response);	
	}
	private ActionForward mostrarSuperior(ActionMapping mapping, ActionForm form,
						HttpServletRequest request, HttpServletResponse response) {
				return new  PersonalMovProxy().mostrarSuperior(mapping,form,request,response);	
	}
	private ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			return new  PersonalMovProxy().eliminar(mapping,form,request,response);	
	}
	
	/**
	 * Entra en Bitácora este método
	 * Ya
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward cambiarDiv(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
					return new  PersonalMovProxy().cambiarDiv(mapping,form,request,response);	
	}
	
	/**
	 * Entra en bitácora este método.
	 * No Yet.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	private ActionForward reactivar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
					return new  PersonalMovProxy().reactivar(mapping,form,request,response);	
	}
	
	//Feb2008
	private ActionForward buscar(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) {
	
		HashMap success = new HashMap();
		
		try {
			success = new PersonalMovProxy().buscar(request);
			if (success.containsKey("lista")) 
				request.getSession().setAttribute("tablaVendedores",success.get("lista")); 
			 
		} catch (Exception e) {
			logger.error("errores Exception: " + e.toString());
		}
		
		return mapping.findForward(success.get("action").toString());	
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
	
	private ActionForward horario(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
			
			return new  PersonalMovProxy().horario(mapping,form,request,response);	
	}

	private ActionForward buscaAjax(ActionMapping mapping, ActionForm form, 
									HttpServletRequest request, HttpServletResponse response) {
			
		try {
			logger.info("**** ENTRA PersonalMovAction:buscaAjax( )");
			new PersonalMovProxy().buscaAjax(request, response);
		} catch (Exception e) {
			logger.error("errores Exception: " + e.toString());
		}

		return mapping.findForward(null);	
	}
	
	private ActionForward ajaxSuperior(ActionMapping mapping, ActionForm form, 
									HttpServletRequest request, HttpServletResponse response) {
		
		try {
			new PersonalMovProxy().ajaxSuperior(request, response);
		} catch (Exception e) {
			logger.error("errores Exception: " + e.toString());
		}

		return mapping.findForward(null);	
	}
		
}

