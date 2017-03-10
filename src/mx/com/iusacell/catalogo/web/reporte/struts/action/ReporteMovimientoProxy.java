/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;

import java.util.ArrayList;

import java.util.Date;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;

import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;

import mx.com.iusacell.catalogo.modelo.UserSessionVO;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

import mx.com.iusacell.catalogo.utilerias.Fecha;

import mx.com.iusacell.catalogo.web.personal.struts.action.PersonalMovProxy;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * @author Dvazqueza
 *
 * Paquete : mx.com.iusacell.catalogo.web.reporte.struts.action
 * Proyecto : AdminCatalogoWeb
 *
 */
public class ReporteMovimientoProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(ReporteMovimientoProxy.class);

	/**
	 * PuntosVentaProxy
	 * 
	 * 
	 *
	 *  
	 * 
	 */
	public ReporteMovimientoProxy() {
	}
	
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {

			String control= null;
			if(request.getSession().getAttribute("tablaReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			 
			try{				
				String fechaInicio = (request.getParameter("rangoFchDesde").equals(""))? null:request.getParameter("rangoFchDesde");
				String fechaFin = (request.getParameter("rangoFchHasta").equals(""))? Fecha.dateToString(new Date()):request.getParameter("rangoFchHasta");
				String vendedor = null;
				
				try{
					vendedor = (request.getParameter("pcCveVendedorRadio").equals(""))? null:request.getParameter("pcCveVendedorRadio");
				}catch(NullPointerException npe){}
				
				ArrayList tablaReporte = new ArrayList();
				
				if(fechaInicio != null && fechaFin != null){
					Date dateInicio = Fecha.stringToDate(fechaInicio);
					Date dateFinal = Fecha.stringToDate(fechaFin);
				
					if(dateInicio.after(dateFinal)) fechaInicio= null;
				}else{
					fechaInicio=null;
					fechaFin=null;
				}
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				String listaDivs = "";
				  if(((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs()!= null)
					   listaDivs = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs();
				   
				       String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
				if(encontrado){subdivisionUsuario = 0; divisionUsuario=0;}
				String divisionBuscar = String.valueOf(subdivisionUsuario);
				String divisionBuscar2 = String.valueOf(divisionUsuario);
				  if(subdivisionUsuario!=0 || !encontrado){ 
				 	  divisionBuscar= 
						   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
					  divisionBuscar2=
						   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();      
				  }
				
				if(vendedor==null && fechaInicio==null){
					if(subdivisionUsuario==0 || encontrado){
						tablaReporte = AdminCatFacade.getMovimientosTabla(String.valueOf(500));
					}else{
						tablaReporte = CatalogoFacade.getMovimientosTabla(String.valueOf(500), divisionBuscar, String.valueOf(divisionUsuario));
					}
				}else{
					if(subdivisionUsuario==0 || encontrado){
						Object[] param = {vendedor, fechaInicio,fechaFin};
						tablaReporte = AdminCatFacade.getMovimientosTablaParametro(param);
					}else{
						tablaReporte = CatalogoFacade.getMovimientosTablaParametro(vendedor, fechaInicio,fechaFin, divisionBuscar,String.valueOf(divisionUsuario));
					}
				}
								
				if(tablaReporte.isEmpty()){
					request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
				}else{
					request.getSession().setAttribute("tablaReporte",tablaReporte);
				}
			}catch(CatalogoSystemException cse){
				logger.error(cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("error en verLista " + e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}

	/**
 	* consultar
 	* 
 	* @param mapping - ActionMapping 
 	* @param form - ActionForm 
 	* @param request - HttpServletRequest 
 	* @param response - HttpServletResponse
 	* @return  ActionForward 
 	*/
	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			if(request.getSession().getAttribute("tablaReporte") != null) request.getSession().removeAttribute("tablaReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			 
			try{
				String divisionABuscar = (request.getParameter("pcCveDivisionSeek").equals(""))? null:request.getParameter("pcCveDivisionSeek");
				String puestoBuscar = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");
				String vendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
				String nombreVendedorBuscar = (request.getParameter("pcNomVendedorSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
				String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
				
				
				control=request.getParameter("control");
				int queryId = 0;

				if(control== null || control.equals("")) control = "vendedor";				

				if(control.equals("vendedor") ){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 

					ArrayList tablaVendedores = new ArrayList();
					
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
					int rolUser = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveRol();
					String listaDivs = "";
								if(((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs()!= null)
									   listaDivs = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs();
				   
					String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
					boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
					if(encontrado){subdivisionUsuario = 0; divisionUsuario=0;}
									String divisionBuscar = String.valueOf(subdivisionUsuario);
									String divisionBuscar2 = String.valueOf(divisionUsuario);
										if(subdivisionUsuario!=0 || !encontrado){ 
											divisionBuscar= 
										   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
										   divisionBuscar2=
										   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();      
										}
					String lineaSQL = "";
					ArrayList detalleVen = new ArrayList();
					/**
					 * Si no hay clave de vendedor y si no hay numero de referencia del empleado.
					 */
					if(vendedorBuscar == null && nomClaveEmpleadoReferenciaBuscar == null){
						((ReporteMovimientoForm) form).reset(mapping,request);
					    
						/**
						 * En caso de que exista la división del vendedor.
						 */
						if(divisionABuscar!=null && nomClaveEmpleadoReferenciaBuscar==null && nombreVendedorBuscar==null){
						 	  lineaSQL = PersonalMovProxy.armaQueryBusquedaDivision
														  ("",request,puestoBuscar,rolUser,encontrado,
																				  subdivisionUsuario,divisionBuscar,
																									   divisionABuscar);
						}																									   
						/**
						 * En caso de que exista tanto nombre como puesto del vendedor.
						 */	
						if(nombreVendedorBuscar!= null && puestoBuscar!= null){
							   lineaSQL = PersonalMovProxy.armaQueryBusquedaFiltros
							           				       ("",request,"","",puestoBuscar,nombreVendedorBuscar,rolUser,
							           				                                  encontrado,subdivisionUsuario,divisionBuscar);
							}
						/**
						 * En caso de que exista el nombre del vendedor a buscar.
						 */
						if(nombreVendedorBuscar!= null && puestoBuscar== null){
							 lineaSQL = PersonalMovProxy.armaQueryBusquedaNombre
						 							     ("",request,nombreVendedorBuscar,rolUser,encontrado,
						 							                                      subdivisionUsuario,divisionBuscar);
						}
						/**
						 * En caso de que exista el puesto del vendedor.
						 */	
						if(puestoBuscar!= null && nombreVendedorBuscar== null){
							 lineaSQL = PersonalMovProxy.armaQueryBusquedaPuesto
													   ("",request,puestoBuscar,rolUser,encontrado,
													                            subdivisionUsuario,divisionBuscar);
						}	
					}
					/**
					  * Si hay clave pero no hay numero de referencia del empleado.
					  */
					 else if(vendedorBuscar != null && nomClaveEmpleadoReferenciaBuscar == null) {
						((ReporteMovimientoForm) form).reset(mapping,request);
				      	     lineaSQL = PersonalMovProxy.armaQueryBusquedaClave
								                         ("",request,vendedorBuscar,rolUser,encontrado,
								                                                    subdivisionUsuario,divisionBuscar);					
					}
					/**
					  * Si hay numero de referencia pero no hay clave de vendedor.
					  */
					 else if(nomClaveEmpleadoReferenciaBuscar != null && vendedorBuscar == null){
						((ReporteMovimientoForm) form).reset(mapping,request);
						/**
						 * En caso de que solo hayan proporcionado el numero de empleado.
						 */ 
						  	lineaSQL = PersonalMovProxy.armaQueryBusquedaNoEmpleado
							     					    ("",request,nomClaveEmpleadoReferenciaBuscar,rolUser,encontrado,
							     					                                                 subdivisionUsuario,divisionBuscar);  
					}
					
					Pc_VendedoresVO detalleVendedor = new Pc_VendedoresVO();
					detalleVen = CatalogoFacade.getBuscaVendedores(lineaSQL);
				    if(detalleVen != null){
					    for(int i=0;i<detalleVen.size();i++){
						     detalleVendedor = (Pc_VendedoresVO)detalleVen.get(i);
						     tablaVendedores.add(detalleVendedor);	
					    }
				    }	
				
					if(!tablaVendedores.isEmpty()){
						request.getSession().setAttribute("tablaVendedores",tablaVendedores);
					}else{
						request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
					}
					
				}else if(control.equals("movimientos")){
					return verLista(mapping, form, request, response);
				}
			}catch(CatalogoSystemException cse){
				logger.error(cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("error en consultar" +e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
}

