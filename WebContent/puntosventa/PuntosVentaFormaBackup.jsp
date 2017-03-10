<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO"%>
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
	
	public Object[] getParameters(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		ArrayList param = new ArrayList();
		param.add(res);
		return param.toArray();	
	}

	public Object[] getParametersNull(HttpServletRequest req, String parametro){
		ArrayList param = new ArrayList();
		if(parametro == null || parametro.equalsIgnoreCase("")==true) param.add(null);
		else param.add(parametro);
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
<TITLE><bean:message key="puntosVentaForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script>

		function updateBox(form,item){
			changeInput(form.pcNomPtoventas,item.value,'nombrePto');
			changeInput(form.pcCveReferencia,item.value,'refer');
			changeInput(form.pcCntlInt,item.value,'controlInt');
			changeCombo(form.pcCveRegion,item.value,'region');
			changeCombo(form.pcCveSubdiv,item.value,'div');
			changeCombo(form.pcCveTipoCanal,item.value,'tpCanal');
			changeCombo(form.pcCveCanal,item.value,'canal');
			if( window.mmIsOpera ) return(document.getElementById("eliminar").style.visibility = 'visible');
			if (document.all) return(document.all.eliminar.style.visibility = 'visible');
			if (document.getElementById) return(document.getElementById("eliminar").style.visibility = 'visible');
		}

		function updateDivision(form,item){
			changeInput(form.pcCveDiv,item.value,'div');
		}
		
		function goToTipoSelect(form,item){
			changeCombo(form.pcCveTipoCanal,item.value,'cveTipo');
		}

		function validateForm(form){
			return (validatePuntosVentaForm(form));
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
	<html:form method="post" action="PuntosVentaAction" method="POST"> 
	<bean:define id="PuntosVentaForm" name="PuntosVentaForm" type="mx.com.iusacell.catalogo.web.puntosventa.struts.action.PuntosVentaForm"/>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>

	<TABLE WIDTH="80%" BORDER="0" CELLSPACING="1" CELLPADDING="2" BGCOLOR="#5D5D5E" align="center">
	<TR>
	<TD BGCOLOR="#FFFFFF">
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#CC0000">
		<TR>
		<TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" ><B><bean:message key="puntosVentaForm.header"/></B></TD>
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
			<bean:message key="puntosVentaForm.message.buscar"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveDivisionSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
	
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
				parametrosQuery='<%= getParametros(String.valueOf(divisionUsuario.getPcCveSubdiv()))%>'
			/>
		
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveTipoCanalSeek.displayname"/> :</TD>
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
				script='onChange=goToDynaBuscar(PuntosVentaForm,"tipo_canal_seek")'
			/>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveCanalSeek.displayname"/> :</TD>
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
				parametrosQuery='<%= getParameters(request,"pcCveTipoCanalSeek")%>'			
			/>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcNomPtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventasSeek" name="PuntosVentaForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCvePtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCvePtoventasSeek" name="PuntosVentaForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.buscar"/></html:button>
		 		</TD>
		 </TR>
<logic:present name="tablaPuntosVenta">
		<bean:define id="tablaPuntosVenta" name="tablaPuntosVenta" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoBold" colspan="2">
			<div style="height:200;width:100%;overflow:scroll;visibility:visible">
			<fsj:generalHomeTableEstatico
				lista='<%= tablaPuntosVenta %>'	
				valueMethodName = "getPcCvePtoventas"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Num.Eco,Punto Venta,Canal,Tipo Canal,División"
				metodos="getPcCveReferencia,getPcNomPtoventas,getPcDescCanal,getPcDescTpCanal,getPcDescSubdiv"
				anchosColumnas="18%,18%,18%,18%,20%"
				alineacionesColumnas="center,center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCvePtoventas"
				script="onClick='updateBox(PuntosVentaForm,this)'"
				camposExtras='getPcNomPtoventas=nombrePto,getPcCveCanal=canal,getPcCveTpCanal=tpCanal,getPcCveRegion=region,getPcCveReferencia=refer,getPcCntlInt=controlInt,getPcCveSubdiv=div'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCvePtoventas")%>' 
			/></div>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<div id="eliminar" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToEliminar(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.eliminar"/></html:button></div>
		 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2">
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
		<TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" ><B><bean:message key="puntosVentaForm.detalle.header"/></B></TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	</TABLE>

<BR><BR>
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
		<bean:message key="puntosVentaForm.message"/>
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
	<TR>
	<TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveDivision.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="PuntosVentaForm" property="pcCveDiv"/>
				<fsj:comboGeneral
			    	name = "pcCveSubdiv"
					queryId="<%=Q.SUBDIVISION_ALL%>"				
					valueMethodName = "getPcCveSubdiv"
					descrMethodName = "getPcDescSubdiv"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveSubdiv")%>' 
					textoNoSeleccion = "-- Seleccione una División --"
					valorNoSeleccion = ""
					camposExtras='getPcCveDiv=div'
					agregarValorCamposExtras='true'
					script='onChange=updateDivision(PuntosVentaForm,"div")'
					parametrosQuery='<%= getParametros(String.valueOf(divisionUsuario.getPcCveSubdiv()))%>'
				/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveTipoCanal.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcCveTipoCanal"
					queryId="<%=Q.TIPO_CANALES_ALL%>"				
					valueMethodName = "getPcCveTpCanal"
					descrMethodName = "getPcDescTpCanal"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Tipo_CanalVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveTipoCanal")%>' 
					textoNoSeleccion = "-- Seleccione un Tipo de Canal --"
					valorNoSeleccion = ""
					script='onChange=goToDynaBuscar(PuntosVentaForm,"tipo_canal")'
				/>
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveCanal.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcCveCanal"
					valueMethodName = "getPcCveCanal"
					descrMethodName = "getPcDescCanal"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveCanal")%>' 
					textoNoSeleccion = "-- Seleccione un Canal --"
					valorNoSeleccion = ""
					queryId="<%=Q.CANALES_ALL%>"	
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_CanalVO.class%>"
					camposExtras='getPcDescCanal=descCanal,getPcCveTpCanal=cveTipo'
					agregarValorCamposExtras='true'
					parametrosQuery='<%= getParametersNull(request,PuntosVentaForm.getPcCveTipoCanal())%>'			
					script='onChange=goToTipoSelect(PuntosVentaForm,this)'
				/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcNomPtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventas" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
				<logic:present name="detallePuntoVenta">
					<bean:define id="detallePuntoVenta" name="detallePuntoVenta" type="mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO"/>
					<html:hidden property="pcCvePtoventas" name="PuntosVentaForm" value="<%= String.valueOf(detallePuntoVenta.getPcCvePtoventas()) %>"/>
				</logic:present>				
			</TD>
		</TR>
		<TR BGCOLOR="#E0E1E3">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCntlInt.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCntlInt" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveReferencia.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveReferencia" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>

		<TR bgcolor="#E0E1E3">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToAgregar(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.agregar"/></html:button>
			</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToModificar(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.modificar"/></html:button>
			</TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	</table>
	<BR><BR>
	<html:hidden property="pcCveRegion" name="PuntosVentaForm" />
	</html:form> 
<!--html:javascript formName="PuntosVentaForm" dynamicJavascript="true" staticJavascript="true"/--> 
</BODY>
</html:html>
