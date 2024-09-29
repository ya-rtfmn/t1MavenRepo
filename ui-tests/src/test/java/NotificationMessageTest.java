import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class NotificationMessageTest {

    @Test
    public void testNotificationMessage() {
        open("https://the-internet.herokuapp.com/notification_message_rendered");

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
}
