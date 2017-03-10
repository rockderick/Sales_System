/*
 * Creado el Apr 7, 2005
 *
 */
package mx.com.iusacell.catalogo.servlet;

/**
 * @author Dvazqueza
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class ProveedorConexion {


	public static Connection getConnection(String driver, String tsn_name, String server_name, String port_number, String db_username, String db_password) 
		throws ClassNotFoundException, SQLException, Exception{
		
		Connection conn = null;
		try{
			if(driver.equals("")) driver = "oracle.jdbc.driver.OracleDriver";
			String database = driver.indexOf("oracle")!= (-1) ? tsn_name:"jdbc:oracle:thin://"+ server_name +":"+ port_number + "/"+ tsn_name;
			//if(Constantes.DEBUG_MESSAGES)
			Class.forName(driver); //load the driver
			conn = DriverManager.getConnection("jdbc:oracle:thin://"+ server_name +":"+ port_number + "/"+ tsn_name,db_username,db_password);
		}catch(ClassNotFoundException cnfe){
				throw new ClassNotFoundException("El driver es incorrecto");					
		}catch(SQLException sqle){
				throw new SQLException("Error al obtener la conexion");					
		}catch(Exception no_directds){
			throw new Exception("No se encuentra el Datasource");					
		}
		return conn;
	}
}