 /*
 * Created on Feb 23, 2005
 *
 */
package mx.com.iusacell.catalogo.dao;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import mx.com.iusacell.catalogo.excepciones.CatalogoFlowException;
import mx.com.iusacell.catalogo.modelo.ArbolVO;
import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.modelo.DirectorDivVO;
import mx.com.iusacell.catalogo.modelo.PcEstadosVO;
import mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO;
import mx.com.iusacell.catalogo.modelo.PcMarcasVO;
import mx.com.iusacell.catalogo.modelo.Pc_Bit_MovimientosVO;
import mx.com.iusacell.catalogo.modelo.Pc_CanalVO;
import mx.com.iusacell.catalogo.modelo.Pc_DivisionVO;
import mx.com.iusacell.catalogo.modelo.Pc_Inter_Vendedores_PtoventasVO;
import mx.com.iusacell.catalogo.modelo.Pc_MapeoVendedorVO;
import mx.com.iusacell.catalogo.modelo.Pc_NivelesVentaVO;
import mx.com.iusacell.catalogo.modelo.Pc_PuestosVO;
import mx.com.iusacell.catalogo.modelo.Pc_Puestos_NivelVO;
import mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO;
import mx.com.iusacell.catalogo.modelo.Pc_RegionesVO;
import mx.com.iusacell.catalogo.modelo.Pc_RolesVO;
import mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO;
import mx.com.iusacell.catalogo.modelo.Pc_SuperioresVO;
import mx.com.iusacell.catalogo.modelo.Pc_Tipo_CanalVO;
import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.modelo.TestVO;
import mx.com.iusacell.catalogo.utilerias.GeneralDAO;
import mx.com.iusacell.catalogo.utilerias.Q;

/**
 * @author Dvazqueza
 *
 */
public class CatalogoFacade {
	
	public CatalogoFacade() {
		super();
	}
/*
 * Query para Vendedores
 * 
 */	
	public static Pc_VendedoresVO getVendedor(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(Q.VENDEDORES_DETALLE, new Object[]{clave, subdivision, division},Pc_VendedoresVO.class);
	}
	
	public static Pc_VendedoresVO getVendedorDiv(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(Q.VENDEDOR_DETALLE, new Object[]{clave, subdivision, division},Pc_VendedoresVO.class);
	}

	public static ArrayList getVendedorTable(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.VENDEDORES_DETALLE, new Object[]{clave, subdivision, division},Pc_VendedoresVO.class);
	}

	public static ArrayList getVendedoresListaPuesto(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.VENDEDORES_LISTA_PUESTO, new Object[]{clave, subdivision, division},Pc_VendedoresVO.class);
	}

//	public static ArrayList getVendedoresListaRegion(String clave) {
//		GeneralDAO dao = new GeneralDAO();
//		return dao.findValueObjectsArray(Q.VENDEDORES_LISTA_REGION, new Object[]{clave},Pc_VendedoresVO.class);
//	}

	public static ArrayList getVendedoresTable(String puestoBuscar,String nombreVendedorBuscar,String subdivisionUsuario,String divisionUsuario) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.VENDEDOR_PARAMETRO, new Object[] {puestoBuscar,nombreVendedorBuscar,subdivisionUsuario,divisionUsuario}, Pc_VendedoresVO.class);
	}

	public static void setVendedor(Object [] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.VENDEDOR_INSERTAR, parametros);
	}

	public static void setVendedorSubdiv(String cveVendedor, String cveSuperior) throws CatalogoFlowException{
		GeneralDAO dao = new GeneralDAO();
		int resultado = -1;
		resultado = dao.executeThisQueryCheck(Q.VENDEDOR_INSERTA_INTER_SUBDIVISION, new Object[] {cveVendedor,cveSuperior});
		if(resultado != 0){
			throw new CatalogoFlowException("falla en insertar");
		}
	}
	
	public static void changeVendedor(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.VENDEDOR_ACTUALIZAR, parametros);
	}

	public static void deleteVendedores(String cveVendedor) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.VENDEDOR_ELIMINAR, new Object[] {cveVendedor});		
	}

	public static void altaPersonal(String clave, String fechaAlta) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PERSONAL_ALTA, new Object[]{clave, fechaAlta});
	}
	
	public static ArrayList getArbolDescendenteHistorico(String clave,String subdivision,String fchDato) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.REPORTE_DESC_HISTORICO, new Object[]{clave, fchDato, subdivision},ArbolVO.class);
	}
	
	public static ArrayList getArbolDescendenteTiendaHistorico(String clave, String subdivision,String fchDato) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.REPORTE_DESC_TIENDA_HISTORICO, new Object[]{clave, fchDato, subdivision},ArbolVO.class);
	}
//	public static void unassignVendedores(Object[] parametros) {
//		GeneralDAO dao = new GeneralDAO();
//		dao.executeThisQuery(Q.VENDEDOR_NO_SUPERIOR, parametros);
//	}

//	public static void bajaVendedores(Object[] parametros) {
//		GeneralDAO dao = new GeneralDAO();
//		dao.executeThisQuery(Q.VENDEDOR_BAJA, parametros);
//	}

	public static ClavesVO getVendedoresClave() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.VENDEDOR_CLAVE, new Object[]{},ClavesVO.class);
	}

	public static ClavesVO getVendedoresClavePersona() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.VENDEDOR_CLAVE_PERSONA, new Object[]{},ClavesVO.class);
	}

	public static ClavesVO getVendedoresClaveTienda() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.VENDEDOR_CLAVE_TIENDA, new Object[]{},ClavesVO.class);
	}
	
	public static ArrayList getTablaVendedores(String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.VENDEDORES_TABLE, new Object[]{subdivision, division}, Pc_VendedoresVO.class);
	}

	public static ArrayList getSubordinados(String cveVendedor,String subdivision, String division){
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.VENDEDOR_SUBORDINADOS, new Object[]{cveVendedor, subdivision, division},Pc_VendedoresVO.class);
	}

	public static ArrayList getMovVendedoresHis(String cveVendedor){
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_MOVIMIENTOS_HIS, new Object[]{cveVendedor},ClavesVO.class);
	}
	
	public static ClavesVO getMovVendedoresHis(String cveVendedor, String movimiento){
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.PERSONAL_MOV_HIS, new Object[]{cveVendedor,  movimiento},ClavesVO.class);
	}
	
	public static ClavesVO getMovVendedoresHis(String cveVendedor, String movimiento, Connection conn){
			GeneralDAO dao = new GeneralDAO();
			return (ClavesVO) dao.findValueObject(Q.PERSONAL_MOV_HIS, new Object[]{cveVendedor,  movimiento},ClavesVO.class);
		}
		
	public static ArrayList getMovVendedoresAmbasHis(String cveVendedor){
			GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PERSONAL_MOVAMBAS_HIS, new Object[]{cveVendedor},ClavesVO.class);
		}	
	/*
	 * Query para Personal
	 * 
	 */	
		public static Pc_VendedoresVO getPersonal(String clave, String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_VendedoresVO) dao.findValueObject(Q.PERSONAL_DETALLE, new Object[]{clave, subdivision, division},Pc_VendedoresVO.class);
		}

//		public static ArrayList getPersonalListaPuesto(String clave, String division) {
//			GeneralDAO dao = new GeneralDAO();
//			return dao.findValueObjectsArray(Q.PERSONAL_LISTA_PUESTO, new Object[]{clave, division},Pc_VendedoresVO.class);
//		}

		public static ArrayList getPersonalListaRegion(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_LISTA_REGION, new Object[]{clave},Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalTable(String cvePuesto, String nombreBuscar, String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_PARAMETRO, new Object[] {cvePuesto, nombreBuscar, subdivision, division}, Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalPerdidoTable(String puestoBuscar,String listaBuscar,String subdivisionUsuario, String divisionUsuario) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_PARAMETRO_PERDIDO, new Object[] {puestoBuscar, listaBuscar,subdivisionUsuario, divisionUsuario}, Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalPerdidoTableCompleto(String puestoBuscar,String nombreVendedorBuscar,String subdivisionUsuario,String divisionUsuario) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_PARAMETRO_PERDIDO_COMPLETO, new Object[] {puestoBuscar,nombreVendedorBuscar,subdivisionUsuario,divisionUsuario}, Pc_VendedoresVO.class);
		}

	public static ArrayList getPersonalPerdidoTableActivo(String puestoBuscar,String listaBuscar,String subdivisionUsuario, String divisionUsuario) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PERSONAL_PARAM_ACT_PERDIDO, new Object[] {puestoBuscar,listaBuscar,subdivisionUsuario, divisionUsuario}, Pc_VendedoresVO.class);
	}

	public static ArrayList getPersonalPerdidoTableCompletoActivo(String puestoBuscar,String nombreVendedorBuscar,String subdivisionUsuario,String divisionUsuario) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PERSONAL_PARAM_ACT_PERDIDO_COMPLETO, new Object[] {puestoBuscar,nombreVendedorBuscar,subdivisionUsuario,divisionUsuario}, Pc_VendedoresVO.class);
	}

	public static ArrayList getSuperiorPerdidoTable(String puestoBuscar,String nombreVendedorBuscar,String subdivisionUsuario,String pcCvePuesto,String divisionUsuario) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.SUPERIOR_PARAMETRO_PERDIDO, new Object[] {puestoBuscar,nombreVendedorBuscar,subdivisionUsuario,pcCvePuesto,divisionUsuario}, Pc_VendedoresVO.class);
	}

	public static ArrayList getSuperiorPerdidoTableCompleto(String puestoBuscar, String nombreVendedorBuscar, String subdivisionUsuario, String pcCvePuesto, String divisionUsuario) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.SUPERIOR_PARAMETRO_PERDIDO_COMPLETO, new Object[] {puestoBuscar, nombreVendedorBuscar, subdivisionUsuario, pcCvePuesto, divisionUsuario}, Pc_VendedoresVO.class);
	}


		public static void setPersonal(Object [] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_INSERTAR, parametros);
		}

		public static void changePersonal(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_ACTUALIZAR, parametros);
		}

		public static void setPersonalMov(Object [] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_INSERTAR, parametros);
		}
		
		public static void setPersonalMovHis(Object [] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_INSERTAR_HIS, parametros);
		}
			
		public static void changePersonalMov(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_ACTUALIZAR, parametros);
		}
		public static ClavesVO getPersonalClave() {
			GeneralDAO dao = new GeneralDAO();
			return (ClavesVO) dao.findValueObject(Q.PERSONAL_CLAVE, new Object[]{},ClavesVO.class);
		}

		public static ClavesVO getPersonalClavePersona() {
			GeneralDAO dao = new GeneralDAO();
			return (ClavesVO) dao.findValueObject(Q.PERSONAL_CLAVE_PERSONA, new Object[]{},ClavesVO.class);
		}

		public static ClavesVO getPersonalClaveTienda() {
			GeneralDAO dao = new GeneralDAO();
			return (ClavesVO) dao.findValueObject(Q.PERSONAL_CLAVE_TIENDA, new Object[]{},ClavesVO.class);
		}
	
//		public static ArrayList getTablaPersonal(String division) {
//			GeneralDAO dao = new GeneralDAO();
//			return dao.findValueObjectsArray(Q.PERSONAL_TABLE, new Object[]{division}, Pc_VendedoresVO.class);
//		}

		public static void unassignPersonal(String clave, String autorizador) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_REASIGNAR_SUPERIOR, new Object[]{clave});
		}
		
		public static void noSuperiorPersonal(String clave, String division, String autorizador) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_NO_SUPERIOR, new Object[]{clave, division, autorizador});
		}
		
		public static void bajaPersonal(String clave, String fechaBaja) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_BAJA, new Object[]{clave, fechaBaja});
		}

		public static void deletePersonal(String clave) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_ELIMINAR, new Object[]{clave});
		}

		public static ArrayList getPersonalComprobarSuperior(String superior) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_COMPROBAR_SUPERIOR, new Object[]{superior}, Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalComprobarSuperiorAlta(String superior) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_COMPROBAR_SUPERIOR_ALTA, new Object[]{superior}, Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalComprobarSupCad(String superior) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_COMPROBAR_SUP_CAD, new Object[]{superior}, Pc_VendedoresVO.class);
		}

		public static void unassignSuperiorCadena(String clave, String autorizador) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PERSONAL_REASIGNAR_SUP_CAD, new Object[]{clave, autorizador});
		}

		public static ArrayList getPersonalComprobarNuevo(String nombreVend, String apePaterno, String apeMaterno, String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PERSONAL_COMPROBAR_NUEVO, new Object[]{nombreVend, apePaterno, apeMaterno, subdivision, division}, Pc_VendedoresVO.class);
		}

		public static void checkMovStatus(String cveMov,String cveVendedor, String statusO, String statusD,String fechaMov,int cveUsuario, int cveActualiza){
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.MOVIMIENTOS_CAMBIO_STATUS, new Object[]{cveMov,cveVendedor,statusO,statusD,fechaMov,String.valueOf(cveUsuario),String.valueOf(cveActualiza)});	
			}
			
		public static Pc_VendedoresVO getVendedorByClave(String claveRef, String subdivision, String division) {
					GeneralDAO dao = new GeneralDAO();
					return (Pc_VendedoresVO) dao.findValueObject(Q.VENDEDORES_DETALLE_CLAVE, new Object[]{claveRef, subdivision, division},Pc_VendedoresVO.class);
			}
			
		public static ArrayList getVendedorByClaves(String claveRef, String subdivision, String division) {
					GeneralDAO dao = new GeneralDAO();
					return dao.findValueObjectsArray(Q.VENDEDORES_DETALLE_CLAVE, new Object[]{claveRef, subdivision, division},Pc_VendedoresVO.class);
			}	
			
			//query para traerme los puestos que no se pueden repetit
		public static Pc_PuestosVO getPuestosNoRepite(String cveVendedor) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_PuestosVO) dao.findValueObject(Q.PUESTOS_NO_REPITE, new Object[]{cveVendedor},Pc_PuestosVO.class);
		}
		
/*
 * Query para Puntos de Venta
 * 
 */
	public static Pc_Punto_VentasVO getPuntoVenta(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_Punto_VentasVO) dao.findValueObject(Q.PUNTOS_VENTA_DETALLE,
			new Object[]{clave, subdivision, division},Pc_Punto_VentasVO.class);
	}

	public static void setPuntoVenta(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUNTOS_VENTA_INSERTAR,parametros);
	}

	public static ArrayList getPuntosVentaPorCanal(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUNTOS_VENTA_X_CANAL, new Object[]{clave, subdivision, division}, Pc_Punto_VentasVO.class);
	}

	public static ArrayList getPuntosVentaPorRegion(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUNTOS_VENTA_X_REGION, new Object[]{clave, subdivision, division}, Pc_Punto_VentasVO.class);
	}

	public static ArrayList getPuntosVentaPorDivision(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUNTOS_VENTA_X_DIVISION, new Object[]{clave}, Pc_Punto_VentasVO.class);
	}

	public static ArrayList getPuntosVentaPorSubdivision(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUNTOS_VENTA_X_SUBDIVISION, new Object[]{clave}, Pc_Punto_VentasVO.class);
	}

	public static void changePuntosVenta(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUNTOS_VENTA_ACTUALIZAR, parametros);
	}

	public static ArrayList getPuntosVentaTable(String divisionBuscar,String tipoCanalBuscar, String canalBuscar, String nombreVendedorBuscar, String divisionUsuario, String numReferencia) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUNTOS_VENTA_PARAMETRO1, new Object[] {divisionBuscar, tipoCanalBuscar, canalBuscar, nombreVendedorBuscar, divisionUsuario, numReferencia}, Pc_Punto_VentasVO.class);
	}

	public static ArrayList getPuntosVentaTable1(String divisionBuscar,String tipoCanalBuscar, String canalBuscar, String nombreVendedorBuscar, String divisionUsuario) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUNTOS_VENTA_PARAMETRO, new Object[] {divisionBuscar, tipoCanalBuscar, canalBuscar, nombreVendedorBuscar, divisionUsuario}, Pc_Punto_VentasVO.class);
	}

	
	public static ArrayList getPuntosVentaNumEconomico(String numEconomico) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.PUNTOS_VENTA_NUM_ECONOMICO, new Object[] {numEconomico}, Pc_Punto_VentasVO.class);
	}
	
	public static ArrayList getPuntosVentaNumEconomico(String numEconomico, String division, String subdivision) {
				GeneralDAO dao = new GeneralDAO();
				return dao.findValueObjectsArray(Q.PUNTOS_VENTA_NUMECONOMICO_DIV, new Object[] {numEconomico}, Pc_Punto_VentasVO.class);
		}
			
	public static ClavesVO getPuntosVentaClave() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.PUNTOS_VENTA_CLAVE, new Object[]{},ClavesVO.class);
	}

	public static void deletePuntosVenta(String cvePtoventas, String usuarioLoggeado) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUNTOS_VENTA_ELIMINAR, new Object[] {cvePtoventas, usuarioLoggeado});
	}

	//query para saber si un punto de venta existe en CAT_CSI 19/09/2005
	public static ArrayList getPuntosVentaCAT_CSI(String clavePtoVenta ) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUNTOS_VENTA_CAT_CSI, new Object[] {clavePtoVenta}, Pc_Punto_VentasVO.class);
	}

	
//	public static ArrayList getTablaPuntosVenta(String division) {
//		GeneralDAO dao = new GeneralDAO();
//		return dao.findValueObjectsArray(Q.PUNTOS_VENTA_TABLE, new Object[]{division}, Pc_Punto_VentasVO.class);
//	}

/*
 * Query para Tablas de Tipo de Canales
 * 
 */
	public static ArrayList getTablaTipoCanales() {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.TIPO_CANALES_ALL, new Object[]{null}, Pc_Tipo_CanalVO.class);
	}

	public static ClavesVO getTipoCanalClave() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.TIPO_CANALES_CLAVE,
			new Object[]{},ClavesVO.class);
	}

	public static void setTipoCanal(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.TIPO_CANALES_INSERTAR, parametros);
	}

	public static void deleteTipoCanal(String cveTpCanal) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.TIPO_CANALES_ELIMINAR, new Object[] {cveTpCanal});
	}

	public static void changeTipoCanal(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.TIPO_CANALES_ACTUALIZAR, parametros);
	}

/*
 * Query para Tablas de Canales
 * 
 */
	public static ArrayList getCanalesPorTipo(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.CANALES_X_TIPO, new Object[]{clave}, Pc_CanalVO.class);
	}

	public static ArrayList getTablaCanales() {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.CANALES_ALL, new Object[]{null}, Pc_CanalVO.class);
	}

	public static void setCanal(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.CANALES_INSERTAR, parametros);
	}

	public static void deleteCanal(String cveCanal) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.CANALES_ELIMINAR, new Object[] {cveCanal});
	}

	public static void changeCanal(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.CANALES_ACTUALIZAR, parametros);
	}

	public static ClavesVO getCanalClave() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.CANALES_CLAVE, new Object[]{},ClavesVO.class);
	}

/*
 * Query para Tablas de Regiones
 * 
 */
	public static void changeRegion(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.REGION_ACTUALIZAR, parametros);
	}

	public static void setRegion(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.REGION_INSERTAR, parametros);
	}

	public static void deleteRegion(String cveRegion){
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.REGION_ELIMINAR, new Object[] {cveRegion});
	}

	public static ClavesVO getRegionClave() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.REGION_CLAVE, new Object[]{},ClavesVO.class);
	}

	public static ArrayList getTablaRegiones() {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.REGIONES_ALL, new Object[]{null}, Pc_RegionesVO.class);
	}


/*
 * Query para Tablas de Puestos
 * 
 */
	public static void changePuesto(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUESTO_ACTUALIZAR, parametros);
	}

	public static void setPuesto(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUESTO_INSERTAR, parametros);
	}

	public static void deletePuesto(String cvePuestos) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUESTO_ELIMINAR, new Object[] {cvePuestos});
	}

	public static ClavesVO getPuestoClave() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.PUESTOS_CLAVE, new Object[]{},ClavesVO.class);
	}

	public static ArrayList getTablaPuestos() {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUESTOS_ALL, new Object[]{null}, Pc_PuestosVO.class);
	}

	public static Pc_PuestosVO getPuesto(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_PuestosVO) dao.findValueObject(Q.PUESTOS_DETALLE, new Object[]{clave},Pc_PuestosVO.class);
	}

	public static ArrayList getPuestosArbolDesc(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUESTOS_ARBOL, new Object[]{clave},ArbolVO.class);
	}

	public static ClavesVO getPuestosMayorNivel() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.PUESTOS_NIVEL_MAYOR, new Object[]{},ClavesVO.class);
	}

	public static void setPuestoNivel(String clave,String cvePuesto,String cvePuestoSup) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUESTOS_NIVEL_INSERTAR, new Object[] {clave,cvePuesto,cvePuestoSup} );
	}

	public static void deletePuestoNivel(String clave, String claveSup) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUESTOS_NIVEL_ELIMINAR, new Object[]{clave,claveSup});
	}

	public static Pc_Puestos_NivelVO getPuestoNivel(String clave, String claveSup) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_Puestos_NivelVO) dao.findValueObject(Q.PUESTOS_NIVEL_OBTENER, new Object[]{clave,claveSup},Pc_Puestos_NivelVO.class);
	}

	public static ClavesVO getPuestoNivelClave() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.PUESTOS_NIVEL_CLAVE, new Object[]{},ClavesVO.class);
	}

	public static ArrayList getPuestosNivelxCvePuesto(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUESTOS_NIVEL_OBTENER_X_PUESTO, new Object[]{clave}, Pc_Puestos_NivelVO.class);
	}

	public static ArrayList getPuestosNivelxCvePuestoSup(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.PUESTOS_NIVEL_OBTENER_X_PUESTO_SUP, new Object[]{clave}, Pc_Puestos_NivelVO.class);
	}

	public static void changePuestoNivel(String id, String claveSup) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.PUESTOS_NIVEL_ACTUALIZAR, new Object[]{id,claveSup});
	}	

	/*
	 * Query para Tablas de Relaciones
	 * 
	 */
		public static void changeRelacion(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.RELACION_ACTUALIZAR, parametros);
		}

		public static void setRelacion(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.RELACION_INSERTAR, parametros);
		}

		public static void deleteRelacion(String cveInter, String fechaFin, String usuario, String usuarioLoggeado) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.RELACION_ELIMINAR, new Object[] {cveInter, fechaFin, usuario, usuarioLoggeado});
		}

		public static ClavesVO getRelacionClave() {
			GeneralDAO dao = new GeneralDAO();
			return (ClavesVO) dao.findValueObject(Q.RELACION_CLAVE, new Object[]{},ClavesVO.class);
		}

		public static ArrayList getRelacionesXPuntoVenta(String clave, String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.RELACION_VENDEDORES_X_PUNTO, new Object[]{clave, subdivision, division}, Pc_Inter_Vendedores_PtoventasVO.class);
		}

//		public static ArrayList getRelacionesXVendedor(String clave, String division) {
//			GeneralDAO dao = new GeneralDAO();
//			return dao.findValueObjectsArray(Q.RELACION_PUNTOS_X_VENDEDOR, new Object[]{clave, division}, Pc_Inter_Vendedores_PtoventasVO.class);
//		}
		
		public static ArrayList getRelaciones(String CvePtoventas,String CveVendedor,String subdivisionUsuario, String divisionUsuario) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.RELACION_PUNTOS_X_VENDEDOR_OPT, new Object[] {CvePtoventas, CveVendedor, subdivisionUsuario, divisionUsuario}, Pc_Inter_Vendedores_PtoventasVO.class);
		}

		public static Pc_Inter_Vendedores_PtoventasVO getRelacionParametro(String puntoventas, String vendedor) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_Inter_Vendedores_PtoventasVO) dao.findValueObject(Q.RELACION_PARAMETRO, new Object[] {puntoventas, vendedor}, Pc_Inter_Vendedores_PtoventasVO.class);
		}

			
		public static Pc_Inter_Vendedores_PtoventasVO getRelacion(String clave, String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_Inter_Vendedores_PtoventasVO) dao.findValueObject(Q.RELACION_DETALLE, new Object[] {clave, subdivision, division}, Pc_Inter_Vendedores_PtoventasVO.class);
		}
		

	/*
	 * Query para Tablas de Division
	 * 
	 */
		public static void changeDivision(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.DIVISION_ACTUALIZAR, parametros);
		}

		public static void setDivision(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.DIVISION_INSERTAR, parametros);
		}

		public static void deleteDivision(String cveDivision) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.DIVISION_ELIMINAR, new Object[] {cveDivision});
		}

		public static ClavesVO getDivisionClave() {
			GeneralDAO dao = new GeneralDAO();
			return (ClavesVO) dao.findValueObject(Q.DIVISION_CLAVE, new Object[]{},ClavesVO.class);
		}

		public static ArrayList getTablaDivision(String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.DIVISION_ALL, new Object[]{division}, Pc_DivisionVO.class);
		}

		public static void setDivisionVendedor(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.DIVISION_INSERTA_VENDEDOR, parametros);
		}



	/*
		 * Query para Tablas de Empresa
		 * 
		 */
			public static void changeEmpresa(Object[] parametros) {
				GeneralDAO dao = new GeneralDAO();
				dao.executeThisQuery(Q.EMPRESA_ACTUALIZAR, parametros);
			}

			public static void setEmpresa(Object[] parametros) {
				GeneralDAO dao = new GeneralDAO();
				dao.executeThisQuery(Q.EMPRESA_INSERTAR, parametros);
			}

			public static void deleteEmpresa(String cveEmpresa) {
				GeneralDAO dao = new GeneralDAO();
				dao.executeThisQuery(Q.EMPRESA_ELIMINAR, new Object[] {cveEmpresa});
			}

			
	/*
	 * Query para Tablas de Subdivision
	 * 
	 */
		public static ArrayList getSubdivisionPorTipo(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.SUBDIVISION_X_TIPO, new Object[]{clave}, Pc_SubdivisionVO.class);
		}

		public static ArrayList getTablaSubdivision(String cveSubdiv) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.SUBDIVISION_ALL, new Object[]{cveSubdiv}, Pc_SubdivisionVO.class);
		}
	
		//obtiene subdivision
		public static ArrayList getSubdivisiones(String cveSubdiv) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.SUBDIVISION_ALL1, new Object[]{cveSubdiv}, Pc_SubdivisionVO.class);
		}
		
		public static void setSubdivision(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.SUBDIVISION_INSERTAR, parametros);
		}

		public static void deleteSubdivision(String cveSubdiv) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.SUBDIVISION_ELIMINAR, new Object[] {cveSubdiv});
		}

		public static void changeSubdivision(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.SUBDIVISION_ACTUALIZAR, parametros);
		}

		public static ClavesVO getSubdivisionClave() {
			GeneralDAO dao = new GeneralDAO();
			return (ClavesVO) dao.findValueObject(Q.SUBDIVISION_CLAVE, new Object[]{},ClavesVO.class);
		}


	/*
	 * Query para Tablas de Reportes
	 * 
	 */
//		public static ArrayList getArbolDescendente(String clave,String division) {
//			GeneralDAO dao = new GeneralDAO();
//			return dao.findValueObjectsArray(Q.REPORTE_DESC, new Object[]{clave, division},ArbolVO.class);
//		}

//		public static ArrayList getArbolDescendenteTienda(String clave, String division) {
//			GeneralDAO dao = new GeneralDAO();
//			return dao.findValueObjectsArray(Q.REPORTE_DESC_TIENDA, new Object[]{clave, division},ArbolVO.class);
//		}

		public static ArrayList getArbolDescendente(String clave,String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.REPORTE_DESC_NEW, new Object[]{clave, subdivision, division},ArbolVO.class);
		}
	
		public static ArrayList getArbolDescendenteTienda(String clave, String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.REPORTE_DESC_TIENDA_NEW, new Object[]{clave, subdivision, division},ArbolVO.class);
		}
		
		///
		public static ArrayList getArbolDescendenteVarios(String clave,String subdivision, String division, String subdiv1) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.REPORTE_DESC_NEW2, new Object[]{clave, subdivision, division, subdiv1},ArbolVO.class);
		}
	
		public static ArrayList getArbolDescendenteTiendaVarios(String clave, String subdivision, String division, String subdiv1) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.REPORTE_DESC_TIENDA_NEW2, new Object[]{clave, subdivision, division, subdiv1},ArbolVO.class);
		}
		///

		public static ArrayList getArbolAscendente(String clave, String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.REPORTE_ASC, new Object[]{clave, subdivision, division}, ArbolVO.class);
		}

		public static DirectorDivVO getArbolDirectorDiv(String division) {
			GeneralDAO dao = new GeneralDAO();
			return (DirectorDivVO) dao.findValueObject(Q.REPORTE_GET_DIR_DIV, new Object[]{division},DirectorDivVO.class);
		}


	/*
	 * Query para Tablas de Movimientos
	 * 
	 */
	public static ClavesVO getMovimientoClave() {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.MOVIMIENTOS_CLAVE, new Object[]{},ClavesVO.class);
	}
	public static void setMovVendedorData(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.MOVIMIENTOS_CAMBIO_VENDEDOR_DATA, parametros);
	}
//	public static void setMovPuesto(Object[] parametros) {
//		GeneralDAO dao = new GeneralDAO();
//		dao.executeThisQuery(Q.MOVIMIENTOS_CAMBIO_PUESTO, parametros);
//	}
	public static void setMovPuntoVentas(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.MOVIMIENTOS_CAMBIO_PUNTO_VENTAS, parametros);
	}
//	public static void setMovSuperior(Object[] parametros) {
//		GeneralDAO dao = new GeneralDAO();
//		dao.executeThisQuery(Q.MOVIMIENTOS_CAMBIO_SUPERIOR, parametros);
//	}

	public static Pc_VendedoresVO checkVendedor(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(Q.MOVIMIENTOS_CHECK_VENDEDORES, new Object[]{clave, subdivision, division},Pc_VendedoresVO.class);
	}

	public static Pc_VendedoresVO checkMovVendedor(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(Q.VENDEDORES_CHECK_MOVIMIENTOS, new Object[]{clave,  subdivision, division},Pc_VendedoresVO.class);		
	}
	
	public static void checkMovStatus(String cveMov,String cveVendedor, String statusO, String statusD,String fechaMov){
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.MOVIMIENTOS_CAMBIO_STATUS, new Object[]{cveMov,cveVendedor,statusO,statusD,fechaMov});	
	}
	
	public static Pc_Inter_Vendedores_PtoventasVO checkRelacion(String clave, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_Inter_Vendedores_PtoventasVO) dao.findValueObject(Q.MOVIMIENTOS_CHECK_RELACIONES, new Object[]{clave, subdivision, division},Pc_Inter_Vendedores_PtoventasVO.class);
	}

	public static ArrayList getMovimientosTabla(String limite, String subdivision, String division) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.MOVIMIENTOS_TABLE, new Object[]{limite, subdivision, division},Pc_Bit_MovimientosVO.class);
	}

	public static ArrayList getMovimientosTablaParametro(String vendedor, String fechaInicio, String fechaFin, String subdivisionUsuario, String divisionUsuario ) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.MOVIMIENTOS_PARAMETRO, new Object[] {vendedor, fechaInicio, fechaFin, subdivisionUsuario, divisionUsuario},Pc_Bit_MovimientosVO.class);
	}

	public static Pc_VendedoresVO getVendedorByClaveMovimientos(String claveRef, String subdivision, String division) {
				GeneralDAO dao = new GeneralDAO();
				return (Pc_VendedoresVO) dao.findValueObject(Q.MOVIMIENTOS_DETALLE_CLAVE_REF, new Object[]{claveRef},Pc_VendedoresVO.class);
		}

	/**
	 * Query para Tablas de Mapeo Vendedor
	 * 
	 */
		public static void changeMapeoVendedor(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.MAPEO_ACTUALIZAR, parametros);
		}

		public static void setMapeoVendedor(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.MAPEO_INSERTAR, parametros);
		}

		public static void deleteMapeoVendedor(String claveVendedor,String tokensClave0,String tokensClave1) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.MAPEO_ELIMINAR, new Object[] {claveVendedor,tokensClave0,tokensClave1});
		}

//		public static Pc_MapeoVendedorVO getMapeoVendedor(String clave) {
//			GeneralDAO dao = new GeneralDAO();
//			return (Pc_MapeoVendedorVO) dao.findValueObject(Q.MAPEO_VENDEDOR, new Object[]{clave},Pc_MapeoVendedorVO.class);
//		}
		
		public static ArrayList getMapeoVendedor(String clave, String subdivision, String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.MAPEO_VENDEDOR, new Object[]{clave, subdivision, division},Pc_MapeoVendedorVO.class);
		}

		/**
		 * Realiza una busqueda sobre la tabla PC_MAPEO_VENDEDOR para traer el mapeo de un usuario por medio de
		 * PC_CVE_VENDEDOR, PC_SISTEMA y PC_CVE_REG_VENTA
		 * 
		 * @param claveVendedor
		 * @param sistema
		 * @param claveVenta
		 * @return Lista de mapeos de un usario dado su clave vendedor, sistema y clave de venta
		 */
		public static List getMapeoVendedorBySistemaAndCveVenta(String claveVendedor, String sistema, String claveVenta) {
			
			return new GeneralDAO().findValueObjectsArray(Q.MAPEO_VENDEDOR_SISTEMA_VENTA, new Object[]{claveVendedor, sistema, claveVenta},Pc_MapeoVendedorVO.class);
		}
	
		/**
		 * Inserta nuevo registro en la tabla de CATVEN.PC_MAPEO_VENDEDOR_MOVIMIENTOS en base a los parametros
		 * enviados
		 * 
		 * @param parametros
		 */
		public static void setMapeoVendedorMov(Object[] parametros) {
			
			new GeneralDAO().executeThisQuery(Q.MAPEO_VENDEDOR_MOV_INSERT, parametros);		
		}	      	  

	public static ArrayList getStoredProcedure(Float parametro) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArraySP(146, new Object[]{parametro},TestVO.class);
	}



/*
 * Utilitarias Generales
 * Se utilizan para sustituir algunas funciones particulares con versiones generales que reciben mas parametros
 * 
 */

   /**
    * Obtiene una Clave de una tabla usando un query del tipo SELECT (MAX(INT_PRIMARY_KEY)+1) CLAVE FROM TABLE;
  	* @param queryId
  	* @return ClavesVO
  	*/
	public static ClavesVO getClave(int queryId) {
		 GeneralDAO dao = new GeneralDAO();
		 return (ClavesVO) dao.findValueObject(queryId, new Object[]{},ClavesVO.class);
	}

   /**
	* Opera un Query de Tipo INSERT,DELETE,UPDATE usando el Objeto parametros
	* INSERT parametros -> VALUES(param0,param1,param2,...,paramN)
	* DELETE parametros -> WHERE {PRIMARY_KEY}=param0
	* UPDATE parametros -> SET  COL_NAME1 = param1, COL_NAME2 = param2,...,COL_NAMEn = paramN WHERE {PRIMARY_KEY}=param0 
	* @param int queryId
	* @param Object[] parametros
	* @return
	*/
	public static void affectDB(int queryId,Object[] parametros) {
		 GeneralDAO dao = new GeneralDAO();
		 dao.executeThisQuery(queryId, parametros);
	}

	/**
	 * Regresa un ArrayList con objetos de tipo clase donde
	 * queryId contiene un query de tipo select
	 * parametros contiene los parametros necesarios para ejecutar el query
	 * clase es un Bean que va a contener las columnas regresadas por el Query 
	 * @param int queryId
	 * @param Object[] parametros
	 * @param Class clase
	 * @return ArrayList
	 */
	public static ArrayList getTable(int queryId, Object[] parametros,Class clase) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(queryId, parametros, clase);
	}

	   public static PcHorarioDiaVO obtenerDia(String pcCveDia, String pcEvento)
	   {
		   GeneralDAO dao = new GeneralDAO();
		   return (PcHorarioDiaVO)dao.findValueObject(Q.OBTENER_DIA, new Object[] {
			   pcCveDia, pcEvento
		   }, mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO.class);
	   }

	   public static void insertarPcHorario(String pcCveHorario, String pcDescHorario, String pcCveDiv)
	   {
		   GeneralDAO dao = new GeneralDAO();
		   dao.executeThisQuery(Q.AGREGAR_PC_HORARIO, new Object[] {
			   pcCveHorario, pcDescHorario, pcCveDiv
		   });
	   }

	   public static void insertarPcHorarioSemana(String pcCveHorario, String pcCveDia, String pcDescanso)
	   {
		   GeneralDAO dao = new GeneralDAO();
		   dao.executeThisQuery(Q.AGREGAR_PC_HORARIO_SEMANA, new Object[] {
			   pcCveHorario, pcCveDia, pcDescanso
		   });
	   }

	   public static void insertarPcHorarioDia(String pcCveHorario, String pcCveDia, String pcCveTipoRegistro, String pcHora,String pcCveHora, String pcCveMinuto)
	   {
		   GeneralDAO dao = new GeneralDAO();
		   dao.executeThisQuery(Q.AGREGAR_PC_HORARIO_DIA, new Object[] {
			   pcCveHorario, pcCveDia, pcCveTipoRegistro, pcHora, pcCveHora, pcCveMinuto
		   });
	   }

	   public static PcHorarioDiaVO obtenerSiguientePcCveHorario()
	   {
		   GeneralDAO dao = new GeneralDAO();
		   return (PcHorarioDiaVO)dao.findValueObject(Q.OBTENER_SIG_CVE_HORARIO, new Object[1], mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO.class);
	   }

	   public static ArrayList obtenerHorarioDetalle(String pcCveHorario)
	   {
		   GeneralDAO dao = new GeneralDAO();
		   return dao.findValueObjectsArray(Q.OBTENER_HORARIO_DETALLE, new Object[] {
			   pcCveHorario}, mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO.class);
	   }	
	   
	   
		public static Pc_PuestosVO validarHorarioPuesto(String pcCvePuesto)
		{
		GeneralDAO dao = new GeneralDAO();
		return (Pc_PuestosVO)dao.findValueObject(Q.VALIDAR_HORARIO_PUESTO, new Object[] {
			pcCvePuesto}, mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class);
		}	
	
		public static PcHorarioDiaVO obtenerHorario(String pcCveHorario)
		{
		GeneralDAO dao = new GeneralDAO();
		return (PcHorarioDiaVO)dao.findValueObject(Q.OBTENER_HORARIO, new Object[] {
			pcCveHorario}, mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO.class);
		}
		
		public static Pc_RolesVO obtenerDescripcionRol(String pcCveRol)
		{
				GeneralDAO dao = new GeneralDAO();
				return (Pc_RolesVO)dao.findValueObject(Q.DESC_ROL_MODIFICAR, new Object[] {
					pcCveRol}, mx.com.iusacell.catalogo.modelo.Pc_RolesVO.class);
		}
			
		public static void actualizarRol(String pcCveRol, String pcDescRol)
		{
				GeneralDAO dao = new GeneralDAO();
				dao.executeThisQuery(Q.MODIFICAR_ROL, new Object[] {pcCveRol, pcDescRol});
		}
		
		public static void registroTransaccion(String login,String desc,String cuenta, String ip){
			
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.AGREGAR_TRANSACCION, new Object[] {login, desc, cuenta, ip});
		}	
		
		public static void registrarCambioHorario(Object[] parametros)
		{
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(315, parametros);
		}		
	
		public static void cambiarPersonal(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.CAMBIAR_PERSONAL, parametros);
		}
		
		public static void cerrarMov(String CveVendedor) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.CERRAR_MOV, new Object[] {CveVendedor});
		}
		
		public static PcMarcasVO obtenerMarca(String PcCveMarca) {
			GeneralDAO dao = new GeneralDAO();
			return (PcMarcasVO)dao.findValueObject(Q.OBTENER_MARCA, 
				new Object[]{PcCveMarca}, PcMarcasVO.class);
		}
		
		public static void agregarMarca(String pcDescMarca) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.AGREGAR_MARCA, 
				new Object[]{pcDescMarca});
		}
		
		public static void eliminarMarca(String pcCveMarca) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.ELIMINAR_MARCA, 
				new Object[]{pcCveMarca});
		}	
		
		public static void modificarMarca(String pcCveMarca, String pcDescMarca) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.MODIFICAR_MARCA, 
				new Object[]{pcCveMarca, pcDescMarca});
		}
		
		public static void setPuntoVentaMarcas(String ClavePtoVentas, String pcCveMarca) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.AGREGAR_TIENDA_MARCAS,new Object[]{ClavePtoVentas, pcCveMarca});
		}
		
		public static void agregarCanal(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.AGREGAR_CANAL, parametros);
		}
		
		public static Pc_CanalVO obtenerCanal(String pcCveTpCanal) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_CanalVO)dao.findValueObject(Q.OBTENER_CANAL, new Object[] {pcCveTpCanal},Pc_CanalVO.class);
		}
		
		public static void modificarCanal(String pcCveCanal, String pcDescCanal) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.MODIFICAR_CANAL, new Object[] {pcCveCanal, pcDescCanal});
		}
		
		public static void eliminarRelacionesCanal(String pcCveCanal, String pcFecha) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.ELIMINAR_RELACIONES_CANAL, new Object[] {pcCveCanal, pcFecha});
		}
		
		public static void eliminarCanal(String pcCveCanal, String pcFecha) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.ELIMINAR_CANAL, new Object[] {pcCveCanal,pcFecha});
		}
		
		public static void eliminarCanalComoVendedor(String pcCveVendedor, String pcFecha) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.ELIMINAR_CANAL_VENDEDOR, new Object[] {pcCveVendedor,pcFecha});
		}
		
		public static void eliminarPuntosVenta(String pcCveCanal, String pcFecha) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.ELIMINAR_CANAL, new Object[] {pcCveCanal,pcFecha});
		}
		
		public static void eliminarPuntosVentaCanal(String pcCveCanal) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.ELIMINAR_PUNTOSVENTA_CANAL, new Object[] {pcCveCanal});
		}					
		
		public static void agregarVendedor(String pcCveCanal, String pcDescCanal, String digito, String pcFecha) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.AGREGAR_VENDEDOR, new Object[] {pcCveCanal, pcDescCanal, digito, pcFecha});
		}
		
		public static Pc_VendedoresVO buscarClaveVendedor(String pcCveVendedor) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_VendedoresVO)dao.findValueObject(Q.BUSCAR_PUNTOVENTA, new Object[] {pcCveVendedor},Pc_VendedoresVO.class);
		}
		
		public static void crearRelacionVendedorPuntoVenta(String pcCveVendedor, String pcCvePtoVentas, String pcCveUsuario, String usuarioLoggeado) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.AGREGAR_RELACION_PTOVENTA, new Object[] {pcCveVendedor, pcCvePtoVentas, pcCveUsuario, usuarioLoggeado});
		}
		
		public static Pc_CanalVO existeClaveCanal(String pcCveCanal) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_CanalVO)dao.findValueObject(Q.EXISTE_CLAVE_CANAL, new Object[] {pcCveCanal},Pc_CanalVO.class);
		}
		
		public static String nivelVentasConfig(String pk){
			GeneralDAO dao = new GeneralDAO();
			return dao.findValue(Q.PUESTOS_NIVEL_VENTAS, new Object[] {pk})+"";
		}
		
		public static ArrayList getBuscaVendedores(String sql) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.BUSQUEDA_VENDEDORES, new Object[]{sql},Pc_VendedoresVO.class);
		}

	
		/**
		 * Fecha:       130208 
		 * @author      CAL
		 *
		 * Comentarios: <p>Métodos que sirven para agregar, modificar y borrar registros de la tabla
		 *                 C_CONFIGURACION.</p>
		 */			
	     public static Pc_NivelesVentaVO obtenerNivel(String cl_Configuracion) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_NivelesVentaVO)dao.findValueObject(Q.OBTENER_NIVELES, 
			new Object[]{cl_Configuracion}, Pc_NivelesVentaVO.class);
		 }
		
	     public static ArrayList obtenerTablaNiveles() {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.ALL_NIVELES, new Object[]{null}, Pc_NivelesVentaVO.class);
		 }
		
	     public static void deleteNivelVenta(String cveNivel) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.ELIMINAR_NIVELES, new Object[] {cveNivel});
		 }
		 
	     public static void actualizarNivelVenta(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.ACTUALIZAR_NIVELES, parametros);
		 }
		
	     public static void agregarNivelVenta(Object[] parametros) {
	        GeneralDAO dao = new GeneralDAO();
	        dao.executeThisQuery(Q.AGREGAR_NIVELES, parametros);
         }
         
	     public static ClavesVO getNivelesClave() {
			GeneralDAO dao = new GeneralDAO();
			return (ClavesVO) dao.findValueObject(Q.CLAVES_NIVELES, new Object[]{},ClavesVO.class);
		}
		
		public static Pc_VendedoresVO getDetallePersonal(String vendedor) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_VendedoresVO) dao.findValueObject(Q.DETALLE_VENDEDOR, new Object[]{vendedor},Pc_VendedoresVO.class);
		}
		
		public static ArrayList getSuperiores(String puesto, String subdivision, String division, String superior) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.VENDEDOR_SUPERIOR_X_ARBOL2, new Object[]{puesto, subdivision, division, superior}, Pc_SuperioresVO.class);
		}
		
		public static ArrayList getRegiones(String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.REGION_COMERCIAL, new Object[]{division},Pc_SubdivisionVO.class);
		}
		
		public static ArrayList getCiudades(String estado) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.CIUDAD_COMERCIAL, new Object[]{estado},PcEstadosVO.class);
		}
		
		public static ArrayList getTodasCiudades() {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.TODAS_CIUDADES_COMERCIALES, new Object[]{null}, PcEstadosVO.class);
		}
		public static ArrayList getEstados(String division) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.ESTADO_COMERCIAL, new Object[]{division},Pc_SubdivisionVO.class);
		}
				
		public static void ejecutarComando(String parametros){
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.EJECUTAR_X, new Object[] {parametros});
		}
		
	    public static String nivelVentasConfig2(){
		    GeneralDAO dao = new GeneralDAO();
		    return (String)dao.findValue(Q.PUESTOS_NIVEL_VENTAS2, new Object[] {null});
	    }
	    
	    public static ArrayList confirmaNombres(String nombre){
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.BUSQUEDA_VENDEDORES_CONFIRMAR, new Object[]{nombre},Pc_VendedoresVO.class);
	   	}
	   
		public static ArrayList getBuscaVendedoresSuperiores(String sql){
			GeneralDAO dao = new GeneralDAO();
	    	return dao.findValueObjectsArray(Q.BUSQUEDA_VENDEDORES_SUPERIORES, new Object[]{sql},Pc_VendedoresVO.class);
	    }
	   
  	   	public static void reactivarPuntosVenta(String cvePtoventas, String usuarioLoggeado) {
	   		GeneralDAO dao = new GeneralDAO();
	   		dao.executeThisQuery(Q.PUNTOS_VENTA_REACTIVAR, new Object[] {cvePtoventas, usuarioLoggeado});
       	}
       
	   	public static void deletePuntosVenta2(String cvePtoventas, String usuarioLoggeado) {
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.PUNTOS_VENTA_ELIMINAR2, new Object[] {cvePtoventas, usuarioLoggeado});
	   	}
	   		
	   	public static Pc_Punto_VentasVO getPuntoVenta2(String cvePtoventa, String division1, String division2) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_Punto_VentasVO)dao.findValueObject(Q.PUNTOS_VENTA_REFERENCIA, new Object[] {cvePtoventa, division1, division2}, Pc_Punto_VentasVO.class);
	   	}
	   
	   	public static Pc_Punto_VentasVO getPuntoVenta3(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_Punto_VentasVO) dao.findValueObject(Q.PUNTOS_VENTA_REFERENCIA2,
				new Object[]{clave},Pc_Punto_VentasVO.class);
		}
		
	public static String getParametroValue(String llave){
		GeneralDAO dao = new GeneralDAO();
		return dao.findValue(Q.CATALOGO_PARAMETROS, new Object[] {llave})+"";
	}  
}//Fin de la clase CatalogoFacade******************************