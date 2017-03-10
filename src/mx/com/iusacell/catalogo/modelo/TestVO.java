/*
 * Created on Feb 25, 2005
 *
 */
package mx.com.iusacell.catalogo.modelo;

/**
 * @author Dvazqueza
 *
 */
import java.util.Date;

public class TestVO implements java.io.Serializable{
	
	private String ric; 
	private float price;
	private Date updated;
	
	//Empty Constructor
	public TestVO() {
	}

	//Real Constructor
	public TestVO(String ric, 
				  float price,
				  Date updated) {
			setRic(ric); 
			setPrice(price);
			setUpdated(updated);
	}

	/**
	 * @return
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @return
	 */
	public String getRic() {
		return ric;
	}

	/**
	 * @return
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * @param f
	 */
	public void setPrice(float f) {
		price = f;
	}

	/**
	 * @param string
	 */
	public void setRic(String string) {
		ric = string;
	}

	/**
	 * @param date
	 */
	public void setUpdated(Date date) {
		updated = date;
	}

}
