package org.example.Integration;

public class DiscountService {
    private static final double BULK_THRESHOLD = 1000.0;
    private static final double BULK_RATE = 0.1;

    public double calculateDiscount(double amount, String customerTier) {
        double discount = getBaseTierDiscount(amount, customerTier);

        if (amount > BULK_THRESHOLD) {
            discount += amount * BULK_RATE;
        }

        return discount;
    }

    private double getBaseTierDiscount(double amount, String customerTier) {
        switch (customerTier.toLowerCase()) {
            case "gold":
                return amount * 0.15;
            case "silver":
                return amount * 0.10;
            case "bronze":
                return amount * 0.05;
            default:
                return 0.0;
        }
    }
}
