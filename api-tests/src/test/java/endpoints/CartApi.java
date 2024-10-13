package endpoints;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import schema.CartRequest;

public class CartApi {

    private String token;

    public CartApi(String token) {
        this.token = token;
    }

    @Step("Get client cart")
    public Response getClientCart() {
        return RestApiBuilder.builder()
                .addAuthToken(token)
                .build()
                .get(Urls.CART);
    }

    @Step("Add product to cart: {cartRequest.productId}, quantity: {cartRequest.quantity}")
    public Response addProductToCart(CartRequest cartRequest) {
        return RestApiBuilder.builder()
                .addAuthToken(token)
                .build()
                .body(cartRequest)
                .post(Urls.CART);
    }

    @Step("Remove product from cart: {productId}")
    public Response removeProductFromCart(int productId) {
        String endpoint = String.format(Urls.CART_PRODUCT, productId);
        return RestApiBuilder.builder()
                .addAuthToken(token)
                .build()
                .delete(endpoint);
    }
}

