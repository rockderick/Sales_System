<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="mx.com.iusacell.catalogo.modelo.PcMarcasVO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fsj-html.tld" prefix="fsj" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<jsp:include page="/Valida.jsp"></jsp:include>
<jsp:include page="/Icono.jsp"></jsp:include>
<%
	PcMarcasVO marca = (PcMarcasVO)request.getAttribute("marca");
					
	String pcCveMarca = request.getParameter("identif");
	
%>
<%!
	public String getValor(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		if(res != null && (res.trim().length() == 0)){
			res = null;
		}
		return res;
	}
	
	public Object[] getParametros(String division){
		ArrayList param = new ArrayList();
		param.add(division);
		return param.toArray();	
	}	
%> 
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title><bean:message key="empresaForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script>

		function goToModificar(form) {
			var action = "?action=modificarGuardar";
			form.action = form.action + action;
			form.submit(); 
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
	<html:form method="post" action="MarcasAction" method="POST"> 
	<bean:define id="MarcasForm" name="MarcasForm" type="mx.com.iusacell.catalogo.web.auxiliares.struts.action.MarcasForm"/>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	
	<html:hidden property="pcCveMarca" value="<%=pcCveMarca%>"/>
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3>Agregar Marca</h3></TD>
      </TR>
    </TABLE>
    
    <table width="765" border="0" cellspacing="0" cellpadding="5" align="center">
    
	    <tr>
		<td width="135" align="right"><TD WIDTH="40%"  CLASS="txtContenidoRojo">Descripción de Marca :</TD></td>
			<td width="237" align="left">
				<html:text  property="pcDescMarca" value="<%=marca.getPcDescMarca()%>" size="15" maxlength="16" onchange="this.value=this.value.toUpperCase()"/>
			</td> 
		<td width="135" align="right"></td>
			<td width="237" align="left">	
		</td>
		</tr>
		
			
  	<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3"  align="center">	
	<TR>
	
		 		<TD width="900" align="center"> 
	        	<INPUT TYPE="button" NAME="buscaBtn" VALUE="&nbsp;Modificar&nbsp;" CLASS="boton"
  				onClick="goToModificar(MarcasForm)">
	   			</TD>
	   			

	   			
	   			<TD width="900" align="center"> 
	        	<INPUT TYPE="button" NAME="buscaBtn" VALUE="&nbsp;Cancelar&nbsp;" CLASS="boton"
  				onClick="goToCancelar(MarcasForm)">
	   			</TD>
	   				
	</TR>
	</TABLE>			
			 
	</table>
	

<BR><BR>
</html:form> 
</BODY>
</html:html>
