/*
 * Created on 15/12/2004
 *
 */
package mx.com.iusacell.catalogo.taglib;

/**
 * @author EHERNANDEZH
 *
 */
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import mx.com.iusacell.catalogo.utilerias.PaginaDeElementos;
import mx.com.iusacell.catalogo.utilerias.ReportDate;

/**
 * Clase encargada de generar codigo html para diferentes elementos como combos y tablas
 *  
 * @author none
 *
 */
public class Html {

	private Html() {
	}
	
	/**
	 * Metodo que genera la cadena html para poner el encabezado de un select
	 * 
	 * @param name Nombre del componente html 
	 * @param size Numero de renglones para mostrar
	 * @param disabled True si debe estar desabilitado
	 * @param multiple True si se debe permitir seleccionar mas de un elemento a la vez
	 * @param classHtml Clase de css para el select
	 * @param style Estilo a utilizar para el select
	 * @param script Codigo script para agregarlo al header del select
	 * @return String Cadena con el html del header del select
	 */
	public static String cadenaHeaderSelect(
			String name,
			int size,
			boolean disabled,
			boolean multiple,
			String classHtml,
			String style,
			String script){
		StringBuffer html = new StringBuffer();
		html.append("\n\t<select ");
		if (name != null){
			html.append(" name=" + addComillas(name));
			html.append(" id=" + addComillas(name));
		}
		html.append(" size=" + addComillas(size));
		if (disabled){
			html.append(" disabled");
		}
		if (multiple){
			html.append(" multiple");
		}
		if (classHtml != null){
			html.append(" class=" + addComillas(classHtml));
		}
		if (style != null){
			html.append(" style=" + addComillas(style));
		}
		if (script != null){
			html.append(" " + script);
		}
		html.append(">");
		
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner el encabezado de un select
	 * @param valor Cadena a poner en el elemento value del option
	 * @param descr Cadena a desplegar como descripcion del option
	 * @param selected Cadena con el valor del option que debe quedar seleccionado
	 * @return String Cadena con el html del header del option
	 */
	public static String cadenaOptionSelect(
			String valor,
			String descr,
			String selected){
		StringBuffer html = new StringBuffer();
		html.append("\n\t\t<option value=" + addComillas(valor));
		if (html != null && valor.equalsIgnoreCase(selected)){
			html.append(" selected");
		}
		html.append(">");
		html.append(descr);
		html.append("</option>");
		return html.toString();
	}
	
	
	/**
	 * Metodo que genera la cadena html para poner el encabezado de un select
	 * @param valor Cadena a poner en el elemento value del option
	 * @param descr Cadena a desplegar como descripcion del option
	 * @param selected Cadena con el valor del option que debe quedar seleccionado
	 * @return String Cadena con el html del header del option
	 */
	public static String cadenaConsultaText(
			String valor,
			String descr,
			String selected){
		StringBuffer html = new StringBuffer();
		
		if (html != null && valor.equalsIgnoreCase(selected)){
			html.append("\n\t\t<b>");
			html.append(descr);
			html.append("\n\t\t</b>");
		}
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner fin a un select
	 * @return String Cadena con el html del fin del select
	 */
	public static String cadenaEndSelect(){
		StringBuffer html = new StringBuffer();
		html.append("\n\t</select>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner un hidden
	 * 
	 * @param nombre Nombre del hidden
	 * @param valor Valor a poner al hidden
	 * @return String Cadena con el html del hidden
	 */
	public static String cadenaHidden(String nombre, String valor){
		StringBuffer html = new StringBuffer();
		html.append("\n<input type=" + addComillas("hidden") + " id=" + addComillas(nombre) + " name=" + addComillas(nombre) + " value=" + addComillas(valor) + "/>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner una imagen
	 * 
	 * @param src Ruta donde se encuentra la imagen
	 * @param width Ancho de la imagen o null
	 * @param height Altura de la imagen o null
	 * @param alt Descripcion de la imagen o null
	 * @param border Borde de la imagen o null
	 * @param align Alineacion de la imagen o null
	 * @return
	 */
	public static String cadenaImg(String src, String width, String height, String alt, String border, String align){
		StringBuffer html = new StringBuffer();
		html.append("<img src=" + addComillas(src));
		if(width != null){
			html.append(" width=" + addComillas(width));
		}
		if(height != null){
			html.append(" height=" + addComillas(height));
		}
		if(alt != null){
			html.append(" alt=" + addComillas(alt));
		}
		if(border != null){
			html.append(" border=" + addComillas(border));
		}
		if(align != null){
			html.append(" align=" + addComillas(align));
		}
		html.append("/>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner un div
	 * 
	 * @param nombre Nombre del hidden
	 * @param id Valor a poner al div
	 * @param style Estilo del div 
	 * @return String Cadena con el html del div
	 */
	public static String cadenaHeaderDiv(String id, String style){
		StringBuffer html = new StringBuffer();
		html.append("<div id=" + addComillas(id)+ " style=" + addComillas(style) + ">");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner un href
	 * 
	 * @param nombre Nombre del hidden
	 * @param id Valor a poner al div
	 * @param style Estilo del div 
	 * @return String Cadena con el html del div
	 */
	public static String cadenaHeaderHref(String href){
		StringBuffer html = new StringBuffer();
		html.append("<a href=" + addComillas(href) + ">");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner un span y cerrarlo
	 * 
	 * @param texto Texto a poner dentro del span
	 * @param class Clase para el span o null 
	 * @return String Cadena con el html del span con el texto incluido
	 */
	public static String cadenaSpan(String texto, String clase){
		StringBuffer html = new StringBuffer();
		html.append(cadenaHeaderSpan(clase));
		html.append(texto);
		html.append(cadenaEndSpan());
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner un span
	 * 
	 * @param class Clase para el span o null 
	 * @return String Cadena con el html del span
	 */
	public static String cadenaHeaderSpan(String clase){
		StringBuffer html = new StringBuffer();
		html.append("<span");
		if(clase != null){
			html.append(" class=" + addComillas(clase));
		}
		html.append(">");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para cerrar un div
	 *  
	 * @return String Cadena con el html para cerrar un div
	 */
	public static String cadenaEndDiv(){
		StringBuffer html = new StringBuffer();
		html.append("</div>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para cerrar un href
	 *  
	 * @return String Cadena con el html para cerrar un href
	 */
	public static String cadenaEndHref(){
		StringBuffer html = new StringBuffer();
		html.append("</a>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para cerrar un span
	 *  
	 * @return String Cadena con el html para cerrar un span
	 */
	public static String cadenaEndSpan(){
		StringBuffer html = new StringBuffer();
		html.append("</span>");
		return html.toString();
	}

	/**
	 * Metodo que genera la cadena html para poner el encabezado de una tabla
	 * 
	 * @param border Borde de la tabla
	 * @param width Ancho de la tabla
	 * @param cellpadding Cellpadding para las celdas
	 * @param cellspacing Cellspacing para las celdas
	 * @return Cadena con el html del encabezado de la tabla
	 */
	public static String cadenaHeaderTable(
			int border,
			String width,
			int cellpadding,
			int cellspacing){
		StringBuffer html = new StringBuffer();
		html.append("\n\t<table");
		if (border >= 0){
			html.append(" border=" + addComillas(border));
		}
		if (width != null && width.length() > 0){
			html.append(" width=" + addComillas(width));
		}
		if (cellpadding >= 0){
			html.append(" cellpadding=" + addComillas(cellpadding));
		}
		if (cellspacing >= 0){
			html.append(" cellspacing=" + addComillas(cellspacing));
		}
		html.append(">");
		
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner fin a una tabla
	 * @return String Cadena con el html del fin del select
	 */
	public static String cadenaEndTable(){
		StringBuffer html = new StringBuffer();
		html.append("\n\t</table>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner el encabezado de un renglon
	 * de una tabla
	 * 
	 * @return Cadena con el html del encabezado del renglon de una tabla
	 */
	public static String cadenaHeaderRowTable(){
		StringBuffer html = new StringBuffer();
		html.append("\n\t\t<tr>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner el final de un renglon
	 * de una tabla
	 * 
	 * @return Cadena con el html del final del renglon de una tabla
	 */
	public static String cadenaEndRowTable(){
		StringBuffer html = new StringBuffer();
		html.append("\n\t\t</tr>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner una celda de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @param valor Cadena a poner dentro de la celda
	 * @return Cadena con el html de la celda para una tabla
	 */
	public static String cadenaCeldaTable(
			String width,
			String classCss,
			int height,
			String align,
			String valor){
				

				
		StringBuffer html = new StringBuffer();
		html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
		html.append(valor);
		html.append(cadenaEndCeldaTable());
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner una celda de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @param valor Cadena a poner dentro de la celda
	 * @return Cadena con el html de la celda para una tabla
	 */
	public static String cadenaCeldaTableEstadoCuenta(
			String width,
			String classCss,
			int height,
			String align,
			String valor,
			String grupoId,
			String conceptoId,
			int numColumna){
				
		if(grupoId.equals("1")&&conceptoId.equals("15")&& (numColumna == 1)){
				valor = "";
		}
		if(grupoId.equals("2")&&conceptoId.equals("16")&& (numColumna == 1)){
				valor = "";
		}
		if(grupoId.equals("3")&&conceptoId.equals("17")&& (numColumna == 1)){
				valor = "";
		}
		if(grupoId.equals("4")&&conceptoId.equals("18")&& (numColumna == 1)){
				valor = "";
		}
		if(grupoId.equals("5")&&conceptoId.equals("19")&& (numColumna == 1)){
				valor = "";
		}
		if(grupoId.equals("6")&&conceptoId.equals("20")&& (numColumna == 1)){
				valor = "";
		}
		if(grupoId.equals("10")&&conceptoId.equals("21")&& (numColumna == 1)){
				valor = "";
		}		
				
		StringBuffer html = new StringBuffer();
		html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
		html.append(valor);
		html.append(cadenaEndCeldaTable());
		return html.toString();
	}	
	
	/**
	 * Metodo que genera la cadena html para poner una celda de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @param valor Cadena a poner dentro de la celda
	 * @return Cadena con el html de la celda para una tabla
	 */
	public static String cadenaCeldaTituloTable(
			String width,
			String classCss,
			int height,
			String align,
			String valor){
		StringBuffer html = new StringBuffer();
		html.append(cadenaCeldaTituloTable(width, classCss, height, align));
		html.append(valor);		
		html.append(cadenaEndCeldaTable());
		return html.toString();
	}	
	
	/**
	 * Metodo que genera la cadena html para poner una celda de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @param valor Cadena a poner dentro de la celda
	 * @return Cadena con el html de la celda para una tabla
	 */
	public static String cadenaCeldaTituloEstimadaTable(
			String width,
			String classCss,
			int height,
			String align,
			String valor){
		StringBuffer html = new StringBuffer();
		html.append(cadenaHeaderCeldaEstimadaTable(width, classCss, height, align));
		html.append(valor);
		html.append(cadenaEndCeldaTable());
		return html.toString();
	}	
	
	/**
	 * Metodo que genera la cadena html para poner una celda de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @param valor Cadena a poner dentro de la celda
	 * @return Cadena con el html de la celda para una tabla
	 */
	public static String cadenaCeldaTitulosTable(
			String width,
			String classCss,
			int height,
			String align,
			String valor){
		StringBuffer html = new StringBuffer();
		String color ="FFFFFF";
		html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
		html.append("<FONT COLOR=" + addComillas(color)); 
		html.append(">");
		html.append(valor);
		html.append(cadenaEndCeldaTable());
		return html.toString();
	}	
	
	/**
	 * Metodo que genera la cadena html para poner una celda de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @param valor Cadena a poner dentro de la celda
	 * @return Cadena con el html de la celda para una tabla
	 */
	public static String cadenaCeldaResaltadaTable(
			String width,
			String classCss,
			int height,
			String align,
			String valor,
	        String funcionJavaScript){
		StringBuffer html = new StringBuffer();
		html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
		html.append("<A HREF=");
		html.append("\"" + funcionJavaScript + "(" + valor + ")" + "\"");
		

		
		html.append(">");
		html.append(valor);
		html.append(cadenaEndCeldaTable());
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner una celda de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @param valor Cadena a poner dentro de la celda
	 * @return Cadena con el html de la celda para una tabla
	 */
	public static String cadenaCeldaResaltadaEstadoCuentaTable(
			String width,
			String classCss,
			int height,
			String align,
			String valor,
			String funcionJavaScript,
			String grupo,
			String grupoId,
			String conceptoId){
			
		int idGrupo = Integer.parseInt(grupoId);		
		int idConcepto = Integer.parseInt(conceptoId);
		StringBuffer html = new StringBuffer();
		
				
		if(idConcepto != 15 ){
			if(idConcepto != 16 ){
				if(idConcepto != 17 ){
					if(idConcepto != 18 ){
						if(idConcepto != 19 ){
							if(idConcepto != 20 ){
								if(idConcepto != 21 ){
										html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
										html.append("<A HREF=");
										html.append("\"" + funcionJavaScript + "(" + "'" + grupoId + "'" + "," + "'" + conceptoId + "'" + ")" + "\"");
										html.append(">");
										html.append(valor);
										html.append("<A HREF=");
										html.append("\"" + "javascript:goToConsultarTablaComisiones" + "(" + "'" + grupoId + "'" + "," + "'" + conceptoId + "'" + ")" + "\"");
									html.append(">");
										html.append("<img src=" + "'" + "gifs/herencia.gif" + "'");
										html.append(" alt=" +  "'" + "Tabla de Comisiones"  + "'");
									html.append(" width=" +  "'" + "14"  + "'");
									html.append(" height=" +   "'" + "16"  + "'");
									html.append(" border=" +  "'" + "0"  + "'");
									html.append(" style=" +   "'" + "cursor:hand"  + "'");
									html.append(">");
									html.append("</A>");
									
										html.append(cadenaEndCeldaTable());
								} else {
									html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
									html.append(valor);
									html.append(cadenaEndCeldaTable());
								}
							} else {
								html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
								html.append(valor);
								html.append(cadenaEndCeldaTable());
							}	
						} else {
							html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
							html.append(valor);
							html.append(cadenaEndCeldaTable());
						}	
					} else {
						html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
						html.append(valor);
						html.append(cadenaEndCeldaTable());
					}	
				} else {
					html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
					html.append(valor);
					html.append(cadenaEndCeldaTable());
				}	
			} else {
				html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
				html.append(valor);
				html.append(cadenaEndCeldaTable());
			}	
		} else {
			html.append(cadenaHeaderCeldaTable(width, classCss, height, align));
			html.append(valor);
			html.append(cadenaEndCeldaTable());
		}	
			

		return html.toString();
	}	
		
	
	/**
	 * Metodo que genera la cadena html para poner el encabezado de una celda 
	 * de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @return Cadena con el html del encabezado de la celda para una tabla
	 */
	public static String cadenaHeaderCeldaTable(
			String width,
			String classCss,
			int height,
			String align){
		StringBuffer html = new StringBuffer();
		html.append("\n\t\t\t<td");
		if (width != null && width.length() > 0){
			html.append(" width=" + addComillas(width));
		}
		if (classCss != null && classCss.length() > 0){
			html.append(" class=" + addComillas(classCss));
		}
		if (align != null && align.length() > 0){
			html.append(" align=" + addComillas(align));
		}
		if (height >= 0){
			html.append(" height=" + addComillas(height));
		}
		html.append(">");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner el encabezado de una celda 
	 * de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @return Cadena con el html del encabezado de la celda para una tabla
	 */
	public static String cadenaCeldaTituloTable(
			String width,
			String classCss,
			int height,
			String align){
		String colorCelda="RED";
		String colorFuente="WHITE";
		String boton = "boton1";
		StringBuffer html = new StringBuffer();
		html.append("\n\t\t\t<td");
		if (width != null && width.length() > 0){
			html.append(" width=" + addComillas(width));
		}
		if (classCss != null && classCss.length() > 0){
			html.append(" class=" + addComillas(boton));
			//html.append(" class=" + addComillas(classCss));
			//html.append(" bgcolor=" + addComillas(colorCelda));
		}
		if (align != null && align.length() > 0){
			html.append(" align=" + addComillas(align));
		}
		if (height >= 0){
			html.append(" height=" + addComillas(height));
		}
		html.append(">");
		
		//html.append("<FONT");
		//html.append(" COLOR=" + addComillas(colorFuente));
		//html.append(">");
		
		return html.toString();
	}	
	
	/**
	 * Metodo que genera la cadena html para poner el encabezado de una celda 
	 * de una tabla
	 * 
	 * @param width Ancho de la celda
	 * @param classCss Clase css para la celda
	 * @param height Altura de la celda
	 * @param align Alineacion del texto dentro de la celda
	 * @return Cadena con el html del encabezado de la celda para una tabla
	 */
	public static String cadenaHeaderCeldaEstimadaTable(
			String width,
			String classCss,
			int height,
			String align){
		StringBuffer html = new StringBuffer();
		String boton = "boton1";
		html.append("\n\t\t\t<td");
		html.append(" colspan=" + addComillas(3));
		if (width != null && width.length() > 0){
			html.append(" width=" + addComillas(width));
		}
		if (classCss != null && classCss.length() > 0){
			html.append(" class=" + addComillas(boton));
			//html.append(" class=" + addComillas(classCss));
		}
		if (align != null && align.length() > 0){
			html.append(" align=" + addComillas(align));
		}
		if (height >= 0){
			html.append(" height=" + addComillas(height));
		}
		html.append(">");
		
		
		
		//html.append("<td rowspan='3' width='20%' class='boton1' align='left' height='13'>Si las ventas fueran el importe sería:<TH colspan='3'>");
			
	//	html.append("<td>51-150<td>151-300<td>más de 300");

		//html.append("</tr>");

		return html.toString();
	}	
	
	/**
	 * Metodo que genera la cadena html para poner el final de una celda
	 * de una tabla
	 * 
	 * @return Cadena con el html del final de la celda de una tabla
	 */
	public static String cadenaEndCeldaTable(){
		StringBuffer html = new StringBuffer();
		html.append("\n\t\t\t</td>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner un radio button
	 * 
	 * @param radioName Nombre del radio button
	 * @param value Valor para el radio button
	 * @param selected Cadena para seleccionar el radio button
	 * @return Cadena con el html del radio button
	 */
	public static String cadenaRadioButton(String radioName, String value, String selected, String script){
		StringBuffer html = new StringBuffer();
		html.append("<input type=" + addComillas("radio") 
						+ " value=" + addComillas(value) 
						+ " name=" + addComillas(radioName) 
						+ " " + (value.equals(selected) ? "checked" : "") 
						+ (script != null ? " " + script : "") + "/>");
		return html.toString();
	}

	/**
	 * Metodo que genera la cadena html para poner un checkbox
	 * 
	 * @param checkboxName Nombre del checkbox
	 * @param value Valor del checkbox
	 * @param selected Valor del checkbos que deba estar seleccionado
	 * @param script Script a agregar o null
	 * @return
	 */
	public static String cadenaCheckbox(String checkboxName, String value, String selected, String script){
		StringBuffer html = new StringBuffer();
		html.append("<input type=" + addComillas("checkbox") 
						+ " value=" + addComillas(value) 
						+ " name=" + addComillas(checkboxName) 
						+ " " + (value.equals(selected) ? "checked" : "") 
						+ (script != null ? " " + script : "") + "/>");
		return html.toString();
	}
	
	/**
	 * Metodo que genera la cadena html para poner un checkbox
	 * 
	 * @param checkboxName Nombre del checkbox
	 * @param value Valor del checkbox
	 * @param selected Valor del checkbos que deba estar seleccionado
	 * @param script Script a agregar o null
	 * @param habilitado define si el checkbox debe estar habilitado o no
	 * @return
	 */
	public static String cadenaCheckbox(String checkboxName, String value, String selected, String script, boolean habilitado){
		StringBuffer html = new StringBuffer();
		html.append("<input type=" + addComillas("checkbox") 
						+ " value=" + addComillas(value) 
						+ " name=" + addComillas(checkboxName) 
						+ " " + (value.equals(selected) ? "checked" : "") 
						+ " " + (!habilitado ? "disabled" : "") 
						+ (script != null ? " " + script : "") + "/>");
		return html.toString();
	}
	
	/**
	 * Metodo que agrega a un valor comillas simples ('') o dobles ("")
	 * Si el valor tiene comillas simples le pone comillas dobles, si no, pone comillas simples
	 * @param valor Cadena con el valor a agregar comillas simples o dobles
	 * @return
	 */
	public static String addComillas(String valor){
		if(valor != null){
			if(valor.indexOf("'") == -1){
				return "'" + valor + "'";
			}else{
				return "\"" + valor + "\"";
			}
		}
		return "''";
	}
	
	/**
	 * Metodo que agrega a un valor comillas simples ('') o dobles ("")
	 * 
	 * @param valor Tipo primitivo int a agregarle comillas simples o dobles
	 * @return 
	 */
	public static String addComillas(int valor){
		return addComillas(String.valueOf(valor));
	}

	/**
	 * Metodo que genera la cadena html para poner las ligas para paginacion
	 * @param total El total de resultados
	 * @param pageNum El numero de la pagina actual
	 * @param pageSize El tamaño de la pagina (cantidad de registros que se despliegan por pagina)
	 * @param urlBase El url que se utilizo para desplegar la pagina actual
	 * @param parametros Los parametros que se utilizaron para desplegar la pagina actual
	 * @param parametrosSobreescribir Tuplas de parametros a sobreescribir de los encontrados en el request
	 * @return Cadena con el html con hiperligas para paginacion
	 * @author Pedro Galvan
	 */
	public static String cadenaLigasPaginacion(PaginaDeElementos pagina, HttpServletRequest request, String[] parametrosSobreescribir) {
		StringBuffer html = new StringBuffer();
		if(pagina == null)
			return html.toString();

		// No se genera paginacion si solo hay una pagina
		if(pagina.getTotalPageNum() < 2) {
			return html.toString();
		}

		String urlBase = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		Map parametros = request.getParameterMap();
		int pageNum = pagina.getCurrentPageNum();

		String separador = "?";
		
		if ( queryString == null || parametros == null) {
			return "";
		}
		
		if(queryString.length() > 0) {
			//urlBase = urlBase + separador + queryString;
			//separador = "&";
			StringTokenizer tokens = new StringTokenizer(queryString, "?&=");
			String param;
			String valor;
			while(tokens.hasMoreTokens()){
				param = tokens.nextToken();
				if(tokens.hasMoreTokens()){
					valor = tokens.nextToken();
					parametros.put(param, new String[]{valor});
				}
			}
		}

		/* Necesitamos agregar manualmente los parametros que trae el request. Esto se hace para 
		 * que no se pierda el criterio de busqueda al ir de pagina en pagina.
		 * Para cada parametro hay que verificar si se desea sobreescribir con el arreglo de 
		 * parametrosSobreescribir
		 */
		Iterator it = parametros.keySet().iterator();
		int j;
		int indexParam;
		boolean sobreEscribirParametro;
		String[] paramValues;
		while (it.hasNext()) {
			String paramName = it.next().toString();
			indexParam = urlBase.indexOf(paramName); 
			if(indexParam > 0){
				//encontro la palabra pero verificar que es un parametro completo
				if(urlBase.charAt(indexParam - 1) == '?' || urlBase.charAt(indexParam - 1) == '&'){
					if(indexParam + paramName.length() < urlBase.length() && urlBase.charAt(indexParam + paramName.length()) == '='){
						continue;
					}
				}
			}
			//verificar si desean sobreescribir parametros
			sobreEscribirParametro = false;
			paramValues = new String[]{};
			if(parametrosSobreescribir != null){
				for(j = 0; j < parametrosSobreescribir.length; j++){
					if(j % 2 == 0){
						if(paramName.equals(parametrosSobreescribir[j]) && j + 1 < parametrosSobreescribir.length){
							paramValues = new String[]{parametrosSobreescribir[j + 1]};
							sobreEscribirParametro = true;
							break;
						}
					}
				}
			}
			
			if(!sobreEscribirParametro){
				paramValues = (String [])parametros.get(paramName);
			}
			
			/* Necesitamos hacer un ciclo porque puede que un parametro tenga varios valores. */
			for(int i=0; i < paramValues.length; i++) {
				String valor = paramValues[i];
				if(valor == null) {
					continue;
				}
				if(valor.trim().length() < 1) {
					continue;
				}
				urlBase = urlBase + separador + paramName + "="+valor;
				separador = "&";
			}
		}	
		int index =	urlBase.lastIndexOf("pageNum=");
		if(index > 0) {
			urlBase = urlBase.substring(0,index-1);
		}	
		
		/* Cerramos el div anterior (el que contiene a la tabla con scroll) */
		html.append("</div>");
		/*	Abrimos un nuevo div que dejamos sin cerrar	para que el jsp piense que es el de la tabla con scroll */
		html.append("<div align='right'>"); 

		html.append("Página de Resultados: ");

		if(pagina.hasPrev()) {
			html.append("<a href='" + urlBase + separador + "pageNum=" + pagina.getPrevPageNum() + "'> <B>&lt;</B> </a>\n");
		}
		int firstPage = 1 < (pagina.getCurrentPageNum() - 5) ? pagina.getCurrentPageNum() - 5 : 1;
		int lastPage = pagina.getTotalPageNum() > (pagina.getCurrentPageNum() + 5) ? pagina.getCurrentPageNum() + 5 : pagina.getTotalPageNum();
		for (int i = firstPage; i <= lastPage; i++) {
			if(i != pageNum) {
				html.append("<a href='" + urlBase + separador + "pageNum=" + i + "'>" + i + "</a>\n");
			}
			else {
				html.append(" <B>"+i+"</B> ");
			}
		}
		if(pagina.hasNext()) {
			html.append("<a href='" + urlBase + separador + "pageNum=" + pagina.getNextPageNum() + "'> <B>&gt;</B> </a>\n");
		}
		html.append("<br>");
		return html.toString();
	}
	
   /**
	* Metodo que genera un subtitulo de un proveedor especificao 
	* 
	* @param prov Nombre del proveedor
	* @param numCol Numero de columnas
	* @return Cadena con el html del proveedor
	*/
	public static String generaSubtitulo(String prov, int numCol){
		StringBuffer html = new StringBuffer();
		html.append("<tr><td colspan='"+ numCol + "'><br><span class='subtitulos'>" 
					+ prov + "</span><br></td></tr>");
		return html.toString();
	}
	
	/**
	 * Metodo que agrega la fecha de generacion del reporte el formato "EEEE, d MMMM yyyy HH:mm:ss"
	 * @author payon
	 *
	 */
	public static String putDate(int numColumnas){
	    StringBuffer html = new StringBuffer();
	    return html.append("<tr><td colspan='" + numColumnas + "'><br><span>" + 
	            			(new ReportDate()) + "</span><br></td></tr>").toString();
	}

}

