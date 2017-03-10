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
%>

<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title><bean:message key="acctMgr.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script type="text/javascript" src="../js/md5.js"></script> 
		<script language="JavaScript" src="../js/Calendario.js"></script>
		
		<SCRIPT language="Javascript1.2">
		function updateBox(form,item){
			itemName= 'user' + item.value;
			hiddenItem = FIND(itemName);
			text = hiddenItem.value;
			form.pcUserid.value= text;
			changeDiv(item.value,'user');
			itemName= 'nombre' + item.value;
			hiddenItem = FIND(itemName);
			text = hiddenItem.value;
			form.pcNombreCuenta.value= text;
			itemName= 'fecha' + item.value;
			hiddenItem = FIND(itemName);
			text = hiddenItem.value;
			form.pcFecha.value= text;			
			
			if( window.mmIsOpera ) {
				(document.getElementById("eliminar").style.visibility = 'visible');
				(document.getElementById("modificar").style.visibility = 'visible');
				(document.getElementById("alta").style.visibility = 'hidden');
			}
			if (document.all) {
				(document.all.eliminar.style.visibility = 'visible');
				(document.all.modificar.style.visibility = 'visible');
				(document.all.alta.style.visibility = 'hidden');
				
			}
			if (document.getElementById){
				(document.getElementById("eliminar").style.visibility = 'visible');
				(document.getElementById("modificar").style.visibility = 'visible');
				(document.getElementById("alta").style.visibility = 'hidden');
			} 
	
		}
		
	function changeDiv(valor,prefijo){
		itemName = prefijo + valor;
		hiddenItem      = FIND(itemName);
		if( window.mmIsOpera ) return(document.getElementById("modificar").innerHTML = hiddenItem.value);
		if (document.all) return(document.all.modificar.innerHTML = hiddenItem.value);
		if (document.getElementById) return(document.getElementById("modificar").innerHTML = hiddenItem.value);
		return false; 
	}
				
	function goToGuardar(AcctManagerForm) {
		if(AcctManagerForm.pcUserid.value != AcctManagerForm.pcPassword.value){
			if(AcctManagerForm.pcPassword.value.length >= 9 && AcctManagerForm.pcPassword.value.length <= 16){
				var test = AcctManagerForm.pcPassword.value;
				var pasar1 = false;
				var longitud = AcctManagerForm.pcPassword.value.length;
				for (i=0; i < longitud; i++) {
					var car = test.charAt(i);
					bandera1 = car.match('[A-Za-z]');
					if(bandera1 != null)
						pasar1 = true;
					}
					if(pasar1 != true){
 						alert("El password debe tener como mínimo una letra");	
 						AcctManagerForm.pcPassword.value="";
 						return false;
 					} 
				} else {
					alert("El password debe tener como mínimo 9 caracteres y como máximo 16");
					AcctManagerForm.pcPassword.value="";
					return false;
				}				
		} else {
				alert("El password debe ser diferente al campo Usuario");
				AcctManagerForm.pcPassword.value="";
				return false;
		}

		if (AcctManagerForm.pcPassword.value != AcctManagerForm.pswConfirma.value){
			alert("Los passwords de verificación deben de ser iguales"); 
			AcctManagerForm.pcPassword.value="";
			AcctManagerForm.pswConfirma.value="";
			return false;
		}	
		return true;
	}
	
	function calculaMD5() {
		var pw = document.forms[0].pcPassword.value
		return hex_md5(pw) 
	}	
	
	function goToEncriptar(form){
	
		if(goToGuardar(form)) {
			document.forms[0].pcPassword.value = calculaMD5()
			document.forms[0].pswConfirma.value = document.forms[0].pcPassword.value
			goToAgregar(form)
		} else
			return false;
	}
	
	function goToModificarE(form) {
		if(goToGuardar(form)) {
			document.forms[0].pcPassword.value = calculaMD5()
			document.forms[0].pswConfirma.value = document.forms[0].pcPassword.value
			goToModificar(form)
		} else
			return false;
	}
</script>
<script>history.forward(1)</script>
</HEAD>

		
<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="SysAdmin" />
    <menu:displayMenu name="SysAdminMapeo" />
    <menu:displayMenu name="SysAdminReportes" /> 
</menu:useMenuDisplayer>	

<BR><BR>
<logic:present name="USER">
	<html:form method="post" action="AcctMgr"> 
	<bean:define id="AcctManagerForm" name="AcctManagerForm" type="mx.com.iusacell.sysadmin.web.sysadmin.struts.action.AcctManagerForm"/>	
	<TABLE WIDTH="750" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="acctMgr.main"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<TABLE width="100%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR><TD WIDTH="40%" VALIGN="TOP">		
	<TABLE WIDTH="100%" align="center" id=borde>	
	<logic:messagesPresent>
		<TR><TD height="30" colspan="2" CLASS="errormessagerojo">		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		     <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   
		</TD></TR>
	</logic:messagesPresent>
	<logic:present name="mensaje">
			<bean:define id="mensaje" name="mensaje" type="java.lang.String"/>
			<TR bgcolor="#CCCCCC">
			  <TD colspan="2"></TD>
	    </TR>
			<TR><TD height="30" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
			 		<bean:write name="mensaje" />
			 	</TD>
			</TR>
	</logic:present>	
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%" CLASS="txtContenidoBold"><bean:message key="acctMgr.userid.displayname" /> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<div id="alta" style="visibility:visible">
					<html:text property="pcUserid" name="AcctManagerForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
				</div>
				<div id="modificar" style="visibility:hidden">
					<bean:write property="pcUserid" name="AcctManagerForm"/>	
				</div>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" CLASS="txtContenidoBold"><bean:message key="acctMgr.password.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:password property="pcPassword" name="AcctManagerForm" maxlength="40" size="30" />
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%" CLASS="txtContenidoBold"><bean:message key="acctMgr.confirma.displayname" /> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:password property="pswConfirma" name="AcctManagerForm" maxlength="40" size="30" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="40%" CLASS="txtContenidoBold"><bean:message key="acctMgr.nombreCuenta.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNombreCuenta" name="AcctManagerForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR>
			<TD WIDTH="40%"  CLASS="txtContenidoBold"><bean:message key="acctManagerForm.pcFechaExpiration.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcFecha" name="AcctManagerForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
					<a href="javascript:show_calendar('AcctManagerForm.pcFecha','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
				
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD height="40" colspan="2" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToEncriptar(AcctManagerForm)"><bean:message key="button.agregar"/></html:button>
			<html:button property="boton" styleClass="boton" onclick="goToModificarE(AcctManagerForm)"><bean:message key="button.modificar"/></html:button>
			</TD>
		</TR>	
		</TABLE>
	</TD>
	<TD WIDTH="60%" valign="top">		
		<div style="background:#FFFFFF;height:350;width:100%;overflow:scroll;visibility:visible;align=center">
			<TABLE WIDTH="100%" align="center" id=borde>	
				<TR bgcolor="#FFFFFF">
					<TD ALIGN="CENTER" CLASS="txtContenido">
						<bean:message key="acctMgr.message"/>
					</TD>
				</TR>
				<TR>
					<TD ALIGN="CENTER">
						<bean:define id="tablaCuentas" name="tablaCuentas" type="java.util.ArrayList"/>	
						<fsj:generalHomeTableEstatico
							lista='<%= tablaCuentas %>'
							valueMethodName = "getPcUserid"
							VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_LoginVO.class%>"
							border="1"
							width="100%"
							cellpadding="0"
							cellspacing="0"
							cssColorEncabezado="td_mensajes"
							titulos="UserId, Nombre, Rol"
							metodos="getPcUserid,getPcNombreCuenta,getPcDescRol"
							anchosColumnas="25%,50%, 25%"
							alineacionesColumnas="center,center,center"
							altoRenglon="1"
							cssColorRenglon1="td_gris_tablas"
							cssColorRenglon2="td_blanco_tablas"
							witdhColumnRadio="10%"
							radioName="pcPcUserid"
							camposExtras='getPcUserid=user,getPcNombreCuenta=nombre,getPcExpirationDateStr=fecha'
							agregarValorCamposExtras='true'
							script="onClick='updateBox(AcctManagerForm,this)'"
						/>
					</TD>
				</TR>
			  </TABLE>
			</TD>
		</TR>
	</TABLE>
	<TABLE WIDTH="100%" align="center" id=borde>	
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<div id="eliminar" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToEliminar(AcctManagerForm)"><bean:message key="button.eliminar"/></html:button></div>
			</TD>
		</TR>
	</TABLE>
	
	<br>
	     <div align="right"><html:link action="/Logout.do" styleClass="txtContenidoRojoLink"><bean:message key="adminCatalogoHome.logout"/></html:link></div>	
	</html:form>
</logic:present>	 
<logic:notPresent name="USER">	
<TABLE WIDTH="750" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="acctMgr.unauthorized"/></h3></TD>
      </TR>
    </TABLE>

</logic:notPresent>

</BODY>
</html:html>
