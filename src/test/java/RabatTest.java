import layout.RabatApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RabatTest {

    @Test
    public void initializeProducts() {
        RabatApplication rabatApplication = new RabatApplication();
        Map<Long, Double> products = rabatApplication.initializeProducts();

        Double itemPrice = products.get(2L);

        assertNotNull(itemPrice);
        Assertions.assertNotEquals(800.0, itemPrice);
        assertEquals(1000.0, itemPrice);
    }

    @Test
    public void findMinimalValueInMap() {
        RabatApplication rabatApplication = new RabatApplication();
        Map<Long, Double> products = new HashMap<>();

        products.put(1L, 1500.0);
        products.put(2L, 1200.0);
        products.put(3L, 700.0);
        products.put(4L, 9000.0);

        Double minimalPrice = rabatApplication.findMinimalItemsPrice(products);

        assertNotNull(minimalPrice);
        Assertions.assertNotEquals(1200.0, minimalPrice);
        assertEquals(700.0, minimalPrice);
    }

    @Test
    public void sumAllPriceFromProducts() {
        RabatApplication rabatApplication = new RabatApplication();
        Map<Long, Double> products = new HashMap<>();

        products.put(1L, 1500.0);
        products.put(2L, 1200.0);
        products.put(3L, 700.0);
        products.put(4L, 9000.0);

        Double minimalPrice = rabatApplication.sumAllItemsPrice(products);

        assertNotNull(minimalPrice);
        Assertions.assertNotEquals(17700.0, minimalPrice);
        assertEquals(12400.0, minimalPrice);
    }

    @Test
    public void calcManyTimesCostlyThanCheapestItem() {
        RabatApplication rabatApplication = new RabatApplication();
        Map<Long, Double> products = new HashMap<>();

        products.put(1L, 1500.0);
        products.put(2L, 2000.0);
        products.put(3L, 500.0);
        products.put(4L, 4000.0);

        Double minimalValue = Collections.min(products.values());

        Map<Long, Double> result = rabatApplication.calcManyTimesCostlyThanCheapestItem(products, minimalValue);

        Double partOfTotalPrice = result.get(1L);

        assertNotNull(partOfTotalPrice);
        assertEquals(3.0, partOfTotalPrice);
        Assertions.assertNotEquals(3.3, partOfTotalPrice);
    }

    @Test
    public void calculateRabatForItems() {
        RabatApplication rabatApplication = new RabatApplication();
        Map<Long, Double> products = new HashMap<>();

        products.put(1L, 1500.0);
        products.put(2L, 2000.0);
        products.put(3L, 500.0);
        products.put(4L, 4000.0);

        Double minimalValue = Collections.min(products.values());

        Double sumAllItems = products.values().stream().mapToDouble(item -> item).sum();

        Map<Long, Double> result = rabatApplication.calcManyTimesCostlyThanCheapestItem(products, minimalValue);

        Map<Long, Double> calculatedRabat = rabatApplication.calculateRabatForItems(result, sumAllItems, minimalValue);

        assertEquals(25.0, calculatedRabat.get(2L));
        assertEquals(6.25, calculatedRabat.get(3L));
        Assertions.assertNotEquals(5.5, calculatedRabat.get(1L));
    }

    @Test
    public void setRabatForItems() {
        RabatApplication rabatApplication = new RabatApplication();
        Map<Long, Double> products = new HashMap<>();

        products.put(1L, 1500.0);
        products.put(2L, 2000.0);
        products.put(3L, 500.0);
        products.put(4L, 4000.0);

        Double minimalValue = Collections.min(products.values());

        Double sumAllItems = products.values().stream().mapToDouble(item -> item).sum();

        Map<Long, Double> result = rabatApplication.calcManyTimesCostlyThanCheapestItem(products, minimalValue);

        Map<Long, Double> calculatedRabat = rabatApplication.calculateRabatForItems(result, sumAllItems, minimalValue);

        Assertions.assertNotEquals(500.0, calculatedRabat.get(1L));
        assertEquals(493.75, products.get(3L) - calculatedRabat.get(3L));

    }


}
