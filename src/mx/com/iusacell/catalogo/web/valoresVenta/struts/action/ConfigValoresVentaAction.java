/*
 * Tipo de Archivo: Clase
 * Nombre del Archivo: mx.com.iusacell.catalogo.web.valoresVenta.struts.action.ConfigValoresVentaAction
 * 
 * Autor: CAL
 * Fecha: Feb 13, 2008
 *
 */
package mx.com.iusacell.catalogo.web.valoresVenta.struts.action;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

/**
 * @author JOJEDAH
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfigValoresVentaAction extends Action{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
		{
			String action = request.getParameter("action");
			 if (isCancelled(request)){
				removeFormBean(mapping, request);
				return (mapping.findForward("home"));
			  } 
			if(action == null)
				action = "home";
			try
			{
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
				} else if(action.equals("ejecutar")){
				    return ejecutar(mapping, form, request, response);
				}					
			  return (mapping.findForward(action));											
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return mapping.findForward("error");
		}
		
	private ActionForward home(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {	
				return new  ConfigValoresVentaProxy().verLista(mapping,form,request,response);	
		}
		
	private ActionForward agregar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
				ConfigValoresVentaForm forma = (ConfigValoresVentaForm) form;
				ActionMessages errors = (ActionMessages) forma.validate(mapping, request);
				if (errors != null && errors.isEmpty()) {
					return new  ConfigValoresVentaProxy().agregar(mapping,form,request,response);	
				} else {
					saveErrors(request, errors);
					return mapping.findForward("home");
				}
		}

		private ActionForward eliminar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
				return new  ConfigValoresVentaProxy().eliminar(mapping,form,request,response);	
		}
		
	private ActionForward ejecutar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response){
		        ConfigValoresVentaForm forma = (ConfigValoresVentaForm) form;
		        ActionMessages errors = (ActionMessages) forma.validate(mapping, request);
		    if (errors != null && errors.isEmpty()) {
		        	return new  ConfigValoresVentaProxy().ejecutar(mapping,form,request,response);	
		 }else {
				saveErrors(request, errors);
				return mapping.findForward("home");
		     }
	     }						
	
		private ActionForward modificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
				ConfigValoresVentaForm forma = (ConfigValoresVentaForm) form;
				ActionMessages errors = (ActionMessages) forma.validate(mapping, request);
				/*********************************************************************************
				Enumeration e = request.getSession().getAttributeNames(); 
				for ( ; e.hasMoreElements() ;) {
						 logger.info(e.nextElement());
				}
				**********************************************************************************/

				if (errors != null && errors.isEmpty()) {
					return new  ConfigValoresVentaProxy().modificar(mapping,form,request,response);	
				} else {
					saveErrors(request, errors);
					return mapping.findForward("home");
				}
		}

		private ActionForward consultar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
				ConfigValoresVentaForm forma = (ConfigValoresVentaForm) form;
				ActionMessages errors = (ActionMessages) forma.validate(mapping, request);

				if (errors != null && errors.isEmpty()) {
					return new  ConfigValoresVentaProxy().consultar(mapping,form,request,response);	
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
				
}//Fin de clase ConfigValoresVentaAction****************************************************
