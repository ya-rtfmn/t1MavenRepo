import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class DropdownTest {

    @Test
    public void testDropdown() {
        open("https://the-internet.herokuapp.com/dropdown");

        SelenideElement dropdown = $("#dropdown");

        dropdown.selectOptionByValue("1");
        String selectedText1 = dropdown.getSelectedOption().getText();
        System.out.println("Selected: " + selectedText1);

        dropdown.selectOptionByValue("2");
        String selectedText2 = dropdown.getSelectedOption().getText();
        System.out.println("Selected: " + selectedText2);
    }
}
