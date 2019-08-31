package club.plus1.forcetaxi.stub;

import android.annotation.SuppressLint;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import club.plus1.forcetaxi.model.ServerReceipt;
import club.plus1.forcetaxi.model.ServerUser;
import club.plus1.forcetaxi.service.ActiveLog;

public class ServerStub {

    private static ServerStub mInstance;   // Единственный объект этого класса
    public String appToken;                // Все функции требуют установленный appToken
    public ServerUser user;                 // Информация о текущем пользователе

    // Переменные для заглушки
    boolean tinConnected;       // Заглушка, показывающая, что ИНН привязан
    String smsCode;
    Date smsCodeDate;
    List<String> tins = new ArrayList<>();
    Map<String, String> connected = new HashMap<>();
    Map<String, ServerUser> users = new HashMap<>();
    List<String> phones = new ArrayList<>();
    Map<String, String> phoneUsers = new HashMap<>();
    Map<String, String> userPasswords = new HashMap<>();
    @SuppressLint("UseSparseArrays")
    Map<Integer, ServerReceipt> receipts = new HashMap<>();

    /**
     * Конструктор класса с заполнением заглушечных данных
     */
    private ServerStub(String appToken) {
        ActiveLog.getInstance().log();
        this.appToken = appToken;
        user = new ServerUser("", "", "");
        user.userToken = "";
        tins.add("0123456789");
        tins.add("012345678912");
        connected.put("012345678912", "Иванов Иван Иванович");
        phones.add("1234567890");
        phones.add("0987654321");
        phones.add("9999999999");
        ServerUser user1 = new ServerUser("Иванов", "Иван", "Иванович");
        ServerUser user2 = new ServerUser("Петров", "Петр", "Петрович");
        users.put(user1.toString(), user1);
        users.put(user2.toString(), user2);
        int i = 0;
        for (Map.Entry<String, ServerUser> element : users.entrySet()) {
            phoneUsers.put(phones.get(i), element.getKey());
            userPasswords.put(element.getKey(), "123");
            i++;
        }
        ServerReceipt receipt1 = new ServerReceipt(1, "1234567890", 500);
        receipts.put(receipt1.id, receipt1);
        ServerReceipt receipt2 = new ServerReceipt(2, "9876543210", 100);
        receipts.put(receipt2.id, receipt2);
        tinConnected = true;
        this.smsCode = "";
        this.smsCodeDate = new Date();
    }

    /**
     * Получение единственного экземпляра класса
     *
     * @return ServerStub - экземпляр класса
     */
    public static ServerStub getInstance(String appToken) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new ServerStub(appToken);
        }
        return mInstance;
    }
}
