package PurchaseRegister.DataModels;

/**
 * This class represent data element of full statistical data transfer towards frontend.
 * @see #StatFullTransfer(double, int, double)
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 */
public class StatFullTransfer {
	private final double total;
	private final int count;
	private final double average;

	public StatFullTransfer(double total, int count, double average) {
		this.total = total;
		this.count = count;
		this.average = average;
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
