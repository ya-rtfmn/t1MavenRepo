import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class InputsTest {

    @Test
    public void testInputs() {
        open("https://the-internet.herokuapp.com/inputs");

        int randomValue = (int) (Math.random() * 10000 + 1);
        SelenideElement input = $("input[type='number']");

        input.setValue(String.valueOf(randomValue));
        System.out.println("Random value: " + randomValue);
        System.out.println("Input value: " + input.getValue());
    }
}
