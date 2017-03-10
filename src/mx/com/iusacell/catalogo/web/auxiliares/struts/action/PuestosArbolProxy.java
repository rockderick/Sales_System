/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;

import mx.com.iusacell.catalogo.modelo.Pc_Puestos_NivelVO;
import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import mx.com.iusacell.catalogo.modelo.ArbolVO;

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
 */
public class PuestosArbolProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(PuestosArbolProxy.class);

	/**
	 * Puestos Arbol Proxy
	 * 
	 * 
	 */
	public PuestosArbolProxy() {
	}
	
	/*********************************************************************
	 * Puestos Arbol verLista
	 * 
	 * 
	 *********************************************************************/
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			if(request.getSession().getAttribute("arbolReporte") != null) request.getSession().removeAttribute("arbolReporte"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			String control= null;
			try{
				//logger.debug("PuestosArbolProxy.verLista()");
				
				control=request.getParameter("control");
				//String nombreVendedorBuscar = (request.getParameter("pcCveVendedorSeek").equals(""))? null:request.getParameter("pcCveVendedorSeek");
			
				ArrayList tablaPuestos = CatalogoFacade.getTablaPuestos();

				if(tablaPuestos!= null && !(tablaPuestos.isEmpty()))
					request.getSession().setAttribute("tablaPuestos",tablaPuestos);	

				int nivelInicio = ((ClavesVO) CatalogoFacade.getPuestosMayorNivel()).getClave();
				//logger.debug("nivelInicio:" + nivelInicio);
				
				ArrayList arbolReporte = CatalogoFacade.getPuestosArbolDesc(String.valueOf(nivelInicio));

				if(!arbolReporte.isEmpty()){
					request.getSession().setAttribute("arbolReporte",arbolReporte);
					ArrayList textJavaScript = generarArbol(arbolReporte,"Jerarquia");
					request.getSession().setAttribute("textJS",textJavaScript);						
				}else{
					request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
				}
			}catch(CatalogoSystemException cse){
				logger.error("PuestosArbolProxy:verLista: " + cse.toString());
				request.setAttribute("cse_error", cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("PuestosArbolProxy:verLista: " + e.toString());
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}
	/*********************************************************************
	 * Puestos Arbol agregar
	 * 
	 * 
	 *********************************************************************/
	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				String cvePuestosSup = (request.getParameter("pcCvePuestosSup").equals(""))? null:request.getParameter("pcCvePuestosSup");
				String descPuestos = (request.getParameter("pcDescPuestos").equals(""))? null:request.getParameter("pcDescPuestos");
				
				ClavesVO clave = CatalogoFacade.getPuestoClave();
				Object[] parametros = {
					String.valueOf(clave.getClave()),
					descPuestos
				};
			
				CatalogoFacade.setPuesto(parametros);

				if(cvePuestosSup== null)
					cvePuestosSup = String.valueOf(((ClavesVO) CatalogoFacade.getPuestosMayorNivel()).getClave());

				ClavesVO relacion  = CatalogoFacade.getPuestoNivelClave();
				CatalogoFacade.setPuestoNivel(String.valueOf(relacion.getClave()),String.valueOf(clave.getClave()),cvePuestosSup);
				
				
				form.reset(mapping,request);
			}catch(CatalogoSystemException cse){
				logger.error("PuestosArbolProxy:agregar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("PuestosArbolProxy:agregar: " + e.toString());
				return mapping.findForward("error");
			}
			return verLista(mapping, form, request, response);
	}
	/*********************************************************************
	 * Puestos Arbol eliminar
	 * 
	 * 
	 *********************************************************************/
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

			try{
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				String cvePuestos = (request.getParameter("pcCvePuestos").equals(""))? null:request.getParameter("pcCvePuestos");
				
				ArrayList puestos = new ArrayList();
				String superior = null;
				if(subdivisionUsuario==0){
					puestos = AdminCatFacade.getVendedoresListaPuesto(cvePuestos);
				}else {
					puestos = CatalogoFacade.getVendedoresListaPuesto(cvePuestos, String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
				}
				
				if(puestos.isEmpty() || puestos == null){
					
					ArrayList subordinados = CatalogoFacade.getPuestosNivelxCvePuestoSup(cvePuestos);
					ArrayList jefes = CatalogoFacade.getPuestosNivelxCvePuesto(cvePuestos);

					if(subordinados!= null && !subordinados.isEmpty()){
						Iterator subItera = subordinados.iterator();
						while(subItera.hasNext()){
							Pc_Puestos_NivelVO subordinado = (Pc_Puestos_NivelVO) subItera.next();
							if(jefes!= null && !jefes.isEmpty()){
								Iterator jefeItera = jefes.iterator();
								while(jefeItera.hasNext()){
									Pc_Puestos_NivelVO jefe = (Pc_Puestos_NivelVO) jefeItera.next();
									superior = String.valueOf(jefe.getPcCvePuestoSup());
									ClavesVO clave = CatalogoFacade.getPuestoNivelClave(); 

									CatalogoFacade.setPuestoNivel(String.valueOf(clave.getClave()),String.valueOf(subordinado.getPcCvePuesto()),superior);
									
									CatalogoFacade.deletePuestoNivel(String.valueOf(jefe.getPcCvePuesto()),String.valueOf(jefe.getPcCvePuestoSup()));
								}
							}
							
							CatalogoFacade.deletePuestoNivel(String.valueOf(subordinado.getPcCvePuesto()),String.valueOf(subordinado.getPcCvePuestoSup()));
						}
					}
					
					CatalogoFacade.deletePuesto(cvePuestos);
					form.reset(mapping,request);
				}else{
					request.getSession().setAttribute("noData","No se puede borrar este Puesto porque esta en uso");
				}
			}catch(CatalogoSystemException cse){
				logger.error("PuestosArbolProxy:eliminar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("PuestosArbolProxy:eliminar: " + e.toString());
				return mapping.findForward("error");
			}
		
			return verLista(mapping, form, request, response);
	}
	/*********************************************************************
	 * Puestos Arbol asignar
	 * 
	 * 
	 *********************************************************************/
	public ActionForward asignar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

			try{
				String cvePuestos = (request.getParameter("pcCvePuestos").equals(""))? null:request.getParameter("pcCvePuestos");
				String cvePuestosSup = (request.getParameter("pcCvePuestosSup").equals(""))? null:request.getParameter("pcCvePuestosSup");
				String control = (request.getParameter("control").equals(""))? null:request.getParameter("control");
					
				if(control == null){
					return mapping.findForward("home");
				}else if(control.equals("insertar")){
					Pc_Puestos_NivelVO relacion = CatalogoFacade.getPuestoNivel(cvePuestos,cvePuestosSup);
					if(relacion==null){
						ClavesVO clave = CatalogoFacade.getPuestoNivelClave();
						CatalogoFacade.setPuestoNivel(String.valueOf(clave.getClave()),cvePuestos,cvePuestosSup);
					} 						
					request.getSession().setAttribute("noData","Ya existe la relacion que se intenta eliminar");
				}else if(control.equals("borrar")){
					CatalogoFacade.deletePuestoNivel(cvePuestos,cvePuestosSup);
				}
				form.reset(mapping,request);
			}catch(CatalogoSystemException cse){
				logger.error("PuestosArbolProxy:asignar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("PuestosArbolProxy:asignar: " + e.toString());
				return mapping.findForward("error");
			}
			return verLista(mapping, form, request, response);
	}
	/*********************************************************************
	 * Puestos Arbol modificar
	 * 
	 * 
	 *********************************************************************/
	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				Object[] parametros = {
						request.getParameter("pcCvePuestos"),
						request.getParameter("pcDescPuestos")
					};
					
				CatalogoFacade.changePuesto(parametros);
				form.reset(mapping,request);
			}catch(CatalogoSystemException cse){
				logger.error("PuestosArbolProxy:modificar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("PuestosArbolProxy:modificar: " + e.toString());
				return mapping.findForward("error");
			}
			return verLista(mapping, form, request, response);
	}
	/*********************************************************************
	 * Puestos Arbol consultar
	 * 
	 * 
	 *********************************************************************/
	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			ArrayList tablaPuestos = new ArrayList();
		   
		   	logger.info("tablapuestos ");
		   
			tablaPuestos = CatalogoFacade.getTablaPuestos();
			
			request.getSession().setAttribute("tablaPuestos",tablaPuestos);	
			return mapping.findForward("home");
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
				folder = "foldersTree = gFld(\'<i>Niveles</i>\', \'\',\'\')"; 								
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
						folder = "foldersTree = gFld(\'<i>Niveles</i>\', \'\',\'\')"; 								
						iconoCerrado = "foldersTree.iconSrcClosed = ICONPATH + 'homeplus24.gif'";
						iconoAbierto = "foldersTree.iconSrc = ICONPATH + 'home24.gif'";
						textJavaScript.add(folder);
						textJavaScript.add(iconoCerrado);
						textJavaScript.add(iconoAbierto);
						folder = "aux" + elemento.getNivel() + " =  insFld(foldersTree, gFld(\'" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" + elemento.getPcCvePuesto() + "\")','javascript:updateList(\"" + elemento.getPcCvePuesto() + "\")'))";
						iconoAbierto = "aux" + elemento.getNivel() + ".iconSrc = ICONPATH + 'group24.gif'";
						iconoCerrado = "aux" + elemento.getNivel() + ".iconSrcClosed = ICONPATH + 'groupplus24.gif'";
						textJavaScript.add(folder);
						textJavaScript.add(iconoCerrado);
						textJavaScript.add(iconoAbierto);
						nivelActual.push(new Integer(elemento.getNivel()));
					}else if( (nivelActual.peek().equals(new Integer((elemento.getNivel()-1))))){
						folder = "aux" + elemento.getNivel() + " =  insFld(aux" + nivelActual.peek() + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" + elemento.getPcCvePuesto() + "\")','javascript:updateList(\"" + elemento.getPcCvePuesto() + "\")'))";
						iconoAbierto = "aux" + elemento.getNivel() + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = "aux" + elemento.getNivel() + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.add(folder);
						textJavaScript.add(iconoCerrado);
						textJavaScript.add(iconoAbierto);
						nivelActual.push(new Integer(elemento.getNivel()));
					}else if( (nivelActual.peek().equals(new Integer((elemento.getNivel()))))){
						nivelActual.pop();
						folder = "aux" + elemento.getNivel() + " =  insFld(aux" + nivelActual.peek() + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(" + elemento.getPcCvePuesto() + ")','javascript:updateList(\"" + elemento.getPcCvePuesto() + "\")'))";
						iconoAbierto = "aux" + elemento.getNivel() + ".iconSrc = ICONPATH + 'man24.gif'";
						iconoCerrado = "aux" + elemento.getNivel() + ".iconSrcClosed = ICONPATH + 'manplus24.gif'";
						textJavaScript.add(folder);
						textJavaScript.add(iconoCerrado);
						textJavaScript.add(iconoAbierto);
						nivelActual.push(new Integer(elemento.getNivel()));
					}else{
						int current_level=0;
						while(elemento.getNivel() <= ((Integer)nivelActual.peek()).intValue()){
							nivelActual.pop();
							current_level = ((Integer)nivelActual.peek()).intValue();
						}
						folder = "aux" + elemento.getNivel() + " =  insFld(aux" + current_level + ", gFld('" + elemento.getPcDescPuesto()+ "', 'javascript:updateTable(\"" + elemento.getPcCvePuesto() + "\")','javascript:updateList(\"" + elemento.getPcCvePuesto() + "\")'))";
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

}
