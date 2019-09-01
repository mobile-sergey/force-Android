package club.plus1.forcetaxi.model;

import java.util.Date;

import club.plus1.forcetaxi.stub.ConstantStub;

public class ServerReceipt {
    public int id;
    public String clientPhoneNumber;
    public double amount;
    public Date date;
    public String url;
    public String executor;
    public String serviceType;

    public ServerReceipt(int id, String clientPhoneNumber, double amount, ServerUser user) {
        this.id = id;
        this.serviceType = ConstantStub.SERVICE_TYPE;
        this.amount = amount;
        this.executor = user.toString() + ", " + user.fts.tin;
        this.clientPhoneNumber = clientPhoneNumber;
        this.date = new Date();
        this.url = ConstantStub.URL_RECEIPT + id;
    }
}
