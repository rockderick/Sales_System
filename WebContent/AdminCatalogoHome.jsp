<%@ page language="java" %>  
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/fsj-html.tld" prefix="fsj" %> 
<%@ page import="mx.com.iusacell.catalogo.*" %>
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
String alineacion = "center";
%>
<script type="text/javascript" src="js/md5.js"></script> 
<script language="Javascript1.2">
			function calculaMD5() {
			var pw = document.forms[0].pcPassword.value
			return hex_md5(pw) 
		}	
		
		function goToEncriptar(){
			// removed document.forms[0].pcPassword.value = calculaMD5()
			document.forms[0].submit()
			
		}
		
		
		
		function KeyDown(e){
              var k = e.keyCode; 
	          if (k == 13){
		         goToEncriptar();
              }		
		}
			
</script>		

<html:html>

	<head>
		

		<title><bean:message key="adminCatalogoHome.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link rel="stylesheet" type="text/css" href="css/estilos_exp.css">
	    <!--<link rel="stylesheet" type="text/css" media="screen" href="css/global.css" />-->
	  <logic:present name="USER">
	    <% 
	    alineacion = "right";
	    %>
	    <link rel="stylesheet" type="text/css" media="screen" href="css/<%= (String)request.getSession().getAttribute("estiloCss") %>.css"  />
	  </logic:present>    
	  	<link rel="stylesheet" type="text/css" media="screen" href="css/tabs.css" />  
		<script language="JavaScript" src="js/showdate.js"></script>
   <logic:present name="USER">  
	   <script type="text/javascript" src="js/tabs.js"></script> 
   </logic:present>
       <jsp:include page="Icono.jsp" />   
		<script language="JavaScript" src="js/tools.js"></script>
		<script language="JavaScript" src="js/std_button.js"></script>
		
		<script>history.forward(1)</script>
	</head>
  
<body text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" class="principalPagina">

<table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tr>
    <td width="221" height="31"><IMG SRC="<%= request.getContextPath()%>/images/header_4_r1_c1.gif" width="221" height="31" border="0"></td>
    <td align="right" height="31" background="<%= request.getContextPath()%>/images/4_bg_menu.gif"><table width="300" height="31" border="0" cellpadding="0" cellspacing="0" background="<%= request.getContextPath()%>/images/header_41.gif">
        <tr height="31">
          <td width="226" align="center" class="headerB"><script language="Javascript">document.write(Showdate(new Date(), 'ddd', 'mmm', 'dd', 'yyyy', '-'))</script></td>
         <logic:present name="USER"> 
          <td width="37" align="center">
	       	<html:link action="Login.do?action=Salir" styleClass="headerB">salir</html:link>
          </td>
         </logic:present> 
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
<logic:present name="USER">
<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home" />
    <menu:displayMenu name="Vendedores" /> 
    <menu:displayMenu name="Personal" /> 
    <menu:displayMenu name="PuntosVenta" /> 
    <menu:displayMenu name="Relaciones" /> 
    <menu:displayMenu name="Reportes" /> 
    <menu:displayMenu name="Auxiliares" /> 
   
 
    
	
<logic:present name="Admin">
	<menu:displayMenu name="Configuracion" /> 
 </logic:present>		
</menu:useMenuDisplayer>	
</logic:present>
<br>
<br>
<table width="700" cellpadding="0" cellspacing="0" align="<%= alineacion %>">
	<TR>
	<TD height="40" CLASS="titulotop1"><bean:message key="adminCatalogoHome.welcome"/></TD></TR>
	<TR><TD>&nbsp;</TD></TR>
	<TR><TD>
<logic:notPresent name="USER">
<html:form method="post" action="Login"> 
    	<logic:present name="DIVISION">
	<bean:define id="DIVISION" name="DIVISION" type="java.util.ArrayList"/>
	<bean:define id="divisionUsuario" name="divisionUsuario" type="java.lang.String"/>
	<bean:define id="UsuarioDivision" name="USUARIODIV" type="java.lang.String"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" id=borde>		
			<TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" colspan="2" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7">
				<bean:message key="adminCatalogoHome.login.instrucciones2" />
				</TD>
			</TR>
			<TR bgcolor="#F4F4F4">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo">
				<bean:message key="adminCatalogoHome.division.displayname" />
				</TD>
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo">
				<fsj:comboGeneral
			    	name = "pcCveSubdiv"
					queryId="<%= 180%>"				
					valueMethodName = "getPcCveSubdiv"
					descrMethodName = "getPcDescSubdiv"
					VOClass = "<%= mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%= getValor(request,"pcCveSubdiv")%>' 
					textoNoSeleccion = "-- Seleccione una División --"
					valorNoSeleccion = ""
					parametrosQuery='<%=new Object[] {String.valueOf(UsuarioDivision)}%>'
				/>
				
				</TD>
			</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<html:submit><bean:message key="adminCatalogoHome.submit.displayname"/></html:submit>
			</TD>
		</TR>
		</TABLE>
		<html:hidden name="LoginForm" property="action" value="solicitar" />
		<html:hidden name="LoginForm" property="pcUserid" value=""/>
		<html:hidden name="LoginForm" property="pcPassword" value=""/>
		<html:hidden name="LoginForm" property="pcCveDiv" value="<%=divisionUsuario%>"/>

	</logic:present>
	<logic:notPresent name="DIVISION">
		
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" id=borde>		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<bean:message key="adminCatalogoHome.login.instrucciones"/>
			</TD>
		</TR>
		<TR bgcolor="#F4F4F4">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo">
			<bean:message key="adminCatalogoHome.usuario.displayname"/>
			</TD>
			<TD><html:text name="LoginForm" property="pcUserid"/></TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo">
			<bean:message key="adminCatalogoHome.password.displayname"/>
			</TD>
			<TD><html:password name="LoginForm" property="pcPassword" value="" onkeypress="return(KeyDown(event))" /></TD>
		</TR>
		<TR bgcolor="#F4F4F4">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2">
			<html:button property="boton" styleClass="boton" onclick="goToEncriptar()" ><bean:message key="adminCatalogoHome.submit.displayname"/>
			</html:button> 
			
			</TD>
		</TR>
		</TABLE>
       
		<html:hidden name="LoginForm" property="action" value="autorizar"/>
		<html:hidden name="LoginForm" property="pcCveDiv" value=""/>
		<html:errors property="pcUserid"/>
        <html:errors property="pcPassword"/>
	</logic:notPresent>
<logic:present name="mensaje">
	<bean:define id="mensaje" name="mensaje" type="java.lang.String"/>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" >		
		<TR><TD CLASS="errormessagenegro" colspan="2">
		 		<bean:write name="mensaje" />
		 	</TD>
		</TR>
		</TABLE>
</logic:present>	
</html:form>
</logic:notPresent>	
<logic:present name="USER">
	<bean:define id="Usuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
	<TR><TD CLASS="txtContenidoRojo">
<bean:message key="adminCatalogoHome.instrucciones" />
	</TD></TR>
	<TR><TD>
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" >		
		<TR>
			<TD WIDTH="40%" height="40" colspan="2" CLASS="txtContenidoRojo" align="left">
			<img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:message key="adminCatalogoHome.instrucciones2" />
			</TD>
		</TR>
		</TABLE>
	</TD></TR>
	<TR><TD>
	<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3">		
		<logic:notEqual name="Usuario" property="pcCveRol" value="4">
			
			<TR >
			  <TD WIDTH="21%" ALIGN="right"><img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6">&nbsp; </TD>
				<TD WIDTH="79%" CLASS="txtContenido" colspan="4">
				Quiero asignar <html:link action="/AsistPtoventasAction.do" styleClass="txtContenidoRojo">Puntos de Venta</html:link> a mis vendedores
				</TD>
			</TR>
			<TR >
			  <TD WIDTH="21%" ALIGN="right"><img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6">&nbsp; </TD>
				<TD WIDTH="79%" CLASS="txtContenido" colspan="4">
				Quiero <html:link action="/AsistReasignarAction.do" styleClass="txtContenidoRojo">Reasignar Personal</html:link> de la plantilla de algun vendedor
				</TD>
			</TR>
		</logic:notEqual>	
		<logic:equal name="Usuario" property="pcCveRol" value ="4">
			<TR >
			  <TD WIDTH="21%" ALIGN="right"><img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6">&nbsp; </TD>
				<TD WIDTH="79%" CLASS="txtContenido" colspan="4">
				Su Perfil solo le permite realizar consultas al Portal SAEO
				</TD>
			</TR>
			
		</logic:equal>
			<TR >
				<TD WIDTH="21%" ALIGN="right" colspan="2">
					<html:link page="/manual/AdminCatalogo.pdf" styleClass="txtContenidoRojoLink"><bean:message key="adminCatalogoHome.manual"/></html:link>
				</TD>
			</TR>
		</TABLE>
		<TABLE width="100%">	
			<TR >
				<TD WIDTH="80%" ALIGN="CENTER" colspan="2">
				  <span style="color:red">Este portal funciona optimamente en Internet Explorer y en Mozilla 2.0</span>
				</TD>
			</TR>
		</TABLE>
	</TD></TR>
	
</logic:present>	
<logic:present name="unauthorized">
	<TR><TD>&nbsp;</TD></TR>	
	<TR><TD>	
	<bean:define id="unauthorized" name="unauthorized" type="java.lang.String"/>
	
		<TABLE WIDTH="100%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF">		
		<TR ><TD CLASS="errormessagerojo" colspan="2">
		 		<bean:write name="unauthorized" />
		 	</TD>
		</TR>
		</TABLE>
	</TD></TR>
</logic:present>
</table>
</body>

</html:html>


