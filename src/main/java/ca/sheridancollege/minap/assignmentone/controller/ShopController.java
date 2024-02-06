package ca.sheridancollege.minap.assignmentone.controller;

import ca.sheridancollege.minap.assignmentone.model.Product;
import ca.sheridancollege.minap.assignmentone.service.ProductService;
import ca.sheridancollege.minap.assignmentone.service.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ShopController {
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShopController(ProductService productService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/product")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("products", productService.findAll());
        return "product";
    }

    @PostMapping("/product")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        if (String.valueOf(product.getId()).length() == 9) {
            productService.save(product);
            redirectAttributes.addFlashAttribute("success", "Product added successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Product ID must be exactly 9 digits long.");
        }
        return "redirect:/product";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long productId, HttpSession session) {
        productService.findById(productId).ifPresent(product -> {
            // Add product details to the session
            shoppingCartService.addProductToCart(product, session);

            // Additionally, you can store product details as attributes in the session
            session.setAttribute("productId", product.getId());
            session.setAttribute("productName", product.getName());
            session.setAttribute("productPrice", product.getPrice());
        });
        return "redirect:/shopping";
    }

    @GetMapping("/shopping")
    public String viewShoppingCart(Model model, HttpSession session) {
        model.addAttribute("products", shoppingCartService.getProductsInCart(session));
        model.addAttribute("cartSize", shoppingCartService.getCartSize(session));
        return "shopping";
    }

    @GetMapping("/checkout")
    public String viewCheckoutPage(Model model, HttpSession session) {
        model.addAttribute("cartItems", shoppingCartService.getProductsInCart(session));
        model.addAttribute("subtotal", shoppingCartService.calculateSubtotal(session));
        model.addAttribute("tax", shoppingCartService.calculateTax(session));
        model.addAttribute("total", shoppingCartService.calculateTotal(session));
        return "checkout";
    }

    // Additional methods for updating the cart, removing items, etc., can be added as needed.
}
