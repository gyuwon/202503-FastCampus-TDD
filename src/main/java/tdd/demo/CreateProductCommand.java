package tdd.demo;

public record CreateProductCommand(
    String name,
    int priceAmount,
    int stockQuantity
) {
}
