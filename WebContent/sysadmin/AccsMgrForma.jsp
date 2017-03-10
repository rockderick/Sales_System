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
			changeInput(form.pcCveUsuario,item.value,'user');
			changeInput(form.pcCveRol,item.value,'rol');
			changeInput(form.pcCveDiv,item.value,'div');
			
			itemName= 'roldiv' + item.value;
			hiddenItem = FIND(itemName);
			text = hiddenItem.value;
			form.pcUserid.value= text;

		}		
		</SCRIPT>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="SysAdmin" />
    <menu:displayMenu name="SysAdminMapeo" />
    <menu:displayMenu name="SysAdminReportes" /> 
</menu:useMenuDisplayer>	

<BR><BR>
	<html:form method="post" action="AccsMgr"> 
	<bean:define id="AccsManagerForm" name="AccsManagerForm" type="mx.com.iusacell.sysadmin.web.sysadmin.struts.action.AccsManagerForm"/>	

	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="accsMgr.main"/></h3></TD>
      </TR>
    </TABLE>
   	<BR>

	<TABLE width="700" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	
	  <TR bgcolor="#FFFFFF">
			<TD WIDTH="47%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			<bean:message key="accsMgr.message"/>
			</TD>
			<td height="40"></td>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="47%" height="25"  CLASS="txtContenidoBold"><bean:message key="accsMgr.cuentas.displayname"/> : </TD>
			<TD WIDTH="53%" height="25" ALIGN="LEFT" CLASS="PTabData">
			<bean:define id="tablaCuentas" name="tablaCuentas" type="java.util.ArrayList"/>	
				<fsj:comboGeneralEstaticoTag
			    	name = "pcCveUsuario"
					lista='<%= tablaCuentas %>'
					valueMethodName = "getPcUserid"
					descrMethodName = "getPcUserid"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_LoginVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveUsuario")%>' 
					textoNoSeleccion = "-- Cuenta Acceso--"
					valorNoSeleccion = ""
				/>
			</TD>
		</TR>
		<TR>
			<TD WIDTH="47%" height="25"  CLASS="txtContenidoBold"><bean:message key="accsMgr.division.displayname"/> : </TD>
			<TD WIDTH="53%" height="25" ALIGN="LEFT" CLASS="PTabData">
			<bean:define id="tablaDivision" name="tablaDivision" type="java.util.ArrayList"/>	
				<fsj:comboGeneralEstaticoTag
		    		name = "pcCveDiv"
					lista="<%= tablaDivision %>"				
					valueMethodName = "getPcCveDiv"
					descrMethodName = "getPcDescDiv"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_DivisionVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveDiv")%>' 
					textoNoSeleccion = "-- Seleccione una División --"
					valorNoSeleccion = ""
				/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="47%" height="25"  CLASS="txtContenidoBold"><bean:message key="accsMgr.roles.displayname"/> : </TD>
			<TD WIDTH="53%" height="25" ALIGN="LEFT" CLASS="PTabData">
			<bean:define id="tablaRoles" name="tablaRoles" type="java.util.ArrayList"/>	
				<fsj:comboGeneralEstaticoTag
		    		name = "pcCveRol"
					lista="<%= tablaRoles %>"				
					valueMethodName = "getPcCveRol"
					descrMethodName = "getPcDescRol"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_RolesVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveRol")%>' 
					textoNoSeleccion = "-- Seleccione Tipo Cuenta --"
					valorNoSeleccion = ""
				/>
			</TD>
		</TR>
		<TR>
			<TD WIDTH="47%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToAgregar(AccsManagerForm)"><bean:message key="button.agregar"/></html:button>
			</TD>
			<TD WIDTH="53%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToActualizar(AccsManagerForm)"><bean:message key="button.modificar"/></html:button>
			</TD>
			<td height="40"></td>
		</TR>	
		</TABLE>
	</TD></TR>
	
	<TR><TD colspan="2">		
	<div style="background:#FFFFFF;height:350;width:100%;overflow:scroll;visibility:visible;align=center">
		<TABLE WIDTH="80%" align="center">	
		  <TR bgcolor="#F9F9F9">
			<TD ALIGN="CENTER" CLASS="PTabData" colspan="2">
				<html:hidden name="AccsManagerForm" property="pcStatus" />
				<html:hidden name="AccsManagerForm" property="pcUserid" />
				<bean:define id="tablaPermisos" name="tablaPermisos" type="java.util.ArrayList"/>	
					<fsj:generalHomeTableEstatico
						lista='<%= tablaPermisos %>'
						valueMethodName = "getPcCveRolDiv"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_RolesDivVO.class%>"
						border="1"
						width="100%"
						cellpadding="0"
						cellspacing="0"
						cssColorEncabezado="td_mensajes"
						titulos="UserId, Division, Tipo Cuenta, Estado"
						metodos="getPcUserid,getPcDescSubdiv,getPcDescRol,getPcDescStatus"
						anchosColumnas="20%,20%,30%,20%"
						alineacionesColumnas="center,center,center,center"
						altoRenglon="1"
						cssColorRenglon1="td_gris_tablas"
						cssColorRenglon2="td_blanco_tablas"
						witdhColumnRadio="10%"
						radioName="pcCveRolDiv"
						camposExtras='getPcUserid=user,getPcCveRol=rol,getPcCveDiv=div,getPcStatus=status,getPcCveRolDiv=roldiv'
						agregarValorCamposExtras='true'
						script="onClick='updateBox(AccsManagerForm,this)'"
					/>
				</TD>
			</TR>
		</TABLE>	
	</div>
	</TD>
	</TR>
	</TABLE>
	<TABLE WIDTH="80%" align="center">	
		<TR bgcolor="#FFFFFF">
				<TD WIDTH="30%" height="40" ALIGN="CENTER" CLASS="txtContenido">
					<html:button property="boton" styleClass="boton" onclick="goToEliminar(AccsManagerForm)"><bean:message key="button.eliminar"/></html:button>
				</TD>
				<TD WIDTH="30%" height="40" ALIGN="CENTER" CLASS="txtContenido">
					<html:button property="boton" styleClass="boton" onclick="goToActivar(AccsManagerForm)"><bean:message key="accsMgr.button.activar"/></html:button>
				</TD>
				<TD WIDTH="30%" height="40" ALIGN="CENTER" CLASS="txtContenido">
					<html:button property="boton" styleClass="boton" onclick="goToModificar(AccsManagerForm)"><bean:message key="accsMgr.button.desactivar"/></html:button>
				</TD>
				
			</TR>
	</TABLE> 
	<logic:present name="mensaje">
		<TABLE WIDTH="80%" align="center">	
			<bean:define id="mensaje" name="mensaje" type="java.lang.String"/>
			<TR><TD height="35" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
			 		<bean:write name="mensaje" />
			 	</TD>
			</TR>
		</TABLE>
	</logic:present>	
			 <div align="right"><html:link action="/Logout.do" styleClass="txtContenidoRojoLink"><bean:message key="adminCatalogoHome.logout"/></html:link></div>	
	</html:form> 
</BODY>
</html:html>
