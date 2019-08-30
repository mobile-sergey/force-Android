package club.plus1.forcetaxi.stub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import club.plus1.forcetaxi.model.ResponseUser;
import club.plus1.forcetaxi.model.ServerError;
import club.plus1.forcetaxi.model.ServerFts;
import club.plus1.forcetaxi.model.ServerUser;
import club.plus1.forcetaxi.service.Regex;

public class ResponseUserStub implements ResponseUser {

    // Константы для заглушки
    private static final String USER_TOKEN = "047069db-df4b-48ce-ba48-50641d9cc490";
    // Параметры, возращаемые методами сервера
    boolean ok;         // Результат работы метода
    ServerError error;  // Описание результата работы с кодом и текстом
    private String appToken;    // Все функции требуют установленный appToken
    private String userToken;   // Токен авторизации пользователя
    // Переменные для заглушки
    private List<String> tins = new ArrayList<>();
    private Map<String, String> connected = new HashMap<>();

    /**
     * Конструктор класса с заполнением начальными данными
     *
     * @param appToken - токен приложения
     */
    public ResponseUserStub(String appToken) {
        ok = false;
        error = new ServerError("unknown_error", "");
        this.appToken = appToken;
        userToken = "";
        tins.add("0123456789");
        tins.add("012345678912");
        connected.put("012345678912", "Иванов Иван Иванович");
    }

    /**
     * Метод запроса данных пользователя
     *
     * @return user: object - данные самозанятого
     * user.fts: object - данные самозанятого из ФНС
     */
    @Override
    public ServerUser getUser() {
        ServerUser user = new ServerUser();
        user.fts = new ServerFts();
        user.userToken = USER_TOKEN;
        return user;
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
        } else if (!tins.contains(tin)) {
            this.ok = false;
            this.error = new ServerError("unregistered_tin");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            ServerFts fts = new ServerFts();
            fts.tin = tin;
            return fts;
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
            return tins.get(0);
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
        } else if (!tins.contains(tin)) {
            this.ok = false;
            this.error = new ServerError("unregistered_tin");
            return null;
        } else if (connected.keySet().contains(tin)) {
            this.ok = false;
            this.error = new ServerError("tin_already_connected");
            return null;
        } else {
            this.ok = true;
            this.error = new ServerError("");
            ServerUser user = new ServerUser();
            ServerFts fts = new ServerFts();
            fts.tin = tin;
            user.fts = fts;
            user.userToken = USER_TOKEN;
            return user;
        }
    }
}
