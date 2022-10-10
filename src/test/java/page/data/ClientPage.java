package page.data;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Selenide.$$;

public class ClientPage {

    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";

    private static ElementsCollection cards = $$(".list__item div");

    public static int getCardBalance(DataHelper.CardInfo cardInfo) {
        String id = cardInfo.getCardId();
        val text = cards.findBy(Condition.attributeMatching("data-test-id", id)).text();
        return amountofMoney(text);
    }

    private static int amountofMoney(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
