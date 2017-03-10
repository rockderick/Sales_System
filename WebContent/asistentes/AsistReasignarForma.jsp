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
		var rad = document.getElementById('pcCveVendedor');
			if(rad!=null){
				if(idCheck(form.pcCveVendedor)){
				var action = "";
				if(form.pcCveVendedorSeek.value == form.pcCveVendedor.value){
			       alert('No puede realizar este movimiento, favor de revisar.');
			       return false;
			    }
				action ="?action=consultar&control=siguiente";
				form.action = form.action + action;
				form.submit();
						}else{
						alert("Debe seleccionar un Vendedor");
			     		}
		 	   }
	   }
			function goToSiguienteFinal(form) {
			var rad = document.getElementById('pcCveSuperiorReasignar');
			if(rad!=null){
				if(idCheck(form.pcCveSuperiorReasignar)){
				var action = "";
				if(form.pcCveSuperiorReasignar.value == form.pcCveVendedor.value){
			       alert('No puede realizar este movimiento, favor de revisar.');
			       return false;
			    }
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
		
		function goToBuscarPagCero(form,control) {
			var action ="";
			  
			  if(form.pcNomVendedorSeek.value=="" && form.pcCvePuestoSeek.value==""
			     && form.pcCveVendedorSeek.value=="" && form.pcNomClaveEmpleadoReferenciaSeek.value==""){
			    alert("Introduzca al menos un criterio de búsqueda, favor de revisar.");
			    return false;
			  }
			  
			  if(isNaN(form.pcCveVendedorSeek.value)){
			     alert("La clave de vendedor, sólo debe ser numérica, favor de revisar.");
			     return false;
			  }
			  
			 if(form.pcNomVendedorSeek.value!=""){ 
			  if(!isNaN(form.pcNomVendedorSeek.value)){
			     alert("El nombre del vendedor, sólo debe contener letras, favor de revisar.");
			     return false;
			  }
			 }
			  			 		
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
				form.action = form.action + action;
				form.submit(); 
		}
		
		function goToBuscarPageUno(form,control){
			var action ="";
			  if(form.pcNomVendedorSeek.value=="" && form.pcCvePuestoSeek.value==""
			     && form.pcCveVendedorSeek.value=="" && form.pcNomClaveEmpleadoReferenciaSeek.value==""){
			    alert("Introduzca al menos un criterio de búsqueda, favor de revisar.");
			    return false;
			  }
			  
			  if(isNaN(form.pcCveVendedorSeek.value)){
			     alert("La clave de vendedor, sólo debe ser numérica, favor de revisar.");
			     return false;
			  }
			  
			  if(form.pcNomVendedorSeek.value!=""){ 
			  if(!isNaN(form.pcNomVendedorSeek.value)){
			     alert("El nombre del vendedor, sólo debe contener letras, favor de revisar.");
			     return false;
			  }
			 }			 
		
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
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
	<html:form styleId="AsistReasignarForm" action="AsistReasignarAction" method="POST" onsubmit="return validateAsistReasignarForm(this);"> 
	<bean:define id="AsistReasignarForm" name="AsistReasignarForm" type="mx.com.iusacell.catalogo.web.asistentes.struts.action.AsistReasignarForm"/>
	<bean:define id="userSession" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="asistReasignar.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

<logic:equal name="AsistReasignarForm" property="page" value="0">
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
				<html:text property="pcNomVendedorSeek" name="AsistReasignarForm" maxlength="50" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
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
				<html:text property="pcCveVendedorSeek" name="AsistReasignarForm" maxlength="7" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
		 	<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message  key="asistente.pcNomClaveEmpleadoReferenciaSeek.displayname"/> :</TD>
			<TD WIDTH="40%"  ALIGN="LEFT" CLASS="PTabData">
			<html:text property="pcNomClaveEmpleadoReferenciaSeek" name="ClaveReferenciaForm" maxlength="8" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
		</TD>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarPagCero(AsistReasignarForm,'vendedores')"><bean:message key="button.buscar"/></html:button>
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
		      <li><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/></li>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>
<logic:present name="tablaVendedores">
	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<html:hidden name="AsistReasignarForm" property="pcNombreCompleto"/>
			<html:hidden name="AsistReasignarForm" property="pcCvePuesto"/>
			<html:hidden name="AsistReasignarForm" property="pcDescPuesto"/>
			<html:hidden name="AsistReasignarForm" property="pcCveSuperiorActual"/>
			<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorActual"/>
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
				titulos="Clave,Nombre,Puesto,Status(Fecha),Superior,Division"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto,getPcStatusFecha,getPcNombreSuperior,getPcSubdivision"
				anchosColumnas="10%,30%,18%,15%,12%,12%"
				alineacionesColumnas="center,center,center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCveVendedor"
				script="onClick='updatePcNombreCompleto(AsistReasignarForm,this)'"
				camposExtras='getPcNombreCompleto=nombre,getPcCvePuesto=cvePuesto,getPcDescPuesto=puesto,getPcNombreSuperior=nomSup,getPcCveSuperior=cveSup'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveVendedor")%>'
			/></div>
		</TD></TR>
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<!--<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistReasignarForm)"><bean:message key="asistente.button.anterior"/></html:button>-->
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistReasignarForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
  </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 		<html:hidden name="AsistReasignarForm" property="pcCveVendedor" value="0"/>
		 		<html:hidden name="AsistReasignarForm" property="pcNombreCompleto" value=""/>
		 		<html:hidden name="AsistReasignarForm" property="pcCvePuesto" value=""/>
		 		<html:hidden name="AsistReasignarForm" property="pcDescPuesto" value=""/>
		 		<html:hidden name="AsistReasignarForm" property="pcCveSuperiorActual" value="0"/>
				<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorActual" value=""/>
		 	</TD>
		</TR>
		 <!--<TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistReasignarForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 </TR>-->
</logic:present>
	</table>	
</logic:equal>

<logic:equal name="AsistReasignarForm" property="page" value="1">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistSuperior.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistReasignarForm"/>
				<html:hidden property="pcCveVendedor" name="AsistReasignarForm"/>
				<html:hidden name="AsistReasignarForm" property="pcCveSuperiorActual"/>
				<bean:write property="pcNombreCompleto" name="AsistReasignarForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistSuperior.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistReasignarForm" />
				<html:hidden property="pcDescPuesto" name="AsistReasignarForm" />
				<bean:write property="pcDescPuesto" name="AsistReasignarForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistSuperior.pcNombreSuperiorActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistReasignarForm" property="pcCveSuperiorActual" />
				<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorActual" />
				<bean:write name="AsistReasignarForm" property="pcNombreSuperiorActual" />
			</TD>
		</TR>		
<logic:present name="tablaSubordinados">
	<bean:define id="tablaSubordinados" name="tablaSubordinados" type="java.util.ArrayList"/>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<bean:message key="asistSuperior.message.subordinados"/>
			</TD>
		</TR>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<html:hidden name="AsistReasignarForm" property="pcCvePuestoReasignar"/>
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
			<bean:message key="asistSuperior.message.buscar.pagina1"/>
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
				<html:text property="pcNomVendedorSeek" name="AsistReasignarForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
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
				<html:text property="pcCveVendedorSeek" name="AsistReasignarForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message  key="asistente.pcNomClaveEmpleadoReferenciaSeek.displayname"/> :</TD>
			<TD WIDTH="40%"  ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomClaveEmpleadoReferenciaSeek" name="ClaveReferenciaForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarPageUno(AsistReasignarForm,'superior_reasignar')"><bean:message key="button.buscar"/></html:button>
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
			<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorReasignar"/>
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
				script="onClick='updatePcNombreSuperiorReasignar(AsistReasignarForm,this)'"
				camposExtras='getPcNombreCompleto=nombre'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveSuperiorReasignar")%>'
			/></div>
		</TD></TR>
        <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistReasignarForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToSiguienteFinal(AsistReasignarForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 		</TD>
	    </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 		<html:hidden name="AsistReasignarForm" property="pcCveSuperiorReasignar" value="0"/>
		 		<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorReasignar" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistReasignarForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistReasignarForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 			</TD>
		 </TR>
</logic:present>
</logic:present>
<logic:present name="noSubordinados">
		<bean:define id="noSubordinados" name="noSubordinados" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noSubordinados" />
		 		<html:hidden name="AsistReasignarForm" property="pcCveSuperiorReasignar" value="0"/>
		 		<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorReasignar" value=""/>
		 	</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistReasignarForm)"><bean:message key="asistente.button.anterior"/></html:button>
		 		</TD>
		 		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToSiguiente(AsistReasignarForm)"><bean:message key="asistente.button.siguiente"/></html:button>
	 			</TD>
		 </TR>
</logic:present>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

<logic:equal name="AsistReasignarForm" property="page" value="2">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistSuperior.pcNombreCompleto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistReasignarForm"/>
				<html:hidden property="pcCveVendedor" name="AsistReasignarForm"/>
				<bean:write property="pcNombreCompleto" name="AsistReasignarForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistSuperior.pcDescPuesto.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistReasignarForm" />
				<html:hidden property="pcDescPuesto" name="AsistReasignarForm" />
				<bean:write property="pcDescPuesto" name="AsistReasignarForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistSuperior.pcNombreSuperiorActual.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistReasignarForm" property="pcCveSuperiorActual" />
				<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorActual" />
				<bean:write name="AsistReasignarForm" property="pcNombreSuperiorActual" />
			</TD>
		</TR>		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistSuperior.pcNombreSuperiorReasignar.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistReasignarForm" property="pcCveSuperiorReasignar" />
				<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorReasignar" />
				<bean:write name="AsistReasignarForm" property="pcNombreSuperiorReasignar" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistente.pcFchMovimiento.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text name="AsistReasignarForm" property="pcFchMovimiento"  maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('AsistReasignarForm.pcFchMovimiento','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToRegresar(AsistReasignarForm)"><bean:message key="asistente.button.anterior"/></html:button>
	 		</TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToModificar(AsistReasignarForm)"><bean:message key="asistente.button.confirmar"/></html:button>
	 		</TD>
		</TR>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

<logic:equal name="AsistReasignarForm" property="page" value="3">
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistReasignar.pcNombreCompleto.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcNombreCompleto" name="AsistReasignarForm"/>
				<html:hidden property="pcCveVendedor" name="AsistReasignarForm"/>
				<bean:write property="pcNombreCompleto" name="AsistReasignarForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistReasignar.pcDescPuesto.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcCvePuesto" name="AsistReasignarForm" />
				<html:hidden property="pcDescPuesto" name="AsistReasignarForm" />
				<bean:write property="pcDescPuesto" name="AsistReasignarForm" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="asistReasignar.pcNombreSuperiorReasignar.resumen"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="AsistReasignarForm" property="pcCveSuperiorReasignar" />
				<html:hidden name="AsistReasignarForm" property="pcNombreSuperiorReasignar" />
				<bean:write name="AsistReasignarForm" property="pcNombreSuperiorReasignar" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToInicio(AsistReasignarForm)"><bean:message key="asistente.button.regresar"/></html:button>
	 		</TD>
		</TR>
		</TABLE>
	</TD></TR>
	</table>
</logic:equal>

	<html:hidden name="AsistReasignarForm" property="pcCveVendedorChg"/>
	<html:hidden name="AsistReasignarForm" property="pcNombreCompletoChg"/>
	<html:hidden name="AsistReasignarForm" property="page"/>
	<html:hidden property="coordXHolder" name="AsistReasignarForm" />
	<html:hidden property="coordYHolder" name="AsistReasignarForm" />
	<BR><BR>
	</html:form> 
</BODY>
</html:html>
