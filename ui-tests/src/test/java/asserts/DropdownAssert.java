package asserts;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;

public class DropdownAssert {

    @Step("Verify selected option text")
    public void assertSelectedOptionText(SelenideElement selectedOption, String expectedText) {
        selectedOption.shouldHave(text(expectedText));
    }
}