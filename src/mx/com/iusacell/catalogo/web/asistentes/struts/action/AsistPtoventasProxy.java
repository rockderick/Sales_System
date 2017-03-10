/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.asistentes.struts.action;

import java.util.ArrayList;
import java.util.Date;


import mx.com.iusacell.catalogo.dao.CatalogoFacade;

import mx.com.iusacell.catalogo.utilerias.Fecha;

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
 * @author Daniel Vazquez A.
 * 
 * @version 1.0
 * 
 */
public class AsistPtoventasProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(AsistPtoventasProxy.class);
	/**
	 * Puestos Proxy
	 *  
	 */
	public AsistPtoventasProxy() {
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
			AsistPtoventasForm forma = (AsistPtoventasForm) form;
			forma.setPage(0);
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

			try{

				if(request.getSession().getAttribute("tablaRelaciones") != null) request.getSession().removeAttribute("tablaRelaciones"); 
				if(request.getSession().getAttribute("noDataRelaciones") != null) request.getSession().removeAttribute("noDataRelaciones");
			
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				
				/**
				 * Código en prueba.
				 * 5/05/08.
				 */
				String listaDivs = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
				if(listaDivs==null){
					listaDivs = "";
				}
				String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				int rolUser = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveRol();
				boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
				if(encontrado){
					subdivisionUsuario = 0; 
					divisionUsuario=0;
			    }
				String divisionBuscar1 = String.valueOf(subdivisionUsuario);
				String divisionBuscar2 = String.valueOf(divisionUsuario);
					if(subdivisionUsuario!=0 || !encontrado){ 
						divisionBuscar1= 
							   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
					    divisionBuscar2=
						       ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();      
				}
				
				int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();

				String cveVendedor = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");	 
				String cvePtoventas = (request.getParameter("pcCvePtoventas").equals(""))? null:request.getParameter("pcCvePtoventas");	 

				String fechaMovimiento = (request.getParameter("pcFchMovimiento").equals(""))? Fecha.dateToString(new Date()):request.getParameter("pcFchMovimiento");
							
				int cambios =AsistCommons.checkCambios(cveVendedor,cvePtoventas,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario),fechaMovimiento,String.valueOf(cveUsuario),String.valueOf(cveUsuario)); 
				if(cambios>0){
					ClavesVO clave = new ClavesVO();
					clave = CatalogoFacade.getRelacionClave();
					Object[] parametros = {
						String.valueOf(clave.getClave()),
						cveVendedor, 
						cvePtoventas,
						fechaMovimiento,
						String.valueOf(cveUsuario),   // el atributo usuario es numerico
						String.valueOf(cveUsuario),
						Fecha.getHoraActual(fechaMovimiento),// el atributo autorizacion es numerico
						user
					};
			
					CatalogoFacade.setRelacion(parametros);
				}else if (cambios == -1){
	
				}

				ArrayList tablaRelaciones = AsistCommons.obtenerRelaciones(cvePtoventas,cveVendedor,divisionBuscar1,String.valueOf(subdivisionUsuario), encontrado);

				if(!tablaRelaciones.isEmpty()){
					request.getSession().setAttribute("tablaRelaciones",tablaRelaciones);
				}else{
					request.getSession().setAttribute("noDataRelaciones","No existen Relaciones para este Componente");
				}
				((AsistPtoventasForm) form).setPage(5);
			}catch(CatalogoSystemException cse){
				logger.error("Error al agregar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error al agregar: " + e.toString());
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
			return mapping.findForward("home");
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

			String control= null;
			 
			try{
				control=request.getParameter("control");
				int subdivisionUsuario = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCveDiv();
				/**
				 * Código en prueba.
				 * 10/06/08.
				 */
				String listaDivs = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
					if(listaDivs==null){
						listaDivs = "";
					}
				String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				int rolUser = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveRol();
				boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
				if(encontrado){subdivisionUsuario = 0; divisionUsuario=0;}
						String divisionBuscar1 = String.valueOf(subdivisionUsuario);
						String divisionBuscar2 = String.valueOf(divisionUsuario);
					if(subdivisionUsuario!=0 || !encontrado){ 
						divisionBuscar1= 
							   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
					    divisionBuscar2=
							   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();      
					}
				/**
				 * Fin de Código de prueba.
				 */	
				if(control == null){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					if(request.getSession().getAttribute("detalleVendedores") != null) request.getSession().removeAttribute("detalleVendedores");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
					return mapping.findForward("home");
				}else if(control.equals("tipo_canal_seek")){
					return AsistCommons.buscarTipoCanal(mapping,form,request,response);
				}else if(control.equals("vendedor")){
					if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
					if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

					String puestoBuscar = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");
					String vendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
					String nombreVendedorBuscar = (request.getParameter("pcNomVendedorSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
					String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
					
					((AsistPtoventasForm) form).reset(mapping,request);
					if(puestoBuscar!= null && !puestoBuscar.equals(""))
						((AsistPtoventasForm) form).setPcCvePuesto(Integer.parseInt(puestoBuscar));
					else
						((AsistPtoventasForm) form).setPcCvePuesto(1);
							
					((AsistPtoventasForm) form).setPcCveVendedor(0);

					ArrayList tablaVendedores = AsistCommons.buscarVendedoresA(vendedorBuscar, puestoBuscar, nombreVendedorBuscar,
					                                                          nomClaveEmpleadoReferenciaBuscar, subdivisionUsuario, divisionUsuario,
					                                                          listaDivs,encontrado,rolUser, request);
					if(!tablaVendedores.isEmpty()){
						request.getSession().setAttribute("tablaVendedores",tablaVendedores);
					}else{
						request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
					}
					((AsistPtoventasForm)form).setPage(3);
					return mapping.findForward("home");
				}else if(control.equals("puntos_venta")){
					if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
					if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

					String divisionBuscar = (request.getParameter("pcCveDivSeek").equals(""))? null:request.getParameter("pcCveDivSeek");
					String tipoCanalBuscar = (request.getParameter("pcCveTipoCanalSeek").equals(""))? null:request.getParameter("pcCveTipoCanalSeek");
					String canalBuscar = (request.getParameter("pcCveCanalSeek").equals(""))? null:request.getParameter("pcCveCanalSeek");
					String vendedorBuscar = (request.getParameter("pcCvePtoventasSeek").equals(""))? null:request.getParameter("pcCvePtoventasSeek");
					String nombreVendedorBuscar = (request.getParameter("pcNomPtoventasSeek").equals(""))? null:request.getParameter("pcNomPtoventasSeek");
				
					ArrayList tablaPuntosVenta = AsistCommons.buscarPuntoVentas(divisionBuscar ,tipoCanalBuscar ,canalBuscar ,
					                                                                            vendedorBuscar ,nombreVendedorBuscar, subdivisionUsuario, 
					                                                                                            divisionUsuario,listaDivs,encontrado,rolUser);
					if(!tablaPuntosVenta.isEmpty()){
						request.getSession().setAttribute("tablaPuntosVenta",tablaPuntosVenta);
					}else{
						request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
					}
					((AsistPtoventasForm)form).setPage(1);
					return mapping.findForward("home");
				}else if(control.equals("otro_punto")){
					String vendedor = (request.getParameter("pcCveVendedor").equals(""))? "0":request.getParameter("pcCveVendedor");
					String nombreVendedor = (request.getParameter("pcNombreCompleto").equals(""))? null:request.getParameter("pcNombreCompleto");
					((AsistPtoventasForm)form).setPcCveVendedorChg(Integer.parseInt(vendedor));	
					((AsistPtoventasForm)form).setPcNombreCompletoChg(nombreVendedor);
					((AsistPtoventasForm)form).setPage(0);
				}else if(control.equals("otro_vendedor")){
					if(request.getSession().getAttribute("tablaRelaciones") != null) request.getSession().removeAttribute("tablaRelaciones");
					if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 

					((AsistPtoventasForm)form).setPcCveVendedor(0);
					((AsistPtoventasForm)form).setPcNombreCompleto(null);					
					((AsistPtoventasForm)form).setPcCveVendedorChg(0);	
					((AsistPtoventasForm)form).setPcNombreCompletoChg(null);

					((AsistPtoventasForm)form).setPage(2);
				}else if(control.equals("siguiente")){
					switch(((AsistPtoventasForm) form).getPage()){
						case 1:
							int cveVendedor = ((AsistPtoventasForm)form).getPcCveVendedorChg();	
							String nombre = ((AsistPtoventasForm)form).getPcNombreCompletoChg();
	
							if(cveVendedor==0){
								((AsistPtoventasForm) form).setPcCveVendedor(0);
								((AsistPtoventasForm) form).setPcNombreCompleto(null);
								((AsistPtoventasForm) form).setPage(2);
							}else{
								((AsistPtoventasForm) form).setPcCveVendedor(cveVendedor);
								((AsistPtoventasForm) form).setPcNombreCompleto(nombre);
								((AsistPtoventasForm) form).setPage(4);
							}
							break;
						case 2:
							((AsistPtoventasForm) form).setPcCveVendedor(0);
							((AsistPtoventasForm) form).setPcNombreCompleto(null);
							((AsistPtoventasForm) form).setPage(3);
							break;
						case 3:
							if(request.getSession().getAttribute("tablaRelaciones") != null) request.getSession().removeAttribute("tablaRelaciones");
							if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
							if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
							String vendedor = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");	 
							String ptoventas = (request.getParameter("pcCvePtoventas").equals(""))? null:request.getParameter("pcCvePtoventas");
							if(vendedor!=null){
							  request.setAttribute("vendedor",vendedor);
							}else{
							  request.setAttribute("ptoventa",ptoventas);		 
							}
							ArrayList tablaRelaciones = AsistCommons.obtenerRelaciones(ptoventas,vendedor,divisionBuscar1,divisionBuscar2, encontrado);
							request.getSession().setAttribute("tablaRelaciones",tablaRelaciones);
							((AsistPtoventasForm) form).setPage(4);
							break;
						default:
							((AsistPtoventasForm) form).setPage(Integer.parseInt(request.getParameter("page"))+1);
							break;
					}				
					return mapping.findForward("home");
				}else if(control.equals("regresar")){
					((AsistPtoventasForm) form).setPage(Integer.parseInt(request.getParameter("page"))-1);
					//logger.info("Regresando a:" + ((AsistPtoventasForm) form).getPage());
					((AsistPtoventasForm) form).reset(mapping,request);
					return mapping.findForward("home");
				}else if(control.equals("buscar")){
					return mapping.findForward("home");
				}
			}catch(Exception e){
				logger.error("consultar e_error: " + e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
}



