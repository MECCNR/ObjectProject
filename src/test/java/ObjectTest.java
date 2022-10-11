import data.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.data.DashboardPage;
import page.data.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.firstCard;
import static data.DataHelper.secondCard;

public class ObjectTest {

    @BeforeEach
    void before() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    // Этот тест очень сложно устроен! Поэтому объясняю---
    // У вас есть две карты. Первая (firstCard()), и вторая (data.secondCard())

    @Test
    void transferTestFirstCardForBabies() {
        DashboardPage page = new DashboardPage();
        int originalMoney = page.getCardBalance(firstCard()); // начальное количество денег. В скобках добавьте карту для которой вы хотите пополнить счёт
        int originalMoney2 = page.getCardBalance(secondCard()); // начальное количество денег другой карты. В скобках добавьте карту с которой вы хотите забрать деньги
        int howMuchMoneyDoYouWantToAdd = 150; // здесь напишите сколько вы хотите добавить денег
        int expected = originalMoney + howMuchMoneyDoYouWantToAdd;
        int expected2 = originalMoney2 - howMuchMoneyDoYouWantToAdd;
        page.transferMoney(firstCard(), secondCard(), howMuchMoneyDoYouWantToAdd); // ОЧЕНЬ ВАЖНО! В скобах впишите картe d которой вы хотите
        // пополнить счёт. После запятой запишите карту с которой вы хотите забрать деньги. В третьей ничего не меняйте.
        int actual = page.getCardBalance(firstCard()); // в скобках этого метода должна быть карта в которой был пополнен счёт
        int actual2 = page.getCardBalance(secondCard()); // в скобках этого метода должна быть карта с которой были взяты деньги
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }

    @Test
    void transferTestSecondCardForNoobs() {
        DashboardPage page2 = new DashboardPage();
        int originalMoney = page2.getCardBalance(secondCard());
        int originalMoney2 = page2.getCardBalance(firstCard());
        int howMuchMoneyDoYouWantToAdd = 3000;
        int expected = originalMoney + howMuchMoneyDoYouWantToAdd;
        int expected2 = originalMoney2 - howMuchMoneyDoYouWantToAdd;
        page2.transferMoney(secondCard(), firstCard(), howMuchMoneyDoYouWantToAdd);
        int actual = page2.getCardBalance(secondCard());
        int actual2 = page2.getCardBalance(firstCard());
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(expected2, actual2);
    }
}
