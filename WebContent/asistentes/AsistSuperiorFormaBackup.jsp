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
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script language="Javascript1.2">

		function updatePcNomPtoventas(form,item){
			changeInput(form.pcNomPtoventas,item.value,'nombrePto');
			changeInput(form.pcCveDiv,item.value,'division');
		}

		function updatePcNombreCompleto(form,item){
			changeInput(form.pcNombreCompleto,item.value,'nombre');
			changeInput(form.pcCvePuesto,item.value,'cvePuesto');
			changeInput(form.pcDescPuesto,item.value,'puesto');
			changeInput(form.pcCveSuperiorActual,item.value,'cveSup');
			changeInput(form.pcNombreSuperiorActual,item.value,'nomSup');
		}

		function updatePcNombreSuperior(form,item){
			changeInput(form.pcNombreSuperior,item.value,'nombre');
		}
		
		function updatePcNombreSuperiorReasignar(form,item){
			changeInput(form.pcNombreSuperiorReasignar,item.value,'nombre');
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
		function goToInicio(form) {
			var action = "";
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
	<html:form styleId="AsistSuperiorForm" action="AsistSuperiorAction" method="POST" onsubmit="return validateAsistSuperiorForm(this);"> 
	<bean:define id="AsistSuperiorForm" name="AsistSuperiorForm" type="mx.com.iusacell.catalogo.web.asistentes.struts.action.AsistSuperiorForm"/>
	<bean:define id="userSession" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>


	<TABLE WIDTH="80%" BORDER="0" CELLSPACING="1" CELLPADDING="2" BGCOLOR="#5D5D5E" align="center">
	<TR>
	<TD BGCOLOR="#FFFFFF">
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#CC0000">
		<TR><TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" >
			<B><bean:message key="asistSuperior.header"/></B>
		</TD></TR>
		</TABLE>
	</TD>
	</TR>
	</TABLE>
	<BR><BR>

<logic:equal name="AsistSuperiorForm" property="page" value="0">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>
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
				<html:text property="pcNomVendedorSeek" name="AsistSuperiorForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
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
			<TD ALIGN="CENTER" CLASS="txtContenido" COLSPAN="2">
				<bean:message key="asistente.message.buscar2.vendedor"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="AsistSuperiorForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistSuperiorForm,'vendedores')"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
		 </TABLE>
	</TD></TR></table>	
<BR><BR>
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
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
<logic:present name="tablaVendedores">
	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
			<html:hidden name="AsistSuperiorForm" property="pcNombreCompleto"/>
			<html:hidden name="AsistSuperiorForm" property="pcCvePuesto"/>
			<html:hidden name="AsistSuperiorForm" property="pcDescPuesto"/>
			<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorActual"/>
			<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorActual"/>
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
				titulos="Clave,Nombre,Puesto,Superior"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto,getPcNombreSuperior"
				anchosColumnas="15%,33%,18%,26%"
				alineacionesColumnas="center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCveVendedor"
				script="onClick='updatePcNombreCompleto(AsistSuperiorForm,this)'"
				camposExtras='getPcNombreCompleto=nombre,getPcCvePuesto=cvePuesto,getPcDescPuesto=puesto,getPcNombreSuperior=nomSup,getPcCveSuperior=cveSup'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveVendedor")%>'
			/></div>
		</TD></TR>
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistSuperiorForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistSuperiorForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noData" />
		 		<html:hidden name="AsistSuperiorForm" property="pcCveVendedor" value="0"/>
		 		<html:hidden name="AsistSuperiorForm" property="pcNombreCompleto" value=""/>
		 		<html:hidden name="AsistSuperiorForm" property="pcCvePuesto" value=""/>
		 		<html:hidden name="AsistSuperiorForm" property="pcDescPuesto" value=""/>
		 		<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorActual" value="0"/>
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorActual" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistSuperiorForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
	</table>	
</logic:equal>

<logic:equal name="AsistSuperiorForm" property="page" value="1">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistSuperiorForm"/>
				<html:hidden property="pcCveVendedor" name="AsistSuperiorForm"/>
				<bean:write property="pcNombreCompleto" name="AsistSuperiorForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistSuperiorForm" />
				<html:hidden property="pcDescPuesto" name="AsistSuperiorForm" />
				<bean:write property="pcDescPuesto" name="AsistSuperiorForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperiorActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorActual" />
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorActual" />
				<bean:write name="AsistSuperiorForm" property="pcNombreSuperiorActual" />
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<bean:message key="asistSuperior.message.buscar.pagina1"/>
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
				<html:text property="pcNomVendedorSeek" name="AsistSuperiorForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
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
			<TD ALIGN="CENTER" CLASS="txtContenido" COLSPAN="2">
				<bean:message key="asistente.message.buscar2.vendedor"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="AsistSuperiorForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistSuperiorForm,'superior')"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
		 </TABLE>	
	</TD></TR>
	</table>
<BR><BR>
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
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
<logic:present name="tablaSuperiores">
	<bean:define id="tablaSuperiores" name="tablaSuperiores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
			<html:hidden name="AsistSuperiorForm" property="pcNombreSuperior"/>
			<div style="height:200;width:100%;overflow:scroll;visibility:visible">	
			<fsj:generalHomeTableEstatico
				lista='<%= tablaSuperiores %>'	
				valueMethodName = "getPcCveVendedor"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Clave,Nombre,Puesto"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto"
				anchosColumnas="15%,51%,26%"
				alineacionesColumnas="center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCveSuperior"
				script="onClick='updatePcNombreSuperior(AsistSuperiorForm,this)'"
				camposExtras='getPcNombreCompleto=nombre'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveSuperior")%>'
			/></div>
		</TD></TR>
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistSuperiorForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistSuperiorForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noData" />
		 		<html:hidden name="AsistSuperiorForm" property="pcCveSuperior" value="0"/>
		 		<html:hidden name="AsistSuperiorForm" property="pcNombreSuperior" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistSuperiorForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
	</table>		
</logic:equal>

<logic:equal name="AsistSuperiorForm" property="page" value="2">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistSuperiorForm"/>
				<html:hidden property="pcCveVendedor" name="AsistSuperiorForm"/>
				<bean:write property="pcNombreCompleto" name="AsistSuperiorForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistSuperiorForm" />
				<html:hidden property="pcDescPuesto" name="AsistSuperiorForm" />
				<bean:write property="pcDescPuesto" name="AsistSuperiorForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperiorActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorActual" />
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorActual" />
				<bean:write name="AsistSuperiorForm" property="pcNombreSuperiorActual" />
			</TD>
		</TR>		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperior.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistSuperiorForm" property="pcCveSuperior" />
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperior" />
				<bean:write name="AsistSuperiorForm" property="pcNombreSuperior" />
			</TD>
		</TR>
<logic:present name="tablaSubordinados">
	<bean:define id="tablaSubordinados" name="tablaSubordinados" type="java.util.ArrayList"/>
		<TR bgcolor="#E0E1E3">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<bean:message key="asistSuperior.message.subordinados"/>
			</TD>
		</TR>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
			<html:hidden name="AsistSuperiorForm" property="pcCvePuestoReasignar"/>
			<div style="height:200;width:100%;overflow:scroll;visibility:visible">	
			<fsj:generalTableEstatico
				lista='<%= tablaSubordinados %>'	
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Clave,Nombre,Puesto"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto"
				anchosColumnas="15%,51%,26%"
				alineacionesColumnas="center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
			/></div>
		</TD></TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoBold" colspan="2">
			<bean:message key="asistSuperior.message.buscar.pagina1"/>
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
				<html:text property="pcNomVendedorSeek" name="AsistSuperiorForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
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
			<TD ALIGN="CENTER" CLASS="txtContenido" COLSPAN="2">
				<bean:message key="asistente.message.buscar2.vendedor"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="AsistSuperiorForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistSuperiorForm,'superior_reasignar')"><bean:message key="button.buscar"/></html:button>
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
<logic:present name="tablaSuperiores">
	<bean:define id="tablaSuperiores" name="tablaSuperiores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
			<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorReasignar"/>
			<div style="height:200;width:100%;overflow:scroll;visibility:visible">	
			<fsj:generalHomeTableEstatico
				lista='<%= tablaSuperiores %>'	
				valueMethodName = "getPcCveVendedor"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Clave,Nombre,Puesto"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto"
				anchosColumnas="15%,51%,26%"
				alineacionesColumnas="center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCveSuperiorReasignar"
				script="onClick='updatePcNombreSuperiorReasignar(AsistSuperiorForm,this)'"
				camposExtras='getPcNombreCompleto=nombre'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveSuperiorReasignar")%>'
			/></div>
		</TD></TR>
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistSuperiorForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistSuperiorForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noData" />
		 		<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorReasignar" value="0"/>
		 		<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorReasignar" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistSuperiorForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistSuperiorForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 			</TD>
		 </TR>
</logic:present>
</logic:present>
<logic:present name="noSubordinados">
		<bean:define id="noSubordinados" name="noSubordinados" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noSubordinados" />
		 		<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorReasignar" value="0"/>
		 		<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorReasignar" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistSuperiorForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistSuperiorForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 			</TD>
		 </TR>
</logic:present>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

<logic:equal name="AsistSuperiorForm" property="page" value="3">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistSuperiorForm"/>
				<html:hidden property="pcCveVendedor" name="AsistSuperiorForm"/>
				<bean:write property="pcNombreCompleto" name="AsistSuperiorForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistSuperiorForm" />
				<html:hidden property="pcDescPuesto" name="AsistSuperiorForm" />
				<bean:write property="pcDescPuesto" name="AsistSuperiorForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperiorActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorActual" />
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorActual" />
				<bean:write name="AsistSuperiorForm" property="pcNombreSuperiorActual" />
			</TD>
		</TR>		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperior.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistSuperiorForm" property="pcCveSuperior" />
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperior" />
				<bean:write name="AsistSuperiorForm" property="pcNombreSuperior" />
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperiorReasignar.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorReasignar" />
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorReasignar" />
				<bean:write name="AsistSuperiorForm" property="pcNombreSuperiorReasignar" />
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistente.pcFchMovimiento.displayname" /> <bean:message key="mascara.fecha"/>: 
				<html:text name="AsistSuperiorForm" property="pcFchMovimiento"  maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('AsistSuperiorForm.pcFchMovimiento','','','DD/MM/YYYY','es')">
				<img src="../images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistSuperiorForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToModificar(AsistSuperiorForm)"><bean:message key="asistente.button.confirmar"/></html:button>
	 		</TD>
		</TR>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

<logic:equal name="AsistSuperiorForm" property="page" value="4">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreCompleto.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistSuperiorForm"/>
				<html:hidden property="pcCveVendedor" name="AsistSuperiorForm"/>
				<bean:write property="pcNombreCompleto" name="AsistSuperiorForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcDescPuesto.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistSuperiorForm" />
				<html:hidden property="pcDescPuesto" name="AsistSuperiorForm" />
				<bean:write property="pcDescPuesto" name="AsistSuperiorForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperior.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistSuperiorForm" property="pcCveSuperior" />
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperior" />
				<bean:write name="AsistSuperiorForm" property="pcNombreSuperior" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
		<logic:present name="AsistSuperiorForm" property="pcNombreSuperiorReasignar">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperiorReasignar.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistSuperiorForm" property="pcCveSuperiorReasignar" />
				<html:hidden name="AsistSuperiorForm" property="pcNombreSuperiorReasignar" />
				<bean:write name="AsistSuperiorForm" property="pcNombreSuperiorReasignar" />
			</TD>
		</logic:present>
		<logic:notPresent name="AsistSuperiorForm" property="pcNombreSuperiorReasignar">
			<TD COLSPAN="2"  CLASS="txtContenidoBold"><bean:message key="asistSuperior.pcNombreSuperiorSinReasignar.resumen"/></TD>
		</logic:notPresent>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToInicio(AsistSuperiorForm)"><bean:message key="asistente.button.regresar"/></html:button>
	 		</TD>
		</TR>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

	<html:hidden name="AsistSuperiorForm" property="pcCveVendedorChg"/>
	<html:hidden name="AsistSuperiorForm" property="pcNombreCompletoChg"/>
	<html:hidden name="AsistSuperiorForm" property="page"/>
	<html:hidden property="coordXHolder" name="AsistSuperiorForm" />
	<html:hidden property="coordYHolder" name="AsistSuperiorForm" />
	<BR><BR>
	</html:form> 
<!--html:javascript formName="AsistSuperiorForm" dynamicJavascript="true" staticJavascript="true"/-->
</BODY>
</html:html>
