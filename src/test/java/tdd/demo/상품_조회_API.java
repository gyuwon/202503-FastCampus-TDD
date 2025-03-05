package tdd.demo;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class 상품_조회_API {

    @Test
    void 요청이_성공하면_200_OK_상태코드를_반환한다(
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        URI location = client.postForEntity(
            "/api/products",
            new CreateProductCommand("키보드", 10000, 100),
            Void.class
        ).getHeaders().getLocation();

        // Act
        ResponseEntity<Product> response = client.getForEntity(
            location,
            Product.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(200);
    }

    @Test
    void 존재하지_않는_식별자가_입력되면_404_Not_Found_상태코드를_반환한다(
        @Autowired TestRestTemplate client
    ) {
        // Arrange
        URI location = URI.create("/api/products/" + Long.MAX_VALUE);

        // Act
        ResponseEntity<Product> response = client.getForEntity(
            location,
            Product.class
        );

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }
}
