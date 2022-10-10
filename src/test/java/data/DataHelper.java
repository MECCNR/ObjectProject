package data;

import lombok.Value;

public class DataHelper {
    public DataHelper() {
    }

    public static CardInfo firstCard() {
        return new CardInfo("92df3f1c-a033-48e6-8390-206f6b1f56c0", "5559000000000001");
    }

    public static CardInfo secondCard() {
        return new CardInfo("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "5559000000000002");
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private String cardId;
        private String cardNumber;
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }
}
