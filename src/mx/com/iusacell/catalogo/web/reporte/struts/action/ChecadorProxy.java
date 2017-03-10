/*
 * Creado el Jun 1, 2007
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.web.reporte.struts.action;

import mx.com.iusacell.catalogo.bd.ProveedorConexion;
import mx.com.iusacell.catalogo.dao.PersonalFacade;
import mx.com.iusacell.catalogo.modelo.ChecadorVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import mx.com.iusacell.catalogo.utilerias.Fecha;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;


import org.apache.log4j.Logger;

/**
 * @author jojedah
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class ChecadorProxy {

	protected static final Logger logger = Logger.getLogger(ChecadorProxy.class);
	
	
	public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
			
		return mapping.findForward("home");
	}
	
	public ActionForward reporte(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		String secuencia = null;
		String fechaInicial = null;
		String fechaFinal = null;
		ArrayList reporteChecador = null;
		UserSessionVO user = null;
		int pcCveRol = 0;
		int pcCveDiv = 0;
		
		fechaInicial = Fecha.obtenerFechaDDMMYYYY(request.getParameter("fechaInicial"));
		fechaFinal = Fecha.obtenerFechaDDMMYYYY(request.getParameter("fechaFinal"));
		
		
		user = (UserSessionVO) request.getSession().getAttribute("USER");
		
		pcCveRol = user.getPcCveRol();
		pcCveDiv = user.getPcCveDiv();
		
		if(pcCveRol == 2 || pcCveRol == 4){
			secuencia = crearReporte(fechaInicial,fechaFinal,String.valueOf(pcCveDiv));
			
		}else if (pcCveRol == 1) {
			pcCveDiv = user.getPcCveSubdiv();
			secuencia = crearReporte(fechaInicial,fechaFinal,"99");
		}
		
	
		
		
		reporteChecador = obtenerReporte(secuencia);
		
		request.getSession().setAttribute("reporteChecador",reporteChecador);
		eliminarReporte(secuencia);
			
			
		return mapping.findForward("home");
	}
	
	public void exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		
		exportarExcel(mapping, form, request, response);
		
		//return mapping.findForward("home");
	}
	
	public static String crearReporte(String fechaInicial, String fechaFinal, String usuario) {
		Connection conn = null;
		CallableStatement cs = null;
		String secuencia = null;
		ResultSet rs = null;
		
		
		try {
			
			conn = ProveedorConexion.getConnection();
			cs = conn.prepareCall("{ ? = call reporte_checador (?,?,?) }");
			
			//registramos el tipo de dato que retornará la función
			cs.registerOutParameter(1, java.sql.Types.VARCHAR);

			//seteamos los parametros
			cs.setString(2, fechaInicial);
			cs.setString(3, fechaFinal);
			cs.setString(4, usuario);
			
			
			//ejecutamos la funcion
			 cs.executeUpdate();
			
			//obtenemos la secuencia generada
			secuencia = cs.getString(1);
			
			
		} catch (Exception exc) {
			exc.printStackTrace();
			
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (conn != null) {
					conn.close();
					cs.close();
				}
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
		
		return secuencia;

	}
	
	public static ArrayList obtenerReporte(String secuencia) {
		
		ArrayList reporteChecador = null;
		
		reporteChecador = PersonalFacade.obtenerReporte(secuencia);

		return  reporteChecador;

	}
	
	public static void eliminarReporte(String secuencia) {
		
		PersonalFacade.eliminarReporte(secuencia);
		
	}
	
	public static void exportarExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("application/vnd.ms-excel");
		ServletOutputStream out = response.getOutputStream();
			
		ArrayList reporteChecador = (ArrayList)request.getSession().getAttribute("reporteChecador");	
		
		ArrayList titulos = new  ArrayList();
		titulos.add("FECHA REGISTRO");
		titulos.add("CLAVE SAEO");
		titulos.add("NUM EMP");
		titulos.add("PERSONA");
		titulos.add("PUESTO");
		titulos.add("DESCANSO");
		titulos.add("JUSTIFICACION DESCANSO");
		titulos.add("FALTA");
		titulos.add("JUSTIFICACION FALTA");
		titulos.add("ENTRADA");
		titulos.add("HORARIO ENTRADA");
		titulos.add("JUSTIFICACION ENTRADA");
		titulos.add("SALIDA A COMER");
		titulos.add("HORARIO SALIDA A COMER");
		titulos.add("JUSTIFICACION SALIDA A COMER");
		titulos.add("ENTRADA DE COMER");
		titulos.add("HORARIO ENTRADA DE COMER");
		titulos.add("JUSTIFICACION ENTRADA DE COMER");
		titulos.add("SALIDA");
		titulos.add("HORARIO SALIDA");
		titulos.add("JUSTIFICACION SALIDA");
		titulos.add("TIENDA");
		titulos.add("DIVISION");
		
		short rownum;

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet s = wb.createSheet();
		HSSFRow r = null;
		HSSFCell c = null;

		HSSFCellStyle cs = wb.createCellStyle();
		HSSFCellStyle cs2 = wb.createCellStyle();
		HSSFCellStyle cs3 = wb.createCellStyle();
		HSSFDataFormat df = wb.createDataFormat();

		HSSFFont f = wb.createFont();
		HSSFFont f2 = wb.createFont();
		
		f.setFontHeightInPoints((short) 10);
		f.setColor( (short)0xc );
		f.setBoldweight((short) 20);
		f2.setFontHeightInPoints((short) 12);
		f2.setColor( (short)HSSFFont.COLOR_RED );
		f2.setBoldweight((short) 20);
		cs.setFont(f);
		cs2.setFont(f2);

		wb.setSheetName(0, "Reporte Checador", 
						HSSFWorkbook.ENCODING_UTF_16 );

			//GENERAR LOS TITULOS
				
			r = s.createRow(0);
			for(int i = 0; i< titulos.size(); i++)
			{
				if (i == 4 ){	
					s.setColumnWidth((short) i, (short) ((50 * 18) / ((double) 1 / 20)));
				} else if (i == 3){
					s.setColumnWidth((short) i, (short) ((50 * 8) / ((double) 1 / 20)));
				}
				else {
				s.setColumnWidth((short) i, (short) ((50 * 6) / ((double) 1 / 20)));
				}	
				String titulo = (String) titulos.get(i);
			
				c = r.createCell((short) (i));
				c.setCellStyle(cs2);
				c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
				c.setCellValue( titulo);
			}
						
				

				//CREAR LOS REGISTROS DE INFORMACION
				for (rownum = (short) 0; rownum < reporteChecador.size(); rownum++)
				{
					ChecadorVO checador= (ChecadorVO)reporteChecador.get(rownum);
					// create a row
					r = s.createRow(rownum+1);

							
					c = r.createCell((short) (0));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getFechaRegistro());								

					c = r.createCell((short) (1));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcCveVendedor());
								
					c = r.createCell((short) (2));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcCveEmpRef());								

					c = r.createCell((short) (3));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcNomVendedor());

					c = r.createCell((short) (4));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcDescPuesto());
									
					c = r.createCell((short) (5));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcFechaCero());									

					c = r.createCell((short) (6));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcTipoestatusCero());
									
					c = r.createCell((short) (7));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcFechaCinco());
							
					c = r.createCell((short) (8));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcTipoestatusCinco());
									
					c = r.createCell((short) (9));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcFechaUno());
							
					c = r.createCell((short) (10));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcHoraUno());
								
					c = r.createCell((short) (11));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcTipoestatusUno());

					c = r.createCell((short) (12));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcFechaDos());

					c = r.createCell((short) (13));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcHoraDos());
					
					c = r.createCell((short) (14));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcTipoestatusDos());
					
					c = r.createCell((short) (15));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcFechaTres());
					
					c = r.createCell((short) (16));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcHoraTres());
										
					c = r.createCell((short) (17));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcTipoestatusTres());
					
					c = r.createCell((short) (18));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcFechaCuatro());
					
					c = r.createCell((short) (19));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcHoraCuatro());
										
					c = r.createCell((short) (20));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcTipoestatusCuatro());	
					
					c = r.createCell((short) (21));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcNomPtoventas());	
					
					c = r.createCell((short) (22));
					c.setCellStyle(cs);
					c.setEncoding( HSSFCell.ENCODING_COMPRESSED_UNICODE );
					c.setCellValue( checador.getPcDescDiv());							
																																																																				
				}
					

			wb.write(out);
			out.close();
			
		
		
	}		
}
