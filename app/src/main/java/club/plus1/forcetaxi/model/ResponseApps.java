package club.plus1.forcetaxi.model;

public interface ResponseApps {

    // Параметры, возращаемые методами сервера
    boolean ok = false;         // Результат работы метода
    ServerError error = null;   // Описание результата работы с кодом и текстом

    /**
     * Метод регистрации установки приложения
     *
     * @param appId             - айди разработчика
     * @param installationToken - токен разработчика приложения
     * @param description       - коментарий к устанвке, виден только разработчику
     * @return app_token - токен приложения, необходимо передавать в каждом методе сервера
     */
    String registrationInstallation(String appId, String installationToken, String description);
}
