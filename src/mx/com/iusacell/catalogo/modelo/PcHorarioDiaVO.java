// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 21/07/2006 2:06:05 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PcHorarioDiaVO.java

package mx.com.iusacell.catalogo.modelo;

import java.io.Serializable;

public class PcHorarioDiaVO
    implements Serializable
{
	
	private int pcCveHorario;
	private String pcDescHorario;
	private int pcCveDia;
	private int pcCveTipoRegistro;
	private String pcHora;
	private int pcDiaLaborable;
	private int pcEvento;
	private String descripcion;
	private int pcCveHora;
	private String pcDescHora;
	private String pcCveMinuto;
	private String pcDescMinuto;
	private String horaMinuto;
	
	
	private String pcDescDia;
	private String entrada;
	private String salidaComer;
	private String entradaComer;
	private String salida;
		

    public PcHorarioDiaVO()
    {
    }

    public int getPcCveDia()
    {
        return pcCveDia;
    }

    public int getPcCveHorario()
    {
        return pcCveHorario;
    }

    public int getPcCveTipoRegistro()
    {
        return pcCveTipoRegistro;
    }

    public String getPcHora()
    {
        return pcHora;
    }

    public void setPcCveDia(int i)
    {
        pcCveDia = i;
    }

    public void setPcCveHorario(int i)
    {
        pcCveHorario = i;
    }

    public void setPcCveTipoRegistro(int i)
    {
        pcCveTipoRegistro = i;
    }

    public void setPcHora(String string)
    {
        pcHora = string;
    }

    public int getPcDiaLaborable()
    {
        return pcDiaLaborable;
    }

    public int getPcEvento()
    {
        return pcEvento;
    }

    public void setPcDiaLaborable(int i)
    {
        pcDiaLaborable = i;
    }

    public void setPcEvento(int i)
    {
        pcEvento = i;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public String getPcDescDia()
    {
        return pcDescDia;
    }

    public void setDescripcion(String string)
    {
        descripcion = string;
    }

    public void setPcDescDia(String string)
    {
        pcDescDia = string;
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
	public String getEntrada() {
		return entrada;
	}

	/**
	 * @return
	 */
	public String getEntradaComer() {
		return entradaComer;
	}

	/**
	 * @return
	 */
	public String getSalida() {
		return salida;
	}

	/**
	 * @return
	 */
	public String getSalidaComer() {
		return salidaComer;
	}

	/**
	 * @param string
	 */
	public void setEntrada(String string) {
		entrada = string;
	}

	/**
	 * @param string
	 */
	public void setEntradaComer(String string) {
		entradaComer = string;
	}

	/**
	 * @param string
	 */
	public void setSalida(String string) {
		salida = string;
	}

	/**
	 * @param string
	 */
	public void setSalidaComer(String string) {
		salidaComer = string;
	}

	/**
	 * @return
	 */
	public int getPcCveHora() {
		return pcCveHora;
	}

	/**
	 * @return
	 */
	public String getPcCveMinuto() {
		return pcCveMinuto;
	}

	/**
	 * @return
	 */
	public String getPcDescHora() {
		return pcDescHora;
	}

	/**
	 * @return
	 */
	public String getPcDescMinuto() {
		return pcDescMinuto;
	}

	/**
	 * @param i
	 */
	public void setPcCveHora(int i) {
		pcCveHora = i;
	}

	/**
	 * @param i
	 */
	public void setPcCveMinuto(String i) {
		pcCveMinuto = i;
	}

	/**
	 * @param string
	 */
	public void setPcDescHora(String string) {
		pcDescHora = string;
	}

	/**
	 * @param string
	 */
	public void setPcDescMinuto(String string) {
		pcDescMinuto = string;
	}
	
	public String getHoraMinuto() {
		return getPcHora() + " : " + getPcCveMinuto();
	}

}