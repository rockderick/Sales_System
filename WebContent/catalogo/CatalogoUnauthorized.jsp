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
<title><bean:message key="mensajeSistema.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
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


<table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td width="221" height="31"><IMG SRC="<%= request.getContextPath()%>/images/header_4_r1_c1.gif" width="221" height="31" border="0"></td>
    <td align="right" height="31" background="<%= request.getContextPath()%>/images/4_bg_menu.gif"><table width="300" height="31" border="0" cellpadding="0" cellspacing="0" background="<%= request.getContextPath()%>/images/header_41.gif">
        <tr height="31">
          <td width="37">&nbsp;</td>
          <td width="226" align="center" class="headerB"><script>fecha ()</script></td>
          <td width="37" align="center"><a href="javascript:window.close();" class="headerB">salir</a></td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td width="221" height="30"><IMG SRC="<%= request.getContextPath()%>/images/header_1_r2_c1.gif" width="221" height="30" border="0"></td>
    <td valign="bottom" background="<%= request.getContextPath()%>/images/bg_header_1_r2_c3.gif"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="7%">&nbsp;</td>
          <td width="89%">&nbsp;</td>
          <td width="4%">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
</table>
<BR><BR>
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
  <TR bgcolor="#FFFFFF">
    <TD WIDTH="40%" ALIGN="CENTER" CLASS="errormessagerojo" colspan="2"><bean:message key="unauthorized.message" /> </TD>
  </TR>
</TABLE>
</BODY>
</html:html>
