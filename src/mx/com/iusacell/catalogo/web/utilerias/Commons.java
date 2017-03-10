/*
 * Creado el Jun 1, 2005
 *
 */
package mx.com.iusacell.catalogo.web.utilerias;

/**
 * @author Dvazqueza
 *
 */
import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Collections;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.utilerias.Fecha;
import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO;
import mx.com.iusacell.catalogo.modelo.ClavesVO;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;



import org.apache.log4j.Logger;


public class Commons {

	protected static final Logger logger = Logger.getLogger(Commons.class);

	/***********************************************************************************
	 * buscarPuntoVentas
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public static ArrayList buscarPuntoVentas(String divisionBuscar, String tipoCanalBuscar, 
	                                       String canalBuscar, String vendedorBuscar, 
	                                       String nombreVendedorBuscar, int subdivisionUsuario, int divisionUsuario)
			throws CatalogoSystemException ,Exception{

			String control= null;
			int queryId = 0;

			try{				
				ArrayList tablaPuntosVenta  = new ArrayList();	

				if(divisionBuscar == null) {
					if(subdivisionUsuario!=0) divisionBuscar = String.valueOf(subdivisionUsuario);
				} 
				
				if(vendedorBuscar == null){
					tablaPuntosVenta  = CatalogoFacade.getPuntosVentaTable(divisionBuscar,tipoCanalBuscar,canalBuscar,nombreVendedorBuscar, String.valueOf(divisionUsuario),null);
				}else{

					Pc_Punto_VentasVO detallePuntoVenta = null;
					if(subdivisionUsuario==0){
						detallePuntoVenta = AdminCatFacade.getPuntoVenta(vendedorBuscar);	
					}else{
						detallePuntoVenta = CatalogoFacade.getPuntoVenta(vendedorBuscar, String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
					}
					if(detallePuntoVenta != null){
						tablaPuntosVenta.add(detallePuntoVenta);  
					}
				}
				return tablaPuntosVenta;
			}catch(CatalogoSystemException cse){
				logger.error("buscarPuntoVentas cse_error: " + cse.toString());
				throw cse;
			}catch(Exception e){
				logger.error("buscarPuntoVentas e_error: " + e.toString());
				throw e;
			}
	}
	
	/***********************************************************************************
	 * buscarSuperior
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public static ArrayList buscarSuperior(String vendedorBuscar, 
									           String puestoBuscar, 
									           String nombreVendedorBuscar,
									           String pcCvePuesto, 
									           int subdivisionUsuario,
									           int divisionUsuario)
			throws CatalogoSystemException ,Exception{
			

			try{
				ArrayList tablaVendedores = new ArrayList();
				if(vendedorBuscar == null){
					int tokens = 0;
					
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
						ArrayList stringCompleto = AdminCatFacade.getSuperiorTableCompleto(new Object[] {puestoBuscar,nombreVendedorBuscar,pcCvePuesto});
						if(stringCompleto!= null && !stringCompleto.isEmpty()){
							tablaVendedores.addAll(stringCompleto);
						}else{
							for(int i=0; i<listaBuscar.size();i++){
								ArrayList resultParcial = AdminCatFacade.getSuperiorTable(new Object[] {puestoBuscar,(String) listaBuscar.get(i),pcCvePuesto});
								if(resultParcial != null) tablaVendedores.addAll(resultParcial);  
							}
						}
					}else{
						ArrayList stringCompleto = CatalogoFacade.getSuperiorPerdidoTableCompleto(puestoBuscar,nombreVendedorBuscar,String.valueOf(subdivisionUsuario),pcCvePuesto, String.valueOf(divisionUsuario));
						if(stringCompleto!= null && !stringCompleto.isEmpty()){
							tablaVendedores.addAll(stringCompleto);
						}else{
							for(int i=0; i<listaBuscar.size();i++){
								ArrayList resultParcial = CatalogoFacade.getSuperiorPerdidoTable(puestoBuscar,(String) listaBuscar.get(i),String.valueOf(subdivisionUsuario),pcCvePuesto,String.valueOf(divisionUsuario));
								if(resultParcial!=null) tablaVendedores.addAll(resultParcial);  
							}
						}
					}
						
					if(tablaVendedores!= null && !tablaVendedores.isEmpty()){
						ordenaUnicos(tablaVendedores);
					}
				}else{
					Pc_VendedoresVO detalleVendedor = null;
					if(subdivisionUsuario==0){
						detalleVendedor = AdminCatFacade.getPersonal(vendedorBuscar);
					}else{
						detalleVendedor = CatalogoFacade.getPersonal(vendedorBuscar, String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));	
					}
					
					if(detalleVendedor != null){
						tablaVendedores.add(detalleVendedor);
					}
				}
				return tablaVendedores;
			}catch(CatalogoSystemException cse){
				logger.error("buscarSuperiores cse_error: " + cse.toString());
				throw cse;
			}catch(Exception e){
				logger.error("buscarSuperiores e_error: " + e.toString());
				throw e;
			}
	}
	/***********************************************************************************
	 * buscarVendedor
	 * @return	ActionForward
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public static ArrayList buscarVendedores(String vendedorBuscar, 
	                                           String puestoBuscar, 
	                                           String nombreVendedorBuscar, 
	                                           int subdivisionUsuario, 
	                                           int divisionUsuario)
		throws CatalogoSystemException ,Exception{	

		try{
			ArrayList tablaVendedores = new ArrayList();
	
			if(vendedorBuscar == null){
				int tokens = 0;
							
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
							ArrayList resultParcial = CatalogoFacade.getPersonalPerdidoTable(puestoBuscar,(String) listaBuscar.get(i),String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
							if(resultParcial!=null) tablaVendedores.addAll(resultParcial);  
						}
					}
				}
				if(tablaVendedores!= null && !tablaVendedores.isEmpty()){
					ordenaUnicos(tablaVendedores);
				}
			}else{
				Pc_VendedoresVO detalleVendedor = null;
				if(subdivisionUsuario==0){
					detalleVendedor = AdminCatFacade.getPersonal(vendedorBuscar);
				}else{
					detalleVendedor = CatalogoFacade.getPersonal(vendedorBuscar, String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));	
				}
				
				if(detalleVendedor != null) tablaVendedores.add(detalleVendedor);
													
			}
			return tablaVendedores;
		}catch(CatalogoSystemException cse){
			logger.error("buscarVendedores cse_error:" + cse.toString());
			throw cse;
		}catch(Exception e){
			logger.error("buscarVendedores e_error: " + e.toString());
			throw e;
		}
	
	}
	/**************************************************************************************
	 * buscarSubordinados
	 * @param cveVendedor
	 * @param division
	 * @return
	 * @throws CatalogoSystemException
	 * @throws Exception
	 ***************************************************************************************/
	public static ArrayList buscarSubordinados(String cveVendedor, int subdivision, int division) 
			throws CatalogoSystemException ,Exception{
		try{		
			ArrayList tablaVendedores = new ArrayList();
			if(division==0){
				tablaVendedores = AdminCatFacade.getSubordinados(cveVendedor);
			}else{
				tablaVendedores = CatalogoFacade.getSubordinados(cveVendedor,String.valueOf(subdivision),String.valueOf(division));
			}
			
			if(tablaVendedores!= null && !tablaVendedores.isEmpty()){
				ordenaUnicos(tablaVendedores);
			}
			return tablaVendedores;
		}catch(CatalogoSystemException cse){
			logger.error("buscarSubordinados cse_error:" + cse.toString());
			throw cse;
		}catch(Exception e){
			logger.error("buscarSubordinados e_error: " + e.toString());
			throw e;
		}
			
	}
	/***********************************************************************************
	 * obtenerClaveVendedor
	 * @return	int
	 * @param 	mapping 	--  ActionMapping  
	 *  	  	form		--	ActionForm
	 * 		  	request 	--	HttpServletRequest
	 *        	response	--	HttpServletResponse
	 ***********************************************************************************/
	public static int obtenerClaveVendedor(String tipoRegistro,String pcCveVendedorDefine){

			ClavesVO clave = new ClavesVO();
			int cveVendedor = 0;	
			if(tipoRegistro.equals("persona")){
				clave = CatalogoFacade.getVendedoresClavePersona();
				cveVendedor = clave.getClave();
			}else if(tipoRegistro.equals("tienda")){
				clave = CatalogoFacade.getVendedoresClaveTienda();
				cveVendedor = clave.getClave();
			}else if(tipoRegistro.equals("distribuidor") || tipoRegistro.equals("interno")){
				try{
					cveVendedor = Integer.parseInt(pcCveVendedorDefine);
				}catch(NumberFormatException nfe){
					cveVendedor = 0;
				}
			}
		return cveVendedor;
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
	
	public static int checkCambios(String CveVendedor, String CvePtoventas, String subdivisionUsuario, String divisionUsuario, String fechaMov, String usuario, String autoriza) throws Exception{
			String ptoventas_nuevo = null;
			//int usuario = 100001;
			//int autoriza = 100001;
			int cambios_presentes=0;

			try{

				if(fechaMov==null)fechaMov = Fecha.dateToString(new Date());				

				ArrayList tablaRelaciones = null;
				if(divisionUsuario.equals("0")){
					tablaRelaciones = AdminCatFacade.getRelaciones(new Object[] {CvePtoventas,CveVendedor});	
				}else{
					tablaRelaciones = CatalogoFacade.getRelaciones(CvePtoventas,CveVendedor,subdivisionUsuario,divisionUsuario);
				}

				if(tablaRelaciones== null && tablaRelaciones.isEmpty()){
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
				logger.error("modificar cse_error:" + cse.toString());
				return ERROR;
			}catch(Exception e){
				logger.error("modificar e_error:" + e.toString());
				return ERROR;
			}
		return cambios_presentes;		
	}
	/*****************************************************************************************************
	 * obtenerRelaciones
	 * @param cvePtoventas
	 * @param cveVendedor
	 * @param divisionUsuario
	 * @return
	 *****************************************************************************************************/
	public static ArrayList obtenerRelaciones(String cvePtoventas, String cveVendedor, int subdivisionUsuario, int divisionUsuario){
		ArrayList tablaRelaciones = new ArrayList(); 					

		if(cvePtoventas!=null){
			ArrayList relaciones = null;
			if(subdivisionUsuario==0){
				relaciones = AdminCatFacade.getRelaciones(new Object[] {null,cveVendedor});
			}else{
				relaciones = CatalogoFacade.getRelaciones(null,cveVendedor,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
			}
			if(!relaciones.isEmpty()){
				tablaRelaciones.addAll(relaciones);
			}
		}
		
		if(cveVendedor!=null){
			ArrayList relaciones = null;
			if(subdivisionUsuario==0){
				relaciones = AdminCatFacade.getRelaciones(new Object[] {cvePtoventas,null});
			}else{
				relaciones = CatalogoFacade.getRelaciones(cvePtoventas,null,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
			}
			if(!relaciones.isEmpty()){
				tablaRelaciones.addAll(relaciones);
			}
		}
		
		if(tablaRelaciones!= null && !tablaRelaciones.isEmpty()){
			ordenaUnicos(tablaRelaciones);
		}			
		
		return tablaRelaciones ;
	} 
	/*******************************************************************************************************
	 * ordenaUnicos
	 * @param arreglo - ArrayList
	 *******************************************************************************************************/
	public static void ordenaUnicos(ArrayList arreglo){
		Collections.sort(arreglo);
		for(int i=0; i< arreglo.size();i++){
			Object objeto = arreglo.get(i);
			int indice = arreglo.lastIndexOf(objeto);
			if(indice!=i) {
				arreglo.remove(indice);
			} 
		}
	}
	
	
	public static int CAMBIO_PUESTO = 2;
	public static int CAMBIO_SUPERIOR = 1;
	public static int CAMBIO_SUP_CAD = 4;
	public static int CAMBIOS_PERSONAL = 3;
	public static int SIN_CAMBIOS_PERSONAL = 0;
	public static int ERROR_PERSONAL = (-1);
	/**
	 * checkCambiosPersonal
	 * 
	 * @param CveVendedor
	 * @param CveSuperior
	 * @param CveSupCad
	 * @param CvePuesto
	 * @param divisionUsuario
	 * @param fechaMov
	 * @param usuario
	 * @param autoriza
	 * @return
	 * @throws Exception
	 */
	public static int checkCambiosPersonal(String CveVendedor, String CveSuperior, String CveSupCad, String CvePuesto, String subdivisionUsuario,String divisionUsuario, String fechaMov, String usuario, String autoriza) throws Exception{
			int superior_nuevo=0;
			int supCad_nuevo=0;
			int puesto_nuevo=0;
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
				return ERROR_PERSONAL;
			}catch(Exception e){
				logger.error("modificar:checkCambios e_error: " + e.toString());
				return ERROR_PERSONAL;
			}
		return cambios_presentes;		
	}
	/********************************************************************************************************
	 * desasignarPersonal
	 * 
	 * @param cveVendedor
	 * @param division
	 * @param fechaMov
	 * @param usuario
	 * @param autoriza
	 * @return
	 * @throws Exception
	 ************************************************************************************************************/
	public static int desasignarPersonal(String cveVendedor, String subdivision, String division, 
	                              String fechaMov, 	  String usuario, 
	                              String autoriza) throws Exception{
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
					if(checkCambiosPersonal( vendedorCheck , superiorCheck, supCadCheck, "", subdivision, division, fechaMov, usuario, autoriza)==-1)
						cambios_ciclo=ERROR_PERSONAL;
				}	
				if(cambios_ciclo==ERROR_PERSONAL){
					cambios_presentes=ERROR_PERSONAL;
				}else{
					CatalogoFacade.unassignPersonal(cveVendedor, usuario);
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
					if(checkCambiosPersonal( vendedorCheck , superiorCheck, supCadCheck, "", subdivision, division, fechaMov, usuario, autoriza)==-1)
						cambios_ciclo=ERROR_PERSONAL;
				}	
				if(cambios_ciclo==ERROR_PERSONAL){
					cambios_presentes=ERROR_PERSONAL;
				}else{
					CatalogoFacade.unassignSuperiorCadena(cveVendedor, autoriza);
				}
			}
			if(cambios_presentes!= ERROR_PERSONAL && checkCambiosPersonal( cveVendedor, division, "", "", subdivision, division, fechaMov, usuario, autoriza)>-1){
				
				CatalogoFacade.noSuperiorPersonal(cveVendedor, division, autoriza);
				cambios_presentes=CAMBIOS_PERSONAL;
			}else{
				cambios_presentes=ERROR_PERSONAL;
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

	
	
}