package club.plus1.forcetaxi.model;

import org.junit.Test;

import club.plus1.forcetaxi.stub.ResponseBalanceStub;

import static org.junit.Assert.assertNull;

public class ResponseBalanceTest {

    @Test
    public void ResponseBalance() {
        ResponseBalance balance = new ResponseBalanceStub("");
        assertNull(balance.error);
    }

}