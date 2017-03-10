/*
 * Created on Mar 17, 2005
 *
 */
package mx.com.iusacell.catalogo.web.personal.struts.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.utilerias.Fecha;
import mx.com.iusacell.catalogo.utilerias.DigitoVerificador;

import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.modelo.Pc_Inter_Vendedores_PtoventasVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;

import mx.com.iusacell.catalogo.modelo.ClavesVO;
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
 * tablaRegiones - Genera la Lista de los Regiones desde la BD
 */
public class PersonalProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(PersonalProxy.class);
	/**
	 * Personal Proxy
	 *  
	 */
	public PersonalProxy() {
	}
	/*****************************************************************************************
	 * Personal VerLista
	 *  
	 *****************************************************************************************/
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
			ArrayList tablaPuestos = new ArrayList();
		
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

			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
			if(request.getSession().getAttribute("detalleVendedor") != null) request.getSession().removeAttribute("detalleVendedor"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");

			//PersonalForm forma = (PersonalForm) form;
			int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();

			try{
				ClavesVO clave = new ClavesVO();
				String tipoRegistro= request.getParameter("pcTipoRegistro");
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
				String strCiudad = (request.getParameter("pcCiudad").equals(""))? null:request.getParameter("pcCiudad");
				String strEstado = (request.getParameter("pcEstado").equals(""))? null:request.getParameter("pcEstado");
				String strDireccion = (request.getParameter("pcDireccion").equals(""))? null:request.getParameter("pcDireccion");
				String strColonia = (request.getParameter("pcColonia").equals(""))? null:request.getParameter("pcColonia");
				String strCp = (request.getParameter("pcCp").equals(""))? null:request.getParameter("pcCp");
				String strTel = (request.getParameter("pcTel").equals(""))? null:request.getParameter("pcTel");
				String strFax = (request.getParameter("pcFax").equals(""))? null:request.getParameter("pcFax");
				String CveSupCad = (request.getParameter("pcCveSupCad").equals(""))? null:request.getParameter("pcCveSupCad");
				String pcCveEmpresa = (request.getParameter("pcCveEmpresa").equals(""))? null:request.getParameter("pcCveEmpresa");

				String CveSubdiv = null;
				if(subdivisionUsuario==0){
					CveSubdiv = (request.getParameter("pcCveSubdiv").equals(""))? null:request.getParameter("pcCveSubdiv");
				}

				if(CveSuperior == null){
					CveSuperior = String.valueOf(subdivisionUsuario);
				}
				if(CveSubdiv  == null){
					CveSubdiv  = String.valueOf(subdivisionUsuario);
				}
				if(CveSupCad == null){
					CveSupCad = "NULL";
				}
				if(Status == null){
					Status = "ALTA";
				}
				
				DigitoVerificador digVerif = new DigitoVerificador();
				digVerif.setDigitoVerificador((long) clave.getClave());
				
				Object[] parametros = {
						String.valueOf(clave.getClave()),
						request.getParameter("pcCvePuesto"), 
						CveSuperior,
						request.getParameter("pcNomVendedor"),
						request.getParameter("pcApePaterno"),
						request.getParameter("pcApeMaterno"),
						request.getParameter("pcFchAlta"),
						String.valueOf(digVerif.getDigitoVerificador()),
						request.getParameter("pcCntrTda"),
						Status,
						request.getParameter("pcCveEmpRef"),
						strCiudad,
						strEstado,
						strDireccion,
						strColonia,
						strCp,
						strTel,
						strFax,
						CveSupCad,
						CveSubdiv, 
						pcCveEmpresa
					};
					
				CatalogoFacade.setPersonal(parametros);
				((PersonalForm) form).reset(mapping,request);
				String cvePuesto = (request.getParameter("pcCvePuesto").equals(""))? null:request.getParameter("pcCvePuesto");
				((PersonalForm) form).setPcCvePuestoSeek(cvePuesto);

				Pc_VendedoresVO vendedor = null;
				if(divisionUsuario==0){
					vendedor = AdminCatFacade.getPersonal(String.valueOf(clave.getClave()));
				}else{
					vendedor = CatalogoFacade.getPersonal(String.valueOf(clave.getClave()),String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
				}
				ArrayList tablaVendedores = new ArrayList();
				if(vendedor!=null)tablaVendedores.add(vendedor);
				if(!tablaVendedores.isEmpty()){
					request.getSession().setAttribute("tablaVendedores",tablaVendedores);
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
			if(request.getSession().getAttribute("detalleVendedor") != null) request.getSession().removeAttribute("detalleVendedor"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
			
			String control= null;
			 
			try{
				control=request.getParameter("control");
				String cveVendedor  = (request.getParameter("pcCveVendedor").equals(""))? null:request.getParameter("pcCveVendedor");
				String fechaBaja = (request.getParameter("pcFchBaja").equals(""))? null:request.getParameter("pcFchBaja");
				if(fechaBaja ==null){
					fechaBaja= Fecha.dateToString(new Date());
				}
/*
 * 	Los vendedores no se eliminan simplemente se dan de baja, se cambia el código
 */					

				if(cveVendedor!=null){
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();

					int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
					if(verificarPersonal(cveVendedor,String.valueOf(subdivisionUsuario))==true){
						request.getSession().setAttribute("hayPersonal","No se puede dar de Baja al vendedor " + cveVendedor + " porque tiene personal asignado");
					}else {
						if(control==null || control.equals("baja")){
							CatalogoFacade.bajaPersonal(cveVendedor,fechaBaja);
							request.getSession().setAttribute("noData","Se dio de baja el vendedor con clave " + cveVendedor); 
						}else if(control.equals("borrado")){
							Pc_VendedoresVO checarMov = null;  
							Pc_Inter_Vendedores_PtoventasVO  checarRelacion =  null;

							if(subdivisionUsuario==0){
								checarMov = AdminCatFacade.checkMovVendedor(cveVendedor);
								checarRelacion =  AdminCatFacade.checkRelacion(cveVendedor);
							}else{
								checarMov = CatalogoFacade.checkMovVendedor(cveVendedor, String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
								checarRelacion =  CatalogoFacade.checkRelacion(cveVendedor,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
							}
						
							if(checarMov == null && checarRelacion== null){
								CatalogoFacade.deletePersonal(cveVendedor); 
								request.getSession().setAttribute("noData","Se elimino el vendedor con clave " + cveVendedor); 
							} else{
								CatalogoFacade.bajaPersonal(cveVendedor, fechaBaja);
								request.getSession().setAttribute("noData","No se puede borrar el vendedor con clave " + cveVendedor + " ya que tiene alguna relación presente en la base. Se marco el registro como BAJA. Para borrar el vendedor definitivamente, elimine las relaciones existentes e intente de nuevo."); 
							}
						}
					}
				}
				((PersonalForm ) form).reset(mapping,request);
			}catch(CatalogoSystemException cse){
				logger.error("Error: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error: " + e.toString());
				return mapping.findForward("error");
			}
		
			return mapping.findForward("home");
	}
	/*****************************************************************************************
	 * Personal Modificar
	 *  
	 *****************************************************************************************/
	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			//PersonalForm forma = (PersonalForm) form;
			if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
			if(request.getSession().getAttribute("detalleVendedor") != null) request.getSession().removeAttribute("detalleVendedor"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
			
			String CveVendedor = request.getParameter("pcCveVendedor");
			String CvePuestoDisplay = request.getParameter("pcCvePuesto");
			
			if(CveVendedor == null || CveVendedor.equals("")){
				CveVendedor=  request.getParameter("pcCveVendedorChg");
			}
			
			if(CveVendedor != null && !(CveVendedor.equals(""))){
				try{

					String strCiudad = (request.getParameter("pcCiudad").equals(""))? null:request.getParameter("pcCiudad");
					String strEstado = (request.getParameter("pcEstado").equals(""))? null:request.getParameter("pcEstado");
					String strDireccion = (request.getParameter("pcDireccion").equals(""))? null:request.getParameter("pcDireccion");
					String strColonia = (request.getParameter("pcColonia").equals(""))? null:request.getParameter("pcColonia");
					String strCp = (request.getParameter("pcCp").equals(""))? null:request.getParameter("pcCp");
					String strTel = (request.getParameter("pcTel").equals(""))? null:request.getParameter("pcTel");
					String strFax = (request.getParameter("pcFax").equals(""))? null:request.getParameter("pcFax");
					String strFechaAlta = (request.getParameter("pcFchAlta").equals(""))? null:request.getParameter("pcFchAlta");
					String CveSupCad = null;//(request.getParameter("pcCveSupCad").equals(""))? null:request.getParameter("pcCveSupCad");
					String CveSuperior = (request.getParameter("pcCveSuperior").equals(""))? null:request.getParameter("pcCveSuperior");
					String pcCveEmpresa = (request.getParameter("pcCveEmpresa").equals(""))? null:request.getParameter("pcCveEmpresa");
										
					String strFechaCambio = (request.getParameter("pcFchCambio").equals(""))? null:request.getParameter("pcFchCambio");

					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
					String CveSubdiv = null;
					if(subdivisionUsuario==0){
						CveSubdiv = (request.getParameter("pcCveSubdiv").equals(""))? null:request.getParameter("pcCveSubdiv");
					}

					/*if(CveSubdiv==null) CveSuperior= String.valueOf(subdivisionUsuario);
					if(CveSuperior==null) CveSuperior= String.valueOf(subdivisionUsuario);
					if(strFechaAlta==null) strFechaAlta = Fecha.dateToString(new Date());
					if(strFechaCambio==null) strFechaCambio = Fecha.dateToString(new Date());*/
					
					if(CveSuperior==null) CveSuperior= String.valueOf(subdivisionUsuario);
					if(CveSubdiv==null) CveSubdiv= String.valueOf(subdivisionUsuario);
					if(strFechaAlta==null) strFechaAlta = Fecha.dateToString(new Date());
					if(strFechaCambio==null) strFechaCambio = Fecha.dateToString(new Date());
								
					Object[] parametros = {
						CveVendedor, 
						request.getParameter("pcCvePuesto"), 
						CveSuperior,
						request.getParameter("pcNomVendedor"),
						request.getParameter("pcApePaterno"),
						request.getParameter("pcApeMaterno"),
						request.getParameter("pcCntrTda"),
						request.getParameter("pcCveEmpRef"),
						strCiudad,
						strEstado,
						strDireccion,
						strColonia,
						strCp,
						strTel,
						strFax,
						strFechaAlta,
						(CveSupCad==null)?"NULL":CveSupCad,
						CveSubdiv,
						pcCveEmpresa
						};
					int cveUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();	
					if(checkCambios(CveVendedor,CveSuperior,CveSupCad,request.getParameter("pcCvePuesto"),String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario), strFechaCambio, String.valueOf(cveUsuario),String.valueOf(cveUsuario))>-1){						
						CatalogoFacade.changePersonal(parametros);
						((PersonalForm) form).reset(mapping,request);
					}else{
						request.getSession().setAttribute("noData","Hubo un problema al realizar los cambios para la clave " + CveVendedor);
					}
					((PersonalForm) form).setPcCvePuestoSeek(CvePuestoDisplay);
					Pc_VendedoresVO vendedor = null;
					if (subdivisionUsuario==0){
						vendedor = AdminCatFacade.getPersonal(CveVendedor);
						//tablaVendedores  = AdminCatFacade.getPersonalTable(new Object[] {CvePuestoDisplay,null});
					}else{
						vendedor = CatalogoFacade.getPersonal(CveVendedor,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
						//tablaVendedores  = CatalogoFacade.getPersonalTable(CvePuestoDisplay,null,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
					}
					ArrayList tablaVendedores  = new ArrayList();
					if(vendedor!=null) tablaVendedores.add(vendedor);
					if(!tablaVendedores.isEmpty()){
						request.getSession().setAttribute("tablaVendedores",tablaVendedores);
					}
				}catch(CatalogoSystemException cse){
					logger.error("modificar cse_error: " + cse.toString());
					return mapping.findForward("error");
				}catch(Exception e){
					logger.error("modificar e_error: " + e.toString());
					return mapping.findForward("error");
				}
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
			 
			try{
				control=request.getParameter("control");
				
				if(control == null){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					if(request.getSession().getAttribute("detalleVendedor") != null) request.getSession().removeAttribute("detalleVendedor");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
					if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");
					return mapping.findForward("home");
				}else if(control.equals("superior")){
					
					return mapping.findForward("home");
				}else if(control.equals("superiorSel")){
					String superiorSel = (request.getParameter("pcCveSuperiorSel").equals(""))? null:request.getParameter("pcCveSuperiorSel");
					((PersonalForm) form).setPcCveSuperior(Integer.parseInt(superiorSel));
					return mapping.findForward("home");
				}else if(control.equals("buscar")){
					if(request.getSession().getAttribute("tablaVendedores") != null) request.getSession().removeAttribute("tablaVendedores"); 
					if(request.getSession().getAttribute("detalleVendedor") != null) request.getSession().removeAttribute("detalleVendedor");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
					if(request.getSession().getAttribute("hayPersonal") != null) request.getSession().removeAttribute("hayPersonal");

					String puestoBuscar = (request.getParameter("pcCvePuestoSeek").equals(""))? null:request.getParameter("pcCvePuestoSeek");
					String vendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
					String nombreVendedorBuscar = (request.getParameter("pcNomVendedorSeek").equals(""))? null:request.getParameter("pcNomVendedorSeek");
					String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");

					int queryId = 0;

					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();

					if(vendedorBuscar == null && nomClaveEmpleadoReferenciaBuscar== null){
						((PersonalForm) form).reset(mapping,request);

						if(puestoBuscar!= null && !puestoBuscar.equals(""))
							((PersonalForm) form).setPcCvePuesto(Integer.parseInt(puestoBuscar));
						else
							((PersonalForm) form).setPcCvePuesto(1);
							
						((PersonalForm) form).setPcCveSuperior(0);
						((PersonalForm) form).setPcCveVendedor(0);
						((PersonalForm) form).setPcCveEmpRef("");
						((PersonalForm) form).setPcFchAlta("");
						((PersonalForm) form).setPcFchBaja("");

						int tokens = 0;
						ArrayList tablaVendedores = new ArrayList();
						
						ArrayList listaBuscar = new ArrayList();
						if(nombreVendedorBuscar!= null && !nombreVendedorBuscar.equals("")){
							StringTokenizer strTk = new StringTokenizer(nombreVendedorBuscar);
							tokens = strTk.countTokens();
							
							if(tokens>1){
								while(strTk.hasMoreTokens()){
									String tokenStr =(String)strTk.nextElement();
									listaBuscar.add(tokenStr);
								}
							}
						}else{
							listaBuscar.add(null);
						}
						
						if(subdivisionUsuario==0){
							ArrayList stringCompleto = AdminCatFacade.getPersonalTableCompleto(new Object[] {puestoBuscar,nombreVendedorBuscar});
							if(stringCompleto!= null && !stringCompleto.isEmpty()){
								tablaVendedores.addAll(stringCompleto);
							}else{
								for(int i=0; i<listaBuscar.size();i++){
									ArrayList resultParcial = AdminCatFacade.getPersonalTable(new Object[] {puestoBuscar,(String) listaBuscar.get(i)});
									
									if(resultParcial != null) tablaVendedores.addAll(resultParcial);  
								}
							}
						}else{
							ArrayList stringCompleto = CatalogoFacade.getPersonalPerdidoTableCompleto(puestoBuscar,nombreVendedorBuscar,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
							if(stringCompleto!= null && !stringCompleto.isEmpty()){
								tablaVendedores.addAll(stringCompleto);
							}else{
								for(int i=0; i<listaBuscar.size();i++){
									ArrayList resultParcial = CatalogoFacade.getPersonalPerdidoTable(puestoBuscar,(String) listaBuscar.get(i),String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
									if(resultParcial!=null) tablaVendedores.addAll(resultParcial);  
								}
							}
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
					if(subdivisionUsuario==0){
						detalleVendedor = AdminCatFacade.getPersonal(vendedorBuscar); 
					}else{
						detalleVendedor = CatalogoFacade.getPersonal(vendedorBuscar, String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
					}
						
					if(detalleVendedor != null){
						((PersonalForm) form).setPcNomVendedor(detalleVendedor.getPcNomVendedor());
						((PersonalForm) form).setPcApePaterno(detalleVendedor.getPcApePaterno());
						((PersonalForm) form).setPcApeMaterno(detalleVendedor.getPcApeMaterno());
						((PersonalForm) form).setPcCiudad(detalleVendedor.getPcCiudad());
						((PersonalForm) form).setPcEstado(detalleVendedor.getPcEstado());
						((PersonalForm) form).setPcDireccion(detalleVendedor.getPcDireccion());
						((PersonalForm) form).setPcColonia(detalleVendedor.getPcColonia());
						((PersonalForm) form).setPcCp(detalleVendedor.getPcCp());
						((PersonalForm) form).setPcTel(detalleVendedor.getPcTel());
						((PersonalForm) form).setPcFax(detalleVendedor.getPcFax());
						((PersonalForm) form).setPcCntrTda(detalleVendedor.getPcCntrTda());
						((PersonalForm) form).setPcStatus(detalleVendedor.getPcStatus());
						((PersonalForm) form).setPcDigVerif(String.valueOf(detalleVendedor.getPcDigVerif()));
						((PersonalForm) form).setPcCvePuesto(detalleVendedor.getPcCvePuesto());
						((PersonalForm) form).setPcCveSuperior(detalleVendedor.getPcCveSuperior());
						((PersonalForm) form).setPcCveVendedor(detalleVendedor.getPcCveVendedor());
						((PersonalForm) form).setPcCveEmpRef(String.valueOf(detalleVendedor.getPcCveEmpRef()));
						((PersonalForm) form).setPcFchAlta(Fecha.dateToString(detalleVendedor.getPcFchAlta()));
						((PersonalForm) form).setPcFchBaja(Fecha.dateToString(detalleVendedor.getPcFchBaja()));
						((PersonalForm) form).setPcCveVendedorChg(detalleVendedor.getPcCveVendedor());
						//((PersonalForm) form).setPcDescHorario(detalleVendedor.getPcDescHorario());
								
						ArrayList tablaVendedores = new ArrayList();
						tablaVendedores.add(detalleVendedor);
						request.getSession().setAttribute("tablaVendedores",tablaVendedores);	
						request.getSession().setAttribute("detalleVendedor",detalleVendedor);	
												
					}else{
						request.getSession().setAttribute("noData","No Existen Vendedores con Clave " + vendedorBuscar);
							((PersonalForm) form).reset(mapping,request);
					}																
				}
				else if(nomClaveEmpleadoReferenciaBuscar != null && vendedorBuscar == null){
				Pc_VendedoresVO detalleVendedor = null;
				if(subdivisionUsuario==0){
					detalleVendedor = AdminCatFacade.getVendedorByClave(nomClaveEmpleadoReferenciaBuscar);
				}else {
					detalleVendedor = CatalogoFacade.getVendedorByClave(nomClaveEmpleadoReferenciaBuscar,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
				}
				//if(detalleVendedor != null)tablaVendedores.add(detalleVendedor);
				if(detalleVendedor != null)
				{
					((PersonalForm) form).setPcNomVendedor(detalleVendedor.getPcNomVendedor());
					((PersonalForm) form).setPcApePaterno(detalleVendedor.getPcApePaterno());
					((PersonalForm) form).setPcApeMaterno(detalleVendedor.getPcApeMaterno());
					((PersonalForm) form).setPcCiudad(detalleVendedor.getPcCiudad());
					((PersonalForm) form).setPcEstado(detalleVendedor.getPcEstado());
					((PersonalForm) form).setPcDireccion(detalleVendedor.getPcDireccion());
					((PersonalForm) form).setPcColonia(detalleVendedor.getPcColonia());
					((PersonalForm) form).setPcCp(detalleVendedor.getPcCp());
					((PersonalForm) form).setPcTel(detalleVendedor.getPcTel());
			   	    ((PersonalForm) form).setPcFax(detalleVendedor.getPcFax());
					((PersonalForm) form).setPcCntrTda(detalleVendedor.getPcCntrTda());
					((PersonalForm) form).setPcStatus(detalleVendedor.getPcStatus());
					((PersonalForm) form).setPcDigVerif(String.valueOf(detalleVendedor.getPcDigVerif()));
					((PersonalForm) form).setPcCvePuesto(detalleVendedor.getPcCvePuesto());
					((PersonalForm) form).setPcCveSuperior(detalleVendedor.getPcCveSuperior());
					((PersonalForm) form).setPcCveVendedor(detalleVendedor.getPcCveVendedor());
					((PersonalForm) form).setPcCveEmpRef(String.valueOf(detalleVendedor.getPcCveEmpRef()));
					((PersonalForm) form).setPcFchAlta(Fecha.dateToString(detalleVendedor.getPcFchAlta()));
					((PersonalForm) form).setPcFchBaja(Fecha.dateToString(detalleVendedor.getPcFchBaja()));
					((PersonalForm) form).setPcCveVendedorChg(detalleVendedor.getPcCveVendedor());
					
					 ArrayList tablaVendedores = new ArrayList();
					 tablaVendedores.add(detalleVendedor);
					 request.getSession().setAttribute("tablaVendedores",tablaVendedores);	
					 request.getSession().setAttribute("detalleVendedor",detalleVendedor);
				  }
			   }	
			}
		}catch(CatalogoSystemException cse){
				logger.error("error consultar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error: " + e.toString());
				e.printStackTrace();
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
					check = CatalogoFacade.checkVendedor(CveVendedor, subdivisionUsuario, divisionUsuario);
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
					if(CveSupCad!=null && !CveSupCad.equals("") && !CveSupCad.equals("NULL") ){
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
	public int desasignarPersonal(String cveVendedor, String subdivisionUsuario, String division, String fechaMov, String usuario, String autoriza) throws Exception{
		int cambios_presentes=0;
		int cambios_ciclo = 0;
		try{
			Pc_VendedoresVO miVendedor = null;
			if(division.equals("0")){
				miVendedor = AdminCatFacade.getPersonal(cveVendedor);	 
			}else{
				miVendedor = CatalogoFacade.getPersonal(cveVendedor, subdivisionUsuario, division);
			}
			
			ArrayList subordinados = CatalogoFacade.getPersonalComprobarSuperior(cveVendedor);
			if(!subordinados.isEmpty()){
				Iterator subordinadosItera = subordinados.iterator();
				while(subordinadosItera.hasNext()){
					Pc_VendedoresVO gente = (Pc_VendedoresVO) subordinadosItera.next();
					String vendedorCheck = String.valueOf(gente.getPcCveVendedor());
					String superiorCheck= String.valueOf(miVendedor.getPcCveSuperior());
					String supCadCheck = String.valueOf(gente.getPcCveSupCad());
					if(checkCambios( vendedorCheck , superiorCheck, supCadCheck, "", subdivisionUsuario,division, fechaMov, usuario, autoriza)==-1)
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
					if(checkCambios( vendedorCheck , superiorCheck, supCadCheck, "", subdivisionUsuario, division, fechaMov, usuario, autoriza)==-1)
						cambios_ciclo=ERROR;
				}	
				if(cambios_ciclo==ERROR){
					cambios_presentes=ERROR;
				}else{
					CatalogoFacade.unassignSuperiorCadena(cveVendedor, autoriza);
				}
			}
			if(cambios_presentes!= ERROR && checkCambios( cveVendedor, subdivisionUsuario, division, "", "", division, fechaMov, usuario, autoriza)>-1){
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
			logger.error(e.toString());
			throw e;
		}
		return existenSubordinados;
	}
}

