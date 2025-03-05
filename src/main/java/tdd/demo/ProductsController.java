package tdd.demo;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    private final ProductRepository repository;

    public ProductsController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/products")
    ResponseEntity<?> createProduct() {
        var product = new Product();
        repository.save(product);
        URI location = URI.create("/api/products/" + product.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/api/products/{id}")
    ResponseEntity<?> findProduct(@PathVariable Long id) {
        if (repository.existsById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
