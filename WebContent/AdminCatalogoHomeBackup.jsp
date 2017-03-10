<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/fsj-html.tld" prefix="fsj" %>
<%!
	public String getValor(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		if(res != null && (res.trim().length() == 0)){
			res = null;
		}
		return res;
	}
	
%> 
<html:html>
	<head>
		<title><bean:message key="adminCatalogoHome.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" type="text/css" href="css/estilos_exp.css">
	    <link rel="stylesheet" type="text/css" media="screen" href="css/global.css" />   
    	<link rel="stylesheet" type="text/css" media="screen" href="css/tabs.css" />    
<logic:present name="USER">
	    <script type="text/javascript" src="js/tabs.js"></script>
</logic:present>
		<script language="JavaScript" src="js/tools.js"></script>
		<script language="JavaScript" src="js/std_button.js"></script>
		<script language="JavaScript">
			function updateBox(form,item){
				changeInput(form.pcCveDiv,item.value,'div');
			}
		</script>
	</head>
  
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">

<logic:present name="USER">
<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home" />
    <menu:displayMenu name="Vendedores" /> 
    <menu:displayMenu name="Personal" /> 
    <menu:displayMenu name="PuntosVenta" /> 
    <menu:displayMenu name="Relaciones" /> 
    <menu:displayMenu name="Reportes" /> 
    <menu:displayMenu name="Auxiliares" /> 
</menu:useMenuDisplayer>	
</logic:present>

<TABLE cellspacing="0" cellpadding="0" border="0" width="100%" height="58">
		<TR><TD rowspan="2" width="189" height="58"><IMG border="0" src="images/logo_iusacell.gif" width="189"></TD>
			<TD height="29" background="images/bg_top.gif"></TD>
		</TR>
		<TR><TD height="29" background="images/background_header.gif">&nbsp;</TD></TR>
</TABLE>

<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="titulotop1" colspan="2">
			<bean:message key="adminCatalogoHome.welcome" />
			</TD>
		</TR>
		</TABLE>
	</TD></TR>
	<TR><TD>&nbsp;</TD></TR>
	<TR><TD>&nbsp;</TD></TR>
	<TR><TD>
<logic:notPresent name="USER">
<html:form method="post" action="Login"> 
	<logic:present name="DIVISION">
	<bean:define id="DIVISION" name="DIVISION" type="java.util.ArrayList"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<bean:message key="adminCatalogoHome.login.instrucciones2" />
			</TD>
		</TR>
		<TR bgcolor="#E0E1E3">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold">
			<bean:message key="adminCatalogoHome.division.displayname" />
			</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold">
			<html:hidden name="LoginForm" property="pcCveDiv"/>
			<fsj:comboGeneralEstaticoTag
				lista="<%= DIVISION %>"
		    	name = "pcCveSubdiv"
				valueMethodName = "getPcCveSubdiv"
				descrMethodName = "getPcDescSubdiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveSubdiv")%>' 
				textoNoSeleccion = "-- Seleccione una División --"
				valorNoSeleccion = ""
				script="onClick='updateBox(LoginForm,this)'"
				camposExtras='getPcCveDiv=div'
				agregarValorCamposExtras='true'
			/></TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<html:submit><bean:message key="adminCatalogoHome.submit.displayname"/></html:submit>
			</TD>
		</TR>
		</TABLE>
		<html:hidden name="LoginForm" property="action" value="solicitar" />
		<html:hidden name="LoginForm" property="pcUserid" value=""/>
		<html:hidden name="LoginForm" property="pcPassword" value=""/>
	</logic:present>
	<logic:notPresent name="DIVISION">
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<bean:message key="adminCatalogoHome.login.instrucciones"/>
			</TD>
		</TR>
		<TR bgcolor="#E0E1E3">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold">
			<bean:message key="adminCatalogoHome.usuario.displayname"/>
			</TD>
			<TD><html:text name="LoginForm" property="pcUserid"/></TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold">
			<bean:message key="adminCatalogoHome.password.displayname"/>
			</TD>
			<TD><html:password name="LoginForm" property="pcPassword" /></TD>
		</TR>
		<TR bgcolor="#E0E1E3">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<html:submit><bean:message key="adminCatalogoHome.submit.displayname"/></html:submit>
			</TD>
		</TR>
		</TABLE>
		<html:hidden name="LoginForm" property="action" value="autorizar"/>
		<html:hidden name="LoginForm" property="pcCveDiv"/>
		<html:hidden name="LoginForm" property="pcCveSubdiv"/>
	</logic:notPresent>
<logic:present name="mensaje">
	<bean:define id="mensaje" name="mensaje" type="java.lang.String"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF"><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="mensaje" />
		 	</TD>
		</TR>
		</TABLE>
</logic:present>	
</html:form>
</logic:notPresent>	
<logic:present name="USER">
	<TR><TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<bean:message key="adminCatalogoHome.instrucciones" />
			</TD>
		</TR>
		</TABLE>
	</TD></TR>
	<TR><TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<bean:message key="adminCatalogoHome.instrucciones2" />
			</TD>
		</TR>
		</TABLE>
	</TD></TR>
	<TR><TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			Quiero asignar <html:link action="/AsistPtoventasAction.do" styleClass="txtContenidoRojoLink">Puntos de Venta</html:link> a mis vendedores
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			Quiero cambiar el <html:link action="/AsistPuestoAction.do" styleClass="txtContenidoRojoLink">Puesto </html:link> a algun vendedor
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			Quiero cambiar el <html:link action="/AsistSuperiorAction.do" styleClass="txtContenidoRojoLink">Supervisor</html:link> a algun vendedor
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			Quiero <html:link action="/AsistReasignarAction.do" styleClass="txtContenidoRojoLink">Reasignar Personal</html:link> de la plantilla de algun vendedor
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
				<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
				<bean:write property="pcCveDiv" name="divisionUsuario"/>-<bean:write property="pcCveSubdiv" name="divisionUsuario"/>
			</TD>
		</TR>
		</TABLE>
	</TD></TR>
</logic:present>	
<logic:present name="unauthorized">
	<TR><TD>&nbsp;</TD></TR>	
	<TR><TD>&nbsp;</TD></TR>	
	<TR><TD>	
	<bean:define id="unauthorized" name="unauthorized" type="java.lang.String"/>
	
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF"><TD ALIGN="CENTER" CLASS="errormessagerojo" colspan="2">
		 		<bean:write name="unauthorized" />
		 	</TD>
		</TR>
		</TABLE>
	</TD></TR>
</logic:present>
	</table>


<div align="right"><html:link page="/manual/AdminCatalog.pdf" styleClass="txtContenidoRojoLink"><bean:message key="adminCatalogoHome.manual"/></html:link></div>	
<div align="right"><html:link action="/Logout.do" styleClass="txtContenidoRojoLink"><bean:message key="adminCatalogoHome.logout"/></html:link></div>	

</body>

</html:html>


