package PurchaseRegister.DataModels;

/**
 * <b>This class represents a statistics element.</b>
 * @see #Stat(double)
 * @see #addTotal(double)
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 * @since 06-11-2022
 */
public class Stat {

	private double total;
	private int count;

	/**
	 * <b>Constructs a new Stat element.</b>
	 * @param newTotal	double of new total.
	 */
	public Stat(double newTotal) {
		total = newTotal;
		count = 1;
	}

	/**
	 * <b>Adds a new total to the Stat, increments count.</b>
	 * @param newTotal	double of new total.
	 */
	public void addTotal(double newTotal) {
		total += newTotal;
		count++;
	}

	/**
	 * <b>Returns total of the Stat.</b>
	 * @return	double of total.
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * <b>Returns count of the Stat.</b>
	 * @return	int of count.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * <b>Returns average of the Stat by dividing stored total with stored count.</b>
	 * @return	double of average.
	 */
	public double getAverage() {
		return total / count;
	}
}
