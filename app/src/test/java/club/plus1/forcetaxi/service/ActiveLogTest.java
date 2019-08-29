package club.plus1.forcetaxi.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import club.plus1.forcetaxi.model.ServerError;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ActiveLogTest {

    private ActiveLog log;

    @Before
    public void createObject() {
        log = ActiveLog.getInstance();
    }

    @Test
    public void getInstance() {
        assertTrue(ActiveLog.isActive());
    }

    @Test
    public void log() {
        assertTrue(ActiveLog.isActive());
        log.log();
        assertFalse(log.isError);
    }

    @Test
    public void logError_error() {
        assertTrue(ActiveLog.isActive());
        log.logError(true, new ServerError("unknown_error"));
        assertTrue(log.isError);
        assertEquals("unknown_error", log.message);
    }

    @Test
    public void logError_ok() {
        assertTrue(ActiveLog.isActive());
        log.logError(false, new ServerError("unknown_error"));
        assertFalse(log.isError);
        assertEquals("unknown_error", log.message);
    }

    @After
    public void destroyObject() {
        log = null;
    }
}