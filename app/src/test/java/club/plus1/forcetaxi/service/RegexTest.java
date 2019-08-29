package club.plus1.forcetaxi.service;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RegexTest {

    @Test
    public void phone_wrong() {
        assertFalse("test@test.com".matches(Regex.phone()));
        assertFalse("123".matches(Regex.phone()));
    }

    @Test
    public void phone_ok() {
        assertTrue("+7987654321".matches(Regex.phone()));
        assertTrue("8987654321".matches(Regex.phone()));
    }

    @Test
    public void tin_wrong() {
        assertFalse("123".matches(Regex.tin()));
        assertFalse("123456789t".matches(Regex.tin()));
    }

    @Test
    public void tin_ok() {
        assertTrue("1234567890".matches(Regex.tin()));
        assertTrue("123456789012".matches(Regex.tin()));
    }

    @Test
    public void date_wrong() {
        assertFalse("2000-02-30".matches(Regex.date()));
        assertFalse("2000-13-30".matches(Regex.date()));
        assertFalse("3000-01-30".matches(Regex.date()));
        assertFalse("30-12-1999".matches(Regex.date()));
    }

    @Test
    public void date_ok() {
        assertTrue("2000-02-28".matches(Regex.date()));
        assertTrue("1999-12-30".matches(Regex.date()));
    }

    @Test
    public void passportSeries_wrong() {
        assertFalse("123".matches(Regex.passportSeries()));
    }

    @Test
    public void passportSeries_ok() {
        assertTrue("1234".matches(Regex.passportSeries()));
    }

    @Test
    public void passportNumber_wrong() {
        assertFalse("123".matches(Regex.passportNumber()));
    }

    @Test
    public void passportNumber_ok() {
        assertTrue("123456".matches(Regex.passportNumber()));
    }
}