// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 21/07/2006 7:24:39 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RegistroVO.java

package mx.com.iusacell.catalogo.modelo;

import java.io.Serializable;

public class RegistroVO
    implements Serializable
{
	
	private int id;
	private String descripcion;

    public RegistroVO()
    {
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public int getId()
    {
        return id;
    }

    public void setDescripcion(String string)
    {
        descripcion = string;
    }

    public void setId(int i)
    {
        id = i;
    }


}