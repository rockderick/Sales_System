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
<jsp:include page="/Icono.jsp"></jsp:include>
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
<title><bean:message key="vendedoresForm.title"/></TITLE>
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
			changeInput(form.pcCvePuestoActual,item.value,'cvePuesto');
			changeInput(form.pcDescPuestoActual,item.value,'puesto');
			changeInput(form.pcCveSuperiorActual,item.value,'cveSup');
			changeInput(form.pcNombreSuperiorActual,item.value,'nomSup');
		}

		function updatePcNombreSuperior(form,item){
			changeInput(form.pcNombreSuperior,item.value,'nombre');
		}
		
		function updatePcNombreSuperiorReasignar(form,item){
			changeInput(form.pcNombreSuperiorReasignar,item.value,'nombre');
		}

		function updatePcDescPuesto(form,item){
			changeInput(form.pcDescPuesto,item.value,'desc');
		}
		
		function goToSiguiente(form) {
			var action = "";
				action ="?action=consultar&control=siguiente";
				form.action = form.action + action;
				form.submit();
		}
		function goToSiguienteDescPuesto(form) {
		  var rad = document.getElementById('pcCvePuesto');
		     if(rad!=null){
				if(idCheck(form.pcCvePuesto)){
			 var action = "";
				action ="?action=consultar&control=siguiente";
				form.action = form.action + action;
				form.submit();
				}else{
				alert("Debe seleccionar un Puesto");
				}
			 }
		}
		function goToSiguienteCveVen(form) {
		  var rad = document.getElementById('pcCveVendedor');
		     if(rad!=null){
				if(idCheck(form.pcCveVendedor)){
			 var action = "";
				action ="?action=consultar&control=siguiente";
				form.action = form.action + action;
				form.submit();
				}else{
				alert("Debe seleccionar un Vendedor");
				}
			 }
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
	<html:form styleId="AsistPuestoForm" action="AsistPuestoAction" method="POST" onsubmit="return validateAsistPuestoForm(this);"> 
	<bean:define id="AsistPuestoForm" name="AsistPuestoForm" type="mx.com.iusacell.catalogo.web.asistentes.struts.action.AsistPuestoForm"/>
	<bean:define id="userSession" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="asistPuesto.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

<logic:equal name="AsistPuestoForm" property="page" value="0">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<bean:message key="asistente.message.buscar.vendedor"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="asistente.message.buscar1.vendedor"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedorSeek" name="AsistPuestoForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCvePuestoSeek.displayname"/> :</TD>
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
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="AsistPuestoForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" CLASS="txtContenidoRojo"><bean:message
				key="asistente.pcNomClaveEmpleadoReferenciaSeek.displayname" />
			:</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData"><html:text
				property="pcNomClaveEmpleadoReferenciaSeek"
				name="ClaveReferenciaForm" maxlength="40" size="30" value=""
				onchange="this.value=this.value.toUpperCase()" /></TD>
		</TR>
		 <TR bgcolor="#F9F9F9">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistPuestoForm,'vendedores')"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
		 </TABLE>
	</TD></TR></table>	
<BR><BR>
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
<logic:messagesPresent>
		<TR><TD CLASS="errormessagerojo" colspan="2"> <h2> <bean:message key="errors.errorgeneral"/> </h2>
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <li><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/></li>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>
<logic:present name="tablaVendedores">
	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<html:hidden name="AsistPuestoForm" property="pcNombreCompleto"/>
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
				script="onClick='updatePcNombreCompleto(AsistPuestoForm,this)'"
				camposExtras='getPcNombreCompleto=nombre,getPcCvePuesto=cvePuesto,getPcDescPuesto=puesto,getPcNombreSuperior=nomSup,getPcCveSuperior=cveSup'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveVendedor")%>'
			/></div>
		</TD></TR>
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguienteCveVen(AsistPuestoForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 		<html:hidden name="AsistPuestoForm" property="pcCveVendedor" value="0"/>
		 		<html:hidden name="AsistPuestoForm" property="pcNombreCompleto" value=""/>
		 		<html:hidden name="AsistPuestoForm" property="pcCvePuesto" value=""/>
		 		<html:hidden name="AsistPuestoForm" property="pcDescPuesto" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
	</table>	
</logic:equal>

<logic:equal name="AsistPuestoForm" property="page" value="1">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistPuestoForm"/>
				<html:hidden property="pcCveVendedor" name="AsistPuestoForm"/>
				<bean:write property="pcNombreCompleto" name="AsistPuestoForm"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcDescPuestoActual" name="AsistPuestoForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCvePuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<html:hidden property="pcDescPuesto" name="AsistPuestoForm" />
			<fsj:generalHomeTable
				queryId="<%=Q.PUESTOS_ALL%>"	
				valueMethodName = "getPcCvePuesto"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Puesto"
				metodos="getPcDescPuesto"
				anchosColumnas="90%"
				alineacionesColumnas="center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="10%"
				radioName="pcCvePuesto"
				script="onClick='updatePcDescPuesto(AsistPuestoForm,this)'"
				camposExtras='getPcDescPuesto=desc'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCvePuesto")%>'
			/></TD>
		</TR>		
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguienteDescPuesto(AsistPuestoForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
		 </TR>
		 </TABLE>
	</TD></TR>
	</table>
</logic:equal>

<logic:equal name="AsistPuestoForm" property="page" value="2">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistPuestoForm"/>
				<html:hidden property="pcCveVendedor" name="AsistPuestoForm"/>
				<bean:write property="pcNombreCompleto" name="AsistPuestoForm"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistPuestoForm" />
				<html:hidden property="pcDescPuesto" name="AsistPuestoForm" />
				<bean:write property="pcDescPuesto" name="AsistPuestoForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcDescPuestoActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcDescPuestoActual" name="AsistPuestoForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperiorActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcNombreSuperiorActual" name="AsistPuestoForm"  />
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<bean:message key="asistPuesto.message.cambio.superior"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<bean:message key="asistPuesto.message.buscar.pagina1"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="asistente.message.buscar1.vendedor"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedorSeek" name="AsistPuestoForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCvePuestoSeek.displayname"/> :</TD>
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
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="AsistPuestoForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message	key="asistente.pcNomClaveEmpleadoReferenciaSeek.displayname" />	:</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">	<html:text
				property="pcNomClaveEmpleadoReferenciaSeek"
				name="AsistPuestoForm" maxlength="40" size="30" value=""
				onchange="this.value=this.value.toUpperCase()" /></TD>
		</TR>
		
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistPuestoForm,'superior')"><bean:message key="button.buscar"/></html:button>
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
		      <li><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/></li>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>
<logic:present name="tablaSuperiores">
	<bean:define id="tablaSuperiores" name="tablaSuperiores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<html:hidden name="AsistPuestoForm" property="pcNombreSuperior"/>
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
				script="onClick='updatePcNombreSuperior(AsistPuestoForm,this)'"
				camposExtras='getPcNombreCompleto=nombre'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveSuperior")%>'
			/></div>
		</TD></TR>
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistPuestoForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 		<html:hidden name="AsistPuestoForm" property="pcCveSuperior" value="0"/>
		 		<html:hidden name="AsistPuestoForm" property="pcNombreSuperior" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
	</table>		
</logic:equal>

<logic:equal name="AsistPuestoForm" property="page" value="3">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistPuestoForm"/>
				<html:hidden property="pcCveVendedor" name="AsistPuestoForm"/>
				<bean:write property="pcNombreCompleto" name="AsistPuestoForm"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistPuestoForm" />
				<html:hidden property="pcDescPuesto" name="AsistPuestoForm" />
				<bean:write property="pcDescPuesto" name="AsistPuestoForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcDescPuestoActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcDescPuestoActual" name="AsistPuestoForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperiorActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPuestoForm" property="pcNombreSuperiorActual" />
			</TD>
		</TR>		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperior.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistPuestoForm" property="pcCveSuperior" />
				<html:hidden name="AsistPuestoForm" property="pcNombreSuperior" />
				<bean:write name="AsistPuestoForm" property="pcNombreSuperior" />
			</TD>
		</TR>
<logic:present name="tablaSubordinados">
	<bean:define id="tablaSubordinados" name="tablaSubordinados" type="java.util.ArrayList"/>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<bean:message key="asistPuesto.message.subordinados"/>
			</TD>
		</TR>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<html:hidden name="AsistPuestoForm" property="pcCvePuestoReasignar"/>
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
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<bean:message key="asistPuesto.message.buscar.pagina1"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="asistente.message.buscar1.vendedor"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedorSeek" name="AsistPuestoForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCvePuestoSeek.displayname"/> :</TD>
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
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="AsistPuestoForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"> <bean:message key="asistente.pcNomClaveEmpleadoReferenciaSeek.displayname" />:</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData"><html:text
				property="pcNomClaveEmpleadoReferenciaSeek"
				name="AsistPuestoForm" maxlength="40" size="30" value=""
				onchange="this.value=this.value.toUpperCase()" /></TD>
		</TR>
		
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(AsistPuestoForm,'superior_reasignar')"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
<logic:messagesPresent>
		<TR><TD CLASS="errormessagerojo" colspan="2">
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <li><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/></li>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>
<logic:present name="tablaSuperiores">
	<bean:define id="tablaSuperiores" name="tablaSuperiores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<html:hidden name="AsistPuestoForm" property="pcNombreSuperiorReasignar"/>
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
				script="onClick='updatePcNombreSuperiorReasignar(AsistPuestoForm,this)'"
				camposExtras='getPcNombreCompleto=nombre'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveSuperiorReasignar")%>'
			/></div>
		</TD></TR>
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistPuestoForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 		<html:hidden name="AsistPuestoForm" property="pcCveSuperiorReasignar" value="0"/>
		 		<html:hidden name="AsistPuestoForm" property="pcNombreSuperiorReasignar" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistPuestoForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 			</TD>
		 </TR>
</logic:present>
</logic:present>
<logic:present name="noSubordinados">
		<bean:define id="noSubordinados" name="noSubordinados" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noSubordinados" />
		 		<html:hidden name="AsistPuestoForm" property="pcCveSuperiorReasignar" value="0"/>
		 		<html:hidden name="AsistPuestoForm" property="pcNombreSuperiorReasignar" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistPuestoForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 			</TD>
		 </TR>
</logic:present>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

<logic:equal name="AsistPuestoForm" property="page" value="4">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistPuestoForm"/>
				<html:hidden property="pcCveVendedor" name="AsistPuestoForm"/>
				<bean:write property="pcNombreCompleto" name="AsistPuestoForm"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistPuestoForm" />
				<html:hidden property="pcDescPuesto" name="AsistPuestoForm" />
				<bean:write property="pcDescPuesto" name="AsistPuestoForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcDescPuestoActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcDescPuestoActual" name="AsistPuestoForm" />
			</TD>
		</TR>
		<logic:equal name="AsistPuestoForm" property="pcCveSuperiorChg" value="1">
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperiorActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPuestoForm" property="pcNombreSuperiorActual" />
			</TD>
		</TR>		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperior.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistPuestoForm" property="pcCveSuperior" />
				<html:hidden name="AsistPuestoForm" property="pcNombreSuperior" />
				<bean:write name="AsistPuestoForm" property="pcNombreSuperior" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperiorReasignar.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistPuestoForm" property="pcCveSuperiorReasignar" />
				<html:hidden name="AsistPuestoForm" property="pcNombreSuperiorReasignar" />
				<bean:write name="AsistPuestoForm" property="pcNombreSuperiorReasignar" />
			</TD>
		</TR>
		</logic:equal>
		<logic:equal name="AsistPuestoForm" property="pcCveSuperiorChg" value="0">
		<TR BGCOLOR="#FFFFFF">
			<TD COLSPAN ="2">
				<html:hidden name="AsistPuestoForm" property="pcCveSuperior" />
				<html:hidden name="AsistPuestoForm" property="pcNombreSuperior" />
				<html:hidden name="AsistPuestoForm" property="pcCveSuperiorReasignar" />
				<html:hidden name="AsistPuestoForm" property="pcNombreSuperiorReasignar" />
			</TD>
		</TR>
		</logic:equal>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcFchMovimiento.displayname" /> <bean:message key="mascara.fecha"/>: 
				<html:text name="AsistPuestoForm" property="pcFchMovimiento"  maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('AsistPuestoForm.pcFchMovimiento','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPuestoForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToModificar(AsistPuestoForm)"><bean:message key="asistente.button.confirmar"/></html:button>
	 		</TD>
		</TR>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

<logic:equal name="AsistPuestoForm" property="page" value="5">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreCompleto.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistPuestoForm"/>
				<html:hidden property="pcCveVendedor" name="AsistPuestoForm"/>
				<bean:write property="pcNombreCompleto" name="AsistPuestoForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcDescPuesto.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistPuestoForm" />
				<html:hidden property="pcDescPuesto" name="AsistPuestoForm" />
				<bean:write property="pcDescPuesto" name="AsistPuestoForm" />
			</TD>
		</TR>
		<logic:equal name="AsistPuestoForm" property="pcCveSuperiorChg" value="1">
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperior.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCveSuperior" name="AsistPuestoForm" />
				<html:hidden property="pcNombreSuperior" name="AsistPuestoForm" />
				<bean:write property="pcNombreSuperior" name="AsistPuestoForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
		<logic:notEqual name="AsistPuestoForm" property="pcNombreSuperiorReasignar" value="">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperiorReasignar.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistPuestoForm" property="pcCveSuperiorReasignar" />
				<html:hidden name="AsistPuestoForm" property="pcNombreSuperiorReasignar" />
				<bean:write name="AsistPuestoForm" property="pcNombreSuperiorReasignar" />
			</TD>
		</logic:notEqual>
		<logic:equal name="AsistPuestoForm" property="pcNombreSuperiorReasignar" value="">
			<TD COLSPAN="2"  CLASS="txtContenidoRojo"><bean:message key="asistPuesto.pcNombreSuperiorSinReasignar.resumen"/></TD>
		</logic:equal>
		</TR>
		</logic:equal>
		<logic:equal name="AsistPuestoForm" property="pcCveSuperiorChg" value="0">
		<TR BGCOLOR="#FFFFFF">
			<TD COLSPAN ="2">
				<html:hidden name="AsistPuestoForm" property="pcCveSuperior" />
				<html:hidden name="AsistPuestoForm" property="pcNombreSuperior" />
				<html:hidden name="AsistPuestoForm" property="pcCveSuperiorReasignar" />
				<html:hidden name="AsistPuestoForm" property="pcNombreSuperiorReasignar" />				
			</TD>
		</TR>
		</logic:equal>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToInicio(AsistPuestoForm)"><bean:message key="asistente.button.regresar"/></html:button>
	 		</TD>
		</TR>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

	<html:hidden name="AsistPuestoForm" property="pcCvePuestoActual"/>
	<html:hidden name="AsistPuestoForm" property="pcDescPuestoActual"/>
	<html:hidden name="AsistPuestoForm" property="pcCveSuperiorActual"/>
	<html:hidden name="AsistPuestoForm" property="pcNombreSuperiorActual"/>
	<html:hidden name="AsistPuestoForm" property="pcCveSuperiorChg"/>
	<html:hidden name="AsistPuestoForm" property="pcCveVendedorChg"/>
	<html:hidden name="AsistPuestoForm" property="pcNombreCompletoChg"/>
	<html:hidden name="AsistPuestoForm" property="page"/>
	<html:hidden name="AsistPuestoForm" property="coordXHolder" />
	<html:hidden name="AsistPuestoForm" property="coordYHolder" />
	<BR><BR>
	</html:form> 
</BODY>
</html:html>
