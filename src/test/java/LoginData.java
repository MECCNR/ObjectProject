import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import lombok.val;

import static com.codeborne.selenide.Selenide.*;

public class LoginData {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    public String firstCard = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
    public String secondCard = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
    public String firstNumber = "5559000000000001";
    public String secondNumber = "5559000000000002";
    private ElementsCollection cards = $$(".list__item div");

    public LoginData() {
    }

    public void enterAccount() {
        open("http://localhost:9999");
        $("[data-test-id=login] input").setValue("vasya");
        $("[data-test-id=password] input").setValue("qwerty123");
        $("[data-test-id=action-login]").click();
        $("[data-test-id=code] input").setValue("12345");
        $("[data-test-id=action-verify]").click();
    }

    public int getCardBalance(String id) {
        val text = cards.findBy(Condition.attributeMatching("data-test-id", id)).text();
        return amountofMoney(text);
    }

    private int amountofMoney(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public void transferMoney(String firstId, String secondNumber, int amount) {
        cards.findBy(Condition.attributeMatching("data-test-id", firstId)).find("button").click();
        $("[data-test-id=amount] input").setValue(String.valueOf(amount));
        $("[data-test-id=from] input").setValue(secondNumber);
        $("[data-test-id=action-transfer]").click();
    }
}
