package endpoints;

import config.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RestApiBuilder {

    private RequestSpecification requestSpecification;

    private RestApiBuilder() {
        requestSpecification = RestAssured.given()
                .contentType("application/json")
                .accept("application/json");

//      По неведомой мне причине не завелось через builder, он почему-то ничего не возвращает
//        requestSpecification = new RequestSpecBuilder()
//                .setBaseUri(EnvConfig.cfg.baseUrl())
//                .setContentType("application/json")
//                .setAccept("application/json")
//                .build();
    }

    public static RestApiBuilder builder() {
        return new RestApiBuilder();
    }

    public RestApiBuilder addAuthToken(String token) {
        requestSpecification.header("Authorization", "Bearer " + token);
        return this;
    }

    public RequestSpecification build() {
        return requestSpecification;
    }
}

