/*
 * Created on 15/12/2004
 *
 */
package mx.com.iusacell.catalogo.utilerias;

/**
 * @author EHERNANDEZH
 *
 */
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;

/**
 * La clase <Code>Fecha</Code> convierte fechas a distintos formatos, p.e.
 * DD/MM/YYYY, para su uso entre los distintos servicios del sistema
 * <P>
 * @author Juan Francisco Cardona
 */
public class Fecha  implements java.io.Serializable {

	private static SimpleDateFormat dateFormat;
	private static SimpleDateFormat dateFormatDB;
	private static SimpleDateFormat currentFormat;
	private static SimpleDateFormat currentFormatDB;
	
	private static FieldPosition fieldPosition;
	private static FieldPosition fieldPositionDB;
	private static FieldPosition fieldPositionPeriodo;
	
	static {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatDB = new SimpleDateFormat("MM/dd/yyyy");
		fieldPosition = new FieldPosition(SimpleDateFormat.DEFAULT);
		fieldPositionDB = new FieldPosition(SimpleDateFormat.DEFAULT);
	
		currentFormat = new SimpleDateFormat("MMddyyyyHHmmss");
		currentFormatDB = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		fieldPositionPeriodo = new FieldPosition(SimpleDateFormat.DEFAULT);
	}

    /**
     * Convierte una fecha almacenada en un objeto <Code>java.util.Date
     * </Code> a un objeto <Code>String</Code>  con el formato
     * "DD/MM/YYYY" para mostrarse en pantalla
     * 
     * @param date Fecha a convertir
     * @return Cadena con el formato especificado
     */
    static public String dateToString(java.util.Date date) {
		StringBuffer result = new StringBuffer();
	  try{		
		if(date != null){	
			dateFormat.format(date, result, fieldPosition);
		 }
	  }catch(Exception e){
		throw new CatalogoSystemException("Error al convertir la cadena a String", e);
	  }
		return result.toString();
    }
    
    /**
     * Convierte una fecha almacenada en un <Code>String</Code> con el formato
     * "DD/MM/YYYY" a un objeto <Code>java.util.date</Code>. Para convertir fechas
     * capturadas por el usuario
     * 
     * @param fecha Fecha en cadena a convertir
     * @return date con la fecha especificada
     */
    static public java.util.Date stringToDate(String fecha) {	
    	try{	
			return dateFormat.parse(fecha);
    	}catch(ParseException e){
    		throw new CatalogoSystemException("Error al convertir la cadena '" + fecha + "' a Date", e);
    	}
    }
    
	/**
	 * Convierte una fecha almacenada en un objeto <Code>java.util.Date
	 * </Code> a un objeto <Code>String</Code>  con el formato
	 * "MM/DD/YYYY" para mandarse a comparar en queries en la base de datos
	 * 
	 * @param date Fecha a convertir
	 * @return Cadena con el formato especificado
	 */
	static public String dateToStringDB(java.util.Date date) {
		StringBuffer result = new StringBuffer();	
		if(date != null){	
			dateFormatDB.format(date, result, fieldPositionDB);
		}
		return result.toString();
	}

	/**
	 * Convierte una fecha almacenada en un objeto <Code>java.util.Date
	 * </Code> a un objeto <Code>String</Code>  con el formato
	 * "YYYYMM" para mostrarse en pantalla
	 * 
	 * @param date Fecha a convertir
	 * @return Cadena con el formato especificado
	 */
	static public String dateToStringCurrent(java.util.Date date) {
		StringBuffer result = new StringBuffer();	
		if(date != null){	
			currentFormat.format(date, result, fieldPositionPeriodo);
		}
		return result.toString();
	}


	/**
	 * Convierte una fecha almacenada en un <Code>String</Code> con el formato
	 * "DD/MM/YYYY" a un objeto <Code>java.util.date</Code>. Para convertir fechas
	 * capturadas por el usuario
	 * 
	 * @param fecha Fecha en cadena a convertir
	 * @return date con la fecha especificada
	 */
	static public java.util.Date stringToDateDB(String fecha) {	
		try{	
			return currentFormatDB.parse(fecha);
		}catch(ParseException e){
			throw new CatalogoSystemException("Error al convertir la cadena '" + fecha + "' a Date", e);
		}
	}
	/**
	 * Obtiene el numero de Anio correspondiente a 
	 * una fecha almacenada en un objeto <Code>java.util.Date</Code> 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param date Fecha
	 * @return Numero de Quincena
	 */
	static public int getAnio(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);

		return cal.get(Calendar.YEAR);
	}

	/**
	 * Obtiene el numero de Quincena correspondiente a 
	 * una fecha almacenada en un objeto <Code>java.util.Date</Code> 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param date Fecha
	 * @return Numero de Quincena
	 */
	static public int getQuincena(Date fecha) {

	  Calendar cal = Calendar.getInstance();
	  cal.setTime(fecha);

	  int numMes = cal.get(Calendar.MONTH);
	  int numDia = cal.get(Calendar.DAY_OF_MONTH);

	  int numQuincena = numMes * 2;
	  if (numDia > 15){
		numQuincena++;
	  }
	  numQuincena++;

	  return numQuincena;
	}
	
	/**
	 * Obtiene la fecha correspondiente al dia de inicio de la Fecha Dada 
	 * en forma de Anio y Quincena, esta es debuelta en 
	 * un objeto <Code>java.util.Date</Code> 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param int Anio, int Quincena
	 * @return date Fecha
	 */
	static public Date getFechaInicioQuincena(int anio, int quincena) {
		int numMes = (int)Math.ceil((quincena/2.0)-1);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, anio);
		cal.set(Calendar.MONTH, numMes);

		if((quincena%2)==0){
			cal.set(Calendar.DAY_OF_MONTH, 16);		
		}
		else{
			cal.set(Calendar.DAY_OF_MONTH, 1);				
		}
		
		return cal.getTime();
	}
	
	/**
	 * Obtiene la fecha correspondiente al dia final de la Fecha Dada 
	 * en forma de Anio y Quincena, esta es debuelta en 
	 * un objeto <Code>java.util.Date</Code> 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param int Anio, int Quincena
	 * @return date Fecha
	 */
	static public Date getFechaFinQuincena(int anio, int quincena) {
		int numMes = (int)Math.ceil((quincena/2.0)-1);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, anio);
		cal.set(Calendar.MONTH, numMes);

		if((quincena%2)==0){
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		else{
			cal.set(Calendar.DAY_OF_MONTH, 15);				
		}
		
		return cal.getTime();
	}
	
	
	/**
	 * Obtiene el numero de Quincena Actual
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @return Numero de Quincena actual en un String 
	 * 
	 */
	static public String getStrQuincenaAct() {
		
		return new String("" + getQuincenaAct());		
	}

	/**
	 * Obtiene el numero de Quincena Actual
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @return Numero de Quincena actual 
	 * 
	 */
	static public int getQuincenaAct() {
		Calendar cal = Calendar.getInstance();
		
		return getQuincena(cal.getTime());		
	}


	/**
	 * Obtiene el numero del Year Actual
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @return Numero de Year actual en un String 
	 * 
	 */
	static public String getStrYearAct() {
		Calendar cal = Calendar.getInstance();
		
		return new String("" + cal.get(Calendar.YEAR));		
	}

	/**
	 * Obtiene la Fecha Actual Actual
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @return Fecha Actual 
	 * 
	 */
	static public java.util.Date getHoy() {
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime();		
	}

	/**
	 * Obtiene la fecha sumando un numero de quincenas dadas   
	 * fecha almacenada en un objeto <Code>java.util.Date</Code> 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param date Fecha
	 * @return date Fecha con la siguiente quincena.
	 */
	static public Date getSumaQuincenas(int anio, int quincena, int quincenasSumar) {
		int numeroQuincenasAnio = 24;
		int nuevoAnio = 0;
		int nuevaQuincena = 0;
		
		int numeroQuincenasFaltantesAnio = numeroQuincenasAnio - quincena;
		int numeroQuicenasSobrantes = 0;
		
		if(numeroQuincenasFaltantesAnio >= quincenasSumar){
			nuevoAnio = anio;
			nuevaQuincena = quincena + quincenasSumar;
		}
		else{
			nuevoAnio = anio;

			quincenasSumar -= numeroQuincenasFaltantesAnio;
			nuevoAnio++;

			while(quincenasSumar > numeroQuincenasAnio){
				quincenasSumar -= numeroQuincenasAnio;
				nuevoAnio++;
			}
			
			nuevaQuincena = quincenasSumar; 
		}

		return getFechaInicioQuincena(nuevoAnio, nuevaQuincena);
	}


	
	/**
	 * Obtiene la fecha de la siguiente quincena a la  
	 * fecha almacenada en un objeto <Code>java.util.Date</Code> 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param date Fecha
	 * @return date Fecha con la siguiente quincena.
	 */
	static public Date getSiguienteQuincena(Date fecha) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);

		int numMes = cal.get(Calendar.MONTH);
		int numDia = cal.get(Calendar.DAY_OF_MONTH);
		int year = cal.get(Calendar.YEAR);

		if (numDia <= 15){
		  numDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		else{
		  numDia = 15;
		  if (numMes == 11){
			numMes = 0;
			year++;
		  }
		  else{
			numMes++;
		  }
		}

		cal.set(year, numMes, numDia);

		return cal.getTime();
	}
	
	/**
	 * Verifica si la primera fecha es mayor que la 
	 * segunda fecha, la fecha esta dada por el año y la quincena. 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param date Fecha
	 * @return boolean True si la fecha es mayor.
	 */
	static public boolean mayorQue(int year, int quincena, int yearC, int quincenaC){
	
		if (year > yearC){
			return true;
		}
		else if (year < yearC){
			return false;
		}
		else {
			if (quincena > quincenaC){
				return true;
			}
			else{
				return false;
			}
		}
	}

	/**
	 * Verifica si la primera fecha es mayor o Igual que la 
	 * segunda fecha, la fecha esta dada por el año y la quincena. 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param date Fecha
	 * @return boolean True si la fecha es mayor.
	 */
	static public boolean mayorIgualQue(int year, int quincena, int yearC, int quincenaC){
	
		if (year > yearC){
			return true;
		}
		else if (year < yearC){
			return false;
		}
		else {
			if (quincena >= quincenaC){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	/**
	 * Verifica si la primera fecha es menor o igual que la 
	 * segunda fecha, la fecha esta dada por el año y la quincena. 
	 *
	 * @author Alberto Isaac Flores Chavarria (aflores) Q
	 * @param date Fecha
	 * @return boolean True si la fecha menor o igual
	 */
	static public boolean menorIgualQue(int year, int quincena, int yearC, int quincenaC){
	
		if (year < yearC){
			return true;
		}
		else if (year > yearC){
			return false;
		}
		else {
			if (quincena <= quincenaC){
				return true;
			}
			else{
				return false;
			}
		}
	}
	
	
	/**
	 * Este metodo compara un valor (que en este caso representa un año
	 * o un mes) con el año o mes actual.
	 * 
	 * @param valor El valor del año/mes a comparar.
	 * @param tipo El tipo del valor a comparar, "A" si el valor
	 * corresponde a un año o "M" si el valor corresponde a un mes.
	 * 
	 * @return <code>boolean</code> <code>true</code> si el valor comparado es igual al
	 * año/mes actual, <code>false</code> en caso contrario.
	 */
	public static boolean comparaFechas(int valor, String tipo) {

		boolean resultado = false;
		Calendar hoy = Calendar.getInstance();

		if (tipo.equals("A")) {
			if (valor == hoy.get(Calendar.YEAR))
				resultado = true;
			else 
				resultado = false;
		} else if (tipo.equals("M")) {
			if ((valor - 1) == hoy.get(Calendar.MONTH))
				resultado = true;
			else
				resultado = false;
		}
		
		return resultado;
	}
	
	/**
	 * Obtiene la diferencia de dos fechas en dias.
	 * (fecha1 - fecha2)
	 * @return long
	 */
	static public long diferenciaDias(Calendar fecha1, Calendar fecha2) {
		Calendar postDate;
		Calendar prevDate;
		int signo = 1;
		long ONE_HOUR = 60 * 60 * 1000L;
		if (fecha1.after(fecha2)) {
			postDate = fecha1;
			prevDate = fecha2;
		} else {
			postDate = fecha2;
			prevDate = fecha1;
			signo = -1;
		}

		// the first getTime() returns a Date, the second takes 
		// that Date object and returns millisecs since 1/1/70. 
		// The API has misleading and horrible naming here, sorry.

		long duration = postDate.getTime().getTime() - prevDate.getTime().getTime();

		// Add one hour in case the duration includes a 
		// 23 hour Daylight Savings spring forward day.

		return (signo * (duration + ONE_HOUR) / (24 * ONE_HOUR));
	}
	
	/**
	 * Este metodo proporciona la diferencia en semanas entre dos fechas.
	 * 
	 * @param fechaInicial La primer fecha a comparar. Esta debe ser 
	 * menor que <code>fechaFinal</code>
	 * @param fechaFinal La segunda fecha a comparar. Esta debe ser 
	 * mayor a <code>fechaInicial</code>
	 * @return <code>int</code>
	 */
	public static int diferenciaSemanas(Date fechaInicial, Date fechaFinal) {
		return calcularDiferenciaFechas(fechaInicial, fechaFinal, "semanas");
	}

	/**
	 * Este metodo proporciona la diferencia en meses entre dos fechas.
	 * 
	 * @param fechaInicial La primer fecha a comparar. Esta debe ser 
	 * menor que <code>fechaFinal</code>
	 * @param fechaFinal La segunda fecha a comparar. Esta debe ser 
	 * mayor a <code>fechaInicial</code>
	 * @return <code>int</code>
	 */	
	public static int diferenciaMeses(Date fechaInicial, Date fechaFinal) {
		return calcularDiferenciaFechas(fechaInicial, fechaFinal, "meses");
	}

	/**
	 * Este metodo proporciona la diferencia en años entre dos fechas.
	 * 
	 * @param fechaInicial La primer fecha a comparar. Esta debe ser 
	 * menor que <code>fechaFinal</code>
	 * @param fechaFinal La segunda fecha a comparar. Esta debe ser 
	 * mayor a <code>fechaInicial</code>
	 * @return <code>int</code>
	 */
	public static int diferenciaAnios(Date fechaInicial, Date fechaFinal) {
		return calcularDiferenciaFechas(fechaInicial, fechaFinal, "anios");
	}

	/**
	 * Este metodo realiza el calculo para conocer la diferencia entre dos fechas.
	 * Es el metodo base para los metodos <code>diferenciaSemanas</code>, 
	 * <code>diferenciaMeses</code> y <code>diferenciaAnios</code>.
	 * 
	 * @param fechaInicial La primer fecha a comparar, esta debe ser menor que
	 * la fecha final.
	 * @param fechaFinal La segunda fecha a comparar, esta debe ser mayor que
	 * la fecha inicial.
	 * @param tipo Para identificar el metodo que invoca a este, los valores 
	 * pueden ser cuatro: "dias", "semanas", "meses" y "anios"
	 */
	protected static int calcularDiferenciaFechas(Date fechaInicial, Date fechaFinal, String tipo) {

		TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");
		Locale locale = new Locale("es", "MX");
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", locale);
		
		int diferencia = 0;

		Date date1 = null;
		Date date2 = null;

		try {
			date1 = df.parse(df.format(fechaInicial));
			date2 = df.parse(df.format(fechaFinal));
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		Calendar cal1 = Calendar.getInstance(tz);
		Calendar cal2 = Calendar.getInstance(tz);

		// different date might have different offset
		cal1.setTime(date1);
		long ldate1 = date1.getTime() + cal1.get(Calendar.ZONE_OFFSET) + cal1.get(Calendar.DST_OFFSET);

		cal2.setTime(date2);
		long ldate2 = date2.getTime() + cal2.get(Calendar.ZONE_OFFSET) + cal2.get(Calendar.DST_OFFSET);

		// Use integer calculation, truncate the decimals
		int hr1 = (int)(ldate1/3600000); //60*60*1000
		int hr2 = (int)(ldate2/3600000);

		int days1 = (int)hr1/24;
		int days2 = (int)hr2/24;

		int dateDiff = days2 - days1;
		int weekOffset = (cal2.get(Calendar.DAY_OF_WEEK) - cal1.get(Calendar.DAY_OF_WEEK))<0 ? 1 : 0;
		int weekDiff = dateDiff/7 + weekOffset;
		int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

		if (tipo.equals("dias"))
			diferencia = dateDiff;

		if (tipo.equals("semanas"))
			diferencia = weekDiff;

		if (tipo.equals("meses"))
			diferencia = monthDiff;

		if (tipo.equals("anios"))
			diferencia = yearDiff;
			
		return diferencia;
	}	

	/**
	 * Este metodo obtiene el mes corriente del año en curso. El
	 * numero que regresa este metodo corresponde almes comenzando 
	 * a contar desde uno. Ej. enero = 1, febrero = 2, etc.
	 *  
	 * @return <code>int</code> El numero del mes corriente.
	 */
	public static int getMesActual() {
		
		Calendar cal = getCalendar();
		return (cal.get(Calendar.MONTH) + 1); 
	}
	
	/**
	 * Este metodo obtiene el numero del año en curso.
	 *  
	 * @return <code>int</code> El numero del año en curso.
	 */
	public static int getAnioActual() {
		
		Calendar cal = getCalendar();
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * Este metodo obtiene el mes que esta contenido en una fecha
	 * que tiene el tipo <code>Date</code>
	 * 
	 * @param fecha La fecha de la cual se quiere conocer el mes.
	 * @return <code>int</code> El mes contenido en la fecha.
	 */
	public static int getMesDeFecha(Date fecha) {

		Calendar cal = getCalendar();
		cal.setTime(fecha);
		return (cal.get(Calendar.MONTH) + 1);		
	}
	
	/**
	 * Este metodo obtiene el año que esta contenido en una fecha que
	 * tiene el tipo <code>Date</code>.
	 *  
	 * @param fecha La fecha de la cual se quiere conocer el año.
	 * @return <code>int</code> El año contenido en la fecha.
	 */
	public static int getAnioDeFecha(Date fecha) {

		Calendar cal = getCalendar();
		cal.setTime(fecha);
		return cal.get(Calendar.YEAR);
	}
	
	/**
	 * Este metodo crea una nueva instancia de la clase abstracta
	 * <code>Calendar</code> con el Locale y el TimeZone definidos
	 * para el idioma Español y el pais Mexico
	 * 
	 * @return <code>Calendar</code> La instancia de la clase Calendar.
	 */
	public static Calendar getCalendar() {
		Locale locale = new Locale("es","MX");
		TimeZone tz = TimeZone.getTimeZone("America/Mexico_City");

		return Calendar.getInstance(tz, locale);
	}
	
	/**
	 * Este metodo arma un objeto tipo <code>Date</code> dados un año
	 * y un mes.
	 * 
	 * @param anio El año con el que se va a armar la fecha.
	 * @param mes El mes con el que se va a armar la fecha.
	 * @return <code>Date</code> El objeto que se construyo con
	 * el mes y año especificados.
	 */
	public static Date getFechaDeAnioMes(int anio, int mes) {
		
		Calendar cal = getCalendar();
		cal.set(anio, (mes - 1), cal.get(Calendar.DATE));
		
		return cal.getTime();
	}
	
	public static Date getFechaDiaMesUltimoDia(int anio, int mes) {
		
		Calendar cal = getCalendar();
		cal.set(anio, (mes - 1), cal.get(Calendar.DAY_OF_MONTH));
		
		return cal.getTime();
	}

	/**
	 * Metodo que regresa un <code>String</code> con formato YYYY-MM-DD
	 * que es como se puede comparar con la base, dado un <code>Date</code>
	 * 
	 * @author Andres Valdez Beristain 
	 * @param d El date que se quiere convertir
	 * 
	 * @return <code>String</code>
	 */
	public static String dateToStringGuion(Date d) {
		String st = dateToStringDB(d); //Con formato "MM/DD/YYYY"
		StringBuffer sb = new StringBuffer();
		
		//append del año
		sb.append(st.substring(st.lastIndexOf("/")+1));
		sb.append("-");
		//append del mes
		sb.append(st.substring(0,st.indexOf("/")));
		sb.append("-");
		//append del dia
		st = st.substring(st.indexOf("/")+1,st.lastIndexOf("/"));
		sb.append(st);
		return sb.toString();
	}
	


	/**
	 * @param string
	 * regresa un Date dado un parametro String del formato YYYY-MM-DD
	 * @return
	 */
	public static Date stringGuionToDate(String string) {
		//YYYY-MM-DD
		StringBuffer sb = new StringBuffer();
		String dia = string.substring(string.lastIndexOf('-')+1);
		String mes = string.substring(string.indexOf('-')+1,string.lastIndexOf('-'));
		String anio = string.substring(0,string.indexOf('-'));
		
		sb.append(dia + "/" + mes + "/" + anio);
		
		return stringToDate(sb.toString());
	}
	
	/**
	 * Recibe el numero de mes comenzando en 1 y regresa el
	 * nombre del mes
	 * @param numMes Numero de mes empezando en 1
	 * @return String nombre del mes
	 */
	public static String getNombreMes(int numMes){
		String mes = "";
		switch(numMes){
			case 1:
				mes = "Enero";
				break;
			case 2:
				mes =  "Febrero";
				break;
			case 3:
				mes =  "Marzo";
				break;
			case 4:
				mes =  "Abril";
				break;
			case 5:
				mes =  "Mayo";
				break;
			case 6:
				mes =  "Junio";
				break;
			case 7:
				mes =  "Julio";
				break;
			case 8:
				mes =  "Agosto";
				break;
			case 9:
				mes =  "Septiembre";
				break;
			case 10:
				mes =  "Octubre";
				break;
			case 11:
				mes =  "Noviembre";
				break;
			case 12:
				mes =  "Diciembre";
				break;
		}
		return mes;
	}
	
	
	//Metodos para calcular semanas segun ETESa
	/**
	 * Obtiene la fecha inicial de la primer semana del año (Primer lunes del año). 
	 * @param anho Año del cual se desea obtener la primer semana.
	 * @return La fecha inicial de la primer semana del año.
	 */
	private static GregorianCalendar getPrimeraSemana(int anho){
		/* Establecer el primer día del año */
		GregorianCalendar primera = new GregorianCalendar(anho, Calendar.JANUARY, 1);
	
		/* Obtener el primer lunes del año */
		primera.add(Calendar.DATE, (9 - primera.get(Calendar.DAY_OF_WEEK)) % 7);
		
		return primera;	
	}

	/**
	 * Obtiene el número de la última semana del año.
	 * @param anho Año del cual se desea obtener la última semana.
	 * @return Última semana del año
	 */	
	private static int getUltimaSemana(int anho){
		/* Obtener la primera semana del año */
		GregorianCalendar fecha = getPrimeraSemana(anho);
		
		/* Obtener la última semana del año */
		return (int)Math.ceil((fecha.getActualMaximum(Calendar.DAY_OF_YEAR) + 1
				- fecha.get(Calendar.DAY_OF_YEAR)) / 7.0);
	}

	/**
	 * Obtiene la fecha de inicio para una semana específica de un año en particular.
	 * @param anho Año en el que se encuentra la semana.
	 * @param semana Semana para la cual se requiere fecha de inicio.
	 * @return La fecha de inicio para una semana específica de un año en particular.
	 * @throws IllegalArgumentException Si no es una semana válida.
	 */
	public static Date getInicioSemana(int anho, int semana) throws IllegalArgumentException {
		/* Obtener la primera semana del año */
		GregorianCalendar fecha = getPrimeraSemana(anho);
				
		/* Obtener la fecha de inicio de la semana deseada */
		if(semana > 0){
			fecha.add(Calendar.DATE, (semana - 1) * 7);
		} else {
			throw new IllegalArgumentException("El número de semana no puede ser negativo o cero.");
		}

		/* La fecha de inicio de la semana debe de ser del mismo año */
		if(fecha.get(Calendar.YEAR) != anho){
			throw new IllegalArgumentException("El número de semana es mayor a la última semana de ese año.");	
		}
		
		return fecha.getTime();
	}

	/**
	 * Obtiene la fecha final de una semana específica de un año en particular.
	 * @param anho Año en el que se encuentra la semana.
	 * @param semana Semana para la cual se requiere fecha final.
	 * @return La fecha final de una semana específica de un año en particular.
	 */
	public static Date getFinalSemana(int anho, int semana){
		/* Establecer el inicio de la semana */
		GregorianCalendar fecha = new GregorianCalendar();
		fecha.setTime(getInicioSemana(anho, semana)); 
			
		/* Establecer el final de la semana */
		fecha.add(Calendar.DATE, 6);
						
		return fecha.getTime();
	}

	/**
	 * Obtiene el año en el cual se considera que está contenida una fecha en particular.
	 * @param fecha Fecha de la cual se requiere obtener el año.
	 * @return El año en el cual se considera que está contenida una fecha en particular.
	 */
	public static int getAnho(Date fecha){
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.setTime(fecha);

		/* Obtener la primera semana del año */
		GregorianCalendar primera = getPrimeraSemana(calendario.get(Calendar.YEAR));
		
		/* Si la fecha es menor a la fecha de inicio de la primera semana considerarla
		 * como parte de la última semana de año anterior */
		if(calendario.get(Calendar.DAY_OF_YEAR) < primera.get(Calendar.DAY_OF_YEAR)){
			return calendario.get(Calendar.YEAR) - 1;
		} else {
			return calendario.get(Calendar.YEAR);
		}
		
	}

	/**
	 * Obtiene la semana en la cual se considera que está contenida una fecha en particular.
	 * @param fecha Fecha de la cual se requiere obtener la semana.
	 * @return La semana en la cual se considera que está contenida una fecha en particular.
	 */
	public static int getSemana(Date fecha){
		GregorianCalendar calendario = new GregorianCalendar();
		calendario.setTime(fecha);

		/*  Revisar si es parte de la última semana del año anterior */
		int anho = getAnho(fecha);
		if(calendario.get(Calendar.YEAR) > anho) {
			return getUltimaSemana(anho);
		} else {
			
			/* Obtener la primera semana del año */
			GregorianCalendar primera = getPrimeraSemana(anho);

			/* Obtener la semana */
			return (int)Math.ceil((calendario.get(Calendar.DAY_OF_YEAR) + 1 
					- primera.get(Calendar.DAY_OF_YEAR)) / 7.0);									
		}		
	}
	/**
	 * Convierte una fecha tipo Date a tipo Calendar
	 * @param fecha Fecha a convertir
	 * @return Fecha en tipo Calendar
	 */
	public static Calendar getFecha(String fecha){
		Date fech=Fecha.stringToDate(fecha);
		Calendar cal = Calendar.getInstance();
				cal.setTime(fech);
				int numMes1 = cal.get(Calendar.MONTH);
				int numDia1 = cal.get(Calendar.DAY_OF_MONTH);
				int year1 = cal.get(Calendar.YEAR);
		return cal;
	}	
	
	/**
		 * Obtiene la fecha de la quincena anterior a la  
		 * fecha almacenada en un objeto <Code>java.util.Date</Code> 
		 *
		 * @author Alberto Isaac Flores Chavarria (aflores) Q
		 * @param date Fecha
		 * @return date Fecha con la siguiente quincena.
		 */
		static public Date getAnteriorQuincena(Date fecha) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(fecha);

			int numMes = cal.get(Calendar.MONTH);
			int numDia = cal.get(Calendar.DAY_OF_MONTH);
			int year = cal.get(Calendar.YEAR);
			if (numDia >= 15){
			numDia = 15;
			}else{
			  if (numMes == 0){
				   numMes = 11;
				   year--;
			   }else if(numMes != 0){
			   	numMes--;
			   }
			   cal.set(Calendar.MONTH,numMes);
			   numDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			  
			}
			cal.set(year, numMes, numDia);

			return cal.getTime();
		}
		
	/**	
	 * Regresa una cadena donde concatena el año 
	 * y el numero de quincena en el año para todas las quincenas en el trimestre solicitado
	 * separdas por comas
	 * 
	 * @author asantos
	 * @param int anio
	 * @param int trimestre
	 * @return String quincenasAnioTrimestre
	 */
	
	public static String getQuincenasAnioTrimestre(int anio, int trimestre) throws IllegalArgumentException {
		
		StringBuffer quincenasAnioTrimestre = new StringBuffer();
		
		/*valida el numero de año*/
		if (anio < 1000 || anio >2500 ) {
			throw new IllegalArgumentException("El número de año no es valido");
		}
		
		StringTokenizer quincenasTrimestre = new StringTokenizer(getQuincenasTrimestre(trimestre),",");
		while(quincenasTrimestre.hasMoreElements()){
			quincenasAnioTrimestre.append(anio);
			quincenasAnioTrimestre.append(quincenasTrimestre.nextElement());
			quincenasAnioTrimestre.append(quincenasTrimestre.hasMoreTokens()? "," : "");			
		}		
		
		return quincenasAnioTrimestre.toString();
	}
	
	/**	
	 * Regresa una cadena con los numeros de quincena en un año 
	 * dentro del trimestre solicitado y separados por comas
	 * 
	 * @author asantos
	 * @param int trimestre
	 * @return String quincenasTrimestre
	 */
	
	public static String getQuincenasTrimestre(int trimestre) throws IllegalArgumentException {
		
		StringBuffer quincenasTrimestre = new StringBuffer();
		//utilizado para obtener el numero de quincena en el trimestre
		//6 quincenas por trimestre
		int incremento = trimestre -1;
		int quincena = 0;
				
		/* valida el numero de trimestre */
		if(trimestre > 0 && trimestre <= 4){
			for (int i = 1; i <= 6; i++){
				quincena = i + (6 * incremento);
				//Agrega un cero a la izquierda no afecta en queries de comparacion con enteros
				if(quincena < 10){
					quincenasTrimestre.append("0");
					quincenasTrimestre.append(quincena);									
				}
				else{
					quincenasTrimestre.append(quincena);					 
				}
				//Agrega una como de separacion
				quincenasTrimestre.append( i<6 ? "," : "");
			}
		} else {
			throw new IllegalArgumentException("El número de trimestre solo puede ser 1, 2, 3 o 4.");
		}
		
		return quincenasTrimestre.toString();
	}
	
	
	/**	
	 * Regresa un Calendar con la fecha obtenida de sumar los dias del segundo parametro a la fecha
	 * del primero.
	 * @author avaldez
	 * @param Date fecha
	 * @param int dias
	 * @return String quincenasTrimestre
	 */
	public static Calendar addDaysToDate(Date fecha, int dias){
		long horas = 60 * 60 * 1000;
		long totalDias = dias * 24 * horas;
		long fechaInicial = fecha.getTime();
		
		fecha.setTime(fechaInicial + totalDias);
		Calendar nuevaFecha = Calendar.getInstance();
		nuevaFecha.setTime(fecha);
		return nuevaFecha;
	}
	
	/**	
	 * Regresa un boolean true si las dos fechas de los parametros se refieren al mismo dia.
	 * No toma en cuenta la hora, simplemente si es el mismo dia regresa true
	 * @author avaldez
	 * @param fecha1 Primer fecha a comparar
	 * @param fecha2 Segunda fecha a comparar
	 * @return boolean True si es el mismo dia
	 */
	public static boolean isTheSameDay(Calendar fecha1, Calendar fecha2){
		return diferenciaDias(fecha1,fecha2)==0;		
	}
	
	public static String getHoraActual(String fechaInicio) throws IllegalArgumentException {
		Calendar calendar = Calendar.getInstance();
		fechaInicio = fechaInicio + " ";
		if(calendar.get(Calendar.HOUR_OF_DAY) < 10)
			fechaInicio = fechaInicio + "0" + calendar.get(Calendar.HOUR_OF_DAY);
		else				
			fechaInicio = fechaInicio + calendar.get(Calendar.HOUR_OF_DAY);
 
		 if(calendar.get(Calendar.MINUTE) < 10)
			fechaInicio = fechaInicio + ":0" + calendar.get(Calendar.MINUTE);
		 else
			fechaInicio = fechaInicio + ":" + calendar.get(Calendar.MINUTE);

		if(calendar.get(Calendar.SECOND) < 10)	
			fechaInicio = fechaInicio + ":0" + calendar.get(Calendar.SECOND);
		else
			fechaInicio = fechaInicio + ":" + calendar.get(Calendar.SECOND);
			
		return fechaInicio;
	}
	
	/**
	 * Metodo que regresa un <code>String</code> con formato YYYY-MM-DD
	 * que es como se puede comparar con la base, dado un <code>Date</code>
	 * 
	 * @author Alejandro Ojeda Hernandez
	 * @param d El date que se quiere convertir
	 * 
	 * @return <code>String</code>
	 */
	public static String obtenerFechaDDMMYYYY(String fecha) {
		String st = fecha; //Con formato "DD/MM/YYYY"
		StringBuffer sb = new StringBuffer();

		//append del dia
		sb.append(st.substring(0,2));
		//append del mes
		sb.append(st.substring(3,5));
		//append del anio		
		sb.append(st.substring(6,10));
		
		return sb.toString();
	}
	
}


