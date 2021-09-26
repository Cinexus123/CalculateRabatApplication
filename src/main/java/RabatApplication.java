import layout.LayoutApplication;
import products.AvailableProducts;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RabatApplication {

    private final Double rabat = 100.0;

    public RabatApplication() {
        Map<Long, Double> productDetails = initializeProducts();
        Double minimalPrice = findMinimalItemsPrice(productDetails);
        Double sumItemsPrice = sumAllItemsPrice(productDetails);
        Map<Long, Double> partTotalPriceItems = calcManyTimesCostlyThanCheapestItem(productDetails, minimalPrice);
        Map<Long, Double> discountForItems = calculateRabatForItems(partTotalPriceItems, sumItemsPrice, minimalPrice);
        setRabatForItems(productDetails, discountForItems);
        LayoutApplication layoutApplication = new LayoutApplication();
        layoutApplication.setLayout();
    }

    public Map<Long, Double> initializeProducts() {
        AvailableProducts[] availableProducts = AvailableProducts.values();
        Map<Long, Double> product = new HashMap<>();

        if (availableProducts.length > 5) {
            System.out.println("More than 5 items were entered in enum class. The program is terminated.");
            System.exit(0);
        }

        for (AvailableProducts availableProducts1 : availableProducts) {
            product.put(availableProducts1.getId(), availableProducts1.getPrice());
            System.out.println("The product before the discount with the number id: " + availableProducts1.getId() + " price : " + availableProducts1.getPrice());
        }
        return product;
    }

    public Double findMinimalItemsPrice(Map<Long, Double> productDetails) {
        return Collections.min(productDetails.values());
    }

    public Double sumAllItemsPrice(Map<Long, Double> productDetails) {
        return productDetails.values().stream().mapToDouble(item -> item).sum();
    }

    public Map<Long, Double> calcManyTimesCostlyThanCheapestItem(Map<Long, Double> productDetails, Double minimalPrice) {
        Map<Long, Double> partOfTotalPrice = new HashMap<>();

        for (Map.Entry<Long, Double> entry : productDetails.entrySet())
            partOfTotalPrice.put(entry.getKey(), entry.getValue() / minimalPrice);

        return partOfTotalPrice;
    }

    public Map<Long, Double> calculateRabatForItems(Map<Long, Double> partTotalPriceItems, Double sumItemsPrice, Double minimalPrice) {
        Double totalSumParts = sumItemsPrice / minimalPrice;
        Double rabatPrice = rabat / totalSumParts;

        for (Map.Entry<Long, Double> entry : partTotalPriceItems.entrySet())
            partTotalPriceItems.put(entry.getKey(), entry.getValue() * rabatPrice);

        return partTotalPriceItems;
    }

    public Map<Long, Double> setRabatForItems(Map<Long, Double> productDetails, Map<Long, Double> discountForItems) {
        DecimalFormat dec = new DecimalFormat("#0.00");
        System.out.println();

        for (Map.Entry<Long, Double> entry : productDetails.entrySet()) {
            if (entry.getValue() - discountForItems.get(entry.getKey()) < 0) {
                System.out.println("The discount is too high and the price is negative. The program is terminated.");
                System.exit(0);
            }

            productDetails.replace(entry.getKey(), entry.getValue() - discountForItems.get(entry.getKey()));
            System.out.println("The product after the discount with the number id: " + entry.getKey() + " price : " + dec.format(entry.getValue()));
        }
        return productDetails;
    }

    public static void main(String[] args) {
        new RabatApplication();
    }
}
