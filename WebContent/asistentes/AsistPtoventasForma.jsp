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
<title><bean:message key="vendedoresForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script language="JavaScript" src="../js/SmartScroll.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script type="text/javascript" src="../js/tabs.js"></script>
		
		<script language="Javascript1.2">

		function updatePcNomPtoventas(form,item){
			changeInput(form.pcNomPtoventas,item.value,'nombrePto');
			changeInput(form.pcCveDiv,item.value,'division');
		}

		function updatePcNomVendedor(form,item){
			changeInput(form.pcNombreCompleto,item.value,'nomVend');
		}
		
		function goToSiguienteUno(form) {
		    var rad = document.getElementById('pcCvePtoventas');
			if(rad!=null){
				if(idCheck(form.pcCvePtoventas)){
				var action = "";
				action ="?action=consultar&control=siguiente";
				form.action = form.action + action;
				form.submit();
				}else{
						alert("Debe seleccionar un Punto de Venta.");
			       }
		 	   }
		 }
		 
		 function goToSiguienteTres(form){
			var rad = document.getElementById('pcCvePtoventas');
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
		
		function goToSiguienteDos(form) {
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
		
		function goToBuscarSig(form,control) {
			var action ="";
			    if(form.pcCveDivSeek.value=="" && form.pcCveDivSeek.value=="" &&
			       form.pcCveTipoCanalSeek.value=="" && form.pcCveCanalSeek.value==""
			       && form.pcCvePtoventasSeek.value=="" && form.pcNomPtoventasSeek.value==""){
			    alert('Debe especificar al menos un criterio de búsqueda, favor de revisar.');
			    return false;
			    }
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
				form.action = form.action + action;
				form.submit(); 	
		     }
		     
		function goToBuscarSig2(form,control) {
			var action ="";
			    if(form.pcCvePuestoSeek.value=="" && form.pcCveVendedorSeek.value=="" 
			       && form.pcNomClaveEmpleadoReferenciaSeek.value=="" && form.pcNomVendedorSeek.value==""){
			    alert('Escriba al menos un criterio de búsqueda, favor de revisar.');
			    return false;
			    }
			
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
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
	<html:form method="post" styleId="AsistPtoventasForm" action="AsistPtoventasAction" onsubmit="return validateAsistPtoventasForm(this);"> 
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	
	 <TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="asistPtoventas.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>


<logic:equal name="AsistPtoventasForm" property="page" value="0">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<logic:notEqual name="AsistPtoventasForm" property="pcCveVendedorChg" value="0" >
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomVendedor.displayname"/> :</TD>
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
		     <li> <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/></li>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCveDivSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
		<logic:equal name="divisionUsuario" property="pcCveDiv" value="0">
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
				parametrosQuery='<%= new Object[] {String.valueOf(divisionUsuario.getPcCveSubdiv())}%>'
			 />
		   </logic:equal>
		   <logic:notEqual name="divisionUsuario" property="pcCveDiv" value="0">
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
				parametrosQuery='<%= new Object[] {divisionUsuario.getPcCvesDivs()}%>'
			 />
		   </logic:notEqual>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCveTipoCanalSeek.displayname"/> :</TD>
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
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCveCanalSeek.displayname"/> :</TD>
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
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCvePtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCvePtoventasSeek" name="AsistPtoventasForm" maxlength="8" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomPtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventasSeek" name="AsistPtoventasForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarSig(AsistPtoventasForm,'puntos_venta')"><bean:message key="asistente.button.siguiente"/></html:button>
		 		</TD>
		 </TR>
		</table>
	</TD></TR></TABLE>	
</logic:equal>


<logic:equal name="AsistPtoventasForm" property="page" value="1">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="asistente.message.buscar.ptoventas"/>
			</TD>
		</TR>
		<logic:notEqual name="AsistPtoventasForm" property="pcCveVendedorChg" value="0" >
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomVendedor.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNombreCompletoChg"/>
			</TD>
		</TR>
		</logic:notEqual>		
<logic:messagesPresent>
		<TR><TD CLASS="errormessagerojo" colspan="2">  <h2> <bean:message key="errors.errorgeneral"/> </h2>
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		     <li> <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/></li>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>
<logic:present name="tablaPuntosVenta">
		<bean:define id="tablaPuntosVenta" name="tablaPuntosVenta" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<html:hidden name="AsistPtoventasForm" property="pcNomPtoventas"/>
			<html:hidden name="AsistPtoventasForm" property="pcCveDiv"/>
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
				camposExtras='getPcNomPtoventas=nombrePto,getPcCveDiv=division'
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
				<html:button property="boton" styleClass="boton" onclick="goToSiguienteUno(AsistPtoventasForm)"><bean:message key="asistente.button.siguiente"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
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
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="20%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCvePtoventas.displayname"/> :</TD>
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
		<html:hidden name="AsistPtoventasForm" property="pcCveDiv"/>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#FFFFFF">
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
				<html:text name="AsistPtoventasForm" property="pcNomVendedorSeek" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
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
			<TD COLSPAN="2" ALIGN="CENTER" CLASS="txtContenido"><bean:message key="asistente.message.buscar2.vendedor"/></TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text name="AsistPtoventasForm"  property="pcCveVendedorSeek" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message  key="asistente.pcNomClaveEmpleadoReferenciaSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomClaveEmpleadoReferenciaSeek" name="AsistPtoventasForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		 <TR bgcolor="#F9F9F9">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPtoventasForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarSig2(AsistPtoventasForm,'vendedor')"><bean:message key="asistente.button.siguiente"/></html:button>
		 		</TD>
		 </TR>
		 </TABLE>
	 </TD>
	 </TR>
	 </table>
</logic:equal>

<logic:equal name="AsistPtoventasForm" property="page" value="3">

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomPtoventasSeek.displayname"/> :</TD>
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
		<html:hidden name="AsistPtoventasForm" property="pcCveDiv"/>		<TABLE WIDTH="700" align="center">	<logic:present name="tablaVendedores">
	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
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
				titulos="Clave,Nombre,Puesto,Division,Status(Fecha de Alta)"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto,getPcSubdivision,getPcStatusFecha"
				anchosColumnas="12%,25%,25%,20%,18%"
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
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>		 
		 <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistPtoventasForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguienteDos(AsistPtoventasForm)"><bean:message key="asistente.button.siguiente"/></html:button>
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
		<TABLE cellpadding="0" cellspacing="0" border="0" align="center">
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomPtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNomPtoventas"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcNomVendedor.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNombreCompleto"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcFchMovimiento.displayname" /> <bean:message key="mascara.fecha"/>: 
				<html:text name="AsistPtoventasForm" property="pcFchMovimiento"  maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('AsistPtoventasForm.pcFchMovimiento','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
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
		<TD HEIGHT="20"  CLASS="txtContenidoRojo" ALIGN="CENTER" ><h3><bean:message key="asistPtoventas.header.relaciones"/></h3></TD>
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
		<TABLE cellpadding="0" cellspacing="0" border="0" align="center">
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCvePtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNomPtoventas"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcCveVendedor.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="AsistPtoventasForm" property="pcNombreCompleto"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
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
		<TD HEIGHT="20"  CLASS="txtContenidoRojo" ALIGN="CENTER" ><h3><bean:message key="asistPtoventas.header.relaciones"/></h3></TD>
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
</BODY>
</html:html>
