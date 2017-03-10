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

	public Object[] getParametros(HttpServletRequest req, String valor,String subdivision,String division){
		String res = req.getParameter(valor);
		if(valor.equals("pcCvePuesto") && (res==null || res.equals(""))) res = "1";
		ArrayList param = new ArrayList();
		param.add(res);
		param.add(subdivision);
		param.add(division);
		return param.toArray();	
	}	

%> 
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title><bean:message key="personalForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script language="JavaScript" >
		function verBaja(){
			elementos = FIND('pcCveVendedor');
			if(elementos!=undefined){
				for (i=0;i<elementos.length;i++){
					if(document.forms[0].pcCveVendedor[i].checked==true){
						if( window.mmIsOpera ) {
							(document.getElementById("eliminar").style.visibility = 'visible');
							(document.getElementById("baja").style.visibility = 'visible');
						}
						if (document.all) {
							(document.all.eliminar.style.visibility = 'visible');
							(document.all.baja.style.visibility = 'visible');
						}
						if (document.getElementById){
							(document.getElementById("eliminar").style.visibility = 'visible');
							(document.getElementById("baja").style.visibility = 'visible');
						} 			
					}
				}
			 }
		}
		</script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script language="JavaScript" src="../js/SmartScroll.js"></script>
	    <script type="text/javascript" src="../js/tabsPersonal.js"></script>
		<script>

		function updateCveRef(form,value){
			if(form.pcTipoRegistro.options[form.pcTipoRegistro.selectedIndex].value == 'interno')
				form.pcCveEmpRef.value = value;
		}

		function changeComboForceZero(comboBox,valor,prefijo){
			comboItem  = prefijo + valor;
			hiddenItem = FIND(comboItem);
			valor = hiddenItem.value==0?"":hiddenItem.value;
			for(i=0; i< comboBox.length; i++){
				if(comboBox.options[i].value == valor) comboBox.selectedIndex = i;
			}
			return;
		}

		function updateBox(form,item){
			changeCombo(form.pcCvePuesto,item.value,'puesto');
			changeCombo(form.pcCveSuperior,item.value,'superior');
			changeInput(form.pcCveSuperiorSel,item.value,'superior');
			if(form.Admin.value == 1)changeComboForceZero(form.pcCveSubdiv,item.value,'div');
			changeRadio(form.pcStatus,item.value,'status');
			changeInput(form.pcNomVendedor,item.value,'nomVend');
			changeInput(form.pcApePaterno,item.value,'apePat');
			changeInput(form.pcApeMaterno,item.value,'apeMat');
			changeInput(form.pcCiudad,item.value,'ciudad');
			changeInput(form.pcEstado,item.value,'estado');
			changeInput(form.pcCp,item.value,'cp');                       
			changeInput(form.pcTel,item.value,'tel');                      
			changeInput(form.pcFax,item.value,'fax');                     
			changeInput(form.pcDireccion,item.value,'direccion');       		
			changeInput(form.pcColonia,item.value,'colonia');    
			changeInput(form.pcCveEmpRef,item.value,'empref');    
			changeInput(form.pcFchAlta,item.value,'fchalta');
			changeInput(form.pcCveEmpresa,item.value,'empresa');    
			changeDiv(item.value,'cve');
			changeDivDigito(item.value,'digito');
			if( window.mmIsOpera ) {
				(document.getElementById("eliminar").style.visibility = 'visible');
				(document.getElementById("baja").style.visibility = 'visible');
			}
			if (document.all) {
				(document.all.eliminar.style.visibility = 'visible');
				(document.all.baja.style.visibility = 'visible');
			}
			if (document.getElementById){
				(document.getElementById("eliminar").style.visibility = 'visible');
				(document.getElementById("baja").style.visibility = 'visible');
			} 
			valor=form.pcCveSuperior.options[form.pcCveSuperior.selectedIndex].value;
			if(valor!=form.pcCveSuperiorSel.value) goToDynaBuscar(PersonalForm,"superiorSel");
		}

		function goToTipoSelect(form,item){
			changeCombo(form.pcCveTipoCanal,item.value,'cveTipo');
		}

		function validateForm(form){
			return (validateVendedoresForm(form));
		}		
		
		function changeDiv(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("CveVendedor").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.CveVendedor.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("CveVendedor").innerHTML = hiddenItem.value);
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
		
		function updateTextField(comboBox){
			if(comboBox.options[comboBox.selectedIndex].value == 'persona' || comboBox.options[comboBox.selectedIndex].value == 'tienda'){
				if( window.mmIsOpera ) {
					document.getElementById("CveVendedor").style.visibility = 'visible';
					document.getElementById("guion").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("cveDefinir").style.visibility = 'hidden';
					return;
				}
				if (document.all) {
					document.all.CveVendedor.style.visibility = 'visible';
					document.all.guion.style.visibility = 'visible';
					document.all.DigVerif.style.visibility = 'visible';
					document.all.cveDefinir.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("CveVendedor").style.visibility = 'visible';
					document.getElementById("guion").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("cveDefinir").style.visibility = 'hidden';
					return;
				}
			}else if ( comboBox.options[comboBox.selectedIndex].value == 'distribuidor' || comboBox.options[comboBox.selectedIndex].value == 'interno'){
				if( window.mmIsOpera ){
				 	document.getElementById("cveDefinir").style.visibility = 'visible';
				 	document.getElementById("DigVerif").style.visibility = 'hidden';
				 	document.getElementById("guion").style.visibility = 'hidden';
				 	document.getElementById("CveVendedor").style.visibility = 'hidden';
				 	return;
				}
				if (document.all) {
					document.all.cveDefinir.style.visibility = 'visible';
					document.all.DigVerif.style.visibility = 'hidden';
					document.all.guion.style.visibility = 'hidden';
					document.all.CveVendedor.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("cveDefinir").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'hidden';
					document.getElementById("guion").style.visibility = 'hidden';
					document.getElementById("CveVendedor").style.visibility = 'hidden';
					return;
				}
			}
		}
		
	function goToModificarPersonal(form) {
			var action = "";
			var undefined;
			if(document.forms[0].pcCveEmpresa.value == "") {
				alert("Debes de Elegir una Empresa")
				document.forms[0].pcCveEmpresa.focus()
				return false;
								
			}
			
			if(document.forms[0].pcCveSubdiv ==null || document.forms[0].pcCveSubdiv == undefined) {
				action ="?action=modificar";
				form.action = form.action + action;
				form.submit(); 
			} else {
				if(document.forms[0].pcCveSubdiv.value == "") {
					alert("Debes de Elegir una division")
					document.forms[0].pcCveSubdiv.focus()
					return false;
				} else {
					action ="?action=modificar";
					form.action = form.action + action;
					form.submit(); 
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
	<html:form method="post" action="PersonalAction" method="POST" onsubmit="return validatePersonalForm(this);"> 
	<bean:define id="PersonalForm" name="PersonalForm" type="mx.com.iusacell.catalogo.web.personal.struts.action.PersonalForm"/>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
		<TR>
		<TD HEIGHT="20"><h3><bean:message key="personalForm.header"/></h3></TD>
		</TR>
</TABLE>

    <BR>

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<bean:message key="personalForm.message.buscar"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="personalForm.message.buscar1"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcNomVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedorSeek" name="PersonalForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCvePuestoSeek.displayname"/> :</TD>
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
			<TD COLSPAN="2" ALIGN="CENTER" CLASS="txtContenido"><bean:message key="personalForm.message.buscar2"/></TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveVendedorSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="PersonalForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcNomClaveEmpleadoReferenciaSeek.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomClaveEmpleadoReferenciaSeek" name="PersonalForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		 <TR bgcolor="#F9F9F9">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscar(PersonalForm)"><bean:message key="button.buscar"/></html:button>
		 		</TD>
		 </TR>
<logic:messagesPresent>
		<TR><TD CLASS="errormessagerojo" colspan="2"> <h2> <bean:message key="errors.errorgeneral"/> </h2>
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7" /> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>
<logic:present name="hayPersonal">
		<bean:define id="hayPersonal" name="hayPersonal" type="java.lang.String"/>
		<TR><TD CLASS="errormessagerojo" colspan="2">
		 		<bean:write name="hayPersonal" /><BR>
		 		Vaya a <html:link action="AsistReasignarAction">Reasignar Personal</html:link> para mover la plantilla de esta Persona.
		 	</TD>
		</TR>
</logic:present>
<logic:present name="tablaVendedores">
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
				titulos="Clave,Nombre,Puesto,Fecha de Alta,Status, División"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto,getPcFechaAltaStr,getPcStatus, getPcSubdivision"
				anchosColumnas="15%,33%,18%,10%,8%, 8%"
				alineacionesColumnas="center,center,center,center,center, center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCveVendedor"
				script="onClick='updateBox(PersonalForm,this)'"
				camposExtras='getPcCveVendedor=cve,getPcNomVendedor=nomVend,getPcApePaterno=apePat,getPcApeMaterno=apeMat,getPcCvePuesto=puesto,getPcCveSuperior=superior,getPcFechaAltaStr=fchalta,getPcFechaBajaStr=fchbaja,getPcDigVerif=digito,getPcCntrTda=controlTda,getPcStatus=status,getPcCveEmpRef=empRef,getPcCiudad=ciudad,getPcEstado=estado,getPcCp=cp,getPcTel=tel,getPcFax=fax,getPcDireccion=direccion,getPcColonia=colonia,getPcCveEmpRef=empref,getPcCveEmpresa=empresa,getPcCveSubdiv=div'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveVendedor")%>'
			/></div>
		</TD></TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<div id="eliminar" style="visibility:hidden">&nbsp;</div>
		 		</TD>
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<div id="baja" style="visibility:hidden">
				<table cellpadding="0" cellspacing="0" border="0" align="center">
				<tr><td CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFchBaja.displayname" /> <bean:message key="mascara.fecha"/>: </td>
				    <td CLASS="txtContenido"><html:text property="pcFchBaja" name="PersonalForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				        <a href="javascript:show_calendar('PersonalForm.pcFchBaja','','','DD/MM/YYYY','es')">
  				        <img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a></td>
				    <td CLASS="txtContenido"><html:button property="boton" styleClass="boton" onclick="goToEliminar(PersonalForm,'baja')"><bean:message key="personalForm.button.baja"/></html:button></td>
				</tr></table>
				</div>
		 		</TD>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>
		</TABLE>
				
	</TD>
	</TR>
	</table>
<BR><BR>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="personalForm.detalle.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>



	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
		<bean:message key="personalForm.message"/>
		</TD>
	</TR>
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcTipoRegistro.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:select property="pcTipoRegistro" name="PersonalForm" onchange="updateTextField(this)" >
					<html:option key="vendedoresForm.pcTipoRegistro.radio.persona.displayname" value="persona"/>
					<html:option key="vendedoresForm.pcTipoRegistro.radio.tienda.displayname" value="tienda"/>
					<html:option key="vendedoresForm.pcTipoRegistro.radio.ditribuidor.displayname" value="distribuidor"/>
					<html:option key="vendedoresForm.pcTipoRegistro.radio.interno.displayname" value="interno"/>
				</html:select>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<span ID="CveVendedor" style="visibility:visible"><bean:write property="pcCveVendedor" name="PersonalForm"/></span><span ID="guion">-</span><span ID="DigVerif"><bean:write property="pcDigVerif" name="PersonalForm"/></span><span ID="cveDefinir" style="visibility:hidden"><html:text name="PersonalForm" property="pcCveVendedorDefine" size="10" maxlength="10" onchange="updateCveRef(PersonalForm,this.value)"/></span>
				<html:hidden property="pcCveVendedorChg" name="PersonalForm"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcNomVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomVendedor" name="PersonalForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcApePaterno.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcApePaterno" name="PersonalForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcApeMaterno.displayname" /> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcApeMaterno" name="PersonalForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCvePuesto.displayname"/> : </TD>
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
						script='onChange=goToDynaBuscar(PersonalForm,"superior")'
					/> <html:button property="boton" styleClass="boton" onclick="goToDynaBuscar(PersonalForm,'superior')"><bean:message key="personalForm.button.superior"/></html:button>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveSuperior.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<html:hidden name="PersonalForm" property="pcCveSuperiorSel" />		
					<fsj:comboGeneral
				    	name = "pcCveSuperior"
						queryId="<%=Q.VENDEDOR_SUPERIOR_X_ARBOL%>"				
						valueMethodName = "getPcCveVendedor"
						descrMethodName = "getPcNombreVendedor"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCveSuperiorSel")%>' 
						textoNoSeleccion = "-- Seleccione un Nombre --"
						valorNoSeleccion = ""
						parametrosQuery='<%= getParametros(request,"pcCvePuesto",String.valueOf(divisionUsuario.getPcCveSubdiv()),String.valueOf(divisionUsuario.getPcCveDiv()))%>'			
					/>
			</TD>
		</TR>
		
		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="empresaForm.empresa.displayname"/> : </TD>
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
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveEmpRef.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveEmpRef" name="PersonalForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFchAlta.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcFchAlta" name="PersonalForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('PersonalForm.pcFchAlta','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
			</TD>
		</TR>
<logic:equal name="divisionUsuario" property="pcCveSubdiv" value="0">
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveSubdiv.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<html:hidden property="Admin" value="1"/>
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
				parametrosQuery='<%= new Object[] {String.valueOf(divisionUsuario.getPcCveSubdiv())}%>'
			/>
			</TD>
		</TR>
</logic:equal>		
<logic:present name="detalleVendedor">
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFchBaja.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcFchBaja" name="PersonalForm"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcDigVerif.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<bean:write property="pcDigVerif" name="PersonalForm"/>
			</TD>
		</TR>
		
</logic:present>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<table cellpadding="0" cellspacing="0" border="0" align="center">
				<tr><td CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFchCambio.displayname" /> <bean:message key="mascara.fecha"/>: </td>
				    <td CLASS="txtContenido"><html:text property="pcFchCambio" name="PersonalForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				        <a href="javascript:show_calendar('PersonalForm.pcFchCambio','','','DD/MM/YYYY','es')">
  				        <img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a></td>
				    <td CLASS="txtContenido"><html:button property="boton" styleClass="boton" onclick="goToModificarPersonal(PersonalForm)"><bean:message key="button.modificar"/></html:button></td>
				</tr></table>
			</TD>
		</TR>
		</TABLE>
				<html:hidden property="pcStatus" name="PersonalForm"/>
				<html:hidden property="pcDigVerif" name="PersonalForm"/>
				<html:hidden property="pcCntrTda" name="PersonalForm"/>
				<html:hidden property="pcCiudad" name="PersonalForm" />
				<html:hidden property="pcEstado" name="PersonalForm" />
				<html:hidden property="pcDireccion" name="PersonalForm" />
				<html:hidden property="pcColonia" name="PersonalForm" />
				<html:hidden property="pcCp" name="PersonalForm" />
				<html:hidden property="pcTel" name="PersonalForm" />
				<html:hidden property="pcFax" name="PersonalForm" />
	</TD>
	</TR>
	</table>

	<logic:notEqual name="divisionUsuario" property="pcCveSubdiv" value="0">
	<html:hidden property="Admin" value="0"/>
	</logic:notEqual>
	<html:hidden property="coordXHolder" name="PersonalForm" />
	<html:hidden property="coordYHolder" name="PersonalForm" />
	<BR><BR>
	</html:form> 
</BODY>
</html:html>
