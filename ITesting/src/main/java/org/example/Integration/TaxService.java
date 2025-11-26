package org.example.Integration;

import java.util.HashMap;
import java.util.Map;

public class TaxService {
    private final Map<String, Double> regionTaxRates;
    private static final double DEFAULT_TAX_RATE = 0.08;

    public TaxService() {
        regionTaxRates = new HashMap<>();
        regionTaxRates.put("NY", 0.085);
        regionTaxRates.put("CA", 0.095);
        regionTaxRates.put("TX", 0.0625);
    }

    public double calculateTax(double amount, String region) {
        return amount * regionTaxRates.getOrDefault(region, DEFAULT_TAX_RATE);
    }
}
