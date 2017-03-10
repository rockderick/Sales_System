/*
 * Creado el Jun 1, 2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;

import java.io.IOException;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;

import org.apache.log4j.Logger;

/**
 * @author jojedah
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ChecadorAction extends Action {

	protected static final Logger logger = Logger.getLogger(ChecadorAction.class);
	
	public ChecadorAction() {
	}

public ActionForward execute(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {

		MessageResources messages = getResources(request);

		String action = request.getParameter("action");


		if (action == null) {
			action = "home";
		} 

		try {

			if (action.equals("home")) {
				return home(mapping, form, request, response);
			} else if(action.equals("reporte")){ 
				return reporte(mapping, form, request, response);
			} else if(action.equals("exportar")){ 
				exportar(mapping, form, request, response);
			}
	          
			return (mapping.findForward(action));
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return null;
	}
	
	private ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return new ChecadorProxy().home(mapping, form, request, response);
	}
	
	private ActionForward reporte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return new ChecadorProxy().reporte(mapping, form, request, response);
	}
	
	private void exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		new ChecadorProxy().exportar(mapping, form, request, response);
	}
}
