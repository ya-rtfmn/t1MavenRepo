package uiTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class NotificationMessageTest {

    static {
        Configuration.timeout = 1000;
    }

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/notification_message_rendered");
    }

    @Test
    public void testNotificationMessage() {
        SelenideElement clickHere = $(".example > p:nth-child(2) > a:nth-child(5)");
        SelenideElement notification = $("#flash");
        SelenideElement closeNotification = $(".close");

        while (true) {
            clickHere.click();

            if (notification.getText().contains("Action successful")) {
                System.out.println(notification.getText());
                break;
            } else {
                closeNotification.click();
            }
        }
    }

    @RepeatedTest(5)
    public void testNotificationMessageRepeated() {
        SelenideElement clickHere = $(".example > p:nth-child(2) > a:nth-child(5)");
        SelenideElement notification = $("#flash");
        SelenideElement closeNotification = $(".close");

        clickHere.click();

        notification.shouldHave(text("Action successful"));
        closeNotification.click();
    }
}
