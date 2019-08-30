package club.plus1.forcetaxi.model;

public interface ResponseAuth {

    // Параметры, возращаемые методами сервера
    boolean ok = false;         // Результат работы метода
    ServerError error = null;   // Описание результата работы с кодом и текстом
    String appToken = "";       // Все функции требуют установленный appToken
    String userToken = "";      // Токен авторизации пользователя

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
}
