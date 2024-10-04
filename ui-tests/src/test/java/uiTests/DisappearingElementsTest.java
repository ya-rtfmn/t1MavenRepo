package uiTests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class DisappearingElementsTest {

    static {
        Configuration.timeout = 1000;
    }

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/disappearing_elements");
    }

    @Test
    public void testDisappearingElements() {
        int attempts = 0;
        boolean foundFiveElements = false;

        while (attempts < 10) {
            attempts++;
            int elementCount = $$("ul li").size();
            if (elementCount == 5) {
                foundFiveElements = true;
                break;
            } else {
                refresh();
            }
        }

        if (!foundFiveElements) {
            throw new AssertionError("5 elements not found after 10 attempts.");
        }
    }

    @RepeatedTest(10)
    public void testDisappearingElementsRepeated() {
        refresh();

        $$("ul li").shouldHave(size(5));
    }
}