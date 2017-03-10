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

	public Object[] getParametros(HttpServletRequest req, String parametro){
		ArrayList param = new ArrayList();
		param.add(parametro);
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
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
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

		function goToTipoSelect(form,item){
			changeCombo(form.pcCveTipoCanal,item.value,'cveTipo');
		}

		function validateForm(form){
			return (validateVendedoresForm(form));
		}		
		</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home" />
    <menu:displayMenu name="Vendedores" /> 
    <menu:displayMenu name="PuntosVenta" /> 
    <menu:displayMenu name="Relaciones" /> 
    <menu:displayMenu name="Auxiliares" /> 
    <menu:displayMenu name="Exit" />
</menu:useMenuDisplayer>	

<BR><BR>
	<html:form method="post" action="VendedoresAction" method="POST" onsubmit="return validateVendedoresForm(this);"> 
	<bean:define id="VendedoresForm" name="VendedoresForm" type="mx.com.iusacell.catalogo.web.vendedores.struts.action.VendedoresForm"/>
	
	<TABLE WIDTH="80%" BORDER="0" CELLSPACING="1" CELLPADDING="2" BGCOLOR="#5D5D5E" align="center">
	<TR>
	<TD BGCOLOR="#FFFFFF">
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#CC0000">
		<TR>
		<TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" ><B><bean:message key="vendedoresForm.header"/></B></TD>
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
			<bean:message key="vendedoresForm.message.buscar"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCvePuestoSeek.displayname"/> :</TD>
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
		
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="VendedoresForm" maxlength="40" size="30" value=""/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNomVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedorSeek" name="VendedoresForm" maxlength="40" size="30" value=""/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToConsultar(VendedoresForm)"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
<logic:messagesPresent>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
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
	
			<fsj:generalHomeTableEstatico
				lista='<%= tablaVendedores %>'	
				valueMethodName = "getPcCveVendedor"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Nombre,Puesto,Fecha de Alta,Ciudad"
				metodos="getPcNombreCompleto,getPcDescPuesto,getPcFechaAltaStr,getPcCiudad"
				anchosColumnas="23%,23%,23%,23%"
				alineacionesColumnas="center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCveVendedor"
				script="onClick='updateBox(VendedoresForm,this)'"
				camposExtras='getPcNomVendedor=nomVend,getPcApePaterno=apePat,getPcApeMaterno=apeMat,getPcCvePuesto=puesto,getPcCveSuperior=superior,getPcFechaAltaStr=fchalta,getPcFechaBajaStr=fchbaja,getPcDigVerif=digito,getPcCntrTda=controlTda,getPcStatus=status,getPcCveEmpRef=empRef,getPcCiudad=ciudad'
				agregarValorCamposExtras='true'
			/>
		</TD></TR>
		<TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToConsultar(VendedoresForm)"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>		
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>
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
		<TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" ><B><bean:message key="vendedoresForm.detalle.header"/></B></TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	</TABLE>

<BR><BR>

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
		<bean:message key="vendedoresForm.message"/>
		</TD>
	</TR>
	<TR>
	<TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcNomVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedor" name="VendedoresForm" maxlength="40" size="30" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcApePaterno.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcApePaterno" name="VendedoresForm" maxlength="40" size="30" />
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcApeMaterno.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcApeMaterno" name="VendedoresForm" maxlength="40" size="30" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCiudad.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCiudad" name="VendedoresForm" maxlength="40" size="30" />
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCvePuesto.displayname"/> : </TD>
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
					/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcCveSuperior.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<logic:present name="USER">
				<bean:define id="userSession" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
					<fsj:comboGeneral
				    	name = "pcCveSuperior"
						queryId="<%=Q.VENDEDOR_ALL%>"				
						valueMethodName = "getPcCveVendedor"
						descrMethodName = "getPcNombreVendedor"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCveSuperior")%>' 
						textoNoSeleccion = "-- Seleccione un Nombre --"
						valorNoSeleccion = ""
						parametrosQuery='<%= new Object[] {String.valueOf(userSession.getPcCveDiv())}%>'			
					/>
			</logic:present>
			</TD>
		</TR>
<logic:present name="detalleVendedor">
	<bean:define id="detalleVendedor" name="detalleVendedor" type="mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO"/>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcFchAlta.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcFchAlta" name="detalleVendedor"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="vendedoresForm.pcFchBaja.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcFchBaja" name="detalleVendedor"/>
			</TD>
		</TR>
</logic:present>
		<TR bgcolor="#E0E1E3">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToAgregar(VendedoresForm)"><bean:message key="button.agregar"/></html:button>
			</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToModificar(VendedoresForm)"><bean:message key="button.modificar"/></html:button>
			</TD>
		</TR>
		</TABLE>
				<html:hidden property="pcDigVerif" name="VendedoresForm"/>
				<html:hidden property="pcCntrTda" name="VendedoresForm"/>
				<html:hidden property="pcCveEmpRef" name="VendedoresForm"/>
				<html:hidden property="pcStatus" name="VendedoresForm"/>
	</TD>
	</TR>
	</table>


	<BR><BR>
	</html:form> 
<html:javascript formName="VendedoresForm" dynamicJavascript="true" staticJavascript="true"/>
</BODY>
</html:html>
