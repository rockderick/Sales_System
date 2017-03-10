package mx.com.iusacell.catalogo.taglib;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;
import org.apache.commons.beanutils.MethodUtils;

/**
 * @author Juan Francisco Cardona A
 *
 * Presenta una tabla con la informacion de una consulta de la
 * base de datos
 */
public class GeneralCheckTableTag extends GeneralTableTag {
	
	/** Ancho de la columna para poner el radio button */ 
	protected String witdhColumnCheck;
	
	/** Titulo de la columna para poner el radio */
	protected String titleColumnCheck = "&nbsp;";
	
	/** Nombre del radio button */
	protected String checkName;
	
	/** Cadena para seleccionar un radio button */
	protected String[] selected;
	
	/** Script a agregar al radio button **/
	protected String script;
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException {
			return super.doStartTag();
	}
	
	/**
	 * Metodo invocado antes de poner las celdas para los titulos
	 * Este metodo puede sobreescribirse para agregar celdas antes de los titulos
	 *
	 */
	protected void doBeforeTitulos() throws IOException{
		JspWriter writer = super.pageContext.getOut();
		writer.print(Html.cadenaCeldaTable(witdhColumnCheck, super.cssColorEncabezado, super.altoRenglon, "center", titleColumnCheck));
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
		String select = "";
		try{
			String valorCelda = String.valueOf(MethodUtils.invokeExactMethod(elemento, super.valueMethodName, null));
			if(selected!=null){
				for(int i=0;i<selected.length;i++){
					if(selected[i].equals(valorCelda)){
						select = valorCelda;
						break;
					}
				}
			}
			writer.print(Html.cadenaHeaderCeldaTable(witdhColumnCheck, color, super.altoRenglon, "center"));
			writer.print(Html.cadenaCheckbox(checkName, valorCelda, select, script));
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
	 * @return
	 */
	public String getCheckName() {
		return checkName;
	}

	/**
	 * @return
	 */
	public String getTitleColumnCheck() {
		return titleColumnCheck;
	}

	/**
	 * @return
	 */
	public String getWitdhColumnCheck() {
		return witdhColumnCheck;
	}

	/**
	 * @param string
	 */
	public void setCheckName(String string) {
		checkName = string;
	}

	/**
	 * @param string
	 */
	public void setTitleColumnCheck(String string) {
		titleColumnCheck = string;
	}

	/**
	 * @param string
	 */
	public void setWitdhColumnCheck(String string) {
		witdhColumnCheck = string;
	}

	/**
	 * @return
	 */
	public String[] getSelected() {
		return selected;
	}

	/**
	 * @param string
	 */
	public void setSelected(String[] strings) {
		selected = strings;
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



