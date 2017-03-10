package mx.com.iusacell.catalogo.bd;


import java.sql.*;

import javax.sql.*;
import javax.naming.*;
import java.util.Hashtable;


import org.apache.log4j.Logger;



	public class ProveedorConexion{
		protected static final Logger logger = Logger.getLogger(ProveedorConexion.class);
		
		public static Connection getConnection(String driver, String database, String username, String password) throws ClassNotFoundException, SQLException{
			Class.forName(driver); //load the driver
			return DriverManager.getConnection(database, username, password); //connect to the db
		}

		/*public static Connection getConnection(){
			/*Connection conn = null;
			try{
				Context ctx = new InitialContext();
				if(ctx == null )
					throw new Exception("No hay Context disponible");

				DataSource ds = null;
				try {
					ds = (DataSource)ctx.lookup("java:jdbc/Esiscom");
				} catch (Exception e) {
					try{
						ds = (DataSource)ctx.lookup("java:jdbc/Esiscom");	
					}catch(Exception ee){
						Class.forName("oracle.jdbc.driver.OracleDriver");
						return (DriverManager.getConnection("jdbc:oracle://172.19.4.201:1521/esiscom","READONLY","lectura"));
					}
				}
				if (ds == null) {
					throw new Exception("No se encuentra el Datasource");
				}
				conn = ds.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
			Connection conn = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			}
			catch (Exception e){
			}
			
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@172.19.235.201:1521:esiscomdes","prodsc","dprodsc");
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@172.19.4.201:1521:esiscom","READONLY","lectura");
			}
			catch (SQLException e){

			}
			
			return conn;
		}*/
		
		public static Connection getConnection() throws SQLException {
			DataSource ds = null;
//			Context ctx;
			try {

				//Hashtable env = new Hashtable();
				//env.put(Context.INITIAL_CONTEXT_FACTORY,"com.ibm.websphere.naming.WsnInitialContextFactory");
				InitialContext ctx = new InitialContext();
				//ds = (DataSource) ctx.lookup("java:comp/env/admincatalogo");
				if(ctx == null)
					logger.error("Error no existe contexto");
				//else
					//logger.info("Existe contexto " + ctx.toString());
				//Context envCtx = (Context) ctx.lookup("java:/");
				//ds = (DataSource)envCtx.lookup("jdbc/catalogo");
				Context context = new InitialContext();
				ds = (javax.sql.DataSource) context.lookup("jdbc/catalogo");
				
//				ctx = new InitialContext();
//				ds = (DataSource) ctx.lookup("jdbc/catalogo");
			} catch (NamingException e) {
				logger.error("error al obtener el DataSource " + e.getMessage());
				e.printStackTrace();
				return null;
			}
			return ds.getConnection();
		}
	}