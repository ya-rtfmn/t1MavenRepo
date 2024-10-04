package uiTests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class DropdownTest {

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void testDropdown() {
        SelenideElement dropdown = $("#dropdown");

        dropdown.selectOptionByValue("1");
        String selectedText1 = dropdown.getSelectedOption().getText();
        System.out.println("Selected: " + selectedText1);

        dropdown.selectOptionByValue("2");
        String selectedText2 = dropdown.getSelectedOption().getText();
        System.out.println("Selected: " + selectedText2);
    }

    @ParameterizedTest
    @CsvSource({
            "1, Option 1",
            "2, Option 2"
    })
    public void testDropdownState(String value, String expectedText) {
        SelenideElement dropdown = $("#dropdown");
        dropdown.selectOptionByValue(value);
        dropdown.getSelectedOption().shouldHave(text(expectedText));
    }
}
