package apiTests;

import config.BaseTest;
import config.EnvConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import schema.LoginRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTests extends BaseTest {

    private static final String VALID_USERNAME = EnvConfig.getValidUsername();
    private static final String VALID_PASSWORD = EnvConfig.getValidPassword();
    private static final String INVALID_USERNAME = EnvConfig.getInvalidUsername();
    private static final String INVALID_PASSWORD = EnvConfig.getInvalidPassword();

    @Test
    public void testValidLogin() {
        LoginRequest loginRequest = new LoginRequest(VALID_USERNAME, VALID_PASSWORD);
        Response response = login(loginRequest);
        String token = response.jsonPath().getString("access_token");

        assertEquals(200, response.getStatusCode());
        assertNotNull(token);
    }

    @Test
    public void testInvalidLogin() {
        LoginRequest loginRequest = new LoginRequest(INVALID_USERNAME, INVALID_PASSWORD);
        Response response = login(loginRequest);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    public void testLoginWithWrongPassword() {
        LoginRequest loginRequest = new LoginRequest(VALID_USERNAME, INVALID_PASSWORD);
        Response response = login(loginRequest);

        assertEquals(401, response.getStatusCode());
    }
}
