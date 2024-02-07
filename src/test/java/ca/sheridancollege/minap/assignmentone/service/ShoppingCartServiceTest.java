package ca.sheridancollege.minap.assignmentone.service;

import ca.sheridancollege.minap.assignmentone.model.Product;
import ca.sheridancollege.minap.assignmentone.service.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartServiceTest {

    private ShoppingCartService shoppingCartService;
    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        shoppingCartService = new ShoppingCartService();
        session = new MockHttpSession();
    }

    @Test
    public void testAddProductToCart() {
        // Arrange
        Product product = new Product(123456789L, "Test Product", 19.99);

        // Act
        shoppingCartService.addProductToCart(product, session);

        // Assert
        assertEquals(1, shoppingCartService.getProductsInCart(session).size());
    }

    @Test
    public void testCalculateTotal() {
        // Arrange
        Product product1 = new Product(123456789L, "Test Product 1", 19.99);
        Product product2 = new Product(987654321L, "Test Product 2", 29.99);
        shoppingCartService.addProductToCart(product1, session);
        shoppingCartService.addProductToCart(product2, session);

        // Act
        double total = Double.parseDouble(shoppingCartService.calculateTotal(session));

        // Assert
        assertEquals(49.98, total);
    }
}
