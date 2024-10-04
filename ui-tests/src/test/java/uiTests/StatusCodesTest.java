package uiTests;

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
    public void testStatusCodes() {
        String[] codes = {"200", "301", "404", "500"};

        for (String code : codes) {
            $("a[href='status_codes/" + code + "']").click();

            String bodyText = $("body").getText();
            System.out.println("Status " + code + ": " + bodyText);
            System.out.println("=================================");
            back();
        }
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
