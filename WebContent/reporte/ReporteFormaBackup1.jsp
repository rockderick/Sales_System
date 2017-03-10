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
	
	public Object[] getParameters(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		ArrayList param = new ArrayList();
		param.add(res);
		return param.toArray();	
	}

	public Object[] getParametersNull(HttpServletRequest req, String parametro){
		ArrayList param = new ArrayList();
		if(parametro == null || parametro.equalsIgnoreCase("")==true) param.add(null);
		else param.add(parametro);
		return param.toArray();	
	}
	
%> 
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE><bean:message key="reporteForm.title"/></TITLE>
<style>
   /* styles for the tree */
   SPAN.TreeviewSpanArea A {
        font-size: 8pt; 
        font-family: verdana,helvetica; 
        text-decoration: none;
        color: black
   }
   SPAN.TreeviewSpanArea A:hover {
        color: '#820082';
   }
   /* rest of the document */
   BODY {background-color: white}
   TD {
        font-size: 8pt; 
        font-family: verdana,helvetica; 
   }
</style>

		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
<logic:present name="jsArray">
		<bean:define id="jsArray" name="jsArray" type="java.lang.String"/>
		<script language="Javascript1.2">
		<bean:write name="jsArray" filter="false"/>
		</script>
</logic:present>		
		<script language="Javascript1.2" src="../js/Reporte.js"></script>
		<script language="Javascript1.2" src="../js/ua.js"></script>
		<script language="Javascript1.2" src="../js/ftiens4.js"></script>
		<script language="Javascript1.2">

		function goToExcel(form, control) {
			var action ="";
			if(control == null || control==undefined){
				action = "?action=excel";
				form.action = form.action + action;
			}else{
				action = "?action=excel&control=" + control;
				form.action = form.action + action;
			}
			form.submit();
		}

		function goToBuscarReporte(form,control) {
			var action ="";
				form.pcDescDivisionSeek.value= form.pcCveDivisionSeek.options[form.pcCveDivisionSeek.selectedIndex].text;
				//alert(form.pcDescDivisionSeek.value);
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
				form.action = form.action + action;
				form.submit(); 
		}
		
//Environment variables are usually set at the top of this file.
USETEXTLINKS = 1
STARTALLOPEN = 0
USEFRAMES = 0
USEICONS = 1
WRAPTEXT = 1
PRESERVESTATE = 1

ICONPATH = '../icons/' //change if the gif's folder is a subfolder, for example: 'images/'


<logic:present name="textJavaScript">
	<bean:define id="arbol" name="textJavaScript" type="java.lang.String"/>
	<bean:write name="arbol" filter="false"/>
</logic:present>
<logic:notPresent name="textJavaScript">
    foldersTree = gFld("<i>Reporte</i>", "")
	foldersTree.iconSrc = ICONPATH + "home24.gif"
	foldersTree.iconSrcClosed = ICONPATH + "homeplus24.gif"
</logic:notPresent>
	
		</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" onResize="if (navigator.family == 'nn4') window.location.reload()">
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
	<html:form method="post" action="ReporteArbolAction" method="POST" onsubmit="return validateReporteForm(this);"> 
	<bean:define id="ReporteArbolForm" name="ReporteArbolForm" type="mx.com.iusacell.catalogo.web.reporte.struts.action.ReporteArbolForm"/>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
	
	<TABLE WIDTH="80%" BORDER="0" CELLSPACING="1" CELLPADDING="2" BGCOLOR="#5D5D5E" align="center">
	<TR>
	<TD BGCOLOR="#FFFFFF">
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#CC0000">
		<TR>
		<TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" ><B><bean:message key="reporteForm.header"/></B></TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	</TABLE>

<BR><BR>

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="reporteForm.message.buscar"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="repoteForm.pcCveDivisionSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<fsj:comboGeneral
		    	name = "pcCveDivisionSeek"
				queryId="<%=Q.SUBDIVISION_ALL%>"				
				valueMethodName = "getPcCveSubdiv"
				descrMethodName = "getPcDescSubdiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveDivisionSeek")%>' 
				textoNoSeleccion = "-- Seleccione una Divisi�n --"
				valorNoSeleccion = ""
				parametrosQuery='<%= new Object[] {String.valueOf(divisionUsuario.getPcCveSubdiv())} %>'
			/>
			<html:hidden name="ReporteArbolForm" property="pcDescDivisionSeek" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="reporteForm.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="ReporteArbolForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#E0E1E3">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarReporte(ReporteArbolForm)"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
<logic:messagesPresent>
		<TR><TD CLASS="errormessagerojo" colspan="2">
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <li><bean:write name="error"/></li>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>
		<TR bgcolor="#E0E1E3">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToExcel(ReporteArbolForm,'excel')">Reporte Excel</html:button>
 		</TD>
 		<TD>
 		<logic:present name="liga">
 		<bean:define name="liga" id="liga"/>
		<html:link href="<%=(String)liga%>" target="excel">Reporte Excel</html:link>
		</logic:present>
 		</TD>
 		</TR>
		</TABLE>
	</TD>
	</TR>
	</table>
	
<BR><BR>
	<TABLE WIDTH="80%" BORDER="0" CELLSPACING="1" CELLPADDING="2" BGCOLOR="#5D5D5E" align="center">
	<TR>
	<TD BGCOLOR="#FFFFFF">
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#CC0000">
		<TR>
		<TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" ><B><bean:message key="reporteForm.header"/></B></TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	</TABLE>

<BR><BR>

<div style="position:absolute; top:0; left:0; visibility:hidden"><table border=0><tr><td><font size=-2><a style="font-size:7pt;text-decoration:none;color:silver" href="http://www.treemenu.net/" target=_blank>JavaScript Tree Menu</a></font></td></tr></table></div>

	<table width="95%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
		<bean:message key="reporteForm.message"/>
		</TD>
	</TR>
	<TR>
	<TD valign="top" width="65%">
			<table border=0 width="100%">
			<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>
			<span class=TreeviewSpanArea>
			<script>initializeDocument()</script>
			</span>
			</td></tr></table>
	</TD><TD valign="top" width="35%">
			<table border=0 width="100%">
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcClaveCompleta.displayname"/></td><td class="txtContenido"><span ID="divPcClaveCompleta">&nbsp;</span></td></tr>
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcNombreVendedor.displayname"/></td><td class="txtContenido"><span ID="divPcNombreVendedor">&nbsp;</span></td></tr>
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcDescPuesto.displayname"/></td><td class="txtContenido"><span ID="divPcDescPuesto">&nbsp;</span></td></tr>
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcCiudad.displayname"/></td><td class="txtContenido"><span ID="divPcCiudad">&nbsp;</span></td></tr>
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcEstado.displayname"/></td><td class="txtContenido"><span ID="divPcEstado">&nbsp;</span></td></tr>		
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcDireccion.displayname"/></td><td class="txtContenido"><span ID="divPcDireccion">&nbsp;</span></td></tr>	
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcColonia.displayname"/></td><td class="txtContenido"><span ID="divPcColonia">&nbsp;</span></td></tr>
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcCp.displayname"/></td><td class="txtContenido"><span ID="divPcCp">&nbsp;</span></td></tr>		
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcTel.displayname"/></td><td class="txtContenido"><span ID="divPcTel">&nbsp;</span></td></tr>
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcFax.displayname"/></td><td class="txtContenido"><span ID="divPcFax">&nbsp;</span></td></tr>
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcFchAlta.displayname"/></td><td class="txtContenido"><span ID="divPcFchAlta">&nbsp;</span></td></tr>
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcFchBaja.displayname"/></td><td class="txtContenido"><span ID="divPcFchBaja">&nbsp;</span></td></tr>
			</table>
		<div ID="Tienda" style="visibility:hidden">
			<table border=0 width="100%">
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcCvePtoventas.displayname"/></td><td class="txtContenido"><span ID="divPcCvePtoventas">&nbsp;</span></td></tr> 
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcNomPtoventas.displayname"/></td><td class="txtContenido"><span ID="divPcNomPtoventas">&nbsp;</span></td></tr> 
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcDescCanal.displayname"/></td><td class="txtContenido"><span ID="divPcDescCanal">&nbsp;</span></td></tr> 
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcDescRegion.displayname"/></td><td class="txtContenido"><span ID="divPcDescRegion">&nbsp;</span></td></tr> 
			<tr bgcolor="#E0E1E3"><td class="txtContenido" width="25%"><bean:message key="reporteForm.divPcDescTpCanal.displayname"/></td><td class="txtContenido"><span ID="divPcDescTpCanal">&nbsp;</span></td></tr> 
			</table>
		</div>
	</TD>
	</TR>
	</table>
	</html:form> 
<!--html:javascript formName="VendedoresForm" dynamicJavascript="true" staticJavascript="true"/-->
</BODY>
</html:html>
