package uiTests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class DisappearingElementsTest {

    @Test
    public void testDisappearingElements() {
        open("https://the-internet.herokuapp.com/disappearing_elements");

        int attempts = 0;
        boolean foundFiveElements = false;

        while (attempts < 10) {
            attempts++;
            int elementCount = $$("ul li").size();
            if (elementCount == 5) {
                foundFiveElements = true;
                break;
            } else {
                refresh();
            }
        }

        if (!foundFiveElements) {
            throw new AssertionError("5 elements not found after 10 attempts.");
        }
    }
}