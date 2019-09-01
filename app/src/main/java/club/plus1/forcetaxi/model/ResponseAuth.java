package club.plus1.forcetaxi.model;

import org.jetbrains.annotations.NotNull;

public interface ResponseAuth {

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

    /**
     * Метод для авторизации юзера
     *
     * @param phoneNumber - номер телефона на который был отправлен код
     * @param password    - пароль
     * @return user - на этапе разработки, полный объект user token - объект токена
     */
    ServerUser login(String phoneNumber, String password);

    /**
     * Метод запроса сброса пароля
     *
     * @param phoneNumber - номер телефона юзера
     */
    void passwordRequestReset(String phoneNumber);

    /**
     * Метод сброса пароля
     *
     * @param phoneNumber - номер телефона пользователя
     * @param password    - новый пароль
     * @param smsCode     - код из смс
     */
    void passwordReset(String phoneNumber, String password, String smsCode);

    /**
     * Метод для деавторизации юзера
     */
    void logout();

    // TODO: Метод нужно добавить в API

    /**
     * Метод отправки письма с инструкциями
     *
     * @param email - электронная почта юзера
     */
    void sendMail(String email);
}
