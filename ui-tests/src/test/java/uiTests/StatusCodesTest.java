package uiTests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class StatusCodesTest {

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/status_codes");
    }

    @Test
    public void testStatusCode200() {
        checkStatusCode("200");
    }

    @Test
    public void testStatusCode301() {
        checkStatusCode("301");
    }

    @Test
    public void testStatusCode404() {
        checkStatusCode("404");
    }

    @Test
    public void testStatusCode500() {
        checkStatusCode("500");
    }

    private void checkStatusCode(String code) {
        $("a[href='status_codes/" + code + "']").click();
        SelenideElement body = $("body");

        String bodyText = body.getText();
        System.out.println("Status " + code + ": " + bodyText);
        System.out.println("=================================");

        back();
    }

    @ParameterizedTest
    @ValueSource(strings = {"200", "301", "404", "500"})
    public void testStatusCodes(String code) {
        $("a[href='status_codes/" + code + "']").click();
        String expectedText = "This page returned a " + code + " status code.";

        $("body").shouldHave(text(expectedText));
        back();
    }
}
