 <%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO"%>
<%@ page import="mx.com.iusacell.catalogo.modelo.PcMarcasVO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fsj-html.tld" prefix="fsj" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<jsp:include page="/Valida.jsp"></jsp:include>
<jsp:include page="/Icono.jsp"></jsp:include>
<%
	ArrayList marcas = (ArrayList)request.getSession().getAttribute("marcas");		
	String region = (String)request.getAttribute("cveRegion");
	String ciudad = (String)request.getAttribute("cveCiudad");
	//String estado = (String)request.getAttribute("cveEstado");
	
	String numeroRels = (String)request.getAttribute("numeroRels");
%>
<%!
	public String getValor(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		if(res != null && (res.trim().length() == 0)){
			res = null;
		}
		return res;
	}
	
	public Object[] getParameters(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		ArrayList param = new ArrayList();
		param.add(res);
		return param.toArray();	
	}

	public Object[] getParametersNull(HttpServletRequest req, String parametro){
		ArrayList param = new ArrayList();
		if(parametro == null || parametro.equalsIgnoreCase("")==true) param.add(null);
		else param.add(parametro);
		return param.toArray();	
	}

	public Object[] getParametros(String division){
		ArrayList param = new ArrayList();
		param.add(division);
		return param.toArray();	
	}		
%> 
<html:html>
<HEAD>
<html:base/>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<title><bean:message key="puntosVentaForm.title"/></TITLE>
	<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
    <link rel="stylesheet" type="text/css" media="screen" href="../css/<%= (String)request.getSession().getAttribute("estiloCss") %>.css" />   
    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    

	<script language="JavaScript" src="../js/tools.js"></script>
	<script>
	var ajaxReq;

	function getAjax(){
		var request;
		if (window.XMLHttpRequest){ //IE7 or non-IE browsers
			request = new XMLHttpRequest();
		}
		if (window.ActiveXObject){ //IE7-
			try{
				request = new ActiveXObject("MSXML2.XMLHTTP.3.0");
			}catch(E){
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		return request;
	}
	
	function cambiaRegion(division){
		ajaxReq = getAjax();
		if(ajaxReq && division != 0){
		    ajaxReq.abort();
			ajaxReq.onreadystatechange = handleRegion;
			ajaxReq.open("GET", "<%= request.getContextPath()%>/PuntosVentaAction.do?action=buscaRegion&pcCveDivision="+division, true);
			ajaxReq.send(null);
		}
		else{
			var lista = document.getElementById("pcRegion");
			lista.options.length = 1;
			lista.options[0] = new Option("-- Seleccione una division --","0",true,true);
		}
	}
		
	function cambiaCiudad(estado){
	    
		ajaxReq = getAjax();
		if(ajaxReq && estado != 0){
		    ajaxReq.abort();
			ajaxReq.onreadystatechange = handleCiudad;
			ajaxReq.open("GET", "<%= request.getContextPath()%>/PuntosVentaAction.do?action=buscaCiudad&pcCveEstado="+estado, true);
			ajaxReq.send(null);
		}
		else{
			var lista = document.getElementById("pcCiudad");
			lista.options.length = 1;
			lista.options[0] = new Option("-- Seleccione un estado --","0",true,true);
		}
	}

	function cambiaRegionInicial(division,valor,prefijo,valor2,prefijo2){
			ajaxReq = getAjax();
			var lista = document.getElementById("pcRegion");
			//var lista2 = document.getElementById("pcEstado");
		     if(ajaxReq && division != 0){
			     ajaxReq.abort();
				 ajaxReq.onreadystatechange = handleRegion;
				 ajaxReq.open("GET", "<%= request.getContextPath()%>/PuntosVentaAction.do?action=buscaRegion&pcCveDivision="+division, true);
				 ajaxReq.send(null); 
				 
		      }else{
			       lista.options.length = 1;
			       lista.options[0] = new Option("-- Seleccione una division --","0",true,true);
	        }
	     
	           setTimeout(codigo, 200);
			   
			    function codigo(){
				       var comboItem  = prefijo + valor;
				       var hiddenItem = FIND(comboItem);
				       var hiddenVal = hiddenItem.value;
				       lista.selectedIndex = 0;
				        for(i=0; i< lista.length; i++){
					        if(lista.options[i].value == hiddenVal) lista.selectedIndex = i;
		                }
		               //var comboItem2  = prefijo2 + valor2;
				       //var hiddenItem2 = FIND(comboItem2);
				       //var hiddenVal2 = hiddenItem2.value;
				       //lista2.selectedIndex = 0;
				       // for(i=0; i< lista2.length; i++){
					   //     if(lista2.options[i].value == hiddenVal2) lista2.selectedIndex = i;
		               // }
	            }      
	}
	
	function cambiaCiudadInicial(estado,valor,prefijo){
			ajaxReq = getAjax();
			var lista = document.getElementById("pcCiudad");
		     if(ajaxReq && estado != 0){
			     ajaxReq.abort();
				 ajaxReq.onreadystatechange = handleCiudad;
				 ajaxReq.open("GET", "<%= request.getContextPath()%>/PuntosVentaAction.do?action=buscaCiudad&pcCveEstado="+estado, true);
				 ajaxReq.send(null); 
				 
		      }else{
			       lista.options.length = 1;
			       lista.options[0] = new Option("-- Seleccione un estado --","0",true,true);
	        }
	     
	           setTimeout(codigo, 200);
			   
			    function codigo(){
			       var comboItem  = prefijo + valor;
			       var hiddenItem = FIND(comboItem);
			       var hiddenVal = hiddenItem.value;
			       lista.selectedIndex = 0;
			        for(i=0; i< lista.length; i++){
				        if(lista.options[i].value == hiddenVal) lista.selectedIndex = i;
	                }
	            }
	               	      
	}
			
	function handleRegion(){
		if(ajaxReq.readyState == 4 && ajaxReq.status == 200){
			var xmlDoc = ajaxReq.responseXML;
			var lista = document.getElementById("pcRegion");
			var regiones = xmlDoc.getElementsByTagName("divisiones")[0];
			var lasRegiones = regiones.getElementsByTagName("region");
			lista.options.length = 0;
	
			for(i = 0; i < lasRegiones.length; i++) {
				
				var clave = lasRegiones[i].getElementsByTagName("clave")[0].firstChild.nodeValue;
				var desc = lasRegiones[i].getElementsByTagName("descripcion")[0].firstChild.nodeValue;
				lista.options[i] = new Option(desc, clave);
			}   
			
			/*var lista1 = document.getElementById("pcEstado");
			var estados = xmlDoc.getElementsByTagName("divisiones")[0];
			var lasEstados = estados.getElementsByTagName("estado");
			lista1.options.length = 0;
	
			for(i = 0; i < lasEstados.length; i++) {
				
				var clave =lasEstados[i].getElementsByTagName("clave")[0].firstChild.nodeValue;
				var desc = lasEstados[i].getElementsByTagName("descripcion")[0].firstChild.nodeValue;
				lista1.options[i] = new Option(desc, clave);
			} */  
			        
		}	
	}
	
	
	function handleCiudad(){
		if(ajaxReq.readyState == 4 && ajaxReq.status == 200){
			var xmlDoc = ajaxReq.responseXML;
			var lista = document.getElementById("pcCiudad");
			var ciudades = xmlDoc.getElementsByTagName("ciudades")[0];
			var lasCiudades = ciudades.getElementsByTagName("ciudad");
			lista.options.length = 0;
	
			for(i = 0; i < lasCiudades.length; i++) {
				var clave = lasCiudades[i].getElementsByTagName("clave")[0].firstChild.nodeValue;
				var desc = lasCiudades[i].getElementsByTagName("descripcion")[0].firstChild.nodeValue;
				lista.options[i] = new Option(desc, clave);
			}           
		}	
	}
			
		function updateBox(form,item){
			changeInput(form.pcNomPtoventas,item.value,'nombrePto');
			changeInput(form.pcCveReferencia,item.value,'refer');
			changeInput(form.pcDireccion,item.value,'dire');
			changeInput(form.pcColonia,item.value,'col');
			changeInput(form.pcCp,item.value,'cp');
			changeInput(form.pcTel,item.value,'tel');
			changeInput(form.pcFax,item.value,'fax');
			changeCombo(form.pcCntlInt,item.value,'controlInt');
			changeCombo(form.pcCveSubdiv,item.value,'div');
			var div	= document.getElementById('pcCveSubdiv').value;
			cambiaRegionInicial(div,item.value,'region');
			changeCombo(form.pcCveTipoCanal,item.value,'tpCanal');
			changeCombo(form.pcCveCanal,item.value,'canal');
			changeCombo(form.pcEstado,item.value,'estado');
			var est = document.getElementById('pcEstado').value;
			cambiaCiudadInicial(est,item.value,'ciudad');
			changeDivDigito(item.value,'digito');
			changeClave(item.value,'clave');
			changeStatus(item.value,'status');
			changeInput(form.pcFchIniOp,item.value,'inicio');
			changeInput(form.pcFchBaja,item.value,'baja');
			changeInput(form.pcFchReactvcn,item.value,'reactiva');			

			var estatus = getStatus(item.value,'status');
			   if( estatus == "BAJA"){
			     if(document.forms[0].CveRol.value != "4") {
				    if(window.mmIsOpera) 
					   document.getElementById("reactivar").style.visibility = 'visible';
					   document.getElementById("eliminar").style.visibility = 'hidden';
					
				 if(document.all) 
					document.all.reactivar.style.visibility = 'visible';
					document.all.eliminar.style.visibility = 'hidden';
				
				 if(document.getElementById) 
					document.getElementById("reactivar").style.visibility = 'visible';
					document.getElementById("eliminar").style.visibility = 'hidden';
			  }
			}else{
			   if(document.forms[0].CveRol.value != "4") {
				  if( window.mmIsOpera ) 
				  	  document.getElementById("eliminar").style.visibility = 'visible';
				  	  document.getElementById("reactivar").style.visibility = 'hidden';
					
				  if(document.all) 
					 document.all.eliminar.style.visibility = 'visible';
					 document.all.reactivar.style.visibility = 'hidden';
				
				  if(document.getElementById) 
					 document.getElementById("eliminar").style.visibility = 'visible';
					 document.getElementById("reactivar").style.visibility = 'hidden';
			   }
		    }	
			
			if(document.forms[0].pcTipoRegistro.value == "MAN") {
				
				document.forms[0].pcTipoRegistro.value = "AUT";
				
				if( window.mmIsOpera ) {
					document.getElementById("DigVerifManual").style.visibility = 'hidden';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("ClaveManual").style.visibility = 'hidden';
					document.getElementById("Clave").style.visibility = 'visible';
					document.getElementById("Status").style.visibility = 'visible';
					return;
				}
				if (document.all) {
					document.all.DigVerifManual.style.visibility = 'hidden';
					document.all.DigVerif.style.visibility = 'visible';
					document.all.ClaveManual.style.visibility = 'hidden';
					document.all.Clave.style.visibility = 'visible';
					document.all.Status.style.visibility = 'visible';
					return;
				}
				if (document.getElementById) {
					document.getElementById("DigVerifManual").style.visibility = 'hidden';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("ClaveManual").style.visibility = 'hidden';
					document.getElementById("Clave").style.visibility = 'visible';
					document.getElementById("Status").style.visibility = 'visible';
					return;
				}
				
			}
			
		}
		
		function goToTipoSelect(form,item){
			changeCombo(form.pcCveTipoCanal,item.value,'cveTipo');
		}

		function validateForm(form){
			return (validatePuntosVentaForm(form));
		}
		
		function changeClave(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("Clave").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.Clave.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("Clave").innerHTML = hiddenItem.value);
			return false; 
		}
		
		function changeDivDigito(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("DigVerif").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.DigVerif.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("DigVerif").innerHTML = hiddenItem.value);
			return false; 
		}
		
		function changeStatus(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("Status").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.Status.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("Status").innerHTML = hiddenItem.value);
			return false; 
		}
		
		function getStatus(valor, prefijo){
		   itemName = prefijo + valor;
		   hiddenItem      = FIND(itemName);
		   return hiddenItem.value;
		}
		
		function updateTextField(comboBox){
			if(comboBox.options[comboBox.selectedIndex].value == "AUT"){
				if( window.mmIsOpera ) {
					document.getElementById("DigVerifManual").style.visibility = 'hidden';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("ClaveManual").style.visibility = 'hidden';
					document.getElementById("Clave").style.visibility = 'visible';
					document.getElementById("Status").style.visibility = 'visible';
					return;
				}
				if (document.all) {
					document.all.DigVerifManual.style.visibility = 'hidden';
					document.all.DigVerif.style.visibility = 'visible';
					document.all.ClaveManual.style.visibility = 'hidden';
					document.all.Clave.style.visibility = 'visible';
					document.all.Status.style.visibility = 'visible';
					return;
				}
				if (document.getElementById) {
					document.getElementById("DigVerifManual").style.visibility = 'hidden';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("ClaveManual").style.visibility = 'hidden';
					document.getElementById("Clave").style.visibility = 'visible';
					document.getElementById("Status").style.visibility = 'visible';
					return;
				}
			} else if ( comboBox.options[comboBox.selectedIndex].value == "MAN"){
				var undefined;		
				document.forms[0].pcCvePtoventas.value = "";
				document.forms[0].pcDigVerif.value = "";
				document.forms[0].pcCveSubdiv.value = "";
				document.forms[0].pcCveTipoCanal.value = "";
				document.forms[0].pcCveCanal.value = "";
				document.forms[0].pcNomPtoventas.value = "";
				document.forms[0].pcCntlInt.value = "";
				document.forms[0].pcCveReferencia.value = "";
				document.forms[0].pcDireccion.value = "";
				document.forms[0].pcColonia.value = "";
				document.forms[0].pcCp.value = "";
				document.forms[0].pcTel.value = "";
				document.forms[0].pcFax.value = "";
				document.forms[0].pcCiudad.value = "";
				document.forms[0].pcEstado.value = "";
				
				if( window.mmIsOpera ){
				 	document.getElementById("DigVerifManual").style.visibility = 'visible';
				 	document.getElementById("DigVerif").style.visibility = 'hidden';
				 	document.getElementById("ClaveManual").style.visibility = 'visible';
				 	document.getElementById("Clave").style.visibility = 'hidden';
				 	document.getElementById("Status").style.visibility = 'hidden';
				 	return;
				}
				if (document.all) {
					document.all.DigVerifManual.style.visibility = 'visible';
					document.all.DigVerif.style.visibility = 'hidden';
					document.all.ClaveManual.style.visibility = 'visible';
					document.all.Clave.style.visibility = 'hidden';
					document.all.Status.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("DigVerifManual").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'hidden';
					document.getElementById("ClaveManual").style.visibility = 'visible';
					document.getElementById("Clave").style.visibility = 'hidden';
					document.getElementById("Status").style.visibility = 'hidden';
					return;
				}
			}
		}
		
		function tipoOperacion() {
		
			<logic:present name="mensaje">
				alert('<bean:write name="mensaje"/>');
			</logic:present>
<%
  if(numeroRels!=null){
%>
            var relaciones = <%= numeroRels %>;
            var action = "?action=eliminar";
		    PuntosVentaForm.action = PuntosVentaForm.action + action;
            if(confirm("El punto de venta todavía tiene "+relaciones +" relaciones, si lo da de baja estas se borrarán \n ¿Desea darlo de baja de todas maneras?")){
            <% request.getSession().setAttribute("confirmar",""); %>
			   PuntosVentaForm.submit();
			}else{			
			<% request.removeAttribute("numeroRels");%>
			}
   
<%}%>   
			if(document.forms[0].pcTipoRegistro.value == "MAN") {
				if( window.mmIsOpera ){
				 	document.getElementById("DigVerifManual").style.visibility = 'visible';
				 	document.getElementById("DigVerif").style.visibility = 'hidden';
				 	document.getElementById("ClaveManual").style.visibility = 'visible';
				 	document.getElementById("Clave").style.visibility = 'hidden';
				 	return;
				}
				if (document.all) {
					document.all.DigVerifManual.style.visibility = 'visible';
					document.all.DigVerif.style.visibility = 'hidden';
					document.all.ClaveManual.style.visibility = 'visible';
					document.all.Clave.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("DigVerifManual").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'hidden';
					document.getElementById("ClaveManual").style.visibility = 'visible';
					document.getElementById("Clave").style.visibility = 'hidden';
					return;
				}
			}
		}
		
		function goToAgregarMarca(form){
			MM_validateForm('pcCveMarca','RisCombo','Marca :');
	    	if (document.MM_returnValue){
		    	var action = "?action=agregarMarca";
				form.action = form.action + action;
				form.submit();
	    	}
 
		}
		
		function goToEliminarMarca(form){
		
				if (form.marcasCombo.selectedIndex <= -1){
					 alert("Seleccione una Marca a Quitar");
				}else{
			    	var action = "?action=eliminarMarca";
					form.action = form.action + action;
					form.submit();
				 }
		}
					
		</script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>
		<script type="text/javascript" src="../js/tabs.js"></script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
		<script>history.forward(1)</script>
</HEAD>

<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" onload="tipoOperacion()" class="puntosVentaForma" >

<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home" />
    <menu:displayMenu name="Vendedores" /> 
    <menu:displayMenu name="Personal" /> 
    <menu:displayMenu name="PuntosVenta" /> 
    <menu:displayMenu name="Relaciones" /> 
    <menu:displayMenu name="Reportes" /> 
    <menu:displayMenu name="Auxiliares" />
<logic:present name="Admin" >
    <menu:displayMenu name="Configuracion" />
</logic:present>     
</menu:useMenuDisplayer>	

<BR><BR>
	<html:form  action="PuntosVentaAction" method="POST"> 
	<bean:define id="PuntosVentaForm" name="PuntosVentaForm" type="mx.com.iusacell.catalogo.web.puntosventa.struts.action.PuntosVentaForm"/>
	<bean:define id="divisionUsuario" name="USER" type="mx.com.iusacell.catalogo.modelo.UserSessionVO"/>
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="puntosVentaForm.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>

	<TABLE WIDTH="700" align="center">	
	<TR bgcolor="#FFFFFF">
			<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
			<bean:message key="puntosVentaForm.message.buscar"/>
			</TD>
	  </TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveDivisionSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
	
			<fsj:comboGeneral
		    	name = "pcCveDivSeek"
				queryId="<%=Q.SUBDIVISION_ALL%>"				
				valueMethodName = "getPcCveSubdiv"
				descrMethodName = "getPcDescSubdiv"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveDivSeek")%>' 
				textoNoSeleccion = "-- Seleccione una División --"
				valorNoSeleccion = ""
				parametrosQuery='<%= getParametros(String.valueOf(divisionUsuario.getPcCveSubdiv()))%>'
			/>
		
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveTipoCanalSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
		    	name = "pcCveTipoCanalSeek"
				queryId="<%=Q.TIPO_CANALES_SELECTED%>"				
				valueMethodName = "getPcCveTpCanal"
				descrMethodName = "getPcDescTpCanal"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Tipo_CanalVO.class%>"
				size = "1"
				classHtml = "input1"
				selected='<%=getValor(request,"pcCveTipoCanalSeek")%>' 
				textoNoSeleccion = "-- Seleccione un Tipo de Canal --"
				valorNoSeleccion = ""
				script='onChange=goToDynaBuscar(PuntosVentaForm,"tipo_canal_seek")'
			/> 
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveCanalSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcCveCanalSeek"
					valueMethodName = "getPcCveCanal"
					descrMethodName = "getPcDescCanal"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveCanalSeek")%>' 
					textoNoSeleccion = "-- Seleccione un Canal --"
					valorNoSeleccion = ""
					queryId="<%=Q.CANALES_X_TIPO%>"	
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_CanalVO.class%>"
					camposExtras='getPcDescCanal=descCanal,getPcCveTpCanal=cveTipo'
					agregarValorCamposExtras='true'
					parametrosQuery='<%= getParameters(request,"pcCveTipoCanalSeek")%>'			
				/>
			</TD>	
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcNomPtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventasSeek" name="PuntosVentaForm" maxlength="40" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCvePtoventasSeek.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCvePtoventasSeek" name="PuntosVentaForm" maxlength="10" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%" height="25" CLASS="txtContenidoBold"><bean:message key="puntosVentaForm.pcCveNumEconomico.displayname"/> :</TD>
			<TD WIDTH="40%" height="25" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveNumEconomico" name="PuntosVentaForm" maxlength="10" size="30" value="" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<html:button property="boton" styleClass="boton" onclick="goToBuscarPtoVta(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.buscar"/></html:button>
		 		</TD>
				<td></td>
		 </TR>
<logic:present name="tablaPuntosVenta">
		<bean:define id="tablaPuntosVenta" name="tablaPuntosVenta" type="java.util.ArrayList"/>
		<TR><TD CLASS="txtContenidoRojo" colspan="2">
			<div class="pTabla">
			<fsj:generalHomeTableEstatico
				lista='<%= tablaPuntosVenta %>'	
				valueMethodName = "getPcCvePtoventas"
				VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO.class%>"
				border="1"
				width="100%"
				cellpadding="0"
				cellspacing="0"
				cssColorEncabezado="td_mensajes"
				titulos="Cve Punto Venta, Dig. Verif., Num.Eco,Punto Venta,Canal,Tipo Canal,División,Jefe Tienda, Status"
				metodos="getPcCvePtoventas,getPcDigVerif,getPcCveReferencia,getPcNomPtoventas,getPcDescCanal,getPcDescTpCanal,getPcDescSubdiv,getPcJefeTienda, getPcStatus"
				anchosColumnas="12%,12%,13%,13%,13%,13%,13%,13%, 10"
				alineacionesColumnas="center,center,center,center,center,center,center,center,center"
				altoRenglon="1"
				cssColorRenglon1="td_gris_tablas"
				cssColorRenglon2="td_blanco_tablas"
				witdhColumnRadio="8%"
				radioName="pcCvePtoventas"
				script="onClick='updateBox(PuntosVentaForm,this)'"
				camposExtras='getPcCvePtoventas=clave,getPcCveCiudad=ciudad,getPcCveEstado=estado,getPcStatus=status,getPcDigVerif=digito,getPcNomPtoventas=nombrePto,getPcCveCanal=canal,getPcCveTpCanal=tpCanal,getPcCveRegion=region,getPcCveReferencia=refer,getPcCntlInt=controlInt,getPcCveSubdiv=div,getPcDireccion=dire,getPcColonia=col,getPcCp=cp,getPcTel=tel,getPcFax=fax,getPcFchIniOp=inicio,getPcFchBaja=baja,getPcFchReactvcn=reactiva'
				agregarValorCamposExtras='true'
				selected='<%=getValor(request,"pcCvePtoventas")%>' 
			/></div>
			</TD>
		</TR>
		 <TR bgcolor="#FFFFFF">
				<TD WIDTH="40%" height="40" ALIGN="CENTER" CLASS="txtContenido">
				<div id="eliminar"  style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToEliminar(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.eliminar"/></html:button></div>
				<div id="reactivar" style="visibility:hidden"><html:button property="boton" styleClass="boton" onclick="goToReactivarPtoVenta(PuntosVentaForm)">Reactivar</html:button></div>
 		   </TD>
		   <td></td>
		 </TR>
</logic:present>
<logic:present name="noData">
		<bean:define id="noData" name="noData" type="java.lang.String"/>
		<TR><TD height="30" colspan="2" CLASS="errormessagenegro"> <img src="<%= request.getContextPath()%>/images/bullet_cuadro_negro.gif" width="6" height="6">
		 		<bean:write name="noData" />
		 	</TD>
		</TR>
</logic:present>
		
</TABLE>
				
	
	
	
<BR><BR>	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3><bean:message key="puntosVentaForm.detalle.header"/></h3></TD>
      </TR>
    </TABLE>
	<BR>
	<table width="80%" cellpadding="0" cellspacing="0" border="0" align="center">
	<TR bgcolor="#FFFFFF">
		<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido" colspan="2">
		<bean:message key="puntosVentaForm.message"/>
		</TD>
	</TR>
<logic:messagesPresent>
		<TR>
			<TD CLASS="errormessagerojo" colspan="2">
			   <bean:message key="errors.header"/>
			   <H1 CLASS="errormessagerojo">ERROR</H1>
			   <html:messages id="error">
			      <img src="<%= request.getContextPath()%>/images/f_roja.gif" width="13" height="7"> <bean:write name="error"/>
			   </html:messages>
			   <bean:message key="errors.footer"/>
			   <hr>
			</TD>
		</TR>
</logic:messagesPresent>	
	
		<TD>
			<TABLE WIDTH="700" align="center">	
			<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcTipoRegistro.displayname"/> : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:select property="pcTipoRegistro" name="PuntosVentaForm" onchange="updateTextField(this)" >
					<html:option key="puntosVentaForm.pcTipoRegistro.radio.aut.displayname" value="AUT"/>
					<html:option key="puntosVentaForm.pcTipoRegistro.radio.man.displayname" value="MAN"/>
				</html:select>
			</TD>
		</TR>
		
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCvePtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				
				<span ID="Clave" style="visibility:visible">
					<bean:write property="pcCvePtoventas" name="PuntosVentaForm"/>
				</span>
				<span ID="ClaveManual" style="visibility:hidden">
					<html:text property="pcCvePtoventas" name="PuntosVentaForm"  maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()"/>
								 	
				</span>
			</TD>
			
		</TR> 
		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcDigito.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<span ID="DigVerif" style="visibility:visible">
					<bean:write property="pcDigVerif" name="PuntosVentaForm"/>
				</span>
				<span ID="DigVerifManual" style="visibility:hidden">
					<html:text property="pcDigVerif" name="PuntosVentaForm" maxlength="5" size="2" onchange="this.value=this.value.toUpperCase()"/>
				</span>
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCveTipoCanal.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcCveTipoCanal"
					queryId="<%=Q.TIPO_CANALES_ALL%>"				
					valueMethodName = "getPcCveTpCanal"
					descrMethodName = "getPcDescTpCanal"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_Tipo_CanalVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveTipoCanal")%>' 
					textoNoSeleccion = "-- Seleccione --"
					valorNoSeleccion = ""
					script='onChange=goToDynaBuscar(PuntosVentaForm,"tipo_canal")'
				/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCveCanal.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcCveCanal"
					valueMethodName = "getPcCveCanal"
					descrMethodName = "getPcDescCanal"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveCanal")%>' 
					textoNoSeleccion = "-- Seleccione --"
					valorNoSeleccion = ""
					queryId="<%=Q.CANALES_ALL%>"	
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_CanalVO.class%>"
					camposExtras='getPcDescCanal=descCanal,getPcCveTpCanal=cveTipo'
					agregarValorCamposExtras='true'
					parametrosQuery='<%= getParametersNull(request,PuntosVentaForm.getPcCveTipoCanal())%>'			
					script='onChange=goToTipoSelect(PuntosVentaForm,this)'
				/>
			</TD>
		</TR>
		<TR bgcolor="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCveDivision.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
		    		name = "pcCveSubdiv"
					queryId="<%=Q.SUBDIVISION_ALL%>"				
					valueMethodName = "getPcCveSubdiv"
					descrMethodName = "getPcDescSubdiv"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveSubdiv")%>' 
					textoNoSeleccion = "-- Seleccione --"
					valorNoSeleccion = "0"
					script="onChange='cambiaRegion(this.value)'"	
					parametrosQuery='<%= getParametros(String.valueOf(divisionUsuario.getPcCveSubdiv()))%>'
				/>
			</TD>
		</TR>
	   <logic:notPresent name="cveRegion" >
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Region:</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<select name="pcRegion" id="pcRegion">
					<option selected="selected">-- Seleccione una division --</option>
				</select>
			</TD>
		</TR>
		</logic:notPresent>
		<logic:present name="cveRegion" > 
		 <TR bgcolor="#F9F9F9">
		   <TD WIDTH="10%"  CLASS="txtContenidoRojo">Region:</TD>
		   <TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
		    		name = "pcRegion"
					queryId="<%=Q.REGION_COMERCIAL%>"				
					valueMethodName = "getPcCveRegion"
					descrMethodName = "getPcDescRegion"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.Pc_SubdivisionVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%= region %>' 
					textoNoSeleccion = "-- Seleccione una division --"
					valorNoSeleccion = "0"
					parametrosQuery='<%= getParameters(request,"pcCveSubdiv")%>'
				/>
			</TD>
		 </TR>
		</logic:present>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcNomPtoventas.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcNomPtoventas" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
				<logic:present name="detallePuntoVenta">
					<bean:define id="detallePuntoVenta" name="detallePuntoVenta" type="mx.com.iusacell.catalogo.modelo.Pc_Punto_VentasVO"/>
					<html:hidden property="pcCvePtoventas" name="PuntosVentaForm" value="<%= String.valueOf(detallePuntoVenta.getPcCvePtoventas()) %>"/>
				</logic:present>				
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Clasificación :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:select property="pcCntlInt">
					<html:option value="0">0</html:option>
					<html:option value="1">1</html:option>
					<html:option value="2">2</html:option>
				</html:select>
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCveReferencia.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCveReferencia" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>

		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcDireccion.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcDireccion" name="PuntosVentaForm" maxlength="80" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
				
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcColonia.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcColonia" name="PuntosVentaForm" maxlength="40" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCp.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcCp" name="PuntosVentaForm" maxlength="5" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcTel.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcTel" name="PuntosVentaForm" maxlength="12" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		
		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcFax.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:text property="pcFax" name="PuntosVentaForm" maxlength="12" size="30" onchange="this.value=this.value.toUpperCase()"/>
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcEstado.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
		    		name = "pcEstado"
					queryId="<%=Q.ESTADO_COMERCIAL%>"			
					valueMethodName = "getPcCveEstado"
					descrMethodName = "getPcDescEstado"
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcEstadosVO.class%>"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcEstado")%>' 
					textoNoSeleccion = "-- Seleccione  --"
					valorNoSeleccion = ""
					script="onChange='cambiaCiudad(this.value)'"
				/>
			</TD>
		</TR>
		<TR bgcolor="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo"><bean:message key="puntosVentaForm.pcCiudad.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<html:hidden property="pcDescCiudad" name="PuntosVentaForm" />		
				<logic:notPresent name="cveCiudad" >
					<fsj:comboGeneral
			    		name = "pcCiudad"
						queryId="<%=Q.TODAS_CIUDADES_COMERCIALES%>"				
						valueMethodName = "getPcCveCiudad"
						descrMethodName = "getPcDescCiudad"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcEstadosVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%= ciudad %>' 
						textoNoSeleccion = "-- Seleccione un estado --"
						valorNoSeleccion = "0"
						parametrosQuery='<%= getParameters(request,"pcEstado")%>'
					/>
				</logic:notPresent>
				<logic:present name="cveCiudad" > 
					<fsj:comboGeneral
			    		name = "pcCiudad"
						queryId="<%=Q.CIUDAD_COMERCIAL%>"				
						valueMethodName = "getPcCveCiudad"
						descrMethodName = "getPcDescCiudad"
						VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcEstadosVO.class%>"
						size = "1"
						classHtml = "input1"
						selected='<%= ciudad %>' 
						textoNoSeleccion = "-- Seleccione una ciudad --"
						valorNoSeleccion = "0"
						parametrosQuery='<%= getParameters(request,"pcEstado")%>'
					/>
				</logic:present>
			</TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
		  <TD WIDTH="10%"  CLASS="txtContenidoRojo">Fecha de Alta: </TD>
		  <TD>
		   <html:text property="pcFchIniOp" name="PuntosVentaForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()" readonly="true" />
		   <a href="javascript:show_calendar('PuntosVentaForm.pcFchIniOp','','','DD/MM/YYYY','es')">
		    <img src="<%= request.getContextPath()%>/images/calendario.gif" alt="Haz click en el Calendario para elegir la fecha" width="14" height="16" border="0" id="imgCalendario" > 
		   </a>
		  </TD>
		</TR>	
		<TR BGCOLOR="#FFFFFF">
		  <TD WIDTH="10%"  CLASS="txtContenidoRojo">Fecha de Baja: </TD>
		  <TD>
		   <html:text property="pcFchBaja" name="PuntosVentaForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()" readonly="true"/>
		  </TD>
		</TR>
		<TR BGCOLOR="#F9F9F9">
		  <TD WIDTH="10%"  CLASS="txtContenidoRojo">Fecha de Reactivación: </TD>
		  <TD>
		   <html:text property="pcFchReactvcn" name="PuntosVentaForm" maxlength="10" size="10" onchange="this.value=this.value.toUpperCase()" readonly="true"/>
		  </TD>
		</TR>
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">Marca : </TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
				<fsj:comboGeneral
			    	name = "pcCveMarca"
					valueMethodName = "getPcCveMarca"
					descrMethodName = "getPcDescMarca"
					size = "1"
					classHtml = "input1"
					selected='<%=getValor(request,"pcCveMarca")%>' 
					textoNoSeleccion = "-- Seleccione una Marca --"
					valorNoSeleccion = ""
					queryId="<%=Q.MARCAS_ALL%>"	
					VOClass = "<%=mx.com.iusacell.catalogo.modelo.PcMarcasVO.class%>"
				/>
			</TD>
			
			</TR>	
			
        <tr> 
         
         
        <table width="40%" border="0" cellspacing="0">
        <tr> 
          <td width="131" height="27" valign="middle" align="center"> 
            <table width="100%" border="0" cellspacing="0" cellpadding="2">
              <tr> 
                <td valign="middle"> 
                  <div align="left"> 
                                 <table width="40" border="0" cellspacing="0" cellpadding="0" height="26">
                                    <tr> 
	        						  	<TD WIDTH="7%" ALIGN="CENTER" CLASS="txtContenido">
											<html:button property="boton" styleClass="boton" onclick="goToAgregarMarca(PuntosVentaForm)">Agregar Marca</html:button>
										</TD>
                                    </tr>
                                 </table>
                  </div>
                </td>
              </tr>
              <tr> 
                <td valign="middle"> 
                  <div align="left"> 
                                 <table width="40" border="0" cellspacing="0" cellpadding="0" height="26">
                                    <tr> 
						  				<TD WIDTH="8%" ALIGN="CENTER" CLASS="txtContenido">
											<html:button property="boton" styleClass="boton" onclick="goToEliminarMarca(PuntosVentaForm)">Eliminar Marca</html:button>
										</TD>
                                    </tr>
                                 </table>
                  </div>
                </td>
              </tr>
            </table>
          </td>
          <td height="27" width="100"> 
            <div align="center"> 
            <select name="marcasCombo" size="5">
            <logic:present name="marcas">
            <% for(int i=0;i<=marcas.size()-1;i++){
						PcMarcasVO marca = new PcMarcasVO();
						marca = (PcMarcasVO)marcas.get(i);	%>				
					<option value=" <%= marca.getPcCveMarca()%>"><%= marca.getPcDescMarca()%></option>
			<%}%>
			</logic:present>
            </select>
            </div>
          </td>
        </tr>
      </table>	
		
		<TR BGCOLOR="#FFFFFF">
			<TD WIDTH="10%"  CLASS="txtContenidoRojo">
				<bean:message key="puntosVentaForm.pcStatus.displayname"/> :</TD>
			<TD WIDTH="40%" ALIGN="LEFT" CLASS="PTabData">
					<span ID="Status" style="visibility:visible">
					<bean:write property="pcStatus" name="PuntosVentaForm"/>
				</span>
			</TD>
		</TR>
		
		<logic:notEqual name="divisionUsuario" property="pcCveRol" value="4">
			<input type="hidden" name="CveRol" value="0">
			<TR bgcolor="#F9F9F9">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
					<html:button property="boton" styleClass="boton" onclick="goToAgregarPtoVenta(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.agregar"/></html:button>
				</TD>
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
					<html:button property="boton" styleClass="boton" onclick="goToModificarPtoVenta(PuntosVentaForm)"><bean:message key="puntosVentaForm.button.modificar"/></html:button>
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
	</TD>
	</table>
	<BR><BR>
	<html:hidden property="pcCveRegion" name="PuntosVentaForm" />

</html:form> 
</BODY>
</html:html>
