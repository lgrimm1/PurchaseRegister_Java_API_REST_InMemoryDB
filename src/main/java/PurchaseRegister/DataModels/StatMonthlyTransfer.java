package PurchaseRegister.DataModels;

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
public class StatMonthlyTransfer {

	private final int year;
	private final int month;
	private final double total;
	private final int count;
	private final double average;

	public StatMonthlyTransfer(int year, int month, double total, int count, double average) {
		this.year = year;
		this.month = month;
		this.total = total;
		this.count = count;
		this.average = average;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public double getTotal() {
		return total;
	}

	public int getCount() {
		return count;
	}

	public double getAverage() {
		return average;
	}
}
