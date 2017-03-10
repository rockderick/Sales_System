<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="java.util.ArrayList"%>
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

<%
     String sql = "";
     String botones = "";
     String visibilidadBtn = "";
            sql = (String)request.getAttribute("linea");
           if(sql == null){
               sql = "";
           }
           
           if(sql.startsWith("T")){
             botones = "&nbsp;&nbsp;";
             botones +="<input type='button' name='boton' id='botonCommit' value='Commit' onclick='goToConfirm(ConfigValoresVentaForm,'commit')' class='boton'> ";
             botones +="<input type='button' name='boton' id='botonRollBack' value='Rollback' onclick='gotoConfirm(ConfigValoresVentaForm,'rollback')' class='boton'> ";
           
             visibilidadBtn = "visibility:hidden";
           }
           
            
    String visibilidad = "visibility:hidden";
          if(!sql.equals(""))visibilidad= "visibility:visible";
%>
<html:html>
 <HEAD>
  <html:base/>
    <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<META name="GENERATOR" content="IBM WebSphere Studio">
    <TITLE><bean:message key="personalConfigForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
		<script language="JavaScript" src="../js/tools.js"></script>
		<!--<script type="text/javascript" src="../js/tabs.js"></script>-->
		<script language="JavaScript" src="../js/valida_forma.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script>
		function updateBox(form,item){
			itemName= 'valor' + item.value;	
			hiddenItem = document.getElementById(itemName);
			text = hiddenItem.value;
			form.ds_Valor.value= text;
			
			itemName = 'configuracion' + item.value;
			hiddenItem = document.getElementById(itemName);
			text = hiddenItem.value;
			form.ds_Configuracion.value= text;
						
			if(document.forms[0].CveRol.value != "4") {
				if( window.mmIsOpera ) return(document.getElementById("eliminar").style.visibility = 'visible');
				if (document.all) return(document.all.eliminar.style.visibility = 'visible');
				if (document.getElementById) return(document.getElementById("eliminar").style.visibility = 'visible');
			}
			
		}
        
		function validateForm(form){
			return (validateConfigValoresVentaForm(form));
		}
							
		</script>
	    <script>history.forward(1)</script>	
</HEAD>
<html:form method="post" action="ConfigValoresVentaAction">
<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" >
<logic:notPresent name="Admin">
<%
   response.sendRedirect("../Welcome.do");
%>
</logic:notPresent>
<bean:define id="ConfigValoresVentaForm" name="ConfigValoresVentaForm" type="mx.com.iusacell.catalogo.web.valoresVenta.struts.action.ConfigValoresVentaForm"/>	
<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	
<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home" />
    <menu:displayMenu name="Vendedores" /> 
    <menu:displayMenu name="Personal" /> 
    <menu:displayMenu name="PuntosVenta" /> 
    <menu:displayMenu name="Relaciones" /> 
    <menu:displayMenu name="Reportes" /> 
    <menu:displayMenu name="Auxiliares" /> 
</menu:useMenuDisplayer>
<BR/>
<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="personalConfigForm.header"/></h3></TD>
      </TR>
</TABLE>
<BR/>
<TABLE width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
 <TR>
	<TD>
	   <TABLE WIDTH="700" align="center">	<TR bgcolor="#FFFFFF">
		<TR bgcolor="#F9F9F9">
			<TD>
			   <TABLE>
			    <TR>
			     <TD>
			      <TABLE WIDTH="700" align="center">	
			         <TR bgcolor="#FFFFFF">
			            <TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			             <bean:message key="puestosForm.message"/>
			            </TD>
		             </TR>
		        <TR>
			<TD ALIGN="CENTER" colspan="2">
			<fsj:generalHomeTable
				queryId="<%=Q.ALL_NIVELES%>"				
				valueMethodName = "getClConfiguracion"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_NivelesVentaVO.class%>"
				border="1"
				width="60%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Descripciones"
				metodos="getDsValor"
				anchosColumnas="90%"
				alineacionesColumnas="center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="10%"
				radioName="pcCveNiveles"
				camposExtras='getDsValor=valor,getDsConfiguracion=configuracion'
				agregarValorCamposExtras='true'
				script="onClick='updateBox(ConfigValoresVentaForm,this)'"
			/>
		</TR>
		<TR bgcolor="#FFFFFF">
		  <TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
		<div id="eliminar" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToMyEliminar(ConfigValoresVentaForm)"><bean:message key="button.eliminar"/></html:button></div>
		  </TD>
		</TR>	
	  </TABLE>
	  </TD>
	</TR>
	<TR>
	 <TD>
	   <TABLE WIDTH="700" align="center">	
	     <TR bgcolor="#F9F9F9">
		   <TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalConfigForm.message.modificarConfiguracion"/> :<logic:present
										name="noData">
										<bean:define id="noData" name="noData" type="java.lang.String" />
										<TR>
											<TD class="errormessagenegro" colspan="2"><IMG
												src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif"
												width="6" height="6"> <bean:write name="noData" /></TD>
										</TR>
									</logic:present></TD>
		   <TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="ds_Configuracion" name="ConfigValoresVentaForm" maxlength="60" size="30" onchange="this.value=this.value.toUpperCase()" value="" />
		   </TD>
		 </TR>
		 <TR>
		  <TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalConfigForm.message.modificarValor"/>:</TD>		 
          <TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
		   <html:text property="ds_Valor" name="ConfigValoresVentaForm" maxlength="60" size="30" onchange="this.value=this.value.toUpperCase()" value="" />
		  </TD>
		 </TR>  		 
           <logic:messagesPresent>
		     <TR>
		       <TD CLASS="errormessagerojo" colspan="2">
		         <bean:message key="errors.header"/>
		           <html:messages id="error">
		            <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> 
		           <bean:write name="error"/>
		          </html:messages>
		         <bean:message key="errors.footer"/>
		         <hr>
		       </TD>
		      </TR>
           </logic:messagesPresent>		
	       <logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
	         <input type="hidden" name="CveRol" value="0">
			 <TR bgcolor="#FFFFFF">
			   <TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				 <html:button property="boton" styleClass="boton" onclick="goToAgregar(ConfigValoresVentaForm)"><bean:message key="button.agregar"/></html:button>
			   </TD>
			   <TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
				 <html:button property="boton" styleClass="boton" onclick="goToModificar(ConfigValoresVentaForm)"><bean:message key="button.modificar"/></html:button>
			   </TD>
			 </TR>
		   </TABLE>
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
  </TABLE>
  <input type="hidden" value="<%= sql %>" id="textArea" />
 <div id="configurar" style="<%= visibilidad %>" >
 <logic:present name="Admin" >
   <html:textarea property="ds_Command" name="ConfigValoresVentaForm" value="<%= sql %>" styleClass="areaDeTexto">
   </html:textarea>
   <div style="<%= visibilidadBtn %>"><html:button property="boton" styleClass="boton" onclick="goToEjecutar(ConfigValoresVentaForm)"><bean:message key="button.ejecutar"/></html:button></div>
   <html:button property="boton" styleClass="boton" onclick="closingExec();"><bean:message key="button.cerrar"/></html:button>  
 </logic:present>
 </div> 
 </TABLE> 
<BR><BR>
</BODY>
</html:form> 
</html:html>
                                                                                                                                                                                            