/*
 * Created on 21/02/2004
 *
 */
package mx.com.iusacell.catalogo.taglib;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;
import mx.com.iusacell.catalogo.utilerias.GeneralDAO;
import mx.com.iusacell.catalogo.utilerias.LeeParametros;

import org.apache.commons.beanutils.MethodUtils;

/**
 * @author DVAZQUEZA
 *
 * Clase de utileria para Generar una Lista/combo a partir de un Query
 */
public class GeneralListTag extends TagSupport {

	/**
	 * Numero de query a ejecutar
	 */
	protected int queryId = 0;
	/**
	 * Nombre del metodo a ejecutar del VO para desplegar el value de cada option
	 */
	protected String valueMethodName = null;
	/**
	 * Nombre del metodo a ejecutar del VO para desplegar la descripcion de cada option
	 */
	protected String descrMethodName = null;
	/**
	 * Clase VO que sera regresada por la consulta a la base de datos
	 */
	protected Class VOClass = null;
	/**
	 * Nombre del combo
	 */
	protected String name = null;
	/**
	 * Numero de renglones para desplegar el combo
	 */
	protected int size = 1;
	/**
	 * Valor del elemento que debe quedar seleccionado
	 */
	protected String selected = null;
	/**
	 * True si se desea que el combo quede desabilitado
	 */
	protected boolean disabled = false;
	/**
	 * True si se desea que se puedan seleccionar mas de un elemento del combo
	 */
	protected boolean multiple = false;
	/**
	 * Nombre de la clase ccs para el combo 
	 */
	protected String classHtml = null;
	/**
	 * Estilo html a utilizarse para el combo
	 */
	protected String style = null;
	/**
	 * Script(s) para agregar al combo Ej: onChange='recargarPagina();'
	 */
	protected String script = null;
	/**
	 * Texto a desplegar para el elemento vacio del combo (primer elemento)
	 */
	protected String textoNoSeleccion = null;
	/**
	 * Texto a desplegar para el valor del elemento vacio del combo (primer elemento)
	 */
	protected String valorNoSeleccion = null;
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
	 *		agregarValorCamposExtras="true"
	 * 		valueMethodName="getId"
	 * 	  	Se genera 
	 * 		<input type="hidden" name="id1" value="1">
	 * 		<input type="hidden" name="id2" value="2">
	 * 		<input type="hidden" name="cuentaPadre1" value="0001-0001-0000-0000">
	 * 		<input type="hidden" name="cuentaPadre2" value="0001-0001-0000-0000">
	 * 
	 */
	protected String camposExtras = null;
	
	/**
	 * True si se desea agregar a los nombres de los hidden (si se esta utilizando el campo
	 * camposExtras) el valor regresado por el campo valueMethodName
	 */
	protected boolean agregarValorCamposExtras = true;
	
	/** False si no se desea consultar la base de datos (default true) */
	protected boolean consultar = true;

	/**
	 * Variable privada para almacenar la consulta
	 */
	protected ArrayList data;

	/**
	 * Metodo que genera el html del select en pantalla
	 */
	public int doStartTag() throws JspException {
		try {
			if(consultar){
				realizaConsulta();
				generaCamposExtras();	
			}		
			generaHeaderSelect();
			generaEmptyOption();
			if(consultar){
				generaOptions();
			}
			generaEndSelect();
		} catch (IOException ioE) {
			
			throw new CatalogoSystemException("Error de tipo IO al crear combo " + name, ioE);
		} catch (SQLException sqlE) {
			throw new CatalogoSystemException("Error de tipo SQL al crear combo " + name, sqlE);
		}
		return SKIP_BODY;
	}
	
	/**
	 * Metodo que realiza la consulta a la base de datos
	 */
	public void realizaConsulta() throws SQLException{
		data = new GeneralDAO().findValueObjectsArray(queryId,parametrosQuery, VOClass);
		
	}
	
	/**
	 * Metodo que genera el html para agregar los campos extras que se deseen poner 
	 * ya sea como script en arreglos o como campos hidden
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
				throw new CatalogoSystemException("Error al generar el combo " + name, nsme);
			}catch (InvocationTargetException ite) {
				throw new CatalogoSystemException("Error al generar el combo " + name, ite);
			}catch (IllegalAccessException iae) {
				throw new CatalogoSystemException("Error al generar el combo " + name, iae);
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
		writer.println(Html.cadenaHidden(nombre, String.valueOf(valor)));
	}
	
	/**
	 * Metodo que genera el html para poner el encabezado de un select. Este metodo
	 * es el primero invocado por doStartTag y puede ser sobreescrito para proveer
	 * una funcionalidad diferente
	 */
	public void generaHeaderSelect() throws JspException, IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaHeaderSelect(name, size, disabled, multiple, classHtml, style, script));
	}
	
	/**
	 * Metodo que genera el html para poner el option vacio de un select de ser necesario. 
	 * Este metodo es invocado despues de generaHeaderSelect y puede ser sobreescrito para 
	 * proveer una funcionalidad diferente
	 */
	public void generaEmptyOption() throws JspException, IOException{
		if (textoNoSeleccion != null && valorNoSeleccion != null) {
			JspWriter writer = super.pageContext.getOut();
			writer.print(Html.cadenaOptionSelect(valorNoSeleccion, textoNoSeleccion, selected));
		}
	}
	
	/**
	 * Metodo que genera el html para poner los options de un select
	 * Este metodo es invocado despues de generaEmptyOption y puede ser sobreescrito para 
	 * proveer una funcionalidad diferente
	 */
	public void generaOptions() throws JspException, IOException{
		JspWriter writer = super.pageContext.getOut();
		try{
			
	Method metodoValor;
	Method metodoDescripcion;
			
			
			//poner los valores en el select
			Iterator it = data.iterator();
			Object valor = null;
			Object descr = null;
			Object vo = null;
			while (it.hasNext()) {
				vo = it.next();
				
				try{
					
					metodoValor = (vo.getClass()).getDeclaredMethod(valueMethodName, null);
				} catch (NoSuchMethodException nsme){
					try{
						metodoValor = (vo.getClass().getSuperclass()).getDeclaredMethod(valueMethodName, null);
					}catch (NoSuchMethodException nsme2){
						throw new CatalogoSystemException("Error al generar el combo " + name, nsme2);
					}
					
				}
				
				try{
					metodoDescripcion = (vo.getClass()).getDeclaredMethod(descrMethodName, null);
				} catch (NoSuchMethodException nsme){
					try{
						metodoDescripcion = (vo.getClass().getSuperclass()).getDeclaredMethod(descrMethodName, null);
					}catch (NoSuchMethodException nsme2){
						throw new CatalogoSystemException("Error al generar el combo " + name, nsme2);
					}
	
				}
				
								
				valor = metodoValor.invoke(vo,null);   //MethodUtils.invokeExactMethod(vo, valueMethodName, null);
				descr = metodoDescripcion.invoke(vo,null); //MethodUtils.invokeExactMethod(vo,descrMethodName, null);
				
				generaOption(valor, descr, selected);
			}						
		}catch (InvocationTargetException ite) {
			throw new CatalogoSystemException("Error al generar el combo " + name, ite);
		}catch (IllegalAccessException iae) {
			throw new CatalogoSystemException("Error al generar el combo " + name, iae);
		}
	}
	
	/**
	 * Metodo que genera el html para poner el option de un select
	 * Este metodo es invocado dentro del metodo generaOptions para cada elemento consultado
	 * y puede ser sobreescrito para proveer una funcionalidad diferente
	 */
	public void generaOption(Object valor, Object descr, String selected) throws JspException, IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaOptionSelect(String.valueOf(valor), String.valueOf(descr), selected));
	}
	
	/**
	 * Metodo que genera el html para poner fin a un select
	 * Este metodo es invocado despues de generaOptions y puede ser sobreescrito para 
	 * proveer una funcionalidad diferente
	 */
	public void generaEndSelect() throws JspException, IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaEndSelect());
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
	public String getClassHtml() {
		return classHtml;
	}

	/**
	 * @return
	 */
	public String getDescrMethodName() {
		return descrMethodName;
	}

	/**
	 * @return
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @return
	 */
	public boolean isMultiple() {
		return multiple;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
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
	public String getScript() {
		return script;
	}

	/**
	 * @return
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @return
	 */
	public String getTextoNoSeleccion() {
		return textoNoSeleccion;
	}

	/**
	 * @return
	 */
	public String getValorNoSeleccion() {
		return valorNoSeleccion;
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
	 * @param string
	 */
	public void setCamposExtras(String string) {
		camposExtras = string;
	}

	/**
	 * @param string
	 */
	public void setClassHtml(String string) {
		classHtml = string;
	}

	/**
	 * @param string
	 */
	public void setDescrMethodName(String string) {
		descrMethodName = string;
	}

	/**
	 * @param b
	 */
	public void setDisabled(boolean b) {
		disabled = b;
	}

	/**
	 * @param b
	 */
	public void setMultiple(boolean b) {
		multiple = b;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
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
	public void setScript(String string) {
		script = string;
	}

	/**
	 * @param string
	 */
	public void setSelected(String string) {
		selected = string;
	}

	/**
	 * @param i
	 */
	public void setSize(int i) {
		size = i;
	}

	/**
	 * @param string
	 */
	public void setStyle(String string) {
		style = string;
	}

	/**
	 * @param string
	 */
	public void setTextoNoSeleccion(String string) {
		textoNoSeleccion = string;
	}

	/**
	 * @param string
	 */
	public void setValorNoSeleccion(String string) {
		valorNoSeleccion = string;
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

}


