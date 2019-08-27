package club.plus1.forcetaxi.stub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResponseAppsStubTest {

    private ResponseAppsStub app;

    @Before
    public void createObject() {
        app = new ResponseAppsStub();
    }

    @Test
    public void createResponseAppsStub() {
        assertFalse(app.ok);
        assertEquals("unknown_error", app.error.getId());
    }

    @Test
    public void registrationInstallation_wrong_app_installation_token() {
        assertEquals("", app.registrationInstallation("4", "wrong", ""));
        assertFalse(app.ok);
        assertEquals("wrong_app_installation_token", app.error.getId());
    }

    @Test
    public void registrationInstallation_empty_app_id() {
        assertEquals("", app.registrationInstallation("", "7180d024-260e-4d33-bc30-574c0680caf2", ""));
        assertFalse(app.ok);
        assertEquals("empty_app_id", app.error.getId());
    }

    @Test
    public void registrationInstallation_wrong_app_id() {
        assertEquals("", app.registrationInstallation("wrong", "7180d024-260e-4d33-bc30-574c0680caf2", ""));
        assertFalse(app.ok);
        assertEquals("wrong_app_id", app.error.getId());
    }

    @Test
    public void registrationInstallation_ok() {
        assertEquals("5aa27b1100fa7d9e369f5bc726b05b69", app.registrationInstallation("4", "7180d024-260e-4d33-bc30-574c0680caf2", ""));
        assertTrue(app.ok);
        assertEquals("", app.error.getId());
    }

    @After
    public void destroyObject() {
        app = null;
    }
}