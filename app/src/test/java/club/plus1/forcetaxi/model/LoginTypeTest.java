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
    public void toString1() {
        LoginType loginType = LoginType.phone;
        assertEquals("phone", loginType.toString());
    }
}