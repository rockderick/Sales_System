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



//Handlers
function changeTextPersonal(campo, nodo){
	if(nodo && nodo.nodeValue!=null){
		campo.value = nodo.nodeValue
	}
}

function limpiar(){
        var form = document.forms[0];
        var temp = "";
        form.pcNomVendedor.value = temp;
        form.pcApePaterno.value = temp;
		form.pcApeMaterno.value = temp;
		form.pcCiudad.value = temp;
		form.pcEstado.value = temp;
		form.pcDireccion.value = temp;
		form.pcColonia.value = temp;
		form.pcCp.value = temp;
		form.pcTel.value = temp;
		form.pcFax.value = temp;
		form.pcCveEmpRef.value = temp;
		form.pcFchAlta.value = temp;
		form.pcFchBaja.value = temp;
        form.pcCvePuesto.selectedIndex = 0;
		form.pcCveSuperior.selectedIndex = 0;
		form.pcCveHorario.selectedIndex = 0;
		form.pcCveEmpresa.selectedIndex = 0;
		if(form.pcCveSubdiv)
			form.pcCveSubdiv.selectedIndex = 0;
		form.pcTipoComision.selectedIndex = 0;
		form.pcNivelVentas.selectedIndex = 0;
}

function handleVendedor(){
	if(ajaxReq.readyState == 4 && ajaxReq.status == 200){
		var xmlDoc = ajaxReq.responseXML;
		var form = document.forms[0];
		var root = xmlDoc.getElementsByTagName("vendedor")[0];
		var clave = root.getElementsByTagName("clave")[0].firstChild.nodeValue;
		//Spans
		document.getElementById("CveVendedor").innerHTML = root.getElementsByTagName("claveOta")[0].firstChild.nodeValue;
		document.getElementById("guion").innerHTML = "";    
		document.getElementById("pcCveVendedorChg").value = clave;
		document.getElementById("pcCveVendedorVal").value = clave;
		if(document.getElementById("pcCveVendedorSelected")){
			document.getElementById("pcCveVendedorSelected").value = clave;
			document.getElementById("pcCveVendedorVal").value = clave;
		}else{
			var newField = document.createElement("input");
			newField.type = "hidden";
			newField.name = "pcCveVendedorSelected";
			newField.id = "pcCveVendedorSelected";
			newField.value = clave;
			document.getElementById("CveVendedor").appendChild(newField);
		}
		//Texts
		changeTextPersonal(form.pcNomVendedor, root.getElementsByTagName("nombre")[0].firstChild);
		changeTextPersonal(form.pcApePaterno, root.getElementsByTagName("paterno")[0].firstChild);
		changeTextPersonal(form.pcApeMaterno, root.getElementsByTagName("materno")[0].firstChild);
		changeTextPersonal(form.pcCiudad, root.getElementsByTagName("ciudad")[0].firstChild);
		changeTextPersonal(form.pcEstado, root.getElementsByTagName("estado")[0].firstChild);
		changeTextPersonal(form.pcDireccion, root.getElementsByTagName("direccion")[0].firstChild);
		changeTextPersonal(form.pcColonia, root.getElementsByTagName("colonia")[0].firstChild);
		changeTextPersonal(form.pcCp, root.getElementsByTagName("cp")[0].firstChild);
		changeTextPersonal(form.pcTel, root.getElementsByTagName("telefono")[0].firstChild);
		changeTextPersonal(form.pcFax, root.getElementsByTagName("fax")[0].firstChild);
		changeTextPersonal(form.pcCveEmpRef, root.getElementsByTagName("numero")[0].firstChild);
		changeTextPersonal(form.pcFchAlta, root.getElementsByTagName("fechaAlta")[0].firstChild);
		changeTextPersonal(form.pcFchBaja, root.getElementsByTagName("fechaBaja")[0].firstChild);
		//Combos
		changeComboPersonal(form.pcCvePuesto, root.getElementsByTagName("cvePuesto")[0].firstChild.nodeValue,null);
		changeComboPersonal(form.pcCveSuperior, root.getElementsByTagName("cveSuperior")[0].firstChild.nodeValue, root.getElementsByTagName("nomSuperior")[0].firstChild.nodeValue);
		changeComboPersonal(form.pcCveHorario, root.getElementsByTagName("horario")[0].firstChild.nodeValue,null);
		changeComboPersonal(form.pcCveEmpresa, root.getElementsByTagName("empresa")[0].firstChild.nodeValue,null);
		if(form.pcCveSubdiv)
			changeComboPersonal(form.pcCveSubdiv, root.getElementsByTagName("cveDivision")[0].firstChild.nodeValue,null);
		changeComboPersonal(form.pcTipoComision, root.getElementsByTagName("tipoComision")[0].firstChild.nodeValue,null);
		changeComboPersonal(form.pcNivelVentas, root.getElementsByTagName("nivelVentas")[0].firstChild.nodeValue,null);
		
		//Habilita el combo horario
		if(root.getElementsByTagName("habHorario")[0].firstChild.nodeValue == 'N'){
			form.pcCveHorario.disabled = true;
		}else{
		    form.pcCveHorario.disabled = false;
		}
		// Desabilita los campos nombre y numero de empleado para personal Interno
		if(root.getElementsByTagName("tipoVendedor")[0].firstChild.nodeValue == 'I'){
			form.pcNomVendedor.readOnly = "readonly";
			form.pcApePaterno.readOnly = "readonly";
			form.pcApeMaterno.readOnly = "readonly";
			form.pcCveEmpRef.readOnly = "readonly";
			form.pcNomVendedor.title = "No puede actualizar (Num. Empleado/Nombre Vendedor) en Personal Interno";
			form.pcApePaterno.title = "No puede actualizar (Num. Empleado/Nombre Vendedor) en Personal Interno";
			form.pcApeMaterno.title = "No puede actualizar (Num. Empleado/Nombre Vendedor) en Personal Interno";
			form.pcCveEmpRef.title = "No puede actualizar (Num. Empleado/Nombre Vendedor) en Personal Interno";
		}			
	}
}

function handleBuscaSup(){
	if(ajaxReq.readyState == 4 && ajaxReq.status == 200){
		var xmlDoc = ajaxReq.responseXML;
		var comboSup = document.forms[0].pcCveSuperior;
		comboSup.length = 1;
		comboSup.options[0] = new Option("-- Seleccione un nombre --", "0");
		//Llenado del combo
		var root = xmlDoc.getElementsByTagName("superiores")[0];
		var losSuperiores = root.getElementsByTagName("superior");
		var e, clave, nombre;

		for(var i = 0; i < losSuperiores.length; i++){
			e = losSuperiores[i];
			clave = e.getElementsByTagName("clave")[0].firstChild.nodeValue;
			nombre = e.getElementsByTagName("nombre")[0].firstChild.nodeValue;
			comboSup.options[i+1] = new Option(nombre, clave);
		}
		//alert("Superiores encontrados: "+comboSup.length);
	}
}