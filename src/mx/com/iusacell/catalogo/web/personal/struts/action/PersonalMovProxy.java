/*
 * Created on Mar 17, 2005
 *
 */
package mx.com.iusacell.catalogo.web.personal.struts.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.HashMap;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.dao.PersonalFacade;
import mx.com.iusacell.catalogo.utilerias.Fecha;
import mx.com.iusacell.catalogo.utilerias.Q;
import mx.com.iusacell.catalogo.utilerias.DigitoVerificador;

import mx.com.iusacell.catalogo.modelo.Pc_PuestosVO;

import mx.com.iusacell.catalogo.modelo.Pc_SuperioresVO;
import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.modelo.Pc_Inter_Vendedores_PtoventasVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import java.sql.Connection;
import java.sql.SQLException;
import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.iusacell.catalogo.bd.ProveedorConexion;
import mx.com.iusacell.catalogo.utilerias.GeneralDAO;

import org.jdom.*;
import org.jdom.output.XMLOutputter;

import org.apache.log4j.Logger;

/**
 * @author Dvazqueza
 * 
 * @version 1.0
 * 
 * tablaPuestos - Genera la Lista de los Puestos desde la BD
 * tablaRegiones - Genera la Lista de los Regiones desde la BD
 */
public class PersonalMovProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(PersonalMovProxy.class);
	/**
	 * Personal Proxy
	 *  
	 */
	public PersonalMovProxy() {
	}
	
	/*****************************************************************************************
	 * Personal VerLista
	 *  
	 *****************************************************************************************/
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
			ArrayList tablaPuestos = new ArrayList();
			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
			if(request.getSession().getAttribute("detalleVendedore") != null) request.getSession().removeAttribute("detalleVendedore"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
			if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
		
			tablaPuestos = CatalogoFacade.getTablaPuestos();
			
			request.getSession().setAttribute("tablaPuestos",tablaPuestos);	
			return mapping.findForward("home");
	}
	/*****************************************************************************************
	 * Personal Agregar
	 *  
	 *****************************************************************************************/
	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
	
			logger.info("Entre a PersonalMovProxy agregar");
			
			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
			if(request.getSession().getAttribute("detalleVendedore") != null) request.getSession().removeAttribute("detalleVendedore"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
			if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
			String listaDivs = "";
			if(((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs()!= null)
			       listaDivs = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs();
			int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			int Usuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
			String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
			boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
			
			String control = (request.getParameter("control")== null)?"":request.getParameter("control");
			
			
			try {
				
				String nombreVend = request.getParameter("pcNomVendedor").equals("")? null:request.getParameter("pcNomVendedor");
				String apePaterno = request.getParameter("pcApePaterno").equals("")? null:request.getParameter("pcApePaterno");
				String apeMaterno = request.getParameter("pcApeMaterno").equals("")? null:request.getParameter("pcApeMaterno");

				((PersonalMovForm) form).setPcDistMAster(""+request.getParameter("pcDistMAster"));

 /* *******************************************************************************************
  * 17/May/2005
  * Codigo para buscar el vendedor antes de insertar uno nuevo
  * *******************************************************************************************/
  				String tipoRegistro= request.getParameter("pcTipoRegistro");
				
				if(tipoRegistro.equals("distribuidor") || tipoRegistro.equals("interno")) {
					String claveDefinida = request.getParameter("pcCveVendedorDefine");
					((PersonalMovForm) form).setPcCveVendedor(Integer.parseInt(claveDefinida));
				}
				// Busca vendedores con los criterios de nombre 
 				ArrayList tablaVendedores = null;
				if(!control.equals("confirmar")){
					String nombre = apePaterno+" "+apeMaterno;
					String nombreVendedor = armaQueryBusquedaNombre("",request,nombre,1,true,0,"");
					tablaVendedores = CatalogoFacade.getBuscaVendedores(nombreVendedor);  
				}
				
				// En caso de encontrar algun vendedor, solicita confirmacion para insertarse
				if(tablaVendedores!=null && !tablaVendedores.isEmpty()){
					Object[] vendedores =  tablaVendedores.toArray(); 
					Arrays.sort(vendedores);
					tablaVendedores.clear();
					tablaVendedores.addAll(Arrays.asList(vendedores));
					request.getSession().setAttribute("tablaVendedores",tablaVendedores);
					request.getSession().setAttribute("porConfirmar","confirmar");
					if(!(request.getParameter("pcNivelVentas") == null))
						request.setAttribute("ventasConfirmar",request.getParameter("pcNivelVentas"));
 /* *******************************************************************************************
  * Termina codigo de verificacion
  * 
  * *******************************************************************************************/
				// Si no se encuentra, entonces se debera insertar
				} else {
					ClavesVO clave = new ClavesVO();
					tipoRegistro= request.getParameter("pcTipoRegistro");
					
					if(tipoRegistro.equals("persona") || tipoRegistro == null){
						clave = CatalogoFacade.getPersonalClavePersona();
					}else if(tipoRegistro.equals("tienda") ){
						clave = CatalogoFacade.getPersonalClaveTienda();
					}else if(tipoRegistro.equals("distribuidor") || tipoRegistro.equals("interno") ){
	
						String claveDefinida = request.getParameter("pcCveVendedorDefine");
						if(claveDefinida.equals(""))
							clave = new ClavesVO(0);
						else
							clave = new ClavesVO(Integer.parseInt(claveDefinida));
					}
					String Status = (request.getParameter("pcStatus").equals(""))? null:request.getParameter("pcStatus");
					String CveSuperior = (request.getParameter("pcCveSuperior").equals(""))? null:request.getParameter("pcCveSuperior");
					String CveSupCad = null;//(request.getParameter("pcCveSupCad").equals(""))? null:request.getParameter("pcCveSupCad");
					String fechaAlta = (request.getParameter("pcFchAlta").equals(""))? Fecha.dateToString(new Date()):request.getParameter("pcFchAlta");
	
					String CveSubdiv = null;
					logger.info("CveSuperior " + subdivisionUsuario);
					
					if(subdivisionUsuario==0 || encontrado){
						CveSubdiv = (request.getParameter("pcCveSubdiv").equals(""))? null:request.getParameter("pcCveSubdiv");
					}else{
					   if(listaDivs != null){
					      if(listaDivs.length()>3){
						     CveSubdiv = (request.getParameter("pcCveSubdiv").equals(""))? null:request.getParameter("pcCveSubdiv");
					       }else{
						     CveSubdiv = listaDivs;
					      }					
					   }
					}  
	
					if(Status == null){
						Status = "ALTA";
					}
					
					logger.info("CveSuperior " + CveSuperior);
					
					if(CveSuperior == null){
						if(subdivisionUsuario == 0 || encontrado) {
							if(CveSubdiv == null){
								CveSuperior = String.valueOf(subdivisionUsuario);
							} else
								CveSuperior = CveSubdiv;
						} else 
							CveSuperior = String.valueOf(subdivisionUsuario);
					}
					if(CveSubdiv == null){
						CveSubdiv = String.valueOf(subdivisionUsuario);
					}
					if(CveSupCad == null){
						CveSupCad = "NULL";
					}
					
					logger.info("CveSuperior " + CveSuperior);
					
					
					String digito = "0";
					if(tipoRegistro.equals("persona") || tipoRegistro.equals("tienda")){ 
						DigitoVerificador digVerif = new DigitoVerificador();
						digVerif.setDigitoVerificador((long)clave.getClave());
						digito = String.valueOf(digVerif.getDigitoVerificador());
					}else if(tipoRegistro.equals("interno")){
						if(String.valueOf(clave.getClave()).length()==6){
							if((String.valueOf(clave.getClave()).indexOf("7")==0) || ((String.valueOf(clave.getClave()).indexOf("8")==0)) || ((String.valueOf(clave.getClave()).indexOf("9")==0))){
								DigitoVerificador digVerif = new DigitoVerificador();
								digVerif.setDigitoVerificador((long)clave.getClave());
								digito = String.valueOf(digVerif.getDigitoVerificador());
								
							}
						}
					}
					
					String pcCveHorario = request.getParameter("pcCveHorario");
							
					if(pcCveHorario == null){
						pcCveHorario = "0";
					}
					
	
					Connection conn = null;
									
					Object[] parametros = {
						String.valueOf(clave.getClave()),
						request.getParameter("pcCvePuesto"), 
						CveSuperior,
						request.getParameter("pcNomVendedor"),
						request.getParameter("pcApePaterno"),
						request.getParameter("pcApeMaterno"),
						fechaAlta,
						digito,
						request.getParameter("pcCntrTda"),
						Status,
						request.getParameter("pcCveEmpRef"),
						request.getParameter("pcCiudad"),
						request.getParameter("pcEstado"),
						request.getParameter("pcDireccion"),
						request.getParameter("pcColonia"),
						request.getParameter("pcCp"),
						request.getParameter("pcTel"),
						request.getParameter("pcFax"),
						CveSupCad,
						CveSubdiv, 
						request.getParameter("pcCveEmpresa"),
						pcCveHorario,
						request.getParameter("pcNivelVentas")== "" ? "0" : request.getParameter("pcNivelVentas"),
						request.getParameter("pcTipoComision")== "" ? "S": request.getParameter("pcTipoComision"),
						user,
					    request.getParameter("pcDistMAster")
					};
					
					
					
					Object[] parametrosHis = {
						String.valueOf(clave.getClave()),
						request.getParameter("pcCvePuesto"), 
						CveSuperior,
						Fecha.getHoraActual(fechaAlta),
						"1",
						String.valueOf(Usuario),
						pcCveHorario
					};
				
					try {	
						 //CatalogoFacade.setPersonalMov(parametros);
						//AGREGO AL HISTORICO 
						//CatalogoFacade.setPersonalMovHis(parametrosHis);
						
						GeneralDAO dao = new GeneralDAO();
						conn = ProveedorConexion.getConnection();
						conn.setAutoCommit(false);
						/**
						 * Ejecutar transacción de actualización de vendedores.
						 */
						int bandera = dao.executeTransaccion(10, parametros, conn);
						
						if(bandera > 0)
							bandera = dao.executeTransaccion(315, parametrosHis, conn);
							
						if(bandera > 0) {
							
							conn.commit();
						}
						/**
						 * 
						 */
						else {
							logger.error("hago rollback bandera en PersonalMovProxy: " + bandera );
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
					
					((PersonalMovForm) form).reset(mapping,request);

					String cvePuesto = (request.getParameter("pcCvePuesto").equals(""))? null:request.getParameter("pcCvePuesto");
					((PersonalMovForm) form).setPcCvePuestoSeek(cvePuesto);
					tablaVendedores = new ArrayList();
					if(subdivisionUsuario==0 || encontrado){
						tablaVendedores.add(AdminCatFacade.getPersonal(String.valueOf(clave.getClave())));
					}else if(listaDivs!=null){
						tablaVendedores.add(AdminCatFacade.getPersonal(String.valueOf(clave.getClave())));
					}else{
						tablaVendedores.add(CatalogoFacade.getPersonal(String.valueOf(clave.getClave()),String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario)));
					}
			
					if(tablaVendedores!= null && !tablaVendedores.isEmpty()){
						request.getSession().setAttribute("tablaVendedores",tablaVendedores);
					}
				}
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
	/*****************************************************************************************
	 * Personal Eliminar
	 *  
	 *****************************************************************************************/
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
			if(request.getSession().getAttribute("detalleVendedore") != null) request.getSession().removeAttribute("detalleVendedore"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
			if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
			
			String control= null;
			String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
			boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig(); 
			 
			try {
				control = request.getParameter("control");
				String cveVendedor = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
				String fechaBaja = (request.getParameter("pcFchBaja2").equals(""))? null:request.getParameter("pcFchBaja2");
				if(fechaBaja == null) {
					fechaBaja = Fecha.dateToString(new Date());
				}

				if(cveVendedor != null) {
				   
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
					int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
				   
					if(verificarPersonal(cveVendedor,String.valueOf(subdivisionUsuario)) == true) {
						request.getSession().setAttribute("noData","No se puede dar de Baja al vendedor " + cveVendedor + " porque tiene personal asignado");
					} else {
						if(control == null || control.equals("baja")){
					   	//COMPRUEBO LOS MOVIMIENTOS HISTORICOS DEL USUARIO
						ArrayList movsVendedor = CatalogoFacade.getMovVendedoresHis(cveVendedor); // Obtiene la lista de movimientos del usuario
						ClavesVO idSigEnBitMovs = CatalogoFacade.getClave(64); // Obtiene el ID del siguiente movimiento
						
						GeneralDAO dao = new GeneralDAO();
						Connection conn = null;
						
						try {
							conn = ProveedorConexion.getConnection();
							conn.setAutoCommit(false);
							int respBaja = 0;

							Pc_VendedoresVO vendedor = null;
							vendedor = PersonalFacade.obtenerDatosVendedor(cveVendedor);
							if(movsVendedor != null) {
								for(int i = 0; i < movsVendedor.size(); i++) {
									ClavesVO claveId = (ClavesVO)movsVendedor.get(i);
									Object[] parametros = {String.valueOf(claveId.getClave()), 
										cveVendedor, Fecha.getHoraActual(fechaBaja), 
										String.valueOf(cveUsuario)};
									//ESTA OPERACION REALIZA EL CIERRE DE LOS MOVIMIENTOS DEL VENDEDOR QUE LES FALTE CIERRE					   
									PersonalFacade.cerrarMovimientosEnBaja(String.valueOf(claveId.getClave()),cveVendedor,Fecha.getHoraActual(fechaBaja),String.valueOf(cveUsuario));
								}
								//ESTA OPERACION APLICA LA BAJA EN HISTORICO
								PersonalFacade.aplicarBajaEnHist(String.valueOf(vendedor.getPcCveVendedor()),String.valueOf(vendedor.getPcCvePuesto()),String.valueOf(vendedor.getPcCveSuperior()),Fecha.getHoraActual(fechaBaja),String.valueOf(cveUsuario),String.valueOf(vendedor.getPcCveHorario()));
									
							}
						   	// Cambia el status del vendedor a BAJA
							respBaja = dao.executeTransaccion(59, new Object[]{cveVendedor,fechaBaja, user}, conn);
							request.getSession().setAttribute("noData","Se dio de baja el Vendedor con clave " + cveVendedor);
																			   						 					 
							Object parametrosMovs[] = {String.valueOf(idSigEnBitMovs.getClave()),
								cveVendedor,"ALTA","BAJA",fechaBaja,String.valueOf(cveUsuario),
								String.valueOf(cveUsuario)};
							
							if(respBaja > 0) { // Si la baja se realizo exitosamente, guarda su historico
								respBaja = dao.executeTransaccion(311, parametrosMovs, conn);
								logger.info("Hago commit en Baja. Baja de vendedor ejecutada con Exito");
								conn.commit();
							} else {
								logger.info("Hago rollback en Baja. No se pudo realizar la baja del vendedor");
								conn.rollback();
							}
							
						//Realizar la Baja de las relaciones Vendedor-Tienda
						PersonalFacade.eliminarTiendasVendedor(String.valueOf(vendedor.getPcCveVendedor()),
							Fecha.getHoraActual(fechaBaja), String.valueOf(cveUsuario), user);
						//Realiza la Eliminacion del mapeo entre Vendedor-Sistema
						PersonalFacade.eliminarSistemasVendedor(String.valueOf(vendedor.getPcCveVendedor()));
							
						} catch(Exception e) {
							 logger.error("Error en la transaccion de Baja de vendedor: " + e.toString());
							 conn.rollback();
						} finally {
							try {
								conn.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						
					} else if(control.equals("borrado")) {
						Pc_VendedoresVO checarMov = null;  
						Pc_Inter_Vendedores_PtoventasVO  checarRelacion =  null;
					
						if(subdivisionUsuario==0 || encontrado){
							checarMov = AdminCatFacade.checkMovVendedor(cveVendedor);
							checarRelacion =  AdminCatFacade.checkRelacion(cveVendedor);
						} else {
						   	/**
						   	 * Bloque de prueba.
						   	 */
							   checarMov = CatalogoFacade.checkMovVendedor(cveVendedor, String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
							   checarRelacion =  CatalogoFacade.checkRelacion(cveVendedor,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
						}
						   
						if(checarMov == null && checarRelacion== null){
							CatalogoFacade.deletePersonal(cveVendedor); 
							request.getSession().setAttribute("noData","Se elimino el vendedor con clave " + cveVendedor); 
						} else {
							CatalogoFacade.bajaPersonal(cveVendedor, fechaBaja);
							request.getSession().setAttribute("noData","No se puede borrar el vendedor con clave " + cveVendedor + " ya que tiene alguna relación presente en la base. Se marco el registro como BAJA. Para borrar el vendedor definitivamente, elimine las relaciones existentes e intente de nuevo."); 
						}
					}
				} // end if
			} // end if
			((PersonalMovForm ) form).reset(mapping,request);
		} catch(CatalogoSystemException cse) {
			logger.error("Error: " + cse.toString());
			return mapping.findForward("error");
		} catch(Exception e) {
			logger.error("Error: " + e.toString());
			return mapping.findForward("error");
		}
		((PersonalMovForm) form).setPcFchBaja("");
		form.reset(mapping,request);
		
		return mapping.findForward("home");
	}
	/*****************************************************************************************
	 * Personal Modificar
	 *  
	 *****************************************************************************************/
	public ActionForward modificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

						
			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
				if(request.getSession().getAttribute("detalleVendedore") != null) request.getSession().removeAttribute("detalleVendedore"); 
				if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
				if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
				if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
			
				String CveVendedor =(String)request.getSession().getAttribute("CveVendedor");
				String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				String listaDivs = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
				boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
				
				String ClaveSaeo= (String) request.getSession().getAttribute("claveManto");
				String CvePuestoDisplay = request.getParameter("pcCvePuesto");
				
				((PersonalMovForm) form).setPcDistMAster(""+request.getParameter("pcDistMAster"));
		
				if(CveVendedor == null || CveVendedor.equals("")){
					CveVendedor=  request.getParameter("pcCveVendedorVal");
					
				}
				
				if(CveVendedor != null && !(CveVendedor.equals(""))){
				
					try{

						String CveSuperior = (request.getParameter("pcCveSuperior").equals(""))? null:request.getParameter("pcCveSuperior");
						String strFechaAlta = (request.getParameter("pcFchAlta").equals(""))? null:request.getParameter("pcFchAlta");
						String CveSupCad = null;//(request.getParameter("pcCveSupCad").equals(""))? null:request.getParameter("pcCveSupCad");
						String strFechaCambio = (request.getParameter("pcFchCambio").equals(""))? null:request.getParameter("pcFchCambio");

						int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
						int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
						String cuenta = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
						String CveSubdiv = null;
						
						if(subdivisionUsuario==0 || encontrado){
							CveSubdiv = (request.getParameter("pcCveSubdiv").equals(""))? null:request.getParameter("pcCveSubdiv");
						}else{ 
						   if(listaDivs.length()>3){
							CveSubdiv = (request.getParameter("pcCveSubdiv").equals(""))? null:request.getParameter("pcCveSubdiv");
						}else{
								Pc_VendedoresVO temp = AdminCatFacade.getPersonal(CveVendedor);
								CveSubdiv = String.valueOf(temp.getPcCveSubdiv());
						  }
						}	
						
						
						if(CveSuperior==null) CveSuperior= String.valueOf(subdivisionUsuario);
						if(CveSubdiv==null) CveSubdiv= String.valueOf(subdivisionUsuario);
						if(strFechaAlta==null) strFechaAlta = Fecha.dateToString(new Date());
						if(strFechaCambio==null) strFechaCambio = Fecha.dateToString(new Date());
				
						//para comprobar si el vendedor cambio de puesto o jefe
						Pc_VendedoresVO vendedorTemp = null;
						String pcCvePuestoTemp = "";
						String pcCveSuperiorTemp =  "";
						if(subdivisionUsuario==0 || encontrado){
							if(CveVendedor.equals("0")){
								CveVendedor=ClaveSaeo;
							}
							vendedorTemp =  AdminCatFacade.getPersonal(CveVendedor);
						}else if(listaDivs!=null){
							if(CveVendedor.equals("0")){
								CveVendedor=ClaveSaeo;
							}
							vendedorTemp =  AdminCatFacade.getPersonal(CveVendedor);
						}else{
						  vendedorTemp =  CatalogoFacade.getPersonal(CveVendedor,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
						}
						if(vendedorTemp != null) {
							pcCvePuestoTemp = String.valueOf(vendedorTemp.getPcCvePuesto());
							pcCveSuperiorTemp = String.valueOf(vendedorTemp.getPcCveSuperior());	
						}
									
						//recupero datos de la pagina
						String pcPuesto = request.getParameter("pcCvePuesto");
						String pcHorario = request.getParameter("pcCveHorario");
						
						int movimiento = 0;
						int cambioHorario = 0;
						String pcHorarioAnt = null;
										
						if(vendedorTemp != null) {
							pcCvePuestoTemp = String.valueOf(vendedorTemp.getPcCvePuesto());
							pcCveSuperiorTemp = String.valueOf(vendedorTemp.getPcCveSuperior());
							pcHorarioAnt = String.valueOf(vendedorTemp.getPcCveHorario());
							
							if(pcHorario == null){
								pcHorario = "0";
							}
							
							//se realizo modificacion de horario
							if(pcHorarioAnt.equals(pcHorario)){
								cambioHorario = 0;
							
							} else if (!pcHorarioAnt.equals(pcHorario)){
								cambioHorario = 1;
							
							}	
						}
						
						//realizo la comparacion para saber que movimientos aplican
						if((pcCvePuestoTemp.trim().equals(pcPuesto.trim())) && (pcCveSuperiorTemp.trim().equals(CveSuperior.trim()))) // no se realizo cambio de puesto ni de jefe
							movimiento = 0;// no se realizo ningun movimiento
						else if((pcCvePuestoTemp.trim().equals(pcPuesto.trim())) && !(pcCveSuperiorTemp.trim().equals(CveSuperior.trim())))
							movimiento = 3; // se realizo cambio de superior
						else if(!(pcCvePuestoTemp.trim().equals(pcPuesto.trim())) && (pcCveSuperiorTemp.trim().equals(CveSuperior.trim())))
							movimiento = 4; //se realizo cambio de puesto
						else
							movimiento = 6;	//se realizo cambio de puesto y superior				
					
						//Busco el ultimo movimiento del usuario
						ClavesVO mov = new ClavesVO();
						ArrayList ClavesMov = null;
					
						if(movimiento != 0) {
							if(movimiento == 6) {	
								ClavesMov = CatalogoFacade.getMovVendedoresAmbasHis(CveVendedor);
							}
							else							
								mov = CatalogoFacade.getMovVendedoresHis(CveVendedor, String.valueOf(movimiento));
						}
						else {
							
							if(movimiento == 6) {
								ClavesVO tempmov = new ClavesVO();
								tempmov.setClave(0);
								ClavesMov.add(tempmov);
								
							}
							else
								mov.setClave(0);
						}
					
						if(movimiento == 6){
						
							if(ClavesMov ==null || ClavesMov.size()==0 ) {
								ClavesVO tempmov = new ClavesVO();
								tempmov.setClave(0);
								ClavesMov.add(tempmov);	
							}
						} else {
							if(mov==null) {
								mov = new ClavesVO();
								mov.setClave(0);
							
							}
						}						
						String pcCveHorario = request.getParameter("pcCveHorario");
						
						if(pcCveHorario == null){
							pcCveHorario = "0";
						}
							
						//termina de obtener datos para comprobar si el vendedor cambio de puesto o superior			  
						Object[] parametros = {
							request.getParameter("pcCveVendedor"), 
							request.getParameter("pcCvePuesto"),
							CveSuperior,
							request.getParameter("pcNomVendedor"),
							request.getParameter("pcApePaterno"),
							request.getParameter("pcApeMaterno"),
							request.getParameter("pcCntrTda"),
							request.getParameter("pcCveEmpRef"),
							request.getParameter("pcCiudad"),
							request.getParameter("pcEstado"),
							request.getParameter("pcDireccion"),
							request.getParameter("pcColonia"),
							request.getParameter("pcCp"),
							request.getParameter("pcTel"),
							request.getParameter("pcFax"),
							strFechaAlta,
							(CveSupCad==null)?"NULL":CveSupCad,
							CveSubdiv,
							request.getParameter("pcCveEmpresa"),
							pcCveHorario,
							request.getParameter("pcTipoComision"),
							request.getParameter("pcNivelVentas"),
							user,
							request.getParameter("pcDistMAster")
						};
						Object[] parametrosVendHist = {String.valueOf(mov.getClave()), 
								CveVendedor, Fecha.getHoraActual(strFechaCambio), String.valueOf(cveUsuario), String.valueOf(movimiento)};
						//for(long k=0; k < 5000000L; k++);
						GeneralDAO dao = new GeneralDAO();
						Connection conn = null;	
																		
						if(movimiento == 0) {
						
							if(checkCambios(CveVendedor,CveSuperior,CveSupCad,request.getParameter("pcCvePuesto"),String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario),strFechaCambio,String.valueOf(cveUsuario),String.valueOf(cveUsuario))>-1){
								CatalogoFacade.cambiarPersonal(parametros);//14	
								
								
								Object[] parametrosHistHorario = {CveVendedor,
										request.getParameter("pcCvePuesto"), 
										CveSuperior,
										Fecha.getHoraActual(strFechaCambio),
										"7",
										String.valueOf(cveUsuario),
										pcCveHorario
									};
								
								if(cambioHorario ==1){
									CatalogoFacade.cerrarMov(CveVendedor);
									CatalogoFacade.registrarCambioHorario(parametrosHistHorario);
								}			
								((PersonalMovForm) form).reset(mapping,request);
							}										
						} else { 
					
							//comente la parte que hizo el cambio
							if(checkCambios(CveVendedor,CveSuperior,CveSupCad,request.getParameter("pcCvePuesto"),String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario),strFechaCambio,String.valueOf(cveUsuario),String.valueOf(cveUsuario))>-1){
	
								try {
									conn = ProveedorConexion.getConnection();
									conn.setAutoCommit(false);
									int bandera = 0;
								
									Object[] parametrosHist = {CveVendedor,
										request.getParameter("pcCvePuesto"), 
										CveSuperior,
										Fecha.getHoraActual(strFechaCambio),
										String.valueOf(movimiento),
										String.valueOf(cveUsuario),
										pcCveHorario
									};
												
									if(movimiento > 0) {
										
										Object[] parametrosHistHorario = {CveVendedor,
												request.getParameter("pcCvePuesto"), 
												CveSuperior,
												Fecha.getHoraActual(strFechaCambio),
												"7",
												String.valueOf(cveUsuario),
												pcCveHorario
											};
								
										if(cambioHorario ==1){
											CatalogoFacade.cerrarMov(CveVendedor);
											CatalogoFacade.registrarCambioHorario(parametrosHistHorario);
										}	
									
										if(movimiento == 6) {
										
											for(int i =0; i < ClavesMov.size(); i++) {
											
												ClavesVO temp = (ClavesVO)ClavesMov.get(i);
											
											
												if(temp.getClave() == 0) {
												
													bandera = dao.executeTransaccion(315, parametrosHist, conn); //actualizo registro de vendedores_hist
												} else {
													Object[] parametrosVendHistTemp = {String.valueOf(temp.getClave()), 
													CveVendedor, Fecha.getHoraActual(strFechaCambio), String.valueOf(cveUsuario), String.valueOf(movimiento)};
												 
													bandera = dao.executeTransaccion(321, parametrosVendHistTemp, conn); //actualizo registro de vendedores_hist
													for(long k=0; k < 500000L; k++);
													if(bandera < 0)
														break;
												}
											}
										} else {
												if(mov.getClave() == 0) {
													bandera = dao.executeTransaccion(315, parametrosHist, conn); //actualizo registro de vendedores_hist
													for(long k=0; k < 500000L; k++);
	
											} else {
												bandera = dao.executeTransaccion(321, parametrosVendHist, conn); //actualizo registro de vendedores_hist
												for(long k=0; k < 500000L; k++);
											}
										}
									} else
										bandera = 2;
								
								
									if(bandera > 0) {
										bandera = dao.executeTransaccion(380, parametros, conn);//actualizo vendedores
									}
								
																
								
								
									if(movimiento!=6)
										for(long k=0; k < 500000L; k++);
								
									Object parametrosHistTemp[] = {CveVendedor,
											request.getParameter("pcCvePuesto"), 
											CveSuperior,
											Fecha.getHoraActual(strFechaCambio),
											String.valueOf(movimiento),
											String.valueOf(cveUsuario),
											pcCveHorario
										};
								
								
																					
									if(movimiento !=0) {					
										if(bandera > 0) {
											if(movimiento == 6) {
												ClavesVO temp = (ClavesVO)ClavesMov.get(0);
												if(temp.getClave() != 0){
													bandera = dao.executeTransaccion(315, parametrosHistTemp, conn);//inserto el nuevo registro en vendedores_hist
												}	
											} else { 
												if(mov.getClave() != 0){							
													bandera = dao.executeTransaccion(315, parametrosHistTemp, conn);//inserto el nuevo registro en vendedores_hist
												}	
											}
										}
									}
									
									if(bandera > 0) {
										logger.info("Hago commit en modificar");
										conn.commit();
									} else {
										logger.info("Hago rollback en modificar");
										conn.rollback();
									}
									
									//CatalogoFacade.changePersonalMov(parametros);//14
									((PersonalMovForm) form).reset(mapping,request);
								} catch (Exception e) {
									conn.rollback();
									logger.error("Error en la transaccion de modificar " + e.toString());
									e.printStackTrace();
								}finally {
									try {
										conn.close();
									} catch(Exception e) {
										logger.error("Error al cerrar la conexion en modificar " + e.toString());
										e.printStackTrace();
									}
								}
								
							}else{
								request.getSession().setAttribute("noData","Hubo un problema al realizar los cambios para la clave " + CveVendedor);
							}
						}
						((PersonalMovForm) form).setPcCvePuestoSeek(CvePuestoDisplay);
						Pc_VendedoresVO vendedor = null;
						if(subdivisionUsuario==0 || encontrado){
							vendedor =  AdminCatFacade.getPersonal(CveVendedor);
						}else if(listaDivs!=null){
							vendedor =  AdminCatFacade.getPersonal(CveVendedor);
						}else{
							vendedor =  CatalogoFacade.getPersonal(CveVendedor,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
						}
						ArrayList tablaVendedores  = new ArrayList();
						if(vendedor!= null )tablaVendedores.add(vendedor);  
						if(tablaVendedores!= null && !tablaVendedores.isEmpty()){
							request.getSession().setAttribute("tablaVendedores",tablaVendedores);
						}
					}catch(CatalogoSystemException cse){
						logger.error("modificar cse_error: " + cse.toString());
						cse.printStackTrace();
						return mapping.findForward("error");
					}catch(Exception e){
						e.printStackTrace();
						logger.error("modificar e_error: " + e.toString());
						return mapping.findForward("error");
					}
				}
				
				
				return mapping.findForward("home");
		}
	/****************************************************************************************
		 * 
		 * Personal Alta
		 * Método que verifica que el status del vendedor sea de baja, para poder realizar la 
		 * acción de alta.
		 * Cambiar el status : ALTA
		 * Cambiar fch_alta  : FECHA ACTUAL
		 *
		 ****************************************************************************************/
	
		public ActionForward alta(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
		
					
						if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
						if(request.getSession().getAttribute("detalleVendedore") != null) request.getSession().removeAttribute("detalleVendedore"); 
						if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
						if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
						if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
						
						
						String control= null;
			            String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
						boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
			 
						try{
							control=request.getParameter("control");
							String cveVendedor  = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
							String fechaAlta = (request.getParameter("pcFechaStatus").equals(""))? null:request.getParameter("pcFechaStatus");
							int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
							if(fechaAlta ==null){
							fechaAlta= Fecha.dateToString(new Date());
							}										   
                            Connection conn = null;
							GeneralDAO dao = new GeneralDAO();
							if(control==null || control.equals("alta")){
								ClavesVO cveBit = CatalogoFacade.getClave(64);
								
								try {

									conn = ProveedorConexion.getConnection();
									conn.setAutoCommit(false);
									int bandera = 0;
																					   						   												   						  
							   		//CatalogoFacade.altaPersonal(cveVendedor,fechaAlta);	//310
									bandera = dao.executeTransaccion(310, new Object[] {cveVendedor,fechaAlta, user}, conn);
									
							   		Object parametrosMovs[] = {String.valueOf(cveBit.getClave()),cveVendedor,"BAJA","ALTA",fechaAlta,String.valueOf(cveUsuario),String.valueOf(cveUsuario)};									   											  						 
							   		
							   										
									Object[] parametros = {cveVendedor, Fecha.getHoraActual(fechaAlta), String.valueOf(cveUsuario)};
																							   
							   		if(bandera > 0)
							   			bandera = dao.executeTransaccion(318, parametros, conn);
							   			
							   		if(bandera > 0)
							   			bandera = dao.executeTransaccion(311, parametrosMovs, conn);
							   			//CatalogoFacade.checkMovStatus(String.valueOf(cveBit.getClave()),cveVendedor,"BAJA","ALTA",fechaAlta,cveUsuario,cveUsuario);//311
							   			
							   		if (bandera > 0) {
							   			logger.info("hago commit en Alta");
							   			conn.commit();
										request.getSession().setAttribute("noData","Se modificó el status del vendedor con clave "+ cveVendedor + " a alta");
							   		} else {
										logger.info("hago rollback en Alta");
										conn.rollback();
										request.getSession().setAttribute("noData","ERROR AL MODIFICAR EL STATUS DEL USUARIO");
							   		}
								} catch(Exception e) {
									logger.error("Error en la transaccion " + e.toString());
									request.getSession().setAttribute("noData","ERROR AL MODIFICAR EL STATUS DEL USUARIO");
									conn.rollback();
									e.printStackTrace();
								} finally {
									try {
										conn.close();
									} catch (Exception sqle) {
										logger.error("Error en la transaccion " + sqle.toString());
										sqle.printStackTrace();
									}
								}
							   
							   											  						   						      							
							 }												
						   }catch(Exception e){
								logger.error("Error: " + e.toString());
							return mapping.findForward("error");
						   }			   		
			return mapping.findForward("home");
		}
	
	
	
	
	/*****************************************************************************************
	 * Personal Consultar
	 *  
	 *****************************************************************************************/
	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			StringBuffer pcNomVendedor = new StringBuffer();
			String vendedor = null;
			 
			try{
				String cveVendedor = request.getParameter("pcCveVendedorSeek");
				 if(cveVendedor==null){
				      cveVendedor = "";      
				 }   
				String numeroEmpleado = request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
				 if(numeroEmpleado==null){
				      numeroEmpleado = "";      
				 }
				String puestoEmpleado = request.getParameter("pcCvePuestoSeek");
				 if(puestoEmpleado==null){
				      puestoEmpleado = "";      
				 }  
				String nombre = request.getParameter("pcNomVendedorSeek");
				 if(nombre==null){
				      nombre = "";      
				 }
				 logger.info(("Valor recuperado en cambio jefe"+request.getParameter("pcDistMAster")));
				
				((PersonalMovForm) form).setPcDistMAster(""+request.getParameter("pcDistMAster"));
				
				control=request.getParameter("control");
				
				logger.info("Metodo Consultar: " + control);
				
				if(control == null){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					if(request.getSession().getAttribute("detalleVendedor") != null) request.getSession().removeAttribute("detalleVendedor");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
					if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
					if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
					return mapping.findForward("home");
				}else if(control.equals("superior")){
					//Inicializo las variables para poder evaluarlas en la parte de filtros. 
														
					if(request.getSession().getAttribute("tablaVendedores")!=null && ((PersonalMovForm) form).getPcCveVendedor() != 0){
					   Pc_VendedoresVO vend = new Pc_VendedoresVO();
					   vend = AdminCatFacade.getVendedor(String.valueOf(((PersonalMovForm) form).getPcCveVendedor()));            
					   ((PersonalMovForm) form).setPcFchBaja(Fecha.dateToString(vend.getPcFchBaja()));
					   ((PersonalMovForm) form).setPcDigVerif(String.valueOf(vend.getPcDigVerif()));
					}
					
					String pcCvePuesto = request.getParameter("pcCvePuesto");
				 
				 if(!pcCvePuesto.equals("")){
				      Pc_PuestosVO puesto = new Pc_PuestosVO();
					   puesto = CatalogoFacade.validarHorarioPuesto(pcCvePuesto);
					
					if(puesto.getPcHorario().equals("N")){
						request.getSession().removeAttribute("horario");
					} else {
						request.getSession().setAttribute("horario","true");
					}
				 }
					if(request.getAttribute("tablaVendedores") != null) 
						request.setAttribute("tablaVendedores", request.getAttribute("tablaVendedores"));
							
					request.setAttribute("pcCveVendedorSeek",cveVendedor);
					request.setAttribute("pcNomClaveEmpleadoReferenciaSeek",numeroEmpleado);
					request.setAttribute("pcCvePuestoSeek",puestoEmpleado);
					request.setAttribute("pcNomVendedorSeek",nombre);	
						
					return mapping.findForward("home");
				}else if(control.equals("superiorSel")){
					String superiorSel = (request.getParameter("pcCveSuperiorSel").equals(""))? null:request.getParameter("pcCveSuperiorSel");
					((PersonalMovForm) form).setPcCveSuperior(Integer.parseInt(superiorSel));
					
					request.setAttribute("pcCveVendedorSeek",cveVendedor);
					request.setAttribute("pcNomClaveEmpleadoReferenciaSeek",numeroEmpleado);
					request.setAttribute("pcCvePuestoSeek",puestoEmpleado);
					request.setAttribute("pcNomVendedorSeek",nombre);
					
					return mapping.findForward("home");
				}else if(control.equals("buscar")){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					if(request.getSession().getAttribute("detalleVendedor") != null) request.getSession().removeAttribute("detalleVendedor");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
					if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
					if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
					
					String puestoBuscar = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");
					String vendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
					String nombreVendedorBuscar = (request.getParameter("pcNomVendedorSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
					String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
					
				//	String claveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
					int queryId = 0;

					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
					String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
					boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
					if(vendedorBuscar == null && nomClaveEmpleadoReferenciaBuscar== null){
					
						((PersonalMovForm) form).reset(mapping,request);

						if(puestoBuscar!= null && !puestoBuscar.equals(""))
							((PersonalMovForm) form).setPcCvePuesto(Integer.parseInt(puestoBuscar));
						else
							((PersonalMovForm) form).setPcCvePuesto(1);
						((PersonalMovForm) form).setPcCveSuperior(0);
						((PersonalMovForm) form).setPcCveVendedor(0);
						((PersonalMovForm) form).setPcCveEmpRef("");
						((PersonalMovForm) form).setPcFchAlta("");
						((PersonalMovForm) form).setPcFchBaja("");
						int tokens = 0;
						
						ArrayList tablaVendedores = new ArrayList();
						ArrayList listaBuscar = new ArrayList();
						
						if(nombreVendedorBuscar!= null && !nombreVendedorBuscar.equals("")){
							
							pcNomVendedor.append("%");
							StringTokenizer strTk = new StringTokenizer(nombreVendedorBuscar);
							tokens = strTk.countTokens();
							
							
								while(strTk.hasMoreTokens()){
									String tokenStr =(String)strTk.nextElement();
									pcNomVendedor.append(tokenStr);
									pcNomVendedor.append("%");
									
									listaBuscar.add(tokenStr);
									vendedor = new String(pcNomVendedor);
								}
							
						}else{
							listaBuscar.add(null);
							
						}

						if(subdivisionUsuario==0 || encontrado){
							
							
							ArrayList stringCompleto = AdminCatFacade.getPersonalTableCompleto(new Object[] {puestoBuscar,nombreVendedorBuscar});
							if(stringCompleto!= null && !stringCompleto.isEmpty()){
								tablaVendedores.addAll(stringCompleto);
							}else{
								if(listaBuscar.isEmpty() || listaBuscar.size()==0){
									request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");		
								}else{
								for(int i=0; i<1;i++){
									ArrayList resultParcial = AdminCatFacade.getPersonalTable(new Object[] {puestoBuscar,(String) listaBuscar.get(i)});
									
									if(resultParcial != null) tablaVendedores.addAll(resultParcial);  
								}
							   }
							}
						}else{
							ArrayList vendedores =  PersonalFacade.obtenerVendedores(puestoBuscar,vendedor,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
							tablaVendedores.addAll(vendedores);
						}
						
						if(tablaVendedores!= null && !tablaVendedores.isEmpty()){
							Object[] vendedores =  tablaVendedores.toArray(); 
							Arrays.sort(vendedores);
							tablaVendedores.clear();
							tablaVendedores.addAll(Arrays.asList(vendedores));
							request.getSession().setAttribute("tablaVendedores",tablaVendedores);
						}else{
							request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
						}
					}else if(vendedorBuscar != null && nomClaveEmpleadoReferenciaBuscar == null){
						
						Pc_VendedoresVO detalleVendedor = null;
							if(subdivisionUsuario==0 || encontrado){
								detalleVendedor = AdminCatFacade.getPersonal(vendedorBuscar); 
							}else{
								detalleVendedor = CatalogoFacade.getPersonal(vendedorBuscar, String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
							}
						
						if(detalleVendedor != null){
							((PersonalMovForm) form).setPcNomVendedor(detalleVendedor.getPcNomVendedor());
							((PersonalMovForm) form).setPcApePaterno(detalleVendedor.getPcApePaterno());
							((PersonalMovForm) form).setPcApeMaterno(detalleVendedor.getPcApeMaterno());
							((PersonalMovForm) form).setPcCiudad(detalleVendedor.getPcCiudad());
							((PersonalMovForm) form).setPcEstado(detalleVendedor.getPcEstado());
							((PersonalMovForm) form).setPcDireccion(detalleVendedor.getPcDireccion());
							((PersonalMovForm) form).setPcColonia(detalleVendedor.getPcColonia());
							((PersonalMovForm) form).setPcCp(detalleVendedor.getPcCp());
							((PersonalMovForm) form).setPcTel(detalleVendedor.getPcTel());
							((PersonalMovForm) form).setPcFax(detalleVendedor.getPcFax());
							((PersonalMovForm) form).setPcCntrTda(detalleVendedor.getPcCntrTda());
							((PersonalMovForm) form).setPcStatus(detalleVendedor.getPcStatus());
							((PersonalMovForm) form).setPcDigVerif(String.valueOf(detalleVendedor.getPcDigVerif()));
							((PersonalMovForm) form).setPcCvePuesto(detalleVendedor.getPcCvePuesto());
							((PersonalMovForm) form).setPcCveSuperior(detalleVendedor.getPcCveSuperior());
							((PersonalMovForm) form).setPcCveVendedor(detalleVendedor.getPcCveVendedor());
							((PersonalMovForm) form).setPcCveEmpRef(String.valueOf(detalleVendedor.getPcCveEmpRef()));
							((PersonalMovForm) form).setPcFchAlta(Fecha.dateToString(detalleVendedor.getPcFchAlta()));
							((PersonalMovForm) form).setPcFchBaja(Fecha.dateToString(detalleVendedor.getPcFchBaja()));
							((PersonalMovForm) form).setPcCveVendedorChg(detalleVendedor.getPcCveVendedor());
							//((PersonalMovForm) form).setPcDescHorario(detalleVendedor.getPcDescHorario());
								
							ArrayList tablaVendedores = new ArrayList();
							tablaVendedores.add(detalleVendedor);
							request.getSession().setAttribute("tablaVendedores",tablaVendedores);	
							request.getSession().setAttribute("detalleVendedor",detalleVendedor);	
												
						}else{
							request.getSession().setAttribute("noData","No Existen Vendedores con Clave " + vendedorBuscar);
								((PersonalMovForm) form).reset(mapping,request);
						}																
					}
					else if(nomClaveEmpleadoReferenciaBuscar != null && vendedorBuscar == null){
						ArrayList detalleVendedor = null;
						if(subdivisionUsuario==0 || encontrado){
							detalleVendedor = AdminCatFacade.getVendedorByClaves(nomClaveEmpleadoReferenciaBuscar);
						}else {
							detalleVendedor = CatalogoFacade.getVendedorByClaves(nomClaveEmpleadoReferenciaBuscar,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
						}
					if(detalleVendedor != null && detalleVendedor.size()!=0)
					{						
						ArrayList tablaVendedores = new ArrayList();
						tablaVendedores.addAll(detalleVendedor);
						 request.getSession().setAttribute("tablaVendedores",tablaVendedores);	
						 request.getSession().setAttribute("detalleVendedor",detalleVendedor);		
					  }
					  else{
					  request.getSession().setAttribute("noData","No Existen Vendedores con Clave " + vendedorBuscar);
						  ((PersonalMovForm) form).reset(mapping,request);
					  }	
				   }	
				}
			}catch(CatalogoSystemException cse){
				logger.error("error consultar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error: " + e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}

	public static int CAMBIO_PUESTO = 2;
	public static int CAMBIO_SUPERIOR = 1;
	public static int CAMBIO_SUP_CAD = 4;
	public static int CAMBIOS = 3;
	public static int SIN_CAMBIOS = 0;
	public static int ERROR = (-1);
	/*****************************************************************************************
	 * Personal CheckCambios
	 *  
	 *****************************************************************************************/
	public int checkCambios(String CveVendedor, String CveSuperior, String CveSupCad, String CvePuesto, String subdivisionUsuario, String divisionUsuario, String fechaMov, String usuario, String autoriza) throws Exception{
			int superior_nuevo=0;
			int supCad_nuevo=0;
			int puesto_nuevo=0;
			//int usuario = 100001;
			//int autoriza = 100001;
			int cambios_presentes=0;
			int cambios_cadena=0;
			
			try{
				if(fechaMov==null) fechaMov= Fecha.dateToString(new Date());
				Pc_VendedoresVO check = null;
				if(divisionUsuario.equals("0")){
					check = AdminCatFacade.checkVendedor(CveVendedor); 
				}else{
					check = CatalogoFacade.checkVendedor(CveVendedor, subdivisionUsuario,divisionUsuario);
				}
				
				if(check!= null){
					int superior_actual = check.getPcCveSuperior();
					int supCad_actual = check.getPcCveSupCad();
					int puesto_actual = check.getPcCvePuesto();
					if(CveSuperior!=null && !CveSuperior.equals("")){
						if(superior_actual != Integer.parseInt(CveSuperior)){
							superior_nuevo = Integer.parseInt(CveSuperior);
							cambios_presentes = cambios_presentes  + CAMBIO_SUPERIOR; 
						}else {
							superior_actual = 0;
							superior_nuevo=0;
						}
					}else{
						superior_actual = 0;
						superior_nuevo=0;
					}	
					if(CveSupCad!=null && !CveSupCad.equals("")){
						if(supCad_actual != Integer.parseInt(CveSupCad)){
							supCad_nuevo = Integer.parseInt(CveSupCad);
							cambios_cadena = CAMBIO_SUP_CAD; 
						}else {
							supCad_actual = 0;
							supCad_nuevo=0;
						}
					}else{
						supCad_actual = 0;
						supCad_nuevo=0;
					}	
					if(CvePuesto!=null && !CvePuesto.equals("")){
						if(puesto_actual != Integer.parseInt(CvePuesto)){
							puesto_nuevo =Integer.parseInt(CvePuesto);
							cambios_presentes = cambios_presentes  + CAMBIO_PUESTO; 
						}else {
							puesto_actual=0;
							puesto_nuevo=0;
						}
					}else {
						puesto_actual=0;
						puesto_nuevo=0;
					}
					if(cambios_presentes>0){
						ArrayList param = new ArrayList();
						ClavesVO clave = CatalogoFacade.getMovimientoClave();				
						param.add(String.valueOf(clave.getClave()));
						param.add(String.valueOf(CveVendedor));
						param.add(String.valueOf(superior_actual));
						param.add(String.valueOf(superior_nuevo));
						param.add(String.valueOf(puesto_actual));
						param.add(String.valueOf(puesto_nuevo));
						param.add(usuario);
						param.add(autoriza);
						param.add(fechaMov);
						CatalogoFacade.setMovVendedorData(param.toArray());	
					}
					if(cambios_cadena == CAMBIO_SUP_CAD){
						ArrayList param = new ArrayList();
						ClavesVO clave = CatalogoFacade.getMovimientoClave();				
						param.add(String.valueOf(clave.getClave()));
						param.add(String.valueOf(CveVendedor));
						param.add(String.valueOf(supCad_actual));
						param.add(String.valueOf(supCad_nuevo));
						param.add(String.valueOf(0));
						param.add(String.valueOf(0));
						param.add(usuario);
						param.add(autoriza);
						param.add(fechaMov);
						CatalogoFacade.setMovVendedorData(param.toArray());	
					}
				}
			}catch(CatalogoSystemException cse){
				logger.error("modificar:checkCambios cse_error: " + cse.toString());
				return ERROR;
			}catch(Exception e){
				logger.error("modificar:checkCambios e_error: " + e.toString());
				return ERROR;
			}
		return cambios_presentes;		
	}
	/*****************************************************************************************
	 * Personal Desasignar Personal
	 *  
	 *****************************************************************************************/
	public int desasignarPersonal(String cveVendedor, String subdivision, String division, String fechaMov, String usuario, String autoriza) throws Exception{
		int cambios_presentes=0;
		int cambios_ciclo = 0;
		try{
			Pc_VendedoresVO miVendedor = null;
			if(division.equals("0")){
				miVendedor = AdminCatFacade.getPersonal(cveVendedor); 
			}else{
				miVendedor = CatalogoFacade.getPersonal(cveVendedor, subdivision, division);
			}
			
			ArrayList subordinados = CatalogoFacade.getPersonalComprobarSuperior(cveVendedor);
			if(!subordinados.isEmpty()){
				Iterator subordinadosItera = subordinados.iterator();
				while(subordinadosItera.hasNext()){
					Pc_VendedoresVO gente = (Pc_VendedoresVO) subordinadosItera.next();
					String vendedorCheck = String.valueOf(gente.getPcCveVendedor());
					String superiorCheck= String.valueOf(miVendedor.getPcCveSuperior());
					String supCadCheck = String.valueOf(gente.getPcCveSupCad());
					if(checkCambios( vendedorCheck , superiorCheck, supCadCheck, "", subdivision ,division, fechaMov, usuario, autoriza)==-1)
						cambios_ciclo=ERROR;
				}	
				if(cambios_ciclo==ERROR){
					cambios_presentes=ERROR;
				}else{
					CatalogoFacade.unassignPersonal(cveVendedor, autoriza);
				}
			}
			cambios_ciclo = 0;
			ArrayList subordinados_cadena = CatalogoFacade.getPersonalComprobarSupCad(cveVendedor);
			if(!subordinados_cadena.isEmpty()){
				Iterator subordinadosItera = subordinados_cadena.iterator();
				while(subordinadosItera.hasNext()){
					Pc_VendedoresVO gente = (Pc_VendedoresVO) subordinadosItera.next();
					String vendedorCheck = String.valueOf(gente.getPcCveVendedor());
					String superiorCheck= String.valueOf(gente.getPcCveSuperior());
					String supCadCheck = String.valueOf(miVendedor.getPcCveSupCad());
					if(checkCambios( vendedorCheck , superiorCheck, supCadCheck, "", subdivision, division, fechaMov, usuario, autoriza)==-1)
						cambios_ciclo=ERROR;
				}	
				if(cambios_ciclo==ERROR){
					cambios_presentes=ERROR;
				}else{
					CatalogoFacade.unassignSuperiorCadena(cveVendedor, autoriza);
				}
			}
			if(cambios_presentes!= ERROR && checkCambios( cveVendedor, subdivision, division, "", "", division, fechaMov, usuario, autoriza)>-1){
				CatalogoFacade.noSuperiorPersonal(cveVendedor, division, autoriza);
				cambios_presentes=CAMBIOS;
			}else{
				cambios_presentes=ERROR;
			}
		}catch(CatalogoSystemException cse){
			logger.error("desasignar cse_error: " + cse.toString());
			return ERROR;
		}catch(Exception e){
			logger.error("desasignar e_error: " + e.toString());
			return ERROR;
		}
		return cambios_presentes;
	}

	/**
	 * verificarPersonal
	 * 
	 * @param cveVendedor
	 * @param division
	 * @return existe personal asignado
	 * @throws Exception
	 */
	public boolean verificarPersonal(String cveVendedor, String division) throws Exception{
		boolean existenSubordinados=false;
		try{
			ArrayList subordinados = CatalogoFacade.getPersonalComprobarSuperiorAlta(cveVendedor);
			if(subordinados.isEmpty()){
				existenSubordinados=false;
			}else{
				existenSubordinados=true;
			}
		}catch(Exception e){
			logger.error("Error al verificarPersonal " + e.toString());
			throw e;
		}
		return existenSubordinados;
	}
	
	/*****************************************************************************************
		 * Personal Cambiar de Division
		 *  
		 *****************************************************************************************/
		public ActionForward cambiarDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

				if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
				if(request.getSession().getAttribute("detalleVendedore") != null) request.getSession().removeAttribute("detalleVendedore"); 
				if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
				if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
				if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
			
				String control= null;
			 
				try{
					control=request.getParameter("control");
					String cveVendedor  = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
					String cveDivision  = (request.getParameter("pcCveDivCambia").equals(""))? null:request.getParameter("pcCveDivCambia");
					String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
					int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
					String puesto = (request.getParameter("pcCvePuesto").equals(""))? null:request.getParameter("pcCvePuesto");
					
					logger.info("vendedor dentro de cambiarDiv " + cveVendedor + " cambiar a division " + cveDivision + " y puesto: " + puesto);
					String fechaAlta =  Fecha.dateToString(new Date());
					
					ClavesVO mov = new ClavesVO();
					mov = CatalogoFacade.getMovVendedoresHis(cveVendedor, String.valueOf("3"));
					if(mov == null) {
						mov = new ClavesVO();
						mov.setClave(0);
					}	
					
					String pcCveHorario = request.getParameter("pcCveHorario");
						
					if(pcCveHorario == null){
						pcCveHorario = "0";
					}
					
					Object[] parametrosVendHist = {cveVendedor,
									request.getParameter("pcCvePuesto"), 
									cveDivision,
									Fecha.getHoraActual(Fecha.dateToString(new Date())),
									String.valueOf("3"),
									String.valueOf(cveUsuario),
									pcCveHorario
								};
							
					Connection conn = null;
				    GeneralDAO dao = new GeneralDAO();
				    try {
					   conn = ProveedorConexion.getConnection();
					   conn.setAutoCommit(false);
					   int bandera = 0;
					   
					   if(mov.getClave() == 0) {
							bandera = dao.executeTransaccion(315, parametrosVendHist, conn); //inserto registro la primera vez
							logger.info("valor de bandera: " + bandera);
					   } else {
							Object[] parametrosVendHistTemp = {String.valueOf(mov.getClave()), 
									cveVendedor, Fecha.getHoraActual(Fecha.dateToString(new Date())), String.valueOf(cveUsuario), String.valueOf("3")}; //actualizo vendedor
							 						
							bandera = dao.executeTransaccion(321, parametrosVendHistTemp, conn); //actualizo registro de vendedores_hist
							
							logger.info("valor de bandera: " + bandera);
							
							if(bandera > 0)
								bandera = dao.executeTransaccion(315, parametrosVendHist, conn); //inserto registro la primera vez
							
							logger.info("valor de bandera: " + bandera);	
					   }
					   
					   if(bandera > 0)
					   		bandera = dao.executeTransaccion(Q.VENDEDOR_CAMBIA_DIVISION, new Object[] {cveVendedor,cveDivision, user}, conn);
					   		
						logger.info("valor de bandera: " + bandera);
					   		
					   if(bandera > 0) {
					   	logger.info("cambio de division exitosa, hago commit");
					   	conn.commit();
						request.setAttribute("noData","Se cambio de division al vendedor con clave "+ cveVendedor + ".");
					   } else {
						logger.info("cambio de division fallida, hago rollback");
					   	conn.rollback();
						request.setAttribute("noData","Problemas al realizar el cambio de division al vendedor con clave "+ cveVendedor + ".");
					   }
					 } catch(Exception e) {
						logger.error("Error en la transaccion " + e.toString());
						//request.getSession().setAttribute("noData","ERROR AL CAMBIAR AL USUARIO DE DIVISION.");
						conn.rollback();
						request.setAttribute("noData","Problemas al realizar el cambio de division al vendedor con clave "+ cveVendedor + ".");
						
					} finally {
						try {
							conn.close();
						} catch (Exception sqle) {
							logger.error("Error en la transaccion " + sqle.toString());
							//sqle.printStackTrace();
						}
					}
					((PersonalMovForm ) form).reset(mapping,request);
					
				}catch(CatalogoSystemException cse){
					logger.error("Error: " + cse.toString());
					return mapping.findForward("error");
				}catch(Exception e){
					logger.error("Error: " + e.toString());
					return mapping.findForward("error");
				}
		
				return mapping.findForward("home");
		}
	
	/**
	 * Para reactivar vendedor.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */	
	public ActionForward reactivar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
	
			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
			if(request.getSession().getAttribute("detalleVendedore") != null) request.getSession().removeAttribute("detalleVendedore"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("porConfirmar") != null) request.getSession().removeAttribute("porConfirmar");
			if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");		
			
			String pcCveVendedor = null;
			Pc_VendedoresVO vendedorHis = null;
			Pc_VendedoresVO vendedor = null;
			String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
			
			pcCveVendedor = request.getParameter("pcCveVendedor");
			
			//OBTENER EL ESTATUS DEL VENDEDOR
			vendedor = PersonalFacade.obtenerEstatusVendedor(pcCveVendedor);
			
			if(vendedor.getPcStatus().equals("ALTA")){
				request.getSession().setAttribute("noData","No se puede reactivar el vendedor " +pcCveVendedor + " porque su Estatus es ALTA");
				return mapping.findForward("home");
			} else if(vendedor.getPcStatus().equals("BAJA")){
				// Cambiar el estatus del vendedor de BAJA a ALTA
				PersonalFacade.cambiarEstatusBajaPorAltaVendedor(pcCveVendedor);
				// Cerrar el periodo de BAJA en historico del vendedor
				PersonalFacade.cerrarPeriodoBajaVendedor(pcCveVendedor);
				// Insertar en historico de vendedores el ALTA
				PersonalFacade.agregarAltaEnHistorialVendedores(pcCveVendedor, user.substring(user.length()-6));
				
				((PersonalMovForm) form).setPcFchReact("");
				request.getSession().setAttribute("noData", "Se reactivó el vendedor con clave " + pcCveVendedor);
			}
			
			return mapping.findForward("home");
	}	
		
		/**
		 * Habilita o deshabilita el combo de horario
		 * dependiendo si el puesto lo requiere o no.
		 * 
		 * @param mapping
		 * @param form
		 * @param request
		 * @param response
		 * @return
		 */
		public ActionForward horario(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			String CveVendedor = request.getParameter("pcCveVendedor");
		
			request.getSession().setAttribute("CveVendedor",CveVendedor);

			String pcCvePuesto = request.getParameter("pcCvePuesto");
			String SupBase = request.getParameter("claveSuperior");
			
			Pc_SuperioresVO superiorClave =AdminCatFacade.obtieneClaveSup(SupBase);
		
			int cveSupMostrar=  superiorClave.getPcCveSuperior();
			request.setAttribute("cveSupMostrar", String.valueOf(cveSupMostrar));		
						
			Pc_PuestosVO puesto = new Pc_PuestosVO();
			
			puesto = CatalogoFacade.validarHorarioPuesto(pcCvePuesto);
			
			if(puesto.getPcHorario().equals("N")){
				request.getSession().setAttribute("horario", "false");
			} else {
				request.getSession().removeAttribute("horario");
			}
			
			
						return mapping.findForward("home");

		}

    /**
     * Sirve para recuperar los nombres de los puestos
     * que están registrados como superiores
     * para el vendedor en cuestión.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward superior(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	
	int claveManto= Integer.parseInt(request.getParameter("pcCveVendedor"));
	request.getSession().setAttribute("claveManto", String.valueOf(claveManto));
	return mapping.findForward("buscarSup");
	
	}
	
	public ActionForward mostrarSuperior(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	
			String nombreSuperior = (request.getParameter("pcNomSuperiorSeek").equals(""))? null:request.getParameter("pcNomSuperiorSeek");
			int claveVendedor= Integer.parseInt((String) request.getSession().getAttribute("claveManto"));
			String cvePuesto = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");	
			String claveSuperior = (request.getParameter("pcCveSuperiorSeek").equals(""))? null:request.getParameter("pcCveSuperiorSeek");
			String claveRefSuperior = (request.getParameter("pcNomClaveSuperiorReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveSuperiorReferenciaSeek");

			ArrayList tablaSuperiores = new ArrayList();
			ArrayList nombreSuperiores = AdminCatFacade.obtieneSuperiorTableCompleto(new Object[] {nombreSuperior, String.valueOf(claveVendedor)});
			String mensaje="Su busqueda no genero resultados";
				
			if(nombreSuperiores!= null && !nombreSuperiores.isEmpty()){
						tablaSuperiores.addAll(nombreSuperiores);
						request.getSession().setAttribute("tablaSuperiores",tablaSuperiores);
						}	
						else{
							request.setAttribute("mensaje", mensaje);
							ArrayList puestoSuperiores = AdminCatFacade.obtieneSuperiorClavePuesto(new Object[] {cvePuesto, String.valueOf(claveVendedor)});
							if(request.getSession().getAttribute("tablaSuperiores") != null) request.getSession().removeAttribute("tablaSuperiores");
							if(puestoSuperiores!=null && !puestoSuperiores.isEmpty()){
								request.removeAttribute("mensaje");
								tablaSuperiores.addAll(puestoSuperiores);
								request.getSession().setAttribute("tablaSuperiores",tablaSuperiores);						
							}
							else{
								request.setAttribute("mensaje", mensaje);
								ArrayList claveSuperiores = AdminCatFacade.obtieneSuperiorClave(new Object[] {claveSuperior, String.valueOf(claveVendedor)});
								if(request.getSession().getAttribute("tablaSuperiores") != null) request.getSession().removeAttribute("tablaSuperiores");
								if(claveSuperiores!=null && !claveSuperiores.isEmpty()){
									request.removeAttribute("mensaje");
									tablaSuperiores.addAll(claveSuperiores);
									request.getSession().setAttribute("tablaSuperiores",tablaSuperiores);	
									}else{
									   	request.setAttribute("mensaje", mensaje);
										if(request.getSession().getAttribute("tablaSuperiores") != null) request.getSession().removeAttribute("tablaSuperiores");
								  			ArrayList claveRefSuperiores = AdminCatFacade.obtieneSuperiorClaveReferencia(new Object[] {claveRefSuperior, String.valueOf(claveVendedor)});
											if(claveRefSuperiores!=null && !claveRefSuperiores.isEmpty()){
											  request.removeAttribute("mensaje");
											  tablaSuperiores.addAll(claveRefSuperiores);
												 request.getSession().setAttribute("tablaSuperiores",tablaSuperiores);					
											}
								
										}
															
									}
						    	}	
			return mapping.findForward("buscarSup");
		}
		
		/**
		 * Modificación de la manera de buscar a vendedores.
		 * CAL
		 * @param request
		 * @return
		 */
		public HashMap buscar(HttpServletRequest request) {
			HashMap success = new HashMap();
			ArrayList listaVendedores = null;
			String control = request.getParameter("control");
			int userRol = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveRol();
			int userSubDiv = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			String  user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
			String multiDivs = "";
            multiDivs = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
            
			boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
            //Inicializo las variables para poder evaluarlas en la parte de filtros. 
			String cveVendedor = "";
			String numeroEmpleado = "";
			String puestoEmpleado = "";
			String nombre = "";
			
			cveVendedor = request.getParameter("pcCveVendedorSeek");
			numeroEmpleado = request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
			puestoEmpleado = request.getParameter("pcCvePuestoSeek");
			nombre = request.getParameter("pcNomVendedorSeek");
			
			if(control.equals("nombre")){
				String sql="";	
				sql = armaQueryBusquedaNombre(control, request, nombre, 
						userRol, encontrado, userSubDiv, multiDivs);
				try {
					listaVendedores = CatalogoFacade.getBuscaVendedores(sql);	                                              	                                              
				}catch(Exception e) {
					logger.error("Error en la transaccion " + e.toString());
					request.setAttribute("error",e.toString());
					e.printStackTrace();
			  	}
			}else if(control.equals("cveVendedor")){
				String sql = armaQueryBusquedaClave(control, request, cveVendedor, 
			    	userRol, encontrado, userSubDiv, multiDivs);
				try{
					listaVendedores = CatalogoFacade.getBuscaVendedores(sql);                                              
				}catch(Exception e){
					logger.error("Error en la transaccion " + e.toString());
					request.setAttribute("error",e.toString());
					e.printStackTrace();
				}
			}else if(control.equals("noEmpleado")){
				String sql = armaQueryBusquedaNoEmpleado(control, request, 
					numeroEmpleado,userRol, encontrado, userSubDiv, multiDivs);
				try{
					listaVendedores = CatalogoFacade.getBuscaVendedores(sql);                                                     
				}catch(Exception e){
					logger.error("Error en la transaccion " + e.toString());
					request.setAttribute("error",e.toString());
					e.printStackTrace();
				}
			}else if(control.equals("puesto")){
				String sql = armaQueryBusquedaPuesto(control, request, 
					puestoEmpleado, userRol, encontrado, userSubDiv, multiDivs);
			 	try{
					listaVendedores = CatalogoFacade.getBuscaVendedores(sql); 
			 	}catch(Exception e){
					logger.error("Error en la transaccion " + e.toString());
					request.setAttribute("error",e.toString());
					e.printStackTrace();
			 	}
			}else if(control.equals("filtros")){
				String sql = armaQueryBusquedaFiltros(control, request, cveVendedor, 
					numeroEmpleado, puestoEmpleado, nombre, userRol, encontrado, userSubDiv, multiDivs);
				try{
					listaVendedores = CatalogoFacade.getBuscaVendedores(sql);
				}catch(Exception e){
					logger.error("Error en la transaccion " + e.toString());
					request.setAttribute("error",e.toString());
					e.printStackTrace();
				}	                                                            
			}
			success.put("lista",listaVendedores);
			success.put("action","home");
			
			request.setAttribute("pcCveVendedorSeek",cveVendedor);
			request.setAttribute("pcNomClaveEmpleadoReferenciaSeek",numeroEmpleado);
			request.setAttribute("pcCvePuestoSeek",puestoEmpleado);
			request.setAttribute("pcNomVendedorSeek",nombre);
			
			return success;  
		}
		
		/**
		 * Subrutina para mostrar el detalle de los datos de un vendedor.
		 * Utilizando Ajax.
		 * 
		 * @param request
		 * @param response
		 */
		public void buscaAjax(HttpServletRequest request, HttpServletResponse response) {
					String vendedor = request.getParameter("cveVendedor");
			try{
				Pc_VendedoresVO detalleVendedor = CatalogoFacade.getDetallePersonal(vendedor);
				if(!String.valueOf(detalleVendedor.getPcCvePuesto()).equals("")){
					Pc_PuestosVO puesto = new Pc_PuestosVO();
					puesto = CatalogoFacade.validarHorarioPuesto(String.valueOf(detalleVendedor.getPcCvePuesto()));
					if(puesto.getPcHorario().equals("N")){
						request.getSession().setAttribute("horario", "true");
					} else {
						request.getSession().setAttribute("horario",null);
					}
				}
				Document doc = detalleVendedor.toXml();
				response.setContentType("application/xml");
				XMLOutputter out = new XMLOutputter();
				out.setEncoding("ISO-8859-1");
				out.output(doc, response.getWriter());
			}catch(Exception e){
				logger.error("Ourrio un error de Ajax: " +e.toString());				
			}
		}
		
		public void ajaxSuperior(HttpServletRequest request, HttpServletResponse response) {
			try{
				String res = request.getParameter("cvePuesto") == null ? "1" : request.getParameter("cvePuesto");
				String subdiv = request.getParameter("subDiv");
				String division = request.getParameter("division");
				String superior = request.getParameter("superior");
				if(superior.equals("")){
					superior = null;
				}
								
				ArrayList listaSuperiores = CatalogoFacade.getSuperiores(res, subdiv, division, superior);
				Iterator itSups = listaSuperiores.iterator();
	
				//Creamos raiz
				Element root = new Element("superiores");
				Element hijo = null;
				//Creamos nodos
				Element eCve = null;
				Element eNom = null;
		
				while(itSups.hasNext()){
					Pc_SuperioresVO tmp = (Pc_SuperioresVO)itSups.next();
					eCve = new Element("clave").setText(tmp.getPcCveVendedor()+"");
					eNom = new Element("nombre").setText(tmp.getPcNombreVendedor());
					hijo = new Element("superior");
					hijo.addContent(eCve);
					hijo.addContent(eNom);
					root.addContent(hijo);
				}
				//Creamos el documento
				Document doc = new Document(root);
				response.setContentType("application/xml");
				new XMLOutputter().output(doc, response.getWriter());
			}catch(Exception e){
				logger.error("ocurriò un error de Ajax: " +e.toString());				
			}
		}
		
		public static String armaQueryBusquedaNombre(String control, HttpServletRequest request, 
		                                 String nombre, int userRol, boolean encontrado, int userSubDiv, String divs){
			 String sql = ""; 				
				nombre = nombre.trim().replace(' ','%');				
				sql = " AND (((trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)||' '||trim(V.PC_NOM_VENDEDOR)) LIKE '%"+nombre+"%')"+
 	  	  			  "  OR ((trim(V.PC_NOM_VENDEDOR)||' '||trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)) LIKE '%"+nombre+"%'))";
 	  	  		if(userRol == 1 || userRol == 4){
					sql += "AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
 	  	  		}else if(userRol !=1 && encontrado){
					sql += "AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
 	  	  		}else if(!divs.equals("")){ 
					sql += "AND TO_CHAR(V.PC_CVE_SUBDIV) in ("+divs+")";
 	  	  		}else
					sql += "AND TO_CHAR(V.PC_CVE_SUBDIV) like '"+userSubDiv+"'";
											
			return sql;						
		}
		
		public static String armaQueryBusquedaDivision(String control, HttpServletRequest request, 
												 String nombre, int userRol, boolean encontrado, int userSubDiv, String divs, String subDivBuscar){
					 String sql = ""; 				
						if(userRol == 1 || userRol == 4){
							sql += "AND TO_CHAR(S.PC_CVE_SUBDIV) = '"+subDivBuscar+"'";
						}else if(userRol !=1 && encontrado){
							sql += "AND TO_CHAR(S.PC_CVE_SUBDIV) = '"+subDivBuscar+"'";
						}else if(!divs.equals("")){
							/**
							 * Esta parte del método puede ser modificada dependiendo
							 * las necesidades de las formas.
							 */ 
							sql += "AND TO_CHAR(S.PC_CVE_SUBDIV) = '"+subDivBuscar+"'";
						}else
							sql += "AND TO_CHAR(V.PC_CVE_SUBDIV) like '"+userSubDiv+"'";
											
		   return sql;						
		 }
		
		public static String armaQueryBusquedaClave(String control, HttpServletRequest request, String cveVendedor, 
		                                                int userRol, boolean encontrado, int userSubDiv, String divs){
			     String sql="";	 
					   if(userRol == 1 || userRol == 4){
							sql = "AND V.PC_CVE_VENDEDOR="+cveVendedor+" AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
					   }else if(userRol !=1 && encontrado){
						    sql = "AND V.PC_CVE_VENDEDOR="+cveVendedor+" AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
					   }else if(!divs.equals("")){ 
					        sql = "AND V.PC_CVE_VENDEDOR="+cveVendedor+" AND TO_CHAR(V.PC_CVE_SUBDIV) in ("+divs+")";
				       }else 
						    sql = "AND V.PC_CVE_VENDEDOR="+cveVendedor+" AND TO_CHAR(V.PC_CVE_SUBDIV) like '"+userSubDiv+"'";
						 	  	  
				  return sql;	 
		       }  
		          
		public static String armaQueryBusquedaNoEmpleado(String control, HttpServletRequest request, String numeroEmpleado, 
		                                                     int userRol, boolean encontrado, int userSubDiv, String divs){
	          String sql="";     			       
					   if(userRol == 1 || userRol == 4){
							sql = "AND V.PC_CVE_EMP_REF='"+numeroEmpleado+"' AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
					   }else if(userRol !=1 && encontrado){
					    	sql = "AND V.PC_CVE_EMP_REF='"+numeroEmpleado+"' AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
					   }else if(!divs.equals("")){ 
					        sql = "AND V.PC_CVE_EMP_REF='"+numeroEmpleado+"' AND TO_CHAR(V.PC_CVE_SUBDIV) in ("+divs+")";
				       }else 
						    sql = "AND V.PC_CVE_EMP_REF='"+numeroEmpleado+"' AND TO_CHAR(V.PC_CVE_SUBDIV) like '"+userSubDiv+"'";
							    
					return sql;	
		 		}		
		
	    public static String armaQueryBusquedaPuesto(String control, HttpServletRequest request, String puestoEmpleado, 
	                                                  int userRol, boolean encontrado, int userSubDiv, String divs){
				String sql="";
			           if(userRol == 1 || userRol == 4){
								  sql="AND V.PC_CVE_PUESTO="+puestoEmpleado+" AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
			           }else if(userRol !=1 && encontrado){
								  sql="AND V.PC_CVE_PUESTO="+puestoEmpleado+" AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
			           }else if(!divs.equals("")){ 
					              sql="AND V.PC_CVE_PUESTO="+puestoEmpleado+" AND TO_CHAR(V.PC_CVE_SUBDIV) in ("+divs+")";
				       }else
						sql = "AND V.PC_CVE_PUESTO="+puestoEmpleado+" AND TO_CHAR(V.PC_CVE_SUBDIV) like '"+userSubDiv+"'";
						
					return sql;					
				} 
		
		/**
		 * Este método puede ser modificado para que pueda buscar por los cuatro criterios de busqueda de
		 * un vendedor. Sin embargo, por el momento no es necesario hacer una busqueda tan específica, incluso
		 * se catalogaría de contraproducente puesto que no hay homologación ni control en los registros que
		 * existen actualmente en el SAEO. 
		 * 
		 * @param control
		 * @param request
		 * @param cveVendedor
		 * @param numeroEmpleado
		 * @param puestoEmpleado
		 * @param nombre
		 * @param userRol
		 * @param encontrado
		 * @param userSubDiv
		 * @return sql
		 */		
		public static String armaQueryBusquedaFiltros(String control, HttpServletRequest request, String cveVendedor,  
															         String numeroEmpleado,String puestoEmpleado, 
															                 String nombre, int userRol, boolean encontrado, int userSubDiv, String divs){		
				
				String sql = "";
					   if(userRol == 1){
					   sql="AND V.PC_CVE_PUESTO="+puestoEmpleado+" AND ((((trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)||' '||trim(V.PC_NOM_VENDEDOR)) LIKE '%"+nombre+"%')"+
 	  	  			   " OR ((trim(V.PC_NOM_VENDEDOR)||' '||trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)) LIKE '%"+nombre+"%'))) AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
					   }else if(userRol !=1 && encontrado){
						sql="AND V.PC_CVE_PUESTO="+puestoEmpleado+" AND (((trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)||' '||trim(V.PC_NOM_VENDEDOR)) LIKE '%"+nombre+"%')"+
						" OR ((trim(V.PC_NOM_VENDEDOR)||' '||trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)) LIKE '%"+nombre+"%')) AND TO_CHAR(V.PC_CVE_SUBDIV) like '%'";
					   }else if(!divs.equals("")){ 
					   sql="AND V.PC_CVE_PUESTO="+puestoEmpleado+" AND (((trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)||' '||trim(V.PC_NOM_VENDEDOR)) LIKE '%"+nombre+"%')"+
					  " OR ((trim(V.PC_NOM_VENDEDOR)||' '||trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)) LIKE '%"+nombre+"%')) AND TO_CHAR(V.PC_CVE_SUBDIV) in ("+divs+")";
			           }else 
					   sql="AND V.PC_CVE_PUESTO="+puestoEmpleado+" AND (((trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)||' '||trim(V.PC_NOM_VENDEDOR)) LIKE '%"+nombre+"%')"+
					  " OR ((trim(V.PC_NOM_VENDEDOR)||' '||trim(V.PC_APE_PATERNO)||' '||trim(V.PC_APE_MATERNO)) LIKE '%"+nombre+"%')) AND TO_CHAR(V.PC_CVE_SUBDIV) like '"+userSubDiv+"'";
			return sql;		
		}			
		
		public static boolean validaUsuario(String usuario){
	     String campo = "";
		  try{		
	          campo = CatalogoFacade.nivelVentasConfig2();
		       }catch(Exception e){
		         e.printStackTrace();
		      }
        String []temporal = campo.split(",");     
		    for(int i=0; i<temporal.length;i++){
			    if(usuario.toLowerCase().trim().equals(temporal[i].toLowerCase().trim())){
			       return true;
			    }
		    }
		return false;    	
	   }
	      
}