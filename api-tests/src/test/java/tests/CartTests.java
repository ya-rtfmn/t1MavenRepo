package tests;

import config.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.TokenManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests extends BaseTest {

    @Test
    public void testAddProductToCart() {
        String token = TokenManager.getToken();
        Response response = addProductToCart(1, 1, token);

        assertEquals(201, response.getStatusCode());
    }

    @Test
    public void testAddNonExistingProductToCart() {
        String token = TokenManager.getToken();
        Response response = addNonExistingProductToCart(9999, 1, token);

        assertEquals(404, response.getStatusCode());
        assertEquals("Product not found", response.jsonPath().getString("message"));
    }

    @Test
    public void testRemoveProductFromCart() {
        String token = TokenManager.getToken();
        Response response = removeProductFromCart(1, token);

        assertEquals(200, response.getStatusCode());
    }
}
