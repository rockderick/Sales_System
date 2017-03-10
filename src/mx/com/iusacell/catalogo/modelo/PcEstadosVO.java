/*
 * Creado el Jul 4, 2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author jojedah
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class PcEstadosVO {

	public PcEstadosVO() {
	}
	
	private int pcCveEstado;
	private String pcDescEstado;
	private int pcCveZona;
	
	//CIUDADES
	private int pcCveCiudad;
	private String pcDescCiudad;
	
	/**
	 * @return
	 */
	public int getPcCveEstado() {
		return pcCveEstado;
	}

	/**
	 * @return
	 */
	public int getPcCveZona() {
		return pcCveZona;
	}

	/**
	 * @return
	 */
	public String getPcDescEstado() {
		return pcDescEstado;
	}

	/**
	 * @param i
	 */
	public void setPcCveEstado(int i) {
		pcCveEstado = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveZona(int i) {
		pcCveZona = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescEstado(String string) {
		pcDescEstado = string;
	}

	/**
	 * @return
	 */
	public int getPcCveCiudad() {
		return pcCveCiudad;
	}

	/**
	 * @return
	 */
	public String getPcDescCiudad() {
		return pcDescCiudad;
	}

	/**
	 * @param i
	 */
	public void setPcCveCiudad(int i) {
		pcCveCiudad = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescCiudad(String string) {
		pcDescCiudad = string;
	}

}
