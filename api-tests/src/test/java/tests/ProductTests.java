package tests;

import config.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTests extends BaseTest {

    public static final int EXISTING_PRODUCT_ID = 1;
    public static final int NON_EXISTING_PRODUCT_ID = 9999;

    @Test
    public void testGetProducts() {
        Response response = getProducts();

        assertEquals(200, response.getStatusCode());
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/products-schema.json"));
    }

    @Test
    public void testGetProductById() {
        Response response = getProductById(EXISTING_PRODUCT_ID);

        assertEquals(200, response.getStatusCode());
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/products-schema.json"));
    }

    @Test
    public void testGetNonExistingProduct() {
        Response response = getProductById(NON_EXISTING_PRODUCT_ID);

        assertEquals(404, response.getStatusCode());
        assertEquals("Product not found", response.jsonPath().getString("message"));
    }
}
