package utils;

import data.ValidLoginDataProvider;
import io.restassured.response.Response;

public class TokenManager {

    public static String getToken() {
        ValidLoginDataProvider validLoginDataProvider = new ValidLoginDataProvider();
        RequestHelper requestHelper = new RequestHelper();
        Response response = requestHelper.postLoginRequest(validLoginDataProvider);

        return response.jsonPath().getString("access_token");
    }
}
