package uiTests;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class HoversTest {

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/hovers");
    }

    @Test
    public void testHovers() {
        $$("div.figure").forEach(figure -> {
            figure.hover();
            String caption = figure.$("div.figcaption").getText();
            System.out.println("Caption: " + caption);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "name: user1, 0",
            "name: user2, 1",
            "name: user3, 2"
    })
    public void testHoversCaption(String expectedCaption, int index) {
        $("div.figure:nth-of-type(" + (index + 1) + ")").hover();

        $("div.figure:nth-of-type(" + (index + 1) + ") div.figcaption")
                .shouldHave(text(expectedCaption))
                .shouldHave(text("View profile"));
    }
}

