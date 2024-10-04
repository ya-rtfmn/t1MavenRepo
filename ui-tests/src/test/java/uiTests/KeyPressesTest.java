package uiTests;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class KeyPressesTest {

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/key_presses");
    }

    @ParameterizedTest
    @ValueSource(strings = {"ENTER", "CONTROL", "ALT", "TAB"})
    public void testKeyPresses(String key) {
        $("body").sendKeys(Keys.valueOf(key));

        $("#result").shouldHave(text("You entered: " + key));
    }

    @ParameterizedTest
    @ValueSource(chars = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'})
    public void testLetterKeys(char keyChar) {
        $("#target").sendKeys(String.valueOf(keyChar));

        $("#result").shouldHave(text("You entered: " + keyChar));
    }
}