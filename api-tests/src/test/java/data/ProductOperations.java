package data;

import io.restassured.response.Response;

public interface ProductOperations {
    Response getProducts();
    Response getProductById(int productId);
    Response getNonExistingProduct(int productId);
}
