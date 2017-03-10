
package mx.com.iusacell.catalogo.utilerias;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import mx.com.iusacell.catalogo.web.seguridad.struts.action.LoginAction;

import org.apache.log4j.Logger;

public class SQLQuery {
	public static final int MAX_QUERIES = 10000;
	public static final int COM_EMPRESAS_SIIE_ALL = 201;
	public static final int COM_SUCURSALES_EMPRESA = 202;
	public static final int COM_SUCURSAL = 203;
	public static final int COM_SUCURSALES_REGION = 204;
	public static final int CON_CUENTAS_CONTABLES_PK = 401;
	public static final int CON_CUENTAS_CONTABLES_ALL = 402;
	public static final int CON_TIPOS_CUENTAS_ALL = 403;
	public static final int CON_ATRIBUTOS_FISCALES_ALL = 404;
	public static final int CON_ATRIBUTOS_FISCALES_CUENTA = 405;
	public static final int CON_POLIZAS_HOME = 406;
	public static final int CON_POLIZAS_MAX_ID = 407;
	public static final int CON_MOV_CONTABLE_MAX_ID = 408;
	public static final int CON_POLIZA_POR_ID = 409;
	public static final int CON_MOV_POR_POLIZA = 410;
	public static final int CON_PERIODO_CONTABLE = 411;
	public static final int CON_SALDOS_EMPRESA_CIERRE = 412;
	public static final int CON_MOVIMIENTOS_EMPRESA_MES = 413;
	public static final int CON_MOVIMIENTOS_EMPRESA_MES13 = 414;
	public static final int CON_DECLARACIONES = 415;
	public static final int CON_DECLARACIONES_MAX_ID = 416;
	public static final int CON_DECLARACIONES_IVA = 417;
	public static final int CON_CUENTA_SALDO = 418;
	public static final int CON_REPORTES_MAX_ID = 419;
	public static final int CON_REPORTES_ALL = 420;
	public static final int CON_BLOQUES_MAX_ID = 421;
	public static final int CON_RUBROS_MAX_ID = 422;
	public static final int CON_REPORTE = 423;
	public static final int CON_BLOQUES_PADRE = 424;
	public static final int CON_BLOQUES_HIJO = 425;
	public static final int CON_RUBROS = 426;
	public static final int CON_CUENTA_RUBROS = 427;
	public static final int CON_ULTIMO_PERIODO_CONTABLE = 428;
	public static final int CON_CUENTAS_POR_ATRIBUTO = 429;
	public static final int CON_POLIZAS_FOLIO = 430;
	public static final int CON_GUIAS_CONTABLES_ACTIVE = 431;
	public static final int CON_CUENTAS_CONTABLES_GUIA = 432;
	public static final int CON_CENTROS_COSTOS_GUIA = 433;
	public static final int CON_GUIAS_CONTABLES_CUENTA = 434;
	public static final int CON_GUIA_CONTABLE_MAX = 435;
	public static final int CON_GUIA_CUENTA_GUIA = 436;
	public static final int CON_GUIA_COSTOS_GUIA = 437;
	public static final int CON_GUIA_CUENTA_CRITERIO = 438;
	public static final int CON_ACTIVO_FIJO_FILTROS = 439;
	public static final int CON_TIPO_ACTIVO_FIJO_ACTIVE = 440;
	public static final int CON_ACTIVO_FIJO_MAX = 441;
	public static final int CON_CENTROS_COSTO_EMPRESA = 442;
	public static final int CON_ACTIVO_FIJO_POR_ID = 443;
	public static final int CON_ULTIMA_DEPRECIACION = 444;
	public static final int CON_ACTIVO_DEPRECIACION_CONTABLE_POR_CCOS = 445;
	public static final int CON_ACTIVO_DEPRECIACION_FISCAL_POR_CCOS = 446;
	public static final int CON_BORRAR_CENTROS_POR_GUIA = 447;
	public static final int CON_BORRAR_CUENTAS_POR_GUIA = 448;
	public static final int CON_CUENTAS_CONTABLES_AFIS = 449;
	public static final int CON_TIPO_ACTIVO_FIJO_POR_ID = 450;
	public static final int CON_INPC_POR_PK = 451;
	public static final int CON_DEPRECIACION_POR_ID_FECHA = 452;
	public static final int CON_ACTIVO_FIJO_POR_CC = 453;
	public static final int CON_POLIZA_POR_CAMPO1 = 454;
	public static final int CON_DEPRECIACION_CRITERIA = 455;
	public static final int COB_CLIENTES_Y_CUENTAS_HOME = 1;
	public static final int COB_DATOS_DEL_CLIENTE = 2;
	public static final int COB_CONTRATOS_DEL_CLIENTE = 3;
	public static final int COB_DATOS_DEL_CONTRATO = 4;
	public static final int COB_COBROS_DEL_CONTRATO = 5;
	public static final int COB_CONTRATOS_DEL_CLIENTE_RFC = 6;
	public static final int COB_DETALLES_DEL_PAGO = 7;
	public static final int COB_DETALLES_DEL_PAGO_STANDAR = 8;
	public static final int COB_DETALLES_DE_UN_SOCIO = 9;
	public static final int COB_DETALLES_DE_PAGOS_POR_CONTRATO_STANDAR = 10;
	public static final int COB_VALOR_DEL_TIPO_DE_COBRO = 11;
	public static final int COB_ARTICULOS_DEL_CONTRATO = 12;
	public static final int COB_DATOS_CLIENTE_STANDAR = 13;
	public static final int COB_ESTADO_CUANTA_POR_CLIENTE = 14;
	public static final int COB_DATOS_CONTRATO_STANDAR = 15;
	public static final int GASTOS_VALIDACION_SOLICITUD_PAPELERIA = 21;
	public static final int GASTOS_OBTENER_CUENTA_CONTABLE_ASOCIADA_BANCO = 22;
	public static final int GASTOS_VERIFICAR_IMPORTACION_PAPELERIA = 23;
	public static final int GASTOS_OBTENER_SOLISITUD_DE_PAPELERIA = 24;
	public static final int GASTOS_OBTENER_POLIZA_DE_PAPELERIA = 25;
	public static final int VEN_DATOS_NOTA_SALIDA = 31;
	public static final int VEN_ARTICULOS_NOTA_SALIDA = 32;
	public static final int VEN_ARTICULOS_NOTA_SALIDA_MODIFICABLES = 33;
	public static final int SEG_FUNCION_TRANSACCION_ALL = 35;
	public static final int SEG_FUNCION_TRANSACCION_POR_ROL = 36;
	public static final int FIN_RECIENTE_TIIE = 41;
	public static final int CONSULTA_BANCO = 42;
	public static final int CONSULTA_PRODUCTOS = 43;
	public static final int CONSULTA_HISTORICO_PRECIOS = 44;
	public static final int CONSULTA_SABANA_MONTO_TOTAL_Y_PROMEDIO_PAGOS = 45;
	public static final int COB_CONFRONTA_POR_DEPENDENCIA = 61;
	public static final int COB_CONFRONTA_PK = 62;
	public static final int COB_PAGOS_POR_DEPENDENCIA_ANIO_QUINCENA = 63;
	public static final int COB_PAGOS_ESPERADOS = 64;
	public static final int COB_PAGO_ESPERADO = 65;
	public static final int COB_CLIENTES_PAGOS_ESPERADOS = 66;
	public static final int COB_PAGOS_POR_CONFRONTA = 67;
	public static final int COB_PAGOS_ESPERADOS_CLIENTE = 68;
	public static final int COB_PAGOS_INJUSTIFICADOS_MAX_ID = 69;
	public static final int COB_PAGOS_MAX_ID = 70;
	public static final int COB_PAGOS_POR_CONTRATO = 71;
	public static final int COB_PAGOS_INJUSTIFICADOS_POR_CONFRONTA = 72;
	public static final int COB_CLIENTES_CONTATOS_CERRADOS = 73;
	public static final int COB_PAGOS_POR_CONTRATO_CERRADO = 74;
	public static final int COB_CLIENTE_CONTRATO_PAGOS_ESPERADOS = 75;
	public static final int COB_CONTRATO_CERRADO_POR_SUCURSAL = 76;
	public static final int COB_PAGOS_CONTRATOS_REFINANCIADOS = 77;
	public static final int COB_ACTUALIZA_CONTRATOS_A_VENCIDOS = 78;
	public static final int COB_ACTUALIZA_CONTRATOS_A_VIGENTES = 79;
	public static final int COB_ANTIGUEDAD_ALL = 80;
	public static final int COB_RANGOS_COBRANZA_ALL = 81;
	public static final int COB_LISTA_MOROSOS = 82;
	public static final int COB_LISTA_COBROS_DE_MAS = 83;
	public static final int COB_PAGOS_POR_DEPENDENCIA = 84;
	public static final int COB_CONFRONTA_POR_RANGOS = 85;
	public static final int COB_ARTICULOS_POR_CONTRATO = 86;
	public static final int COB_SUMA_PAGOS_ESPERADOS = 87;
	public static final int COB_PAGOS_POR_CLIENTE = 88;
	public static final int COB_ACTUALIZA_PAGOS_NO_RECIBIDOS = 89;
	public static final int COB_NUMERO_RECIBO_MAX = 90;
	public static final int CON_CENTROS_ACTIVIDAD_PK = 491;
	public static final int CON_CENTROS_ACTIVIDAD_PADRES = 492;
	public static final int CON_CENTROS_ACTIVIDAD_ACTIVOS = 493;
	public static final int CON_CENTROS_ACTIVIDAD_MAX_ID = 494;
	public static final int CON_CUENTA_BORRADA = 495;
	public static final int CON_CUENTAS_HIJAS = 496;
	public static final int CON_CENTROS_ACTIVIDAD_HIJOS = 497;
	public static final int CON_DECLARACIONES_ISRM = 498;
	public static final int CON_DECLARACIONES_ACTIVOM = 499;
	public static final int CON_DECLARACIONES_FIJO = 500;
	public static final int CON_CENTROS_ACTIVIDAD_ALL = 501;
	public static final int CON_MOVIMIENTOS_PERIODO_ATRIBUTO = 502;
	public static final int CON_SALDO_FINAL_ATRIBUTO = 503;
	public static final int CON_INPC_ANIO_MES = 504;
	public static final int CON_ULTIMO_INPC = 505;
	public static final int CON_TASA_ANIO_MES = 506;
	public static final int CON_TASA_RANGO = 507;
	public static final int CON_DECLARACIONES_ISRA = 508;
	public static final int CON_DECLARACIONES_ACTIVOA = 509;
	public static final int CON_SALDOS_MENSUALES_ATRIBUTO = 510;
	public static final int CON_DEPRECIACIONES_ANIO = 511;
	public static final int CON_INPC_ALL = 512;
	public static final int GEN_DOMINIO_ELEMENTOS = 1201;
	public static final int GEN_PARAMETRO_OPERACION = 1202;
	public static final int GEN_LAYOUTS_ALL = 1203;
	public static final int GEN_CAMPOS_BY_LAYOUT = 1204;
	public static final int GEN_DOMINIO_ELEMENTO = 1205;
	public static final int VEN_DEPENDENCIAS_SIIE_ALL = 2001;
	public static final int VEN_REGIONES_SIIE_ALL = 2002;
	public static final int VEN_DEPENDENCIAS_SUCURSAL = 2003;
	public static final int VEN_SOCIOS_POR_SUCURSAL = 2004;
	public static final int VEN_CAMIONETAS_POR_SOCIO = 2005;
	public static final int VEN_TRASPASOS_PROMOTORES = 2006;
	public static final int VEN_DEPENDENCIAS_PK = 2007;
	public static final int VEN_DEPENDENCIAS_POR_REGION = 2008;
	public static final int VEN_REGIONES_PK = 2009;
	public static final int VEN_CAMIONETA_EMPRESA = 205;
	public static final int VEN_CAMIONETA_SUCURSAL = 206;
	public static final int VEN_CAMIONETA_SOCIO = 207;
	public static final int VEN_CAMIONETA_HOME = 208;
	public static final int SEG_LOGIN = 100;
	public static final int SEG_USER_SESSION = 101;
	public static final int SEG_EMPRESAS = 102;
	public static final int SEG_PERMISO = 103;
	public static final int SEG_MENU = 104;
	public static final int SEG_TRANSACCIONES = 105;
	public static final int SEG_MENUVO = 106;
	public static final int SEG_FUNCION_LIGA = 107;
	public static final int SEG_ROLES = 108;
	public static final int SEG_MENU_ALL = 109;
	public static final int SEG_TRAN_ALL = 110;
	public static final int SEG_FUNCION_ALL = 111;
	public static final int SEG_EMPRESAS_USER = 112;
	public static final int SEG_SUCURSALES_USER = 113;
	public static final int SEG_EMPRESAS_USER_RECLUTADORAS = 114;
	public static final int SEG_EMPRESAS_USER_PROVEEDORAS_INT = 115;
	public static final int CON_GENERA_POLIZA = 210;
	public static final int COM_GENERA_REQUISICIONES = 211;
	public static final int COM_GENERA_PARTIDAS = 212;
	public static final int VEN_GENERA_TRASPASO = 213;
	public static final int VEN_GENERA_TRASPASO_PROMOTORES = 214;
	public static final int VEN_GENERA_TRASPASO_MAX = 215;
	public static final int VEN_GENERA_ARTICULOS = 216;
	public static final int CON_GENERA_AXUS_SALFIN = 251;
	public static final int CON_GENERA_AXUS_MOV = 252;
	public static final int CON_GENERA_ENCABEZADO = 253;
	public static final int VEN_RESUMEN_EXIST_VTAS = 254;
	public static final int VEN_RESUMEN_EXIST_VTAS_2 = 255;
	public static final int VEN_RESUMEN_EXIST_VTAS_3 = 256;
	public static final int VEN_RESUMEN_EXIST_VTAS_4 = 257;
	public static final int VEN_RESUMEN_EXIST_VTAS_5 = 258;
	public static final int VEN_RESUMEN_EXIST_VTAS_6 = 259;
	public static final int VEN_RESUMEN_EXIST_VTAS_7 = 260;
	public static final int VEN_RESUMEN_EXIST_VTAS_8 = 261;
	public static final int VEN_RESUMEN_EXIST_VTAS_9 = 262;
	public static final int VEN_RESUMEN_EXIST_VTAS_10 = 263;
	public static final int VEN_RESUMEN_EXIST_VTAS_TOTAL = 264;
	public static final int VEN_GENERA_DEP_MONT_TABLA = 265;
	public static final int INV_PROVEEDORES_POR_EMPRESA = 266;
	public static final int INV_GENERA_TABLA_DEV = 267;
	public static final int INV_GENERA_CENTRO_SERVICIO = 268;
	public static final int INV_GENERA_TABLA_AGREGAR_CONFIRMAR = 269;
	public static final int INV_GENERA_DEVOLUCION = 270;
	public static final int INV_GENERA_ARTICULOS_FAC = 271;
	public static final int INV_TABLA_AGREGAR_CONFIRMAR = 272;
	public static final int INV_GENERA_SIG_ID = 273;
	public static final int INV_GENERA_DEV = 274;
	public static final int INV_DAT_ARTICULO = 275;
	public static final int INV_SIGUIENTE_SOL_NC = 276;
	public static final int INV_PROVEEDOR_INTERNO = 277;
	public static final int INV_GEN_DOM_ELEM_DINAMICOS = 278;
	public static final int INV_DAT_ARTICULO_HIST = 279;
	public static final int INV_GENERA_CEN_SERVICIO = 280;
	public static final int INV_REGRESA_VALOR = 281;
	public static final int INV_ART_ES_VOLUMINIOSO = 282;
	public static final int INV_CHECA_SNC = 283;
	public static final int VEN_GENERA_TABLA_TRAPASOS_SUC = 284;
	public static final int VEN_GENERA_ARTICULO = 285;
	public static final int VEN_SIG_SOL_TRASPASO_SUC_ID = 286;
	public static final int VEN_SIG_PART_TRASPASO_SUC_ID = 287;
	public static final int VEN_REGRESA_PROD_CVE = 288;
	public static final int VEN_GENERA_TRASPASO_VO = 289;
	public static final int VEN_REGRESA_PROD_CODIGO = 290;
	public static final int VEN_REGRESA_PARTIDAS_TRASP = 291;
	public static final int VEN_REGRESA_PARTIDA_TRASP_VO = 292;
	public static final int VEN_REGRESA_PARTIDA_TRASP_VO_BY_PK = 293;
	public static final int VEN_ART_PART_TRASP_SUC = 294;
	public static final int INV_GENERA_ART_HIST_EN_BD = 295;
	public static final int VEN_REGRESA_ARTICULO_DEL_HISTORICO = 296;
	public static final int VEN_GENERA_SIG_POLIZA_ID = 297;
	public static final int VEN_GENERA_CENTRO_CERVICIO = 298;
	public static final int VEN_TABLA_CONSULTAR_TRASP_PROM = 299;
	public static final int VEN_GENERA_CONSULTA_DATOS = 300;
	public static final int GENERA_BALANZA_COMPROBACION = 301;
	public static final int INV_ARTICULOS_CAMBIO_FISICO = 302;
	public static final int INV_CENTRO_SERVICIO = 303;
	public static final int INV_ARTICULO_POR_FOLIO = 304;
	public static final int INV_SECUENCIA_FOLIO_CAMBIO_FISICO = 305;
	public static final int INV_ARTICULO_ID_POR_FOLIO = 306;
	public static final int INV_ARTICULO_DISPONIBLE_O_EN_TRANSITO = 307;
	public static final int INV_DOMINIOS_POR_CATALOGO = 308;
	public static final int INV_ARTICULOS_BAJA = 309;
	public static final int INV_ARTICULOS_POR_BAJ_FOLIO = 310;
	public static final int INV_ARTICULO_POR_NUMERO_SERIE = 311;
	public static final int INV_ARTICULO_SECUENCIA_FOLIO_BAJA = 312;
	public static final int INV_ARTICULO_HISTORICO_ULTIMO = 313;
	public static final int INV_ARTICULO_PFCTE_PRECIO_UNITARIO = 314;
	public static final int EMP_SOC_PERCEPCION = 315;
	public static final int PLA_DES_PLAZO_QUINCENA_A_PAGAR = 316;
	public static final int PRES_CENTROS_ACTIVIDAD_POR_EMPRESA = 317;
	public static final int PRES_PRESUPUESO_CENTRO_COSTO = 318;
	public static final int PRES_CC_HIJOS = 319;
	public static final int FIN_REGION_EMPRESA_SUCURSAL = 320;
	public static final int FIN_VENTAS_POR_PERIODO = 321;
	public static final int FIN_NUMERO_CAMIONETAS_SUCURSAL = 322;
	public static final int FIN_VENTAS_CAMIONETAS_SUCURSAL = 323;
	public static final int FIN_CLIENTES_POR_DEPENDENCIA = 324;
	public static final int GASTOS_CENTRO_DE_COSTOS_HIJOS = 325;
	public static final int GASTOS_PRESUPUESTO_EJERCIDO = 326;
	public static final int FIN_CAMIONETAS_SUCURSAL_POR_FECHA = 327;
	public static final int FIN_NUM_CLIENTES_SUCURSAL = 328;
	public static final int CON_GENERA_MAYOR = 351;
	public static final int CGAS_MUESTRA_TIPO_1_CAL = 1500;
	public static final int CGAS_FECHAS_CAL = 1501;
	public static final int CGAS_DETALLE_CAL = 1502;
	public static final int CGAS_ALL_DETALLE_CAL = 1503;
	public static final int FIN_GET_CUENTAS_BY_AFIS = 1504;
	public static final int COM_FAMILIAS = 1505;
	public static final int COM_PRODUCTOS = 1506;
	public static final int FIN_GET_NIVEL_CONSULTA = 1507;
	public static final int COM_LINEA = 1508;
	public static final int REPORTE_HC_POR_LINEA = 1509;
	public static final int GET_LINEA_POR_PRODUCTO = 1510;
	public static final int GET_NUM_LINEAS = 1511;
	public static final int REPORTE_HC_POR_FAMILIA = 1512;
	public static final int GET_FAMILIA_POR_PRODUCTO = 1513;
	public static final int GET_NUM_FAMILIAS = 1514;
	public static final int REPORTE_HC_POR_PROVEEDOR = 1515;
	public static final int GET_PROVEEDOR_POR_PK = 1516;
	public static final int GET_NUM_PROVEEDORES_POR_EMPRESA = 1517;
	public static final int FIN_GET_REGIONES_EMPRESA = 1518;
	public static final int REPORTE_HC_GET_REGIONES = 1519;
	public static final int GET_TOTAL_SOC_CCOS = 1520;
	public static final int CGAS_SOLICITUD_GASTOS_CONSULTA_REP = 1521;
	public static final int REPORTE_HV_POR_LINEA_ETESA = 1522;
	public static final int REPORTE_HV_POR_FAMILIA_ETESA = 1523;
	public static final int FIN_EMPRESA_PROVEEDORA = 1524;
	public static final int REPORTE_HV_POR_LINEA = 1525;
	public static final int REPORTE_HV_POR_FAMILIA = 1526;
	public static final int INV_GENERA_SIG_ID_PARTIDA = 1527;
	public static final int GET_PRECIO_UNITARIO_PARTIDA_FAC_CTE = 1528;
	public static final int GET_PRECIO_UNITARIO_PARTIDA_FAC_PROV = 1529;
	public static final int GET_FAC_CTE = 1530;
	public static final int GET_FAC_PROVEEDOR_BY_ART_SUC = 1531;
	public static final int INV_CHECA_PSNCS = 1532;
	public static final int GET_ORDENES_PAGOS_DATE_PROV = 1533;
	public static final int GET_VENTAS_FACTURA = 1534;
	public static final int GET_VENTAS_CONTRATOS = 1535;
	public static final int GET_DERECHOS_EMPRESA_ETESA = 1536;
	public static final int GET_OBLIGACIONES_EMPRESA = 1537;
	public static final int GET_OBLIGACIONES_EMPRESA_II = 1538;
	public static final int GET_DERECHOS_EMPRESA = 1539;
	public static final int COUNT_POLIZAS_STATUS_PEND_AUTORIZAR = 1540;
	public static final int REPORTE_HV_POR_PRODUCTO_ETESA = 1541;
	public static final int REPORTE_HV_POR_PRODUCTO = 1542;
	public static final int COB_PAGOS_INJUSTIFICADOS_LISTA = 1543;
	public static final int CON_CENTROS_ACTIVIDAD_PK_II = 1544;
	public static final int INV_BUSCA_PROMOTORES = 352;
	public static final int INV_BUSCA_SUCURSALES = 353;
	public static final int INV_BUSCA_ARTICULOS = 354;
	public static final int INV_BUSCA_DOMINIO = 355;
	public static final int INV_BUSCA_FOLIO_TELI = 356;
	public static final int INV_BUSCA_FOLIO_LOTE = 358;
	public static final int INV_BUSCA_CLAVE_PRODUCTO = 359;
	public static final int FAC_BUSCA_ID_PARTIDA_FACTURA = 360;
	public static final int INV_MAXIMO_ARTICULOS_PARTIDA = 361;
	public static final int INV_SIGUIENTE_NUMERO_SERIE = 362;
	public static final int INV_COSTO_PROMEDIO = 363;
	public static final int INV_COSTO_ULTIMA_FECHA = 364;
	public static final int INV_ETIQUETAS_FACTURA_PROVEEDOR = 365;
	public static final int INV_ETIQUETAS_CLAVE_LOTE = 366;
	public static final int INV_ETIQUETAS_NUM_SERIE_CLAVE_PROD = 367;
	public static final int INV_PROMOTORES_SUCURSAL = 368;
	public static final int INV_CAMIONETAS_PROMOTOR_SUCURSAL = 369;
	public static final int INV_ARTICULOS_TRANSITO_PROMOTOR = 370;
	public static final int CON_COSTO_PROMEDIO_CONTRATOS = 371;
	public static final int CON_MONTO_TOTAL_CONTRATOS = 372;
	public static final int CON_SUCURSALES_SIN_CCOSTOS = 373;
	public static final int CON_ULTIMA_FECHA_CIERRE = 374;
	public static final int INV_VALIDA_FACTURA_SUCURSAL = 375;
	public static final int CGAS_CENTROS_COSTOS_CAPTURA = 376;
	public static final int CGAS_SOCIOS_COSTOS_REGISTRO = 377;
	public static final int CGAS_SOLICITUD_GASTOS_CONSULTA = 378;
	public static final int CGAS_RUBROS = 379;
	public static final int TES_BANCOS = 380;
	public static final int CGAS_CONCEPTOS_VIATICOS = 381;
	public static final int CGAS_CONCEPTOS_SOLICITUD = 382;
	public static final int CGAS_SOLICITUD = 383;
	public static final int CGAS_VIAJES_SOLICITUD = 384;
	public static final int CGAS_SOLICITUDES_AUTORIZAR = 385;
	public static final int CGAS_DETALLES_UNIDADES_SOLICITUD = 386;
	public static final int CGAS_CONCEPTOS_SOLICITUD_AUTORIZA = 387;
	public static final int CGAS_SOLICITUD_MONTO_TOTAL = 388;
	public static final int CGAS_MONTOS_RUBROS = 389;
	public static final int CGAS_VALES_GASOLINA_SUCURSAL = 390;
	private static String[] queries = new String[10000];
	private static String fileQueries;
	private static String fileQueriesFabrica;
	private static String fileQueriesSIICP;
	private static String fileQueriesSAEO = "queriesSAEO.properties";
	
	protected static final Logger logger = Logger.getLogger(SQLQuery.class);

	static {
		queries = new String[10000];

		cargaQueries(fileQueriesSAEO);
	}

	public static void cargaQueries(String file) {
		StringBuffer query = null;

		boolean readingQuery = false;
		int actualQuery = -1;
		try {
			InputStream input = SQLQuery.class.getResourceAsStream(file);

			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			String linea;
			while ((linea = reader.readLine()) != null) {
				linea = linea.trim();

				if ((linea.indexOf("--") == 0) && (((linea.indexOf("<%") != -1) || (linea.indexOf("%>") != -1)))) {
					linea = linea.substring(2);
				}

				if ((linea.length() <= 0) || (linea.indexOf("--") == 0)) {
					continue;
				}
				if (!(readingQuery)) {
					actualQuery = Integer.parseInt(linea);
					readingQuery = true;
					query = new StringBuffer();
				} else {
					int posFinQuery = linea.indexOf(59);

					if (posFinQuery >= 0) {
						query.append(linea.substring(0, posFinQuery).trim());
						if ((actualQuery >= 0) && (actualQuery < 10000)) {
							queries[actualQuery] = query.toString();
							if(actualQuery==100)
							{
								System.out.println("Query 100: "+queries[100]);
								logger.info("Query 100: "+queries[100]);
							}
						}

						readingQuery = false;
					} else {
						query.append(linea + " ");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public static String getQuery(int idQuery) {
		logger.info("SQLQueryClass ejecuto consulta con id: "+idQuery);
		if ((idQuery >= 0) && (idQuery < 10000)) {
			return queries[idQuery];
		}
		return null;
	}

	public static String getFileQueries() {
		return fileQueries;
	}
}