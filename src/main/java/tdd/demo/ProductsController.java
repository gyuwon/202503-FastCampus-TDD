package tdd.demo;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    private final ProductRepository repository;

    public ProductsController(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/api/products")
    ResponseEntity<?> createProduct(@RequestBody CreateProductCommand command) {
        var product = new Product();
        product.setName(command.name());
        product.setPriceAmount(command.priceAmount());
        product.setStockQuantity(command.stockQuantity());
        repository.save(product);
        URI location = URI.create("/api/products/" + product.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/api/products/{id}")
    ResponseEntity<?> findProduct(@PathVariable Long id) {
        Optional<Product> result = repository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
