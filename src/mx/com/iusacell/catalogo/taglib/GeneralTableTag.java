/*
 * Created on 15/12/2004
 *
 */
package mx.com.iusacell.catalogo.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import mx.com.iusacell.catalogo.utilerias.Constantes;
import mx.com.iusacell.catalogo.utilerias.Conversion;
import mx.com.iusacell.catalogo.utilerias.Fecha;
import mx.com.iusacell.catalogo.utilerias.LeeParametros;
import mx.com.iusacell.catalogo.utilerias.PaginaDeElementos;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;


import org.apache.commons.beanutils.MethodUtils;

/**
 * @author Juan Francisco Cardona A
 *
 * Presenta una tabla con la informacion de una consulta de la
 * base de datos
 */
public class GeneralTableTag extends TagSupport {
	
	/** Tamaño del borde para la tabla **/
	protected int border = 0;
	
	/** Ancho de la tabla **/
	protected String width;
	
	/** Cellpadding entre las celdas **/
	protected int cellpadding = 0;
	
	/** Cellspacing entre las celdas **/
	protected int cellspacing = 0;
	
	/** Color para los encabezados de la tabla **/
	protected String cssColorEncabezado;
	
	/** Titulos para la tabla separados por coma
	 *  El numero de titulos determina el numero de columnas que debe tener 
	 *  la tabla.
	 *  Ej. "Cuenta Contable, Fecha Alta, Estatus"
	 */
	protected String titulos;
	
	/** Metodos del VO para cada columna de la tabla separados por coma
	 *  Ej. "getId, getCuenta, getFechaAlta, getEstatus"
	 */
	protected String metodos;
	
	/** Cadena con los anchos de columnas separados por coma (',') **/
	protected String anchosColumnas;
	
	/** Cadena con las alineaciones de columnas separados por coma (',') 
	 * 	Ej: "center,left,right,center,center"
	 */
	protected String alineacionesColumnas;
	
	/** Altuar de cada renglon de la tabla **/
	protected int altoRenglon;
	
	/** Color 1 para mostrar en los renglones de la tabla*/
	protected String cssColorRenglon1;
	
	/** Color 2 para mostrar en los renglones de la tabla*/
	protected String cssColorRenglon2; 
	
	/**
	 * Numero de query a ejecutar
	 */
	protected int queryId;
	/**
	 * Clase VO que sera regresada por la consulta a la base de datos
	 */
	protected Class VOClass;
	/**
	 * Parametros necesarios para el query a ejecutar
	 */
	protected Object parametrosQuery[] = new Object[]{};
	/**
	 * Cadena de metodos separados por coma de los campos que se quieren agregar
	 * como hidden. Para cada metodo debe indicarse el nombre a
	 * utilizar para el campo oculto separado por '=' del metodo. 
	 * Para cada elemento genera el nombre como el nombre indicado seguido del valor
	 * que debe ponerse en el combo 
	 * Si el campo agregarValorCamposExtras es true, se agrega al nombre del hidden
	 * el valor regresado por el metodo (del VO) indicado en el campo valueMethodName
	 * Ej Tenemos un VO con id=1, cuenta=0001-0001-0000-0000
	 * 	  Tenemos un VO con id=2, cuenta=0002-0002-0000-0000
	 * 	  Si indicamos los siguientes campos
	 * 	  	camposExtras='getId=id,getCuenta=cuentaPadre'
	 * 		agregarValorCamposExtras="false"
	 * 	  	Se genera 
	 * 		<input type="hidden" name="id" value="1">
	 * 		<input type="hidden" name="id" value="2">
	 * 		<input type="hidden" name="cuentaPadre" value="0001-0001-0000-0000">
	 * 		<input type="hidden" name="cuentaPadre" value="0001-0001-0000-0000">
	 * 	  Si indicamos los siguientes campos
	 * 	  	camposExtras="getId=id,getCuenta=cuentaPadre"
	 * 		agregarValorCamposExtras="true"
	 * 		valueMethodName="getId"
	 * 	  	Se genera 
	 * 		<input type="hidden" name="id1" value="1">
	 * 		<input type="hidden" name="id2" value="2">
	 * 		<input type="hidden" name="cuentaPadre1" value="0001-0001-0000-0000">
	 * 		<input type="hidden" name="cuentaPadre2" value="0001-0001-0000-0000">
	 * 
	 */
	protected String camposExtras;
	/**
	 * Nombre del metodo a ejecutar del VO para desplegar el value para camposExtras
	 */
	protected String valueMethodName;
	/**
	 * True si se desea agregar a los nombres de los hidden (si se esta utilizando el campo
	 * camposExtras) el valor regresado por el campo valueMethodName
	 */
	protected boolean agregarValorCamposExtras = true;	
	/**
	 * False para no realizar la consulta (default true) 
	 */
	protected boolean consultar = true;

	/**
	 * Arreglo de tuplas con los valores del nombre de parametro a
	 * sobreescribir para evitar que la paginacion ejecute varias veces
	 * el mismo codigo
	 */
	protected String[] parametrosPaginacion;
	
	/**
	 * Variable interna para almacenar la consulta
	 */
	protected List data;
	/** Variables para almacenar los campos por columnas en arreglos **/
	protected ArrayList titulosArray = new ArrayList();
	protected ArrayList anchosColumnasArray = new ArrayList();
	protected ArrayList metodosArray = new ArrayList();
	protected ArrayList alineacionesColumnasArray = new ArrayList();
	protected int numColumnas;
	
	/**
	 * Valor del tamaño de pagina - cantidad de registros a mostrar. Se traduce
	 * en el "limit" del query. 
	 * Posibles valores del parametro y su significado:
	 *   -1                No se usa paginación; 
	 *   [sin valor]       Se usa el tamaño de pagina default Constantes.TAMAÑO_PAGINA_DEFAULT; 
	 *   [entero positivo] Se usa el tamaño de pagina provisto como parametro en el taglib; 
	 */
	protected int pageSize = Constantes.TAMAÑO_PAGINA_DEFAULT;

	protected PaginaDeElementos pagina = null;	
	
	/** Permite establecer el cambio de fecha para la tabla bitacora de proceso **/
	protected boolean fechaBip;
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
		try{
			ajustaParametros();
			if(consultar){
				setPaginacionInfo();
				realizaConsulta();
				generaCamposExtras();
			}
			generaHeaderTable();
			generaTitulosTable();
			generaRenglonesTable();
			generaEndTable();
			if(consultar){
				//generaLigasPaginacion(parametrosPaginacion);
			}
		}catch (IOException ioE) {
			throw new CatalogoSystemException("Error de tipo IO al crear tabla general ", ioE);
		}catch (SQLException sqlE) {
			throw new CatalogoSystemException("Error de tipo SQL al crear tabla general ", sqlE);
		}
		return super.doStartTag();
	}
	
	/**
	 * Primer metodo en invocarse para verificar que los parametros contienen informacion
	 * correcta.
	 * Este metodo puede sobreescribirse para validar mas parametros.
	 */
	public void ajustaParametros(){
		StringTokenizer tokens;
		int contador;
		//obtener los titulos y numero de columnas
		tokens = new StringTokenizer(titulos, ",");
		numColumnas = 0;
		//eliminar los valores antiguos que pueda contener
		titulosArray.clear();
		while(tokens.hasMoreTokens()){
			titulosArray.add(tokens.nextToken().trim());
			numColumnas++;
		}
		
		//obtener los nombres de los metodos
		tokens = new StringTokenizer(metodos, ",");
		contador = 0;
		//eliminar los valores antiguos que pueda contener
		metodosArray.clear();
		while(tokens.hasMoreTokens()){
			metodosArray.add(tokens.nextToken().trim());
			contador++;
		}
		if(contador < numColumnas){
			throw new CatalogoSystemException("Faltan metodos para generar la tabla general", null);
		}
		
		//obtener las alineaciones para las columnas
		tokens = new StringTokenizer(alineacionesColumnas, ",");
		contador = 0;
		//eliminar los valores antiguos que pueda contener
		alineacionesColumnasArray.clear();
		while(tokens.hasMoreTokens()){
			alineacionesColumnasArray.add(tokens.nextToken().trim());
			contador++;
		}
		if(contador < numColumnas){
			throw new CatalogoSystemException("Faltan alineaciones para generar la tabla general", null);
		}
		
		//obtener los anchos para las columnas
		tokens = new StringTokenizer(anchosColumnas, ",");
		contador = 0;
		//eliminar los valores antiguos que pueda contener
		anchosColumnasArray.clear();
		while(tokens.hasMoreTokens()){
			anchosColumnasArray.add(tokens.nextToken().trim());
			contador++;
		}
		if(contador < numColumnas){
			throw new CatalogoSystemException("Faltan anchos para generar la tabla general", null);
		}
	}

	/**
	 * Obtiene e inicializa los valores para paginacion (paginas totales, pagina actual, etc)
	 *
	 */
	public int calculaPageNum() {
		int num = 1;
		try {
			num = Integer.parseInt(super.pageContext.getRequest().getParameter("pageNum"));
		} catch (NumberFormatException e) {
		}
		return num;
	}

	protected PaginaDeElementos getPaginaElementos() {
		return new PaginaDeElementos(queryId, parametrosQuery, VOClass);	
	}
	
	protected void setPaginacionInfo() {
		pagina = getPaginaElementos();
		pagina.setPageNum(calculaPageNum());
		pagina.setPageSize(pageSize);
	}
	/**
	 * @return Returns the fechaBip.
	 */
	public boolean isFechaBip() {
		return fechaBip;
	}
	/**
	 * @param fechaBip The fechaBip to set.
	 */
	public void setFechaBip(boolean fechaBip) {
		this.fechaBip = fechaBip;
	}
	/**
	 * Metodo que realiza la consulta a la base de datos
	 */
	protected void realizaConsulta() throws SQLException {
		data = pagina.getElements();
		
	}

	/**
	 * Metodo que manda a generar las hiperligas necesarias para la paginacion. 
	 */
	protected void generaLigasPaginacion(String [] parametrosPaginacion) throws JspException, IOException {
		JspWriter writer = super.pageContext.getOut();
		HttpServletRequest req = (HttpServletRequest)super.pageContext.getRequest();
		writer.print(Html.cadenaLigasPaginacion(pagina, req, parametrosPaginacion));
	}
	
	/**
	 * Metodo que genera el html para agregar los campos extras que se deseen poner 
	 * como campos hidden
	 */
	public void generaCamposExtras() throws JspException, IOException{
		if(camposExtras != null){
			//obtiene los metodos
			LeeParametros lp = new LeeParametros(camposExtras, ',', '=');
			ArrayList metodos = lp.getKeys();
			ArrayList nombres = lp.getValues();
			
			//recorrer la informacion y desplegar valores
			Object valor = null;
			Object vo = null;
			String metodo;
			String nombre;
			//recorrer cada metodo
			try{
				for(int j = 0; j < metodos.size(); j++){
					metodo = (String)metodos.get(j);
					nombre = (String)nombres.get(j);
					//para cada objeto consultado desplegar resultado
					for(int i = 0; i < data.size(); i++) {
						vo = data.get(i);
						valor = MethodUtils.invokeExactMethod(vo, metodo, null);
						generaHidden(nombre + (getAgregarValorCamposExtras() ? MethodUtils.invokeExactMethod(vo, valueMethodName, null) : ""), valor);
					}		
				}
			}catch (NoSuchMethodException nsme) {
				throw new CatalogoSystemException("Error al generar el tabla general ", nsme);
			}catch (InvocationTargetException ite) {
				throw new CatalogoSystemException("Error al generar el tabla general ", ite);
			}catch (IllegalAccessException iae) {
				throw new CatalogoSystemException("Error al generar el tabla general ", iae);
			}
		}
	}
	
	/**
	 * Metodo que genera el html para poner un valor en un hidden. Este metodo
	 * es el invocado por generaCamposExtras y puede ser sobreescrito para proveer
	 * una funcionalidad diferente
	 */
	public void generaHidden(String nombre, Object valor) throws JspException, IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaHidden(nombre, String.valueOf(valor)));
	}

	
	/**
	 * Metodo que presenta en pantalla el encabezado de la tabla
	 * Este metodo puede sobreescribirse para modificar la forma de desplegar 
	 * el encabezado de la tabla
	 *  
	 * @throws IOException
	 */
	protected void generaHeaderTable() throws IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaHeaderTable(border, width, cellpadding, cellspacing));
	}
	
	/**
	 * Metodo que presenta en pantalla los titulos de la tabla
	 * Este metodo puede sobreescribirse para modificar la forma de desplegar 
	 * los titulos de la tabla
	 *
	 */
	protected void generaTitulosTable() throws IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaHeaderRowTable());
		doBeforeTitulos();
		for(int i = 0; i < numColumnas; i++){
			//writer.print(Html.cadenaCeldaTitulosTable((String)anchosColumnasArray.get(i), cssColorEncabezado, altoRenglon, (String)alineacionesColumnasArray.get(i), (String)titulosArray.get(i)));
			writer.print(Html.cadenaCeldaTable((String)anchosColumnasArray.get(i), cssColorEncabezado, altoRenglon, (String)alineacionesColumnasArray.get(i), (String)titulosArray.get(i)));
		}
		doAfterTitulos();
		writer.print(Html.cadenaEndRowTable());
	}
	
	/**
	 * Metodo invocado antes de poner las celdas para los titulos
	 * Este metodo puede sobreescribirse para agregar celdas antes de los titulos
	 *
	 */
	protected void doBeforeTitulos() throws IOException{}
	
	/**
	 * Metodo invocado despues de poner las celdas para los titulos
	 * Este metodo puede sobreescribirse para agregar celdas al final de los titulos
	 *
	 */
	protected void doAfterTitulos() throws IOException{}
	
	/**
	 * Metodo que pinta los renglones de la tabla
	 * Este metodo puede sobreescribirse para modificar la forma de desplegar 
	 * los renglones de la tabla
	 * 
	 */
	protected void generaRenglonesTable() throws IOException{
		if(!consultar)
			return;
			
		JspWriter writer = super.pageContext.getOut();
		for(int i = 0; i < data.size(); i++){
			generaRenglonTable(data.get(i), (i % 2 == 0 ? cssColorRenglon1 : cssColorRenglon2), i);			
		}
	}	
	
	/**
	 * Metodo que presenta en pantalla la información de un renglon de la tabla
	 * Este metodo puede sobreescribirse para modificar la forma de desplegar 
	 * un renglon
	 *  
	 * @param elemento Objecto VO a presentar su informacion
	 * @param color Css color para mostrar el renglo
	 * @param numElemento Numero de elemento actual empezando en 0
	 * @throws IOException
	 */
	protected void generaRenglonTable(Object elemento, String color, int numElemento) throws IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaHeaderRowTable());
		doBeforeCeldas(elemento, color, numElemento);
		try{
			for(int i = 0; i < numColumnas; i++){
				generaCeldaTable(elemento, color, numElemento, i);
			}
		} catch (NoSuchMethodException e) {
			throw new CatalogoSystemException("Error al generar la tabla general ", e);
		} catch (IllegalAccessException e) {
			throw new CatalogoSystemException("Error al generar la tabla general ", e);
		} catch (InvocationTargetException e) {
			throw new CatalogoSystemException("Error al generar la tabla general ", e);
		}
		doAfterCeldas(elemento, color, numElemento);
		writer.print(Html.cadenaEndRowTable());
	}	
	
	/**
	 * Metodo que presenta en pantalla la información de un celda de la tabla
	 * Este metodo puede sobreescribirse para modificar la forma de desplegar 
	 * un renglon
	 * 
	 * @param elemento Objecto VO a presentar su informacion
	 * @param color Css color para mostrar el renglo
	 * @param numRenglon Numero de elemento actual empezando en 0
	 * @param numColumna Numero de columna de la celda empezando en 0
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected void generaCeldaTable(Object elemento, String color, int numRenglon, int numColumna) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaCeldaTable((String)anchosColumnasArray.get(numColumna), color, altoRenglon, (String)alineacionesColumnasArray.get(numColumna), convierteString(MethodUtils.invokeExactMethod(elemento, (String)metodosArray.get(numColumna), null), (String)metodosArray.get(numColumna))));
	}
	
	/**
	 * Metodo invocado antes de poner las celdas de la tabla
	 * Este metodo puede sobreescribirse para agregar celdas antes de las demas celdas
	 *
	 * @param elemento Objecto VO a presentar su informacion
	 * @param color Css color para mostrar el renglo
	 * @param numElemento Numero de elemento actual empezando en 0
	 */
	protected void doBeforeCeldas(Object elemento, String color, int numElemento) throws IOException{}
	
	/**
	 * Metodo invocado despues de poner las celdas de la tabla
	 * Este metodo puede sobreescribirse para agregar celdas al final de las demas celdas
	 * 
	 * @param elemento Objecto VO a presentar su informacion
	 * @param color Css color para mostrar el renglo
	 * @param numElemento Numero de elemento actual empezando en 0
	 */
	protected void doAfterCeldas(Object elemento, String color, int numElemento) throws IOException{}
	
	/**
	 * Metodo que convierte un objeto en String con formatos definidos
	 * 
	 * @param valor Objeto a convertir a String
	 * @param metodoName Nombre del metodo ejecutado para obtener este valor
	 * @return Cadena representando al objeto
	 */
	protected String convierteString(Object valor, String metodoName){
		String resultado = "";
		if(valor instanceof Double){
			resultado = Conversion.formatea((Double)valor);
		}else if(valor instanceof java.util.Date){
			//parametro usado solo para la tabla bitacora de procesos
			//no requerido en las demas tablas
			if(fechaBip){
				DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, new Locale("es","MX"));
				resultado = df.format((java.util.Date)valor);
			}else{
				resultado = Fecha.dateToString((java.util.Date)valor);
			}
		}else{
			if(valor != null){ 
				resultado = String.valueOf(valor);
			}else{
				resultado = "&nbsp;";
			}
		}
		return resultado;
	}
	
	/**
	 * Metodo que presenta en pantalla el final de la tabla
	 * Este metodo puede sobreescribirse para modificar la forma de desplegar 
	 * el final de la tabla
	 *  
	 * @throws IOException
	 */
	protected void generaEndTable() throws IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaEndTable());
	}
	

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.IterationTag#doAfterBody()
	 */
	public int doAfterBody() throws JspException {
		return super.doAfterBody();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	/**
	 * @return
	 */
	public String getAlineacionesColumnas() {
		return alineacionesColumnas;
	}

	/**
	 * @return
	 */
	public int getAltoRenglon() {
		return altoRenglon;
	}

	/**
	 * @return
	 */
	public String getAnchosColumnas() {
		return anchosColumnas;
	}

	/**
	 * @return
	 */
	public int getBorder() {
		return border;
	}

	/**
	 * @return
	 */
	public String getCamposExtras() {
		return camposExtras;
	}

	/**
	 * @return
	 */
	public int getCellpadding() {
		return cellpadding;
	}

	/**
	 * @return
	 */
	public int getCellspacing() {
		return cellspacing;
	}

	/**
	 * @return
	 */
	public String getCssColorEncabezado() {
		return cssColorEncabezado;
	}

	/**
	 * @return
	 */
	public String getCssColorRenglon1() {
		return cssColorRenglon1;
	}

	/**
	 * @return
	 */
	public String getCssColorRenglon2() {
		return cssColorRenglon2;
	}

	/**
	 * @return
	 */
	public String getMetodos() {
		return metodos;
	}

	/**
	 * @return
	 */
	public int getNumColumnas() {
		return numColumnas;
	}

	/**
	 * @return
	 */
	public Object[] getParametrosQuery() {
		return parametrosQuery;
	}

	/**
	 * @return
	 */
	public int getQueryId() {
		return queryId;
	}

	/**
	 * @return
	 */
	public String getTitulos() {
		return titulos;
	}

	/**
	 * @return
	 */
	public String getValueMethodName() {
		return valueMethodName;
	}

	/**
	 * @return
	 */
	public Class getVOClass() {
		return VOClass;
	}

	/**
	 * @return
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @param string
	 */
	public void setAlineacionesColumnas(String string) {
		alineacionesColumnas = string;
	}

	/**
	 * @param i
	 */
	public void setAltoRenglon(int i) {
		altoRenglon = i;
	}

	/**
	 * @param string
	 */
	public void setAnchosColumnas(String string) {
		anchosColumnas = string;
	}

	/**
	 * @param i
	 */
	public void setBorder(int i) {
		border = i;
	}

	/**
	 * @param string
	 */
	public void setCamposExtras(String string) {
		camposExtras = string;
	}

	/**
	 * @param i
	 */
	public void setCellpadding(int i) {
		cellpadding = i;
	}

	/**
	 * @param i
	 */
	public void setCellspacing(int i) {
		cellspacing = i;
	}

	/**
	 * @param string
	 */
	public void setCssColorEncabezado(String string) {
		cssColorEncabezado = string;
	}

	/**
	 * @param string
	 */
	public void setCssColorRenglon1(String string) {
		cssColorRenglon1 = string;
	}

	/**
	 * @param string
	 */
	public void setCssColorRenglon2(String string) {
		cssColorRenglon2 = string;
	}

	/**
	 * @param string
	 */
	public void setMetodos(String string) {
		metodos = string;
	}

	/**
	 * @param i
	 */
	public void setNumColumnas(int i) {
		numColumnas = i;
	}

	/**
	 * @param objects
	 */
	public void setParametrosQuery(Object[] objects) {
		parametrosQuery = objects;
	}

	/**
	 * @param i
	 */
	public void setQueryId(int i) {
		queryId = i;
	}

	/**
	 * @param string
	 */
	public void setTitulos(String string) {
		titulos = string;
	}

	/**
	 * @param string
	 */
	public void setValueMethodName(String string) {
		valueMethodName = string;
	}

	/**
	 * @param class1
	 */
	public void setVOClass(Class class1) {
		VOClass = class1;
	}

	/**
	 * @param string
	 */
	public void setWidth(String string) {
		width = string;
	}

	/**
	 * @return
	 */
	public boolean isConsultar() {
		return consultar;
	}

	/**
	 * @param b
	 */
	public void setConsultar(boolean b) {
		consultar = b;
	}

	/**
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param i
	 */
	public void setPageSize(int i) {
		pageSize = i;
	}

	/**
	 * @return
	 */
	public boolean isAgregarValorCamposExtras() {
		return agregarValorCamposExtras;
	}
	
	/**
	 * @return
	 */
	public boolean getAgregarValorCamposExtras() {
		return agregarValorCamposExtras;
	}

	/**
	 * @param b
	 */
	public void setAgregarValorCamposExtras(boolean b) {
		agregarValorCamposExtras = b;
	}

	/**
	 * @return
	 */
	public String[] getParametrosPaginacion() {
		return parametrosPaginacion;
	}

	/**
	 * @param strings
	 */
	public void setParametrosPaginacion(String[] strings) {
		parametrosPaginacion = strings;
	}

}

