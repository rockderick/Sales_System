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
<%!
	public String getValor(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		if(res != null && (res.trim().length() == 0)){
			res = null;
		}
		return res;
	}
	
%> 

<%
String PcDescCanal = (String)request.getAttribute("PcDescCanal");
String PcCveCanal = (String)request.getAttribute("PcCveCanal");
%>
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title><bean:message key="canalesForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script>
			
			function goToGuardar(form) {
				MM_validateForm('pcDescCanal','RisText','Descripción de Canal :');
				if (document.MM_returnValue){
					var action = "?action=modificarGuardar";
					form.action = form.action + action;
					form.submit();
				}
					
			}		
			
			function goToCancelar(form) {
				var action = "?action=cancelar";
				form.action = form.action + action;
				form.submit();

			}												

		</script>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home" />
    <menu:displayMenu name="Vendedores" /> 
    <menu:displayMenu name="Personal" /> 
    <menu:displayMenu name="PuntosVenta" /> 
    <menu:displayMenu name="Relaciones" /> 
    <menu:displayMenu name="Reportes" /> 
    <menu:displayMenu name="Auxiliares" /> 
</menu:useMenuDisplayer>	

<BR><BR>

	<html:form method="post" action="CanalesAction"> 
	<bean:define id="CanalesForm" name="CanalesForm" type="mx.com.iusacell.catalogo.web.auxiliares.struts.action.CanalesForm"/>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
		 	
	<TABLE WIDTH="700" BORDER="0" align="center">

      <TR>
        <TD HEIGHT="20"><h3>Modificar Canal</h3></TD>
      </TR>
      
    </TABLE>
    
    <BR><BR>
    
    <TABLE WIDTH="700" BORDER="0" align="center">
    

		
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Canal a Modificar</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text value="<%=PcDescCanal%>" property="pcDescCanal" name="CanalesForm" maxlength="50" size="50" onchange="this.value=this.value.toUpperCase()"/>
				<html:hidden property="pcCveCanal" value="<%=PcCveCanal%>"/> 
			</TD>
		</TR>
		
		<TR></TR><TR></TR><TR></TR>

	  </TABLE>
	  
	  <br>
	  

	 

	


	 
	 <TABLE WIDTH="700" BORDER="0" align="center">
	 
	 	<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
			<input type="hidden" name="CveRol" value="0">
		<TR>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToGuardar(CanalesForm)">Guardar</html:button>
			</TD>
		 	<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToCancelar(CanalesForm)">Cancelar</html:button>
		 	</TD>
		</TR>
	</logic:notEqual>	
			
	<logic:equal name="divisionUsuario" property="pcCveRol" value ="4">	
		<input type="hidden" name="CveRol" value="4">
		<tr>
			<td>&nbsp;</td>
		</tr>
	</logic:equal>


   </TABLE>
	


	


		

		

</html:form> 
</BODY>
</html:html>
