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
import mx.com.iusacell.catalogo.web.auxiliares.struts.action.PuestosForm;
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
public class PuestosProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	/**
	 * Puestos Proxy
	 * 
	 * 
	 */
	public PuestosProxy() {
	}
	

	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			ArrayList tablaPuestos = new ArrayList();
		
			tablaPuestos = CatalogoFacade.getTablaPuestos();
			
			request.getSession().setAttribute("tablaPuestos",tablaPuestos);	
			return mapping.findForward("home");
	}

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				ClavesVO clave = new ClavesVO();
				clave = CatalogoFacade.getPuestoClave();
				PuestosForm forma = (PuestosForm) form;
				Object[] parametros = {
					String.valueOf(clave.getClave()),
					request.getParameter("pcDescPuestos")
				};
			
				try{
					CatalogoFacade.setPuesto(parametros);
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
				String cvePuestos = (request.getParameter("pcCvePuestos").equals(""))? null:request.getParameter("pcCvePuestos");
				
				ArrayList puestos = new ArrayList();
				
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();				
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();				

				if(subdivisionUsuario==0){
					puestos = AdminCatFacade.getVendedoresListaPuesto(cvePuestos);	
				}else{
					puestos = CatalogoFacade.getVendedoresListaPuesto(cvePuestos, String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
				}
				
				if(puestos.isEmpty() || puestos == null){
					CatalogoFacade.deletePuesto(cvePuestos);
					((PuestosForm) form).reset(mapping,request);
				}else{
					request.getSession().setAttribute("noData","No se puede borrar este Puesto porque esta en uso");
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
				
				PuestosForm forma = (PuestosForm) form;
				Object[] parametros = {
						request.getParameter("pcCvePuestos"),
						request.getParameter("pcDescPuestos")
					};
					
					try{
						CatalogoFacade.changePuesto(parametros);
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
			ArrayList tablaPuestos = new ArrayList();
		
			tablaPuestos = CatalogoFacade.getTablaPuestos();
			
			request.getSession().setAttribute("tablaPuestos",tablaPuestos);	
			return mapping.findForward("home");
	}


}
