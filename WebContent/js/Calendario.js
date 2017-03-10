
var weekend = [0,6]; 
var weekendColor = "#DAF2FE";
var fontface = "Arial";
var fontsize = 2;

var gNow = new Date();
var ggWinCal;
isNav = (navigator.appName.indexOf("Netscape") != -1) ? true : false;
isIE = (navigator.appName.indexOf("Microsoft") != -1) ? true : false;

// mensajes 
Calendar.Meses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"];
Calendar.Dias = ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"];
Calendar.titulo = "Calendario";
Calendar.imagenFondo = "images/fondo_calendario.jpg";
Calendar.imagenAtrasaAno = "images/atrasano.gif";
Calendar.imagenAtrasaMes = "images/atrasmes.gif";
Calendar.imagenAdelantaMes = "images/adelasmes.gif";
Calendar.imagenAdelantaAno = "images/adelasano.gif";


// Años normales
Calendar.DOMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
// Años bisiestos
Calendar.lDOMonth = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

function Calendar(p_item, p_WinCal, p_month, p_year){ 
	if ((p_month == null) && (p_year == null)) return;

	if (p_WinCal == null)
		this.gWinCal = ggWinCal;
	else
		this.gWinCal = p_WinCal;
	
	if (p_month == null) {
		this.gMonthName = null;
		this.gMonth = null;
		this.gYearly = true;
	} else {
		this.gMonthName = Calendar.get_month(p_month); 
		this.gMonth = new Number(p_month);
		this.gYearly = false;
	}

	this.gYear = p_year;
	this.gFormat = 'DD-MM-YYYY';
	this.gLanguage = 'es';
	this.gBGColor = "white";
	this.gFGColor = "black";
	this.gTextColor = "black";
	this.gHeaderColor = "black";
	this.gReturnItem = p_item;
}

Calendar.get_month = Calendar_get_month;
Calendar.get_daysofmonth = Calendar_get_daysofmonth;
Calendar.calc_month_year = Calendar_calc_month_year;
Calendar.print = Calendar_print;

function Calendar_get_month(monthNo) {
	return Calendar.Meses[monthNo];
}
function Calendar_get_daysofmonth(monthNo, p_year) {
	if ((p_year % 4) == 0) {
		if ((p_year % 100) == 0 && (p_year % 400) != 0)
			return Calendar.DOMonth[monthNo];
	
		return Calendar.lDOMonth[monthNo];
	} else
		return Calendar.DOMonth[monthNo];
}

function Calendar_calc_month_year(p_Month, p_Year, incr) {
	var ret_arr = new Array();
	
	if (incr == -1) {
		if (p_Month == 0) {
			ret_arr[0] = 11;
			ret_arr[1] = parseInt(p_Year) - 1;
		}
		else {
			ret_arr[0] = parseInt(p_Month) - 1;
			ret_arr[1] = parseInt(p_Year);
		}
	} else if (incr == 1) {
		if (p_Month == 11) {
			ret_arr[0] = 0;
			ret_arr[1] = parseInt(p_Year) + 1;
		}
		else {
			ret_arr[0] = parseInt(p_Month) + 1;
			ret_arr[1] = parseInt(p_Year);
		}
	}
	return ret_arr;
}

function Calendar_print() {
	ggWinCal.print();
}


new Calendar();

Calendar.prototype.getMonthlyCalendarCode = function() {
	var vCode = "";
	var vHeader_Code = "";
	var vData_Code = "";
	
	// Crea la tabla
	vCode = vCode + "<TABLE BORDER=0 >";
	
	vHeader_Code = this.cal_header();
	vData_Code = this.cal_data();
	vCode = vCode + vHeader_Code + vData_Code;
	
	vCode = vCode + "</TABLE>";
	
	return vCode;
}

Calendar.prototype.show = function() {
	var vCode = "";
	
	this.gWinCal.document.open();
	
	var strTitulo = null;
	strTitulo = Calendar.titulo;

	this.wwrite("<html>");
	this.wwrite("<head><title>" + strTitulo + "</title>");
	this.wwrite("</head>");
	
	var strImagenFondo = null;
	strImagenFondo = Calendar.imagenFondo;

	this.wwrite("<body background='" + strImagenFondo + "' " + 
		"link=\"" + this.gLinkColor + "\" " + 
		"vlink=\"" + this.gLinkColor + "\" " +
		"alink=\"" + this.gLinkColor + "\" " +
		"text=\"" + this.gTextColor + "\">");
	this.wwriteA("<br><center><FONT FACE='" + fontface + "' SIZE=2 color=#00537B><B>");
	this.wwriteA(this.gMonthName + " " + this.gYear);
	this.wwriteA("</B></center>");

	// Coloca las barras de navegacion
	var prevMMYYYY = Calendar.calc_month_year(this.gMonth, this.gYear, -1);
	var prevMM = prevMMYYYY[0];
	var prevYYYY = prevMMYYYY[1];

	var nextMMYYYY = Calendar.calc_month_year(this.gMonth, this.gYear, 1);
	var nextMM = nextMMYYYY[0];
	var nextYYYY = nextMMYYYY[1];
	
	var strPathAtrasaAno = Calendar.imagenAtrasaAno;
	var strPathAtrasaMes = Calendar.imagenAtrasaMes;
	var strPathAdelantaMes = Calendar.imagenAdelantaMes;
	var strPathAdelantaAno = Calendar.imagenAdelantaAno;
	
	this.wwrite("<TABLE WIDTH='100%' bgcolor=white bordercolor=black BORDER=1 CELLSPACING=0 CELLPADDING=0'><TR><TD ALIGN=center VALIGN=bottom>");
	this.wwrite("<A HREF=\"" +
		"javascript:window.opener.Build(" + 
		"'" + this.gReturnItem + "', '" + this.gMonth + "', '" + (parseInt(this.gYear)-1) + "', '" + this.gFormat + "', '" + this.gLanguage + "'" + 
		");" +
		"\"><img src=" + strPathAtrasaAno + " border=0><\/A></TD><TD ALIGN=center VALIGN=bottom>");
	this.wwrite("<A HREF=\"" +
		"javascript:window.opener.Build(" + 
		"'" + this.gReturnItem + "', '" + prevMM + "', '" + prevYYYY + "', '" + this.gFormat + "', '" + this.gLanguage + "'" +
		");" +
		"\"><img src=" + strPathAtrasaMes + " border=0><\/A></TD><TD ALIGN=center VALIGN=bottom>");
	this.wwrite("<A HREF=\"" +
		"javascript:window.opener.Build(" + 
		"'" + this.gReturnItem + "', '" + nextMM + "', '" + nextYYYY + "', '" + this.gFormat + "', '" + this.gLanguage + "'" +
		");" +
		"\"><img src=" + strPathAdelantaMes + " border=0><\/A></TD><TD ALIGN=center VALIGN=bottom>");
	this.wwrite("<A HREF=\"" +
		"javascript:window.opener.Build(" + 
		"'" + this.gReturnItem + "', '" + this.gMonth + "', '" + (parseInt(this.gYear)+1) + "', '" + this.gFormat + "', '" + this.gLanguage + "'" +
		");" +
		"\"><img src=" + strPathAdelantaAno + " border=0><\/A></TD></TR></TABLE><BR>");

	vCode = this.getMonthlyCalendarCode();
	this.wwrite(vCode);

	this.wwrite("</font></body></html>");
	this.gWinCal.document.close();
}

Calendar.prototype.showY = function() {
	var vCode = "";
	var i;
	var vr, vc, vx, vy;		// Renglon, columna, coordenadas en x, coordenadas en y
	var vxf = 285;			// factor de X
	var vyf = 200;			// factor de Y
	var vxm = 10;			// margen en X
	var vym;				// margen en Y
	if (isIE)	vym = 75;
	else if (isNav)	vym = 25;
	
	this.gWinCal.document.open();

	this.wwrite("<html>");
	this.wwrite("<head><title>Calendar</title>");
	this.wwrite("<style type='text/css'>\n<!--");
	for (i=0; i<12; i++) {
		vc = i % 3;
		if (i>=0 && i<= 2)	vr = 0;
		if (i>=3 && i<= 5)	vr = 1;
		if (i>=6 && i<= 8)	vr = 2;
		if (i>=9 && i<= 11)	vr = 3;
		
		vx = parseInt(vxf * vc) + vxm;
		vy = parseInt(vyf * vr) + vym;

		this.wwrite(".lclass" + i + " {position:absolute;top:" + vy + ";left:" + vx + ";}");
	}
	this.wwrite("-->\n</style>");
	this.wwrite("</head>");

	this.wwrite("<body background='Logo.gif'" + 
		"link=\"" + this.gLinkColor + "\" " + 
		"vlink=\"" + this.gLinkColor + "\" " +
		"alink=\"" + this.gLinkColor + "\" " +
		"text=\"" + this.gTextColor + "\">");
	this.wwrite("<FONT FACE='" + fontface + "' SIZE=2><B>");
	this.wwrite("Year : " + this.gYear);
	this.wwrite("</B><BR>");

	var prevYYYY = parseInt(this.gYear) - 1;
	var nextYYYY = parseInt(this.gYear) + 1;
	
	this.wwrite("<TABLE WIDTH='100%' BORDER=  CELLSPACING=0 CELLPADDING=0'><TR><TD ALIGN=center>");
	this.wwrite("[<A HREF=\"" +
		"javascript:window.opener.Build(" + 
		"'" + this.gReturnItem + "', null, '" + prevYYYY + "', '" + this.gFormat + "'" +
		");" +
		"\" alt='Prev Year'>ioo<\/A>]</TD><TD ALIGN=center>");
	this.wwrite("[<A HREF=\"javascript:window.print();\">Print</A>]</TD><TD ALIGN=center>");
	this.wwrite("[<A HREF=\"" +
		"javascript:window.opener.Build(" + 
		"'" + this.gReturnItem + "', null, '" + nextYYYY + "', '" + this.gFormat + "'" +
		");" +
		"\">ioo<\/A>]</TD></TR></TABLE><BR>");

	var j;
	for (i=11; i>=0; i--) {
		if (isIE)
			this.wwrite("<DIV ID=\"layer" + i + "\" CLASS=\"lclass" + i + "\">");
		else if (isNav)
			this.wwrite("<LAYER ID=\"layer" + i + "\" CLASS=\"lclass" + i + "\">");

		this.gMonth = i;
		this.gMonthName = Calendar.get_month(this.gMonth, this.gLanguage);
		vCode = this.getMonthlyCalendarCode();
		this.wwrite(this.gMonthName + "/" + this.gYear + "<BR>");
		this.wwrite(vCode);

		if (isIE)
			this.wwrite("</DIV>");
		else if (isNav)
			this.wwrite("</LAYER>");
	}

	this.wwrite("</font><BR></body></html>");
	this.gWinCal.document.close();
}

Calendar.prototype.wwrite = function(wtext) {
	this.gWinCal.document.writeln(wtext);
}

Calendar.prototype.wwriteA = function(wtext) {
	this.gWinCal.document.write(wtext);
}

Calendar.prototype.cal_header = function() {
	var vCode = "";
	var arrayDays = Calendar.Dias;
	
	vCode = vCode + "<TR>";
    vCode = vCode + "<TD WIDTH='14%'><FONT SIZE='2' FACE='" + fontface + "' COLOR='" + this.gHeaderColor + "'><B>" + arrayDays[0] + "</B></FONT></TD>";
	vCode = vCode + "<TD WIDTH='14%'><FONT SIZE='2' FACE='" + fontface + "' COLOR='" + this.gHeaderColor + "'><B>" + arrayDays[1] + "</B></FONT></TD>";
	vCode = vCode + "<TD WIDTH='14%'><FONT SIZE='2' FACE='" + fontface + "' COLOR='" + this.gHeaderColor + "'><B>" + arrayDays[2] + "</B></FONT></TD>";
	vCode = vCode + "<TD WIDTH='14%'><FONT SIZE='2' FACE='" + fontface + "' COLOR='" + this.gHeaderColor + "'><B>" + arrayDays[3] + "</B></FONT></TD>";
	vCode = vCode + "<TD WIDTH='14%'><FONT SIZE='2' FACE='" + fontface + "' COLOR='" + this.gHeaderColor + "'><B>" + arrayDays[4] + "</B></FONT></TD>";
	vCode = vCode + "<TD WIDTH='14%'><FONT SIZE='2' FACE='" + fontface + "' COLOR='" + this.gHeaderColor + "'><B>" + arrayDays[5] + "</B></FONT></TD>";
   	vCode = vCode + "<TD WIDTH='16%'><FONT SIZE='2' FACE='" + fontface + "' COLOR='" + this.gHeaderColor + "'><B>" + arrayDays[6] + "</B></FONT></TD>";	  
	vCode = vCode + "</TR>";
	
	return vCode;
}

Calendar.prototype.cal_data = function() {
	var vDate = new Date();
	vDate.setDate(1);
	vDate.setMonth(this.gMonth);
	vDate.setFullYear(this.gYear);

	var vFirstDay=vDate.getDay();
	var vDay=1;
	var vLastDay=Calendar.get_daysofmonth(this.gMonth, this.gYear);
	var vOnLastDay=0;
	var vCode = "";

	vCode = vCode + "<TR>";
	for (i=0; i<vFirstDay; i++) {
		vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(i) + "><FONT SIZE='2' FACE='" + fontface + "'>&nbsp;</FONT></TD>";
	}

	// Primera semana
	for (j=vFirstDay; j<7; j++) {
		vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(j) + "><FONT SIZE='2' FACE='" + fontface + "'>" + 
			"<A HREF='#' " + 
				"onClick=\"self.opener.document." + this.gReturnItem + ".value='" + 
				this.format_data(vDay) + 
				"';window.close();\">" + 
				this.format_day(vDay) + 
			"</A>" + 
			"</FONT></TD>";
		vDay=vDay + 1;
	}
	vCode = vCode + "</TR>";

	// Las semanas restantes
	for (k=2; k<7; k++) {
		vCode = vCode + "<TR>";

		for (j=0; j<7; j++) {
			vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(j) + "><FONT SIZE='2' FACE='" + fontface + "'>" + 
				"<A HREF='#' " + 
					"onClick=\"self.opener.document." + this.gReturnItem + ".value='" + 
					this.format_data(vDay) + 
					"';window.close();\">" + 
				this.format_day(vDay) + 
				"</A>" + 
				"</FONT></TD>";
			vDay=vDay + 1;

			if (vDay > vLastDay) {
				vOnLastDay = 1;
				break;
			}
		}

		if (j == 6)
			vCode = vCode + "</TR>";
		if (vOnLastDay == 1)
			break;
	}
	
	// Llena los fines de semana 
	for (m=1; m<(7-j); m++) {
		if (this.gYearly)
			vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(j+m) + 
			"><FONT SIZE='2' FACE='" + fontface + "' COLOR='gray'>&nbsp;</FONT></TD>";
		else
			vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(j+m) + 
			"><FONT SIZE='2' FACE='" + fontface + "' COLOR='gray'>" + m + "</FONT></TD>";
	}
	
	return vCode;
}

Calendar.prototype.format_day = function(vday) {
	var vNowDay = gNow.getDate();
	var vNowMonth = gNow.getMonth();
	var vNowYear = gNow.getFullYear();

	if (this.gMonth == vNowMonth && vday == vNowDay && this.gYear == vNowYear)
		return ("<FONT COLOR=\"#CC0000\"><B>" + vday + "</B></FONT>");
	else
		return (vday);
}

Calendar.prototype.write_weekend_string = function(vday) {
	var i;

	// Regresa el formato especial para los dias de fin de semana.
	for (i=0; i<weekend.length; i++) {
		if (vday == weekend[i])
			return (" BGCOLOR=\"" + weekendColor + "\"");
	}
	
	return "";
}

Calendar.prototype.format_data = function(p_day) {
	var vData;
	var vMonth = 1 + this.gMonth;
	vMonth = (vMonth.toString().length < 2) ? "0" + vMonth : vMonth;
	var vY4 = new String(this.gYear);
	var vDD = (p_day.toString().length < 2) ? "0" + p_day : p_day;

	vData = vDD + "/" + vMonth + "/" + vY4;
	return vData;
}

function Build(p_item, p_month, p_year) { 
	var p_WinCal = ggWinCal;

	gCal = new Calendar(p_item, p_WinCal, p_month, p_year); 

	// En este punto se personaliza el calendario
	gCal.gBGColor="#143A65";
	gCal.gLinkColor="#143A65";
	gCal.gTextColor="#143A65";
	gCal.gHeaderColor="#143A65";

	if (gCal.gYearly)	gCal.showY();
	else	gCal.show();
}

function show_calendar() {
	p_item = arguments[0];
	p_month = new String(gNow.getMonth());
	p_year = new String(gNow.getFullYear().toString());

	vWinCal = window.open("", "Calendar", "width=250,height=250,status=no,resizable=no,top=200,left=200");
	
	vWinCal.opener = self;
	ggWinCal = vWinCal;

	Build(p_item, p_month, p_year);
}
