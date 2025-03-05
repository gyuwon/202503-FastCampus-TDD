package tdd.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {

    public ProductsController() {
    }

    @PostMapping("/api/products")
    ResponseEntity<?> createProduct() {
        return ResponseEntity.created(null).build();
    }
}
