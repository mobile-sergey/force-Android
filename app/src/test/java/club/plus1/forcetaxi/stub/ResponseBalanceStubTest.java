package club.plus1.forcetaxi.stub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import club.plus1.forcetaxi.model.ServerError;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResponseBalanceStubTest {

    private static final String APP_TOKEN = "5aa27b1100fa7d9e369f5bc726b05b69";
    private ResponseBalanceStub balance;

    @Before
    public void createObject() {
        balance = new ResponseBalanceStub(APP_TOKEN);
    }

    @Test
    public void ResponseBalanceStub() {
        assertFalse(balance.ok);
        assertEquals("unknown_error", balance.error.getId());
    }

    @Test
    public void getErrorText() {
        balance.error = new ServerError("unknown_error", "test");
        assertEquals("test", balance.getErrorText());
    }

    @Test
    public void balance_ok() {
        balance.balance();
        assertTrue(balance.ok);
        assertEquals("", balance.error.getId());
    }

    @After
    public void destroyObject() {
        balance = null;
    }
}