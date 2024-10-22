package uiTests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.CheckboxPage;
import asserts.CheckboxAssert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class CheckboxTest {

    CheckboxPage checkboxPage = new CheckboxPage();
    CheckboxAssert checkboxPageAssert = new CheckboxAssert();

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void testCheckboxes() {
        toggleCheckbox(checkboxPage.checkbox1, true);
        toggleCheckbox(checkboxPage.checkbox2, false);

        System.out.println("Checkbox 1 checked attribute: " + checkboxPage.checkbox1.getAttribute("checked"));
        System.out.println("Checkbox 2 checked attribute: " + checkboxPage.checkbox2.getAttribute("checked"));

        checkboxPageAssert.assertCheckboxSelected(checkboxPage.checkbox1, true);
        checkboxPageAssert.assertCheckboxSelected(checkboxPage.checkbox2, false);
    }

    @ParameterizedTest
    @CsvSource({
            "1-2, Checkbox 1, then checkbox 2",
            "2-1, Checkbox 2, then checkbox 1"
    })
    public void testCheckboxes(String order, String description) {
        performAction(order, description);
    }

    @Step("{description}")
    private void performAction(String order, String description) {
        if (order.equals("1-2")) {
            toggleCheckbox(checkboxPage.checkbox1, true);
            toggleCheckbox(checkboxPage.checkbox2, false);
        } else if (order.equals("2-1")) {
            toggleCheckbox(checkboxPage.checkbox2, false);
            toggleCheckbox(checkboxPage.checkbox1, true);
        }
    }

    @Step("Изменение состояния чекбокса: {0}")
    private void toggleCheckbox(SelenideElement checkbox, boolean shouldBeSelected) {
        checkboxPage.toggleCheckbox(checkbox, shouldBeSelected);
        checkboxPageAssert.assertCheckboxSelected(checkbox, shouldBeSelected);
    }
}
