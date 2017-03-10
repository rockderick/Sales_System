<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fsj-html.tld" prefix="fsj" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<jsp:include page="/Valida.jsp"></jsp:include>
<jsp:include page="/Icono.jsp"></jsp:include>
<html:html>
<HEAD>
<html:base />
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title>Iusacell:: Administracion de Roles</TITLE>
<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>
<script language="JavaScript" src="../js/tools.js"></script>
<script language="Javascript1.2" src="../js/std_button.js"></script>
<SCRIPT>

		function goToBuscar(form) {
			var action = "?action=home";
			form.action = form.action + action;
			form.submit();	
		}
		
		function goToGuardar(form) {
		
			MM_validateForm('nuevoRol','RisText','Nuevo Rol :');
			if(document.MM_returnValue){ 
				var action = "?action=AgregarGuardarRol";
				form.action = form.action + action;
				form.submit(); 
			}	
		}
		
		</SCRIPT>
<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3"
marginwidth="0" marginheight="0"  onLoad="this.document.AdmRolesForm.nuevoRol.focus();">
<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="SysAdmin" />
    <menu:displayMenu name="SysAdminMapeo" />
    <menu:displayMenu name="SysAdminReportes" /> 
</menu:useMenuDisplayer>	

<BR>
<BR>
<html:form method="post" action="AdmRoles">
<bean:define id="AdmRolesForm" name="AdmRolesForm" type="mx.com.iusacell.sysadmin.web.sysadmin.struts.action.AdmRolesForm"/>	
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
		<TR>
		<TD HEIGHT="20">
			<h3>Agregar un nuevo Rol</h3>
		</TD>
		</TR>
	</TABLE>
	<BR><br>
<TABLE WIDTH="700" align="center">	
	<TR bgcolor="#F9F9F9">
			<TD WIDTH="53%" height="25" ALIGN="center">
				<b>Nuevo Rol: </b> <html:text property="nuevoRol" value=""/>
			</td>
	</tr>
	<TR bgcolor="#FFFFFF">
			<TD WIDTH="53%" height="15" ALIGN="center">
				</td>
	</TR>
	</table>
	<table WIDTH="700" align="center">
	<TR bgcolor="#F9F9F9">
			<TD WIDTH="53%" height="25" ALIGN="center" CLASS="PTabData">
				<html:button property="boton" value ="Agregar" styleClass="boton" onclick="goToGuardar(AdmRolesForm)"/>
			</td>
			<TD WIDTH="53%" height="25" ALIGN="center" CLASS="PTabData">
				<html:button property="boton" value ="Cancelar" styleClass="boton" onclick="goToBuscar(AdmRolesForm)"/>
			</td>
	</tr>
</table>
<logic:present name="mensaje">
			<bean:define id="mensaje" name="mensaje" type="java.lang.String"/>
			<TR bgcolor="#CCCCCC">
			  <TD colspan="2"></TD>
	    </TR>
			<TR><TD height="30" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
			 		<center><b><bean:write name="mensaje" /></b></center>
			 	</TD>
			</TR>
	</logic:present>	
	     <div align="right"><html:link action="/Logout.do" styleClass="txtContenidoRojoLink">Salir del Sistema</html:link></div>	
</html:form>
</BODY>
</html:html>
