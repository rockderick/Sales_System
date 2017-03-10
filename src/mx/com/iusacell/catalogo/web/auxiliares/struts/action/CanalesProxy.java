/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import java.util.ArrayList;

import mx.com.iusacell.catalogo.dao.CatalogoFacade;

import mx.com.iusacell.catalogo.modelo.Pc_CanalVO;

import mx.com.iusacell.catalogo.modelo.ClavesVO;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;


import mx.com.iusacell.catalogo.utilerias.DigitoVerificador;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CanalesProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {


	public CanalesProxy() {
	}
	
	public ActionForward home(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {

			request.getSession().setAttribute("Clave", "Inicio");
			return mapping.findForward("home");
	}
	
	public ActionForward generarClave(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			
			String pcGenerar = request.getParameter("pcGenerar");
			
			if(pcGenerar.equals("SI")){
				request.getSession().setAttribute("Clave", "GeneradaPorSistema");	
			} else if (pcGenerar.equals("NO")){
				request.getSession().setAttribute("Clave", "GeneradaPorUsuario");
			}
		
			return mapping.findForward("agregar");
	}	
	
	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
		
			return mapping.findForward("agregar");
	}

	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			ArrayList tablaCanales = new ArrayList();
		
			tablaCanales = CatalogoFacade.getTablaCanales();
			
			request.getSession().setAttribute("tablaCanales",tablaCanales);	
			return mapping.findForward("home");
	}

	public ActionForward agregarGuardar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			String digito = null;
			ClavesVO clave = new ClavesVO();
			String claveCanal = null;
			Pc_CanalVO canal = null; 
			
			canal = CatalogoFacade.existeClaveCanal(request.getParameter("pcCveCanal"));
			
			
			//EXISTE CLAVE DE CANAL QUE SE QUIERE AÑADIR
			if(canal == null){
				
				try{
					
					if(request.getSession().getAttribute("Clave").equals("GeneradaPorSistema")){
						clave = CatalogoFacade.getCanalClave();
					} else if(request.getSession().getAttribute("Clave").equals("GeneradaPorUsuario")){
						claveCanal = request.getParameter("pcCveCanal");
						clave.setClave(Integer.parseInt(claveCanal));
					}
					
					CanalesForm forma = (CanalesForm) form;
					Object[] parametros = {
							String.valueOf(clave.getClave()),
							request.getParameter("pcDescCanal"),
							request.getParameter("pcCveTpCanal"),
							request.getParameter("pcFecha")
						};
						
						
					DigitoVerificador digVerif = new DigitoVerificador();
					digVerif.setDigitoVerificador((long)clave.getClave());
					digito = String.valueOf(digVerif.getDigitoVerificador());
						
						try{
							CatalogoFacade.agregarCanal(parametros);
							CatalogoFacade.agregarVendedor(String.valueOf(clave.getClave()),request.getParameter("pcDescCanal"),digito,request.getParameter("pcFecha"));
							forma.reset(mapping,request);
						}catch(CatalogoSystemException cse){
							cse.printStackTrace();
							return mapping.findForward("error");
						}
				}catch(Exception e){
					e.printStackTrace();
					return mapping.findForward("error");
				}
				
				request.setAttribute("mensaje","Se ha agregado exitosamente el Canal");		
			
				return mapping.findForward("home");
			}
			else if(canal != null){
				request.setAttribute("mensaje","Ya existe la Clave del Canal en el Sistema");
				return mapping.findForward("agregar");		
			}
			
			return null;		
	}
	
	public ActionForward modificarGuardar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			String pcCveCanal = null;
			String pcDescCanal = null;
			pcCveCanal = request.getParameter("pcCveCanal");
			pcDescCanal = request.getParameter("pcDescCanal");
				

			CatalogoFacade.modificarCanal(pcCveCanal,pcDescCanal);


			
			request.setAttribute("mensaje","Se ha modificado exitosamente el Canal");		
		
			return mapping.findForward("home");
	}

	public ActionForward eliminarGuardar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			
			String pcCveCanal = null;
			String pcFecha = null;
			pcCveCanal = request.getParameter("pcCveCanal");
			pcFecha = request.getParameter("pcFecha");
			
			
			//eliminar las relaciones canal-puntos de venta
			CatalogoFacade.eliminarPuntosVentaCanal(pcCveCanal);
			
			//eliminar en pc_vendedores
			CatalogoFacade.eliminarCanalComoVendedor(pcCveCanal,pcFecha);
			
			//eliminar el canal
			CatalogoFacade.eliminarCanal(pcCveCanal,pcFecha);
			
			request.setAttribute("mensaje","Se ha eliminado exitosamente el Canal");	
		
			return mapping.findForward("home");
	}

	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

		
		String pcCveTpCanal = null;
		Pc_CanalVO canal = new Pc_CanalVO();
		
		pcCveTpCanal = request.getParameter("pcCveCanal");
		
		canal = CatalogoFacade.obtenerCanal(pcCveTpCanal);

		
		request.setAttribute("PcCveCanal",canal.getPcCveCanal());
		request.setAttribute("PcDescCanal",canal.getPcDescCanal());		
		
		return mapping.findForward("modificar");
	}
	
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

		
		String pcCveTpCanal = null;
		Pc_CanalVO canal = new Pc_CanalVO();
		
		pcCveTpCanal = request.getParameter("pcCveCanal");
		
		canal = CatalogoFacade.obtenerCanal(pcCveTpCanal);

		
		request.setAttribute("PcCveCanal",canal.getPcCveCanal());
		request.setAttribute("PcDescCanal",canal.getPcDescCanal());		
		
		return mapping.findForward("eliminar");
	}

	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			return mapping.findForward("home");
	}


}
