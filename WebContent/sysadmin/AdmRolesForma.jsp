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
<%!public String getValor(HttpServletRequest req, String valor) {
	String res = req.getParameter(valor);
	if (res != null && (res.trim().length() == 0)) {
		res = null;
	}
	return res;
}
%>

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
			var action = "?action=home&buscar=true";
			form.action = form.action + action;
			form.submit();	
		}
		function goToAgregar(form) {
			var action = "?action=Agregar";
			form.action = form.action + action;
			form.submit();	
		}
		function goToModificar(form) {
			var action = "?action=Modificar";
			form.action = form.action + action;
			form.submit();	
		}

		function window_onload(form) {
			<logic:present name="mensaje">
				alert('<bean:write name="mensaje"/>');
			</logic:present>
		}
		function goToModificar(form) {
		
			var rad = document.getElementById('identif');
				if(rad!=null){
					if(idCheck(form.identif)){
						var action = "?action=Modificar";
						form.action = form.action + action;
						form.submit();
					}else{
						alert("Debe seleccionar un rol para Modificar");
					}
				}else{
					alert("Debe buscar un rol para Modificar");
				}
		}
		
		
		</SCRIPT>
<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3"
	marginwidth="0" marginheight="0" onLoad="window_onload(AdmRolesForm)">
<menu:useMenuDisplayer name="TabbedMenu"
	bundle="org.apache.struts.action.MESSAGE">
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
			<h3>Administración de Roles</h3>
		</TD>
		</TR>
	</TABLE>
	<BR><br>
<TABLE WIDTH="700" align="center">	
	<TR bgcolor="#F9F9F9">
			<TD WIDTH="53%" height="25" ALIGN="center">
				<b>Rol: </b> <html:text property="rol" value=""/>
			</td>
	</tr>
	<TR bgcolor="#FFFFFF">
			<TD WIDTH="53%" height="15" ALIGN="center">
				</td>
	</TR>
	<TR bgcolor="#F9F9F9">
			<TD WIDTH="53%" height="25" ALIGN="center" CLASS="PTabData">
				<html:button property="boton" value ="buscar" styleClass="boton" onclick="goToBuscar(AdmRolesForm)"/>
			</td>
	</tr>
</table>
<BR>
<div align="center">
	<div style="height:220;width:70%;overflow:scroll;visibility:visible">
		<fsj:generalHomeTable 
		  border="1" 
		  width="100%" 
		  cellpadding="3" 
		  cellspacing="0" 
		  queryId="<%=Q.OBTENER_PERFILES%>"
		  parametrosQuery='<%=new Object[]{ getValor(request,"rol")}%>'
		  VOClass="<%=mx.com.iusacell.catalogo.modelo.Pc_RolesVO.class%>" 
		  titulos="Roles" 
		  metodos="getPcDescRol" 
		  anchosColumnas="100%" 
		  alineacionesColumnas="left" 
		  cssColorEncabezado="boton" 
		  altoRenglon="13"
		  cssColorRenglon1="td_blanco" 
		  cssColorRenglon2="td_gris_tablas"
		  witdhColumnRadio="3%"
		  radioName="identif"
		  valueMethodName="getPcCveRol"
		  consultar='<%=request.getParameter("buscar")!=null%>'
		/> 
	</div>
	</div>
	<BR>
	<table width="83%"  border="0" align="center" cellpadding="1" cellspacing="0">
    	 	<tr align="center">
				<td align="center">
					<html:button property="boton"  value="Agregar" styleClass="boton" onclick="goToAgregar(AdmRolesForm)"/>
				</td>
				<td align="center">
					<html:button property="boton"  value="Modificar" styleClass="boton" onclick="goToModificar(AdmRolesForm)"/>
	
				</td>
	    	</tr>
     </table>
      <div align="right"><html:link action="/Logout.do" styleClass="txtContenidoRojoLink">Salir del Sistema</html:link></div>	
</html:form>
</BODY>
</html:html>
