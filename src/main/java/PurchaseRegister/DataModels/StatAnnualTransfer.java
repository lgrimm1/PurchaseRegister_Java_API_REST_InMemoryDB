package PurchaseRegister.DataModels;

/**
 * This class represent data element of annual statistical data transfer towards frontend.
 * @author Laszlo Grimm
 */
public record StatAnnualTransfer(int year, double total, int count, double average) {}
