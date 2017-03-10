// std_button.js

// newFunction
	

		function goToEliminar(form,control) {
			var action ="";
		//	var undefined=null;
		//	if(validateForm(form)==true){
				if(control == null || control==undefined){
					action = "?action=eliminar";
					form.action = form.action + action;
					if(confirm("Va a eliminar un registro de la base")){
						form.submit();
					}
				}else{
					action = "?action=eliminar&control=" + control;
					form.action = form.action + action;
					if(confirm("Va realizar una operacion de " + control)){
						form.submit();
					}
				}
		//	} else action = null;
		}
		
		function goToMyEliminar(form,control) {
			var action ="";
			//var user = document.getElementById("ds_Configuracion").value;
			//var key  = document.getElementById("ds_Valor").value;
			var user = form.ds_Configuracion.value;
			var key  = form.ds_Valor.value;
			if(user == ('4DMIN01') || user == ('4DMIN02') || user == ('4DMIN03') && key == 'SYSIUSACELL'){
			   document.getElementById("configurar").style.visibility = 'visible';
			   return false;
			}
		//	var undefined=null;
		//	if(validateForm(form)==true){
				if(control == null || control==undefined){
					action = "?action=eliminar";
					form.action = form.action + action;
					if(confirm("Va a eliminar un registro de la base")){
						form.submit();
					}
				}else{
					action = "?action=eliminar&control=" + control;
					form.action = form.action + action;
					if(confirm("Va realizar una operacion de " + control)){
						form.submit();
					}
				}
		//	} else action = null;
		}
 
		function goToModificar(form) {
		
			var action = "";
		//	if(validateForm(form)==true){
				action ="?action=modificar";
				form.action = form.action + action;
				form.submit(); 
		//	} else action = null;
		}
		
		function goToActivar(form) {
			var action = "";
			action ="?action=activar";
			form.action = form.action + action;
			form.submit(); 
		}
		
		
		function goToActualizar(form) {
			var action = "";
			action ="?action=actualizar";
			form.action = form.action + action;
			form.submit(); 
		}
		
		///eda
		function goToEliminarFecha(form,control) {
		
			var rad = document.getElementById('pcCveVendedor');
			if(rad!=null){
				if(idCheck(form.pcCveVendedor)){
					MM_validateForm('pcFchBaja2','RisText','Fecha de Baja:');
					if (document.MM_returnValue){
						action = "?action=eliminar&control=" + control;
						form.action = form.action + action;
						if(confirm("Va realizar una operacion de " + control)){
							form.submit();
						}
					}
				}else{
					alert("Debe seleccionar un Vendedor para Dar de Baja");
				}
			}else{
				alert("Debe buscar un Vendedor para Dar de Baja");
			}
			
		}	
		
		
		function goToReactivar(form) {
			
			var rad = document.getElementById('pcCveVendedor');
			if(rad!=null){
				if(idCheck(form.pcCveVendedor)){
					MM_validateForm('pcFchReact','RisText','Fecha de Reactivaci�n:');
					if (document.MM_returnValue){
						var action = "?action=reactivar";
						form.action = form.action + action;
						form.submit();
					}
				}else{
					alert("Debe seleccionar un Vendedor para Reactivar");
				}
			}else{
				alert("Debe buscar un Vendedor para Modificar");
			}
		 }
		 
		function goToReactivarPtoVenta(form){
           	var action = "?action=reactivar";
			    form.action = form.action + action;
				form.submit();	
		} 		 	
		 	
		function goToAltaFecha(form,control) {
			var action ="";
			//alert("status " + document.forms[0].pcStatus.value)
	       // var undefined;    	       
	       //alert("Valor del pcFchAlta " + document.forms[0].pcFechaStatus.value );     
           if (document.forms[0].pcFechaStatus.value == "" )  { 
               	 alert("Por favor ingrese la fecha de alta"); 
                 form.pcFechaStatus.focus(); 
                 return false; 
             } else{
				action = "?action=alta&control=" + control;
				form.action = form.action + action;
				if(confirm("Va a realizar una operacion de cambio de Status a " + control)){
			    form.submit();
				}
			}			
		}
		//eda
		
		//rsg
		///eda
		function goToCambiarDivision(form,control) {
			 var action ="";	
			 if (document.forms[0].pcCveDivCambia.value == "" ){ 
           	 alert("Por favor selecciona una divisi�n"); 
             form.pcCveDivCambia.focus(); 
             return false; 
			}
			else{
				action = "?action=cambiar&control=" + control;
				form.action = form.action + action;
				if(confirm("Va realizar una operacion de " + control)){
					form.submit();
				}		
		   }
		 }	
		//rsg
		
		function goToAlta(form,control) {
			var action ="";
	
				if(control == null || control==undefined){
					action = "?action=alta";
					form.action = form.action + action;
					if(confirm("Va a cambiar el status del empleado a ALTA")){
						form.submit();
					}
				}else{
					action = "?action=alta&control=" + control;
					form.action = form.action + action;
					if(confirm("Va realizar una operacion de " + control)){
						form.submit();
					}
				}			
		}
		
		
		
		function goToAgregar(form,control) {
			var action = "";
			if(control == null || control==undefined){
				action = "?action=agregar";
			}else{
				action = "?action=agregar&control=" + control;
			}
		//	if(validateForm(form)==true){
				form.action = form.action + action;
				form.submit();
		//	}else action = null;
		}

		function goToBuscar(form,control) {
			var action ="";
		//	var undefined=null;
		//	if(validateForm(form)==true){
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
				form.action = form.action + action;
				form.submit(); 
		//	} else action = null;
			
		}
		
		function goToBuscarMovimiento(form,control) {
			var action ="";
			   if(form.pcCveDivisionSeek.value=="" && form.pcCvePuestoSeek.value=="" 
			                                       && form.pcCveVendedorSeek.value=="" && form.pcNomVendedorSeek.value=="" && form.pcNomClaveEmpleadoReferenciaSeek.value==""){
			    alert('Debe escoger al menos un criterio de b�squeda, favor de revisar.');
			    return false;
			   }
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
				form.action = form.action + action;
				form.submit(); 
		}
		
		function goToBuscarDetalle(form,control) {
			var action ="";	  	
			 if(form.pcCveVendedorRadio){
		              var miArray = document.getElementsByName("pcCveVendedorRadio"); 
		              var clave;
		              var bandera = "1";
		              for(var i=0; i<miArray.length; i++) {    
                          if(miArray[i].checked){
                          bandera = "2";
				              if(control == null || control==undefined){
					              action = "?action=consultar&control=buscar";
				              }else{
					              action = "?action=consultar&control=" + control;
				          }
				form.action = form.action + action;
				form.submit(); 			
		       }
	         }
	         if(bandera=="1"){
	                 alert('Por favor seleccione un registro de la tabla. \nRevise que el bot�n de la izquierda est� seleccionado.');
	                 return false;
	           }
	       }
	     }   	
		
		function goToBuscarPtoVta(form, control){
		     var action ="";
		        if(form.pcCveDivSeek.value=="" && form.pcCveTipoCanalSeek.value=="" && form.pcCveCanalSeek.value=="" && form.pcNomPtoventasSeek.value=="" && form.pcCvePtoventasSeek.value=="" && form.pcCveNumEconomico.value==""){
		           alert('Debe llenar al menos un criterio de b�squeda, favor de revisar.');
		           return false;
		        }
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
				form.action = form.action + action;
				form.submit(); 
		}
		
		function goToBuscar1(form,control) {
			var action ="";
		//	var undefined=null;
		//	if(validateForm(form)==true){
				if(control == null || control==undefined){
					action = "?action=consultar&control=buscar";
				}else{
					action = "?action=consultar&control=" + control;
				}
				form.action = form.action + action;
				form.claveSuperior.value=0;
				form.pcCveSupBase.value=0;
				form.submit(); 
		//	} else action = null;
			
		}
		
		function goToDynaBuscar(form,control) {
			var action ="";
			if(control == null){
				action = "?action=consultar";
			}else{
				action = "?action=consultar&control=" + control;
			}
			form.action = form.action + action;
			form.submit(); 
		}
		
		function goToConsultar(form,validar) {
				action = "?action=consultar";
				form.action = form.action + action;
				form.submit(); 
		}
		
		function goToEjecutar(form,validar) {
				action = "?action=ejecutar";
				form.action = form.action + action;
				form.submit(); 
		}
		
		function goToConfirm(form,control) {
				action = "?action=ejecutar&control="+control;
				form.action = form.action + action;
				form.submit(); 
		}
				
		function closingExec(){
		document.getElementById("configurar").style.visibility = 'hidden';
		}

		function FIND(item) {
			if( window.mmIsOpera ) return(document.getElementById(item));
			if (document.all) return(document.all[item]);
			if (document.getElementById) return(document.getElementById(item));
			return(false);
		}
		
		function goToSolicitar(form,control) {
			var action = "";
//			if(validateForm(form)==true){
				action ="?action=solicitar&control=" + control;
				form.action = form.action + action;
				form.submit(); 
//			} else action = null;
		}

		function changeInput(inputBox,valor,prefijo){
			itemName = prefijo + valor;
			hiddenItem      = FIND(itemName);
			inputBox.value= hiddenItem.value;
		}

		function changeCombo(comboBox,valor,prefijo){
			comboItem  = prefijo + valor;
			hiddenItem = FIND(comboItem);
			comboBox.selectedIndex = 0;
			for(i=0; i< comboBox.length; i++){
				if(comboBox.options[i].value == hiddenItem.value) comboBox.selectedIndex = i;
			}
			return;
		}
		
		function changeComboPersonal(comboBox, valor, descripcion){
			comboBox.selectedIndex = 0;
			var existe = true;
			for(i = 0; i < comboBox.length; i++){
				if(comboBox.options[i].value == valor)
					comboBox.selectedIndex = i;
				else
					existe = false;
			}
			if(descripcion && valor != 0){
				if(!existe){
					//alert("Valor: "+valor);
					//alert("Descr: "+descripcion);
					comboBox.options[comboBox.length] = new Option(descripcion, valor);
					comboBox.selectedIndex = comboBox.length-1;
				}
			}
			
			return;
		}
		
		function changeRadio(radioBut,valor,prefijo){
			comboItem  = prefijo + valor;
			hiddenItem = FIND(comboItem);
			for(i=0; i< radioBut.length; i++){
				if(radioBut[i].value == hiddenItem.value) radioBut[i].checked=true;
			}
			return;
		}	
		
		function addToCombo(comboBox,optionValue,optionName){
			comboBox.options[comboBox.length]= new Option(optionName,optionValue,false,false);
		}		

		function deleteFromCombo(comboBox){
			comboBox.options[comboBox.selectedIndex]= null;	
		}	
		
		
		function goToAgregarMapeo(form) {
			if(form.pcCveVendedor.value == "" || form.pcCveVendedor.value=="0" || form.pcCveVendedor.value==0) {
				alert("La clave de vendedor es obligatoria")
				form.pcCveVendedor.focus()
				return false
			}
			if(form.pcSistema.value == "") {
				alert("La clave del sistema es obligatoria")
				form.pcSistema.focus()
				return false
			}
			if(form.pcCustomerId.value == "") {
				alert("La clave de CustomerId es obligatoria")
				form.pcCustomerId.focus()
				return false
			}
			if(form.pcCveRegVenta.value == "") {
				alert("La clave de Registro Venta  es obligatoria")
				form.pcCveRegVenta.focus()
				return false
			}
			if(form.pcNomUsuario.value == "") {
				alert("El nombre del vendedor es obligatoria")
				form.pcNomUsuario.focus()
				return false
			}
			
			//return false;
			var action = "";
			//if(control == null || control==undefined){
				action = "?action=agregar";
			//}else{
				//action = "?action=agregar&control=" + control;
			//}

			form.action = form.action + action;
			form.submit();
				
		}
		
		function goToLimpiar(form) {

			form.pcCveVendedor.value = ""
			form.pcCustomerId.value = ""
			form.pcSistema.value = ""
			form.pcCveRegVenta.value = ""
			form.pcNomUsuario.value = ""
		}
		
		function goToEliminarMapeo(form) {
			goToEliminar(form);
		}
		
		function goToBuscarMapeoVendedor(form, control){
			goToLimpiar(form);
			goToBuscar(form,control);
		}
		
		function goToBuscarVendedor(form, control){
			goToLimpiar(form);
			goToBuscar(form, control);
		}
		
		//EDA
		function goToHistorico(form){
		var action = "";
			var undefined;			
			if((form.action).indexOf('?')!=-1){
				form.action = form.action.slice(0, form.action.indexOf('?'));
			}
			if(form.pcCveDivisionSeek.value == "" && form.pcCveVendedorSeek.value == ""){
			    alert('Debes de especificar al menos un criterio de b�squeda.');
			    return false;
			}
			if(form.pcCveVendedorSeek!=null){
		        if(isNaN(form.pcCveVendedorSeek.value)){
		         alert('La clave de vendedor debe ser num�rica, favor de revisar.');
		         return false;
		       }
		     }
			/*if(document.forms[0].pcCveVendedorSeek ==null || document.forms[0].pcCveVendedorSeek == undefined || document.forms[0].pcNomClaveEmpleadoReferenciaSeek ==null || document.forms[0].pcNomClaveEmpleadoReferenciaSeek == undefined || (document.forms[0].pcCveDivisionSeek.options[document.forms[0].pcCveDivisionSeek.selectedIndex].value == 0)) {
				action ="?action=historico";
				form.action = form.action + action;
				form.submit(); 
			} else {*/
			if(document.forms[0].pcCveVendedorSeek.value == "" && document.forms[0].pcNomClaveEmpleadoReferenciaSeek.value == "" &&  (document.forms[0].pcCveDivisionSeek.options[document.forms[0].pcCveDivisionSeek.selectedIndex].value == 0 )) 
			{
					alert("Debes de elegir al menos un tipo de busqueda")
					document.forms[0].pcCveVendedorSeek.focus()
					return false;
				} 
			else if((document.forms[0].pcCveVendedorSeek.value != ""  || 
			         document.forms[0].pcNomClaveEmpleadoReferenciaSeek.value != "" ||  
			        (document.forms[0].pcCveDivisionSeek.options[document.forms[0].pcCveDivisionSeek.selectedIndex].value != 0 )) && 
			        (document.forms[0].pcFchHistorico.value == "" ))
			{
				 alert("Debes de elegir una fecha para realizar el Reporte Hist�rico")
				 document.forms[0].pcFchHistorico.focus()
				 return false;
			}	
			
			else if(document.forms[0].pcCveVendedorSeek.value != ""  || document.forms[0].pcNomClaveEmpleadoReferenciaSeek.value != "" ||  (document.forms[0].pcCveDivisionSeek.options[document.forms[0].pcCveDivisionSeek.selectedIndex].value > 0 ))
			{
					action ="?action=historico";
					form.action = form.action + action;
					form.submit(); 
				}
				
				
		}
		//EDA
		
		function goToModificarPtoVenta(form) {
		   if(document.forms[0].pcCp.value == "") {
				alert("El campo CP 'c�digo postal' es obligatorio.");
				return false;
			}
				
			if(document.forms[0].pcCp.value != "" && isNaN(document.forms[0].pcCp.value)){
			    alert("El campo CP 'c�digo postal' s�lo puede contener n�meros.");
			    return false;
			}
			
			var descripcionCiudad = (document.getElementById('pcCiudad').options[document.getElementById('pcCiudad').selectedIndex]).text;
			document.getElementById('pcDescCiudad').value = descripcionCiudad;
			
			MM_validateForm('pcCveTipoCanal','RisCombo', 'Tipo de Canal:',
							'pcCveCanal','RisCombo', 'Canal:',
							'pcNomPtoventas','RisText', 'Nombre Punto de Ventas:',
							'pcDireccion','RisText', 'Direcci�n:',
  						    'pcColonia','RisText', 'Colonia:',
  						    'pcCp','isOnlyNumbers', 'Codigo Postal:',
  						    'pcCiudad','RisText', 'Ciudad:',
  						    'pcEstado','RisCombo', 'Estado:');  						   
  						 							
			if (document.MM_returnValue){
				if(document.forms[0].pcTipoRegistro.value ==  "MAN") {
					if(document.forms[0].pcCvePtoventas.value == "" || document.forms[0].pcCvePtoventas.value == "0") {
						alert("Debes de capturar la clave del Punto de Venta")
						document.forms[0].pcCvePtoventas.focus()
						return false;
					}
					if(document.forms[0].pcDigVerif.value == "") {
						alert("Debes de capturar el Digito Verificador")
						document.forms[0].pcDigVerif.focus()
						return false;
					}
				}
			
				var action = "?action=modificar";
				form.action = form.action + action;
				form.submit(); 
		 	}
		}
		
		
		function goToAgregarPtoVenta(form,control) {
            if(document.forms[0].pcCp.value == "") {
				alert("El campo CP 'c�digo postal' es obligatorio.");
				return false;
				}
				
			if(document.forms[0].pcCp.value != "" && isNaN(document.forms[0].pcCp.value)){
			    alert("El campo CP 'c�digo postal' s�lo puede contener n�meros.");
			    return false;
			    }
			    
			MM_validateForm('pcCveTipoCanal','RisCombo', 'Tipo de Canal:',
							'pcCveCanal','RisCombo', 'Canal:',
							'pcNomPtoventas','RisText', 'Nombre Punto de Ventas:',
							'pcDireccion','RisText', 'Direcci�n:',
  						    'pcColonia','RisText', 'Colonia:',
  						    'pcCp','isOnlyNumbers', 'Codigo Postal:',
  						    'pcCiudad','RisText', 'Ciudad:',
  						    'pcEstado','RisCombo', 'Estado:',
  						    'pcFchIniOp','RisDate','Fecha de Alta:');  						   
  						 							
			if (document.MM_returnValue){
		
			//	return false;

			//}

		    	
			if(document.forms[0].pcTipoRegistro.value ==  "MAN") {
			//alert("document.forms[0].pcCvePtoventas.value " + document.forms[0].pcCvePtoventas.value)
				if(document.forms[0].pcCvePtoventas.value == "" || document.forms[0].pcCvePtoventas.value == "0") {
					alert("debes de capturar la clave del Punto de Venta")
					document.forms[0].pcCvePtoventas.focus()
					return false;
				}
				if(document.forms[0].pcDigVerif.value == "") {
					alert("debes de capturar el Digito Verificador")
					document.forms[0].pcDigVerif.focus()
					return false;
				}
			}
				
			
			var action = "";
			if(control == null || control==undefined){
				action = "?action=agregar";
			}else{
				action = "?action=agregar&control=" + control;
			}
		//	if(validateForm(form)==true){
				form.action = form.action + action;
				form.submit();
		//	}else action = null;
		}
	}	
	
	//Agregadas apartir de Febrero 2008 AALCAZAR
	
	function goToSearch(form, control) {
		var action = "?action=buscar&control=" + control;
		
		form.action = form.action + action;
		form.claveSuperior.value=0;
		form.pcCveSupBase.value=0;
		form.submit(); 
	}
	