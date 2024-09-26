package config;

import data.CartOperations;
import data.LoginOperations;
import data.ProductOperations;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import schema.CartRequest;
import schema.LoginRequest;
import utils.RequestHelper;

public class BaseTest implements LoginOperations, ProductOperations, CartOperations {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://9b142cdd34e.vps.myjino.ru:49268";
    }

    protected RequestSpecification getRequestSpec() {
        return RestAssured.given()
                .contentType("application/json")
                .accept("application/json");
    }

    @Override
    public Response login(LoginRequest loginRequest) {
        RequestHelper requestHelper = new RequestHelper();

        return requestHelper.postLoginRequest(loginRequest);
    }

    @Override
    public Response getProducts() {
        return getRequestSpec().get("/products");
    }

    @Override
    public Response getProductById(int productId) {
        return getRequestSpec().get("/products/" + productId);
    }

    @Override
    public Response getClientCart(String token) {
        return getRequestSpec()
                .header("Authorization", "Bearer " + token)
                .get("/cart");
    }

    @Override
    public Response addProductToCart(CartRequest cartRequest, String token) {
        return getRequestSpec()
                .header("Authorization", "Bearer " + token)
                .body(cartRequest)
                .post("/cart");
    }

    @Override
    public Response removeProductFromCart(int productId, String token) {
        return getRequestSpec()
                .header("Authorization", "Bearer " + token)
                .delete("/cart/" + productId);
    }
}
