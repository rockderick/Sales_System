/*
 * Created on 15/12/2004
 *
 */
package mx.com.iusacell.catalogo.utilerias;


import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author avaldez
 */
public class ReportDate {
	private static SimpleDateFormat dateFormat;
	private static FieldPosition fieldPosition;
	
	private static SimpleDateFormat dateFormat2;
	private static FieldPosition fieldPosition2;
	
	static {
		dateFormat = new SimpleDateFormat("EEEE, d MMMM yyyy HH:mm:ss",new Locale("es","MX"));
		fieldPosition = new FieldPosition(SimpleDateFormat.DEFAULT);

		dateFormat2 = new SimpleDateFormat("d/MM/yyyy HH:mm",new Locale("es","MX"));
		fieldPosition2 = new FieldPosition(SimpleDateFormat.DEFAULT);
	}
	
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd",new Locale("es","MX"));
		
		Date fecha = df.parse("2003/09/02",new ParsePosition(0));
	}
	public String toString(){
		StringBuffer result = new StringBuffer();	
		Date date = new Date();
		dateFormat.format(date, result, fieldPosition);
		return result.toString().toUpperCase();
	}
	public static String getDateFormat2(Date date) {
		StringBuffer result = new StringBuffer();	
		dateFormat2.format(date, result, fieldPosition2);
		return result.toString().toUpperCase();
	}
}

