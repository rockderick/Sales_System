/*
 * Creado el 4/05/2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.naming.NamingException;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import mx.com.iusacell.catalogo.bd.ProveedorConexion;
import mx.com.iusacell.catalogo.utilerias.Constante;
import mx.com.iusacell.webservice.modelo.Director;
import mx.com.iusacell.webservice.modelo.Empleado;
import mx.com.iusacell.webservice.modelo.LogInfoVo;
import mx.com.iusacell.webservice.modelo.Sistemas;
import mx.com.iusacell.webservice.modelo.Division;

/**
 * Clase con metodos para uso de base de datos de la estructura organizacional
 * 
 * @author gsalazar
 */

public class EstructuraOrganizacionalDAO {
	
	private final int HUELLA_REGISTRADA = 1;
	private final int HUELLA_DESHABILITADA = 5;
	//Log log = new Log( Logger.getLogger(EstructuraOrganizacionalDAO.class) , "EstructuraOrganizacionalDAO.class" , "EstructuraOrganizacional.log", true, Level.ALL  );
	private static Logger log = Logger.getLogger(EstructuraOrganizacionalDAO.class);
			
	/**
	 * Recupera los datos de un usuario, realiza un mapeo de la tabla PC_MAPEO_VENDEDOR a
	 * PC_VENDEDORES para obtener el idUnico a traves del login y el sistema
	 * 
	 * @param loginUsuario login utilizado para accesar a un sistema
	 * @param idSistema identificador del sistema que esta realizando el login
	 * @return Empleado
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 * 
	 */
	public Empleado getIdUnicoUsuario(String loginUsuario, String idSistema)
		throws ServiceException, SQLException, NamingException {
		
		Empleado empleado = new Empleado();
		String sql = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = ProveedorConexion.getConnection();
			//log.info("loginUsuario = " + loginUsuario);
			//log.info("idSistema = " + idSistema);

			st = conn.createStatement();
				sql =   " select PC_VENDEDORES.PC_CVE_VENDEDOR, PC_VENDEDORES.PC_ID_UNICO, PC_CVE_SUPERIOR ,"
						+ "      PC_NOM_VENDEDOR||' '||PC_APE_PATERNO||' '||PC_APE_MATERNO PC_NOMBRE_USUARIO,"
						+ "      PC_ESTATUS_HUELLA, PC_PUESTOS.PC_CVE_PUESTO, PC_DESC_PUESTO"
						+ " from PC_VENDEDORES, PC_MAPEO_VENDEDOR, PC_PUESTOS "
						+ "where PC_CVE_REG_VENTA='"+ loginUsuario	+ "'" + //LoginUsuario
						   " and PC_SISTEMA = '" + idSistema + "'" + //Id del Sistema
						   " and PC_VENDEDORES.PC_CVE_VENDEDOR = pc_mapeo_vendedor.PC_CVE_VENDEDOR"
						 + " and PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO(+)";

			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				//empleado.idUnico = rs.getString("PC_ID_UNICO");
				empleado.idUnico = rs.getString("PC_CVE_VENDEDOR"); // gsv 28/07/2005 cve_vendedor
				empleado.idPadre = rs.getString("PC_CVE_SUPERIOR");
				empleado.nombreCompleto = rs.getString("PC_NOMBRE_USUARIO");
				empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
				empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
				empleado.estatusHuella=rs.getInt("PC_ESTATUS_HUELLA");				
				empleado.sistemasAsignados = getSistemasAsignados(rs.getString("PC_CVE_VENDEDOR"), conn);
				log.info("WS: Consulta Exitosa");
			}

			conn.close();
		} catch (Exception e) {
			log.error(e.toString());
			//e.printStackTrace();
			throw new ServiceException( "EstructuraOrganizacionalDAO : Error en getIdUnicoUsuario() " + e.getMessage(), e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return empleado;
	}

	/**
	 * Obtiene datos del empleado de la tabla PC_VENDEDORES
	 * 
	 * @param idUnicoUsuario id unico asignado al usuario
	 * @return Empleado
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 * 
	 */
	public Empleado getIdUnicoUsuario(String idUnicoUsuario)
		throws ServiceException, SQLException, NamingException {
		
		Empleado empleado = new Empleado();
		String sql = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = ProveedorConexion.getConnection();
			//log.info("EstructuraOrganizacionalDAO.getIdUnicoUsuario(idUnicoUsuario)\n ");
			//log.info("idUnicoUsuario = " + idUnicoUsuario);			

			st = conn.createStatement();
				sql =
					"	  select PC_VENDEDORES.PC_CVE_VENDEDOR ,PC_VENDEDORES.PC_ID_UNICO, PC_CVE_SUPERIOR ,"
						+ " PC_NOM_VENDEDOR|| ' ' ||PC_APE_PATERNO|| ' ' ||PC_APE_MATERNO PC_NOMBRE_USUARIO,"
						+ " PC_ESTATUS_HUELLA, PC_PUESTOS.PC_CVE_PUESTO, PC_DESC_PUESTO"
						+ " from PC_VENDEDORES, PC_PUESTOS"
						+ " where PC_VENDEDORES.PC_CVE_VENDEDOR="+ idUnicoUsuario
						+ " and PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO(+)";

			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				empleado.idUnico = rs.getString("PC_CVE_VENDEDOR"); // gsv 28/07/2005 cve_vendedor
				empleado.idPadre = rs.getString("PC_CVE_SUPERIOR");
				empleado.nombreCompleto = rs.getString("PC_NOMBRE_USUARIO");
				empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
				empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
				empleado.estatusHuella=rs.getInt("PC_ESTATUS_HUELLA");
				//Se obtienen los sistemas asignados al usuario con la clave del vendedor				
				empleado.sistemasAsignados = getSistemasAsignados(rs.getString("PC_CVE_VENDEDOR"), conn);
				log.info("WS: Consulta Exitosa");
			}
			conn.close();
		} catch (Exception e) {			
			log.error(e.toString());
			//e.printStackTrace();
			throw new ServiceException( "EstructuraOrganizacionalDAO : Error en getIdUnicoUsuario(idUnicoUsuario) " + e.getMessage(), e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return empleado;
	}


	/**
	 * Obtiene la linea de mando determinada por el parametro nivel del usuario proporcionado
	 * internamente obtiene el campo PC_CVE_VENDEDOR para hacer la busqueda
	 *  
	 * @param idUsuario id unico asignado al usuario
	 * @param nivel nivel de la linea de mando
	 * @return ArrayList de objetos Empleado
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 * 
	 */
	public Vector getLineaMando(String idUsuario, int nivel)
		throws ServiceException, SQLException, NamingException {
		
		String idUnico = "";
		String sql = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Vector consulta = new Vector();
		int pc_cve_vendedor=0;
		
		//Se recupera primero la clave del vendedor
		try {			
			// gsv 28/07/2005 cambio de PC_ID_UNICO a PC_CVE_VENDEDOR
			//pc_cve_vendedor = getPcCveVendedor(idUsuario);
			pc_cve_vendedor = Integer.parseInt(idUsuario);
		} catch (Exception ex) {			
			log.error(ex.toString());	
			throw new ServiceException("No se encontro pc_cve_vendedor para el id:" + idUsuario);
		}		

		try {
			conn = ProveedorConexion.getConnection();
			//log.info("EstructuraOrganizacionalDAO.getLineaMando() \n ");
			//log.info("idUsuario = " + idUsuario);
			//log.info("nivel = " + nivel);

			st = conn.createStatement();

			sql =
				"SELECT PC_VENDEDORES.PC_ID_UNICO, PC_VENDEDORES.PC_CVE_VENDEDOR,  PC_VENDEDORES.PC_CVE_SUPERIOR ,"
					+ " PC_VENDEDORES.PC_NOM_VENDEDOR || ' ' ||"
					+ " PC_VENDEDORES.PC_APE_PATERNO || ' ' ||"
					+ " PC_VENDEDORES.PC_APE_MATERNO PC_NOMBRE_USUARIO,"
					+ " PC_PUESTOS.PC_CVE_PUESTO, "
					+ " PC_PUESTOS.PC_DESC_PUESTO, "
					+ " PC_VENDEDORES.PC_ESTATUS_HUELLA "+
				//estatus_huella
	" FROM PC_VENDEDORES, PC_PUESTOS"
		+ " WHERE PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO"
		+ "   AND nvl(PC_STATUS, 'nulo')<>'BAJA'"
		+ "   AND LEVEL = "+nivel
		+ " START WITH PC_VENDEDORES.PC_CVE_VENDEDOR = "+ pc_cve_vendedor
		+ " CONNECT BY PRIOR PC_VENDEDORES.PC_CVE_VENDEDOR = PC_VENDEDORES.PC_CVE_SUPERIOR";

			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			while (rs.next()) {
				idUnico = rs.getString("PC_CVE_VENDEDOR");

				Empleado empleado = new Empleado();
				empleado.idUnico = idUnico; // gsv 28/07/2005 cve_vendedor
				empleado.idPadre = rs.getString("PC_CVE_SUPERIOR");
				empleado.nombreCompleto = rs.getString("PC_NOMBRE_USUARIO");
				empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
				empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
				empleado.estatusHuella=rs.getInt("PC_ESTATUS_HUELLA");

				empleado.sistemasAsignados = getSistemasAsignados(idUnico, conn);
				consulta.add(empleado);
				//log.info("PC_CVE_VENDEDOR = " + idUnico);
			}

			conn.close();
		} catch (Exception e) {
			//log.error("ERROR = " + e);
			log.error(e.toString());
			//e.printStackTrace();
			throw new ServiceException(
				"EstructuraOrganizacionalDAO : Error en getLineaMando() " + e.getMessage(), e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

		return consulta;
	}

	/**
	 * Obtiene los sistemas asociados al usuario en la tabla PC_MAPEO_VENDEDOR
	 * 
	 * @param cve_vendedor Valor para el campo PC_CVE_VENDEDOR 
	 * @param conn conexion a base de datos (Connection)
	 * @return ArrayList de objetos Sistemas
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 * 
	 */
	public Vector getSistemasAsignados(String cve_vendedor, Connection conn)
		throws ServiceException, SQLException, NamingException {

		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		Vector consulta = new Vector();

		try {
			//log.info("EstructuraOrganizacionalDAO.getSistemasAsignados() \n ");
			//log.info("cve_vendedor = " + cve_vendedor);

			st = conn.createStatement();

			sql =
					" select PC_SISTEMA, PC_CVE_REG_VENTA"
					+ " from PC_MAPEO_VENDEDOR"
					+ " where PC_CVE_VENDEDOR = "+ cve_vendedor;

			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			while (rs.next()) {
				String sistema = rs.getString("PC_SISTEMA");
				String login = rs.getString("PC_CVE_REG_VENTA");
				Sistemas toSistemas = new Sistemas();
				toSistemas.idSistema = 0;
				toSistemas.descripcionSistema = sistema;
				toSistemas.loginUsuario = login;

				consulta.add(toSistemas);
				//log.info("PC_SISTEMA = " + sistema + ", LOGIN = " + login);
			}

		} catch (Exception e) {
			//log.error("ERROR = " + e);
			log.error(e.toString());
			//e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
		}
		return consulta;
	}

	/**
	 * Obtiene los Empleados con puesto cuya descripcion sea Director o Gerente
	 * 
	 * @return ArrayList de objetos Director
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 * 
	 */
	public Vector  getDirectores()
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		Vector resultado=new Vector();

		try {
			conn = ProveedorConexion.getConnection();
			//log.info("EstructuraOrganizacionalDAO.getDirectores() \n ");

			st = conn.createStatement();

			sql ="  SELECT PC_ID_UNICO, PC_CVE_VENDEDOR, PC_CVE_SUPERIOR ,"
					+ "    PC_NOM_VENDEDOR || ' ' ||PC_APE_PATERNO|| ' ' ||PC_APE_MATERNO PC_NOMBRE_USUARIO,"
					+ "    PC_PUESTOS.PC_CVE_PUESTO, PC_DESC_PUESTO, PC_ESTATUS_HUELLA "+
					" FROM PC_VENDEDORES, PC_PUESTOS"
					+ " WHERE PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO"
					+ "   AND nvl(PC_STATUS, 'nulo')<>'BAJA'"
					+ "   AND PC_VENDEDORES.PC_CVE_PUESTO IN (select PC_CVE_PUESTO from pc_puestos"
					+ " 	  where UPPER(PC_DESC_PUESTO) like '%DIRECTOR%' " 
					+ "          OR UPPER(PC_DESC_PUESTO) like '%GERENTE%')"
					+ "	  AND PC_CVE_SUPERIOR IN (select PC_CVE_VENDEDOR from PC_DIVISION)";

			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			while (rs.next()) {
				Director empleado = new Director();
				empleado.idUnico = rs.getString("PC_CVE_VENDEDOR"); // gsv 28/07/2005 cve_vendedor
				empleado.idPadre = rs.getString("PC_CVE_SUPERIOR");
				empleado.nombreCompleto = rs.getString("PC_NOMBRE_USUARIO");
				empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
				empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
				empleado.estatusHuella=rs.getInt("PC_ESTATUS_HUELLA");
				
				//Obtencion de los sistemas asignados al usuario			
				empleado.sistemasAsignados = getSistemasAsignados(rs.getString("PC_CVE_VENDEDOR"), conn);
					 
				resultado.add(empleado);
				//log.info("Director: ID_UNICO = " + empleado.idUnico);
			}

		} catch (Exception e) {
			//log.error("ERROR getDirectores()= " + e);
			log.error(e.toString());
			//e.printStackTrace();
		} finally {
			if (rs != null) 
				rs.close();
			if (st != null) 
				st.close();
			if (conn != null) 
				conn.close();
			
		}
			return resultado;
	}


	/**
	 * Obtiene el valor del campo PC_CVE_VENDEDOR para el usuario proporcionado
	 * 
	 * @param idUnicoUsuario id unico asignado al usuario
	 * @return int Clave numerica del empleado o -1 si no la encontro
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getPcCveVendedor(String idUnicoUsuario)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_pc_cve_vendedor = -1;

		try {
			conn = ProveedorConexion.getConnection();
			//log.info("EstructuraOrganizacionalDAO.getPcCveVendedor() \n ");
			//log.info("idUsuario = " + idUnicoUsuario);

			st = conn.createStatement();

			sql =	" select PC_CVE_VENDEDOR"
					+ " from PC_VENDEDORES"
					+ " where PC_ID_UNICO ='"+ idUnicoUsuario+"'";
			
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				int pc_cve_vendedor = rs.getInt("PC_CVE_VENDEDOR");
				//log.info("PC_CVE_VENDEDOR = " + pc_cve_vendedor);
				valor_pc_cve_vendedor = pc_cve_vendedor;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

			
		}
		return valor_pc_cve_vendedor;
	}

	/**
	 * Obtiene el catalogo de Divisiones
	 * @return ArrayList de objetos Division
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public Vector getDivisiones()
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		Vector resultado=new Vector();
		
		try {
			conn = ProveedorConexion.getConnection();
			//log.info("EstructuraOrganizacionalDAO.getDivisiones() \n ");

			st = conn.createStatement();
			sql =	"select PC_CVE_DIV, PC_DESC_DIV, PC_DESC_CORTA_DIV  from PC_DIVISION";
								
			log.info("sql = " + sql);
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				Division division= new Division();
				division.claveDivision= rs.getInt("PC_CVE_DIV");
				division.descripcionDivision=rs.getString("PC_DESC_DIV");
				division.descripcionCorta=rs.getString("PC_DESC_CORTA_DIV");
				
				//log.info("Division = " + division.claveDivision+" - "+division.descripcionDivision);
				resultado.add(division);
			}

		} catch (Exception e) {
			//log.error("ERROR getDivisiones()= " + e);
			log.error(e.toString());
			//e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

			
		}
		
		return resultado;
	}
	
	/**
	 * Realiza una busqueda de empleados pertenecientes a una Division con los apellidos especificados
	 * y que tengan estatus de huella registrada o deshabilitada
	 * 
	 * @param apellidoPaterno Apellido Paterno del empleado, <b>obligatorio</b>
	 * @param apellidoMaterno Apellido Materno del empleado, <b>opcional</b>
	 * @param division Division en la que se desea hacer la busqueda, debe ser una clave de division valida
	 * @return ArrayList de objetos Empleado
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public Vector getEmpleadosHuellaRegistrada(String apellidoPaterno, String apellidoMaterno, int division)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		Vector resultado=new Vector();
		String busquedaApellidoMaterno="";
		/*
		log.info("EstructuraOrganizacionalDAO.getEmpleadosHuellaRegistrada() \n ");
		log.info("apellidoPaterno="+apellidoPaterno);
		log.info("apellidoMaterno="+apellidoMaterno);
		log.info("division="+division);
		*/
		if (apellidoPaterno!=null && !apellidoPaterno.trim().equals("")){		

			try {
				conn = ProveedorConexion.getConnection();
				
				st = conn.createStatement();
				
				if (apellidoMaterno!=null && !apellidoMaterno.trim().equals("")){
					busquedaApellidoMaterno = " AND UPPER(PC_VENDEDORES.PC_APE_MATERNO) like '"+apellidoMaterno.toUpperCase()+"%'";
				}			
				
				sql ="SELECT PC_VENDEDORES.PC_ID_UNICO, PC_VENDEDORES.PC_CVE_VENDEDOR,  PC_VENDEDORES.PC_CVE_SUPERIOR ,"
						+ " PC_VENDEDORES.PC_NOM_VENDEDOR || ' ' ||"
						+ " PC_VENDEDORES.PC_APE_PATERNO || ' ' ||"
						+ " PC_VENDEDORES.PC_APE_MATERNO PC_NOMBRE_USUARIO,"
						+ " PC_PUESTOS.PC_CVE_PUESTO, "
						+ " PC_PUESTOS.PC_DESC_PUESTO, "
						+ " PC_VENDEDORES.PC_ESTATUS_HUELLA "+
						" FROM PC_VENDEDORES, PC_PUESTOS"
						+ " WHERE"
						+ " PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO"
						+ " AND  nvl(PC_STATUS, 'nulo')<>'BAJA'"
						+ " AND UPPER(PC_VENDEDORES.PC_APE_PATERNO) like '"+apellidoPaterno.toUpperCase()+"%'"					
						+ busquedaApellidoMaterno	
						+ " AND (PC_VENDEDORES.PC_ESTATUS_HUELLA = "+ HUELLA_REGISTRADA
						+	" OR PC_VENDEDORES.PC_ESTATUS_HUELLA = "+ HUELLA_DESHABILITADA +") "				
						+ " AND PC_VENDEDORES.PC_CVE_VENDEDOR IN ("
								+ " SELECT PC_VENDEDORES.PC_CVE_VENDEDOR"
								+ " FROM PC_VENDEDORES, PC_PUESTOS "
								+ " WHERE PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO" 
								+ " START WITH PC_VENDEDORES.PC_CVE_SUBDIV = " + division
								+ " CONNECT BY PRIOR PC_VENDEDORES.PC_CVE_VENDEDOR = PC_VENDEDORES.PC_CVE_SUPERIOR)";
	
				log.info("sql = " + sql);
	
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Empleado empleado = new Empleado();
					empleado.idUnico = rs.getString("PC_CVE_VENDEDOR"); // gsv 28/07/2005 cve_vendedor
					empleado.idPadre = rs.getString("PC_CVE_SUPERIOR");
					empleado.nombreCompleto = rs.getString("PC_NOMBRE_USUARIO");
					empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
					empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
					empleado.estatusHuella=rs.getInt("PC_ESTATUS_HUELLA");
					
					//Obtencion de los sistemas asignados al usuario			
					empleado.sistemasAsignados = getSistemasAsignados(rs.getString("PC_CVE_VENDEDOR"), conn);
						 
					resultado.add(empleado);
					//log.info("getEmpleadosHuellaRegistrada: " + empleado.idUnico+" - "+empleado.nombreCompleto);
				}
				//log.info("getEmpleadosHuellaRegistrada: encontrados = "+resultado.size());
	
			} catch (Exception e) {
				//log.error("ERROR getEmpleadosHuellaRegistrada()= " + e);
				log.error(e.toString());
				//e.printStackTrace();
			} finally {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
				
			}
		}
		else {
			log.info("El Apellido Paterno es requerido");
		}
		return resultado;
	}	

	/**
	 * Realiza una busqueda de empleado por apellido paterno, materno y division
	 * 
	 * No se toma en cuenta el estatus de la huella del empleado
	 * 
	 * @param apellidoPaterno Apellido Paterno del empleado, <b>obligatorio</b>
	 * @param apellidoMaterno Apellido Materno del empleado, <b>opcional</b>
	 * @param division Division en la que se desea hacer la busqueda, debe ser una clave de division valida
	 * @return
	 * ArrayList de objetos Empleado
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException 
	 */	
	public Vector busquedaEmpleados(String apellidoPaterno, String apellidoMaterno, int division)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		Vector resultado=new Vector();
		String busquedaApellidoMaterno="";
		/*
		log.info("EstructuraOrganizacionalDAO.busquedaEmpleados() \n ");
		log.info("apellidoPaterno="+apellidoPaterno);
		log.info("apellidoMaterno="+apellidoMaterno);
		log.info("division="+division);
		*/
		if (apellidoPaterno!=null && !apellidoPaterno.trim().equals("")){		

			try {
				conn = ProveedorConexion.getConnection();
				
				st = conn.createStatement();
				
				if (apellidoMaterno!=null && !apellidoMaterno.trim().equals("")){
					busquedaApellidoMaterno = " AND UPPER(PC_VENDEDORES.PC_APE_MATERNO) like '"+apellidoMaterno.toUpperCase()+"%'";
				}			
				
				sql ="SELECT PC_VENDEDORES.PC_ID_UNICO, PC_VENDEDORES.PC_CVE_VENDEDOR,  PC_VENDEDORES.PC_CVE_SUPERIOR ,"
						+ " PC_VENDEDORES.PC_NOM_VENDEDOR || ' ' ||"
						+ " PC_VENDEDORES.PC_APE_PATERNO || ' ' ||"
						+ " PC_VENDEDORES.PC_APE_MATERNO PC_NOMBRE_USUARIO,"
						+ " PC_PUESTOS.PC_CVE_PUESTO, "
						+ " PC_PUESTOS.PC_DESC_PUESTO, "
						+ " PC_VENDEDORES.PC_ESTATUS_HUELLA "+
						" FROM PC_VENDEDORES, PC_PUESTOS"
						+ " WHERE"
						+ " PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO"
						+ " AND  nvl(PC_STATUS, 'nulo')<>'BAJA'"
						+ " AND UPPER(PC_VENDEDORES.PC_APE_PATERNO) like '"+apellidoPaterno.toUpperCase()+"%'"					
						+ busquedaApellidoMaterno
						+ " AND PC_VENDEDORES.PC_CVE_VENDEDOR IN ("
								+ " SELECT PC_VENDEDORES.PC_CVE_VENDEDOR"
								+ " FROM PC_VENDEDORES, PC_PUESTOS "
								+ " WHERE PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO" 
								+ " START WITH PC_VENDEDORES.PC_CVE_SUBDIV = " + division
								+ " CONNECT BY PRIOR PC_VENDEDORES.PC_CVE_VENDEDOR = PC_VENDEDORES.PC_CVE_SUPERIOR)";
	
				log.info("sql = " + sql);
	
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Empleado empleado = new Empleado();
					empleado.idUnico = rs.getString("PC_CVE_VENDEDOR"); // gsv 28/07/2005 cve_vendedor
					empleado.idPadre = rs.getString("PC_CVE_SUPERIOR");
					empleado.nombreCompleto = rs.getString("PC_NOMBRE_USUARIO");
					empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
					empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
					empleado.estatusHuella=rs.getInt("PC_ESTATUS_HUELLA");
					
					//Obtencion de los sistemas asignados al usuario			
					empleado.sistemasAsignados = getSistemasAsignados(rs.getString("PC_CVE_VENDEDOR"), conn);
						 
					resultado.add(empleado);
					//log.info("busquedaEmpleados: " + empleado.idUnico+" - "+empleado.nombreCompleto);
				}
				//log.info("busquedaEmpleados: encontrados = "+resultado.size());
	
			} catch (Exception e) {
				log.error(e.toString());			
			} finally {
				if (rs != null) rs.close();
				if (st != null) st.close();
				if (conn != null) conn.close();
			}
		}
		else {
			log.info("El Apellido Paterno es requerido");
		}
		return resultado;
	}	


	/**
	 * Realiza una busqueda de empleado dentro de los subordinados de un Superior 
	 * 
	 * @param apellidoPaterno Apellido Paterno del empleado, <b>obligatorio</b>
	 * @param apellidoMaterno Apellido Materno del empleado, <b>opcional</b>
	 * @param idPadre id del empleado a partir de donde se buscaran los subordinados
	 * @return
	 * ArrayList de objetos Empleado
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public Vector busquedaSubordinados(String apellidoPaterno, String apellidoMaterno, String idPadre)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		Vector resultado=new Vector();
		String busquedaApellidoMaterno="";
		/*
		log.info("EstructuraOrganizacionalDAO.busquedaEmpleados() \n ");
		log.info("apellidoPaterno="+apellidoPaterno);
		log.info("apellidoMaterno="+apellidoMaterno);
		log.info("idPadre="+idPadre);
		*/
		if (apellidoPaterno!=null && !apellidoPaterno.trim().equals("")){		

			try {
				conn = ProveedorConexion.getConnection();
				
				st = conn.createStatement();
				
				if (apellidoMaterno!=null && !apellidoMaterno.trim().equals("")){
					busquedaApellidoMaterno = " AND UPPER(PC_VENDEDORES.PC_APE_MATERNO) like '"+apellidoMaterno.toUpperCase()+"%'";
				}			
				
				sql ="SELECT PC_VENDEDORES.PC_ID_UNICO, PC_VENDEDORES.PC_CVE_VENDEDOR,  PC_VENDEDORES.PC_CVE_SUPERIOR ,"
						+ " PC_VENDEDORES.PC_NOM_VENDEDOR || ' ' ||"
						+ " PC_VENDEDORES.PC_APE_PATERNO || ' ' ||"
						+ " PC_VENDEDORES.PC_APE_MATERNO PC_NOMBRE_USUARIO,"
						+ " PC_PUESTOS.PC_CVE_PUESTO, "
						+ " PC_PUESTOS.PC_DESC_PUESTO, "
						+ " PC_VENDEDORES.PC_ESTATUS_HUELLA "+
						" FROM PC_VENDEDORES, PC_PUESTOS"
						+ " WHERE"
						+ " PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO"
						+ " AND  nvl(PC_STATUS, 'nulo')<>'BAJA'"
						+ " AND UPPER(PC_VENDEDORES.PC_APE_PATERNO) like '"+apellidoPaterno.toUpperCase()+"%'"					
						+ busquedaApellidoMaterno
						+ " AND PC_VENDEDORES.PC_CVE_VENDEDOR IN ("
								+ " SELECT PC_VENDEDORES.PC_CVE_VENDEDOR"
								+ " FROM PC_VENDEDORES, PC_PUESTOS "
								+ " WHERE PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO" 
								+ " AND LEVEL > 1 "
								+ " START WITH PC_VENDEDORES.PC_CVE_VENDEDOR = " + idPadre
								+ " CONNECT BY PRIOR PC_VENDEDORES.PC_CVE_VENDEDOR = PC_VENDEDORES.PC_CVE_SUPERIOR)";
	
				log.info("sql = " + sql);
	
				rs = st.executeQuery(sql);
				while (rs.next()) {
					Empleado empleado = new Empleado();
					empleado.idUnico = rs.getString("PC_CVE_VENDEDOR"); // gsv 28/07/2005 cve_vendedor
					empleado.idPadre = rs.getString("PC_CVE_SUPERIOR");
					empleado.nombreCompleto = rs.getString("PC_NOMBRE_USUARIO");
					empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
					empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
					empleado.estatusHuella=rs.getInt("PC_ESTATUS_HUELLA");
					
					//Obtencion de los sistemas asignados al usuario			
					empleado.sistemasAsignados = getSistemasAsignados(rs.getString("PC_CVE_VENDEDOR"), conn);
						 
					resultado.add(empleado);
					//log.info("busquedaEmpleados: " + empleado.idUnico+" - "+empleado.nombreCompleto);
				}
				//log.info("busquedaEmpleados: encontrados = "+resultado.size());
	
			} catch (Exception e) {
				log.error(e.toString());			
			} finally {
				if (rs != null) rs.close();
				if (st != null) st.close();
				if (conn != null) conn.close();
			}
		}
		else {
			log.info("El Apellido Paterno es requerido");
		}
		return resultado;
	}

	/**
	 * Inserta un mapeo en la tabla de vendedores 
	 */
	public int crearMapeoVendedor(int vendedor, String sistema, int id,
								  String login, String nombre, String password) throws Exception{
	
		Connection conn = null;
		String sql = "";
		Statement st = null;
		int resultado = 0;
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql ="INSERT INTO PC_MAPEO_VENDEDOR VALUES("+vendedor+",'"+sistema+"',"+id+",'"+login+"','"+nombre+"','"+password+"')";
			log.info("crearMapeoVendedor = " + sql);
			resultado = st.executeUpdate(sql);
		} catch (Exception e) {
			log.error(e.toString());			
		} finally {
			if (st != null) st.close();
			if (conn != null) conn.close();
		}
		return resultado;
	}
	
	/**
	 * Borra el mapeo de un vendedor 
	 */
	public int borrarMapeoVendedor(int vendedor, String sistema) throws Exception{

		Connection conn = null;
		String sql = "";
		Statement st = null;
		int resultado = 0;
	
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "DELETE FROM PC_MAPEO_VENDEDOR " +				" WHERE PC_CVE_VENDEDOR = " + vendedor +
				" AND PC_SISTEMA = '" + sistema + "'";
			log.info("borrarMapeoVendedor = " + sql);
			resultado = st.executeUpdate(sql);
		} catch (Exception e) {
			log.error(e.toString());			
		} finally {
			if (st != null) st.close();
			if (conn != null) conn.close();
		}
		return resultado;
	}
	
	/**
	 * Borra todos los mapeos relacionados a un vendedor 
	 */
	public int borrarMapeoVendedorTodos(int cveVendedor) throws Exception{

		Connection conn = null;
		String sql = "";
		Statement st = null;
		int resultado = 0;
	
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "DELETE FROM PC_MAPEO_VENDEDOR " +
				"WHERE PC_CVE_VENDEDOR = " + cveVendedor;
			log.info("borrarMapeoVendedorTodos = " + sql);
			resultado = st.executeUpdate(sql);
		} catch (Exception e) {
			log.error(e.toString());			
		} finally {
			if (st != null) st.close();
			if (conn != null) conn.close();
		}
		return resultado;
	}

	/**
	 * Borra un Canal de la tabla PC_CANAL 
	 */
	public int borrarCanal(String cveCanal) throws Exception{

		Connection conn = null;
		String sql = "";
		Statement st = null;
		int resultado = 0;
	
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "DELETE FROM pc_canal WHERE pc_cve_canal = '" + cveCanal + "'";
			log.info("borrarCanal = " + sql);
			
			resultado = st.executeUpdate(sql);
		} catch (Exception e) {
			log.error(e.toString());			
		} finally {
			if (st != null) st.close();
			if (conn != null) conn.close();
		}
		return resultado;
	}

	/**
	 * Borra un Vendedor de la tabla PC_VENDEDORES_HIS y PC_VENDEDORES 
	 */
	public int borrarVendedor(String cveVendedor) throws Exception{

		Connection conn = null;
		String sql = "";
		Statement st = null;
		int resultado1 = 0, resultado2 = 0;
	
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			
			sql = "DELETE FROM pc_vendedores_his WHERE pc_cve_vendedor = '" + cveVendedor + "'";
			log.info("borrarVendedorHis = " + sql);
			resultado1 = st.executeUpdate(sql);
			
			sql = "DELETE FROM pc_vendedores WHERE pc_cve_vendedor = '" + cveVendedor + "'";
			log.info("borrarVendedor = " + sql);
			resultado2 = st.executeUpdate(sql);
			
		} catch (Exception e) {
			log.error(e.toString());			
		} finally {
			if (st != null) st.close();
			if (conn != null) conn.close();
		}
		return (resultado1 * resultado2);
	}

	/**
	 * Actualiza un mapeo en la tabla de vendedores 
	 */
	public int modificaMapeoVendedor(int vendedor, String sistema, String loginActual, String loginNuevo) throws Exception{

		Connection conn = null;
		String sql = "";
		Statement st = null;
		int resultado = 0;

		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "UPDATE PC_MAPEO_VENDEDOR SET PC_CVE_REG_VENTA = '"+loginNuevo+"' " +
				  "WHERE PC_CVE_VENDEDOR = "+vendedor +" AND PC_SISTEMA = '"+sistema+"' " +
				  "AND PC_CVE_REG_VENTA = '"+loginActual+"'";
			log.info("modificaMapeoVendedor = " + sql);
			resultado = st.executeUpdate(sql);
		} catch (Exception e) {
			log.error(e.toString());			
		} finally {
			st.close();
			conn.close();
		}
		return resultado;
	}
	
	/**
	 * Obtiene la linea de mando determinada por el parametro nivel del usuario proporcionado
	 * internamente obtiene el campo PC_CVE_VENDEDOR para hacer la busqueda
	 *  
	 * @param idUsuario id unico asignado al usuario
	 * @param nivel nivel de la linea de mando
	 * @return ArrayList de objetos Empleado
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 * 
	 */
	public Vector getLineaMandoAlterna(String idUsuario, String divisiones)
		throws ServiceException, SQLException, NamingException {
		
		String idUnico = "";
		String sql = "";
		String condicion = "";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		Vector consulta = new Vector();
		int pc_cve_vendedor=0;
	
		try {			
			pc_cve_vendedor = Integer.parseInt(idUsuario);
		} catch (Exception ex) {			
			log.error(ex.toString());	
			throw new ServiceException("No se encontro pc_cve_vendedor para el id:" + idUsuario);
		}		

		try {
			conn = ProveedorConexion.getConnection();
			/*
			log.info("EstructuraOrganizacionalDAO.getLineaMandoAlterna() \n ");
			log.info("idUsuario = " + idUsuario);
			log.info("divisiones = " + divisiones);
			*/
			st = conn.createStatement();
			
			if(!divisiones.equals("%"))
				condicion = " AND PC_CVE_SUBDIV in ("+divisiones+")"; 

			sql = "SELECT PC_ID_UNICO, PC_CVE_VENDEDOR, PC_CVE_SUPERIOR ," +
				  " PC_NOM_VENDEDOR||' '||PC_APE_PATERNO||' '||PC_APE_MATERNO PC_NOMBRE_USUARIO," +
				  " PC_PUESTOS.PC_CVE_PUESTO, PC_DESC_PUESTO, PC_ESTATUS_HUELLA" +
				  "  FROM PC_VENDEDORES, PC_PUESTOS" +
				  " WHERE PC_VENDEDORES.PC_CVE_PUESTO = PC_PUESTOS.PC_CVE_PUESTO" +
				  "   AND NVL(PC_STATUS, 'nulo') <> 'BAJA'" +condicion+
				  "   AND PC_CVE_VENDEDOR = "+idUsuario;
				
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			while (rs.next()) {
				idUnico = rs.getString("PC_CVE_VENDEDOR");

				Empleado empleado = new Empleado();
				empleado.idUnico = idUnico; // gsv 28/07/2005 cve_vendedor
				empleado.idPadre = rs.getString("PC_CVE_SUPERIOR");
				empleado.nombreCompleto = rs.getString("PC_NOMBRE_USUARIO");
				empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
				empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
				empleado.estatusHuella=rs.getInt("PC_ESTATUS_HUELLA");
				empleado.sistemasAsignados = getSistemasAsignados(idUnico, conn);
				consulta.add(empleado);
				//log.info("PC_CVE_VENDEDOR = " + idUnico);
			}

			conn.close();
		} catch (Exception e) {
			//log.error("ERROR = " + e);
			log.error(e.toString());
			//e.printStackTrace();
			throw new ServiceException(
				"EstructuraOrganizacionalDAO : Error en getLineaMando() " + e.getMessage(), e);
		} finally {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (conn != null) conn.close();
		}

		return consulta;
	}
	
	/**
	 * Obtiene el catalogo de Divisiones
	 * @return ArrayList de objetos Division
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public String getDivisionesAlternas(String idUsuario, int perfil)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "", resultado = "";
		Statement st = null;
		ResultSet rs = null;
	
		try {
			conn = ProveedorConexion.getConnection();
			//log.info("EstructuraOrganizacionalDAO.getDivisionesAlternas() \n ");
			if(perfil == Constante.AUTORIZADOR)//autorizador
				sql = "SELECT DISTINCT PC_DIVISIONES_AUT DIVISIONES FROM PC_DATASEC WHERE PC_CVE_AUTORIZA = "+idUsuario;
			else if(perfil == Constante.REGISTRADOR1 || perfil == Constante.REGISTRADOR2)//registrador
				sql = "SELECT DISTINCT PC_DIVISIONES_REG DIVISIONES FROM PC_DATASEC WHERE PC_CVE_REGISTRA = "+idUsuario;

			st = conn.createStatement();
			log.info("sql = " + sql);
			rs = st.executeQuery(sql);
			
			if(rs.next()){
				resultado = rs.getString("DIVISIONES"); 
				return resultado;
			}

		} catch (Exception e) {
			log.error(e.toString());
			//e.printStackTrace();
		} finally {
			if (rs != null)	rs.close();
			if (st != null)	st.close();
			if (conn != null) conn.close();
		}
		return resultado;
	}
	
	/**
	 * Inserta un vendedor en la tabla PC_VENDEDORES 
	 */
	public int crearVendedor(String cveSAP, String cvePuesto,String cveSuperior,
								String nombre, String apepaterno, String apematerno,
								String fechaalta, String digitoVerificador,
								String numeroEmpleado, String ciudad,String estado,
								String direcion, String colonia,String cp,
								String tel, String fax,String comisionar,
								String pc_cve_subdiv, String pc_cve_empresa,
								String pc_cve_horario, String pc_nivel_ventas, String pc_tipo_comision, String pc_respuesta,
								String pc_tipo_vendedor, String cveusurioalta
								) throws Exception{

		Connection conn = null;
		String sql = "",sqlHis="";
		Statement st = null;
		int resultado = 0;
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			
			sql ="INSERT INTO PC_VENDEDORES " +
				"(PC_CVE_VENDEDOR, PC_CVE_PUESTO,  PC_CVE_SUPERIOR,"+
					"PC_NOM_VENDEDOR, PC_APE_PATERNO, PC_APE_MATERNO,"+ 
					"PC_FCH_ALTA, 	 PC_DIG_VERIF,"+     
					"PC_CVE_EMP_REF, PC_CIUDAD,"+
					"PC_ESTADO,PC_DIRECCION,PC_COLONIA,"+
					"PC_CP,PC_TEL,PC_FAX," +
					"PC_COMISIONAR,pc_cve_subdiv, pc_cve_empresa,"+
                    "pc_cve_horario, pc_nivel_ventas, pc_tipo_comision, pc_respuesta,"+
                    "pc_pregunta, pc_tipo_vendedor,PC_STATUS) " +
				" VALUES("+cveSAP+","+cvePuesto+","+cveSuperior+"," +
					"'"+nombre+"','"+apepaterno+"','"+apematerno+"'," +
					"to_date('"+fechaalta+"','dd/mm/yyyy'),"+digitoVerificador+","+
					"'"+numeroEmpleado+"','"+ciudad+"'," +
					"'"+estado+"','"+direcion+"','"+colonia+"'," +
					"'"+cp+"','"+tel+"','"+fax+"'," +
					"'"+comisionar+"'," +					""+pc_cve_subdiv+","+pc_cve_empresa+","+
					"'"+pc_cve_horario+"',"+pc_nivel_ventas+",'"+pc_tipo_comision+"'," +
					"'"+cveusurioalta+"','1','"+pc_tipo_vendedor+"','ALTA'" +					")";
			log.info("crearVendedor = " + sql);
			
			resultado = st.executeUpdate(sql);
			
			sqlHis="INSERT INTO PC_VENDEDORES_HIS (PC_CVE_ID, PC_CVE_VENDEDOR, PC_CVE_PUESTO," +				   " PC_CVE_SUPERIOR, PC_FCH_INICIO, PC_FCH_FIN," +				   " PC_TP_MOV, PC_FCH_TRANSACCION, PC_CVE_USUARIO, PC_CVE_HORARIO) VALUES "+
				   " (SEQ_PC_VENDEDORES_HIS.nextval,"+cveSAP+","+cvePuesto+","+cveSuperior+","+
	               "   to_date('"+fechaalta+"','dd/mm/yyyy'),'',1,SYSDATE, 1, '0'" +	               " )";
			log.info("crearVendedorHistorico = " + sqlHis);
       
			st.executeUpdate(sqlHis);
			
		} catch (Exception e) {
			log.error(e.toString());			
		} finally {
			if (st != null)	st.close();
			if (conn != null) conn.close();
		}
		return resultado;
	}

	/**
	 * Obtiene el Digito Verificador de la cadena ingresada 
	 */
	public int getDigitoVerificador(String cadena) throws Exception {
		
		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;

		int digVerificador = -1; // Valor por default
	
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			
			sql = "SELECT digito_verificador ('" + cadena + "') digverificador FROM DUAL";

			log.info("digitoVerificador = " + sql);
			
			rs = st.executeQuery(sql);
			if (rs.next())
				digVerificador = rs.getInt("digverificador");
			
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null)	rs.close();
			if (st != null)	st.close();
			if (conn != null) conn.close();
		}
		return digVerificador;
	}
	
	/**
	 * Inserta un Punto de Venta en la tabla PC_PUNTO_VENTAS 
	 */
	public String crearPuntoDeVenta(String cvePtoVentas, String numEconomico, String cveCanal, 
		String cveRegion, String nomPtoventas, String ciudad, String estado, 
		String cp, String direccion, String colonia, String cveDiv,  
		String cveEstado, String cveCiudad) throws Exception 
	{

		Connection conn = null;
		Statement st = null;
		String sql = "";

		int digVerificador = -1;
		String cadDigVerificador = "", clavePV;
		boolean autogenerada = false;
		
		try {
			// Verifica si el sistema necesita generar la clave de punto de venta
			autogenerada = cvePtoVentas.equals("AUTOGENERADA")?true:false;

			if(autogenerada) {
				// Extrae la clave de PV siguiente
				clavePV = String.valueOf(getNextCvePuntoVenta()); 
			} else {
				// Usa la clave de PV que viene como parametro
				clavePV = cvePtoVentas;
			}
			
			digVerificador = this.getDigitoVerificador(clavePV);
			cadDigVerificador = (digVerificador >= 0)? ""+digVerificador : "null";
			
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			
			sql ="Insert into PC_PUNTO_VENTAS \n" +
    			"(PC_CVE_PTOVENTAS, PC_CVE_REFERENCIA, PC_CVE_CANAL, PC_CVE_REGION, \n" +    			"   PC_NOM_PTOVENTAS, PC_CIUDAD, PC_ESTADO, PC_CP, PC_DIRECCION, PC_COLONIA, \n" +    			"   PC_CVE_DIV, PC_CVE_SUBDIV, PC_DIG_VERIF, PC_STATUS, PC_CVE_ESTADO, \n" +    			"   PC_FCH_ALTA, PC_FCH_INI_OP, PC_CVE_CIUDAD) \n" +
				"Values \n" +				"  ('" + clavePV + "', '" + numEconomico + "', '" + cveCanal + "', '" + cveRegion + "',\n" +				"   '" + nomPtoventas + "', '" + ciudad + "', '" + estado + "', '" + cp + "',\n" +  
   				"   '" + direccion + "', '" + colonia + "', " + cveDiv + ", " + cveDiv + ", " + cadDigVerificador + ",\n" +   				"   'ALTA', " + cveEstado + ", SYSDATE, SYSDATE, " + cveCiudad + ")";
			
			log.info("crearPuntoDeVenta = " + sql);
			
			st.executeUpdate(sql); // Ejecuta la insercion en BD

		} catch (Exception e) {
			clavePV = null; // Respuesta que indica un error durante la insercion
			log.error(e.toString());
			//e.printStackTrace();
		} finally {
			if (st != null)	st.close();
			if (conn != null) conn.close();
		}
		return clavePV;
	}	

	/**
	 * Obtiene el valor del campo PC_CVE_VENDEDOR 
	 * 
	 * @param clave id unico asignado al usuario
	 * @return int Clave numerica del empleado o -1 si no la encontro
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getPcCveVendedorSAEO(String clave)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_pc_cve_vendedor = -1;

		try {
			conn = ProveedorConexion.getConnection();

			st = conn.createStatement();

			sql =	" select PC_CVE_VENDEDOR"
					+ " from PC_VENDEDORES"
					+ " where PC_CVE_VENDEDOR ='"+ clave+"'";
		
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				int pc_cve_vendedor = rs.getInt("PC_CVE_VENDEDOR");
				//log.info("PC_CVE_VENDEDOR = " + pc_cve_vendedor);
				valor_pc_cve_vendedor = pc_cve_vendedor;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

			
		}
		return valor_pc_cve_vendedor;
	}	
		
		
	/**
	 * Obtiene el valor del campo PC_CVE_VENDEDOR 
	 * 
	 * @return int Clave numerica del empleado o -1 si no la pudo generar
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getnextCveVendedorSAEO()
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_pc_cve_vendedor = -1;

		try {
			conn = ProveedorConexion.getConnection();

			st = conn.createStatement();

			sql =	" select get_cve_vendedor() PC_CVE_VENDEDOR"+
                    "   from dual ";
			
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				int pc_cve_vendedor = rs.getInt("PC_CVE_VENDEDOR");
				valor_pc_cve_vendedor = pc_cve_vendedor;
				log.info("PC_CVE_VENDEDOR encontrada= " + pc_cve_vendedor);
			}

		} catch (Exception e) {

			log.error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();

			
		}
		return valor_pc_cve_vendedor;
	}		

	/**
	 * Obtiene el valor del campo PC_CVE_PTOVENTAS 
	 * 
	 * @return int Clave numerica del punto de ventas o -1 si no la pudo generar
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int getNextCvePuntoVenta()
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int pcCvePtoVentas = -1;

		try {
			conn = ProveedorConexion.getConnection();

			st = conn.createStatement();

			sql = "SELECT SEQ_PC_PUNTO_VENTAS.NEXTVAL AS CLAVE FROM DUAL";
			
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				pcCvePtoVentas = rs.getInt("CLAVE");
				log.info("PC_CVE_PTOVENTAS encontrada = " + pcCvePtoVentas);
			}

		} catch (Exception e) {

			log.error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();

			
		}
		return pcCvePtoVentas;
	}		

	/**
	 * Valida que exista el puesto en la tabla pc_puestos
	 * 
	 * @param puesto
	 * @return true or false
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean validaPuesto(String puesto)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_retorno = -1;
		boolean existe=false;
		
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();

			sql =	" select pc_cve_puesto "+
					"   from pc_puestos " +					"     where pc_cve_puesto ="+puesto;
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				existe=true;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

			
		}
		return existe;
	}		
	
	
	/**
	 * Valida que exista el superior en la tabla pc_vendedores
	 * 
	 * @param cveSuperior
	 * @return true or false
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean validaSuperior(String cveSAEO)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_retorno = -1;
		boolean existe=false;
		
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();

			sql =	" select pc_cve_vendedor "+
					"   from pc_vendedores " +					"     where pc_status='ALTA' " +
					"     and pc_cve_vendedor ="+cveSAEO;
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				existe=true;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return existe;
	}	
	

	/**
	 * Valida que la variable venga contenga N o S
	 * 
	 * @param fecha
	 * @return true or false
	 */
	public boolean validacomisionar(String comisionar){
		boolean existe = false;
		try {
			if( (comisionar != null && comisionar.length()>=1) && 
				(comisionar.equals("S") || comisionar.equals("N")))
					existe = true;
		} catch (Exception e) {
			log.error(e.toString());
		} 
		return existe;
	}	


	/**
	 * Valida que exista la division en estatus A
	 * 
	 * @param cveDivision
	 * @return true or false
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean validaDivision(String cveDivision)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_retorno = -1;
		boolean existe=false;
		
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();

			sql =	" select pc_cve_subdiv "+
					"   from pc_subdivision " +
					"     where pc_status_portal='A' " +
					"     and pc_cve_subdiv ="+cveDivision;
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				existe=true;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

			
		}
		return existe;
	}


	/**
	 * Valida que exista la empresa en tabla
	 * 
	 * @param cveEmpresa
	 * @return true or false
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean validaEmpresa(String cveEmpresa)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_retorno = -1;
		boolean existe=false;
		
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();

			sql =	" select pc_cve_empresa "+
					"   from pc_empresas " +
					"     where pc_cve_empresa ="+cveEmpresa;
			log.info("sql = " + sql);
			
			rs = st.executeQuery(sql);
			if (rs.next()) {
				existe=true;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

			
		}
		return existe;
	}


	/**
	 * Valida que exista el horario
	 * 
	 * @param cveHorario
	 * @return true or false
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean validaHorario(String cveHorario)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_retorno = -1;
		boolean existe=false;
		
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();

			sql =	" select pc_cve_horario "+
					"   from pc_horario " +
					"     where pc_cve_horario ="+cveHorario;
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				existe=true;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

			
		}
		return existe;
	}


	/**
	 * Valida que exista el nivel
	 * 
	 * @param cveNivel
	 * @return true or false
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean validaNivelVentas(String cveNivel)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_retorno = -1;
		boolean existe=false;
		
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();

			sql =	" select pc_nivel "+
					"   from pc_nivel_cuotas " +
					"     where pc_nivel ="+cveNivel;
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				existe=true;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}

			
		}
		return existe;
	}
	
	
	/**
	 * Valida que la variable venga contenga M o V
	 * 
	 * @param cve
	 * @return true or false
	 */
	public boolean validaTipoVendedor(String cve){
		boolean existe=false;
		try {
			if(cve!=null){
					if(cve.length()>=1){
					   if(cve.equals("M") || cve.equals("V")){
									existe=true;
									}
					
					}
			}
			
		} catch (Exception e) {
			log.error(e.toString());
		} 
			return existe;
		
	}	
	
	
	/**
	 * Valida que exista el cvePVT en ALTA
	 * 
	 * @param cvePVT
	 * @return true or false
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public boolean validaPVT(String cvePVT)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_retorno = -1;
		boolean existe = false;
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();

			sql =	"SELECT pc_cve_ptoventas "+
					"FROM pc_punto_ventas " +
					"WHERE pc_cve_ptoventas = '"+cvePVT+"' " +					"AND pc_status = 'ALTA'";
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next() && rs.getString("pc_cve_ptoventas").equals(cvePVT)) {
				existe = true;
			}

		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return existe;		
	}
	
	/**
	 * Valida la existencia de la relacion de un Vendedor y un Punto de Venta
	 * @param cveVend Clave del Vendedor
	 * @param cvePVT Clave del Punto de Venta
	 * @return
	 *  -2 = Relacion No existente
	 * 	-1 = Relacion existente y sin estatus
	 *   0 = Relacion existente y en BAJA
	 *   1 = Relacion existente y en ALTA
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public int validaRelVendPVT(String cveVend,String cvePVT)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		int valor_retorno = -2; /// Default = Relacion NO existente
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();

			sql =	" select decode(pc_status,null,-1,'ALTA',1,'BAJA',0) PC_STATUS "+
					" from pc_inter_vendedores_ptoventas " +
					" where pc_cve_ptoventas='"+cvePVT+"' " +
					" and pc_cve_vendedor='"+cveVend+"'";
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			if (rs.next()) {
				valor_retorno = rs.getInt("PC_STATUS");
				log.info("retorno validaRelVendPVT = " + valor_retorno);
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
			
		}
		return valor_retorno;
	}	
	
	/**
	 * Crea una relacion entre un Vendedor y un Punto de Venta
	 * @param cveSAEO Clave SAEO del Vendedor
	 * @param cvePVT Clave del Punto de Venta
	 * @return int
	 *  1 = Relacion agregada con Exito, 
	 * -3 = Relacion encontrada con estatus en BAJA,
	 * -4 = Relacion encontrada con estatus en ALTA,
	 * -5 = Relacion encontrada sin estatus
	 * @throws Exception
	 */
	public int crearInterVendedorPvt(String cveSAEO, String cvePVT)
		throws Exception {
	
		Connection conn = null;
		String sql = "", sqlHis = "";
		Statement st = null;
		int resultado = -1, cveInter = 0;
		int valida = 0;
	
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
	
			// Verifica primero si esta relacion existe 
			valida = validaRelVendPVT(cveSAEO, cvePVT);
			/* -2 = No existe la relacion entre Vendedor y PDV
			*  -1 = Relacion existente y sin estatus
			*   0 = Relacion existente y en BAJA
			*   1 = Relacion existente y en ALTA */ 
			if (valida == -2) { // La Relacion no se encontro y puede ser agregada
				sql =
					"INSERT INTO PC_INTER_VENDEDORES_PTOVENTAS "
						+ "(PC_CVE_INTER, PC_CVE_VENDEDOR, "
						+ "PC_CVE_PTOVENTAS, PC_FCH_MOVIMIENTO, "
						+ "PC_CVE_USUARIO, PC_CVE_AUTORIZA, PC_STATUS,PC_FCH_INICIO)  "
						+ "VALUES ("
						+ "(SELECT NVL((MAX(PC_CVE_INTER)+1),0) CLAVE FROM PC_INTER_VENDEDORES_PTOVENTAS),"
						+ cveSAEO + ", '" + cvePVT + "', "
						+ "SYSDATE, 1, 1, 'ALTA', SYSDATE)";
				log.info("crearInterVendedorPvt = " + sql);
				
				resultado = st.executeUpdate(sql); // Se da por Default que sera 1
			} else if (valida == 0) {  // Relacion encontrada con estatus en BAJA
				resultado = -3;
			} else if (valida == 1) {  // Relacion encontrada con estatus en ALTA
				resultado = -4;
			} else if (valida == -1) { // Relacion encontrada sin estatus
				resultado = -5;
			}
			
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return resultado;
	}

	/**
	 * Crea una relacion entre un Vendedor y un Punto de Venta
	 * @param cveSAEO Clave SAEO del Vendedor
	 * @param cvePVT Clave del Punto de Venta
	 * @param cveUsuarioEjecuta Clave SAEO del usuario que ejecuta la relacion
	 * @param cveUsuarioAutoriza Clave SAEO del usuario que autoriza la relacion
	 * @return 
	 *  1 = Relacion agregada con exito
	 *  0 = Ocurrio un Error al ejecutar el Insert
	 * -3 = Relacion encontrada con estatus en BAJA
	 * -4 = Relacion encontrada con estatus en ALTA
	 * -5 = Relacion encontrada pero sin estatus
	 * @throws Exception
	 */
	public int crearInterVendedorPvt(
		String cveSAEO, String cvePVT, String cveUsuarioEjecuta, String cveUsuarioAutoriza)
		throws Exception {
	
		Connection conn = null;
		String sql = "";
		Statement st = null;
		int resultado = 0;
		int valida = 0;
	
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
	
			// Verifica primero si esta relacion existe 
			valida = validaRelVendPVT(cveSAEO, cvePVT);
			/* -2 = No existe la relacion entre Vendedor y PDV
			*  -1 = Relacion existente y sin estatus
			*   0 = Relacion existente y en BAJA
			*   1 = Relacion existente y en ALTA */ 
			if (valida == -2) { // OK, la Relacion no se encontro y debe ser agregada
				sql =
					"INSERT INTO PC_INTER_VENDEDORES_PTOVENTAS "
						+ "(PC_CVE_INTER, PC_CVE_VENDEDOR, "
						+ "PC_CVE_PTOVENTAS, PC_FCH_MOVIMIENTO, "
						+ "PC_CVE_USUARIO, PC_CVE_AUTORIZA, PC_STATUS,PC_FCH_INICIO) "
						+ "VALUES ("
						+ "(SELECT NVL((MAX(PC_CVE_INTER)+1),0) CLAVE FROM PC_INTER_VENDEDORES_PTOVENTAS), "
						+ cveSAEO + ", '" + cvePVT + "', "
						+ "SYSDATE, " + cveUsuarioEjecuta + ", " 
						+ cveUsuarioAutoriza + ", 'ALTA', SYSDATE)";
				log.info("crearInterVendedorPvt = " + sql);
				
				resultado = st.executeUpdate(sql);
			} else if (valida == 0) {  // Relacion encontrada con estatus en BAJA
				resultado = -3;
			} else if (valida == 1) {  // Relacion encontrada con estatus en ALTA
				resultado = -4;
			} else if (valida == -1) { // Relacion encontrada pero sin estatus
				resultado = -5;
			}
			
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return resultado;
	}

/*Catalogos*/

/**
 * Obtiene el catalogo de Puestos
 * @return ArrayList de objetos Puestos
 * @throws ServiceException
 * @throws SQLException
 * @throws NamingException
 */
public Vector getPuestos()
	throws ServiceException, SQLException, NamingException {

	Connection conn = null;
	String sql = "";
	Statement st = null;
	ResultSet rs = null;
	Vector resultado=new Vector();
		
	try {
		conn = ProveedorConexion.getConnection();
		//log.info("EstructuraOrganizacionalDAO.getDivisiones() \n ");

		st = conn.createStatement();
		sql =	"select PC_CVE_PUESTO,PC_DESC_PUESTO,PC_HORARIO from PC_PUESTOS where PC_VISUAL_SAEO='S' order by PC_DESC_PUESTO desc";
								
		log.info("sql = " + sql);
		
		rs = st.executeQuery(sql);
			
		while (rs.next()) {
			Division puestos= new Division();
			puestos.claveDivision= rs.getInt("PC_CVE_PUESTO");
			puestos.descripcionDivision=rs.getString("PC_DESC_PUESTO");
			puestos.horariovisible=rs.getString("PC_HORARIO");
			resultado.add(puestos);
		}

	} catch (Exception e) {
		//log.error("ERROR getDivisiones()= " + e);
		log.error(e.toString());
		//e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}

		
	}
	return resultado;
}



/**
 * Obtiene el catalogo de Horarios
 * @return ArrayList de objetos Horarios
 * @throws ServiceException
 * @throws SQLException
 * @throws NamingException
 */
public Vector getHorarios()
	throws ServiceException, SQLException, NamingException {

	Connection conn = null;
	String sql = "";
	Statement st = null;
	ResultSet rs = null;
	Vector resultado=new Vector();
		
	try {
		conn = ProveedorConexion.getConnection();
		st = conn.createStatement();
		sql =	"select PC_CVE_HORARIO,PC_DESC_HORARIO from pc_horario where pc_visual_SAEO='S' ";
								
		log.info("sql = " + sql);
		
		rs = st.executeQuery(sql);
			
		while (rs.next()) {
			Division horario= new Division();
			horario.claveDivision= rs.getInt("PC_CVE_HORARIO");
			horario.descripcionDivision=rs.getString("PC_DESC_HORARIO");
			horario.descripcionCorta="";
			resultado.add(horario);
		}

	} catch (Exception e) {
		//log.error("ERROR getDivisiones()= " + e);
		log.error(e.toString());
		//e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}

		
	}
	return resultado;
}



/**
 * Obtiene el catalogo de NiveldeVentas
 * @return ArrayList de objetos NiveldeVentas
 * @throws ServiceException
 * @throws SQLException
 * @throws NamingException
 */
public Vector getNiveldeVentas()
	throws ServiceException, SQLException, NamingException {

	Connection conn = null;
	String sql = "";
	Statement st = null;
	ResultSet rs = null;
	Vector resultado=new Vector();
		
	try {
		conn = ProveedorConexion.getConnection();
		st = conn.createStatement();
		sql =	"select PC_NIVEL, to_char(PC_CUOTA,'$99,999,990.00') PC_CUOTA from pc_nivel_cuotas ";
								
		log.info("sql = " + sql);
		
		rs = st.executeQuery(sql);
			
		while (rs.next()) {
			Division nivel= new Division();
			nivel.claveDivision= rs.getInt("PC_NIVEL");
			nivel.descripcionDivision=rs.getString("PC_CUOTA");
			nivel.descripcionCorta="";
			resultado.add(nivel);
		}

	} catch (Exception e) {
		//log.error("ERROR getDivisiones()= " + e);
		log.error(e.toString());
		//e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}

		
	}
	return resultado;
}



/**
 * Obtiene el catalogo de Empresas
 * @return ArrayList de objetos Empresas
 * @throws ServiceException
 * @throws SQLException
 * @throws NamingException
 */
public Vector getEmpresas()
	throws ServiceException, SQLException, NamingException {

	Connection conn = null;
	String sql = "";
	Statement st = null;
	ResultSet rs = null;
	Vector resultado=new Vector();
		
	try {
		conn = ProveedorConexion.getConnection();
		st = conn.createStatement();
		sql =	"select PC_CVE_EMPRESA,PC_DESC_EMPRESA from pc_empresas  order by PC_DESC_EMPRESA desc";
								
		log.info("sql = " + sql);
		
		rs = st.executeQuery(sql);
			
		while (rs.next()) {
			Division empresas= new Division();
			empresas.claveDivision= rs.getInt("PC_CVE_EMPRESA");
			empresas.descripcionDivision=rs.getString("PC_DESC_EMPRESA");
			empresas.descripcionCorta="";
			resultado.add(empresas);
		}

	} catch (Exception e) {
		//log.error("ERROR getDivisiones()= " + e);
		log.error(e.toString());
		//e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}

		
	}
	
	return resultado;
}
	
/**
 * Obtiene el catalogo de PVT
 * @return ArrayList de objetos PVT
 * @throws ServiceException
 * @throws SQLException
 * @throws NamingException
 */
public Vector getPuntosdeVenta()
	throws ServiceException, SQLException, NamingException {

	Connection conn = null;
	String sql = "";
	Statement st = null;
	ResultSet rs = null;
	Vector resultado=new Vector();
		
	try {
		conn = ProveedorConexion.getConnection();
		st = conn.createStatement();
		sql =	 " SELECT pc_cve_ptoventas, PC_NOM_PTOVENTAS" +			" FROM pc_punto_ventas where pc_STATUS='ALTA'" +			" ORDER BY PC_NOM_PTOVENTAS desc";
								
		log.info("sql = " + sql);
		
		rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Division empresas= new Division();
			empresas.claveString=rs.getString("pc_cve_ptoventas");
			empresas.descripcionDivision=rs.getString("PC_NOM_PTOVENTAS");
			empresas.descripcionCorta="";
			resultado.add(empresas);
		}
		
		log.info("cuantos="+resultado.size());

	} catch (Exception e) {
		//log.error("ERROR getDivisiones()= " + e);
		log.error(e.toString());
		//e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}

		
	}
	return resultado;
}

	/**
	 * Lista de los PDV del Canal de Distribuidotres UNE-T
	 * @return Vector de objetos PVT
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public Vector getPuntosdeVentaCanalDistUneT()
		throws ServiceException, SQLException, NamingException {
	
		Connection conn = null;
		String sql = new String();
		Statement st = null;
		ResultSet rs = null;
		Vector resultado = new Vector();
			
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "SELECT pc_cve_ptoventas, pc_nom_ptoventas " + 
				"    FROM pc_punto_ventas " +
				"   WHERE pc_cve_canal IN (SELECT pc_cve_canal " +
				"                            FROM pc_canal " +
				"                           WHERE pc_cve_tp_canal = '26' AND pc_status = 'ALTA') " +
				"     AND pc_status = 'ALTA' " +
				"ORDER BY pc_cve_canal DESC";
									
			log.info("sql = " + sql);

			rs = st.executeQuery(sql);
			
			Division pdvVo = new Division();
			while (rs.next()) {
				pdvVo = new Division();
				pdvVo.setPccveptoventas(rs.getString(1));
				pdvVo.setPcnomptoventas(rs.getString(2));
				resultado.add(pdvVo);
			}
			log.info("PDV UNE-T Encontrados = " + resultado.size());
	
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
	
			
		}
		return resultado;
	}

/**
 * Obtiene el catalogo de PVT apartir de una descripcion del nombre
 * @return ArrayList de objetos PVT
 * @throws ServiceException
 * @throws SQLException
 * @throws NamingException
 */
public Vector getPuntosdeVenta(String Division,String CveJefe,String NombreParcial)
	throws ServiceException, SQLException, NamingException {

	Connection conn = null;
	String sql = "";
	Statement st = null;
	ResultSet rs = null;
	Vector resultado=new Vector();
		
	try {
		conn = ProveedorConexion.getConnection();
		st = conn.createStatement();
	
	
	if(NombreParcial==null && CveJefe!=null){
		
		sql="SELECT   pc_punto_ventas.pc_cve_ptoventas pc_cve_ptoventas, "+
				 "pc_punto_ventas.pc_cve_referencia pc_cve_referencia, "+
				 "pc_punto_ventas.pc_cve_canal pc_cve_canal, "+
				 "pc_punto_ventas.pc_cve_region pc_cve_region, "+
				 "pc_punto_ventas.pc_nom_ptoventas pc_nom_ptoventas, "+
				 "pc_punto_ventas.pc_direccion pc_direccion, "+
				 "pc_punto_ventas.pc_colonia pc_colonia, "+
				 "pc_punto_ventas.pc_cve_ciudad pc_cve_ciudad, "+
				 "pc_punto_ventas.pc_cve_estado pc_cve_estado, "+
				 "pc_punto_ventas.pc_status pc_status, "+
				 "pc_punto_ventas.pc_cve_subdiv pc_cve_subdiv, "+
				 "pc_subdivision.pc_desc_subdiv pc_desc_subdiv, "+         
				 "pc_tipo_canal.pc_cve_tp_canal pc_cve_tp_canal, "+
				 "pc_canal.pc_desc_canal pc_desc_canal, "+
				 "pc_tipo_canal.pc_desc_tp_canal pc_desc_tp_canal "+
			"FROM pc_punto_ventas, pc_canal, pc_tipo_canal, pc_subdivision,pc_inter_vendedores_ptoventas inter "+ 
		   "WHERE (    (pc_canal.pc_cve_canal = pc_punto_ventas.pc_cve_canal) "+
				  "AND (pc_tipo_canal.pc_cve_tp_canal = pc_canal.pc_cve_tp_canal) "+
				  "AND (pc_subdivision.pc_cve_subdiv = pc_punto_ventas.pc_cve_subdiv) "+
				  "AND (pc_punto_ventas.pc_status = 'ALTA') "+
				  "AND inter.PC_CVE_PTOVENTAS=pc_punto_ventas.PC_CVE_PTOVENTAS "+
				  "AND inter.PC_CVE_VENDEDOR="+CveJefe+" "+
				 ") "+
		"ORDER BY pc_nom_ptoventas desc ";		
		
	
	}else if(NombreParcial!=null && Division==null){
		sql="SELECT   pc_punto_ventas.pc_cve_ptoventas pc_cve_ptoventas,"+
				 "pc_punto_ventas.pc_cve_referencia pc_cve_referencia,"+
				 "pc_punto_ventas.pc_cve_canal pc_cve_canal, "+
				 "pc_punto_ventas.pc_cve_region pc_cve_region, "+
				 "pc_punto_ventas.pc_nom_ptoventas pc_nom_ptoventas, "+
				 "pc_punto_ventas.pc_direccion pc_direccion, "+
				 "pc_punto_ventas.pc_colonia pc_colonia, "+
				 "pc_punto_ventas.pc_cve_ciudad pc_cve_ciudad, "+
				 "pc_punto_ventas.pc_cve_estado pc_cve_estado, "+
				 "pc_punto_ventas.pc_status pc_status, "+
				 "pc_punto_ventas.pc_cve_subdiv pc_cve_subdiv, "+
				 "pc_subdivision.pc_desc_subdiv pc_desc_subdiv, "+         
				 "pc_tipo_canal.pc_cve_tp_canal pc_cve_tp_canal, "+
				 "pc_canal.pc_desc_canal pc_desc_canal, "+
				 "pc_tipo_canal.pc_desc_tp_canal pc_desc_tp_canal "+
			"FROM pc_punto_ventas, pc_canal, pc_tipo_canal, pc_subdivision"+ 
		   "WHERE (    (pc_canal.pc_cve_canal = pc_punto_ventas.pc_cve_canal) "+
				  "AND (pc_tipo_canal.pc_cve_tp_canal = pc_canal.pc_cve_tp_canal) "+
				  "AND (pc_subdivision.pc_cve_subdiv = pc_punto_ventas.pc_cve_subdiv) "+
				  "AND (pc_punto_ventas.pc_status = 'ALTA') "+
				  "AND (UPPER (pc_punto_ventas.pc_nom_ptoventas) LIKE UPPER ('%"+NombreParcial+"%')) "+
				 ")"+
		"ORDER BY pc_nom_ptoventas desc";
		
	}else if(Division!=null){
		
		if(NombreParcial!=null){
			sql="SELECT   pc_punto_ventas.pc_cve_ptoventas pc_cve_ptoventas,"+
					 "pc_punto_ventas.pc_cve_referencia pc_cve_referencia,"+
					 "pc_punto_ventas.pc_cve_canal pc_cve_canal, "+
					 "pc_punto_ventas.pc_cve_region pc_cve_region, "+
					 "pc_punto_ventas.pc_nom_ptoventas pc_nom_ptoventas, "+
					 "pc_punto_ventas.pc_direccion pc_direccion, "+
					 "pc_punto_ventas.pc_colonia pc_colonia, "+
					 "pc_punto_ventas.pc_cve_ciudad pc_cve_ciudad, "+
					 "pc_punto_ventas.pc_cve_estado pc_cve_estado, "+
					 "pc_punto_ventas.pc_status pc_status, "+
					 "pc_punto_ventas.pc_cve_subdiv pc_cve_subdiv, "+
					 "pc_subdivision.pc_desc_subdiv pc_desc_subdiv, "+         
					 "pc_tipo_canal.pc_cve_tp_canal pc_cve_tp_canal, "+
					 "pc_canal.pc_desc_canal pc_desc_canal, "+
					 "pc_tipo_canal.pc_desc_tp_canal pc_desc_tp_canal "+
				"FROM pc_punto_ventas, pc_canal, pc_tipo_canal, pc_subdivision "+ 
			   "WHERE (    (pc_canal.pc_cve_canal = pc_punto_ventas.pc_cve_canal) "+
					  "AND (pc_tipo_canal.pc_cve_tp_canal = pc_canal.pc_cve_tp_canal) "+
					  "AND (pc_subdivision.pc_cve_subdiv = pc_punto_ventas.pc_cve_subdiv) "+
					  "AND (pc_punto_ventas.pc_status = 'ALTA') "+
					  "AND (pc_punto_ventas.pc_cve_subdiv) IN ("+Division+") "+
					  "AND (UPPER (pc_punto_ventas.pc_nom_ptoventas) LIKE UPPER ('%"+NombreParcial+"%')) "+
					 ")"+
			"ORDER BY pc_nom_ptoventas desc";
			
		}else{
			sql="SELECT   pc_punto_ventas.pc_cve_ptoventas pc_cve_ptoventas,"+
							 "pc_punto_ventas.pc_cve_referencia pc_cve_referencia,"+
							 "pc_punto_ventas.pc_cve_canal pc_cve_canal, "+
							 "pc_punto_ventas.pc_cve_region pc_cve_region, "+
							 "pc_punto_ventas.pc_nom_ptoventas pc_nom_ptoventas, "+
							 "pc_punto_ventas.pc_direccion pc_direccion, "+
							 "pc_punto_ventas.pc_colonia pc_colonia, "+
							 "pc_punto_ventas.pc_cve_ciudad pc_cve_ciudad, "+
							 "pc_punto_ventas.pc_cve_estado pc_cve_estado, "+
							 "pc_punto_ventas.pc_status pc_status, "+
							 "pc_punto_ventas.pc_cve_subdiv pc_cve_subdiv, "+
							 "pc_subdivision.pc_desc_subdiv pc_desc_subdiv, "+         
							 "pc_tipo_canal.pc_cve_tp_canal pc_cve_tp_canal, "+
							 "pc_canal.pc_desc_canal pc_desc_canal, "+
							 "pc_tipo_canal.pc_desc_tp_canal pc_desc_tp_canal "+
						"FROM pc_punto_ventas, pc_canal, pc_tipo_canal, pc_subdivision "+ 
					   "WHERE (    (pc_canal.pc_cve_canal = pc_punto_ventas.pc_cve_canal) "+
							  "AND (pc_tipo_canal.pc_cve_tp_canal = pc_canal.pc_cve_tp_canal) "+
							  "AND (pc_subdivision.pc_cve_subdiv = pc_punto_ventas.pc_cve_subdiv) "+
							  "AND (pc_punto_ventas.pc_status = 'ALTA') "+
							  "AND (pc_punto_ventas.pc_cve_subdiv) IN ("+Division+") ) "+
							 ")"+
					"ORDER BY pc_nom_ptoventas desc";
			
		}
		
				
	}	
	
		log.info("sql = " + sql);
		rs = st.executeQuery(sql);
		
		while (rs.next()) {
			Division pvt= new Division();
			pvt.pccveptoventas=rs.getString("pc_cve_ptoventas");
			pvt.pccvereferencia=rs.getString("pc_cve_referencia");
			pvt.pccvecanal=rs.getString("pc_cve_canal");
			pvt.pccveregion=rs.getString("pc_cve_region");
			pvt.pcnomptoventas=rs.getString("pc_nom_ptoventas");
			pvt.pcdireccion=rs.getString("pc_direccion");
			pvt.pccolonia=rs.getString("pc_colonia");
			pvt.pccveciudad=rs.getString("pc_cve_ciudad");
			pvt.pccveestado=rs.getString("pc_cve_estado");
			pvt.pcstatus=rs.getString("pc_status");
			pvt.pccvesubdiv=rs.getInt("pc_cve_subdiv");
			pvt.pcdescsubdiv=rs.getString("pc_desc_subdiv");
			pvt.pccvetpcanal=rs.getString("pc_cve_tp_canal");
			pvt.pcdesccanal=rs.getString("pc_desc_canal");
			pvt.pcdesctpcanal=rs.getString("pc_desc_tp_canal");
			resultado.add(pvt);
		}
		
		log.info("cuantos="+resultado.size());

	} catch (Exception e) {
		//log.error("ERROR getDivisiones()= " + e);
		log.error(e.toString());
		//e.printStackTrace();
	} finally {
		if (rs != null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		if (conn != null) {
			conn.close();
		}

		
	}
	return resultado;
}


	/**
	 * Obtiene el catalogo de PVT apartir de una descripcion del nombre
	 * @return ArrayList de objetos PVT
	 * @throws ServiceException
	 * @throws SQLException
	 * @throws NamingException
	 */
	public Vector getJefes(String Nombre,String numEmpleado)
		throws ServiceException, SQLException, NamingException {
	
		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		Vector resultado=new Vector();
			
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			
			sql ="SELECT  '' ||v.pc_cve_vendedor  PC_CVE_VENDEDOR"+
					",V.PC_CVE_EMP_REF  PC_CVE_EMP_REF"+
				    ", v.pc_nom_vendedor|| ' '|| v.pc_ape_paterno|| ' '|| v.pc_ape_materno PC_NOMBRE_VENDEDOR "+
					",v.pc_cve_puesto PC_CVE_PUESTO "+
					", p.pc_desc_puesto pc_desc_puesto "+        
				    ", v.pc_status PC_ESTATUS "+
				    ", to_char(v.pc_fch_alta,'dd/mm/yyyy') PC_FECH_ALTA "+
				    ", to_char(v.pc_fch_baja,'dd/mm/yyyy') PC_FECH_BAJA "+
				    ", v.PC_CVE_SUBDIV CVE_DIVISION "+
				    ", s.pc_desc_subdiv DESC_DIVISION "+
				"FROM pc_vendedores v, "+
					"pc_puestos p, "+
					"pc_subdivision s "+
				"WHERE ( p.pc_cve_puesto = v.pc_cve_puesto "+
			   	"AND v.pc_cve_subdiv = s.pc_cve_subdiv "+
				"AND (" +				"((TRIM (v.pc_ape_paterno)|| ' '|| TRIM (v.pc_ape_materno)|| ' '|| TRIM (v.pc_nom_vendedor)) LIKE TRIM(REPLACE(upper('"+Nombre+"'),' ','%'))           ) "+
			        "OR ((   TRIM (v.pc_nom_vendedor)|| ' '|| TRIM (v.pc_ape_paterno)|| ' '|| TRIM (v.pc_ape_materno)) LIKE TRIM(REPLACE(upper('"+Nombre+"'),' ','%'))  )   " +				"OR ((   TRIM (v.pc_nom_vendedor) LIKE TRIM(REPLACE(upper('"+Nombre+"'),' ','%'))  )       ) "+
				"OR ((   TRIM (v.PC_APE_PATERNO) LIKE TRIM(REPLACE(upper('"+Nombre+"'),' ','%'))  )       ) "+
				"OR ((   TRIM (v.PC_APE_MATERNO) LIKE TRIM(REPLACE(upper('"+Nombre+"'),' ','%'))  )       ) "+										  
				"OR ((   TRIM (v.PC_NOM_VENDEDOR)|| ' '|| TRIM (v.PC_APE_PATERNO) LIKE TRIM(REPLACE(upper('"+Nombre+"'),' ','%'))  )       ) "+	
				"OR ((   TRIM (v.PC_NOM_VENDEDOR)|| ' '|| TRIM (v.PC_APE_MATERNO) LIKE TRIM(REPLACE(upper('"+Nombre+"'),' ','%'))  )       ) "+
				"OR ((   TRIM (v.PC_APE_PATERNO)|| ' '|| TRIM (v.PC_APE_MATERNO) LIKE TRIM(REPLACE(upper('"+Nombre+"'),' ','%'))  )       ) "+
			       	"    ) "+
				")OR  V.PC_CVE_EMP_REF in('"+numEmpleado+"') "+
				"AND p.pc_cve_puesto = v.pc_cve_puesto "+
				"AND v.pc_cve_subdiv = s.pc_cve_subdiv ";
									
			log.info("sql = " + sql);
			
			rs = st.executeQuery(sql);
			
			while (rs.next()) {
				Empleado empleado = new Empleado();
						empleado.idUnico = rs.getString("PC_CVE_VENDEDOR");
				        empleado.cveempref = rs.getString("PC_CVE_EMP_REF");
						empleado.nombreCompleto = rs.getString("PC_NOMBRE_VENDEDOR");
						empleado.clavePuesto = rs.getInt("PC_CVE_PUESTO");
						empleado.descripcionPuesto = rs.getString("PC_DESC_PUESTO");
						empleado.estatusHuellaS=rs.getString("PC_ESTATUS");
						empleado.fechaalta=rs.getString("PC_FECH_ALTA");
						empleado.fechabaja=rs.getString("PC_FECH_BAJA");
						empleado.cvedivision=rs.getString("CVE_DIVISION");
						empleado.descdivision=rs.getString("DESC_DIVISION");
				resultado.add(empleado);
			}
			
			log.info("cuantos="+resultado.size());
	
		} catch (Exception e) {
			//log.error("ERROR getDivisiones()= " + e);
			log.error(e.toString());
			//e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
	
			
		}
		return resultado;
	}

	public String getPcCveCanal(String nombreCanal)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		String valPcCveCanal = null;

		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "SELECT pc_cve_canal FROM pc_canal " +				"WHERE TRIM(UPPER (pc_desc_canal)) LIKE TRIM(UPPER ('%" + nombreCanal + "%'))";
			log.info("sql = " + sql);
			rs = st.executeQuery(sql);
			if (rs.next())
				valPcCveCanal = rs.getString("pc_cve_canal");
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return valPcCveCanal;
	}

	public String getDivision(String cveDivision)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		String nomDivision = null;
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "SELECT pc_desc_div FROM pc_division WHERE pc_cve_div = " + cveDivision;
			log.info("sql = " + sql);
			rs = st.executeQuery(sql);
			if (rs.next())
				nomDivision = rs.getString("pc_desc_div");
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return nomDivision;
	}
	
	public String getEstado(String cveEstado)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		String nomEstado = null;
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "SELECT pc_desc_estado FROM pc_estados WHERE pc_cve_estado = " + cveEstado;
			log.info("sql = " + sql);
			rs = st.executeQuery(sql);
			if (rs.next())
				nomEstado = rs.getString("pc_desc_estado");
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return nomEstado;
	}

	public String getCiudad(String cveCiudad)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		String nomCiudad = null;
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "SELECT pc_desc_ciudad FROM pc_cat_ciudad WHERE pc_cve_ciudad = " + cveCiudad;
			log.info("sql = " + sql);
			rs = st.executeQuery(sql);
			if (rs.next())
				nomCiudad = rs.getString("pc_desc_ciudad");
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null) rs.close();
			if (st != null) st.close();
			if (conn != null) conn.close();
		}
		return nomCiudad;
	}

	public String getRegion(String cveRegion)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		String sql = "";
		Statement st = null;
		ResultSet rs = null;
		String nomRegion = null;
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "SELECT DISTINCT pc_cve_region FROM pc_cat_region WHERE pc_cve_region = '" + cveRegion + "'";
			log.info("sql = " + sql);
			rs = st.executeQuery(sql);
			if (rs.next())
				nomRegion = rs.getString("pc_cve_region");
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return nomRegion;
	}

	public LogInfoVo validaLoginUsrSis(String idUsuario, String idSistema)
		throws ServiceException, SQLException, NamingException {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		LogInfoVo logInfoVo = new LogInfoVo();
		
		try {
			conn = ProveedorConexion.getConnection();
			st = conn.createStatement();
			sql = "SELECT v.pc_cve_vendedor, v.pc_cve_superior, "
					+ "       TRIM(v.pc_nom_vendedor || ' ' || v.pc_ape_paterno || ' ' || v.pc_ape_materno) pc_nombre_usuario, "
					+ "       v.pc_estatus_huella, v.pc_status, "
					+ "       v.pc_huella_type, v.pc_user_gs, "
					+ "       v.pc_tipo_login "
					+ "  FROM pc_vendedores v, pc_mapeo_vendedor m, pc_puestos p "
					+ " WHERE m.pc_cve_reg_venta = '" + idUsuario + "' "
					+ "   AND m.pc_sistema = '" + idSistema + "' "
					+ "   AND v.pc_cve_vendedor = m.pc_cve_vendedor "
					+ "   AND v.pc_cve_puesto = p.pc_cve_puesto(+)";

			log.info("sql = " + sql);
			rs = st.executeQuery(sql);
			if (rs.next()) {
				logInfoVo.setCveVendedor(rs.getString("pc_cve_vendedor"));
				logInfoVo.setCveSuperior(rs.getString("pc_cve_superior"));
				logInfoVo.setNombreUsuario(rs.getString("pc_nombre_usuario"));
				logInfoVo.setEstatusHuella(new Integer (rs.getInt("pc_estatus_huella")));
				logInfoVo.setStatus(rs.getString("pc_status"));
				logInfoVo.setTipoHuella(new Integer (rs.getString("pc_huella_type")));
				logInfoVo.setUserGS(rs.getString("pc_user_gs"));
				logInfoVo.setTipoLogin(new Integer(rs.getString("pc_tipo_login")));
			}
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (conn != null)
				conn.close();
		}
		return logInfoVo;
	}
}
