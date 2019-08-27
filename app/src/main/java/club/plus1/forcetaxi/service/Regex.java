package club.plus1.forcetaxi.service;

public class Regex {

    /**
     * Метод получения регулярного выражения для проверки телефона
     */
    public static String phone() {
        return "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    }

    /**
     * Метод получения регулярного выражения для проверки ИНН
     */
    public static String tin() {
        return "^(\\d{10}|\\d{12})$";
    }

    /**
     * Метод получения регулярного выражения для проверки даты YYYY-MM-DD
     */
    public static String date() {
        return "(19|20)\\d\\d-((0[1-9]|1[012])-(0[1-9]|[12]\\d)|(0[13-9]|1[012])-30|(0[13578]|1[02])-31)";
    }

    /**
     * Метод получения регулярного выражения для проверки серии паспорта
     */
    public static String passportSeries() {
        return "^(\\d{4})$";
    }

    /**
     * Метод получения регулярного выражения для проверки номера паспорта
     */
    public static String passportNumber() {
        return "^(\\d{6})$";
    }

}
