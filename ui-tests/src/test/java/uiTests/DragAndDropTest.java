package uiTests;

import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class DragAndDropTest {

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/drag_and_drop");
    }

    @Test
    public void dragAndDropElements() {
        SelenideElement elementA = $("#column-a");
        SelenideElement elementB = $("#column-b");

        elementA.dragAndDrop(DragAndDropOptions.to(elementB));

        elementA.shouldHave(text("B"));
        elementB.shouldHave(text("A"));
    }

    @Test
    public void dragAndDropElementsWithoutDragAndDrop() {
        actions().clickAndHold($("#column-a"))
                .moveToElement($("#column-b"))
                .release()
                .perform();

        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}