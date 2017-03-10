/*
 * Created on Nov 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;

import java.util.ArrayList;
import java.util.Iterator;

import java.lang.Integer;
import java.util.Arrays;

import javax.servlet.ServletOutputStream;
import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.modelo.DirectorDivVO;

import mx.com.iusacell.catalogo.modelo.ArbolVO;

import mx.com.iusacell.catalogo.modelo.UserSessionVO;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

import mx.com.iusacell.catalogo.utilerias.ExcelDoc;
import mx.com.iusacell.catalogo.utilerias.Fecha;


import mx.com.iusacell.catalogo.utilerias.MyFileStructure;

import mx.com.iusacell.catalogo.taglib.Html;

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
public class ReportePtoVentasProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {
	
	protected static final Logger logger = Logger.getLogger(ReportePtoVentasProxy.class);
	
	public ReportePtoVentasProxy() {
	}
	   
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
				
			String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("jsArray") != null) request.getSession().removeAttribute("jsArray"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			 
			try{
				control=request.getParameter("control");
				String nombreVendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
				
				String nomClaveEmpleadoReferenciaBuscar = null;
				
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
	
				ArrayList arbolReporte =null;
				ArrayList tiendaReporte =null;
				ArrayList tiendaDivision = null;
					
				if(subdivisionUsuario==0 && nomClaveEmpleadoReferenciaBuscar == null){
					arbolReporte =AdminCatFacade.getArbolDescendente(nombreVendedorBuscar);
					tiendaReporte =AdminCatFacade.getArbolDescendenteTienda(nombreVendedorBuscar);
					//tiendaDivision = AdminCatFacade.getTiendaVendedor(null, null, nombreVendedorBuscar);
				}else{
					if(new Integer(nombreVendedorBuscar.trim()).intValue() == subdivisionUsuario) {
						arbolReporte =CatalogoFacade.getArbolDescendente(nombreVendedorBuscar,String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
						tiendaReporte =CatalogoFacade.getArbolDescendenteTienda(nombreVendedorBuscar,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
						//tiendaDivision = AdminCatFacade.getTiendaVendedor(null, null, nombreVendedorBuscar);
					} else {
						divisionUsuario = new Integer(nombreVendedorBuscar.trim()).intValue();								
						arbolReporte =CatalogoFacade.getArbolDescendente(nombreVendedorBuscar,String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
						tiendaReporte =CatalogoFacade.getArbolDescendenteTienda(nombreVendedorBuscar,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
						//tiendaDivision = AdminCatFacade.getTiendaVendedor(null, null, nombreVendedorBuscar);
					}
				}
				
				if(arbolReporte==null || arbolReporte.isEmpty()){
					request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
				}else{
					String jsArray = generarArregloDetalle(arbolReporte,tiendaReporte);
					request.getSession().setAttribute("arbolReporte",arbolReporte);
					request.getSession().setAttribute("jsArray",jsArray);
										
					String textJavaScript = generarArbolTiendaVendedorPuestos1(tiendaDivision, arbolReporte,tiendaReporte,"Jerarquia", 0, false);
					request.getSession().setAttribute("textJavaScriptPtoVenta",textJavaScript);	
				}
				
							   
				
			}catch(CatalogoSystemException cse){
				logger.error(cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error(e.toString());
				return mapping.findForward("error");
			}
			
			return mapping.findForward("home");
	}

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ReportePtoVentasForm forma = (ReportePtoVentasForm) form;

			return mapping.findForward("home");
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ReportePtoVentasForm forma = (ReportePtoVentasForm) form;

			return mapping.findForward("home");
	}

	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ReportePtoVentasForm forma = (ReportePtoVentasForm) form;

			return mapping.findForward("home");
	}

	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("jsArray") != null) request.getSession().removeAttribute("jsArray"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			 
			try{
				String divisionBuscar = (request.getParameter("pcCveDivisionSeek").equals(""))? null:request.getParameter("pcCveDivisionSeek");
				
				String divisionBuscarDesc = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
				
				
				if(divisionBuscar== null || divisionBuscar.equals("")) {
				
					// Si el usuario no proporciona una division para buscar entonces se dedica a buscar al vendedor
					
					return verLista(mapping, form, request, response);
				}
				else{
					((ReportePtoVentasForm) form).reset(mapping,request);
					((ReportePtoVentasForm) form).setPcCveDivisionSeek(divisionBuscar);
					
					// Buscamos el Nodo inicial		
					
					DirectorDivVO director  = CatalogoFacade.getArbolDirectorDiv(divisionBuscar);
						
					if(director != null){
						//buscamos a que division pertenece el usuario
						int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
						
						ArrayList arbolReporte = null;
						ArrayList tiendaReporte = null;
						ArrayList tiendaDivision = null;
						
						
						//Corremos el Query para obtener el ärbol completo y las tiendas de cada usuario que las tenga
						if(subdivisionUsuario==0){
							arbolReporte = AdminCatFacade.getArbolDescendentePuestos(String.valueOf(director.getPcCveVendedor()),String.valueOf(divisionBuscar));
							tiendaReporte = AdminCatFacade.getArbolDescendentePtoVenta(String.valueOf(director.getPcCveVendedor()), String.valueOf(divisionBuscar));
							//tiendaDivision = AdminCatFacade.getTiendaVendedor(String.valueOf(divisionBuscar), String.valueOf(divisionBuscar), null);
						}else{
							arbolReporte = CatalogoFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
							tiendaReporte = AdminCatFacade.getArbolDescendentePtoVenta(String.valueOf(director.getPcCveVendedor()), String.valueOf(divisionBuscar));
							//tiendaDivision = AdminCatFacade.getTiendaVendedor(String.valueOf(divisionBuscar), String.valueOf(divisionBuscar), null);	
						} 
												
						if (arbolReporte== null || arbolReporte.isEmpty()){
							//No hay datos en el árbols asi que se aborta
							request.getSession().setAttribute("noData","El árbol resultante esta vacío");
						}else{
							// Hay datos asi que  contruimos los arreglos para el Javascript 
							String jsArray = generarArregloDetalle(arbolReporte, tiendaReporte);
							request.getSession().setAttribute("arbolReporte",arbolReporte);
							request.getSession().setAttribute("jsArray",jsArray);
						}
											
						String textJavaScript = generarArbolTiendaVendedorPuestos1(tiendaDivision, arbolReporte,tiendaReporte,director.getPcDescDiv(), director.getPcCveDiv(),true);
												
						request.getSession().setAttribute("textJavaScriptPtoVenta",textJavaScript);
					}else{
						// No se encontro el usuario incial para la division asi que abortamos el árbol
						request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
						//String textJavaScript = generarArbolTienda(new ArrayList(),new ArrayList(),divisionBuscarDesc, true);
						String textJavaScript = generarArbolTiendaVendedorPuestos(new ArrayList(), new ArrayList(), new ArrayList(),divisionBuscarDesc, 0, true);
						request.getSession().setAttribute("textJavaScriptPtoVenta",textJavaScript);
					}
				}  
			}catch(CatalogoSystemException cse){
				logger.error("Error en consultar CatalogoSystemException: " + cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error en Consultar Exception: " + e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}



	/**
	 * @param arbolReporte - ArrayList
	 * @return String
	 *
	 * from ArrayList arbolReporte
	 * 
	 */

	public String generarArregloDetalle(ArrayList arbolReporte,ArrayList tiendaReporte){
		StringBuffer html = new StringBuffer();
		html.append("\n\tdetalle = new Array(");
		if(arbolReporte.isEmpty()){
			html.append(Html.addComillas("") + ",");
			html.append(Html.addComillas("") + ",");
			html.append(Html.addComillas(""));
		}else{
			Iterator ramas = arbolReporte.iterator();
			while(ramas.hasNext()){
				ArbolVO elemento = (ArbolVO) ramas.next();
				
				html.append("\n\tnew Array(");
				html.append(Html.addComillas(String.valueOf(elemento.getPcCveVendedor()) + "-" + elemento.getPcCvePtoventas()));
				html.append(",new Array(");
				html.append(Html.addComillas(elemento.getPcClaveCompleta())+ ",");
				html.append(Html.addComillas(elemento.getPcNombreVendedor())+ ",");
				html.append(Html.addComillas(elemento.getPcCvePuesto())+ ",");
				html.append(Html.addComillas(elemento.getPcDescPuesto())+ ",");
				html.append(Html.addComillas(elemento.getPcFechaAltaStr())+ ",");
				html.append(Html.addComillas(elemento.getPcFechaBajaStr())+ ",");
				html.append(Html.addComillas(elemento.getPcDigVerif())+ ",");
				html.append(Html.addComillas(elemento.getPcCiudad())+ ",");
				html.append(Html.addComillas(elemento.getPcEstado())+ ",");
				html.append(Html.addComillas(elemento.getPcDireccion())+ ",");
				html.append(Html.addComillas(elemento.getPcColonia())+ ",");
				html.append(Html.addComillas(elemento.getPcCp())+ ",");
				html.append(Html.addComillas(elemento.getPcTel())+ ",");
				html.append(Html.addComillas(elemento.getPcCveEmpRef()) + ",");
				html.append(Html.addComillas(elemento.getPcFax()));
				html.append("),new Array(");
				html.append(Html.addComillas(elemento.getPcCvePtoventas())+ ",");
				html.append(Html.addComillas(elemento.getPcNomPtoventas())+ ",");
				html.append(Html.addComillas(elemento.getPcCveCanal())+ ",");
				html.append(Html.addComillas(elemento.getPcDescCanal())+ ",");
				html.append(Html.addComillas(elemento.getPcCveRegion())+ ",");
				html.append(Html.addComillas(elemento.getPcDescRegion())+ ",");
				html.append(Html.addComillas(elemento.getPcCveTpCanal())+ ",");
				html.append(Html.addComillas(elemento.getPcDescTpCanal()));
				html.append("))");
				if(ramas.hasNext()) html.append(",");			
			}
		}
		html.append(");");
		
		html.append("\n\tdetalleTienda = new Array(");
		if(tiendaReporte.isEmpty()){
			html.append(Html.addComillas("") + ",");
			html.append(Html.addComillas(""));
		}else{
			Iterator ramas = tiendaReporte.iterator();
			boolean adicionales = false;
			while(ramas.hasNext()){
				ArbolVO elemento = (ArbolVO) ramas.next();
				
				if(elemento.getPcCvePtoventas()!= null && !elemento.getPcCvePtoventas().equals("")){
					html.append("\n\tnew Array(");
					//html.append(Html.addComillas(String.valueOf(elemento.getPcCveVendedor()) + "-" + elemento.getPcCvePtoventas()));
					html.append(Html.addComillas(String.valueOf(elemento.getPcCvePtoventas()) + "-" + 0));
					html.append(",new Array(");
					html.append(Html.addComillas(elemento.getPcCvePtoventas())+ ",");
					html.append(Html.addComillas(elemento.getPcNomPtoventas())+ ",");
					html.append(Html.addComillas(elemento.getPcCveCanal())+ ",");
					html.append(Html.addComillas(elemento.getPcDescCanal())+ ",");
					html.append(Html.addComillas(elemento.getPcCveRegion())+ ",");
					html.append(Html.addComillas(elemento.getPcDescRegion())+ ",");
					html.append(Html.addComillas(elemento.getPcCveTpCanal())+ ",");
					html.append(Html.addComillas(elemento.getPcDescTpCanal()));
					html.append("))");
					if(ramas.hasNext()) {
						adicionales = true;
						html.append(",");
					}else{ 	
						adicionales = false;
					}
				}		
			}
			if (adicionales==true) html.append("new Array('','')");
		}
		html.append(");");
		
		return html.toString();
	}

	    

	    
		//////reporte Reporte Excel
		public ActionForward excelPuntoVenta(ActionMapping mapping, 
									   ActionForm form,
									   HttpServletRequest request, 
									   HttpServletResponse response) 
			{
		
				ArrayList excelDocPersonal = new ArrayList();
				ArrayList excelDocTiendas = new ArrayList();
				try{
					if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
					if(request.getSession().getAttribute("jsArray") != null) request.getSession().removeAttribute("jsArray"); 
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
					if(request.getSession().getAttribute("liga") != null) request.getSession().removeAttribute("liga");
					
					String divisionBuscar = (request.getParameter("pcCveDivisionSeek").equals(""))? null:request.getParameter("pcCveDivisionSeek");
					String divisionBuscarDesc = (request.getParameter("pcDescDivisionSeek").equals(""))? null:request.getParameter("pcDescDivisionSeek");

					((ReportePtoVentasForm) form).reset(mapping,request);
					((ReportePtoVentasForm) form).setPcCveDivisionSeek(divisionBuscar);
					
					// Buscamos el Nodo inicial		
					DirectorDivVO director  = CatalogoFacade.getArbolDirectorDiv(divisionBuscar);
					
					
					if(director != null){
						//buscamos a que division pertenece el usuario
						int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						
						ArrayList arbolReporte = null;
						ArrayList tiendaReporte = null;
						ArrayList tiendaDivision = null;
						
						//Corremos el Query para obtener el ärbol completo y las tiendas de cada usuario que las tenga
						if(subdivisionUsuario==0){
							
							arbolReporte = AdminCatFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),String.valueOf(divisionBuscar));
							tiendaReporte = AdminCatFacade.getArbolDescendentePtoVenta(String.valueOf(director.getPcCveVendedor()), String.valueOf(divisionBuscar));
							//tiendaReporte = CatalogoFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
							tiendaDivision = AdminCatFacade.getTiendaVendedor(String.valueOf(divisionBuscar), String.valueOf(divisionBuscar), null);
						}else{
							
							arbolReporte = CatalogoFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
							//tiendaReporte = CatalogoFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
							tiendaReporte = AdminCatFacade.getArbolDescendentePtoVenta(String.valueOf(director.getPcCveVendedor()), String.valueOf(divisionBuscar));
							tiendaDivision = AdminCatFacade.getTiendaVendedor(String.valueOf(divisionBuscar), String.valueOf(divisionBuscar), null);	
						} 
												
						if (arbolReporte== null || arbolReporte.isEmpty()){
							//No hay datos en el árbols asi que se aborta
							request.getSession().setAttribute("noData","No se selecciono la división");
						}else{
							// Hay datos asi que  contruimos los arreglos para el Javascript 
							excelDocPersonal = generarReportePersonalPtoVentaExcel(tiendaDivision, arbolReporte, tiendaReporte,director.getPcDescDiv());
							//excelDocTiendas = generarReporteTiendasExcel(tiendaDivision, arbolReporte, tiendaReporte,divisionBuscarDesc);
						}
						//Contruimos un árbol con o sin datos
					}

					ExcelDoc doc= new ExcelDoc();
			
					if(excelDocPersonal != null && !excelDocPersonal.isEmpty()) {
				
						String[] encabezados = {"División","Clave Punto Venta", "Nombre Punto Venta", "Clave","Nombre","Puesto","Clave Superior","Superior",
												"Puesto Superior","Fecha Alta","Tipo Canal", "Nomina", "Canal"};
						String[] metodos = {"pcDescDiv","pcCvePtoventas", "pcNomPtoventas","pcClaveCompleta","pcNombreVendedor","pcDescPuesto",
											"pcCveSuperior","pcNombreSuperior","pcDescPuestoSup","pcFechaAltaStr",
											"pcDescTpCanal", "pcCveEmpRef", "pcDescCanal"};

						ArrayList encabezado = new ArrayList(Arrays.asList(encabezados));
						ArrayList metodo = new ArrayList(Arrays.asList(metodos));
						doc.obtieneMetodos("personal", encabezado, metodo, excelDocPersonal);
					}
					

					if(doc.getNumHojas()>0){
				
						String ruta1 = request.getSession().getServletContext().getRealPath("") + "/excel/";
						String ruta = request.getContextPath() + "/excel/";
						String identificador = Fecha.dateToStringCurrent(Fecha.getHoy());
						String sessionid = request.getSession().getId();
						//request.getSession().setAttribute("ligaPtoVenta", ruta + sessionid + identificador + ".xls");
				
						MyFileStructure mf = new MyFileStructure();
				
						mf.setDirname(ruta1);
						mf.build();
						mf.list();
						//escribe a disco duro
						/*
						FileOutputStream fileOut = new FileOutputStream(ruta1 + sessionid + identificador + ".xls");
						doc.escribirDisco(fileOut);//escribe un archivo al disco duro
						fileOut.close();*/
						//escribe en el navegador
						
						  response.setContentType("application/vnd.ms-excel");
						  ServletOutputStream out = response.getOutputStream();
						  doc.escribirArchivo(out);
						  out.close();
					}else{
						request.getSession().setAttribute("noData","No se generó el archivo de Reporte");
					}
				}catch(Exception e){
					logger.error("Excel error " + e);
					return mapping.findForward("error");
				}
				
				if(excelDocPersonal == null || excelDocPersonal.size() == 0)
					return mapping.findForward("home");
				else
					return null;
			}
		/////termina Reporte Excel
	    
		public ArrayList generarReportePersonalPtoVentaExcel(ArrayList tiendaDivision, ArrayList arbolReporte,ArrayList tiendaReporte, String division){
			return null;
		}
			
			
			/////////reporte por punto de venta y puestos
	public String generarArbolTiendaVendedorPuestos(ArrayList tiendaDivision, ArrayList arbolReporte,ArrayList tiendaReporte,String divisionDesc, int cveDivision, boolean division){
				
		StringBuffer textJavaScript = new StringBuffer();
			 
		String folder = "";
		String documento = "";
		String carpeta = "";
		String iconoCerrado = "";
		String iconoAbierto = "";
		String iconoDoc = "";
		String iconoDocCerrado = "";
		String divisionName = divisionDesc;
		int ancla = cveDivision;
		
		//variables para agruparlos por puestos
		int cveSuperiorAnterior = 0;
		int cveSuperiorActual = 0;
		int cvePuestoAnterior = 0;
		int cvePuestoActual = 0;
		boolean primeravez = false;
		String sCarpetaPuesto = "";
		
		/* genero un arrayList de Objetos nuevo */
				ArrayList arbolReporte1 = arbolReporte;
				ArrayList tiendaReporte1 = tiendaReporte;
		
				ArrayList probandoReporte = new ArrayList();
		
				Iterator ramasReporte = arbolReporte1.iterator();
		
				while(ramasReporte.hasNext()){
					boolean existe = false;
					ArbolVO probando = (ArbolVO)ramasReporte.next();
					Iterator ramasTienda = tiendaReporte1.iterator();
					while (ramasTienda.hasNext()) {
						ArbolVO tiendaP = (ArbolVO)ramasTienda.next();
				
						if(tiendaP.getPcCvePtoventas()!= null && !tiendaP.getPcCvePtoventas().equals("")){
							if(probando.getPcCveVendedor() == tiendaP.getPcCveVendedor()) {
								tiendaP.setLevel(probando.getLevel());
								tiendaP.setNivel(probando.getNivel());
								tiendaP.setPcNombreVendedor(probando.getPcNombreVendedor());
								tiendaP.setPcDigVerif(probando.getPcDigVerif());
								tiendaP.setPcCiudad(probando.getPcCiudad());
								tiendaP.setPcEstado(probando.getPcEstado());
								tiendaP.setPcDireccion(probando.getPcDireccion());
								tiendaP.setPcColonia(probando.getPcColonia());
								tiendaP.setPcCveDiv(probando.getPcCveDiv());
								tiendaP.setPcCvePuesto(probando.getPcCvePuesto());
								tiendaP.setPcCveEmpRef(probando.getPcCveEmpRef());
								tiendaP.setPcDescPuesto(probando.getPcDescPuesto());
								tiendaP.setPcCveSuperior(probando.getPcCveSuperior());
								tiendaP.setPcNombreSuperior(probando.getPcNombreSuperior());
								tiendaP.setPcDescPuestoSup(probando.getPcDescPuestoSup());
								tiendaP.setPcDescDiv(probando.getPcDescDiv());
									
								probandoReporte.add(tiendaP);
								ramasTienda.remove();
								existe = true;
							}
						} 
					}
					if(!existe) {
						probando.setPcNomPtoventas("No tiene tienda asignada");
						probandoReporte.add(probando);
					}
				}
		
				logger.info("tamaño " + probandoReporte.size());
				//imprimo datos
				if(!probandoReporte.isEmpty()) {
					Iterator IReporte = probandoReporte.iterator();
					while(IReporte.hasNext()){
						ArbolVO reporte = (ArbolVO)IReporte.next();
						logger.info("nivel " + reporte.getNivel() + " vendedor " + reporte.getPcCveVendedor() + " puesto " + reporte.getPcCvePuesto() + " cve tienda " + reporte.getPcCvePtoventas());
					}
				}
				
				arbolReporte = new ArrayList();
				arbolReporte = probandoReporte;
				
		if(arbolReporte.isEmpty()){
			folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + divisionName + "</i>\', \'\')"; 								
			iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
			iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
			textJavaScript.append("\n\t" + folder);
			textJavaScript.append("\n\t" + iconoCerrado);
			textJavaScript.append("\n\t" + iconoAbierto);
		} else {
			//busco si tiene nivel 1
			boolean bandera = false;
														
			Iterator ramas = arbolReporte.iterator();
			String nodoActual = "";
			//int vendedorActual =0;	
			while(ramas.hasNext()){
				ArbolVO elemento = (ArbolVO) ramas.next();
				if(elemento.getNivel()==1) {
					if(division){ //La bandera division nos indica que estamos generando un árbol de division a partir del vendedor raiz
						folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + elemento.getPcNombreVendedor() + "</i>\', \'\')"; 								
						iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
						iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
						nodoActual = "foldersTree";
						primeravez = true;
					}else{
						String sPuesto = elemento.getPcDescPuesto();
						if(sPuesto.indexOf(" ") > 0)
							sPuesto = sPuesto.replace(' ','_');
						if(sPuesto.indexOf(".") > 0)	
							sPuesto = sPuesto.replace('.','_');
						
							
						folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + divisionName + "</i>\', \'\')"; 								
						iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
						iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);

						String nodo = "foldersTree";
						int iPuestoSuperior = 0;
						if(elemento.getPcCveSuperior() < 0)
							iPuestoSuperior = elemento.getPcCveSuperior() * -1;
						else
							iPuestoSuperior = elemento.getPcCveSuperior(); 
							
						sCarpetaPuesto = "aux"+sPuesto+ iPuestoSuperior; 
						folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
						iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
						iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
						 
						carpeta = "aux" + elemento.getPcCveVendedor(); //carpeta = "aux" + elemento.getNivel();  
						folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+ "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
						nodoActual = carpeta;
						cvePuestoAnterior = elemento.getPcCvePuesto();
						cveSuperiorAnterior = elemento.getPcCveSuperior();
												
						ancla = elemento.getPcCveVendedor();
												
					}
			} else {
				
				String nodo = (elemento.getPcCveSuperior() ==  cveDivision) ? "foldersTree":"aux" + elemento.getPcCveSuperior();
				
				String sPuesto = elemento.getPcDescPuesto();
				if(sPuesto.indexOf(" ") > 0)
					sPuesto = sPuesto.replace(' ','_');
				if(sPuesto.indexOf(".") > 0)	
					sPuesto = sPuesto.replace('.','_');
				int iPuestoSuperior = 0;
				if(elemento.getPcCveSuperior() < 0)
					iPuestoSuperior = elemento.getPcCveSuperior() * -1;
				else
					iPuestoSuperior = elemento.getPcCveSuperior(); 
				
														
				if(primeravez) {
					sCarpetaPuesto = "aux"+sPuesto+iPuestoSuperior; 
					folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
					iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
					iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
					textJavaScript.append("\n\t" + folder);
					textJavaScript.append("\n\t" + iconoCerrado);
					textJavaScript.append("\n\t" + iconoAbierto);
					
					//vendedor
					carpeta = "aux" + elemento.getPcCveVendedor();   
					folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+  "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
					iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
					iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
					textJavaScript.append("\n\t" + folder);
					textJavaScript.append("\n\t" + iconoCerrado);
					textJavaScript.append("\n\t" + iconoAbierto);
					
					primeravez = false;
					cvePuestoAnterior = elemento.getPcCvePuesto();
					cveSuperiorAnterior = elemento.getPcCveSuperior();
					nodoActual = sCarpetaPuesto;
					
				} else {
					
					if(cveSuperiorAnterior == elemento.getPcCveSuperior() && cvePuestoAnterior == elemento.getPcCvePuesto()) {
						carpeta = "aux" + elemento.getPcCveVendedor();
												
						//vendedor
						folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+  "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
							
						cvePuestoAnterior = elemento.getPcCvePuesto();
						cveSuperiorAnterior = elemento.getPcCveSuperior(); 
						carpeta = "aux"+sPuesto+iPuestoSuperior;
						
					} else {
						sCarpetaPuesto = "aux"+sPuesto+iPuestoSuperior; 
						folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
						iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
						iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto); 
						
						//vendedor
						carpeta = "aux" + elemento.getPcCveVendedor();   
						folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+ "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
											
						cvePuestoAnterior = elemento.getPcCvePuesto();
						cveSuperiorAnterior = elemento.getPcCveSuperior();
						
					}
					nodoActual = sCarpetaPuesto;
				}
				
				
				
			}
		}
					
	}	
	/*if(!tiendaReporte.isEmpty()){
			Iterator ramas = tiendaReporte.iterator();
			String nodoActual = "";
			while(ramas.hasNext()){
				ArbolVO tienda = (ArbolVO) ramas.next();
				if(tienda.getPcCvePtoventas()!= null && !tienda.getPcCvePtoventas().equals("")){
					nodoActual = "aux" + tienda.getPcCveVendedor(); 
					documento = "docAux = insFld(" + nodoActual + ", gFld('" + tienda.getPcNomPtoventas() + "','javascript:updateTienda(\"" + tienda.getPcCveVendedor() + "-" + tienda.getPcCvePtoventas() + "\")'))";
					iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
					iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
					textJavaScript.append("\n\t" + documento);
					textJavaScript.append("\n\t" + iconoDoc);
					textJavaScript.append("\n\t" + iconoDocCerrado);
				}
			}
		}*/
			
		return textJavaScript.toString(); 
	
	} //fin de metodo

	public String generarArbolTiendaVendedorPuestos1(ArrayList tiendaDivision, ArrayList arbolReporte,ArrayList tiendaReporte,String divisionDesc, int cveDivision, boolean division){
		StringBuffer textJavaScript = new StringBuffer();
			 
		String folder = "";
		String documento = "";
		String carpeta = "";
		String iconoCerrado = "";
		String iconoAbierto = "";
		String iconoDoc = "";
		String iconoDocCerrado = "";
		String divisionName = divisionDesc;
		int ancla = cveDivision;
		
		//variables para agruparlos por puestos
		int cveSuperiorAnterior = 0;
		int cveSuperiorActual = 0;
		int cvePuestoAnterior = 0;
		int cvePuestoActual = 0;
		int cveVendedorAnterior = 0;
		int cveVendedorActual = 0;
				
		boolean primeravez = false;
		String sCarpetaPuesto = "";
		String sCarpetaPuestoTienda = "";
		String tiendaPuesto[] = null;	
		String puestoTienda[] = null;
	
		/* genero un arrayList de Objetos nuevo */
		ArrayList arbolReporte1 = arbolReporte;
		ArrayList tiendaReporte1 = tiendaReporte;
	
		ArrayList probandoReporte = new ArrayList();
	
		Iterator ramasReporte = arbolReporte1.iterator();
		while(ramasReporte.hasNext()){
			boolean existe = false;
			ArbolVO probando = (ArbolVO)ramasReporte.next();
			Iterator ramasTienda = tiendaReporte1.iterator();
			while (ramasTienda.hasNext()) {
				ArbolVO tiendaP = (ArbolVO)ramasTienda.next();
				if(tiendaP.getPcCvePtoventas()!= null && !tiendaP.getPcCvePtoventas().equals("")){
					if(probando.getPcCveVendedor() == tiendaP.getPcCveVendedor()) {
						tiendaP.setLevel(probando.getLevel());
						tiendaP.setNivel(probando.getNivel());
						tiendaP.setPcNombreVendedor(probando.getPcNombreVendedor());
						tiendaP.setPcDigVerif(probando.getPcDigVerif());
						tiendaP.setPcCiudad(probando.getPcCiudad());
						tiendaP.setPcEstado(probando.getPcEstado());
						tiendaP.setPcDireccion(probando.getPcDireccion());
						tiendaP.setPcColonia(probando.getPcColonia());
						tiendaP.setPcCveDiv(probando.getPcCveDiv());
						tiendaP.setPcCvePuesto(probando.getPcCvePuesto());
						tiendaP.setPcCveEmpRef(probando.getPcCveEmpRef());
						tiendaP.setPcDescPuesto(probando.getPcDescPuesto());
						tiendaP.setPcCveSuperior(probando.getPcCveSuperior());
						tiendaP.setPcNombreSuperior(probando.getPcNombreSuperior());
						tiendaP.setPcDescPuestoSup(probando.getPcDescPuestoSup());
						tiendaP.setPcDescDiv(probando.getPcDescDiv());
						probandoReporte.add(tiendaP);
						ramasTienda.remove();
						existe = true;
					}
				} 
			}
			if(!existe) {
				probando.setPcNomPtoventas("");
				probandoReporte.add(probando);
			}
		}
		
		//imprimo datos
		/*if(!probandoReporte.isEmpty()) {
			Iterator IReporte = probandoReporte.iterator();
			while(IReporte.hasNext()){
				ArbolVO reporte = (ArbolVO)IReporte.next();
			}
		}*/
		/* termino de generar ArrayList */
		
			if(probandoReporte.isEmpty()){
				folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + divisionName + "</i>\', \'\')"; 								
				iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
				iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
				textJavaScript.append("\n\t" + folder);
				textJavaScript.append("\n\t" + iconoCerrado);
				textJavaScript.append("\n\t" + iconoAbierto);
			} else {
				boolean bandera = false;
				boolean banderaExiste=false;//bandera que nos indica que si en la primera vez hay tienda o no 											
				Iterator ramas = probandoReporte.iterator();
				String nodoActual = "";
			
				tiendaPuesto = new String[probandoReporte.size()+ 1];
				puestoTienda = new String[probandoReporte.size()+ 1];
				int iContador = 0;	
				int iContadorPuestos = 0;
				boolean encontre = false;
				String nombreTienda;
				while(ramas.hasNext()){
					nombreTienda = "";
					encontre = false;
					ArbolVO elemento = (ArbolVO) ramas.next();
					if(elemento.getNivel()==1) {
						if(division){//La bandera division nos indica que estamos generando un árbol de division a partir del vendedor raiz
							folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + elemento.getPcNombreVendedor() + "</i>\', \'\')"; 								
							iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
							iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
							nodoActual = "foldersTree";
							primeravez = true;//cuando se genera un reporte a nivel division
						}else{
							String sPuesto = elemento.getPcDescPuesto();
							if(sPuesto.indexOf(" ") > 0)
								sPuesto = sPuesto.replace(' ','_');
							if(sPuesto.indexOf(".") > 0)	
								sPuesto = sPuesto.replace('.','_');
						
							String nodo = "foldersTree";
							/*if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
								nombreTienda = "<FONT face=arial  color=red>  Tienda (" + elemento.getPcNomPtoventas() + ") </FONT";
							}*/
															
							int iPuestoSuperior = 0;
							if(elemento.getPcCveSuperior() < 0)
								iPuestoSuperior = elemento.getPcCveSuperior() * -1;
							else
								iPuestoSuperior = elemento.getPcCveSuperior(); 
							
							sCarpetaPuesto = "aux"+sPuesto+ iPuestoSuperior;
											
							if(cveVendedorAnterior == elemento.getPcCveVendedor())
								encontre = true;
												
							if(!encontre) {	
								folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + divisionName + "</i>\', \'\')"; 								
								iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
								iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
								textJavaScript.append("\n\t" + folder);
								textJavaScript.append("\n\t" + iconoCerrado);
								textJavaScript.append("\n\t" + iconoAbierto);
													
								folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ nombreTienda + "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
								iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
								iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
								textJavaScript.append("\n\t" + folder);
								textJavaScript.append("\n\t" + iconoCerrado);
								textJavaScript.append("\n\t" + iconoAbierto);
															
								carpeta = "aux" + sPuesto + elemento.getPcCveVendedor();
								folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+ "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
								iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
								iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
								textJavaScript.append("\n\t" + folder);
								textJavaScript.append("\n\t" + iconoCerrado);
								textJavaScript.append("\n\t" + iconoAbierto);
							}
												
							if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
								String sTienda = elemento.getPcCvePtoventas();
								if(sTienda.indexOf("-") > 0)
									sTienda = sTienda.replace('-','_');
								
								sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveVendedor()+sTienda; 
								folder = sCarpetaPuestoTienda + " =  insFld("+ "aux" + sPuesto + elemento.getPcCveVendedor() + ", gFld('" + elemento.getPcNomPtoventas()+ "', 'javascript:updateTable(\"" + elemento.getPcCvePtoventas() +  "-" + "0" + "\")'))";
								iconoAbierto = sCarpetaPuestoTienda + ".iconSrc = ICONPATH + 'tienda.gif'";
								iconoCerrado = sCarpetaPuestoTienda + ".iconSrcClosed = ICONPATH + 'tienda.gif'";
								textJavaScript.append("\n\t" + folder);
								textJavaScript.append("\n\t" + iconoCerrado);
								textJavaScript.append("\n\t" + iconoAbierto);
								tiendaPuesto[iContador] = sCarpetaPuestoTienda;
								iContador++;
						
								puestoTienda[iContadorPuestos] = "aux"+sPuesto+elemento.getPcCveVendedor()+sTienda;
								iContadorPuestos++;
							} else {
								puestoTienda[iContadorPuestos] = "aux"+sPuesto+elemento.getPcCveVendedor();
								iContadorPuestos++;
								sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveVendedor(); 
								tiendaPuesto[iContador] = sCarpetaPuestoTienda;
								iContador++;
							}
					
							nodoActual = carpeta;
							cvePuestoAnterior = elemento.getPcCvePuesto();
							cveSuperiorAnterior = elemento.getPcCveSuperior();
							cveVendedorAnterior = elemento.getPcCveVendedor();
						}
				} else {
					boolean vendedorAnterior = false;
					if(cveVendedorAnterior == elemento.getPcCveVendedor())
						vendedorAnterior = true;
						
					String sPuesto = elemento.getPcDescPuesto();
					String sPuestoSuperior = elemento.getPcDescPuestoSup();
					
					if(sPuesto.indexOf(" ") > 0)
						sPuesto = sPuesto.replace(' ','_');
					if(sPuesto.indexOf(".") > 0)	
						sPuesto = sPuesto.replace('.','_');
					
					if(sPuestoSuperior.indexOf(" ") > 0)
						sPuestoSuperior = sPuestoSuperior.replace(' ','_');
					if(sPuestoSuperior.indexOf(".") > 0)	
						sPuestoSuperior = sPuestoSuperior.replace('.','_');
											
					int iPuestoSuperior = 0;
					if(elemento.getPcCveSuperior() < 0)
						iPuestoSuperior = elemento.getPcCveSuperior() * -1;
					else
						iPuestoSuperior = elemento.getPcCveSuperior(); 
					
					String nodo = (elemento.getPcCveSuperior() ==  cveDivision) ? "foldersTree":"aux" + sPuestoSuperior + elemento.getPcCveSuperior();
						
					String sTienda="";
					if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
						sTienda = elemento.getPcCvePtoventas();
						if(sTienda.indexOf("-") > 0)
							sTienda = sTienda.replace('-','_');
						//nombreTienda = "<FONT face=arial color=red>  Tienda (" + elemento.getPcNomPtoventas() + ")<FONT>";
					}
			
					if(primeravez) {
						
						if(!vendedorAnterior) {
							sCarpetaPuesto = "aux"+sPuesto+iPuestoSuperior; 
							folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ nombreTienda + "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
							iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
							iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
						
							//vendedor
							carpeta = "aux" + sPuesto + elemento.getPcCveVendedor();
							folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+ "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
							iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
							iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
						}					
						primeravez = false;
						
						cvePuestoAnterior = elemento.getPcCvePuesto();
						cveSuperiorAnterior = elemento.getPcCveSuperior();
						nodoActual = sCarpetaPuesto;
					
						// agregado para tienda
						if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
						
							if(sTienda.indexOf("-") > 0)
								sTienda = sTienda.replace('-','_');

							sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveVendedor()+sTienda; 
							folder = sCarpetaPuestoTienda + " =  insFld("+ "aux" + sPuesto + elemento.getPcCveVendedor() + ", gFld('" + elemento.getPcNomPtoventas()+ "', 'javascript:updateTable(\"" + elemento.getPcCvePtoventas() +  "-" + "0" + "\")'))";
							iconoAbierto = sCarpetaPuestoTienda + ".iconSrc = ICONPATH + 'tienda.gif'";
							iconoCerrado = sCarpetaPuestoTienda + ".iconSrcClosed = ICONPATH + 'tienda.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
							tiendaPuesto[iContador] = sCarpetaPuestoTienda;
							iContador++;
						} else {
							sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveVendedor(); 
							tiendaPuesto[iContador] = sCarpetaPuestoTienda;
							iContador++;
						}
				} else {
					String puesto = null;
					boolean bEncontreTienda=false;
					
						if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
							String sTienda1 = "aux" + sPuestoSuperior + elemento.getPcCveSuperior() + sTienda;
							for(int i=0; i < iContador;i++) { //busco si la tienda ya esta dado de alta en el arreglo
								if(tiendaPuesto[i].equals(sTienda1)) {
									nodo = tiendaPuesto[i];
									bEncontreTienda = true;
									break;
								}
							}
					
							puesto = "aux"+sPuesto+elemento.getPcCveSuperior() + sTienda;	
					
							for(int j=0; j < iContadorPuestos; j++) {
								if(puestoTienda[j].equals(puesto)) {
									encontre = true;
									break;
								}
							}		
							
						} else {
								String sTienda1 = "aux" + sPuestoSuperior + elemento.getPcCveSuperior();
								for(int i=0; i < iContador;i++) { //busco si la tienda ya esta dado de alta en el arreglo
									if(tiendaPuesto[i].equals(sTienda1)) {
										nodo = tiendaPuesto[i];
										break;
									}
								}
		
								puesto = "aux"+sPuesto+elemento.getPcCveSuperior();	
	
								for(int j=0; j < iContadorPuestos; j++) {
									if(puestoTienda[j].equals(puesto)) {
										encontre = true;
										break;
									}
								}	
							}
							
						if(vendedorAnterior) {   //agregado 29/12/2005
							if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
									sTienda = elemento.getPcCvePtoventas();
									if(sTienda.indexOf("-") > 0)
										sTienda = sTienda.replace('-','_');
		
									sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveSuperior()+sTienda;
									folder = sCarpetaPuestoTienda + " =  insFld("+ "aux" + sPuesto + elemento.getPcCveVendedor() + ", gFld('" + elemento.getPcNomPtoventas()+ "', 'javascript:updateTable(\"" + elemento.getPcCvePtoventas() +  "-" + "0" + "\")'))";
									iconoAbierto = sCarpetaPuestoTienda + ".iconSrc = ICONPATH + 'tienda.gif'";
									iconoCerrado = sCarpetaPuestoTienda + ".iconSrcClosed = ICONPATH + 'tienda.gif'";
									textJavaScript.append("\n\t" + folder);
									textJavaScript.append("\n\t" + iconoCerrado);
									textJavaScript.append("\n\t" + iconoAbierto);
									tiendaPuesto[iContador] = sCarpetaPuestoTienda;
									tiendaPuesto[iContador] = "aux"+sPuesto+ elemento.getPcCveSuperior()+sTienda;;
									iContador++;
								} else {
									sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveSuperior();
									tiendaPuesto[iContador] = sCarpetaPuestoTienda;
									iContador++;
								}			
						}//agregado 29/12/2005
						else if(cveSuperiorAnterior == elemento.getPcCveSuperior() && cvePuestoAnterior == elemento.getPcCvePuesto()) {
							sCarpetaPuesto = "aux"+sPuesto+iPuestoSuperior+sTienda;
											
							if(!encontre) {
								folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ nombreTienda + "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
								iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
								iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
								textJavaScript.append("\n\t" + folder);
								textJavaScript.append("\n\t" + iconoCerrado);
								textJavaScript.append("\n\t" + iconoAbierto);
							} 
							
							//	vendedor
							carpeta = "aux" + sPuesto + elemento.getPcCveVendedor();
							folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+  "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
							iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
							iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
							
							cvePuestoAnterior = elemento.getPcCvePuesto();
							cveSuperiorAnterior = elemento.getPcCveSuperior(); 
							carpeta = "aux"+sPuesto+iPuestoSuperior;
							
												
						} else {
							sCarpetaPuesto = "aux"+sPuesto+iPuestoSuperior+sTienda;
								
							if(!encontre) {
								folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ nombreTienda + "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
								iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
								iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
								textJavaScript.append("\n\t" + folder);
								textJavaScript.append("\n\t" + iconoCerrado);
								textJavaScript.append("\n\t" + iconoAbierto);
							}
							//vendedor 
							carpeta = "aux" + sPuesto + elemento.getPcCveVendedor();   
							folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+ "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
							iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
							iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
							
							cvePuestoAnterior = elemento.getPcCvePuesto();
							cveSuperiorAnterior = elemento.getPcCveSuperior();
							
						}
						nodoActual = sCarpetaPuesto;
					}
				     
				        
					if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
						puestoTienda[iContadorPuestos] = "aux"+sPuesto+elemento.getPcCveSuperior()+sTienda;
						iContadorPuestos++;
					} else {
						puestoTienda[iContadorPuestos] = "aux"+sPuesto+elemento.getPcCveSuperior();
						iContadorPuestos++;
					}	
					cveVendedorAnterior = elemento.getPcCveVendedor();
				}
			
			}
					
		}	
	
		//for(int i=0; i < tiendaPuesto.length; i++)
		/*for(int i=0; i < puestoTienda.length; i++)
					
		/*if(!tiendaReporte.isEmpty()){
				Iterator ramas = tiendaReporte.iterator();
				String nodoActual = "";
				while(ramas.hasNext()){
					ArbolVO tienda = (ArbolVO) ramas.next();
					if(tienda.getPcCvePtoventas()!= null && !tienda.getPcCvePtoventas().equals("")){
						nodoActual = "aux" + tienda.getPcCveVendedor(); 
						documento = "docAux = insFld(" + nodoActual + ", gFld('" + tienda.getPcNomPtoventas() + "','javascript:updateTienda(\"" + tienda.getPcCveVendedor() + "-" + tienda.getPcCvePtoventas() + "\")'))";
						iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
						iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
						textJavaScript.append("\n\t" + documento);
						textJavaScript.append("\n\t" + iconoDoc);
						textJavaScript.append("\n\t" + iconoDocCerrado);
					}
				}
			}*/
		
								
			return textJavaScript.toString(); 
	
	} //fin de metodo
	
	/* otro metodo prueba */
		public String generarArbolTiendaVendedorPuestos2(ArrayList tiendaDivision, ArrayList arbolReporte,ArrayList tiendaReporte,String divisionDesc, int cveDivision, boolean division){
				
		StringBuffer textJavaScript = new StringBuffer();
			 
		String folder = "";
		String documento = "";
		String carpeta = "";
		String iconoCerrado = "";
		String iconoAbierto = "";
		String iconoDoc = "";
		String iconoDocCerrado = "";
		String divisionName = divisionDesc;
		int ancla = cveDivision;
		
		//variables para agruparlos por puestos
		int cveSuperiorAnterior = 0;
		int cveSuperiorActual = 0;
		int cvePuestoAnterior = 0;
		int cvePuestoActual = 0;
		int cveVendedorAnterior = 0;
		int cveVendedorActual = 0;
				
		boolean primeravez = false;
		String sCarpetaPuesto = "";
		String sCarpetaPuestoTienda = "";
		String tiendaPuesto[] = null;	
		String puestoTienda[] = null;
		
		/* genero un arrayList de Objetos nuevo */
		ArrayList arbolReporte1 = arbolReporte;
		ArrayList tiendaReporte1 = tiendaReporte;
		
		ArrayList probandoReporte = new ArrayList();
		
		Iterator ramasReporte = arbolReporte1.iterator();
		
		while(ramasReporte.hasNext()){
			boolean existe = false;
			ArbolVO probando = (ArbolVO)ramasReporte.next();
			Iterator ramasTienda = tiendaReporte1.iterator();
			while (ramasTienda.hasNext()) {
				ArbolVO tiendaP = (ArbolVO)ramasTienda.next();
				
				if(tiendaP.getPcCvePtoventas()!= null && !tiendaP.getPcCvePtoventas().equals("")){
					if(probando.getPcCveVendedor() == tiendaP.getPcCveVendedor()) {
						tiendaP.setLevel(probando.getLevel());
						tiendaP.setNivel(probando.getNivel());
						tiendaP.setPcNombreVendedor(probando.getPcNombreVendedor());
						tiendaP.setPcDigVerif(probando.getPcDigVerif());
						tiendaP.setPcCiudad(probando.getPcCiudad());
						tiendaP.setPcEstado(probando.getPcEstado());
						tiendaP.setPcDireccion(probando.getPcDireccion());
						tiendaP.setPcColonia(probando.getPcColonia());
						tiendaP.setPcCveDiv(probando.getPcCveDiv());
						tiendaP.setPcCvePuesto(probando.getPcCvePuesto());
						tiendaP.setPcCveEmpRef(probando.getPcCveEmpRef());
						tiendaP.setPcDescPuesto(probando.getPcDescPuesto());
						tiendaP.setPcCveSuperior(probando.getPcCveSuperior());
						tiendaP.setPcNombreSuperior(probando.getPcNombreSuperior());
						tiendaP.setPcDescPuestoSup(probando.getPcDescPuestoSup());
						tiendaP.setPcDescDiv(probando.getPcDescDiv());
						probandoReporte.add(tiendaP);
						ramasTienda.remove();
						existe = true;
					}
				} 
			}
			if(!existe) {
				probando.setPcNomPtoventas("");
				probandoReporte.add(probando);
			}
		}
		
		logger.info("tamaño " + probandoReporte.size());
		//imprimo datos
		if(!probandoReporte.isEmpty()) {
			Iterator IReporte = probandoReporte.iterator();
			while(IReporte.hasNext()){
				ArbolVO reporte = (ArbolVO)IReporte.next();
				logger.info("nivel " + reporte.getNivel() + " vendedor " + reporte.getPcCveVendedor() + " puesto " + reporte.getPcCvePuesto() + " cve tienda " + reporte.getPcCvePtoventas());
			}
		}
		/* termino de generar ArrayList */
		
		if(probandoReporte.isEmpty()){
			folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + divisionName + "</i>\', \'\')"; 								
			iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
			iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
			textJavaScript.append("\n\t" + folder);
			textJavaScript.append("\n\t" + iconoCerrado);
			textJavaScript.append("\n\t" + iconoAbierto);
		} else {
			boolean bandera = false;
			boolean banderaExiste=false;//bandera que nos indica que si en la primera vez hay tienda o no 											
			Iterator ramas = probandoReporte.iterator();
			String nodoActual = "";
			
			tiendaPuesto = new String[probandoReporte.size()+ 1];
			puestoTienda = new String[probandoReporte.size()+ 1];
			int iContador = 0;	
			int iContadorPuestos = 0;
			boolean encontre = false;
			while(ramas.hasNext()){
				encontre = false;
				ArbolVO elemento = (ArbolVO) ramas.next();
				if(elemento.getNivel()==1) {
					if(division){ //La bandera division nos indica que estamos generando un árbol de division a partir del vendedor raiz
						folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + elemento.getPcNombreVendedor() + "</i>\', \'\')"; 								
						iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
						iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
						nodoActual = "foldersTree";
						primeravez = true;//cuando se genera un reporte a nivel division
					}else{
						String sPuesto = elemento.getPcDescPuesto();
						if(sPuesto.indexOf(" ") > 0)
							sPuesto = sPuesto.replace(' ','_');
						if(sPuesto.indexOf(".") > 0)	
							sPuesto = sPuesto.replace('.','_');
						
						/*folder = "foldersTree = gFld(\'<i>REPORTE DIVISION false" + divisionName + "</i>\', \'\')"; 								
						iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
						iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);*/

						String nodo = "foldersTree";
						
						int iPuestoSuperior = 0;
						if(elemento.getPcCveSuperior() < 0)
							iPuestoSuperior = elemento.getPcCveSuperior() * -1;
						else
							iPuestoSuperior = elemento.getPcCveSuperior(); 
							
						sCarpetaPuesto = "aux"+sPuesto+ iPuestoSuperior;
					
						//busco si el puesto ya fue dado de alta
						String puesto = "aux"+sPuesto+elemento.getPcCveSuperior();
						
						if(cveVendedorAnterior == elemento.getPcCveVendedor())
							encontre = true;
												
						if(!encontre) {	
							folder = "foldersTree = gFld(\'<i>REPORTE DIVISION " + divisionName + "</i>\', \'\')"; 								
							iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
							iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
													
							folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
							iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
							iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
							
							carpeta = "aux" + elemento.getPcCveVendedor();   
							folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+ "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
							iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
							iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
						}
												
						if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
							String sTienda = elemento.getPcCvePtoventas();
							if(sTienda.indexOf("-") > 0)
								sTienda = sTienda.replace('-','_');
								
							sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveVendedor()+sTienda; 
							folder = sCarpetaPuestoTienda + " =  insFld("+ "aux" + elemento.getPcCveVendedor() + ", gFld('" + elemento.getPcNomPtoventas()+ "', 'javascript:updateTable(\"" + elemento.getPcCvePtoventas() +  "-" + "0" + "\")'))";
							iconoAbierto = sCarpetaPuestoTienda + ".iconSrc = ICONPATH + 'tienda.gif'";
							iconoCerrado = sCarpetaPuestoTienda + ".iconSrcClosed = ICONPATH + 'tienda.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
							tiendaPuesto[iContador] = sCarpetaPuestoTienda;
							iContador++;
						
							puestoTienda[iContadorPuestos] = "aux"+sPuesto+elemento.getPcCveVendedor()+sTienda;
							iContadorPuestos++;
						} else {
							puestoTienda[iContadorPuestos] = "aux"+sPuesto+elemento.getPcCveVendedor();
							iContadorPuestos++;
							sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveVendedor(); 
							tiendaPuesto[iContador] = sCarpetaPuestoTienda;
							iContador++;
						}
															
						nodoActual = carpeta;
						cvePuestoAnterior = elemento.getPcCvePuesto();
						cveSuperiorAnterior = elemento.getPcCveSuperior();
						cveVendedorAnterior = elemento.getPcCveVendedor();
																							
					}
			} else {
				
				String nodo = (elemento.getPcCveSuperior() ==  cveDivision) ? "foldersTree":"aux" + elemento.getPcCveSuperior();
				
				String sPuesto = elemento.getPcDescPuesto();
				String sPuestoSuperior = elemento.getPcDescPuestoSup();
				if(sPuesto.indexOf(" ") > 0)
					sPuesto = sPuesto.replace(' ','_');
				if(sPuesto.indexOf(".") > 0)	
					sPuesto = sPuesto.replace('.','_');
					
				if(sPuestoSuperior.indexOf(" ") > 0)
					sPuestoSuperior = sPuestoSuperior.replace(' ','_');
				if(sPuestoSuperior.indexOf(".") > 0)	
					sPuestoSuperior = sPuestoSuperior.replace('.','_');
											
				int iPuestoSuperior = 0;
				if(elemento.getPcCveSuperior() < 0)
					iPuestoSuperior = elemento.getPcCveSuperior() * -1;
				else
					iPuestoSuperior = elemento.getPcCveSuperior(); 
					
				String sTienda="";
				if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
					sTienda = elemento.getPcCvePtoventas();
					if(sTienda.indexOf("-") > 0)
						sTienda = sTienda.replace('-','_');
				}
				
				if(primeravez) {
					
					sCarpetaPuesto = "aux"+sPuesto+iPuestoSuperior; 
					folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
					iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
					iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
					textJavaScript.append("\n\t" + folder);
					textJavaScript.append("\n\t" + iconoCerrado);
					textJavaScript.append("\n\t" + iconoAbierto);
					
					//vendedor
					carpeta = "aux" + elemento.getPcCveVendedor();   
					folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+ "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
					iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
					iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
					textJavaScript.append("\n\t" + folder);
					textJavaScript.append("\n\t" + iconoCerrado);
					textJavaScript.append("\n\t" + iconoAbierto);
										
					primeravez = false;
					cvePuestoAnterior = elemento.getPcCvePuesto();
					cveSuperiorAnterior = elemento.getPcCveSuperior();
					nodoActual = sCarpetaPuesto;
					
					/* agregador para tienda*/
					if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
						
						if(sTienda.indexOf("-") > 0)
							sTienda = sTienda.replace('-','_');

						sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveVendedor()+sTienda; 
						folder = sCarpetaPuestoTienda + " =  insFld("+ "aux" + elemento.getPcCveVendedor() + ", gFld('" + elemento.getPcNomPtoventas()+ "', 'javascript:updateTable(\"" + elemento.getPcCvePtoventas() +  "-" + "0" + "\")'))";
						iconoAbierto = sCarpetaPuestoTienda + ".iconSrc = ICONPATH + 'tienda.gif'";
						iconoCerrado = sCarpetaPuestoTienda + ".iconSrcClosed = ICONPATH + 'tienda.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
						tiendaPuesto[iContador] = sCarpetaPuestoTienda;
						iContador++;
	
						
					} else {
						sCarpetaPuestoTienda = "aux"+sPuesto+ elemento.getPcCveVendedor(); 
						tiendaPuesto[iContador] = sCarpetaPuestoTienda;
						iContador++;
										
						
					}
			} else {
					String puesto = null;
					boolean bEncontreTienda=false;
					if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
						String sTienda1 = "aux" + sPuestoSuperior + elemento.getPcCveSuperior() + sTienda;
						for(int i=0; i < iContador;i++) { //busco si la tienda ya esta dado de alta en el arreglo
							if(tiendaPuesto[i].equals(sTienda1)) {
								nodo = tiendaPuesto[i];
								bEncontreTienda = true;
								break;
							}
					}
					
					puesto = "aux"+sPuesto+elemento.getPcCveSuperior() + sTienda;	
					
					for(int j=0; j < iContadorPuestos; j++) {
						if(puestoTienda[j].equals(puesto)) {
							encontre = true;
							break;
						}
					}		
						
				} else {
						String sTienda1 = "aux" + sPuestoSuperior + elemento.getPcCveSuperior();
						for(int i=0; i < iContador;i++) { //busco si la tienda ya esta dado de alta en el arreglo
							if(tiendaPuesto[i].equals(sTienda1)) {
								nodo = tiendaPuesto[i];
								break;
							}
						}
	
						puesto = "aux"+sPuesto+elemento.getPcCveSuperior();	

						for(int j=0; j < iContadorPuestos; j++) {
							if(puestoTienda[j].equals(puesto)) {
								encontre = true;
								break;
							}
						}	
					}
					
										
					if(cveSuperiorAnterior == elemento.getPcCveSuperior() && cvePuestoAnterior == elemento.getPcCvePuesto()) {
						sCarpetaPuesto = "aux"+sPuesto+iPuestoSuperior+sTienda;
						
						if(!encontre) {
							folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
							iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
							iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
						} 
						
						//	vendedor
						carpeta = "aux" + elemento.getPcCveVendedor();
						folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+  "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
							
						cvePuestoAnterior = elemento.getPcCvePuesto();
						cveSuperiorAnterior = elemento.getPcCveSuperior(); 
						carpeta = "aux"+sPuesto+iPuestoSuperior;
						
												
					} else {
						sCarpetaPuesto = "aux"+sPuesto+iPuestoSuperior+sTienda;
								
						if(!encontre) {
							folder = sCarpetaPuesto + " =  insFld("+ nodo + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" +  "-" +  "\")'))";
							iconoAbierto = sCarpetaPuesto + ".iconSrc = ICONPATH + 'puesto-open.gif'";
							iconoCerrado = sCarpetaPuesto + ".iconSrcClosed = ICONPATH + 'puesto-cerrado.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto); 
						}
						
						//vendedor
						carpeta = "aux" + elemento.getPcCveVendedor();   
						folder = carpeta + " =  insFld("+ sCarpetaPuesto + ", gFld('" + elemento.getPcNombreVendedor()+ "', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
											
						cvePuestoAnterior = elemento.getPcCvePuesto();
						cveSuperiorAnterior = elemento.getPcCveSuperior();
						
					}
					nodoActual = sCarpetaPuesto;
				}
				
				if(elemento.getPcCvePtoventas()!=null && !elemento.getPcCvePtoventas().equals("")) {
						puestoTienda[iContadorPuestos] = "aux"+sPuesto+elemento.getPcCveSuperior()+sTienda;
						iContadorPuestos++;
				} else {
					puestoTienda[iContadorPuestos] = "aux"+sPuesto+elemento.getPcCveSuperior();
					iContadorPuestos++;
				}	
			
			}
			
		}
					
	}	
	
	/*if(!tiendaReporte.isEmpty()){
			Iterator ramas = tiendaReporte.iterator();
			String nodoActual = "";
			while(ramas.hasNext()){
				ArbolVO tienda = (ArbolVO) ramas.next();
				if(tienda.getPcCvePtoventas()!= null && !tienda.getPcCvePtoventas().equals("")){
					nodoActual = "aux" + tienda.getPcCveVendedor(); 
					documento = "docAux = insFld(" + nodoActual + ", gFld('" + tienda.getPcNomPtoventas() + "','javascript:updateTienda(\"" + tienda.getPcCveVendedor() + "-" + tienda.getPcCvePtoventas() + "\")'))";
					iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
					iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
					textJavaScript.append("\n\t" + documento);
					textJavaScript.append("\n\t" + iconoDoc);
					textJavaScript.append("\n\t" + iconoDocCerrado);
				}
			}
		}*/
		
								
		return textJavaScript.toString(); 
	}

} //fin de clase



