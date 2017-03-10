<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="mx.com.iusacell.catalogo.modelo.Pc_PuestosVO"%>
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
%> 
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title><bean:message key="puestosArbolForm.title"/></TITLE>

<style>
   SPAN.TreeviewSpanArea A {
        font-size: 8pt; 
        font-family: verdana,helvetica; 
        text-decoration: none;
        color: black
   }
   SPAN.TreeviewSpanArea A:hover {
        color: '#820082';
   }
   BODY {background-color: white}
   TD {
        font-size: 8pt; 
        font-family: verdana,helvetica; 
   }
</style>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script language="Javascript1.2" src="../js/ua.js"></script>
		<script language="Javascript1.2" src="../js/ftiens4a.js"></script>
		<script>

		function validateForm(form){
			return (validatePuestosForm(form));
		}

		function changePcCvePuesto(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem = FIND(itemName);
			document.forms[0].pcCvePuestos.value= hiddenItem.value;
		}

		function changePcDescPuesto(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem = FIND(itemName);
			document.forms[0].pcDescPuestos.value= hiddenItem.value;
		}

		function changeListPcCvePuesto(form,valor){
			changeCombo1(form.pcCvePuestos,valor);
			changeInput(form.pcDescPuestos,valor,'puesto');
		}

		function updateTable(valor){
			form = document.forms[0];
			changeListPcCvePuesto(form,valor);
		}

		function updateList(valor){
			form = document.forms[0];
			changeCombo1(form.pcCvePuestosSup,valor);
			changeInput(form.pcDescPuestosSup,valor,'puestoSup');
			
			if(document.forms[0].CveRol.value != "4") {
				if( window.mmIsOpera ){
					(document.getElementById("colocarSuperior").style.visibility = 'visible');
					(document.getElementById("quitarSuperior").style.visibility = 'visible');
				} 
				if (document.all) {
					(document.all.colocarSuperior.style.visibility = 'visible');
					(document.all.quitarSuperior.style.visibility = 'visible');
				}
				if (document.getElementById) {
					(document.getElementById("colocarSuperior").style.visibility = 'visible');
					(document.getElementById("quitarSuperior").style.visibility = 'visible');
				}
			}
		}

		function changeCombo1(comboBox,valor){
			for(i=0; i< comboBox.length; i++){
				if(comboBox.options[i].value == valor) comboBox.selectedIndex = i;
			}
			return;
		}

		function updatePuesto(form,item){
			if(item.options[item.selectedIndex].value != ""){
				changeInput(form.pcDescPuestos,item.options[item.selectedIndex].value,'puesto');
			}else{
				form.pcDescPuestos.value = '';
			}
		}


		function updateSup(form,item){
			if(item.options[item.selectedIndex].value != ""){
				changeInput(form.pcDescPuestosSup,item.options[item.selectedIndex].value,'puestoSup');
				if(document.forms[0].CveRol.value != "4") {
					if( window.mmIsOpera ) {
						(document.getElementById("colocarSuperior").style.visibility = 'visible');
						(document.getElementById("quitarSuperior").style.visibility = 'visible');
					}
					if (document.all){
						(document.all.colocarSuperior.style.visibility = 'visible');
						(document.all.quitarSuperior.style.visibility = 'visible');
					}
					if (document.getElementById){
						(document.getElementById("colocarSuperior").style.visibility = 'visible');
						(document.getElementById("quitarSuperior").style.visibility = 'visible');
					}
				}
			}else{
				form.pcDescPuestosSup.value = '';
				if(document.forms[0].CveRol.value != "4") {	
					if( window.mmIsOpera ){
						(document.getElementById("colocarSuperior").style.visibility = 'hidden');
						(document.getElementById("quitarSuperior").style.visibility = 'hidden');
					}
					if (document.all){
						(document.all.colocarSuperior.style.visibility = 'hidden');
						(document.all.quitarSuperior.style.visibility = 'hidden');
					}
					if (document.getElementById) {
						(document.getElementById("colocarSuperior").style.visibility = 'hidden');
						(document.getElementById("quitarSuperior").style.visibility = 'hidden');
					}
				}
			}
		}

		function goToSuperior(form,control){
			var action ="";
				if(control == null || control==undefined){
					action = "?action=asignar";
					form.action = form.action + action;
				}else{
					action = "?action=asignar&control=" + control;
					form.action = form.action + action;
				}
				form.submit();
		}

//Environment variables are usually set at the top of this file.		
USETEXTLINKS = 1
STARTALLOPEN = 0
USEFRAMES = 0
USEICONS = 1
WRAPTEXT = 1
PRESERVESTATE = 1

ICONPATH = '../icons/' //change if the gif's folder is a subfolder, for example: 'images/'


		<logic:present name="textJS">
			<bean:define id="arbol" name="textJS" type="java.util.ArrayList"/>
			<logic:iterate name="arbol" id="entrada" indexId="indice" type="java.lang.String">
				<bean:write name="entrada" filter="false"/>
		  	</logic:iterate>
		</logic:present>
		<logic:notPresent name="textJS">
		    foldersTree = gFld("<i>Reporte</i>", "","")
			foldersTree.iconSrc = ICONPATH + "home24.gif"
			foldersTree.iconSrcClosed = ICONPATH + "homeplus24.gif"
		</logic:notPresent>
	</script>
	<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" onResize="if (navigator.family == 'nn4') window.location.reload()">

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
	<html:form method="post" action="PuestosArbolAction" > 
	<bean:define id="PuestosArbolForm" name="PuestosArbolForm" type="mx.com.iusacell.catalogo.web.auxiliares.struts.action.PuestosArbolForm"/>	
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="puestosArbolForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	 <TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
		<bean:message key="puestosArbolForm.message1"/>
		</TD>
	 </TR>
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
		<bean:message key="puestosArbolForm.message2"/>
		</TD>
	</TR>
	</table>

<BR><BR>

<div style="position:absolute; top:0; left:0; visibility:hidden"><table border=0><tr><td><font size=-2><a style="font-size:7pt;text-decoration:none;color:silver" href="http://www.treemenu.net/" target=_blank>JavaScript Tree Menu</a></font></td></tr></table></div>

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD valign="top" width="55%">
			<table border=0 width="100%">
			<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>
			<span class=TreeviewSpanArea>
			<script>initializeDocument()</script>
			</span>
			</td></tr></table>
	</TD><TD valign="top" width="45%">
			<table border=0 width="100%">
			<tr bgcolor="#F9F9F9"><td class="txtContenido" width="25%"><bean:message key="puestosArbolForm.pcDescPuestos.displayname"/></td><td class="txtContenido">
<logic:present name="tablaPuestos">
	<bean:define id="tablaPuestos" name="tablaPuestos" type="java.util.ArrayList"/>
			<fsj:comboGeneralEstaticoTag
		    	name = "pcCvePuestos"
				lista='<%= tablaPuestos %>'	
				valueMethodName = "getPcCvePuesto"
				descrMethodName = "getPcDescPuesto"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCvePuestosSup")%>' 
				textoNoSeleccion = "-- Seleccione un Puesto --"
				valorNoSeleccion = ""
				camposExtras='getPcCvePuesto=clave,getPcDescPuesto=puesto'
				agregarValorCamposExtras='true'
				script="onChange='updatePuesto(PuestosArbolForm,this)'"
			/>
			<html:hidden property="pcDescPuestos" name="PuestosArbolForm" />
			</td></tr>
			<tr bgcolor="#FFFFFF"><td class="txtContenido" width="25%"><bean:message key="puestosArbolForm.pcDescPuestosSup.displayname"/></td><td class="txtContenido">

			<fsj:comboGeneralEstaticoTag
		    	name = "pcCvePuestosSup"
				lista='<%= tablaPuestos %>'	
				valueMethodName = "getPcCvePuesto"
				descrMethodName = "getPcDescPuesto"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCvePuestosSup")%>' 
				textoNoSeleccion = "-- Seleccione un Puesto --"
				valorNoSeleccion = ""
				camposExtras='getPcCvePuesto=claveSup,getPcDescPuesto=puestoSup'
				agregarValorCamposExtras='true'
				script="onChange='updateSup(PuestosArbolForm,this)'"
			/>
			<html:hidden name="PuestosArbolForm" property="pcDescPuestosSup"/>
</logic:present>
			</td></tr>
			<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
				<input type="hidden" name="CveRol" value="0">
				<tr bgcolor="#FFFFFF">
					<td WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
					<div id="colocarSuperior" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToSuperior(PuestosArbolForm,'insertar')"><bean:message key="puestosArbolForm.button.asignar.superior"/></html:button></div>
			 		</td>
					<td WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
					<div id="quitarSuperior" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToSuperior(PuestosArbolForm,'borrar')"><bean:message key="puestosArbolForm.button.eliminar.superior"/></html:button></div>
			 		</td>
			 	</tr>
			 </logic:notEqual>
			 <logic:equal name="divisionUsuario" property="pcCveRol" value ="4">	
				<input type="hidden" name="CveRol" value="4">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</logic:equal>	
			
			</table>
	</TD>
	</TR>
	</table>

<br><br>

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		</TD></TR>
</logic:present>	 		 
<logic:messagesPresent>
		<TR><TD CLASS="errormessagerojo" colspan="2">
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		</TD></TR>
</logic:messagesPresent>		
		</TABLE>
	</TD>
	</TR>
	</table>
</html:form> 
</BODY>
</html:html>
