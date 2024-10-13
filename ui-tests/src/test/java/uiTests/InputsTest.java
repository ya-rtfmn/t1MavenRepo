package uiTests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import pages.InputsPage;
import asserts.InputsAssert;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class InputsTest {
    static {
        Configuration.timeout = 1000;
    }

    InputsPage inputsPage = new InputsPage();
    InputsAssert inputsAssert = new InputsAssert();

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/inputs");
    }

    @Test
    public void testInputField() {
        String value = "123";
        inputsPage.enterValue(value);
        inputsAssert.assertInputValue(inputsPage.inputField, value);
    }

    @TestFactory
    public Stream<org.junit.jupiter.api.DynamicTest> dynamicInputTests() {
        return Stream.of("100", "-100", "0", "9999").map(value ->
                org.junit.jupiter.api.DynamicTest.dynamicTest("Test input value: " + value, () -> {
                    inputsPage.enterValue(value);
                    inputsAssert.assertInputValue(inputsPage.inputField, value);
                }));
    }
}
