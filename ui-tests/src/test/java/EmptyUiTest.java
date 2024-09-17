import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmptyUiTest {

    @Step("Step UI 1")
    @Description("This UI test just passes")
    @Test
    public void testAlwaysPasses() {
        assertTrue(true);
    }
}
