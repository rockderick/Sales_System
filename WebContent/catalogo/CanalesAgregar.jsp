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
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script>
		
			function window_onload(form) {
				<logic:present name="mensaje">
					alert('<bean:write name="mensaje"/>');
				</logic:present>
			
			}
			
			function goToGuardar(form) {
				
				<%if(request.getSession().getAttribute("Clave").equals("GeneradaPorSistema")){%>
					
					MM_validateForm('pcCveTpCanal','RisCombo','Tipo de Canal :',
									'pcDescCanal','RisText','Descripción de Canal :',
									'pcFecha','RisDate','Fecha de Alta :');
					if (document.MM_returnValue){
						var action = "?action=agregarGuardar";
						form.action = form.action + action;
						form.submit();
					}
				<%}%>
				
				<%if(request.getSession().getAttribute("Clave").equals("GeneradaPorUsuario")){%>
					MM_validateForm('pcCveTpCanal','RisCombo','Tipo de Canal :',
									'pcCveCanal','RisText','Clave de Canal :',
									'pcDescCanal','RisText','Descripción de Canal :',
									'pcFecha','RisDate','Fecha de Alta :');
					if (document.MM_returnValue){
						var action = "?action=agregarGuardar";
						form.action = form.action + action;
						form.submit();
					}
				<%}%> 
			}
			
			function generarClave(form) {
					var action = "?action=generarClave";
					form.action = form.action + action;
					form.submit();
			}						
			
			function goToCancelar(form) {
				var action = "?action=cancelar";
				form.action = form.action + action;
				form.submit();

			}												

		</script>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" onLoad="window_onload(CanalesForm)">

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

	<html:form method="post" action="CanalesAction"> 
	<bean:define id="CanalesForm" name="CanalesForm" type="mx.com.iusacell.catalogo.web.auxiliares.struts.action.CanalesForm"/>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
		 	
	<TABLE WIDTH="700" BORDER="0" align="center">

      <TR>
        <TD HEIGHT="20"><h3>Agregar Canal</h3></TD>
      </TR>
      
    </TABLE>
    
    
    <TABLE WIDTH="700" BORDER="0" align="center">
    
    	<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="30%"  ALIGN="left" CLASS="txtContenidoRojo">Tipo de Canal</TD>
			<TD WIDTH="70%" ALIGN="left" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcCveTpCanal"
					queryId="<%=Q.TIPO_CANALES_ALL%>"				
					valueMethodName = "getPcCveTpCanal"
					descrMethodName = "getPcDescTpCanal"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Tipo_CanalVO.class%>"
					selected='<%=getValor(request,"pcCveTpCanal")%>' 
					size = "1"
					classHtml = "input1"
					textoNoSeleccion = "-- Seleccione un Tipo de Canal --"
					valorNoSeleccion = ""
				/>
			</TD>
		</TR>
		
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="20%"  ALIGN="left" CLASS="txtContenidoRojo">Generar Clave Automaticamente</TD>
			
			<td class="arboltxt">
            SI
                <html:radio property="pcGenerar" value="SI" onclick="generarClave(CanalesForm)"></html:radio>
          
            NO
                <html:radio property="pcGenerar" value="NO" onclick="generarClave(CanalesForm)"></html:radio>
            
          </td>
			
		</TR>
		
		
		<logic:equal name="Clave" value="GeneradaPorUsuario">
	    
	    
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Clave del Canal</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				
				<INPUT type= "text" name = "pcCveCanal" maxlength="10" size="10">
			</TD>
		</TR>
	
	 </logic:equal>
	 
	 <logic:equal name="Clave" value="GeneradaPorSistema">
	    
	    
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Clave del Canal</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				
				<INPUT type= "text" name = "pcCveCanal"  maxlength="10" size="10" disabled="disabled" value="0">
			</TD>
		</TR>
	
	 </logic:equal>
	 
	 


		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="canalesForm.pcDescCanal.displayname"/></TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcDescCanal" name="CanalesForm" maxlength="50" size="50" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Fecha de Alta</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<input type = text name= "pcFecha"  size="10" maxlength="35" />&nbsp;
				<a href="javascript:show_calendar('forms[0].pcFecha','','','DD/MM/YYYY','es')"><img src="<%=request.getContextPath()%>/images/calendario.gif" alt="Calendario" width="14" height="16" border="0" style="cursor:hand" > </a></td>
			</TD>
		</TR>
		
		<TR></TR><TR></TR><TR></TR>

	  </TABLE>
	  
	  <br>
	  

	 

	


	 
	 <TABLE WIDTH="700" BORDER="0" align="center">
	 
	 	<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
			<input type="hidden" name="CveRol" value="0">
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToGuardar(CanalesForm)">Guardar</html:button>
			</TD>
		 	<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToCancelar(CanalesForm)">Cancelar</html:button>
		 	</TD>
		</TR>
	</logic:notEqual>	
			
	<logic:equal name="divisionUsuario" property="pcCveRol" value ="4">	
		<input type="hidden" name="CveRol" value="4">
		<tr>
			<td>&nbsp;</td>
		</tr>
	</logic:equal>


   </TABLE>
	


	


		

		

</html:form> 
</BODY>
</html:html>

