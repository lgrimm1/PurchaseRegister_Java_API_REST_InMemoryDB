package PurchaseRegister.DataModels;

import java.io.*;

/**
 * This class represent data element of monthly statistical data transfer towards frontend.
 * @see #StatMonthlyTransfer(int, int, double, int, double)
 * @see #getYear()
 * @see #getMonth()
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 */
public class StatMonthlyTransfer implements Serializable {

	private final Integer year;
	private final Integer month;
	private final Double total;
	private final Integer count;
	private final Double average;

	public StatMonthlyTransfer(int year, int month, double total, int count, double average) {
		this.year = year;
		this.month = month;
		this.total = total;
		this.count = count;
		this.average = average;
	}

	public Integer getYear() {
		return year;
	}

	public Integer getMonth() {
		return month;
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
