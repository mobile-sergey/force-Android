package club.plus1.forcetaxi.model;

import java.util.Map;

public interface ResponseReceipt {

    // Параметры, возращаемые методами сервера
    boolean ok = false;         // Результат работы метода
    ServerError error = null;   // Описание результата работы с кодом и текстом
    String appToken = "";       // Все функции требуют установленный appToken
    String userToken = "";      // Токен авторизации пользователя

    /**
     * Метод выбивания чека
     *
     * @param clientPhoneNumber - номер клиента
     * @param amount            - сумма чека
     * @return receipt: object - данные выбитого чека
     */
    ServerReceipt receipts(String clientPhoneNumber, double amount);

    /**
     * Метод запроса выбитых чеков
     *
     * @param limit  - количество чеков (по умолчанию 50)
     * @param offset - смещение от начала (по умолчанию 0)
     * @return receipts: array - выбитые чеки самозанятого
     */
    Map<Integer, ServerReceipt> getReceipts(int limit, int offset);

    /**
     * Метод запроса информации по чеку
     *
     * @param receiptId - внутренний id чека
     * @return receipt: object - информация по чеку самозанятого
     */
    ServerReceipt getReceiptById(int receiptId);

    /**
     * Метод сторнирования чека
     *
     * @param reason - внутренний id чека
     * @return receipt: object - информация по чеку самозанятого
     */
    ServerReceipt cancelReceipt(int receiptId, String reason);

}
