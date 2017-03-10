function MM_findObj(n, d) { //v4.01
    var p,i,x;

    if(!d) {
        d=document;
    }

    if((p=n.indexOf("?"))>0&&parent.frames.length) {
        d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
    }

    if(!(x=d[n]) && d.all) {
        x=d.all[n];
    }

    for (i=0; !x && i<d.forms.length; i++) {
        x = d.forms[i][n];
    }

    for(i=0;!x&&d.layers&&i<d.layers.length;i++) {
        x = MM_findObj(n,d.layers[i].document);
    }

    if(!x && d.getElementById) {
        x=d.getElementById(n);
    }

    return x;
}

function MM_validateForm() { //v4.1rbf
    var i,p,q,nm,test,msg,num,min,max,errors='',args=MM_validateForm.arguments,error=false,encontrado=true;
	var mikExp = /[$\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\=\|\<\>]/;
	//var mikExp = /[\^\{\}\`\|\<\>\']/;

    for (i=0; i<(args.length-1); i+=3) {

        test=args[i+1];
        val=MM_findObj(args[i]);
        //validar que el campo exista
        if (test.indexOf('isLength') != -1){
			var indice1 = args[i].indexOf('-');
			val = MM_findObj(args[i].substring(0,indice1));
            var cp = args[i].substring(indice1+1);
        }
        if(!val){
        	errors+='- Error de programación: no se encontró el elemento \"'+args[i]+'\".\n';
        	error = true;
        	encontrado = false;
        	break;
        }
		
		val2=MM_findObj(args[i]);
		// Validación para la longitud obligatoria de un campo donde el campo viene concatenado a la longitud requerida
		if (test.indexOf('isLength') != -1){
			val2 = MM_findObj(args[i].substring(0,indice1));
        }
		nm=args[i+2]; //El nombre del CAMPO manda el ALERT
		
	    	if (!(test.indexOf('isCombo') != -1 ||
					test.indexOf('isRadio') != -1 ||
					test.indexOf('isText') != -1 ||
					test.indexOf('isURL') != -1 ||
					test.indexOf('isCero') != -1 ||
					test.indexOf('isPathToFile') != -1 ||
					test.indexOf('isEmail') != -1 ||
					test.indexOf('isDate') != -1 ||
					test.indexOf('isTime') != -1 ||
					test.indexOf('isSignedNum') != -1 ||
					test.indexOf('isNum') != -1 ||
					test.indexOf('isUnsignedNum') != -1 ||
					test.indexOf('isInteger') != -1 ||
					test.indexOf('isOnlyNumbers') != -1 ||
					test.indexOf('isCurrency') != -1 ||
					test.indexOf('isFloat') != -1 ||
					test.indexOf('isUnsignedFloat') != -1 ||
					test.indexOf('isPorcent4_4') != -1 ||
					test.indexOf('isPorcent1_1') != -1 ||
					test.indexOf('inRange') != -1 ||
					test.indexOf('isLength') != -1)){
				errors+='- Error de programación: la opción \"'+test+'\" es inválida.\n';
        		error = true;
        		break;
			}
	
        if (test.indexOf('isCombo')!=-1) {
    		if (val.options[val.selectedIndex].value == 0 || val.options[val.selectedIndex].value == '') {
    			errors+='- \"'+nm+'\" es obligatorio.\n';
    			error = true;
    		}
    	} else if (test.indexOf('isRadio')!=-1){
				val = MM_findObj(args[i]);
				if(!radioSelected(val)){
					errors+='- \"'+nm+'\" es obligatorio.\n';
					error = true;
				}
        } else if ((val=Trim(val.value))!="") {
            if (test.indexOf('isText')!=-1) {
                val2.value = Trim(val2.value);
				if(val.search(mikExp) > -1) {
					errors+='- \"'+nm+'\" contiene caracteres inválidos: ' + val + '.\n';
					error = true;
				}
            }else if (test.indexOf('isURL')!=-1) {
  		        val2.value = Trim(val2.value);
				if (!(/^(http:\/\/\w+(\.\w+)*\/)?(\w+)?(\/(\w+|\.\.|\.))*(\?\w+=\w+(&\w+=\w+)*)?$/.test(val))) {
					errors+='- \"'+nm+'\" debe contener un URL válido.\n';
					error = true;
				}
            }else if (test.indexOf('isCero')!=-1) {
				val = MM_findObj(args[i]);
				var cadena = Trim(val.value);
				val2.value = Trim(val2.value);
				if(cadena <= 0){
					errors+='- \"'+nm+'\" debe ser mayor a cero.\n';
					error = true;
				}
			}else if (test.indexOf('isPathToFile')!=-1) {
				val2.value = Trim(val2.value);
				//if (!(/^(([A-Za-z0-9_\(\)]+|\.\.|\.)\/)*[A-Za-z0-9_\(\)]+$/.test(val))) {
				if (!(/^(([A-Za-z0-9_:\(\)]+|\.\.|\.)\/)*[A-Za-z0-9_\(\)]+(.[A-Za-z0-9])+$/.test(val))) {
					errors+='- \"'+nm+'\" debe contener una ruta válida a un archivo.\n';
					error = true;
				}
            } else if (test.indexOf('isEmail')!=-1) {
            	val2.value = Trim(val2.value);
                p=val.indexOf('@');
                if (p<1 || p==(val.length-1)) {
                    errors+='- \"'+nm+'\" debe contener una direccion de correo electronico.\n';
					error = true;
                } else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)+$/.test(val))) {
					errors+='- \"'+nm+'\" contiene una dirección de correo inválida: ' + val + '.\n';
					error = true;
				}
            } else if (test.indexOf('isDate')!=-1) {

                dia = val.substring(0,2);
                s1  = val.substring(2,3);
                mes = val.substring(3,5);
                s2  = val.substring(5,6);
                ano = val.substring(6,10);

                if ( (val.length!=10) || // el formato de fecha es de 10 caracteres
                        (isNaN(dia)) || // verificar el dia
                        (isNaN(mes)) || // verificar el mes
                        (isNaN(ano)) || // verificar el anio
                        (s1!="/") || (s2!="/") ) { // verificar los separadores
                    errors+='- \"'+nm+'\" debe contener una fecha (dd/mm/aaaa).\n';
        			error = true;
        		} else if ((ano < 1900) || (ano > 2079)) {
        			errors+='- \"'+nm+'\" contiene una fecha inválida: ' + val + '.\n';
        			error = true;
                } else if (!validaFecha(dia,mes,ano)) {
        			errors+='- \"'+nm+'\" contiene una fecha inválida: ' + val + '.\n';
        			error = true;
       		    }

            } else if (test.indexOf('isTime')!=-1) {

                hora = val.substring(0,2);
                sep  = val.substring(2,3);
                min  = val.substring(3,5);

                if ( (val.length!=5) ||  // el formato de horario es de 5 caracteres ##:##
                     (isNaN(hora)) ||  // verificar la hora
                     (isNaN(min)) || // verificar el minuto
                     (sep!=":")) {  // verificar el separador
                    errors+='- \"'+nm+'\" debe contener una hora (hh:mm de 00:00 a 23:59).\n';
					error = true;
	    		} else if ((hora < 0) || (hora > 23) || (min < 0) || (min > 59)) {
					errors+='- \"'+nm+'\" contiene una hora inválida: ' + val + '.\n';
					error = true;
                }
            }else if (test.indexOf('isLength') != -1) {
                if(val.length != cp){
        		  errors+='- \"'+nm+'\" el número de caracteres debe ser : ' + cp + '.\n';
        		  error = true;
        		}
		   } else if (test!='R') {
                num = parseFloat(Trim(val));

                if (test.indexOf('isSignedNum') != -1 || test.indexOf('isNum') != -1) {
                	val2.value = Trim(val2.value);
					if (!(/^[-+]?\d+$/.test(val))) {
						errors+='- \"'+nm+'\" debe contener un número entero.\n';
						error = true;
						break;
					}
	    	    }

                if (test.indexOf('isUnsignedNum') != -1 || test.indexOf('isInteger') != -1) {
                    val2.value = Trim(val2.value);
					if (!(/^\d+$/.test(val))) {
						errors+='- \"'+nm+'\" debe contener un número entero sin signo.\n';
						error = true;
						break;
					}
                }

                /**if (test.indexOf('isOnlyNumbers') != -1) {
                	val2.value = Trim(val2.value);
					if (!(/^\d+$/.test(val))) {
						errors+='- \"'+nm+'\" solo acepta números.\n';
						error = true;
						break;
					}
                }**/

                if (test.indexOf('isCurrency') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[-+]?\d*\.\d\d$/.test(val))) {
                    	errors+='- \"'+nm+'\" debe contener un número con 2 decimales.\n';
						error = true;
                    }
                }

                if (test.indexOf('isFloat') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[-+]?\d*\.?\d+$/.test(val))) {
                        errors+='- \"'+nm+'\" debe contener un número.\n';
						error = true;
                    }
                }
                
                if (test.indexOf('isUnsignedFloat') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[+]?\d*\.?\d+$/.test(val))) {
                        errors+='- \"'+nm+'\" debe contener un número positivo.\n';
						error = true;
                    }
                }

                if (test.indexOf('isPorcent4_4') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[-+]?\d\d\d\d\.\d\d\d\d$/.test(val))) {
                        errors+='- \"'+nm+'\" debe contener un número con 4 enteros 4 decimales.\n';
						error = true;
                    }
                }

                if (test.indexOf('isPorcent1_1') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[-+]?\d\.\d$/.test(val))) {
                        errors+='- \"'+nm+'\" debe contener un número con 1 entero 1 decimal.\n';
						error = true;
                    }
                }

                if (test.indexOf('inRange') != -1) {
                	val2.value = Trim(val2.value);
                    p=test.indexOf(':');
                    min=test.substring(8,p); max=test.substring(p+1);
                    if (num<min || max<num) {
                        errors+='- \"'+nm+'\" debe contener un número entre '+min+' y '+max+'.\n';
						error = true;
                    }
                }
            }
        } else if (test.charAt(0) == 'R') {
            errors += '- \"'+nm+'\" es obligatorio.\n';
			error = true;
        }
    }

    if (errors) {
        alert('Ocurrieron los siguientes errores:\n'+errors);
        if(encontrado)
			val2.focus();
    }

    document.MM_returnValue = (errors == '');
}

function validaFecha(myDayStr, myMonthStr, myYearStr) {

	if ((myDayStr > 31) || (myMonthStr > 12)) {
		return false;
	}
 
	var myDate = new Date(myYearStr, myMonthStr-1, myDayStr);

	/* Cuando una fecha es invalida, al convertirla a Date()
	   el mes (getMonth()) se recorre automaticamente, resultando
	   diferente de myMonthStr-1 */	
	return (parseInt(myDate.getMonth())) == parseInt(myMonthStr-1);
}

function formatCurrency( num ) {
        var isNegative = false;
        num = num.toString().replace(/\\$|\\,/g,'');
        if( isNaN( num ) ) {
          num = "0";
        }
        if ( num < 0 ) {
          num = Math.abs( num );
          isNegative = true;
        }
        cents = Math.floor( ( num * 100 + 0.5 ) % 100 );
        num = Math.floor( ( num * 100 + 0.5 ) / 100 ).toString();
        if ( cents < 10 ) {
          cents = "0" + cents;
        }
        for ( i = 0; i < Math.floor( ( num.length - ( 1 + i ) ) / 3 ); i++) {
          num = num.substring( 0 ,num.length - ( 4 * i + 3 ) ) + ',' + num.substring( num.length - ( 4 * i + 3 ) );
        }

        var result = num + '.' + cents;
        if ( isNegative ) {
          result = "-" + result;
        }
        return result;
      }


// FUNCIONES GENERALES DE JAVA
// Este archivo <FuncionesJava.js> se debe encontrar en la carpeta _private

	// Trim.- Elimina los espacios de la derecha y de la izquierda de una "Cadena"
	function Trim(strCadena) // Debe ir en un archivo de Funciones
	{
		var strCadenaTmp;
		var nIndex, nIndex2;
		
		for (nIndex=0; nIndex < strCadena.length; nIndex++)
		{
			if (strCadena.charAt(nIndex) != " ") {
				break;
			}
		}
		for (nIndex2 = strCadena.length-1; nIndex2 >= 0 && nIndex < strCadena.length; nIndex2--)
		{
			if (strCadena.charAt(nIndex2) != " ") {
				break;
			}
		}
		//Se concatenan CadenaTmp desde nIndex hasta nIndex2
		strCadenaTmp = "";
		if (nIndex <= nIndex2)
			strCadenaTmp = strCadena.substring(nIndex, nIndex2 + 1);
		return strCadenaTmp;
	}


	// NumeroCorrecto.- Valida que una Cadena sea un 'número entero' válido
	function NumeroCorrecto(strCadena)
	{
		var nIndex = 0;
		
		if (strCadena.length > 0) {
			for (nIndex ; nIndex < strCadena.length; nIndex++)
			{
				if ((strCadena.charAt(nIndex) < "0") || (strCadena.charAt(nIndex) > "9"))
					return false;
			}
		}
		return true;
	}

	// DecimalCorrecto.- Valida que una Cadena sea un 'número double' válido
	function DecimalCorrecto(strCadena)
	{
		var nIndex = 0;
		var numPuntos = 0;
		if (strCadena.length > 0) {
			for (nIndex ; nIndex < strCadena.length; nIndex++)
			{
				if ((strCadena.charAt(nIndex) < "0") || (strCadena.charAt(nIndex) > "9")){
					if(!(strCadena.charAt(nIndex) == '.' && ++numPuntos <= 1)){						
						return false;
					}
				}
			}
		}
		return true;
	}

	// TextoCorrecto.- Valida que una Cadena no contenga los simbolos < , > y &
	function TextoCorrecto(strCadena)
	{
		var nIndex = 0;
		
		if (strCadena.length > 0) {
			for (nIndex ; nIndex < strCadena.length; nIndex++)
			{
				if ((strCadena.charAt(nIndex) == "<") || (strCadena.charAt(nIndex) == ">")
					|| (strCadena.charAt(nIndex) == "@"))
					return false;
			}
		}
		return true;
	}	

	//getParameter.- Regresa un parametro de una liga url
	function getParameter(liga,parametro){
		var posLiga, posParam = -1;
		var enCharEspecial = false;
		var enValor = false;
		var valor = "";
		parametro += "=";
		for (posLiga = 0 ;posLiga < liga.length; posLiga++){
		  if(liga.charAt(posLiga) == "?" || liga.charAt(posLiga) == "&"){
		    if(enValor){
		      return valor;
		    }else{
		      enCharEspecial = true;
		    }
		  }else{
		    if(enValor){
		      valor += liga.charAt(posLiga);
		    }else{
		      if(enCharEspecial){
		        if(liga.charAt(posLiga) == parametro.charAt(++posParam)){
		          if(posParam == parametro.length-1){
		            enValor = true;
		          }
		        }else{
		          enCharEspecial = false;
		          posParam = -1;
		        }
		      }
		    }
		  }
		}
		return valor;
	}

	//validaFrames.- Si la pagina no esta dentro de frames manda a pagina de error
	function validaFrames(){
		var ns = navigator.appName == "Netscape";
		if(!ns){
			if(window.parent.document.all["body"]){
				document.location.href = "Error.jsp?"
				+ "funcion=No definido"
				+ "&error=" + "La página solicitada es inválida";
			}
		}
	}

	//comboValue.- Regresa el valor del objeto seleccionado
	function comboValue(combo){
		return combo.options[combo.selectedIndex].value;
	}

	//comboTexto.- Regresa el texto del objeto seleccionado
	function comboTexto(combo){
		return combo.options[combo.selectedIndex].text;
	}

	//radioValue.- Regresa el valor del radio boton señalado o cadena vacia si
	//ninguno esta señalado
	function radioValue(radio){
		if(radio != null){
			if(radio.length == null){
				if(radio.checked){
					return radio.value;
				}else{
					return "";
				}
			}
			for(i=0; i<radio.length; i++){
				if(radio[i].checked){
					return radio[i].value;
				}
			}
		}
		return "";
	}
	
	//selectRadio.- Selecciona el radio que tenga el valor value
	function selectRadio(radio, value){
		if(radio != null){
			if(radio.length == null){
				if(radio.value == value){
					radio.checked = true;
				}else{
					return;
				}
			}
			for(i=0; i<radio.length; i++){
				if(radio[i].value == value){
					radio[i].checked = true;
					break;
				}
			}
		}
	}
	
	//selectRadio.- Deselecciona el radio que este seleccionado
	function unselectRadio(radio){
		if(radio != null){
			if(radio.length == null){
				radio.checked = false;
			}
			for(i=0; i<radio.length; i++){
				radio[i].checked = false;
			}
		}
	}

	//radioSelected.- Regresa true si un radio buton esta seleccionado, false 
	//en caso contrario
	function radioSelected(radio){
		if(radio != null){
			if(radio.length == null){
				if(radio.checked){
					return true;
				}else{
					return false;
				}
			}
			for(i=0; i<radio.length; i++){
				if(radio[i].checked){
					return true;
				}
			}
		}
		return false;
	}

	//wait.- Metodo para esperar cierto tiempo
	function wait(time){
		var t = 0;
		while (t < time){
			t = t+1;
		}
	}

	//checkboxValue.- Regresa S si esta seleccionado o N en otro caso
	function checkboxValue(checkb){
		if(checkb.checked == true){
			return "S";
		}
		return "N";
	}

	// MensajeInicioLoad.- Este mensaje muestra cada página cuando inicia su carga
	function MensajeInicioLoad() {
		statusBarMsg("Cargando página ...");
		return;
	}

	// MensajeFinLoad.- Este mensaje muestra cada página cuando termina su carga
	function MensajeFinLoad() {
		statusBarMsg("Listo");
		return;
	}
	
	//Obtiene los dias de diferencia entre una fecha y otra
	//Regresa un numero negativo si la 1er fecha es MAYOR a la 2da
	var SECOND = 1000; // el numero de milisegundos en un segundo
	var MINUTE = SECOND * 60; // el numero de milisegundos en un minuto
	var HOUR = MINUTE * 60; // el numero de milisegundos en una hora
	var DAY = HOUR * 24; // el numero de milisegundos en un dia
	var WEEK = DAY * 7; // el numero de milisegundos en una semana

	function daysBetween(fecha1, fecha2) {
		dia1 = fecha1.substring(0,2);
                mes1 = fecha1.substring(3,5);
                anio1 = fecha1.substring(6,10);
                dia2 = fecha2.substring(0,2);
                mes2 = fecha2.substring(3,5);
                anio2 = fecha2.substring(6,10);
		var nTime = Date.UTC(anio1, mes1 - 1, dia1); // especifica tiempo (UTC)
		var dTime = Date.UTC(anio2, mes2 - 1, dia2); // especifica tiempo (UTC)
		//var bTime = Math.abs(nTime - dTime)  // diferencia de tiempo
		var bTime = dTime - nTime;  // diferencia de tiempo
		return Math.round(bTime / DAY);
		//alert(Math.round(bTime / DAY));
	}
	
	function statusBarMsg(msg) {
		//alert(msg);
		window.status=msg;
		setTimeout("window.status=''",5000);
	}
	
	//metodo para validar que no se escriban en el textarea mas caracteres
	//del restringido por el valor limite
	function limitTextAreaLength(textArea, limite) {
		if (textArea.value.length > (limite - 1)) {
			textArea.value = textArea.value.substring(0, (limite - 1));
			alert("Ha sobrepasado el límite de caracteres (" + limite + ")");
		}
	}
	
  //AVB Checa si un radiobutton esta seleccionado			
  function idCheck(rad) {
	  if(rad.checked == true){
		  return true;
	  }
	  for (i=0; i< rad.length; i++) {
		  if(rad[i].checked == true){
			  return true;
		  }
	  }
	  return false;
  } 
 //rarce Enfoca un campo dependiendo de la accion ocurrida
 function Enfoca(form, action, campo, accion){
	if(accion == action){
	eval("form." + campo).focus();
	}
}

function esFecha(forma, areaTexto) {

	StrLen = areaTexto.value.length;

	for (x = 1; x <=StrLen; x++)
	{
		caracter = areaTexto.value.substring(x-1, x);
		
		if(x == 1)
		{
			if (caracter != "0" && caracter != "1" && caracter != "2" && caracter != "3")
			{
				areaTexto.value = areaTexto.value.substring(0,x-1);
				break;
			}
		}

		if(x == 2 || x == 5)
		{
			if (caracter != "0" && caracter != "1" && caracter != "2" && caracter != "3" && caracter != "4" && caracter != "5" && caracter != "6" && caracter != "7" && caracter != "8" && caracter != "9")
			{
				areaTexto.value = areaTexto.value.substring(0,x-1);
				break;
			}
		}
			
		if(x == 3 || x == 6)
		{
			if (caracter != "/")
			{
				areaTexto.value = areaTexto.value.substring(0,x-1);
				break;
			}
		}

		if(x == 4)
		{
			if (caracter != "0" && caracter != "1")
			{
				areaTexto.value = areaTexto.value.substring(0,x-1);
				break;
			}
		}

		if(x == 7 || x == 8 || x == 9 || x == 10)
		{
			if (caracter != "0" && caracter != "1" && caracter != "2" && caracter != "3" && caracter != "4" && caracter != "5" && caracter != "6" && caracter != "7" && caracter != "8" && caracter != "9")
			{
				areaTexto.value = areaTexto.value.substring(0,x-1);
				break;
			}
		}
		
		if(x == 2){
			dia = areaTexto.value.substring(0,2);
			dia = parseInt(dia);
			if (dia > 31)
			{
				areaTexto.value = areaTexto.value.substring(0,x-1);
				break;
			}
		}

		if(x == 5){
			mes = areaTexto.value.substring(3,5);
			mes = parseInt(mes);
			if (mes > 12)
			{
				areaTexto.value = areaTexto.value.substring(0,x-1);
				break;
			}
		}
		
		if(x == 10){
			anio = areaTexto.value.substring(6,10);
			anio = parseInt(anio);
			if (anio > 2999 || anio < 1900)
			{
				areaTexto.value = areaTexto.value.substring(0,x-1);
				break;
			}
		}
	}
}

function MM_validateForma() { //v4.1rbf
    var i,p,q,nm,test,msg,num,min,max,errors='',args=MM_validateForma.arguments,error=false,encontrado=true;
	var mikExp = /[$\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\=\|\<\>]/;
	//var mikExp = /[\^\{\}\`\|\<\>\']/;

    for (i=0; i<(args.length-1); i+=3) {

        test=args[i+1];
        val=MM_findObj(args[i]);
        //validar que el campo exista
        if (test.indexOf('isLength') != -1){
			var indice1 = args[i].indexOf('-');
			val = MM_findObj(args[i].substring(0,indice1));
            var cp = args[i].substring(indice1+1);
        }
        if(!val){
        	errors+='- Error de programación: no se encontró el elemento \"'+args[i]+'\".\n';
        	error = true;
        	encontrado = false;
        	break;
        }
		
		val2=MM_findObj(args[i]);
		// Validación para la longitud obligatoria de un campo donde el campo viene concatenado a la longitud requerida
		if (test.indexOf('isLength') != -1){
			val2 = MM_findObj(args[i].substring(0,indice1));
        }
		nm=args[i+2]; //El nombre del CAMPO manda el ALERT
		
	    	if (!(test.indexOf('isCombo') != -1 ||
					test.indexOf('isRadio') != -1 ||
					test.indexOf('isText') != -1 ||
					test.indexOf('isURL') != -1 ||
					test.indexOf('isCero') != -1 ||
					test.indexOf('isPathToFile') != -1 ||
					test.indexOf('isEmail') != -1 ||
					test.indexOf('isDate') != -1 ||
					test.indexOf('isTime') != -1 ||
					test.indexOf('isSignedNum') != -1 ||
					test.indexOf('isNum') != -1 ||
					test.indexOf('isUnsignedNum') != -1 ||
					test.indexOf('isInteger') != -1 ||
					test.indexOf('isOnlyNumbers') != -1 ||
					test.indexOf('isCurrency') != -1 ||
					test.indexOf('isFloat') != -1 ||
					test.indexOf('isUnsignedFloat') != -1 ||
					test.indexOf('isPorcent4_4') != -1 ||
					test.indexOf('isPorcent1_1') != -1 ||
					test.indexOf('inRange') != -1 ||
					test.indexOf('isLength') != -1)){
				errors+='- Error de programación: la opción \"'+test+'\" es inválida.\n';
        		error = true;
        		break;
			}
	
        if (test.indexOf('isCombo')!=-1) {
    		if (val.options[val.selectedIndex].value == '') {
    			errors+='- \"'+nm+'\" es obligatorio.\n';
    			error = true;
    		}
    	} else if (test.indexOf('isRadio')!=-1){
				val = MM_findObj(args[i]);
				if(!radioSelected(val)){
					errors+='- \"'+nm+'\" es obligatorio.\n';
					error = true;
				}
        } else if ((val=Trim(val.value))!="") {
            if (test.indexOf('isText')!=-1) {
                val2.value = Trim(val2.value);
				if(val.search(mikExp) > -1) {
					errors+='- \"'+nm+'\" contiene caracteres inválidos: ' + val + '.\n';
					error = true;
				}
            } else if (test.indexOf('isURL')!=-1) {
  		        val2.value = Trim(val2.value);
				if (!(/^(http:\/\/\w+(\.\w+)*\/)?(\w+)?(\/(\w+|\.\.|\.))*(\?\w+=\w+(&\w+=\w+)*)?$/.test(val))) {
					errors+='- \"'+nm+'\" debe contener un URL válido.\n';
					error = true;
				}
            }else if (test.indexOf('isCero')!=-1) {
				val = MM_findObj(args[i]);
				var cadena = Trim(val.value);
				val2.value = Trim(val2.value);
				if(cadena <= 0){
					errors+='- \"'+nm+'\" debe ser mayor a cero.\n';
					error = true;
				}
			}else if (test.indexOf('isPathToFile')!=-1) {
				val2.value = Trim(val2.value);
				//if (!(/^(([A-Za-z0-9_\(\)]+|\.\.|\.)\/)*[A-Za-z0-9_\(\)]+$/.test(val))) {
				if (!(/^(([A-Za-z0-9_:\(\)]+|\.\.|\.)\/)*[A-Za-z0-9_\(\)]+(.[A-Za-z0-9])+$/.test(val))) {
					errors+='- \"'+nm+'\" debe contener una ruta válida a un archivo.\n';
					error = true;
				}
            } else if (test.indexOf('isEmail')!=-1) {
            	val2.value = Trim(val2.value);
                p=val.indexOf('@');
                if (p<1 || p==(val.length-1)) {
                    errors+='- \"'+nm+'\" debe contener una direccion de correo electronico.\n';
					error = true;
                } else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)+$/.test(val))) {
					errors+='- \"'+nm+'\" contiene una dirección de correo inválida: ' + val + '.\n';
					error = true;
				}
            } else if (test.indexOf('isDate')!=-1) {

                dia = val.substring(0,2);
                s1  = val.substring(2,3);
                mes = val.substring(3,5);
                s2  = val.substring(5,6);
                ano = val.substring(6,10);

                if ( (val.length!=10) || // el formato de fecha es de 10 caracteres
                        (isNaN(dia)) || // verificar el dia
                        (isNaN(mes)) || // verificar el mes
                        (isNaN(ano)) || // verificar el anio
                        (s1!="/") || (s2!="/") ) { // verificar los separadores
                    errors+='- \"'+nm+'\" debe contener una fecha (dd/mm/aaaa).\n';
        			error = true;
        		} else if ((ano < 1900) || (ano > 2079)) {
        			errors+='- \"'+nm+'\" contiene una fecha inválida: ' + val + '.\n';
        			error = true;
                } else if (!validaFecha(dia,mes,ano)) {
        			errors+='- \"'+nm+'\" contiene una fecha inválida: ' + val + '.\n';
        			error = true;
       		    }

            } else if (test.indexOf('isTime')!=-1) {

                hora = val.substring(0,2);
                sep  = val.substring(2,3);
                min  = val.substring(3,5);

                if ( (val.length!=5) ||  // el formato de horario es de 5 caracteres ##:##
                     (isNaN(hora)) ||  // verificar la hora
                     (isNaN(min)) || // verificar el minuto
                     (sep!=":")) {  // verificar el separador
                    errors+='- \"'+nm+'\" debe contener una hora (hh:mm de 00:00 a 23:59).\n';
					error = true;
	    		} else if ((hora < 0) || (hora > 23) || (min < 0) || (min > 59)) {
					errors+='- \"'+nm+'\" contiene una hora inválida: ' + val + '.\n';
					error = true;
                }
            }else if (test.indexOf('isLength') != -1) {
                if(val.length != cp){
        		  errors+='- \"'+nm+'\" el número de caracteres debe ser : ' + cp + '.\n';
        		  error = true;
        		}
		   } else if (test!='R') {
                num = parseFloat(Trim(val));

                if (test.indexOf('isSignedNum') != -1 || test.indexOf('isNum') != -1) {
                	val2.value = Trim(val2.value);
					if (!(/^[-+]?\d+$/.test(val))) {
						errors+='- \"'+nm+'\" debe contener un número entero.\n';
						error = true;
						break;
					}
	    	    }

                if (test.indexOf('isUnsignedNum') != -1 || test.indexOf('isInteger') != -1) {
                    val2.value = Trim(val2.value);
					if (!(/^\d+$/.test(val))) {
						errors+='- \"'+nm+'\" debe contener un número entero sin signo.\n';
						error = true;
						break;
					}
                }

                if (test.indexOf('isOnlyNumbers') != -1) {
                	val2.value = Trim(val2.value);
					if (!(/^\d+$/.test(val))) {
						errors+='- \"'+nm+'\" solo acepta números.\n';
						error = true;
						break;
					}
                }

                if (test.indexOf('isCurrency') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[-+]?\d*\.\d\d$/.test(val))) {
                    	errors+='- \"'+nm+'\" debe contener un número con 2 decimales.\n';
						error = true;
                    }
                }

                if (test.indexOf('isFloat') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[-+]?\d*\.?\d+$/.test(val))) {
                        errors+='- \"'+nm+'\" debe contener un número.\n';
						error = true;
                    }
                }
                
                if (test.indexOf('isUnsignedFloat') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[+]?\d*\.?\d+$/.test(val))) {
                        errors+='- \"'+nm+'\" debe contener un número positivo.\n';
						error = true;
                    }
                }

                if (test.indexOf('isPorcent4_4') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[-+]?\d\d\d\d\.\d\d\d\d$/.test(val))) {
                        errors+='- \"'+nm+'\" debe contener un número con 4 enteros 4 decimales.\n';
						error = true;
                    }
                }

                if (test.indexOf('isPorcent1_1') != -1) {
                	val2.value = Trim(val2.value);
                    if (!(/^[-+]?\d\.\d$/.test(val))) {
                        errors+='- \"'+nm+'\" debe contener un número con 1 entero 1 decimal.\n';
						error = true;
                    }
                }

                if (test.indexOf('inRange') != -1) {
                	val2.value = Trim(val2.value);
                    p=test.indexOf(':');
                    min=test.substring(8,p); max=test.substring(p+1);
                    if (num<min || max<num) {
                        errors+='- \"'+nm+'\" debe contener un número entre '+min+' y '+max+'.\n';
						error = true;
                    }
                }
            }
        } else if (test.charAt(0) == 'R') {
            errors += '- \"'+nm+'\" es obligatorio.\n';
			error = true;
        }
    }

    if (errors) {
        alert('Ocurrieron los siguientes errores:\n'+errors);
        if(encontrado)
			val2.focus();
    }

    document.MM_returnValue = (errors == '');
}

	function validarClave(valor){ 
        	 valor = parseInt(valor) 
      	if (isNaN(valor)) { 
            return 0;
     	 }else{
     	 return 1;
     	 }
	}
	
	function validarEntero(valor){ 
    		 valor = parseInt(valor) 
     		if (isNaN(valor)) { 
            return false; 
     		}else{ 
            return true; 
        	 } 
	   }
	   
 	function  diferenciaFechas (form){
  
  		//Obtiene los datos del formulario
 		CadenaFecha1 = form.fechaInicial.value
   		CadenaFecha2 = form.fechaFinal.value
   
   		//Obtiene dia, mes y a?o
   		var fechai = new fecha( CadenaFecha1 )   
   		var fechaf = new fecha( CadenaFecha2 )
   
   		//Obtiene objetos Date
   		var miFecha1 = new Date( fechai.anio, fechai.mes, fechai.dia )
   		var miFecha2 = new Date( fechaf.anio, fechaf.mes, fechaf.dia )

   		//Resta fechas y redondea
   		var diferencia = miFecha2.getTime() - miFecha1.getTime()
   		dias = Math.floor(diferencia / (1000 * 60 * 60 * 24))
   		var segundos = Math.floor(diferencia / 1000)
   		if(dias>15){	
   		return 1;
  		}else{
  			return 2;
  		}
	}
	
	function fechaMayorOIgualQue(fechai, fechaf){ 
	    var bRes = false; 
	    var sDia0 = fechai.value.substr(0, 2); 
	    var sMes0 = fechai.value.substr(3, 2); 
	    var sAno0 = fechai.value.substr(6, 4); 
	    var sDia1 = fechaf.value.substr(0, 2); 
	    var sMes1 = fechaf.value.substr(3, 2); 
	    var sAno1 = fechaf.value.substr(6, 4); 
	    if (sAno0 > sAno1) bRes = true; 
	    else { 
	     if (sAno0 == sAno1){ 
	      if (sMes0 > sMes1) bRes = true; 
	      else { 
	       if (sMes0 == sMes1) 
	        if (sDia0 >= sDia1) bRes = true; 
	      } 
	     } 
	    } 
	    return bRes; 
   }
   
 	function fecha( cadena ) {

  		 //Separador para la introduccion de las fechas
  		 var separador = "/"

  		 //Separa por dia, mes y a?o
  		 if ( cadena.indexOf( separador ) != -1 ) {
	        var posi1 = 0
	        var posi2 = cadena.indexOf( separador, posi1 + 1 )
	        var posi3 = cadena.indexOf( separador, posi2 + 1 )
	        this.dia = cadena.substring( posi1, posi2 )
	        this.mes = cadena.substring( posi2 + 1, posi3 )
	        this.anio = cadena.substring( posi3 + 1, cadena.length )
	  	} else {
	        this.dia = 0
	        this.mes = 0
	        this.anio = 0   
  		 }
	}  		   	 	