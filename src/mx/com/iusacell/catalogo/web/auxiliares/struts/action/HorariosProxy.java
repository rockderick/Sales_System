// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 21/07/2006 1:52:34 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   HorariosProxy.java

package mx.com.iusacell.catalogo.web.auxiliares.struts.action;


import java.util.ArrayList;

import javax.servlet.http.*;
import mx.com.iusacell.catalogo.dao.CatalogoFacade;
import mx.com.iusacell.catalogo.modelo.PcHorarioDiaVO;
import org.apache.struts.action.*;

// Referenced classes of package mx.com.iusacell.catalogo.web.auxiliares.struts.action:
//            HorariosForm

public class HorariosProxy
{

    public HorariosProxy()
    {
    }

    public ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        HorariosForm h = (HorariosForm)form;
        h.setPcDiaLaborable(null);
        return mapping.findForward("home");
    }

    public ActionForward consultar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String pcCveHorario = request.getParameter("identif") == null ? "" : request.getParameter("identif");
        ArrayList horario = null;
        horario = CatalogoFacade.obtenerHorarioDetalle(pcCveHorario);
        request.setAttribute("horario", horario);
        return mapping.findForward("consultar");
    }

    public ActionForward cancelar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        if(horarioEnSesion == null)
        {
            return mapping.findForward("home");
        } else
        {
            horarioEnSesion.removeAll(horarioEnSesion);
            return mapping.findForward("home");
        }
    }

    public ActionForward agregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return mapping.findForward("agregar");
    }

    public ActionForward eventos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String pcDiaLaborable = request.getParameter("pcDiaLaborable");
        if(pcDiaLaborable.equals("1"))
            request.setAttribute("eventos", "eventos");
        else
        if(pcDiaLaborable.equals("2"))
            request.setAttribute("SinEventos", "SinEventos");
        return mapping.findForward("agregar");
    }

    public ActionForward agregarDia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String pcDiaLaborable = request.getParameter("pcDiaLaborable");
        if(pcDiaLaborable.equals("1"))
            request.setAttribute("eventos", "eventos");
        else
        if(pcDiaLaborable.equals("2"))
            request.setAttribute("SinEventos", "SinEventos");
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        if(horarioEnSesion == null)
        {
            cargarDiaInicial(request);
        } else
        {
            int estatus = validarDiaHabil(request);
            if(estatus == 2)
            {
                request.setAttribute("mensaje3", "Este d\355a ya se definio como descanso anteriormente. Presione aceptar para perder la configuracion anterior de este dia y poder definir este dia como laborable. Presione cancelar para descartar este dia como laborable");
                request.setAttribute("diaEliminar", request.getParameter("pcCveDia"));
                return mapping.findForward("agregar");
            }
            int mismo = validarMismoEvento(request);
            if(mismo == 1)
            {
                request.setAttribute("mensaje4", "No se puede asignar el mismo evento para el mismo d\355a");
                return mapping.findForward("agregar");
            }
            cargarDiaSiguiente(request);
        }
        HorariosForm h = (HorariosForm)form;
        h.setPcHora(null);
        return mapping.findForward("agregar");
    }

    public ActionForward agregarDiaDescanso(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String pcDiaLaborable = request.getParameter("pcDiaLaborable");
        if(pcDiaLaborable.equals("1"))
            request.setAttribute("eventos", "eventos");
        else
        if(pcDiaLaborable.equals("2"))
            request.setAttribute("SinEventos", "SinEventos");
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        if(horarioEnSesion == null)
        {
            cargarDiaInicialDescanso(request);
        } else
        {
            int estatus = validarDiaDescanso(request);
            if(estatus == 1)
            {
                request.setAttribute("mensaje", "No se puede definir dos veces el mismo dia como descanso");
                return mapping.findForward("agregar");
            }
            if(estatus == 2)
            {
                request.setAttribute("mensaje2", "Este d\355a ya se definio como laborable anteriormente. Presione aceptar para perder la configuracion anterior y definir este dia como descanso. Presione cancelar para descartar este dia como descanso");
                request.setAttribute("diaEliminar", request.getParameter("pcCveDia"));
                return mapping.findForward("agregar");
            }
            cargarDiaSiguienteDescanso(request);
        }
        HorariosForm h = (HorariosForm)form;
        h.setPcDiaLaborable(null);
        return mapping.findForward("agregar");
    }

    public void cargarDiaInicial(HttpServletRequest request)
    {
        ArrayList horario = new ArrayList();
		String hora = "0";
		String minuto = "0";
		
        String pcCveDia = request.getParameter("pcCveDia");
        String pcDiaLaborable = request.getParameter("pcDiaLaborable");
        String pcEvento = request.getParameter("pcEvento");
        String pcHora = request.getParameter("pcHora");
		String pcCveHora = request.getParameter("pcHora");
		String pcCveMinuto = request.getParameter("pcMinutos");
		
		if(pcHora.length()==1){		
			pcHora = hora.concat(pcHora);
		}
		
		if(pcCveMinuto.length()==1){		
			pcCveMinuto = minuto.concat(pcCveMinuto);
		}
		
        PcHorarioDiaVO dia = new PcHorarioDiaVO();
        PcHorarioDiaVO infoDia = new PcHorarioDiaVO();
        
        dia.setPcCveDia((new Integer(pcCveDia)).intValue());
        dia.setPcDiaLaborable((new Integer(pcDiaLaborable)).intValue());
        dia.setPcEvento((new Integer(pcEvento)).intValue());
        dia.setPcHora(pcHora);
		dia.setPcCveHora(new Integer(pcCveHora).intValue());
		dia.setPcCveMinuto(pcCveMinuto);
        
        infoDia = obtenerDatosDia(pcCveDia, pcEvento);
        dia.setPcDescDia(infoDia.getPcDescDia());
        dia.setDescripcion(infoDia.getDescripcion());
        
        horario.add(dia);
        request.getSession().setAttribute("horario", horario);
    }

    public void cargarDiaSiguiente(HttpServletRequest request)
    {
		String hora = "0";
		String minuto = "0";
    	
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        String pcCveDia = request.getParameter("pcCveDia");
        String pcDiaLaborable = request.getParameter("pcDiaLaborable");
        String pcEvento = request.getParameter("pcEvento");
        String pcHora = request.getParameter("pcHora");
		String pcCveHora = request.getParameter("pcHora");
		String pcCveMinuto = request.getParameter("pcMinutos");
		
		if(pcHora.length()==1){		
			pcHora = hora.concat(pcHora);
		}
		if(pcCveMinuto.length()==1){		
			pcCveMinuto = minuto.concat(pcCveMinuto);
		}		
       
        PcHorarioDiaVO dia = new PcHorarioDiaVO();
        PcHorarioDiaVO infoDia = new PcHorarioDiaVO();
        dia.setPcCveDia((new Integer(pcCveDia)).intValue());
        dia.setPcDiaLaborable((new Integer(pcDiaLaborable)).intValue());
        dia.setPcEvento((new Integer(pcEvento)).intValue());
        dia.setPcHora(pcHora);
		dia.setPcCveHora(new Integer(pcCveHora).intValue());
		dia.setPcCveMinuto(pcCveMinuto);
        
        infoDia = obtenerDatosDia(pcCveDia, pcEvento);
        dia.setPcDescDia(infoDia.getPcDescDia());
        dia.setDescripcion(infoDia.getDescripcion());
        horarioEnSesion.add(dia);
        request.getSession().setAttribute("horario", horarioEnSesion);
    }

    public PcHorarioDiaVO obtenerDatosDia(String pcCveDia, String pcEvento)
    {
        PcHorarioDiaVO dia = new PcHorarioDiaVO();
        dia = CatalogoFacade.obtenerDia(pcCveDia, pcEvento);
        return dia;
    }

    public void cargarDiaInicialDescanso(HttpServletRequest request)
    {
        ArrayList horario = new ArrayList();
        String pcCveDia = request.getParameter("pcCveDia");
        String pcDiaLaborable = request.getParameter("pcDiaLaborable");
        String pcEvento = "0";
        String pcHora = "00";
        PcHorarioDiaVO dia = new PcHorarioDiaVO();
        PcHorarioDiaVO infoDia = new PcHorarioDiaVO();
        dia.setPcCveDia((new Integer(pcCveDia)).intValue());
        dia.setPcDiaLaborable((new Integer(pcDiaLaborable)).intValue());
        dia.setPcEvento((new Integer(pcEvento)).intValue());
        dia.setPcHora(pcHora);
        infoDia = obtenerDatosDia(pcCveDia, pcEvento);
        dia.setPcDescDia(infoDia.getPcDescDia());
        dia.setDescripcion(infoDia.getDescripcion());
        horario.add(dia);
        request.getSession().setAttribute("horario", horario);
    }

    public void cargarDiaSiguienteDescanso(HttpServletRequest request)
    {
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        String pcCveDia = request.getParameter("pcCveDia");
        String pcDiaLaborable = request.getParameter("pcDiaLaborable");
        String pcEvento = "0";
        String pcHora = "00";
        PcHorarioDiaVO dia = new PcHorarioDiaVO();
        PcHorarioDiaVO infoDia = new PcHorarioDiaVO();
        dia.setPcCveDia((new Integer(pcCveDia)).intValue());
        dia.setPcDiaLaborable((new Integer(pcDiaLaborable)).intValue());
        dia.setPcEvento((new Integer(pcEvento)).intValue());
        dia.setPcHora(pcHora);
        infoDia = obtenerDatosDia(pcCveDia, pcEvento);
        dia.setPcDescDia(infoDia.getPcDescDia());
        dia.setDescripcion(infoDia.getDescripcion());
        horarioEnSesion.add(dia);
        request.getSession().setAttribute("horario", horarioEnSesion);
    }

    public int validarDiaDescanso(HttpServletRequest request)
    {
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        String pcCveDia = request.getParameter("pcCveDia");
        int diaNuevo = (new Integer(pcCveDia)).intValue();
        for(int i = 0; i < horarioEnSesion.size(); i++)
        {
            PcHorarioDiaVO dia = new PcHorarioDiaVO();
            dia = (PcHorarioDiaVO)horarioEnSesion.get(i);
            if(dia.getPcCveDia() == diaNuevo)
            {
                if(dia.getPcDiaLaborable() == 2)
                    return 1;
                if(dia.getPcDiaLaborable() == 1)
                    return 2;
            }
        }

        return 0;
    }

    public ActionForward eliminarDiaAnterior(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        int diaEliminar = (new Integer(request.getParameter("dia"))).intValue();
        for(int i = 0; i < horarioEnSesion.size(); i++)
        {
            PcHorarioDiaVO dia = new PcHorarioDiaVO();
            dia = (PcHorarioDiaVO)horarioEnSesion.get(i);
            if(dia.getPcCveDia() == diaEliminar)
            {
                horarioEnSesion.remove(i);
                i--;
            }
        }

        return mapping.findForward("agregar");
    }

    public int validarDiaHabil(HttpServletRequest request)
    {
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        String pcCveDia = request.getParameter("pcCveDia");
        int diaNuevo = (new Integer(pcCveDia)).intValue();
        for(int i = 0; i < horarioEnSesion.size(); i++)
        {
            PcHorarioDiaVO dia = new PcHorarioDiaVO();
            dia = (PcHorarioDiaVO)horarioEnSesion.get(i);
            if(dia.getPcCveDia() == diaNuevo)
            {
                if(dia.getPcDiaLaborable() == 1)
                    return 1;
                if(dia.getPcDiaLaborable() == 2)
                    return 2;
            }
        }

        return 0;
    }

    public int validarMismoEvento(HttpServletRequest request)
    {
        ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
        String pcCveDia = request.getParameter("pcCveDia");
        String pcEvento = request.getParameter("pcEvento");
        int pcEventoEvaluar = (new Integer(pcEvento)).intValue();
        int pcCveDiaEvaluar = (new Integer(pcCveDia)).intValue();
        for(int i = 0; i < horarioEnSesion.size(); i++)
        {
            PcHorarioDiaVO dia = new PcHorarioDiaVO();
            dia = (PcHorarioDiaVO)horarioEnSesion.get(i);
            if(dia.getPcCveDia() == pcCveDiaEvaluar)
                return dia.getPcEvento() != pcEventoEvaluar ? 2 : 1;
        }

        return 0;
    }
    
	public ActionForward guardarHorario(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
		String pcDescHorario = request.getParameter("pcDescHorario");
		String pcCveDiv = (String)request.getSession().getAttribute("pcCveDiv");
		int total = 0;
		if(horarioEnSesion == null)
		{
			request.setAttribute("mensaje", "Es necesario configurar el Horario");
			return mapping.findForward("agregar");
		} else
		{
			int pcCveHorario = obtenerSiguientePcCveHorario();
			//agregarPCHorario(horarioEnSesion);
			
			total = validarDiasTotales(horarioEnSesion);
			if(total == 1){
				request.setAttribute("mensaje5","Se han configurado solo algunos días de la semana para el horario. Presione Aceptar para configurar los días restantes como descanso. Presione Cancelar para seguir configurando el Horario");
				return mapping.findForward("agregar");
			} else {
				agregarPcHorario(pcCveHorario, pcDescHorario, pcCveDiv);
				agregarPcHorarioSemana(pcCveHorario, horarioEnSesion);
				agregarPcHorarioDia(pcCveHorario, horarioEnSesion);
				horarioEnSesion.removeAll(horarioEnSesion);
				request.setAttribute("mensaje", "Se ha agregado exitosamente el Horario");
				horarioEnSesion.removeAll(horarioEnSesion);
				return mapping.findForward("home");
			}
            

		}
	}
	
	public ActionForward guardarHorarioDescansos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList horarioEnSesion = (ArrayList)request.getSession().getAttribute("horario");
		String pcDescHorario = request.getParameter("pcDescHorario");
		String pcCveDiv = (String)request.getSession().getAttribute("pcCveDiv");
		int total = 0;
		if(horarioEnSesion == null)
		{
			request.setAttribute("mensaje", "Es necesario configurar el Horario");
			return mapping.findForward("agregar");
		} else
		{
			int pcCveHorario = obtenerSiguientePcCveHorario();
			
			//agregarPCHorario(horarioEnSesion);
			agregarDiasDescanso(horarioEnSesion);
			

            
			agregarPcHorario(pcCveHorario, pcDescHorario, pcCveDiv);
			agregarPcHorarioSemana(pcCveHorario, horarioEnSesion);
			agregarPcHorarioDia(pcCveHorario, horarioEnSesion);
			horarioEnSesion.removeAll(horarioEnSesion);
			request.setAttribute("mensaje", "Se ha agregado exitosamente el Horario");
			horarioEnSesion.removeAll(horarioEnSesion);
			return mapping.findForward("home");
		}
	}
	
	public void agregarDiasDescanso(ArrayList horarioEnSesion)
	{
		
		int uno = 0;
		int dos = 0;
		int tres = 0;
		int cuatro = 0;
		int cinco = 0;
		int seis = 0;
		int siete = 0;
		
		
				
		for(int i = 0; i < horarioEnSesion.size(); i++)
		{
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia = (PcHorarioDiaVO)horarioEnSesion.get(i);
			
			switch (dia.getPcCveDia()){
				case 1:
					uno = 1;
					break;
				case 2:
					dos = 1;
					break;
				case 3:
					tres = 1;
					break;
				case 4:
					cuatro = 1;
					break;
				case 5:
					cinco = 1;
					break;
				case 6:
					seis = 1;
					break;
				case 7:
					siete = 1;
					break;         		
			};
		}
		
		if(uno == 0){
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia.setPcCveDia(1);
			dia.setPcDiaLaborable(2);
			dia.setPcCveTipoRegistro(0);
			dia.setPcEvento(0);
			dia.setPcHora("00");
			horarioEnSesion.add(dia);
		}
		if(dos == 0){
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia.setPcCveDia(2);
			dia.setPcDiaLaborable(2);
			dia.setPcCveTipoRegistro(0);
			dia.setPcEvento(0);
			dia.setPcHora("00");
			horarioEnSesion.add(dia);
		}
		if(tres == 0){
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia.setPcCveDia(3);
			dia.setPcDiaLaborable(2);
			dia.setPcCveTipoRegistro(0);
			dia.setPcEvento(0);
			dia.setPcHora("00");
			horarioEnSesion.add(dia);
		}
		if(cuatro == 0){
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia.setPcCveDia(4);
			dia.setPcDiaLaborable(2);
			dia.setPcCveTipoRegistro(0);
			dia.setPcEvento(0);
			dia.setPcHora("00");
			horarioEnSesion.add(dia);
		}
		if(cinco == 0){
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia.setPcCveDia(5);
			dia.setPcDiaLaborable(2);
			dia.setPcCveTipoRegistro(0);
			dia.setPcEvento(0);
			dia.setPcHora("00");
			horarioEnSesion.add(dia);
		}
		if(seis == 0){
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia.setPcCveDia(6);
			dia.setPcDiaLaborable(2);
			dia.setPcCveTipoRegistro(0);
			dia.setPcEvento(0);
			dia.setPcHora("00");
			horarioEnSesion.add(dia);
		}
		if(siete == 0){
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia.setPcCveDia(7);
			dia.setPcDiaLaborable(2);
			dia.setPcCveTipoRegistro(0);
			dia.setPcEvento(0);
			dia.setPcHora("00");
			horarioEnSesion.add(dia);
		}	        
	}	   	    

    public void agregarPcHorario(int pcCveHorario, String pcDescHorario, String pcCveDiv)
    {
        CatalogoFacade.insertarPcHorario(String.valueOf(pcCveHorario), pcDescHorario, pcCveDiv);
    }

    public void agregarPcHorarioSemana(int pcCveHorario, ArrayList horarioEnSesion)
    {
        String descanso = null;
        int pcCveDiaAnterior = 0;
        for(int i = 0; i < horarioEnSesion.size(); i++)
        {
            PcHorarioDiaVO dia = new PcHorarioDiaVO();
            dia = (PcHorarioDiaVO)horarioEnSesion.get(i);
            int pcDescanso = dia.getPcDiaLaborable();
            if(pcDescanso == 1)
                descanso = "N";
            else
            if(pcDescanso == 2)
                descanso = "S";
            if(dia.getPcCveDia() != pcCveDiaAnterior)
                CatalogoFacade.insertarPcHorarioSemana(String.valueOf(pcCveHorario), String.valueOf(dia.getPcCveDia()), descanso);
            pcCveDiaAnterior = dia.getPcCveDia();
        }

    }

    public void agregarPcHorarioDia(int pcCveHorario, ArrayList horarioEnSesion)
    {
        String descanso = null;
        for(int i = 0; i < horarioEnSesion.size(); i++)
        {
            PcHorarioDiaVO dia = new PcHorarioDiaVO();
            dia = (PcHorarioDiaVO)horarioEnSesion.get(i);
            CatalogoFacade.insertarPcHorarioDia(String.valueOf(pcCveHorario), String.valueOf(dia.getPcCveDia()), String.valueOf(dia.getPcEvento()), dia.getPcHora(),String.valueOf(dia.getPcCveHora()),String.valueOf(dia.getPcCveMinuto()));
        }

    }

    public int obtenerSiguientePcCveHorario()
    {
        PcHorarioDiaVO dia = new PcHorarioDiaVO();
        dia = CatalogoFacade.obtenerSiguientePcCveHorario();
        int pcCveHorario = dia.getPcCveHorario();
        return pcCveHorario;
    }
    
	public int validarDiasTotales(ArrayList horarioEnSesion)
	{
		int pcCveDiaAnterior = 0;
		int contador = 0;
		
		for(int i = 0; i < horarioEnSesion.size(); i++)
		{
        	
			PcHorarioDiaVO dia = new PcHorarioDiaVO();
			dia = (PcHorarioDiaVO)horarioEnSesion.get(i);

			if(dia.getPcCveDia() != pcCveDiaAnterior){
				contador = contador + 1;
			}
			pcCveDiaAnterior = dia.getPcCveDia();
			
		}

		if(contador == 7){
			return 0;
		}else {
			return 1;
		} 
			
	}

    /*public void agregarPCHorario(ArrayList horarioEnSesion)
    {
        for(int i = 0; i < horarioEnSesion.size(); i++)
        {
            PcHorarioDiaVO dia = new PcHorarioDiaVO();
            dia = (PcHorarioDiaVO)horarioEnSesion.get(i);
        }

    }*/
}