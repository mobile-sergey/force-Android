package club.plus1.forcetaxi.model;

import org.jetbrains.annotations.NotNull;

public interface ResponseBalance {

    // Параметры, возращаемые методами сервера
    boolean ok = false;          // Результат работы метода
    ServerError error = null;    // Описание результата работы с кодом и текстом
    String appToken = "";               // Все функции требуют установленный appToken
    String userToken = "";              // Токен авторизации пользователя

    /**
     * Возвращает текст ошибки, если она есть
     *
     * @return String - текст ошибки, если она есть
     */
    @NotNull
    String getErrorText();

    // TODO: Метод ниже нужно добавить в API

    /**
     * Метод для получения баланса юзера
     *
     * @return amount - текущий баланс пользователя
     */
    int balance();

}
