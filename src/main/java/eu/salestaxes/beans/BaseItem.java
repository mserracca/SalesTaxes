/**
 * 
 */
package eu.salestaxes.beans;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Base Item
 * 
 * @author Marco Serracca
 *
 */
public abstract class BaseItem implements Item, Serializable {

	private static final long serialVersionUID = -2944041932753400606L;

	private static final BigDecimal TAX_RATE = new BigDecimal("0.10");
	private static final BigDecimal IMPORT_RATE = new BigDecimal("0.05");
	private static final double ROUND_VAL = 0.05D;
	private static final int DEC_NUMBER = 2;

	protected abstract boolean isTaxFree();

	/**
	 * Constructor
	 * 
	 * @param itemName
	 * @param itemPrice
	 */
	public BaseItem(String itemName, BigDecimal itemPrice) {
		setName(itemName);
		setPrice(itemPrice);
		setImported(false);
	}

	/**
	 * Constructor
	 * 
	 * @param itemName
	 * @param itemPrice
	 * @param isImported
	 */
	public BaseItem(String itemName, BigDecimal itemPrice, boolean isImported) {
		setName(itemName);
		setPrice(itemPrice);
		setImported(isImported);
	}

	private BigDecimal getTaxes(BigDecimal price, BigDecimal taxRate) {

		BigDecimal taxes = price.multiply(taxRate);

		double taxesD = taxes.doubleValue();
		double mod = taxesD % ROUND_VAL;

		taxesD += (mod == 0) ? 0 : (ROUND_VAL - mod);
		taxes = new BigDecimal(taxesD).setScale(DEC_NUMBER, BigDecimal.ROUND_HALF_UP);

		return taxes;

	}

	public BigDecimal getShelfPrice() {

		if (getPrice() != null) {
			return getPrice().add(getSalesTaxes());
		}
		return BigDecimal.ZERO;
	}

	public BigDecimal getSalesTaxes() {

		BigDecimal saleTaxes = BigDecimal.ZERO;

		if (getPrice() != null) {
			if (!isTaxFree()) {
				saleTaxes = getTaxes(getPrice(), TAX_RATE);
			}
			if (isImported()) {
				saleTaxes = saleTaxes.add(getTaxes(getPrice(), IMPORT_RATE));
			}
			return saleTaxes;
		}
		return saleTaxes;
	}

}
