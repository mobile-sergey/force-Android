package club.plus1.forcetaxi.stub;

import org.jetbrains.annotations.NotNull;

import club.plus1.forcetaxi.model.ResponseUser;
import club.plus1.forcetaxi.model.ServerError;
import club.plus1.forcetaxi.model.ServerFts;
import club.plus1.forcetaxi.model.ServerUser;
import club.plus1.forcetaxi.service.Regex;

public class ResponseUserStub implements ResponseUser {

    // Параметры, возращаемые методами сервера
    public boolean ok;          // Результат работы метода
    public ServerError error;   // Описание результата работы с кодом и текстом
    ServerStub server;          // Заглушка для сервера и всех переменных

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    public ResponseUserStub(String appToken) {
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
     * Метод запроса данных пользователя
     *
     * @return user: object - данные самозанятого
     * user.fts: object - данные самозанятого из ФНС
     */
    @Override
    public ServerUser getUser() {
        return server.user;
    }

    /**
     * Метод проверки привязки ИНН
     *
     * @param tin - ИНН
     * @return connected: bool - привязан ли ИНН
     */
    @Override
    public boolean checkConnectedTin(String tin) {
        if (tin.isEmpty()) {
            this.ok = false;
            this.error = new ServerError("empty_tin");
            return false;
        } else if (!tin.matches(Regex.tin())) {
            this.ok = false;
            this.error = new ServerError("wrong_tin");
            return false;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            return true;
        }
    }

    /**
     * Метод проверки ИНН
     *
     * @param tin - ИНН
     * @return fts: object - данные самозанятого из ФНС
     */
    @Override
    public ServerFts validateTin(String tin) {
        if (tin.isEmpty()) {
            this.ok = false;
            this.error = new ServerError("empty_tin");
            return null;
        } else if (!tin.matches(Regex.tin())) {
            this.ok = false;
            this.error = new ServerError("wrong_tin");
            return null;
        } else if (!server.tins.contains(tin)) {
            this.ok = false;
            this.error = new ServerError("unregistered_tin");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            return new ServerFts(tin);
        }
    }

    /**
     * Метод поиска ИНН по параметрам
     *
     * @param firstName      - Имя
     * @param secondName     - Фамилия
     * @param patronymic     - Отчество
     * @param birthday       - дата (YYYY-MM-DD)
     * @param passportSeries - серия паспорта (4 цифры)
     * @param passportNumber - номер паспорта (6 цифр)
     * @return tin: number(string) - номер ИНН
     */
    @Override
    public String searchTin(String firstName, String secondName, String patronymic,
                            String birthday, String passportSeries, String passportNumber) {
        if (!passportSeries.matches(Regex.passportSeries())) {
            this.ok = false;
            this.error = new ServerError("wrong_passport_series");
            return "";
        } else if (!passportNumber.matches(Regex.passportNumber())) {
            this.ok = false;
            this.error = new ServerError("wrong_passport_number");
            return "";
        } else if (!birthday.matches(Regex.date())) {
            this.ok = false;
            this.error = new ServerError("wrong_birthday");
            return "";
        } else {
            this.ok = true;
            this.error = new ServerError("");
            return server.tins.get(0);
        }
    }

    /**
     * Метод привязки ИНН к пользователю
     *
     * @param tin - ИНН
     * @return user: object - данные пользователя
     */
    @Override
    public ServerUser connectTin(String tin) {
        if (tin.isEmpty()) {
            this.ok = false;
            this.error = new ServerError("empty_tin");
            return null;
        } else if (!tin.matches(Regex.tin())) {
            this.ok = false;
            this.error = new ServerError("wrong_tin");
            return null;
        } else if (!server.tins.contains(tin)) {
            this.ok = false;
            this.error = new ServerError("unregistered_tin");
            return null;
        } else if (server.connected.keySet().contains(tin)) {
            this.ok = false;
            this.error = new ServerError("tin_already_connected");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            String fio = server.connected.get(tin);
            ServerUser user = server.users.get(fio);
            if (user == null) {
                user = new ServerUser("", "", "");
            }
            user.fts = new ServerFts(tin);
            server.user = user;
            return user;
        }
    }
}
