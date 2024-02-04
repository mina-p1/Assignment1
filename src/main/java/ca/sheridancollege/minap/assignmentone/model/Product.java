package ca.sheridancollege.minap.assignmentone.model;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "products")
public class Product {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    // Default constructor is required by JPA
    public Product() {
    }

    // Additional constructors can be added as needed
    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters for all fields



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // hashCode, equals, and toString methods can be overridden as needed
}

