package PurchaseRegister.DataModels;

import java.io.*;
import java.math.*;

/**
 * This class represent data element of monthly statistical data transfer towards frontend.
 * @see #StatMonthlyTransfer(Long, Long, BigDecimal, Long, BigDecimal)
 * @see #getYear()
 * @see #getMonth()
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 */
public class StatMonthlyTransfer implements Serializable {

	private final Long year;
	private final Long month;
	private final BigDecimal total;
	private final Long count;
	private final BigDecimal average;

	public StatMonthlyTransfer(Long year, Long month, BigDecimal total, Long count, BigDecimal average) {
		this.year = year;
		this.month = month;
		this.total = total;
		this.count = count;
		this.average = average;
	}

	public Long getYear() {
		return year;
	}

	public Long getMonth() {
		return month;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Long getCount() {
		return count;
	}

	public BigDecimal getAverage() {
		return average;
	}
}
