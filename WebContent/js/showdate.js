	//-----------------------------------------------------------------------
	//	--- Datetime.js
	//	--- Copyright (c) 1997-2003, Draf Designs
	//	--- http://drafdesigns.com	info@drafdesigns.com
	//	--- Author: Demetrius Francis
	//	--- EULA:	Freeware - ALL COPYRIGHT LINES MUST ACCOMPANY THE SCRIPT.
	//	--- Revised: October 7, 2003
	//-----------------------------------------------------------------------

	var aDay	= new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado");
	var aMonth	= new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre")


	function DateFormat(xdate,x)
		{
		/*	
			Date Format function to be used in internally by the Showdate function.
			Returns either: d, dd, ddd, dddd, m, mm, mmm, mmmm, y, yy, yyy, yyyy.
			eg. Sun Sep 28 09:22:08 EDT 2003  returns  28, 28, Sun., Sunday, 9, 09, Sep., September, 03, 03, 03, 2003
				Sun Sep 28 09:22:08 EDT 2003 = 28 28 Sun. Sunday, 9 09 Sep. September, 03 03 03 2003   
		*/
		x = x.toLowerCase();
		return (((x == "d")  ? xdate.getDate() : ((x == "dd") ? ((xdate.getDate() <= 9) ? "0"+xdate.getDate() : xdate.getDate()) : ((x == "ddd") ? aDay[xdate.getDay()].substring(0,3)+". " : ((x == "dddd") ? aDay[xdate.getDay()]+", " : ((x == "m")  ? xdate.getMonth()+1 : ((x == "mm") ? (((xdate.getMonth()+1) <= 9) ? "0"+(xdate.getMonth()+1) : xdate.getMonth()+1) : ((x == "mmm") ? aMonth[xdate.getMonth()].substring(0,3) : ((x == "mmmm") ? aMonth[xdate.getMonth()] : ((x == "y" || x == "yy" || x == "yyy") ? xdate.getFullYear().toString().substring(2,4) : ((x == "yyyy") ? xdate.getFullYear().toString() : "")))))))))))
		}


	function Showdate(_date, _var1, _var2, _var3, _var4, _del)
		{
		// ----------------------------------------------------------------------------------------
		// Title:	Showdate()
		// Author:	Draf Designs  draf@angelfire.com
		// Legal:	© 1998-2000, Draf Designs
		// EULA:	Open Source - These lines must always accompany this script 
		// ----------------------------------------------------------------------------------------
		// Content:	Date formatter where _month = month, _day = day of week spelled out, yyyy = four digit year. 
		// The script will only display a day spelled out if the parameter is ddd, or dddd. dd or d means do not display the day.
		//
		//	Syntax: document.writeln(Showdate(date object, "ddd", "mmm", "dd", "yyyy", "-"));
		//		where date can be a field variable = rs("Datecreated"); constant= new Date(yyyy,m,dd); 
		//
		//	Results in: Thursday, January-01-1970  NOTE that the numeric month in Javascript is from 0-11
		//
		// timerID=setInterval("if (_ie){++nd; if (nd > xStyle.length) nd=0; document.all.md.innerHTML=Showdate(null, "mmm", "ddd", "yy" , "-");}",3000)
		// timerOn=true
		// ----------------------------------------------------------------------------------------

		var today	= (_date == null) ? new Date() : new Date(_date);
		_del =  ((_del == null) ? " " : _del);
		return ( DateFormat(today, _var1) + DateFormat(today, _var2) + ((_var2 != "")? _del : "") + DateFormat(today, _var3) + ((_var3 != "")? _del : "") + DateFormat(today, _var4))
		}



