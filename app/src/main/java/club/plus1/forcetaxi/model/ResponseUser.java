package club.plus1.forcetaxi.model;

import org.jetbrains.annotations.NotNull;

public interface ResponseUser {

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
     * Метод запроса данных пользователя
     *
     * @return user: object - данные самозанятого
     * user.fts: object - данные самозанятого из ФНС
     */
    ServerUser getUser();

    /**
     * Метод проверки привязки ИНН
     *
     * @param tin - ИНН
     * @return connected: bool - привязан ли ИНН
     */
    boolean checkConnectedTin(String tin);

    /**
     * Метод проверки ИНН
     *
     * @param tin - ИНН
     * @return fts: object - данные самозанятого из ФНС
     */
    ServerFts validateTin(String tin);

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
    String searchTin(String firstName, String secondName, String patronymic,
                     String birthday, String passportSeries, String passportNumber);

    /**
     * Метод привязки ИНН к пользователю
     *
     * @param tin - ИНН
     * @return user: object - данные пользователя
     */
    ServerUser connectTin(String tin);
}
