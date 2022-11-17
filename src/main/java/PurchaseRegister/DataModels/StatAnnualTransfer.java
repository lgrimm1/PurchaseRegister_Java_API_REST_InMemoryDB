package PurchaseRegister.DataModels;

import java.io.*;

/**
 * This class represent data element of annual statistical data transfer towards frontend.
 * @see #StatAnnualTransfer(int, double, int, double)
 * @see #getYear()
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 */
public class StatAnnualTransfer implements Serializable {
	private final Integer year;
	private final Double total;
	private final Integer count;
	private final Double average;

	public StatAnnualTransfer(int year, double total, int count, double average) {
		this.year = year;
		this.total = total;
		this.count = count;
		this.average = average;
	}

	public Integer getYear() {
		return year;
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
