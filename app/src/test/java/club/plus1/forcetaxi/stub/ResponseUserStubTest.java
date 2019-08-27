package club.plus1.forcetaxi.stub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import club.plus1.forcetaxi.model.ServerUser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ResponseUserStubTest {

    private static final String APP_TOKEN = "5aa27b1100fa7d9e369f5bc726b05b69";
    private static final String USER_TOKEN = "047069db-df4b-48ce-ba48-50641d9cc490";
    private ResponseUserStub user;

    @Before
    public void createObject() {
        user = new ResponseUserStub(APP_TOKEN);
    }

    @Test
    public void getResponseUserStub() {
        assertFalse(user.ok);
        assertEquals("unknown_error", user.error.getId());
    }

    @Test
    public void getUser_ok() {
        ServerUser serverUser = user.getUser();
        assertEquals(USER_TOKEN, serverUser.userToken);
        assertFalse(user.ok);
        assertEquals("unknown_error", user.error.getId());
    }

    @Test
    public void checkConnectedTin_empty_tin() {
        assertFalse(user.checkConnectedTin(""));
        assertFalse(user.ok);
        assertEquals("empty_tin", user.error.getId());
    }

    @Test
    public void checkConnectedTin_wrong_tin() {
        assertFalse(user.checkConnectedTin("12345"));
        assertFalse(user.ok);
        assertEquals("wrong_tin", user.error.getId());
    }

    @Test
    public void checkConnectedTin_ok() {
        assertTrue(user.checkConnectedTin("1234567890"));
        assertTrue(user.ok);
        assertEquals("", user.error.getId());
    }

    @Test
    public void validateTin_empty_tin() {
        assertNull(user.validateTin(""));
        assertFalse(user.ok);
        assertEquals("empty_tin", user.error.getId());
    }

    @Test
    public void validateTin_wrong_tin() {
        assertNull(user.validateTin("12345"));
        assertFalse(user.ok);
        assertEquals("wrong_tin", user.error.getId());
    }

    @Test
    public void validateTin_unregistered_tin() {
        assertNull(user.validateTin("9999999999"));
        assertFalse(user.ok);
        assertEquals("unregistered_tin", user.error.getId());
    }

    @Test
    public void validateTin_ok() {
        assertEquals("0123456789", user.validateTin("0123456789").tin);
        assertTrue(user.ok);
        assertEquals("", user.error.getId());
    }

    @Test
    public void searchTin_wrong_passport_series() {
        assertEquals("", user.searchTin("Иванов", "Иван", "Иванович",
                "2000-01-01", "12", "123456"));
        assertFalse(user.ok);
        assertEquals("wrong_passport_series", user.error.getId());
    }

    @Test
    public void searchTin_wrong_passport_number() {
        assertEquals("", user.searchTin("Иванов", "Иван", "Иванович",
                "2000-01-01", "1234", "d6"));
        assertFalse(user.ok);
        assertEquals("wrong_passport_number", user.error.getId());
    }

    @Test
    public void searchTin_wrong_birthday() {
        assertEquals("", user.searchTin("Иванов", "Иван", "Иванович",
                "12345", "1234", "123456"));
        assertFalse(user.ok);
        assertEquals("wrong_birthday", user.error.getId());
    }

    @Test
    public void searchTin_ok() {
        assertEquals("0123456789", user.searchTin("Иванов", "Иван",
                "Иванович", "2000-01-01", "1234", "123456"));
        assertTrue(user.ok);
        assertEquals("", user.error.getId());
    }

    @Test
    public void connectTin_empty_tin() {
        assertNull(user.connectTin(""));
        assertFalse(user.ok);
        assertEquals("empty_tin", user.error.getId());
    }

    @Test
    public void connectTin_wrong_tin() {
        assertNull(user.connectTin("123"));
        assertFalse(user.ok);
        assertEquals("wrong_tin", user.error.getId());
    }

    @Test
    public void connectTin_unregistered_tin() {
        assertNull(user.connectTin("9999999999"));
        assertFalse(user.ok);
        assertEquals("unregistered_tin", user.error.getId());
    }

    @Test
    public void connectTin_tin_already_connected() {
        assertNull(user.connectTin("012345678912"));
        assertFalse(user.ok);
        assertEquals("tin_already_connected", user.error.getId());
    }

    @Test
    public void connectTin_ok() {
        assertEquals("0123456789", user.connectTin("0123456789").fts.tin);
        assertTrue(user.ok);
        assertEquals("", user.error.getId());
    }

    @After
    public void destroyObject() {
        user = null;
    }
}