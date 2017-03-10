/*
 * Tipo de Archivo: Clase
 * Nombre:          mx.com.iusacell.catalogo.web.valoresVenta.struts.action.ConfigValoresVentaProxy
 * 
 * Autor:           CAL
 * Fecha:           Feb 13, 2008
 * Modificada:      Jun 16, 2008
 * Descripción: 
 * Versión:         2.0
 */
package mx.com.iusacell.catalogo.web.valoresVenta.struts.action;

import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;
import mx.com.iusacell.catalogo.modelo.ClavesVO;

import mx.com.iusacell.catalogo.utilerias.GeneralDAO;
import mx.com.iusacell.catalogo.utilerias.Q;
import mx.com.iusacell.catalogo.bd.ProveedorConexion;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.sql.Connection;

/**
 * Compañía:    <p>Iusacell</p>   
 * Descripcion: <p></p>
 * Fecha:       130208
 * 
 *@author  CAL
 *@version 1.0
 * 
 */
public class ConfigValoresVentaProxy {
	
	/**
	 * Constructor por defecto.
	 */
	public ConfigValoresVentaProxy(){
	}
	
    Connection conn = null;
	GeneralDAO dao = new GeneralDAO();
	  
	
	/**
	 * Sólo para cargar los datos de la tabla, es el método que utiliza por defecto la acción al cargar la jsp.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward verLista(ActionMapping mapping, ActionForm form, 
		HttpServletRequest request, HttpServletResponse response) {
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			
			ArrayList tablaPuestos = new ArrayList();
		
			tablaPuestos = CatalogoFacade.obtenerTablaNiveles();
			request.getSession().setAttribute("tablaPuestos",tablaPuestos);	
			return mapping.findForward("home");
	}
    
    /**
     * Método para insertar un nuevo registro en la base de datos en la tabla de c_configuracion.
     *  
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward agregar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				ClavesVO clave = new ClavesVO();
				clave = CatalogoFacade.getNivelesClave();
				ConfigValoresVentaForm forma = (ConfigValoresVentaForm) form;
				Object[] parametros = {
				String.valueOf(clave.getClave()),
				request.getParameter("ds_Configuracion"),
				request.getParameter("ds_Valor")
				};
			
				try{
					CatalogoFacade.agregarNivelVenta(parametros);
					forma.reset(mapping,request);
				}catch(CatalogoSystemException cse){
					return mapping.findForward("error");
				}
			}catch(Exception e){
				return mapping.findForward("error");
			}
			return mapping.findForward("home");
	}

    /**
     * Para eliminar un registro de la tabla de c_configuración.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward eliminar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");

			try{
				String cvePuestos = (request.getParameter("pcCveNiveles").equals(""))? null:request.getParameter("pcCveNiveles");
				if(cvePuestos!=null){
					CatalogoFacade.deleteNivelVenta(cvePuestos);
					((ConfigValoresVentaForm) form).reset(mapping,request);
				}else{
					request.getSession().setAttribute("noData","No se puede borrar este Puesto porque esta en uso");
				}
			}catch(CatalogoSystemException cse){
				return mapping.findForward("error");
			}catch(Exception e){
				return mapping.findForward("error");
			}
		
			return mapping.findForward("home");
	}


	/**
		* Para alterar un registro de la tabla de c_configuración.
		* 
		* @param mapping
		* @param form
		* @param request
		* @param response
		* @return
		*/
	   public ActionForward ejecutar(ActionMapping mapping, ActionForm form,
		   HttpServletRequest request, HttpServletResponse response){
			   if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");		    
			    String linea = "";
			    int iteracion = 0;
			    String flag = "";
			    		    
		try{
		   conn = ProveedorConexion.getConnection();
		   conn.setAutoCommit(false); 
		   linea = (request.getParameter("ds_Command").equals(""))? null:request.getParameter("ds_Command");
		   String[] lines = linea.split(";");
			if(lines!=null){
				iteracion++;
				if(lines.length==1){
					flag = dao.ejecutaTransaccion(Q.EJECUTAR_X,new Object[] {lines[0]}, conn);
					if(flag.length()>5){
					   conn.rollback();
					   linea = flag + " --ERROR en la línea número :"+iteracion + " del script. Recuerde que debe de separar las sentencias por punto y coma.";
					   request.setAttribute("linea",linea);
					   conn.close();
					   return mapping.findForward("home");
					}
				 }else{	
				       for(int i=0;i<=(lines.length-1);i++){
						flag = dao.ejecutaTransaccion(Q.EJECUTAR_X,new Object[] {lines[i]}, conn);
						if(flag.length()>5){
						   iteracion =i+1;
						   conn.rollback();
						   linea = flag + " --ERROR en la línea número :"+iteracion + " del script. Recuerde que debe de separar las sentencias por punto y coma.";
						   request.setAttribute("linea",linea);
						   conn.close();
						   return mapping.findForward("home");
						}
				      }
				    }
				request.setAttribute("linea","Todas las sentencias ejecutadas con éxito.");
				conn.commit();   
			}else{
			      request.getSession().setAttribute("noData","Sin datos para procesar.");
		   }
		 }catch(Exception e){
			   	     linea = e.getMessage().toString() + " --ERROR en la línea número :"+iteracion + " del script. Recuerde que debe de separar las sentencias por punto y coma.";
			         request.setAttribute("linea",linea);
				   return mapping.findForward("home");
		}
		try {
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		((ConfigValoresVentaForm) form).reset(mapping,request);
		  return mapping.findForward("home");
	   } 
     
    /**
     * Método utilitario para actualizar un registro de la tabla de c_configuracion.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward modificar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			try{
				ConfigValoresVentaForm forma = (ConfigValoresVentaForm) form;
				Object[] parametros = {
						request.getParameter("pcCveNiveles"),
						request.getParameter("ds_Configuracion"),
					    request.getParameter("ds_Valor")
					};
					
					try{
						CatalogoFacade.actualizarNivelVenta(parametros);
						forma.reset(mapping,request);
					}catch(CatalogoSystemException cse){
						return mapping.findForward("error");
					}
			}catch(Exception e){
				return mapping.findForward("error");
			}		
			return mapping.findForward("home");
	}
    
    /**
     * Método utilitario para hacer una consulta en específico de la tabla de c_configuracion.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
	public ActionForward consultar(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response){
			if(request.getSession().getAttribute("noData") != null) request.getSession().removeAttribute("noData");
			ArrayList tablaPuestos = new ArrayList();
		
			tablaPuestos = CatalogoFacade.getTablaPuestos();
			
			request.getSession().setAttribute("tablaPuestos",tablaPuestos);	
			return mapping.findForward("home");
	}

}//Fin de la clase ConfigValoresVentaProxy*******************************************