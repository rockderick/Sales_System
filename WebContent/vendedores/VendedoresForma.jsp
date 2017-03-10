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
	
		<script language="JavaScript">
		
		function controlVisible(){
			comboBox = document.VendedoresForm.pcTipoRegistro;
			if(comboBox!= null && comboBox != undefined && comboBox.type != 'hidden'){
				if ( comboBox.options[comboBox.selectedIndex].value == 'distribuidor' || comboBox.options[comboBox.selectedIndex].value == 'interno'){
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
		}

		</script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script language="JavaScript" src="../js/SmartScroll.js"></script>
	    <script type="text/javascript" src="../js/tabsVendedores.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script language="Javascript1.2">

		function updatePcNomPtoventas(form,item){
			changeInput(form.pcNomPtoventas,item.value,'nombrePto');
			changeInput(form.pcCveSubdiv,item.value,'subdiv');
			changeInput(form.pcCveDiv,item.value,'division');
		}
		
		function goToSiguiente(form) {
			var action = "";
				action ="?action=consultar&control=siguiente";
				form.action = form.action + action;
				form.submit();
		}
		
		function goToSiguienteVal(form) {
		
		MM_validateForm		('pcNomVendedor','RisText', 'Nombre vendedor:',
							'pcApePaterno','RisText', 'Apellido Paterno:',
							'pcApeMaterno','RisText', 'Apellido Materno:',
							'pcCiudad','RisText', 'Ciudad:',
							'pcCveEmpRef', 'RisOnlyNumbers', 'Numero Empleado:',
							'pcCveEmpresa','RisCombo', 'Empresa',	
							'pcFchAlta', 'RisDate', 'Fecha de Ingreso:');
							
			if (document.MM_returnValue){				
			var action = "";
				action ="?action=consultar&control=siguiente";
				form.action = form.action + action;
				form.submit();
		}
	}	
		function goToSiguienteTipoPersona(form) {
			var action = "";
			<logic:equal name="horario" value="true">
				MM_validateForm('pcCveHorario','RisCombo','Horario :');
					if (document.MM_returnValue){
						action ="?action=consultar&control=siguiente";
						form.action = form.action + action;
						form.submit();
					}
			</logic:equal>
			<logic:equal name="horario" value="false">
						action ="?action=consultar&control=siguiente";
						form.action = form.action + action;
						form.submit();
			</logic:equal>
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
		
		function goToHorario(form) {
				var horario = form.pcCveHorario.value;
				var action = "";
				MM_validateForm('pcCveHorario','RisCombo','Horario :');
				if (document.MM_returnValue){
					window.open("VendedoresAction.do?action=horario&pcCveHorario="+horario+"&buscar1=true",
						"BuscaSocio","width=700,height=400,status=0, menubar=0,toolbar=0, " +
						"resizable=1, top=100, left=100, scrollbars=1");
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
 <logic:present name="Admin">
    <menu:displayMenu name="Configuracion" />
 </logic:present>    
</menu:useMenuDisplayer>	

<BR><BR>
	<html:form method="post" styleId="VendedoresForm" action="VendedoresAction" method="POST" onsubmit="return validateVendedoresForm(this);"> 
	<bean:define id="VendedoresForm" name="VendedoresForm" type="mx.com.iusacell.catalogo.web.vendedores.struts.action.VendedoresForm"/>
	<bean:define id="userSession" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>


<logic:equal name="VendedoresForm" property="page" value="0">
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"  ALIGN="CENTER" ><h3><bean:message key="vendedoresForm.punto_ventas.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

		<TABLE WIDTH="700" align="center">	
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" height="40" colspan="2" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7">
			<bean:message key="vendedoresForm.message.buscar.puntoVentas"/>
			</TD>
		</TR>
<logic:messagesPresent>
		<TR><TD height="40" colspan="2" CLASS="errormessagerojo">	      <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		</TD>
		</TR>
</logic:messagesPresent>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveDivisionSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<logic:present name="USER">
			<fsj:comboGeneral
		    	name = "pcCveDivSeek"
				queryId="<%=Q.SUBDIVISION_ALL%>"				
				valueMethodName = "getPcCveSubdiv"
				descrMethodName = "getPcDescSubdiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveDivSeek")%>' 
				textoNoSeleccion = "-- Seleccione una División --"
				valorNoSeleccion = ""
				parametrosQuery='<%= new Object[] {String.valueOf(userSession.getPcCveSubdiv())}%>'
			/>
		</logic:present>
		</TD></TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveTipoCanalSeek.displayname"/> :</TD>
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
				script='onChange=goToDynaBuscar(VendedoresForm,"tipo_canal_seek")'
			/>
		</TD></TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveCanalSeek.displayname"/> :</TD>
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
		</TD></TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCvePtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCvePtoventasSeek" name="VendedoresForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNomPtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventasSeek" name="VendedoresForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(VendedoresForm,'puntos_venta')"><bean:message key="vendedoresForm.button.siguiente"/></html:button>
		 		</TD>
				<td></td>
		 </TR>
		 </TABLE>	
	<br>
</logic:equal>


<logic:equal name="VendedoresForm" property="page" value="1">	
<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
  <TR>
    <TD HEIGHT="20"><h3><bean:message key="vendedoresForm.punto_ventas.header"/></h3></TD>
  </TR>
</TABLE>
<BR>
	<table width="700" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="100%" align="center">	
	  <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" height="40" colspan="2" CLASS="txtContenido"><span class="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"></span>
			<bean:message key="vendedoresForm.message.buscar.puntoVentas"/>
			</TD>
		</TR>
<logic:messagesPresent>
		<TR><TD height="30" colspan="2" CLASS="errormessagerojo">		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		</TD></TR>
</logic:messagesPresent>
<logic:present name="tablaPuntosVenta">
		<bean:define id="tablaPuntosVenta" name="tablaPuntosVenta" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<html:hidden name="VendedoresForm" property="pcNomPtoventas"/>
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
				titulos="Clave Punto Venta,Num Econ,Punto Venta,Canal,Tipo Canal,División"
				metodos="getPcCvePtoventas,getPcCveReferencia,getPcNomPtoventas,getPcDescCanal,getPcDescTpCanal,getPcDescSubdiv"
				anchosColumnas="15%,10%,20%,20%,20%,10%"
				alineacionesColumnas="center,center,center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="5%"
				radioName="pcCvePtoventas"
				camposExtras='getPcNomPtoventas=nombrePto,getPcCveSubdiv=subdiv,getPcCveDiv=division'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCvePtoventas")%>' 
				script="onClick='updatePcNomPtoventas(VendedoresForm,this)'"
			/></div>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(VendedoresForm)"><bean:message key="vendedoresForm.button.anterior"/></html:button>
		 		</TD>
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguiente(VendedoresForm)"><bean:message key="vendedoresForm.button.siguiente"/></html:button>
		 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD height="30" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" /><html:hidden name="VendedoresForm" property="pcCvePtoventas" value="0"/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(VendedoresForm)"><bean:message key="vendedoresForm.button.anterior"/></html:button>
		 		</TD>
				<td height="40"></td>
		 </TR>
</logic:present>
		</table>
	</TD></TR></TABLE>	
</logic:equal>


<logic:equal name="VendedoresForm" property="page"  value="2">
<BR>
<BR>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="vendedoresForm.puesto.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<table width="700" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" height="40" bgcolor="#FFFFFF" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> 
		<bean:message key="vendedoresForm.message"/>
		</TD>
	</TR>
	<TR>
	<TD>		<TABLE WIDTH="100%" align="center">	
	<logic:messagesPresent>
		<TR><TD height="40" colspan="2" CLASS="errormessagerojo"> 
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		     <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		</TD></TR>
	</logic:messagesPresent>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCvePtoventas.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcNomPtoventas" />
				<html:hidden name="VendedoresForm" property="pcNomPtoventas" />
				<html:hidden name="VendedoresForm" property="pcCvePtoventas" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcTipoRegistro.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:select property="pcTipoRegistro" name="VendedoresForm" onchange="updateTextField(this)">
				<html:option key="vendedoresForm.pcTipoRegistro.radio.persona.displayname" value="persona"/>
				<html:option key="vendedoresForm.pcTipoRegistro.radio.tienda.displayname" value="tienda"/>
				<html:option key="vendedoresForm.pcTipoRegistro.radio.ditribuidor.displayname" value="distribuidor"/>
				<html:option key="vendedoresForm.pcTipoRegistro.radio.interno.displayname" value="interno"/>
				</html:select>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<span ID="CveVendedor" style="visibility:visible"><bean:message key="vendedoresForm.pcCveDefinidaSistema.displayname"/></span><span ID="cveDefinir" style="visibility:hidden"><html:text name="VendedoresForm" property="pcCveVendedorDefine" size="10" maxlength="10"/></span>
				<html:hidden property="pcCveVendedorChg" name="VendedoresForm"/>
			</TD>
		</TR>
		<!-- EMPIEZA JEFE DE TIENDA PRESENTE -->
		<logic:present name="EXISTE_JEFE_TIENDA">
			<TR bgcolor="#F9F9F9">
				<TD WIDTH="10%"CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCvePuesto.displayname"/> : </TD>
				<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<fsj:comboGeneral
				    	name = "pcCvePuesto"
						queryId="<%=Q.PUESTOS_JEFE_TIENDA%>"				
						valueMethodName = "getPcCvePuesto"
						descrMethodName = "getPcDescPuesto"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCvePuesto")%>' 
						textoNoSeleccion = "-- Seleccione un Puesto --"
						valorNoSeleccion = ""
						
					/> 
				</TD>
			</TR>
			<TR bgcolor="#F9F9F9">
				<TD WIDTH="10%"CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveSuperior.displayname"/> : </TD>
				<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<bean:write name="VendedoresForm" property="pcNombreSuperior" />
					<html:hidden name="VendedoresForm" property="pcNombreSuperior" />
					<html:hidden name="VendedoresForm" property="pcCveSuperior" />
				</TD>
			</TR>
		</logic:present>
		<!-- TERMINA JEFE DE TIENDA NO PRESENTE -->

		<!-- EMPIEZA JEFE DE TIENDA NO PRESENTE -->		
		<logic:notPresent name="EXISTE_JEFE_TIENDA">
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCvePuesto.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<fsj:comboGeneral
				    	name = "pcCvePuesto"
						queryId="<%=Q.PUESTOS_ALL%>"				
						valueMethodName = "getPcCvePuesto"
						descrMethodName = "getPcDescPuesto"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCvePuesto")%>' 
						textoNoSeleccion = "-- Seleccione un Puesto --"
						valorNoSeleccion = ""
						script='onChange=goToDynaBuscar(VendedoresForm,"superior")'
					/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveSuperior.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<fsj:comboGeneral
				    	name = "pcCveSuperior"
						queryId="<%=Q.VENDEDOR_SUPERIOR_X_ARBOL_DIV2 %>"				
						valueMethodName = "getPcCveVendedor"
						descrMethodName = "getPcNombreVendedor"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCveSuperior")%>' 
						textoNoSeleccion = "-- Seleccione un Nombre --"
						valorNoSeleccion = ""
						parametrosQuery='<%= getParametrosNull(request,new String[]{"pcCvePuesto","pcCveSubdiv", "pcCveDiv"})%>'
					/>
			</TD>
		</TR>
		
		
		<logic:equal name="horario" value="true">

			<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"CLASS="txtContenidoBold">Horario : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<fsj:comboGeneral
				    	name = "pcCveHorario"
						queryId="<%=Q.OBTENER_HORARIOS %>"				
						valueMethodName = "getPcCveHorario"
						descrMethodName = "getPcDescHorario"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCveHorario")%>' 
						textoNoSeleccion = "-- Seleccione un Horario --"
						valorNoSeleccion = ""
						parametrosQuery='<%=new Object[]{null}%>'
					/>
			</TD>
			
				<TD WIDTH="10%" ALIGN="left" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToHorario(VendedoresForm)">Ver Detalle Horario</html:button>
		 		</TD>
			
			</TR>
		</logic:equal>	
		
		
		<logic:equal name="horario" value="false">

			<html:hidden property="pcCveHorario" value="0"/>	
		</logic:equal>		
		
		
		</logic:notPresent>
		<!-- TERMINA JEFE DE TIENDA NO PRESENTE -->
				<TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(VendedoresForm)"><bean:message key="vendedoresForm.button.anterior"/></html:button>
		 		</TD>
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguienteTipoPersona(VendedoresForm)"><bean:message key="vendedoresForm.button.siguiente"/></html:button>
		 		</TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	</table>
    <br>
    <br>
</logic:equal>

<logic:equal name="VendedoresForm" property="page"  value="3">	
<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="vendedoresForm.detalle.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<table width="700" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" height="40" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> 
		<bean:message key="vendedoresForm.message"/>
		</TD>
	</TR>
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<logic:messagesPresent>
		<TR><TD height="40" colspan="2" CLASS="errormessagerojo"> 
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		     <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		</TD></TR>
</logic:messagesPresent>		
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNomPtoventas.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcNomPtoventas" />
				<html:hidden name="VendedoresForm" property="pcNomPtoventas" />
				<html:hidden name="VendedoresForm" property="pcCvePtoventas" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcTipoRegistro.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcTipoRegistro" />
				<html:hidden name="VendedoresForm" property="pcTipoRegistro" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<logic:equal name="VendedoresForm" property="pcCveVendedor" value="0">
				<bean:message key="vendedoresForm.pcCveDefinidaSistema.displayname"/>
				</logic:equal>
				<logic:notEqual name="VendedoresForm" property="pcCveVendedor" value="0">
				<bean:write name="VendedoresForm" property="pcCveVendedor" />
				</logic:notEqual>
				<html:hidden name="VendedoresForm" property="pcCveVendedor" />
				<html:hidden name="VendedoresForm" property="pcCveVendedorDefine" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcDescPuesto.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcDescPuesto" />
				<html:hidden name="VendedoresForm" property="pcDescPuesto" />
				<html:hidden name="VendedoresForm" property="pcCvePuesto" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNombreSuperior.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcNombreSuperior" />
				<html:hidden name="VendedoresForm" property="pcNombreSuperior" />
				<html:hidden name="VendedoresForm" property="pcCveSuperior" />
			</TD>
		</TR>
		
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold">Horario : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcDescHorario" />
				<html:hidden name="VendedoresForm" property="pcDescHorario" />
				<html:hidden name="VendedoresForm" property="pcCveHorario" />
			</TD>
		</TR>
			<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNomVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedor" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcApePaterno.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcApePaterno" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcApeMaterno.displayname" /> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcApeMaterno" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCiudad.displayname" /> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCiudad" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcEstado.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcEstado" name="VendedoresForm" maxlength="20" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcDireccion.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcDireccion" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcColonia.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcColonia" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCp.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCp" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcTel.displayname"/> <bean:message key="mascara.telefono"/>: </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcTel" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcFax.displayname"/> <bean:message key="mascara.telefono"/>: </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcFax" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveEmpRef.displayname"/> : </TD>
			<TD WIDTH="40%" height="22" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveEmpRef" name="VendedoresForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="empresaForm.empresa.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
				    	name = "pcCveEmpresa"
						queryId="<%=Q.EMPRESA_ALL%>"				
						valueMethodName = "getPcCveEmpresa"
						descrMethodName = "getPcDescEmpresa"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_EmpresaVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCveEmpresa")%>' 
						textoNoSeleccion = "-- Seleccione una Empresa --"
						valorNoSeleccion = ""
						parametrosQuery='<%= new Object[]{null} %>'			
					/>
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="22"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcFchAlta.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="40%" height="22" ALIGN="center" CLASS="PTabData">
				<html:text property="pcFchAlta" name="VendedoresForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('VendedoresForm.pcFchAlta','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(VendedoresForm)"><bean:message key="vendedoresForm.button.anterior"/></html:button>
		 		</TD>
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguienteVal(VendedoresForm)"><bean:message key="vendedoresForm.button.siguiente"/></html:button>
		 		</TD>
		</TR>
		</TABLE>
				<html:hidden property="pcStatus" name="VendedoresForm"/>
				<html:hidden property="pcDigVerif" name="VendedoresForm"/>
				<html:hidden property="pcCntrTda" name="VendedoresForm"/>
	</TD>
	</TR>
	</table>

    <br>
</logic:equal>


<logic:equal name="VendedoresForm" property="page"  value="4">	
<br>
<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="vendedoresForm.detalle.header"/></h3></TD>
      </TR>
</TABLE>
	<BR>

	<table width="700" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" height="40" bgcolor="#FFFFFF" CLASS="txtContenidoRojo">
		<img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:message key="vendedoresForm.message"/>
		</TD>
	</TR>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR>
		  <TD height="1" colspan="2" bgcolor="#CCCCCC"></TD>
  </TR>
		<TR><TD height="22" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" /><html:hidden name="VendedoresForm" property="pcCvePtoventas" value="0"/>
		 	</TD>
		</TR>
</logic:present>
<logic:present name="addedData">
		<bean:define id="addedData" name="addedData" type="java.lang.String"/>
		<TR><TD height="22" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6"> 
		 		<bean:write name="addedData" /><html:hidden name="VendedoresForm" property="pcCvePtoventas" value="0"/>
		 	</TD>
		</TR>
</logic:present>
<logic:present name="tablaVendedores">
	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
		<TR><TD height="22" colspan="2" CLASS="errormessagerojo"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6">	      <bean:message key="vendedoresForm.errors.repetido"/>
		</TD></TR>
		<TR><TD colspan="2">
			<div style="height:200;width:100%;overflow:scroll;visibility:visible">	
			<fsj:generalInfoTableEstatico
				lista='<%= tablaVendedores %>'	
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
			/></div>
		</TD></TR>
</logic:present>
	<TR>
	<TD>		<TABLE WIDTH="100%" align="center">	<logic:messagesPresent>
		<TR><TD height="30" colspan="2" CLASS="errormessagerojo"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6">
		   <bean:message key="errors.header"/>
		   <html:messages id="error">	          <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   
		</TD></TR>
</logic:messagesPresent>				
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNomPtoventas.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcNomPtoventas" />
				<html:hidden name="VendedoresForm" property="pcCvePtoventas" />
				<html:hidden name="VendedoresForm" property="pcNomPtoventas" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcTipoRegistro.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcTipoRegistro" />
				<html:hidden name="VendedoresForm" property="pcTipoRegistro" />
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<logic:equal name="VendedoresForm" property="pcCveVendedor" value="0">
				<bean:message key="vendedoresForm.pcCveDefinidaSistema.displayname"/>
				</logic:equal>
				<logic:notEqual name="VendedoresForm" property="pcCveVendedor" value="0">
				<bean:write name="VendedoresForm" property="pcCveVendedor" />
				</logic:notEqual>				<html:hidden name="VendedoresForm" property="pcCveVendedor" />
				<html:hidden name="VendedoresForm" property="pcCveVendedorDefine" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcDescPuesto.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcDescPuesto" />
				<html:hidden name="VendedoresForm" property="pcCvePuesto" />
				<html:hidden name="VendedoresForm" property="pcDescPuesto" />
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNombreSuperior.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcNombreSuperior" />
				<html:hidden name="VendedoresForm" property="pcCveSuperior" />
				<html:hidden name="VendedoresForm" property="pcNombreSuperior" />
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold">Horario : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write name="VendedoresForm" property="pcDescHorario" />
				<html:hidden name="VendedoresForm" property="pcDescHorario" />
				<html:hidden name="VendedoresForm" property="pcCveHorario" />
			</TD>
		</TR>
		
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNomVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcNomVendedor" name="VendedoresForm" />
				<html:hidden property="pcNomVendedor" name="VendedoresForm" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcApePaterno.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write  property="pcApePaterno" name="VendedoresForm" />
				<html:hidden  property="pcApePaterno" name="VendedoresForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcApeMaterno.displayname" /> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcApeMaterno" name="VendedoresForm"  />
				<html:hidden property="pcApeMaterno" name="VendedoresForm"  />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCiudad.displayname" /> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcCiudad" name="VendedoresForm" />
				<html:hidden property="pcCiudad" name="VendedoresForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcEstado.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcEstado" name="VendedoresForm" />
				<html:hidden property="pcEstado" name="VendedoresForm" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcDireccion.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write  property="pcDireccion" name="VendedoresForm" />
				<html:hidden  property="pcDireccion" name="VendedoresForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcColonia.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcColonia" name="VendedoresForm" />
				<html:hidden property="pcColonia" name="VendedoresForm" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCp.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcCp" name="VendedoresForm" />
				<html:hidden property="pcCp" name="VendedoresForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcTel.displayname"/> <bean:message key="mascara.telefono"/>: </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcTel" name="VendedoresForm" />
				<html:hidden property="pcTel" name="VendedoresForm" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcFax.displayname"/> <bean:message key="mascara.telefono"/>: </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcFax" name="VendedoresForm" />
				<html:hidden property="pcFax" name="VendedoresForm" />
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveEmpRef.displayname"/> : </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcCveEmpRef" name="VendedoresForm" />
				<html:hidden property="pcCveEmpRef" name="VendedoresForm" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcFchAlta.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcFchAlta" name="VendedoresForm" />
				<html:hidden property="pcFchAlta" name="VendedoresForm" />
			</TD>
		</TR>
		<TR>
			<TD WIDTH="10%" height="20"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.empresa.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="40%" height="20" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcCveEmpresa" name="VendedoresForm" />
				<html:hidden property="pcCveEmpresa" name="VendedoresForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(VendedoresForm)"><bean:message key="vendedoresForm.button.cancelar"/></html:button>
			</TD>
			<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			<logic:notPresent name="addedData">
				<html:button property="boton" styleClass="boton" onclick="goToAgregar(VendedoresForm)"><bean:message key="vendedoresForm.button.finalizar"/></html:button>
			</logic:notPresent>
			</TD>
		</TR>
		</TABLE>
				<html:hidden property="pcStatus" name="VendedoresForm"/>
				<html:hidden property="pcDigVerif" name="VendedoresForm"/>
				<html:hidden property="pcCntrTda" name="VendedoresForm"/>
	</TD>
	</TR>
	</table>

</logic:equal>
	
	<html:hidden name="VendedoresForm" property="pcCveSubdiv"/>
	<html:hidden name="VendedoresForm" property="pcCveDiv" />
	<html:hidden name="VendedoresForm" property="page"/>
	<html:hidden property="coordXHolder" name="VendedoresForm" />
	<html:hidden property="coordYHolder" name="VendedoresForm" />
	<BR>
	<BR>
	</html:form> 
</BODY>
</html:html>
