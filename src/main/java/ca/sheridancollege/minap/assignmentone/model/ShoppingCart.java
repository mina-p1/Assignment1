package ca.sheridancollege.minap.assignmentone.model;


import lombok.Getter;

import java.util.*;


public class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public double calculateTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public int getTotalItemCount() {
        return products.size();
    }
}


