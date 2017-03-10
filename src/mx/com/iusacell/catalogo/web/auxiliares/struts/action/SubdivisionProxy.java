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

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


/**
 * @author Dvazqueza
 * 
 * @version 1.0
 * 
 * tablaPuestos - Genera la Lista de los Puestos desde la BD
 */
public class SubdivisionProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	
	protected static final Logger logger = Logger.getLogger(SubdivisionProxy.class);

	/**
	 * Puestos Proxy
	 * 
	 * 
	 */
	public SubdivisionProxy() {
	}
	

	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			ArrayList tablaSubdivision = CatalogoFacade.getTablaSubdivision(String.valueOf(subdivisionUsuario));
			request.getSession().setAttribute("tablaSubdivision",tablaSubdivision);	
			return mapping.findForward("home");
	}

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				ClavesVO clave = new ClavesVO();
				clave = CatalogoFacade.getSubdivisionClave();
				Object[] parametros = {
						String.valueOf(clave.getClave()),
						request.getParameter("pcDescSubdiv"),
						request.getParameter("pcCveDiv")
					};
					
						CatalogoFacade.setSubdivision(parametros);
						((SubdivisionForm) form).reset(mapping,request);
			}catch(CatalogoSystemException cse){
				logger.error("Error en Agregar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error en Agregar: " + e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();

				ArrayList canales = new ArrayList();
				if(subdivisionUsuario == 0){
					canales = AdminCatFacade.getPuntosVentaPorSubdivision(request.getParameter("pcCveSubdivision"));
				}else{
					canales = CatalogoFacade.getPuntosVentaPorSubdivision(String.valueOf(subdivisionUsuario));
				}

				if(canales.isEmpty() || canales == null){
					CatalogoFacade.deleteSubdivision(request.getParameter("pcCveSubdiv"));
					((SubdivisionForm) form).reset(mapping,request);
				}else{
					request.getSession().setAttribute("noData","No se puede borrar esta Region porque esta en uso");
				}
			}catch(CatalogoSystemException cse){
				logger.error("Error en eliminar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error en eliminar: " + e.toString());
				return mapping.findForward("error");
			}
		
			return mapping.findForward("home");
	}

	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				Object[] parametros = {
						request.getParameter("pcCveSubdiv"),
						request.getParameter("pcDescSubdiv"),
						request.getParameter("pcCveDiv")
					};
					
						CatalogoFacade.changeSubdivision(parametros);
						((SubdivisionForm) form).reset(mapping,request);
			}catch(CatalogoSystemException cse){
				logger.error("Error en modificar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error en modificar: " + e.toString());
				return mapping.findForward("error");
			}
		
			return mapping.findForward("home");
	}

	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			return mapping.findForward("home");
	}


}
