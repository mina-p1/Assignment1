package ca.sheridancollege.minap.assignmentone.model;


import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShoppingCart class to hold products and their quantities.
 */
public class ShoppingCart {

    // Using LinkedHashMap to preserve the order of insertion.
    private Map<Product, Integer> products = new LinkedHashMap<>();

    // A variable to keep track of the total number of items in the cart.
    private int totalItemCount = 0;

    /**
     * Adds a product to the cart or increments its quantity if already exists.
     *
     * @param product the product to add.
     */
    public void addProduct(Product product) {
        products.merge(product, 1, Integer::sum);
        totalItemCount = products.values().stream().mapToInt(Integer::intValue).sum();
    }

    /**
     * Calculates the total cost of the items in the cart.
     *
     * @return the total cost.
     */
    public double calculateTotal() {
        return products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    /**
     * Gets an unmodifiable view of the products in the cart.
     *
     * @return the products in the cart.
     */
    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    /**
     * Gets the total number of items in the cart.
     *
     * @return the total number of items.
     */
    public int getTotalItemCount() {
        return totalItemCount;
    }
}

