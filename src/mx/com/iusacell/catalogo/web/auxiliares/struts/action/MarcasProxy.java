/*
 * Creado el 3/11/2006
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;



import javax.servlet.http.*;
import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.modelo.PcMarcasVO;

import org.apache.struts.action.*;
/**
 * @author JOJEDAH
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class MarcasProxy {
	
	public MarcasProxy()
	{
	}
	
	public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("home");
	}
	
	public ActionForward agregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		return mapping.findForward("agregar");
	}
	
	public ActionForward agregarGuardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		String pcDescMarca = request.getParameter("pcDescMarca");

		CatalogoFacade.agregarMarca(pcDescMarca);
		request.setAttribute("mensaje","Se ha agregado correctamente la Marca");
		
		return mapping.findForward("home");
	}
	
	public ActionForward modificar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		String PcCveMarca = request.getParameter("identif");
		PcMarcasVO marca= null;
		
		marca = CatalogoFacade.obtenerMarca(PcCveMarca);
		
		request.setAttribute("marca",marca);
		
		return mapping.findForward("modificar");
	}
	
	public ActionForward modificarGuardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		String pcCveMarca = (request.getParameter("pcCveMarca") != null)
						? request.getParameter("pcCveMarca") : "";
						
		String pcDescMarca= (request.getParameter("pcDescMarca") != null)
						? request.getParameter("pcDescMarca") : "";
										
		CatalogoFacade.modificarMarca(pcCveMarca, pcDescMarca);
		request.setAttribute("mensaje","Se ha modificado correctamente la Marca");
		
		
		return mapping.findForward("home");
	}	
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		String pcCveMarca = request.getParameter("identif");

		CatalogoFacade.eliminarMarca(pcCveMarca);
		request.setAttribute("mensaje","Se ha eliminado correctamente la Marca");
		
		return mapping.findForward("home");
	}		

}
