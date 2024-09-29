package uiTests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class HoversTest {

    @Test
    public void testHovers() {
        open("https://the-internet.herokuapp.com/hovers");

        $$("div.figure").forEach(figure -> {
            figure.hover();
            String caption = figure.$("div.figcaption").getText();
            System.out.println("Caption: " + caption);
        });
    }
}
