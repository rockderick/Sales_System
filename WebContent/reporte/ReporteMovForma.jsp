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
<title><bean:message key="reporteForm.title"/></TITLE>
	<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	<script language="JavaScript" src="../js/tools.js"></script>
	<script language="JavaScript" src="../js/Calendario.js"></script>
    <script type="text/javascript" src="../js/tabs.js"></script>
	<script language="Javascript1.2" src="../js/std_button.js"></script>
	<script>history.forward(1)</script>
</HEAD>
<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" >

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
	<html:form method="post" action="ReporteMovimientoAction" onsubmit="return validateReporteForm(this);"> 
	<bean:define id="ReporteMovimientoForm" name="ReporteMovimientoForm" type="mx.com.iusacell.catalogo.web.reporte.struts.action.ReporteMovimientoForm"/>	
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="reporteMovForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<TABLE WIDTH="700" align="center">	
	  <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="4">
			<bean:message key="reporteMovForm.message.buscar"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%"  CLASS="txtContenidoRojo" colspan="2">Divisi&oacute;n :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData" colspan="2">
		  <logic:equal name="divisionUsuario" property="pcCveDiv" value="0">
			<fsj:comboGeneral
		    	name = "pcCveDivisionSeek"
				queryId="<%=Q.DIVISION_ALL%>"				
				valueMethodName = "getPcCveDiv"
				descrMethodName = "getPcDescDiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_DivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveDivisionSeek")%>' 
				textoNoSeleccion = "-- Seleccione una División --"
				valorNoSeleccion = ""
				parametrosQuery='<%= new Object[] {String.valueOf(divisionUsuario.getPcCveSubdiv())}%>'
			/>
		  </logic:equal>
		  <logic:notEqual name="divisionUsuario" property="pcCveDiv" value="0">
			<fsj:comboGeneral
		    	name = "pcCveDivisionSeek"
				queryId="<%=Q.DIVISION_ALL%>"				
				valueMethodName = "getPcCveDiv"
				descrMethodName = "getPcDescDiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_DivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveDivisionSeek")%>' 
				textoNoSeleccion = "-- Seleccione una División --"
				valorNoSeleccion = ""
				parametrosQuery='<%= new Object[] {divisionUsuario.getPcCvesDivs()}%>'
			/>
		  </logic:notEqual>	
			</TD>
		</TR>	
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%"  CLASS="txtContenidoRojo" colspan="2"><bean:message key="reporteMovForm.pcCvePuestoSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData" colspan="2">
			<fsj:comboGeneral
		    	name = "pcCvePuestoSeek"
				queryId="<%=Q.PUESTOS_ALL%>"				
				valueMethodName = "getPcCvePuesto"
				descrMethodName = "getPcDescPuesto"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCvePuestoSeek")%>' 
				textoNoSeleccion = "-- Seleccione un Puesto --"
				valorNoSeleccion = ""
			/>	
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%"  CLASS="txtContenidoRojo"  colspan="2"><bean:message key="reporteMovForm.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData" colspan="2">
				<html:text property="pcCveVendedorSeek" name="ReporteMovimientoForm" maxlength="7" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%"  CLASS="txtContenidoRojo" colspan="2"><bean:message key="reporteMovForm.pcNomVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData" colspan="2">
				<html:text property="pcNomVendedorSeek" name="ReporteMovimientoForm" maxlength="45" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%"  CLASS="txtContenidoRojo" colspan="2"><bean:message key="reporteMovForm.pcNomClaveEmpleadoReferenciaSeek"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData" colspan="2">
				<html:text property="pcNomClaveEmpleadoReferenciaSeek" name="ReporteMovimientoForm" maxlength="8" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#F9F9F9">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="4">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarMovimiento(ReporteMovimientoForm,'vendedor')"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
<logic:messagesPresent>
		<TR><TD height="40" colspan="4" CLASS="errormessagerojo">
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6"> <bean:write name="error"/>		   </html:messages>
		   <bean:message key="errors.footer"/>
		</TD>
		</TR>
</logic:messagesPresent>
<logic:present name="tablaVendedores">
	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="4">
			<div style="height:200;width:100%;overflow:scroll;visibility:visible">	
			<fsj:generalHomeTableEstatico
				lista='<%= tablaVendedores %>'	
				valueMethodName = "getPcCveVendedor"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Clave,Nombre,Puesto,División,Status(Fecha de Alta)"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto,getPcSubdivision,getPcStatusFecha"
				anchosColumnas="12%,30%,20%,20%,18%"
				alineacionesColumnas="center,center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="7%"
				radioName="pcCveVendedorRadio"
				selected='<%=getValor(request,"pcCveVendedorRadio")%>'
			/></div>
		</TD></TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%"  CLASS="txtContenidoRojo"><bean:message key="reporteMovForm.fechaRango.inferior.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="30%" ALIGN="center" bgcolor="#F9F9F9" CLASS="PTabData">
				<html:text property="rangoFchDesde" name="ReporteMovimientoForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('ReporteMovimientoForm.rangoFchDesde','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
			</TD>
			<TD WIDTH="40%"  CLASS="txtContenidoRojo"><bean:message key="reporteMovForm.fechaRango.superior.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="30%" ALIGN="center" bgcolor="#F9F9F9" CLASS="PTabData">
				<html:text property="rangoFchHasta" name="ReporteMovimientoForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('ReporteMovimientoForm.rangoFchHasta','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
			</TD>
		</TR>
		 <TR>
				<TD height="40" colspan="4" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarDetalle(ReporteMovimientoForm,'movimientos')"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD height="35" colspan="4" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>
		</TABLE>
	
<BR><BR>	
<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="reporteMovForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

<logic:present name="tablaReporte">
	<bean:define id="tablaReporte" name="tablaReporte" type="java.util.ArrayList"/>
	<TABLE WIDTH="700" BORDER="0" CELLSPACING="1" CELLPADDING="2" ID="borde" align="center">
	<TR><TD class='td_mensajes'><bean:message key="reporteMovForm.table.header.cveVendedor"/></TD>
		<TD class='td_mensajes'><bean:message key="reporteMovForm.table.header.nomVendedor"/></TD>
		<TD class='td_mensajes'><bean:message key="reporteMovForm.table.header.tipoMov"/></TD>
		<TD class='td_mensajes'><bean:message key="reporteMovForm.table.header.Origen"/></TD>
		<TD class='td_mensajes'><bean:message key="reporteMovForm.table.header.Destino"/></TD>
		<TD class='td_mensajes'><bean:message key="reporteMovForm.table.header.Fecha"/></TD>
		<TD class='td_mensajes'><bean:message key="reporteMovForm.table.header.FechaTran"/></TD>
	</TR>
	<logic:iterate id="movimiento" name="tablaReporte" indexId="rowNum" type="mx.com.iusacell.catalogo.modelo.Pc_Bit_MovimientosVO">
	<% 	if(movimiento.getPcDescPuestoOrigen() != null && !movimiento.getPcDescPuestoOrigen().equals("")) { 
			if(rowNum.intValue()%2==0){ 
	%>
	<TR class='td_gris_tablas'>
	<% 		}else{%>
	<TR class='td_blanco_tablas'>
	<% 		}%>
		<TD><bean:write name="movimiento" property="pcCveVendedor"/></TD>
		<TD><bean:write name="movimiento" property="pcNomCompVendedor"/></TD>
		<TD><bean:message key="reporteMovForm.movimiento.header.puesto"/></TD>
		<TD><bean:write name="movimiento" property="pcDescPuestoOrigen"/></TD>
		<TD><bean:write name="movimiento" property="pcDescPuestoDestino"/></TD>
		<TD><bean:write name="movimiento" property="pcFchMovimientoStr"/></TD>
		<TD><bean:write name="movimiento" property="pcFchTransaccionStr"/></TD>
	</TR>
	<% 	} 
		if(movimiento.getPcNomCompSuperiorOrigen() != null && !(movimiento.getPcNomCompSuperiorOrigen().trim().equals(""))) { 
			if(rowNum.intValue()%2==0){ 
	%>
	<TR class='td_gris_tablas'>
	<% 		}else{%>
	<TR class='td_blanco_tablas'>
	<% 		}%>
		<TD><bean:write name="movimiento" property="pcCveVendedor"/></TD>
		<TD><bean:write name="movimiento" property="pcNomCompVendedor"/></TD>
		<TD><bean:message key="reporteMovForm.movimiento.header.superior"/></TD>
		<TD><bean:write name="movimiento" property="pcNomCompSuperiorOrigen"/></TD>
		<TD><bean:write name="movimiento" property="pcNomCompSuperiorDestino"/></TD>
		<TD><bean:write name="movimiento" property="pcFchMovimientoStr"/></TD>
		<TD><bean:write name="movimiento" property="pcFchTransaccionStr"/></TD>
	</TR>
	<% 	} 
		if((movimiento.getPcNomPtoventasOrigen() != null && !movimiento.getPcNomPtoventasOrigen().equals("")) || (movimiento.getPcNomPtoventasDestino() != null && !movimiento.getPcNomPtoventasDestino().equals(""))) { 
			if(rowNum.intValue()%2==0){ 
	%>
	<TR class='td_gris_tablas'>
	<% 		}else{%>
	<TR class='td_blanco_tablas'>
	<% 		} %>
		<TD><bean:write name="movimiento" property="pcCveVendedor"/></TD>
		<TD><bean:write name="movimiento" property="pcNomCompVendedor"/></TD>
		<TD><bean:message key="reporteMovForm.movimiento.header.tienda"/></TD>
		<TD><bean:write name="movimiento" property="pcNomPtoventasOrigen"/></TD>
		<TD><bean:write name="movimiento" property="pcNomPtoventasDestino"/></TD>
		<TD><bean:write name="movimiento" property="pcFchMovimientoStr"/></TD>
		<TD><bean:write name="movimiento" property="pcFchTransaccionStr"/></TD>
	</TR>
	<% 	} %>
	</logic:iterate>
	</TABLE>
</logic:present>

	</html:form> 
</BODY>
</html:html>
