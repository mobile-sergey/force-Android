package club.plus1.forcetaxi.stub;

import java.util.Date;

import club.plus1.forcetaxi.model.ResponseRegistration;
import club.plus1.forcetaxi.model.ServerError;
import club.plus1.forcetaxi.model.ServerPhone;
import club.plus1.forcetaxi.model.ServerUser;
import club.plus1.forcetaxi.service.Generator;
import club.plus1.forcetaxi.service.Regex;

public class ResponseRegistrationStub implements ResponseRegistration {

    // Основные переменные класса
    boolean ok;                 // Результат работы метода
    ServerError error;          // Описание результата работы с кодом и текстом
    ServerStub server;          // Заглушка для сервера и всех переменных

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    public ResponseRegistrationStub(String appToken) {
        ok = false;
        error = new ServerError("unknown_error", "");
        server = ServerStub.getInstance(appToken);
    }

    /**
     * Метод проверки регистрации номера телефона
     *
     * @param phoneNumber - номер телефона на который был отправлен код
     * @return phone - на этапе разработки, полный объект телефона
     */
    @Override
    public ServerPhone validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            ok = false;
            error = new ServerError("empty_phonenumber");
            return null;
        } else if (!phoneNumber.matches(Regex.phone())) {
            ok = false;
            error = new ServerError("wrong_phonenumber");
            return null;
        } else if (server.phones.contains(phoneNumber)) {
            ok = false;
            error = new ServerError("phonenumber_already_exists");
            return null;
        } else {
            ok = true;
            error = new ServerError("");
            server.phones.add(phoneNumber);
            return new ServerPhone(phoneNumber);
        }
    }

    /**
     * Метод отправки смс кода на номер телефона
     *
     * @param phoneNumber - номер телефона на который был отправлен код
     * @return pin - на этапе разработки, пин код, который будет отправляться на телефон
     * ( на ранних этапах разработки смс не отправляется )
     */
    @Override
    public String requestSmsCode(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            ok = false;
            error = new ServerError("empty_phonenumber");
            return "";
        } else if (!phoneNumber.matches(Regex.phone())) {
            ok = false;
            error = new ServerError("wrong_phonenumber");
            return "";
        } else {
            ok = true;
            error = new ServerError("");
            server.smsCode = Generator.smsCode();
            server.smsCodeDate = new Date();
            return server.smsCode;
        }
    }

    /**
     * Метод проверки смс кода для номера телефона
     *
     * @param phoneNumber - номер телефона на который был отправлен код
     * @param smsCode     - код, который был в смс
     * @return phone - на этапе разработки, полный объект телефона
     */
    @Override
    public ServerPhone validateSmsCode(String phoneNumber, String smsCode) {
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
        } else if (smsCode.isEmpty()) {
            ok = false;
            error = new ServerError("smscode_is_empty");
            return null;
        } else if (((new Date()).getTime() - server.smsCodeDate.getTime()) > 24 * 60 * 60) {
            ok = false;
            error = new ServerError("smscode_is_outdated");
            return null;
        } else if (!smsCode.equals(server.smsCode)) {
            ok = false;
            error = new ServerError("wrong_smscode");
            return null;
        } else {
            ok = true;
            error = new ServerError("");
            return new ServerPhone(phoneNumber);
        }
    }

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
    @Override
    public ServerUser createUser(String phoneNumber, String email,
                                 String firstName, String secondName, String patronymic, String password) {
        if (phoneNumber.isEmpty()) {
            ok = false;
            error = new ServerError("empty_phonenumber");
            return null;
        } else if (password.isEmpty()) {
            ok = false;
            error = new ServerError("empty_password");
            return null;
        } else if (!phoneNumber.matches(Regex.phone())) {
            ok = false;
            error = new ServerError("wrong_phonenumber");
            return null;
        } else if (!server.phones.contains(phoneNumber)) {
            ok = false;
            error = new ServerError("phonenumber_is_not_validated");
            return null;
        } else if (!server.users.keySet().contains(firstName + " " + secondName + " " + patronymic)) {
            ok = false;
            error = new ServerError("user_already_exist");
            return null;
        } else {
            ok = true;
            error = new ServerError("");
            ServerUser user = new ServerUser(firstName, secondName, patronymic);
            server.users.put(user.toString(), user);
            return user;
        }
    }

    String getSmsCode() {
        return server.smsCode;
    }

    void setSmsCode() {
        server.smsCode = Generator.smsCode();
    }

    Date getSmsCodeDate() {
        return server.smsCodeDate;
    }

    void setSmsCodeDate(Date newDate) {
        server.smsCodeDate = newDate;
    }
}
