package page.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReplenishmentPage {

    private static ElementsCollection cards = $$(".list__item div");

    public static void transferMoney(DataHelper.CardInfo card, DataHelper.CardInfo card2, int amount) {
        String firstCard = card.getCardId();
        String secondNumber = card2.getCardNumber();
        cards.findBy(Condition.attributeMatching("data-test-id", firstCard)).find("button").click();
        $("[data-test-id=amount] input").setValue(String.valueOf(amount));
        $("[data-test-id=from] input").setValue(secondNumber);
        $("[data-test-id=action-transfer]").click();
    }
}
