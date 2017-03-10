/*
 * Created on Feb 22, 2005
 *
 */
package mx.com.iusacell.catalogo.web.puntosventa.struts.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.modelo.PcEstadosVO;
import mx.com.iusacell.catalogo.modelo.PcMarcasVO;

import mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO;
import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO;
import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;


import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

import mx.com.iusacell.catalogo.utilerias.DigitoVerificador;

import mx.com.iusacell.rfcSap.sapDao.SapPuntosVentaDao;

import org.apache.struts.action.ActionForm; 
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 * @author Dvazqueza
 *
 * Paquete : mx.com.iusacell.catalogo.web.puntosventa.struts.action
 * Proyecto : AdminCatalogoWeb
 *
 */
public class PuntosVentaProxy extends mx.com.iusacell.catalogo.utilerias.Proxy {

	protected static final Logger logger = Logger.getLogger(PuntosVentaProxy.class);

	/**
	 * PuntosVentaProxy
	 */
	public PuntosVentaProxy() {
	}

	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
			ArrayList tablaPuestos = new ArrayList();
			if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta");
		
			tablaPuestos = CatalogoFacade.getTablaPuestos();
			
			request.getSession().setAttribute("tablaPuestos",tablaPuestos);	
			return mapping.findForward("home");
	}

	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			Pc_Punto_VentasVO detallePuntoVenta = null;
			if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
			if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			int Usuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveUsuario();
			String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();

			Pc_VendedoresVO vendedor = null;
			
			try{
				
				String tipoRegistro = request.getParameter("pcTipoRegistro") == null ? "":request.getParameter("pcTipoRegistro");
				ClavesVO clave = new ClavesVO();
				String digito = "0";
				String ClavePtoVentas = "";
				boolean bandera = false;
				
				//agregado 15/09/2005 para generar digito verificador para la tienda
				if(tipoRegistro.equals("AUT")) {
					clave = CatalogoFacade.getPuntosVentaClave();
					DigitoVerificador digVerif = new DigitoVerificador();
				    digVerif.setDigitoVerificador((long)clave.getClave());
				    digito = String.valueOf(digVerif.getDigitoVerificador());
					ClavePtoVentas = String.valueOf(clave.getClave());
					bandera = true;
				}
				else {
				
					ClavePtoVentas = request.getParameter("pcCvePtoventas");
					digito = request.getParameter("pcDigVerif");
					ArrayList Existe = CatalogoFacade.getPuntosVentaCAT_CSI(ClavePtoVentas);
					if(Existe == null || Existe.size() > 0)
						bandera = true;
					else
						bandera = false;
				}	
				bandera = true;
				
				
				//validar que el punto de venta exista en CAT_CSI,
				//siempre y cuando sea un un ALTA de un punto de venta que Exista en PVS
				 
				if(bandera) {
				
				
					Object[] parametros = {
							ClavePtoVentas,
							request.getParameter("pcCveReferencia"),
							request.getParameter("pcCveCanal"),
							request.getParameter("pcNomPtoventas"), 
							request.getParameter("pcCntlInt"),
							request.getParameter("pcCveSubdiv"),
							request.getParameter("pcRegion"),
							request.getParameter("pcDireccion"),
							request.getParameter("pcColonia"),
							request.getParameter("pcCp"),
							request.getParameter("pcTel"),
							request.getParameter("pcFax"),//agregado 15/06/09
						    request.getParameter("pcCiudad"),
						    request.getParameter("pcEstado"),
							digito,
							user,
						    request.getParameter("pcFchIniOp")
						};
						
						
					CatalogoFacade.setPuntoVenta(parametros);
					
					/*ALTA SAP*/
					detallePuntoVenta = AdminCatFacade.getPuntoVenta(ClavePtoVentas);
										
					Pc_Punto_VentasVO puntoDeVenta  = new Pc_Punto_VentasVO();
					puntoDeVenta.setPcCvePtoventas(ClavePtoVentas);
					puntoDeVenta.setPcNomPtoventas(detallePuntoVenta.getPcNomPtoventas());
					puntoDeVenta.setPcDescEstado(detallePuntoVenta.getPcDescEstado());
					puntoDeVenta.setPcDireccion(detallePuntoVenta.getPcDireccion());
					puntoDeVenta.setPcDescCiudad(detallePuntoVenta.getPcDescCiudad());
					puntoDeVenta.setPcCp(detallePuntoVenta.getPcCp());
					puntoDeVenta.setPcDescCanal(detallePuntoVenta.getPcDescCanal());
					puntoDeVenta.setPcDescDiv(detallePuntoVenta.getPcDescDiv());
					puntoDeVenta.setPcCveReferencia(detallePuntoVenta.getPcCveReferencia());
								
					logger.info("ClavePtoVentas="+ClavePtoVentas);			
					logger.info("detallePuntoVenta.getPcNomPtoventas()="+detallePuntoVenta.getPcNomPtoventas());
					logger.info("detallePuntoVenta.getPcDescEstado()="+detallePuntoVenta.getPcDescEstado());
					logger.info("detallePuntoVenta.getPcDireccion()="+detallePuntoVenta.getPcDireccion());
					logger.info("detallePuntoVenta.getPcDescCiudad()="+detallePuntoVenta.getPcDescCiudad());
					logger.info("detallePuntoVenta.getPcDescCanal()="+detallePuntoVenta.getPcDescCanal());
					logger.info("detallePuntoVenta.getPcDescDiv()="+detallePuntoVenta.getPcDescDiv());
												
				
					SapPuntosVentaDao dao =new SapPuntosVentaDao();
					dao.agregarPVT(puntoDeVenta);
					///////////////////	
					ArrayList marcas = (ArrayList)request.getSession().getAttribute("marcas");
					
					if(marcas != null){
						for(int i = 0; i < marcas.size(); i++)
						{
							PcMarcasVO marca = new PcMarcasVO();
							marca = (PcMarcasVO)marcas.get(i);
							
							CatalogoFacade.setPuntoVentaMarcas(ClavePtoVentas,new Integer(marca.getPcCveMarca()).toString());
						}	
						marcas.removeAll(marcas);	
					}
				}
				else
					request.setAttribute("noData", "ERROR al agregar el Punto de Venta: EL Punto de Venta no existe en el Portal de Ventas y Servicios");
				
				((PuntosVentaForm) form).reset(mapping,request);
				((PuntosVentaForm) form).setPcCveCanalSeek(request.getParameter("pcCveCanal"));
				((PuntosVentaForm) form).setPcCveTipoCanalSeek(request.getParameter("pcCveTipoCanal"));
				((PuntosVentaForm) form).setPcCveDivSeek(Integer.parseInt(request.getParameter("pcCveSubdiv")));
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				
				ArrayList tablaPuntosVenta  = CatalogoFacade.getPuntosVentaTable(request.getParameter("pcCveSubdiv"),request.getParameter("pcCveTipoCanal"),request.getParameter("pcCveCanal"),null,String.valueOf(divisionUsuario),null);
				
				
				if(!tablaPuntosVenta.isEmpty()){
					request.getSession().setAttribute("tablaPuntosVenta",tablaPuntosVenta);
				}

			}catch(CatalogoSystemException cse){
				logger.error("Error en agregar: " + cse.toString());
				cse.printStackTrace();
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error en agregar: " + e.toString());
				e.printStackTrace();
				return mapping.findForward("error");
			}
			String cveRegion = request.getParameter("pcRegion");
			request.setAttribute("cveRegion",cveRegion);
			buscaRegion(request,response);
			String cveCiudad = request.getParameter("pcCiudad");
			request.setAttribute("cveCiudad",cveCiudad);
			buscaCiudad(request,response);
			return mapping.findForward("home");
	}

	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
			boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
			boolean borra = false;
			boolean indFch = false;
			Pc_Punto_VentasVO detallePuntoVenta = null;
			Pc_Punto_VentasVO ptoVtaVO = null;
			String confirmar = null;
			ArrayList lstPdvXVendedor = null;
			
			// Elimina atributos residentes en Session
			if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
			if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			try {
				String cvePtoventas = (request.getParameter("pcCvePtoventas").equals(""))? null:request.getParameter("pcCvePtoventas");
				logger.info("Eliminando PDV = " + cvePtoventas);
				
				if(request.getSession().getAttribute("confirmar") != null) {
					confirmar = (String)request.getSession().getAttribute("confirmar");
				}
				int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();	
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();	
				
				if (confirmar != null) 
					borra = true;
				
				logger.info("pasa1");
				logger.info("subdivision = " + subdivisionUsuario);
				
				if(subdivisionUsuario==0 || encontrado){
					logger.info("pasa2");
					lstPdvXVendedor = AdminCatFacade.getRelacionesXPuntoVenta(cvePtoventas);
				}
				if(lstPdvXVendedor == null || lstPdvXVendedor.isEmpty() || borra) {
					logger.info("pasa3");
					ptoVtaVO = AdminCatFacade.getPuntoVenta(cvePtoventas);
					if(ptoVtaVO!=null){
						logger.info("pasa4");
						indFch = confirmarFechaBaja(ptoVtaVO);					
					}
					if(indFch){
						logger.info("pasa5");
						 CatalogoFacade.deletePuntosVenta2(cvePtoventas,user);
				    }else{   
						logger.info("pasa6");
					     CatalogoFacade.deletePuntosVenta(cvePtoventas,user); 
					}
				
					/*Baja SAP*/
					detallePuntoVenta = AdminCatFacade.getPuntoVenta(cvePtoventas);
					Pc_Punto_VentasVO puntoDeVenta  = new Pc_Punto_VentasVO();
					puntoDeVenta.setPcCvePtoventas(cvePtoventas);
					puntoDeVenta.setPcNomPtoventas(detallePuntoVenta.getPcNomPtoventas());
					puntoDeVenta.setPcDescEstado(detallePuntoVenta.getPcDescEstado());
					puntoDeVenta.setPcDireccion(detallePuntoVenta.getPcDireccion());
					puntoDeVenta.setPcDescCiudad(detallePuntoVenta.getPcDescCiudad());
					puntoDeVenta.setPcCp(detallePuntoVenta.getPcCp());
					puntoDeVenta.setPcCveReferencia(detallePuntoVenta.getPcCveReferencia());
			        puntoDeVenta.setPcDescCanal(detallePuntoVenta.getPcDescCanal());
					puntoDeVenta.setPcDescDiv(detallePuntoVenta.getPcDescDiv());
								
					SapPuntosVentaDao dao =new SapPuntosVentaDao();
					dao.eliminarPVT(puntoDeVenta);
					//////////////
				
					((PuntosVentaForm)form).reset(mapping,request);
					request.getSession().setAttribute("noData","El Punto de Ventas "+ cvePtoventas +" ha sido dado de BAJA.");
					if(borra)request.getSession().removeAttribute("confirmar");			
				} else {
					logger.info("pasa7");
					int longitud = lstPdvXVendedor.size();
					String longitudRelaciones = String.valueOf(longitud);
					if(subdivisionUsuario==0 || encontrado){
						detallePuntoVenta = AdminCatFacade.getPuntoVenta(cvePtoventas);
					}else{
						detallePuntoVenta = CatalogoFacade.getPuntoVenta(cvePtoventas,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
					}
					
					((PuntosVentaForm)form).setPcCveRegion(detallePuntoVenta.getPcCveRegion());
					((PuntosVentaForm)form).setPcCveDiv(detallePuntoVenta.getPcCveDiv());
					((PuntosVentaForm)form).setPcCveSubdiv(detallePuntoVenta.getPcCveSubdiv());
					((PuntosVentaForm)form).setPcCveCanal(detallePuntoVenta.getPcCveCanal());
					((PuntosVentaForm)form).setPcCveTipoCanal(detallePuntoVenta.getPcCveTpCanal());
					((PuntosVentaForm)form).setPcCvePtoventas(detallePuntoVenta.getPcCvePtoventas());
					
					((PuntosVentaForm)form).setPcNomPtoventas(detallePuntoVenta.getPcNomPtoventas());
					((PuntosVentaForm)form).setPcCveReferencia(detallePuntoVenta.getPcCveReferencia());
					((PuntosVentaForm)form).setPcCntlInt(detallePuntoVenta.getPcCntlInt());

					((PuntosVentaForm)form).setPcCveDivSeek(0);
					((PuntosVentaForm)form).setPcCveTipoCanalSeek("");
					((PuntosVentaForm)form).setPcCveCanalSeek("");
					((PuntosVentaForm)form).setPcNomPtoventasSeek("");

					request.setAttribute("numeroRels",longitudRelaciones);
					request.getSession().setAttribute("detallePuntoVenta",detallePuntoVenta);
				}
			}catch(CatalogoSystemException cse){
				logger.error("Error al eliminar PDV: " + cse.toString());
				logger.error("[ERROR] No se pudo eliminar el PDV: " + cse.getCause());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error al eliminar PDV: " + e.toString());
				logger.error("[ERROR] No se pudo eliminar el PDV: " + e.getCause());
				return mapping.findForward("error");
			}
			buscaRegion(request,response);
			request.setAttribute("cveRegion",request.getParameter("pcRegion"));
			return mapping.findForward("home");
	}

	public ActionForward reactivar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
				String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
								boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
				if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
				if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta"); 
				if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
				try{
					String cvePtoventas = (request.getParameter("pcCvePtoventas").equals(""))? null:request.getParameter("pcCvePtoventas");
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();	
					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();	
					  
					CatalogoFacade.reactivarPuntosVenta(cvePtoventas, user);
					((PuntosVentaForm)form).reset(mapping,request);
					request.getSession().setAttribute("noData","El Punto de Ventas "+ cvePtoventas +" ha sido reactivado.");
					Pc_Punto_VentasVO detallePuntoVenta = null;
				
					if(subdivisionUsuario==0 || encontrado){
						detallePuntoVenta = AdminCatFacade.getPuntoVenta(cvePtoventas);
					}else{
						detallePuntoVenta = CatalogoFacade.getPuntoVenta(cvePtoventas,String.valueOf(subdivisionUsuario),String.valueOf(divisionUsuario));
					}
						
					/*REACTIVAR MODIFICAR SAP*/
					detallePuntoVenta = AdminCatFacade.getPuntoVenta(cvePtoventas);
					Pc_Punto_VentasVO puntoDeVenta  = new Pc_Punto_VentasVO();
					puntoDeVenta.setPcCvePtoventas(cvePtoventas);
					puntoDeVenta.setPcNomPtoventas(detallePuntoVenta.getPcNomPtoventas());
					puntoDeVenta.setPcDescEstado(detallePuntoVenta.getPcDescEstado());
					puntoDeVenta.setPcDireccion(detallePuntoVenta.getPcDireccion());
					puntoDeVenta.setPcDescCiudad(detallePuntoVenta.getPcDescCiudad());
					puntoDeVenta.setPcCp(detallePuntoVenta.getPcCp());
					puntoDeVenta.setPcCveReferencia(detallePuntoVenta.getPcCveReferencia());
					puntoDeVenta.setPcDescCanal(detallePuntoVenta.getPcDescCanal());
					puntoDeVenta.setPcDescDiv(detallePuntoVenta.getPcDescDiv());
												
					SapPuntosVentaDao dao =new SapPuntosVentaDao();
					dao.modificarPVT(puntoDeVenta);
					//////////////
					
					((PuntosVentaForm)form).setPcCveRegion(detallePuntoVenta.getPcCveRegion());
					((PuntosVentaForm)form).setPcCveDiv(detallePuntoVenta.getPcCveDiv());
					((PuntosVentaForm)form).setPcCveSubdiv(detallePuntoVenta.getPcCveSubdiv());
					((PuntosVentaForm)form).setPcCveCanal(detallePuntoVenta.getPcCveCanal());
					((PuntosVentaForm)form).setPcCveTipoCanal(detallePuntoVenta.getPcCveTpCanal());
					((PuntosVentaForm)form).setPcCvePtoventas(detallePuntoVenta.getPcCvePtoventas());
				
					((PuntosVentaForm)form).setPcNomPtoventas(detallePuntoVenta.getPcNomPtoventas());
					((PuntosVentaForm)form).setPcCveReferencia(detallePuntoVenta.getPcCveReferencia());
					((PuntosVentaForm)form).setPcCntlInt(detallePuntoVenta.getPcCntlInt());

					((PuntosVentaForm)form).setPcCveDivSeek(0);
					((PuntosVentaForm)form).setPcCveTipoCanalSeek("");
					((PuntosVentaForm)form).setPcCveCanalSeek("");
					((PuntosVentaForm)form).setPcNomPtoventasSeek("");

					request.getSession().setAttribute("detallePuntoVenta",detallePuntoVenta);
				}catch(CatalogoSystemException cse){
					logger.error("Error en eliminar: " + cse.toString());
					return mapping.findForward("error");
				}catch(Exception e){
					logger.error("Error en eliminar: " + e.toString());
					return mapping.findForward("error");
				}
				buscaRegion(request,response);
				request.setAttribute("cveRegion",request.getParameter("pcRegion"));
				return mapping.findForward("home");
		}   

	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			PuntosVentaForm forma = (PuntosVentaForm) form;
			Pc_Punto_VentasVO detallePuntoVenta = null;
			String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
			if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
			if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta"); 
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				Object[] parametros = {
					request.getParameter("pcCvePtoventas"),
					request.getParameter("pcCveCanal"),
					request.getParameter("pcRegion"),
					request.getParameter("pcNomPtoventas"), 
					request.getParameter("pcCveReferencia"),
					request.getParameter("pcCntlInt"),
					request.getParameter("pcCveSubdiv"),
					request.getParameter("pcDireccion"),
					request.getParameter("pcColonia"),
					request.getParameter("pcCp"),
					request.getParameter("pcTel"),
					request.getParameter("pcFax"),//agregado 15/06/2005
					request.getParameter("pcCiudad"),
					request.getParameter("pcEstado"),
					user,request.getParameter("pcDescCiudad")
					};
					
				logger.info("PARAMETROS: pcCiudad = " + 
					request.getParameter("pcCiudad") + ", pcDescCiudad = " + 
					request.getParameter("pcDescCiudad"));
					
				CatalogoFacade.changePuntosVenta(parametros);
				
				/*REACTIVAR MODIFICAR SAP*/
				detallePuntoVenta = AdminCatFacade.getPuntoVenta((String)request.getParameter("pcCvePtoventas"));
				Pc_Punto_VentasVO puntoDeVenta  = new Pc_Punto_VentasVO();
				puntoDeVenta.setPcCvePtoventas((String)request.getParameter("pcCvePtoventas"));
				puntoDeVenta.setPcNomPtoventas(detallePuntoVenta.getPcNomPtoventas());
				puntoDeVenta.setPcDescEstado(detallePuntoVenta.getPcDescEstado());
				puntoDeVenta.setPcDireccion(detallePuntoVenta.getPcDireccion());
				puntoDeVenta.setPcDescCiudad(detallePuntoVenta.getPcDescCiudad());
				puntoDeVenta.setPcCp(detallePuntoVenta.getPcCp());
				puntoDeVenta.setPcCveReferencia(detallePuntoVenta.getPcCveReferencia());
				puntoDeVenta.setPcDescCanal(detallePuntoVenta.getPcDescCanal());
				puntoDeVenta.setPcDescDiv(detallePuntoVenta.getPcDescDiv());
								
				SapPuntosVentaDao dao =new SapPuntosVentaDao();
				dao.modificarPVT(puntoDeVenta);
				////////////////////
				
				ArrayList marcas = (ArrayList)request.getSession().getAttribute("marcas");
					
				if(marcas != null){
					for(int i = 0; i < marcas.size(); i++)
					{
						PcMarcasVO marca = new PcMarcasVO();
						marca = (PcMarcasVO)marcas.get(i);
							
						CatalogoFacade.setPuntoVentaMarcas(request.getParameter("pcCvePtoventas"),new Integer(marca.getPcCveMarca()).toString());
					}	
					marcas.removeAll(marcas);	
				}
				
				String canal = request.getParameter("pcCveCanal");
				String tipo_canal =  request.getParameter("pcCveTipoCanal");
				int subdivision = Integer.parseInt(request.getParameter("pcCveSubdiv"));
				forma.reset(mapping,request);
				forma.setPcCveCanalSeek(canal);
				forma.setPcCveTipoCanalSeek(tipo_canal);
				forma.setPcCveDivSeek(subdivision);

				ArrayList param = new ArrayList();
					
				param.add(request.getParameter("pcCveSubdiv"));
				param.add(request.getParameter("pcCveTipoCanal"));
				param.add(request.getParameter("pcCveCanal"));
				param.add(null);
				int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
				ArrayList tablaPuntosVenta  = CatalogoFacade.getPuntosVentaTable(request.getParameter("pcCveSubdiv"),request.getParameter("pcCveTipoCanal"),request.getParameter("pcCveCanal"),null, String.valueOf(divisionUsuario),null);
				if(!tablaPuntosVenta.isEmpty()){
					request.getSession().setAttribute("tablaPuntosVenta",tablaPuntosVenta);
				}
			}catch(CatalogoSystemException cse){
				logger.error("Error en modificar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error en modificar: " + e.toString());
				return mapping.findForward("error");
			}
			buscaRegion(request,response);
			request.setAttribute("cveRegion",request.getParameter("pcRegion"));
			String cveCiudad = request.getParameter("pcCiudad");
			request.setAttribute("cveCiudad",cveCiudad);

			return mapping.findForward("home");
	}

	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			String control= null;
			 
			try{
				control=request.getParameter("control");
				
				if(control == null){
					return mapping.findForward("home");
				}else if(control.equals("tipo_canal")){
					((PuntosVentaForm) form).setPcCvePtoventas(request.getParameter("pcCvePtoventas"));
					
					String digito = request.getParameter("pcDigVerif");
						if(digito == null || digito.equals(""))
							digito ="0";
					((PuntosVentaForm) form).setPcDigVerif(Integer.parseInt(digito));
					
					String tipoCanal = request.getParameter("pcCveTipoCanal");
					   if(tipoCanal == null || tipoCanal.equals(""))
					       tipoCanal ="";
					((PuntosVentaForm) form).setPcCveTipoCanal(tipoCanal);  
					String cveRegion = request.getParameter("pcRegion");
				if(!cveRegion.equals("-- Seleccione una division --"))
					request.setAttribute("cveRegion", cveRegion);
					return mapping.findForward("home");
				}else if(control.equals("tipo_canal_seek")){
					String cveRegion = request.getParameter("pcRegion");
				if(!cveRegion.equals("-- Seleccione una division --"))
					request.setAttribute("cveRegion", cveRegion);
					return mapping.findForward("home");
				}else if(control.equals("buscar")){
					if(request.getSession().getAttribute("tablaPuntosVenta") != null) request.getSession().removeAttribute("tablaPuntosVenta"); 
					if(request.getSession().getAttribute("detallePuntoVenta") != null) request.getSession().removeAttribute("detallePuntoVenta");
					if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

					String divisionBuscar = (request.getParameter("pcCveDivSeek").equals(""))? null:request.getParameter("pcCveDivSeek");
					String tipoCanalBuscar = (request.getParameter("pcCveTipoCanalSeek").equals(""))? null:request.getParameter("pcCveTipoCanalSeek");
					String canalBuscar = (request.getParameter("pcCveCanalSeek").equals(""))? null:request.getParameter("pcCveCanalSeek");
					String vendedorBuscar = (request.getParameter("pcCvePtoventasSeek").equals(""))? null:request.getParameter("pcCvePtoventasSeek");
					String nombreVendedorBuscar = (request.getParameter("pcNomPtoventasSeek").equals(""))? null:request.getParameter("pcNomPtoventasSeek");
					String pcNumEconomico = (request.getParameter("pcCveNumEconomico").equals(""))? null:request.getParameter("pcCveNumEconomico");
					
					
					
					int subdivisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveSubdiv();
					int divisionUsuario = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcCveDiv();
					String user = ((UserSessionVO) request.getSession().getAttribute("USER")).getPcUserid();
					boolean encontrado = ((UserSessionVO) request.getSession().getAttribute("USER")).isPcValidadoConfig();
					if(divisionBuscar == null){
						if(subdivisionUsuario!= 0 || !encontrado) divisionBuscar= String.valueOf(subdivisionUsuario);
					}

					int queryId = 0;

					if(vendedorBuscar == null && pcNumEconomico == null){
						
						((PuntosVentaForm) form).reset(mapping,request);
						
						if(divisionBuscar == null || divisionBuscar.equals("")){
							((PuntosVentaForm) form).setPcCveDivSeek(0);
						}else {
							((PuntosVentaForm) form).setPcCveDivSeek(Integer.parseInt(divisionBuscar));
						}
						((PuntosVentaForm) form).setPcCveTipoCanalSeek(tipoCanalBuscar);
						((PuntosVentaForm) form).setPcCveCanalSeek(canalBuscar);
						((PuntosVentaForm) form).setPcNomPtoventasSeek(nombreVendedorBuscar);
						
						//ArrayList tablaPuntosVenta  = CatalogoFacade.getPuntosVentaTable(divisionBuscar,tipoCanalBuscar,canalBuscar,nombreVendedorBuscar, String.valueOf(divisionUsuario));
						ArrayList tablaPuntosVenta  = CatalogoFacade.getPuntosVentaTable1(divisionBuscar,tipoCanalBuscar,canalBuscar,nombreVendedorBuscar, String.valueOf(divisionUsuario));
						if(!tablaPuntosVenta.isEmpty()){
							request.getSession().setAttribute("tablaPuntosVenta",tablaPuntosVenta);
						}else{
							request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
						}
					} else if(vendedorBuscar == null && pcNumEconomico != null) { 
						
						((PuntosVentaForm) form).reset(mapping,request);
						
						if(divisionBuscar == null || divisionBuscar.equals("")){
							((PuntosVentaForm) form).setPcCveDivSeek(0);
						}else {
							((PuntosVentaForm) form).setPcCveDivSeek(Integer.parseInt(divisionBuscar));
						}
						((PuntosVentaForm) form).setPcCveTipoCanalSeek(tipoCanalBuscar);
						((PuntosVentaForm) form).setPcCveCanalSeek(canalBuscar);
						((PuntosVentaForm) form).setPcNomPtoventasSeek(nombreVendedorBuscar);
						ArrayList tablaPuntosVenta = null;
						if(subdivisionUsuario==0 || encontrado){
							tablaPuntosVenta  = CatalogoFacade.getPuntosVentaNumEconomico(pcNumEconomico);	
						}else{
							tablaPuntosVenta  = CatalogoFacade.getPuntosVentaNumEconomico(pcNumEconomico,String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
						}
						 
						if(tablaPuntosVenta != null){
							request.getSession().setAttribute("tablaPuntosVenta",tablaPuntosVenta);
						}else{
							request.getSession().setAttribute("noData","Su Búsqueda no genero Resultados");
						}
					}
					else if(vendedorBuscar != null && pcNumEconomico == null){
						
						Pc_Punto_VentasVO detallePuntoVenta = null;
						
						if(subdivisionUsuario==0 || encontrado){
							detallePuntoVenta = AdminCatFacade.getPuntoVenta(vendedorBuscar);	
						}else{
							detallePuntoVenta = CatalogoFacade.getPuntoVenta(vendedorBuscar,String.valueOf(subdivisionUsuario), String.valueOf(divisionUsuario));
						}
						
						if(detallePuntoVenta != null){
							((PuntosVentaForm) form).setPcCveRegion(detallePuntoVenta.getPcCveRegion());
							((PuntosVentaForm) form).setPcCveDiv(detallePuntoVenta.getPcCveDiv());
							((PuntosVentaForm) form).setPcCveSubdiv(detallePuntoVenta.getPcCveSubdiv());
							((PuntosVentaForm) form).setPcCveCanal(detallePuntoVenta.getPcCveCanal());
							((PuntosVentaForm) form).setPcCveTipoCanal(detallePuntoVenta.getPcCveTpCanal());
							((PuntosVentaForm) form).setPcCvePtoventas(detallePuntoVenta.getPcCvePtoventas());
							((PuntosVentaForm) form).setPcNomPtoventas(detallePuntoVenta.getPcNomPtoventas());
							((PuntosVentaForm) form).setPcCveReferencia(detallePuntoVenta.getPcCveReferencia());
							((PuntosVentaForm) form).setPcCntlInt(detallePuntoVenta.getPcCntlInt());
							((PuntosVentaForm) form).setPcCveDivSeek(0);
							((PuntosVentaForm) form).setPcCveTipoCanalSeek("");
							((PuntosVentaForm) form).setPcCveCanalSeek("");
							((PuntosVentaForm) form).setPcNomPtoventasSeek("");
							((PuntosVentaForm) form).setPcDireccion(detallePuntoVenta.getPcDireccion());
							((PuntosVentaForm) form).setPcColonia(detallePuntoVenta.getPcColonia());
							((PuntosVentaForm) form).setPcCp(detallePuntoVenta.getPcCp());
							((PuntosVentaForm) form).setPcTel(detallePuntoVenta.getPcTel());
							((PuntosVentaForm) form).setPcFax(detallePuntoVenta.getPcFax());
							((PuntosVentaForm) form).setPcCveCiudad(detallePuntoVenta.getPcCveCiudad());
							((PuntosVentaForm) form).setPcCveEstado(detallePuntoVenta.getPcCveEstado());
							((PuntosVentaForm) form).setPcFchIniOp(detallePuntoVenta.getPcFchIniOp());
							((PuntosVentaForm) form).setPcFchBaja(detallePuntoVenta.getPcFchBaja());
							((PuntosVentaForm) form).setPcFchReactvcn(detallePuntoVenta.getPcFchReactvcn());
							ArrayList tablaPuntosVenta  = new ArrayList();
							tablaPuntosVenta.add(detallePuntoVenta);
							request.getSession().setAttribute("detallePuntoVenta",detallePuntoVenta);
							request.getSession().setAttribute("tablaPuntosVenta",tablaPuntosVenta);
						}else{
							request.getSession().setAttribute("noData","No Existen Puntos de Venta con Clave " + vendedorBuscar);
							((PuntosVentaForm) form).setPcCveRegion("");
							((PuntosVentaForm) form).setPcCveDiv(0);
							((PuntosVentaForm) form).setPcCveSubdiv(0);
							((PuntosVentaForm) form).setPcCveCanal("");
							((PuntosVentaForm) form).setPcCveTipoCanal("");
							((PuntosVentaForm) form).setPcCvePtoventas(null);
							((PuntosVentaForm) form).setPcNomPtoventas("");
							((PuntosVentaForm) form).setPcCveReferencia("");
							((PuntosVentaForm) form).setPcCntlInt("");
							((PuntosVentaForm) form).setPcDireccion("");
							((PuntosVentaForm) form).setPcColonia("");
							((PuntosVentaForm) form).setPcCp("");
							((PuntosVentaForm) form).setPcTel("");
							((PuntosVentaForm) form).setPcFax("");
							((PuntosVentaForm) form).setPcCveCiudad(0);
							((PuntosVentaForm) form).setPcCveEstado(0);
							((PuntosVentaForm) form).setPcFchIniOp("");
							((PuntosVentaForm) form).setPcFchBaja("");
							((PuntosVentaForm) form).setPcFchReactvcn("");
							
						}
					}
										
					request.setAttribute("cveRegion",request.getParameter("pcRegion"));
					return mapping.findForward("home");
				} else if(control.equals("CSI")){
					
					Pc_Punto_VentasVO PuntoVeEntaCSI = AdminCatFacade.getPuntoVentaCAT_CSI(request.getParameter("pcCvePtoventas"));
					request.setAttribute("cveRegion",request.getParameter("pcRegion"));
					return mapping.findForward("home");
				}
			}catch(CatalogoSystemException cse){
				logger.error("Error en consultar: " + cse.toString());
				return mapping.findForward("error");
			}catch(Exception e){
				logger.error("Error en consultar: " + e.toString());
				return mapping.findForward("error");
			}
			request.setAttribute("cveRegion",request.getParameter("pcRegion"));
			return mapping.findForward("home");
	}
	
	
	public ActionForward agregarMarca(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){

			String pcCveMarca = request.getParameter("pcCveMarca");
			String cveRegion = request.getParameter("pcRegion");
							if(!cveRegion.equals("-- Seleccione una division --")){
								request.setAttribute("cveRegion", cveRegion);
							}
			String cveCiudad = request.getParameter("pcCiudad");
										if(!cveCiudad.equals("-- Seleccione un estado --")){
											request.setAttribute("cveCiudad", cveCiudad);
							}
							
					
			ArrayList marcasEnSesion = (ArrayList)request.getSession().getAttribute("marcas");
			if(marcasEnSesion == null){
				
				ArrayList marcas = new ArrayList();
				PcMarcasVO marca = null;
				
				marca = CatalogoFacade.obtenerMarca(pcCveMarca);
				marcas.add(marca);
				
				request.getSession().setAttribute("marcas", marcas);
				
			} else{
				
				int mismo = validarMismaMarca(request);
				if(mismo == 1){
					request.setAttribute("mensaje", "La marca seleccionada ya fue agregada");
					return mapping.findForward("home");
				}
				
				
				PcMarcasVO marca = null;
				marca = CatalogoFacade.obtenerMarca(pcCveMarca);
				marcasEnSesion.add(marca);
				
				request.getSession().setAttribute("marcas", marcasEnSesion);
			}
			buscaRegion(request,response);
			buscaCiudad(request,response);
			request.setAttribute("cveRegion",request.getParameter("pcRegion"));
			request.setAttribute("cveCiudad",request.getParameter("pcCiudad"));
			return mapping.findForward("home");
	}
	
	public ActionForward eliminarMarca(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			
			String pcCveMarca = request.getParameter("marcasCombo").trim();
			int marka = (new Integer(pcCveMarca)).intValue();
			String cveRegion = request.getParameter("pcRegion");
										if(!cveRegion.equals("-- Seleccione una division --"))
											request.setAttribute("cveRegion", cveRegion);
			String cveCiudad = request.getParameter("pcCiudad");
										if(!cveCiudad.equals("-- Seleccione un estado --")){
											request.setAttribute("cveCiudad", cveCiudad);
											}											
           
			ArrayList marcasEnSesion = (ArrayList)request.getSession().getAttribute("marcas");
			
			for(int i = 0; i < marcasEnSesion.size(); i++)
			{
				PcMarcasVO marca = new PcMarcasVO();
				marca = (PcMarcasVO)marcasEnSesion.get(i);
				
				String markita = new Integer(marca.getPcCveMarca()).toString();
				if(marca.getPcCveMarca() == marka){
					marcasEnSesion.remove(i);
				}
							
			}
			buscaRegion(request,response);
			buscaCiudad(request,response);
			request.setAttribute("cveRegion",request.getParameter("pcRegion"));
			request.setAttribute("cveCiudad",request.getParameter("pcCiudad"));
			return mapping.findForward("home");
	}
	
	public void buscaRegion(HttpServletRequest request, HttpServletResponse response){
			
		String division = request.getParameter("pcCveDivision");
		if(!division.equals("")){
		 try{
		    	ArrayList regiones = CatalogoFacade.getRegiones(division);
			    Iterator itReg = regiones.iterator();
			
			    //Construye XML
			    Element root = new Element("divisiones"); 
			    while (itReg.hasNext()){
				       Pc_SubdivisionVO tmp = (Pc_SubdivisionVO)itReg.next();
				       Element region = new Element("region");
				       Element clave = new Element("clave").addContent(tmp.getPcCveRegion());
				       Element desc = new Element("descripcion").setText(tmp.getPcDescRegion());
				       region.addContent(clave);
				       region.addContent(desc);
				       root.addContent(region); 
			    }
			     
				/*ArrayList estados = CatalogoFacade.getEstados(division);
				Iterator itEst = estados.iterator();*/
					
				//Construye XML
				/*while (itEst.hasNext()){
					   Pc_SubdivisionVO tmp = (Pc_SubdivisionVO)itEst.next();
					   logger.info(Integer.toString(tmp.getPcCveEstado()));
					   Element estado = new Element("estado");
					   Element clave = new Element("clave").addContent(Integer.toString(tmp.getPcCveEstado()));
					   Element desc = new Element("descripcion").setText(tmp.getPcDescEstado());
					   estado.addContent(clave);
					   estado.addContent(desc);
					   root.addContent(estado); 
				}*/
								     
			     Document doc = new Document(root);
			     response.setContentType("application/xml");
			     XMLOutputter out = new XMLOutputter();
			     out.setEncoding("ISO-8859-1");
			     out.output(doc, response.getWriter());			
		    }catch(Exception e){
			       e.printStackTrace();
	       }
		  } 
	     }
	     
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void buscaCiudad(HttpServletRequest request, HttpServletResponse response){
			
			String estado = request.getParameter("pcCveEstado");
			if(!estado.equals("")){
			 try{
					ArrayList ciudades = CatalogoFacade.getCiudades(estado);
					Iterator itCiud = ciudades.iterator();
			
					//Construye XML
					Element root = new Element("ciudades"); 
					while (itCiud.hasNext()){
						   PcEstadosVO tmp = (PcEstadosVO)itCiud.next();
						   Element ciudad = new Element("ciudad");
						   Element clave = new Element("clave").addContent(Integer.toString(tmp.getPcCveCiudad()));
						   Element desc = new Element("descripcion").setText(tmp.getPcDescCiudad());
						   ciudad.addContent(clave);
						   ciudad.addContent(desc);
						   root.addContent(ciudad); 
					}
					 Document doc = new Document(root);
					 response.setContentType("application/xml");
					 XMLOutputter out = new XMLOutputter();
					 out.setEncoding("ISO-8859-1");
					 out.output(doc, response.getWriter());			
				}catch(Exception e){
					   e.printStackTrace();
			   }
			}
		}
	
	/**
	 * 
	 * @param request
	 * @param response
	 */     	
	public void buscaEstado(HttpServletRequest request, HttpServletResponse response){
			
				String division = request.getParameter("pcCveDivision");
				if(!division.equals("")){
				 try{
						
						ArrayList estados = CatalogoFacade.getEstados(division);
						Iterator itEst = estados.iterator();
					
						//Construye XML
						Element root = new Element("estados"); 
						while (itEst.hasNext()){
							   Pc_SubdivisionVO tmp = (Pc_SubdivisionVO)itEst.next();
							   logger.info(Integer.toString(tmp.getPcCveEstado()));
							   Element estado = new Element("estado");
							   Element clave = new Element("clave").addContent(Integer.toString(tmp.getPcCveEstado()));
							   Element desc = new Element("descripcion").setText(tmp.getPcDescEstado());
							   estado.addContent(clave);
							   estado.addContent(desc);
							   root.addContent(estado); 
						}
						 Document doc = new Document(root);
						 response.setContentType("application/xml");
						 XMLOutputter out = new XMLOutputter();
						 out.setEncoding("ISO-8859-1");
						 out.output(doc, response.getWriter());			
					}catch(Exception e){
						   e.printStackTrace();
				   }
				} 
			}	
	
	
	public int validarMismaMarca(HttpServletRequest request){
		ArrayList marcas = (ArrayList)request.getSession().getAttribute("marcas");
		String pcCveMarca = request.getParameter("pcCveMarca");
		
		
		for(int i = 0; i < marcas.size(); i++)
		{
			PcMarcasVO marca = new PcMarcasVO();
			marca = (PcMarcasVO)marcas.get(i);
			
			if(marca.getPcCveMarca() == new Integer(pcCveMarca).intValue()){
				return 1;	
			}
		}
		return 0;
	}
	
	public boolean confirmarFechaBaja(Pc_Punto_VentasVO fechaPtoVta){
		Calendar  cal = Calendar.getInstance();
		String []fecha = fechaPtoVta.getPcFchIniOp().split("/");
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
					
		sb.append(fecha[2]);
		sb.append("-");
		sb.append(fecha[1]);
		sb.append("-");
		sb.append(fecha[0]);

		Date fchInicio = Date.valueOf(sb.toString());
					
		int dia = cal.get(Calendar.DATE);
		int mes = cal.get(Calendar.MONTH);
		mes++;
		int año = cal.get(Calendar.YEAR);
					
		sb2.append(String.valueOf(año));
		sb2.append("-");
		sb2.append(String.valueOf(mes));
		sb2.append("-");
		sb2.append(String.valueOf(dia));
					
		boolean indicador = Date.valueOf(sb2.toString()).before(fchInicio);
		
		return indicador;
	}

}//Fin de clase PuntosVentaProxy******************************