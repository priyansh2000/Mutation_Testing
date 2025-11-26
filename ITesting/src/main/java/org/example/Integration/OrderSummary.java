package org.example.Integration;

public class OrderSummary {
    private double subtotal;
    private double discount;
    private double tax;
    private double total;

    public OrderSummary(double subtotal, double discount, double tax, double total) {
        this.subtotal = subtotal;
        this.discount = discount;
        this.tax = tax;
        this.total = total;
    }

    // Getters
    public double getSubtotal() {
        return subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTax() {
        return tax;
    }

    public double getTotal() {
        return total;
    }
}
