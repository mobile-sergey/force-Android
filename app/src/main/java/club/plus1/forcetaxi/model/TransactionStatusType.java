package club.plus1.forcetaxi.model;

public enum TransactionStatusType {
    success, processing, cancel;

    @Override
    public String toString() {
        if (this == TransactionStatusType.success) {
            return "Успешно";
        } else if (this == TransactionStatusType.cancel) {
            return "Отклонено";
        } else {
            return "В обработке";
        }
    }
}
