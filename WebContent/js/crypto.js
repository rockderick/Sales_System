/************************
CRYPTO BASE64 FUNCTIONS 
*************************/

 function base64ToAscii(c) {
   var result = 0;

   if (0 <= c && c <= 25) {
	  result = String.fromCharCode(c + 65);  // 65 = 'A'
   } else if (26 <= c && c <= 51) {
	  result = String.fromCharCode(c - 26 + 97);  // 97 = 'a'
   } else if (52 <= c && c <= 61) {
	  result = String.fromCharCode(c - 52 + 48);  // 48 = '0'
   } else if (c == 62) {
	  result = '+'; 
   } else if( c == 63 ) {
	  result = '/';
   } else {
      result = '='; 
   }
   return result;
 }

 // Por razones de seguridad se cambio el nombre de base64Encode por encriptar
 function encriptar(str) {
   var result = "";
   var i = 0;
   var sextet = 0;
   var leftovers = 0;
   var octet = 0;
   
   for (i=0; i < str.length; i++) {
    octet = str.charCodeAt(i);
    switch( i % 3 ) {
      case 0:
        sextet = ( octet & 0xFC ) >> 2 ;
        leftovers = octet & 0x03 ;
        break;
      case 1:
        sextet = ( leftovers << 4 ) | ( ( octet & 0xF0 ) >> 4 );
        leftovers = octet & 0x0F ;
        break;
      case 2:
        sextet = ( leftovers << 2 ) | ( ( octet & 0xC0 ) >> 6 ) ;
        leftovers = ( octet & 0x3F ) ;
        break;
    }
    result = result + base64ToAscii(sextet);
    if( (i % 3) == 2 ) result = result + base64ToAscii(leftovers);
   } 
   return result.toString();
 }