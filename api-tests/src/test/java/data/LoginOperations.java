package data;

import io.restassured.response.Response;
import schema.LoginRequest;

public interface LoginOperations {
    Response login(LoginRequest loginRequest);
}
