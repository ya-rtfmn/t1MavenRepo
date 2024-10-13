package pages;

import com.codeborne.selenide.ElementsCollection;
import static com.codeborne.selenide.Selenide.$$;

public class DisappearingElementsPage {

    public ElementsCollection elementsList = $$("ul li");

    public int getElementsCount() {
        return elementsList.size();
    }

    public void refreshPage() {
        com.codeborne.selenide.Selenide.refresh();
    }
}