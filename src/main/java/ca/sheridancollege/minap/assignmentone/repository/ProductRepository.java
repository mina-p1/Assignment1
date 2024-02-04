package ca.sheridancollege.minap.assignmentone.repository;


import ca.sheridancollege.minap.assignmentone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}