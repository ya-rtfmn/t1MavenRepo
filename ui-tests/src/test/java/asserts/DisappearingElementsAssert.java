package asserts;

import io.qameta.allure.Step;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

public class DisappearingElementsAssert {

    @Step("Assert that 5 elements are present")
    public void assertFiveElementsPresent() {
        $$("ul li").shouldHave(size(5));
    }
}