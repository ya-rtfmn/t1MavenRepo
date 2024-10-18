package uiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.TestFactory;

import java.util.List;

public class AddRemoveElementsTest {

    static {
        Configuration.timeout = 1000;
    }

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/add_remove_elements/");
    }

    @Test
    public void testAddRemoveElements() {
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

    @TestFactory
    public List<DynamicTest> testAddRemoveElementsFactory() {
        return List.of(
                addRemoveTest("2:1", 2, 1),
                addRemoveTest("5:2", 5, 2),
                addRemoveTest("1:3", 1, 3)
        );
    }

    private DynamicTest addRemoveTest(String name, int additions, int removals) {
        return DynamicTest.dynamicTest(name, () -> {
            refresh();

            for (int i = 0; i < additions; i++) {
                $("button[onclick='addElement()']").click();
            }
            ElementsCollection addedElements = $$("button.added-manually");

            addedElements.shouldHave(size(additions));

            for (int i = 0; i < removals; i++) {
                addedElements.get(0).click();
                addedElements = $$("button.added-manually");

                addedElements.shouldHave(size(additions - (i + 1)));
            }
        });
    }
}
