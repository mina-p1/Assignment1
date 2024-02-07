package ca.sheridancollege.minap.assignmentone.service;

import ca.sheridancollege.minap.assignmentone.model.Product;
import ca.sheridancollege.minap.assignmentone.model.ShoppingCart;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ShoppingCartService {

    private static final String SHOPPING_CART_ATTRIBUTE = "shoppingCart";

    public ShoppingCart getOrCreateCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART_ATTRIBUTE);
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute(SHOPPING_CART_ATTRIBUTE, cart);
        }
        return cart;
    }

    public void addProductToCart(Product product, HttpSession session) {
        ShoppingCart cart = getOrCreateCart(session);
        cart.addProduct(product);
    }



    public List<Product> getProductsInCart(HttpSession session) {
        ShoppingCart cart = getOrCreateCart(session);
        return cart.getProducts();
    }

    public int getCartSize(HttpSession session) {
        ShoppingCart cart = getOrCreateCart(session);
        return cart.getTotalItemCount();
    }

    public String calculateTotal(HttpSession session) {
        double subtotal = calculateSubtotal(session);
        double tax = calculateTax(session);
        BigDecimal total = BigDecimal.valueOf(subtotal + tax);

        // Round to 2 decimal places and convert to String format
        return String.format("%.2f", total.setScale(2, RoundingMode.HALF_UP));
    }



    public double calculateSubtotal(HttpSession session) {
        ShoppingCart cart = getOrCreateCart(session);
        return cart.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public double calculateTax(HttpSession session) {
        double subtotal = calculateSubtotal(session);
        final double TAX_RATE = 0.13; // Assuming a tax rate of 13%
        return subtotal * TAX_RATE;
    }


}