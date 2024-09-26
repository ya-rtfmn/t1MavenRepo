package tests;

import config.BaseTest;
import config.EnvConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import schema.CartRequest;
import schema.LoginRequest;
import utils.TokenManager;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests extends BaseTest {

    private static final String VALID_USERNAME = EnvConfig.getValidUsername();
    private static final String VALID_PASSWORD = EnvConfig.getValidPassword();
    public static final int EXISTING_PRODUCT_ID = 1;
    public static final int NON_EXISTING_PRODUCT_ID = 9999;
    public static final int QUANTITY_ONE = 1;
    private String token;

    @BeforeEach
    public void setUp() {
        final LoginRequest loginRequest = new LoginRequest(VALID_USERNAME, VALID_PASSWORD);
        token = TokenManager.getToken(loginRequest);
    }

    @Test
    public void testGetClientCart() {
        Response response = getClientCart(token);

        assertEquals(200, response.getStatusCode());
        response.then().body(matchesJsonSchemaInClasspath("schemas/client-cart-schema.json"));
    }


    @Test
    @Order(1)
    public void testAddProductToCart() {
        CartRequest cartRequest = new CartRequest(EXISTING_PRODUCT_ID, QUANTITY_ONE);

        Response response = addProductToCart(cartRequest, token);

        assertEquals(201, response.getStatusCode());
        response.then().body(matchesJsonSchemaInClasspath("schemas/add-remove-product-message-schema.json"));
    }

    @Test
    public void testAddNonExistingProductToCart() {
        CartRequest cartRequest = new CartRequest(NON_EXISTING_PRODUCT_ID, QUANTITY_ONE);

        Response response = addProductToCart(cartRequest, token);

        assertEquals(404, response.getStatusCode());
        assertEquals("Product not found", response.jsonPath().getString("message"));
    }

    @Test
    public void testAddProductToCartWithoutAuthorization() {
        token = "";
        CartRequest cartRequest = new CartRequest(EXISTING_PRODUCT_ID, QUANTITY_ONE);

        Response response = addProductToCart(cartRequest, token);

        assertEquals(422, response.getStatusCode());
    }

    @Test
    @Order(2)
    public void testRemoveProductFromCart() {
        Response response = removeProductFromCart(EXISTING_PRODUCT_ID, token);

        assertEquals(200, response.getStatusCode());
        response.then().body(matchesJsonSchemaInClasspath("schemas/add-remove-product-message-schema.json"));
    }

    @Test
    public void testRemoveNonExistingProductFromCart() {
        Response response = removeProductFromCart(NON_EXISTING_PRODUCT_ID, token);

        assertEquals(404, response.getStatusCode());
        assertEquals("Product not found in cart", response.jsonPath().getString("message"));
    }
}
