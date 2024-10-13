package asserts;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.attribute;

public class CheckboxAssert {

    public void assertCheckboxSelected(SelenideElement checkbox, boolean shouldBeSelected) {
        if (shouldBeSelected) {
            checkbox.shouldHave(attribute("checked"));
        } else {
            checkbox.shouldNotHave(attribute("checked"));
        }
    }
}
