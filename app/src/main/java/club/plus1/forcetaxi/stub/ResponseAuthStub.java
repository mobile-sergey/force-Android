package club.plus1.forcetaxi.stub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import club.plus1.forcetaxi.model.ResponseAuth;
import club.plus1.forcetaxi.model.ServerError;
import club.plus1.forcetaxi.model.ServerUser;
import club.plus1.forcetaxi.service.Regex;

class ResponseAuthStub implements ResponseAuth {

    // Константы для заглушки
    private static final String USER_TOKEN = "047069db-df4b-48ce-ba48-50641d9cc490";
    // Параметры, возращаемые методами сервера
    boolean ok;         // Результат работы метода
    ServerError error;  // Описание результата работы с кодом и текстом
    String userToken;   // Токен авторизации пользователя
    private String appToken;    // Все функции требуют установленный appToken
    // Переменные для заглушки
    private List<String> users = new ArrayList<>();
    private List<String> phones = new ArrayList<>();
    private Map<String, String> userPhones = new HashMap<>();
    private Map<String, String> userPasswords = new HashMap<>();

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    ResponseAuthStub(String appToken) {
        ok = false;
        error = new ServerError("unknown_error", "");
        this.appToken = appToken;
        userToken = "";
        phones.add("1234567890");
        phones.add("0987654321");
        phones.add("9999999999");
        users.add("Иванов Иван Иванович");
        users.add("Петров Петр Петрович");
        userPhones.put(users.get(0), phones.get(0));
        userPhones.put(users.get(1), phones.get(1));
        userPasswords.put(users.get(0), "123");
        userPasswords.put(users.get(1), "123");
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
            this.ok = false;
            this.error = new ServerError("empty_phonenumber");
            return null;
        } else if (!phoneNumber.matches(Regex.phone())) {
            this.ok = false;
            this.error = new ServerError("wrong_phonenumber");
            return null;
        } else if (!phones.contains(phoneNumber)) {
            this.ok = false;
            this.error = new ServerError("phonenumber_is_not_registered");
            return null;
        } else if (!userPhones.values().contains(phoneNumber)) {
            this.ok = false;
            this.error = new ServerError("user_not_found");
            return null;
        } else if (!userPasswords.values().contains(password)) {
            this.ok = false;
            this.error = new ServerError("wrong_password");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            ServerUser user = new ServerUser();
            user.userToken = userToken = USER_TOKEN;
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
        this.ok = true;
        this.error = new ServerError("");
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
        this.ok = true;
        this.error = new ServerError("");
    }

    /**
     * Метод для деавторизации юзера
     */
    @Override
    public void logout() {
        this.ok = true;
        this.error = new ServerError("");
        userToken = "";
    }
}
