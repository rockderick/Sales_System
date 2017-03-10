<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fsj-html.tld" prefix="fsj" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<jsp:include page="/Valida.jsp"></jsp:include>
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
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title><bean:message key="accsMgr.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<SCRIPT>
		function updateBox(form,item){
			itemName= 'status' + item.value;
			hiddenItem = FIND(itemName);
			text = hiddenItem.value;
			form.pcStatus.value= text;
		}		
		</SCRIPT>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="AuxiliaresConsulta" />
    <menu:displayMenu name="SysAdminReportes" /> 
    
</menu:useMenuDisplayer>	

<BR><BR>
	<html:form method="post" action="Consulta"> 
	<bean:define id="ConsultaForm" name="ConsultaForm" type="mx.com.iusacell.sysconsulta.web.sysconsulta.struts.action.ConsultaForm"/>	
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="adminCatalogoHome.welcome"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<TABLE width="700" cellpadding="0" cellspacing="0" border="0" align="center">
	<logic:present name="mensaje">
			<bean:define id="mensaje" name="mensaje" type="java.lang.String"/>
			<TR><TD height="35" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
			 		<bean:write name="mensaje" />
			 	</TD>
			</TR>
	</logic:present>	
	<TR><TD colspan="2">		
	

	</TD></TR>
	</TABLE>	
			 <div align="right"><html:link action="/Logout.do" styleClass="txtContenidoRojoLink"><bean:message key="adminCatalogoHome.logout"/></html:link></div>	
	</html:form> 
</BODY>
</html:html>
