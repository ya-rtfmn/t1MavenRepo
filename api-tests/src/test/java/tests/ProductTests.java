package tests;

import config.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTests extends BaseTest {

    @Test
    public void testGetProducts() {
        Response response = getProducts();

        assertEquals(200, response.getStatusCode());
        assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test
    public void testGetProductById() {
        int productId = 1;
        Response response = getProductById(productId);

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void testGetNonExistingProduct() {
        int nonExistingProductId = 9999;
        Response response = getNonExistingProduct(nonExistingProductId);

        assertEquals(404, response.getStatusCode());
        assertEquals("Product not found", response.jsonPath().getString("message"));
    }
}
