
	function changePcFchAlta(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcFchAlta").innerHTML = valor);
		if (document.all) return(document.all.divPcFchAlta.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcFchAlta").innerHTML = valor);
		return false;
	}

	function changePcFchBaja(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcFchBaja").innerHTML = valor);
		if (document.all) return(document.all.divPcFchBaja.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcFchBaja").innerHTML = valor);
		return false;
	}

	function changePcDigVerif(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcDigVerif").innerHTML = valor);
		if (document.all) return(document.all.divPcDigVerif.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcDigVerif").innerHTML = valor);
		return false;
	}

	function changePcCiudad(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcCiudad").innerHTML = valor);
		if (document.all) return(document.all.divPcCiudad.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcCiudad").innerHTML = valor);
		return false;
	}

	function changePcEstado(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcEstado").innerHTML = valor);
		if (document.all) return(document.all.divPcEstado.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcEstado").innerHTML = valor);
		return false;
	}

	function changePcDireccion(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcDireccion").innerHTML = valor);
		if (document.all) return(document.all.divPcDireccion.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcDireccion").innerHTML = valor);
		return false;
	}

	function changePcColonia(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcColonia").innerHTML = valor);
		if (document.all) return(document.all.divPcColonia.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcColonia").innerHTML = valor);
		return false;
	}

	function changePcCp(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcCp").innerHTML = valor);
		if (document.all) return(document.all.divPcCp.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcCp").innerHTML = valor);
		return false;
	}

	function changePcTel(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcTel").innerHTML = valor);
		if (document.all) return(document.all.divPcTel.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcTel").innerHTML = valor);
		return false;
	}

	function changePcFax(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcFax").innerHTML = valor);
		if (document.all) return(document.all.divPcFax.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcFax").innerHTML = valor);
		return false;
	}

	function changePcCveVendedor(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcClaveCompleta").innerHTML = valor);
		if (document.all) return(document.all.divPcClaveCompleta.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcClaveCompleta").innerHTML = valor);
		return false;
	}

	function changePcNombreVendedor(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcNombreVendedor").innerHTML = valor);
		if (document.all) return(document.all.divPcNombreVendedor.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcNombreVendedor").innerHTML = valor);
		return false;
	}

	function changePcCvePuesto(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcCvePuesto").innerHTML = valor);
		if (document.all) return(document.all.divPcCvePuesto.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcCvePuesto").innerHTML = valor);
		return false;
	}

	function changePcDescPuesto(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcDescPuesto").innerHTML = valor);
		if (document.all) return(document.all.divPcDescPuesto.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcDescPuesto").innerHTML = valor);
		return false;
	}

	function checkPcCvePtoventas(valor){
		itemName = prefijo + valor;
		hiddenItem      = FIND(itemName);
		clave = valor;
		if(clave == null || clave == ''){
			return false;
		}else{return true;}
	}
	
	function changePcCvePtoventas(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcCvePtoventas").innerHTML = valor);
		if (document.all) return(document.all.divPcCvePtoventas.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcCvePtoventas").innerHTML = valor);
		return false;
	}

	function changePcNomPtoventas(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcNomPtoventas").innerHTML = valor);
		if (document.all) return(document.all.divPcNomPtoventas.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcNomPtoventas").innerHTML = valor);
		return false;
	}
	function changePcCveCanal(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcCveCanal").innerHTML = valor);
		if (document.all) return(document.all.divPcCveCanal.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcCveCanal").innerHTML = valor);
		return false;
	}
	function changePcDescCanal(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcDescCanal").innerHTML = valor);
		if (document.all) return(document.all.divPcDescCanal.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcDescCanal").innerHTML = valor);
		return false;
	}

	function changePcCveRegion(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcCveRegion").innerHTML = valor);
		if (document.all) return(document.all.divPcCveRegion.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcCveRegion").innerHTML = valor);
		return false;
	}

	function changePcDescRegion(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcDescRegion").innerHTML = valor);
		if (document.all) return(document.all.divPcDescRegion.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcDescRegion").innerHTML = valor);
		return false;
	}
	function changePcCveTpCanal(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcCveTpCanal").innerHTML = valor);
		if (document.all) return(document.all.divPcCveTpCanal.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcCveTpCanal").innerHTML = valor);
		return false;
	}

	function changePcDescTpCanal(valor){
		if( window.mmIsOpera ) return(document.getElementById("divPcDescTpCanal").innerHTML = valor);
		if (document.all) return(document.all.divPcDescTpCanal.innerHTML = valor);
		if (document.getElementById) return(document.getElementById("divPcDescTpCanal").innerHTML = valor);
		return false;
	}

	function updateTienda1(valor){
		if(checkPcCvePtoventas(valor,'pcCvePtoventas')== true){
			changePcCvePtoventas(valor,'pcCvePtoventas');
			changePcNomPtoventas(valor,'pcNomPtoventas');
			//changePcCveCanal(valor,'pcCveCanal');
			changePcDescCanal(valor,'pcDescCanal');
			//changePcCveRegion(valor,'pcCveRegion');
			changePcDescRegion(valor,'pcDescRegion');
			//changePcCveTpCanal(valor,'pcCveTpCanal');
			changePcDescTpCanal(valor,'pcDescTpCanal');
			if( window.mmIsOpera ){
				document.getElementById("Tienda").style.visibility = 'visible';
			}
			if (document.all){
				document.all.Tienda.style.visibility = 'visible';
			}
			if (document.getElementById){
				document.getElementById("Tienda").style.visibility = 'visible';
			}
		}else{
			if( window.mmIsOpera ) (document.getElementById("Tienda").style.visibility = 'hidden');
			if (document.all) (document.all.Tienda.style.visibility = 'hidden');
			if (document.getElementById) (document.getElementById("Tienda").style.visibility = 'hidden');
		}
	}

	function updateTable1(valor){
		changePcFchAlta(valor,'pcFchAlta');
		changePcFchBaja(valor,'pcFchBaja');
		//changePcDigVerif(valor,'pcDigVerif');
		changePcCiudad(valor,'pcCiudad');
		changePcEstado(valor,'pcEstado');
		changePcDireccion(valor,'pcDireccion');
		changePcColonia(valor,'pcColonia');
		changePcCp(valor,'pcCp');
		changePcTel(valor,'pcTel');
		changePcFax(valor,'pcFax');
		changePcCveVendedor(valor,'pcClaveCompleta');
		changePcNombreVendedor(valor,'pcNombreVendedor');
		//changePcCvePuesto(valor,'pcCvePuesto');
		changePcDescPuesto(valor,'pcDescPuesto');
		if( window.mmIsOpera ) (document.getElementById("Tienda").style.visibility = 'hidden');
		if (document.all) (document.all.Tienda.style.visibility = 'hidden');
		if (document.getElementById) (document.getElementById("Tienda").style.visibility = 'hidden');
	}

	
	function updateTable(valor){
		var elemento = (-1);
		for(i=0; i< detalle.length; i++){
			if(detalle[i][0]== valor){
				elemento=i;
				break;
			}
		}
		
		if(elemento != (-1)){
			vendedor = detalle[i][1];
			changePcCveVendedor(vendedor[0]);
			changePcNombreVendedor(vendedor[1]);
			//changePcCvePuesto(vendedor[2]);
			changePcDescPuesto(vendedor[3]);
			changePcFchAlta(vendedor[4]);
			changePcFchBaja(vendedor[5]);
			//changePcDigVerif(vendedor[6]);
			changePcCiudad(vendedor[7]);
			changePcEstado(vendedor[8]);
			changePcDireccion(vendedor[9]);
			changePcColonia(vendedor[10]);
			changePcCp(vendedor[11]);
			changePcTel(vendedor[12]);
			changePcFax(vendedor[13]);
		}else{
			changePcCveVendedor("");
			changePcNombreVendedor("");
			//changePcCvePuesto("");
			changePcDescPuesto("");
			changePcFchAlta("");
			changePcFchBaja("");
			//changePcDigVerif("");
			changePcCiudad("");
			changePcEstado("");
			changePcDireccion("");
			changePcColonia("");
			changePcCp("");
			changePcTel("");
			changePcFax("");
		}
		if( window.mmIsOpera ) (document.getElementById("Tienda").style.visibility = 'hidden');
		if (document.all) (document.all.Tienda.style.visibility = 'hidden');
		if (document.getElementById) (document.getElementById("Tienda").style.visibility = 'hidden');
	}

	function updateTienda(valor){

		var elemento = (-1);
		for(i=0; i< detalleTienda.length; i++){
			if(detalleTienda[i][0]== valor){
				elemento=i;
				break;
			}
		}

		if(elemento != (-1)){
			tienda = detalleTienda[i][1];
			if(tienda[0] != null && tienda[0] != ''){
				changePcCvePtoventas(tienda[0]);
				changePcNomPtoventas(tienda[1]);
				//changePcCveCanal(tienda[2]);
				changePcDescCanal(tienda[3]);
				//changePcCveRegion(tienda[4]);
				changePcDescRegion(tienda[5]);
				//changePcCveTpCanal(tienda[6]);
				changePcDescTpCanal(tienda[7]);
				if( window.mmIsOpera ){
					document.getElementById("Tienda").style.visibility = 'visible';
				}
				if (document.all){
					document.all.Tienda.style.visibility = 'visible';
				}
				if (document.getElementById){
					document.getElementById("Tienda").style.visibility = 'visible';
				}
			}else{
				if( window.mmIsOpera ) (document.getElementById("Tienda").style.visibility = 'hidden');
				if (document.all) (document.all.Tienda.style.visibility = 'hidden');
				if (document.getElementById) (document.getElementById("Tienda").style.visibility = 'hidden');
			}
		}else{
			changePcCvePtoventas("");
			changePcNomPtoventas("");
			//changePcCveCanal("");
			changePcDescCanal("");
			//changePcCveRegion("");
			changePcDescRegion("");
			//changePcCveTpCanal("");
			changePcDescTpCanal("");

			if( window.mmIsOpera ) (document.getElementById("Tienda").style.visibility = 'hidden');
			if (document.all) (document.all.Tienda.style.visibility = 'hidden');
			if (document.getElementById) (document.getElementById("Tienda").style.visibility = 'hidden');
		}
	}

	////////////////Reporte Vendedor Punto Venta
	function updateTablePtoVenta(valor){

		var elemento = (-1);
		for(i=0; i< detalle.length; i++){
			if(detalle[i][0]== valor){
				elemento=i;
				break;
			}
		}
		
		if(elemento != (-1)){
			vendedor = detalle[i][1];
			changePcCveVendedor(vendedor[0]);
			changePcNombreVendedor(vendedor[1]);
			//changePcCvePuesto(vendedor[2]);
			changePcDescPuesto(vendedor[3]);
			changePcFchAlta(vendedor[4]);
			changePcFchBaja(vendedor[5]);
			//changePcDigVerif(vendedor[6]);
			changePcCiudad(vendedor[7]);
			changePcEstado(vendedor[8]);
			changePcDireccion(vendedor[9]);
			changePcColonia(vendedor[10]);
			changePcCp(vendedor[11]);
			changePcTel(vendedor[12]);
			changePcFax(vendedor[13]);
		}else{
			changePcCveVendedor("");
			changePcNombreVendedor("");
			//changePcCvePuesto("");
			changePcDescPuesto("");
			changePcFchAlta("");
			changePcFchBaja("");
			//changePcDigVerif("");
			changePcCiudad("");
			changePcEstado("");
			changePcDireccion("");
			changePcColonia("");
			changePcCp("");
			changePcTel("");
			changePcFax("");
		}
		if( window.mmIsOpera ) (document.getElementById("Tienda").style.visibility = 'hidden');
		if (document.all) (document.all.Tienda.style.visibility = 'hidden');
		if (document.getElementById) (document.getElementById("Tienda").style.visibility = 'hidden');
	}

	function updateTiendaPtoVenta(valor){

		var elemento = (-1);
		for(i=0; i< detalleTienda.length; i++){
			if(detalleTienda[i][0]== valor){
				elemento=i;
				break;
			}
		}

		if(elemento != (-1)){
			tienda = detalleTienda[i][1];
			if(tienda[0] != null && tienda[0] != ''){
				changePcCvePtoventas(tienda[0]);
				changePcNomPtoventas(tienda[1]);
				//changePcCveCanal(tienda[2]);
				changePcDescCanal(tienda[3]);
				//changePcCveRegion(tienda[4]);
				changePcDescRegion(tienda[5]);
				//changePcCveTpCanal(tienda[6]);
				changePcDescTpCanal(tienda[7]);
				if( window.mmIsOpera ){
					document.getElementById("Tienda").style.visibility = 'visible';
				}
				if (document.all){
					document.all.Tienda.style.visibility = 'visible';
				}
				if (document.getElementById){
					document.getElementById("Tienda").style.visibility = 'visible';
				}
			}else{
				if( window.mmIsOpera ) (document.getElementById("Tienda").style.visibility = 'hidden');
				if (document.all) (document.all.Tienda.style.visibility = 'hidden');
				if (document.getElementById) (document.getElementById("Tienda").style.visibility = 'hidden');
			}
		}else{
			changePcCvePtoventas("");
			changePcNomPtoventas("");
			//changePcCveCanal("");
			changePcDescCanal("");
			//changePcCveRegion("");
			changePcDescRegion("");
			//changePcCveTpCanal("");
			changePcDescTpCanal("");

			if( window.mmIsOpera ) (document.getElementById("Tienda").style.visibility = 'hidden');
			if (document.all) (document.all.Tienda.style.visibility = 'hidden');
			if (document.getElementById) (document.getElementById("Tienda").style.visibility = 'hidden');
		}
	}

