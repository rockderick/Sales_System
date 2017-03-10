/*
 * Created on 15/12/2004
 *
 */
package mx.com.iusacell.catalogo.utilerias;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * La clase <Code>LeeParametros</Code> decodifica una cadena con el formato
 * key=value,key2=value2,...,key3=value3 y
 * <P>
 * @author Juan Francisco Cardona Amozurrutia
 */
 
public class LeeParametros {
	  /**
   * <Code>keys</Code> Arreglo privado de llaves
   */
  private ArrayList keys;

  /**
   * <Code>values</Code> Arreglo privado de valores
   */
  private ArrayList values;

/**
 * LeeParametros constructor comment.
 */
public LeeParametros() {
	super();
}
  /**
   * Constructor de la clase
   * @param cadena Cadena de parámetros codificada de la siguiente manera:
   * key1=valor1,key2=valor2,...,keyn=valorn
   * @param separadorParametros Letra para separar parametros
   * @param separadorLlaveValor Letra para separar las llaves de sus valores
   */
  public LeeParametros(String cadena, char separadorParametros,
          char separadorLlaveValor){
      keys = new ArrayList();
      values = new ArrayList();
      if(cadena != null){
          StringTokenizer elementos;
          StringTokenizer tokens =
              new StringTokenizer(cadena,String.valueOf(separadorParametros));
          String key;
          String value;
          while(tokens.hasMoreTokens()){
              elementos = new StringTokenizer(tokens.nextToken(),
                  String.valueOf(separadorLlaveValor));
              if(elementos.hasMoreTokens()){
                  key = elementos.nextToken();
                  if(elementos.hasMoreTokens()){
                      value = elementos.nextToken();
                      keys.add(key);
                      values.add(value);
                  }
              }
          }
      }
  }
  /**
   * Constructor de la clase
   * @param cadena Cadena de parámetros codificada de la siguiente manera:
   * key1=valor1,key2=valor2,...,keyn=valorn
   * @param separadorParametros Cadena para separar parametros
   * @param separadorLlaveValor Cadena para separar las llaves de sus valores
   */
  public LeeParametros(String cadena, String separadorParametros,
          String separadorLlaveValor){
      keys = new ArrayList();
      values = new ArrayList();
      if(cadena != null && separadorParametros != null &&
              separadorLlaveValor != null){
          //Posiciones para leer parametros
          int posActual = 0;
          int lastPos = 0;
          //Posicion del separador de llave valor
          int posSeparaLlaveValor = 0;
          //longitud de los separadores
          int lenSeparadorParametros = separadorParametros.length();
          int lenSeparadorLlaveValor = separadorLlaveValor.length();
          //variables para almacenar las llaves y valores
          String key;
          String value;
          String parametro;
          while((posActual=indexOf(cadena,separadorParametros,posActual)) !=-1){
              parametro = cadena.substring(lastPos, posActual);
              if((posSeparaLlaveValor = parametro.indexOf(separadorLlaveValor))
                       != -1){
                  key = parametro.substring(0, posSeparaLlaveValor);
                  value = parametro.substring(
                      posSeparaLlaveValor + lenSeparadorLlaveValor);
                  keys.add(key);
                  values.add(value);
              }
              posActual += lenSeparadorParametros;
              lastPos = posActual;
          }

      }
  }
  /**
   * Método privado que regresa la posición de del string separador dentro del
   * string cadena. Si el separador no se encuentra en la cadena y la posición
   * actual no es el fin de cadena regresa la longitud de la cadena
   */
  private int indexOf(String cadena, String separador, int posActual){
      int pos = cadena.indexOf(separador, posActual);
      if(pos != -1){
          return pos;
      }
      if(posActual < cadena.length()){
          return cadena.length();
      }
      return -1;
  }
  /**
   * Mótodo para leer el parámetro asignado a una llave
   * @param llave Llave para accesar al parámetro
   * @return El string asiciado con la llave o null si la llave no existe
   */
  public String leeValor(String llave){
      int pos = keys.indexOf(llave);
      return pos > -1 ? (String)values.get(pos) : null;
  }
	/**
	 * @return
	 */
	public ArrayList getKeys() {
		return keys;
	}

/**
 * @return
 */
public ArrayList getValues() {
	return values;
}

}
