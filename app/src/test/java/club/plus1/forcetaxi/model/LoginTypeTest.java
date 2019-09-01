package club.plus1.forcetaxi.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTypeTest {

    @Test
    public void name() {
        LoginType loginType = LoginType.email;
        assertEquals("email", loginType.name());
    }

    @Test
    public void toString_test() {
        LoginType loginType = LoginType.phone;
        assertEquals("Мобильный телефон", loginType.toString());
    }
}