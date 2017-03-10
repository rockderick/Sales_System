/*
 * Creado el Jul 1, 2005
 *
 */
package mx.com.iusacell.catalogo.utilerias;

import java.util.*;
/**
 * @author Dvazqueza
 *
 */
public class MyDir {
  private String path;
  private String name;
   // All files in this directory:
  private Vector files = new Vector();
   // All directories in this directory:
  private Vector dirs = new Vector();

  public MyDir(String path, String name) {
	this.path = path;
	this.name = name;
  }  
  
  public String getName() { return name; }  

  public String getPath() { return path; }  
  
  public void addFile(MyFile f) { files.addElement(f); }  

  public void addDir(MyDir d) { dirs.addElement(d); }  
  
  public Vector getFiles() { return files; }  
  
  public Vector getDirs() { return dirs; }  
  
}