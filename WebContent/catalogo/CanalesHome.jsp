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
		<script language="JavaScript" src="../js/SmartScroll.js"></script>
	    <script type="text/javascript" src="../js/tabsPersonal.js"></script>
		<script>
		
			function window_onload(form) {
				<logic:present name="mensaje">
					alert('<bean:write name="mensaje"/>');
				</logic:present>
			
			}
		
			function goToConsultar(form) {
				MM_validateForm('pcCveTipoCanal','RisCombo','Tipo de Canal :');
				if (document.MM_returnValue){
					var action = "?action=home&buscar=true";
					form.action = form.action + action;
					form.submit();
				}
			}
			
			function goToAgregar(form) {
					var action = "?action=agregar";
					form.action = form.action + action;
					form.submit();
			}
			
			function goToModificar(form) {
				var rad = document.getElementById('pcCveCanal');
				if(rad!=null){
					if(idCheck(form.pcCveCanal)){
						var action = "?action=modificar";
						form.action = form.action + action;
						form.submit();
					}else{
						alert("Debe seleccionar un Canal para Modificar");
					}
				}else{
					alert("Debe buscar un Canal para Modificar");
				}
			}			
			

			
			function goToEliminar2(form) {
				var rad = document.getElementById('pcCveCanal');
				if(rad!=null){
					if(idCheck(form.pcCveCanal)){
						var action = "?action=eliminar";
						form.action = form.action + action;
						form.submit();
					}else{
						alert("Debe seleccionar un Canal para Eliminar");
					}
				}else{
					alert("Debe buscar un Canal para Eliminar");
				}
			}
			
			function goToEliminar(form) {
				var rad = document.getElementById('pcCveCanal');
				if(rad!=null){
					if(idCheck(form.pcCveCanal)){
						MM_validateForm('pcFecha','RisText','Fecha de Eliminación :');
						if (document.MM_returnValue){
							resp = window.confirm('Se eliminará el Canal y sus Puntos de Venta asociados \n¿Desea continuar?');
							if (resp){
								var action = "?action=eliminarGuardar";
								form.action = form.action + action;
								form.submit();
							}
						}
					}else{
						alert("Debe seleccionar un Canal para Eliminar");
					}	
				}else{
					alert("Debe buscar un Canal para Eliminar");
				}	
					
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
        <TD HEIGHT="20"><h3>Administrar Canales</h3></TD>
      </TR>
      
    </TABLE>
    
    
    <TABLE WIDTH="700" BORDER="0" align="center">
    
    	<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="30%"  ALIGN="left" CLASS="txtContenidoRojo"><bean:message key="canalesForm.message.buscar"/></TD>
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcCveTipoCanal"
					queryId="<%=Q.TIPO_CANALES_ALL%>"				
					valueMethodName = "getPcCveTpCanal"
					descrMethodName = "getPcDescTpCanal"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Tipo_CanalVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveTipoCanal")%>' 
					textoNoSeleccion = "-- Seleccione un Tipo de Canal --"
					valorNoSeleccion = ""
				/>
			</TD>
			<TD WIDTH="30%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			     <html:button property="boton" styleClass="boton" onclick="goToConsultar(CanalesForm)">Buscar Canal</html:button>
		    </TD>
		</TR>
		<TR></TR>

	  </TABLE>

<%
if(request.getParameter("pcCveTipoCanal")!=null)
    if(!request.getParameter("pcCveTipoCanal").equals("03")){
%>	
	<div align="center">
	<div style="height:220;width:720;overflow:scroll;visibility:visible">
			<fsj:generalHomeTable
				queryId="<%=Q.CANALES_X_TIPO%>"	
				valueMethodName = "getPcCveCanal"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_CanalVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="boton1"
				titulos="Cve Tipo Canal, Clave Canal, Canales, Estatus"
				metodos="getPcCveTpCanal,getPcCveCanal,getPcDescCanal,getPcStatus"
				anchosColumnas="10%,15%, 50%,25%"
				alineacionesColumnas="center,center, center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="10%"
				radioName="pcCveCanal"
				parametrosQuery='<%=new Object[]{getValor(request,"pcCveTipoCanal")}%>'
				consultar='<%=request.getParameter("buscar")!=null%>'			
			/>

	 
	</div>
	</div>
<%
}else{
%>	
	<div align="center">
	<div style="height:220;width:720;overflow:scroll;visibility:visible">
			<fsj:generalHomeTable
				queryId="<%= Q.CANALES_X_TIPO_2 %>"	
				valueMethodName = "getPcCveCanal"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_CanalVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="boton1"
				titulos="Cve Tipo Canal, Clave Canal, Canales, Estatus"
				metodos="getPcCveTpCanal,getPcCveOta,getPcDescCanal,getPcStatus"
				anchosColumnas="10%,20%, 50%,20%"
				alineacionesColumnas="center,center, center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="10%"
				radioName="pcCveCanal"
				parametrosQuery='<%=new Object[]{getValor(request,"pcCveTipoCanal")}%>'
				consultar='<%=request.getParameter("buscar")!=null%>'			
			/>

	 
	</div>
	</div>
	
<%
}
%>
	
<%
if(request.getParameter("pcCveTipoCanal")!=null){
%>			
		<TABLE WIDTH="400" BORDER="0" align="CENTER">
		<TR BGCOLOR="#FFFFFF" ALIGN="RIGHT">
			<TD WIDTH="40%"  CLASS="txtContenidoRojo" ALIGN="RIGHT">Fecha de Eliminación <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="40%" ALIGN="RIGHT">
				<input type = text name= "pcFecha"  size="15" maxlength="35" />&nbsp;
				<a href="javascript:show_calendar('forms[0].pcFecha','','','DD/MM/YYYY','es')"><img src="<%=request.getContextPath()%>/images/calendario.gif" alt="Calendario" width="14" height="16" border="0" style="cursor:hand" > </a></td>
				
			</TD>
		 </TR>
	 	</TABLE>
<%}%>	
	

	
	
	<BR>
	 
	 <TABLE WIDTH="700" BORDER="0" align="center">
	 	
	 
<%
if(request.getParameter("pcCveTipoCanal")!=null){
%>	 
	 
	<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
		<TR>
			<TD WIDTH="50%" ALIGN="left" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToModificar(CanalesForm)"><bean:message key="button.modificar"/></html:button>
		 	</TD>
		 	<TD WIDTH="50%" ALIGN="right" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToEliminar(CanalesForm)"><bean:message key="button.eliminar"/></html:button>
		 	</TD>
		</TR>
	</logic:notEqual>
<%}%>			
	<logic:equal name="divisionUsuario" property="pcCveRol" value ="4">	
		<input type="hidden" name="CveRol" value="4">
		<tr>
			<td>&nbsp;</td>
		</tr>
	</logic:equal>
	<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
			<input type="hidden" name="CveRol" value="0">
		<TR>
		   <BR>
			<TD WIDTH="100%" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToAgregar(CanalesForm)"><bean:message key="button.agregar"/></html:button>
			</TD>
		</TR>
	  </logic:notEqual>	
   </TABLE>
</html:form> 
</BODY>
</html:html>
