package club.plus1.forcetaxi.stub;

import org.jetbrains.annotations.NotNull;

import club.plus1.forcetaxi.model.ResponseBalance;
import club.plus1.forcetaxi.model.ServerError;

public class ResponseBalanceStub implements ResponseBalance {

    // Параметры, возращаемые методами сервера
    public boolean ok;          // Результат работы метода
    public ServerError error;   // Описание результата работы с кодом и текстом
    private ServerStub server;  // Заглушка для сервера и всех переменных

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    public ResponseBalanceStub(String appToken) {
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
     * Метод для получения баланса юзера
     *
     * @return amount - текущий баланс пользователя
     */
    @Override
    public int balance() {
        return 1000;
    }
}
