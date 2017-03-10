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

	public String[] getValores(HttpServletRequest req, String valor){
		String[] res = req.getParameterValues(valor);
		if(res != null && (res.length == 0)){
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
	
	public Object[] getParametersDiv(HttpServletRequest req, String parametro, String division){
		ArrayList param = new ArrayList();
		param.add(parametro);
		param.add(division);
		return param.toArray();	
	}

	public Object[] getParameters(HttpServletRequest req, String parametro){
		ArrayList param = new ArrayList();
		param.add(parametro);
		return param.toArray();	
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
<TITLE>Iusacell :: Administrador Catalogo Vendedores </TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
   		<script language="JavaScript" src="../js/showdate.js"></script>		
   		<script language="JavaScript" src="../js/Calendario.js"></script>   		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script>

		function updateBox(form,item){
			changeCombo(form.pcCvePuesto,item.value,'puesto');
			changeCombo(form.pcCveSuperior,item.value,'superior');
			changeInput(form.pcNomVendedor,item.value,'nomVend');
			changeInput(form.pcApePaterno,item.value,'apePat');
			changeInput(form.pcApeMaterno,item.value,'apeMat');
			changeInput(form.pcCiudad,item.value,'ciudad');
		}

		function showEliminar(){
			if(document.forms[0].CveRol.value != "4") {
				if( window.mmIsOpera ) return(document.getElementById("eliminar").style.visibility = 'visible');
				if (document.all) return(document.all.eliminar.style.visibility = 'visible');
				if (document.getElementById) return(document.getElementById("eliminar").style.visibility = 'visible');
			}
		}

		function goToTipoSelect(form,item){
			changeCombo(form.pcCveTipoCanal,item.value,'cveTipo');
		}
		
		function agregarListaVendedor(form) {
			var undefined;
			comboBox= form.pcCveVendedor;
			if(form.pcCveVendedorRadio.length != undefined){
				for(i=0;i<form.pcCveVendedorRadio.length;i++){
					if(form.pcCveVendedorRadio[i].checked == true) optionValue = form.pcCveVendedorRadio[i].value;
				}
			}else{
				optionValue = form.pcCveVendedorRadio.value;
			}
			hiddenId  = 'nomVend' + optionValue;
			hiddenItem = FIND(hiddenId);
			optionName = hiddenItem.value; 
			addToCombo(comboBox,optionValue,optionName)
		}
		
		function agregarListaPuntosVenta(form) {
			var undefined;
			comboBox= form.pcCvePtoventas;
			if(form.pcCvePtoventasRadio.length != undefined){
				for(i=0;i<form.pcCvePtoventasRadio.length;i++){
					if(form.pcCvePtoventasRadio[i].checked == true) optionValue = form.pcCvePtoventasRadio[i].value;
				}
			}else{
				optionValue = form.pcCvePtoventasRadio.value;
			}
			hiddenId  = 'nombrePto' + optionValue;
			hiddenItem = FIND(hiddenId);
			optionName = hiddenItem.value; 
			addToCombo(comboBox,optionValue,optionName)
		}
		
		function goToBuscarPtos(form,control){
		  if(form.pcCveDivisionSeek.value==0 && form.pcCveTipoCanalSeek.value==0 && form.pcCveCanalSeek.value==0 && form.pcCvePtoventasSeek.value==0 && form.pcNomPtoventasSeek.value==0 && form.pcNoReferenciaSeek.value==0){
	           alert('Debe llenar al menos un criterio de búsqueda, favor de revisar.');
	           return false;
	          }
	        goToBuscar(form,control);
		}
		
		 function goToBuscarVen(form,control){
		   var nomVen = form.pcNomVendedorSeek.value;
		   var cveVen = form.pcCveVendedorSeek.value;
		   if(form.pcNomVendedorSeek.value==0 && form.pcCveVendedorSeek.value==0 && form.pcCvePuestoSeek.value==0 && form.pcNomClaveEmpleadoReferenciaSeek.value==0){
	           alert('Debe llenar al menos un criterio de búsqueda, favor de revisar.');
	           return false;
	          }
	          if((cveVen.length>0) && isNaN(form.pcCveVendedorSeek.value)){
	           alert('El campo "Vendedor a Buscar" debe de ser numérico, favor de revisar.');
	           return false;
	          }
	          if((nomVen.length>0) && !isNaN(form.pcNomVendedorSeek.value)){
	           alert('El campo "Nombre de Vendedor" no debe contener numeros, favor de revisar.');
	           return false;
	          }
	        goToBuscar(form,control);  
		}

		function validateForm(form){
			return (validateRelacionesForm(form));
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
	<html:form method="post" action="RelacionesAction"> 
	<bean:define id="RelacionesForm" name="RelacionesForm" type="mx.com.iusacell.catalogo.web.relacion.struts.action.RelacionesForm"/>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
	
	<TABLE WIDTH="750" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="relacionesForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR><BR>

<table width="750" cellpadding="0" cellspacing="0" border="0" align="center">
<TR><TD WIDTH="45%" height="40" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7">
<bean:message key="relacionesForm.message.buscar.vendedores"/>
</TD>
  <TD width="25" height="40"><img src="<%= request.getContextPath()%>/images/vacio.gif" width="25" height="5"></TD>
  <TD WIDTH="55%" height="40" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7">
<bean:message key="relacionesForm.message.buscar.puntosVenta"/>
</TD></TR> 
<TR><TD VALIGN="TOP" CLASS="PTabData">
	
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" id=borde>		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" height="40" colspan="2" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7">
			<bean:message key="personalForm.message.buscar1"/>
			</TD>
		</TR>
		<TR bgcolor="#F4F4F4">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message  key="relacionesForm.pcCvePuestoSeek.displayname"/> :</TD>
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
			/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message  key="relacionesForm.pcNomVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedorSeek" name="RelacionesForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" height="40" colspan="2" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7">
			<bean:message key="personalForm.message.buscar2"/>
			</TD>
		</TR>
		<TR bgcolor="#F4F4F4">
			<TD WIDTH="20%"  CLASS="txtContenidoBold"><bean:message  key="relacionesForm.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="30%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="RelacionesForm" maxlength="8" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD CLASS="txtContenidoBold" width="20%"><bean:message  key="relacionesForm.pcNomClaveEmpleadoReferenciaSeek.displayname"/> :</TD>
			<TD ALIGN="LEFT" CLASS="PTabData" width="30">
				<html:text property="pcNomClaveEmpleadoReferenciaSeek" name="RelacionesForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F4F4F4">
				<TD ALIGN="CENTER" CLASS="txtContenido" colspan="2">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarVen(RelacionesForm,'vendedores')"><bean:message key="relacionesForm.button.buscar.vendedor"/></html:button>
		 		</TD>
		</TR>
		</TABLE>
		</TD>
		<TD width="25">&nbsp;</TD>
		<TD WIDTH="55%" ALIGN="LEFT" VALIGN="top" CLASS="PTabData" >
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" id=borde>		
		<TR bgcolor="#F4F4F4">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message  key="relacionesForm.pcCveDivisionSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
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
			<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message  key="relacionesForm.pcCveTipoCanalSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
	
			<fsj:comboGeneral
		    	name = "pcCveTipoCanalSeek"
				queryId="<%=Q.TIPO_CANALES_ALL%>"				
				valueMethodName = "getPcCveTpCanal"
				descrMethodName = "getPcDescTpCanal"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Tipo_CanalVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveTipoCanalSeek")%>' 
				textoNoSeleccion = "-- Seleccione un Tipo de Canal --"
				valorNoSeleccion = ""
				script='onChange=goToDynaBuscar(RelacionesForm,"tipo_canal_seek")'
			/></TD>
		</TR>
		<TR bgcolor="#F4F4F4">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message  key="relacionesForm.pcCveCanalSeek.displayname"/> :</TD>
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
				parametrosQuery='<%= getParameters(request,RelacionesForm.getPcCveTipoCanalSeek())%>'			
			/></TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="20%" CLASS="txtContenidoBold"><bean:message  key="relacionesForm.pcCvePtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="30%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCvePtoventasSeek" name="RelacionesForm" maxlength="9" size="20" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#F4F4F4">
			<TD WIDTH="20%" CLASS="txtContenidoBold">No. Económico de Punto de Ventas:</TD>
			<TD WIDTH="30%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNoReferenciaSeek" name="RelacionesForm" maxlength="9" size="20" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="20%" CLASS="txtContenidoBold"><bean:message  key="relacionesForm.pcNomPtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="30%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventasSeek" name="RelacionesForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F4F4F4">
				<TD ALIGN="CENTER" CLASS="txtContenido" colspan="2">
					<html:button property="boton" styleClass="boton" onclick="goToBuscarPtos(RelacionesForm,'puntosVenta')"><bean:message key="relacionesForm.button.buscar.puntoVenta"/></html:button>
		 		</TD>
		</TR>
		</TABLE>				

	</TD></TR>
	<TR>	
		<TD>&nbsp;</TD>
	    <TD width="25">&nbsp;</TD>
	  	<TD>&nbsp;</TD>
	</TR>
<TR><TD VALIGN="TOP">&nbsp;
<logic:present name="tablaVendedores">
	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" id=borde>
		<TR><TD CLASS="txtContenido" colspan="2">
		<div style="height:250;width:100%;overflow:scroll;visibility:visible">
	
			<fsj:generalCheckTableEstatico
				lista='<%= tablaVendedores %>'	
				valueMethodName = "getPcCveVendedor"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Cve Vendedor,Nombre,Puesto, Division"
				metodos="getPcCveVendedor,getPcNombreCompleto,getPcDescPuesto, getPcSubdivision"
				anchosColumnas="25%,25%,20%,20%"
				alineacionesColumnas="center,center,center, center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnCheck="10%"
				checkName="pcCveVendedor"
				selected='<%=getValores(request,"pcCveVendedor")%>'
				camposExtras='getPcNombreCompleto=nomVend'
				agregarValorCamposExtras='true'
			/>
			</div>
		</TR>		
		<TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER">
				<html:button property="boton" styleClass="boton" onclick="goToSolicitar(RelacionesForm,'vendedores')"><bean:message key="relacionesForm.button.ver.relaciones"/></html:button>				
		 		</TD>
		</TR>
		</TABLE>
</logic:present>
<logic:present name="noDataVendedores">
		<bean:define id="noDataVendedores" name="noDataVendedores" type="java.lang.String"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noDataVendedores"/>
		 	</TD>
		</TR>
		</TABLE>
</logic:present>
</TD>
  <TD WIDTH="25" ALIGN="LEFT" VALIGN="TOP">&nbsp;</TD>
  <TD WIDTH="55%" ALIGN="LEFT" VALIGN="TOP">&nbsp;
<logic:present name="tablaPuntosVenta">
		<bean:define id="tablaPuntosVenta" name="tablaPuntosVenta" type="java.util.ArrayList"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" id=borde>		
		<TR><TD CLASS="txtContenido" colspan="2">
		<div style="height:250;width:100%;overflow:scroll;visibility:visible">

			<fsj:generalCheckTableEstatico
				lista='<%= tablaPuntosVenta %>'	
				valueMethodName = "getPcCvePtoventas"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Clave Pto Venta, Digito, Punto Venta,Num. Económico,Tipo Tienda, Canal, División"
				metodos="getPcCvePtoventas,getPcDigVerif,getPcNomPtoventas,getPcCveReferencia,getPcCntlInt,getPcDescCanal, getPcDescSubdiv"
				anchosColumnas="10%,10%,20%,20%,10%,15%, 15%"
				alineacionesColumnas="center, center,center,center,center, center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnCheck="10%"
				checkName="pcCvePtoventas"
				selected='<%=getValores(request,"pcCvePtoventas")%>'
				camposExtras='getPcNomPtoventas=nombrePto'
				agregarValorCamposExtras='true'
			/>
		</div>
		</TD></TR>
		<TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER">
				<html:button property="boton" styleClass="boton" onclick="goToSolicitar(RelacionesForm,'ptoventas')"><bean:message key="relacionesForm.button.ver.relaciones"/></html:button>				
		 		</TD>
		</TR>
		</TABLE>		
</logic:present>
<logic:present name="noDataPuntosVenta">
		<bean:define id="noDataPuntosVenta" name="noDataPuntosVenta" type="java.lang.String"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="noDataPuntosVenta" />
		</TD></TR>
		</TABLE>		
</logic:present>
	</TD>
	</TR>
	<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
		<input type="hidden" name="CveRol" value="0">
		<TR bgcolor="#F4F4F4">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="3">
				<html:button property="boton" styleClass="boton" onclick="goToAgregar(RelacionesForm)"><bean:message key="button.agregar"/></html:button>
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
	<TABLE WIDTH="750" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="relacionesForm.header.detalle"/></h3></TD>
      </TR>
    </TABLE>
	<BR><BR>

	<TABLE WIDTH="750" BORDER="0" align="center" CELLPADDING="3" CELLSPACING="1" id=borde>		
		<logic:present name="tablaRelaciones">
				<bean:define id="tablaRelaciones" name="tablaRelaciones" type="java.util.ArrayList"/>
				<TR><TD WIDTH="100%"  CLASS="txtContenidoRojo" colspan="2">
					<fsj:generalHomeTableEstatico
						lista='<%= tablaRelaciones %>'	
						radioName="pcCveInter"
						valueMethodName = "getPcCveInter"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Inter_Vendedores_PtoventasVO.class%>"
						border="1"
						width="100%"
						cellpadding="0"
						cellspacing="0"
						cssColorEncabezado="td_mensajes"
						titulos="Clave del Vendedor,Nombre del Vendedor,Clave del PDV,Nombre Punto Venta,Tipo Tienda,Canal,Fecha"
						metodos="getPcCveVendedor,getPcNombreVendedor,getPcCvePtoventas,getPcNomPtoventas,getPcCntlInt,getPcDescCanal,getPcFchMovimientoStr"
						anchosColumnas="14%,14%,14%,14%,14%,14%,14%"
						alineacionesColumnas="center,center,center,center,center,center,center"
						altoRenglon="1"
						cssColorRenglon1="td_gris_tablas"
						cssColorRenglon2="td_blanco_tablas"
						witdhColumnRadio="8%"
						selected='<%=getValor(request,"pcCveInter")%>'
						script="onClick='showEliminar()'"
					/>
					</TD>
				</TR>
				<TR bgcolor="#F4F4F4">
					<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
						<div id="eliminar" style="visibility:hidden">
						<table cellpadding="0" cellspacing="0" border="0" align="center">
						<tr><td CLASS="txtContenidoRojo"><bean:message key="relacionesForm.pcFchBaja.displayname" /> <bean:message key="mascara.fecha"/>: </td>
						    <td CLASS="txtContenido"><html:text property="pcFchBaja" name="RelacionesForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
						        <a href="javascript:show_calendar('RelacionesForm.pcFchBaja','','','DD/MM/YYYY','es')">
		  				        <img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a></td>
						    <td CLASS="txtContenido"><html:button property="boton" styleClass="boton" onclick="goToEliminar(RelacionesForm)"><bean:message key="button.eliminar"/></html:button></td>
						</tr></table>
						</div>
					</TD>
				</TR>
		</logic:present>
		<logic:present name="noData">
				<bean:define id="noData" name="noData" type="java.lang.String"/>
				<TR><TD CLASS="errormessagenegro" colspan="2">
				 		<bean:write name="noData" />
				</TD></TR>
		</logic:present>
		<logic:present name="noDataRelaciones">
				<bean:define id="noDataRelaciones" name="noDataRelaciones" type="java.lang.String"/>
				<TR><TD CLASS="errormessagenegro" colspan="2">
				 		<bean:write name="noDataRelaciones" />
				</TD></TR>
		</logic:present>
	</TABLE>

<html:hidden name="RelacionesForm" property="ultimaBusqueda" />
	<BR><BR>
	</html:form> 
</BODY>
</html:html>
