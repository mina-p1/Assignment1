package ca.sheridancollege.minap.assignmentone.service;

import ca.sheridancollege.minap.assignmentone.model.Product;
import ca.sheridancollege.minap.assignmentone.model.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ShoppingCartService {

    public static final String SHOPPING_CART = "shoppingCart";
    private static final double TAX_RATE = 0.13; // 13% tax rate


    public void addProductToCart(Product product, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        if (cart == null) {
            cart = new ShoppingCart();
        }
        cart.addProduct(product);
        session.setAttribute(SHOPPING_CART, cart);
    }

    public HashMap<Product, Integer> getProductsInCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        return cart != null ? (HashMap<Product, Integer>) cart.getProducts() : new HashMap<>();
    }

    public int getCartSize(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        return cart != null ? cart.getTotalItemCount() : 0;
    }

    public double calculateSubtotal(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        if (cart == null) {
            return 0;
        }
        return cart.getProducts().entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public double calculateTax(HttpSession session) {
        double subtotal = calculateSubtotal(session);
        return subtotal * TAX_RATE;
    }

    public double calculateTotal(HttpSession session) {
        double subtotal = calculateSubtotal(session);
        double tax = calculateTax(session);
        return subtotal + tax;
    }

    




    // Other methods as required...
}
