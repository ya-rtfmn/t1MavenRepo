package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class DropdownPage {
    public SelenideElement dropdown = $("#dropdown");

    public void selectOptionByValue(String value) {
        dropdown.selectOptionByValue(value);
    }

    public SelenideElement getSelectedOption() {
        return dropdown.getSelectedOption();
    }
}