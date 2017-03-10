// app_menu.js

// newFunction
function mmLoadMenus() {
	  codeVendedores = "vendedores";
	  codePuntosVenta ="puntosVenta";
	  codeRegiones="regiones";
	  if (window.mm_menu_0) return;
	  //Menu(label, mw, mh, fnt, fs, fclr, fhclr, bg, bgh, halgn, valgn, pad, space, to, sx, sy, srel, opq, vert, idt, aw, ah) 
	  window.mm_menu_0 = new Menu("root",207,16,"Verdana, Arial, Helvetica, sans-serif",10,"#333333","#cc0000","#f5f2f2","#999999","left","middle",3,0,500,-5,7,true,true,true,0,true,true);
	  mm_menu_0.addMenuItem("Nuevo&nbsp;Punto&nbsp;de&nbsp;Venta","location='PuntosVentaAction.do?action=home'");
	  mm_menu_0.addMenuItem("Modificar&nbsp;Punto&nbsp;de&nbsp;Venta","location='PuntosVentaAction.do?action=home'");
	  mm_menu_0.addMenuItem("Detalles&nbsp;Punto&nbsp;de&nbsp;Venta","location='PuntosVentaAction.do?action=home'");
	  mm_menu_0.fontWeight="plain";
	  mm_menu_0.hideOnMouseOut=true;
	  mm_menu_0.menuBorder=1;
	  mm_menu_0.menuLiteBgColor='#000000';
	  mm_menu_0.menuBorderBgColor='#CCCCCC';
	  mm_menu_0.bgColor='#CCCCCC'; 
	  window.mm_menu_1 = new Menu("root",207,16,"Verdana, Arial, Helvetica, sans-serif",10,"#333333","#cc0000","#f5f2f2","#999999","left","middle",3,0,500,30,7,true,true,true,0,true,true);
	  mm_menu_1.addMenuItem("Nuevo&nbsp;Vendedor","location='VendedoresAction.do?action=home'");
	  mm_menu_1.addMenuItem("Modificar&nbsp;Vendedor","location='VendedoresAction.do?action=home'");
	  mm_menu_1.addMenuItem("Detalles&nbsp;Vendedor","location='TablaCatalogoAction.do?action=vendedores'");
	  mm_menu_1.fontWeight="plain";
	  mm_menu_1.hideOnMouseOut=true;
	  mm_menu_1.menuBorder=1;
	  mm_menu_1.menuLiteBgColor='#000000';
	  mm_menu_1.menuBorderBgColor='#CCCCCC';
	  mm_menu_1.bgColor='#CCCCCC';
	  window.mm_menu_2 = new Menu("root",207,16,"Verdana, Arial, Helvetica, sans-serif",10,"#333333","#cc0000","#f5f2f2","#999999","left","middle",3,0,500,30,7,true,true,true,0,true,true);
	  mm_menu_2.addMenuItem("Puestos&nbsp;","location='PuestosAction.do?action=home'");
	  mm_menu_2.addMenuItem("Regiones&nbsp;","location='RegionesAction.do?action=home'");
	  mm_menu_2.addMenuItem("Tipos&nbsp;de&nbsp;Canal&nbsp;","location='TipoCanalesAction.do?action=home'");
	  mm_menu_2.addMenuItem("Canales&nbsp;","location='CanalesAction.do?action=home'");
	  mm_menu_2.fontWeight="plain";
	  mm_menu_2.hideOnMouseOut=true;
	  mm_menu_2.menuBorder=1;
	  mm_menu_2.menuLiteBgColor='#000000';
	  mm_menu_2.menuBorderBgColor='#CCCCCC';
	  mm_menu_2.bgColor='#CCCCCC';
	  window.mm_menu_3 = new Menu("root",207,16,"Verdana, Arial, Helvetica, sans-serif",10,"#333333","#cc0000","#f5f2f2","#999999","left","middle",3,0,500,30,7,true,true,true,0,true,true);
	  mm_menu_3.addMenuItem("Revisar&nbsp;","location='comisiones_detalle.html'");
	  mm_menu_3.fontWeight="plain";
	  mm_menu_3.hideOnMouseOut=true;
	  mm_menu_3.menuBorder=1;
	  mm_menu_3.menuLiteBgColor='#000000';
	  mm_menu_3.menuBorderBgColor='#CCCCCC';
	  mm_menu_3.bgColor='#CCCCCC';
	  mm_menu_3.writeMenus();
	} // mmLoadMenus()
