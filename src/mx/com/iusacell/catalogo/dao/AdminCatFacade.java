/*
 * Created on Feb 23, 2005
 *
 */
package mx.com.iusacell.catalogo.dao;


import java.util.ArrayList;

import mx.com.iusacell.catalogo.utilerias.GeneralDAO;
import mx.com.iusacell.catalogo.utilerias.Q;
import mx.com.iusacell.catalogo.utilerias.DIV;
//Empieza el modelo de aplicacion
import mx.com.iusacell.catalogo.modelo.Pc_NivelesVentaVO;
import mx.com.iusacell.catalogo.modelo.Pc_SuperioresVO;
import mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO;
import mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO;
import mx.com.iusacell.catalogo.modelo.Pc_Inter_Vendedores_PtoventasVO;
import mx.com.iusacell.catalogo.modelo.Pc_DivisionVO;
import mx.com.iusacell.catalogo.modelo.Pc_MapeoVendedorVO;
import mx.com.iusacell.catalogo.modelo.Pc_Bit_MovimientosVO;


import mx.com.iusacell.catalogo.modelo.ClavesVO;
import mx.com.iusacell.catalogo.modelo.ArbolVO;

import mx.com.iusacell.catalogo.modelo.Pc_LoginVO;
import mx.com.iusacell.catalogo.modelo.Pc_RolesVO;
import mx.com.iusacell.catalogo.modelo.Pc_RolesDivVO;

/**
 * @author Dvazqueza
 *
 */
public class AdminCatFacade {
	
	public AdminCatFacade() {
		super();
	}
/*
 * Query para Vendedores
 * 
 */	
	public static Pc_VendedoresVO getVendedor(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(DIV.ADMIN_QUERY_VENDEDORES_DETALLE, new Object[]{clave},Pc_VendedoresVO.class);
	}

	public static ArrayList getVendedorTable(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDORES_DETALLE, new Object[]{clave},Pc_VendedoresVO.class);
	}

	public static ArrayList getVendedoresListaPuesto(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDORES_LISTA_PUESTO, new Object[]{clave},Pc_VendedoresVO.class);
	}

	public static ArrayList getVendedoresTable(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDOR_PARAMETRO, parametros, Pc_VendedoresVO.class);
	}

	public static ArrayList getTablaVendedores() {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDORES_TABLE, new Object[]{}, Pc_VendedoresVO.class);
	}

	/*
	 * Query para Personal
	 * 
	 */	
		public static Pc_VendedoresVO getPersonal(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_VendedoresVO) dao.findValueObject(DIV.ADMIN_QUERY_VENDEDORES_DETALLE, new Object[]{clave},Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalListaPuesto(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDORES_LISTA_PUESTO, new Object[]{clave},Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalTable(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDOR_PARAMETRO, parametros, Pc_VendedoresVO.class);
		}
		
		public static ArrayList getPersonalTableCompleto(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDOR_PARAMETRO_COMPLETO, parametros, Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalTableActivo(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDOR_PARAM_ACT, parametros, Pc_VendedoresVO.class);
		}
		
		public static ArrayList getPersonalTableCompletoActivo(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDOR_PARAM_COMPLETO_ACT, parametros, Pc_VendedoresVO.class);
		}


	public static ArrayList getSuperiorTable(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_SUPERIOR_PARAMETRO, parametros, Pc_VendedoresVO.class);
	}
		
	public static ArrayList getSuperiorTableCompleto(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_SUPERIOR_PARAMETRO_COMPLETO, parametros, Pc_VendedoresVO.class);
	}


		public static ArrayList getTablaPersonal() {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDORES_TABLE, new Object[]{}, Pc_VendedoresVO.class);
		}

		public static ArrayList getPersonalComprobarNuevo(String nombreVend, String apePaterno, String apeMaterno) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_PERSONAL_COMPROBAR_NUEVO, new Object[]{nombreVend, apePaterno, apeMaterno}, Pc_VendedoresVO.class);
		}
		
	public static Pc_VendedoresVO getVendedorByClave(String claveRef) {
				GeneralDAO dao = new GeneralDAO();
				return (Pc_VendedoresVO) dao.findValueObject(DIV.ADMIN_QUERY_VENDEDORES_DETALLE_BY_CLAVE, new Object[]{claveRef},Pc_VendedoresVO.class);
			}
	
	public static ArrayList getVendedorByClaves(String claveRef) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDORES_DETALLE_BY_CLAVE, new Object[]{claveRef},Pc_VendedoresVO.class);
		}
/*
 * Query para Puntos de Venta
 * 
 */
	public static Pc_Punto_VentasVO getPuntoVenta(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_Punto_VentasVO) dao.findValueObject(DIV.ADMIN_QUERY_PUNTOS_VENTA_DETALLE,
			new Object[]{clave},Pc_Punto_VentasVO.class);
	}

	public static ArrayList getPuntosVentaPorCanal(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_PUNTOS_VENTA_X_CANAL, new Object[]{clave}, Pc_Punto_VentasVO.class);
	}

	public static ArrayList getPuntosVentaPorRegion(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_PUNTOS_VENTA_X_REGION, new Object[]{clave}, Pc_Punto_VentasVO.class);
	}

	public static ArrayList getPuntosVentaPorSubdivision(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_PUNTOS_VENTA_X_SUBDIVISION, new Object[]{clave}, Pc_Punto_VentasVO.class);
	}

	public static ArrayList getTablaPuntosVenta() {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_PUNTOS_VENTA_TABLE, new Object[]{}, Pc_Punto_VentasVO.class);
	}

	/*
	 * Query para Tablas de Relaciones
	 * 
	 */
		public static ArrayList getRelacionesXPuntoVenta(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_RELACION_VENDEDORES_X_PUNTO, new Object[]{clave}, Pc_Inter_Vendedores_PtoventasVO.class);
		}

		public static ArrayList getRelacionesXVendedor(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_RELACION_PUNTOS_X_VENDEDOR, new Object[]{clave}, Pc_Inter_Vendedores_PtoventasVO.class);
		}
		
		public static ArrayList getRelaciones(Object[] parametros) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_RELACION_PUNTOS_X_VENDEDOR_OPT, parametros, Pc_Inter_Vendedores_PtoventasVO.class);
		}
		
		public static Pc_Inter_Vendedores_PtoventasVO getRelacion(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return (Pc_Inter_Vendedores_PtoventasVO) dao.findValueObject(DIV.ADMIN_QUERY_RELACION_DETALLE, new Object[] {clave}, Pc_Inter_Vendedores_PtoventasVO.class);
		}
		

	/*
	 * Query para Tablas de Division
	 * 
	 */
		public static ArrayList getTablaDivision() {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_DIVISION_ALL, new Object[]{}, Pc_DivisionVO.class);
		}

	/*
	 * Query para Tablas de Reportes
	 * 
	 */
//		public static ArrayList getArbolDescendente(String clave) {
//			GeneralDAO dao = new GeneralDAO();
//			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_REPORTE_DESC, new Object[]{clave},ArbolVO.class);
//		}
//
//		public static ArrayList getArbolDescendenteTienda(String clave) {
//			GeneralDAO dao = new GeneralDAO();
//			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_REPORTE_DESC_TIENDA, new Object[]{clave},ArbolVO.class);
//		}

		public static ArrayList getArbolDescendente(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_REPORTE_DESC_NEW, new Object[]{clave},ArbolVO.class);
		}
	
		public static ArrayList getArbolDescendenteTienda(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_REPORTE_DESC_TIENDA_NEW, new Object[]{clave},ArbolVO.class);
		}
		//rsg 16/07/05
		public static ArrayList getArbolDescendente(String clave, String subdiv) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_REPORTE_DESC_NEW1, new Object[]{clave, subdiv},ArbolVO.class);
		}
		
		//	rsg 16/07/05
		 public static ArrayList getArbolDescendentePuestos(String clave, String subdiv) {
			  GeneralDAO dao = new GeneralDAO();
			  return dao.findValueObjectsArray(DIV.ADMIN_QUERY_REPORTE_DESC_NEW2, new Object[]{clave, subdiv},ArbolVO.class);
		  }
		
		
		public static ArrayList getTiendaVendedor(String div, String subdiv, String cveVendedor) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(Q.TIENDA_VENDEDOR_EXISTE, new Object[]{div, subdiv, cveVendedor},ArbolVO.class);
		}
			
		public static ArrayList getArbolDescendenteTienda(String clave, String subdiv) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_REPORTE_DESC_TIENDA_NEW1, new Object[]{clave, subdiv},ArbolVO.class);
		}
		
		public static ArrayList getArbolDescendentePtoVenta(String clave, String subdiv) {
				GeneralDAO dao = new GeneralDAO();
				return dao.findValueObjectsArray(Q.ADMIN_QUERY_REPORTE_PTOVENTA, new Object[]{clave, subdiv},ArbolVO.class);
			}
			
		public static ArrayList getArbolDescendenteHistorico(String clave,String fchDato) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_REPORTE_DESC_HISTORICO, new Object[]{clave,fchDato, null},ArbolVO.class);
		}
		
		public static ArrayList getArbolDescendenteTiendaHistorico(String clave,String fchDato) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_TIENDA_DESC_HISTORICO, new Object[]{clave,fchDato, null},ArbolVO.class);
		}
			

	/*
	 * Query para Tablas de Movimientos
	 * 
	 */

	public static Pc_VendedoresVO checkVendedor(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(DIV.ADMIN_QUERY_MOVIMIENTOS_CHECK_VENDEDORES, new Object[]{clave},Pc_VendedoresVO.class);
	}
	
	public static Pc_VendedoresVO checkMovVendedor(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(DIV.ADMIN_QUERY_VENDEDORES_CHECK_MOVIMIENTOS, new Object[]{clave},Pc_VendedoresVO.class);
	}
	
	public static Pc_Inter_Vendedores_PtoventasVO checkRelacion(String clave) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_Inter_Vendedores_PtoventasVO) dao.findValueObject(DIV.ADMIN_QUERY_MOVIMIENTOS_CHECK_RELACIONES, new Object[]{clave},Pc_Inter_Vendedores_PtoventasVO.class);
	}

	public static ArrayList getMovimientosTabla(String limite) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_MOVIMIENTOS_TABLE, new Object[]{limite},Pc_Bit_MovimientosVO.class);
	}
	
	public static ArrayList getMovimientosTablaParametro(Object[] parametros) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_MOVIMIENTOS_PARAMETRO, parametros,Pc_Bit_MovimientosVO.class);
	}

	public static ArrayList getSubordinados(String cveVendedor){
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(DIV.ADMIN_QUERY_VENDEDOR_SUBORDINADOS, new Object[]{cveVendedor},Pc_VendedoresVO.class);
	}
	
	public static Pc_VendedoresVO getVendedorByClaveMovimientos(String claveRef) {
				GeneralDAO dao = new GeneralDAO();
				return (Pc_VendedoresVO) dao.findValueObject(DIV.MOVIMIENTOS_DETALLE_CLAVE_REF, new Object[]{claveRef},Pc_VendedoresVO.class);
			}

	/**
	 * Query para Tablas de Mapeo Vendedor
	 * 
	 */
	
		public static ArrayList getMapeoVendedor(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return dao.findValueObjectsArray(DIV.ADMIN_QUERY_MAPEO_VENDEDOR, new Object[]{clave},Pc_MapeoVendedorVO.class);
		}

	/**
	 * 
	 * @param queryId
	 * @return
	 */
	public static ArrayList getCuentasAcceso(){
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.SYSADMIN_ACCESS_ACCOUNT_ALL, new Object[]{},Pc_LoginVO.class);
	}

	public static ArrayList getRoles(){
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.SYSADMIN_ROLES_ALL, new Object[]{},Pc_RolesVO.class);
	}

	public static void setCuenta(String userid, String password, String nombreCuenta, String pcFecha){
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.SYSADMIN_ACCESS_ACCOUNT_INSERT, new Object[] {userid, password, nombreCuenta, pcFecha});
	}
	
	public static void actualizaCuenta(String userid, String password, String nombreCuenta){
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.SYSADMIN_ACCESS_ACCOUNT_UPDATE, new Object[] {userid, password, nombreCuenta});
	}
	
	public static void borraCuenta(String userid){
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.SYSADMIN_ACCESS_ACCOUNT_DELETE, new Object[] {userid});
	}
			
	public static void setRolDiv(int CveUsuario, int cveRol, int division){
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.SYSADMIN_ROLES_DIV_INSERT, new Object[] {String.valueOf(CveUsuario), String.valueOf(cveRol), String.valueOf(division)});
	}
	
	public static void updateRolDiv(int cveRolId, int CveUsuario, int cveRol, int division){
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.SYSADMIN_ROLES_DIV_UPDATE1, new Object[] {String.valueOf(cveRolId),String.valueOf(CveUsuario), String.valueOf(cveRol), String.valueOf(division)});
	}
	public static ArrayList getPermisos(){
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.SYSADMIN_ROLES_DIV_ALL, new Object[]{},Pc_RolesDivVO.class);
	}

	public static void deleteRolDiv(int CveRolDiv){
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.SYSADMIN_ROLES_DIV_DELETE, new Object[] {String.valueOf(CveRolDiv)});
	}

	public static void desactivarRolDiv(int CveRolDiv, int status){
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.SYSADMIN_ROLES_DIV_UPDATE, new Object[] {String.valueOf(CveRolDiv),String.valueOf(status)});
	}
	
	public static void activarRolDiv(int CveRolDiv, int status){
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.SYSADMIN_ROLES_DIV_UPDATE2, new Object[] {String.valueOf(CveRolDiv),String.valueOf(status)});
	}
	
	public static ArrayList getDivisiones() {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.SYSADMIN_DIVISION_ALL, new Object[]{}, Pc_DivisionVO.class);
	}

	public static void accessLog(String userid, String IP, String accion){
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.SYSADMIN_ACCESS_LOG, new Object[] {userid, IP, accion});
	}

	//PARA INSERTAR EN LA BITACORA DE MOVIMIENTOS DEL USUARIO
	public static void setBitUsuario(int CveUsuario, String accion){
			GeneralDAO dao = new GeneralDAO();
			dao.executeThisQuery(Q.TRANSACCION_USUARIO, new Object[] {String.valueOf(CveUsuario), accion});
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

	/* OBTENER EL JEFE DE TIENDA */
	public static Pc_VendedoresVO getJefeTienda(String claveTienda) {
		GeneralDAO dao = new GeneralDAO();
		return (Pc_VendedoresVO) dao.findValueObject(Q.JEFE_TIENDA_EXISTE, new Object[]{claveTienda},Pc_VendedoresVO.class);
	}

	//	query para saber si un punto de venta existe en CAT_CSI 19/09/2005
	  public static Pc_Punto_VentasVO getPuntoVentaCAT_CSI(String clavePtoVenta ) {
		  GeneralDAO dao = new GeneralDAO();
		return (Pc_Punto_VentasVO) dao.findValueObject(Q.PUNTOS_VENTA_CAT_CSI, new Object[]{clavePtoVenta},Pc_Punto_VentasVO.class);
		 
	  }
	/**
	 * @param usuario
	 * @param password
	 */
	public static void updatePassword(String userid, String password) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.CAMBIA_PASSWORD_PRIMERA_VEZ, new Object[] {userid, password} );
		
	}
	/**
	 * @param usuario
	 */
	public static void updatePcCambiaPassword(String userid) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.UPDATE_PC_CAMBIA_PASSWORD, new Object[] {userid} );
		
	}
	/**
	 * @param usuario
	 */
	public static void actualizaisBlocked(String userid) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(Q.BLOQUEA_USUARIO_UPDATE, new Object[] {userid} );
		
		
	}
	

	
	/**
	 * @param usuario
	 * @return
	 */
	public static ClavesVO cambiaPassword(String userid) {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.CHECA_PRIMERA_VEZ, new Object[]{userid},ClavesVO.class);

	}
	/**
	 * @param usuario
	 * @return
	 */
	public static ClavesVO checaFchAcceso(String userid) {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.CHECA_FECHA_EXPIRACION , new Object[]{userid},ClavesVO.class);

	}

	/**
	 * @param usuario
	 * @return
	 */
	public static ClavesVO checaIsBlocked(String userid) {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.CHECA_PC_ISBLOCKED, new Object[]{userid},ClavesVO.class);

	}
	
	/**
	 * @param usuario
	 * @return
	 */
	public static ClavesVO checaExpirationDate(String userid) {
		GeneralDAO dao = new GeneralDAO();
		return (ClavesVO) dao.findValueObject(Q.CHECA_EXPIRATION_DATE, new Object[]{userid},ClavesVO.class);

	}
	
	/**
	 * @param usuario
	 */
	public static void updateExpiration(String userid) {
		GeneralDAO dao = new GeneralDAO();
		dao.executeThisQuery(358, new Object[] {userid} );
		
	}
	public static ArrayList obtieneSuperiorTableCompleto(Object[] parametros) {
				GeneralDAO dao = new GeneralDAO();
				return dao.findValueObjectsArray(DIV.OBTIENE_QUERY_SUPERIOR_COMPLETO, parametros, Pc_SuperioresVO.class);
	}
	public static ArrayList obtieneSuperiorClavePuesto(Object[] parametros) {
				GeneralDAO dao = new GeneralDAO();
				return dao.findValueObjectsArray(DIV.OBTIENE_QUERY_SUPERIOR_CLAVE_PUESTO, parametros, Pc_SuperioresVO.class);
	}
	public static ArrayList obtieneSuperiorClave(Object[] parametros) {
				GeneralDAO dao = new GeneralDAO();
				return dao.findValueObjectsArray(DIV.OBTIENE_QUERY_SUPERIOR_CLAVE, parametros, Pc_SuperioresVO.class);
	}
	public static ArrayList obtieneSuperiorClaveReferencia(Object[] parametros) {
				GeneralDAO dao = new GeneralDAO();
				return dao.findValueObjectsArray(DIV.OBTIENE_QUERY_SUPERIOR_CLAVE_REF, parametros, Pc_SuperioresVO.class);
	}
	public static Pc_SuperioresVO obtieneClaveSup(String claveSaeo) {
						GeneralDAO dao = new GeneralDAO();
					return (Pc_SuperioresVO) dao.findValueObject(DIV.OBTIENE_CLAVE_SUPERIOR, new Object[] {claveSaeo}, Pc_SuperioresVO.class);
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
		dao.executeThisQuery(Q.PUESTO_ELIMINAR, new Object[] {cveNivel});
	 }
			 	
}