/*
 * Created on Aug 1, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.com.iusacell.catalogo.web.empresa.struts.action;

import java.util.ArrayList;


import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;

import mx.com.iusacell.catalogo.modelo.UserSessionVO;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;



/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EmpresaProxy {
	protected static final Logger logger = Logger.getLogger(EmpresaProxy.class);


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
				//ClavesVO clave = new ClavesVO();
				//clave = CatalogoFacade.getDivisionClave();
				EmpresaForm forma = (EmpresaForm) form;
				Object[] parametros = {String.valueOf(request.getParameter("pcDescEmpresa")), request.getParameter("pcTipoEmpresa")};
				
				//CatalogoFacade.setDivisionVendedor(parametros);
				CatalogoFacade.setEmpresa(parametros);
				forma.reset(mapping,request);
				request.getSession().setAttribute("queryResult","Su nueva Empresa se insertó con exito");
			}catch(CatalogoSystemException cse){
				
				cse.printStackTrace();
				return mapping.findForward("error");
			}catch(Exception e){
				
				e.printStackTrace();
				return mapping.findForward("error");
			}
		
			return mapping.findForward("home");
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("queryResult") != null) request.getSession().removeAttribute("queryResult");
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

			try{
				EmpresaForm forma = (EmpresaForm) form;
				ArrayList division = new ArrayList();
				
				CatalogoFacade.deleteEmpresa(request.getParameter("pcCveEmpresa"));
					
					forma.reset(mapping,request);
				
					request.getSession().setAttribute("noData","La empresa ha sido borrada con exito");
				
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
				
				EmpresaForm  forma = (EmpresaForm) form;
				Object[] parametros = {
						request.getParameter("pcCveEmpresa"),
						request.getParameter("pcDescEmpresa"),
						request.getParameter("pcTipoEmpresa")
					};
					
					try{
						CatalogoFacade.changeEmpresa(parametros);
						forma.reset(mapping,request);
						request.getSession().setAttribute("queryResult","Su Empresa ha sido modificada");
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
			
			return mapping.findForward("home");
	}
	
}
