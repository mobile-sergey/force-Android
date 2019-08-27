package club.plus1.forcetaxi.stub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import club.plus1.forcetaxi.model.ResponseRegistration;
import club.plus1.forcetaxi.model.ServerError;
import club.plus1.forcetaxi.model.ServerPhone;
import club.plus1.forcetaxi.model.ServerUser;
import club.plus1.forcetaxi.service.Generator;
import club.plus1.forcetaxi.service.Regex;

class ResponseRegistrationStub implements ResponseRegistration {

    // Основные переменные класса
    boolean ok;         // Результат работы метода
    ServerError error;  // Описание результата работы с кодом и текстом
    private String appToken;    // Все функции требуют установленный appToken

    // Переменные для заглушки
    private String smsCode;
    private Date smsCodeDate;
    private List<String> phones = new ArrayList<>();
    private List<String> users = new ArrayList<>();

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    ResponseRegistrationStub(String appToken) {
        this.ok = false;
        this.error = new ServerError("unknown_error", "");
        this.appToken = appToken;
        this.phones.add("1234567890");
        this.phones.add("0987654321");
        this.users.add("Иванов Иван Иванович");
        this.users.add("Петров Петр Петрович");
        this.smsCode = "";
        this.smsCodeDate = new Date();
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
            this.ok = false;
            this.error = new ServerError("empty_phonenumber");
            return null;
        } else if (!phoneNumber.matches(Regex.phone())) {
            this.ok = false;
            this.error = new ServerError("wrong_phonenumber");
            return null;
        } else if (phones.contains(phoneNumber)) {
            this.ok = false;
            this.error = new ServerError("phonenumber_already_exists");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            this.phones.add(phoneNumber);
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
            this.ok = false;
            this.error = new ServerError("empty_phonenumber");
            return "";
        } else if (!phoneNumber.matches(Regex.phone())) {
            this.ok = false;
            this.error = new ServerError("wrong_phonenumber");
            return "";
        } else {
            this.ok = true;
            this.error = new ServerError("");
            this.smsCode = Generator.smsCode();
            this.smsCodeDate = new Date();
            return this.smsCode;
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
        } else if (smsCode.isEmpty()) {
            this.ok = false;
            this.error = new ServerError("smscode_is_empty");
            return null;
        } else if (((new Date()).getTime() - smsCodeDate.getTime()) > 24 * 60 * 60) {
            this.ok = false;
            this.error = new ServerError("smscode_is_outdated");
            return null;
        } else if (!smsCode.equals(this.smsCode)) {
            this.ok = false;
            this.error = new ServerError("wrong_smscode");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
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
            this.ok = false;
            this.error = new ServerError("empty_phonenumber");
            return null;
        } else if (password.isEmpty()) {
            this.ok = false;
            this.error = new ServerError("empty_password");
            return null;
        } else if (!phoneNumber.matches(Regex.phone())) {
            this.ok = false;
            this.error = new ServerError("wrong_phonenumber");
            return null;
        } else if (!phones.contains(phoneNumber)) {
            this.ok = false;
            this.error = new ServerError("phonenumber_is_not_validated");
            return null;
        } else if (!users.contains(firstName + " " + secondName + " " + patronymic)) {
            this.ok = false;
            this.error = new ServerError("user_already_exist");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            this.users.add(firstName + " " + secondName + " " + patronymic);
            return new ServerUser();
        }
    }

    String getSmsCode() {
        return this.smsCode;
    }

    void setSmsCode() {
        this.smsCode = Generator.smsCode();
    }

    Date getSmsCodeDate() {
        return this.smsCodeDate;
    }

    void setSmsCodeDate(Date newDate) {
        this.smsCodeDate = newDate;
    }
}
