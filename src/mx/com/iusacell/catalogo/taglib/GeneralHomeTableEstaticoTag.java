package mx.com.iusacell.catalogo.taglib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.sql.SQLException;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;
import org.apache.commons.beanutils.MethodUtils;

import java.util.ArrayList;
/**
 * @author DVAZQUEZA
 *
 * Presenta una tabla con la informacion de una consulta de la
 * base de datos
 */
public class GeneralHomeTableEstaticoTag extends GeneralTableTag {

	/**
	 * Lista a desplegar
	 */
	protected ArrayList lista = new ArrayList();
	
	/** Ancho de la columna para poner el radio button */ 
	protected String witdhColumnRadio;
	
	/** Titulo de la columna para poner el radio */
	protected String titleColumnRadio = "&nbsp;";
	
	/** Nombre del radio button */
	protected String radioName;
	
	/** Cadena para seleccionar un radio button */
	protected String selected;
	
	/** Script a agregar al radio button **/
	protected String script;
	
	/** variables solo utilizadas para la tabla de bitacora de proceso **/
	/** indica si es una tabla de bitacora de proceso **/
	protected boolean radioBip;
	/** indica el campo que se obtendra de la tabla de bitacora de proceso y será comparado **/
	protected String valueMethodBip;
	/** indica el valor a comparar con el registro de la tabla **/
	protected String valueStatusBip;
	 

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
//		try{
//			ajustaParametros();
//			if(consultar){
//				setPaginacionInfo();
//				realizaConsulta();
//				generaCamposExtras();
//			}
//			generaHeaderTable();
//			generaTitulosTable();
//			generaRenglonesTable();
//			generaEndTable();
//			if(consultar){
//				//generaLigasPaginacion(parametrosPaginacion);
//			}
//		}catch (IOException ioE) {
//			throw new CatalogoSystemException("Error de tipo IO al crear tabla general ", ioE);
//		}catch (SQLException sqlE) {
//			throw new CatalogoSystemException("Error de tipo SQL al crear tabla general ", sqlE);
//		}
		return super.doStartTag();
	}
	
	/**
	 * Metodo invocado antes de poner las celdas para los titulos
	 * Este metodo puede sobreescribirse para agregar celdas antes de los titulos
	 *
	 */
	protected void doBeforeTitulos() throws IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaCeldaTable(witdhColumnRadio, super.cssColorEncabezado, super.altoRenglon, "center", titleColumnRadio));
	}			

	/**
	 * Metodo invocado antes de poner las celdas de la tabla
	 * Este metodo puede sobreescribirse para agregar celdas antes de las demas celdas
	 *
	 * @param elemento Objecto VO a presentar su informacion
	 * @param color Css color para mostrar el renglo
	 * @param numElemento Numero de elemento actual empezando en 0
	 */
	protected void doBeforeCeldas(Object elemento, String color, int numElemento) throws IOException{
		JspWriter writer = super.pageContext.getOut();
		try{
			writer.print(Html.cadenaHeaderCeldaTable(witdhColumnRadio, color, super.altoRenglon, "center"));
			//cambio para que solo aparezca un solo radio button para la tabla de bitacoras de proceso
			//parametro no requerido en las otras tablas
			if(radioBip){
				if(String.valueOf(MethodUtils.invokeExactMethod(elemento, valueMethodBip, null)).equalsIgnoreCase(valueStatusBip)){// con esto obtiene el value
					writer.print(Html.cadenaRadioButton(radioName, String.valueOf(MethodUtils.invokeExactMethod(elemento, super.valueMethodName, null)), selected, script));
				}	
			}else{
				writer.print(Html.cadenaRadioButton(radioName, String.valueOf(MethodUtils.invokeExactMethod(elemento, super.valueMethodName, null)), selected, script));
			}
			writer.print(Html.cadenaEndCeldaTable());
		} catch (NoSuchMethodException e) {
			throw new CatalogoSystemException("Error al generar la tabla general de home ", e);
		} catch (IllegalAccessException e) {
			throw new CatalogoSystemException("Error al generar la tabla general de home ", e);
		} catch (InvocationTargetException e) {
			throw new CatalogoSystemException("Error al generar la tabla general de home ", e);
		}
	}
	/**
	 * Metodo que realiza la consulta a la base de datos
	 */
	protected void realizaConsulta() throws SQLException {
		//data = pagina.getElements();
		super.data=lista;
	}
	
	/**
	 * @return
	 */
	public ArrayList getLista() {
		return lista;
	}

	/**
	 * @param list
	 */
	public void setLista(ArrayList lista) {
		this.lista = lista;
	}
			
	/**
	 * @return
	 */
	public String getRadioName() {
		return radioName;
	}

	/**
	 * @return Returns the radio.
	 */
	public boolean getRadioBip() {
		return radioBip;
	}
	/**
	 * @param radio The radio to set.
	 */
	public void setRadioBip(boolean radioBip) {
		this.radioBip = radioBip;
	}
	/**
	 * @return Returns the valueMethodBip.
	 */
	public String getValueMethodBip() {
		return valueMethodBip;
	}
	/**
	 * @param valueMethodBip The valueMethodBip to set.
	 */
	public void setValueMethodBip(String valueMethodBip) {
		this.valueMethodBip = valueMethodBip;
	}
	/**
	 * @return Returns the valueStatusBip.
	 */
	public String getValueStatusBip() {
		return valueStatusBip;
	}
	/**
	 * @param valueStatusBip The valueStatusBip to set.
	 */
	public void setValueStatusBip(String valueStatusBip) {
		this.valueStatusBip = valueStatusBip;
	}
	/**
	 * @return
	 */
	public String getTitleColumnRadio() {
		return titleColumnRadio;
	}

	/**
	 * @return
	 */
	public String getWitdhColumnRadio() {
		return witdhColumnRadio;
	}

	/**
	 * @param string
	 */
	public void setRadioName(String string) {
		radioName = string;
	}

	/**
	 * @param string
	 */
	public void setTitleColumnRadio(String string) {
		titleColumnRadio = string;
	}

	/**
	 * @param string
	 */
	public void setWitdhColumnRadio(String string) {
		witdhColumnRadio = string;
	}

	/**
	 * @return
	 */
	public String getSelected() {
		return selected;
	}

	/**
	 * @param string
	 */
	public void setSelected(String string) {
		selected = string;
	}

	/**
	 * @return
	 */
	public String getScript() {
		return script;
	}

	/**
	 * @param string
	 */
	public void setScript(String string) {
		script = string;
	}



}



