package club.plus1.forcetaxi.stub;

import club.plus1.forcetaxi.model.ResponseApps;
import club.plus1.forcetaxi.model.ServerError;

public class ResponseAppsStub implements ResponseApps {

    // Основные переменные класса
    boolean ok;         // Результат работы метода
    ServerError error;  // Описание результата работы с кодом и текстом

    /**
     * Конструктор класса с заполнением начальными данными
     */
    public ResponseAppsStub() {
        ok = false;
        error = new ServerError("unknown_error", "");
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
        if (!installationToken.equals(ConstantStub.INSTALL_TOKEN)) {
            ok = false;
            error = new ServerError("wrong_app_installation_token");
            return "";
        } else if (appId.isEmpty()) {
            ok = false;
            error = new ServerError("empty_app_id");
            return "";
        } else if (!appId.equals(ConstantStub.APP_ID)) {
            ok = false;
            error = new ServerError("wrong_app_id");
            return "";
        } else {
            ok = true;
            error = new ServerError("");
            return ConstantStub.APP_TOKEN;
        }
    }
}
