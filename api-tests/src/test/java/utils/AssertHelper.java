package utils;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class AssertHelper {

    public static void assertStatusCode(Response response, int expectedStatusCode) {
        Assertions.assertEquals(expectedStatusCode, response.getStatusCode(), "Status code is not as expected");
    }
}
