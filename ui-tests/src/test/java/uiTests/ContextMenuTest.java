package uiTests;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContextMenuTest {

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/context_menu");
    }

    @Test
    public void rightClickContextMenu() {
        $("#hot-spot").contextClick();

        String alertText = switchTo().alert().getText();
        assertEquals("You selected a context menu", alertText);
        switchTo().alert().accept();
    }
}
