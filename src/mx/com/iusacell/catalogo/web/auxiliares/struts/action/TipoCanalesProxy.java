/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import java.util.ArrayList;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.web.auxiliares.struts.action.TipoCanalesForm;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Dvazqueza
 * 
 * @version 1.0
 * 
 * tablaPuestos - Genera la Lista de los Puestos desde la BD
 */
public class TipoCanalesProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	/**
	 * Puestos Proxy
	 * 
	 * 
	 */
	public TipoCanalesProxy() {
	}
	

	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			ArrayList tablaTipoCanales = new ArrayList();
		
			tablaTipoCanales = CatalogoFacade.getTablaTipoCanales();
			
			request.getSession().setAttribute("tablaTipoCanales",tablaTipoCanales);	
			return mapping.findForward("home");
	}

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				ClavesVO clave = new ClavesVO();
				clave = CatalogoFacade.getTipoCanalClave();
				TipoCanalesForm forma = (TipoCanalesForm) form;
				Object[] parametros = {
						String.valueOf(clave.getClave()),
						request.getParameter("pcDescTpCanal")
					};
					
					try{
						CatalogoFacade.setTipoCanal(parametros);
						forma.reset(mapping,request);
					}catch(CatalogoSystemException cse){
						return mapping.findForward("error");
					}
			}catch(Exception e){
				return mapping.findForward("error");
			}
		
			return mapping.findForward("home");
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

			try{
				TipoCanalesForm forma = (TipoCanalesForm) form;
				ArrayList canales = new ArrayList();
				
				canales = CatalogoFacade.getCanalesPorTipo(request.getParameter("pcCveTpCanal"));
				if(canales.isEmpty() || canales == null){
					CatalogoFacade.deleteTipoCanal(request.getParameter("pcCveTpCanal"));
					forma.reset(mapping,request);
				}else{
					request.getSession().setAttribute("noData","No se puede borrar este tipo de canal porque esta en uso");
				}
			}catch(CatalogoSystemException cse){
				
				return mapping.findForward("error");
			}catch(Exception e){
				
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}

	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				
				TipoCanalesForm forma = (TipoCanalesForm) form;
				Object[] parametros = {
						request.getParameter("pcCveTpCanal"),
						request.getParameter("pcDescTpCanal")
					};
					
					try{
						CatalogoFacade.changeTipoCanal(parametros);
						forma.reset(mapping,request);
					}catch(CatalogoSystemException cse){
						return mapping.findForward("error");
					}
			}catch(Exception e){
				return mapping.findForward("error");
			}
		
			return mapping.findForward("home");
	}

	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ArrayList tablaTipoCanales = new ArrayList();
		
			tablaTipoCanales = CatalogoFacade.getTablaTipoCanales();
			
			request.getSession().setAttribute("tablaTipoCanales",tablaTipoCanales);	
			return mapping.findForward("home");
	}

}
