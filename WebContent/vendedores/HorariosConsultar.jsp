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

%> 


<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title>Consultar Horario</TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script>
		
		
		function goToRegresar(form) {
				var action = "?action=home";
				form.action = form.action + action;
				form.submit();	
				
		}
		
		function goToCerrar(form) {
				window.close();
				
		}		


		</script>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">


	<BR><BR>
	<html:form method="post" action="VendedoresAction"> 
	<bean:define id="VendedoresForm" name="VendedoresForm" type="mx.com.iusacell.catalogo.web.vendedores.struts.action.VendedoresForm"/>	
	
	

	<TABLE WIDTH="700" BORDER="0" align="center">

      <TR>
        <TD HEIGHT="20"><h3>Consultar Horario</h3></TD>
      </TR>
      
    </TABLE>
    

	  
		<TABLE WIDTH="700" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#EEEEEE" align="center">
		<TR>
	
	
		<table border='1' width='70%' cellpadding='3' cellspacing='0' align="center">
			<tr>
				<td width='15%' class='boton1' align='left' height='13'>DIA
				</td>
				<td width='12%' class='boton1' align='left' height='13'>ENTRADA
				</td>
				<td width='12%' class='boton1' align='left' height='13'>SALIDA COMER
				</td>
				<td width='12%' class='boton1' align='left' height='13'>ENTRADA COMER
				</td>
				<td width='12%' class='boton1' align='left' height='13'>SALIDA
				</td>
				
			</tr>
			
            	<logic:present name="horario"  >
					<logic:iterate id="res" name="horario" indexId="i" >
                    <%if(i.intValue()%2 == 0){%>
		                <tr  class="td_gris_tablas">
		                <%}else{%>
						<tr class="td_blanco_tablas">
						<%}%>
		
							<td width='15%'  align='left' height='20'><bean:write name="res" property="pcDescDia"/>
							</td>
							<td width='12%'  align='left' height='20'><bean:write name="res" property="entrada"/>
							</td>
							<td width='12%'  align='left' height='20'><bean:write name="res" property="salidaComer"/>
							</td>
							<td width='12%'  align='left' height='20'><bean:write name="res" property="entradaComer"/>
							</td>
							<td width='12%'  align='left' height='20'><bean:write name="res" property="salida"/>
							</td>
						</tr>
		                </logic:iterate>

					</logic:present>
		</table> 
			
	

	
	<BR><BR>
	
	<TABLE WIDTH="700" BORDER="0" align="center">
	 	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToCerrar(VendedoresForm)">Cerrar</html:button>
		</TD>
		<td></td>
		</TR>
	 </TABLE>
	 
	 
	 

	</html:form> 
</BODY>
</html:html>
