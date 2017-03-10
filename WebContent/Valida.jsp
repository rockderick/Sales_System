<%	
	String valida = (String)request.getSession().getAttribute("validarSession");

	
	String mensaje ="";		
	if(valida == null) {
		mensaje = "Acceso Denegado";
		request.setAttribute("mensaje", mensaje);
	%>
		<jsp:forward page="/index.jsp"></jsp:forward>
		
	<%}%>
