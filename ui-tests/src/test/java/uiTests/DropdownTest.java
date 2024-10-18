package uiTests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.DropdownPage;
import asserts.DropdownAssert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class DropdownTest {
    static {
        Configuration.timeout = 1000;
    }

    DropdownPage dropdownPage = new DropdownPage();
    DropdownAssert dropdownAssert = new DropdownAssert();

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void testDropdown() {
        dropdownPage.selectOptionByValue("1");
        dropdownAssert.assertSelectedOptionText(dropdownPage.getSelectedOption(), "Option 1");

        dropdownPage.selectOptionByValue("2");
        dropdownAssert.assertSelectedOptionText(dropdownPage.getSelectedOption(), "Option 2");
    }

    @ParameterizedTest
    @CsvSource({"1, Option 1", "2, Option 2"})
    public void testDropdownWithParams(String value, String expectedText) {
        dropdownPage.selectOptionByValue(value);
        dropdownAssert.assertSelectedOptionText(dropdownPage.getSelectedOption(), expectedText);
    }
}