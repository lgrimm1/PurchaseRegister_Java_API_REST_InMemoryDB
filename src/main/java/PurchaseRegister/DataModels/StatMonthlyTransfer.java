package PurchaseRegister.DataModels;

/**
 * This class represent data element of annual statistical data transfer towards frontend.
 * @see #StatMonthlyTransfer(int, int, double, int, double)
 * @see #getYear()
 * @see #getMonth()
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @author Laszlo Grimm
 */
public class StatMonthlyTransfer extends StatAnnualTransfer {

	private final Integer month;

	public StatMonthlyTransfer(int year, int month, double total, int count, double average) {
		super(year, total, count, average);
		this.month = month;
	}

	public Integer getMonth() {
		return month;
	}
}
