/*
 * Created on Feb 22, 2005
 * Modified June 2008.
 *
 */
package mx.com.iusacell.catalogo.web.asistentes.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.utilerias.Fecha;

import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;

import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;
import java.sql.Connection;
import java.sql.SQLException;
import mx.com.iusacell.catalogo.utilerias.GeneralDAO;
import mx.com.iusacell.catalogo.bd.ProveedorConexion;

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
public class AsistReasignarProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(AsistReasignarProxy.class);

	/**
	 * Puestos Proxy
	 *  
	 */
	public AsistReasignarProxy() {
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
			AsistReasignarForm forma = (AsistReasignarForm) form;
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
						
			try {
				int subdivisionUsuario = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCveDiv();
				int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
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

				String vendedor = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
				String superiorReasignar = (request.getParameter("pcCveSuperiorReasignar").equals(""))? null:request.getParameter("pcCveSuperiorReasignar");
				String fechaMovimiento = (request.getParameter("pcFchMovimiento").equals(""))? Fecha.dateToString(new Date()):request.getParameter("pcFchMovimiento");
				String superiorActual = (request.getParameter("pcCveSuperiorActual").equals(""))? Fecha.dateToString(new Date()):request.getParameter("pcCveSuperiorActual");			
				
				Pc_VendedoresVO detalleVendedor = null;
				if(superiorReasignar!=null){
                   if(superiorReasignar.equals(vendedor)){
					   request.getSession().setAttribute("noData"," No es factible realizar este movimiento para la clave: " + vendedor);
                    }else{
                    
					Pc_VendedoresVO detalleSuperior = null;
					if(subdivisionUsuario==0 || encontrado){
						detalleSuperior = AdminCatFacade.getPersonal(superiorReasignar);
					}else{
						detalleSuperior = CatalogoFacade.getPersonal(superiorReasignar, divisionBuscar1, divisionBuscar2);	
					}
                    
					//String cuenta = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
					ArrayList tablaSubordinados = AsistCommons.buscarSubordinados(vendedor, subdivisionUsuario,divisionUsuario,divisionBuscar1, encontrado);
					if(!tablaSubordinados.isEmpty()){
									
						Iterator itera = tablaSubordinados.iterator();
						Connection conn = null;
						int bandera = 0;
						try {
						 	GeneralDAO dao = new GeneralDAO();
							conn = ProveedorConexion.getConnection();
							conn.setAutoCommit(false); 
							bandera = 0;
						 
							while(itera.hasNext()){
								detalleVendedor =(Pc_VendedoresVO)itera.next();
								if(detalleVendedor !=null){
									String CveSupCad = String.valueOf(detalleVendedor.getPcCveSupCad());
									String CvePuesto = String.valueOf(detalleVendedor.getPcCvePuesto());
									String CveVendedor =  String.valueOf(detalleVendedor.getPcCveVendedor());
									/**
									 * Con esta validación evitamos el ciclado de los superiores en los vendedores.
									 */
									if(detalleSuperior.getPcCveVendedor()== detalleVendedor.getPcCveVendedor()){
										superiorReasignar = superiorActual;
									}else{
										superiorReasignar = (request.getParameter("pcCveSuperiorReasignar").equals(""))? null:request.getParameter("pcCveSuperiorReasignar");
									}
									
									String cveSubdivision=(detalleSuperior.getPcCveSubdiv()==0)?
										(subdivisionUsuario==0 || encontrado)?String.valueOf(detalleVendedor.getPcCveSubdiv()):divisionBuscar1:
										String.valueOf(detalleSuperior.getPcCveSubdiv());
								
									Object[] parametros = {
										CveVendedor, 
										CvePuesto, 
										superiorReasignar,
										detalleVendedor.getPcNomVendedor(),
										detalleVendedor.getPcApePaterno(),
										detalleVendedor.getPcApeMaterno(),
										detalleVendedor.getPcCntrTda(),
										detalleVendedor.getPcCveEmpRef(),
										detalleVendedor.getPcCiudad(),
										detalleVendedor.getPcEstado(),
										detalleVendedor.getPcDireccion(),
										detalleVendedor.getPcColonia(),
										detalleVendedor.getPcCp(),
										detalleVendedor.getPcTel(),
										detalleVendedor.getPcFax(),
										detalleVendedor.getPcFechaAltaStr(),
										CveSupCad,
										cveSubdivision, 
										String.valueOf(detalleVendedor.getPcCveEmpresa()),
										user
										};
								
									ClavesVO mov = new ClavesVO();
									//mov = CatalogoFacade.getMovVendedoresHis(CveVendedor, String.valueOf("3"));
									int clave = dao.executeCuenta(319, new Object[]{CveVendedor, String.valueOf("3")}, conn);
									
									
									if(clave < 0) {
										bandera = -1;
									} else {
										if(clave == 0) {				
											//mov = new ClavesVO();
											mov.setClave(0);
										} else
											mov.setClave(clave);
									}
									
									Object[] vendedoresHist = {
										CveVendedor, 
										CvePuesto, 
										superiorReasignar,
										Fecha.getHoraActual(fechaMovimiento),
										"3",
										String.valueOf(cveUsuario)
									};
									
									bandera = dao.executeTransaccion(14, parametros, conn);
									if(bandera > 0) {
										if(mov.getClave() == 0)
											bandera = dao.executeTransaccion(377, vendedoresHist, conn);
										else {
											Object[] parametrosVendHist = {String.valueOf(mov.getClave()), 
												CveVendedor, Fecha.getHoraActual(fechaMovimiento), String.valueOf(cveUsuario), String.valueOf("3")};
												
											bandera = dao.executeTransaccion(321, parametrosVendHist, conn); //actualio registro de vendedores_hist
										
											for(long k=0; k < 50000000L; k++);
											vendedoresHist = new Object[]{
												CveVendedor, 
												CvePuesto, 
												superiorReasignar,
												Fecha.getHoraActual(fechaMovimiento),
												"3",
												String.valueOf(cveUsuario)
											};
											
											if(bandera > 0)
												bandera = dao.executeTransaccion(377, vendedoresHist, conn);
											
											for(long k=0; k < 50000000L; k++);
									}
								}
							}
							
							if(bandera < 1) {
								request.getSession().setAttribute("noData","Hubo un problema al realizar los cambios para la clave " + vendedor);
								break;
							}
						}
					//aqui
						
					
							if(bandera > 0) {
								logger.info("hago commit bandera en cambiar superior " + bandera );
								conn.commit();
							} else {
								logger.info("hago rollback bandera en cambiar superior " + bandera );
								conn.rollback();
							}
						} catch (Exception e) {
							 logger.error("Error en la transaccion de agregar usuario " + e.toString());
							 try {
								 conn.rollback();
							 } catch(SQLException sqle) {
								 logger.error("Error al hacer rollback " + sqle.toString());
							 }
						} finally {
							 try {
								 conn.close();
							 } catch(SQLException sqle1) {
								 logger.error("Error al cerrar la conexion " + sqle1.toString());
							 }
						}	
			   	  } 
				}
			  }	
			}catch(CatalogoSystemException cse){
				logger.error("modificar cse_error: " + cse.toString());
				return mapping.findForward("error");							
			} catch (Exception e) {
				logger.error("modificar e_error: " + e.toString());
				return mapping.findForward("error");
			}
			
			((AsistReasignarForm) form).setPage(3);
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
				}else if(control.equals("vendedores")){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

					String puestoBuscar = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");
					String vendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
					String nombreVendedorBuscar = (request.getParameter("pcNomVendedorSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
					String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
					
					((AsistReasignarForm) form).reset(mapping,request);
					if(puestoBuscar!= null && !puestoBuscar.equals("")){
						((AsistReasignarForm) form).setPcCvePuesto(Integer.parseInt(puestoBuscar));
					} else
						((AsistReasignarForm) form).setPcCvePuesto(1);
							
					((AsistReasignarForm) form).setPcCveVendedor(0);

					ArrayList tablaVendedores = AsistCommons.buscarVendedores(vendedorBuscar, puestoBuscar, nombreVendedorBuscar,
					                                                                          nomClaveEmpleadoReferenciaBuscar, subdivisionUsuario, divisionUsuario,
					                                                                                        listaDivs,encontrado,rolUser, request);
					if(!tablaVendedores.isEmpty()){
						request.getSession().setAttribute("tablaVendedores",tablaVendedores);
					}else{
						request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
					}
					return mapping.findForward("home");
				}else if(control.equals("superior_reasignar")){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					if(request.getSession().getAttribute("tablaSuperiores") != null) request.getSession().removeAttribute("tablaSuperiores"); 
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

					String puestoBuscar = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");
					String vendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
					String nombreVendedorBuscar = (request.getParameter("pcNomVendedorSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
					String cvePuesto = (request.getParameter("pcCvePuestoReasignar").equals(""))? null:request.getParameter("pcCvePuestoReasignar");
					String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");

					ArrayList tablaSuperiores = AsistCommons.buscarSuperior(vendedorBuscar, puestoBuscar, nombreVendedorBuscar,nomClaveEmpleadoReferenciaBuscar, 
					                                                           cvePuesto, subdivisionUsuario, divisionUsuario, listaDivs,encontrado,rolUser, request
					);
					if(!tablaSuperiores.isEmpty()){
						request.getSession().setAttribute("tablaSuperiores",tablaSuperiores);
					}else{
						request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
					}
					return mapping.findForward("home");
				}else if(control.equals("siguiente")){
					if(((AsistReasignarForm) form).getPage()==0){
						if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
						if(request.getSession().getAttribute("tablaSuperiores") != null) request.getSession().removeAttribute("tablaSuperiores"); 
						if(request.getSession().getAttribute("tablaSubordinados") != null) request.getSession().removeAttribute("tablaSubordinados"); 
						if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData"); 
						if(request.getSession().getAttribute("noSubordinados") != null) request.getSession().removeAttribute("noSubordinados"); 

						((AsistReasignarForm) form).setPcCveVendedorSeek(0);
						((AsistReasignarForm) form).setPcCvePuestoSeek(0);
						((AsistReasignarForm) form).setPcNomVendedorSeek(null);
						String vendedor = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
						ArrayList tablaSubordinados = AsistCommons.buscarSubordinados(vendedor, subdivisionUsuario, subdivisionUsuario,divisionBuscar1, encontrado);	
						if(!tablaSubordinados.isEmpty()){
							((AsistReasignarForm) form).setPcCvePuestoReasignar(((Pc_VendedoresVO) tablaSubordinados.get(0)).getPcCvePuesto());
							request.getSession().setAttribute("tablaSubordinados",tablaSubordinados);
						}else{
							request.getSession().setAttribute("noSubordinados","La persona seleccionada no tiene Subordinados");
						}
					}
					((AsistReasignarForm) form).setPage(Integer.parseInt(request.getParameter("page"))+1);
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
					return mapping.findForward("home");
				}else if(control.equals("regresar")){
					((AsistReasignarForm) form).setPage(Integer.parseInt(request.getParameter("page"))-1);
					((AsistReasignarForm) form).reset(mapping,request);
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



