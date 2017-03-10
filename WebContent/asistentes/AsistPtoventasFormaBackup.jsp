<%@ page import="mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO"%>
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
	
	public String[] getParameters(HttpServletRequest req, String[] valores){
		String[] parametros  = new String[valores.length];
		String res = null;
		try {
			for(int i = 0; i < valores.length; i++){
				res = req.getParameter(valores[i]);
				if(res != null && (res.trim().length() == 0)){
					res = null;
				}else{
					parametros[i]= res;
				}
			}
		} catch (NullPointerException npe) {
		}
		return parametros;	
	}

	public Object[] getParametros(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		ArrayList param = new ArrayList();
		param.add(res);
		return param.toArray();	
	}	
	
	public Object[] getParametrosNull(HttpServletRequest req, String[] valor){
		String parametro = "";
		ArrayList param = new ArrayList();

		for(int i=0;valor.length>i;i++){
			try{
				parametro = req.getParameter(valor[i]);
			} catch (NullPointerException npe) {
			}	
			if(parametro == null || parametro.equalsIgnoreCase("")==true) param.add(null);
			else param.add(parametro);
		}
		return param.toArray();	
	}
%> 
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE><bean:message key="vendedoresForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script language="JavaScript" src="../js/SmartScroll.js"></script>
		<script language="JavaScript" src="../js/tabs.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script language="Javascript1.2">

		function updatePcNomPtoventas(form,item){
			changeInput(form.pcNomPtoventas,item.value,'nombrePto');
			changeInput(form.pcCveDiv,item.value,'division');
			changeInput(form.pcCveSubdiv,item.value,'subdiv');
		}

		function updatePcNomVendedor(form,item){
			changeInput(form.pcNombreCompleto,item.value,'nomVend');
		}
		
		function goToSiguiente(form) {
			var action = "";
				action ="?action=consultar&control=siguiente";
				form.action = form.action + action;
				form.submit();
		}
		function goToRegresar(form) {
			var action = "";
				action ="?action=consultar&control=regresar";
				form.action = form.action + action;
				form.submit();
		}

		function updateTextField(comboBox){
			if(comboBox.options[comboBox.selectedIndex].value == 'persona' || comboBox.options[comboBox.selectedIndex].value == 'tienda'){
				if( window.mmIsOpera ) {
					document.getElementById("CveVendedor").style.visibility = 'visible';
					document.getElementById("cveDefinir").style.visibility = 'hidden';
					return;
				}
				if (document.all) {
					document.all.CveVendedor.style.visibility = 'visible';
					document.all.cveDefinir.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("CveVendedor").style.visibility = 'visible';
					document.getElementById("cveDefinir").style.visibility = 'hidden';
					return;
				}
			}else if ( comboBox.options[comboBox.selectedIndex].value == 'distribuidor' || comboBox.options[comboBox.selectedIndex].value == 'interno'){
				if( window.mmIsOpera ){
				 	document.getElementById("cveDefinir").style.visibility = 'visible';
				 	document.getElementById("CveVendedor").style.visibility = 'hidden';
				 	return;
				}
				if (document.all) {
					document.all.cveDefinir.style.visibility = 'visible';
					document.all.CveVendedor.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("cveDefinir").style.visibility = 'visible';
					document.getElementById("CveVendedor").style.visibility = 'hidden';
					return;
				}
			}
		}

	
		</script>
		
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
	<html:form method="post" styleId="AsistPtoventasForm" action="AsistPtoventasAction" onsubmit="return validateAsistPtoventasForm(this);"> 
	<bean:define id="userSession" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>

	<TABLE WIDTH="80%" BORDER="0" CELLSPACING="1" CELLPADDING="2" BGCOLOR="#5D5D5E" align="center">
	<TR>
	<TD BGCOLOR="#FFFFFF">
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#CC0000">
		<TR>
		<TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" ><B><bean:message key="asistPtoventas.header"/></B></TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	</TABLE>

<BR><BR>


<logic:equal name="AsistPtoventasForm" property="page" value="0">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<logic:notEqual name="AsistPtoventasForm" property="pcCveVendedorChg" value="0" >
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcNomVendedor.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNombreCompletoChg"/>
			</TD>
		</TR>
		</logic:notEqual>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="asistente.message.buscar.ptoventas"/>
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
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCveDivSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<logic:present name="USER">
			<fsj:comboGeneral
		    	name = "pcCveDivSeek"
				queryId="<%=Q.DIVISION_ALL%>"				
				valueMethodName = "getPcCveDiv"
				descrMethodName = "getPcDescDiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_DivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveDivSeek")%>' 
				textoNoSeleccion = "-- Seleccione una División --"
				valorNoSeleccion = ""
				parametrosQuery='<%= new Object[] {String.valueOf(userSession.getPcCveDiv())}%>'
			/>
		</logic:present>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCveTipoCanalSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
	
			<fsj:comboGeneral
		    	name = "pcCveTipoCanalSeek"
				queryId="<%=Q.TIPO_CANALES_SELECTED%>"				
				valueMethodName = "getPcCveTpCanal"
				descrMethodName = "getPcDescTpCanal"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Tipo_CanalVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveTipoCanalSeek")%>' 
				textoNoSeleccion = "-- Seleccione un Tipo de Canal --"
				valorNoSeleccion = ""
				script='onChange=goToDynaBuscar(AsistPtoventasForm,"tipo_canal_seek")'
			/>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCveCanalSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
	
			<fsj:comboGeneral
		    	name = "pcCveCanalSeek"
				valueMethodName = "getPcCveCanal"
				descrMethodName = "getPcDescCanal"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveCanalSeek")%>' 
				textoNoSeleccion = "-- Seleccione un Canal --"
				valorNoSeleccion = ""
				queryId="<%=Q.CANALES_X_TIPO%>"	
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_CanalVO.class%>"
				camposExtras='getPcDescCanal=descCanal,getPcCveTpCanal=cveTipo'
				agregarValorCamposExtras='true'
				parametrosQuery='<%= getParametros(request,"pcCveTipoCanalSeek")%>'			
			/>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCvePtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCvePtoventasSeek" name="AsistPtoventasForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcNomPtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventasSeek" name="AsistPtoventasForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistPtoventasForm,'puntos_venta')"><bean:message key="asistente.button.siguiente"/></html:button>
		 		</TD>
		 </TR>
		</table>
	</TD></TR></TABLE>	
</logic:equal>


<logic:equal name="AsistPtoventasForm" property="page" value="1">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="asistente.message.buscar.ptoventas"/>
			</TD>
		</TR>
		<logic:notEqual name="AsistPtoventasForm" property="pcCveVendedorChg" value="0" >
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcNomVendedor.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNombreCompletoChg"/>
			</TD>
		</TR>
		</logic:notEqual>		
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
<logic:present name="tablaPuntosVenta">
		<bean:define id="tablaPuntosVenta" name="tablaPuntosVenta" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
			<html:hidden name="AsistPtoventasForm" property="pcNomPtoventas"/>
			<html:hidden name="AsistPtoventasForm" property="pcCveDiv"/>
			<html:hidden name="AsistPtoventasForm" property="pcCveSubdiv"/>
			<div style="height:300;width:100%;overflow:scroll;visibility:visible">
			<fsj:generalHomeTableEstatico
				lista='<%= tablaPuntosVenta %>'	
				valueMethodName = "getPcCvePtoventas"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Num Econ,Punto Venta,Canal,Tipo Canal,División"
				metodos="getPcCveReferencia,getPcNomPtoventas,getPcDescCanal,getPcDescTpCanal,getPcDescSubdiv"
				anchosColumnas="10%,23%,23%,23%,13%"
				alineacionesColumnas="center,center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCvePtoventas"
				camposExtras='getPcNomPtoventas=nombrePto,getPcCveDiv=division,getPcCveSubdiv=subdiv'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCvePtoventas")%>' 
				script="onClick='updatePcNomPtoventas(AsistPtoventasForm,this)'"
			/></div>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPtoventasForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistPtoventasForm)"><bean:message key="asistente.button.siguiente"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noData" /><html:hidden name="AsistPtoventasForm" property="pcCvePtoventas" value="0"/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPtoventasForm)"><bean:message key="vendedoresForm.button.anterior"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
		</table>
	</TD></TR></TABLE>	
</logic:equal>


<logic:equal name="AsistPtoventasForm" property="page" value="2">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="20%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCvePtoventas.displayname"/> :</TD>
			<TD WIDTH="80%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNomPtoventas"/>
			</TD>
		</TR>
	</table>
	
<BR><BR>
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
		<html:hidden name="AsistPtoventasForm" property="pcNomPtoventas"/>
		<html:hidden name="AsistPtoventasForm" property="pcCvePtoventas"/>
		<html:hidden name="AsistPtoventasForm" property="pcCveDiv"/>
		<html:hidden name="AsistPtoventasForm" property="pcCveSubdiv"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<bean:message key="asistente.message.buscar.vendedor"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="asistente.message.buscar1.vendedor"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcNomVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text name="AsistPtoventasForm" property="pcNomVendedorSeek" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCvePuestoSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
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
			/></TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD COLSPAN="2" ALIGN="CENTER" CLASS="txtContenido"><bean:message key="asistente.message.buscar2.vendedor"/></TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text name="AsistPtoventasForm"  property="pcCveVendedorSeek" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPtoventasForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistPtoventasForm,'vendedor')"><bean:message key="asistente.button.siguiente"/></html:button>
		 		</TD>
		 </TR>
		 </TABLE>
	 </TD>
	 </TR>
	 </table>
</logic:equal>

<logic:equal name="AsistPtoventasForm" property="page" value="3">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcNomPtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNomPtoventas"/>
			</TD>
		</TR>
	</table>
	
<BR><BR>
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
		<html:hidden name="AsistPtoventasForm" property="pcNombreCompleto"/>
		<html:hidden name="AsistPtoventasForm" property="pcNomPtoventas"/>
		<html:hidden name="AsistPtoventasForm" property="pcCvePtoventas"/>
		<html:hidden name="AsistPtoventasForm" property="pcCveDiv"/>
		<html:hidden name="AsistPtoventasForm" property="pcCveSubdiv"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">
<logic:present name="tablaVendedores">
	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
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
				titulos="Clave,Nombre,Puesto,Fecha de Alta,Status"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto,getPcFechaAltaStr,getPcStatus"
				anchosColumnas="15%,33%,18%,18%,8%"
				alineacionesColumnas="center,center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCveVendedor"
				script="onClick='updatePcNomVendedor(AsistPtoventasForm,this)'"
				camposExtras='getPcCveVendedor=cve,getPcNombreCompleto=nomVend'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveVendedor")%>'
			/></div>
		 </TD></TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>		 
		 <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPtoventasForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistPtoventasForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
	 	 </TR>
		 </TABLE>
	 </TD>
	 </TR>
	 </table>	
</logic:equal>

<logic:equal name="AsistPtoventasForm" property="page" value="4">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
		<html:hidden name="AsistPtoventasForm" property="pcCveVendedor"/>
		<html:hidden name="AsistPtoventasForm" property="pcNombreCompleto"/>
		<html:hidden name="AsistPtoventasForm" property="pcNomPtoventas"/>
		<html:hidden name="AsistPtoventasForm" property="pcCvePtoventas"/>
		<html:hidden name="AsistPtoventasForm" property="pcCveDiv"/>
		<html:hidden name="AsistPtoventasForm" property="pcCveSubdiv"/>		
		<TABLE cellpadding="0" cellspacing="0" border="0" align="center">
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcNomPtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNomPtoventas"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcNomVendedor.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNombreCompleto"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcFchMovimiento.displayname" /> <bean:message key="mascara.fecha"/>: 
				<html:text name="AsistPtoventasForm" property="pcFchMovimiento"  maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('AsistPtoventasForm.pcFchMovimiento','','','DD/MM/YYYY','es')">
				<img src="../images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPtoventasForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToAgregar(AsistPtoventasForm)"><bean:message key="asistente.button.confirmar"/></html:button>
	 		</TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	<TR><TD>&nbsp;</TD></TR><TR><TD>
		<TABLE cellpadding="0" cellspacing="0" border="0" align="center">
		<TR>
		<TD HEIGHT="20"  CLASS="txtContenidoBold" ALIGN="CENTER" ><B><bean:message key="asistPtoventas.header.relaciones"/></B></TD>
		</TR>
		<TR><TD BGCOLOR="#FFFFFF">
<logic:present name="tablaRelaciones">
	<bean:define id="tablaRelaciones" name="tablaRelaciones" type="java.util.ArrayList"/>
			<fsj:generalTableEstatico
				lista='<%= tablaRelaciones %>'
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Inter_Vendedores_PtoventasVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Vendedor,Punto Venta,Canal,Fecha"
				metodos="getPcNombreVendedor,getPcNomPtoventas,getPcDescCanal,getPcFchMovimientoStr"
				anchosColumnas="23%,23%,23%,23%"
				alineacionesColumnas="center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
			/>
</logic:present>
		</TD></TR>
		</TABLE>		
	</TD>
	</TR>
	</table>
</logic:equal>

<logic:equal name="AsistPtoventasForm" property="page" value="5">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
		<html:hidden name="AsistPtoventasForm" property="pcCveVendedor"/>
		<html:hidden name="AsistPtoventasForm" property="pcNombreCompleto"/>
		<html:hidden name="AsistPtoventasForm" property="pcNomPtoventas"/>
		<html:hidden name="AsistPtoventasForm" property="pcCvePtoventas"/>
		<html:hidden name="AsistPtoventasForm" property="pcCveDiv"/>
		<html:hidden name="AsistPtoventasForm" property="pcCveSubdiv"/>		
		<TABLE cellpadding="0" cellspacing="0" border="0" align="center">
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCvePtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNomPtoventas"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCveVendedor.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNombreCompleto"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistPtoventasForm,'otro_vendedor')"><bean:message key="asistPtoventas.button.otroVendedor"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistPtoventasForm,'otro_punto')"><bean:message key="asistPtoventas.button.otroPunto"/></html:button>
	 		</TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	<TR><TD>&nbsp;</TD></TR><TR><TD>
		<TABLE cellpadding="0" cellspacing="0" border="0" align="center">
		<TR>
		<TD HEIGHT="20"  CLASS="txtContenidoBold" ALIGN="CENTER" ><B><bean:message key="asistPtoventas.header.relaciones"/></B></TD>
		</TR>
		<TR><TD BGCOLOR="#FFFFFF">
<logic:present name="tablaRelaciones">
	<bean:define id="tablaRelaciones" name="tablaRelaciones" type="java.util.ArrayList"/>
			<fsj:generalTableEstatico
				lista='<%= tablaRelaciones %>'
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Inter_Vendedores_PtoventasVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Vendedor,Punto Venta,Canal,Fecha"
				metodos="getPcNombreVendedor,getPcNomPtoventas,getPcDescCanal,getPcFchMovimientoStr"
				anchosColumnas="23%,23%,23%,23%"
				alineacionesColumnas="center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
			/>
</logic:present>
		</TD></TR>
		</TABLE>	
	</TD></TR>
	</table>
</logic:equal>


	<html:hidden name="AsistPtoventasForm" property="pcCveVendedorChg"/>
	<html:hidden name="AsistPtoventasForm" property="pcNombreCompletoChg"/>
	<html:hidden name="AsistPtoventasForm" property="page"/>
	<html:hidden property="coordXHolder" name="AsistPtoventasForm" />
	<html:hidden property="coordYHolder" name="AsistPtoventasForm" />
	<BR><BR>
	</html:form> 
<!--html:javascript formName="AsistPtoventasForm" dynamicJavascript="true" staticJavascript="true"/-->
</BODY>
</html:html>
