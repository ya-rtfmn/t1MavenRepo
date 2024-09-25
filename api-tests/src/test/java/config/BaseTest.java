package config;

import data.CartOperations;
import data.ProductOperations;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest implements ProductOperations, CartOperations {

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
    public Response getProducts() {
        return getRequestSpec().get("/products");
    }

    @Override
    public Response getProductById(int productId) {
        return getRequestSpec().get("/products/" + productId);
    }

    @Override
    public Response getNonExistingProduct(int productId) {
        return getRequestSpec().get("/products/" + productId);
    }

    @Override
    public Response addProductToCart(int productId, int quantity, String token) {
        String payload = "{ \"product_id\": " + productId + ", \"quantity\": " + quantity + " }";
        return getRequestSpec()
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .post("/cart");
    }

    @Override
    public Response addNonExistingProductToCart(int productId, int quantity, String token) {
        String payload = "{ \"product_id\": " + productId + ", \"quantity\": " + quantity + " }";
        return getRequestSpec()
                .header("Authorization", "Bearer " + token)
                .body(payload)
                .post("/cart");
    }

    @Override
    public Response removeProductFromCart(int productId, String token) {
        return getRequestSpec()
                .header("Authorization", "Bearer " + token)
                .delete("/cart/" + productId);
    }
}
