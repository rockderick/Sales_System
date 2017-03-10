/*
 * Creado el 3/09/2006
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author JOJEDAH
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class PcHorasVO  implements java.io.Serializable{
	
	private int pcCveHora;
	private String pcDescHora;
	
	private int pcCveMinuto;
	private String pcDescMinuto;
	
	public PcHorasVO(){
		
	}

	/**
	 * @return
	 */
	public int getPcCveHora() {
		return pcCveHora;
	}

	/**
	 * @return
	 */
	public String getPcDescHora() {
		return pcDescHora;
	}

	/**
	 * @param i
	 */
	public void setPcCveHora(int i) {
		pcCveHora = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescHora(String string) {
		pcDescHora = string;
	}

	/**
	 * @return
	 */
	public int getPcCveMinuto() {
		return pcCveMinuto;
	}

	/**
	 * @return
	 */
	public String getPcDescMinuto() {
		return pcDescMinuto;
	}

	/**
	 * @param i
	 */
	public void setPcCveMinuto(int i) {
		pcCveMinuto = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescMinuto(String string) {
		pcDescMinuto = string;
	}

}
