/*
 * Creado el Jul 1, 2005
 *
 */
package mx.com.iusacell.catalogo.utilerias;

/**
 * @author Dvazqueza
 *
 */
public class MyFile {
  private String path;
  private String name;
  private String fecha;

  /*
  * Set path and file name.
  * Example: For the file "MyFile.class" in 
  * "classes\hansen\playground" we have:
  *   file = "MyFile.class" and
  *   path = "classes\hansen\playground"
  */
  public MyFile(String path, String name, String fecha) {
	this.path = path;
	this.name = name;
	this.fecha= fecha;
  }  
  
  /*
  * Get the name of the file
  */
  public String getName() { return name; }  

  /*
  * Get the path of the file
  */
  public String getPath() { return path; }
  
  /*
  * Get the date of the file
  */
  public String getFecha() { return fecha; }
    
}