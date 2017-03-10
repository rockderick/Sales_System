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
<title><bean:message key="canalesForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script>
		
		function window_onload(form) {
			<logic:present name="mensaje">
				alert('<bean:write name="mensaje"/>');
			</logic:present>
			
		}
		
		function goToBuscar(form) {
				var action = "?action=home&buscar=true";
				form.action = form.action + action;
				form.submit();	
				
		}

		
		function goToAgregar(form) {
			var action = "?action=agregar";
			form.action = form.action + action;
			form.submit();	
				
		}
		
		function goToConsultar(form) {
			var rad = document.getElementById('identif');
			if(rad!=null){
				if(idCheck(form.identif)){
					var action = "?action=consultar";
					form.action = form.action + action;
					form.submit();
				}else{
					alert("Debe seleccionar un Horario para Consultar");
				}
			}else{
				alert("Debe buscar un Horario para Consultar");
			}
		}


		</script>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" onLoad="window_onload(HorariosForm)">

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
	<html:form method="post" action="HorariosAction"> 
	<bean:define id="HorariosForm" name="HorariosForm" type="mx.com.iusacell.catalogo.web.auxiliares.struts.action.HorariosForm"/>	
	
	

	<TABLE WIDTH="700" BORDER="0" align="center">

      <TR>
        <TD HEIGHT="20"><h3>Administrar Horarios</h3></TD>
      </TR>
      
    </TABLE>
    
    
    <TABLE WIDTH="700" BORDER="0" align="center">
    
    	<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Nombre de Horario :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<html:text property="pcDescHorario" size="20"></html:text>
			</TD>
		</TR>
		
		<TR></TR><TR></TR><TR></TR>

	  </TABLE>
	  
	  <br>
	  
	 <TABLE WIDTH="700" BORDER="0" align="center">
	 	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToBuscar(HorariosForm)">Buscar Horario</html:button>
		</TD>
		<td></td>
		</TR>
	 </TABLE>
	 

	
	<div align="center">
	<div style="height:220;width:720;overflow:scroll;visibility:visible">
		<fsj:generalHomeTable 
		  border="1" 
		  width="100%" 
		  cellpadding="3" 
		  cellspacing="0" 
		  queryId="<%=367%>"
		  parametrosQuery='<%=new Object[]{ getValor(request,"pcDescHorario")}%>'
		  VOClass="<%=mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO.class%>" 
		  titulos="Horario" 
		  metodos="getPcDescHorario" 
		  anchosColumnas="100%" 
		  alineacionesColumnas="left" 
		  cssColorEncabezado="boton1" 
		  altoRenglon="13"
		  cssColorRenglon1="td_gris_tablas" 
		  cssColorRenglon2="td_blanco_tablas"
		  witdhColumnRadio="3%"
		  radioName="identif"
		  valueMethodName="getPcCveHorario"
		  consultar='<%=request.getParameter("buscar")!=null%>'
		/> 
	</div>
	</div>
	
	<BR>
	
	<TABLE WIDTH="700" BORDER="0" align="center">
	 	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToConsultar(HorariosForm)">Ver Detalle</html:button>
		</TD>
		<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToAgregar(HorariosForm)">Agregar</html:button>
		</TD>
		</TR>
	 </TABLE>
	 

	</html:form> 
</BODY>
</html:html>
