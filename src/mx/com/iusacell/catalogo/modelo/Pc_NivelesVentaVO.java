/* Tipo de Archivo:   Clase.
 * Nombre:            mx.com.iusacell.catalogo.modelo.Pc_NivelesVentaVO.java
 * Empresa:           Iusacell.
 * Descripcion:       Se encarga de encapsular los valores de la tabla cl_configuracion.
 * Fecha de Creacion: Feb 12, 2008. 17:00 hrs.
 * Autor:             CAL.
 * Versión:           1.0
 * 
 */
package mx.com.iusacell.catalogo.modelo;

import java.io.Serializable;

/**
 * @author CAL.
 *
 *  Nombre:  mx.com.iusacell.catalogo.modelo.Pc_NivelesVentaVO.java
 *  Empresa: Iusacell.
 * <p>Se encarga de encapsular los valores de la tabla cl_configuracion.</p>
 */
public class Pc_NivelesVentaVO implements Serializable{
	    
	    private int        clConfiguracion;
		private String     dsConfiguracion;
		private String     dsValor;
		//AAJ
		private int pcNivel;
		private String pcCuota;
		

    /**
     * Constructor por defecto.
     *
     */
	public Pc_NivelesVentaVO() {
	} 
	
    /**
     * Constructor que recibe los tres parametros.
     * 
     * @param clConfiguracion
     * @param dsConfiguracion
     * @param dsValor
     */
	public Pc_NivelesVentaVO( int clConfiguracion, String dsConfiguracion, String dsValor){
		setClConfiguracion(clConfiguracion);
		setDsConfiguracion(dsConfiguracion);
		setDsValor(dsValor);
	}

		/**
		 * @return
		 */
		public int getClConfiguracion() {
			return clConfiguracion;
		}

		/**
		 * @return
		 */
		public String getDsConfiguracion() {
			return dsConfiguracion;
		}

		/**
		 * @return
		 */
		public String getDsValor() {
			return dsValor;
		}

		/**
		 * @param i
		 */
		public void setClConfiguracion(int i) {
			clConfiguracion = i;
		}

		/**
		 * @param string
		 */
		public void setDsConfiguracion(String string) {
			dsConfiguracion = string;
		}

		/**
		 * @param string
		 */
		public void setDsValor(String string) {
			dsValor = string;
		}

		/**
		 * @return
		 */
		public String getPcCuota() {
			return pcNivel+" - "+pcCuota;
		}

		/**
		 * @return
		 */
		public int getPcNivel() {
			return pcNivel;
		}

		/**
		 * @param string
		 */
		public void setPcCuota(String string) {
			pcCuota = string;
		}

		/**
		 * @param i
		 */
		public void setPcNivel(int i) {
			pcNivel = i;
		}

}//Fin de la clase Pc_NivelesVentaVO**********************************
