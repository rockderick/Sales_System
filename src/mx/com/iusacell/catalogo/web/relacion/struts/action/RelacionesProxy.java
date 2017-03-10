/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.relacion.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import java.util.Collections;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.utilerias.Fecha;

import mx.com.iusacell.catalogo.web.personal.struts.action.PersonalMovProxy;
import mx.com.iusacell.catalogo.modelo.Pc_PuestosVO;

import mx.com.iusacell.catalogo.modelo.Pc_Inter_Vendedores_PtoventasVO;
import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO;
import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import org.apache.log4j.Logger;

/**
 * @author Dvazqueza
 * 
 * @version 1.0
 * 
 */
public class RelacionesProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(RelacionesProxy.class);
	
	/**
	 * Relaciones Proxy
	 *  
	 */
	public RelacionesProxy() {
	}

	/********************************************************************************************
	 * Relaciones VerLista
	 *  
	 ********************************************************************************************/
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
			ArrayList tablaPuestos = new ArrayList();
		
			tablaPuestos = CatalogoFacade.getTablaPuestos();
			
			request.getSession().setAttribute("tablaPuestos",tablaPuestos);	
			return mapping.findForward("home");
	}
	/********************************************************************************************
	 * Relaciones Consultar
	 *  
	 ********************************************************************************************/
	public ActionForward consultar(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
			
			Pc_Inter_Vendedores_PtoventasVO detalleRelacion = new Pc_Inter_Vendedores_PtoventasVO();
			ArrayList listaRelaciones = new ArrayList();
			
			String clave = request.getParameter("pcCveInter");		
			String puntoVentas = request.getParameter("pcCvePtoventas");		
			String vendedor = request.getParameter("pcCveVendedor");		

			String control= null;
			 
			try{
				control=request.getParameter("control");
		   		if(control == null){
			   		return mapping.findForward("home");
		   		}else if(control.equals("tipo_canal")){
		   			return mapping.findForward("home");
		   		}else if(control.equals("tipo_canal_seek")){
		   			return mapping.findForward("home");
		   		}else if(control.equals("puntosVenta")){
					return buscaPuntosVenta(mapping, form, request, response);
				}else if(control.equals("vendedores")){
					return buscaVendedores(mapping, form, request, response);
				}
			}catch(Exception e){
				logger.error("Error en execute: " + e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
	
	/********************************************************************************************
	 * Relaciones Agregar
	 * 
	 * Este metodo esta encargado de agregar relaciones de vendedores con puntos de venta.
	 * 
	 * **************************************
	 * @version 1.1 Modificación para relacionar un vendedor a mas de un punto de venta.
	 * 				IWR 7241
	 * @author lfcrespo@mx1.ibm.com
	 * @since 29/04/2013 
	 * ************************************** 
	 ********************************************************************************************/
	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

		logger.info("**** Ejecutando RelacionesProxy.agregar() ****");

		String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();

		try {
			if(request.getSession().getAttribute("tablaRelaciones") != null) request.getSession().removeAttribute("tablaRelaciones"); 
			if(request.getSession().getAttribute("noDataRelaciones") != null) request.getSession().removeAttribute("noDataRelaciones");
			if(request.getSession().getAttribute("noDataVendedores") != null) request.getSession().removeAttribute("noDataVendedores");
			
			ArrayList tablaRelaciones = new ArrayList(); 					
			
			int cvePtoventas_size=0;
			int cveVendedor_size=0;

			String[] cvePtoventas = request.getParameterValues("pcCvePtoventas");
			String[] cveVendedor = request.getParameterValues("pcCveVendedor");
			String[] cvePuesto = request.getParameterValues("pcCvePuesto");
			
			if(cvePtoventas != null){
				cvePtoventas_size = cvePtoventas.length;
			}
			
			if(cveVendedor != null){
				cveVendedor_size = cveVendedor.length;
			}
			
			String fechaMovimiento = Fecha.dateToString(new Date());
			String fechaInicio = fechaMovimiento;
			Pc_Inter_Vendedores_PtoventasVO relacion = null;
			
			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
			int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
			String divs = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
						
			if(cvePtoventas_size > 0 && cveVendedor_size > 0){
				for(int i=0;i<cvePtoventas.length;i++){
					for(int j=0;j<cveVendedor.length;j++){
						boolean bandera = false;
						
						/* Valida el puesto del vendedor.
						 * 
						 * Realiza consulta para obtener la clave de puesto, si es alta en vendedores,
						 * si esta relacionado a un punto de venta y si el punto de venta es alta.
						 * En teoria si el vendedor esta relacionado a un punto de venta. lfcrespo
						 */
						Pc_PuestosVO puestoVendedor = CatalogoFacade.getPuestosNoRepite(cveVendedor[j]);
						
						/* Revisa que no exista relacion entre punto de venta y vendedor.
						 * 
						 * Se comenta la validación. Si el puestoVendedor es diferente de nulo quiere decir
						 * que esta asociado a un punto de venta, se obtiene la relacion del vendedor con
						 * el punto de venta que se intenta relacionar. lfcrespo
						 */
						//if(puestoVendedor == null)
						relacion = CatalogoFacade.getRelacionParametro(cvePtoventas[i],cveVendedor[j]);
						
						//Pc_Inter_Vendedores_PtoventasVO relacion = null;
						
						
						// Se agrega la hora a la fecha
						Calendar calendar = Calendar.getInstance();
						
						fechaInicio = fechaInicio + " ";
						if(calendar.get(Calendar.HOUR_OF_DAY) < 10)
							fechaInicio = fechaInicio + "0" + calendar.get(Calendar.HOUR_OF_DAY);
						else				
							fechaInicio = fechaInicio + calendar.get(Calendar.HOUR_OF_DAY);
 
						 if(calendar.get(Calendar.MINUTE) < 10)
							fechaInicio = fechaInicio + ":0" + calendar.get(Calendar.MINUTE);
						 else
							fechaInicio = fechaInicio + ":" + calendar.get(Calendar.MINUTE);
	
						if(calendar.get(Calendar.SECOND) < 10)	
							fechaInicio = fechaInicio + ":0" + calendar.get(Calendar.SECOND);
						else
							fechaInicio = fechaInicio + ":" + calendar.get(Calendar.SECOND);
							
						/* Se comenta la validación. Si el puestoVendedor es nulo significaba que no tenia 
						 * relación con un punto de venta, por lo tanto se le podia asignar un punto de venta,
						 * esto cambia ya que ahora aunque tenga relación con un punto de venta se le puede
						 * asignar mas puntos de venta. lfcrespo
						 */
						//if(puestoVendedor == null) {
						
						/* Si la relacion es nula, quiere decir que el vendedor no esta asignado al punto de venta
						 * al que se quiere relacionar, por lo tanto es correcto hacer la relacion. lfcrespo
						 */
						if(relacion==null){
							
							// Se registra en bitacora que se hizo una nueva asignación de punto de venta a un vendedor
							int cambios =checkCambios(cveVendedor[j],cvePtoventas[i],String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario),fechaMovimiento,String.valueOf(cveUsuario),String.valueOf(cveUsuario)); 
							
							// Si no hubo errores en el checkCambios se realiza la relación
							if(cambios>0){
								ClavesVO clave = new ClavesVO();
								clave = CatalogoFacade.getRelacionClave();
								Object[] parametros = {
										String.valueOf(clave.getClave()),
										cveVendedor[j], 
										cvePtoventas[i],
										fechaMovimiento,
										String.valueOf(cveUsuario),   // el atributo usuario es numerico
										String.valueOf(cveUsuario), // el atributo autorizacion es numerico
										fechaInicio,//Fecha de Inicio
										user
									};
				
								CatalogoFacade.setRelacion(parametros);
								
							}else if (cambios == -1){
								
								logger.error("Error al revisar cambios, no se realiza la nueva relación.");
							}
						} 	else {
							/* Si la relación no es nula, quiere decir que el vendedor ya esta asignado al punto de venta
							 * que se esta tratando de relacionar, por lo tnato es correcto mandar el mensaje. lfcrespo
							 */ 
							request.setAttribute("noData","Ya existe la relacion entre el vendedor " + cveVendedor[j] +  " y la tienda " + cvePtoventas[i]);
						}
						
						// Se comenta el else. Si el puestoVendedor es diferente de nulo, quiere decir que ya tiene
						// una relación. Esto ya no aplica ya que el vendedor podra tener mas de un punto de venta. lfcrespo
						//} else {
						//	request.setAttribute("noData","El vendedor " + cveVendedor[j] +  " ya esta asignado a otra tienda");
						//}
						
						// genero un delay de varios segundos
						if(cvePtoventas_size > 1 || cveVendedor_size > 1)
							for(long k=0; k < 500000L; k++);

						fechaInicio = fechaMovimiento;
					}
				}
				String cveVendedorRes = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
				String cvePtoventasRes = (request.getParameter("pcCvePtoventas").equals(""))? null:request.getParameter("pcCvePtoventas");

				if(cvePtoventas.length > cveVendedor.length){
					if(subdivisionUsuario==0){
						tablaRelaciones = AdminCatFacade.getRelaciones(new Object[] {null,cveVendedorRes});
					}else{
						tablaRelaciones = CatalogoFacade.getRelaciones(null,cveVendedorRes,divs,divs);
					}
				}else{
					if(subdivisionUsuario==0 ){
						tablaRelaciones = AdminCatFacade.getRelaciones(new Object[] {cvePtoventasRes,null});
					}else{
						tablaRelaciones = CatalogoFacade.getRelaciones(cvePtoventasRes,null,divs,divs);
					}
				}
				form.reset(mapping,request);	
				if(!tablaRelaciones.isEmpty()){
					request.getSession().setAttribute("tablaRelaciones",tablaRelaciones);
				}else{
					request.setAttribute("noData","No existen Relaciones para este Componente");
				}
			}else{
				request.setAttribute("noData","No existen Relaciones para este Componente");
			}
		}catch(CatalogoSystemException cse){
			logger.error("Error al agregar relacion a PDV: " + cse.toString());
			
			// Despliega en pantalla el mensaje que lanza el Trigger: CANDADO_RELACIONES_ALMACEN_TRG
			// perteneciente a la Tabla: PC_INTER_VENDEDORES_PTOVENTAS
			String arrMsjErr [] = cse.getCausedByException().toString().split(":");
			if (arrMsjErr[1].trim().equals("ORA-20009")) {
				request.setAttribute("noData",arrMsjErr[2].split("\n")[0].trim());
			} else {
				return mapping.findForward("error");
			}			
		}catch(Exception e){
			logger.error("Error en agregar: " + e.toString());

			return mapping.findForward("error");
		}
		
		return mapping.findForward("home");
	}
	/********************************************************************************************
	 * Relaciones Modificar
	 *  
	 ********************************************************************************************/
	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			return mapping.findForward("home");
	}
	/********************************************************************************************
	 * Relaciones Eliminar
	 *  
	 ********************************************************************************************/
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			/* *
			 *	Se comentaron esas dos lineas porque el regreso lo hace de solicitar con la última busqueda 
			 *  ver metodo solicitar
			 * */
//						if(request.getSession().getAttribute("tablaRelaciones") != null) request.getSession().removeAttribute("tablaRelaciones"); 
//						if(request.getSession().getAttribute("noDataRelaciones") != null) request.getSession().removeAttribute("noDataRelaciones");
			try{
				String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				String cveInter = (request.getParameter("pcCveInter").equals(""))? null:request.getParameter("pcCveInter");
				String strFechaBaja = (request.getParameter("pcFchBaja").equals(""))? null:request.getParameter("pcFchBaja");
				
				if(strFechaBaja== null) strFechaBaja = Fecha.dateToString(new Date());
				
				String fechaFin = strFechaBaja;
						
				Calendar calendar = Calendar.getInstance();

				fechaFin = fechaFin + " ";
				if(calendar.get(Calendar.HOUR_OF_DAY) < 10)
					fechaFin = fechaFin + "0" + calendar.get(Calendar.HOUR_OF_DAY);
				else				
					fechaFin = fechaFin + calendar.get(Calendar.HOUR_OF_DAY);
 
				 if(calendar.get(Calendar.MINUTE) < 10)
					fechaFin = fechaFin + ":0" + calendar.get(Calendar.MINUTE);
				 else
					fechaFin = fechaFin + ":" + calendar.get(Calendar.MINUTE);
	
				if(calendar.get(Calendar.SECOND) < 10)	
					fechaFin = fechaFin + ":0" + calendar.get(Calendar.SECOND);
				else
					fechaFin = fechaFin + ":" + calendar.get(Calendar.SECOND);
								
				int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
				if(cveInter!=null) {
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
					String	divisionBuscar = String.valueOf(subdivisionUsuario);
					String divisionBuscar2 = String.valueOf(divisionUsuario);
					if(subdivisionUsuario!=0){ 
									divisionBuscar= 
										   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
								    divisionBuscar2=
										   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();      
										}    
					
					Pc_Inter_Vendedores_PtoventasVO relacion = null;
					if(subdivisionUsuario==0){
						relacion = AdminCatFacade.getRelacion(cveInter);	
					}else{
						relacion = CatalogoFacade.getRelacion(cveInter, divisionBuscar, divisionBuscar2);
					}
					
					if(relacion != null){
						int bitacora = checkCambios(String.valueOf(relacion.getPcCveVendedor()), relacion.getPcCvePtoventas(),divisionBuscar,divisionBuscar2,strFechaBaja,String.valueOf(cveUsuario),String.valueOf(cveUsuario));
						if(bitacora == ELIMINAR_PUNTOVENTAS) CatalogoFacade.deleteRelacion(cveInter, fechaFin, String.valueOf(cveUsuario), user);
					}
				}
			}catch(CatalogoSystemException cse){
				logger.error("Error en eliminar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error en eliminar: " + e.toString());
				return mapping.findForward("error");
			}
			//return mapping.findForward("home");
			return solicitar(mapping, form,	request, response);			
	}
	/********************************************************************************************
	 * Relaciones BuscaPuntosVenta
	 *  
	 ********************************************************************************************/
	public ActionForward buscaPuntosVenta(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
			if(request.getSession().getAttribute("noDataPuntosVenta") != null) request.getSession().removeAttribute("noDataPuntosVenta");
			if(request.getSession().getAttribute("noDataVendedores") != null) request.getSession().removeAttribute("noDataVendedores");
		
			int queryId = 0;
		
			ArrayList   tablaPuntosVenta = new ArrayList();					
			try{
				String divisionBuscar = (request.getParameter("pcCveDivisionSeek").equals(""))? null:request.getParameter("pcCveDivisionSeek");
				String tipoCanalBuscar = (request.getParameter("pcCveTipoCanalSeek").equals(""))? null:request.getParameter("pcCveTipoCanalSeek");
				String canalBuscar = (request.getParameter("pcCveCanalSeek").equals(""))? null:request.getParameter("pcCveCanalSeek");
				String vendedorBuscar = (request.getParameter("pcCvePtoventasSeek").equals(""))? null:request.getParameter("pcCvePtoventasSeek").trim();
				String nombreVendedorBuscar = (request.getParameter("pcNomPtoventasSeek").equals(""))? null:request.getParameter("pcNomPtoventasSeek").trim();
				String noReferencia = (request.getParameter("pcNoReferenciaSeek").equals(""))? null:request.getParameter("pcNoReferenciaSeek").trim();
				
				try{
					((RelacionesForm) form).setPcCvePtoventas(request.getParameterValues("pcCvePtoventas"));
					((RelacionesForm) form).setPcCveVendedor(request.getParameterValues("pcCveVendedor"));
				}catch(Exception e){
					logger.error("Error en recuperar datos de la Pantalla.");
				}
				
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				
				logger.info("subdivisionUsuario="+subdivisionUsuario);
				
				String divisionBuscar2 = String.valueOf(divisionUsuario);
					if(subdivisionUsuario!=0){ 
						boolean estadiv=false;
					   divisionBuscar2=
					   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs(); 
					   if(divisionBuscar!=null){
					   String cadenaDivs =((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
					   StringTokenizer tokens=new StringTokenizer(cadenaDivs, ",");
					   while(tokens.hasMoreTokens()){
					    if(divisionBuscar.equals(tokens.nextToken())){
							estadiv=true;
					    }
					   }
					   
					   }
					   if(!estadiv)
					      divisionBuscar=((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();     
					}    
							
				if(vendedorBuscar == null){
					tablaPuntosVenta = CatalogoFacade.getPuntosVentaTable(divisionBuscar,tipoCanalBuscar,canalBuscar,nombreVendedorBuscar, divisionBuscar2, noReferencia);
				}else{
					Pc_Punto_VentasVO detallePuntoVenta = null; 
					if(subdivisionUsuario==0){
						detallePuntoVenta = CatalogoFacade.getPuntoVenta3(vendedorBuscar);
					}else{
						detallePuntoVenta = CatalogoFacade.getPuntoVenta2(vendedorBuscar,divisionBuscar, divisionBuscar2);
					}
					if(detallePuntoVenta != null) tablaPuntosVenta.add(detallePuntoVenta);
				}
			}catch(CatalogoSystemException cse){
				logger.error("Error: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error: " + e.toString());
				return mapping.findForward("error");
			}

			if(!tablaPuntosVenta.isEmpty()){
				request.getSession().setAttribute("tablaPuntosVenta",tablaPuntosVenta);
			}else{
				request.getSession().setAttribute("noDataPuntosVenta","Su Búsqueda no genero Resultados");
			}
			return mapping.findForward("home");
			
	}
	/********************************************************************************************
	 * Relaciones BuscaVendedores
	 *  
	 ********************************************************************************************/
	public ActionForward buscaVendedores(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			RelacionesForm forma = (RelacionesForm) form;
			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
			if(request.getSession().getAttribute("noDataVendedores") != null) request.getSession().removeAttribute("noDataVendedores");
			
			ArrayList parametros = new ArrayList();
			ArrayList tablaVendedores = new ArrayList(); 					

			try{
				String puestoBuscar = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");
				String vendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek").trim();
				String nombreVendedorBuscar = (request.getParameter("pcNomVendedorSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
				String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek").trim();
			
				try{
					((RelacionesForm) form).setPcCvePtoventas(request.getParameterValues("pcCvePtoventas"));
					((RelacionesForm) form).setPcCveVendedor(request.getParameterValues("pcCveVendedor"));
					((RelacionesForm) form).setPcNomClaveEmpleadoReferenciaSeek(nomClaveEmpleadoReferenciaBuscar);
				}catch(Exception e){
					
				}
				
				/**
				 * 15/05/08.
				 * CAL
				 */
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				int rolUser = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveRol();
				boolean validado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
				if(validado){subdivisionUsuario = 0; divisionUsuario=0;}
				String divisionBuscar = String.valueOf(subdivisionUsuario);
				String divisionBuscar2 = String.valueOf(divisionUsuario);
					if(subdivisionUsuario!=0 || !validado){ 
						divisionBuscar= 
					   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
					   divisionBuscar2=
					   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();      
					}    
				String lineaSQL = "";	
			    ArrayList listaBuscar = new ArrayList();				
			/**
			 * En caso de que no exista la claveSAEO ni un numero de empleado.
			 */
				if(vendedorBuscar == null && nomClaveEmpleadoReferenciaBuscar == null){				
				  /**
				   * En caso de que exista tanto nombre como puesto del vendedor.
				   */	
				   if(nombreVendedorBuscar!= null && puestoBuscar!= null){
				    if((!nombreVendedorBuscar.equals("")) && (!puestoBuscar.equals(""))){
						 lineaSQL = PersonalMovProxy.armaQueryBusquedaFiltros
												   ("",request,"","",puestoBuscar,nombreVendedorBuscar,rolUser,validado,subdivisionUsuario,divisionBuscar);
					}
				   }	
				 /**
				  * En caso de que exista el nombre del vendedor a buscar.
				  */
					if(nombreVendedorBuscar!= null && puestoBuscar== null){
					  if(!nombreVendedorBuscar.equals("")){
   						 lineaSQL = PersonalMovProxy.armaQueryBusquedaNombre
						                           ("",request,nombreVendedorBuscar,rolUser,validado,subdivisionUsuario,divisionBuscar);
					  }
					} 
				 /**
				  * En caso de que exista el puesto del vendedor.
				  */	
					if(nombreVendedorBuscar== null && puestoBuscar!= null){
					  if(!puestoBuscar.equals("")){
						 lineaSQL = PersonalMovProxy.armaQueryBusquedaPuesto
												   ("",request,puestoBuscar,rolUser,validado,subdivisionUsuario,divisionBuscar);
					  }
					}  	
                    					
					/**
					 * En caso de que la tabla de vendedores no venga vacìa.
					 */	
					if(tablaVendedores!= null && !tablaVendedores.isEmpty()){
						Collections.sort(tablaVendedores);
						for(int i=0; i< tablaVendedores.size();i++){
							Pc_VendedoresVO vendedor =(Pc_VendedoresVO) tablaVendedores.get(i);
							int indice = tablaVendedores.lastIndexOf(vendedor);
							if(indice!=i) {
								tablaVendedores.remove(indice);
							} 
						}
					}
				}									
				/**
				 * En caso de que sólo hayan proporcionado la clave del vendedor.
				 */ 
				else{
				
				 if(vendedorBuscar != null && nomClaveEmpleadoReferenciaBuscar == null){
					lineaSQL = PersonalMovProxy.armaQueryBusquedaClave
								   ("",request,vendedorBuscar,rolUser,validado,subdivisionUsuario,divisionBuscar);					
				}
				
				/**
				 * En caso de que solo hayan proporcionado el numero de empleado.
				 */ 
				if((nomClaveEmpleadoReferenciaBuscar != null) && (!nomClaveEmpleadoReferenciaBuscar.equals("")) && (vendedorBuscar == null)){
					lineaSQL = PersonalMovProxy.armaQueryBusquedaNoEmpleado
								   ("",request,nomClaveEmpleadoReferenciaBuscar,rolUser,validado,subdivisionUsuario,divisionBuscar);
				}
			}
				listaBuscar = CatalogoFacade.getBuscaVendedores(lineaSQL);
				tablaVendedores.addAll(listaBuscar);
				
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
				request.getSession().setAttribute("noDataVendedores","Su Búsqueda no genero Resultados");
			}
			return mapping.findForward("home");
	}
	/********************************************************************************************
	 * Relaciones Solicitar
	 *  
	 ********************************************************************************************/
	public ActionForward solicitar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			RelacionesForm forma = (RelacionesForm) form;
			if(request.getSession().getAttribute("tablaRelaciones") != null) request.getSession().removeAttribute("tablaRelaciones"); 
			if(request.getSession().getAttribute("noDataRelaciones") != null) request.getSession().removeAttribute("noDataRelaciones");
			if(request.getSession().getAttribute("noDataVendedores") != null) request.getSession().removeAttribute("noDataVendedores");
			
			ArrayList tempRecords = new ArrayList();
			ArrayList tablaRelaciones = new ArrayList(); 					

			try{
				String control=request.getParameter("control");
				
				String subdivisionUsuario = String.valueOf(((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv());
				String divisionUsuario = String.valueOf(((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv());
				String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				boolean validado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
				if(validado){subdivisionUsuario = "0"; divisionUsuario="0";}
				if(!subdivisionUsuario.equals("0")){ 
					subdivisionUsuario = 
									   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();
					divisionUsuario    =
									   ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCvesDivs();      
									}    
				
				String[] CveVendedor = request.getParameterValues("pcCveVendedor");
				String[] CvePtoventas = request.getParameterValues("pcCvePtoventas");
				
				String buscar = ((RelacionesForm) form).getUltimaBusqueda();
									
				if(control == null){
					if(buscar.equals("vendedores") || buscar.equals("ptoventas")) {
						control=buscar;
						((RelacionesForm) form).setUltimaBusqueda("");
					}else{
						((RelacionesForm) form).setUltimaBusqueda("");
						return mapping.findForward("home");	
					}
				}					
				
				
				if(control.equals("vendedores")){
					((RelacionesForm) form).setUltimaBusqueda("vendedores");
					if(CveVendedor!= null){
						for(int i=0; i<CveVendedor.length; i++){
							if(divisionUsuario.equals("0") || validado){
				
								tempRecords = AdminCatFacade.getRelaciones(new Object[] {null,CveVendedor[i]});
							}else{
								tempRecords = CatalogoFacade.getRelaciones(null,CveVendedor[i],subdivisionUsuario, divisionUsuario);
							}
							
							if(!tempRecords.isEmpty())tablaRelaciones.addAll(tempRecords);
						}
					}
				}else if(control.equals("ptoventas")){
					((RelacionesForm) form).setUltimaBusqueda("ptoventas");
					if(CvePtoventas!= null){
						for(int i=0; i< CvePtoventas.length; i++){
							if(divisionUsuario.equals("0") || validado){
								tempRecords=AdminCatFacade.getRelaciones(new Object[] {CvePtoventas[i], null});
							}else{
								tempRecords=CatalogoFacade.getRelaciones(CvePtoventas[i], null,subdivisionUsuario,divisionUsuario);
							}
							if(!tempRecords.isEmpty())tablaRelaciones.addAll(tempRecords);
						}
					}
				}
				if(!tablaRelaciones.isEmpty()){
					request.getSession().setAttribute("tablaRelaciones",tablaRelaciones);
				}else{
					request.setAttribute("noDataRelaciones","No existen Relaciones para este Componente");
				}
			}catch(CatalogoSystemException cse){
				logger.error(cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error(e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
	/********************************************************************************************
	 * Relaciones checkCambios
	 *  
	 ********************************************************************************************/
	public static int ELIMINAR_PUNTOVENTAS = 2;
	public static int AGREGAR_PUNTOVENTAS = 1;
	public static int CAMBIO_PUNTOVENTAS = 4;
	public static int CAMBIOS = 8;
	public static int SIN_CAMBIOS = 0;
	public static int ERROR = (-1);
	
	public int checkCambios(String CveVendedor, String CvePtoventas, String subdivisionUsuario, String divisionUsuario, String fechaMov, String usuario, String autoriza) throws Exception{
			String ptoventas_nuevo = null;
			int cambios_presentes=0;

			try{

				if(fechaMov==null)fechaMov = Fecha.dateToString(new Date());				
				ArrayList tablaRelaciones = null;
				if(divisionUsuario.equals("0")){
					tablaRelaciones = AdminCatFacade.getRelaciones(new Object[] {CvePtoventas,CveVendedor});	
				}else{
					tablaRelaciones = CatalogoFacade.getRelaciones(CvePtoventas,CveVendedor,subdivisionUsuario,divisionUsuario);
				}

				if(tablaRelaciones== null || tablaRelaciones.isEmpty()){
					cambios_presentes = cambios_presentes  + CAMBIO_PUNTOVENTAS; 

					String ptoventas_actual = "";
					ptoventas_nuevo = CvePtoventas;
					ArrayList param = new ArrayList();
					ClavesVO clave = CatalogoFacade.getMovimientoClave();				
					param.add(String.valueOf(clave.getClave()));
					param.add(String.valueOf(CveVendedor));
					param.add(ptoventas_actual);
					param.add(ptoventas_nuevo);
					param.add(usuario);
					param.add(autoriza);
					param.add(fechaMov);
					CatalogoFacade.setMovPuntoVentas(param.toArray());	
				}else{
					cambios_presentes = cambios_presentes  + ELIMINAR_PUNTOVENTAS; 

					String ptoventas_actual = CvePtoventas;
					ptoventas_nuevo = "";
					ArrayList param = new ArrayList();
					ClavesVO clave = CatalogoFacade.getMovimientoClave();				
					param.add(String.valueOf(clave.getClave()));
					param.add(String.valueOf(CveVendedor));
					param.add(ptoventas_actual);
					param.add(ptoventas_nuevo);
					param.add(String.valueOf(usuario));
					param.add(String.valueOf(autoriza));
					param.add(fechaMov);
					CatalogoFacade.setMovPuntoVentas(param.toArray());	
				}
			}catch(CatalogoSystemException cse){
				logger.error("checar cambios cse_error:" + cse.toString());
				return ERROR;
			}catch(Exception e){
				logger.error("checar cambios e_error:" + e.toString());
				return ERROR;
			}
		return cambios_presentes;		
	}

}
