package utils;

import io.restassured.response.Response;
import schema.LoginRequest;

public class TokenManager {

    public static String getToken(LoginRequest loginRequest) {
        RequestHelper requestHelper = new RequestHelper();
        Response response = requestHelper.postLoginRequest(loginRequest);

        return response.jsonPath().getString("access_token");
    }
}
