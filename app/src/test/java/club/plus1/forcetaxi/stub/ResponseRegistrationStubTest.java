package club.plus1.forcetaxi.stub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import club.plus1.forcetaxi.model.ServerError;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ResponseRegistrationStubTest {

    private static final String APP_TOKEN = "5aa27b1100fa7d9e369f5bc726b05b69";
    private ResponseRegistrationStub reg;

    @Before
    public void createObject() {
        reg = new ResponseRegistrationStub(APP_TOKEN);
    }

    @Test
    public void ResponseRegistrationStub() {
        assertFalse(reg.ok);
        assertEquals("unknown_error", reg.error.getId());
    }

    @Test
    public void getErrorText() {
        reg.error = new ServerError("unknown_error", "test");
        assertEquals("test", reg.getErrorText());
    }

    @Test
    public void validatePhoneNumber_empty_phonenumber() {
        assertNull(reg.validatePhoneNumber(""));
        assertFalse(reg.ok);
        assertEquals("empty_phonenumber", reg.error.getId());
    }

    @Test
    public void validatePhoneNumber_wrong_phonenumber() {
        assertNull(reg.validatePhoneNumber("test@test.com"));
        assertFalse(reg.ok);
        assertEquals("wrong_phonenumber", reg.error.getId());
    }

    @Test
    public void validatePhoneNumber_phonenumber_already_exists() {
        assertNull(reg.validatePhoneNumber("1234567890"));
        assertFalse(reg.ok);
        assertEquals("phonenumber_already_exists", reg.error.getId());
    }

    @Test
    public void validatePhoneNumber_wrong_ok() {
        assertEquals("+79150042300", reg.validatePhoneNumber("+79150042300").phoneNumber);
        assertTrue(reg.ok);
        assertEquals("", reg.error.getId());
    }

    @Test
    public void requestSmsCode_empty_phonenumber() {
        assertEquals("", reg.requestSmsCode(""));
        assertFalse(reg.ok);
        assertEquals("empty_phonenumber", reg.error.getId());
    }

    @Test
    public void requestSmsCode_wrong_phonenumber() {
        assertEquals("", reg.requestSmsCode("test@test.com"));
        assertFalse(reg.ok);
        assertEquals("wrong_phonenumber", reg.error.getId());
    }

    @Test
    public void requestSmsCode_ok() {
        String smsCode = reg.requestSmsCode("+79150042300");
        assertEquals(smsCode, reg.getSmsCode());
        assertTrue(reg.ok);
        assertEquals("", reg.error.getId());
    }

    @Test
    public void validateSmsCode_empty_phonenumber() {
        assertNull(reg.validateSmsCode("", reg.getSmsCode()));
        assertFalse(reg.ok);
        assertEquals("empty_phonenumber", reg.error.getId());
    }

    @Test
    public void validateSmsCode_wrong_phonenumber() {
        assertNull(reg.validateSmsCode("test@test.com", reg.getSmsCode()));
        assertFalse(reg.ok);
        assertEquals("wrong_phonenumber", reg.error.getId());
    }

    @Test
    public void validateSmsCode_phonenumber_is_not_registered() {
        assertNull(reg.validateSmsCode("+79999999999", reg.getSmsCode()));
        assertFalse(reg.ok);
        assertEquals("phonenumber_is_not_registered", reg.error.getId());
    }

    @Test
    public void validateSmsCode_smscode_is_empty() {
        assertNull(reg.validateSmsCode("0987654321", ""));
        assertFalse(reg.ok);
        assertEquals("smscode_is_empty", reg.error.getId());
    }

    @Test
    public void validateSmsCode_smscode_is_outdated() {
        Date date = new Date((new Date().getTime() - 3 * 24 * 60 * 60));
        reg.setSmsCodeDate(date);
        reg.setSmsCode();
        assertNull(reg.validateSmsCode("0987654321", reg.getSmsCode()));
        assertFalse(reg.ok);
        assertEquals("smscode_is_outdated", reg.error.getId());
    }

    @Test
    public void validateSmsCode_wrong_smscode() {
        assertNull(reg.validateSmsCode("0987654321", "99999"));
        assertFalse(reg.ok);
        assertEquals("wrong_smscode", reg.error.getId());
    }

    @Test
    public void validateSmsCode_ok() {
        Date date = new Date();
        reg.setSmsCodeDate(date);
        reg.setSmsCode();
        assertEquals("0987654321", reg.validateSmsCode("0987654321", reg.server.smsCode).phoneNumber);
        assertTrue(reg.ok);
        assertEquals("", reg.error.getId());
    }

    @Test
    public void createUser_empty_phonenumber() {
        assertNull(reg.createUser("", "test@test.com",
                "Иванов", "Иван", "Алексеевич", "123"));
        assertFalse(reg.ok);
        assertEquals("empty_phonenumber", reg.error.getId());
    }

    @Test
    public void createUser_empty_password() {
        assertNull(reg.createUser("0987654321", "test@test.com",
                "Иванов", "Алексей", "Алексеевич", ""));
        assertFalse(reg.ok);
        assertEquals("empty_password", reg.error.getId());
    }

    @Test
    public void createUser_wrong_phonenumber() {
        assertNull(reg.createUser("test@test.com", "test@test.com",
                "Иванов", "Алексей", "Алексеевич", "123"));
        assertFalse(reg.ok);
        assertEquals("wrong_phonenumber", reg.error.getId());
    }

    @Test
    public void createUser_phonenumber_is_not_validated() {
        assertNull(reg.createUser("+79999999999", "test@test.com",
                "Иванов", "Алексей", "Алексеевич", "123"));
        assertFalse(reg.ok);
        assertEquals("phonenumber_is_not_validated", reg.error.getId());
    }

    @Test
    public void createUser_user_already_exist() {
        assertNull(reg.createUser("0987654321", "test@test.com",
                "Алексеев", "Алексей", "Алексеевич", "123"));
        assertFalse(reg.ok);
        assertEquals("user_already_exist", reg.error.getId());
    }

    @Test
    public void createUser_ok() {
        assertNotEquals(null, reg.createUser("0987654321", "test@test.com",
                "Иванов", "Иван", "Иванович", "123"));
        assertTrue(reg.ok);
        assertEquals("", reg.error.getId());
    }

    @Test
    public void getSmsCodeDate() {
        Date date = new Date();
        reg.setSmsCodeDate(date);
        assertEquals(date, reg.getSmsCodeDate());
    }

    @After
    public void destroyObject() {
        reg = null;
    }
}