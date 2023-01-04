package PurchaseRegister.DataModels;

/**
 * This class represent data element of monthly statistical data transfer towards frontend.
 * @author Laszlo Grimm
 */
public record StatMonthlyTransfer(int year, int month, double total, int count, double average) {}
