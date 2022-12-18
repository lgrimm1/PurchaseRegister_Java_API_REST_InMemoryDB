package PurchaseRegister.DataModels;

/**
 * This class represent data element of annual statistical data transfer towards frontend.
 * @see #StatAnnualTransfer(int, double, int, double)
 * @see #getYear()
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 */
public class StatAnnualTransfer {
	private final int year;
	private final double total;
	private final int count;
	private final double average;

	public StatAnnualTransfer(int year, double total, int count, double average) {
		this.year = year;
		this.total = total;
		this.count = count;
		this.average = average;
	}

	public int getYear() {
		return year;
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
