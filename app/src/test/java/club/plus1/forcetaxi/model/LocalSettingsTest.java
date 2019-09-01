package club.plus1.forcetaxi.model;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import club.plus1.forcetaxi.MockSharedPreference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class LocalSettingsTest {

    // Названия хранимых настроек
    private static final String PREF_PIN = "pin";
    private static final String PREF_IN_FNS = "inFns";
    private static final String PREF_FORCE_ACCEPTED = "forceAccepted";
    @Mock
    Context mockContext;
    private MockSharedPreference mockPrefs;
    private MockSharedPreference.Editor mockPrefsEditor;
    private LocalSettings settings;

    @Before
    public void setUp() {
        mockPrefs = new MockSharedPreference();
        mockPrefsEditor = mockPrefs.edit();
        settings = new LocalSettings();
        mockPrefsEditor.putString(PREF_PIN, "1234");
        mockPrefsEditor.putBoolean(PREF_IN_FNS, true);
        mockPrefsEditor.putBoolean(PREF_FORCE_ACCEPTED, true);
    }

    @Test
    public void getPin() {
        String pin = "";
        mockPrefs.getString(PREF_PIN, pin);
        assertEquals(pin, settings.getPin());
    }

    @Test
    public void setPin() {
        String pin = "";
        mockPrefs.getString(PREF_PIN, pin);
        settings.setPin("123");
        assertEquals("123", settings.getPin());
        settings.setPin(pin);
    }

    @Test
    public void isPinSet() {
        String pin = settings.getPin();
        settings.setPin("");
        assertFalse(settings.isPinSet());
        settings.setPin("123");
        assertTrue(settings.isPinSet());
        settings.setPin(pin);
    }

    @Test
    public void isInFns() {
        boolean inFns = false;
        mockPrefs.getBoolean(PREF_IN_FNS, inFns);
        assertEquals(inFns, settings.isInFns());
    }

    @Test
    public void setInFns() {
        boolean inFns = false;
        mockPrefs.getBoolean(PREF_IN_FNS, inFns);
        settings.setInFns(true);
        assertTrue(settings.isInFns());
        settings.setInFns(false);
        assertFalse(settings.isInFns());
        settings.setInFns(inFns);
    }

    @Test
    public void isForceAccepted() {
        boolean forceAccepted = false;
        mockPrefs.getBoolean(PREF_FORCE_ACCEPTED, forceAccepted);
        assertEquals(forceAccepted, settings.isForceAccepted());
    }

    @Test
    public void setForceAccepted() {
        boolean forceAccepted = false;
        mockPrefs.getBoolean(PREF_FORCE_ACCEPTED, forceAccepted);
        settings.setForceAccepted(true);
        assertTrue(settings.isForceAccepted());
        settings.setForceAccepted(false);
        assertFalse(settings.isForceAccepted());
        settings.setForceAccepted(forceAccepted);
    }

    @After
    public void tearDown() {
        mockPrefs = null;
        settings = null;
        mockPrefsEditor = null;
    }
}