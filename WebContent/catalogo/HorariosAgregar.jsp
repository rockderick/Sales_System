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

<%
String diaEliminar = (String)request.getAttribute("diaEliminar");
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
			<logic:present name="mensaje2">
				var diaEliminar = '<%=diaEliminar%>';
				resp = window.confirm('<bean:write name="mensaje2"/>');
            	if (resp){
            		parent.document.location = "HorariosAction.do?action=eliminarDiaAnterior&dia="+diaEliminar+"&buscar1=true";
            	}
			</logic:present>
			<logic:present name="mensaje3">
				var diaEliminar = '<%=diaEliminar%>';
				resp = window.confirm('<bean:write name="mensaje3"/>');
            	if (resp){
            		parent.document.location = "HorariosAction.do?action=eliminarDiaAnterior&dia="+diaEliminar+"&buscar1=true";
            	}
			</logic:present>
			<logic:present name="mensaje4">
				alert('<bean:write name="mensaje4"/>');
			</logic:present>
			<logic:present name="mensaje5">
				resp = window.confirm('<bean:write name="mensaje5"/>');
            	if (resp){
            		parent.document.location = "HorariosAction.do?action=guardarHorarioDescansos&pcDescHorario="+form.pcDescHorario.value+"&buscar1=true";
            	}
			</logic:present>
			
		}
		
		function goToChangeDia(form) {	
			var action = "?action=agregar";
			form.action = form.action + action;
			form.submit();
				
		}
		
		function goToEventos(form) {
	    	MM_validateForm('pcCveDia','RisCombo','Día :');
	    	if (document.MM_returnValue){
	    			var action = "?action=eventos&buscar=true";
					form.action = form.action + action;
					form.submit();
	    		}
		}
		
		function goToDia(form) {
			MM_validateForma('pcEvento','RisCombo','Evento :',
							'pcHora','RisCombo','Hora :',
							'pcMinutos','RisCombo','Minutos :');				
			if (document.MM_returnValue){		
				var action = "?action=agregarDia";
				form.action = form.action + action;
				form.submit();
			}					
		}
		
		function goToDiaDescanso(form) {	
				var action = "?action=agregarDiaDescanso";
				form.action = form.action + action;
				form.submit();
				
		}
		
		function goToGuardar(form) {
			MM_validateForm('pcDescHorario','RisText','Nombre :');
			if (document.MM_returnValue){
				var action = "?action=guardarHorario";
				form.action = form.action + action;
				form.submit();
			}	
				
		}
		
		function goToCancelar(form) {	
			var action = "?action=cancelar";
			form.action = form.action + action;
			form.submit();
				
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
        <TD HEIGHT="20"><h3>Agregar Horario</h3></TD>
      </TR>
      
    </TABLE>
    
    
    <TABLE WIDTH="700" BORDER="0" align="center">
    
    	<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Nombre de Horario :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<html:text property="pcDescHorario" size="20"></html:text>
			</TD>
		</TR>

		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Día :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<fsj:comboGeneral
		    	name = "pcCveDia"
				queryId="<%=Q.OBTENER_DIAS%>"				
				valueMethodName = "getPcCveDia"
				descrMethodName = "getPcDescDia"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcDiasVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveDia")%>' 
				textoNoSeleccion = "-- Seleccione un Día --"
				valorNoSeleccion = ""
				script="onChange='goToChangeDia(HorariosForm)'"
			/></TD>
		</TR>
		
		<TR></TR><TR></TR><TR></TR>
		
		<TR BGCOLOR="#F9F9F9">
		  <td class="txtContenidoRojo">Día Laborable
                <html:radio property="pcDiaLaborable" value="1" onclick="goToEventos(HorariosForm)"></html:radio>
          </td>
          
          <td class="txtContenidoRojo">Día de Descanso
             <html:radio property="pcDiaLaborable" value="2" onclick="goToEventos(HorariosForm)"></html:radio>
          </td>
         </TR>
         
         <TR></TR><TR></TR><TR></TR>
         
        <logic:present name="eventos">
		
			<TR BGCOLOR="#FFFFFF">
				<TD WIDTH="10%"  CLASS="txtContenidoRojo">Evento :</TD>
				<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcEvento"
					queryId="<%=Q.OBTENER_EVENTOS%>"				
					valueMethodName = "getId"
					descrMethodName = "getDescripcion"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.RegistroVO.class%>"
					size = "1"
					classHtml = "input1"
					textoNoSeleccion = "-- Seleccione un Evento --"
					valorNoSeleccion = ""
				/></TD>
			</TR>
			<TR></TR>
			
			<TR BGCOLOR="#F9F9F9">		
				
				<TD WIDTH="10%"  CLASS="txtContenidoRojo">Hora :</TD>
				<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcHora"
					queryId="<%=Q.OBTENER_HORAS%>"				
					valueMethodName = "getPcCveHora"
					descrMethodName = "getPcDescHora"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcHorasVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcHora")%>' 
					textoNoSeleccion = "-- Seleccione una Hora --"
					valorNoSeleccion = ""
				/></TD>
				
				<TD WIDTH="10%"  CLASS="txtContenidoRojo">Minutos :</TD>
				<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcMinutos"
					queryId="<%=Q.OBTENER_MINUTOS%>"				
					valueMethodName = "getPcCveMinuto"
					descrMethodName = "getPcDescMinuto"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcHorasVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcMinutos")%>' 
					textoNoSeleccion = "-- Seleccione un Minuto --"
					valorNoSeleccion = ""
				/></TD>
			</TR>
	         
	       
	      
	    </TABLE>
	    

	    
	    <TABLE WIDTH="700" BORDER="0" align="center">
	    	<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToDia(HorariosForm)">Agregar a Horario</html:button>
			 </TD>
			<td></td>
			</TR>
		</TABLE>
	
	 </logic:present>
	 
	  </TABLE>
	  

	 
	 <logic:present name="SinEventos">
		
	    
	    
	    <TABLE WIDTH="700" BORDER="0" align="center">
	    	<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToDiaDescanso(HorariosForm)">Agregar a Horario</html:button>
			 </TD>
			<td></td>
			</TR>
		</TABLE>
	
	 </logic:present>
	 
	 
	 <div align="CENTER">
	 
	 <div style="height:350;width:720;overflow:scroll;visibility:visible">
	
	 <TABLE WIDTH="700" BORDER="0" align="center">
	 
	 	<tr>
			<td width='20%' class='boton1' align='left' height='13'>Dia
			</td>
			<td width='10%' class='boton1' align='left' height='13'>Dia Habil
			</td>
			<td width='10%' class='boton1' align='right' height='13'>Evento
			</td>					
			<td width='10%' class='boton1' align='right' height='13'>Hora
			</td>
		</tr>
		
		<logic:present name="horario">
			<logic:iterate id="dia" name="horario" indexId="i" >
					
			<%if(i.intValue()%2 == 0){%>
		    	<tr  class="td_gris_tablas">
		    <%}else{%>
				<tr class="td_blanco_tablas">
			<%}%>		
				<td width='10%'  align='left' height='13'><bean:write name="dia" property="pcDescDia"/>
				</td>
				  	<logic:equal name="dia" property="pcDiaLaborable" value="1">
						<td width='10%'  align='right' height='13'>SI
					</td>		  	
				  	</logic:equal>
				  	<logic:equal name="dia" property="pcDiaLaborable" value="2">
						<td width='10%'  align='right' height='13'>NO
					</td>		  	
				  	</logic:equal>
				<td width='10%'  align='right' height='13'><bean:write name="dia" property="descripcion"/>
				</td>
				<td width='15%'  align='right' height='13'><bean:write name="dia" property="horaMinuto"/>
				</td>
			</tr>
			
			</logic:iterate>
		</logic:present>
		

	 </TABLE>
	 
	 
	 
	 </div>
	 </div>
	 <TABLE WIDTH="700" BORDER="0" align="center">
	 	    <TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToGuardar(HorariosForm)">Guardar Horario</html:button>
			 </TD>
			 <TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToCancelar(HorariosForm)">Cancelar Horario</html:button>
			 </TD>
			<td></td>
			</TR>
	 </TABLE>
	 
	 

	</html:form> 
</BODY>
</html:html>
