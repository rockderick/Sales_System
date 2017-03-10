/*
 * Created on May 2, 2005
 *
 */
package mx.com.iusacell.catalogo.utilerias;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import javax.servlet.ServletOutputStream;


/**
 * @author rsalinasg
 *
 */
public class ExcelDoc {

	private HSSFWorkbook libro;
	
	private ArrayList datos;
	private ArrayList encabezado;
	private ArrayList metodo;
	
	private String tituloHoja;
	
//	public static void main(String[] args) throws IOException {
//
//		ArrayList datos = new ArrayList();
//		datos.add(new PruebaVO());
//		datos.add(new PruebaVO());
//		datos.add(new PruebaVO());
//		datos.add(new PruebaVO());
//
//		ArrayList encabezado = new ArrayList();
//		encabezado.add("nombre");
//		encabezado.add("apellido paterno");
//		encabezado.add("apellido materno");
//		encabezado.add("variable entera");
//		encabezado.add("variable double");
//		encabezado.add("variable float");
//		encabezado.add("variable fecha");
//
//		ArrayList metodo = new ArrayList();
//		metodo.add("getNombre");
//		metodo.add("getApepat");
//		metodo.add("getApemat");
//		metodo.add("getI");
//		metodo.add("getJ");
//		metodo.add("getK");
//		metodo.add("getHoy");
//
//		//este metodo recibe recibe los siguientes parametros
//		HSSFWorkbook libro =(HSSFWorkbook) obtieneMetodos("Prueba", encabezado, metodo, datos);
//		
//		FileOutputStream fileOut = new FileOutputStream("workbook.xls");
//		libro.write(fileOut);
//		fileOut.close();
//	}


	public ExcelDoc(){
		this.datos = new ArrayList();
		this.encabezado = new ArrayList();
		this.metodo = new ArrayList();
		this.libro = new HSSFWorkbook();
	}
	
	public ExcelDoc(String nombreHoja, 
	                ArrayList Encabezado, 
	                ArrayList Metodos,
					ArrayList DatosReporte){
		this.datos = DatosReporte;
		this.encabezado = Encabezado;
		this.metodo = Metodos;
		this.tituloHoja = nombreHoja;
		
		this.libro = new HSSFWorkbook();
		obtieneMetodos(tituloHoja,encabezado, metodo,datos);
	}
	
	public ExcelDoc(String nombreHoja, 
					ArrayList Encabezado, 
					ArrayList Metodos,
					HashMap DatosReporte,
					String[] llaves){
		
		this.datos = new ArrayList();
		this.encabezado = Encabezado;
		this.metodo = Metodos;
		this.tituloHoja = nombreHoja;
	
		this.libro = new HSSFWorkbook();
		obtieneMetodos(tituloHoja,encabezado, metodo,DatosReporte, llaves);
	}
	
	/** este metodo recibe los siguientes datos
	 * 
	 * @param nombreHoja Nombre de la hoja
	 * @param Encabezado la lista de encabezados de la hoja
	 * @param Metodos nombre de los metodos
	 * @param DatosReporte datos del reporte
	 * @return
	 */
	public void obtieneMetodos(String nombreHoja, ArrayList Encabezado, ArrayList Metodos,
		ArrayList DatosReporte) {
		Class parameterClass;
		Method method = null;
		Object objeto = null;
		Object resul = null;
		//creo el libro
		HSSFWorkbook wb = this.libro;
		
		HSSFSheet sheet = wb.createSheet(nombreHoja);
	
		//create 3 cell styles
		HSSFCellStyle cs = wb.createCellStyle();
		HSSFDataFormat df = wb.createDataFormat();
		//create 2 fonts objects
		HSSFFont f = wb.createFont();
		//set font 1 to 12 point type
		f.setFontHeightInPoints((short) 10);
	
		//make it blue
		f.setColor((short) 0xc);
	
		//make it bold
		//arial is the default font
		f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		f.setColor((short)HSSFFont.COLOR_RED);

		//set cell stlye
		cs.setFont(f);
		//set the cell format 
		cs.setDataFormat(df.getFormat("#,##0.0"));

		
		//set the sheet name in Unicode
		//wb.setSheetName(0, "\u0422\u0435\u0441\u0442\u043E\u0432\u0430\u044F "
				  //+ "\u0421\u0442\u0440\u0430\u043D\u0438\u0447\u043A\u0430", HSSFWorkbook.ENCODING_UTF_16);
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// Creo la fila para el encabezado
		HSSFRow row1 = sheet.createRow((short) 0);
		HSSFCell c = null;
		for (int i = 0; i < Encabezado.size(); i++) {
			//row1.createCell((short) (i + 1)).setCellValue((String) Encabezado.get(i));
			c = row1.createCell((short)(i));
			sheet.setColumnWidth((short) (i), (short) ((50 * 8) / ((double) 1 / 20)));
			c.setCellStyle(cs);
			c.setEncoding(HSSFCell.ENCODING_COMPRESSED_UNICODE);
			c.setCellValue((String)Encabezado.get(i));
			c.setCellStyle(cellStyle);
		}
		try {
			for (int i = 0; i < DatosReporte.size(); i++) {
				Object obj = (Object) DatosReporte.get(i);
				Class obj1 = obj.getClass();
				//Crear celda para cada registro
			
				HSSFRow celda = sheet.createRow((short) i + 1);
				HSSFCell col = null;
				for (int j = 0; j < Metodos.size(); j++) {
					//method = obj1.getDeclaredMethod((String) Metodos.get(j), null);
					try {
						method = obj1.getDeclaredMethod(asignaNombre((String)Metodos.get(j)), null);
					} catch (Exception e) {

						method = obj1.getSuperclass().getDeclaredMethod(asignaNombre((String)Metodos.get(j)), null);
					}

					col = celda.createCell((short) (j));
					col.setCellStyle(cellStyle);
					if (method.getReturnType() == String.class) {
						col.setCellValue((String) method.invoke(obj, null));
						
					} 
					else if (method.getReturnType() == Integer.class || method.getReturnType() == int.class) {
						col.setCellValue(((Integer) method.invoke(obj, null)).doubleValue());
					} 
					else if (method.getReturnType() == Float.class || method.getReturnType() == float.class) {
						col.setCellValue(((Float) method.invoke(obj, null)).doubleValue());
					} 
					else if (method.getReturnType() == Double.class || method.getReturnType() == double.class) {
						col.setCellValue(((Double) method.invoke(obj, null)).doubleValue());
					}
					else if (method.getReturnType() == Date.class) {
						col.setCellValue(Fecha.dateToString((Date) method.invoke(obj, null)));
					}
				}
				
			}
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (IllegalArgumentException iae1) {
			iae1.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return wb;
	}

	/** este metodo recibe los siguientes datos
	 * 
	 * @param nombreHoja Nombre de la hoja
	 * @param Encabezado la lista de encabezados de la hoja
	 * @param Metodos nombre de los metodos
	 * @param DatosReporte datos del reporte
	 * @return
	 */
	public void obtieneMetodos(String nombreHoja,
	                                          ArrayList Encabezado, 
	                                          ArrayList Metodos,
	                                          HashMap MapaDatosReporte,
	                                          String[] llaves) {
		Class parameterClass;
		Method method = null;
		Object objeto = null;
		Object resul = null;
		//creo el libro
		HSSFWorkbook wb = this.libro;
		HSSFSheet sheet = wb.createSheet(nombreHoja);
	
		//create 3 cell styles
		HSSFDataFormat df = wb.createDataFormat();
		//								create 2 fonts objects
		HSSFFont headerFont = wb.createFont();
		headerFont.setFontHeightInPoints((short) 10); 		// set font 1 to 12 point type
		headerFont.setColor(HSSFColor.WHITE.index);

		HSSFFont titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 10);
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		titleFont.setColor(HSSFColor.BLACK.index); 

		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(df.getFormat("General"));
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle moneyStyle = wb.createCellStyle();
		moneyStyle.setDataFormat(df.getFormat("($#,##0.00_);[Red]($#,##0.00)"));
		moneyStyle.setAlignment(HSSFCellStyle.ALIGN_RIGHT);

		HSSFCellStyle dateStyle = wb.createCellStyle();
		dateStyle.setDataFormat(df.getFormat("m/d/yy"));
		dateStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCellStyle headerStyle = wb.createCellStyle();
		headerStyle.setFont(headerFont);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND );
		headerStyle.setFillForegroundColor(HSSFColor.DARK_RED.index);
		headerStyle.setFillBackgroundColor(HSSFColor.DARK_RED.index);

		HSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_GENERAL);
		titleStyle.setFont(titleFont);

		try {
			//Set llaves=MapaDatosReporte.keySet();
			//Iterator itera = llaves.iterator();
			short renglonNum = 0; // controla el renglon de la hoja
			for(int k=0;k < llaves.length; k++){
				String grupo = llaves[k]; // obtener la llave del Mapa que sera el titulo del grupo en la hoja
				
				ArrayList DatosReporte = (ArrayList) MapaDatosReporte.get(grupo); //Obtener los datos en ese grupo
				
				HSSFRow renglon = sheet.createRow((short) renglonNum);
				renglonNum++;
				
				HSSFCell myCell = renglon.createCell((short)0);
				myCell.setCellStyle(titleStyle);
				myCell.setEncoding(HSSFCell.ENCODING_COMPRESSED_UNICODE);
				myCell.setCellValue(grupo);

				// Creo la fila para el encabezado
				HSSFRow row1 = sheet.createRow((short) renglonNum);
				renglonNum++;
				
				HSSFCell c = null;
				for (int i = 0; i < Encabezado.size(); i++) {
					c = row1.createCell((short)(i));
					sheet.setColumnWidth((short) (i), (short) ((50 * 8) / ((double) 1 / 20)));
					c.setCellStyle(headerStyle);
					c.setEncoding(HSSFCell.ENCODING_COMPRESSED_UNICODE);
					c.setCellValue((String)Encabezado.get(i));
				}
			
				for (int i = 0; i < DatosReporte.size(); i++) {
					Object obj = (Object) DatosReporte.get(i);
					Class obj1 = obj.getClass();
					//Crear celda para cada registro
				
					HSSFRow celda = sheet.createRow((short) renglonNum);
					renglonNum++;
					
					HSSFCell col = null;
					for (int j = 0; j < Metodos.size(); j++) {
						
						//method = obj1.getDeclaredMethod((String) Metodos.get(j), null);
						try {
							method = obj1.getDeclaredMethod(asignaNombre((String)Metodos.get(j)), null);
						} catch(Exception e) {
							method = obj1.getSuperclass().getDeclaredMethod(asignaNombre((String)Metodos.get(j)), null);
						}
						
						col = celda.createCell((short) (j));
						if (method.getReturnType() == String.class) {
							col.setCellStyle(cellStyle);
							col.setCellValue((String) method.invoke(obj, null));
						} 
						else if (method.getReturnType() == Integer.class || method.getReturnType() == int.class) {
							col.setCellStyle(cellStyle);
							col.setCellValue(((Integer) method.invoke(obj, null)).doubleValue());
						} 
						else if (method.getReturnType() == Float.class || method.getReturnType() == float.class) {
							col.setCellStyle(moneyStyle);
							col.setCellValue(((Float) method.invoke(obj, null)).doubleValue());
						} 
						else if (method.getReturnType() == Double.class || method.getReturnType() == double.class) {
							col.setCellStyle(moneyStyle);
							col.setCellValue(((Double) method.invoke(obj, null)).doubleValue());
						}
						else if (method.getReturnType() == Date.class) {
							col.setCellStyle(dateStyle);
							col.setCellValue(Fecha.dateToString((Date) method.invoke(obj, null)));
						}
					}
				}
			}
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		} catch (IllegalArgumentException iae1) {
			iae1.printStackTrace();
		} catch (InvocationTargetException ite) {
			ite.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return wb;
	}



	public void escribirArchivo(FileOutputStream fileOut)
		throws FileNotFoundException, Exception{
		try{
			//FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
			this.libro.write(fileOut);
			//fileOut.close();
		}catch(FileNotFoundException fnfe){
			throw fnfe;	
		}catch(Exception e){
			throw e;		
		}
	}


	public void escribirArchivo(ServletOutputStream out)
		throws IOException, Exception{
		try{
			this.libro.write(out);
			//out.close();
		}catch(IOException ioe){
			throw ioe;	
		}catch(Exception e){
			throw e;		
		}
	}

	public void escribirDisco(FileOutputStream out)
		throws IOException, Exception{
		try{
			this.libro.write(out);
			//out.close();
		}catch(IOException ioe){
			throw ioe;	
		}catch(Exception e){
			throw e;		
		}
	}
	/**
	 * @return
	 */
	public HSSFWorkbook getLibro() {
		return libro;
	}

	/**
	 * @param workbook
	 */
	public void setLibro(HSSFWorkbook workbook) {
		libro = workbook;
	}

	/**
	 * @return
	 */
	public ArrayList getDatos() {
		return datos;
	}

	/**
	 * @return
	 */
	public ArrayList getEncabezado() {
		return encabezado;
	}

	/**
	 * @return
	 */
	public ArrayList getMetodo() {
		return metodo;
	}

	/**
	 * @return
	 */
	public String getTituloHoja() {
		return tituloHoja;
	}

	/**
	 * @param list
	 */
	public void setDatos(ArrayList list) {
		datos = list;
	}

	/**
	 * @param list
	 */
	public void setEncabezado(ArrayList list) {
		encabezado = list;
	}

	/**
	 * @param list
	 */
	public void setMetodo(ArrayList list) {
		metodo = list;
	}

	/**
	 * @param string
	 */
	public void setTituloHoja(String string) {
		tituloHoja = string;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumHojas(){
		return this.libro.getNumberOfSheets(); 
	}
	/**Este metodo returna el nombre del metodo que sera utilizad
	 * para realizar el reflection
	 * @param nombre
	 * @return nombre metodo
	 */
	private static String asignaNombre(String nombre) {
		
			return "get"+ Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1, nombre.length());
			
	}
}
