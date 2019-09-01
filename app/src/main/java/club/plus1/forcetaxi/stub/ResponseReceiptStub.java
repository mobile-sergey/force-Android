package club.plus1.forcetaxi.stub;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import club.plus1.forcetaxi.model.ResponseReceipt;
import club.plus1.forcetaxi.model.ServerError;
import club.plus1.forcetaxi.model.ServerReceipt;
import club.plus1.forcetaxi.service.Regex;

public class ResponseReceiptStub implements ResponseReceipt {

    // Основные переменные класса
    public boolean ok;          // Результат работы метода
    public ServerError error;   // Описание результата работы с кодом и текстом
    ServerStub server;          // Заглушка для сервера и всех переменных

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    public ResponseReceiptStub(String appToken) {
        ok = false;
        error = new ServerError("unknown_error", "");
        server = ServerStub.getInstance(appToken);
    }

    /**
     * Возвращает текст ошибки, если она есть
     *
     * @return String - текст ошибки, если она есть
     */
    @NotNull
    public String getErrorText() {
        return error.getText();
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
            ok = false;
            error = new ServerError("empty_client_phonenumber");
            return null;
        } else if (!clientPhoneNumber.matches(Regex.phone())) {
            ok = false;
            error = new ServerError("wrong_phonenumber");
            return null;
        } else if (amount <= 0) {
            ok = false;
            error = new ServerError("wrong_amount");
            return null;
        } else if (!server.user.tinConnected) {
            ok = false;
            error = new ServerError("tin_not_connected");
            return null;
        } else {
            ok = true;
            error = new ServerError("");
            ServerReceipt receipt = new ServerReceipt(server.receipts.size(), clientPhoneNumber, amount, server.user);
            server.receipts.put(receipt.id, receipt);
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
        if (!server.user.tinConnected) {
            ok = false;
            error = new ServerError("tin_not_connected");
            return null;
        } else {
            ok = true;
            error = new ServerError("");
            return server.receipts;
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
            ok = false;
            error = new ServerError("wrong_receipt_id");
            return null;
        } else if (!server.receipts.keySet().contains(receiptId)) {
            ok = false;
            error = new ServerError("receipt_not_found");
            return null;
        } else if (!server.user.tinConnected) {
            ok = false;
            error = new ServerError("tin_not_connected");
            return null;
        } else {
            ok = true;
            error = new ServerError("");
            return server.receipts.get(receiptId);
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
            ok = false;
            error = new ServerError("wrong_receipt_id");
            return null;
        } else if (!server.receipts.keySet().contains(receiptId)) {
            ok = false;
            error = new ServerError("receipt_not_found");
            return null;
        } else if (!server.user.tinConnected) {
            ok = false;
            error = new ServerError("tin_not_connected");
            return null;
        } else {
            ok = true;
            error = new ServerError("");
            return server.receipts.get(receiptId);
        }
    }
}
