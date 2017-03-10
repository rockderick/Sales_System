package mx.com.iusacell.catalogo.utilerias;




/**
 * This class is used to build the Value Object that comes
 * from the response of the web service VerificaUsuarioService
 * using the method ValidaLlaveMaestraIusacell 
 *   
 * @author rj148m
 * 
 */
public class ValidacionLlaveVO  implements java.io.Serializable {
   
	private static final long serialVersionUID = 1L;

	private boolean auth;

    private boolean cambioLlave;

    private java.lang.String fechaCad;

    private java.lang.String mensaje;

    public ValidacionLlaveVO() {
    }

    public ValidacionLlaveVO(
           boolean auth,
           boolean cambioLlave,
           java.lang.String fechaCad,
           java.lang.String mensaje) {
           this.auth = auth;
           this.cambioLlave = cambioLlave;
           this.fechaCad = fechaCad;
           this.mensaje = mensaje;
    }


    /**
     * Gets the auth value for this ValidacionLlaveVO.
     * 
     * @return auth
     */
    public boolean getAuth() {
        return auth;
    }


    /**
     * Sets the auth value for this ValidacionLlaveVO.
     * 
     * @param auth
     */
    public void setAuth(boolean auth) {
        this.auth = auth;
    }


    /**
     * Gets the cambioLlave value for this ValidacionLlaveVO.
     * 
     * @return cambioLlave
     */
    public boolean getCambioLlave() {
        return cambioLlave;
    }


    /**
     * Sets the cambioLlave value for this ValidacionLlaveVO.
     * 
     * @param cambioLlave
     */
    public void setCambioLlave(boolean cambioLlave) {
        this.cambioLlave = cambioLlave;
    }


    /**
     * Gets the fechaCad value for this ValidacionLlaveVO.
     * 
     * @return fechaCad
     */
    public java.lang.String getFechaCad() {
        return fechaCad;
    }


    /**
     * Sets the fechaCad value for this ValidacionLlaveVO.
     * 
     * @param fechaCad
     */
    public void setFechaCad(java.lang.String fechaCad) {
        this.fechaCad = fechaCad;
    }


    /**
     * Gets the mensaje value for this ValidacionLlaveVO.
     * 
     * @return mensaje
     */
    public java.lang.String getMensaje() {
        return mensaje;
    }


    /**
     * Sets the mensaje value for this ValidacionLlaveVO.
     * 
     * @param mensaje
     */
    public void setMensaje(java.lang.String mensaje) {
        this.mensaje = mensaje;
    }

   

   

   

}
