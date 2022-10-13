package page.data;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {
    private ElementsCollection cards = $$(".list__item div");

    private SelenideElement heading = $("[data-test-id=amount] input");

    public TransferPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage transferMoney(DataHelper.CardInfo card2, int amount) {
        String secondNumber = card2.getCardNumber();
        $("[data-test-id=amount] input").setValue(String.valueOf(amount));
        $("[data-test-id=from] input").setValue(secondNumber);
        $("[data-test-id=action-transfer]").click();
        return new DashboardPage();
    }
}
