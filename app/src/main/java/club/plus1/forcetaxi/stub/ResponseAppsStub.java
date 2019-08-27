package club.plus1.forcetaxi.stub;

import club.plus1.forcetaxi.model.ResponseApps;
import club.plus1.forcetaxi.model.ServerError;

class ResponseAppsStub implements ResponseApps {

    // Константы для заглушки
    private static final String APP_ID = "4";
    private static final String INSTALL_TOKEN = "7180d024-260e-4d33-bc30-574c0680caf2";
    private static final String APP_TOKEN = "5aa27b1100fa7d9e369f5bc726b05b69";
    // Основные переменные класса
    boolean ok;         // Результат работы метода
    ServerError error;  // Описание результата работы с кодом и текстом

    /**
     * Конструктор класса с заполнением начальными данными
     */
    ResponseAppsStub() {
        this.ok = false;
        this.error = new ServerError("unknown_error", "");
    }

    /**
     * Метод регистрации установки приложения
     *
     * @param appId             - айди разработчика
     * @param installationToken - токен разработчика приложения
     * @param description       - коментарий к устанвке, виден только разработчику
     * @return app_token - токен приложения, необходимо передавать в каждом методе сервера
     */
    @Override
    public String registrationInstallation(String appId, String installationToken, String description) {
        if (!installationToken.equals(INSTALL_TOKEN)) {
            this.ok = false;
            this.error = new ServerError("wrong_app_installation_token");
            return "";
        } else if (appId.isEmpty()) {
            this.ok = false;
            this.error = new ServerError("empty_app_id");
            return "";
        } else if (!appId.equals(APP_ID)) {
            this.ok = false;
            this.error = new ServerError("wrong_app_id");
            return "";
        } else {
            this.ok = true;
            this.error = new ServerError("");
            return APP_TOKEN;
        }
    }
}
