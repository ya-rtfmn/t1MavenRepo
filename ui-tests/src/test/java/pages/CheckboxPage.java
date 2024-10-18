package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$;

public class CheckboxPage {

    public SelenideElement checkbox1 = $$("input[type='checkbox']").get(0);
    public SelenideElement checkbox2 = $$("input[type='checkbox']").get(1);

    public void toggleCheckbox(SelenideElement checkbox, boolean shouldBeSelected) {
        if (shouldBeSelected && !checkbox.isSelected()) {
            checkbox.click();
        } else if (!shouldBeSelected && checkbox.isSelected()) {
            checkbox.click();
        }
    }
}
