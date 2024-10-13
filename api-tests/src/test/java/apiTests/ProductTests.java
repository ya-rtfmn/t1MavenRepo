package apiTests;

import endpoints.ProductApi;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import utils.AssertHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTests extends BaseTest {

    private static final int EXISTING_PRODUCT_ID = 1;
    private static final int NON_EXISTING_PRODUCT_ID = 9999;

    @Test
    @Description("Test get all products")
    public void testGetProducts() {
        Response response = ProductApi.getProducts();

        AssertHelper.assertStatusCode(response, 200);
    }

    @Test
    @Description("Test get product by ID")
    public void testGetProductById() {
        Response response = ProductApi.getProductById(EXISTING_PRODUCT_ID);

        AssertHelper.assertStatusCode(response, 200);
    }

    @Test
    @Description("Test get non-existing product")
    public void testGetNonExistingProduct() {
        Response response = ProductApi.getProductById(NON_EXISTING_PRODUCT_ID);

        AssertHelper.assertStatusCode(response, 404);
        String message = response.jsonPath().getString("message");
        assertEquals("Product not found", message);
    }
}
