function updateCveRef(form,value){
			if(form.pcTipoRegistro.options[form.pcTipoRegistro.selectedIndex].value == 'interno'){
				form.pcCveEmpRef.value = value;
		  }
		}

		function changeComboForceZero(comboBox,valor,prefijo){
			comboItem  = prefijo + valor;
			hiddenItem = FIND(comboItem);
			valor = hiddenItem.value==0?"":hiddenItem.value;
			for(i=0; i< comboBox.length; i++){
				if(comboBox.options[i].value == valor) comboBox.selectedIndex = i;
			}
			return;
		}
		/*
		function updateBox(form,item){
			form.pcCveSupBase.value=item.value;	
			changeCombo(form.pcCvePuesto,item.value,'puesto');
			changeCombo(form.pcCveHorario,item.value,'horario');
			changeInput(form.pcCveSuperiorSel,item.value,'superior');
			if(form.Admin.value == 1)changeComboForceZero(form.pcCveSubdiv,item.value,'div');
			changeRadio(form.pcStatus,item.value,'status');
			changeInput(form.pcNomVendedor,item.value,'nomVend');
			changeInput(form.pcApePaterno,item.value,'apePat');
			changeInput(form.pcApeMaterno,item.value,'apeMat');
			changeInput(form.pcCiudad,item.value,'ciudad');
			changeInput(form.pcEstado,item.value,'estado');
			changeInput(form.pcCp,item.value,'cp');                       
			changeInput(form.pcTel,item.value,'tel');                      
			changeInput(form.pcFax,item.value,'fax');                     
			changeInput(form.pcDireccion,item.value,'direccion');       		
			changeInput(form.pcColonia,item.value,'colonia');    
			changeInput(form.pcCveEmpRef,item.value,'empref');    
			changeInput(form.pcFchAlta,item.value,'fchalta');
			changeInput(form.pcCveEmpresa,item.value,'empresa');
			changeDiv(item.value,'cve');
			changeDivDigito(item.value,'digito');
			
			
			if(document.forms[0].CveRol.value != "4") {
				if( window.mmIsOpera ) {
					(document.getElementById("eliminar").style.visibility = 'visible');
					(document.getElementById("baja").style.visibility = 'visible');
					(document.getElementById("division").style.visibility = 'visible');
				}
				if (document.all) {
					(document.all.eliminar.style.visibility = 'visible');
					(document.all.baja.style.visibility = 'visible');
					(document.all.division.style.visibility = 'visible');
				}
				if (document.getElementById){
					(document.getElementById("eliminar").style.visibility = 'visible');
					(document.getElementById("baja").style.visibility = 'visible');
					(document.getElementById("division").style.visibility = 'visible');
				} 
			}
			valor=form.pcCveSuperior.options[form.pcCveSuperior.selectedIndex].value;
			

			var division = 0;
			
			var action = "";
			action ="?action=horario";
			form.action = form.action + action;
			verSup();
			form.submit();
		}*/

		function verSup(){
		var clave=PersonalMovForm.pcCveSupBase.value;
		PersonalMovForm.claveSuperior.value=clave;
		}
		
		function goToTipoSelect(form,item){
			changeCombo(form.pcCveTipoCanal,item.value,'cveTipo');
		}

		function validateForm(form){
			return (validateVendedoresForm(form));
		}		
		
		function changeDiv(valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			if( window.mmIsOpera ) return(document.getElementById("CveVendedor").innerHTML = hiddenItem.value);
			if (document.all) return(document.all.CveVendedor.innerHTML = hiddenItem.value);
			if (document.getElementById) return(document.getElementById("CveVendedor").innerHTML = hiddenItem.value);
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
		
		function updateTextField(comboBox){
			if(comboBox.options[comboBox.selectedIndex].value == 'persona' || comboBox.options[comboBox.selectedIndex].value == 'tienda'){
				if( window.mmIsOpera ) {
					document.getElementById("CveVendedor").style.visibility = 'visible';
					document.getElementById("guion").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("cveDefinir").style.visibility = 'hidden';
					return;
				}
				if (document.all) {
					document.all.CveVendedor.style.visibility = 'visible';
					document.all.guion.style.visibility = 'visible';
					document.all.DigVerif.style.visibility = 'visible';
					document.all.cveDefinir.style.visibility = 'hidden';
					return;
				}
				if (document.getElementById) {
					document.getElementById("CveVendedor").style.visibility = 'visible';
					document.getElementById("guion").style.visibility = 'visible';
					document.getElementById("DigVerif").style.visibility = 'visible';
					document.getElementById("cveDefinir").style.visibility = 'hidden';
					return;
				}
			}else if ( comboBox.options[comboBox.selectedIndex].value == 'distribuidor' || comboBox.options[comboBox.selectedIndex].value == 'interno' ){
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
		}
		
		function goToAgregarAdmin(form, control) {
			var action = "";
			
		
			if (document.getElementById("optdistmaster").checked) 
					{
					form.pcDistMAster.value='M';	
				}else{
					form.pcDistMAster.value='V';	
					}
		
			var undefined;

			if(document.forms[0].pcCveSubdiv ==null || document.forms[0].pcCveSubdiv == undefined) {
				if(control == null || control==undefined){
					action = "?action=agregar";
				}else{
					action = "?action=agregar&control=" + control;
				}
				
				if(form.pcCveHorario.disabled == true){
					form.action = form.action + action;
					form.submit();
				} else {
					validarHorario(form, action);		
				}
			} else {
			
			MM_validateForma('pcNomVendedor','RisText', 'Nombre vendedor:',
							'pcApePaterno','RisText', 'Apellido Paterno:',
							'pcApeMaterno','RisText', 'Apellido Materno:',
							'pcCiudad','RisText', 'Ciudad:',
							'pcCvePuesto', 'RisCombo', 'Puesto:',
							'pcCveSuperior', 'RisCombo', 'Superior:',	
							'pcCveEmpRef', 'RisText', 'Numero Empleado:',
							'pcCveEmpresa', 'RisCombo', 'Empresa:',
							'pcFchAlta', 'RisDate', 'Fecha de Alta:',
							'pcCveSubdiv', 'RisCombo', 'Division:',
							'pcTipoComision','RisCombo','Comision:',
							'pcNivelVentas','RisCombo','Nivel de ventas:');
							
								
				
				if (document.MM_returnValue){
			
				/*if(document.forms[0].pcCveSubdiv.value == "") {
					alert("Debes de Elegir una division")
					document.forms[0].pcCveSubdiv.focus()
					return false;
				} *///else {
					if(control == null || control==undefined){
						action = "?action=agregar";
					}else{
						action = "?action=agregar&control=" + control;
					}
					
					if(form.pcCveHorario.disabled == true){
						form.action = form.action + action;
						form.submit();
					} else {
						validarHorario(form, action);
					//}
				}
			}
		}
	}
		
		
		
		function goToModificarAdmin(form) {
			
			if (document.getElementById("optdistmaster").checked) 
					{
					form.pcDistMAster.value='M';	
				}else{
					form.pcDistMAster.value='V';	
					}
		
		MM_validateForm('pcCveVendedor','RisRadio','Vendedor :',
		                'pcTipoComision','RisCombo','Comisión:',
		                'pcNivelVentas','RisCombo','Nivel de ventas: ');
			if (document.MM_returnValue){
				var fecha = validafecha(form);
				var action = "";
				if(form.pcCveHorario.disabled == true){
					if(fecha) {
						action ="?action=modificar";
						form.action = form.action + action;
						form.submit(); 
					} else
						return false;
				}
				if(form.pcCveHorario.disabled == false){
					if(form.pcCveHorario.value == 0){
						alert("Debes elegir un horario");
					} else {
						if(fecha){
							action ="?action=modificar";
							form.action = form.action + action;
							form.submit();
						}
					}
				}	
			}
		}
			//valido que la fecha de cambio sea mayor a la fecha de alta
			//valida fechas	
			function validafecha(form) {
			var bandera = true;
			
			if (document.forms[0].pcFchAlta.value == "")
				return failMsg(document.forms[0].pcFchAlta, "Debes especificar la Fecha Inicial.")
			
			if (document.forms[0].pcFchCambio.value == "")
				return failMsg(document.forms[0].pcFchCambio, "Debes especificar la Fecha de Cambio.")
			
			//valido fechas
			if(Date.parse(parseaFecha(document.forms[0].pcFchCambio.value)) < Date.parse(parseaFecha(document.forms[0].pcFchAlta.value)))
			{
				alert("La fecha Cambio debe ser mayor a la Fecha Alta")
				return false;
			}
			
			return bandera;
			
		}
		
		
		function goToHorario(form) {
				var horario = form.pcCveHorario.value;
				var action = "";
				MM_validateForm('pcCveHorario','RisCombo','Horario :');
				if (document.MM_returnValue){
					window.open("VendedoresAction.do?action=horario&pcCveHorario="+horario+"&buscar1=true",
						"BuscaSocio","width=700,height=400,status=0, menubar=0,toolbar=0, " +
						"resizable=1, top=100, left=100, scrollbars=1");
				}
		}
		
		function validarHorario(form, action) {
			if(form.pcCveHorario.disabled == false){
				if(form.pcCveHorario.value == 0){
					alert("Debes elegir un horario");
				} else{
					form.action = form.action + action;
					form.submit();
				}
			}
		}
		
		function goToActualizarComboSuperiores(){
		var newClave= document.PersonalMovForm.claveSuperior.value;
		document.PersonalMovForm.pcCveSuperior.value=newClave;
		}
		
		function goToReactivar(form) {
			
			var rad = document.getElementById('pcCveVendedor');
			if(rad!=null){
				if(idCheck(form.pcCveVendedor)){
					MM_validateForm('pcFchReact','RisText','Fecha de Reactivación:');
					if (document.MM_returnValue){
						var action = "?action=reactivar";
						form.action = form.action + action;
						if(confirm("Va realizar una operacion de Reactivación")){
							form.submit();
						}
					}
				}else{
					alert("Debe seleccionar un Vendedor para Reactivar");
				}
			}else{
				alert("Debe buscar un Vendedor para Reactivar");
			}
		 }
		 
		 function goToModificar(form) {
				var rad = document.getElementById('identif');
				if(rad!=null){
					if(idCheck(form.identif)){
						var action = "?action=Modificar";
						form.action = form.action + action;
						form.submit();
					}else{
						alert("Debe seleccionar un registro para Modificar");
					}
				}else{
					alert("Debe buscar un registro para Modificar");
				}
			}		