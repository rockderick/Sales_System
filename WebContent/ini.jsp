<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="mx.com.iusacell.catalogo.web.seguridad.struts.action.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fsj-html.tld" prefix="fsj" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head profile="http://gmpg.org/xfn/11">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="generator" content="WordPress 2.7.1" /> <!-- leave this for stats -->
	<link rel="stylesheet" type="text/css" href="css/fin.css">
	<title>Iusacell :: Administrador Catalogo Vendedores</title>
	<script>
		function Salir() {
			history.current = "";
			parent.document.location = "Login.do?action=Salir";
		}
	</script>
</head>
<body>
  <div id="header">
    <div id="headerimg">
	      <h1><a >SAEO</a></h1>
	      <div class="description">Area de Sistemas IUSACELL</div>
	  </div>
  </div>
	<html:form method="post" action="Login" > 
		<bean:define id="LoginForm" name="LoginForm" type="mx.com.iusacell.catalogo.web.seguridad.struts.action.LoginForm"/>
		<div id="wrapper">
			<div id="page">
				<div id="content">
					<div class="post" id="post-419">
						<div class="entry">
							<h2><a href="#" onclick="Salir()" rel="bookmark" title="Salir de la pagina">Debes iniciar tu sesi&oacute;n desde la p&aacute;gina de Portal Corporativo.</a></h2>
							<span>EL acceso a SAEO es por medio de la Llave Maestra.</span>
						</div>
						<br/>
						<br/>
						<logic:present name="mensaje">
							<div class="entry">
					    	<h2><a href="#" onclick="Salir()" rel="bookmark" title="Salir de la pagina">Se presentaron los siguientes errores al inicio.</a></h2>
						    	<span><bean:write name="mensaje"/></span>
					  		</div>
					    </logic:present>
						<div class="post-content">
						</div>
					</div>
				</div>
			</div>
		</div>
	</html:form>
</body>
</html>