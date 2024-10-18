package uiTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class InputsTest {

    static {
        Configuration.timeout = 1000;
    }

    @BeforeEach
    public void openPage() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(false).savePageSource(false));
        open("https://the-internet.herokuapp.com/inputs");
    }

    @Test
    public void testInputs() {
        int randomValue = (int) (Math.random() * 10000 + 1);
        SelenideElement input = $("input[type='number']");

        input.clear();
        input.sendKeys(String.valueOf(randomValue));

        System.out.println("Random value: " + randomValue);
        System.out.println("Input value: " + input.getValue());
    }

    @TestFactory
    public Stream<DynamicTest> dynamicTestInputs() {
        return Stream.of(
                "1234", "5678", "91011", "500", "0", "9999"
        ).map(value -> DynamicTest.dynamicTest("Значение: " + value, () -> {
            SelenideElement input = $("input[type='number']");
            input.clear();
            input.sendKeys(value);

            input.shouldHave(Condition.value(value));
        }));
    }

    @TestFactory
    public Stream<DynamicTest> dynamicTestNegativeInputs() {
        return Stream.of(
                " 42", "7 ", "abc", "!@#$"
        ).map(value -> DynamicTest.dynamicTest("Значение: " + value, () -> {
            SelenideElement input = $("input[type='number']");
            input.clear();
            input.sendKeys(value);

            input.shouldNotHave(Condition.exactValue(value));
        }));
    }
}
