package club.plus1.forcetaxi.model;

import org.junit.Test;

import club.plus1.forcetaxi.stub.ResponseAuthStub;

import static org.junit.Assert.assertNull;

public class ResponseAuthTest {

    @Test
    public void name() {
        ResponseAuth auth = new ResponseAuthStub("");
        assertNull(auth.error);
    }

}