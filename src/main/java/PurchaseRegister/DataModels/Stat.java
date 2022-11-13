package PurchaseRegister.DataModels;

/**
 * <b>This class represents a statistics element.</b>
 * @see #Stat(double, int)
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @see #deepCopy()
 * @author Laszlo Grimm
 * @since 13-11-2022
 */
public class Stat {

	private final double total;
	private final int count;

	public Stat(double newTotal, int newCount) {
		total = newTotal;
		count = newCount;
	}

	public double getTotal() {
		return total;
	}

	public int getCount() {
		return count;
	}

	public double getAverage() {
		return total / count;
	}

	public Stat deepCopy() {
		return new Stat(total, count);
	}
}
