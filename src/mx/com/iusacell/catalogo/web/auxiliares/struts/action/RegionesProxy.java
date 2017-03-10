/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import java.util.ArrayList;


import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import mx.com.iusacell.catalogo.web.auxiliares.struts.action.RegionesForm;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Dvazqueza
 *
 */
public class RegionesProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	/**
	 * 
	 */
	public RegionesProxy() {
	}
	
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ArrayList tablaRegiones = new ArrayList();
	
			tablaRegiones = CatalogoFacade.getTablaRegiones();
		
			request.getSession().setAttribute("tablaRegiones",tablaRegiones);	
			return mapping.findForward("home");
	}

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			try{
				ClavesVO clave = new ClavesVO();
				clave = CatalogoFacade.getRegionClave();
				RegionesForm forma = (RegionesForm) form;
				Object[] parametros = {
						String.valueOf(clave.getClave()),
						request.getParameter("pcDescRegion")
					};
					
					try{
						CatalogoFacade.setRegion(parametros);
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
				RegionesForm forma = (RegionesForm) form;
				ArrayList regiones = new ArrayList();
				
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				
				if(subdivisionUsuario== 0){
					regiones = AdminCatFacade.getPuntosVentaPorRegion(request.getParameter("pcCveRegion"));
				}else{
					regiones = CatalogoFacade.getPuntosVentaPorRegion(request.getParameter("pcCveRegion"), String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
				}
				if(regiones.isEmpty() || regiones == null){
					CatalogoFacade.deleteRegion(request.getParameter("pcCveRegion"));
					forma.reset(mapping,request);
				}else{
					request.getSession().setAttribute("noData","No se puede borrar esta Region porque esta en uso");
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
				
				RegionesForm  forma = (RegionesForm) form;
				Object[] parametros = {
						request.getParameter("pcCveRegion"),
						request.getParameter("pcDescRegion")
					};
					
					try{
						CatalogoFacade.changeRegion(parametros);
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
			ArrayList tablaRegiones = new ArrayList();
		
			tablaRegiones = CatalogoFacade.getTablaRegiones();
			
			request.getSession().setAttribute("tablaRegiones",tablaRegiones);	
			return mapping.findForward("home");
	}
	
}
