<%@ page import="mx.com.iusacell.catalogo.utilerias.Q"%>
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/fsj-html.tld" prefix="fsj" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg" %>
<jsp:include page="/Valida.jsp"></jsp:include>
<%!
	public String getValor(HttpServletRequest req, String valor){
		String res = req.getParameter(valor);
		if(res != null && (res.trim().length() == 0)){
			res = null;
		}
		return res;
	}
	
%> 
<html:html>
<html:base/>

	<head>
	
	
		<title>Iusacell :: Reporte Checador </title>
		<link rel="stylesheet" type="text/css" href="../css/estilos_exp.css" />
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />   
	    <link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />    
		<script language="JavaScript" src="../js/tools.js"></script>
		<script language="JavaScript" src="../js/Calendario.js"></script>
	    <script type="text/javascript" src="../js/tabs.js"></script>
		<script language="Javascript1.2" src="../js/std_button.js"></script>		
		<script language="Javascript1.2" src="../js/Reporte.js"></script>
		<script language="Javascript1.2" src="../js/ua.js"></script>
		<script language="Javascript1.2" src="../js/ftiens4.js"></script>
		<script language="Javascript1.2">
		
		function goToReporte(form) {
		
			MM_validateForm('fechaInicial','RisText','Fecha Inicial :',
							'fechaFinal','RisText','Fecha Final :');
	    	if (document.MM_returnValue){
				if (!fechaMayorOIgualQue(document.ChecadorForm.fechaFinal, document.ChecadorForm.fechaInicial)){  
      				alert("La Fecha Inicial debe ser menor a la Fecha Final."); 
				} else {
					var entrar=diferenciaFechas(form);
					if(entrar==1){
						alert("El Periodo de Fechas debe ser Menor a 32 dias.");
					} else {
						var action = "?action=reporte";
						form.action = form.action + action;
						form.submit();
					}	
				}
			}
			
		}
		
		function goToExportar(form) {
		
			<logic:notPresent name="reporteChecador">
				alert("Debe realizar una Busqueda");
			</logic:notPresent>
				
			<logic:present name="reporteChecador">
				window.open("ChecadorAction.do?action=exportar",
					"Checador","width=900,height=500,status=0, menubar=0,toolbar=0, " +
					"resizable=1, top=100, left=100, scrollbars=1");
			</logic:present>
		
		}

		<script>history.forward(1)</script>
		
		</script>
	</head>


<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="3" marginwidth="0" marginheight="0" onResize="if (navigator.family == 'nn4') window.location.reload()">
<menu:useMenuDisplayer name="TabbedMenu" bundle="org.apache.struts.action.MESSAGE">
    <menu:displayMenu name="Home" />
    <menu:displayMenu name="Vendedores" /> 
    <menu:displayMenu name="Personal" /> 
    <menu:displayMenu name="PuntosVenta" /> 
    <menu:displayMenu name="Relaciones" /> 
    <menu:displayMenu name="Reportes" /> 
    <menu:displayMenu name="Auxiliares" /> 
</menu:useMenuDisplayer>	

<BR><BR>
	<html:form method="post" action="ChecadorAction"> 
	<bean:define id="ChecadorForm" name="ChecadorForm" type="mx.com.iusacell.catalogo.web.reporte.struts.action.ChecadorForm"/>
	<TABLE WIDTH="700" BORDER="0" align="center" id=borde>
      <TR>
        <TD HEIGHT="20"><h3>Reporte Checador</h3></TD>
      </TR>
    </TABLE>
	<BR>
	
	
		<TABLE WIDTH="85%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF" align="center">
			<tr bgcolor="#FFFFFF">
				<td><span class="txtContenidoBold">Fecha Inicial :</span> </td>
				<td>	
					<input type = text name= "fechaInicial"  size="15" maxlength="35" />&nbsp;
				<a href="javascript:show_calendar('forms[0].fechaInicial','','','DD/MM/YYYY','es')"><img src="<%=request.getContextPath()%>/images/calendario.gif" alt="Calendario" width="14" height="16" border="0" style="cursor:hand" > </a></td>
			</tr>
		
		 	<tr BGCOLOR="#E0E1E3">
				<td><span class="txtContenidoBold">Fecha Final :</span> </td>
				<td>
					<input type = text name= "fechaFinal"  size="15" maxlength="35" />&nbsp;
				<a href="javascript:show_calendar('forms[0].fechaFinal','','','DD/MM/YYYY','es')"><img src="<%=request.getContextPath()%>/images/calendario.gif" alt="Calendario" width="14" height="16" border="0" style="cursor:hand" > </a></td>
			</tr>
	 
		
		</TABLE>
		
		<BR>
		
		
		<TABLE WIDTH="85%" BORDER="0" CELLSPACING="1" CELLPADDING="3" BGCOLOR="#FFFFFF" align="center">
			<tr bgcolor="#FFFFFF">
				<TD WIDTH="40%" ALIGN="CENTER" CLASS="txtContenido">
					<html:button property="boton" styleClass="boton" onclick="goToReporte(ChecadorForm)">Generar</html:button>
				</TD>
			</tr>
		</TABLE>	
		
		<BR>
	
	<TABLE WIDTH="85%" BORDER="0" CELLSPACING="1" CELLPADDING="2" BGCOLOR="#CCCCCC" align="center">
	<TR>
	<TD BGCOLOR="#FFFFFF">
		<TABLE WIDTH="85%" BORDER="0" CELLSPACING="1" CELLPADDING="0" BGCOLOR="#EEEEEE">
		<TR>
	
	
		<table border='1' width='100%' cellpadding='3' cellspacing='0'>
			<tr>

				<td width='5%' class='titulosChecador' align='left' height='13'>FECHA REGISTRO
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>CLAVE SAEO
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>NUM EMP
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>PERSONA
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>PUESTO
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>FALTA
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>JUSTIFICACION FALTA
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>ENTRADA
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>HORARIO ENTRADA
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>SALIDA
				</td>
				<td width='5%' class='titulosChecador' align='left' height='13'>HORARIO SALIDA
				</td>														
				<td width='5%' class='titulosChecador' align='left' height='13'>TIENDA
				</td>				
				<td width='5%' class='titulosChecador' align='left' height='13'>DIVISION
				</td>																				
			</tr>
			
            <pg:pager maxIndexPages="0" maxPageItems="15" export="currentPageNumber=pageNumber" url="ChecadorHome"> 
            	<logic:present name="reporteChecador"  >
					<logic:iterate id="res" name="reporteChecador" indexId="i" > 			
                    <pg:item> 
                    <%if(i.intValue()%2 == 0){%>
		                <tr  class="td_gris_checador">
		                <%}else{%>
						<tr>
						<%}%>
									
							<td width='5%'  align='left' height='13' class="td_gris_checador"><bean:write name="res" property="fechaRegistro"/>
							</td>
							<td width='5%'  align='left' height='13' class="td_gris_checador"><bean:write name="res" property="pcCveVendedor"/>
							</td>
							<td width='5%'  align='left' height='13' class="td_gris_checador"><bean:write name="res" property="pcCveEmpRef"/>
							</td>
							<td width='5%'  align='left' height='13' class="td_gris_checador"><bean:write name="res" property="pcNomVendedor"/>
							</td>
							<td width='5%'  align='left' height='13' class="td_gris_checador"><bean:write name="res" property="pcDescPuesto"/>
							</td>
							<td width='5%'  align='right' height='13' class="td_gris_checador"><bean:write name="res" property="pcFechaCinco"/>
							</td>
							<td width='5%'  align='right' height='13' class="td_gris_checador"><bean:write name="res" property="pcTipoestatusCinco"/>
							</td>							
							<td width='5%'  align='right' height='13' class="td_gris_checador"><bean:write name="res" property="pcFechaUno"/>
							</td>
							<td width='5%'  align='right' height='13' class="td_gris_checador"><bean:write name="res" property="pcHoraUno"/>
							</td>
							<td width='5%'  align='right' height='13' class="td_gris_checador"><bean:write name="res" property="pcFechaCuatro"/>
							</td>
							<td width='5%'  align='right' height='13' class="td_gris_checador"><bean:write name="res" property="pcHoraCuatro"/>
							</td>
							<td width='5%'  align='right' height='13' class="td_gris_checador"><bean:write name="res" property="pcNomPtoventas"/>
							</td>
							<td width='5%'  align='right' height='13' class="td_gris_checador"><bean:write name="res" property="pcDescDiv"/>
							</td>																																																	
						</tr>
                    	</pg:item> 
		                </logic:iterate>

					</logic:present>
		</table> 
		
		           <table width="50%"  border="0" align="center" cellpadding="1" cellspacing="0">
                    <tr>
                       <td height="20" align="right" valign="middle">
                       <div align="right"><span class="menuNVTC style7">
                       
			            <pg:index>
						<b> 
			            <pg:prev><img src="gifs/bullet_rojo_izq.gif" width="10" height="11" border="0" align="absmiddle">
			            	<a href="<%=pageUrl%>"class="menuNVTC style8"> Anterior</img></span> | </a> </pg:prev>
			            <pg:next> <a href="<%=pageUrl%>" class="menuNVTC style8">Adelante </img></a> </pg:next><img src="gifs/bullet_rojo_der.gif" width="11" height="12" align="absmiddle">&nbsp;&nbsp;&nbsp;</div> </b>
			            </pg:index> 
			            </pg:pager>
                        </td>
                      </tr>
                    </table>
	
	
	
		</TR>
		</TABLE>
		
	    <table width="50%"  border="0" align="center" cellpadding="1" cellspacing="0">
        	<tr>
				<td width="900" align="center"> 
		 			<html:button property="boton" styleClass="boton" onclick="goToExportar(ChecadorForm)">Exportar</html:button>
		 		</TD>
          	</tr>
        </table>	
	
	</TD>
	</TR>
	</TABLE>	


	





	</html:form> 
</BODY>
</html:html>


