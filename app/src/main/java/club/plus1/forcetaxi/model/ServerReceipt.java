package club.plus1.forcetaxi.model;

public class ServerReceipt {
    public int id;
    public String clientPhoneNumber;
    public double amount;

    public ServerReceipt(int id, String clientPhoneNumber, double amount) {
        this.id = id;
        this.clientPhoneNumber = clientPhoneNumber;
        this.amount = amount;
    }
}
