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
import mx.com.iusacell.catalogo.web.auxiliares.struts.action.DivisionForm;
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
public class DivisionProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	/**
	 * 
	 */
	public DivisionProxy() {
	}
	
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");	
			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			ArrayList tablaDivision = new ArrayList();
	
			if(subdivisionUsuario==0){
				tablaDivision = AdminCatFacade.getTablaDivision();
			}else{
				tablaDivision = CatalogoFacade.getTablaDivision(String.valueOf(subdivisionUsuario));
			}
		
			request.getSession().setAttribute("tablaDivision",tablaDivision);	
			return mapping.findForward("home");
	}

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("queryResult") != null) request.getSession().removeAttribute("queryResult");
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			try{
				ClavesVO clave = new ClavesVO();
				clave = CatalogoFacade.getDivisionClave();
				DivisionForm forma = (DivisionForm) form;
				Object[] parametros = {
						String.valueOf(clave.getClave()),
						request.getParameter("pcDescDiv")
					};
					
				CatalogoFacade.setDivisionVendedor(parametros);
				CatalogoFacade.setDivision(parametros);
				forma.reset(mapping,request);
				request.getSession().setAttribute("queryResult","Su nueva División se insertó con exito");
			}catch(CatalogoSystemException cse){
				
				return mapping.findForward("error");
			}catch(Exception e){
				
				return mapping.findForward("error");
			}
		
			return mapping.findForward("home");
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("queryResult") != null) request.getSession().removeAttribute("queryResult");
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

			try{
				DivisionForm forma = (DivisionForm) form;
				ArrayList division = new ArrayList();
				
				division = CatalogoFacade.getPuntosVentaPorDivision(request.getParameter("pcCveDiv"));
				if(division.isEmpty() || division == null){
					CatalogoFacade.deleteDivision(request.getParameter("pcCveDiv"));
					CatalogoFacade.deleteVendedores(request.getParameter("pcCveDiv"));
					forma.reset(mapping,request);
				}else{
					request.getSession().setAttribute("noData","No se puede borrar esta División porque esta en uso");
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
			if(request.getSession().getAttribute("queryResult") != null) request.getSession().removeAttribute("queryResult");
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				
				DivisionForm  forma = (DivisionForm) form;
				Object[] parametros = {
						request.getParameter("pcCveDiv"),
						request.getParameter("pcDescDiv")
					};
					
					try{
						CatalogoFacade.changeDivision(parametros);
						forma.reset(mapping,request);
						request.getSession().setAttribute("queryResult","Su División ha sido modificada");
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
			if(request.getSession().getAttribute("queryResult") != null) request.getSession().removeAttribute("queryResult");
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ArrayList tablaDivision = new ArrayList();
			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			if(subdivisionUsuario==0){
				tablaDivision = AdminCatFacade.getTablaDivision();	
			}else{
				tablaDivision = CatalogoFacade.getTablaDivision(String.valueOf(subdivisionUsuario));
			}
			
			request.getSession().setAttribute("tablaDivision",tablaDivision);	
			return mapping.findForward("home");
	}
	
}
