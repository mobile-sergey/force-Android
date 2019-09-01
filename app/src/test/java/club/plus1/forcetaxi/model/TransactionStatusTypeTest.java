package club.plus1.forcetaxi.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransactionStatusTypeTest {

    @Test
    public void name() {
        TransactionStatusType statusType = TransactionStatusType.success;
        assertEquals("success", statusType.name());
    }

    @Test
    public void toString_success() {
        TransactionStatusType statusType = TransactionStatusType.success;
        assertEquals("Успешно", statusType.toString());
    }

    @Test
    public void toString_cancel() {
        TransactionStatusType statusType = TransactionStatusType.cancel;
        assertEquals("Отклонено", statusType.toString());
    }

    @Test
    public void toString_processing() {
        TransactionStatusType statusType = TransactionStatusType.processing;
        assertEquals("В обработке", statusType.toString());
    }

}