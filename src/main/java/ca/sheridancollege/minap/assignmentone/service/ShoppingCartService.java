package ca.sheridancollege.minap.assignmentone.service;

import ca.sheridancollege.minap.assignmentone.model.Product;
import ca.sheridancollege.minap.assignmentone.model.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    public static final String SHOPPING_CART = "shoppingCart";

    public void addProductToCart(Product product, HttpSession session) {
        ShoppingCart cart = getOrCreateCart(session);
        cart.addProduct(product);
        session.setAttribute(SHOPPING_CART, cart);
    }

    public List<Product> getProductsInCart(HttpSession session) {
        ShoppingCart cart = getOrCreateCart(session);
        return cart.getProducts();
    }

    public int getCartSize(HttpSession session) {
        ShoppingCart cart = getOrCreateCart(session);
        return cart.getTotalItemCount();
    }

    public double calculateSubtotal(HttpSession session) {
        ShoppingCart cart = getOrCreateCart(session);
        return cart.calculateTotal();
    }



    public double calculateTax(HttpSession session) {
        double subtotal = calculateSubtotal(session);
        final double TAX_RATE = 0.13; // Assuming a 13% tax rate
        return subtotal * TAX_RATE;
    }

    public double calculateTotal(HttpSession session) {
        double subtotal = calculateSubtotal(session);
        double tax = calculateTax(session);
        return subtotal + tax;
    }

    public ShoppingCart getOrCreateCart(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute(SHOPPING_CART);
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute(SHOPPING_CART, cart);
        }
        return cart;
    }




}
