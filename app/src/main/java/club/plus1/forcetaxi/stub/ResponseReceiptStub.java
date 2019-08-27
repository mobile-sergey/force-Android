package club.plus1.forcetaxi.stub;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

import club.plus1.forcetaxi.model.ResponseReceipt;
import club.plus1.forcetaxi.model.ServerError;
import club.plus1.forcetaxi.model.ServerReceipt;
import club.plus1.forcetaxi.service.Regex;

class ResponseReceiptStub implements ResponseReceipt {

    boolean tinConnected;
    // Основные переменные класса
    boolean ok;                 // Результат работы метода
    ServerError error;          // Описание результата работы с кодом и текстом
    private String appToken;    // Все функции требуют установленный appToken

    // Переменные для заглушки
    @SuppressLint("UseSparseArrays")
    private Map<Integer, ServerReceipt> receipts = new HashMap<>();

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    ResponseReceiptStub(String appToken) {
        this.ok = false;
        this.error = new ServerError("unknown_error", "");
        this.appToken = appToken;
        ServerReceipt receipt1 = new ServerReceipt();
        receipt1.id = "12345";
        receipt1.amount = 500;
        receipt1.clientPhoneNumber = "1234567890";
        this.receipts.put(12345, receipt1);
        ServerReceipt receipt2 = new ServerReceipt();
        receipt2.id = "12346";
        receipt2.amount = 100;
        receipt2.clientPhoneNumber = "9876543210";
        this.receipts.put(12346, receipt2);
        tinConnected = true;
    }

    /**
     * Метод выбивания чека
     *
     * @param clientPhoneNumber - номер клиента
     * @param amount            - сумма чека
     * @return receipt: object - данные выбитого чека
     */
    @Override
    public ServerReceipt receipts(String clientPhoneNumber, double amount) {
        if (clientPhoneNumber.isEmpty()) {
            this.ok = false;
            this.error = new ServerError("empty_client_phonenumber");
            return null;
        } else if (!clientPhoneNumber.matches(Regex.phone())) {
            this.ok = false;
            this.error = new ServerError("wrong_phonenumber");
            return null;
        } else if (amount <= 0) {
            this.ok = false;
            this.error = new ServerError("wrong_amount");
            return null;
        } else if (!tinConnected) {
            this.ok = false;
            this.error = new ServerError("tin_not_connected");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            ServerReceipt receipt = new ServerReceipt();
            receipt.id = "11111";
            receipt.amount = amount;
            receipt.clientPhoneNumber = clientPhoneNumber;
            return receipt;
        }
    }

    /**
     * Метод запроса выбитых чеков
     *
     * @param limit  - количество чеков (по умолчанию 50)
     * @param offset - смещение от начала (по умолчанию 0)
     * @return receipts: array - выбитые чеки самозанятого
     */
    @Override
    public Map<Integer, ServerReceipt> getReceipts(int limit, int offset) {
        if (!tinConnected) {
            this.ok = false;
            this.error = new ServerError("tin_not_connected");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            return receipts;
        }
    }

    /**
     * Метод запроса информации по чеку
     *
     * @param receiptId - внутренний id чека
     * @return receipt: object - информация по чеку самозанятого
     */
    @Override
    public ServerReceipt getReceiptById(int receiptId) {
        if (receiptId <= 0) {
            this.ok = false;
            this.error = new ServerError("wrong_receipt_id");
            return null;
        } else if (!tinConnected) {
            this.ok = false;
            this.error = new ServerError("tin_not_connected");
            return null;
        } else if (!receipts.keySet().contains(receiptId)) {
            this.ok = false;
            this.error = new ServerError("receipt_not_found");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            return receipts.get(receiptId);
        }
    }

    /**
     * Метод сторнирования чека
     *
     * @param reason - внутренний id чека
     * @return receipt: object - информация по чеку самозанятого
     */
    @Override
    public ServerReceipt cancelReceipt(int receiptId, String reason) {
        if (receiptId <= 0) {
            this.ok = false;
            this.error = new ServerError("wrong_receipt_id");
            return null;
        } else if (!tinConnected) {
            this.ok = false;
            this.error = new ServerError("tin_not_connected");
            return null;
        } else if (!receipts.keySet().contains(receiptId)) {
            this.ok = false;
            this.error = new ServerError("receipt_not_found");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            return receipts.get(receiptId);
        }
    }
}
