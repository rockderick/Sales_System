// this is the uncommented version of FormChek.js. You can find the 
//commented version in the root directory. The reason we are maintaining two versions is size and time for downloading.

var digits = "0123456789";
var lowercaseLetters = "abcdefghijklmnopqrstuvwxyz"
var uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
var whitespace = " \t\n\r";
var decimalPointDelimiter = "."
var mPrefix = "No ha capturado un dato necesario en el "
var mSuffix = " campo. Este dato es requerido. Por favor tecleelo ahora."
var sEmail = "Email"

var iEmail = "This field must be a valid email address (like foo@bar.com). Please reenter it now."
var iDay = "This field must be a day number between 1 and 31.  Please reenter it now."
var iMonth = "This field must be a month number between 1 and 12.  Please reenter it now."
var iYear = "This field must be a 2 or 4 digit year number.  Please reenter it now."
var iDatePrefix = "El Día, Mes y Año no forman una fecha válida. Por favor vuélvalos a capturar."
var pEntryPrompt = "Please enter a "
var pStateCode = "2 character code (like CA)."
var pEmail = "valid email address (like foo@bar.com)."
var pDay = "day number between 1 and 31."
var pMonth = "month number between 1 and 12."
var pYear = "2 or 4 digit year number."

var defaultEmptyOK = false

var msg = ""
var nline = "\n"
var typeA = "A"
var typeB = "B"
var typeI = "I"
var typeF = "F"
var typeX = "X"
var typeD = "D"
var typeC = "C"
var typeS = "S"
var typeH = "H"
var typeO = "O"
var typeZ = "Z"

var ProcessFormFlag

function failMsg(o, m)
{
     alert(m)
	 o.focus()
	 return false
}

//document.onkeypress = 
function checkTextEntry(form, field, e, len, type)
{
   //e = event
   // NS4 = e.which
   // IE  = e.keyCode
   // NS6 = e.charCode 
   // layers are no longer supported in NS6
   var c = document.layers ? e.which : document.all ? e.keyCode : e.charCode;
   var s = String.fromCharCode(c)
   var fieldVal = field.value
   var invalid = false
   var i, ok
   var ok_keys = new Array(8,9,12,13,16,17,18,19,20,27,144,0);
   // 8=backspace, 9=tab, 16-shift, 17=ctrl, 18=alt, 19=pause, 20=capslock,  
   // 27=esc,144=numlock, 12=center numpad key
   // 13=enter 'venkat added on 8/22 for the defect no88
   // 0=any edition or function key with netscape 6 'David R. 2/27/01
   for(i = 0; i < ok_keys.length; i++) 
   {                        
		if(c == ok_keys[i])
		{	
			ok = true;
			break;
		}
   }
   if (ok == true) return true;
   if (type == "A")	// alpha
   {
       // allow  space, and hyphen
	      //if(s == "'" && c == 39) {

		if ((s != "'") &&  (s != "-") && (c != 32))
       	{
			if (!isAlphabetic(s)) { invalid = true }
       		
		} else {
			if (!isAlphabetic(s) && (c!=32)) { invalid = true }
		}
	
   }
   else if (type == "I")	// integer
   {
	   if (e.shiftKey)
			invalid = true
       if (!isNonnegativeInteger(s)) // && ((c < 96) || (c > 105)))
           invalid = true
    
 
   }

   
   else if (type == "B")	// string
   {
       // Allow for "'", "-", ".", "#", "@" and blank space
	
	   if(s=="'" && c==39) {
       		if ((c != 189) && (c != 222) && (s != " ") && (c != 51)
        	  && (c != 190) && (c != 188) && (c != 32) && (c != 96) && (s != "@"))
       		{
				if (!isAlphanumeric(s) || (e.shiftKey && !isAlphabetic(s)))
					{ invalid = true } 
	       }
		}
   }
   else if (type == "F")	// float
   {  
   		if (e.shiftKey)
			invalid = true
       // Allow the decimal point
	   if ((s != ".") && (s != "-") && (c != 190))
       {
           if (!isFloat(s)) // && ((c < 96) || (c > 105))) 
           { invalid = true }
       }
   }
   else if (type == "D")	// date string
   {
		// allow integers, "-", and "/"	
		if (e.shiftKey)
			invalid = true
		if ((s != "/") && (s != "-") && (c != 189) && (c != 191) && !isNonnegativeInteger(s))
           invalid = true		
   }
   else if (type == "O")	// No debe permitir Apostrofe '
   {
		// permite todo excepto '
		//if (e.shiftKey)
			//invalid = true
		if ((s == "'") && (c == 39) /*&& (c != 191)*/ && !isNonnegativeInteger(s))
		   invalid = true		
		 
   }
   
  else if (type == "Z")	// alpha
   {
       // allow  space, and hyphen
	      //if(s == "'" && c == 39) {

		if ((s != "'") &&  (s != "-") && (c != 32) )
       	{
		   // alert ("El" + s);
			if (!isAlphabetic(s) && s!="*" && !isDigit (s))  { invalid = true }
       		
		} else {
			if (!isAlphabetic(s)) { invalid = true }
		}
	
   }   
   else if (type == "H")	// hour string
   {
		// allow integers, ":"
		//if (e.shiftKey)
			//invalid = true
		if ((s != ":") && (c != 189) /*&& (c != 191)*/ && !isNonnegativeInteger(s))
		   invalid = true		
		 
   }
   
   else if (type == "S")	// social security number
   {
		// allow integers and "-"
		if (e.shiftKey)
			invalid = true
		if ((s != "-") && (c != 189) && !isNonnegativeInteger(s))
           invalid = true		
   }   
   else if (type == "C")	// currency
   {  
   		if (e.shiftKey && c != 52)
			invalid = true
		if (e.shiftKey && c == 52 && fieldVal.indexOf("$", 0) != -1)
			invalid = true
       // Allow the decimal point
	   if ((c != 190) && (c != 110))
       {
           if ((!isFloat(s)) && ((c < 96) || (c > 105))) { invalid = true }
       }
   }
   if (invalid)
   {
		field.focus();
		return false;
   } else {
		s = field.value;
		var range;
		// Determine if we reached the maximum length of the field
		if (s.length == len)
		{
			// If range of characters is selected, allow text entry
			if (navigator.appName == "Netscape") 
			{
				range = document.getSelection();
				if (s == range.text) return true;
			} else {
				if (document.selection.type == "Text") 
				{
					range = document.selection.createRange();
					if (s == range.text) return true;
				}
			}
			// Otherwise, no more characters can be entered.
			return false;
		}
		return true;
	}
}

function makeArray(n) {
   for (var i = 1; i <= n; i++) {
      this[i] = 0
   } 
   return this
}

var daysInMonth = makeArray(12);
daysInMonth[1] = 31;
daysInMonth[2] = 29;   // must programmatically check this
daysInMonth[3] = 31;
daysInMonth[4] = 30;
daysInMonth[5] = 31;
daysInMonth[6] = 30;
daysInMonth[7] = 31;
daysInMonth[8] = 31;
daysInMonth[9] = 30;
daysInMonth[10] = 31;
daysInMonth[11] = 30;
daysInMonth[12] = 31;


function isEmpty(s)
{   return ((s == null) || (s.length == 0))
}

function isWhitespace (s)
{   var i;
    if (isEmpty(s)) return true;
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (whitespace.indexOf(c) == -1) return false;
    }
    return true;
}

function stripCharsInBag (s, bag)
{   var i;
    var returnString = "";
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function stripCharsNotInBag (s, bag)
{   var i;
    var returnString = "";
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (bag.indexOf(c) != -1) returnString += c;
    }
    return returnString;
}

function stripWhitespace (s)
{   return stripCharsInBag (s, whitespace)
}


function charInString (c, s)
{   for (i = 0; i < s.length; i++)
    {   if (s.charAt(i) == c) return true;
    }
    return false
}

function stripInitialWhitespace (s)
{   var i = 0;
    while ((i < s.length) && charInString (s.charAt(i), whitespace))
       i++;
    return s.substring (i, s.length);
}

function isLetter (c)
{   return ( ((c >= "a") && (c <= "z")) || ((c >= "A") && (c <= "Z"))
			|| c=="ñ" || c=="Ñ" || c=="á" || c=="é" || c=="í"
			|| c=="ó" || c=="ú" || c=="(" || c==")")
}

function isDigit (c)
{   return ((c >= "0") && (c <= "9"))
}

function isLetterOrDigit (c)
{   return (isLetter(c) || isDigit(c))
}

function isInteger (s)
{   var i;
    if (isEmpty(s)) 
       if (isInteger.arguments.length == 1) return defaultEmptyOK;
       else return (isInteger.arguments[1] == true);
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (!isDigit(c)) return false;
    }
    return true;
}

function isSignedInteger (s)
{   if (isEmpty(s)) 
       if (isSignedInteger.arguments.length == 1) return defaultEmptyOK;
       else return (isSignedInteger.arguments[1] == true);
    else {
        var startPos = 0;
        var secondArg = defaultEmptyOK;
        if (isSignedInteger.arguments.length > 1)
            secondArg = isSignedInteger.arguments[1];
        if ( (s.charAt(0) == "-") || (s.charAt(0) == "+") )
           startPos = 1;    
        return (isInteger(s.substring(startPos, s.length), secondArg))
    }
}

function isPositiveInteger (s)
{   var secondArg = defaultEmptyOK;
    if (isPositiveInteger.arguments.length > 1)
        secondArg = isPositiveInteger.arguments[1];
    return (isSignedInteger(s, secondArg)
         && ( (isEmpty(s) && secondArg)  || (parseInt (s) > 0) ) );
}

function isNonnegativeInteger (s)
{   var secondArg = defaultEmptyOK;
    if (isNonnegativeInteger.arguments.length > 1)
        secondArg = isNonnegativeInteger.arguments[1];
    return (isSignedInteger(s, secondArg)
         && ( (isEmpty(s) && secondArg)  || (parseInt (s) >= 0) ) );
}

function isNegativeInteger (s)
{   var secondArg = defaultEmptyOK;
    if (isNegativeInteger.arguments.length > 1)
        secondArg = isNegativeInteger.arguments[1];
    return (isSignedInteger(s, secondArg)
         && ( (isEmpty(s) && secondArg)  || (parseInt (s) < 0) ) );
}

function isNonpositiveInteger (s)
{   var secondArg = defaultEmptyOK;
    if (isNonpositiveInteger.arguments.length > 1)
        secondArg = isNonpositiveInteger.arguments[1];
    return (isSignedInteger(s, secondArg)
         && ( (isEmpty(s) && secondArg)  || (parseInt (s) <= 0) ) );
}

function isFloat (s)
{   var i;
    var seenDecimalPoint = false;
    if (isEmpty(s)) 
       if (isFloat.arguments.length == 1) return defaultEmptyOK;
       else return (isFloat.arguments[1] == true);
    if (s == decimalPointDelimiter) return false;
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if ((c == decimalPointDelimiter) && !seenDecimalPoint) seenDecimalPoint = true;
        else if (!isDigit(c)) return false;
    }
    return true;
}

function isSignedFloat (s)
{   if (isEmpty(s)) 
       if (isSignedFloat.arguments.length == 1) return defaultEmptyOK;
       else return (isSignedFloat.arguments[1] == true);
    else {
        var startPos = 0;
        var secondArg = defaultEmptyOK;
        if (isSignedFloat.arguments.length > 1)
            secondArg = isSignedFloat.arguments[1];
        if ( (s.charAt(0) == "-") || (s.charAt(0) == "+") )
           startPos = 1;    
        return (isFloat(s.substring(startPos, s.length), secondArg))
    }
}

function isAlphabetic (s)
{   var i;
    if (isEmpty(s)) 
       if (isAlphabetic.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphabetic.arguments[1] == true);
    for (i = 0; i < s.length; i++)
    {   
        var c = s.charAt(i);
        if (!isLetter(c))
        return false;
    }
    return true;
}

function isAlphanumeric (s)
{   var i;
    if (isEmpty(s)) 
       if (isAlphanumeric.arguments.length == 1) return defaultEmptyOK;
       else return (isAlphanumeric.arguments[1] == true);
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number or letter.
        var c = s.charAt(i);
        if (! (isLetter(c) || isDigit(c) ) )
        return false;
    }
    return true;
}

function reformat (s)
{   var arg;
    var sPos = 0;
    var resultString = "";
    for (var i = 1; i < reformat.arguments.length; i++) {
       arg = reformat.arguments[i];
       if (i % 2 == 1) resultString += arg;
       else {
           resultString += s.substring(sPos, sPos + arg);
           sPos += arg;
       }
    }
    return resultString;
}

function isEmail (s)
{   if (isEmpty(s)) 
       if (isEmail.arguments.length == 1) return defaultEmptyOK;
       else return (isEmail.arguments[1] == true);
    if (isWhitespace(s)) return false;
    var i = 0;
    var sLength = s.length;
    while ((i < sLength) && (s.charAt(i) != "@"))
    { i++
    }
    if ((i >= sLength) || (s.charAt(i) != "@")) return false;
    else i += 2;
    while ((i < sLength) && (s.charAt(i) != "."))
    { i++
    }
    if ((i >= sLength) || (s.charAt(i) != ".")) return false;

	return true;
}

function isYear (s)
{   if (isEmpty(s)) 
       if (isYear.arguments.length == 1) return defaultEmptyOK;
       else return (isYear.arguments[1] == true);
    if (!isNonnegativeInteger(s)) return false;
    return ((s.length == 2) || (s.length == 4));
}

function isIntegerInRange (s, a, b)
{   if (isEmpty(s)) 
       if (isIntegerInRange.arguments.length == 1) return defaultEmptyOK;
       else return (isIntegerInRange.arguments[1] == true);
    if (!isInteger(s, false)) return false;
    var num = parseInt (s);
    return ((num >= a) && (num <= b));
}
function isMonth (s)
{   if (isEmpty(s)) 
       if (isMonth.arguments.length == 1) return defaultEmptyOK;
       else return (isMonth.arguments[1] == true);
    return isIntegerInRange (s, 1, 12);
}

function isDay (s)
{   if (isEmpty(s)) 
       if (isDay.arguments.length == 1) return defaultEmptyOK;
       else return (isDay.arguments[1] == true);   
    return isIntegerInRange (s, 1, 31);
}

function daysInFebruary (year)
{    return (  ((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0) ) ) ? 29 : 28 );
}


function isDate (year, month, day)
{  
    if (! (isYear(year, false) && isMonth(month, false) && isDay(day, false))) return false;
    var intYear = parseInt(year);
    var intMonth = parseInt(month);
    var intDay = parseInt(day);
    if (intDay > daysInMonth[intMonth]) return false; 
    if ((intMonth == 2) && (intDay > daysInFebruary(intYear))) return false;
    return true;
}

function prompt (s)
{   window.status = s
}

function promptEntry (s)
{   window.status = pEntryPrompt + s
}

function warnEmpty (theField, s)
{   theField.focus()
    alert(mPrefix + s + mSuffix)
    return false
}

function warnInvalid (theField, s)
{   theField.focus()
    theField.select()
    alert(s)
    return false
}

function checkString (theField, s, emptyOK)
{ 
    if (checkString.arguments.length == 2) emptyOK = defaultEmptyOK;
    if ((emptyOK == true) && (isEmpty(theField.value))) return true;
    if (isWhitespace(theField.value)) 
       return warnEmpty (theField, s);
    else return true;
}

function checkEmail (theField, emptyOK)
{   if (checkEmail.arguments.length == 1) emptyOK = defaultEmptyOK;
    if ((emptyOK == true) && (isEmpty(theField.value))) return true;
    else if (!isEmail(theField.value, false)) 
       return warnInvalid (theField, iEmail);
    else return true;
}

function checkYear (theField, emptyOK)
{   if (checkYear.arguments.length == 1) emptyOK = defaultEmptyOK;
    if ((emptyOK == true) && (isEmpty(theField.value))) return true;
    if (!isYear(theField.value, false)) 
       return warnInvalid (theField, iYear);
    else return true;
}

function checkMonth (theField, emptyOK)
{   if (checkMonth.arguments.length == 1) emptyOK = defaultEmptyOK;
    if ((emptyOK == true) && (isEmpty(theField.value))) return true;
    if (!isMonth(theField.value, false)) 
       return warnInvalid (theField, iMonth);
    else return true;
}

function checkDay (theField, emptyOK)
{   if (checkDay.arguments.length == 1) emptyOK = defaultEmptyOK;
    if ((emptyOK == true) && (isEmpty(theField.value))) return true;
    if (!isDay(theField.value, false)) 
       return warnInvalid (theField, iDay);
    else return true;
}

function checkDate (yearField, monthField, dayField, labelString, OKtoOmitDay)
{      if (checkDate.arguments.length == 4) OKtoOmitDay = false;
    if (!isYear(yearField.value)) return warnInvalid (yearField, iYear);
    if (!isMonth(monthField.value)) return warnInvalid (monthField, iMonth);
    if ( (OKtoOmitDay == true) && isEmpty(dayField.value) ) return true;
    else if (!isDay(dayField.value)) 
       return warnInvalid (dayField, iDay);
    if (isDate (yearField.value, monthField.value, dayField.value))
       return true;
    alert (iDatePrefix + labelString + iDateSuffix)
    return false
}

function getRadioButtonValue (radio)
{   for (var i = 0; i < radio.length; i++)
    {   if (radio[i].checked) { break }
    }
    return radio[i].value
}

function padWithZero(leftSide, strLen, s)
{
	if (s.length != 0) {
		while(s.length < strLen) {
			if (leftSide==true)
				s = "0" + s
			else
				s = s + "0"
		}
	}
	return s;
}

function formatNumber(expr, decplaces)
// Taken from JavaScript Bible page 569
{
	var str = "" + Math.round(eval(expr) * Math.pow(10,decplaces))

	while (str.length <= decplaces) {
		str = "0" + str
	}
	var decpoint = str.length - decplaces
	return str.substring(0,decpoint) + "." + str.substring(decpoint, str.length)
}

//Macromedia, UltraDev Dreamweaver 4
//01.03.01

function MM_findObj(n, d) { //v4.0
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}

function MM_changeProp(objName,x,theProp,theValue) { //v3.0
  var obj = MM_findObj(objName);
  if (obj && (theProp.indexOf("style.")==-1 || obj.style)) eval("obj."+theProp+"='"+theValue+"'");
}

function validateDate(sMonth, sDay, sYear)
{ 
	// Dependencies: formChek.js
    if (isEmpty(sMonth + sDay + sYear))
    {
		return false;
    }
    else
    {
		var m = "";
		var d = "";
		var y = sYear;
		var iYear = parseInt(y);
		var dToday = new Date();

		if (sMonth.charAt(0) == "0") { m = sMonth.charAt(1); }
		else { m = sMonth; }
		if (sDay.charAt(0) == "0") { d = sDay.charAt(1); }
		else { d = sDay; }
		if (y.length < 4 || !isDate(y, m, d)) 
		{
			return false;
		}
		if (iYear < (dToday.getFullYear() - 100))
		{
			return false;
		}
		else if (iYear > dToday.getFullYear())
		{
			return false;
		}
	}
    return true;
}

function isValidDate(dateStr, field) 
{  
	// Checa para los siguientes formatos de fechas:
	// MM/DD/YYYY
	// También separa fecha en mes, día, y año

	//var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
	// Para requerir 4 digitos del año, use:
	var datePat = /^(\d{2})(\/)(\d{2})\2(\d{4})$/;

	var matchArray = dateStr.match(datePat); // Este formato esta bien?
	if (matchArray == null) 
	{
		msg = "No es un formato de fecha válido. El Formato es dd/mm/aaaa"
		return failMsg(field, msg);
	}

	day = matchArray[1]; // parse date into variables
	month = matchArray[3]; 
	year = matchArray[4];
	
	
	if (month < 1 || month > 12) 
	{ // checa el rango del mes
		msg = "El Mes debe estar entre 1 y 12."
		return failMsg(field, msg);
	}

	if (day < 1 || day > 31) 
	{
		msg = "El Día debe estar entre 1 y 31."
		return failMsg(field, msg);
	}

	if ((month==4 || month==6 || month==9 || month==11) && day==31)
	{
		msg = "Mes "+month+" No tiene 31 días."
		return failMsg(field, msg);
	}

	if (month == 2) 
	{ // checa para el mes de febrero con 29 dias
		var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
		if (day>29 || (day==29 && !isleap)) {
			msg = "Febrero " + year + " no tiene " + day + " días."
			return failMsg(field, msg);
		}
	}
	return true;  // fecha valida
}

//funcion que valida que una fecha no sea menor a la fecha actual
function ValidaFechas(dateStr, field) 
{
	// Checa para los siguientes formatos de fechas:
	// MM/DD/YYYY
	// También separa fecha en mes, día, y año

	//var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
	// Para requerir 4 digitos del año, use:
	var datePat = /^(\d{2})(\/)(\d{2})\2(\d{4})$/;

	var matchArray = dateStr.match(datePat); // Este formato esta bien?
	if (matchArray == null) 
	{
		msg = "No es un formato de fecha válido."
		return failMsg(field, msg);
	}

	day = matchArray[1]; // parse date into variables
	month = matchArray[3]; 
	year = matchArray[4];
	
	Stamp = new Date();
	var year1 = Stamp.getYear();
	if (year1 < 2000) year1 = 1900 + year1;
	var mes = Stamp.getMonth() + 1
	var dia = Stamp.getDate()
	
	if(year==year1) {
		if(month > mes) {
				msg = "fecha Valida"
		} else if(month == mes) {
			if(day < dia) {
				msg = "La fecha que ingresaste debe ser mayor o igual a la fecha actual";
				return failMsg(field, msg);
			}
		} else {
			msg = "La fecha que ingresaste debe ser mayor o igual a la fecha actual";
			return failMsg(field, msg);
		}
	} else if(year>year1){
		msg = "fecha Valida"
	}
	else {
		msg = "La fecha que ingresaste debe mayor o igual a la fecha actual";
		return failMsg(field, msg);
	}
	
	return true; //fecha valida
}

function isValidTime(TimeStr, field) 
{
	// Checa para el formato de Hora:
	// HH:MM
	// También separa la hora en hora y minutos

	var timePat = /^(\d{2})(\:)(\d{2})$/;

	var matchArray = TimeStr.match(timePat); // Este formato esta bien?
	if (matchArray == null) 
	{
		msg = "No es un formato de hora válido. El formato es de 24 horas(HH:MM)"
		return failMsg(field, msg);
	}

	hour = matchArray[1]; // parse date into variables
	minute = matchArray[3]; 
	
	if (hour < 0 || hour > 23) 
	{ // checa el rango de hora
		msg = "La hora debe estar entre 0 y 23."
		return failMsg(field, msg);
	}

	if (minute < 0 || minute > 59) 
	{
		msg = "Los minutos deben de estar entre 0 y 59."
		return failMsg(field, msg);
	}

	return true;  // hora valida
}

//funcion que valida que una fecha sea menor o igual a la fecha actual
//valida para la depuración de formatos
function ValidaFechaDepura(dateStr, field) 
{
	// Checa para los siguientes formatos de fechas:
	// MM/DD/YYYY
	// También separa fecha en mes, día, y año

	//var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{2}|\d{4})$/;
	// Para requerir 4 digitos del año, use:
	var datePat = /^(\d{2})(\/)(\d{2})\2(\d{4})$/;

	var matchArray = dateStr.match(datePat); // Este formato esta bien?
	if (matchArray == null) 
	{
		msg = "No es un formato de fecha válido."
		return failMsg(field, msg);
	}

	day = matchArray[1]; // parse date into variables
	month = matchArray[3]; 
	year = matchArray[4];
	
	Stamp = new Date();
	var year1 = Stamp.getYear();
	if (year1 < 2000) year1 = 1900 + year1;
	var mes = Stamp.getMonth() + 1
	var dia = Stamp.getDate()
	
	if(year==year1) {
		if(month > mes) {
				msg = "La fecha inicio/final debe ser menor o igual a la fecha actual";
				return failMsg(field, msg);
		} else if(month == mes) {
			if(day > dia) {
				msg = "La fecha inicio/final debe ser menor o igual a la fecha actual";
				return failMsg(field, msg);
			}
		} else {
			msg = "Fecha Valida";
			//return failMsg(field, msg);
		}
	 } else if(year > year1){
		msg = "La fecha inicio/final debe menor o igual a la fecha actual";
		return failMsg(field, msg);
	} else {
		msg = "La fecha valida";
	}
	
	return true; //fecha valida
}


		function parseaFecha(fecha) {
			var fechaP;
			fechaP = fecha.substring(3,5)+"/"
			fechaP+= fecha.substring(0,2)+"/"
			fechaP+= fecha.substring(6,10)
			//alert("fecha" + fechaP)
			return fechaP;
		}