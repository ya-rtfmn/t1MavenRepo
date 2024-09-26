package data;

import io.restassured.response.Response;
import schema.CartRequest;

public interface CartOperations {
    Response getClientCart(String token);
    Response addProductToCart(CartRequest cartRequest, String token);
    Response removeProductFromCart(int productId, String token);
}
