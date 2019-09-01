package club.plus1.forcetaxi.model;

import org.jetbrains.annotations.NotNull;

public interface ResponseRegistration {

    // Параметры, возращаемые методами сервера
    boolean ok = false;          // Результат работы метода
    ServerError error = null;    // Описание результата работы с кодом и текстом
    String appToken = "";               // Все функции требуют установленный appToken

    /**
     * Возвращает текст ошибки, если она есть
     *
     * @return String - текст ошибки, если она есть
     */
    @NotNull
    String getErrorText();

    /**
     * Метод проверки регистрации номера телефона
     *
     * @param phoneNumber - номер телефона на который был отправлен код
     * @return phone - на этапе разработки, полный объект телефона
     */
    ServerPhone validatePhoneNumber(String phoneNumber);

    /**
     * Метод отправки смс кода на номер телефона
     *
     * @param phoneNumber - номер телефона на который был отправлен код
     * @return pin - на этапе разработки, пин код, который будет отправляться на телефон
     * ( на ранних этапах разработки смс не отправляется )
     */
    String requestSmsCode(String phoneNumber);

    /**
     * Метод проверки смс кода для номера телефона
     *
     * @param phoneNumber - номер телефона на который был отправлен код
     * @param smsCode     - код, который был в смс
     * @return phone - на этапе разработки, полный объект телефона
     */
    ServerPhone validateSmsCode(String phoneNumber, String smsCode);

    /**
     * Метод для создания нового пользователя на площадке.
     *
     * @param phoneNumber - Ранее валидированный номер телефона (обязательно),
     * @param email       - Электронная почта (опционально),
     * @param firstName   - Имя (опционально),
     * @param secondName  - Фамилия (опционально),
     * @param patronymic  - Отчество (опционально),
     * @param password    - Пароль (обязательно)
     * @return user - на данный момент полный объект user, и в нём user_token - токен авторизации пользователя
     */
    ServerUser createUser(String phoneNumber, String email,
                          String firstName, String secondName, String patronymic, String password);
}
