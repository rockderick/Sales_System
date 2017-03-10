/*
 * Creado el 10/03/2005
 *
 * Para cambiar la plantilla para este archivo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
package mx.com.iusacell.catalogo.utilerias;

/**
 * @author Dvazqueza
 *
 * Para cambiar la plantilla para este comentario de tipo generado vaya a
 * Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y comentarios
 */
public class DigitoVerificador {

	private long digitoVerificador=0;
	
	public DigitoVerificador(){
		this.digitoVerificador = 0;
	}
	
	public long setDigitoVerificador(long id){
		
		String codigo = String.valueOf(id);
		long intLargo = (long) codigo.length();

			if ((intLargo % 2) == 1){
				intLargo=intLargo+1;
				codigo = "0" + codigo;
			}

		long intContPar=0;
		long intContImpar=0;
		
			for(int i=0; i < intLargo; i++){
				if (((i+1)%2)== 0){ 
					intContPar = intContPar +  Long.parseLong(String.valueOf(codigo.charAt(i))) * 3;
				}else{
					intContImpar = intContImpar + Long.parseLong(String.valueOf(codigo.charAt(i)));
				}
				
			}

			long intTotal = intContPar + intContImpar;
			long intCV = 0;

			while(String.valueOf(intTotal).substring(String.valueOf(intTotal).length() -1).compareTo("0") != 0){
				intTotal = intTotal + 1;
			   	intCV = intCV + 1;
			}

			return this.digitoVerificador = intCV;
	}


	/**
	 * @return
	 */
	public long getDigitoVerificador() {
		return digitoVerificador;
	}

}
