package eu.salestaxes.beans;

import java.math.BigDecimal;

/**
 * Taxes exempt item
 * 
 * @author Marco Serracca
 *
 */
public class UntaxedItem extends GenericItem {

	private static final long serialVersionUID = -8895141383473637660L;

	/**
	 * Constructor
	 * 
	 * @param itemName
	 * @param itemPrice
	 */
	public UntaxedItem(String itemName, BigDecimal itemPrice) {

		super(itemName, itemPrice);
	}

	/**
	 * Constructor
	 * 
	 * @param itemName
	 * @param itemPrice
	 * @param isImported
	 */
	public UntaxedItem(String itemName, BigDecimal itemPrice, boolean isImported) {

		super(itemName, itemPrice, isImported);
	}

	@Override
	protected boolean isTaxFree() {

		return true;
	}

}
