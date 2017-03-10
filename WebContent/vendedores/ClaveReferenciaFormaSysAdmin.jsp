<%@ page import="mx.com.iusacell.catalogo.modelo.Pc_MapeoVendedorVO"%>
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
<title><bean:message key="vendedoresForm.title"/></TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script language="Javascript1.2">
			
		function updateBox(form,item){
			if( window.mmIsOpera ) {
				(document.getElementById("obtener").style.visibility = 'visible');
				return;
			}
			if (document.all) {
				(document.all.item("obtener").style.visibility = 'visible');
				return;
			}
			if (document.getElementById){
				(document.getElementById("obtener").style.visibility = 'visible');
				return;
			} 
		}

		function changeValor(elemento,valor){
			if( window.mmIsOpera ) return(document.getElementById(elemento).innerHTML = valor);
			if (document.all) return(document.all.item(elemento).innerHTML = valor);
			if (document.getElementById) return(document.getElementById(elemento).innerHTML = valor);
			return false;
		}	
		
		function updateForm(form,item){
			changeInput(form.pcCveVendedor,item.value,'clave');
			changeInput(form.pcSistema,item.value,'sistema');
			changeInput(form.pcCustomerId,item.value,'custid');
			changeInput(form.pcCveRegVenta,item.value,'regVen');
			changeInput(form.pcNomUsuario,item.value,'usuario');			
		
			document.getElementById("btnEliminarMapeo").disabled=false;
		}

		function changeDiv(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("CveVendedor").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.CveVendedor.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("CveVendedor").innerHTML = hiddenItem.value);
			return false; 
		}
		</script>
		<script>history.forward(1)</script>
		
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="SysAdmin" />
    <menu:displayMenu name="SysAdminMapeo" /> 
	<menu:displayMenu name="SysAdminReportes" /> 
    
</menu:useMenuDisplayer>	

<BR><BR>
	<html:form method="post" action="ClaveReferenciaActionSysAdmin"> 
	<bean:define id="ClaveReferenciaForm" name="ClaveReferenciaForm" type="mx.com.iusacell.catalogo.web.vendedores.struts.action.ClaveReferenciaForm"/>	
	<TABLE WIDTH="750" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="mapeoVendedorForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<table width="750" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD height="40" colspan="2" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> 
		<bean:message key="mapeoVendedorForm.message.buscar.vendedores"/>
		</TD>
	</TR>
	<TR bgcolor="#F9F9F9">
		<TD WIDTH="50%" height="25"  CLASS="txtContenidoBold"><bean:message  key="mapeoVendedorForm.pcCvePuestoSeek.displayname"/> :</TD>
		<TD WIDTH="50%" height="25" ALIGN="LEFT" CLASS="PTabData">

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
	<TR BGCOLOR="#FFFFFF">
		<TD WIDTH="50%" height="25" CLASS="txtContenidoBold"><bean:message  key="mapeoVendedorForm.pcCveVendedorSeek.displayname"/> :</TD>
		<TD WIDTH="50%" height="25" ALIGN="LEFT" CLASS="PTabData">
			<html:text property="pcCveVendedorSeek" name="ClaveReferenciaForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
		</TD>
	</TR>
	<TR bgcolor="#F9F9F9">
		<TD WIDTH="50%" height="25"  CLASS="txtContenidoBold"><bean:message  key="mapeoVendedorForm.pcNomVendedorSeek.displayname"/> :</TD>
		<TD WIDTH="50%" height="25" ALIGN="LEFT" CLASS="PTabData">
			<html:text property="pcNomVendedorSeek" name="ClaveReferenciaForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
		</TD>
	</TR>
	<TR BGCOLOR="#FFFFFF">
		<TD WIDTH="50%" height="25" CLASS="txtContenidoBold">
			&nbsp;
		</TD>
		<TD WIDTH="50%" height="25" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden name="ClaveReferenciaForm" property="pcNomClaveEmpleadoReferenciaSeek" />
			
		</TD>
	</TR>
	<TR bgcolor="#FFFFFF">
			<TD height="40" colspan="2" ALIGN="CENTER" CLASS="txtContenido">
			<html:button property="boton" styleClass="boton" onclick="goToBuscarVendedor(ClaveReferenciaForm,'vendedores')"><bean:message key="mapeoVendedorForm.button.buscar.vendedores"/></html:button>
	 		</TD>
	</TR>
<logic:present name="tablaVendedores">
	<TR bgcolor="#FFFFFF">
	<TD ALIGN="LEFT" VALIGN="top" CLASS="PTabData"  colspan="2">&nbsp;
		<bean:define id="tablaVendedores" name="tablaVendedores" type="java.util.ArrayList"/>
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
				titulos="Clave,Nombre,Puesto,Fecha de Alta,Status, Division"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto,getPcFechaAltaStr,getPcStatus, getPcSubdivision"
				anchosColumnas="15%,33%,10%,18%,8%, 8%"
				alineacionesColumnas="center,center,center,center,center, center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="10%"
				radioName="pcCveVendedorRadio"
				selected='<%=getValor(request,"pcCveVendedorRadio")%>'
				camposExtras='getPcNombreCompleto=nomVend'
				agregarValorCamposExtras='true'
				script="onClick='updateBox(ClaveReferenciaForm,this)'"
			/>
	  </div>
	</TD></TR>
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="50%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			<div id="obtener" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToBuscarMapeoVendedor(ClaveReferenciaForm,'mapeos')"><bean:message key="mapeoVendedorForm.button.buscar.mapeo"/></html:button></div>
	  </TD>
		<td height="40"></td>
	</TR>
</logic:present>
<logic:present name="mapeoVendedor">
	<TR bgcolor="#FFFFFF">
	<TD ALIGN="LEFT" VALIGN="top" CLASS="PTabData"  colspan="2">&nbsp;
	<bean:define id="mapeoVendedor" name="mapeoVendedor" type="java.util.ArrayList"/>
		<div style="height:200;width:100%;overflow:scroll;visibility:visible">
			<fsj:generalHomeTableEstatico
				lista='<%= mapeoVendedor %>'	
				valueMethodName = "getPcCveCompuesta"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_MapeoVendedorVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Clave,Sistema,Cliente ID,Registro Ventas,Nombre de Usuario, Division"
				metodos="getPcCveVendedor,getPcSistema,getPcCustomerId,getPcCveRegVenta,getPcNomUsuario, getPcSubdivision"
				anchosColumnas="15%,18%,10%,8%,33%,8%"
				alineacionesColumnas="center,center,center,center,center, center, center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="10%"
				radioName="pcMapeoVendedorRadio"
				selected='<%=getValor(request,"pcMapeoVendedorRadio")%>'
				camposExtras='getPcCveVendedor=clave,getPcSistema=sistema,getPcCustomerId=custid,getPcCveRegVenta=regVen,getPcNomUsuario=usuario'
				agregarValorCamposExtras='true'
				script="onClick='updateForm(ClaveReferenciaForm,this)'"
			/>
	  </div>
	</TD></TR>
</logic:present>

<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
				<TABLE WIDTH="700" align="center">	<TR><TD CLASS="errormessagenegro" colspan="2"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6"><bean:write name="noData" /></TD></TR>
		</TABLE>
</logic:present>
</table>
<BR>
<BR>	
<TABLE WIDTH="750" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="mapeoVendedorForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>


	<TABLE WIDTH="750" align="center">	<logic:messagesPresent>
		<TR>
		  <TD height="40" colspan="2" CLASS="txtContenidoRojo"><img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7">
		<bean:message key="mapeoVendedorForm.message"/></TD>
	    </TR>
		<TR>
		  <TD height="40" colspan="2" CLASS="errormessagerojo">	      <bean:message key="errors.header"/>
		   <html:messages id="error">
		      <img src="<%= request.getContextPath()%>/images/bullet_cuadro_rojo.gif" width="6" height="6"> <bean:write name="error"/>
		   </html:messages>
		   <bean:message key="errors.footer"/>
		   
		</TD>
		</TR>
</logic:messagesPresent>
		<TR>
		  <TD>&nbsp;</TD>
		  <TD>&nbsp;</TD>
	    </TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="mapeoVendedorForm.pcCveVendedor.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveVendedor" name="ClaveReferenciaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />	
					 
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="mapeoVendedorForm.pcSistema.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcSistema" name="ClaveReferenciaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />			
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="mapeoVendedorForm.pcCustomerId.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCustomerId" name="ClaveReferenciaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="mapeoVendedorForm.pcCveRegVentas.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveRegVenta" name="ClaveReferenciaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" CLASS="txtContenidoBold"><bean:message key="mapeoVendedorForm.pcNomUsuario.displayname" /> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomUsuario" name="ClaveReferenciaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()" />
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToAgregarMapeo(ClaveReferenciaForm)"><bean:message key="mapeoVendedorForm.button.agregar"/></html:button>
		 		</TD>
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToModificar(ClaveReferenciaForm)"><bean:message key="mapeoVendedorForm.button.modificar"/></html:button>
		 		</TD>
		 		<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
			 		<html:button property="btnEliminarMapeo" styleClass="boton" onclick="goToEliminarMapeo(ClaveReferenciaForm)"  disabled="true"><bean:message key="button.eliminar"/>
			 		</html:button>

		 		</TD>
		</TR>
	</TABLE>

	<BR><BR>
	</html:form> 
</BODY>
</html:html>
