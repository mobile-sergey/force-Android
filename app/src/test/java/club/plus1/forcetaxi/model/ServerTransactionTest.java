package club.plus1.forcetaxi.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTransactionTest {

    @Test
    public void ServerTransaction() {
        ServerTransaction transaction = new ServerTransaction("2000-01-01", 500.0, TransactionStatusType.success);
        assertEquals("2000-01-01", transaction.date);
        assertEquals(TransactionStatusType.success, transaction.status);
    }
}