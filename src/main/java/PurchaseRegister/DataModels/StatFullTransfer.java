package PurchaseRegister.DataModels;

import java.io.*;
import java.math.*;

/**
 * This class represent data element of full statistical data transfer towards frontend.
 * @see #StatFullTransfer(BigDecimal, Long, BigDecimal)
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 */
public class StatFullTransfer implements Serializable {
	private final BigDecimal total;
	private final Long count;
	private final BigDecimal average;

	public StatFullTransfer(BigDecimal total, Long count, BigDecimal average) {
		this.total = total;
		this.count = count;
		this.average = average;
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
