package club.plus1.forcetaxi.stub;

import club.plus1.forcetaxi.model.ResponseAuth;
import club.plus1.forcetaxi.model.ServerError;
import club.plus1.forcetaxi.model.ServerUser;
import club.plus1.forcetaxi.service.Regex;

public class ResponseAuthStub implements ResponseAuth {

    // Параметры, возращаемые методами сервера
    boolean ok;                 // Результат работы метода
    ServerError error;          // Описание результата работы с кодом и текстом
    ServerStub server;          // Заглушка для сервера и всех переменных

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    public ResponseAuthStub(String appToken) {
        ok = false;
        error = new ServerError("unknown_error", "");
        server = ServerStub.getInstance(appToken);
    }

    /**
     * Метод для авторизации юзера
     *
     * @param phoneNumber - номер телефона на который был отправлен код
     * @param password    - пароль
     * @return user - на этапе разработки, полный объект user token - объект токена
     */
    @Override
    public ServerUser login(String phoneNumber, String password) {
        if (phoneNumber.isEmpty()) {
            ok = false;
            error = new ServerError("empty_phonenumber");
            return null;
        } else if (!phoneNumber.matches(Regex.phone())) {
            ok = false;
            error = new ServerError("wrong_phonenumber");
            return null;
        } else if (!server.phones.contains(phoneNumber)) {
            ok = false;
            error = new ServerError("phonenumber_is_not_registered");
            return null;
        } else if (!server.phoneUsers.keySet().contains(phoneNumber)) {
            ok = false;
            error = new ServerError("user_not_found");
            return null;
        } else if (!server.userPasswords.values().contains(password)) {
            ok = false;
            error = new ServerError("wrong_password");
            return null;
        } else {
            ok = true;
            error = new ServerError("");
            String fio = server.phoneUsers.get(phoneNumber);
            ServerUser user = server.users.get(fio);
            server.user = user;
            return user;
        }
    }

    /**
     * Метод запроса сброса пароля
     *
     * @param phoneNumber - номер телефона юзера
     */
    @Override
    public void passwordRequestReset(String phoneNumber) {
        ok = true;
        error = new ServerError("");
    }

    /**
     * Метод сброса пароля
     *
     * @param phoneNumber - номер телефона пользователя
     * @param password    - новый пароль
     * @param smsCode     - код из смс
     */
    @Override
    public void passwordReset(String phoneNumber, String password, String smsCode) {
        ok = true;
        error = new ServerError("");
    }

    /**
     * Метод для деавторизации юзера
     */
    @Override
    public void logout() {
        ok = true;
        error = new ServerError("");
        server.user = new ServerUser("", "", "");
    }
}
