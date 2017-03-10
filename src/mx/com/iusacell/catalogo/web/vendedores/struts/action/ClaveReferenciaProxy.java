/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.vendedores.struts.action;

import java.util.ArrayList;

import java.util.List;
import java.util.StringTokenizer;

import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.dao.CatalogoFacade;


import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;

import mx.com.iusacell.catalogo.modelo.Pc_MapeoVendedorVO;

import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

/**
 * @author Daniel Vazquez A.
 * 
 * @version 1.0
 * 
 */
public class ClaveReferenciaProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(ClaveReferenciaProxy.class);

	/**
	 * Puestos Proxy
	 *  
	 */
	public ClaveReferenciaProxy() {
	}
	/***********************************************************************************
	 * verLista
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
			return mapping.findForward("home");
	}
	/***********************************************************************************
	 * agregar
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			if(request.getSession().getAttribute("mapeoVendedor") != null) request.getSession().removeAttribute("mapeoVendedor"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			try{
				String claveVendedor = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
				String sistema = (request.getParameter("pcSistema").equals(""))? null:request.getParameter("pcSistema");
				String customerID = (request.getParameter("pcCustomerId").equals(""))? null:request.getParameter("pcCustomerId");
				String claveRegVentas = (request.getParameter("pcCveRegVenta").equals(""))? null:request.getParameter("pcCveRegVenta");
				String nomUsuario = (request.getParameter("pcNomUsuario").equals(""))? null:request.getParameter("pcNomUsuario");
								
				
				Object[] parametros = {
						claveVendedor,
						sistema,
						customerID,
						claveRegVentas,
						nomUsuario				
					};
					
				CatalogoFacade.setMapeoVendedor(parametros);

				request.getSession().setAttribute("noData","Ha sido agregado un nuevo Mapeo para el Vendedor con clave : " + claveVendedor);
			}catch(CatalogoSystemException cse){
				
				logger.error("Error: " + cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				
				logger.error("Error: " + e.toString());
				request.setAttribute("e_error", e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
	/***********************************************************************************
	 * eliminar
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			try{
				String claveVendedor = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
				String claveCompuesta = (request.getParameter("pcMapeoVendedorRadio").equals(""))? null:request.getParameter("pcMapeoVendedorRadio");
				
				//String[] tokensClave = claveCompuesta.split("/");
				// La clase StringTokenizer se supone que sera reemplazada por el metodo String.split() en 1.4.2
				String[] tokensClave = {"",""};
				StringTokenizer st = new StringTokenizer(claveCompuesta,"/");
				int indice = 0;
				while (st.hasMoreTokens()) {
					tokensClave[indice++] = st.nextToken();
				}
	
				logger.info("Clave Vendedor: " + claveVendedor);
				logger.info("Clave tokensClave 0: " + tokensClave[0]);
				logger.info("Clave tokensClave 1: " + tokensClave[1]);
				
				List mapeos = null;
				
				try{
					mapeos = CatalogoFacade.getMapeoVendedorBySistemaAndCveVenta(claveVendedor,tokensClave[0],tokensClave[1]);
					
					logger.info("Tamaño mapeos: " + mapeos.size());
					
					CatalogoFacade.deleteMapeoVendedor(claveVendedor,tokensClave[0],tokensClave[1]);
					
					if(request.getSession().getAttribute("mapeoVendedor") != null) request.getSession().removeAttribute("mapeoVendedor");
					
				}catch(CatalogoSystemException cse){
					logger.error("Error al eliminar mapeo " + cse.toString());

					request.setAttribute("cse_error", cse.toString());
					return mapping.findForward("error");
				}

				try{
					if(mapeos != null && mapeos.size() > 0){
					
						Pc_MapeoVendedorVO vo = null;

						UserSessionVO user = (UserSessionVO) request.getSession().getAttribute("USER");
				
						logger.info("USER: " + ToStringBuilder.reflectionToString(user));
				
						for(int i = 0; i < mapeos.size(); i++){
							
							vo = (Pc_MapeoVendedorVO) mapeos.get(i);
							
							Object[] parametros = {
									vo.getPcCveVendedor() + "",
									vo.getPcSistema(),
									vo.getPcCustomerId() + "",
									vo.getPcCveRegVenta(),
									vo.getPcNomUsuario(),
									user.getPcCveUsuario() + "",
									user.getPcUserid(),
									user.getPcNombreCuenta(),
									"B"
								};
	
							logger.info("Antes de insertar...");
							CatalogoFacade.setMapeoVendedorMov(parametros);
						}
					}

				}catch(CatalogoSystemException cse){
					logger.error("Error al eliminar mapeo " + cse.toString());
				}
				
				request.getSession().setAttribute("noData","Se ha eliminado el Mapeo para el Vendedor con clave : " + claveVendedor + " en el sistema " + tokensClave[0]);
			}catch(CatalogoSystemException cse){
				
				logger.error("Error al eliminar mapeo " + cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				
				logger.error("Error: " + e.toString());
				request.setAttribute("e_error", e.toString());
				return mapping.findForward("error");
			}
			
			return buscaMapeos(mapping, form, request, response);
	}
	/***********************************************************************************
	 * modificar
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			if(request.getSession().getAttribute("mapeoVendedor") != null) request.getSession().removeAttribute("mapeoVendedor"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

			try{
				String claveVendedor = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
				String sistema = (request.getParameter("pcSistema").equals(""))? null:request.getParameter("pcSistema");
				String customerID = (request.getParameter("pcCustomerId").equals(""))? null:request.getParameter("pcCustomerId");
				String claveRegVentas = (request.getParameter("pcCveRegVenta").equals(""))? null:request.getParameter("pcCveRegVenta");
				String nomUsuario = (request.getParameter("pcNomUsuario").equals(""))? null:request.getParameter("pcNomUsuario");				
				
				String claveCompuesta = (request.getParameter("pcMapeoVendedorRadio").equals(""))? null:request.getParameter("pcMapeoVendedorRadio");
				
				//String[] tokensClave = claveCompuesta.split("/");
				// La clase StringTokenizer se supone que sera reemplazada por el metodo String.split() en 1.4.2
				String[] tokensClave = {"",""};
				StringTokenizer st = new StringTokenizer(claveCompuesta,"/");
				int indice = 0;
     			while (st.hasMoreTokens()) {
					tokensClave[indice++] = st.nextToken();
				}
			
				Object[] parametros = {
						claveVendedor,
						sistema,
						customerID,
						claveRegVentas,
						nomUsuario,			
						tokensClave[0],
						tokensClave[1]
					};
			
				CatalogoFacade.changeMapeoVendedor(parametros);

				request.getSession().setAttribute("noData","Se actualizó el Mapeo para el Vendedor con clave : " + claveVendedor);
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				ArrayList mapeoVendedor= null;
				
				if(subdivisionUsuario==0){
					mapeoVendedor= AdminCatFacade.getMapeoVendedor(claveVendedor);
				}else{
					mapeoVendedor= CatalogoFacade.getMapeoVendedor(claveVendedor, String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
				}

				if(mapeoVendedor == null)
					request.getSession().setAttribute("noData","no existen Claves adicionales para el Vendedor con clave " + claveVendedor);
				else
					request.getSession().setAttribute("mapeoVendedor", mapeoVendedor);

				
			}catch(CatalogoSystemException cse){
				logger.error("Error: " + cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				e.printStackTrace();
				logger.error("Error: " + e.toString());
				request.setAttribute("e_error", e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
	/***********************************************************************************
	 * consultar
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			try{
				if(request.getSession().getAttribute("mapeoVendedor") != null) request.getSession().removeAttribute("mapeoVendedor"); 
				if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
		
				String control= null;
			 
				control=request.getParameter("control");
					
				if(control == null){
					return mapping.findForward("home");
				}else if(control.equals("vendedores")){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					return buscaVendedores(mapping, form, request, response);
				}else if(control.equals("mapeos")){
					return buscaMapeos(mapping, form, request, response);
				}
			}catch(CatalogoSystemException cse){
				
				logger. error("Error: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				
				logger.error("Error: " + e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
	/********************************************************************************************
	 * ClaveReferencia BuscaVendedores
	 *  
	 ********************************************************************************************/
	public ActionForward buscaVendedores(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
	
			ArrayList tablaVendedores = new ArrayList(); 					

			try{

				String puestoBuscar = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");
				String vendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
				String nombreVendedorBuscar = (request.getParameter("pcNomVendedorSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
				String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
				
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				
				if(vendedorBuscar == null && nomClaveEmpleadoReferenciaBuscar == null ){
					if(subdivisionUsuario==0){
						tablaVendedores = AdminCatFacade.getVendedoresTable(new Object[] {puestoBuscar,nombreVendedorBuscar});	
					}else{
						tablaVendedores = CatalogoFacade.getVendedoresTable(puestoBuscar,nombreVendedorBuscar,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));					
					}
				}else if (vendedorBuscar != null && nomClaveEmpleadoReferenciaBuscar == null){
					Pc_VendedoresVO detalleVendedor = null;
					if(subdivisionUsuario==0){
						detalleVendedor = AdminCatFacade.getVendedor(vendedorBuscar);
					}else {
						detalleVendedor = CatalogoFacade.getVendedor(vendedorBuscar,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
					}
					if(detalleVendedor != null)tablaVendedores.add(detalleVendedor); 
				} else if(nomClaveEmpleadoReferenciaBuscar != null && vendedorBuscar == null){
					
					Pc_VendedoresVO detalleVendedor = null;
					if(subdivisionUsuario==0){
						detalleVendedor = AdminCatFacade.getVendedorByClave(nomClaveEmpleadoReferenciaBuscar);
					}else {
						detalleVendedor = CatalogoFacade.getVendedorByClave(nomClaveEmpleadoReferenciaBuscar,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
					}
					if(detalleVendedor != null)tablaVendedores.add(detalleVendedor); 
				}
			}catch(CatalogoSystemException cse){
				logger.error("Error: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error: " + e.toString());
				return mapping.findForward("error");
			}
	
			if(!tablaVendedores.isEmpty()){
				request.getSession().setAttribute("tablaVendedores",tablaVendedores);
			}else{
				request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
			}
			return mapping.findForward("home");
	}
	/********************************************************************************************
	 * ClaveReferencia BuscaMapeos
	 *  
	 ********************************************************************************************/
	public ActionForward buscaMapeos(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			String claveVendedor = (request.getParameter("pcCveVendedorRadio").equals(""))? null:request.getParameter("pcCveVendedorRadio");
			
			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();

			ArrayList mapeoVendedor= null;
			if (subdivisionUsuario==0){
				mapeoVendedor= AdminCatFacade.getMapeoVendedor(claveVendedor);
			}else{
				mapeoVendedor= CatalogoFacade.getMapeoVendedor(claveVendedor,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
			}

			if(mapeoVendedor == null)
				request.getSession().setAttribute("noData","no existen Claves adicionales para el Vendedor con clave " + claveVendedor);
			else{
				request.getSession().setAttribute("mapeoVendedor", mapeoVendedor);
//				((ClaveReferenciaForm) form).setPcCveVendedor(mapeoVendedor.getPcCveVendedor());						
//				((ClaveReferenciaForm) form).setPcSistema(mapeoVendedor.getPcSistema());						
//				((ClaveReferenciaForm) form).setPcCustomerId(mapeoVendedor.getPcCustomerId());						
//				((ClaveReferenciaForm) form).setPcCveRegVentas(mapeoVendedor.getPcCveRegVentas());						
//				((ClaveReferenciaForm) form).setPcNomUsuario(mapeoVendedor.getPcNomUsuario());						
			}
			
			ClaveReferenciaForm forma = (ClaveReferenciaForm) form;
			
			forma.setPcCveVendedor(0);
			forma.setPcSistema(null);
			forma.setPcCustomerId(0);
			forma.setPcCveRegVenta(null);
			forma.setPcNomUsuario(null);
			
			return mapping.findForward("home");
	}	
}
