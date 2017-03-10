// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 21/07/2006 1:52:09 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   HorariosAction.java

package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.*;

// Referenced classes of package mx.com.iusacell.catalogo.web.auxiliares.struts.action:
//            HorariosProxy

public class HorariosAction extends Action
{

    public HorariosAction()
    {
    }

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        org.apache.struts.util.MessageResources messages = getResources(request);
        String action = request.getParameter("action");
        if(action == null)
            action = "home";
        try
        {
            if(action.equals("home"))
                return home(mapping, form, request, response);
            if(action.equals("eventos"))
                return eventos(mapping, form, request, response);
            if(action.equals("agregarDia"))
                return agregarDia(mapping, form, request, response);
            if(action.equals("agregarDiaDescanso"))
                return agregarDiaDescanso(mapping, form, request, response);
            if(action.equals("eliminarDiaAnterior"))
                return eliminarDiaAnterior(mapping, form, request, response);
            if(action.equals("guardarHorario"))
                return guardarHorario(mapping, form, request, response);
			if(action.equals("guardarHorarioDescansos"))
				return guardarHorarioDescansos(mapping, form, request, response);                 
            if(action.equals("consultar"))
                return consultar(mapping, form, request, response);
            if(action.equals("agregar"))
                return agregar(mapping, form, request, response);
            if(action.equals("cancelar"))
                return cancelar(mapping, form, request, response);
            else
                return mapping.findForward(action);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return mapping.findForward("error");
    }

    private ActionForward home(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).home(mapping, form, request, response);
    }

    private ActionForward consultar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).consultar(mapping, form, request, response);
    }

    private ActionForward agregar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).agregar(mapping, form, request, response);
    }

    private ActionForward cancelar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).cancelar(mapping, form, request, response);
    }

    private ActionForward eventos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).eventos(mapping, form, request, response);
    }

    private ActionForward agregarDia(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).agregarDia(mapping, form, request, response);
    }

    private ActionForward agregarDiaDescanso(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).agregarDiaDescanso(mapping, form, request, response);
    }

    private ActionForward eliminarDiaAnterior(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).eliminarDiaAnterior(mapping, form, request, response);
    }

    private ActionForward guardarHorario(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return (new HorariosProxy()).guardarHorario(mapping, form, request, response);
    }
    
	private ActionForward guardarHorarioDescansos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return (new HorariosProxy()).guardarHorarioDescansos(mapping, form, request, response);
	}
}