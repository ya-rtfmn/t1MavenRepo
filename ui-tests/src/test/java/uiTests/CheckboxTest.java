package uiTests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class CheckboxTest {

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void testCheckboxes() {
        SelenideElement checkbox1 = $$("input[type='checkbox']").get(0);
        SelenideElement checkbox2 = $$("input[type='checkbox']").get(1);

        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }

        if (checkbox2.isSelected()) {
            checkbox2.click();
        }

        System.out.println("Checkbox 1 checked attribute: " + checkbox1.getAttribute("checked"));
        System.out.println("Checkbox 2 checked attribute: " + checkbox2.getAttribute("checked"));
    }

    @ParameterizedTest
    @CsvSource({
            "1-2, Checkbox 1, then checkbox 2",
            "2-1, Checkbox 2, then checkbox 1"
    })
    public void testCheckboxes(String order, String description) {
        SelenideElement checkbox1 = $$("input[type='checkbox']").get(0);
        SelenideElement checkbox2 = $$("input[type='checkbox']").get(1);

        performAction(order, checkbox1, checkbox2, description);
    }

    @Step("{description}")
    private void performAction(String order, SelenideElement checkbox1, SelenideElement checkbox2, String description) {
        if (order.equals("1-2")) {
            toggleCheckbox(checkbox1, true);
            toggleCheckbox(checkbox2, false);
        } else if (order.equals("2-1")) {
            toggleCheckbox(checkbox2, false);
            toggleCheckbox(checkbox1, true);
        }
    }

    @Step("Изменение состояния чекбокса: {0}")
    private void toggleCheckbox(SelenideElement checkbox, boolean shouldBeSelected) {
        if (shouldBeSelected && checkbox.getAttribute("checked") == null) {
            checkbox.click();
        } else if (!shouldBeSelected && checkbox.getAttribute("checked") != null) {
            checkbox.click();
        }

        if (shouldBeSelected) {
            checkbox.shouldHave(attribute("checked"));
        } else {
            checkbox.shouldNotHave(attribute("checked"));
        }    }
}