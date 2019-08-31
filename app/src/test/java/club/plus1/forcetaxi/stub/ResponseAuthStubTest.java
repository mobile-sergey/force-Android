package club.plus1.forcetaxi.stub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ResponseAuthStubTest {

    private static final String APP_TOKEN = "5aa27b1100fa7d9e369f5bc726b05b69";
    private static final String USER_TOKEN = "047069db-df4b-48ce-ba48-50641d9cc490";
    private ResponseAuthStub auth;

    @Before
    public void createObject() {
        auth = new ResponseAuthStub(APP_TOKEN);
    }

    @Test
    public void createResponseAuthStub() {
        assertFalse(auth.ok);
        assertEquals("unknown_error", auth.error.getId());
    }

    @Test
    public void login_empty_phonenumber() {
        assertNull(auth.login("", "123"));
        assertFalse(auth.ok);
        assertEquals("empty_phonenumber", auth.error.getId());
    }

    @Test
    public void login_wrong_phonenumber() {
        assertNull(auth.login("test@test.com", "123"));
        assertFalse(auth.ok);
        assertEquals("wrong_phonenumber", auth.error.getId());
    }

    @Test
    public void login_phonenumber_is_not_registered() {
        assertNull(auth.login("5555555555", "123"));
        assertFalse(auth.ok);
        assertEquals("phonenumber_is_not_registered", auth.error.getId());
    }

    @Test
    public void login_user_not_found() {
        assertNull(auth.login("9999999999", "123"));
        assertFalse(auth.ok);
        assertEquals("user_not_found", auth.error.getId());
    }

    @Test
    public void login_wrong_password() {
        assertNull(auth.login("1234567890", "12345"));
        assertFalse(auth.ok);
        assertEquals("wrong_password", auth.error.getId());
    }

    @Test
    public void login_ok() {
        assertEquals(USER_TOKEN, auth.login("1234567890", "123").userToken);
        assertTrue(auth.ok);
        assertEquals("", auth.error.getId());
    }

    @Test
    public void passwordRequestReset_ok() {
        auth.passwordRequestReset("0123456789");
        assertTrue(auth.ok);
        assertEquals("", auth.error.getId());
    }

    @Test
    public void passwordReset_ok() {
        auth.passwordReset("0123456789", "123", "0000");
        assertTrue(auth.ok);
        assertEquals("", auth.error.getId());
    }

    @Test
    public void logout_ok() {
        auth.server.user.userToken = USER_TOKEN;
        auth.logout();
        assertEquals("", auth.userToken);
        assertTrue(auth.ok);
        assertEquals("", auth.error.getId());
    }

    @After
    public void destroyObject() {
        auth = null;
    }
}