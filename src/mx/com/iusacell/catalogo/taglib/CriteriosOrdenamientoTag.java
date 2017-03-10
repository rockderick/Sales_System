/*
 * Created on Jan 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mx.com.iusacell.catalogo.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

/**
 * @author avaldez
 */
public class CriteriosOrdenamientoTag extends TagSupport {

	private String etiquetas;
	private String campos;

	protected ArrayList etiquetasArray = new ArrayList();
	protected ArrayList camposArray = new ArrayList();
	
	protected int numColumnas;

	public int doStartTag() throws JspException {
		ajustaParametros();
		try {
			insertaCriterioBusqueda(super.pageContext.getOut());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public void ajustaParametros(){
		StringTokenizer tokens;
		int contador;
		//Nombres de etiquetas
		tokens = new StringTokenizer(etiquetas, ",");
		numColumnas = 0;
		while(tokens.hasMoreTokens()){
			etiquetasArray.add(tokens.nextToken().trim());
			numColumnas++;
		}
	
		//Nombres de campos
		tokens = new StringTokenizer(campos, ",");
		contador = 0;
		while(tokens.hasMoreTokens()){
			camposArray.add(tokens.nextToken().trim());
			contador++;
		}
		if(contador < numColumnas){
			throw new CatalogoSystemException("Faltan metodos para generar la tabla general", null);
		}
	
		
	}
	private void insertaCriterioBusqueda(JspWriter out) throws IOException{
			
			ServletRequest req = super.pageContext.getRequest();
			String value = req.getParameter("order");
			String ascDesc = req.getParameter("ascDesc") == null ? "" : req.getParameter("ascDesc");
    		String selected = null;
			out.println("<table width=\"850\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
			out.println("  <tr> ");
			out.println("    <td background=\"gifs/lineapunt.gif\"><img src=\"gifs/spacer.gif\" width=\"1\" height=\"1\"/></td>");
			out.println("  </tr>");
			out.println("  <tr>");
			out.println("    <td class=\"tdgris\">");
			out.println("      <table width=\"650\" border=\"0\" cellspacing=\"0\" cellpadding=\"5\">");
			out.println("        <tr>");
			out.println("          <td width=\"131\"><div align=\"right\">Ordenar por</div></td>");
			out.println("          <td width=\"218\">");
			out.println("            <select name=\"order\">");
			for ( int i = 0 ; i < numColumnas; i++){
				if (value == null){
					selected = "";
				}else{
					if(value.equals((String)camposArray.get(i))){
						selected = " selected ";
					}else{
						selected = "";
					}
				}
				out.println("	          <option value=\"" + (String)camposArray.get(i) + "\"" + selected + ">" + (String)etiquetasArray.get(i) + "</option>");
			}
			out.println("            </select>");
			out.println("         </td>");
			out.println("         <td>"); 
			out.println("           <div align=\"center\">");	
			out.println("             <input type=\"radio\" name=\"ascDesc\" value=\"ASC\""  +  (ascDesc.equals("ASC" )||ascDesc.equals("") ?  " checked" : "") +">Ascendente&nbsp;&nbsp;</input>");
			out.println("             <input type=\"radio\" name=\"ascDesc\" value=\"DESC\"" +  (ascDesc.equals("DESC") ? " checked" : "") +">Descendente</input>");
			out.println("           </div>");
			out.println("         </td>");
			out.println("       </tr>");
			out.println("     </table>");
			out.println("   </td>");
			out.println("  </tr>");
			out.println("  <tr> ");
			out.println("    <td background=\"../gifs/lineapunt.gif\"><img src=\"../gifs/spacer.gif\" width=\"1\" height=\"1\"/></td>");
			out.println("  </tr>");
			out.println("</table>");
		}

	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}

	public String getEtiquetas() {
		return etiquetas;
	}

	public void setCampos(String campos) {
		this.campos = campos;
	}

	public String getCampos() {
		return campos;
	}

}

