package club.plus1.forcetaxi.model;

public enum TransactionStatus {
    success, processing, cancel;

    @Override
    public String toString() {
        if (this == TransactionStatus.success) {
            return "Успешно";
        } else if (this == TransactionStatus.processing) {
            return "В обработке";
        } else if (this == TransactionStatus.cancel) {
            return "Отклонено";
        } else {
            return "Не известно";
        }
    }
}
