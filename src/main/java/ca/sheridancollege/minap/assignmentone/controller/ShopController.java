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


import java.util.List;
import java.util.Optional;

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
        model.addAttribute("product", new Product()); // Add this line to instantiate a new Product
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product";
    }


    @PostMapping("/product")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Product added successfully!");
        return "redirect:/product";
    }



    @GetMapping("/shopping")
    public String viewShoppingCart(Model model, HttpSession session) {
        model.addAttribute("products", shoppingCartService.getProductsInCart(session));
        model.addAttribute("cartSize", shoppingCartService.getCartSize(session));
        return "shopping";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long productId, HttpSession session) {
        Optional<Product> productOpt = productService.findById(productId);
        productOpt.ifPresent(product -> shoppingCartService.addProductToCart(product, session));
        return "redirect:/shopping";
    }


    @GetMapping("/checkout")
    public String viewCheckoutPage(Model model, HttpSession session) {
        model.addAttribute("cartItems", shoppingCartService.getProductsInCart(session));
        model.addAttribute("subtotal", shoppingCartService.calculateSubtotal(session));
        model.addAttribute("tax", shoppingCartService.calculateTax(session));
        model.addAttribute("total", shoppingCartService.calculateTotal(session));
        return "checkout";
    }




    // ... Additional methods for updating the cart, removing items, etc.
}

