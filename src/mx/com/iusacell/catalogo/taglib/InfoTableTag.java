/*
 * Creado el May 17, 2005
 *
 */
package mx.com.iusacell.catalogo.taglib;

/**
 * @author DVAZQUEZA
 *
 * Presenta una tabla con la informacion de una consulta de la
 * base de datos
 */

import java.io.IOException;


import javax.servlet.jsp.JspException;
import java.sql.SQLException;



import java.util.ArrayList;

public class InfoTableTag extends GeneralTableTag {

	/**
	 * Lista a desplegar
	 */
	protected ArrayList lista = new ArrayList();

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
		//JspWriter writer = pageContext.getOut();
		//writer.print(Html.cadenaCeldaTable(witdhColumnRadio, cssColorEncabezado, altoRenglon, "center", titleColumnRadio));
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
//		JspWriter writer = pageContext.getOut();
//		try{
//			writer.print(Html.cadenaHeaderCeldaTable(witdhColumnRadio, color, altoRenglon, "center"));
//			cambio para que solo aparezca un solo radio button para la tabla de bitacoras de proceso
//			parametro no requerido en las otras tablas
//			writer.print(Html.cadenaRadioButton(radioName, String.valueOf(MethodUtils.invokeExactMethod(elemento, valueMethodName, null)), selected, script));
//			writer.print(Html.cadenaEndCeldaTable());
//		} catch (NoSuchMethodException e) {
//			throw new CatalogoSystemException("Error al generar la tabla general de home ", e);
//		} catch (IllegalAccessException e) {
//			throw new CatalogoSystemException("Error al generar la tabla general de home ", e);
//		} catch (InvocationTargetException e) {
//			throw new CatalogoSystemException("Error al generar la tabla general de home ", e);
//		}
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

}



