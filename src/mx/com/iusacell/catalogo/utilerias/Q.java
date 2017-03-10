/*
 * Created on 23/02/2005
 *
 */
package mx.com.iusacell.catalogo.utilerias;

/**
 * @author DVAZQUEZA
 *
 */
public class Q {

    // Constantes para queries
	public static int COMUNES 									= 0;

	public static int VENDEDORES_TABLE 							= 1;    // Query para obtener tabla completa de vendedores
	public static int VENDEDORES_DETALLE						= 4;	// Query para obtener datos generales de un vendedor
	public static int VENDEDORES_LISTA_PUESTO					= 6;	// Query para obtener lista vendedores
	public static int VENDEDORES_LISTA_REGION					= 7;	// Query para obtener lista vendedores
	public static int VENDEDOR_INSERTAR							= 10;	// Insert para tabla PC_VENDEDORES
	public static int VENDEDOR_ACTUALIZAR						= 14;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_ELIMINAR							= 37;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_PARAMETRO						= 38;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_CLAVE							= 39;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_ALL							    = 40;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_CLAVE_PERSONA					= 55;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_CLAVE_TIENDA						= 56;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_SUPERIOR_X_PUESTO				= 57;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_BAJA 							= 59;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_NO_SUPERIOR  					= 60;	// Update para tabla PC_VENDEDORES
	public static int VENDEDORES_CHECK_MOVIMIENTOS				= 75;
	public static int VENDEDOR_SUPERIOR_X_ARBOL_DIV 			= 76;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_SUPERIOR_X_ARBOL					= 86;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_SUP_CAD_X_ARBOL					= 102;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_SUBORDINADOS						= 168;	// Update para tabla PC_VENDEDORES
	public static int VENDEDOR_INSERTA_INTER_SUBDIVISION		= 170;	// Insert para tabla PC_INTER_SUBDIVISION
	
	public static int REGIONES_ALL								= 3;	// Query para obtener todas las regiones
	public static int REGION_INSERTAR							= 15;	// Insert para tabla PC_REGION
	public static int REGION_ACTUALIZAR							= 16;	// Update para tabla PC_REGION
	public static int REGION_ELIMINAR							= 17;	// Delete para tabla PC_REGION
	public static int REGION_CLAVE								= 23;   // Query para obtener todos los puestos

	public static int TIPO_CANALES_ALL							= 8;	// Query para obtener lista vendedores
	public static int TIPO_CANALES_INSERTAR						= 29;	// Insert para tabla PC_PUESTOS
	public static int TIPO_CANALES_ACTUALIZAR					= 30;	// Update para tabla PC_PUESTOS
	public static int TIPO_CANALES_ELIMINAR						= 31;	// Delete para tabla PC_PUESTOS
	public static int TIPO_CANALES_CLAVE						= 32;   // Query para obtener todos los puestos
	public static int TIPO_CANALES_SELECTED						= 58;	// Update para tabla PC_VENDEDORES

	public static int CANALES_X_TIPO							= 9;	// Query para obtener lista vendedores
	public static int CANALES_INSERTAR							= 25;	// Insert para tabla PC_PUESTOS
	public static int CANALES_ACTUALIZAR						= 26;	// Update para tabla PC_PUESTOS
	public static int CANALES_ELIMINAR							= 27;	// Delete para tabla PC_PUESTOS
	public static int CANALES_CLAVE								= 28;   // Query para obtener todos los puestos
	public static int CANALES_ALL								= 33;   // Query para obtener todos los puestos

	public static int PUESTO_INSERTAR							= 18;	// Insert para tabla PC_PUESTOS
	public static int PUESTO_ACTUALIZAR							= 19;	// Update para tabla PC_PUESTOS
	public static int PUESTO_ELIMINAR							= 20;	// Delete para tabla PC_PUESTOS
	public static int PUESTOS_ALL 								= 21;   // Query para obtener todos los puestos
	public static int PUESTOS_CLAVE								= 22;   // Query para obtener todos los puestos
	public static int PUESTOS_DETALLE 							= 71;
	public static int PUESTOS_ARBOL 							= 77;
	public static int PUESTOS_NIVEL_MAYOR 						= 78;
	public static int PUESTOS_NIVEL_INSERTAR 					= 79;
	public static int PUESTOS_NIVEL_ELIMINAR 					= 80;
	public static int PUESTOS_NIVEL_OBTENER 					= 81;
	public static int PUESTOS_NIVEL_CLAVE						= 82;   // Query para obtener todos los puestos
	public static int PUESTOS_NIVEL_OBTENER_X_PUESTO			= 83;
	public static int PUESTOS_NIVEL_ACTUALIZAR					= 84;
	public static int PUESTOS_NIVEL_OBTENER_X_PUESTO_SUP		= 85;
	
	public static int PUNTOS_VENTA_TABLE						= 2;	// Query para obtener tabla completa de puntos de venta
	public static int PUNTOS_VENTA_DETALLE						= 5;	// Query para obtener datos generales de un punto de venta
	public static int PUNTOS_VENTA_INSERTAR						= 11;	// Insert para tabla PC_PUNTO_VENTAS
	public static int PUNTOS_VENTA_X_CANAL						= 12;	// Query para obtener lista punto venta		
	public static int PUNTOS_VENTA_ACTUALIZAR					= 13;	// Update para tabla PC_PUNTO_VENTAS		
	public static int PUNTOS_VENTA_PARAMETRO					= 34;   // Query para obtener todos los puestos
	public static int PUNTOS_VENTA_CLAVE						= 35;   // Query para obtener todos los puestos
	public static int PUNTOS_VENTA_ELIMINAR						= 36;   // Query para obtener todos los puestos
	public static int PUNTOS_VENTA_X_REGION						= 72;	// Query para obtener lista punto venta		
	public static int PUNTOS_VENTA_X_DIVISION					= 73;	// Query para obtener lista punto venta		
	public static int PUNTOS_VENTA_X_SUBDIVISION				= 177;	// Query para obtener lista punto venta		

	public static int RELACION_INSERTAR							= 41;	// Insert para tabla PC_PUESTOS
	public static int RELACION_ACTUALIZAR						= 42;	// Update para tabla PC_PUESTOS
	public static int RELACION_ELIMINAR							= 43;	// Update para tabla PC_VENDEDORES
	//public static int RELACION_PARAMETRO						= 44;	// Update para tabla PC_VENDEDORES
	public static int RELACION_CLAVE							= 45;	// Update para tabla PC_VENDEDORES
	//public static int RELACION_ALL						    = 46;	// Update para tabla PC_VENDEDORES
	public static int RELACION_VENDEDORES_X_PUNTO				= 47;	// Update para tabla PC_VENDEDORES
	public static int RELACION_PUNTOS_X_VENDEDOR			    = 48;	// Update para tabla PC_VENDEDORES
	public static int RELACION_PUNTOS_X_VENDEDOR_OPT		    = 49;	// Update para tabla PC_VENDEDORES
	public static int RELACION_DETALLE						    = 94;	// Obtener una objeto relacion a partir de su clave 
	public static int RELACION_PARAMETRO					    = 179;	// Obtener una objeto relacion a partir de su clave 


	public static int DIVISION_ALL								= 50;	// Query para obtener todas las division
	public static int DIVISION_INSERTAR							= 51;	// Insert para tabla PC_DIVISION
	public static int DIVISION_ACTUALIZAR						= 52;	// Update para tabla PC_DIVISION
	public static int DIVISION_ELIMINAR							= 53;	// Delete para tabla PC_DIVISION
	public static int DIVISION_CLAVE							= 54;   // Query para obtener la clave de divisiones
	public static int DIVISION_INSERTA_VENDEDOR					= 87;	// Insert para tabla PC_DIVISION

	public static int SUBDIVISION_ALL							= 171;	// Query para obtener todas las subdivisiones
	public static int SUBDIVISION_INSERTAR						= 172;	// Insert para tabla PC_SUBDIVISION
	public static int SUBDIVISION_ACTUALIZAR					= 173;	// Update para tabla PC_SUBDIVISION
	public static int SUBDIVISION_ELIMINAR						= 174;	// Delete para tabla PC_SUBDIVISION
	public static int SUBDIVISION_CLAVE							= 175;  // Query para obtener la clave de subdivisiones
	public static int SUBDIVISION_X_TIPO						= 176;  // Query para obtener la clave de subdivisiones
	public static int SUBDIVISION_ALL1							= 306;	// Query para obtener todas las subdivisiones

	public static int PERSONAL_TABLE 							= 1;    // Query para obtener tabla completa de vendedores
	public static int PERSONAL_DETALLE							= 4;	// Query para obtener datos generales de un vendedor
	public static int PERSONAL_LISTA_PUESTO						= 6;	// Query para obtener lista vendedores
	public static int PERSONAL_LISTA_REGION						= 7;	// Query para obtener lista vendedores
	public static int PERSONAL_INSERTAR							= 10;	// Insert para tabla PC_VENDEDORES
	public static int PERSONAL_ACTUALIZAR						= 14;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_ELIMINAR							= 37;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_PARAMETRO						= 38;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_PARAMETRO_PERDIDO				= 137;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_PARAMETRO_PERDIDO_COMPLETO    	= 139;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_PARAM_ACT_PERDIDO				= 149;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_PARAM_ACT_PERDIDO_COMPLETO    	= 150;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_CLAVE							= 39;	// Update para tabla PC_VENDEDORES
	//public static int PERSONAL_ALL						    = 40;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_CLAVE_PERSONA					= 55;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_CLAVE_TIENDA						= 56;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_SUPERIOR_X_PUESTO				= 57;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_BAJA 							= 59;	// Update para tabla PC_VENDEDORES
    public static int PERSONAL_ALTA 							= 310;	// Update para tabla PC_VENDEDORES		
	public static int PERSONAL_NO_SUPERIOR  					= 60;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_REASIGNAR_SUPERIOR				= 74;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_SUPERIOR_X_ARBOL					= 76;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_COMPROBAR_SUPERIOR				= 101;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_COMPROBAR_SUP_CAD				= 103;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_REASIGNAR_SUP_CAD				= 104;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_COMPROBAR_NUEVO					= 140;	// Update para tabla PC_VENDEDORES
	public static int PERSONAL_INSERTA_INTER_SUBDIVISION		= 170;	// Insert para tabla PC_INTER_SUBDIVISION
	public static int PERSONAL_COMPROBAR_SUPERIOR_ALTA			= 178;	// Comprobar subordinados pero esten activos
		
	public static int REPORTE_DESC								= 61;	// Insert para tabla PC_VENDEDORES
	public static int REPORTE_DESC_TIENDA						= 93;	// Insert para tabla PC_VENDEDORES
	public static int REPORTE_ASC								= 62;	// Update para tabla PC_VENDEDORES
	//public static int REPORTE_GET_DIR_DIV						= 63;	// Update para tabla PC_VENDEDORES
	public static int REPORTE_GET_DIR_DIV						= 88;	// Update para tabla PC_VENDEDORES
	public static int REPORTE_DESC_NEW							= 133;	// Insert para tabla PC_VENDEDORES
	public static int REPORTE_DESC_TIENDA_NEW					= 134;	// Insert para tabla PC_VENDEDORES


	public static int MOVIMIENTOS_CLAVE							= 64;	// Obtener clave para tabla PC_BIT_MOVIMIENTOS
	public static int MOVIMIENTOS_CAMBIO_SUPERIOR				= 65;	// Update para tabla PC_BIT_MOVIMIENTOS
	public static int MOVIMIENTOS_CAMBIO_PUESTO					= 66;	// Update para tabla PC_BIT_MOVIMIENTOS
	public static int MOVIMIENTOS_CAMBIO_PUNTO_VENTAS			= 67;	// Update para tabla PC_BIT_MOVIMIENTOS
	public static int MOVIMIENTOS_CHECK_VENDEDORES				= 68;	// Select para tabla PC_BIT_MOVIMIENTOS
	public static int MOVIMIENTOS_CHECK_RELACIONES				= 69;	// Select para tabla PC_BIT_MOVIMIENTOS
	public static int MOVIMIENTOS_CAMBIO_VENDEDOR_DATA			= 70;	// Update para tabla PC_BIT_MOVIMIENTOS
    public static int MOVIMIENTOS_CAMBIO_STATUS			        = 311;	// Update para tabla PC_BIT_MOVIMIENTOS	  

	public static int MOVIMIENTOS_TABLE							= 95;	// Select para tabla PC_BIT_MOVIMIENTOS
	public static int MOVIMIENTOS_PARAMETRO						= 96;	// Select para tabla PC_BIT_MOVIMIENTOS


	public static int MAPEO_INSERTAR							= 89;	// Update para tabla PC_VENDEDORES
	public static int MAPEO_ACTUALIZAR							= 90;	// Update para tabla PC_VENDEDORES
	public static int MAPEO_ELIMINAR							= 91;	// Update para tabla PC_VENDEDORES
	public static int MAPEO_VENDEDOR							= 92;	// Update para tabla PC_VENDEDORES
	public static int MAPEO_VENDEDOR_SISTEMA_VENTA				= 452;	// Select para tabla PC_MAPEO_VENDEDOR por CVE_VENDEDOR, PC_SISTEMA, PC_CVE_REG_VENTA

	public static int MAPEO_VENDEDOR_MOV_INSERT					= 453;	// Insert nuevo para tabla CATVEN.PC_MAPEO_VENDEDOR_MOVIMIENTOS

	public static int LOGIN_ROL				             		= 97;	// Login
	public static int LOGIN_ACCIONES							= 98;	// Login
	public static int LOGIN_DIVISIONES							= 99;	// Login
	public static int LOGIN_ENTER             					= 100;	// Login
	public static int LOGIN_ROL_UNICO		             		= 132;	// Login
	public static int LOGIN_SUBDIVISIONES						= 169;	// Login

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
	
	public static int SYSADMIN_ACCESS_ACCOUNT_ALL				= 151;
	
	public static int SYSADMIN_APP_ACTIONS_ALL					= 152;
	public static int SYSADMIN_ROLES_ALL						= 153;
	public static int SYSADMIN_PERFILES_PARAM					= 154;
	public static int SYSADMIN_ACCESS_ACCOUNT_INSERT			= 155;
	public static int SYSADMIN_ACCESS_ACCOUNT_DELETE			= 156;
	public static int SYSADMIN_ACCESS_ACCOUNT_UPDATE			= 157;
	public static int SYSADMIN_ACCESS_ACCOUNT_CHG_PSW			= 158;
	public static int SYSADMIN_PERFILES_INSERT					= 159;
	public static int SYSADMIN_PERFILES_DELETE					= 160;
	public static int SYSADMIN_ROLES_DIV_INSERT					= 161;
	public static int SYSADMIN_ROLES_DIV_ALL					= 162;
	public static int SYSADMIN_ROLES_DIV_DELETE					= 163;
	public static int SYSADMIN_DIVISION_ALL						= 164;
	public static int SYSADMIN_ROLES_DIV_UPDATE					= 165;
	public static int SYSADMIN_ROLES_DIV_UPDATE1				= 346;
	public static int SYSADMIN_ROLES_DIV_UPDATE2				= 348;
	public static int SYSADMIN_ACCESS_LOG						= 166;
	
	public static int ULTIMO_QUERY_ID							= 179;   // El numero de Query en que vamos		

	public static int REPORTE_DESC_NEW1							= 302;	// Insert para tabla PC_VENDEDORES
	public static int REPORTE_DESC_TIENDA_NEW1					= 303;	// Insert para tabla PC_VENDEDORES
	public static int REPORTE_DESC_NEW2							= 304;	// Insert para tabla PC_VENDEDORES
	public static int REPORTE_DESC_TIENDA_NEW2					= 305;	// Insert para tabla PC_VENDEDORES
	
	public static int EMPRESA_ALL								= 307; //catalogo empresas
	public static int EMPRESA_INSERTAR							= 308; //catalogo empresas
	public static int EMPRESA_ELIMINAR							= 309; //catalogo empresas
	public static int EMPRESA_ACTUALIZAR						= 340; //catalogo empresas

	public static int VENDEDORES_DETALLE_CLAVE					= 312;	// Query para obtener datos generales de un vendedor por clave
	public static int MOVIMIENTOS_DETALLE_CLAVE_REF				= 314;	// Obtener clave para tabla PC_BIT_MOVIMIENTOS
	
	public static int PERSONAL_INSERTAR_HIS						= 315; //-- Query para INSERTAR EN LA TABLA DE HISTORICOS
	public static int PERSONAL_MOVIMIENTOS_HIS					= 316; //-- Query para INSERTAR EN LA TABLA DE HISTORICOS
	public static int PERSONAL_BAJA_HIS							= 317; //-- Query para INSERTAR EN LA TABLA DE HISTORICOS
	public static int PERSONAL_MOV_HIS							= 319; //-- Query para INSERTAR EN LA TABLA DE HISTORICOS
	public static int PERSONAL_MOVAMBAS_HIS						= 320; //-- Query para INSERTAR EN LA TABLA DE HISTORICOS
	public static int PERSONAL_ACTUALIZA_HIS					= 321; //-- Query para INSERTAR EN LA TABLA DE HISTORICOS
				    
	public static int PUNTOS_VENTA_PARAMETRO1					= 322;   // Query para obtener todos los puestos
	
	public static int REPORTE_DESC_HISTORICO  					= 332; // Query obtiene la información jerárquica para acceso de divisionales
	public static int REPORTE_DESC_TIENDA_HISTORICO 			= 333; // Query obtiene la información jerárquica de una tienda para acceso de divisionales

	public static int PUNTOS_VENTA_CAT_CSI						= 334;   // Query para obtener punto de venta existente en CAT_CSI
	
	public static int VENDEDOR_CAMBIA_DIVISION					= 335;   // Query para cambiar de division a un vendedor
	
	public static int PUNTOS_VENTA_NUM_ECONOMICO				= 336;   // Query para TRAER PUNTOS DE VENTA POR NUMERO ECONOMICO ADMINISTRADOR
	public static int PUNTOS_VENTA_NUMECONOMICO_DIV			    = 337;   // Query para TRAER PUNTOS DE VENTA POR NUMERO ECONOMICO DIVISIONAL
	
	public static int PUESTOS_JEFE_TIENDA						= 338;  //QUERY PARA TRAER EL PERSONAL A CARGO DE UN JEFE DE TIENDA
	public static int JEFE_TIENDA_EXISTE 						= 339; //QUERY PARA TRAERME EL JEFE DE UNA TIENDA EN CASO DE EXISTIR
	
	public static int TIENDA_VENDEDOR_EXISTE 					= 341; //QUERY PARA TRAERME LAS TIENDAS QUE TIENEN VENDEDORES ASIGNADOS
    public static int ADMIN_QUERY_REPORTE_PTOVENTA              = 342; //QUERY PARA REPORTE DE PUNTO DE VENTA
    public static int PUNTO_VENTA_EXISTE_PVS					= 343;//QUERY PARA TRAERM PUNTO CE VENTA QUE EXISTE EN PVS
    
	public static int PUESTOS_NO_REPITE							= 344;//Query para traer los puestos que no se pueden repetir
	public static int ROL_USUARIO_INDISTINTO					= 347;//Query para traer LOS ROLES QUE TENGA ASOCIADO EL USUARIO ESTEN BLOQUEADOS O NO
	
	public static int TRANSACCION_USUARIO						= 349;//Query para registrar las transacciones del usuario
	public static int ULTIMO_QUERY_MAS_UNO						= 350; //ULTIMO QUERY
	public static int CHECA_FECHA_EXPIRACION                    = 351;//Query para checar la fecha de ultimo de acceso del usuario
	
	public static int BLOQUEA_USUARIO_UPDATE                    = 352;//Query para bloquear al usuario
	
	
	public static int CHECA_PRIMERA_VEZ                         =353;//Query para checar si es la primera vez que entra el usuario
	
	public static int CAMBIA_PASSWORD_PRIMERA_VEZ               =354;//Query para cambiar el password la primera vez
	
	public static int UPDATE_PC_CAMBIA_PASSWORD                 =355;//Query para cambiar el campo PC_CAMBIA_PASSWORD
	
	public static int CHECA_PC_ISBLOCKED							= 356;//Query para checar isblocked
	
	public static int CHECA_EXPIRATION_DATE						=357;//Query para checar la fecha de expiracion
	
	public static int RESETEAR_FECHA_EXPIRACION					=358;//Update para resetar fecha expiracion
	
	public static int OBTENER_DIAS 								=359;//Obtener los doas de la semana
	
	public static int OBTENER_EVENTOS 							=360;//Obtener los eventos del checador
	
	public static int OBTENER_DIA 								=361;//Obteher dia
	
	public static int AGREGAR_PC_HORARIO 						=362;//agregar pc_horario
	
	public static int AGREGAR_PC_HORARIO_SEMANA 				=363;//agregar pc_horario_semana
	
	public static int AGREGAR_PC_HORARIO_DIA 					=364;//agregar pc_horario_dia
	
	public static int OBTENER_SIG_CVE_HORARIO 					=365;//obtener siguiente clave de horario
	
	public static int OBTENER_HORARIO_DETALLE 					=366;//obtener el detalle de un horario
	
	public static int VALIDAR_HORARIO_PUESTO 					=368;//obtener SI EL PUESTO REQUIERE HORARIO
	
	public static int OBTENER_HORARIOS 							=369;//obtener los horarios
	
	public static int OBTENER_HORARIO							=370;//obtener los horarios
	
	public static int HORARIOS_ALL								=371;//obtener los horarios


	public static int AGREGAR_TRANSACCION						=372; //Para Agregar Transaccion
	
	public static int OBTENER_PERFILES							=373; //Para obtener los perfiles
	
	public static int AGREGAR_NUEVO_ROL							=374; //Agrega nuevo rol
	
	public static int DESC_ROL_MODIFICAR						=375; //Obtiene desc del rol
	
	public static int MODIFICAR_ROL								=376; //Modifica Rol
	
 	public static int INSERT_VENDEDORES_HIS 				    = 377; // Query para actualizar la tabla de vendedores
 	
	public static int OBTENER_HORAS 				    	    = 378; //Query para obtener las horas
	
	public static int OBTENER_MINUTOS 				    	    = 379; //Query para obtener los minutos
	
	public static int CAMBIAR_PERSONAL 				    	    = 380; //UPDATE PARA MODIFICAR UNA PERSONA
	
	public static int CERRAR_MOV 				    	    	= 381; //CERRAR MOVIMIENTO
	
	public static int OBTENER_MARCAS 				    	    = 382; //OBTENER MARCAS
	
	public static int OBTENER_MARCA 				    	    = 383; //OBTENER MARCA
	
	public static int AGREGAR_MARCA 				    	    = 384; //AGREGAR MARCA
	
	public static int ELIMINAR_MARCA				    	    = 385; //ELIMINAR MARCA
	
	public static int MODIFICAR_MARCA				    	    = 386; //MODIFICAR MARCA
	
	public static int MARCAS_ALL     				    	    = 387; //OBTENER TODAS LAS MARCAS
	
	public static int AGREGAR_TIENDA_MARCAS     				= 388; //AGREGAR_TIENDA_MARCAS
	
	public static int VENDEDOR_SUPERIOR_X_ARBOL2				= 394;
	
	public static int VENDEDOR_SUPERIOR_X_ARBOL_DIV2			= 396;//Consulta para obtener los superiores asignados a un puesto especifico
	
	public static int VENDEDOR_DETALLE							= 397;// Query para obtener datos generales de un vendedor						
	public static int CERRAR_MOVIMIENTO							= 398;// Update para cerrar los movimientos en Historico durante la Baja	
	public static int APLICAR_BAJA_HIST							= 399;// Insert para aplicar la baja en Historico
	public static int OBTENER_DATOS_VENDEDOR					= 400;// Obtener el registro del vendedor	
	public static int ELIMINAR_TIENDAS_VENDEDOR					= 401;// Obtener las tiendas del vendedor			

	public static int CERRAR_PERIODO_BAJA_VEND					= 402;// Cerrar el Periodo durante el que estuvo dado de baja 			
	public static int AGREGAR_ALTA_HIST_VEND					= 403;// Agregar el registro de alta en el historico	
	public static int CAMBIAR_ESTATUS_BAJA_ALTA_VEND			= 404;// Cambia el estatus del vendedor de baja a alta	

	public static int CAMBIAR_BAJA_ALTA							= 407;// Cambiar la Baja por ALTA
	public static int ESTATUS_VENDEDOR							= 408;// Obtener el Estatus del Vendedor
	public static int OBTENER_VENDEDOR							= 409;// Obtener UNO O MAS  VendedorES
	public static int OBTENER_REPORTE_CHECADOR					= 410;// Obtener Reporte Checador
	public static int ELIMINAR_REPORTE_CHECADOR					= 411;// Eliminar un reporte de Checador asociado con una secuencia
	public static int ESTADOS_ALL								= 412;// Obtener todos los estados
	public static int AGREGAR_CANAL								= 413;// Agregar Canal
	public static int OBTENER_CANAL								= 414;// Obtener Canal
	public static int MODIFICAR_CANAL							= 415;// Modificar Canal
	public static int ELIMINAR_RELACIONES_CANAL					= 416;// Eliminar las relaciones canal-puntos de venta
	public static int ELIMINAR_CANAL							= 417;// Eliminar Canal
	public static int ELIMINAR_PUNTOSVENTA_CANAL				= 418;// Eliminar Puntos de Venta asociados a un Canal
	public static int AGREGAR_VENDEDOR							= 419;// Agregar un canal como vendedor
	public static int BUSCAR_PUNTOVENTA						    = 420;// Buscar punto de venta en vendedores
	public static int AGREGAR_RELACION_PTOVENTA					= 421;// Buscar punto de venta en vendedores
	public static int ELIMINAR_CANAL_VENDEDOR					= 422;// Eliminar un vendedor que representa un canal		
	public static int EXISTE_CLAVE_CANAL 					    = 423;// vALIDAR SI UNA CLAVE DE CANAL A AÑADIR EXISTE
					
	/*
	 * Adicion de la consulta de la tabla C_CONFIGURACION.
	 * @author CAL
	 *
	 * Fecha: 120208_17:09.
	 */
    public static int OBTENER_NIVELES 					        = 425;// Buscar niveles de venta para puestos. 
	public static int ALL_NIVELES		                        = 426;// Obtener toda la tabla de los niveles de venta.
    public static int ELIMINAR_NIVELES                          = 427;// Eliminar un registro de la tabla C_CONFIGURACION.
    public static int ACTUALIZAR_NIVELES                        = 428;// Actualizar un registro de la tabla C_CONFIGURACION.
    public static int AGREGAR_NIVELES                           = 429;// Agregar un registro de la tabla C_CONFIGURACION.
    public static int CLAVES_NIVELES                            = 430;// Obtiene el último id más uno de la tabla C_CONFIGURACION.
    public static int EJECUTAR_X                                = 431;// Ejecuta.
	public static int PUESTOS_NIVEL_VENTAS						= 432;// Puestos que necesitan tener un nivel de ventas.
	public static int BUSQUEDA_VENDEDORES						= 433;// Busqueda de vendedores v2.
	public static int PUESTOS_NIVEL_VENTAS2                     = 434;// Busca los usuarios permitidos en el sistema para ver todas las divisiones.
    public static int DETALLE_VENDEDOR						    = 435;// Detalle para vendedores v2.0
	public static int REGION_COMERCIAL							= 440;// Obtiene la region comercial x pto de vta
	public static int CIUDAD_COMERCIAL							= 447;// Obtiene la ciudad comercial x estado
	public static int ESTADO_COMERCIAL							= 412;// Obtiene el estado comercial x division
	public static int NIVEL_VENTAS								= 436;// Combo de niveles de venta segun catalogo
    public static int BUSQUEDA_VENDEDORES_CONFIRMAR             = 437;// Tabla de las coincidencias con el nuevo vendedor.
    public static int SUBDIVISIONES_TODAS                       = 438;// Trae todas las divisiones sin importar si estan dadas de baja en el portal.
    public static int BUSQUEDA_VENDEDORES_SUPERIORES            = 441;// Busqueda de vendedores en alta y con subordinados.
    public static int CANALES_X_TIPO_2							= 442;// Query para obtener CLAVE OTA de los distribuidores.
    public static int PUNTOS_VENTA_REACTIVAR                    = 443;// Query para reactivar y borrar fecha de baja del punto de venta.
    public static int PUNTOS_VENTA_ELIMINAR2                    = 444;// Query para eliminar e igualar fechas de baja y de inicio de operacion.
    public static int PUNTOS_VENTA_REFERENCIA                   = 445;// Query para buscar Puntos de Venta por numero de referencia.
    public static int PUNTOS_VENTA_REFERENCIA2                  = 446;// Query para buscar Puntos de Venta por numero de referencia.
    public static int USUS_ADMIN                                = 448;// Usu admin.
    
    public static int ELIMINAR_SISTEMAS_VENDEDOR				= 449;//Elimina los registros del mapeo entre sistemas asignados y el vendedor
	public static int TODAS_CIUDADES_COMERCIALES				= 450;// Obtiene todas las ciudades comerciales de todos los estados
	public static int CATALOGO_PARAMETROS						= 451;//Obtiene el catalogo de parametros para conectar al RFC de SAP	    		
}
