<%@ page import="mx.com.iusacell.catalogo.modelo.Pc_VendedoresVO"%>
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
%> 
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title>Iusacell:: Busqueda de Superiores</TITLE>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="JavaScript" src="../js/valida_forma.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script language="JavaScript" >
	
		</script>		
		<script language="JavaScript" src="../js/Calendario.js"></script>
	    <script type="text/javascript" src="../js/tabsPersonal.js"></script>
		<script>

	
		function goToMostrarSup(form) {
		var action = "?action=mostrarSup";
		form.action = form.action + action;
		form.submit(); 
		}
	
		function superior(valor){
			//alert(valor);
			if(document.getElementById("cveSupSelected")){
				document.getElementById("cveSupSelected").value = valor;
			}else{
				var newField = document.createElement("input");
				newField.type = "hidden";
				newField.name = "cveSupSelected";
				newField.id = "cveSupSelected";
				newField.value = valor;
				document.getElementById("buscaSup").appendChild(newField);
			}
		}
		
		function goToAsignarSup(form) {
		
			var action;
			action = "?action=consultar&control=superior";
			
			var rad=document.getElementById('cveSupSelected');
			if(rad!=null){
			if(idCheck(form.cveSup)){
					var clave= radioValue(form.cveSup);
					window.opener.document.PersonalMovForm.claveSuperior.value=clave;
					window.opener.document.PersonalMovForm.action = window.opener.document.PersonalMovForm.action + action;
					window.opener.document.PersonalMovForm.submit();
					window.close();
			  	}else
					alert("Seleccione el Superior");
			}else
				alert("Busque un Superior");
	  	}
		</script>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0">

<BR><BR>
	<html:form method="post" action="PersonalMovAction" method="POST" onsubmit="return validatePersonalForm(this);"> 
	<bean:define id="PersonalMovForm" name="PersonalMovForm" type="mx.com.iusacell.catalogo.web.personal.struts.action.PersonalMovForm"/>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>	
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3>Busqueda de Superior</h3></TD>
      </TR>
    </TABLE>
	<BR>

	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR>
	<TD>		<TABLE WIDTH="700" align="center">	<TR bgcolor="#FFFFFF">
			<TD ALIGN="CENTER" CLASS="txtContenidoRojo" colspan="2" width="67%">
			Utilice la siguiente forma para buscar al superior que le interesa.
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD ALIGN="CENTER" CLASS="txtContenido" colspan="2" width="67%">
			Si tiene el nombre o parte de él escribalo en la caja de Nombre de Superior. Si quiere puede filtrar los superiores por Puesto.
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD CLASS="txtContenidoRojo">
			Nombre del Superior a buscar :</TD>
			<TD ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomSuperiorSeek" name="PersonalMovForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD CLASS="txtContenidoRojo">
			 <bean:message key="personalForm.pcCvePuestoSeek.displayname"/> :
			</TD>
			<TD ALIGN="LEFT" CLASS="PTabData">
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
			<TD COLSPAN="2" ALIGN="CENTER" CLASS="txtContenido" width="469">
			 O si conoce la clave del superior puede buscarlo por clave utilizando la siguiente caja de texto. 
			</TD>
		</TR>
		
		<TR bgcolor="#F9F9F9">
			<TD CLASS="txtContenidoRojo">
			 Clave del Superior a buscar :
			</TD>
			<TD ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveSuperiorSeek" name="PersonalMovForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD   CLASS="txtContenidoRojo">Número de Empleado :</TD>
			<TD   ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomClaveSuperiorReferenciaSeek" name="PersonalMovForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>	
		 <TR bgcolor="#F9F9F9">
				<TD ALIGN="center" CLASS="txtContenido">
				 <html:button property="boton" styleClass="boton" onclick="goToMostrarSup(PersonalMovForm)">
				  <bean:message key="button.buscar"/>
				 </html:button>
		 		</TD>
		 		
		 		<TD ALIGN="CENTER" CLASS="txtContenido">
				 <html:button property="boton" styleClass="boton" onclick="goToAsignarSup(PersonalMovForm)">
				  Seleccionar
				 </html:button>
		 		</TD>	
		 </TR>
		 <tr><td id="buscaSup">&nbsp;</td></tr>

<logic:present name="tablaSuperiores">
<logic:present name="porConfirmar">
		<TR><TD CLASS="errormessagerojo" colspan="2">
		   <bean:message key="personalForm.errors.repetido"/>
		</TD></TR>
</logic:present>

	<bean:define id="tablaSuperiores" name="tablaSuperiores" type="java.util.ArrayList"/>
		<TR>
		 <TD CLASS="txtContenidoRojo" colspan="2">
			<div style="height:200;width:100%;overflow:scroll;visibility:visible">	
			 <fsj:generalHomeTableEstatico
				lista='<%= tablaSuperiores %>'	
				valueMethodName = "getPcCveVendedor"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SuperioresVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Clave,Nombre,Puesto,Fecha de Alta,Status, División"
				metodos="getPcClaveCompleta,getPcNombreCompleto,getPcDescPuesto,getPcFechaAltaStr,getPcStatus, getPcSubdivision"
				anchosColumnas="15%,33%,18%,10%,8%, 8%"
				alineacionesColumnas="center,center,center,center,center, center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="cveSup"
				camposExtras='getPcCveVendedor=cve,getPcNomVendedor=nomVend,getPcApePaterno=apePat,getPcApeMaterno=apeMat,getPcCvePuesto=puesto,getPcCveSuperior=superior,getPcFechaAltaStr=fchalta,getPcFechaBajaStr=fchbaja,getPcDigVerif=digito,getPcCntrTda=controlTda,getPcStatus=status,getPcCveEmpRef=empRef,getPcCiudad=ciudad,getPcEstado=estado,getPcCp=cp,getPcTel=tel,getPcFax=fax,getPcDireccion=direccion,getPcColonia=colonia,getPcCveEmpRef=empref,getPcCveEmpresa=empresa,getPcCveSubdiv=div,getPcCveHorario=horario'
				agregarValorCamposExtras='true'
				script="onClick='superior(this.value)'"
				selected='<%=getValor(request,"pcCveVendedor")%>'
			 />
		  </div>
		 </TD>
	   </TR>
	   	</table>	
	   	</TD>
	   	</TR>	   		 		
</logic:present>
			
</TABLE>
	</html:form> 
	<logic:present name="mensaje">
			<b><small><bean:write name="mensaje"/></small></b>
				
			</logic:present>	

</BODY>
</html:html>
