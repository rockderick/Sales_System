<%@ page import="mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO"%>
<%@ page import="mx.com.iusacell.catalogo.modelo.UserSessionVO"%>
<%@ page import="mx.com.iusacell.catalogo.dao.AdminCatFacade"%>
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
	
	public String[] getParameters(HttpServletRequest req, String[] valores){
		String[] parametros  = new String[valores.length];
		String res = null;
		try {
			for(int i = 0; i < valores.length; i++){
				res = req.getParameter(valores[i]);
				if(res != null && (res.trim().length() == 0)){
					res = null;
				}else{
					parametros[i]= res;
				}
			}
		} catch (NullPointerException npe) {
		}
		return parametros;	
	}

	public Object[] getParametros(HttpServletRequest req, String valor,String subdivision,String division){
		String res = req.getParameter(valor);
		if(valor.equals("pcCvePuesto") && (res==null || res.equals(""))) res = "1";
		
		ArrayList param = new ArrayList();
		param.add(res);
		param.add(subdivision);
		param.add(division);
		return param.toArray();	
	}
	public Object[] getParametrosSuperior(HttpServletRequest req, String valor,String subdivision,String division, String superior){
		String res = req.getParameter(valor);
		if(valor.equals("pcCvePuesto") && (res==null || res.equals(""))) res = "1";
		
		ArrayList param = new ArrayList();
		param.add(res);
		param.add(subdivision);
		param.add(division);
		param.add(superior);
		return param.toArray();	
	}	
%> 
<%
String cveMostrarSup= (String) request.getAttribute("cveSupMostrar");
String sup= request.getParameter("claveSuperior");
if(cveMostrarSup!=null){
sup=cveMostrarSup;
}else{
sup= request.getParameter("claveSuperior");
}

      String cveVendedorSeek = ((String)request.getAttribute("pcCveVendedorSeek")==null)? 
                                "":(String)request.getAttribute("pcCveVendedorSeek");
                    
      String nomVendedorSeek = ((String)request.getAttribute("pcNomVendedorSeek")==null)?
                                "":(String)request.getAttribute("pcNomVendedorSeek");
                                
      String nomClaveEmpleadoReferenciaSeek = ((String)request.getAttribute("pcNomClaveEmpleadoReferenciaSeek")==null)?
                                "":(String)request.getAttribute("pcNomClaveEmpleadoReferenciaSeek");
      
      String cvePuestoSeek = ((String)request.getAttribute("pcCvePuestoSeek")==null)?
                                "":(String)request.getAttribute("pcCvePuestoSeek");;

      Integer consultas = null;
      if(((UserSessionVO)request.getSession().getAttribute("USER")).getPcCveRol() ==4)
                   consultas = new Integer(1);
                   
      if(((UserSessionVO)request.getSession().getAttribute("USER")).isPcValidadoConfig())
           ((UserSessionVO)request.getSession().getAttribute("USER")).setPcCveSubdiv(0);
                   
%>

<html:html>
<HEAD>
<html:base/>
<title><bean:message key="personalForm.title"/></TITLE>
<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	
<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
<!--<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />-->
<link rel="stylesheet" type="text/css" media="screen" href="../css/<%= (String)request.getSession().getAttribute("estiloCss") %>.css"  />   
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
<script language="JavaScript" src="../js/tools.js"></script>
<script language="JavaScript" src="../js/valida_forma.js"></script>
<script language="Javascript1.2" src="../js/std_button.js"></script>
<script language="JavaScript" src="../js/Calendario.js"></script>
<script language="JavaScript" src="../js/tabsPersonal.js"></script>
<SCRIPT language="JavaScript" src="../js/personalFncs.js"></SCRIPT>
<SCRIPT language="JavaScript" src="../js/personalAjax.js"></SCRIPT>
<script>
	function comprueba (valor) {
		
			<logic:present name="mensaje">
				alert('<bean:write name="mensaje"/>');
			</logic:present>		
			if(valor != "4") {
				if( window.mmIsOpera ) {
					if(document.getElementById("eliminar") !=null)
						(document.getElementById("eliminar").style.visibility = 'visible');
					if(document.getElementById("baja")!=null)
						(document.getElementById("baja").style.visibility = 'visible');
					if(document.getElementById("division")!=null)
						(document.getElementById("division").style.visibility = 'visible');
				}
				if (document.all) {
					if(document.all.eliminar!=null)
						(document.all.eliminar.style.visibility = 'visible');
					if(document.all.baja!=null)
						(document.all.baja.style.visibility = 'visible');
					if(document.all.division!=null)
						(document.all.division.style.visibility = 'visible');
				}
				if (document.getElementById){
					if(document.getElementById("eliminar")!=null)
						(document.getElementById("eliminar").style.visibility = 'visible');
					if(document.getElementById("baja")!=null)
						(document.getElementById("baja").style.visibility = 'visible');
					if(document.getElementById("division")!=null)
						(document.getElementById("division").style.visibility = 'visible');
				} 
			}
			
			if ( document.forms[0].pcTipoRegistro.value == 'distribuidor' || document.forms[0].pcTipoRegistro.value== 'interno' ){
			
				if ( document.forms[0].pcTipoRegistro.value == 'distribuidor'){
					document.getElementById("distmaster").style.visibility = 'visible';
				}else{
					document.getElementById("distmaster").style.visibility = 'hidden';
				}
				if ( document.forms[0].pcDistMAster.value == 'M' ) {
					document.getElementById("optdistmaster").checked=true;
				}else{
					document.getElementById("optdistmaster").checked=false;
				}
				
				if( window.mmIsOpera ){
					
				 	document.getElementById("cveDefinir").style.visibility = 'visible';
				 	document.getElementById("DigVerif").style.visibility = 'hidden';
				 	document.getElementById("guion").style.visibility = 'hidden';
				 	document.getElementById("CveVendedor").style.visibility = 'hidden';
				 	return;
				}
				if (document.all) {
					
					document.all.cveDefinir.style.visibility = 'visible';
					document.all.DigVerif.style.visibility = 'hidden';
					document.all.guion.style.visibility = 'hidden';
					document.all.CveVendedor.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					
					document.getElementById("cveDefinir").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'hidden';
					document.getElementById("guion").style.visibility = 'hidden';
					document.getElementById("CveVendedor").style.visibility = 'hidden';
					return;
				}
				
			}
				if(PersonalMovForm.claveSuperior.value!=0){
				goToActualizarComboSuperiores();
				}			
		}
		
		function goToSuperior(form){
			if(form.pcCveVendedor){
		    	var miArray = document.getElementsByName("pcCveVendedor"); 
		        var clave;
		        for(var i=0; i<miArray.length; i++) {    
                	if(miArray[i].checked)
                    	clave = miArray[i].value;
                }       
			    var numero = validarEntero(clave);
			    if(!numero){
			    	alert('Seleccione un vendedor por favor.');
			    } else
					window.open("<%= request.getContextPath()%>/PersonalMovAction.do?action=buscarSuperior&pcCveVendedor="+clave+"",
					"BuscaSuperior","width=730,height=500,status=0, menubar=0,toolbar=0, " +
					"resizable=1, top=100, left=100, scrollbars=1");
					<% if(request.getSession().getAttribute("tablaSuperiores")!=null)
						request.getSession().removeAttribute("tablaSuperiores"); %>
		 	} else
		    var confirmacion = confirm('¿Esta realizando el alta de un nuevo vendedor?');
		    if(confirmacion){
		           window.open("<%= request.getContextPath()%>/PersonalMovAction.do?action=buscarSuperior&pcCveVendedor=0",
				   "BuscaSuperior","width=730,height=500,status=0, menubar=0,toolbar=0, " +
				   "resizable=1, top=100, left=100, scrollbars=1");
				   <% if(request.getSession().getAttribute("tablaSuperiores")!=null)
				      request.getSession().removeAttribute("tablaSuperiores"); %>
			}
		}
	        
	function goToDynaBuscarPersonal(form,control) {
		
		if(document.getElementById("optdistmaster").checked){
			form.pcDistMAster.value='M';
			}else{
				form.pcDistMAster.value='V';
			}
		
			var action ="";
			if(form.pcCveVendedor){
		              var miArray = document.getElementsByName("pcCveVendedor"); 
		              var clave;
		              for(var i=0; i<miArray.length; i++) {    
                          if(miArray[i].checked)
                             clave = miArray[i].value;
                     }
                     var numero = validarEntero(clave);
                     if(numero){
 						if(control == null){
				           action = "?action=consultar";
			            }else{
			      	       action = "?action=consultar&control=" + control;
			          }
			             form.action = form.action + action;
			             form.submit();                    
                     }else{
                        alert('Escoja a un vendedor de la parte superior por favor.');
                        return false;
                     }
                   }else{
                       if(control == null){
				           action = "?action=consultar";
			            }else{
			      	       action = "?action=consultar&control=" + control;
			          }
			             form.action = form.action + action;
			             form.submit();        
           }   
		}        	 

</script>
<script language="JavaScript">
//Llamadas AJAX
function updateFormAjax(vendedor){
        limpiar();
	if(!ajaxReq)
		ajaxReq = getAjax();
	if (ajaxReq){
	    ajaxReq.abort();
		ajaxReq.onreadystatechange = handleVendedor;
		ajaxReq.open("GET", "<%= request.getContextPath()%>/PersonalMovAction.do?action=buscaAjax&cveVendedor="+vendedor, true);
		ajaxReq.send(null);
	}
}

function buscaSuperior(puesto){
	if(!ajaxReq)
		ajaxReq = getAjax();
	if (ajaxReq){
	    ajaxReq.abort();
		ajaxReq.onreadystatechange = handleBuscaSup;
		<% if(sup == null || sup.equals("0")){ %>
		var comboSup = document.forms[0].pcCveSuperior;
		var superior = comboSup.options[comboSup.selectedIndex].value;
		<% }else{ %>
		var superior = <%=sup%>;
		<% } %>
		ajaxReq.open("GET","<%= request.getContextPath()%>/PersonalMovAction.do?action=ajaxSuperior&cvePuesto="+puesto+"&subDiv=<%=String.valueOf(divisionUsuario.getPcCveSubdiv())%>&division=<%=String.valueOf(divisionUsuario.getPcCveDiv())%>&superior="+superior, true);
		ajaxReq.send(null);
	}
}

	    function goToChoose(){
			var forma = document.forms[0];
			var cveVendedor = forma.pcCveVendedorSeek.value;
			var numeroEmpleado = forma.pcNomClaveEmpleadoReferenciaSeek.value;
			var puestoEmpleado = forma.pcCvePuestoSeek.value;
			var nombre = forma.pcNomVendedorSeek.value;
			var control = "";

			if(cveVendedor.length>0) {
				if(isNaN(cveVendedor)) {
					alert('El campo clave debe de ser numérico');
	                return false;
				}
	            control = 'cveVendedor';
	            goToSearch(PersonalMovForm,control);
			}else if(nombre.length>0 && puestoEmpleado.length==0){
				if(!(isNaN(nombre))){
					alert('El campo nombre no puede contener números');
					return false;
				}
				control = 'nombre';
				goToSearch(PersonalMovForm,control);
	        }else if(numeroEmpleado.length>0){
	            control = 'noEmpleado';
	            goToSearch(PersonalMovForm,control);
	        }else if(puestoEmpleado.length>0 && nombre.length==0){
	            control = 'puesto';
	            goToSearch(PersonalMovForm,control); 
	        }else if(cveVendedor.length==0 && numeroEmpleado.length==0 && puestoEmpleado.length==0 && nombre.length==0){
	            alert('Debe llenar al menos un criterio de búsqueda, favor de revisar');
	        	return false;
	        }else if(nombre.length>0 && puestoEmpleado.length>0){
	            control = 'filtros';	                   
	            goToSearch(PersonalMovForm,control);
	        }
		}
</script>
<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" 
	marginheight="0" onload='comprueba(document.forms[0].CveRol.value)'
	class="personalPagina">

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

<BR><BR>
	<html:form method="post" action="PersonalMovAction" method="POST" onsubmit="return validatePersonalForm(this);"> 
	<bean:define id="PersonalMovForm" name="PersonalMovForm" type="mx.com.iusacell.catalogo.web.personal.struts.action.PersonalMovForm"/>
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="personalForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>
	<html:hidden property="claveSuperior" value="<%=sup%>" name="PersonalMovForm"/>
	<html:hidden property="pcCveSupBase" value="<%=cveMostrarSup%>" name="PersonalMovForm"/>

	<table width="700" cellpadding="0" cellspacing="0" border="0" align="center" id="principalTable">
	<TR>
	<TD>
		<TABLE HEIGHT="20" WIDTH="700" align="center">
		<TR>
			<TD ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="3" width="67%">
			<bean:message key="personalForm.message.buscar"/>
			</TD>
		</TR>
		<TR >
			<TD ALIGN="CENTER" CLASS="txtContenido" colspan="3" width="67%">
			<bean:message key="personalForm.message.buscar1"/>
			</TD>
		</TR>
		<TR >
			<TD CLASS="txtContenidoRojo" ><bean:message key="personalForm.pcCveVendedorSeek.displayname"/>: </TD>
			<TD ALIGN="RIGHT" CLASS="PTabData">
				<html:text property="pcCveVendedorSeek" name="PersonalMovForm" maxlength="7" size="30" value="<%= cveVendedorSeek %>" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR>
		 <TD>&nbsp;
		 </TD>
		 <TD ALIGN="RIGHT" >
		   <html:button property="boton" styleClass="boton" onclick="goToChoose()">
			<bean:message key="button.buscar"/>
		   </html:button>
		 </TD>
		</TR>	
	</TABLE>
	<TABLE HEIGHT="20" WIDTH="700" align="center" id="tabla2" >
	   <TR>
	     <TD>&nbsp;</TD>
	   </TR>
	<logic:present name="error">
	   <TR CLASS="PTabData" align="center">
	     <TD CLASS="txtContenidoRojo"><bean:message key="personalForm.errors.mostrar"/></TD>
	   </TR>  
	</logic:present>   
      <TR align="center" id="borde2" >
        <TD CLASS="txtContenidoRojo" colspan="3" width="67%"><h3><bean:message key="personalForm.buscar.detalle"/></h3></TD>
      </TR>
       <TR >
			<TD CLASS="txtContenidoRojo">
			  <bean:message key="personalForm.pcNomVendedorSeek.displayname"/>:
			</TD>
			<TD ALIGN="RIGHT" CLASS="PTabData">
				<html:text property="pcNomVendedorSeek" name="PersonalMovForm" maxlength="40" size="30" value="<%= nomVendedorSeek %>" onblur="this.value=this.value.toUpperCase()" />
			</TD>
	   </TR>  
		<TR >
			<TD   CLASS="txtContenidoRojo"><bean:message key="personalForm.pcNomClaveEmpleadoReferenciaSeek.displayname"/> :</TD>
			<TD   ALIGN="RIGHT" CLASS="PTabData">
				<html:text property="pcNomClaveEmpleadoReferenciaSeek" name="PersonalMovForm" maxlength="10" size="30" value="<%= nomClaveEmpleadoReferenciaSeek %>" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR>
	    <TD CLASS="txtContenidoRojo" ><bean:message key="personalForm.pcCvePuestoSeek.displayname"/>: </TD>	
		<TD ALIGN="RIGHT" CLASS="PTabData">
	    	<fsj:comboGeneral
		    	name = "pcCvePuestoSeek"
				queryId="<%=Q.PUESTOS_ALL%>"				
				valueMethodName = "getPcCvePuesto"
				descrMethodName = "getPcDescPuesto"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCvePuestoSeek")%>' 
				textoNoSeleccion = "-- Seleccione un Puesto --"
				valorNoSeleccion = ""
			 />
	    </TD>
	   </TR>
	   <TR>
	     <TD>&nbsp;</TD>
	     <TD align="right">
		   	<html:button property="boton" styleClass="boton" onclick="goToChoose()">
		 		<bean:message key="button.buscar"/>
			</html:button>
		 </TD>
		</TR>    
	 </TABLE> 
<TABLE HEIGHT="20" WIDTH="700" align="center" id="tabla2">		 
<logic:messagesPresent>
		<TR>
		 <TD CLASS="errormessagerojo" colspan="2" width="469"> <h2> <bean:message key="errors.errorgeneral"/> </h2>
		   <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   <hr>
		 </TD>
		</TR>
</logic:messagesPresent>
<logic:present name="tablaVendedores">
<logic:present name="porConfirmar">
		<TR><TD CLASS="errormessagerojo" colspan="2">
		   <bean:message key="personalForm.errors.repetido"/>
		</TD></TR>
</logic:present>

	<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/> 
		<TR>
		 <TD CLASS="txtContenidoRojo" colspan="2">
			<div style="height:200;width:100%;overflow:scroll;visibility:visible">	
			 <fsj:generalHomeTableEstatico
				lista='<%= tablaVendedores %>'	
				valueMethodName = "getPcCveVendedor"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Clave,Nombre,Puesto,Estatus (fecha),División,Huella"
				metodos="getPcCveOta, getPcNombreCompleto, getPcDescPuesto, getPcStatusFecha, getPcSubdivision, getPcEstatusHuella"
				anchosColumnas="12%,25%,20%,18%,15%,10%"
				alineacionesColumnas="center,center,center,center,center, center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="5%"
				radioName="pcCveVendedor"
				script="onClick='updateFormAjax(this.value);'"
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCveVendedor")%>'
			 />
		  </div>
		 </TD>
	   </TR> 
	   <TR >
		  <TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
			<div id="eliminar" style="visibility:hidden">&nbsp;</div>
		  </TD>
	   </TR> 
	   <logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
	     <TR>
		   <TD WIDTH="40%" ALIGN="left" CLASS="txtContenido" colspan="2">
			 	<table cellpadding="2" cellspacing="0" border="0" align="left">
					<div id="baja" style="visibility:hidden">
					<tr>
						<td CLASS="txtContenidoRojo" WIDTH="10%" align="left">Fecha de Reactivación:</td>
				    	<td CLASS="txtContenido" WIDTH="40%" align="left">
				    		<html:text property="pcFchReact" name="PersonalMovForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				        	<a href="javascript:show_calendar('PersonalMovForm.pcFchReact','','','DD/MM/YYYY','es')">
  				        		<img src="<%=request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" >
  				        	</a>
  				        </td>
  				        <td CLASS="txtContenido" align="left">
  				        	<html:button property="boton" styleClass="boton" onclick="goToReactivar(PersonalMovForm)">Reactivación</html:button>
  				        </td>
					<tr>
  				        <td CLASS="txtContenidoRojo" WIDTH="10%" align="left"><bean:message key="personalForm.pcFchBaja.displayname" /> <bean:message key="mascara.fecha"/>: </td>
				    	<td CLASS="txtContenido" WIDTH="40%" align="left"><input type="text" name="pcFchBaja2" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				        	<a href="javascript:show_calendar('PersonalMovForm.pcFchBaja2','','','DD/MM/YYYY','es')">
  				        		<img src="<%=request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" >
  				        	</a>
  				        </td>
  				        <td CLASS="txtContenido" align="left"><html:button property="boton" styleClass="boton" onclick="goToEliminarFecha(PersonalMovForm,'baja')"><bean:message key="personalForm.button.baja"/></html:button></td>
					</tr>
					</div>
					<div id="division" style="visibility:hidden">		
					<TR>
						<TD WIDTH="10%" CLASS="txtContenidoRojo" align="left"><bean:message key="personalForm.pcCveSubdiv.displayname"/> : </TD>
						<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
							<fsj:comboGeneral
						    	name = "pcCveDivCambia"
								queryId="<%=Q.DIVISION_ALL%>"				
								valueMethodName = "getPcCveDiv"
								descrMethodName = "getPcDescDiv"
								VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_DivisionVO.class%>"
								size = "1"
								classHtml = "input1"
								textoNoSeleccion = "-- Seleccione una División --"
								valorNoSeleccion = ""
								parametrosQuery='<%= new Object[] {"0"}%>'
							/> 
						</TD>
						<td CLASS="txtContenido" align="left"><html:button property="boton" styleClass="boton" onclick="goToCambiarDivision(PersonalMovForm,'cambiarDivisión')"><bean:message key="personalForm.button.division"/></html:button></td>
					</TR>
					</div>
				</table>
		 		</TD>
		 </TR>
  </logic:notEqual>		 
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR>
		    <TD CLASS="errormessagenegro" colspan="2"> 
		        <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>
		</TABLE>
				
	</TD>
	</TR>
	</table>
<BR><BR>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="personalForm.detalle.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<table width="700" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR >
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
		<bean:message key="personalForm.message"/>
		</TD>
	</TR>
	<TR>
	<TD>
<script>
function checadistmaster(comboBox){
	
	if(comboBox.options[comboBox.selectedIndex].value == 'distribuidor'){
		document.getElementById("distmaster").style.visibility = 'visible';
		}else{
			
			document.getElementById("distmaster").style.visibility = 'hidden';
			}
	}

function cambiaoptdist(){
	
	if(document.getElementById("optdistmaster").checked){
		document.getElementById("optdistmaster").checked=false;
		}else{
			document.getElementById("optdistmaster").checked=true;
			}
	
	}
	
	
	
</script>
		<TABLE WIDTH="700" BORDER="0" CELLSPACING="1" CELLPADDING="3" ALIGN="CENTER" >		
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcTipoRegistro.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:select property="pcTipoRegistro" name="PersonalMovForm" onchange="updateTextField(this);checadistmaster(this);" >
					<html:option key="vendedoresForm.pcTipoRegistro.radio.persona.displayname" value="persona"/>
					<html:option key="vendedoresForm.pcTipoRegistro.radio.tienda.displayname" value="tienda"/>
					<html:option key="vendedoresForm.pcTipoRegistro.radio.ditribuidor.displayname" value="distribuidor"/>
					<html:option key="vendedoresForm.pcTipoRegistro.radio.interno.displayname" value="interno"/>
				</html:select>
				<html:hidden property="pcDistMAster" name="PersonalMovForm"/>
				<span ID="distmaster" style="visibility:hidden">Master<input type=radio value="0" id="optdistmaster" onclick="cambiaoptdist();"></span>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<span id="CveVendedor"><bean:write property="pcCveVendedor" name="PersonalMovForm"/></span>
				<span ID="guion">-</span>
				<span ID="DigVerif"><bean:write property="pcDigVerif" name="PersonalMovForm"/></span>
				<span ID="cveDefinir" style="visibility:hidden">
					<html:text name="PersonalMovForm" property="pcCveVendedorDefine" size="10" maxlength="10" onchange="updateCveRef(PersonalMovForm,this.value)"/>
				</span>
				<input type="hidden" id="pcCveVendedorChg" name="pcCveVendedorChg" />
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcNomVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text maxlength="40" size="30" property="pcNomVendedor" name="PersonalMovForm" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcApePaterno.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcApePaterno" name="PersonalMovForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>	
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcApeMaterno.displayname" /> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcApeMaterno" name="PersonalMovForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCiudad.displayname" /> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCiudad" name="PersonalMovForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcEstado.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcEstado" name="PersonalMovForm" maxlength="20" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcDireccion.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcDireccion" name="PersonalMovForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcColonia.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcColonia" name="PersonalMovForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCp.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCp" name="PersonalMovForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcTel.displayname"/> <bean:message key="mascara.telefono"/>: </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcTel" name="PersonalMovForm"  maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFax.displayname"/> <bean:message key="mascara.telefono"/>: </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcFax" name="PersonalMovForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCvePuesto.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<fsj:comboGeneral
				    	name = "pcCvePuesto"
						queryId="<%=Q.PUESTOS_ALL%>"				
						valueMethodName = "getPcCvePuesto"
						descrMethodName = "getPcDescPuesto"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_PuestosVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCvePuesto")%>' 
						textoNoSeleccion = "-- Seleccione un Puesto --"
						valorNoSeleccion = ""
						script='onChange=goToDynaBuscarPersonal(PersonalMovForm,"superior"),updateFormAjax(this.form.pcCveVendedor.value)'
						disabled='<%= consultas != null %>'
					/><br>
	          <logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">	
				<html:button property="boton" styleClass="boton" onclick="goToSuperior(PersonalMovForm)">
				   <bean:message key="personalForm.button.superior"/>
				</html:button>
			  </logic:notEqual>
			</TD>
		</TR>
		<logic:notEqual name="divisionUsuario" property="pcCveSubdiv" value="0">
		 <TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveSuperior.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="PersonalMovForm" property="pcCveSuperiorSel" />
					<fsj:comboGeneral
				    	name = "pcCveSuperior"
						queryId="<%=Q.VENDEDOR_SUPERIOR_X_ARBOL2%>"				
						valueMethodName = "getPcCveVendedor"
						descrMethodName = "getPcNombreVendedor"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCveSuperior")%>' 
						textoNoSeleccion = "-- Seleccione un Nombre --"
						valorNoSeleccion = ""
						parametrosQuery='<%= getParametrosSuperior(request,"pcCvePuesto",divisionUsuario.getPcCvesDivs(),divisionUsuario.getPcCvesDivs(),sup)%>'			
						disabled='<%=consultas != null %>'
					/>
			</TD>
		</TR>
		</logic:notEqual>
	   <logic:equal name="divisionUsuario" property="pcCveSubdiv" value="0">
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveSuperior.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="PersonalMovForm" property="pcCveSuperiorSel" />
					<fsj:comboGeneral
				    	name = "pcCveSuperior"
						queryId="<%=Q.VENDEDOR_SUPERIOR_X_ARBOL2%>"				
						valueMethodName = "getPcCveVendedor"
						descrMethodName = "getPcNombreVendedor"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCveSuperior")%>' 
						textoNoSeleccion = "-- Seleccione un Nombre --"
						valorNoSeleccion = ""
						parametrosQuery='<%= getParametrosSuperior(request,"pcCvePuesto",String.valueOf(divisionUsuario.getPcCveSubdiv()),String.valueOf(divisionUsuario.getPcCveDiv()),sup)%>'			
						disabled='<%=consultas != null %>'
					/>
			</TD>
		</TR>
	   </logic:equal>	
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Comision: </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<% String tipoComis = request.getParameter("pcTipoComision") == null ? "0" : request.getParameter("pcTipoComision"); %>
			<select name="pcTipoComision" class="input1">
				<option value="" <%=tipoComis.equals("0") ? "selected='selected'" : ""%>>-- Seleccione --</option>
				<option value="S" <%=tipoComis.equals("S") ? "selected='selected'" : ""%>>SEMANAL</option>
				<option value="M" <%=tipoComis.equals("M") ? "selected='selected'" : ""%>>MENSUAL</option>
			</select>
			</TD>
		<TR id="nivelVentas">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Nivel de ventas: </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<% String nvlVtas = request.getParameter("pcNivelVentas") == null ? "0" : request.getParameter("pcNivelVentas"); %>
				<fsj:comboGeneral
				    	name = "pcNivelVentas"
						queryId="<%=Q.NIVEL_VENTAS%>"				
						valueMethodName = "getPcNivel"
						descrMethodName = "getPcCuota"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_NivelesVentaVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=nvlVtas%>' 
						textoNoSeleccion = "-- Seleccione  --"
						valorNoSeleccion = ""
					/>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Horario : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
					name = "pcCveHorario"
					queryId="<%=Q.OBTENER_HORARIOS %>"				
					valueMethodName = "getPcCveHorario"
					descrMethodName = "getPcDescHorario"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveHorario")%>' 
					textoNoSeleccion = "-- Seleccione un Horario --"
					valorNoSeleccion = "0"
					parametrosQuery='<%=new Object[]{null}%>'
					disabled='<%=request.getSession().getAttribute("horario") == null%>'
				/><html:button property="boton" styleClass="boton" onclick="goToHorario(PersonalMovForm)">Ver Detalle Horario</html:button>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveEmpRef.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveEmpRef" name="PersonalMovForm" maxlength="7" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="empresaForm.empresa.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
				    	name = "pcCveEmpresa"
						queryId="<%=Q.EMPRESA_ALL%>"				
						valueMethodName = "getPcCveEmpresa"
						descrMethodName = "getPcDescEmpresa"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_EmpresaVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%=getValor(request,"pcCveEmpresa")%>' 
						textoNoSeleccion = "-- Seleccione una Empresa --"
						valorNoSeleccion = ""
						parametrosQuery='<%= new Object[]{null} %>'			
					/>
			</TD>
		</TR>
				<TR >
					<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFchBaja.displayname"/> : </TD>
					<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					  <html:text property="pcFchBaja" name="PersonalMovForm" maxlength="10" size="10" style="background-color:FFFFFF;" readonly="true" />
					</TD>
				</TR>
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFchAlta.displayname" /> <bean:message key="mascara.fecha"/>: </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				
				<html:text property="pcFchAlta" name="PersonalMovForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
				<a href="javascript:show_calendar('PersonalMovForm.pcFchAlta','','','DD/MM/YYYY','es')">
				<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" > </a>
				
			</TD>
		</TR>	
  <logic:present name="multiDivisiones" >
    <logic:notEqual name="divisionUsuario" property="pcCveSubdiv" value="0" > 
 		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveSubdiv.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<html:hidden property="Admin" value="1"/>
			<fsj:comboGeneral
		    	name = "pcCveSubdiv"
				queryId="<%=Q.SUBDIVISION_ALL %>"				
				valueMethodName = "getPcCveSubdiv"
				descrMethodName = "getPcDescSubdiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveSubdiv")%>' 
				textoNoSeleccion = "-- Seleccione una División --"
				valorNoSeleccion = ""
				parametrosQuery='<%= new Object[] {divisionUsuario.getPcCvesDivs()}%>'
			/>
			</TD>
		</TR>		
    </logic:notEqual>
  </logic:present>  			   
<logic:equal name="divisionUsuario" property="pcCveSubdiv" value="0">
  <logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
		<TR >
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcCveSubdiv.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
			<html:hidden property="Admin" value="1"/>
			<fsj:comboGeneral
		    	name = "pcCveSubdiv"
				queryId="<%=Q.SUBDIVISION_ALL %>"				
				valueMethodName = "getPcCveSubdiv"
				descrMethodName = "getPcDescSubdiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveSubdiv")%>' 
				textoNoSeleccion = "-- Seleccione una División --"
				valorNoSeleccion = ""
				parametrosQuery='<%= new Object[] {String.valueOf(divisionUsuario.getPcCveSubdiv())}%>'
			/>
			</TD>
		</TR>
	</logic:notEqual>		
</logic:equal>
	   <logic:present name="detalleVendedor">
				<TR >
					<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFchBaja.displayname"/> : </TD>
					<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
						<bean:write property="pcFchBaja" name="PersonalMovForm"/>
					</TD>
				</TR>
				<TR >
					<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="personalForm.pcDigVerif.displayname"/> : </TD>
					<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
						<bean:write property="pcDigVerif" name="PersonalMovForm"/>
					</TD>
				</TR>
		</logic:present>
		
		
			<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
				<input type="hidden" name="CveRol" value="0">
				<TR >
					<logic:present name="porConfirmar">
							<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
									<html:button property="boton" styleClass="boton" onclick="goToAgregarAdmin(PersonalMovForm,'confirmar')"><bean:message key="personalForm.button.confirmar"/></html:button>
								</TD>
					</logic:present>
					<logic:notPresent name="porConfirmar">
								<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
									<html:button property="boton" styleClass="boton" onclick="goToAgregarAdmin(PersonalMovForm)"><bean:message key="button.agregar"/></html:button>
								</TD>
					</logic:notPresent>
						
				
					<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
						<table cellpadding="0" cellspacing="0" border="0" align="center">
							<tr>
								<td CLASS="txtContenidoRojo"><bean:message key="personalForm.pcFchCambio.displayname" /> <bean:message key="mascara.fecha"/>: </td>
							    <td CLASS="txtContenido"><html:text property="pcFchCambio" name="PersonalMovForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
							        <a href="javascript:show_calendar('PersonalMovForm.pcFchCambio','','','DD/MM/YYYY','es')">
			  				        	<img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" style="cursor:hand" >
			  				        </a>
			  				       </td>
							    <td CLASS="txtContenido">
							    	<html:button property="boton" styleClass="boton" onclick="goToModificarAdmin(PersonalMovForm)"><bean:message key="button.modificar"/></html:button>
							    </td>
							</tr>
						</table>
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
				<html:hidden property="pcStatus" name="PersonalMovForm"/>
				<html:hidden property="pcDigVerif" name="PersonalMovForm"/>
				<html:hidden property="pcCntrTda" name="PersonalMovForm"/>
	</TD>
	</TR>
	</table>

	<logic:notEqual name="divisionUsuario" property="pcCveSubdiv" value="0">
	<html:hidden property="Admin" value="0"/>
	</logic:notEqual>
	<input type="hidden" name="pcCveVendedorVal" id="pcCveVendedorVal" value="<bean:write name="PersonalMovForm" property="pcCveVendedor" />"/>
	<BR><BR>
	</html:form> 
</BODY>
</html:html>
