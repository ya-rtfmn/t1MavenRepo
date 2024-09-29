import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class CheckboxTest {

    @Test
    public void testCheckboxes() {
        open("https://the-internet.herokuapp.com/checkboxes");

        SelenideElement checkbox1 = $$("input[type='checkbox']").get(0);
        SelenideElement checkbox2 = $$("input[type='checkbox']").get(1);

        if (!checkbox1.isSelected()) {
            checkbox1.click();
        }

        if (checkbox2.isSelected()) {
            checkbox2.click();
        }

        System.out.println("Checkbox 1 checked: " + checkbox1.isSelected());
        System.out.println("Checkbox 2 checked: " + checkbox2.isSelected());
    }
}