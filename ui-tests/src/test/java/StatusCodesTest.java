import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class StatusCodesTest {

    @Test
    public void testStatusCodes() {
        open("https://the-internet.herokuapp.com/status_codes");

        String[] codes = {"200", "301", "404", "500"};

        for (String code : codes) {
            $("a[href='status_codes/" + code + "']").click();

            String bodyText = $("body").getText();
            System.out.println("Status " + code + ": " + bodyText);
            System.out.println("=================================");
            back();
        }
    }
}
