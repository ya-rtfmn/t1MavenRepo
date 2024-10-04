package uiTests;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class InfiniteScrollTest {

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/infinite_scroll");
    }

    @Test
    public void testInfiniteScrollWithActions() {
        boolean textFound = false;

        while (!textFound) {
            actions().sendKeys(Keys.ARROW_DOWN).perform();
            sleep(1);

            String bodyText = $("body").getText();
            textFound = bodyText.contains("Eius");
        }

        String bodyText = $("body").getText();
        assert bodyText.contains("Eius");
    }
}