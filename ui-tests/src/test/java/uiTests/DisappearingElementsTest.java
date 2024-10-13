package uiTests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pages.DisappearingElementsPage;
import asserts.DisappearingElementsAssert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class DisappearingElementsTest {
    static {
        Configuration.timeout = 1000;
    }

    DisappearingElementsPage disappearingElementsPage = new DisappearingElementsPage();
    DisappearingElementsAssert disappearingElementsAssert = new DisappearingElementsAssert();

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/disappearing_elements");
    }

    @Test
    public void testDisappearingElements() {
        int attempts = 0;
        while (attempts < 10) {
            attempts++;
            disappearingElementsPage.refreshPage();
            if (disappearingElementsPage.getElementsCount() == 5) {
                break;
            }
        }
        disappearingElementsAssert.assertFiveElementsPresent();
    }

    @RepeatedTest(10)
    public void testRepeatedDisappearingElements() {
        disappearingElementsPage.refreshPage();
        disappearingElementsAssert.assertFiveElementsPresent();
    }
}
