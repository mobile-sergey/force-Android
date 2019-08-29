package club.plus1.forcetaxi.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class GeneratorTest {

    @Test
    public void smsCode_format() {
        for (int i = 0; i < 100; i++) {
            assertEquals(4, Generator.smsCode().length());
            assertTrue(Integer.parseInt(Generator.smsCode()) >= 0);
            assertTrue(Integer.parseInt(Generator.smsCode()) <= 9999);
        }
    }

    @Test
    public void smsCode_equals() {
        String code1 = Generator.smsCode();
        String code2 = Generator.smsCode();
        assertNotEquals(code1, code2);
    }
}