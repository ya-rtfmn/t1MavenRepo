import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmptyApiTest {

    @Step("Step API 1")
    @Description("This API test just passes")
    @Test
    public void testAlwaysPasses() {
        int statusCode = RestAssured.get("https://httpbin.org/status/200").statusCode();
        assertEquals(200, statusCode);
    }
}
