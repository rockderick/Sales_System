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
public class GeneralCheckTableEstaticoTag extends GeneralTableTag {

	/**
	 * Lista a desplegar
	 */
	protected ArrayList lista = new ArrayList();
	

	/** Ancho de la columna para poner el check button */ 
	protected String witdhColumnCheck;
	
	/** Titulo de la columna para poner el check */
	protected String titleColumnCheck = "&nbsp;";
	
	/** Nombre del check button */
	protected String checkName;
	
	/** Cadena para seleccionar un check button */
	protected String[] selected;
	
	/** Script a agregar al check button **/
	protected String script;

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



