/*
 * Created on 15/12/2004
 *
 */
package mx.com.iusacell.catalogo.utilerias;

/**
 * @author EHERNANDEZH
 *
 */
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;

import mx.com.iusacell.catalogo.utilerias.CatalogoMath;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

/**
 * La clase <Code>Conversion</Code> convierte valores primitivos a String con
 * un formato definido
 * <P>
 */
public class Conversion implements Serializable{

    private static DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
	private static DecimalFormat decimalFormat4Decimales;
    private static DecimalFormat decimalFormat;
    private static DecimalFormat intFormat;
    private static int numDecimales = 2;
    private static FieldPosition fieldPosition;
    static {
        simbolos.setDecimalSeparator('.');
        simbolos.setGroupingSeparator(',');
		decimalFormat4Decimales = new DecimalFormat("##,###,###,###,###,##0.0000", simbolos);
        decimalFormat = new DecimalFormat("##,###,###,###,###,##0.00", simbolos);
        intFormat = new DecimalFormat("##,###,###,###,###,##0", simbolos);
		fieldPosition = new FieldPosition(DecimalFormat.INTEGER_FIELD);
    }
    
    /**
     * Este metodo hace el formateo de un numero tipo double
     * para presentarlo en pantalla, aplicandole previamente
     * las reglas de redondeo.
     * 
     * @param valor El numero a formatear.
     * @return <code>double</code> El numero formateado con las
     * reglas de redondeo aplicadas.
     */
    public static String formateaConRedondeo(double valor) {
    	return formatea(CatalogoMath.redondear(valor));
    }

    /**
     * Método que formatea un valor double como un número con dos decimales
     * @param el double a formatear
     * @return Una cadena con el valor del double formateado
     */
    public static String  formatea(double d){
        StringBuffer result = new StringBuffer();
		decimalFormat.format(d, result, fieldPosition);
        return result.toString();
    }
    
    /**
     * Método que formatea un valor flotante como un número con dos decimales
     * @param el flotante a formatear
     * @return Una cadena con el valor del flotante formateado
     */
    public static String  formatea(float f){
        return formatea((double)f);
    }

	/**
	 * Método que formatea un valor double como un número con dos decimales
	 * @param el double a formatear
	 * @return Una cadena con el valor del double formateado
	 */
	public static String formatea(Double d) {
		StringBuffer result = new StringBuffer();
		if(d != null){
			decimalFormat.format(d, result, fieldPosition);
			return result.toString();
		}
		return "";
	}
	    
    /**
     * Método que formatea un valor int con ceros a su izquierda
     * @param i el int a formatear
     * @param size tamaño del string a regresar
     * @return Una cadena de longitud size con el valor del int rellenando
     * ceros a la izquierda
     */
    public static String  formateaCeros(int i,int size){
        return formateaCeros((long)i,size);
    }
    
    /**
     * Método que formatea un valor long con ceros a su izquierda
     * @param l el long a formatear
     * @param size tamaño del string a regresar
     * @return Una cadena de longitud size con el valor del long rellenando
     * ceros a la izquierda
     */
    public static String  formateaCeros(long l,int size){
        String strLong = String.valueOf(l);
        StringBuffer result = new StringBuffer(size);
        for(int i = strLong.length(); i < size; i++){
            result.append('0');
        }
        result.append(strLong);
        return result.toString();
    }
    
	/**
	 * Método que formatea una cadena con ceros a su izquierda
	 * @param l la cadena a formatear
	 * @param size tamaño del string a regresar
	 * @return Una cadena de longitud size con el valor del long rellenando
	 * ceros a la izquierda
	 */
	public static String  formateaCeros(String strLong,int size){  
		StringBuffer result = new StringBuffer(size);
		for(int i = strLong.length(); i < size; i++){
			result.append('0');
		}
		result.append(strLong);
		return result.toString();
	}
    
	/**
	 * Método que formatea un valor int como un número con comas
	 * @param el int a formatear
	 * @return Una cadena con el valor del int formateado
	 */
    public static String  formateaint(int f){
        return formateaint((long)f);
    }
    
    /**
     * Método que formatea un valor long como un número con comas.
     * @param el double a formatear
     * @return Una cadena con el valor del double formateado
     */
    public static String  formateaint(long d){
        StringBuffer result = new StringBuffer();
        intFormat.format(d, result, fieldPosition);
        return result.toString();
    }
    
    /**
     * Este metodo convierte un string en un tipo de dato int
     * @param valor La cadena a convertir
     * @return La cadena convertida en tipo int
     */
    public static int stringToInt(String valor){
    	
    	int numero = 0; 
    	try {
    		 numero = new Integer(valor).intValue();
    	} catch (NumberFormatException e) {
			throw new CatalogoSystemException("El parámetro no es un número valido", e);
    	}
    	
    	return numero;
    }

	/**
	 * Este metodo convierte un string en un tipo de dato double
	 * @param valor La cadena a convertir
	 * @return La cadena convertida en tipo double
	 */
	public static double stringToDouble(String valor){
    	
		double numero = 0; 
		try {
			 numero = new Double(valor).doubleValue();
		} catch (NumberFormatException e) {
			throw new CatalogoSystemException("El parámetro no es un número valido", e);
		}
    	
		return numero;
	}
	
	/**
	 * Método que trunca un valor double a cuatro decimales
	 * @param el double a truncar
	 * @return Una cadena con el valor del double truncado
	 */
	public static String  trunca4Decimales(double d){
		StringBuffer result = new StringBuffer();
		decimalFormat4Decimales.format(d, result, fieldPosition);
		return result.toString();
	}
}
