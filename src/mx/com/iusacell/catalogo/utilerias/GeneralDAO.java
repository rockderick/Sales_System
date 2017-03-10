/*
 * Created on Nov 29, 2016
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mx.com.iusacell.catalogo.utilerias;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.StringTokenizer;
import mx.com.iusacell.catalogo.bd.ProveedorConexion;
import mx.com.iusacell.catalogo.excepciones.CatalogoAppException;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;
import org.apache.log4j.Logger;

public class GeneralDAO {
	protected static final Logger logger = Logger.getLogger(GeneralDAO.class);
	private static boolean timestampConNanos;
	private ResultSetMetaData data;
	private int cols;

	static {
		try {
			Timestamp.class.getDeclaredMethod("getTime", new Class[0]);
			timestampConNanos = true;
			logger.info("Timestamp incluye nanos");
		} catch (NoSuchMethodException e) {
			timestampConNanos = false;
			logger.info("Timestamp NO incluye nanos");
		}
	}

	public Object findValue(int idQuery, Object[] argumentos) {
		Connection conn = null;
		ResultSet results = null;
		Object object = null;
		String sqlText = getQuery(idQuery, argumentos);
		 System.out.println("SQLText findValue: "+sqlText);
		try {
			out(sqlText);
			conn = ProveedorConexion.getConnection();
			if (conn != null) {
				Statement sql = conn.createStatement();
				results = sql.executeQuery(sqlText);
				if ((results != null) && (results.next()))
					object = results.getObject(1);
			}
		} catch (SQLException e) {
			throw new CatalogoSystemException("SQL Error en findValue", e);
		} finally {
			cerrarConexion(results, conn);
		}
		return object;
	}

	public Object findValueObject(int idQuery, Object[] argumentos, Class claseVO) {
		return findValueObject(getQuery(idQuery, argumentos), claseVO);
	}

	public ArrayList findValueObjectsArray(int idQuery, Object[] argumentos, Class claseVO) {
		return findValueObjectsArray(getQuery(idQuery, argumentos), claseVO);
	}

	public ArrayList findValueObjectsArray(int idQuery, Object[] argumentos, Class claseVO, int limit, int offset) {
		return findValueObjectsArray(getQuery(idQuery, argumentos, limit, offset), claseVO);
	}

	public ArrayList findValueObjectsArraySP(int idQuery, Object[] argumentos, Class claseVO) {
		return findValueObjectsArraySP(getStoredProcedure(idQuery), argumentos, claseVO);
	}

	public int getCount(int idQuery, Object[] argumentos) {
		return getCount(getQuery(idQuery, argumentos));
	}

	public Object findValueObject(String sqlText, Class claseVO)
  {
    Connection conn = null;
    ResultSet results = null;
    Object object = null;
    try {
      out(sqlText);
      conn = ProveedorConexion.getConnection();
      if (conn != null) {
        Statement sql = conn.createStatement();
        results = sql.executeQuery(sqlText);
        object = copyData(results, claseVO); 
      }
      logger.info("conn es nulo");
     
    }
    catch (SQLException e) {
      throw new CatalogoSystemException("SQL Error en findValueObject", e);
    } finally {
      cerrarConexion(results, conn);
    }
    return object;
  }

	public void executeThisQuery(int idQuery, Object[] argumentos)
  {
    String query = getQuery(idQuery, argumentos);

    Connection conn = null;
    ResultSet results = null;
    out(query);
    try {
      conn = ProveedorConexion.getConnection();
      if (conn != null) {
        Statement sql = conn.createStatement();
        int up = sql.executeUpdate(query); 
      }
      logger.info("conn es NULO");
    } catch (SQLException e) {
      throw new CatalogoSystemException("SQL Error en executeThisQuery", e);
    } finally {
      cerrarConexion(results, conn);
    }
  }

	public int executeThisQueryCheck(int idQuery, Object[] argumentos)
  {
    String query = getQuery(idQuery, argumentos);
    Connection conn = null;
    ResultSet results = null;
    out(query);
    int up = -1;
    try {
      conn = ProveedorConexion.getConnection();
      if (conn != null) {
        Statement sql = conn.createStatement();
        up = sql.executeUpdate(query);
      }
      logger.info("conn es NULO");
    } catch (SQLException e) {
      throw new CatalogoSystemException("SQL Error en executeThisQueryCheck", e);
    } finally {
      cerrarConexion(results, conn);
    }
    return up;
  }

	public ArrayList findValueObjectsArray(String sqlText, Class claseVO)
  {
    Connection conn = null;
    ResultSet results = null;
    ArrayList array = new ArrayList();
    //System.out.println("SQLText findValueObjectsArray: "+sqlText);
    try {
      out(sqlText);
      conn = ProveedorConexion.getConnection();
      if (conn != null) {
        Statement sql = conn.createStatement();
        results = sql.executeQuery(sqlText);
        array = copyDataArray(results, claseVO); 
      }
       logger.info("conn es NULO");
    }
    catch (SQLException e)
    {
      throw new CatalogoSystemException("SQL Error en findValueObjectsArray", e);
    } finally {
      cerrarConexion(results, conn);
    }
    return array;
  }

	public ArrayList findValueObjectsArraySP(String sqlText, Object[] argumentos, Class claseVO)
  {
    Connection conn = null;
    ResultSet results = null;
    ArrayList array = new ArrayList();
    try {
      out(sqlText);
      conn = ProveedorConexion.getConnection();
      if (conn != null)
      {
        CallableStatement sql = conn.prepareCall(sqlText);

        sql.registerOutParameter(1, -10);

        if (argumentos.length > 0) {
          for (int i = 0; i < argumentos.length; ++i) {
            setParameter(argumentos[i], sql, i + 2);
          }
        }
        sql.execute();
        results = (ResultSet)sql.getObject(1);

        array = copyDataArray(results, claseVO);
      }
      label162: logger.info("conn es NULO");
    }
    catch (SQLException e) {
      throw new CatalogoSystemException("SQL Error en findValueObjectsArray", e);
    } finally {
      cerrarConexion(results, conn);
    }
    return array;
  }

	public void setParameter(Object valor, CallableStatement sql, int idx) throws SQLException {
		if (valor instanceof java.util.Date) {
			sql.setDate(idx, (java.sql.Date) valor);
		}
		if (valor instanceof Double) {
			sql.setDouble(idx, ((Double) valor).doubleValue());
		}
		if (valor instanceof Float) {
			sql.setFloat(idx, ((Float) valor).floatValue());
		}
		if (valor instanceof Integer) {
			sql.setInt(idx, ((Integer) valor).intValue());
		}
		if (valor instanceof Long) {
			sql.setLong(idx, ((Long) valor).longValue());
		}
		if (valor == null) {
			sql.setNull(idx, 0);
		}
		if (valor instanceof Short) {
			sql.setShort(idx, ((Short) valor).shortValue());
		}
		if (valor instanceof String)
			sql.setString(idx, (String) valor);
	}

	public int getCount(String sqlText)
  {
    Connection conn = null;
    ResultSet results = null;
    int resultado = 0;

    StringBuffer sb = new StringBuffer();
    String sqlTextTmp = sqlText.toUpperCase();

    int orderIndex = sqlTextTmp.lastIndexOf("ORDER BY");
    if (orderIndex != -1) {
      sqlText = sqlText.substring(0, orderIndex);
    }
    sb.append("SELECT COUNT(*) FROM (");
    sb.append(sqlText);
    sb.append(") AS COUNT");
    sqlText = sb.toString();
    try
    {
      out(sqlText);
      conn = ProveedorConexion.getConnection();
      if (conn != null)
      {
        Statement sql = conn.createStatement();
        results = sql.executeQuery(sqlText);
        if ((results != null) && (results.next())) {
          resultado = results.getInt(1); 
        }
      }
      logger.info("conn es NULO");
    } catch (SQLException e) {
      throw new CatalogoSystemException("SQL Error en getCount", e);
    } finally {
      cerrarConexion(results, conn);
    }
    return resultado;
  }

	public Object copyData(ResultSet resultSet, Class claseVO) throws SQLException {
		Object resultado = null;

		if ((resultSet == null) || (claseVO == null)) {
			throw new CatalogoSystemException("ResultSet o Clase nula", null);
		}

		if (!(resultSet.next())) {
			return resultado;
		}

		ArrayList mapeoMetodos = getMetaDataMapeo(resultSet, claseVO);

		resultado = copyDataIntern(resultSet, claseVO, mapeoMetodos, this.data, this.cols);

		this.data = null;
		return resultado;
	}

	public ArrayList copyDataArray(ResultSet resultSet, Class claseVO) throws SQLException {
		ArrayList array = new ArrayList();
		Object resultado = null;

		if ((resultSet == null) || (claseVO == null)) {
			throw new CatalogoSystemException("ResultSet o Clase nula", null);
		}

		if (!(resultSet.next())) {
			return array;
		}

		ArrayList mapeoMetodos = getMetaDataMapeo(resultSet, claseVO);
		do {
			array.add(copyDataIntern(resultSet, claseVO, mapeoMetodos, this.data, this.cols));
		} while (resultSet.next());
		this.data = null;

		return array;
	}

	private ArrayList getMetaDataMapeo(ResultSet resultSet, Class claseVO) throws SQLException {
		this.data = resultSet.getMetaData();
		this.cols = this.data.getColumnCount();

		ArrayList mapeoMetodos = new ArrayList(this.cols);
		for (int i = 1; i <= this.cols; ++i) {
			String columnName = columnToAttribute(this.data.getColumnName(i));

			Class parameterClass = columnTypeToClass(this.data.getColumnType(i));

			String setMethod = attributeToSetMethod(columnName);

			if ((columnName.equals("timestamp")) || (columnName.indexOf("Timestamp") != -1)) {
				parameterClass = Long.TYPE;
			}
			Method method = getMethod(claseVO, setMethod, parameterClass);

			mapeoMetodos.add(method);
		}
		return mapeoMetodos;
	}

	private Object copyDataIntern(ResultSet resultSet, Class claseVO, ArrayList mapeoMetodos, ResultSetMetaData data,
			int cols) throws SQLException {
		Object object = null;
		try {
			object = claseVO.newInstance();

			for (int i = 1; i <= cols; ++i) {
				Method method = (Method) mapeoMetodos.get(i - 1);
				if (method != null)
					copyColumn(object, resultSet, data, i, method);
			}
		} catch (IllegalAccessException e) {
			throw new CatalogoSystemException("Error al crear objeto de " + claseVO, e);
		} catch (InstantiationException e) {
			throw new CatalogoSystemException("Error al crear objeto de " + claseVO, e);
		}

		return object;
	}

	private void copyColumn(Object object, ResultSet resultSet, ResultSetMetaData data, int col, Method method)
			throws SQLException {
		Class parameterClass = method.getParameterTypes()[0];
		Method jdk4Method = null;
		try {
			if ((parameterClass == Integer.TYPE) || (parameterClass == Integer.class)) {
				method.invoke(object, new Object[] { new Integer(resultSet.getInt(col)) });
				return;
			}
			if (parameterClass == String.class) {
				method.invoke(object, new Object[] { resultSet.getString(col) });
				return;
			}
			if (parameterClass == java.util.Date.class) {
				java.sql.Date sqlDate = resultSet.getDate(col);
				java.util.Date utilDate = null;
				if (sqlDate != null) {
					utilDate = new java.util.Date(sqlDate.getTime());
				}
				method.invoke(object, new Object[] { utilDate });
				return;
			}
			if ((parameterClass == Long.TYPE) || (parameterClass == Long.class)) {
				if (columnTypeToClass(data.getColumnType(col)) == java.util.Date.class) {
					if (timestampConNanos) {
						method.invoke(object, new Object[] { new Long(resultSet.getTimestamp(col).getTime()) });
						return;
					}
					method.invoke(object, new Object[] { new Long(resultSet.getTimestamp(col).getTime()
							+ resultSet.getTimestamp(col).getNanos() / 1000000) });
					return;
				}

				method.invoke(object, new Object[] { new Long(resultSet.getLong(col)) });
				return;
			}
			if ((parameterClass == Double.TYPE) || (parameterClass == Double.class))
				method.invoke(object, new Object[] { new Double(resultSet.getDouble(col)) });
		} catch (InvocationTargetException e) {
			throw new CatalogoSystemException("Error al invocar el metodo " + method.getName(), e);
		} catch (IllegalAccessException e) {
			throw new CatalogoSystemException("Error al invocar el metodo " + method.getName(), e);
		}
	}

	private String columnToAttribute(String columnName) {
		int posPunto = columnName.indexOf(46);

		if (posPunto >= 0) {
			columnName = columnName.substring(posPunto + 1, columnName.length());
		}

		int posUnderScore = columnName.indexOf(95);
		if (posUnderScore < 0) {
			return columnName.toLowerCase();
		}

		columnName = columnName.toLowerCase();
		while (((posUnderScore = columnName.indexOf(95)) >= 0) && (posUnderScore != columnName.length() - 1)) {
			columnName = columnName.substring(0, posUnderScore)
					+ Character.toUpperCase(columnName.charAt(posUnderScore + 1))
					+ ((columnName.length() >= posUnderScore + 3)
							? columnName.substring(posUnderScore + 2, columnName.length()) : "");
		}

		return columnName;
	}

	private Class columnTypeToClass(int columnType) {
		Class clase = null;
		switch (Math.abs(columnType)) {
		case 2:
			clase = Integer.TYPE;
			break;
		case 4:
		case 5:
			clase = Integer.TYPE;
			break;
		case 12:
			clase = String.class;
			break;
		case 91:
		case 93:
			clase = java.util.Date.class;
			break;
		case 8:
			clase = Double.TYPE;
		}

		return clase;
	}

	private String attributeToSetMethod(String attribute) {
		return "set" + Character.toUpperCase(attribute.charAt(0)) + attribute.substring(1, attribute.length());
	}

	private Method getMethod(Class claseVO, String methodName, Class parameterClass) {
		Method method = null;
		if (parameterClass == Integer.TYPE) {
			method = getMethodExactParameter(claseVO, methodName, Integer.TYPE);
			if (method == null)
				method = getMethodExactParameter(claseVO, methodName, Integer.class);
		} else if (parameterClass == Double.TYPE) {
			method = getMethodExactParameter(claseVO, methodName, Double.TYPE);
			if (method == null)
				method = getMethodExactParameter(claseVO, methodName, Double.class);
		} else if (parameterClass == Long.TYPE) {
			method = getMethodExactParameter(claseVO, methodName, Long.TYPE);
			if (method == null)
				method = getMethodExactParameter(claseVO, methodName, Long.class);
		} else {
			method = getMethodExactParameter(claseVO, methodName, parameterClass);
		}

		return method;
	}

	private Method getMethodExactParameter(Class claseVO, String methodName, Class parameterClass) {
		Method method = null;
		if (claseVO != Object.class) {
			try {
				method = claseVO.getDeclaredMethod(methodName, new Class[] { parameterClass });
			} catch (NoSuchMethodException localNoSuchMethodException) {
			}
			if (method == null) {
				return getMethodExactParameter(claseVO.getSuperclass(), methodName, parameterClass);
			}
		}
		return method;
	}

	public String getQuery(int idQuery, Object[] argumentos, int limit, int offset) throws CatalogoAppException {
		String sqlQuery = SQLQuery.getQuery(idQuery);
		logger.info("GeneralDAO ejecuto consulta con id: "+idQuery + " La consulta a ejecutar es: "+sqlQuery+ " y tiene: "+argumentos.length );
		if (sqlQuery == null) {
			throw new CatalogoSystemException("No se encontró el query con id " + idQuery, null);
		}

		sqlQuery = ajustarElementosDinamicos(sqlQuery, argumentos);
		try {
			for (int i = 0; i < argumentos.length; ++i)
				argumentos[i] = convierteAString(argumentos[i]);
		} catch (NullPointerException localNullPointerException) {
		}
		sqlQuery = MessageFormatIDS.format(sqlQuery, argumentos);
		int posNullCadena=0;
		while ((posNullCadena = sqlQuery.indexOf("'null'")) >= 0) {
			sqlQuery = sqlQuery.substring(0, posNullCadena) + "null" + 
			sqlQuery.substring(posNullCadena + 6, sqlQuery.length());
		}

		if (offset > 0) {
			sqlQuery = sqlQuery + " OFFSET " + offset;
		}

		return sqlQuery;
	}

	public String getQuery(int idQuery, Object[] argumentos) throws CatalogoAppException {
		return getQuery(idQuery, argumentos, -1, -1);
	}

	public String getStoredProcedure(int idQuery) throws CatalogoSystemException {
		String sqlQuery = SQLQuery.getQuery(idQuery);

		if (sqlQuery == null) {
			throw new CatalogoSystemException("No se encontró el query con id " + idQuery, null);
		}

		return sqlQuery;
	}

	private String ajustarElementosDinamicos(String query, Object[] argumentos) {
		int posElementoDinamico=0;
		while ((posElementoDinamico = query.indexOf("<%")) != -1) {
			
			StringTokenizer tokens = new StringTokenizer(query.substring(posElementoDinamico + 2));
			String condition = tokens.nextToken();

			int posElementoDinamicoFin = query.indexOf(condition + "%>", posElementoDinamico);
			if (posElementoDinamicoFin == -1) {
				throw new CatalogoSystemException("Query no cierra el elemento dinamico " + condition, null);
			}

			if (condicionValida(condition, argumentos)) {
				query = query.substring(0, posElementoDinamico) + " "
						+ query.substring(posElementoDinamico + 2 + condition.length(), posElementoDinamicoFin) + " "
						+ query.substring(posElementoDinamicoFin + condition.length() + 2);
			} else {
				query = query.substring(0, posElementoDinamico) + " "
						+ query.substring(posElementoDinamicoFin + condition.length() + 2);
			}
		}
		return query;
	}

	private boolean condicionValida(String condition, Object[] argumentos)
  {
	 boolean res = false;
	if (condition.length() == 0) {
      throw new CatalogoSystemException("La condicion del query es invalida " + condition, null);
    }

    boolean negation = condition.charAt(0) == '!';
    if (negation) {
      condition = condition.substring(1);
    }

    if (condition.length() == 0) {
      throw new CatalogoSystemException("La condicion del query es invalida " + condition, null);
    }
    StringTokenizer tokens = new StringTokenizer(condition, "!=<>", true);
    String numero = tokens.nextToken();
    int arg = Integer.parseInt(numero);

    String valor = null;
    try {
      if (tokens.hasMoreTokens()) {
        String operacion = tokens.nextToken();
        while (tokens.hasMoreTokens()) {
          String letra = tokens.nextToken();
          if (("!".equals(letra)) || (">".equals(letra)) || ("<".equals(letra)) || ("=".equals(letra)))
            operacion = operacion + letra;
          else
            valor = letra;
        }
       
      }

      String operacion = "=";
      valor = "true";
    
    int comparacion = valor.compareToIgnoreCase(convierteAStringConNull(argumentos[arg]));
   
    if ("=".equals(operacion))
      res = comparacion == 0;
    else if ("!=".equals(operacion))
      res = comparacion != 0;
    else if (">".equals(operacion))
      res = comparacion < 0;
    else if ("<".equals(operacion))
      res = comparacion > 0;
    else if (">=".equals(operacion))
      res = comparacion <= 0;
    else if ("<=".equals(operacion)) {
      res = comparacion >= 0;
    }

    if (negation) {
      res = !(res);
    }
    }
    catch (Exception e) {
        throw new CatalogoSystemException("Error al obtener la operacion en el elemento dinamico " + condition, null);
    }

    return res;
  }

	private String convierteAString(Object valor) {
		if (valor == null) {
			return null;
		}
		if (valor instanceof String) {
			return ((String) valor);
		}
		if (valor instanceof java.util.Date) {
			return Fecha.dateToStringDB((java.util.Date) valor);
		}
		return String.valueOf(valor);
	}

	private String convierteAStringConNull(Object valor) {
		if (valor == null) {
			return "null";
		}
		if (valor instanceof String) {
			return ((String) valor);
		}
		if (valor instanceof java.util.Date) {
			return Fecha.dateToStringDB((java.util.Date) valor);
		}
		return String.valueOf(valor);
	}

	protected void cerrarConexion(ResultSet results, Connection conn) {
		if (results != null)
			try {
				results.close();
			} catch (Exception localException) {
			}
		if (conn == null)
			return;
		try {
			conn.close();
		} catch (Exception localException1) {
		}
	}

	private static void out(Object cadena) {
		logger.info("query a ejecutar: " + cadena);
	}

	private void logQuery(int idQuery, String sqlQuery) {
		Connection conn = null;
		ResultSet results = null;
		String explain;
		try {
			conn = ProveedorConexion.getConnection();
			Statement sql = conn.createStatement();
			results = sql.executeQuery("explain " + sqlQuery);

			if ((results != null) && (results.next())) {
				explain = results.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cerrarConexion(results, conn);
		}
	}

	public boolean executeTran(int idQuery, Object[] argumentos, Connection conn) {
		boolean bandera = false;
		String query = getQuery(idQuery, argumentos);
		try {
			Statement sql = conn.createStatement();
			out(query);
			bandera = sql.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return bandera;
	}

	public int executeTransaccion(int idQuery, Object[] argumentos, Connection conn) {
		int i = 0;
		String query = getQuery(idQuery, argumentos);
		try {
			Statement sql = conn.createStatement();
			out(query);
			i = sql.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return i;
	}

	public String ejecutaTransaccion(int idQuery, Object[] argumentos, Connection conn) {
		int i = 0;
		String query = getQuery(idQuery, argumentos);
		try {
			Statement sql = conn.createStatement();

			i = sql.executeUpdate(query);
		} catch (Exception e) {
			return e.getMessage().toString();
		}
		return String.valueOf(i);
	}

	public int executeCuenta(int idQuery, Object[] argumentos, Connection conn) {
		int i = 0;
		ResultSet results = null;
		String query = getQuery(idQuery, argumentos);
		try {
			Statement sql = conn.createStatement();
			results = sql.executeQuery(query);
			if ((results != null) && (results.next()))
				i = results.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return i;
	}
}