package layout;

import products.AvailableProducts;

import java.text.DecimalFormat;
import java.util.*;

public class RabatApplication {

    private Map<Long, Double> productDetails;
    private List<String> productName;

    private LayoutApplication layoutApplication;

    public Double rabat = 100.0;

    public RabatApplication() {
        productDetails = initializeProducts();
        layoutApplication = new LayoutApplication(this, productDetails, productName);
    }

    public Map<Long, Double> initializeProducts() {
        Map<Long, Double> product = new LinkedHashMap<>();
        productName = new ArrayList<>();
        AvailableProducts[] availableProducts = AvailableProducts.values();

        if (availableProducts.length > 5) {
            System.out.println("More than 5 items were entered in enum class. The program is terminated.");
            System.exit(0);
        }

        for (AvailableProducts availableProducts1 : availableProducts) {
            product.put(availableProducts1.getId(), availableProducts1.getPrice());
            productName.add(availableProducts1.getProduct());
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
        Map<Long, Double> partOfTotalPrice = new LinkedHashMap<>();

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

    public void setRabatForItems(Map<Long, Double> productDetails, Map<Long, Double> discountForItems) {
        List<Double> priceAfterDiscount = new ArrayList<>();
        DecimalFormat dec = new DecimalFormat("#0.00");

        for (Map.Entry<Long, Double> entry : productDetails.entrySet()) {
            if (entry.getValue() - discountForItems.get(entry.getKey()) < 0) {
                System.out.println("The discount is too high and the price is negative. The program is terminated.");
                System.exit(0);
            }

            productDetails.replace(entry.getKey(), entry.getValue() - discountForItems.get(entry.getKey()));
            priceAfterDiscount.add(Double.parseDouble(dec.format(entry.getValue()).replace(",", ".")));
        }
        layoutApplication.setPriceAfterDiscount(priceAfterDiscount);
    }

    public void initDiscount() {
        Double minimalPrice = findMinimalItemsPrice(productDetails);
        Double sumItemsPrice = sumAllItemsPrice(productDetails);
        Map<Long, Double> partTotalPriceItems = calcManyTimesCostlyThanCheapestItem(productDetails, minimalPrice);
        Map<Long, Double> discountForItems = calculateRabatForItems(partTotalPriceItems, sumItemsPrice, minimalPrice);
        setRabatForItems(productDetails, discountForItems);
    }

    public void clearData() {
        productDetails.clear();
        productName.clear();
        productDetails = initializeProducts();
    }

    public static void main(String[] args) {
        new RabatApplication();
    }
}
