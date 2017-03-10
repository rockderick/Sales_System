<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
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
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title><bean:message key="puestosForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script>

		function updateBox(form,item){
			itemName= 'puesto' + item.value;
			hiddenItem = FIND(itemName);
			text = hiddenItem.value;
			form.pcDescPuestos.value= text;
			
			if(document.forms[0].CveRol.value != "4") {
				if( window.mmIsOpera ) return(document.getElementById("eliminar").style.visibility = 'visible');
				if (document.all) return(document.all.eliminar.style.visibility = 'visible');
				if (document.getElementById) return(document.getElementById("eliminar").style.visibility = 'visible');
			}
		}

		function validateForm(form){
			return (validatePuestosForm(form));
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
<logic:present name="Admin" >
    <menu:displayMenu name="Configuracion" />
</logic:present>     
</menu:useMenuDisplayer>	

<BR><BR>
	<html:form method="post" action="PuestosAction"> 
	<bean:define id="PuestosForm" name="PuestosForm" type="mx.com.iusacell.catalogo.web.auxiliares.struts.action.PuestosForm"/>	
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="puestosForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<bean:message key="puestosForm.message"/>
			</TD>
		 </TR>
		<TR>
			<TD ALIGN="CENTER" colspan="2">
	
			<fsj:generalHomeTable
				queryId="<%=Q.PUESTOS_ALL%>"				
				valueMethodName = "getPcCvePuesto"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
				border="1"
				width="60%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Puestos"
				metodos="getPcDescPuesto"
				anchosColumnas="90%"
				alineacionesColumnas="center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="10%"
				radioName="pcCvePuestos"
				camposExtras='getPcDescPuesto=puesto'
				agregarValorCamposExtras='true'
				script="onClick='updateBox(PuestosForm,this)'"
			/>
		
		</TR>
		 
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<div id="eliminar" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToEliminar(PuestosForm)"><bean:message key="button.eliminar"/></html:button></div>
		 		</TD>
		 </TR>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		</TD></TR>
</logic:present>	 		 
		</TABLE>
	</TD>
	</TR>
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puestosForm.pcDescPuestos.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcDescPuestos" name="PuestosForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
<logic:messagesPresent>
		<TR><TD CLASS="errormessagerojo" colspan="2">
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>		
	<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
	<input type="hidden" name="CveRol" value="0">
			<TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
					<html:button property="boton" styleClass="boton" onclick="goToAgregar(PuestosForm)"><bean:message key="button.agregar"/></html:button>
				</TD>
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
					<html:button property="boton" styleClass="boton" onclick="goToModificar(PuestosForm)"><bean:message key="button.modificar"/></html:button>
			 	</TD>
			</TR>
			</TABLE>
			</TD>
		</TR>
	</logic:notEqual>	
			
	<logic:equal name="divisionUsuario" property="pcCveRol" value ="4">	
		<input type="hidden" name="CveRol" value="4">
		<tr>
			<td>&nbsp;</td>
		</tr>
	</logic:equal>
	</table>
<BR><BR>
</html:form> 
</BODY>
</html:html>
