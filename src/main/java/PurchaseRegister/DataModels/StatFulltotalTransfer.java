package PurchaseRegister.DataModels;

import java.io.*;

/**
 * This class represent data element of fulltotal statistical data transfer towards frontend.
 * @see #StatFulltotalTransfer(double, int, double)
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 */
public class StatFulltotalTransfer implements Serializable {
	private final Double total;
	private final Integer count;
	private final Double average;

	public StatFulltotalTransfer(double total, int count, double average) {
		this.total = total;
		this.count = count;
		this.average = average;
	}

	public Double getTotal() {
		return total;
	}

	public Integer getCount() {
		return count;
	}

	public Double getAverage() {
		return average;
	}
}
