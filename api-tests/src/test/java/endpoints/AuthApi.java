package endpoints;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import schema.LoginRequest;

public class AuthApi {

    @Step("Login with username: {loginRequest.username}")
    public static Response login(LoginRequest loginRequest) {
        return RestApiBuilder.builder()
                .build()
                .body(loginRequest)
                .post(Urls.LOGIN);
    }
}
