package org.example;
import org.example.Integration.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class AppTest
{
    private final DiscountService discountService = new DiscountService();
    private final TaxService taxService = new TaxService();
    private final OrderProcessor orderProcessor = new OrderProcessor(discountService, taxService);

    private final List<OrderItem> sampleItems = Arrays.asList(
            new OrderItem("P1", 2, 500.0),
            new OrderItem("P2", 1, 300.0)
    );

    @Test
    void testProcessOrder()
    {
        OrderSummary summary = orderProcessor.processOrder(sampleItems, "gold", "NY");

        assertEquals(1300.0, summary.getSubtotal(), 0.001);  // Subtotal
        assertEquals(325.0, summary.getDiscount(), 0.001);  // Discount (base + bulk)
        assertEquals(82.875, summary.getTax(), 0.001);      // Tax on discounted total
        assertEquals(1057.875, summary.getTotal(), 0.001);  // Final total
    }


    @Test
    void testProcessOrderMutant1()
    {
        OrderSummary summary = orderProcessor.processOrderMutant1(sampleItems, "gold", "NY");

        // Invalid because Mutant 1 swaps parameters
        assertNotEquals(195.0, summary.getDiscount());
        assertNotEquals(94.05, summary.getTax());
    }

    @Test
    void testProcessOrderMutant2()
    {
        OrderSummary summary = orderProcessor.processOrderMutant2(sampleItems, "gold", "NY");

        // Invalid because Mutant 2 uses constant replacement
        assertNotEquals(195.0, summary.getDiscount());
        assertNotEquals(94.05, summary.getTax());
    }

    @Test
    void testProcessOrderMutant3()
    {
        OrderSummary summary = orderProcessor.processOrderMutant3(sampleItems, "gold", "NY");

        // Invalid because Mutant 3 introduces parameter duplication
        assertNotEquals(94.05, summary.getTax());
    }

    @Test
    void testProcessOrderWithDifferentInputs()
    {
        List<OrderItem> items = Arrays.asList(
                new OrderItem("P1", 5, 100.0),
                new OrderItem("P2", 2, 50.0)
        );

        OrderSummary summary = orderProcessor.processOrder(items, "silver", "CA");

        assertEquals(600.0, summary.getSubtotal(), 0.001);
        assertEquals(60.0, summary.getDiscount(), 0.001);
        assertEquals(51.3, summary.getTax(), 0.001);
        assertEquals(591.3, summary.getTotal(), 0.001);
    }


    @Test
    public void testProcessOrderIntegration()
    {
        List<OrderItem> items = Arrays.asList(
                new OrderItem("P1", 2, 500.0),// means 2*500=1000
                new OrderItem("P2", 1, 200.0)  // means 1*200=200
        );
        OrderSummary summary = orderProcessor.processOrder(items, "gold", "NY");
        double expectedSubtotal = 1200.0;                  // Sum of item totals
        double baseDiscount = expectedSubtotal * 0.15;    // 15% discount for "gold"
        double bulkDiscount = expectedSubtotal * 0.10;    // 10% bulk discount
        double expectedDiscount = baseDiscount + bulkDiscount;
        double discountedTotal = expectedSubtotal - expectedDiscount;
        double expectedTax = discountedTotal * 0.085;     // Tax rate for "NY" (8.5%)
        double expectedTotal = discountedTotal + expectedTax;

        assertEquals(expectedSubtotal, summary.getSubtotal(), 0.01, "Subtotal mismatch");
        assertEquals(expectedDiscount, summary.getDiscount(), 0.01, "Discount mismatch");
        assertEquals(expectedTax, summary.getTax(), 0.01, "Tax mismatch");
        assertEquals(expectedTotal, summary.getTotal(), 0.01, "Total mismatch");
    }

    @Test
    void testProcessOrderCheck()
    {
        double subtotal = 1300.0; // 1000.0 + 300.0
        double expectedDiscount = subtotal * 0.15 + subtotal * 0.10; // 15% + 10% bulk discount for gold tier
        double expectedTax = (subtotal - expectedDiscount) * 0.085; // Tax for NY region (8.5%)
        double expectedTotal = (subtotal - expectedDiscount) + expectedTax;

        // Create an OrderProcessor instance
        OrderProcessor orderProcessor = new OrderProcessor(new DiscountService(), new TaxService());

        // Process the order
        OrderSummary summary = orderProcessor.processOrder(sampleItems, "gold", "NY");

        // Assertions
        assertEquals(expectedDiscount, summary.getDiscount(), 0.001); // Check if discount matches
        assertEquals(expectedTax, summary.getTax(), 0.001); // Check if tax matches
        assertEquals(expectedTotal, summary.getTotal(), 0.001); // Check if total matches
    }
}
