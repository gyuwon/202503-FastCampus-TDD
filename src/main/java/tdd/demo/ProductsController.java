package tdd.demo;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    public ProductsController() {
    }

    @PostMapping("/api/products")
    ResponseEntity<?> createProduct() {
        URI location = URI.create("/api/products/1");
        return ResponseEntity.created(location).build();
    }
}
