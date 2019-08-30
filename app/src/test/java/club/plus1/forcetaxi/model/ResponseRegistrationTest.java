package club.plus1.forcetaxi.model;

import org.junit.Test;

import club.plus1.forcetaxi.stub.ResponseRegistrationStub;

import static org.junit.Assert.assertNull;

public class ResponseRegistrationTest {

    @Test
    public void name() {
        ResponseRegistration registration = new ResponseRegistrationStub("");
        assertNull(registration.error);
    }

}