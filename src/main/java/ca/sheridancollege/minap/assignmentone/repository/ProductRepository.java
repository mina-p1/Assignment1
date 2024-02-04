package ca.sheridancollege.minap.assignmentone.repository;


import ca.sheridancollege.minap.assignmentone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // This interface will automatically have methods such as:
    // - findAll()
    // - findById()
    // - save()
    // - deleteById()
    // ... and more, provided by JpaRepository.

    // You can define custom queries as needed, for example:
    // List<Product> findByNameContaining(String keyword);
}