/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.vendedores.struts.action;

import java.util.ArrayList;
import java.util.Date;


import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.utilerias.Fecha;
import mx.com.iusacell.catalogo.utilerias.DigitoVerificador;

import mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO;
import mx.com.iusacell.catalogo.modelo.Pc_PuestosVO;

import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO;
import mx.com.iusacell.catalogo.modelo.ClavesVO;

import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import java.sql.Connection;
import java.sql.SQLException;
import mx.com.iusacell.catalogo.bd.ProveedorConexion;
import mx.com.iusacell.catalogo.utilerias.GeneralDAO;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;


import mx.com.iusacell.catalogo.web.utilerias.Commons;


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
public class VendedoresProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {
	
	protected static final Logger logger = Logger.getLogger(VendedoresProxy.class);

	/**
	 * Puestos Proxy
	 *  
	 */
	public VendedoresProxy() {
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
			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();

			((VendedoresForm)form).setPage(0);
			((VendedoresForm)form).setPcCveDiv(divisionUsuario);
			((VendedoresForm)form).setPcCveSubdiv(subdivisionUsuario );
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

			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
			if(request.getSession().getAttribute("detalleVendedores") != null) request.getSession().removeAttribute("detalleVendedores");
			if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
			if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta");
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("addedData") != null) request.getSession().removeAttribute("addedData");
			
			try{
				logger.debug("VendedoresProxy|agregar|try");
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				int Usuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
				String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				
				String Status = (request.getParameter("pcStatus").equals(""))? "ALTA":request.getParameter("pcStatus");
				String CveSuperior = (request.getParameter("pcCveSuperior").equals(""))? null:request.getParameter("pcCveSuperior");
				String CveSupCad = null;//(request.getParameter("pcCveSupCad").equals(""))? "NULL":request.getParameter("pcCveSupCad");
				String fechaAlta = (request.getParameter("pcFchAlta").equals(""))? Fecha.dateToString(new Date()):request.getParameter("pcFchAlta");
				String tipoRegistro = (request.getParameter("pcTipoRegistro").equals(""))? "persona":request.getParameter("pcTipoRegistro");
				String CveVendedorDefine = (request.getParameter("pcCveVendedorDefine").equals(""))?"0":request.getParameter("pcCveVendedorDefine");
				
				if(Status == null){
					Status = "ALTA";
				}
				if(CveSuperior == null){
					CveSuperior = String.valueOf(subdivisionUsuario);
				}
				
				logger.debug("Status:" + Status + ":Superior:" + CveSuperior);

				String cveVendedor= (tipoRegistro.equals("persona") || tipoRegistro.equals("tienda"))?
					String.valueOf(Commons.obtenerClaveVendedor(tipoRegistro,CveVendedorDefine)): 
					request.getParameter("pcCveVendedor"); 
				String digito = "0";
				
				if(tipoRegistro.equals("persona") || tipoRegistro.equals("tienda") || tipoRegistro.equals("distribuidor")){ 
					DigitoVerificador digVerif = new DigitoVerificador();
					digVerif.setDigitoVerificador(Long.parseLong(cveVendedor));
					digito = String.valueOf(digVerif.getDigitoVerificador());
				}else if(tipoRegistro.equals("interno")){
					if(cveVendedor.length()==6){
						if((cveVendedor.indexOf("70")==0) || (cveVendedor.indexOf("80")==0) || (cveVendedor.indexOf("91")==0)){
							DigitoVerificador digVerif = new DigitoVerificador();
							digVerif.setDigitoVerificador(Long.parseLong(cveVendedor));
							digito = String.valueOf(digVerif.getDigitoVerificador());
						}
					}
				}

				String pcEstado = (request.getParameter("pcEstado")==null)?"":request.getParameter("pcEstado");
				String pcDireccion = (request.getParameter("pcDireccion")==null)?"":request.getParameter("pcDireccion");
				String pcColonia = (request.getParameter("pcColonia")==null)?"":request.getParameter("pcColonia");
				String pcCp = (request.getParameter("pcCp")==null)?"00000":request.getParameter("pcCp");
				String pcTel = (request.getParameter("pcTel")==null)?"555-555-5555":request.getParameter("pcTel");
				String pcFax = (request.getParameter("pcFax")==null)?"555-555-5555":request.getParameter("pcFax");
			
				String pcCntrTda = (request.getParameter("pcCntrTda")==null)?"0":request.getParameter("pcCntrTda");
				String pcCveEmpRef = (request.getParameter("pcCveEmpRef")==null)?"0":request.getParameter("pcCveEmpRef");
				
				String CvePtoVentas = (request.getParameter("pcCvePtoventas")==null)?"0":request.getParameter("pcCvePtoventas");
				String CveEmpresa = (request.getParameter("pcCveEmpresa")==null)?"0":request.getParameter("pcCveEmpresa");
				
				Pc_Punto_VentasVO punto_ventas = null;
				if(divisionUsuario==0){
					punto_ventas = AdminCatFacade.getPuntoVenta(CvePtoVentas);
				}else{
					punto_ventas = CatalogoFacade.getPuntoVenta(CvePtoVentas,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
				}
				
				/* La Clave de Subdivision para nuestro nuevo Vendedor es:
				 *		Si subdivisionUsuario es 0 --> es Administrador intentar
				 *			Si el punto_ventas que estamos asociando existe (se supone que siempre existe) intenta
				 *				si tiene bien la subdivision colocar esta
				 *				si no entonces intentar
				 *					Obtener pcCveSubdiv (desde la primera pagina) si no es cero es eso si no regresar "0"
				 *			Si No existe
				 *					Obtener pcCveSubdiv (desde la primera pagina) si no es cero es eso si no regresar "0"
				 *		Si es diferente de 0 colocar subdivisionUsuario
				 *					
				 */
				String pcCveSubdiv = null; 
				
				if(subdivisionUsuario==0){
					if(punto_ventas!=null){
						if(punto_ventas.getPcCveSubdiv()!=0){
							pcCveSubdiv = String.valueOf(punto_ventas.getPcCveSubdiv());
						}else {
							if(request.getParameter("pcCveSubdiv")==null)pcCveSubdiv = "0";
							else pcCveSubdiv = request.getParameter("pcCveSubdiv");
						}
					}else{
						if(request.getParameter("pcCveSubdiv")==null)pcCveSubdiv = "0";
						else pcCveSubdiv = request.getParameter("pcCveSubdiv");
					}    							
				}else 
					pcCveSubdiv = String.valueOf(subdivisionUsuario);
					
				//pcCveSubdiv = pcCveSubdiv.equals("0")?String.valueOf(subdivisionUsuario):pcCveSubdiv;
				
				String pcCveHorario = request.getParameter("pcCveHorario");
						
				if(pcCveHorario == null){
					pcCveHorario = "0";
				}
			
				Object[] parametros = {
						cveVendedor, 
						request.getParameter("pcCvePuesto"), 
						CveSuperior,
						request.getParameter("pcNomVendedor"),
						request.getParameter("pcApePaterno"),
						request.getParameter("pcApeMaterno"),
						fechaAlta,
						digito,
						pcCntrTda,
						Status,
						pcCveEmpRef,
						request.getParameter("pcCiudad"),
						pcEstado,
						pcDireccion,
						pcColonia,
						pcCp,
						pcTel,
						pcFax,
						CveSupCad,
						pcCveSubdiv,
						CveEmpresa,
					    pcCveHorario
					};
				
				Object[] parametrosHis = {
					cveVendedor,
					request.getParameter("pcCvePuesto"), 
					CveSuperior,
					Fecha.getHoraActual(fechaAlta),
					"1",
					String.valueOf(Usuario),
					pcCveHorario
				};
										
				String fechaMovimiento = (request.getParameter("pcFchAlta").equals(""))? null:request.getParameter("pcFchAlta");
						
				if(fechaMovimiento==null){
					fechaMovimiento = Fecha.dateToString(Fecha.getHoy());
					((VendedoresForm)form).setPcFchAlta(fechaMovimiento);
				}
				ClavesVO clave = new ClavesVO();
				clave = CatalogoFacade.getRelacionClave();
										
				Connection conn = null;
				try {
					GeneralDAO dao = new GeneralDAO();
					conn = ProveedorConexion.getConnection();
					conn.setAutoCommit(false);
					int bandera = 0;//dao.executeTransaccion(10, parametros, conn);
						///inserciones a la bd		
					bandera = dao.executeTransaccion(10, parametros, conn);
					
						//CatalogoFacade.setVendedor(parametros);//insercion original
						//try{
							//CatalogoFacade.setVendedorSubdiv(cveVendedor,pcCveSubdiv);//insercion original
							//if(bandera > 0)
								//bandera = dao.executeTransaccion(170, new Object[]{cveVendedor,pcCveSubdiv}, conn);
						/*}catch(CatalogoFlowException cfe){
							logger.error(cfe.toString() + " " + cveVendedor + ":" + CveSuperior );					
						}*/
		
						int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
						
						
						Object[] paramRelacion = {
								String.valueOf(clave.getClave()),
								cveVendedor, 
								request.getParameter("pcCvePtoventas"),
								fechaMovimiento,
								String.valueOf(cveUsuario),   // el atributo usuario es numerico
								String.valueOf(cveUsuario),	// el atributo autorizacion es numerico
								Fecha.getHoraActual(fechaMovimiento),
								user
						};
						
						if (bandera > 0)//inserto en la tabla de historicos
							bandera =  dao.executeTransaccion(315, parametrosHis, conn);
							
						if (bandera > 0)	
							bandera = dao.executeTransaccion(41, paramRelacion, conn);
						
						if(bandera > 0) {
							logger.info("hago commit " + bandera);
							conn.commit();
							request.getSession().setAttribute("addedData","Ha sido agregado un nuevo Vendedor con clave : " + cveVendedor + "-" + digito);
						}
						else {
							logger.info("hago rollback " + bandera);
							conn.rollback();
							
						}
						//termina insercion
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
			
				
			}catch(CatalogoSystemException cse){
				logger.error("agregar cse_error: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("agregar e_error: " + e.toString());
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
				
				if(control == null){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					if(request.getSession().getAttribute("detalleVendedores") != null) request.getSession().removeAttribute("detalleVendedores");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
					if(request.getSession().getAttribute("addedData") != null) request.getSession().removeAttribute("addedData");
					return mapping.findForward("home");
				}else if(control.equals("tipo_canal_seek")){
					return buscarTipoCanal(mapping,form,request,response);
				}else if(control.equals("puntos_venta")){
					return buscarPuntoVentas(mapping,form,request,response);
				}else if(control.equals("superior")){
					
					return buscarSuperior(mapping,form,request,response);
				}else if(control.equals("siguiente")){
					
					
					if(((VendedoresForm) form).getPage()==1){
					
					  String pcPuesto = request.getParameter("pcCvePuesto");
					  String pcDivision = request.getParameter("pcCveDiv");
					  String pcTienda = request.getParameter("pcCvePtoventas");
					  					  
					  Pc_VendedoresVO VendedoresTemp = AdminCatFacade.getJefeTienda(pcTienda);
					  if(VendedoresTemp !=null) {
						
						
						((VendedoresForm) form).setPcCveSuperior(VendedoresTemp.getPcCveVendedor());
						((VendedoresForm) form).setPcNombreSuperior(VendedoresTemp.getPcNombreVendedor());
						request.setAttribute("EXISTE_JEFE_TIENDA","EXISTE_JEFE_TIENDA");
					  }
					  
					  
					} else if(((VendedoresForm) form).getPage()==2){
						String tipoRegistro = request.getParameter("pcTipoRegistro");
						int cveVendedor = 0;	
						if(tipoRegistro.equals("distribuidor") || tipoRegistro.equals("interno")){
							try{
								cveVendedor = Integer.parseInt(request.getParameter("pcCveVendedorDefine"));
							}catch(NumberFormatException nfe){
								cveVendedor = 0;
							}
						}
						if(tipoRegistro.equals("interno")){
							((VendedoresForm) form).setPcCveEmpRef(String.valueOf(cveVendedor));
						}
						((VendedoresForm) form).setPcCveVendedor(cveVendedor);
						
						
						((VendedoresForm) form).setPcDescPuesto( ((Pc_PuestosVO)CatalogoFacade.getPuesto( request.getParameter("pcCvePuesto") )).getPcDescPuesto()) ;
						String CveSuperior = (request.getParameter("pcCveSuperior").equals(""))? null:request.getParameter("pcCveSuperior");						
						String CveSupCad = null;//(request.getParameter("pcCveSupCad").equals(""))? null:request.getParameter("pcCveSupCad");						
						String pcCveHorario = request.getParameter("pcCveHorario");
						
						PcHorarioDiaVO horario = null;
						horario = CatalogoFacade.obtenerHorario(pcCveHorario);
						
						int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
						
						
						if(subdivisionUsuario==0){
							if(CveSuperior == null){
								((VendedoresForm) form).setPcNombreSuperior("");
							}else{
								((VendedoresForm) form).setPcNombreSuperior(((Pc_VendedoresVO)AdminCatFacade.getVendedor(CveSuperior)).getPcNombreCompleto()); 
							}
							if(CveSupCad == null){
								((VendedoresForm) form).setPcNombreSupCad("");
							}else{
								((VendedoresForm) form).setPcNombreSupCad(((Pc_VendedoresVO)AdminCatFacade.getVendedor(CveSupCad)).getPcNombreCompleto()); 
							}
							if(horario == null){
								((VendedoresForm) form).setPcCveHorario("0");
							}else{
								((VendedoresForm) form).setPcCveHorario(pcCveHorario); 
								((VendedoresForm) form).setPcDescHorario(horario.getPcDescHorario()); 
							}
							//((VendedoresForm) form).setPcDescHorario(horario.getPcDescHorario()); 
						
						}else{
							if(CveSuperior == null){
								((VendedoresForm) form).setPcNombreSuperior("");
							}else{
								((VendedoresForm) form).setPcNombreSuperior(((Pc_VendedoresVO)CatalogoFacade.getVendedorDiv(CveSuperior,String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario))).getPcNombreCompleto()); 
							}
							if(CveSupCad == null){
								((VendedoresForm) form).setPcNombreSupCad("");
							}else{
								((VendedoresForm) form).setPcNombreSupCad(((Pc_VendedoresVO)CatalogoFacade.getVendedorDiv(CveSupCad,String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario))).getPcNombreCompleto()); 
							}
							if(horario == null){
								((VendedoresForm) form).setPcCveHorario("");
							}else{
								((VendedoresForm) form).setPcCveHorario(pcCveHorario); 
								((VendedoresForm) form).setPcDescHorario(horario.getPcDescHorario()); 
							}
						}

					}else if(((VendedoresForm) form).getPage()==3){
				  		String nombreVend = request.getParameter("pcNomVendedor").equals("")? null:request.getParameter("pcNomVendedor");
		  				String apePaterno = request.getParameter("pcApePaterno").equals("")? null:request.getParameter("pcApePaterno");
		  				String apeMaterno = request.getParameter("pcApeMaterno").equals("")? null:request.getParameter("pcApeMaterno");
		  				String nombreVendedorBuscar =nombreVend + " " + apePaterno + " " + apeMaterno; 
		  				 
						int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
						
						ArrayList tablaVendedores = Commons.buscarVendedores(null,null,nombreVendedorBuscar,subdivisionUsuario, divisionUsuario);
						
						if(tablaVendedores!= null && !tablaVendedores.isEmpty()){
							request.getSession().setAttribute("tablaVendedores",tablaVendedores);
						}
					}
					((VendedoresForm) form).setPage(Integer.parseInt(request.getParameter("page"))+1);
					return mapping.findForward("home");
				}else if(control.equals("regresar")){
					((VendedoresForm) form).setPage(Integer.parseInt(request.getParameter("page"))-1);
					((VendedoresForm) form).reset(mapping,request);
					
					/*String pcCveHorario = request.getParameter("pcCveHorario");
			
					ArrayList horario = null;
					horario = CatalogoFacade.obtenerHorarioDetalle(pcCveHorario);
					request.getSession().setAttribute("horario", horario)
					*******************
					Pc_PuestosVO puesto = new Pc_PuestosVO();
					puesto = CatalogoFacade.validarHorarioPuesto(pcCvePuesto);
			
					if(puesto.getPcHorario().equals("S")){
						request.setAttribute("horario", "true");
					} else {
						request.setAttribute("horario", "false");
					}
					
					*************************/
					return mapping.findForward("home");
				}else if(control.equals("buscar")){
					return mapping.findForward("home");
				}
			}catch(Exception e){
				logger.error("consultar e_error: " + e.toString());
				e.printStackTrace();
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
	/***********************************************************************************
	 * buscarPuntoVentas
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public ActionForward buscarPuntoVentas(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			
			if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
			if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta");
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("addedData") != null) request.getSession().removeAttribute("addedData");

			String divisionBuscar = (request.getParameter("pcCveDivSeek").equals(""))? null:request.getParameter("pcCveDivSeek");
			String tipoCanalBuscar = (request.getParameter("pcCveTipoCanalSeek").equals(""))? null:request.getParameter("pcCveTipoCanalSeek");
			String canalBuscar = (request.getParameter("pcCveCanalSeek").equals(""))? null:request.getParameter("pcCveCanalSeek");
			String vendedorBuscar = (request.getParameter("pcCvePtoventasSeek").equals(""))? null:request.getParameter("pcCvePtoventasSeek");
			String nombreVendedorBuscar = (request.getParameter("pcNomPtoventasSeek").equals(""))? null:request.getParameter("pcNomPtoventasSeek");
			
			try{				
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				
				if(divisionBuscar == null) {
					if(subdivisionUsuario!=0) divisionBuscar = String.valueOf(subdivisionUsuario);
				} 
				ArrayList tablaPuntosVenta  = Commons.buscarPuntoVentas(divisionBuscar, 
				                                                        tipoCanalBuscar, 
				                                                        canalBuscar, 
				                                                        vendedorBuscar, 
				                                                        nombreVendedorBuscar,
                                                                        subdivisionUsuario,divisionUsuario); 

				((VendedoresForm) form).reset(mapping,request);
				if(vendedorBuscar == null){
					try{
						((VendedoresForm) form).setPcCveDivSeek(Integer.parseInt(divisionBuscar));	
					}catch(NumberFormatException nfe){
						((VendedoresForm) form).setPcCveDivSeek(0);
					}
					((VendedoresForm) form).setPcCveTipoCanalSeek(tipoCanalBuscar);
					((VendedoresForm) form).setPcCveCanalSeek(canalBuscar);
					((VendedoresForm) form).setPcNomPtoventasSeek(nombreVendedorBuscar);
				}else{
					((VendedoresForm) form).setPcCvePtoventasSeek(vendedorBuscar);
				}
				if(!tablaPuntosVenta.isEmpty()){
					request.getSession().setAttribute("tablaPuntosVenta",tablaPuntosVenta);
				}else{
					request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
				}
			}catch(CatalogoSystemException cse){
				logger.error("buscarPuntoVentas cse_error: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("buscarPuntoVentas e_error: " + e.toString());
				return mapping.findForward("error");
			}

			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();

			((VendedoresForm) form).setPage(1);	
			((VendedoresForm)form).setPcCveDiv(divisionUsuario);
			((VendedoresForm)form).setPcCveSubdiv(subdivisionUsuario );

			return mapping.findForward("home");
	}
	/***********************************************************************************
	 * buscarTipoCanal
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public ActionForward buscarTipoCanal(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			((VendedoresForm)form).setPage(0);
			return mapping.findForward("home");
	}
	/***********************************************************************************
	 * buscarSuperior
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public ActionForward buscarSuperior(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			((VendedoresForm)form).setPage(2);
			String pcCvePuesto = request.getParameter("pcCvePuesto");
			
			Pc_PuestosVO puesto = new Pc_PuestosVO();
			
			puesto = CatalogoFacade.validarHorarioPuesto(pcCvePuesto);
			
			if(puesto.getPcHorario().equals("S")){
				request.getSession().setAttribute("horario", "true");
			} else {
				request.getSession().setAttribute("horario", "false");
			}
						
			return mapping.findForward("home");
	}
	
	public ActionForward horario(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String pcCveHorario = request.getParameter("pcCveHorario");
			
			ArrayList horario = null;
			horario = CatalogoFacade.obtenerHorarioDetalle(pcCveHorario);
			request.setAttribute("horario", horario);
			return mapping.findForward("horario");

	}	
	
}



