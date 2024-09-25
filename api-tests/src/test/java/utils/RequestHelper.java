package utils;

import data.LoginDataProvider;
import io.restassured.response.Response;
import config.BaseTest;

public class RequestHelper extends BaseTest{

    public Response postLoginRequest(LoginDataProvider dataProvider) {
        String payload = "{ \"username\": \"" + dataProvider.getUsername() + "\", \"password\": \"" + dataProvider.getPassword() + "\" }";
        return getRequestSpec()
                .body(payload)
                .post("/login");
    }
}