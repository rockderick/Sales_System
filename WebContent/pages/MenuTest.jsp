<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<jsp:include page="/Valida.jsp"></jsp:include>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<TITLE>Testing</TITLE>
    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
    <script type="text/javascript" src="../js/tabs.js"></script>

</HEAD>

<BODY>

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home" />
    <menu:displayMenu name="Vendedores" /> 
    <menu:displayMenu name="PuntosVenta" /> 
    <menu:displayMenu name="Relaciones" /> 
    <menu:displayMenu name="Auxiliares" /> 
    <menu:displayMenu name="Exit" />
</menu:useMenuDisplayer>

</BODY>
</html:html>
