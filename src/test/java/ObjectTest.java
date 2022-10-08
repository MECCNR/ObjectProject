import org.junit.jupiter.api.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ObjectTest {

    LoginData data = new LoginData();

    @BeforeEach
    void before() {
        data.enterAccount();
    }

    // Этот тест очень сложно устроен! Поэтому объясняю---
    // У вас есть две карты. Первая (data.firstCard), и вторая (data.secondCard)
    // У этих карт есть номера (!ВАЖНО). Первый номер для первой карты (data.firstNumber)
    // и второй для второй (data.secondNumber)

    @Test
    void TransferTestFirstCard() {
        int originalMoney = data.getCardBalance(data.secondCard); // начальное количество денег. В скобках добавьте карту для которой вы хотите пополнить счёт
        int howMuchMoneyDoYouWantToAdd = 150; // здесь напишите сколько вы хотите добавить денег
        int expected = originalMoney + howMuchMoneyDoYouWantToAdd;
        data.transferMoney(data.secondCard, data.firstNumber, howMuchMoneyDoYouWantToAdd); // ОЧЕНЬ ВАЖНО! В скобах впишите сначала данные карты которой вы хотите
        // пополнить счёт. После запятой запишите данные НОМЕРА с карты которой вы хотите забрать деньги. В третьей ничего не меняйте.
        int actual = data.getCardBalance(data.secondCard); // в скобках этого метода должна быть карта в которой был пополнен счёт
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void TransferTestSecondCard() {
        int originalMoney = data.getCardBalance(data.firstCard); // начальное количество денег. В скобках добавьте карту для которой вы хотите пополнить счёт
        int howMuchMoneyDoYouWantToAdd = 150; // здесь напишите сколько вы хотите добавить денег
        int expected = originalMoney + howMuchMoneyDoYouWantToAdd;
        data.transferMoney(data.firstCard, data.secondNumber, howMuchMoneyDoYouWantToAdd); // ОЧЕНЬ ВАЖНО! В скобах впишите сначала данные карты которой вы хотите
        // пополнить счёт. После запятой запишите данные НОМЕРА с карты которой вы хотите забрать деньги. В третьей ничего не меняйте.
        int actual = data.getCardBalance(data.firstCard); // в скобках этого метода должна быть карта в которой был пополнен счёт
        Assertions.assertEquals(expected, actual);
    }
}
