package tests;

import config.BaseTest;
import data.InvalidLoginDataProvider;
import data.ValidLoginDataProvider;
import data.WrongPasswordLoginDataProvider;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.RequestHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTests extends BaseTest {

    private RequestHelper requestHelper;

    @BeforeEach
    public void setUp() {
        requestHelper = new RequestHelper();
    }

    @Test
    public void testValidLogin() {
        ValidLoginDataProvider validLoginData = new ValidLoginDataProvider();
        Response response = requestHelper.postLoginRequest(validLoginData);
        String token = response.jsonPath().getString("access_token");

        assertEquals(200, response.getStatusCode());
        assertNotNull(token);
    }

    @Test
    public void testInvalidLogin() {
        InvalidLoginDataProvider invalidLoginData = new InvalidLoginDataProvider();
        Response response = requestHelper.postLoginRequest(invalidLoginData);

        assertEquals(401, response.getStatusCode());
    }

    @Test
    public void testLoginWithWrongPassword() {
        WrongPasswordLoginDataProvider wrongPasswordData = new WrongPasswordLoginDataProvider();
        Response response = requestHelper.postLoginRequest(wrongPasswordData);

        assertEquals(401, response.getStatusCode());
    }
}
