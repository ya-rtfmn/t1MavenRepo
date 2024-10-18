package apiTests;

import endpoints.CartApi;
import config.EnvConfig;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import schema.CartRequest;
import schema.LoginRequest;
import utils.AssertHelper;
import utils.TokenManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTests extends BaseTest {

    private static final int EXISTING_PRODUCT_ID = 1;
    private static final int NON_EXISTING_PRODUCT_ID = 9999;
    private static final int QUANTITY_ONE = 1;

    private String token;
    private CartApi cartApi;

    @BeforeEach
    public void setUp() {
        LoginRequest loginRequest = new LoginRequest(EnvConfig.cfg.getValidUsername(), EnvConfig.cfg.getValidPassword());
        token = TokenManager.getToken(loginRequest);
        cartApi = new CartApi(token);
    }

    @Test
    @Description("Test get client cart")
    public void testGetClientCart() {
        Response response = cartApi.getClientCart();

        AssertHelper.assertStatusCode(response, 200);
    }

    @Test
    @Description("Test add product to cart")
    public void testAddProductToCart() {
        CartRequest cartRequest = new CartRequest(EXISTING_PRODUCT_ID, QUANTITY_ONE);
        Response response = cartApi.addProductToCart(cartRequest);

        AssertHelper.assertStatusCode(response, 201);
    }

    @Test
    @Description("Test add non-existing product to cart")
    public void testAddNonExistingProductToCart() {
        CartRequest cartRequest = new CartRequest(NON_EXISTING_PRODUCT_ID, QUANTITY_ONE);
        Response response = cartApi.addProductToCart(cartRequest);

        AssertHelper.assertStatusCode(response, 404);
        String message = response.jsonPath().getString("message");
        assertEquals("Product not found", message);
    }

    @Test
    @Description("Test add product to cart without authorization")
    public void testAddProductToCartWithoutAuthorization() {
        CartApi unauthenticatedCartApi = new CartApi("");
        CartRequest cartRequest = new CartRequest(EXISTING_PRODUCT_ID, QUANTITY_ONE);
        Response response = unauthenticatedCartApi.addProductToCart(cartRequest);

        AssertHelper.assertStatusCode(response, 422); // Adjust if needed
    }

    @Test
    @Description("Test remove product from cart")
    public void testRemoveProductFromCart() {
        Response response = cartApi.removeProductFromCart(EXISTING_PRODUCT_ID);

        AssertHelper.assertStatusCode(response, 200);
    }

    @Test
    @Description("Test remove non-existing product from cart")
    public void testRemoveNonExistingProductFromCart() {
        Response response = cartApi.removeProductFromCart(NON_EXISTING_PRODUCT_ID);

        AssertHelper.assertStatusCode(response, 404);
        String message = response.jsonPath().getString("message");
        assertEquals("Product not found in cart", message);
    }
}
