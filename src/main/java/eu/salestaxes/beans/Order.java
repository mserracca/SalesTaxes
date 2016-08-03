package eu.salestaxes.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Order containing a list of items
 * 
 * @author Marco Serracca
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 2391113322316471800L;

	private static final String SEP = ": ";
	private static final String SPACE = " ";
	private static final String NEWLINE = "\n";
	private static final String TAXES_MSG = "Sales Taxes: %s";
	private static final String TOTAL_MSG = "Total: %s";

	private static final int MAP_SIZE = 10;

	private BigDecimal total = new BigDecimal(0);
	private BigDecimal taxes = new BigDecimal(0);

	private Map<String, Item> itemsMap;

	/**
	 * Constructor
	 */
	public Order() {
		itemsMap = new LinkedHashMap<>(MAP_SIZE);
	}

	/**
	 * Constructor
	 * 
	 * @param itemsNumber
	 *            Initial capacity for items
	 */
	public Order(int itemsNumber) {

		itemsMap = new LinkedHashMap<>(itemsNumber > 0 ? itemsNumber : MAP_SIZE);
	}

	/**
	 * Add the item to the order
	 * 
	 * @param item
	 */
	public void addItem(Item item) {

		if (item != null) {
			Item existingItem = itemsMap.get(item.getName());
			if (existingItem == null) {
				itemsMap.put(item.getName(), item);
			} else {
				int previousCount = existingItem.getCount();
				existingItem.setCount(++previousCount);
			}
		}
	}

	/**
	 * Remove the item from the order
	 * 
	 * @param item
	 */
	public void removeItem(Item item) {

		if (item != null) {
			Item existingItem = itemsMap.get(item.getName());
			if (existingItem != null) {
				int previousCount = existingItem.getCount();
				if (previousCount > 0) {
					existingItem.setCount(--previousCount);
				} else {
					itemsMap.remove(item);
				}
			}
		}
	}

	/**
	 * Return the total amount of the order
	 * 
	 * @return amount
	 */
	public BigDecimal getTotal() {

		return total;
	}

	/**
	 * Return the total amount of the taxes
	 * 
	 * @return taxes amount
	 */
	public BigDecimal getTaxes() {

		return taxes;
	}

	/**
	 * Purchase the order
	 * 
	 * @return Order's description
	 */
	public String purchase() {

		total = new BigDecimal(0);
		taxes = new BigDecimal(0);

		StringBuilder sb = new StringBuilder(1024);

		for (Item item : itemsMap.values()) {
			BigDecimal itemsCount = new BigDecimal(item.getCount());
			total = total.add(item.getShelfPrice().multiply(itemsCount));
			taxes = taxes.add(item.getSalesTaxes().multiply(itemsCount));

			sb.append(itemsCount).append(SPACE).append(item.getName());
			sb.append(SEP).append(item.getShelfPrice().multiply(itemsCount)).append(NEWLINE);
		}
		sb.append(String.format(TAXES_MSG, taxes)).append(NEWLINE);
		sb.append(String.format(TOTAL_MSG, total));
		return sb.toString();
	}
}
