<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="mx.com.iusacell.catalogo.modelo.UserSessionVO"%>
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
%>

<%
String usuario = (String)request.getSession().getAttribute("usuario");
%>
<html:html>
<HEAD>

		<title>Iusacell :: Administrador Catalogo Vendedores </title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" type="text/css" href="css/estilos_exp.css">
	    <link rel="stylesheet" type="text/css" media="screen" href="css/global.css" />
	  <link rel="stylesheet" type="text/css" media="screen" href="css/tabs.css" />  
	        
	<script type="text/javascript" src="js/md5.js"></script> 
	<script type="text/javascript" src="js/crypto.js"></script> 
		
		<SCRIPT LANGUAGE="JavaScript">
           

			function goToGuardar(LoginForm) {
					if(LoginForm.pcUserid.value != LoginForm.pcPassword.value){
								if(LoginForm.pcPassword.value.length >= 9 && LoginForm.pcPassword.value.length <= 16){

									  var test = LoginForm.pcPassword.value;
									  var pasar1 = false;
									  var longitud = LoginForm.pcPassword.value.length;
											for (i=0; i < longitud; i++) {
												var car = test.charAt(i);
												bandera1 = car.match('[A-Za-z]');
												if(bandera1 != null)
												pasar1 = true;
											}
											if(pasar1 != true){
				 								alert("El password debe tener como mínimo una letra");	
				 								LoginForm.pcPassword.value="";
				 								LoginForm.pswConfirma.value="";
				 								
				 							} else {
											   LoginForm.pcPassword.value = calculaMD5(LoginForm.pcPassword.value)
											   LoginForm.pswConfirma.value = calculaMD5(LoginForm.pswConfirma.value);
										      if (LoginForm.pcPassword.value != LoginForm.pswConfirma.value){
										      alert("Los passwords de verificación deben de ser iguales"); 
										      LoginForm.pcPassword.value="";
										      LoginForm.pswConfirma.value="";
										
										      }else {
										     
												var action = "?action=GuardarPassword";
								       			  LoginForm.action = LoginForm.action + action;
								    	          var usu = LoginForm.pcUserid.value + '/&';
												LoginForm.usu.value = encriptar(usu);
												LoginForm.pcUserid.value = LoginForm.usu.value;
												LoginForm.submit();
												LoginForm.pcUserid.value="";
										      }	

				 							}
										} else {
											alert("El password debe tener como mínimo 9 caracteres y como máximo 16");
											LoginForm.pcPassword.value="";
											LoginForm.pswConfirma.value="";
							  
										}

														
									}else {
										alert("El password debe ser diferente al campo Usuario");
										LoginForm.pcPassword.value="";
										LoginForm.pswConfirma.value="";

									}
							
							}
							
		
		  function goToCancelar(LoginForm) {
			var action = "?action=CancelarPassword";
			LoginForm.action = LoginForm.action + action;
			LoginForm.pcPassword.value="";
			LoginForm.submit();

		}	
		
		
		function calculaMD5(pass) {
			var pw = pass;
			return hex_md5(pw); 
		}	
	function hex_md5(s){ return binl2hex(core_md5(str2binl(s), s.length * chrsz));}
		

		
	</script>
	       
<TITLE></TITLE>
</HEAD>
	   <script language="JavaScript" src="js/tools.js"></script>     
<BODY>


<html:form method="post" action="/Login" >	 
	<bean:define id="LoginForm" name="LoginForm" type="mx.com.iusacell.catalogo.web.seguridad.struts.action.LoginForm"/>

	<BR><BR>
	<TABLE WIDTH="85%" BORDER="0" CELLSPACING="1" CELLPADDING="2" BGCOLOR="#5D5D5E" align="center">
	<TR>
	<TD BGCOLOR="#FFFFFF">
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#CC0000">
		<TR>
		<TD HEIGHT="20"  CLASS="txtContenidoBlanco" ALIGN="CENTER" ><B>Cambiar Password</B></TD>
		</TR>
		</TABLE>
	</TD>
	</TR>
	</TABLE>
	
	<BR>
	
	
	<table width="765" border="0" cellspacing="0" cellpadding="5" align="center">
	<tr>
		<td width="135" align="right"><strong>Usuario:</strong> </td>
		  <td width="237" align="left">
			<html:text  property="pcUserid"  value="<%=usuario%>"  size="15" maxlength="2" readonly="true"/>
		  </td> 
			<TD WIDTH="10%"  CLASS="txtContenidoBold"><strong></strong></TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
	
	</tr>
	<br><br>
	
		<tr>
		<td width="130"> <div align="right"><div align="right"><strong>Password:</strong></div></td>
			  <td width="200"> 
				<html:password  property="pcPassword" value="" size="16" maxlength="16" />
		      </td> 
		
        
		</tr>
		
		<tr>
		<td width="130"> <div align="right"><div align="right"><strong>Confirmar Password:</strong></div></td>
			  <td width="200"> 
				<html:password  property="pswConfirma" value="" size="16" maxlength="16" />
		      </td> 
		
        
		</tr>
			
		<html:hidden property="usu"/>
		
	</table>	
	
	<BR><BR><BR>
	
			<p><b>Reglas del password:</b> </p>
  <ul>
    <li><font size="1">Debe ser diferente al campo Usuario</font></li>
    <li><font size="1">Debe contener 9 caracteres mínimo y al menos una letra</font></li>
    <li><font size="1">Se obliga el cambio de contraseña la primera vez que se ingresa al sistema</font></li>
    <li><font size="1">Se obliga el cambio de contraseña cada 60 dias</font></li>
  </ul>
  
  
  <BR><BR>
	
	<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3"  align="center">	
	<TR>
	
		 		<TD width="900" align="center"> 
	        	<INPUT TYPE="button" NAME="buscaBtn" VALUE="&nbsp;Guardar&nbsp;" CLASS="boton"
  				onClick="goToGuardar(LoginForm)">
	   			</TD>
	   				   			
	   			<TD width="900" align="center"> 
	        	<INPUT TYPE="button" NAME="buscaBtn" VALUE="&nbsp;Cancelar&nbsp;" CLASS="boton"
  				onClick="goToCancelar(LoginForm)">
	   			</TD>
   			 		
	</TR>
	</TABLE>
	
</html:form>
</BODY>
</html:html>
