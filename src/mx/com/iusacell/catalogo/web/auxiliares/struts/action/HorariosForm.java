// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 21/07/2006 1:53:27 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   HorariosForm.java

package mx.com.iusacell.catalogo.web.auxiliares.struts.action;

import org.apache.struts.action.ActionForm;

public class HorariosForm extends ActionForm{
	
	private String pcCveDia;
	private String pcDiaLaborable;
	private String pcEvento;
	private String pcHora;
	private String pcDescHorario;
	private String pcMinutos;

    public HorariosForm()
    {
    }

    public String getPcCveDia()
    {
        return pcCveDia;
    }

    public void setPcCveDia(String string)
    {
        pcCveDia = string;
    }

    public String getPcDiaLaborable()
    {
        return pcDiaLaborable;
    }

    public void setPcDiaLaborable(String string)
    {
        pcDiaLaborable = string;
    }

    public String getPcEvento()
    {
        return pcEvento;
    }

    public void setPcEvento(String string)
    {
        pcEvento = string;
    }

    public String getPcHora()
    {
        return pcHora;
    }

    public void setPcHora(String string)
    {
        pcHora = string;
    }

    public String getPcDescHorario()
    {
        return pcDescHorario;
    }

    public void setPcDescHorario(String string)
    {
        pcDescHorario = string;
    }


	/**
	 * @return
	 */
	public String getPcMinutos() {
		return pcMinutos;
	}

	/**
	 * @param string
	 */
	public void setPcMinutos(String string) {
		pcMinutos = string;
	}

}