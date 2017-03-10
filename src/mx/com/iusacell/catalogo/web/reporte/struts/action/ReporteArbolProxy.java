/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.Arrays;

import javax.servlet.ServletOutputStream;
import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.modelo.DirectorDivVO;

import mx.com.iusacell.catalogo.modelo.ArbolVO;

import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
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
 * @author Dvazqueza
 *
 * Paquete : mx.com.iusacell.catalogo.web.reporte.struts.action
 * Proyecto : AdminCatalogoWeb
 *
 */
public class ReporteArbolProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(ReporteArbolProxy.class);

	/**
	 * PuntosVentaProxy
	 * 
	 * 
	 *
	 *  
	 * 
	 */
	public ReporteArbolProxy() {
	}
	
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {

			String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("jsArray") != null) request.getSession().removeAttribute("jsArray"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			 
			control=request.getParameter("control");
			String nombreVendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
			 
			try{	
				//String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				/**
				 * CAL
				 * Nuevas variables.
			     */
				String listaDivs = "";
				if(((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs()!= null)
				    listaDivs = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs();
					String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
					boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
				/**
				 * Fin de nuevas variables.
				 */					
				ArrayList arbolReporte =null;
				ArrayList tiendaReporte =null;
				if(nombreVendedorBuscar != null){	
						if(subdivisionUsuario==0 || encontrado){
							arbolReporte =AdminCatFacade.getArbolDescendente(nombreVendedorBuscar);
							tiendaReporte =AdminCatFacade.getArbolDescendenteTienda(nombreVendedorBuscar);
						}else{															
								arbolReporte =CatalogoFacade.getArbolDescendente(nombreVendedorBuscar,listaDivs, String.valueOf(divisionUsuario));
								tiendaReporte =CatalogoFacade.getArbolDescendenteTienda(nombreVendedorBuscar,listaDivs,String.valueOf(divisionUsuario));

						}
					
					logger.debug("ReporteArbolProxy.verLista():Dentro del try");
	
					if(arbolReporte==null || arbolReporte.isEmpty()){
						request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
					}else{
						String jsArray = generarArregloDetalle(arbolReporte,tiendaReporte);
						request.getSession().setAttribute("arbolReporte",arbolReporte);
						request.getSession().setAttribute("jsArray",jsArray);
						String textJavaScript = generarArbolTiendaModif(arbolReporte,tiendaReporte,"Jerarquia", 0, false);
						request.getSession().setAttribute("textJavaScript",textJavaScript);	
					}
				}
				//eda
				}catch(CatalogoSystemException cse){
				logger.error(cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error(e.toString());
				return mapping.findForward("error");
			}
			request.setAttribute("nombreVendedorBuscar",nombreVendedorBuscar);
			return mapping.findForward("home");
	}

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ReporteArbolForm forma = (ReporteArbolForm) form;

			return mapping.findForward("home");
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ReporteArbolForm forma = (ReporteArbolForm) form;

			return mapping.findForward("home");
	}

	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ReporteArbolForm forma = (ReporteArbolForm) form;

			return mapping.findForward("home");
	}

	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			//String control= null;
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("jsArray") != null) request.getSession().removeAttribute("jsArray"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			 
			try{
				String divisionBuscar = (request.getParameter("pcCveDivisionSeek").equals(""))? null:request.getParameter("pcCveDivisionSeek");
				//String divisionBuscarDesc = (request.getParameter("pcDescDivisionSeek").equals(""))? null:request.getParameter("pcDescDivisionSeek");
				String divisionBuscarDesc = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
				
				
				
				
				if(divisionBuscar== null || divisionBuscar.equals(""))
					// Si el usuario no proporciona una division para buscar entonces se dedica a buscar al vendedor
					return verLista(mapping, form, request, response);
				else{
					((ReporteArbolForm) form).reset(mapping,request);
					((ReporteArbolForm) form).setPcCveDivisionSeek(divisionBuscar);
					
					// Buscamos el Nodo inicial		
					logger.info("antes de llamar a CatalogoFacade.getArbolDirectorDiv(divisionBuscar);");
					DirectorDivVO director  = CatalogoFacade.getArbolDirectorDiv(divisionBuscar);
						
					if(director != null){
						//buscamos a que division pertenece el usuario
						int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				    /**
				     * CAL
				     * Nuevas variables.
				     */
						String listaDivs = "";
									if(((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs()!= null)
										   listaDivs = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs();
						String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
						boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
					/**
					 * Fin de nuevas variables.
					 */	
						ArrayList arbolReporte = null;
						ArrayList tiendaReporte = null;
						
						
						//Corremos el Query para obtener el ärbol completo y las tiendas de cada usuario que las tenga
						if(subdivisionUsuario==0 || encontrado){
							//arbolReporte = AdminCatFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()));
							//tiendaReporte = AdminCatFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()));
							//rsg 16/07/2005
							arbolReporte = AdminCatFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),String.valueOf(divisionBuscar));
							tiendaReporte = AdminCatFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()), String.valueOf(divisionBuscar));
						}else{							
							arbolReporte = CatalogoFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),divisionBuscar, divisionBuscar);
							tiendaReporte = CatalogoFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()),divisionBuscar, divisionBuscar);
						} 
												
						if (arbolReporte== null || arbolReporte.isEmpty()){
							//No hay datos en el árbol asi que se aborta
							request.getSession().setAttribute("noData","El árbol resultante esta vacío");
						}else{
							// Hay datos asi que  contruimos los arreglos para el Javascript
							String jsArray = generarArregloDetalle(arbolReporte, tiendaReporte);
							request.getSession().setAttribute("arbolReporte",arbolReporte);
							request.getSession().setAttribute("jsArray",jsArray);
						}
											
						//String textJavaScript = generarArbolTienda(arbolReporte,tiendaReporte,director.getPcDescDiv(),true);
						String textJavaScript = generarArbolTiendaModif(arbolReporte,tiendaReporte,director.getPcDescDiv(), director.getPcCveDiv(),true);
						
						request.getSession().setAttribute("textJavaScript",textJavaScript);
						//request.setAttribute("textJavaScript",textJavaScript);
					}else{
						// No se encontro el usuario incial para la division asi que abortamos el árbol
						request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
						String textJavaScript = generarArbolTienda(new ArrayList(),new ArrayList(),divisionBuscarDesc, true);
						request.getSession().setAttribute("textJavaScript",textJavaScript);
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

	public ActionForward excel(ActionMapping mapping, 
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
			//String periodo = (parametros.get("pcMes")==null)? Fecha.dateToStringPeriodo(new Date()):(String)parametros.get("pcMes");
			String divisionBuscar = (request.getParameter("pcCveDivisionSeek").equals(""))? null:request.getParameter("pcCveDivisionSeek");
			String divisionBuscarDesc = (request.getParameter("pcDescDivisionSeek").equals(""))? null:request.getParameter("pcDescDivisionSeek");
			String cveVendedor        = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
//			buscamos a que division pertenece el usuario
						  int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						  int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
			  /** CAL
			   * Nuevas variables.
			  */
				  String listaDivs = "";
					if(((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs()!= null)
						 listaDivs = ((UserSessionVO)request.getSession().getAttribute("USER")).getPcCvesDivs();
		 	 
					   String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
				boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
			  /**
			   * Fin de nuevas variables.
			   */	
			  ArrayList arbolReporte = null;
			  ArrayList tiendaReporte = null;
			  
			((ReporteArbolForm) form).reset(mapping,request);
			((ReporteArbolForm) form).setPcCveDivisionSeek(divisionBuscar);
					
			// Buscamos el Nodo inicial		
			DirectorDivVO director  = CatalogoFacade.getArbolDirectorDiv(divisionBuscar);
			
     if(director != null){		
	     if(subdivisionUsuario==0 || encontrado){
	 	     arbolReporte = AdminCatFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),String.valueOf(divisionBuscar));
		     tiendaReporte = AdminCatFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()), String.valueOf(divisionBuscar));
	 	   }else{					
		     arbolReporte = CatalogoFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),divisionBuscar, divisionBuscar);
		     tiendaReporte = CatalogoFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()),divisionBuscar, divisionBuscar);		
	     }								
			}else{
		  Pc_VendedoresVO listaBuscar = CatalogoFacade.getDetallePersonal(cveVendedor);
			      if(listaBuscar!=null)
		               divisionBuscarDesc = listaBuscar.getPcSubdivision();                       
				
				if(subdivisionUsuario==0 || encontrado){
					arbolReporte =AdminCatFacade.getArbolDescendente(cveVendedor);
					tiendaReporte =AdminCatFacade.getArbolDescendenteTienda(cveVendedor);
			  }else{								
					arbolReporte =CatalogoFacade.getArbolDescendente(cveVendedor,listaDivs,String.valueOf(listaBuscar.getPcCveSubdiv()));
					tiendaReporte =CatalogoFacade.getArbolDescendenteTienda(cveVendedor,listaDivs,String.valueOf(listaBuscar.getPcCveSubdiv()));
			 }	
		}
		if (arbolReporte== null || arbolReporte.isEmpty()){
			//No hay datos en el árbols asi que se aborta
			request.getSession().setAttribute("noData","No se selecciono la división");
		  }else{
			// Hay datos asi que  contruimos los arreglos para el Javascript 
			excelDocPersonal = generarReportePersonalExcel(arbolReporte, tiendaReporte,divisionBuscarDesc);
			excelDocTiendas = generarReporteTiendasExcel(arbolReporte, tiendaReporte,divisionBuscarDesc);
		}
		//Contruimos un árbol con o sin datos
			ExcelDoc doc= new ExcelDoc();
			
			if(excelDocPersonal != null && !excelDocPersonal.isEmpty()) {
				
				String[] encabezados = {"División","Clave","Nombre","Puesto","Clave Superior","Superior",
										"Puesto Superior","Fecha Alta","Tipo Canal", "Nomina"};
				String[] metodos = {"pcDescDiv","pcClaveCompleta","pcNombreVendedor","pcDescPuesto",
									"pcCveSuperior","pcNombreSuperior","pcDescPuestoSup","pcFechaAltaStr",
									"pcDescTpCanal", "pcCveEmpRef"};

				ArrayList encabezado = new ArrayList(Arrays.asList(encabezados));
				ArrayList metodo = new ArrayList(Arrays.asList(metodos));
				doc.obtieneMetodos("personal", encabezado, metodo, excelDocPersonal);
			}
			if(excelDocTiendas != null && !excelDocTiendas.isEmpty()) {
				
				String[] encabezados = {"División","Clave","Nombre","Puesto","Clave Superior","Superior","Puesto Superior","Fecha Alta",
										"Clave Punto Venta","Número Económico","Punto Ventas","Canal","Tipo Canal"};
				String[] metodos = {"pcDescDiv","pcClaveCompleta","pcNombreVendedor","pcDescPuesto",
									"pcCveSuperior","pcNombreSuperior","pcDescPuestoSup","pcFechaAltaStr",
									"pcCvePtoventas","pcCveReferencia","pcNomPtoventas","pcDescCanal","pcDescTpCanal"};
				ArrayList encabezado = new ArrayList(Arrays.asList(encabezados));
				ArrayList metodo = new ArrayList(Arrays.asList(metodos));
				doc.obtieneMetodos("tiendas", encabezado, metodo, excelDocTiendas);
			}

			if(doc.getNumHojas()>0){
				
				String ruta1 = request.getSession().getServletContext().getRealPath("") + "/excel/";
				String ruta = request.getContextPath() + "/excel/";
				String identificador = Fecha.dateToStringCurrent(Fecha.getHoy());
				String sessionid = request.getSession().getId();
				//request.getSession().setAttribute("liga", ruta + sessionid + identificador + ".xls");
				
				MyFileStructure mf = new MyFileStructure();
				
				mf.setDirname(ruta1);
				mf.build();
				mf.list();
				
				//escribe los datos a disco duro			
				/*FileOutputStream fileOut = new FileOutputStream(ruta1 + sessionid + identificador + ".xls");
				doc.escribirDisco(fileOut);
				fileOut.close();*/
				//escribe en el buffer del navegador
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
		if(excelDocPersonal == null || excelDocPersonal.size() == 0 || excelDocTiendas == null || excelDocTiendas.size()==0)
			return mapping.findForward("home");
		else
			return null;
	}


/**
 * @param arbolReporte - ArrayList
 * @param divisionDesc - String
 * @return
 *
 * 
 * Try to create this 
 * <script language="javascript"> 
 *	foldersTree = gFld("<i>Division 1</i>", "demoFramesetRightFrame.html")
 *	foldersTree.iconSrc = ICONPATH + "home24.gif"
 *	foldersTree.iconSrcClosed = ICONPATH + "homeplus24.gif"
 *	aux1 = insFld(foldersTree, gFld("DIRECTOR(DIRECTOR DIVISIONAL)", "demoFramesetRightFrame.html"))
 *	aux1.iconSrc = ICONPATH + "group24.gif"
 *	aux1.iconSrcClosed = ICONPATH + "groupplus24.gif"
 *	aux2 = insFld(aux1, gFld("GERENTE REGIONAL(GERENTE REGIONAL)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
 *	aux2.iconSrc = ICONPATH + "man24.gif"
 *	aux2.iconSrcClosed = ICONPATH + "manplus24.gif"
 * </script>
 * 
 * from ArrayList arbolReporte
 * 
 */

	public ArrayList generarArbol(ArrayList arbolReporte,String divisionDesc){
		ArrayList textJavaScript = new ArrayList();
		Stack nivelActual = new Stack(); 
		String folder = "";
		String iconoCerrado = "";
		String iconoAbierto = "";
		String divisionName = divisionDesc; 
						
		
		if(arbolReporte.isEmpty()){
			folder = "foldersTree = gFld(\'<i>" + divisionName + "</i>\', \'\')"; 								
			iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
			iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
			textJavaScript.add(folder);
			textJavaScript.add(iconoCerrado);
			textJavaScript.add(iconoAbierto);
		}else{
			Iterator ramas = arbolReporte.iterator();
					
			while(ramas.hasNext()){
				ArbolVO elemento = (ArbolVO) ramas.next();

				if(elemento.getNivel()==1){
					folder = "foldersTree = gFld(\'<i>" + elemento.getPcNombreVendedor() + "</i>\', \'\')"; 								
					iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
					iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
					textJavaScript.add(folder);
					textJavaScript.add(iconoCerrado);
					textJavaScript.add(iconoAbierto);
					//folder = "aux" + elemento.getNivel() + " =  insFld(foldersTree, gFld(\'" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor() + "\")'))";
					//iconoAbierto = "aux" + elemento.getNivel() + ".iconSrc = ICONPATH + 'group24.gif'";
					//iconoCerrado = "aux" + elemento.getNivel() + ".iconSrcClosed = ICONPATH + 'groupplus24.gif'";
					//textJavaScript.add(folder);
					//textJavaScript.add(iconoCerrado);
					//textJavaScript.add(iconoAbierto);
					nivelActual.push(new Integer(elemento.getNivel()));
				}else if( (nivelActual.peek().equals(new Integer((elemento.getNivel()-1))))){
					String nodo = "";
					if(((Integer)nivelActual.peek()).intValue() == 1) nodo = "foldersTree"; else nodo = "aux" + nivelActual.peek();  
					folder = "aux" + elemento.getNivel() + " =  insFld("+ nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor() + "\")'))";
					iconoAbierto = "aux" + elemento.getNivel() + ".iconSrc = ICONPATH + 'man24.gif'";
					iconoCerrado = "aux" + elemento.getNivel() + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
					textJavaScript.add(folder);
					textJavaScript.add(iconoCerrado);
					textJavaScript.add(iconoAbierto);
					nivelActual.push(new Integer(elemento.getNivel()));
				}else if( (nivelActual.peek().equals(new Integer((elemento.getNivel()))))){
					String nodo = "";
					nivelActual.pop();
					if(((Integer)nivelActual.peek()).intValue() == 1) nodo = "foldersTree"; else nodo = "aux" + nivelActual.peek();  
					folder = "aux" + elemento.getNivel() + " =  insFld(" + nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(" + elemento.getPcCveVendedor() + ")'))";
					iconoAbierto = "aux" + elemento.getNivel() + ".iconSrc = ICONPATH + 'man24.gif'";
					iconoCerrado = "aux" + elemento.getNivel() + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
					textJavaScript.add(folder);
					textJavaScript.add(iconoCerrado);
					textJavaScript.add(iconoAbierto);
					nivelActual.push(new Integer(elemento.getNivel()));
				}else{
					int current_level=0;
					String nodo = "";
					while(elemento.getNivel() <= ((Integer)nivelActual.peek()).intValue()){
						nivelActual.pop();
						current_level = ((Integer)nivelActual.peek()).intValue();
					}
					if(current_level==1) nodo = "foldersTree"; else nodo = "aux" + nivelActual.peek();  
					folder = "aux" + elemento.getNivel() + " =  insFld(" + nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor() + "\")'))";
					iconoAbierto = "aux" + elemento.getNivel() + ".iconSrc = ICONPATH + 'man24.gif'";
					iconoCerrado = "aux" + elemento.getNivel() + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
					textJavaScript.add(folder);
					textJavaScript.add(iconoCerrado);
					textJavaScript.add(iconoAbierto);
					nivelActual.push(new Integer(elemento.getNivel()));
				}
			}
		}
		return textJavaScript;
	}

/**
 * @param arbolReporte - ArrayList
 * @param divisionDesc - String
 * @return
 *
 * 
 * Try to create this 
 * <script language="javascript"> 
 *	foldersTree = gFld("<i>Division 1</i>", "demoFramesetRightFrame.html")
 *	foldersTree.iconSrc = ICONPATH + "home24.gif"
 *	foldersTree.iconSrcClosed = ICONPATH + "homeplus24.gif"
 *	aux1 = insFld(foldersTree, gFld("DIRECTOR(DIRECTOR DIVISIONAL)", "demoFramesetRightFrame.html"))
 *	aux1.iconSrc = ICONPATH + "group24.gif"
 *	aux1.iconSrcClosed = ICONPATH + "groupplus24.gif"
 *	aux2 = insFld(aux1, gFld("GERENTE REGIONAL(GERENTE REGIONAL)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
 *	aux2.iconSrc = ICONPATH + "man24.gif"
 *	aux2.iconSrcClosed = ICONPATH + "manplus24.gif"
 * </script>
 * 
 * from ArrayList arbolReporte
 * 
 */

	public String generarArbolTienda(ArrayList arbolReporte,ArrayList tiendaReporte,String divisionDesc,boolean division){
		//ArrayList textJavaScript = new ArrayList();
		
		StringBuffer textJavaScript = new StringBuffer();
		Stack nivelActual = new Stack();
		Stack clavesVendedor = new Stack(); 
		String folder = "";
		String documento = "";
		String carpeta = "";
		String iconoCerrado = "";
		String iconoAbierto = "";
		String iconoDoc = "";
		String iconoDocCerrado = "";
		String divisionName = divisionDesc; 
					
	
		if(arbolReporte.isEmpty()){
			folder = "foldersTree = gFld(\'<i>" + divisionName + "</i>\', \'\')"; 								
			iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
			iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
			textJavaScript.append("\n\t" + folder);
			textJavaScript.append("\n\t" + iconoCerrado);
			textJavaScript.append("\n\t" + iconoAbierto);
		}else{
			Iterator ramas = arbolReporte.iterator();
			String nodoActual = "";
			int vendedorActual =0;	
			while(ramas.hasNext()){
				ArbolVO elemento = (ArbolVO) ramas.next();

				if(elemento.getNivel()==1){
					if(division){ //La bandera division nos indica que estamos generando un árbol de diviosn a partir del vendedor raiz
						folder = "foldersTree = gFld(\'<i>" + elemento.getPcNombreVendedor() + "</i>\', \'\')"; 								
						iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
						iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
						nodoActual = "foldersTree";
						vendedorActual = elemento.getPcCveVendedor(); 
						nivelActual.push(new Integer(elemento.getNivel()));
						clavesVendedor.push(new Integer(elemento.getPcCveVendedor()));
					}else{
						folder = "foldersTree = gFld(\'<i>" + divisionName + "</i>\', \'\')"; 								
						iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
						iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);

						String nodo = "foldersTree"; 
						carpeta = "aux" + elemento.getPcCveVendedor(); //carpeta = "aux" + elemento.getNivel();  
						folder = carpeta + " =  insFld("+ nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
						nodoActual = carpeta;

						if(elemento.getPcCvePtoventas() != null && !elemento.getPcCvePtoventas().equals("")){
							documento = "docAux = insFld(" + nodoActual + ", gFld('" + elemento.getPcNomPtoventas() + "','javascript:updateTienda(\"" + elemento.getPcCveVendedor() + "-" + elemento.getPcCvePtoventas() + "\")'))";
							iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
							iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
							textJavaScript.append("\n\t" + documento);
							textJavaScript.append("\n\t" + iconoDoc);
							textJavaScript.append("\n\t" + iconoDocCerrado);
						}
						vendedorActual = elemento.getPcCveVendedor(); 

						nivelActual.push(new Integer(elemento.getNivel()));
						clavesVendedor.push(new Integer(elemento.getPcCveVendedor()));					
					}
					//folder = "aux" + elemento.getNivel() + " =  insFld(foldersTree, gFld(\'" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor() + "\")'))";
					//iconoAbierto = "aux" + elemento.getNivel() + ".iconSrc = ICONPATH + 'group24.gif'";
					//iconoCerrado = "aux" + elemento.getNivel() + ".iconSrcClosed = ICONPATH + 'groupplus24.gif'";
					//textJavaScript.add(folder);
					//textJavaScript.add(iconoCerrado);
					//textJavaScript.add(iconoAbierto);
					//nodoActual = "foldersTree";
					//vendedorActual = elemento.getPcCveVendedor(); 
					//nivelActual.push(new Integer(elemento.getNivel()));
				}else if( (nivelActual.peek().equals(new Integer((elemento.getNivel()-1))))){
					//String nodo = (((Integer)nivelActual.peek()).intValue() == 1 && division)?"foldersTree":"aux" + nivelActual.peek();
					String nodo = (((Integer)nivelActual.peek()).intValue() == 1 && division)?"foldersTree":"aux" + clavesVendedor.peek();
					carpeta = "aux" + elemento.getPcCveVendedor(); //carpeta = "aux" + elemento.getNivel();  
					folder = carpeta + " =  insFld("+ nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
					iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
					iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
					textJavaScript.append("\n\t" + folder);
					textJavaScript.append("\n\t" + iconoCerrado);
					textJavaScript.append("\n\t" + iconoAbierto);
					nodoActual = carpeta;
					if(elemento.getPcCvePtoventas() != null && !elemento.getPcCvePtoventas().equals("")){
						documento = "docAux = insFld(" + nodoActual + ", gFld('" + elemento.getPcNomPtoventas() + "','javascript:updateTienda(\"" + elemento.getPcCveVendedor() + "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
						iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
						textJavaScript.append("\n\t" + documento);
						textJavaScript.append("\n\t" + iconoDoc);
						textJavaScript.append("\n\t" + iconoDocCerrado);
					}
					vendedorActual = elemento.getPcCveVendedor(); 

					nivelActual.push(new Integer(elemento.getNivel()));
					clavesVendedor.push(new Integer(elemento.getPcCveVendedor()));
				}else if( (nivelActual.peek().equals(new Integer((elemento.getNivel()))))){
					String nodo = "";
					nivelActual.pop();
					clavesVendedor.pop();
					if(elemento.getPcCveVendedor() !=  vendedorActual){
						//if(((Integer)nivelActual.peek()).intValue() == 1 && division) nodo = "foldersTree"; else nodo = "aux" + nivelActual.peek();
						nodo = (((Integer)nivelActual.peek()).intValue() == 1 && division)?"foldersTree":"aux" + clavesVendedor.peek();
						carpeta =  "aux" + elemento.getPcCveVendedor(); 
						folder = carpeta + " =  insFld(" + nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.append("\n\t" + folder);
						textJavaScript.append("\n\t" + iconoCerrado);
						textJavaScript.append("\n\t" + iconoAbierto);
						nodoActual = carpeta;
					}
					if(elemento.getPcCvePtoventas() != null && !elemento.getPcCvePtoventas().equals("")){
						documento = "docAux = insFld(" + nodoActual + ", gFld('" + elemento.getPcNomPtoventas() + "','javascript:updateTienda(\"" + elemento.getPcCveVendedor() + "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
						iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
						textJavaScript.append("\n\t" + documento);
						textJavaScript.append("\n\t" + iconoDoc);
						textJavaScript.append("\n\t" + iconoDocCerrado);
					}
					vendedorActual = elemento.getPcCveVendedor(); 
					nivelActual.push(new Integer(elemento.getNivel()));
					clavesVendedor.push(new Integer(elemento.getPcCveVendedor()));
				}else{
					int current_level=0;
					String nodo = "";
					while(elemento.getNivel() <= ((Integer)nivelActual.peek()).intValue()){
						nivelActual.pop();
						clavesVendedor.pop();
						current_level = ((Integer)nivelActual.peek()).intValue();
					}
					//if(current_level==1 && division) nodo = "foldersTree"; else nodo = "aux" + nivelActual.peek();
					nodo = (current_level==1 && division)?"foldersTree":"aux" + clavesVendedor.peek();
					carpeta =  "aux" + elemento.getPcCveVendedor(); 
					folder = carpeta + " =  insFld(" + nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
					iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
					iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
					textJavaScript.append("\n\t" + folder);
					textJavaScript.append("\n\t" + iconoCerrado);
					textJavaScript.append("\n\t" + iconoAbierto);
					nodoActual =  carpeta;
					if(elemento.getPcCvePtoventas() != null && !elemento.getPcCvePtoventas().equals("")){
						documento = "docAux = insFld(" + nodoActual + ", gFld('" + elemento.getPcNomPtoventas() + "','javascript:updateTienda(\"" + elemento.getPcCveVendedor() + "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
						iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
						textJavaScript.append("\n\t" + documento);
						textJavaScript.append("\n\t" + iconoDoc);
						textJavaScript.append("\n\t" + iconoDocCerrado);
					}
					vendedorActual = elemento.getPcCveVendedor(); 

					nivelActual.push(new Integer(elemento.getNivel()));
					clavesVendedor.push(new Integer(elemento.getPcCveVendedor()));
				}
			}
		}
		if(!tiendaReporte.isEmpty()){
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
		}
		return textJavaScript.toString();
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
					html.append(Html.addComillas(String.valueOf(elemento.getPcCveVendedor()) + "-" + elemento.getPcCvePtoventas()));
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

	/**
	 * @param arbolReporte - ArrayList
	 * @return String
	 *
	 * from ArrayList arbolReporte
	 * 
	 */

	public ArrayList generarReportePersonalExcel(ArrayList arbolReporte,ArrayList tiendaReporte, String division){
		ArrayList excelReporte = new ArrayList();
		
		if(!arbolReporte.isEmpty()){

			Iterator ramas = arbolReporte.iterator();
			while(ramas.hasNext()){
				ArbolVO elemento = (ArbolVO) ramas.next();
				elemento.setPcDescDiv(division);
				excelReporte.add(elemento);
				boolean cambio = false;
				for(int i=0; i< tiendaReporte.size();i++){
					ArbolVO tienda = (ArbolVO) tiendaReporte.get(i);
					if(elemento.getPcCveVendedor()== tienda.getPcCveVendedor() && tienda.getPcCvePtoventas()!= null){
						elemento.setPcDescTpCanal(tienda.getPcDescTpCanal());
						break;
					}
				}
			}
		}
		return excelReporte;
	}
	
	/**
	 * @param arbolReporte - ArrayList
	 * @return String
	 *
	 * from ArrayList arbolReporte
	 * 
	 */

	public ArrayList generarReporteTiendasExcel(ArrayList arbolReporte,ArrayList tiendaReporte, String division){
		ArrayList excelReporte = new ArrayList();
		
		if(!arbolReporte.isEmpty()){

			Iterator ramas = arbolReporte.iterator();
			while(ramas.hasNext()){
				boolean existe = false;
				ArbolVO elemento = (ArbolVO) ramas.next();
				elemento.setPcDescDiv(division);
				//excelReporte.add(elemento);
				boolean cambio = false;
				for(int i=0; i< tiendaReporte.size();i++){
					ArbolVO tienda = (ArbolVO) tiendaReporte.get(i);
					if(elemento.getPcCveVendedor() == tienda.getPcCveVendedor() && tienda.getPcCvePtoventas()!= null){
						existe = true;
						tienda.setPcDescDiv(elemento.getPcDescDiv());
						tienda.setPcNombreVendedor(elemento.getPcNombreVendedor());
						tienda.setPcDescPuesto(elemento.getPcDescPuesto());
						tienda.setPcCveSuperior(elemento.getPcCveSuperior());
						tienda.setPcNombreSuperior(elemento.getPcNombreSuperior());
						tienda.setPcDescPuestoSup(elemento.getPcDescPuestoSup());
						tienda.setPcFechaAltaStr(elemento.getPcFechaAltaStr());
						tienda.setPcDigVerif(elemento.getPcDigVerif());
						excelReporte.add(tienda);
						if(cambio==false) cambio=true;
					} else if(cambio==true) break;
				}
				
				if(!existe)
					excelReporte.add(elemento);
			}
		}
		return excelReporte;
	}	

		//rsg
	public String generarArbolTiendaModif(ArrayList arbolReporte,ArrayList tiendaReporte,String divisionDesc, int cveDivision, boolean division){
			//ArrayList textJavaScript = new ArrayList();
			
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
			
			if(arbolReporte.isEmpty()){
				folder = "foldersTree = gFld(\'<i>" + divisionName + "</i>\', \'\')"; 								
				iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
				iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
				textJavaScript.append("\n\t" + folder);
				textJavaScript.append("\n\t" + iconoCerrado);
				textJavaScript.append("\n\t" + iconoAbierto);
			} else {
				//busco si tiene nivel 1
				boolean bandera = false;
				Iterator ramas1 = arbolReporte.iterator();
				
							
				Iterator ramas = arbolReporte.iterator();
				String nodoActual = "";
				//int vendedorActual =0;	
				while(ramas.hasNext()){
					ArbolVO elemento = (ArbolVO) ramas.next();

					if(elemento.getNivel()==1){
						if(division){ //La bandera division nos indica que estamos generando un árbol de diviosn a partir del vendedor raiz
							folder = "foldersTree = gFld(\'<i>" + elemento.getPcNombreVendedor() + "</i>\', \'\')"; 								
							iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
							iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
							nodoActual = "foldersTree";
							//vendedorActual = elemento.getPcCveVendedor(); 
							//nivelActual.push(new Integer(elemento.getNivel()));
							//clavesVendedor.push(new Integer(elemento.getPcCveVendedor()));
						}else{
							folder = "foldersTree = gFld(\'<i>" + divisionName + "</i>\', \'\')"; 								
							iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
							iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);

							String nodo = "foldersTree"; 
							carpeta = "aux" + elemento.getPcCveVendedor(); //carpeta = "aux" + elemento.getNivel();  
							folder = carpeta + " =  insFld("+ nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
							iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
							iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
							textJavaScript.append("\n\t" + folder);
							textJavaScript.append("\n\t" + iconoCerrado);
							textJavaScript.append("\n\t" + iconoAbierto);
							nodoActual = carpeta;
							if(elemento.getPcCvePtoventas() != null && !elemento.getPcCvePtoventas().equals("")){
								documento = "docAux = insFld(" + nodoActual + ", gFld('" + elemento.getPcNomPtoventas() + "','javascript:updateTienda(\"" + elemento.getPcCveVendedor() + "-" + elemento.getPcCvePtoventas() + "\")'))";
								iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
								iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
								textJavaScript.append("\n\t" + documento);
								textJavaScript.append("\n\t" + iconoDoc);
								textJavaScript.append("\n\t" + iconoDocCerrado);
							}
							ancla = elemento.getPcCveVendedor();
							//vendedorActual = elemento.getPcCveVendedor(); 
							//nivelActual.push(new Integer(elemento.getNivel()));
							//clavesVendedor.push(new Integer(elemento.getPcCveVendedor()));			
						}
				} else {
					//String nodo = (((Integer)nivelActual.peek()).intValue() == 1 && division)?"foldersTree":"aux" + clavesVendedor.peek();
					String nodo = (elemento.getPcCveSuperior() ==  cveDivision) ? "foldersTree":"aux" + elemento.getPcCveSuperior();
					carpeta = "aux" + elemento.getPcCveVendedor(); //carpeta = "aux" + elemento.getNivel();  
					folder = carpeta + " =  insFld("+ nodo + ", gFld('" + elemento.getPcNombreVendedor()+ "(" + elemento.getPcDescPuesto()+ ")', 'javascript:updateTable(\"" + elemento.getPcCveVendedor()+ "-" + elemento.getPcCvePtoventas() + "\")'))";
					iconoAbierto = carpeta + ".iconSrc = ICONPATH + 'man24.gif'";
					iconoCerrado = carpeta + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
					textJavaScript.append("\n\t" + folder);
					textJavaScript.append("\n\t" + iconoCerrado);
					textJavaScript.append("\n\t" + iconoAbierto);
					nodoActual = carpeta;
					if(elemento.getPcCvePtoventas() != null && !elemento.getPcCvePtoventas().equals("")){
						
						documento = "docAux = insFld(" + nodoActual + ", gFld('" + elemento.getPcNomPtoventas() + "','javascript:updateTienda(\"" + elemento.getPcCveVendedor() + "-" + elemento.getPcCvePtoventas() + "\")'))";
						iconoDoc = "docAux.iconSrc = ICONPATH + 'tienda.gif'";
						iconoDocCerrado = "docAux.iconSrcClosed = ICONPATH + 'tienda.gif'";
						textJavaScript.append("\n\t" + documento);
						textJavaScript.append("\n\t" + iconoDoc);
						textJavaScript.append("\n\t" + iconoDocCerrado);
					}
					//vendedorActual = elemento.getPcCveVendedor(); 
					//nivelActual.push(new Integer(elemento.getNivel()));
					//clavesVendedor.push(new Integer(elemento.getPcCveVendedor()));
				}
			}
					
		}	
		if(!tiendaReporte.isEmpty()){
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
			}
			
			return textJavaScript.toString(); 
	}
		//rsg
		
		//EDA
	/**
			 * Agregando método de historico 
			 * @param mapping
			 * @param form
			 * @param request
			 * @param response
			 * @return
			 */
	
			public ActionForward historico(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response){
				
						if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
						if(request.getSession().getAttribute("jsArray") != null) request.getSession().removeAttribute("jsArray"); 
						if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			 
						try{
							String divisionBuscar = (request.getParameter("pcCveDivisionSeek").equals(""))? null:request.getParameter("pcCveDivisionSeek");				
							String divisionBuscarDesc = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
							//String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");
							String fchHistorico = (request.getParameter("pcFchHistorico").equals("")?null:request.getParameter("pcFchHistorico"));
							String fchDato = "'" + fchHistorico + " 23:59:59',"+"'dd/mm/yyyy hh24:mi:ss'";
						
							////Se va a buscar por claves	
							if(divisionBuscar == null || divisionBuscar.equals("")){
								// Si el usuario no proporciona una division para buscar entonces se dedica a buscar 
								// al vendedor											
								logger.info("se fue a ver lista..buscando por clave del vendedor o por nomina....");
								return verListaHistorico(mapping, form, request, response);				
							}
							else 
							{
								//Buscando por divisiones
												
								((ReporteArbolForm) form).reset(mapping,request);
								((ReporteArbolForm) form).setPcCveDivisionSeek(divisionBuscar);
					
								// Buscamos el Nodo inicial		
								logger.info("antes de llamar a CatalogoFacade.getArbolDirectorDiv(divisionBuscar);");
								DirectorDivVO director  = CatalogoFacade.getArbolDirectorDiv(divisionBuscar);						
								if(director != null){
									//buscamos a que division pertenece el usuario
									int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
									int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
						
									ArrayList arbolReporte = null;
									ArrayList tiendaReporte = null;
													
									//Corremos el Query para obtener el ärbol completo y las tiendas de cada usuario 
									//que las tenga
							
									if(subdivisionUsuario==0){
										
										arbolReporte = AdminCatFacade.getArbolDescendenteHistorico(String.valueOf(director.getPcCveVendedor()),fchDato);
										tiendaReporte = AdminCatFacade.getArbolDescendenteTiendaHistorico(String.valueOf(director.getPcCveVendedor()),fchDato);
										
										if(arbolReporte.size()>0){							  						
										   int nivel = ((ArbolVO)arbolReporte.get(0)).getNivel();							
										   if(nivel != 1){
											  validaNivel(nivel,arbolReporte);																						
											 }
										}	
									}
									else if(director.getPcCveVendedor() == subdivisionUsuario) {
											//BUSCO SI LA DIVISION TIENE SUBDIVISIONES
										//ArrayList Subdivisiones = CatalogoFacade.getSubdivisiones(String.valueOf(director.getPcCveDiv()));																													
								
										arbolReporte = CatalogoFacade.getArbolDescendenteHistorico(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario),fchDato);
										tiendaReporte = CatalogoFacade.getArbolDescendenteTiendaHistorico(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario),fchDato);
										if(arbolReporte.size()>0){							  						
										 int nivel = ((ArbolVO)arbolReporte.get(0)).getNivel();							
										 if(nivel != 1){
											  validaNivel(nivel,arbolReporte);																						
											 }
										  }																					
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
													
									String textJavaScript = generarArbolTiendaModif(arbolReporte,tiendaReporte,director.getPcDescDiv(), director.getPcCveDiv(),true);						
									request.getSession().setAttribute("textJavaScript",textJavaScript);
							   }
								else{
									// No se encontro el usuario incial para la division asi que abortamos el árbol
									request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
									String textJavaScript = generarArbolTienda(new ArrayList(),new ArrayList(),divisionBuscarDesc, true);
									request.getSession().setAttribute("textJavaScript",textJavaScript);
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
				
	public ActionForward verListaHistorico(ActionMapping mapping, ActionForm form, 
					HttpServletRequest request, HttpServletResponse response) {
					
					String control= null;
					if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
					if(request.getSession().getAttribute("jsArray") != null) request.getSession().removeAttribute("jsArray"); 
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			 
					try{
						control=request.getParameter("control");
						String nombreVendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");					
						//String nomClaveEmpleadoReferenciaBuscar = (request.getParameter("pcNomClaveEmpleadoReferenciaSeek").equals(""))? null:request.getParameter("pcNomClaveEmpleadoReferenciaSeek");			
						int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
						int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
						String fchHistorico = (request.getParameter("pcFchHistorico").equals("")?null:request.getParameter("pcFchHistorico"));
						String fchDato = "'" + fchHistorico + " 23:59:59',"+"'dd/mm/yyyy hh24:mi:ss'";
						
				
						ArrayList arbolReporte =null;
						ArrayList tiendaReporte =null;
						if(nombreVendedorBuscar != null){	
						 if(subdivisionUsuario==0){
						
							 arbolReporte =AdminCatFacade.getArbolDescendenteHistorico(nombreVendedorBuscar,fchDato);
							 tiendaReporte =AdminCatFacade.getArbolDescendenteTiendaHistorico(nombreVendedorBuscar,fchDato);
							 		
								
							  if(arbolReporte.size()>0){							  						
								  int nivel = ((ArbolVO)arbolReporte.get(0)).getNivel();							
								  if(nivel != 1){
									   validaNivel(nivel,arbolReporte);																						
									  }
									}															
							   }
							  else{
							  arbolReporte =CatalogoFacade.getArbolDescendenteHistorico(nombreVendedorBuscar,String.valueOf(subdivisionUsuario),fchDato);
							  tiendaReporte =CatalogoFacade.getArbolDescendenteTiendaHistorico(nombreVendedorBuscar,String.valueOf(subdivisionUsuario),fchDato);
								  if(arbolReporte.size()>0){							  						
									 int nivel = ((ArbolVO)arbolReporte.get(0)).getNivel();							
									   if(nivel != 1){
									   validaNivel(nivel,arbolReporte);																						
									   }
									}
							  }
					
							logger.debug("ReporteArbolProxy.verListaHistorico():Dentro del try");
	
							if(arbolReporte==null || arbolReporte.isEmpty()){
								request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
							}
							else{
								
								String jsArray = generarArregloDetalle(arbolReporte,tiendaReporte);
								request.getSession().setAttribute("arbolReporte",arbolReporte);
								request.getSession().setAttribute("jsArray",jsArray);
								String textJavaScript = generarArbolTienda(arbolReporte,tiendaReporte,"Jerarquia", false);
								request.getSession().setAttribute("textJavaScript",textJavaScript);	
							}
						}									 
						//eda
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
		
			public void validaNivel(int nivel, ArrayList arregloArbolReporte){
				int tmpNivel = nivel - 1;
				
				for(int i=0; i < arregloArbolReporte.size(); i++){
					ArbolVO temp = (ArbolVO)arregloArbolReporte.get(i);

					//((ArbolVO)arregloArbolReporte.get(i)).setNivel(nivel-tmpNivel);
					((ArbolVO)arregloArbolReporte.get(i)).setNivel(temp.getNivel()-tmpNivel);
				}			
			}

		//EDA
		
		//reporte excel historico
	public ActionForward excelHistorico(ActionMapping mapping, 
								   ActionForm form,
								   HttpServletRequest request, 
								   HttpServletResponse response) 
		{
		
		
			try{
				if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
				if(request.getSession().getAttribute("jsArray") != null) request.getSession().removeAttribute("jsArray"); 
				if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
				if(request.getSession().getAttribute("liga") != null) request.getSession().removeAttribute("liga");
				
				String divisionBuscar = (request.getParameter("pcCveDivisionSeek").equals(""))? null:request.getParameter("pcCveDivisionSeek");
				String divisionBuscarDesc = (request.getParameter("pcDescDivisionSeek").equals(""))? null:request.getParameter("pcDescDivisionSeek");
				String fchHistorico = (request.getParameter("pcFchHistorico").equals("")?null:request.getParameter("pcFchHistorico"));
				String fchDato = "'" + fchHistorico + " 23:59:59',"+"'dd/mm/yyyy hh24:mi:ss'";
										
				((ReporteArbolForm) form).reset(mapping,request);
				((ReporteArbolForm) form).setPcCveDivisionSeek(divisionBuscar);
					
				// Buscamos el Nodo inicial		
				DirectorDivVO director  = CatalogoFacade.getArbolDirectorDiv(divisionBuscar);
				ArrayList excelDocPersonal = new ArrayList();
				ArrayList excelDocTiendas = new ArrayList();
					
				if(director != null){
					//buscamos a que division pertenece el usuario
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
	
					ArrayList arbolReporte = null;
					ArrayList tiendaReporte = null;
						
					//Corremos el Query para obtener el ärbol completo y las tiendas de cada usuario que las tenga
					if(subdivisionUsuario==0){
						//arbolReporte = AdminCatFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()));
						//tiendaReporte = AdminCatFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()));
//						rsg 16/07/2005
						arbolReporte = AdminCatFacade.getArbolDescendenteHistorico(String.valueOf(director.getPcCveVendedor()), fchDato);
						tiendaReporte = AdminCatFacade.getArbolDescendenteTiendaHistorico(String.valueOf(director.getPcCveVendedor()), fchDato);
					}else{
					
						//arbolReporte = CatalogoFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
						//tiendaReporte = CatalogoFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
						if(director.getPcCveVendedor() == subdivisionUsuario) {
																						
							arbolReporte = CatalogoFacade.getArbolDescendenteHistorico(String.valueOf(director.getPcCveVendedor()), fchDato, String.valueOf(subdivisionUsuario));
							tiendaReporte = CatalogoFacade.getArbolDescendenteTiendaHistorico(String.valueOf(director.getPcCveVendedor()), fchDato, String.valueOf(subdivisionUsuario));

						} else {

							director.setPcCveVendedor(subdivisionUsuario);
							
							divisionUsuario = subdivisionUsuario;								
							arbolReporte = CatalogoFacade.getArbolDescendente(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario), fchDato);
							tiendaReporte = CatalogoFacade.getArbolDescendenteTienda(String.valueOf(director.getPcCveVendedor()),String.valueOf(subdivisionUsuario), fchDato);
						}
					
					} 
												
					if (arbolReporte== null || arbolReporte.isEmpty()){
						//No hay datos en el árbols asi que se aborta
						request.getSession().setAttribute("noData","No se selecciono la división");
					}else{
						// Hay datos asi que  contruimos los arreglos para el Javascript 
						excelDocPersonal = generarReportePersonalExcel(arbolReporte, tiendaReporte,divisionBuscarDesc);
						excelDocTiendas = generarReporteTiendasExcel(arbolReporte, tiendaReporte,divisionBuscarDesc);
					}
					//Contruimos un árbol con o sin datos
				}

				ExcelDoc doc= new ExcelDoc();
			
				if(excelDocPersonal != null && !excelDocPersonal.isEmpty()) {
				
					String[] encabezados = {"División","Clave","Nombre","Puesto","Clave Superior","Superior",
											"Puesto Superior","Fecha Alta","Tipo Canal", "Nomina"};
					String[] metodos = {"pcDescDiv","pcClaveCompleta","pcNombreVendedor","pcDescPuesto",
										"pcCveSuperior","pcNombreSuperior","pcDescPuestoSup","pcFechaAltaStr",
										"pcDescTpCanal", "pcCveEmpRef"};

					ArrayList encabezado = new ArrayList(Arrays.asList(encabezados));
					ArrayList metodo = new ArrayList(Arrays.asList(metodos));
					doc.obtieneMetodos("personal", encabezado, metodo, excelDocPersonal);
				}
				if(excelDocTiendas != null && !excelDocTiendas.isEmpty()) {
				
					String[] encabezados = {"División","Clave","Nombre","Puesto","Clave Superior","Superior","Puesto Superior","Fecha Alta",
											"Número Económico","Punto Ventas","Canal","Tipo Canal"};
					String[] metodos = {"pcDescDiv","pcClaveCompleta","pcNombreVendedor","pcDescPuesto",
										"pcCveSuperior","pcNombreSuperior","pcDescPuestoSup","pcFechaAltaStr",
										"pcCveReferencia","pcNomPtoventas","pcDescCanal","pcDescTpCanal"};
					ArrayList encabezado = new ArrayList(Arrays.asList(encabezados));
					ArrayList metodo = new ArrayList(Arrays.asList(metodos));
					doc.obtieneMetodos("tiendas", encabezado, metodo, excelDocTiendas);
				}

				if(doc.getNumHojas()>0){
					String ruta1 = request.getSession().getServletContext().getRealPath("") + "/excel/";
					String ruta = request.getContextPath() + "/excel/";
					String identificador = Fecha.dateToStringCurrent(Fecha.getHoy());
					String sessionid = request.getSession().getId();
					//request.getSession().setAttribute("liga", ruta + sessionid + identificador + ".xls");
				
					MyFileStructure mf = new MyFileStructure();

					mf.setDirname(ruta1);
					mf.build();
					mf.list();
					
					//escribe datos en disco
					/*FileOutputStream fileOut = new FileOutputStream(ruta1 + sessionid + identificador + ".xls");
					doc.escribirDisco(fileOut);
					fileOut.close();*/
					//envia los datos directo al browser
					response.setContentType("application/vnd.ms-excel");
					ServletOutputStream out = response.getOutputStream();
					doc.escribirArchivo(out);
					out.close();
			
				}else{
					//request.getSession().setAttribute("noData","No se generó el archivo de Reporte");
					request.setAttribute("noData","No se generó el archivo de Reporte");
				}
			}catch(Exception e){
				logger.error("Excel error " + e);
				return mapping.findForward("error");
			}
			//return mapping.findForward("home");
			return null;
		}
		//reporte excel historico
}

