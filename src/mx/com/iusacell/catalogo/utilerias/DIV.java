/*
 * Created on 23/02/2005
 *
 */
package mx.com.iusacell.catalogo.utilerias;

/**
 * @author DVAZQUEZA
 *
 */
public class DIV {

    // Constantes para queries
//		public static int COMUNES 									= 0;
//
//		public static int VENDEDORES_TABLE 							= 1;    // Query para obtener tabla completa de vendedores
		public static int VENDEDORES_DETALLE						= 4;	// Query para obtener datos generales de un vendedor
//		public static int VENDEDORES_LISTA_PUESTO					= 6;	// Query para obtener lista vendedores
//		public static int VENDEDORES_LISTA_REGION					= 7;	// Query para obtener lista vendedores
//		public static int VENDEDOR_INSERTAR							= 10;	// Insert para tabla PC_VENDEDORES
//		public static int VENDEDOR_ACTUALIZAR						= 14;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_ELIMINAR							= 37;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_PARAMETRO						= 38;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_CLAVE							= 39;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_ALL							    = 40;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_CLAVE_PERSONA					= 55;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_CLAVE_TIENDA						= 56;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_SUPERIOR_X_PUESTO				= 57;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_BAJA 							= 59;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_NO_SUPERIOR  					= 60;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDORES_CHECK_MOVIMIENTOS				= 75;
//		public static int VENDEDOR_SUPERIOR_X_ARBOL_DIV 			= 76;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_SUPERIOR_X_ARBOL					= 86;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_SUP_CAD_X_ARBOL					= 102;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_SUBORDINADOS						= 168;	// Update para tabla PC_VENDEDORES
//		public static int VENDEDOR_INSERTA_INTER_SUBDIVISION		= 170;	// Insert para tabla PC_INTER_SUBDIVISION
//		
//		public static int REGIONES_ALL								= 3;	// Query para obtener todas las regiones
//		public static int REGION_INSERTAR							= 15;	// Insert para tabla PC_REGION
//		public static int REGION_ACTUALIZAR							= 16;	// Update para tabla PC_REGION
//		public static int REGION_ELIMINAR							= 17;	// Delete para tabla PC_REGION
//		public static int REGION_CLAVE								= 23;   // Query para obtener todos los puestos
//
//		public static int TIPO_CANALES_ALL							= 8;	// Query para obtener lista vendedores
//		public static int TIPO_CANALES_INSERTAR						= 29;	// Insert para tabla PC_PUESTOS
//		public static int TIPO_CANALES_ACTUALIZAR					= 30;	// Update para tabla PC_PUESTOS
//		public static int TIPO_CANALES_ELIMINAR						= 31;	// Delete para tabla PC_PUESTOS
//		public static int TIPO_CANALES_CLAVE						= 32;   // Query para obtener todos los puestos
//		public static int TIPO_CANALES_SELECTED						= 58;	// Update para tabla PC_VENDEDORES
//
//		public static int CANALES_X_TIPO							= 9;	// Query para obtener lista vendedores
//		public static int CANALES_INSERTAR							= 25;	// Insert para tabla PC_PUESTOS
//		public static int CANALES_ACTUALIZAR						= 26;	// Update para tabla PC_PUESTOS
//		public static int CANALES_ELIMINAR							= 27;	// Delete para tabla PC_PUESTOS
//		public static int CANALES_CLAVE								= 28;   // Query para obtener todos los puestos
//		public static int CANALES_ALL								= 33;   // Query para obtener todos los puestos
//
//		public static int PUESTO_INSERTAR							= 18;	// Insert para tabla PC_PUESTOS
//		public static int PUESTO_ACTUALIZAR							= 19;	// Update para tabla PC_PUESTOS
//		public static int PUESTO_ELIMINAR							= 20;	// Delete para tabla PC_PUESTOS
//		public static int PUESTOS_ALL 								= 21;   // Query para obtener todos los puestos
//		public static int PUESTOS_CLAVE								= 22;   // Query para obtener todos los puestos
//		public static int PUESTOS_DETALLE 							= 71;
//		public static int PUESTOS_ARBOL 							= 77;
//		public static int PUESTOS_NIVEL_MAYOR 						= 78;
//		public static int PUESTOS_NIVEL_INSERTAR 					= 79;
//		public static int PUESTOS_NIVEL_ELIMINAR 					= 80;
//		public static int PUESTOS_NIVEL_OBTENER 					= 81;
//		public static int PUESTOS_NIVEL_CLAVE						= 82;   // Query para obtener todos los puestos
//		public static int PUESTOS_NIVEL_OBTENER_X_PUESTO			= 83;
//		public static int PUESTOS_NIVEL_ACTUALIZAR					= 84;
//		public static int PUESTOS_NIVEL_OBTENER_X_PUESTO_SUP		= 85;
//		
//		public static int PUNTOS_VENTA_TABLE						= 2;	// Query para obtener tabla completa de puntos de venta
//		public static int PUNTOS_VENTA_DETALLE						= 5;	// Query para obtener datos generales de un punto de venta
//		public static int PUNTOS_VENTA_INSERTAR						= 11;	// Insert para tabla PC_PUNTO_VENTAS
//		public static int PUNTOS_VENTA_X_CANAL						= 12;	// Query para obtener lista punto venta		
//		public static int PUNTOS_VENTA_ACTUALIZAR					= 13;	// Update para tabla PC_PUNTO_VENTAS		
//		public static int PUNTOS_VENTA_PARAMETRO					= 34;   // Query para obtener todos los puestos
//		public static int PUNTOS_VENTA_CLAVE						= 35;   // Query para obtener todos los puestos
//		public static int PUNTOS_VENTA_ELIMINAR						= 36;   // Query para obtener todos los puestos
//		public static int PUNTOS_VENTA_X_REGION						= 72;	// Query para obtener lista punto venta		
//		public static int PUNTOS_VENTA_X_DIVISION					= 73;	// Query para obtener lista punto venta		
//		public static int PUNTOS_VENTA_X_SUBDIVISION				= 177;	// Query para obtener lista punto venta		
//	
//		public static int RELACION_INSERTAR							= 41;	// Insert para tabla PC_PUESTOS
//		public static int RELACION_ACTUALIZAR						= 42;	// Update para tabla PC_PUESTOS
//		public static int RELACION_ELIMINAR							= 43;	// Update para tabla PC_VENDEDORES
//		//public static int RELACION_PARAMETRO						= 44;	// Update para tabla PC_VENDEDORES
//		public static int RELACION_CLAVE							= 45;	// Update para tabla PC_VENDEDORES
//		//public static int RELACION_ALL							    = 46;	// Update para tabla PC_VENDEDORES
//		public static int RELACION_VENDEDORES_X_PUNTO				= 47;	// Update para tabla PC_VENDEDORES
//		public static int RELACION_PUNTOS_X_VENDEDOR			    = 48;	// Update para tabla PC_VENDEDORES
//		public static int RELACION_PUNTOS_X_VENDEDOR_OPT		    = 49;	// Update para tabla PC_VENDEDORES
//		public static int RELACION_DETALLE						    = 94;	// Obtener una objeto relacion a partir de su clave 
//		public static int RELACION_PARAMETRO					    = 179;	// Obtener una objeto relacion a partir de su clave 
//
//
//		public static int DIVISION_ALL								= 50;	// Query para obtener todas las division
//		public static int DIVISION_INSERTAR							= 51;	// Insert para tabla PC_DIVISION
//		public static int DIVISION_ACTUALIZAR						= 52;	// Update para tabla PC_DIVISION
//		public static int DIVISION_ELIMINAR							= 53;	// Delete para tabla PC_DIVISION
//		public static int DIVISION_CLAVE							= 54;   // Query para obtener la clave de divisiones
//		public static int DIVISION_INSERTA_VENDEDOR					= 87;	// Insert para tabla PC_DIVISION
//
//		public static int SUBDIVISION_ALL							= 171;	// Query para obtener todas las subdivisiones
//		public static int SUBDIVISION_INSERTAR						= 172;	// Insert para tabla PC_SUBDIVISION
//		public static int SUBDIVISION_ACTUALIZAR					= 173;	// Update para tabla PC_SUBDIVISION
//		public static int SUBDIVISION_ELIMINAR						= 174;	// Delete para tabla PC_SUBDIVISION
//		public static int SUBDIVISION_CLAVE							= 175;  // Query para obtener la clave de subdivisiones
//		public static int SUBDIVISION_X_TIPO						= 176;  // Query para obtener la clave de subdivisiones
//
//		public static int PERSONAL_TABLE 							= 1;    // Query para obtener tabla completa de vendedores
//		public static int PERSONAL_DETALLE							= 4;	// Query para obtener datos generales de un vendedor
//		public static int PERSONAL_LISTA_PUESTO						= 6;	// Query para obtener lista vendedores
//		public static int PERSONAL_LISTA_REGION						= 7;	// Query para obtener lista vendedores
//		public static int PERSONAL_INSERTAR							= 10;	// Insert para tabla PC_VENDEDORES
//		public static int PERSONAL_ACTUALIZAR						= 14;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_ELIMINAR							= 37;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_PARAMETRO						= 38;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_PARAMETRO_PERDIDO				= 137;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_PARAMETRO_PERDIDO_COMPLETO    	= 139;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_PARAM_ACT_PERDIDO				= 149;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_PARAM_ACT_PERDIDO_COMPLETO    	= 150;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_CLAVE							= 39;	// Update para tabla PC_VENDEDORES
//		//public static int PERSONAL_ALL							    = 40;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_CLAVE_PERSONA					= 55;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_CLAVE_TIENDA						= 56;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_SUPERIOR_X_PUESTO				= 57;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_BAJA 							= 59;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_NO_SUPERIOR  					= 60;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_REASIGNAR_SUPERIOR				= 74;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_SUPERIOR_X_ARBOL					= 76;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_COMPROBAR_SUPERIOR				= 101;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_COMPROBAR_SUP_CAD				= 103;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_REASIGNAR_SUP_CAD				= 104;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_COMPROBAR_NUEVO					= 140;	// Update para tabla PC_VENDEDORES
//		public static int PERSONAL_INSERTA_INTER_SUBDIVISION		= 170;	// Insert para tabla PC_INTER_SUBDIVISION
//		public static int PERSONAL_COMPROBAR_SUPERIOR_ALTA			= 178;	// Comprobar subordinados pero esten activos
//			
//		public static int REPORTE_DESC								= 61;	// Insert para tabla PC_VENDEDORES
//		public static int REPORTE_DESC_TIENDA						= 93;	// Insert para tabla PC_VENDEDORES
//		public static int REPORTE_ASC								= 62;	// Update para tabla PC_VENDEDORES
////		public static int REPORTE_GET_DIR_DIV						= 63;	// Update para tabla PC_VENDEDORES
//    	public static int REPORTE_GET_DIR_DIV						= 88;	// Update para tabla PC_VENDEDORES
//		public static int REPORTE_DESC_NEW							= 133;	// Insert para tabla PC_VENDEDORES
//		public static int REPORTE_DESC_TIENDA_NEW					= 134;	// Insert para tabla PC_VENDEDORES
//
//
//		public static int MOVIMIENTOS_CLAVE							= 64;	// Obtener clave para tabla PC_BIT_MOVIMIENTOS
//		public static int MOVIMIENTOS_CAMBIO_SUPERIOR				= 65;	// Update para tabla PC_BIT_MOVIMIENTOS
//		public static int MOVIMIENTOS_CAMBIO_PUESTO					= 66;	// Update para tabla PC_BIT_MOVIMIENTOS
//		public static int MOVIMIENTOS_CAMBIO_PUNTO_VENTAS			= 67;	// Update para tabla PC_BIT_MOVIMIENTOS
//		public static int MOVIMIENTOS_CHECK_VENDEDORES				= 68;	// Select para tabla PC_BIT_MOVIMIENTOS
//		public static int MOVIMIENTOS_CHECK_RELACIONES				= 69;	// Select para tabla PC_BIT_MOVIMIENTOS
//		public static int MOVIMIENTOS_CAMBIO_VENDEDOR_DATA			= 70;	// Update para tabla PC_BIT_MOVIMIENTOS
//
//		public static int MOVIMIENTOS_TABLE							= 95;	// Select para tabla PC_BIT_MOVIMIENTOS
//		public static int MOVIMIENTOS_PARAMETRO						= 96;	// Select para tabla PC_BIT_MOVIMIENTOS
//
//	
//		public static int MAPEO_INSERTAR							= 89;	// Update para tabla PC_VENDEDORES
//		public static int MAPEO_ACTUALIZAR							= 90;	// Update para tabla PC_VENDEDORES
//		public static int MAPEO_ELIMINAR							= 91;	// Update para tabla PC_VENDEDORES
//		public static int MAPEO_VENDEDOR							= 92;	// Update para tabla PC_VENDEDORES
//
//		public static int LOGIN_ROL				             		= 97;	// Login
//		public static int LOGIN_ACCIONES							= 98;	// Login
//		public static int LOGIN_DIVISIONES							= 99;	// Login
//		public static int LOGIN_ENTER             					= 100;	// Login
//		public static int LOGIN_ROL_UNICO		             		= 132;	// Login
//		public static int LOGIN_SUBDIVISIONES						= 169;	// Login
//
		public static int ADMIN_QUERY_VENDEDORES_TABLE 				= 105; //-- Query Admin equal 1
		public static int ADMIN_QUERY_PUNTOS_VENTA_TABLE			= 106; //-- Query Admin equal 2
		public static int ADMIN_QUERY_VENDEDORES_DETALLE			= 107; //-- Query Admin equal 4
		public static int ADMIN_QUERY_PUNTOS_VENTA_DETALLE          = 108; //-- Query Admin equal 5
		public static int ADMIN_QUERY_VENDEDORES_LISTA_PUESTO		= 109; //-- Query Adimin equal 6
		public static int ADMIN_QUERY_PUNTOS_VENTA_X_CANAL			= 110; //-- Query Adimin equal 12
		public static int ADMIN_QUERY_VENDEDOR_PARAMETRO			= 111; //-- Query Adimin equal 38
		public static int ADMIN_QUERY_VENDEDOR_PARAMETRO_COMPLETO	= 138; //-- Query Adimin equal 38
		public static int ADMIN_QUERY_VENDEDOR_ALL					= 112; //-- Query Admin equal 40
		public static int ADMIN_QUERY_RELACION_PARAMETRO			= 113; //-- Query Admin equal 44
		public static int ADMIN_QUERY_RELACION_ALL					= 114; //-- Query Admin equal 46
		public static int ADMIN_QUERY_RELACION_VENDEDORES_X_PUNTO	= 115; //-- Query Admin equal 47
		public static int ADMIN_QUERY_RELACION_PUNTOS_X_VENDEDOR	= 116; //-- Query Admin equal 48
		public static int ADMIN_QUERY_RELACION_PUNTOS_X_VENDEDOR_OPT= 117; //-- Query Admin equal 49
		public static int ADMIN_QUERY_DIVISION_ALL					= 118; //-- Query Admin equal 50
		public static int ADMIN_QUERY_REPORTE_DESC					= 119; //-- Query Admin equal 61
		public static int ADMIN_QUERY_REPORTE_ASC					= 120; //-- Query Admin equal 62
		public static int ADMIN_QUERY_MOVIMIENTOS_CHECK_VENDEDORES	= 121; //-- Query Admin equal 68
		public static int ADMIN_QUERY_MOVIMIENTOS_CHECK_RELACIONES	= 122; //-- Query Admin equal 69
		public static int ADMIN_QUERY_PUNTOS_VENTA_X_REGION			= 123; //-- Query Admin equal 72
		public static int ADMIN_QUERY_PUNTOS_VENTA_X_SUBDIVISION	= 177; //-- Query Admin equal 177
		public static int ADMIN_QUERY_VENDEDORES_CHECK_MOVIMIENTOS	= 124; //-- Query Admin equal 75
		public static int ADMIN_QUERY_VENDEDOR_SUPERIOR_X_ARBOL		= 125; //-- Query Admin equal 86
		public static int ADMIN_QUERY_MAPEO_VENDEDOR				= 126; //-- Query Admin equal 92
		public static int ADMIN_QUERY_REPORTE_DESC_TIENDA			= 127; //-- Query Admin equal 93
		public static int ADMIN_QUERY_RELACION_DETALLE				= 128; //-- Query Admin equal 94
		public static int ADMIN_QUERY_MOVIMIENTOS_TABLE				= 129; //-- Query Admin equal 95
		public static int ADMIN_QUERY_MOVIMIENTOS_PARAMETRO			= 130; //-- Query Admin equal 96
		public static int ADMIN_QUERY_VENDEDOR_SUP_CAD_X_ARBOL		= 131; //-- Query Admin equal 102
		public static int ADMIN_QUERY_REPORTE_DESC_NEW				= 135; //-- Query Admin equal 133
		public static int ADMIN_QUERY_REPORTE_DESC_TIENDA_NEW		= 136; //-- Query Admin equal 134
		public static int ADMIN_QUERY_PERSONAL_COMPROBAR_NUEVO		= 141; //-- Query Admin equal 140
		public static int ADMIN_QUERY_VENDEDOR_PARAM_ACT			= 147;
		public static int ADMIN_QUERY_VENDEDOR_PARAM_COMPLETO_ACT	= 148;
		public static int ADMIN_QUERY_VENDEDOR_SUBORDINADOS			= 167;

		public static int SUPERIOR_PARAMETRO_PERDIDO				= 142; //-- Query Admin equal 137
		public static int ADMIN_QUERY_SUPERIOR_PARAMETRO_COMPLETO	= 143; //-- Query Admin equal 138
		public static int SUPERIOR_PARAMETRO_PERDIDO_COMPLETO		= 144; //-- Query Admin equal 139
		public static int ADMIN_QUERY_SUPERIOR_PARAMETRO			= 145; //-- Query Admin equal 111 
		
//		public static int SYSADMIN_ACCESS_ACCOUNT_ALL				= 151;
//		public static int SYSADMIN_APP_ACTIONS_ALL					= 152;
//		public static int SYSADMIN_ROLES_ALL						= 153;
//		public static int SYSADMIN_PERFILES_PARAM					= 154;
//		public static int SYSADMIN_ACCESS_ACCOUNT_INSERT			= 155;
//		public static int SYSADMIN_ACCESS_ACCOUNT_DELETE			= 156;
//		public static int SYSADMIN_ACCESS_ACCOUNT_UPDATE			= 157;
//		public static int SYSADMIN_ACCESS_ACCOUNT_CHG_PSW			= 158;
//		public static int SYSADMIN_PERFILES_INSERT					= 159;
//		public static int SYSADMIN_PERFILES_DELETE					= 160;
//		public static int SYSADMIN_ROLES_DIV_INSERT					= 161;
//		public static int SYSADMIN_ROLES_DIV_ALL					= 162;
//		public static int SYSADMIN_ROLES_DIV_DELETE					= 163;
//		public static int SYSADMIN_DIVISION_ALL						= 164;
//		public static int SYSADMIN_ROLES_DIV_UPDATE					= 165;
//		public static int SYSADMIN_ACCESS_LOG						= 166;
		
		public static int ULTIMO_QUERY_ID							= 179;   // El numero de Query en que vamos		
		
	    public static int ADMIN_QUERY_REPORTE_DESC_NEW1				= 300; //-- Query Admin equal 133
        public static int ADMIN_QUERY_REPORTE_DESC_TIENDA_NEW1		= 301; //-- Query Admin equal 134
		public static int ADMIN_QUERY_REPORTE_DESC_NEW2				= 345; //-- Query Admin equal 133

		
		public static int ADMIN_QUERY_VENDEDORES_DETALLE_BY_CLAVE   = 313; //-- Query recupera detalle por clave de empleado por referencia
		public static int MOVIMIENTOS_DETALLE_CLAVE_REF				= 314; //-- Query para mostrar detalle y join con bit_movimientos (reportes)
		
		public static int ADMIN_QUERY_TIENDA_DESC_HISTORICO         = 333; //-- Query Admin Historico para jerarquia TIENDAS
		public static int ADMIN_QUERY_REPORTE_DESC_HISTORICO        = 332; //-- Query Admin Historico para jerarquia PERSONAL
		public static int OBTIENE_QUERY_SUPERIOR_COMPLETO   		=390;
		public static int OBTIENE_QUERY_SUPERIOR_CLAVE_PUESTO  		=391;
		public static int OBTIENE_QUERY_SUPERIOR_CLAVE   	  		=392;
		public static int OBTIENE_QUERY_SUPERIOR_CLAVE_REF 			=393;
		public static int OBTIENE_CLAVE_SUPERIOR 			        =395;
	
			
}
