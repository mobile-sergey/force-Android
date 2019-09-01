package club.plus1.forcetaxi.model;

public class ServerTransaction {
    public double amount;
    public String date;
    TransactionStatusType status;

    ServerTransaction(String date, double amount, TransactionStatusType status) {
        this.date = date;
        this.amount = amount;
        this.status = status;
    }

}
