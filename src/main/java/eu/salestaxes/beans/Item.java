/**
 * 
 */
package eu.salestaxes.beans;

import java.math.BigDecimal;

/**
 * A simple item
 * 
 * @author Marco Serracca
 *
 */
public interface Item {
	
	public String getName();
	
	public void setName(String newName);
	
	public int getCount();
	
	public void setCount(int newCount);
	
	public BigDecimal getPrice();
	
	public void setPrice(BigDecimal newPrice);
	
	public BigDecimal getShelfPrice();
	
	public BigDecimal getSalesTaxes();
	
	public boolean isImported();
	
	public void setImported(boolean isImported);
}
