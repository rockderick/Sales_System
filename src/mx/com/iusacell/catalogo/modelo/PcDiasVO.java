// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 24/07/2006 12:36:28 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   PcDiasVO.java

package mx.com.iusacell.catalogo.modelo;


public class PcDiasVO
{

    public PcDiasVO()
    {
    }

    public int getPcCveDia()
    {
        return pcCveDia;
    }

    public String getPcDescDia()
    {
        return pcDescDia;
    }

    public void setPcCveDia(int i)
    {
        pcCveDia = i;
    }

    public void setPcDescDia(String string)
    {
        pcDescDia = string;
    }

    private int pcCveDia;
    private String pcDescDia;
}