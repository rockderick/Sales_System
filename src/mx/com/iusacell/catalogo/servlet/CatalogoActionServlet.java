/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package mx.com.iusacell.catalogo.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.iusacell.catalogo.modelo.Pc_AccionesVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;


public class CatalogoActionServlet extends ActionServlet {
	protected static final Logger logger = Logger
			.getLogger(CatalogoActionServlet.class);
	public static Set allowActions = new TreeSet();

	static {
		allowActions.add("Welcome.do");
		allowActions.add("Login.do");
		allowActions.add("LoginSubmit.do");
		allowActions.add("Logout.do");
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("unauthorized") != null)
			request.getSession().removeAttribute("unauthorized");
		if (!(isSessionAlive(request))) {
			request.setAttribute(
					"mensaje",
					"Usted no cuenta con los permisos suficientes para realizar esta acción. Su sesión ha expirado.");
			request.setAttribute("SESIONEXPIRADA", "La sesión expiró");
			request.getSession().invalidate();
			response.sendRedirect(request.getContextPath() + "/Login.do");
		} else {
			HttpSession session = request.getSession();
			String url = request.getRequestURI();
			url = url.substring(url.lastIndexOf(47) + 1);
			if (validar(session, url)) {
				if (url.equals("Logout.do")) {
					session.invalidate();
					request.getSession().setAttribute("unauthorized",
							"Ha salido de la sesion.");
					response.sendRedirect(request.getContextPath()
							+ "/Welcome.do");
				} else {
					super.service(request, response);
				}
			} else {
				request.getSession()
						.setAttribute("unauthorized",
								"Usted no cuenta con los permisos suficientes para realizar esta acción.");
				response.sendRedirect(request.getContextPath() + "/Welcome.do");
			}
		}
		response.sendRedirect(request.getContextPath()
				+ "/Welcome.do");
	
		
		
	}

	public boolean isSessionAlive(HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			String url = request.getRequestURI();
			url = url.substring(url.lastIndexOf(47) + 1);

			return ((allowActions.contains(url)) || (session
					.getAttribute("USER") != null));
		} catch (Exception e) {
			logger.error("Problemas validando la sesion " + e.toString());
		}
		return false;
	}

	public boolean validar(HttpSession session, String url)
  {
    try {
      if (!(allowActions.contains(url))) {
        UserSessionVO user = (UserSessionVO)session.getAttribute("USER");
        if (user == null) {
          return false;
        }

        ArrayList autorizado = user.getPcAcciones();
        Iterator itera = autorizado.iterator();

        while (itera.hasNext()) {
          Pc_AccionesVO accion = (Pc_AccionesVO)itera.next();
          if (url.indexOf(accion.getPcUrlAccion()) != -1) {
            session.removeAttribute("unauthorized");
            return true;
          }
        }
        
      }

      return true;
    }
    catch (Exception e) {
      logger.error("Error en validacion" + e.toString());
      return false;
    }
    
  }
}