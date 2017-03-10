/*
 * Creado el May 2, 2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.dao;

import java.util.ArrayList;

import mx.com.iusacell.catalogo.modelo.ChecadorVO;
import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.utilerias.GeneralDAO;
import mx.com.iusacell.catalogo.utilerias.Q;

/**
 * @author JOJEDAH
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class PersonalFacade {

	public PersonalFacade() {
		super();
	}
	
	public static void cerrarMovimientosEnBaja(String pcCveId, String pcCveVendedor, String fechaBaja, String pcCveUsuario) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.CERRAR_MOVIMIENTO,new Object[]{pcCveId, pcCveVendedor,fechaBaja,pcCveUsuario});
	}
		
	public static void aplicarBajaEnHist(String pcCveVendedor, String pcCvePuesto, String pcCveSuperior, String pcFchFin,String pcCveUsuario, String pcCveHorario) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.APLICAR_BAJA_HIST,new Object[]{pcCveVendedor,pcCvePuesto,pcCveSuperior,pcFchFin,pcCveUsuario,pcCveHorario});
	}
	
	public static Pc_VendedoresVO obtenerDatosVendedor(String pcCveVendedor) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO)dao.findValueObject(Q.OBTENER_DATOS_VENDEDOR, new Object[]{pcCveVendedor}, Pc_VendedoresVO.class);
	}
	
	public static ArrayList obtenerDatosVendedor2(String cveVendedor) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.OBTENER_DATOS_VENDEDOR, new Object[]{cveVendedor}, Pc_VendedoresVO.class);
		}
	
	public static void eliminarTiendasVendedor(String pcCveVendedor, String fechaBaja, String cveUsuario, String usuarioLoggeado) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.ELIMINAR_TIENDAS_VENDEDOR, new Object[]{pcCveVendedor, fechaBaja, cveUsuario, usuarioLoggeado});
	}
	
	public static void cerrarPeriodoBajaVendedor(String pcCveVendedor) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.CERRAR_PERIODO_BAJA_VEND, new Object[]{pcCveVendedor});
	}
	
	public static void agregarAltaEnHistorialVendedores(String pcCveVendedor, String usuario) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.AGREGAR_ALTA_HIST_VEND, new Object[]{pcCveVendedor, usuario});
	}

	public static void cambiarEstatusBajaPorAltaVendedor(String pcCveVendedor) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.CAMBIAR_ESTATUS_BAJA_ALTA_VEND, new Object[]{pcCveVendedor});
	}
	
	public static Pc_VendedoresVO obtenerEstatusVendedor(String pcCveVendedor) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(Q.ESTATUS_VENDEDOR, new Object[]{pcCveVendedor},Pc_VendedoresVO.class);
			 
	}
	
	public static ArrayList obtenerVendedores(String puestoBuscar,String nombreVendedorBuscar,String subdivisionUsuario,String divisionUsuario) {
		GeneralDAO dao = new GeneralDAO();
		return (ArrayList) dao.findValueObjectsArray(Q.OBTENER_VENDEDOR, new Object[]{puestoBuscar,nombreVendedorBuscar,subdivisionUsuario,divisionUsuario},Pc_VendedoresVO.class);
			 
	}
	
	public static ArrayList obtenerReporte(String secuencia) {
		GeneralDAO dao = new GeneralDAO();
		return (ArrayList) dao.findValueObjectsArray(Q.OBTENER_REPORTE_CHECADOR, new Object[]{secuencia},ChecadorVO.class);
			 
	}
	
	public static void eliminarReporte(String secuencia) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.ELIMINAR_REPORTE_CHECADOR, new Object[]{secuencia});
			 
	}

	public static void eliminarSistemasVendedor(String pcCveVendedor) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.ELIMINAR_SISTEMAS_VENDEDOR, new Object[]{pcCveVendedor});
	}

							
}
