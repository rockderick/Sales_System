/*
 * Creado el 3/11/2006
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.struts.action.*;

/**
 * @author JOJEDAH
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class MarcasAction  extends Action {
	


	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException
	{
		
		String action = request.getParameter("action");
		if(action == null)
			action = "home";
		try
		{
			if(action.equals("home"))
				return home(mapping, form, request, response);
			else if(action.equals("agregar"))
				return agregar(mapping, form, request, response);
			else if(action.equals("agregarGuardar"))
				return agregarGuardar(mapping, form, request, response);
			else if(action.equals("modificar"))
				return modificar(mapping, form, request, response);
			else if(action.equals("modificarGuardar"))
				return modificarGuardar(mapping, form, request, response);				
			else if(action.equals("eliminar"))
				return eliminar(mapping, form, request, response);					
														
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return mapping.findForward("error");
	}
	
	private ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return new MarcasProxy().home(mapping, form, request, response);
	}
	
	private ActionForward agregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{		
		return new MarcasProxy().agregar(mapping, form, request, response);
	}
	
	private ActionForward agregarGuardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{		
		return new MarcasProxy().agregarGuardar(mapping, form, request, response);
	}
	
	private ActionForward modificar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{		
		return new MarcasProxy().modificar(mapping, form, request, response);
	}
	
	private ActionForward modificarGuardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{		
		return new MarcasProxy().modificarGuardar(mapping, form, request, response);
	}	
	
	private ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{		
		return new MarcasProxy().eliminar(mapping, form, request, response);
	}		
}
