package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class InputsPage {
    public SelenideElement inputField = $("input[type='number']");

    public void enterValue(String value) {
        inputField.clear();
        inputField.sendKeys(value);
    }
}