package ca.sheridancollege.minap.assignmentone.service;



import ca.sheridancollege.minap.assignmentone.model.Product;
import ca.sheridancollege.minap.assignmentone.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();

    // Mimic repository methods for demonstration
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Product save(Product product) {
        products.add(product);
        return product;
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(product -> product.getId().equals(id)).findFirst();
    }
}


// Additional methods can be added to implement more complex business logic,
// such

