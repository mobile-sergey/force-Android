package club.plus1.forcetaxi.stub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class ResponseReceiptStubTest {

    private static final String APP_TOKEN = "5aa27b1100fa7d9e369f5bc726b05b69";
    private static final String USER_TOKEN = "047069db-df4b-48ce-ba48-50641d9cc490";
    private ResponseReceiptStub receipt;

    @Before
    public void createObject() {
        receipt = new ResponseReceiptStub(APP_TOKEN);
    }

    @Test
    public void getResponseReceiptStub() {
        assertFalse(receipt.ok);
        assertEquals("unknown_error", receipt.error.getId());
    }

    @Test
    public void receipts_empty_client_phonenumber() {
        assertNull(receipt.receipts("", 500));
        assertFalse(receipt.ok);
        assertEquals("empty_client_phonenumber", receipt.error.getId());
    }

    @Test
    public void receipts_wrong_phonenumber() {
        assertNull(receipt.receipts("ertq3rg1123", 500));
        assertFalse(receipt.ok);
        assertEquals("wrong_phonenumber", receipt.error.getId());
    }

    @Test
    public void receipts_wrong_amount() {
        assertNull(receipt.receipts("8321654987", -7));
        assertFalse(receipt.ok);
        assertEquals("wrong_amount", receipt.error.getId());
    }

    @Test
    public void receipts_tin_not_connected() {
        receipt.server.user.tinConnected = false;
        assertNull(receipt.receipts("8321654987", 500));
        assertFalse(receipt.ok);
        assertEquals("tin_not_connected", receipt.error.getId());
    }

    @Test
    public void receipts_ok() {
        receipt.server.user.tinConnected = true;
        assertEquals("8321654987", receipt.receipts("8321654987", 500).clientPhoneNumber);
        assertTrue(receipt.ok);
        assertEquals("", receipt.error.getId());
    }

    @Test
    public void getReceipts_tin_not_connected() {
        receipt.server.user.tinConnected = false;
        assertNull(receipt.getReceipts(50, 0));
        assertFalse(receipt.ok);
        assertEquals("tin_not_connected", receipt.error.getId());
    }

    @Test
    public void getReceipts_ok() {
        receipt.server.user.tinConnected = true;
        assertNotNull(receipt.getReceipts(50, 0));
        assertTrue(receipt.ok);
        assertEquals("", receipt.error.getId());
    }

    @Test
    public void getReceiptById_wrong_receipt_id() {
        assertNull(receipt.getReceiptById(0));
        assertFalse(receipt.ok);
        assertEquals("wrong_receipt_id", receipt.error.getId());
    }

    @Test
    public void getReceiptById_tin_not_connected() {
        receipt.server.user.tinConnected = false;
        assertNull(receipt.getReceiptById(1));
        assertFalse(receipt.ok);
        assertEquals("tin_not_connected", receipt.error.getId());
    }

    @Test
    public void getReceiptById_receipt_not_found() {
        assertNull(receipt.getReceiptById(34511));
        assertFalse(receipt.ok);
        assertEquals("receipt_not_found", receipt.error.getId());
    }

    @Test
    public void getReceiptById_ok() {
        receipt.server.user.tinConnected = true;
        assertNotNull(receipt.getReceiptById(1));
        assertTrue(receipt.ok);
        assertEquals("", receipt.error.getId());
    }

    @Test
    public void cancelReceipt_wrong_receipt_id() {
        assertNull(receipt.cancelReceipt(0, ""));
        assertFalse(receipt.ok);
        assertEquals("wrong_receipt_id", receipt.error.getId());
    }

    @Test
    public void cancelReceipt_tin_not_connected() {
        receipt.server.user.tinConnected = false;
        assertNull(receipt.cancelReceipt(1, ""));
        assertFalse(receipt.ok);
        assertEquals("tin_not_connected", receipt.error.getId());
    }

    @Test
    public void cancelReceipt_receipt_not_found() {
        assertNull(receipt.cancelReceipt(34511, ""));
        assertFalse(receipt.ok);
        assertEquals("receipt_not_found", receipt.error.getId());
    }

    @Test
    public void cancelReceipt_ok() {
        receipt.server.user.tinConnected = true;
        assertNotNull(receipt.cancelReceipt(1, ""));
        assertTrue(receipt.ok);
        assertEquals("", receipt.error.getId());
    }

    @After
    public void destroyObject() {
        receipt = null;
    }
}