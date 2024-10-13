package apiTests;

import config.EnvConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = EnvConfig.cfg.baseUrl();
    }
}
