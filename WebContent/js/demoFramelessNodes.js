// You can find instructions for this file at http://www.treeview.net

//Environment variables are usually set at the top of this file.
USETEXTLINKS = 1
STARTALLOPEN = 0
USEFRAMES = 0
USEICONS = 1
WRAPTEXT = 1
PRESERVESTATE = 1


ICONPATH = 'icons/' //change if the gif's folder is a subfolder, for example: 'images/'


foldersTree = gFld("<i>Division 1</i>", "demoFramesetRightFrame.html")
foldersTree.iconSrc = ICONPATH + "home24.gif"
foldersTree.iconSrcClosed = ICONPATH + "homeplus24.gif"
  aux1 = insFld(foldersTree, gFld("DIRECTOR(DIRECTOR DIVISIONAL)", "demoFramesetRightFrame.html"))
  aux1.iconSrc = ICONPATH + "group24.gif"
  aux1.iconSrcClosed = ICONPATH + "groupplus24.gif"
    aux2 = insFld(aux1, gFld("GERENTE REGIONAL(GERENTE REGIONAL)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
    aux2.iconSrc = ICONPATH + "man24.gif"
    aux2.iconSrcClosed = ICONPATH + "manplus24.gif"
      aux3 = insFld(aux2, gFld("SUPERVISOR 1(SUPERVISOR)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
      aux3.iconSrc = ICONPATH + "man24.gif"
      aux3.iconSrcClosed = ICONPATH + "manplus24.gif"
	aux4 = insFld(aux3, gFld("JEFE DE TIENDA 1(JEFE DE TIENDA)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
        aux4.iconSrc = ICONPATH + "man24.gif"
        aux4.iconSrcClosed = ICONPATH + "manplus24.gif"
	  aux5 = insFld(aux4, gFld("VENDEDOR 1(VENDEDOR)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
          aux5.iconSrc = ICONPATH + "man24.gif"
          aux5.iconSrcClosed = ICONPATH + "manplus24.gif"
	  aux5 = insFld(aux4, gFld("VENDEDOR 2(VENDEDOR)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
          aux5.iconSrc = ICONPATH + "man24.gif"
          aux5.iconSrcClosed = ICONPATH + "manplus24.gif"
	  aux5 = insFld(aux4, gFld("VENDEDOR 3(VENDEDOR)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
          aux5.iconSrc = ICONPATH + "man24.gif"
          aux5.iconSrcClosed = ICONPATH + "manplus24.gif"
	aux4 = insFld(aux3, gFld("JEFE DE TIENDA 3(JEFE DE TIENDA)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
        aux4.iconSrc = ICONPATH + "man24.gif"
        aux4.iconSrcClosed = ICONPATH + "manplus24.gif"
      aux3 = insFld(aux2, gFld("SUPERVISOR 2(SUPERVISOR)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
      aux3.iconSrc = ICONPATH + "man24.gif"
      aux3.iconSrcClosed = ICONPATH + "manplus24.gif"
	aux4 = insFld(aux3, gFld("JEFE DE TIENDA 2(JEFE DE TIENDA)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
        aux4.iconSrc = ICONPATH + "man24.gif"
        aux4.iconSrcClosed = ICONPATH + "manplus24.gif"
	aux4 = insFld(aux3, gFld("Roberto(VENDEDOR)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
        aux4.iconSrc = ICONPATH + "man24.gif"
        aux4.iconSrcClosed = ICONPATH + "manplus24.gif"
      aux3 = insFld(aux2, gFld("xx(JEFE DE TIENDA)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
      aux3.iconSrc = ICONPATH + "man24.gif"
      aux3.iconSrcClosed = ICONPATH + "manplus24.gif"
      aux3 = insFld(aux2, gFld("xx(JEFE DE TIENDA)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
      aux3.iconSrc = ICONPATH + "man24.gif"
      aux3.iconSrcClosed = ICONPATH + "manplus24.gif"
      aux3 = insFld(aux2, gFld("xx(JEFE DE TIENDA)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
      aux3.iconSrc = ICONPATH + "man24.gif"
      aux3.iconSrcClosed = ICONPATH + "manplus24.gif"
    aux2 = insFld(aux1, gFld("WILLIAMS(VENDEDOR)", "http://www.treeview.net/treemenu/demopics/beenthere_america.gif"))
    aux2.iconSrc = ICONPATH + "man24.gif"
    aux2.iconSrcClosed = ICONPATH + "manplus24.gif"

