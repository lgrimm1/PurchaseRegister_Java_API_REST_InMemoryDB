package PurchaseRegister.DataModels;

import java.math.*;

/**
 * This class represents a statistics element.
 * @see #Stat(BigDecimal, Long)
 * @see #getTotal()
 * @see #getCount()
 * @see #getAverage()
 * @see #deepCopy()
 * @author Laszlo Grimm
 */
public class Stat {

	private final BigDecimal total;
	private final Long count;

	public Stat(BigDecimal newTotal, Long newCount) {
		total = newTotal;
		count = newCount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Long getCount() {
		return count;
	}

	public BigDecimal getAverage() {
		return BigDecimal.valueOf(total.doubleValue() / count);
	}

	public Stat deepCopy() {
		return new Stat(total, count);
	}
}
