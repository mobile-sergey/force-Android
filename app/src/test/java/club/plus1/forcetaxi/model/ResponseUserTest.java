package club.plus1.forcetaxi.model;

import org.junit.Test;

import club.plus1.forcetaxi.stub.ResponseUserStub;

import static org.junit.Assert.assertNull;

public class ResponseUserTest {

    @Test
    public void name() {
        ResponseUser user = new ResponseUserStub("");
        assertNull(user.error);
    }


}