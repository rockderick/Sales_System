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
<title><bean:message key="puntosVentaForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
    
		<script language="JavaScript" src="../js/tools.js"></script>

		<script>

		function updateBox(form,item){
			changeInput(form.pcNomPtoventas,item.value,'nombrePto');
			changeInput(form.pcCveReferencia,item.value,'refer');
			changeInput(form.pcCntlInt,item.value,'controlInt');
			changeInput(form.pcDireccion,item.value,'dire');
			changeInput(form.pcColonia,item.value,'col');
			changeInput(form.pcCp,item.value,'cp');
			changeInput(form.pcTel,item.value,'tel');
			changeInput(form.pcFax,item.value,'fax');
			changeCombo(form.pcCveRegion,item.value,'region');
			changeCombo(form.pcCveSubdiv,item.value,'div');
			changeCombo(form.pcCveTipoCanal,item.value,'tpCanal');
			changeCombo(form.pcCveCanal,item.value,'canal');
			changeInput(form.pcCiudad,item.value,'ciudad');
			changeInput(form.pcEstado,item.value,'estado');
			changeDivDigito(item.value,'digito');
			changeClave(item.value,'clave');
			changeStatus(item.value,'status');
			
			if( window.mmIsOpera )  
				document.getElementById("eliminar").style.visibility = 'visible';
				
			
			if (document.all) 
				document.all.eliminar.style.visibility = 'visible';
			
			if (document.getElementById) 
				document.getElementById("eliminar").style.visibility = 'visible';
			
			if(document.forms[0].pcTipoRegistro.value == "MAN") {
				
				document.forms[0].pcTipoRegistro.value = "AUT";
				
				if( window.mmIsOpera ) {
					document.getElementById("DigVerifManual").style.visibility = 'hidden';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("ClaveManual").style.visibility = 'hidden';
					document.getElementById("Clave").style.visibility = 'visible';
					document.getElementById("Status").style.visibility = 'visible';
					return;
				}
				if (document.all) {
					document.all.DigVerifManual.style.visibility = 'hidden';
					document.all.DigVerif.style.visibility = 'visible';
					document.all.ClaveManual.style.visibility = 'hidden';
					document.all.Clave.style.visibility = 'visible';
					document.all.Status.style.visibility = 'visible';
					return;
				}
				if (document.getElementById) {
					document.getElementById("DigVerifManual").style.visibility = 'hidden';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("ClaveManual").style.visibility = 'hidden';
					document.getElementById("Clave").style.visibility = 'visible';
					document.getElementById("Status").style.visibility = 'visible';
					return;
				}
				
			}
		}
		
		function goToTipoSelect(form,item){
			changeCombo(form.pcCveTipoCanal,item.value,'cveTipo');
		}

		function validateForm(form){
			return (validatePuntosVentaForm(form));
		}
		
		function changeClave(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("Clave").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.Clave.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("Clave").innerHTML = hiddenItem.value);
			return false; 
		}
		
		function changeDivDigito(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("DigVerif").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.DigVerif.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("DigVerif").innerHTML = hiddenItem.value);
			return false; 
		}
		
		function changeStatus(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("Status").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.Status.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("Status").innerHTML = hiddenItem.value);
			return false; 
		}
		
		function updateTextField(comboBox){
			if(comboBox.options[comboBox.selectedIndex].value == "AUT"){
				if( window.mmIsOpera ) {
					document.getElementById("DigVerifManual").style.visibility = 'hidden';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("ClaveManual").style.visibility = 'hidden';
					document.getElementById("Clave").style.visibility = 'visible';
					document.getElementById("Status").style.visibility = 'visible';
					return;
				}
				if (document.all) {
					document.all.DigVerifManual.style.visibility = 'hidden';
					document.all.DigVerif.style.visibility = 'visible';
					document.all.ClaveManual.style.visibility = 'hidden';
					document.all.Clave.style.visibility = 'visible';
					document.all.Status.style.visibility = 'visible';
					return;
				}
				if (document.getElementById) {
					document.getElementById("DigVerifManual").style.visibility = 'hidden';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("ClaveManual").style.visibility = 'hidden';
					document.getElementById("Clave").style.visibility = 'visible';
					document.getElementById("Status").style.visibility = 'visible';
					return;
				}
			} else if ( comboBox.options[comboBox.selectedIndex].value == "MAN"){
				var undefined;		
				document.forms[0].pcCvePtoventas.value = "";
				document.forms[0].pcDigVerif.value = "";
				document.forms[0].pcCveSubdiv.value = "";
				document.forms[0].pcCveTipoCanal.value = "";
				document.forms[0].pcCveCanal.value = "";
				document.forms[0].pcNomPtoventas.value = "";
				document.forms[0].pcCntlInt.value = "";
				document.forms[0].pcCveReferencia.value = "";
				document.forms[0].pcDireccion.value = "";
				document.forms[0].pcColonia.value = "";
				document.forms[0].pcCp.value = "";
				document.forms[0].pcTel.value = "";
				document.forms[0].pcFax.value = "";
				document.forms[0].pcCiudad.value = "";
				document.forms[0].pcEstado.value = "";
				
				if( window.mmIsOpera ){
				 	document.getElementById("DigVerifManual").style.visibility = 'visible';
				 	document.getElementById("DigVerif").style.visibility = 'hidden';
				 	document.getElementById("ClaveManual").style.visibility = 'visible';
				 	document.getElementById("Clave").style.visibility = 'hidden';
				 	document.getElementById("Status").style.visibility = 'hidden';
				 	return;
				}
				if (document.all) {
					document.all.DigVerifManual.style.visibility = 'visible';
					document.all.DigVerif.style.visibility = 'hidden';
					document.all.ClaveManual.style.visibility = 'visible';
					document.all.Clave.style.visibility = 'hidden';
					document.all.Status.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("DigVerifManual").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'hidden';
					document.getElementById("ClaveManual").style.visibility = 'visible';
					document.getElementById("Clave").style.visibility = 'hidden';
					document.getElementById("Status").style.visibility = 'hidden';
					return;
				}
			}
		}
		
		function tipoOperacion() {
			
			if(document.forms[0].pcTipoRegistro.value == "MAN") {
				if( window.mmIsOpera ){
				 	document.getElementById("DigVerifManual").style.visibility = 'visible';
				 	document.getElementById("DigVerif").style.visibility = 'hidden';
				 	document.getElementById("ClaveManual").style.visibility = 'visible';
				 	document.getElementById("Clave").style.visibility = 'hidden';
				 	return;
				}
				if (document.all) {
					document.all.DigVerifManual.style.visibility = 'visible';
					document.all.DigVerif.style.visibility = 'hidden';
					document.all.ClaveManual.style.visibility = 'visible';
					document.all.Clave.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("DigVerifManual").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'hidden';
					document.getElementById("ClaveManual").style.visibility = 'visible';
					document.getElementById("Clave").style.visibility = 'hidden';
					return;
				}
			}
		}
			
		</script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" onload="tipoOperacion()">

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="AuxiliaresConsulta" />
    <menu:displayMenu name="SysAdminReportes" /> 
    <menu:displayMenu name="PuntoVentaConsulta" /> 
 </menu:useMenuDisplayer>	

<BR><BR>
	<html:form method="post" action="PuntosVentaAction" method="POST"> 
	<bean:define id="PuntosVentaForm" name="PuntosVentaForm" type="mx.com.iusacell.catalogo.web.puntosventa.struts.action.PuntosVentaForm"/>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="puntosVentaForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<TABLE WIDTH="700" align="center">	
	<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="puntosVentaForm.message.buscar"/>
			</TD>
	  </TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveDivisionSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
	
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
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveTipoCanalSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
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
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveCanalSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
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
			</TD>	
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcNomPtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventasSeek" name="PuntosVentaForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCvePtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCvePtoventasSeek" name="PuntosVentaForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.buscar"/></html:button>
		 		</TD>
				<td></td>
		 </TR>
<logic:present name="tablaPuntosVenta">
		<bean:define id="tablaPuntosVenta" name="tablaPuntosVenta" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
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
				titulos="Cve Punto Venta, Dig. Verif., Num.Eco,Punto Venta,Canal,Tipo Canal,División,Jefe Tienda, Status"
				metodos="getPcCvePtoventas,getPcDigVerif,getPcCveReferencia,getPcNomPtoventas,getPcDescCanal,getPcDescTpCanal,getPcDescSubdiv,getPcJefeTienda, getPcStatus"
				anchosColumnas="12%,12%,13%,13%,13%,13%,13%,13%, 10"
				alineacionesColumnas="center,center,center,center,center,center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCvePtoventas"
				script="onClick='updateBox(PuntosVentaForm,this)'"
				camposExtras='getPcCvePtoventas=clave,getPcCiudad=ciudad,getPcEstado=estado,getPcStatus=status,getPcDigVerif=digito,getPcNomPtoventas=nombrePto,getPcCveCanal=canal,getPcCveTpCanal=tpCanal,getPcCveRegion=region,getPcCveReferencia=refer,getPcCntlInt=controlInt,getPcCveSubdiv=div,getPcDireccion=dire,getPcColonia=col,getPcCp=cp,getPcTel=tel,getPcFax=fax'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCvePtoventas")%>' 
			/></div>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<div id="eliminar" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToEliminar(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.eliminar"/></html:button></div>
 		   </TD>
		   <td></td>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD height="30" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>
		
</TABLE>

<BR><BR>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="puntosVentaForm.detalle.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
		<bean:message key="puntosVentaForm.message"/>
		</TD>
	</TR>
<logic:messagesPresent>
		<TR>
			<TD CLASS="errormessagerojo" colspan="2">
			   <bean:message key="errors.header"/>
			   <H1 CLASS="errormessagerojo">ERROR</H1>
			   <html:messages id="error">
			      <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/>
			   </html:messages>
			   <bean:message key="errors.footer"/>
			   <hr>
			</TD>
		</TR>
</logic:messagesPresent>	
	
		<TD>
			<TABLE WIDTH="700" align="center">	
			<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcTipoRegistro.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:select property="pcTipoRegistro" name="PuntosVentaForm" onchange="updateTextField(this)" >
					<html:option key="puntosVentaForm.pcTipoRegistro.radio.aut.displayname" value="AUT"/>
					<html:option key="puntosVentaForm.pcTipoRegistro.radio.man.displayname" value="MAN"/>
				</html:select>
			</TD>
		</TR>
		
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCvePtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				
				<span ID="Clave" style="visibility:visible">
					<bean:write property="pcCvePtoventas" name="PuntosVentaForm"/>
				</span>
				<span ID="ClaveManual" style="visibility:hidden">
					<html:text property="pcCvePtoventas" name="PuntosVentaForm"  maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				</span>
			</TD>
		</TR> 
		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcDigito.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<!-- <html:text property="pcDigVerif" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>-->
				<span ID="DigVerif" style="visibility:visible">
					<bean:write property="pcDigVerif" name="PuntosVentaForm"/>
				</span>
				<span ID="DigVerifManual" style="visibility:hidden">
					<html:text property="pcDigVerif" name="PuntosVentaForm" maxlength="5" size="2" onchange="this.value=this.value.toUpperCase()"/>
				</span>
			</TD>
		</TR>
				<TR bgcolor="#F9F9F9">
				<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCveDivision.displayname"/> :</TD>
				<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
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
						parametrosQuery='<%= getParametros(String.valueOf(divisionUsuario.getPcCveSubdiv()))%>'
					/>
				</TD>
			</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCveTipoCanal.displayname"/> :</TD>
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
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCveCanal.displayname"/> :</TD>
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
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcNomPtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventas" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
				<logic:present name="detallePuntoVenta">
					<bean:define id="detallePuntoVenta" name="detallePuntoVenta" type="mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO"/>
					<html:hidden property="pcCvePtoventas" name="PuntosVentaForm" value="<%= String.valueOf(detallePuntoVenta.getPcCvePtoventas()) %>"/>
				</logic:present>				
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCntlInt.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCntlInt" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCveReferencia.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveReferencia" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>

		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcDireccion.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcDireccion" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
				
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcColonia.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcColonia" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCp.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCp" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcTel.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcTel" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcFax.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcFax" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
			<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCiudad.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCiudad" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcEstado.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcEstado" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">
				<bean:message key="puntosVentaForm.pcStatus.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<span ID="Status" style="visibility:visible">
					<bean:write property="pcStatus" name="PuntosVentaForm"/>
				</span>
			</TD>
		</TR>
		
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToAgregarPtoVenta(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.agregar"/></html:button>
			</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToModificar(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.modificar"/></html:button>
			</TD>
		</TR>
		</TABLE>
	</TD>
	
	</table>
	<BR><BR>
	<html:hidden property="pcCveRegion" name="PuntosVentaForm" />
	</html:form> 
</BODY>
</html:html>
