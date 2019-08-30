package club.plus1.forcetaxi.model;

import org.junit.Test;

import club.plus1.forcetaxi.stub.ResponseReceiptStub;

import static org.junit.Assert.assertNull;

public class ResponseReceiptTest {

    @Test
    public void name() {
        ResponseReceipt receipt = new ResponseReceiptStub("");
        assertNull(receipt.error);
    }

}