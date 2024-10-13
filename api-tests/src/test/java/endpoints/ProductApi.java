package endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class ProductApi {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Step("Get all products")
    public static Response getProducts() {
        return RestApiBuilder.builder()
                .build()
                .get(Urls.PRODUCTS);
    }

    @Step("Get product by ID: {productId}")
    public static Response getProductById(int productId) {
        String endpoint = String.format(Urls.PRODUCT_BY_ID, productId);
        return RestApiBuilder.builder()
                .build()
                .get(endpoint);
    }
}
