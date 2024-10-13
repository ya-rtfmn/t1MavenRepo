package asserts;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.value;

public class InputsAssert {

    @Step("Verify input value")
    public void assertInputValue(SelenideElement input, String expectedValue) {
        input.shouldHave(value(expectedValue));
    }
}