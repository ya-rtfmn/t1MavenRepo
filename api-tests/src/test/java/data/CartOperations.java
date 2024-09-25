package data;

import io.restassured.response.Response;

public interface CartOperations {
    Response addProductToCart(int productId, int quantity, String token);
    Response addNonExistingProductToCart(int productId, int quantity, String token);
    Response removeProductFromCart(int productId, String token);
}