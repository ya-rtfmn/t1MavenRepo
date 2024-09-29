package uiTests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.ElementsCollection;

public class AddRemoveElementsTest {

    @Test
    public void testAddRemoveElements() {
        open("https://the-internet.herokuapp.com/add_remove_elements/");

        for (int i = 0; i < 5; i++) {
            $("button[onclick='addElement()']").click();

            ElementsCollection addedElements = $$("button.added-manually");
            System.out.println("Added element " + (i + 1) + ": " + addedElements.get(i).getText());
        }

        for (int i = 0; i < 3; i++) {
            $$("button.added-manually").get(0).click();

            ElementsCollection remainingElements = $$("button.added-manually");
            System.out.println("Remaining elements count: " + remainingElements.size());
            for (SelenideElement element : remainingElements) {
                System.out.println("Remaining element text: " + element.getText());
            }
        }
    }
}
