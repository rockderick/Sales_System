/*
 * Created on 15/12/2004
 *
 */
package mx.com.iusacell.catalogo.utilerias;


import java.util.*;
/**
 * @author jojeda
 *
 */
public class PaginaDeElementos {

	private int pageNum = 1;
	private int pageSize = -1;
	private int queryId = 0;
	private int totalPages = 0;
	private Object[] parametrosQuery = new Object[] { };
	private Class VOClass;
	private List listaCompleta = null;

	private GeneralDAO dao = new GeneralDAO();

	/**
	 * Constructor para paginar elementos generados a traves de un query
	 * @param query El id del query con el que se van obtener los resultados
	 * @param parametros El parametro del query
	 * @param clase La clase que representa el Value Object de los elementos
	 */
	public PaginaDeElementos(int query, Object[] parametros, Class clase) {
		queryId = query;
		parametrosQuery = parametros;
		VOClass = clase;
	}
	
	/**
	 * Constructor para paginar elementos de una lista previamente disponible
	 * @param lista
	 */
	public PaginaDeElementos(List lista) {
		listaCompleta = lista;
	}

	public void setPageNum(int i) {
		pageNum = i;
	}

	public int getCurrentPageNum() {
		return pageNum;
	}

	public void setPageSize(int i) {
		pageSize = i;
	}
	
	public int getTotalPageNum() {
		int totalCount = 0;
		if (totalPages == 0) {
			if (queryId != 0) {
				totalCount = dao.getCount(queryId, parametrosQuery);
			} else {
				//validacion si es null
				if ( listaCompleta == null )
					totalCount = 0;
				else
					totalCount = listaCompleta.size();
			}
			if (pageSize > 0) {
				totalPages = (int) Math.floor(totalCount / pageSize);
				// Compensar el numero total de paginas por el redondeo
				if ((totalCount % pageSize) != 0) {
					totalPages++;
				}
			} else {
				totalPages = 1;
			}
		}
		return totalPages;
	}

	public List getElements() {
		if(queryId != 0) {
			return getFromDao();
		}
		else if (listaCompleta != null) {
			return getFromList();
		}
		else {
			throw new RuntimeException("Excepcion en paginacion: No se ha establecido ni el query ni el List");
		}
	}

	public boolean hasPrev() {
		if (pageNum > 1)
			return true;
		else
			return false;
	}
	
	public int getPrevPageNum() {
		if(pageNum > 1)
			return (pageNum-1);
		else
			return 1;
	}

	public boolean hasNext() {
		if (pageNum < getTotalPageNum())
			return true;
		else
			return false;
	}
	
	public int getNextPageNum() {
		if(pageNum < getTotalPageNum()) 
			return (pageNum+1);
		else
			return pageNum;
	}
	
	public boolean next() {
		if(hasNext()) {
			pageNum++;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean previous() {
		if(hasPrev()) {
			pageNum--;
			return true;
		}
		else {
			return false;
		}
	}

	private List getFromDao() {		
		if (pageSize > 0) {
			return dao.findValueObjectsArray(queryId, parametrosQuery, VOClass, pageSize, offsetStart());
		}
		else {
			return dao.findValueObjectsArray(queryId, parametrosQuery, VOClass);			
		}	
	}

	private List getFromList() {
		if (pageSize > 0) {
			if(offsetStart() > (offsetEnd()-1 > listaCompleta.size() ? listaCompleta.size() : offsetEnd()-1)){
				return new ArrayList();
			}
			return listaCompleta.subList(offsetStart(), (offsetEnd()-1 > listaCompleta.size() ? listaCompleta.size() : offsetEnd()-1));
		} else {
			return listaCompleta;
		}
	}

	private int offsetStart() {
		return (pageNum-1)*pageSize;
	}

	private int offsetEnd() {
		return offsetStart()+pageSize;
	}

}

