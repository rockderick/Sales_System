/*
 * Created on 15/12/2004
 *
 */
package mx.com.iusacell.catalogo.utilerias;

import java.io.Serializable;
/**
 * @author jojeda
 *
 */
public class CatalogoMath implements Serializable{
	/**
	 * Este metodo regresa un valor del tipo <code>double</code> trucado
	 * a cuatro valores decimales.
	 *  
	 * @param valor Un numero de tipo <code>double</code>.
	 * @return <code>double</code> El valor truncado a 4 decimales.
	 */
	public static double truncar(double valor) {
		
		double temporal = Math.floor(valor * 10000);
		return (temporal / 10000);
	}
	
	/**
	 * Este metodo regresa un valor del tipo <code>double</code> redondeandolo
	 * con dos valores decimales.
	 * 
	 * @param valor El numero a ser redondeado.
	 * @return <code>double</code> El valor redondeado a dos decimales.
	 */
	public static double redondear(double valor) {
		
		double redondeo = 0d;
		double preliminar = (valor * 100);
		String preStr = Double.toString(preliminar);
		String decimal = preStr.substring(preStr.indexOf(".") + 1, preStr.indexOf(".") + 2);
		int pivote = Integer.parseInt(decimal);
		if (pivote > 5) {
			redondeo = (Math.ceil(preliminar) / 100);
		} else {
			redondeo = (Math.floor(preliminar) / 100);
		}

		return redondeo;
	}
	
	/**
	 * Este metodo revisa si el numero que se le pasa como argumento
	 * es un numero par.
	 * 
	 * @param numero El numero que se quiere revisar.
	 * @return <code>boolean</code> <code>true</code> si el numero es
	 * par, <code>false</code> si el numero es impar.
	 */
	public static boolean esPar(int numero) {

		return ((numero % 2) == 0);
	}

	/**
	 * Este metodo evalua una cadena y regresa <code>true</code> si la
	 * cadena es un numero, o <code>false</code> si no lo es.
	 * 
	 * @param valorStr La cadena a ser evaluada.
	 * @return <code>boolean</code> <code>true</code> si la cadena es un 
	 * numero, o <code>false</code> si no lo es.
	 */
	public static boolean esNumeroEntero(String valorStr) {
		
		boolean resultado = false;
		try {
			Integer valor = new Integer(valorStr);
			resultado = true;
		} catch(NumberFormatException nfe) {
			resultado = false;
		}
		
		return resultado;
	}
	
	public static double absoluto(double valor) {
		
		return CatalogoMath.redondear(Math.abs(valor));
	}
}