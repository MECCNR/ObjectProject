package page.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item div");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        String id = cardInfo.getCardId();
        val text = cards.findBy(Condition.attributeMatching("data-test-id", id)).text();
        return amountofMoney(text);
    }

    private int amountofMoney(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void transferMoney(DataHelper.CardInfo card, DataHelper.CardInfo card2, int amount) {
        String firstCard = card.getCardId();
        String secondNumber = card2.getCardNumber();
        cards.findBy(Condition.attributeMatching("data-test-id", firstCard)).find("button").click();
        $("[data-test-id=amount] input").setValue(String.valueOf(amount));
        $("[data-test-id=from] input").setValue(secondNumber);
        $("[data-test-id=action-transfer]").click();
    }
}