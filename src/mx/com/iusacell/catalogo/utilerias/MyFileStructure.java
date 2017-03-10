/*
 * Creado el Jul 1, 2005
 *
 */
package mx.com.iusacell.catalogo.utilerias;
import org.apache.log4j.Logger;


/**
 * @author Dvazqueza
 *
 */
import java.io.*;
import java.util.*;

public class MyFileStructure{ 
	protected static final Logger logger = Logger.getLogger(MyFileStructure.class);
	
  private String dirname;
  private MyDir mdir;

  public void setDirname(String dirname) {
	this.dirname = dirname;
  }
  
  /*
  * Build a hierarchy of MyDir's and 
  * MyFile's for this directory
  */
  public void build() {
	File f = new File(dirname);
	mdir = build(f);
  }  

  /*
  * Build a hierarchy of MyDir's and 
  * MyFile's for "f"
  */
  private MyDir build(File f) { 
	if (!f.exists()) return null; 
	if (!f.isDirectory()) return null; 
    
	// f is an existing directory
	String path = f.getPath();
	String name = f.getName();
	MyDir mdir = new MyDir(path, name);


	// Loop thru files and directories in this path
	String[] files = f.list();
	for (int i = 0; i < files.length; i++) {
	  File f2 = new File(path, files[i]);
	  if (f2.isFile()) {
		mdir.addFile(new MyFile(path, files[i],new Date(f2.lastModified()).toString()));
	  } else if (f2.isDirectory()) {
		File f3 = new File(path, files[i]);
		MyDir m = build(f3); // recursive call
		if (m != null) { mdir.addDir(m); }  
	  }
	}
	return mdir;
  }  


  public void list() {
	  if (mdir == null) {
		
		logger.error("Directorio no Válido");
		return;
	  }  
	  
	  logger.info("Directorio: " + mdir.getPath());
	  list(mdir);
	}
  
	public void list(MyDir m) {
	  // First list the directories 
	  Vector md = m.getDirs();
	  for (int i = 0; i < md.size(); i++) {
		MyDir d = (MyDir)md.elementAt(i);
		
		logger.info("Dir " + d.getPath());
		list(d); // recursive call
	  }  
	  // And now list the files 
	  Vector mf = m.getFiles();
	  for (int i = 0; i < mf.size(); i++) {
		MyFile f = (MyFile)mf.elementAt(i);
		logger.info("Archivo " + f.getPath() + File.separator + f.getName());
		logger.info("Fecha " + f.getFecha());
		
	  }  
	}

}