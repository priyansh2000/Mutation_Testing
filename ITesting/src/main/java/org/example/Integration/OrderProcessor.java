package org.example.Integration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProcessor
{
    private final DiscountService discountService;
    private final TaxService taxService;

    public OrderProcessor(DiscountService discountService, TaxService taxService)
    {
        this.discountService = discountService;
        this.taxService = taxService;
    }

    public OrderSummary processOrder(List<OrderItem> items, String customerTier, String region)
    {
        double subtotal = calculateSubtotal(items);
        double discount = discountService.calculateDiscount(subtotal, customerTier);
        double discountedTotal = subtotal - discount;
        double tax = taxService.calculateTax(discountedTotal, region);
        return new OrderSummary(subtotal, discount, tax, discountedTotal + tax);
    }

    private double calculateSubtotal(List<OrderItem> items)
    {
        return items.stream()
                .mapToDouble(OrderItem::calculateTotal)
                .sum();
    }

    // IVPR Mutant 1: Parameter Swap
    public OrderSummary processOrderMutant1(List<OrderItem> items, String customerTier, String region)
    {
        double subtotal = calculateSubtotal(items);
        double discount = discountService.calculateDiscount(subtotal, region);
        double discountedTotal = subtotal - discount;
        double tax = taxService.calculateTax(discountedTotal, customerTier);
        return new OrderSummary(subtotal, discount, tax, discountedTotal + tax);
    }

    // IVPR Mutant 2: Constant Replacement
    public OrderSummary processOrderMutant2(List<OrderItem> items, String customerTier, String region)
    {
        double subtotal = calculateSubtotal(items);
        double discount = discountService.calculateDiscount(subtotal, "gold");
        double discountedTotal = subtotal - discount;
        double tax = taxService.calculateTax(discountedTotal, "NY");
        return new OrderSummary(subtotal, discount, tax, discountedTotal + tax);
    }

    // IVPR Mutant 3: Parameter Duplication
    public OrderSummary processOrderMutant3(List<OrderItem> items, String customerTier, String region)
    {
        double subtotal = calculateSubtotal(items);
        double discount = discountService.calculateDiscount(subtotal, customerTier);
        double discountedTotal = subtotal - discount;
        double tax = taxService.calculateTax(discountedTotal, customerTier);
        return new OrderSummary(subtotal, discount, tax, discountedTotal + tax);
    }
}
