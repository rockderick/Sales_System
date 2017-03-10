/*
 * Created on Feb 23, 2005
 *
 */
package mx.com.iusacell.catalogo.servlet;


import java.util.ArrayList;

import mx.com.iusacell.catalogo.utilerias.GeneralDAO;
import mx.com.iusacell.catalogo.utilerias.Q;
//Empieza el modelo de aplicacion
import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import mx.com.iusacell.catalogo.modelo.Pc_DivisionVO;
import mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO;
import mx.com.iusacell.catalogo.modelo.Pc_LoginVO;
import mx.com.iusacell.catalogo.modelo.Pc_AccionesVO;

import org.apache.log4j.Logger;

/**
 * @author Dvazqueza
 *
 */
public class ServletFacade {
	
	protected static final Logger logger = Logger.getLogger(ServletFacade.class);
	
	public ServletFacade() {
		super();
	}

	public static UserSessionVO getSesionUsuario(String user, int division, int subdivision, ArrayList subdivisiones) {
		GeneralDAO dao = new GeneralDAO();
		for(int i=0;i<subdivisiones.size();i++){
			Pc_SubdivisionVO subTemp = (Pc_SubdivisionVO)subdivisiones.get(i);
			int tmp = subTemp.getPcCveDiv();
		   if(tmp == subdivision){
		   	 subdivision = tmp;
		   	 division = tmp;
		   }
		}
		return (UserSessionVO) dao.findValueObject(Q.LOGIN_ROL, new Object[]{user, String.valueOf(division), String.valueOf(subdivision)},UserSessionVO.class);
		
	}

	public static Pc_LoginVO getLogin(String user) {
		GeneralDAO dao = new GeneralDAO();
		Pc_LoginVO loginObj = (Pc_LoginVO) dao.findValueObject(Q.LOGIN_ENTER, new Object[]{user},Pc_LoginVO.class);
		if(loginObj == null) {
			return null;
		}else if(loginObj.getPcUserid()==null){
			return null;			
		} else{
			return loginObj; 
		}
	}
	
	public static ArrayList getAcciones(int rol) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.LOGIN_ACCIONES, new Object[]{String.valueOf(rol)},Pc_AccionesVO.class);
	}
	

	public static String ValidaUsu(String clave) {
			GeneralDAO dao = new GeneralDAO();
			return (String)dao.findValue(Q.USUS_ADMIN, new Object[]{clave});
		}

	public static ArrayList getDivision(String user) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.LOGIN_DIVISIONES, new Object[]{user},Pc_DivisionVO.class);
	}

	public static ArrayList getSubdivision(String user) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(Q.LOGIN_SUBDIVISIONES, new Object[]{user},Pc_SubdivisionVO.class);
	}

	public static UserSessionVO getRol(String user) {
		GeneralDAO dao = new GeneralDAO();
		return (UserSessionVO) dao.findValueObject(Q.LOGIN_ROL_UNICO, new Object[]{user},UserSessionVO.class);
	}
	
	public static UserSessionVO getRoles(String user) {
			GeneralDAO dao = new GeneralDAO();
			return (UserSessionVO) dao.findValueObject(Q.ROL_USUARIO_INDISTINTO, new Object[]{user},UserSessionVO.class);
	}

/*
 * Utilitarias Generales
 * Se utilizan para sustituir algunas funciones particulares con versiones generales que reciben mas parametros
 * 
 */
 
   /**
	* Opera un Query de Tipo INSERT,DELETE,UPDATE usando el Objeto parametros
	* INSERT parametros -> VALUES(param0,param1,param2,...,paramN)
	* DELETE parametros -> WHERE {PRIMARY_KEY}=param0
	* UPDATE parametros -> SET  COL_NAME1 = param1, COL_NAME2 = param2,...,COL_NAMEn = paramN WHERE {PRIMARY_KEY}=param0 
	* @param int queryId
	* @param Object[] parametros
	* @return
	*/
	public static void affectDB(int queryId,Object[] parametros) {
		 GeneralDAO dao = new GeneralDAO();
		 dao.executeThisQuery(queryId, parametros);
	}

	/**
	 * Regresa un ArrayList con objetos de tipo clase donde
	 * queryId contiene un query de tipo select
	 * parametros contiene los parametros necesarios para ejecutar el query
	 * clase es un Bean que va a contener las columnas regresadas por el Query 
	 * @param int queryId
	 * @param Object[] parametros
	 * @param Class clase
	 * @return ArrayList
	 */
	public static ArrayList getTable(int queryId, Object[] parametros,Class clase) {
		GeneralDAO dao = new GeneralDAO();
		return dao.findValueObjectsArray(queryId, parametros, clase);
	}


}
