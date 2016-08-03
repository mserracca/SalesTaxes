package eu.salestaxes.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import eu.salestaxes.beans.GenericItem;
import eu.salestaxes.beans.Order;
import eu.salestaxes.beans.UntaxedItem;

/**
 * JUnit class to verify the correctness of exercise's results
 * 
 * @author Marco Serracca
 *
 */
public class SalesTaxesTests {

	@Test
	public void test1() {

		Order order = new Order(3);
		order.addItem(new UntaxedItem("book", new BigDecimal("12.49")));
		order.addItem(new GenericItem("music CD", new BigDecimal("14.99")));
		order.addItem(new UntaxedItem("chocolate bar", new BigDecimal("0.85")));

		StringBuilder exerciseOutputBuilder = new StringBuilder(1024);
		exerciseOutputBuilder.append("1 book: 12.49\n");
		exerciseOutputBuilder.append("1 music CD: 16.49\n");
		exerciseOutputBuilder.append("1 chocolate bar: 0.85\n");
		exerciseOutputBuilder.append("Sales Taxes: 1.50\n");
		exerciseOutputBuilder.append("Total: 29.83");
		Assert.assertEquals(exerciseOutputBuilder.toString(), order.purchase());
	}

	@Test
	public void test2() {

		Order order = new Order(2);
		order.addItem(new UntaxedItem("imported box of chocolates", new BigDecimal("10.00"), true));
		order.addItem(new GenericItem("imported bottle of perfume", new BigDecimal("47.50"), true));

		StringBuilder exerciseOutputBuilder = new StringBuilder(1024);
		exerciseOutputBuilder.append("1 imported box of chocolates: 10.50\n");
		exerciseOutputBuilder.append("1 imported bottle of perfume: 54.65\n");
		exerciseOutputBuilder.append("Sales Taxes: 7.65\n");
		exerciseOutputBuilder.append("Total: 65.15");
		Assert.assertEquals(exerciseOutputBuilder.toString(), order.purchase());
	}

	@Test
	public void test3() {

		Order order = new Order(4);
		order.addItem(new GenericItem("imported bottle of perfume", new BigDecimal("27.99"), true));
		order.addItem(new GenericItem("bottle of perfume", new BigDecimal("18.99")));
		order.addItem(new UntaxedItem("packet of headache pills", new BigDecimal("9.75")));
		order.addItem(new UntaxedItem("imported box of chocolates", new BigDecimal("11.25"), true));

		StringBuilder exerciseOutputBuilder = new StringBuilder(1024);
		exerciseOutputBuilder.append("1 imported bottle of perfume: 32.19\n");
		exerciseOutputBuilder.append("1 bottle of perfume: 20.89\n");
		exerciseOutputBuilder.append("1 packet of headache pills: 9.75\n");
		exerciseOutputBuilder.append("1 imported box of chocolates: 11.85\n");
		exerciseOutputBuilder.append("Sales Taxes: 6.70\n");
		exerciseOutputBuilder.append("Total: 74.68");
		Assert.assertEquals(exerciseOutputBuilder.toString(), order.purchase());
	}
}
