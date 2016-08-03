package eu.salestaxes.beans;

import java.math.BigDecimal;

/**
 * Generic taxed item
 * 
 * @author Marco Serracca
 *
 */
public class GenericItem extends BaseItem {

	private static final long serialVersionUID = -484496294113154103L;

	private String name;
	private BigDecimal price;
	private boolean isImported;
	private int count = 1;

	/**
	 * Constructor
	 * 
	 * @param itemName
	 * @param itemPrice
	 */
	public GenericItem(String itemName, BigDecimal itemPrice) {

		super(itemName, itemPrice);
	}

	/**
	 * Constructor
	 * 
	 * @param itemName
	 * @param itemPrice
	 * @param isImported
	 */
	public GenericItem(String itemName, BigDecimal itemPrice, boolean isImported) {

		super(itemName, itemPrice, isImported);
	}

	@Override
	public String getName() {

		return name;
	}

	@Override
	public void setName(String newName) {

		name = newName;
	}

	@Override
	public BigDecimal getPrice() {

		return price;
	}

	@Override
	public void setPrice(BigDecimal newPrice) {

		price = newPrice;
	}

	@Override
	public boolean isImported() {

		return isImported;
	}

	@Override
	public void setImported(boolean isImported) {

		this.isImported = isImported;
	}

	@Override
	protected boolean isTaxFree() {

		return false;
	}

	@Override
	public int getCount() {

		return count;
	}

	@Override
	public void setCount(int newCount) {

		count = newCount;
	}

}
