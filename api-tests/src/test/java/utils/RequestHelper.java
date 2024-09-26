package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.BaseTest;
import io.restassured.response.Response;
import schema.LoginRequest;

public class RequestHelper extends BaseTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Response postLoginRequest(LoginRequest loginRequest) {
        try {
            String payload = objectMapper.writeValueAsString(loginRequest);
            return getRequestSpec()
                    .body(payload)
                    .post("/login");
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize login request", e);
        }
    }
}
