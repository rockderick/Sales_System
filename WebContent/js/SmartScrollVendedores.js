   function sstchur_SmartScroller_GetCoords()
   {
      var scrollX, scrollY;
      
      if (document.all)
      {
         if (!document.documentElement.scrollLeft)
            scrollX = document.body.scrollLeft;
         else
            scrollX = document.documentElement.scrollLeft;
               
         if (!document.documentElement.scrollTop)
            scrollY = document.body.scrollTop;
         else
            scrollY = document.documentElement.scrollTop;
      }   
      else
      {
         scrollX = window.pageXOffset;
         scrollY = window.pageYOffset;
      }
   
      document.forms[0].coordXHolder.value = scrollX;
      document.forms[0].coordYHolder.value = scrollY;
   }
   
   function sstchur_SmartScroller_Scroll()
   {
   		
      var x = document.forms[0].coordXHolder.value;
      var y = document.forms[0].coordYHolder.value;
      alert("iniciando Scroller :" + x + ":" + y);
      window.scrollTo(x, y);
   }
   
   
   window.onload = sstchur_SmartScroller_Scroll;
   window.onscroll = sstchur_SmartScroller_GetCoords;
   window.onkeypress = sstchur_SmartScroller_GetCoords;
   window.onclick = sstchur_SmartScroller_GetCoords;


