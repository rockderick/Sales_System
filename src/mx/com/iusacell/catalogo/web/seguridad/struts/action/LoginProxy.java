/*
 * Decompiled from SAEO project on Nov 22, 2016
 *
 */
package mx.com.iusacell.catalogo.web.seguridad.struts.action;

import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.TimeZone;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import mx.com.iusacell.catalogo.dao.AdminCatFacade;
import mx.com.iusacell.catalogo.excepciones.CatalogoSystemException;
import mx.com.iusacell.catalogo.modelo.Pc_LoginVO;
import mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO;
import mx.com.iusacell.catalogo.modelo.UserSessionVO;
import mx.com.iusacell.catalogo.servlet.ServletFacade;
import mx.com.iusacell.catalogo.utilerias.EncriptaBlowfish;
import mx.com.iusacell.catalogo.utilerias.MasterKey;
import mx.com.iusacell.catalogo.utilerias.Proxy;
import mx.com.iusacell.catalogo.utilerias.ValidacionLlaveVO;
import mx.com.iusacell.catalogo.web.personal.struts.action.PersonalMovProxy;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import sun.misc.BASE64Decoder;

/**
 * This class it it used as the main controller of the LoginAction class
 * and creates the navigation controls for the user according of
 * their permissions.
 * 
 * @author rj148m
 * @version: 22/11/2016/
 *
 */


public class LoginProxy extends Proxy {
	protected static final Logger logger = Logger.getLogger(LoginProxy.class);

	/*public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if (request.getAttribute("mensaje") != null)
			request.removeAttribute("mensaje");
		try {
			String obtenIP = request.getRemoteAddr();
			String usuario = null;

			Pc_LoginVO login = null;
			UserSessionVO user = null;
            
			logger.info("Entra a home ip=" + obtenIP);
			logger.info("User get from form =" + request.getParameter("pcUserid"));
			logger.info("Password get from form =" + request.getParameter("pcPassword"));
            
			/*String TokenBase64 = null;
			
			//if(request.getParameter("auth")!=null) //Added
			//{	
				//TokenBase64 = (request.getParameter("auth").equals("")) ? null : request.getParameter("auth");
			//}
			TokenBase64="PHVzZXJzPjx1c2VyIGh0dHBfaW1wZXJzb25hdGU9J0lVNDAzMjQ0ODQnIC8+PC91c2Vycz4=";
			//logger.info("Despues de LoginProxy TokenBase64: ");
			if (TokenBase64 == null) {
				request.setAttribute("mensaje", "Debe de iniciar sesion en Portal corporativo");
				logger.info("Error Token en null ");
				return mapping.findForward("error");
			}

			BASE64Decoder base = new BASE64Decoder();
			String usuLlaveMaestra = null;
			try {
				String xmlURLDecoder = URLDecoder.decode(TokenBase64);
				byte[] xmlDecodeBuffer = base.decodeBuffer(xmlURLDecoder);

				String xmlDecodeBufferEnString = "";

				xmlDecodeBufferEnString = new String(xmlDecodeBuffer, "ISO8859-1");

				String xmlString = xmlDecodeBufferEnString;
				xmlString = xmlString.replace('?', '>');

				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

				DocumentBuilder builder = factory.newDocumentBuilder();

				Document document = builder.parse(new InputSource(new StringReader(xmlString)));

				String exp = "/users/user/@http_impersonate";

				Node node = XPathAPI.selectSingleNode(document, exp);

				usuLlaveMaestra = node.getNodeValue();
				usuario = usuLlaveMaestra;
				logger.info("Usuario recuperado de llave Maestra" + usuLlaveMaestra);
			} catch (IOException e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "Error " + e);
				logger.info("1 Error Token [" + TokenBase64 + "]");
				return mapping.findForward("error");
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "Error " + e);
				logger.info("2 Error Token [" + TokenBase64 + "]");
				return mapping.findForward("error");
			} catch (SAXException e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "Error " + e);
				logger.info("3 Error Token [" + TokenBase64 + "]");
				return mapping.findForward("error");
			} catch (TransformerException e) {
				e.printStackTrace();
				request.setAttribute("mensaje", "Error " + e);
				logger.info("4 Error Token [" + TokenBase64 + "]");
				return mapping.findForward("error");
			} catch (Throwable t) {
				t.printStackTrace();
				request.setAttribute("mensaje", "Error " + t);
				logger.info("5 Error Token [" + TokenBase64 + "]");
				return mapping.findForward("error");
			}*/

			//logger.info("El usuario Rec= " + usuario);
			//usuario = usuario.toUpperCase();
			//usuario = "IU40324484"; administrador
			//usuario = "IU800102"; administrador
			//usuario = "IU950339"; con acceso
			//usuario = "CLAUDIA";  sin permisos
			//usuario = "EMARQUEZ4"; con permisos
			//usuario	= "719592"; sin permisos
			//usuario	= "719009"; sin permisos
			//usuario = "942268";
			//usuario = "IU710429" administrador contraseña xxx
			//logger.info("El usuario de hardcore" + usuario);
			/*usuario = request.getParameter("pcUserid");
			logger.info("El usuario UpRec= " + usuario);

			request.getSession().setAttribute("usuario", usuario);

			if (usuario != null) {
				if (request.getSession().getAttribute("LOGIN") != null)
				{
					login = (Pc_LoginVO) request.getSession().getAttribute("LOGIN");
					logger.info("Entra login get Attribute");
				}	
				else {
					logger.info("Entra login ServletFacade de Login");
					login = ServletFacade.getLogin(usuario);
					
				}

				if (login == null) {
					AdminCatFacade.accessLog(usuario, obtenIP, "USUARIO INTENTO INGRESAR SIN EXITO AL PORTAL");
					logger.info("El usuario " + usuario + " intento entrar sin exito");
					request.setAttribute("mensaje",
							"La combinación de nombre de Usuario y Contraseña es incorrecto intente de nuevo.");
				} else {
					AdminCatFacade.accessLog(usuario, obtenIP, "USUARIO INGRESO CON EXITO AL PORTAL");
					ArrayList divisiones = ServletFacade.getSubdivision(login.getPcUserid());

					if (divisiones.isEmpty()) {
						logger.info(
								"El usuario " + usuario + " intento entrar pero no estaba autorizado a las divisiones");
						request.setAttribute("mensaje",
								"El usuario no cuenta con permisos para utilizar la aplicación.");
					} else if (divisiones.size() > 1) {
						request.getSession().setAttribute("multiDivisiones", "1");
						String todasDivisiones = generaCadenaDivisiones(divisiones);

						Pc_SubdivisionVO temp = (Pc_SubdivisionVO) divisiones.get(0);
						int division = temp.getPcCveDiv();
						user = ServletFacade.getRol(login.getPcUserid());
						user.setPcValidadoConfig(true);

						logger.info("El usuario " + usuario + " entro a la aplicación");
						if (user != null) {
							if (user.getPcCveRol() == 0) {
								logger.info("El usuario " + usuario + " tiene permisos de Sistema");
								user.setPcCveDiv(0);
								user.setPcCveSubdiv(0);
								ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
								if (acciones.isEmpty()) {
									request.setAttribute("mensaje",
											"La cuenta con la que entro no tiene privilegios en la división.");
								} else {
									user.setPcAcciones(acciones);
									request.getSession().setAttribute("USER", user);
								}
							} else if (user.getPcCveRol() == 1) {
								logger.info("El usuario " + usuario + " tiene permisos de Administración");

								String admin = ServletFacade.ValidaUsu(usuario);
								logger.info("usuario admin =" + admin);

								if (admin.equals("1")) {
									request.getSession().setAttribute("Admin", user.getPcUserid());
									logger.info("usuario admin correcto");
								}
								ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
								if (acciones.isEmpty()) {
									request.setAttribute("mensaje",
											"La cuenta con la que entro no tiene privilegios en la división.");
								} else {
									user.setPcAcciones(acciones);
									request.getSession().setAttribute("USER", user);
								}
							} else if (user.getPcCveRol() == 4) {
								logger.info("El usuario " + usuario + " tiene permisos de Consulta");
								user.setPcCveDiv(0);
								user.setPcCveSubdiv(0);
								ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
								if (acciones.isEmpty()) {
									request.setAttribute("mensaje",
											"La cuenta con la que entro no tiene privilegios en la división.");
								} else {
									user.setPcAcciones(acciones);
									request.getSession().setAttribute("USER", user);
								}

								request.getSession().setAttribute("estiloCss", "administrador");
							} else {
								String validarSession = "Activa";
								Pc_SubdivisionVO subdivision = (Pc_SubdivisionVO) divisiones.get(0);
								user = ServletFacade.getSesionUsuario(login.getPcUserid(), subdivision.getPcCveDiv(),
										subdivision.getPcCveSubdiv(), divisiones);
								user.setPcCvesDivs(todasDivisiones);
								ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
								user.setPcAcciones(acciones);
								request.getSession().setAttribute("USER", user);
								request.getSession().setAttribute("validarSession", validarSession);

								request.getSession().setAttribute("estiloCss", "consultaDivisional");
								return mapping.findForward("home");
							}

						} else {
							request.getSession().setAttribute("LOGIN", login);
							request.setAttribute("DIVISION", divisiones);
							request.setAttribute("divisionUsuario", String.valueOf(division));
							request.setAttribute("USUARIODIV", usuario);
						}
					} else {
						Pc_SubdivisionVO subdivision = (Pc_SubdivisionVO) divisiones.get(0);
						String todasDivisiones = generaCadenaDivisiones(divisiones);
						user = ServletFacade.getSesionUsuario(login.getPcUserid(), subdivision.getPcCveDiv(),
								subdivision.getPcCveSubdiv(), divisiones);
						user.setPcCvesDivs(todasDivisiones);
						user.setPcValidadoConfig(PersonalMovProxy.validaUsuario(usuario));
						if (user == null) {
							request.setAttribute("mensaje",
									"La cuenta con la que entro no tiene permisos para usar esta división.");
						} else {
							ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
							if (acciones.isEmpty()) {
								logger.info("El usuario " + usuario + " no tiene permisos para entrar a la aplicacion");
								request.setAttribute("mensaje",
										"La cuenta con la que entro no tiene privilegios en la división.");
							} else {
								request.getSession().setAttribute("estiloCss", "consultaDivisional");
								logger.info(
										"El usuario " + usuario + " ha sido autenticado y tiene permisos correctos");
								user.setPcAcciones(acciones);

								request.getSession().setAttribute("USER", user);
							}
						}
					}
				}
			} else {
				AdminCatFacade.accessLog(usuario, obtenIP, "USUARIO INTENTO INGRESAR SIN EXITO AL PORTAL");
				request.setAttribute("mensaje",
						"La combinación de nombre de Usuario y Contraseña es incorrecto intente de nuevo.");
			}

			if ((user != null) && (user.getPcCveRol() == 0)) {
				logger.info("rol=0");

				request.getSession().removeAttribute("USER");
				user.setPcCveDiv(0);
				user.setPcCveSubdiv(0);
				request.getSession().setAttribute("USER", user);
				String validarSession = "Activa";
				request.getSession().setAttribute("validarSession", validarSession);

				return mapping.findForward("sysadmin");
			}

			logger.info("rol dif 0");

			String validarSession = "Activa";

			if ((user.getPcCveDiv() == 0) || (user.isPcValidadoConfig()))
				request.getSession().setAttribute("estiloCss", "administrador");
			else {
				request.getSession().setAttribute("estiloCss", "consultaDivisional");
			}

			request.getSession().setAttribute("validarSession", validarSession);
			return mapping.findForward("home");
		} catch (CatalogoSystemException cse) {
			logger.error("CatalogoSystemException Error " + cse.toString());
			return mapping.findForward("error");
		} catch (Exception e) {
			logger.error("Exception Error " + e.toString());
		}
		return mapping.findForward("error");
	}*/
	
	
	/**
	 * Process the specified HTTP request, and create the corresponding HTTP response 
	 * (or forward to another web component that will create it). Return an ActionForward
	 *  instance describing where permission was granted to the user in the SAEO system
	 *
	 * @param form The optional ActionForm bean for this request (if any)
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are processing
	 * @return
	 */
	public ActionForward loginHome(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		Pc_LoginVO login = null;
		UserSessionVO user = null;
		String obtenIP = request.getRemoteAddr();
		
		
		try {
			
			//Gets the user and the password from the LoginForm
		    String usuario = request.getParameter("pcUserid");
		    String password =  request.getParameter("pcPassword");
			request.getSession().setAttribute("usuario", usuario);
			
					    
			
			/** ************************* */
			/* Validates (Llave Maestra) */
			/** ************************* */
			MasterKey mKey = new MasterKey();
			InetAddress address = InetAddress.getLocalHost();
			
			boolean isvalidMasterKeyUsr=false;
			if(usuario!=null & password!=null )
					isvalidMasterKeyUsr = mKey.validateKey(usuario, password, address.getHostAddress());
			
			/** ************************* */
			/* End of Validation (Llave Maestra) */
			/** ************************* */
			
			
			//If the Global logon it is true then begins 
			// the validation in order to get the permissions
			// for each user
		
			if(isvalidMasterKeyUsr)
			{
				
				if (usuario != null) {
					logger.info("El usuario es diferente de nulo");
					if (request.getSession().getAttribute("LOGIN") != null)
					{
						login = (Pc_LoginVO) request.getSession().getAttribute("LOGIN");
						logger.info("Entra login get Attribute");
					}	
					else {
						logger.info("Entra login ServletFacade de Login");
						login = ServletFacade.getLogin(usuario);
						
					}
	
					if (login == null) {
						AdminCatFacade.accessLog(usuario, obtenIP, "USUARIO INTENTO INGRESAR SIN EXITO AL PORTAL");
						logger.info("El usuario " + usuario + " intento entrar sin exito");
						request.setAttribute("mensaje",
								"La combinación de nombre de Usuario y Contraseña es incorrecto intente de nuevo.");
					} else {
						AdminCatFacade.accessLog(usuario, obtenIP, "USUARIO INGRESO CON EXITO AL PORTAL");
						logger.info("Ingreso al portal y voy a obtener divisiones");
						ArrayList divisiones = ServletFacade.getSubdivision(login.getPcUserid());
	
						if (divisiones.isEmpty()) {
							logger.info(
									"El usuario " + usuario + " intento entrar pero no estaba autorizado a las divisiones");
							request.setAttribute("mensaje",
									"El usuario no cuenta con permisos para utilizar la aplicación.");
						} else if (divisiones.size() > 1) {
							request.getSession().setAttribute("multiDivisiones", "1");
							String todasDivisiones = generaCadenaDivisiones(divisiones);
	
							Pc_SubdivisionVO temp = (Pc_SubdivisionVO) divisiones.get(0);
							int division = temp.getPcCveDiv();
							user = ServletFacade.getRol(login.getPcUserid());
							user.setPcValidadoConfig(true);
	
							logger.info("El usuario " + usuario + " entro a la aplicación");
							System.out.println("El usuario tiene permisos de rol: "+user.getPcCveRol());
							if (user != null) {
								if (user.getPcCveRol() == 0) {
									logger.info("El usuario " + usuario + " tiene permisos de Sistema");
									user.setPcCveDiv(0);
									user.setPcCveSubdiv(0);
									ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
									if (acciones.isEmpty()) {
										request.setAttribute("mensaje",
												"La cuenta con la que entro no tiene privilegios en la división.");
									} else {
										user.setPcAcciones(acciones);
										request.getSession().setAttribute("USER", user);
									}
								} else if (user.getPcCveRol() == 1) {
									logger.info("El usuario " + usuario + " tiene permisos de Administración");
									System.out.println("El usuario " + usuario + " tiene permisos de Administración");
									String admin = ServletFacade.ValidaUsu(usuario);
									//String admin="1";
									logger.info("usuario admin =" + admin);
									System.out.println("usuario admin =" + admin);
	
									if (admin.equals("1")) {
										request.getSession().setAttribute("Admin", user.getPcUserid());
										logger.info("usuario admin correcto");
										System.out.println("usuario admin correcto");
									}
									ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
									if (acciones.isEmpty()) {
										request.setAttribute("mensaje",
												"La cuenta con la que entro no tiene privilegios en la división.");
									} else {
										user.setPcAcciones(acciones);
										request.getSession().setAttribute("USER", user);
									}
								} else if (user.getPcCveRol() == 4) {
									logger.info("El usuario " + usuario + " tiene permisos de Consulta");
									user.setPcCveDiv(0);
									user.setPcCveSubdiv(0);
									ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
									if (acciones.isEmpty()) {
										request.setAttribute("mensaje",
												"La cuenta con la que entro no tiene privilegios en la división.");
									} else {
										user.setPcAcciones(acciones);
										request.getSession().setAttribute("USER", user);
									}
	
									request.getSession().setAttribute("estiloCss", "administrador");
								} else {
									String validarSession = "Activa";
									Pc_SubdivisionVO subdivision = (Pc_SubdivisionVO) divisiones.get(0);
									user = ServletFacade.getSesionUsuario(login.getPcUserid(), subdivision.getPcCveDiv(),
											subdivision.getPcCveSubdiv(), divisiones);
									user.setPcCvesDivs(todasDivisiones);
									ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
									user.setPcAcciones(acciones);
									request.getSession().setAttribute("USER", user);
									request.getSession().setAttribute("validarSession", validarSession);
	
									request.getSession().setAttribute("estiloCss", "consultaDivisional");
									return mapping.findForward("home");
								}
	
							} else {
								request.getSession().setAttribute("LOGIN", login);
								request.setAttribute("DIVISION", divisiones);
								request.setAttribute("divisionUsuario", String.valueOf(division));
								request.setAttribute("USUARIODIV", usuario);
							}
						} else {
							Pc_SubdivisionVO subdivision = (Pc_SubdivisionVO) divisiones.get(0);
							String todasDivisiones = generaCadenaDivisiones(divisiones);
							user = ServletFacade.getSesionUsuario(login.getPcUserid(), subdivision.getPcCveDiv(),
									subdivision.getPcCveSubdiv(), divisiones);
							user.setPcCvesDivs(todasDivisiones);
							user.setPcValidadoConfig(PersonalMovProxy.validaUsuario(usuario));
							if (user == null) {
								request.setAttribute("mensaje",
										"La cuenta con la que entro no tiene permisos para usar esta división.");
							} else {
								ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
								if (acciones.isEmpty()) {
									logger.info("El usuario " + usuario + " no tiene permisos para entrar a la aplicacion");
									request.setAttribute("mensaje",
											"La cuenta con la que entro no tiene privilegios en la división.");
								} else {
									request.getSession().setAttribute("estiloCss", "consultaDivisional");
									logger.info(
											"El usuario " + usuario + " ha sido autenticado y tiene permisos correctos");
									user.setPcAcciones(acciones);
	
									request.getSession().setAttribute("USER", user);
									//request.getSession().setAttribute("Admin", user);
									
								}
							}
						}
					}
				}else {
					AdminCatFacade.accessLog(usuario, obtenIP, "USUARIO INTENTO INGRESAR SIN EXITO AL PORTAL");
					request.setAttribute("mensaje",
							"La combinación de nombre de Usuario y Contraseña es incorrecto intente de nuevo.");
				}
	
				if ((user != null) && (user.getPcCveRol() == 0)) {
					logger.info("rol=0");
	
					request.getSession().removeAttribute("USER");
					user.setPcCveDiv(0);
					user.setPcCveSubdiv(0);
					request.getSession().setAttribute("USER", user);
					String validarSession = "Activa";
					request.getSession().setAttribute("validarSession", validarSession);
	
					return mapping.findForward("sysadmin");
				}
	
				logger.info("rol dif 0");
	
				String validarSession = "Activa";
	
				if ((user.getPcCveDiv() == 0) || (user.isPcValidadoConfig()))
					request.getSession().setAttribute("estiloCss", "administrador");
				else {
					request.getSession().setAttribute("estiloCss", "consultaDivisional");
				}
	
				request.getSession().setAttribute("validarSession", validarSession);
				return mapping.findForward("home");
			}
			//If the validation it is incorrect then display an error message
			else
			{	
				if(usuario!=null || password!=null)
					request.setAttribute("mensaje",	"La combinación de nombre de Usuario y Contraseña es incorrecto intente de nuevo.");
			}
		
		}
		catch (CatalogoSystemException cse) {
			logger.error("CatalogoSystemException Error " + cse.toString());
			//return mapping.findForward("error");
			request.setAttribute("mensaje",	"El usuario no está autorizado para usar la aplicación");
			return mapping.findForward("home");
		} 
		catch (IOException e) {
		
	    	e.printStackTrace();
	    } catch (Exception e) {
			
			e.printStackTrace();
		}
			    
	    
		return mapping.findForward("home");
	
	}
	

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

		cal1.setTime(date1);
		long ldate1 = date1.getTime() + cal1.get(15) + cal1.get(16);

		cal2.setTime(date2);
		long ldate2 = date2.getTime() + cal2.get(15) + cal2.get(16);

		int hr1 = (int) (ldate1 / 3600000L);
		int hr2 = (int) (ldate2 / 3600000L);

		int days1 = hr1 / 24;
		int days2 = hr2 / 24;

		int dateDiff = days2 - days1;
		int weekOffset = (cal2.get(7) - cal1.get(7) < 0) ? 1 : 0;
		int weekDiff = dateDiff / 7 + weekOffset;
		int yearDiff = cal2.get(1) - cal1.get(1);
		int monthDiff = yearDiff * 12 + cal2.get(2) - cal1.get(2);

		if (tipo.equals("dias")) {
			diferencia = dateDiff;
		}
		if (tipo.equals("semanas")) {
			diferencia = weekDiff;
		}
		if (tipo.equals("meses")) {
			diferencia = monthDiff;
		}
		if (tipo.equals("anios")) {
			diferencia = yearDiff;
		}
		logger.info("ººººººººla fecha inicial" + fechaInicial);
		logger.info("ººººººººla fecha final" + fechaFinal);
		logger.info("ºººººººººººLa diferencia es::::" + diferencia);

		return diferencia;
	}

	public ActionForward autorizar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public ActionForward solicitar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String usuario = (request.getParameter("pcUserid").equals("")) ? null : request.getParameter("pcUserid");
			String password = (request.getParameter("pcPassword").equals("")) ? null
					: request.getParameter("pcPassword");
			String cveSubdivision = (request.getParameter("pcCveSubdiv").equals("")) ? null
					: request.getParameter("pcCveSubdiv");

			String cveDivision = request.getParameter("pcCveDiv");
			if (cveDivision == null) {
				cveDivision = "0";
			}
			UserSessionVO user = null;
			Pc_LoginVO login = null;

			if (request.getSession().getAttribute("LOGIN") != null) {
				login = (Pc_LoginVO) request.getSession().getAttribute("LOGIN");
				ArrayList divisiones = ServletFacade.getSubdivision(login.getPcUserid());
				user = ServletFacade.getSesionUsuario(login.getPcUserid(), Integer.parseInt(cveDivision),
						Integer.parseInt(cveSubdivision), divisiones);
				request.getSession().setAttribute("DIV_ACTUAL", user.getPcNombreCuenta());
				if (user == null) {
					request.setAttribute("mensaje",
							"La cuenta con la que entro no tiene permisos para usar esta división.");
				} else {
					ArrayList acciones = ServletFacade.getAcciones(user.getPcCveRol());
					if (acciones.isEmpty()) {
						request.setAttribute("mensaje",
								"La cuenta con la que entro no tiene privilegios en la división.");
					} else {
						user.setPcAcciones(acciones);

						request.getSession().setAttribute("USER", user);
						request.getSession().removeAttribute("LOGIN");
					}
				}
			} else {
				request.setAttribute("mensaje", "La session no es valida o no tienen permisos para ver esta División.");
			}
			return mapping.findForward("home");
		} catch (CatalogoSystemException cse) {
			logger.error("Error de SQL en solicitar " + cse.toString());
			return mapping.findForward("error");
		} catch (Exception e) {
			logger.error("Error en solicitar " + e.toString());
			e.printStackTrace();
		}
		return mapping.findForward("error");
	}

	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("login");
	}

	public ActionForward guardarPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String usuarioDesencriptado = new String(new BASE64Decoder().decodeBuffer(request.getParameter("pcUserid")));
		int es = usuarioDesencriptado.lastIndexOf("/");
		String usuario = usuarioDesencriptado.substring(0, es);

		String password = (request.getParameter("pcPassword").equals("")) ? null : request.getParameter("pcPassword");

		String causa = (String) request.getSession().getAttribute("CAUSA");

		String UsuarioEncriptado = EncriptaBlowfish.encripta(usuario.substring(0, es));
		AdminCatFacade.updatePassword(usuario, password);

		AdminCatFacade.updateExpiration(usuario);

		AdminCatFacade.updatePcCambiaPassword(usuario);

		request.getSession().removeAttribute("DIVISION");
		request.getSession().removeAttribute("USER");

		return mapping.findForward("index");
	}

	public ActionForward cancelarPassword(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("login");
	}
	
	/**
	 *Process the specified HTTP request to exit the systme, and create the corresponding HTTP response 
	 * (or forward to another web component that will create it). Return an ActionForward
	 * instance describing to exit the SAEO system and remove attributes to the system returning
	 * to the SAEO system
	 * @param form The optional ActionForm bean for this request (if any)
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are processing
	 * @return
	 */
	public ActionForward salir(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession();
		
		Enumeration userSessionValues = userSession.getAttributeNames();	
		while(userSessionValues.hasMoreElements())
		{
	   		String element = (String)userSessionValues.nextElement();
	   		//removes elements from the session
	   		userSession.removeAttribute(element);
	   		
	   		
		}
		//for()
	   return mapping.findForward("index");
	}

	public String getValor(HttpServletRequest req, String valor) {
		String res = req.getParameter(valor);

		if ((res != null) && (res.trim().length() == 0)) {
			res = null;
		}

		return res;
	}

	public static String generaCadenaDivisiones(ArrayList lista) {
		Pc_SubdivisionVO temporal = (Pc_SubdivisionVO) lista.get(0);
		StringBuffer sb = new StringBuffer();
		sb.append(temporal.getPcCveDiv());
		for (int i = 1; i < lista.size(); ++i) {
			Pc_SubdivisionVO temporal2 = (Pc_SubdivisionVO) lista.get(i);
			sb.append(",");
			sb.append(temporal2.getPcCveDiv());
		}
		return sb.toString();
	}
}
