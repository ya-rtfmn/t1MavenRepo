package apiTests;

import endpoints.AuthApi;
import config.EnvConfig;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import schema.LoginRequest;
import utils.AssertHelper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTests extends BaseTest {

    private static final String VALID_USERNAME = EnvConfig.cfg.getValidUsername();
    private static final String VALID_PASSWORD = EnvConfig.cfg.getValidPassword();
    private static final String INVALID_USERNAME = EnvConfig.cfg.getInvalidUsername();
    private static final String INVALID_PASSWORD = EnvConfig.cfg.getInvalidPassword();

    @Test
    @Description("Test valid login")
    public void testValidLogin() {
        LoginRequest loginRequest = new LoginRequest(VALID_USERNAME, VALID_PASSWORD);
        Response response = AuthApi.login(loginRequest);

        AssertHelper.assertStatusCode(response, 200);
        String token = response.jsonPath().getString("access_token");
        assertNotNull(token);
    }

    @Test
    @Description("Test invalid login")
    public void testInvalidLogin() {
        LoginRequest loginRequest = new LoginRequest(INVALID_USERNAME, INVALID_PASSWORD);
        Response response = AuthApi.login(loginRequest);

        AssertHelper.assertStatusCode(response, 401);
    }

    @Test
    @Description("Test login with wrong password")
    public void testLoginWithWrongPassword() {
        LoginRequest loginRequest = new LoginRequest(VALID_USERNAME, INVALID_PASSWORD);
        Response response = AuthApi.login(loginRequest);

        AssertHelper.assertStatusCode(response, 401);
    }
}
